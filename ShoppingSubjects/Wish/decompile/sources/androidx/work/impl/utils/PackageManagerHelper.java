package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

public class PackageManagerHelper {
    public static void setComponentEnabled(Context context, Class cls, boolean z) {
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls.getName()), z ? 1 : 2, 1);
            String str = "PackageManagerHelper";
            String str2 = "%s %s";
            Object[] objArr = new Object[2];
            objArr[0] = cls.getName();
            objArr[1] = z ? "enabled" : "disabled";
            Log.d(str, String.format(str2, objArr));
        } catch (Exception e) {
            String str3 = "PackageManagerHelper";
            String str4 = "%s could not be %s";
            Object[] objArr2 = new Object[2];
            objArr2[0] = cls.getName();
            objArr2[1] = z ? "enabled" : "disabled";
            Log.d(str3, String.format(str4, objArr2), e);
        }
    }
}
