const getStatements = {
    "int": "params.getInt(%s)",
    "String": "params.getString(%s)",
    "boolean": "params.getBoolean(%s)",
    "float": "params.getFloat(%s)",
    "byte": "params.getByte(%s)",
    "char": "params.getChar(%s)",
    "double": "params.getDouble(%s)",
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

    let constructorRegex = /(public|protected|private|static|\s) +(\w+) *\(([^)]*)\)/g;

    let constructors = [...javaText.matchAll(constructorRegex).filter((line)=>{
        return !line[0].includes(" private ") && !line[0].includes(" protected ");
    })];

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


        let impl = `setCallbackReflectiveWithArgs("${constructorName}", (BaseData params) -> {
            return new ${className}(${argStr});
        });
        `;

        return {
            name: constructorName,
            idx: constructors.indexOf(constructor),
            arguments: arguments,
            impl: impl
        };
    });

    let methodRegex = /(public|static|\s)* +([\w\<\>\[\]]+)\s+(\w+) *\(([^)]*)\)/g;

    let methods = [...javaText.matchAll(methodRegex).filter((line)=>{
        return !line[0].includes("> ") && !line[0].startsWith(" else ") && !line[0].startsWith(" new ") && !line[0].includes(" private ") && !line[0].includes(" protected ");
        //Doesn't support Type<Subtype> yet
    })];

    let methodDetails = methods.map((method) => {
        let isStatic = `${method[1]}`.includes("static");
        let returnType = method[2];
        let methodName = method[3];
        let argumentString = method[4];
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

        let impl;
        if (returnType === "void") {
            impl = `setCallbackVoidWithArgs("${methodName}", (BaseData params) -> {
                ((${className}) params.get("_self")).${methodName}(${argStr});
            });
            `;
        } else if (callbackStatementsTypes.includes(returnType)) {
            impl = `${callbackStatements[returnType]}("${methodName}", (BaseData params) -> {
                return (${returnType}) ((${className}) params.get("_self")).${methodName}(${argStr});
            });
            `;
        } else {
            usedClasses.push(returnType);
            impl = `setCallbackReflectiveWithArgs("${methodName}", (BaseData params) -> {
                return (${returnType}) ((${className}) params.get("_self")).${methodName}(${argStr});
            });
            `;
        }
        

        return {
            name: methodName,
            returnType: returnType,
            isStatic: isStatic,
            arguments: arguments,
            impl: impl
        };
    });
    return {
        className: className,
        constructors: constructorDetails,
        methods: methodDetails,
        usedClasses: [...new Set(usedClasses)]
    }
}