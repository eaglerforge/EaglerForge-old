package net.lax1dude.eaglercraft.v1_8;

import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;
import java.math.BigInteger;

import net.eaglerforge.api.ModLoader;

public class EaglercraftVersion {
	
	
	//////////////////////////////////////////////////////////////////////
	
	/// Customize these to fit your fork:

	public static final String projectForkName = "EaglerForge";
	public static final String projectForkVersion = "v1.3.2";
	public static final String projectForkVendor = "radmanplays";

	public static final String projectForkURL = "https://github.com/eaglerforge/EaglerForge";

	//////////////////////////////////////////////////////////////////////
	
	public static final String projectOriginName = "EaglercraftX";
	public static final String projectOriginAuthor = "lax1dude";
	public static final String projectOriginRevision = "1.8";
	public static final String projectOriginVersion = "u35";
	
	public static final String projectOriginURL = "https://gitlab.com/lax1dude/eaglercraftx-1.8"; // rest in peace
	
	
	
	// Updating configuration
	
	public static final boolean enableUpdateService = false;

	public static final String updateBundlePackageName = "io.github.eaglerforge";
	public static final int updateBundlePackageVersionInt = 1;

	public static final String updateLatestLocalStorageKey = "latestUpdate_" + updateBundlePackageName;

	// public key modulus for official 1.8 updates
	public static final BigInteger updateSignatureModulus = new BigInteger("21739779283293944547641234121752144733479527730715335699749271442619159938609893590982689572804461419319625441578001249734038565083629151093550809410312809216776541132612535868289464217347406519987043870272003247350984171500109448442655454584224732143839082062248553013446501018949132607651993810828283710954004177870499697987855543078934760823897453100156364985161651700843008935852987255061033006531931537876855599826453378240143866558332896899212921366010793310023844112369123388674684994797583690008684927225401250389368846109116565362442038196378052611894519724086404964550955919753339141756654551164639223580253");
	
	
	// Miscellaneous variables:

	public static final String mainMenuStringA = "Minecraft* 1.8.8";
	public static String getMainMenuStringB() {
		//int loadedmods = ModLoader.returnTotalLoadedMods();
		int loadedmods = -1;
		String mainMenuStringB = projectForkName + " " + projectForkVersion;
		if(!PlatformRuntime.isDebugRuntime()) {
			if(loadedmods == 1) {
				mainMenuStringB = mainMenuStringB + " (" + loadedmods + " Mod loaded)";
			} else {
				mainMenuStringB = mainMenuStringB + " (" + loadedmods + " Mods loaded)";
			}
		}

		return mainMenuStringB;
	}
	public static final String mainMenuStringC = "";
	public static final String mainMenuStringD = "Resources Copyright Mojang AB";

	public static final String mainMenuStringE = "Based on eaglercraft " + projectOriginVersion;
	public static final String mainMenuStringF = null;

	public static final String mainMenuStringG = "Collector's Edition";
	public static final String mainMenuStringH = "PBR Shaders";

	public static final long demoWorldSeed = (long) "North Carolina".hashCode();

	public static final boolean mainMenuEnableGithubButton = true;

	public static final boolean forceDemoMode = false;

	public static final String localStorageNamespace = "_eaglercraftX";

}
