//Blink hack mod that prototype pollutes WebSocket to implement itself.

//Blinking state
var blinking = false;

//The backlog of packets that need to be sent on disable
var backlog = [];

//Store the original, actual WebSocket send method
const originalSend = WebSocket.prototype.send;

//Override WebSocket.send, so when eagler tries to send messages, it runs our code instead
Object.defineProperty(WebSocket.prototype, 'send', {
  configurable: true,
  enumerable: false,
  writable: false,
  value: function(data) {
    //If blinking, push data to backlog along with it's websocket instance.
    if (blinking) {
        backlog.push({data: data, thisArg: this});
    } else { //Else send the data as normal
        originalSend.call(this, data);
    }
  }
});


ModAPI.addEventListener("key", (ev)=>{
    if (ev.key === 48) { //KEY_B
        ev.preventDefault = true;
        blinking = !blinking; //Toggle blinking boolean

        if (blinking === false) { //If blink just turned off, send data.
            for (let i = 0; i < backlog.length; i++) {
                const backlogItem = backlog[i];
                originalSend.call(backlogItem.thisArg, backlogItem.data);
            }
            backlog = [];
        }
    }
});