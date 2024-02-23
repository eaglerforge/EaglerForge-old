
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 3

> INSERT  1 : 7  @  1

+ 
+ import com.google.common.collect.AbstractIterator;
+ 
+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModData;
+ import net.minecraft.client.Minecraft;

> DELETE  1  @  1 : 5

> INSERT  32 : 78  @  32

+ 	@Override
+ 	public void loadModData(BaseData data) {
+ 		super.loadModData(data);
+ 	}
+ 
+ 	public static BlockPos fromModData(BaseData data) {
+ 		return new BlockPos(Vec3i.fromModData(data));
+ 	}
+ 
+ 	@Override
+ 	public ModData makeModData() {
+ 		ModData data = super.makeModData();
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.setCallbackObjectWithDataArg("add", (BaseData params) -> {
+ 			return add(params.getInt("x"), params.getInt("y"), params.getInt("z")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("up", (BaseData params) -> {
+ 			return up(params.getInt("n")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("down", (BaseData params) -> {
+ 			return down(params.getInt("n")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("north", (BaseData params) -> {
+ 			return north(params.getInt("n")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("south", (BaseData params) -> {
+ 			return south(params.getInt("n")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("east", (BaseData params) -> {
+ 			return east(params.getInt("n")).makeModData();
+ 		});
+ 		data.setCallbackObjectWithDataArg("west", (BaseData params) -> {
+ 			return west(params.getInt("n")).makeModData();
+ 		});
+ 		data.setCallbackObject("getBlock", () -> {
+ 			loadModData(data);
+ 			return Minecraft.getMinecraft().theWorld.getBlock(this).makeModData();
+ 		});
+ 		return data;
+ 	}
+ 

> INSERT  23 : 33  @  23

+ 	/**
+ 	 * eagler
+ 	 */
+ 	public BlockPos up(BlockPos dst) {
+ 		dst.x = x;
+ 		dst.y = y + 1;
+ 		dst.z = z;
+ 		return dst;
+ 	}
+ 

> INSERT  8 : 18  @  8

+ 	/**
+ 	 * eagler
+ 	 */
+ 	public BlockPos down(BlockPos dst) {
+ 		dst.x = x;
+ 		dst.y = y - 1;
+ 		dst.z = z;
+ 		return dst;
+ 	}
+ 

> INSERT  8 : 18  @  8

+ 	/**
+ 	 * eagler
+ 	 */
+ 	public BlockPos north(BlockPos dst) {
+ 		dst.x = x;
+ 		dst.y = y;
+ 		dst.z = z - 1;
+ 		return dst;
+ 	}
+ 

> INSERT  8 : 18  @  8

+ 	/**
+ 	 * eagler
+ 	 */
+ 	public BlockPos south(BlockPos dst) {
+ 		dst.x = x;
+ 		dst.y = y;
+ 		dst.z = z + 1;
+ 		return dst;
+ 	}
+ 

> INSERT  12 : 22  @  12

+ 	/**
+ 	 * eagler
+ 	 */
+ 	public BlockPos west(BlockPos dst) {
+ 		dst.x = x - 1;
+ 		dst.y = y;
+ 		dst.z = z;
+ 		return dst;
+ 	}
+ 

> INSERT  8 : 18  @  8

+ 	/**
+ 	 * eagler
+ 	 */
+ 	public BlockPos east(BlockPos dst) {
+ 		dst.x = x + 1;
+ 		dst.y = y;
+ 		dst.z = z;
+ 		return dst;
+ 	}
+ 

> INSERT  4 : 21  @  4

+ 	public BlockPos offsetFaster(EnumFacing facing, BlockPos ret) {
+ 		ret.x = this.getX() + facing.getFrontOffsetX();
+ 		ret.y = this.getY() + facing.getFrontOffsetY();
+ 		ret.z = this.getZ() + facing.getFrontOffsetZ();
+ 		return ret;
+ 	}
+ 
+ 	/**
+ 	 * only use with a regular "net.minecraft.util.BlockPos"!
+ 	 */
+ 	public BlockPos offsetEvenFaster(EnumFacing facing, BlockPos ret) {
+ 		ret.x = this.x + facing.getFrontOffsetX();
+ 		ret.y = this.y + facing.getFrontOffsetY();
+ 		ret.z = this.z + facing.getFrontOffsetZ();
+ 		return ret;
+ 	}
+ 

> CHANGE  2 : 4  @  2 : 4

~ 				: new BlockPos(this.x + facing.getFrontOffsetX() * n, this.y + facing.getFrontOffsetY() * n,
~ 						this.z + facing.getFrontOffsetZ() * n);

> DELETE  104  @  104 : 107

> CHANGE  6 : 7  @  6 : 10

~ 			super(x_, y_, z_);

> EOF
