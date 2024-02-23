
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 3

> INSERT  2 : 7  @  2

+ 
+ import com.google.common.collect.Lists;
+ 
+ import net.eaglerforge.api.BaseData;
+ import net.eaglerforge.api.ModData;

> CHANGE  7 : 10  @  7 : 10

~ 	public int chunkX;
~ 	public int chunkZ;
~ 	public S21PacketChunkData.Extracted extractedData;

> CHANGE  64 : 65  @  64 : 65

~ 		for (ExtendedBlockStorage extendedblockstorage1 : (ArrayList<ExtendedBlockStorage>) arraylist) {

> CHANGE  8 : 9  @  8 : 9

~ 		for (ExtendedBlockStorage extendedblockstorage2 : (ArrayList<ExtendedBlockStorage>) arraylist) {

> CHANGE  5 : 6  @  5 : 6

~ 			for (ExtendedBlockStorage extendedblockstorage3 : (ArrayList<ExtendedBlockStorage>) arraylist) {

> CHANGE  33 : 34  @  33 : 34

~ 	public static class Extracted extends ModData {

> INSERT  2 : 20  @  2

+ 
+ 		public ModData makeModData() {
+ 			ModData d = new ModData();
+ 			d.setCallbackVoid("reload", () -> {
+ 				loadModData(d);
+ 			});
+ 			d.set("data", data);
+ 			d.set("dataSize", dataSize);
+ 			d.setCallbackObject("getRef", () -> {
+ 				return this;
+ 			});
+ 			return d;
+ 		}
+ 
+ 		public void loadModData(BaseData d) {
+ 			data = d.getByteArr("data");
+ 			dataSize = d.getInt("dataSize");
+ 		}

> EOF
