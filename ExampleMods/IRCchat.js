class IRCServer {
    constructor(opCodes, mcUsername) {
        this.opCodes = opCodes;
        this.mcUsername = mcUsername;
        this.userKey = null;
        this.pingIntervalId = null;
    }

    connect() {
        this.mcUsername = ModAPI.getProfileName();
        // Create WebSocket connection
        this.socket = new WebSocket('wss://brick-iron-shift.glitch.me');

        // Set up WebSocket event listeners
        this.socket.addEventListener('open', () => this.onOpen());
        this.socket.addEventListener('message', event => this.onMessage(event.data));
        this.socket.addEventListener('close', () => this.onClose());
        this.socket.addEventListener('error', () => {
            this.sendIRCMessage('WebSocket error occurred');
        });
    }

    onOpen() {
        console.log('[IRC] Connected to IRC');

        const object = {
            op: this.opCodes.CONNECT,
            d: this.mcUsername
        };

        this.sendMessage(JSON.stringify(object));

        // Set a periodic task for pinging the server
        this.pingIntervalId = setInterval(() => {
            const ping = {
                op: this.opCodes.PING,
                d: ''
            };
            this.sendMessage(JSON.stringify(ping));
        }, 20000);

        this.sendIRCMessage('Connected to IRC');
    }

    onMessage(message) {
        const object = JSON.parse(message);

        if (object.hasOwnProperty('op') && object.hasOwnProperty('d')) {
            const opCode = object.op;
            const data = object.d;

            switch (opCode) {
                case this.opCodes.ID:
                    this.userKey = data;
                    console.log('[IRC] User key received.');
                    break;

                case this.opCodes.MESSAGE_RECEIVE:
                    this.sendIRCMessage(data);
                    break;

                case this.opCodes.DISCONNECTED:
                    this.sendIRCMessage(`Disconnected from IRC: ${data}`);
                    break;

                case this.opCodes.INVALID:
                    console.log('[IRC] Received an invalid opcode.');
                    break;
                case this.opCodes.PING:
                    break;

                default:
                    console.log(`[IRC] Unknown opcode: ${opCode}`);
                    break;
            }
        }
    }

    onClose() {
        this.sendIRCMessage('You were disconnected from the IRC chat.');
        console.log('[IRC] Disconnected.');
        if (this.pingIntervalId) {
            clearInterval(this.pingIntervalId);
        }
    }

    disconnect() {
        if (this.pingIntervalId) {
            clearInterval(this.pingIntervalId);
        }

        if (this.socket) {
            this.socket.close();
        }
    }

    sendMessage(msg) {
        if (this.socket.readyState === WebSocket.OPEN) {
            this.socket.send(msg);
        } else {
            this.sendIRCMessage('You are not currently connected to the IRC server');
        }
    }

    sendIRCChatMessage(message) {
        const object = {
            op: this.opCodes.MESSAGE_SEND,
            d: message
        };

        this.sendMessage(JSON.stringify(object));
    }

    sendIRCMessage(message) {
        // Assuming you have a function or a way to display messages in your IRC client
        ModAPI.displayToChat({msg: `[IRC MESSAGE]: ${message}`});
    }
}

// Define the OpCodes based on the provided Java interface
const OpCodes = {
    DISCONNECTED: 6969,
    INVALID: 69420,
    ID: -1,
    CONNECT: 0,
    MESSAGE_SEND: 1,
    MESSAGE_RECEIVE: 2,
    PING: 90
};

var mcUsername = ModAPI.getProfileName();

var ircServer = new IRCServer(OpCodes, mcUsername);


ModAPI.addEventListener("sendchatmessage", (ev) => {
    const message = ev.message;
    

    if (message.startsWith("@")) {
        const parsed = message.substring(1);
        ircServer.sendIRCChatMessage(parsed);
    } else if (message.startsWith(".")) {
        if (message === ".help") {
            ModAPI.displayToChat({ msg: "IRC> Here's a list of commands:" });
            ModAPI.displayToChat({ msg: ".connect - Connect to the IRC server" });
            ModAPI.displayToChat({ msg: ".disconnect - Disconnect from the IRC server" });
            ModAPI.displayToChat({ msg: "To send a message to the IRC server, use @<your message>" });
        } else if (message === ".connect") {
            ircServer.connect();
            ModAPI.displayToChat({ msg: "IRC> Connecting to IRC..." });
        } else if (message === ".disconnect") {
            ircServer.disconnect();
            ModAPI.displayToChat({ msg: "IRC> Disconnecting from IRC..." });
        } else {
            ModAPI.displayToChat({ msg: "IRC> Unknown command. Type .help for a full list of commands!" });
        }
    }
    ev.preventDefault = true;
});
