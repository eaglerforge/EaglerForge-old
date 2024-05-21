import net.eaglerforge.api.*;
import java.util.ArrayList;
public class PLReflect {
    public static ModData makeModData() {
        ModData plReflectGlobal = new ModData();
        ArrayList<BaseData> reflectProfiles = new ArrayList<BaseData>();
        %classdefs%
        BaseData[] reflectProfilesArr = new BaseData[reflectProfiles.size()];
        for (int i = 0; i < reflectProfilesArr.length; i++) {
            reflectProfilesArr[i] = reflectProfiles.get(i);
        }
        plReflectGlobal.set("classes", reflectProfilesArr);
        return plReflectGlobal;
    }
}