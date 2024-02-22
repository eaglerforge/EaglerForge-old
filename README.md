# Eaglerforge v1

### Play Minecraft 1.8 in your browser, supports singleplayer and multiplayer with a javascript modding api

![EaglercraftX 1.8 Screenshot Main Menu](https://github.com/eaglerforge/EaglerForge/assets/95340057/474f88c5-3574-4cd2-9780-3d55f94a13e7)


### eaglerforge mods menu screenshot
![305109047-1985db59-ed58-4c6c-84cf-51c3ff996fa9](https://github.com/eaglerforge/EaglerForge/assets/95340057/72d73c72-a8ad-429b-b00a-c3e5d9ab0739)



### This repository contains:

 - **Utilities to decompile Minecraft 1.8 and apply patch files to it**
 - **Source code to provide the LWJGL keyboard, mouse, and OpenGL APIs in a browser**
 - **Patch files to mod the Minecraft 1.8 source code to make it browser-compatible**
 - **Browser-modified portions of Minecraft 1.8's open-source dependencies**
 - **Plugins for Minecraft servers to allow the Eagler client to connect to them**
 - **a javascript modding API**

### This repository does NOT contain:

 - **Any portion of the decompiled Minecraft 1.8 source code or resources**
 - **Any portion of Mod Coder Pack and its config files**
 - **Data that can be used alone to reconstruct portions of the game's source code**
 - **Software configured by default to allow users to play without owning a copy of Minecraft**

## Getting Started:

### To compile the latest version of the client, on Windows:

1. Make sure you have at least Java 11 installed and added to your PATH
2. Download (clone) this repository to your computer
3. Double click `CompileLatestClient.bat`, a GUI resembling a classic Windows installer should open
4. Follow the steps shown to you in the new window to finish compiling

### To compile the latest version of the client, on Linux/macOS:

1. Make sure you have at least Java 11 installed
2. Download (clone) this repository to your computer
3. Open a terminal in the folder the repository was cloned to
4. Type `chmod +x CompileLatestClient.sh` and hit enter
5. Type `./CompileLatestClient.sh` and hit enter, a GUI resembling a classic Windows installer should open
6. Follow the steps shown to you in the new window to finish compiling


## Launch Options

The EaglercraftX 1.8 client is configured primarily through a variable called `window.eaglercraftXOpts` that must be set before the client starts up.

The default eaglercraftXOpts values are this:

    const relayId = Math.floor(Math.random() * 3);
    window.eaglercraftXOpts = {
        demoMode: false,
        container: "game_frame",
        assetsURI: "assets.epk",
        localesURI: "lang/",
        worldsDB: "worlds",
        servers: [
            { addr: "ws://localhost:8081/", name: "Local test server" }
        ],
        relays: [
            { addr: "wss://relay.deev.is/", comment: "lax1dude relay #1", primary: relayId == 0 },
            { addr: "wss://relay.lax1dude.net/", comment: "lax1dude relay #2", primary: relayId == 1 },
            { addr: "wss://relay.shhnowisnottheti.me/", comment: "ayunami relay #1", primary: relayId == 2 }
        ]
    };

### List of available options

- `container:` the ID of the HTML element to create the canvas in **(required)**
- `assetsURI:` the URL of the assets.epk file **(required)**
- `localesURI:` the URL where extra .lang files can be found
- `worldsDB:` the name of the IndexedDB database to store worlds in
- `demoMode:` whether to launch the game in Java edition demo mode
- `servers:` a list of default servers to display on the Multiplayer screen
- `relays:` the default list of shared world relays to use for invites
- `checkShaderGLErrors:` enables more verbose OpenGL error logging for the shaders
- `enableDownloadOfflineButton:` whether to show a "Download Offline" button on the title screen
- `downloadOfflineButtonLink:` overrides the download link for the "Download Offline" button
- `html5CursorSupport:` enables support for showing the CSS "pointer" cursor over buttons
- `allowUpdateSvc:` enables the certificate-based update system
- `allowUpdateDL:` allows the client to download new updates it finds
- `logInvalidCerts:` print update certificates with invalid signatures to console
- `enableSignatureBadge:` shows a badge on the title screen indicating if the digital signature is valid
- `checkRelaysForUpdates:` proprietary feature used in offline downloads
- `mods` a list of mods you want to load into the game when it launches
