
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  2 : 6  @  2 : 3

~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;
~ 
~ public class MapColor extends ModData {

> CHANGE  37 : 39  @  37 : 39

~ 	public int colorValue;
~ 	public int colorIndex;

> INSERT  11 : 49  @  11

+ 	public void loadModData(BaseData data) {
+ 		colorIndex = data.getInt("colorIndex");
+ 		colorValue = data.getInt("colorValue");
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.set("colorIndex", colorIndex);
+ 		data.set("colorValue", colorValue);
+ 
+ 		int[] rgb = new int[3];
+ 		int rr;
+ 		int gg;
+ 		int bb;
+ 		int dec = colorValue;
+ 		rr = (int) Math.floor(dec / 65536);
+ 		dec -= rr * 65536;
+ 		gg = (int) Math.floor(dec / 256);
+ 		dec -= gg * 256;
+ 		bb = dec;
+ 		rr = Math.min(rr, 255);
+ 		rgb[0] = rr;
+ 		rgb[1] = gg;
+ 		rgb[2] = bb;
+ 		data.set("rgb", rgb);
+ 
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 
+ 		return data;
+ 
+ 	}
+ 

> EOF
