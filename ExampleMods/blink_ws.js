//Blink hack mod that prototype pollutes WebSocket to implement itself.

var blinking = false;
var backlog = [];
const originalSend = WebSocket.prototype.send;

Object.defineProperty(WebSocket.prototype, 'send', {
  configurable: true,
  enumerable: false,
  writable: false,
  value: function(data) {
    if (blinking) {
        backlog.push({data: data, thisArg: this});
    } else {
        originalSend.call(this, data);
    }
  }
});


ModAPI.addEventListener("key", (ev)=>{
    if (ev.key === 48) {
        ev.preventDefault = true;
        blinking = !blinking;
        if (blinking === false) {
            for (let i = 0; i < backlog.length; i++) {
                const backlogItem = backlog[i];
                originalSend.call(backlogItem.thisArg, backlogItem.data);
            }
            backlog = [];
        }
    }
});