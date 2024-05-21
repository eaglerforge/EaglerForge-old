const getStatements = {
    "int": "params.getInt(%s)",
    "String": "params.getString(%s)",
    "boolean": "params.getBoolean(%s)",
    "float": "params.getFloat(%s)",
    "byte": "params.getByte(%s)",
    "char": "params.getChar(%s)",
    "double": "params.getDouble(%s)",
}
const getStatementsTypes = Object.keys(getStatements);
function reconJ(java, className) {
    let constructorRegex = /(public|protected|private|static|\s) +(\w+) *\(([^)]*)\)/g;

    let constructors = [...java.matchAll(constructorRegex).filter((line)=>{
        return !line.includes(" private ") || !line.includes(" protected ");
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
            
            argStr += `(${arguments[key]}) ${getStatementsTypes.includes(arguments[key]) ? getStatements[arguments[key]].replaceAll("%s", "\""+key+"\"") : `params.getReflective("${key}")`}`;
            if (i !== argumentsKeys.length - 1) {
                argStr += ", "
            }
        }


        let impl = `
        setCallbackReflectiveWithArgs("${constructorName}", (BaseData params) -> {
            return new ${className}(${argStr})
        });
        `;

        return {
            name: constructorName,
            idx: constructors.indexOf(constructor),
            arguments: arguments,
            impl: impl
        };
    });
    

    return {
        className: className,
        constructors: constructorDetails
    }
}