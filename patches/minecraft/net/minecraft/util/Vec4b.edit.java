
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> CHANGE  2 : 6  @  2 : 3

~ import net.eaglerforge.api.BaseData;
~ import net.eaglerforge.api.ModData;
~ 
~ public class Vec4b extends ModData {

> INSERT  19 : 45  @  19

+ 	public void loadModData(BaseData data) {
+ 		field_176117_a = data.getByte("a");
+ 		field_176115_b = data.getByte("b");
+ 		field_176116_c = data.getByte("c");
+ 		field_176114_d = data.getByte("d");
+ 	}
+ 
+ 	public static Vec4b fromModData(BaseData data) {
+ 		return new Vec4b(data.getByte("a"), data.getByte("b"), data.getByte("c"), data.getByte("d"));
+ 	}
+ 
+ 	public ModData makeModData() {
+ 		ModData data = new ModData();
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadModData(data);
+ 		});
+ 		data.setCallbackObject("getRef", () -> {
+ 			return this;
+ 		});
+ 		data.set("a", field_176117_a);
+ 		data.set("b", field_176115_b);
+ 		data.set("c", field_176116_c);
+ 		data.set("d", field_176114_d);
+ 		return data;
+ 	}
+ 

> EOF
