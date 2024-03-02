
# Eagler Context Redacted Diff
# Copyright (c) 2024 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  2 : 3  @  2

+ import net.eaglerforge.api.ModData;

> CHANGE  3 : 9  @  3 : 9

~ public class ScaledResolution extends ModData {
~ 	private static double scaledWidthD;
~ 	private static double scaledHeightD;
~ 	private static int scaledWidth;
~ 	private static int scaledHeight;
~ 	private static int scaleFactor;

> INSERT  26 : 50  @  26

+ 	public static ModData makeModData() {
+ 		ModData ScaledResolutionglobal = new ModData();
+ 		ScaledResolutionglobal.setCallbackInt("getScaledWidth", () -> {
+ 			return scaledWidth;
+ 		});
+ 		ScaledResolutionglobal.setCallbackInt("getScaledHeight", () -> {
+ 			return scaledHeight;
+ 		});
+ 		ScaledResolutionglobal.setCallbackDouble("getScaledWidth_double", () -> {
+ 			return scaledWidthD;
+ 		});
+ 		ScaledResolutionglobal.setCallbackDouble("getScaledHeight_double", () -> {
+ 			return scaledHeightD;
+ 		});
+ 		ScaledResolutionglobal.setCallbackInt("getScaledWidth_double", () -> {
+ 			return scaledWidth;
+ 		});
+ 		ScaledResolutionglobal.setCallbackInt("getScaleFactor", () -> {
+ 			return scaleFactor;
+ 		});
+ 
+ 		return ScaledResolutionglobal;
+ 	}
+ 

> EOF
