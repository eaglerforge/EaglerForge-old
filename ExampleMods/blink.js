ModAPI.require("network");
ModAPI.require("player");
var packetsOnTodoList = [];
var blinking = false;
ModAPI.addEventListener("event", (ev) => {
  if (
    blinking &&
    ev.event.startsWith("sendpacket") &&
    ev.event !== "sendpacketkeepalive"
  ) {
    ev.data.preventDefault = true;
    packetsOnTodoList.push(ev);
  }
});
function blinkOn() {
  if (blinking === true) {
    return;
  }
  blinking = true;
  ModAPI.displayToChat({ msg: "Blink activated." });
}
function blinkOff() {
  if (blinking === false) {
    return;
  }
  blinking = false;
  ModAPI.displayToChat({ msg: "Blink deactivated." });
  packetsOnTodoList.forEach((ev) => {
    var data = ev.data;
    switch (ev.event) {
      case "sendpacketanimation":
        ModAPI.network.sendPacketAnimation();
        break;
      case "sendpacketentityaction":
        ModAPI.network.sendPacketEntityAction({
          entityId: data.entityID,
          action: data.action,
          auxData: data.auxData,
        });
        break;
      case "sendpacketinput":
        ModAPI.network.sendPacketInput({
          strafeSpeed: data.strafeSpeed,
          forwardSpeed: data.forwardSpeed,
          jumping: data.jumping,
          sneaking: data.sneaking,
        });
        break;
      case "sendpacketclosewindow":
        ModAPI.network.sendPacketCloseWindow({ windowId: data.windowId });
        break;
      case "sendpacketclickwindow":
        ModAPI.network.sendPacketClickWindow({
          windowId: data.windowId,
          slotId: data.slotId,
          usedButton: data.usedButton,
          mode: data.mode,
          clickedItemRef: data.clickedItem ? data.clickedItem.getRef() : {},
          actionNumber: data.actionNumber,
        });
        break;
      case "sendpacketconfirmtransaction":
        ModAPI.network.sendPacketConfirmTransaction({
          windowId: data.windowId,
          uid: data.uid,
          accepted: data.accepted,
        });
        break;
      case "sendpacketchatmessage":
        ModAPI.network.sendPacketConfirmTransaction({
          messageIn: data.message,
        });
        break;
      case "sendpacketuseentity":
        ModAPI.network.sendPacketUseEntity({
          entityId: data.entityId,
          action: data.action,
        });
        break;
      case "sendpacketplayerposition":
        ModAPI.network.sendPacketPlayerPosition({
          posX: data.x,
          posY: data.y,
          posZ: data.z,
          isOnGround: data.onGround,
        });
      case "sendpacketplayerlook":
        ModAPI.network.sendPacketPlayerLook({
          playerYaw: data.yaw,
          playerPitch: data.pitch,
          isOnGround: data.onGround,
        });
        break;
      case "sendpacketplayerposlook":
        ModAPI.network.sendPacketPlayerPosLook({
          playerX: data.x,
          playerY: data.y,
          playerZ: data.z,
          playerYaw: data.yaw,
          playerPitch: data.pitch,
          isOnGround: data.onGround,
        });
      case "sendpacketplayer":
        ModAPI.network.sendPacketPlayer({
          isOnGround: data.onGround,
        });
        break;
      case "sendpacketplayerdigging":
        ModAPI.network.sendPacketPlayerDigging({
          pos: data.position,
          facing: data.facing,
          action: data.status,
        });
        break;
      case "sendpacketplayerblockplacement":
        ModAPI.network.sendPacketPlayerBlockPlacement({
          stackRef: data.stack.getRef(),
          posRef: data.position.getRef(),
          placedBlockDirectionIn: data.placedBlockDirection,
          facingXIn: data.facingX,
          facingYIn: data.facingY,
          facingZIn: data.facingZ,
        });
        break;
      case "sendpackethelditemchange":
        ModAPI.network.sendPacketHeldItemChange({
          slotId: data.slotId,
        });
        break;
      case "sendpacketcreativeinventoryaction":
        ModAPI.network.sendPacketCreativeInventoryAction({
          slotId: data.slotId,
          stackRef: data.stack.getRef(),
        });
        break;
      case "sendpacketenchantitem":
        ModAPI.network.sendPacketEnchantItem({
          windowId: data.windowId,
          button: data.button,
        });
        break;
      case "sendpacketupdatesign":
        ModAPI.network.sendPacketUpdateSign({
          pos: data.pos,
          lines: data.lines,
        });
        break;
      case "sendpacketplayerabilities":
        ModAPI.network.sendPacketPlayerAbilities({
          capabilitiesRef: ModAPI.player.capabilities.getRef(),
        });
        break;
      case "sendpackettabcomplete":
        ModAPI.network.sendPacketTabComplete({
          msg: data.message,
          target: data.targetBlock,
        });
        break;
      case "sendpacketclientsettings":
        ModAPI.network.sendPacketClientSettings({
          lang: data.lang,
          view: data.view,
          chatVisibility: data.chatVisibility,
          enableColors: data.enableColors,
          modelPartFlags: data.modelPartFlags,
        });
        break;
      case "sendpacketclientstatus":
        ModAPI.network.sendPacketClientStatus({
          status: data.status,
        });
        break;
      case "sendpacketspectate":
        ModAPI.network.sendPacketSpectate({
          uuid: data.id,
        });
        break;
      case "sendpacketresourcepackstatus":
        ModAPI.network.sendPacketResourcePackStatus({
          hash: data.hash,
          status: data.status,
        });
        break;
      default:
        break;
    }
  });
  packetsOnTodoList = [];
}
ModAPI.addEventListener("sendchatmessage", (ev) => {
  if (ev.message.toLowerCase().trim() === ".blinkon") {
    ev.preventDefault = true;
    blinkOn();
  }
  if (ev.message.toLowerCase().trim() === ".blinkoff") {
    ev.preventDefault = true;
    blinkOff();
  }
});
