ModAPI.require("player"); //Require the player
var GrappleHookMod = {
  oldXYZ: [0, 0, 0], //The previous hook position.
  prev: "NONE", //The previous state
  scaleH: 0.25, //Used for X and Z velocity
  scaleV: 0.15, //((Grapple Y) minus (Player Y)) times scaleV
  lift: 0.4, //Base vertical motion
  crouchToCancel: true //Whether or not crouching should disable the grappling hook.
};
ModAPI.addEventListener("update", () => { //Every client tick
  if (!ModAPI.player.fishEntity) { //If the fish hook does not exist.
    if (GrappleHookMod.prev === "GROUND" && (!GrappleHookMod.crouchToCancel || !ModAPI.player.isSneaking())) { //If the old state was ground
      GrappleHookMod.prev = "NONE"; //Update the state
      var mx = GrappleHookMod.oldXYZ[0] - ModAPI.player.x; //Get delta X
      var my = GrappleHookMod.oldXYZ[1] - ModAPI.player.y; //Get delta Y
      var mz = GrappleHookMod.oldXYZ[2] - ModAPI.player.z; //Get delta Z
      mx *= GrappleHookMod.scaleH; //Multiply by horizontal scale
      my *= GrappleHookMod.scaleV; //Multiply by vertical scale
      mz *= GrappleHookMod.scaleH; //Multiply by horizontal scale
      ModAPI.player.motionX += mx; //Add x motion
      ModAPI.player.motionY += my + GrappleHookMod.lift;  //Add y motion, plus base lift.
      ModAPI.player.motionZ += mz; //Add z motion
      ModAPI.player.reload(); //Push changes
    } else {
      GrappleHookMod.prev = "NONE";
    }
  } else if (GrappleHookMod.prev === "NONE") { //If the hook exists, but the previous state was NONE, update the state.
    GrappleHookMod.prev = "AIR";
  }
  if (
    ModAPI.player.fishEntity !== undefined && //If the fish hook exists
    GrappleHookMod.prev === "AIR" && //And the hook was previously in the air
    ModAPI.player.fishEntity.inGround //And the hook is in the ground
  ) {
    GrappleHookMod.oldXYZ = [ //Set old grapple hook position
      ModAPI.player.fishEntity.x,
      ModAPI.player.fishEntity.y,
      ModAPI.player.fishEntity.z,
    ];
    GrappleHookMod.prev = "GROUND";//Update state
  }
});
