//Tracer hack w/ NoReflect
ModAPI.require("player");

//init function
function initTracers() {
    //Get necessary classes and store them
    const classes = {
        EntityPlayerSP: ModAPI.reflect.getClassByName("EntityPlayerSP"),
        EntityPlayer: ModAPI.reflect.getClassByName("EntityPlayer"),
        EntityItem: ModAPI.reflect.getClassByName("EntityItem"),
        EntityAnimal: ModAPI.reflect.getClassByName("EntityAnimal"),
        EntityMob: ModAPI.reflect.getClassByName("EntityMob"),
        GlStateManager: ModAPI.reflect.getClassByName("GlStateManager"),
        EaglercraftGPU: ModAPI.reflect.getClassByName("EaglercraftGPU"),
        MathHelper: ModAPI.reflect.getClassByName("MathHelper"),
        EntityRenderer: ModAPI.reflect.getClassByName("EntityRenderer"),
        Tessellator: ModAPI.reflect.getClassByName("Tessellator"),
        WorldRenderer: ModAPI.reflect.getClassByName("WorldRenderer")
    };

    //Build a method map object, to avoid searching for methods multiple times over.
    const methodMaps = {};
    var usedClasses = Object.keys(classes);
    usedClasses.forEach((className)=>{
        methodMaps[className] = ModAPI.reflect.getMethodMapFromClass(classes[className]);
    });

    console.log(methodMaps);

    //Get the vertex format for 'POSITION'
    const positionVertexFormat = ModAPI.reflect.getClassByName("VertexFormat").class.$platformClass.$$enumConstants$$().data[5];

    //Utility functions for type checking
    function isEntityPlayerSP(obj) {
        return classes.EntityPlayerSP.isObjInstanceOf({ obj: obj });
    }

    function isEntityPlayer(obj) {
        return classes.EntityPlayer.isObjInstanceOf({ obj: obj });
    }

    function isEntityItem(obj) {
        return classes.EntityItem.isObjInstanceOf({ obj: obj });
    }

    function isEntityAnimal(obj) {
        return classes.EntityAnimal.isObjInstanceOf({ obj: obj });
    }

    function isEntityMob(obj) {
        return classes.EntityMob.isObjInstanceOf({ obj: obj });
    }

    
    //Utility functions for running methods on classes/instances of classes by referencing the created methodMaps object.
    function glFunction(name, args) {
        return methodMaps["GlStateManager"][name].exec(args);
    }

    function gpuFunction(name, args) {
        return methodMaps["EaglercraftGPU"][name].exec(args);
    }

    function entityRendererFunction(name, args) {
        return methodMaps["EntityRenderer"][name].exec(args);
    }

    function mathHelperFunction(name, args) {
        return methodMaps["MathHelper"][name].exec(args);
    }

    function tessellatorFunction(name, args) {
        return methodMaps["Tessellator"][name].exec(args);
    }

    function worldRendererFunction(name, args) {
        return methodMaps["WorldRenderer"][name].exec(args);
    }


    //Function to get the player's look vector (position right in front of their nose)
    function getClientLookVec() {
        var f = mathHelperFunction("cos", {value: -ModAPI.player.rotationYaw * 0.017453292 - Math.PI});
        var f1 = mathHelperFunction("sin", {parFloat1: -ModAPI.player.rotationYaw * 0.017453292 - Math.PI});
        var f2 = -mathHelperFunction("cos", {value: -ModAPI.player.rotationPitch * 0.017453292});
        var f3 = mathHelperFunction("sin", {parFloat1: -ModAPI.player.rotationPitch * 0.017453292});
        return [f1 * f2, f3 + ModAPI.player.getEyeHeight(), f * f2];
    }

    //Function to draw a line between two poitns
    function drawLine(start, end) {
        //Get the tessellator by running Tessellator.getInstance()
        var tessellator = tessellatorFunction("getInstance", {});

        //Get the WorldRenderer instance by running tessellator.getWorldRenderer()
        var worldrenderer = tessellatorFunction("getWorldRenderer", {
            _self: tessellator
        });

        //Run worldrenderer.begin(3, positionVertexFormat) to start building the lines
        worldRendererFunction("begin", {
            _self: worldrenderer,
            parInt1: 3,
            parVertexFormat: positionVertexFormat
        });

        //Add the start position and end the vertex immediately.
        worldRendererFunction("endVertex", {
            _self: worldRendererFunction("pos", {
                _self: worldrenderer,
                parDouble1: start[0],
                parDouble2: start[1],
                parDouble3: start[2]
            })
        });

        //Add the start position and end the vertex immediately.
        worldRendererFunction("endVertex", {
            _self: worldRendererFunction("pos", {
                _self: worldrenderer,
                parDouble1: end[0],
                parDouble2: end[1],
                parDouble3: end[2]
            })
        });

        //Draw to screen
        tessellatorFunction("draw", {
            _self: tessellator
        });
    }

    //Every time a frame is rendered
    ModAPI.addEventListener("render", (event) => {
        //Check if both the player and the world instance exist
        if (ModAPI.player && ModAPI.mcinstance.$theWorld) {
            //Store world and render manager
            const world = ModAPI.mcinstance.$theWorld;
            const renderManager = ModAPI.mcinstance.$renderManager;

            //Loop through loaded entities
            for (let i = 0; i < world.$loadedEntityList.$array1.data.length; i++) {
                const entity = world.$loadedEntityList.$array1.data[i];

                //Checks to avoid tracing to self and invalid entities
                if (!entity || isEntityPlayerSP(entity)) {
                    continue;
                }
                if (!(isEntityAnimal(entity) || isEntityItem(entity) || isEntityMob(entity) || isEntityPlayer(entity))) {
                    continue;
                }

                //Temporarily disable view bobbing
                var bobbing = ModAPI.mcinstance.$gameSettings.$viewBobbing;
                ModAPI.mcinstance.$gameSettings.$viewBobbing = 0;

                //Update camera transform to remove view bobbing
                entityRendererFunction("setupCameraTransform", {
                    partialTicks: event.partialTicks,
                    pass: 0
                });

                //WebGL commands to disable depth-test & depth-write, as well as selecting a blend function and line width.
                glFunction("blendFunc", { srcFactor: 770, dstFactor: 771 });
                glFunction("enableBlend", {});
                gpuFunction("glLineWidth", { f: 3.0 });
                glFunction("disableTexture2D", {});
                glFunction("disableDepth", {});
                glFunction("depthMask", { flagIn: false });

                //Choose tracer color based on entity type.
                if (isEntityPlayer(entity)) {
                    glFunction("color", { colorRed: 1, colorGreen: 0, colorBlue: 0, colorAlpha: 0.5 });
                } else if (isEntityAnimal(entity)) {
                    glFunction("color", { colorRed: 0, colorGreen: 0, colorBlue: 1, colorAlpha: 0.5 });
                } else if (isEntityMob(entity)) {
                    glFunction("color", { colorRed: 1, colorGreen: 1, colorBlue: 0, colorAlpha: 0.5 });
                } else if (isEntityItem(entity)) {
                    glFunction("color", { colorRed: 0, colorGreen: 1, colorBlue: 1, colorAlpha: 0.5 });
                }

                //Start is equal to the client look vector
                var start = getClientLookVec();

                //End is equal to the center of the entities' bounding box minus the render position.
                var end = [
                    ((entity.$boundingBox.$minX0 + entity.$boundingBox.$maxX0) / 2) - 0.05 - renderManager.$renderPosX,
                     ((entity.$boundingBox.$minY0 + entity.$boundingBox.$maxY0) / 2) - 0.05 - renderManager.$renderPosY,
                     ((entity.$boundingBox.$minZ0 + entity.$boundingBox.$maxZ0) / 2) - 0.05 - renderManager.$renderPosZ
                ];

                //Draw the line
                drawLine(start, end);

                //Restore the gl state
                glFunction("enableTexture2D", {});
                glFunction("depthMask", { flagIn: true });
                glFunction("enableDepth", {});
                glFunction("disableBlend", {});

                //Restore view bobbing
                ModAPI.mcinstance.$gameSettings.$viewBobbing = bobbing;
                entityRendererFunction("setupCameraTransform", {
                    partialTicks: event.partialTicks,
                    pass: 0
                });
            }
        }
    });
}
initTracers();