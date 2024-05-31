const getStatements = {
    "int": "params.getInt(%s)",
    "String": "params.getString(%s)",
    "boolean": "params.getBoolean(%s)",
    "float": "params.getFloat(%s)",
    "byte": "params.getByte(%s)",
    "char": "params.getChar(%s)",
    "double": "params.getDouble(%s)",
}
const primitiveDefaultValues = {
    "int": "0",
    "String": "\"\"",
    "boolean": "false",
    "float": "0.0f",
    "byte": "(byte) 0",
    "char": "\'a\'",
    "double": "0.0d",
}
const callbackStatements = {
    "boolean": "setCallbackBooleanWithDataArg",
    "int": "setCallbackIntWithDataArg",
    "String": "setCallbackStringWithDataArg",
    "double": "setCallbackDoubleWithDataArg",
    "float": "setCallbackFloatWithDataArg",
}
const getStatementsTypes = Object.keys(getStatements);
const callbackStatementsTypes = Object.keys(callbackStatements);
function reconJ(java, className) {
    var usedClasses = [];
    var javaText = java;

    javaText = javaText.replace(/\/\/.*$/gm, '');
    javaText = javaText.replace(/\/\*[\s\S]*?\*\//gm, '');
    javaText = javaText.replace(/[ \t](public|private|protected|)\s*(static|) (class|enum) .+ \{(\W|\w)*?\n(\t)\}/gm, '');

    let constructorRegex = /(public|protected|private|static|\s) +(\w+) *\(([^)]*)\)/g;

    let constructors = [...javaText.matchAll(constructorRegex).filter((line)=>{
        return !line[0].includes(" private ") && !line[0].includes(" protected ") && !line[0].includes("\n\t\t") && line[1] !== "private" && line[1] !== "protected";
    })];

    if (javaText.match(/^(public|private|protected|) abstract class /gm)) {
        constructors = [];
    }

    let constructorDetails = constructors.map((constructor) => {
        let constructorName = constructor[2];
        let argumentString = constructor[3];
        let arguments = {};

        if (argumentString.trim().length > 0) {
            let argumentList = argumentString.split(",");
            argumentList.forEach((argument) => {
                let [type, name] = argument.trim().split(" ");
                arguments[name] = type;
            });
        }

        let argStr = "";
        var argumentsKeys = Object.keys(arguments);
        for (let i = 0; i < argumentsKeys.length; i++) {
            const key = argumentsKeys[i];
            if (!getStatementsTypes.includes(arguments[key])) {
                usedClasses.push(arguments[key]);
            }
            argStr += `(${arguments[key]}) ${getStatementsTypes.includes(arguments[key]) ? getStatements[arguments[key]].replaceAll("%s", "\""+key+"\"") : `params.getReflective("${key}")`}`;
            if (i !== argumentsKeys.length - 1) {
                argStr += ", "
            }
        }


        let impl = `setCallbackReflectiveWithDataArg("exec", (BaseData params) -> {
            return new ${className}(${argStr});
        });
        `;

        return {
            name: constructorName,
            idx: constructors.indexOf(constructor),
            arguments: arguments,
            impl: impl,
            data: constructor
        };
    });

    let methodRegex = /(public|static|private|protected|final|\s)* +([\w\<\>\[\]]+)\s+(\w+) *\(([^)]*)\)/g;

    let methods = [...javaText.matchAll(methodRegex).filter((line)=>{
        return !line[0].includes("> ") && !line[0].startsWith(" else ") && !line[0].startsWith(" new ") && !line[0].includes(" private ") && !line[0].includes("\tprotected ") && !line[0].includes("\tprivate ") && !line[0].includes(" protected ") && !line[0].includes("\n\t\t") && line[0].includes("public ");
        //Doesn't support Type<Subtype> yet
    })];

    let methodDetails = methods.map((method) => {
        let isStatic = `${method[1]}`.includes("static");
        let returnType = method[2];
        let methodName = method[3];
        let argumentString = method[4];
        let arguments = {};

        let methodContainsInlineClasses = false;
        if (argumentString.trim().length > 0) {
            let argumentList = argumentString.split(",");
            argumentList.forEach((argument) => {
                let [type, name] = argument.trim().split(" ").filter(potential => {
                    return potential !== "final";
                });
                if (type.includes(".")) {
                    methodContainsInlineClasses = true;
                }
                arguments[name] = type;
            });
        }

        if (methodContainsInlineClasses) {
            return false;
        }

        let argStr = "";
        var argumentsKeys = Object.keys(arguments);
        for (let i = 0; i < argumentsKeys.length; i++) {
            const key = argumentsKeys[i];
            if (!getStatementsTypes.includes(arguments[key])) {
                usedClasses.push(arguments[key]);
            }
            argStr += `(${arguments[key]}) ${getStatementsTypes.includes(arguments[key]) ? getStatements[arguments[key]].replaceAll("%s", "\""+key+"\"") : `params.getReflective("${key}")`}`;
            if (i !== argumentsKeys.length - 1) {
                argStr += ", "
            }
        }
        let prefix = isStatic ? className : `((${className}) params.getReflective("_self"))`;
        let impl;
        if (returnType === "void") {
            impl = `setCallbackVoidWithDataArg("exec", (BaseData params) -> {
                try {
                    ${prefix}.${methodName}(${argStr});
                } catch (Exception _exception_reflect_) {
                    return;
                }
            });
            `;
        } else if (callbackStatementsTypes.includes(returnType)) {
            impl = `${callbackStatements[returnType]}("exec", (BaseData params) -> {
                try {
                    return (${returnType}) ${prefix}.${methodName}(${argStr});
                } catch (Exception _exception_reflect_) {
                    return ${primitiveDefaultValues[returnType]};
                }
            });
            `;
        } else {
            usedClasses.push(returnType);
            impl = `setCallbackReflectiveWithDataArg("exec", (BaseData params) -> {
                try {
                    return (${returnType}) ${prefix}.${methodName}(${argStr});
                } catch (Exception _exception_reflect_) {
                    return null;
                }
            });
            `;
        }
        

        return {
            name: methodName,
            returnType: returnType,
            isStatic: isStatic,
            arguments: arguments,
            impl: impl,
            idx: methods.indexOf(method),
            data: method
        };
    });
    return {
        className: className,
        constructors: constructorDetails.filter(obj => obj),
        methods: methodDetails.filter(obj => obj),
        usedClasses: [...new Set(usedClasses)]
    }
}