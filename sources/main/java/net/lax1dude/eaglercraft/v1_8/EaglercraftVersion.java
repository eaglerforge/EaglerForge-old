package net.lax1dude.eaglercraft.v1_8;

import java.math.BigInteger;

import static net.eaglerforge.api.ModLoader.returntotalloadedmods;

public class EaglercraftVersion {
	
	
	//////////////////////////////////////////////////////////////////////
	
	/// Customize these to fit your fork:

	public static final String projectForkName = "EaglerForge";
	public static final String projectForkVersion = "v1.2.5";
	public static final String projectForkVendor = "radmanplays";

	public static final String projectForkURL = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

	//////////////////////////////////////////////////////////////////////
	
	public static final String projectOriginName = "EaglercraftX";
	public static final String projectOriginAuthor = "lax1dude";
	public static final String projectOriginRevision = "1.8";
	public static final String projectOriginVersion = "u26";
	
	public static final String projectOriginURL = "https://gitlab.com/lax1dude/eaglercraftx-1.8"; // rest in peace
	
	
	
	// Updating configuration
	
	public static final boolean enableUpdateService = false;

	public static final String updateBundlePackageName = "io.github.eaglerforge";
	public static final int updateBundlePackageVersionInt = 1;

	public static final String updateLatestLocalStorageKey = "latestUpdate_" + updateBundlePackageName;

	// public key modulus for official 1.8 updates
	public static final BigInteger updateSignatureModulus = new BigInteger("22302119280824038895015337962477680597476104207345604966253054429381650070342648262468873267757814874708611083822921628311978869883104719752229416930333872753358955704590809789294862607939612524727886786527290992955126500676328352370369922449422579744340333676863098339947474594269733166064009051103930148034312132765389453128223739912510446850392754045747927625891409301335822835217981469360698269329758100959647222384610595484900358676517990001334603438032659229303062438724388322647688242065556130002394442671588580331601913919848425081383560948799052093250544042535255506402548518002290940844473097128616949878179");
	
	
	
	// Miscellaneous variables:

	public static int loadedmods = returntotalloadedmods();
	public static final String mainMenuStringA = "Minecraft* 1.8.8";
	public static String mainMenuStringB = projectForkName + " " + projectForkVersion + " (" + loadedmods + " Mods loaded)";
	public static final String mainMenuStringC = "";
	public static final String mainMenuStringD = "Resources Copyright Mojang AB";

	public static final String mainMenuStringE = "Based on eaglercraft " + projectOriginVersion;
	public static final String mainMenuStringF = null;

	public static final String mainMenuStringG = "Collector's Edition";
	public static final String mainMenuStringH = "PBR Shaders";

	public static final long demoWorldSeed = (long) "North Carolina".hashCode();

	public static final boolean mainMenuEnableGithubButton = false;

	public static final boolean forceDemoMode = false;

}
