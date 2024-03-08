
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  3 : 8  @  3 : 135

~ 
~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;
~ import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
~ 

> CHANGE  31 : 32  @  31 : 32

~ public class Block extends ModData {

> INSERT  50 : 51  @  50

+ 	protected boolean fullCube = true;

> INSERT  24 : 26  @  24

+ 	public boolean noRender = false;
+ 	public boolean forceRender = false;

> INSERT  1 : 73  @  1

+ 	public void loadModData(BaseData data) {
+ 		unlocalizedName = data.getString("unlocalizedName");
+ 		slipperiness = data.getFloat("slipperiness");
+ 		blockParticleGravity = data.getFloat("blockParticleGravity");
+ 
+ 		minX = data.getDouble("minX");
+ 		minY = data.getDouble("minY");
+ 		minZ = data.getDouble("minY");
+ 		maxX = data.getDouble("maxX");
+ 		maxY = data.getDouble("maxY");
+ 		maxZ = data.getDouble("maxZ");
+ 
+ 		enableStats = data.getBoolean("enableStats");
+ 		needsRandomTick = data.getBoolean("needsRandomTick");
+ 		isBlockContainer = data.getBoolean("isBlockContainer");
+ 		useNeighborBrightness = data.getBoolean("useNeighborBrightness");
+ 		translucent = data.getBoolean("translucent");
+ 		fullBlock = data.getBoolean("fullBlock");
+ 
+ 		lightOpacity = data.getInt("lightOpacity");
+ 		lightValue = data.getInt("lightValue");
+ 
+ 		blockHardness = data.getFloat("blockHardness");
+ 		blockResistance = data.getFloat("blockResistance");
+ 
+ 		noRender = data.getBoolean("noRender");
+ 		forceRender = data.getBoolean("forceRender");
+ 		fullCube = data.getBoolean("fullCube");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.set("unlocalizedName", unlocalizedName);
+ 		data.set("slipperiness", slipperiness);
+ 		data.set("blockParticleGravity", blockParticleGravity);
+ 
+ 		data.set("minX", minX);
+ 		data.set("minY", minY);
+ 		data.set("minZ", minZ);
+ 		data.set("maxX", maxX);
+ 		data.set("maxY", maxY);
+ 		data.set("maxZ", maxZ);
+ 
+ 		data.set("blockMaterial", blockMaterial.makeModData());
+ 
+ 		data.set("enableStats", enableStats);
+ 		data.set("needsRandomTick", needsRandomTick);
+ 		data.set("isBlockContainer", isBlockContainer);
+ 		data.set("useNeighborBrightness", useNeighborBrightness);
+ 		data.set("translucent", translucent);
+ 		data.set("fullBlock", fullBlock);
+ 		data.set("fullCube", fullCube);
+ 
+ 		data.set("lightOpacity", lightOpacity);
+ 		data.set("lightValue", lightValue);
+ 		data.set("blockHardness", blockHardness);
+ 		data.set("blockResistance", blockResistance);
+ 		data.set("noRender", noRender);
+ 		data.set("forceRender", forceRender);
+ 
+ 		data.setCallbackInt("getID", () -> {
+ 			return getIdFromBlock(this);
+ 		});
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		return data;
+ 	}
+ 

> CHANGE  247 : 248  @  247 : 248

~ 	public void randomTick(World world, BlockPos blockpos, IBlockState iblockstate, EaglercraftRandom random) {

> CHANGE  3 : 4  @  3 : 4

~ 	public void updateTick(World var1, BlockPos var2, IBlockState var3, EaglercraftRandom var4) {

> CHANGE  2 : 3  @  2 : 3

~ 	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, EaglercraftRandom rand) {

> CHANGE  18 : 19  @  18 : 19

~ 	public int quantityDropped(EaglercraftRandom random) {

> CHANGE  3 : 4  @  3 : 4

~ 	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {

> DELETE  26  @  26 : 27

> DELETE  25  @  25 : 26

> CHANGE  251 : 252  @  251 : 252

~ 	public int quantityDroppedWithBonus(int fortune, EaglercraftRandom random) {

> INSERT  136 : 137  @  136

+ 		bootstrapStates();

> INSERT  468 : 508  @  468

+ 	public static void bootstrapStates() {
+ 		BlockBed.bootstrapStates();
+ 		BlockDirt.bootstrapStates();
+ 		BlockDoor.bootstrapStates();
+ 		BlockDoublePlant.bootstrapStates();
+ 		BlockFlowerPot.bootstrapStates();
+ 		BlockHugeMushroom.bootstrapStates();
+ 		BlockLever.bootstrapStates();
+ 		BlockLog.bootstrapStates();
+ 		BlockNewLeaf.bootstrapStates();
+ 		BlockNewLog.bootstrapStates();
+ 		BlockOldLeaf.bootstrapStates();
+ 		BlockOldLog.bootstrapStates();
+ 		BlockPistonExtension.bootstrapStates();
+ 		BlockPistonMoving.bootstrapStates();
+ 		BlockPlanks.bootstrapStates();
+ 		BlockPrismarine.bootstrapStates();
+ 		BlockQuartz.bootstrapStates();
+ 		BlockRail.bootstrapStates();
+ 		BlockRailDetector.bootstrapStates();
+ 		BlockRailPowered.bootstrapStates();
+ 		BlockRedSandstone.bootstrapStates();
+ 		BlockRedstoneComparator.bootstrapStates();
+ 		BlockRedstoneWire.bootstrapStates();
+ 		BlockSand.bootstrapStates();
+ 		BlockSandStone.bootstrapStates();
+ 		BlockSapling.bootstrapStates();
+ 		BlockSilverfish.bootstrapStates();
+ 		BlockSlab.bootstrapStates();
+ 		BlockStairs.bootstrapStates();
+ 		BlockStone.bootstrapStates();
+ 		BlockStoneBrick.bootstrapStates();
+ 		BlockStoneSlab.bootstrapStates();
+ 		BlockStoneSlabNew.bootstrapStates();
+ 		BlockTallGrass.bootstrapStates();
+ 		BlockTrapDoor.bootstrapStates();
+ 		BlockWall.bootstrapStates();
+ 		BlockWoodSlab.bootstrapStates();
+ 	}
+ 

> INSERT  43 : 47  @  43

+ 
+ 	public boolean eaglerShadersShouldRenderGlassHighlights() {
+ 		return false;
+ 	}

> EOF
