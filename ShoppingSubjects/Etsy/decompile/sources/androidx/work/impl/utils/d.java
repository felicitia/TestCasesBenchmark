package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import androidx.work.e;
import com.etsy.android.lib.models.ResponseConstants;

/* compiled from: PackageManagerHelper */
public class d {
    public static void a(@NonNull Context context, @NonNull Class cls, boolean z) {
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls.getName()), z ? 1 : 2, 1);
            String str = "PackageManagerHelper";
            String str2 = "%s %s";
            Object[] objArr = new Object[2];
            objArr[0] = cls.getName();
            objArr[1] = z ? ResponseConstants.ENABLED : "disabled";
            e.b(str, String.format(str2, objArr), new Throwable[0]);
        } catch (Exception e) {
            String str3 = "PackageManagerHelper";
            String str4 = "%s could not be %s";
            Object[] objArr2 = new Object[2];
            objArr2[0] = cls.getName();
            objArr2[1] = z ? ResponseConstants.ENABLED : "disabled";
            e.b(str3, String.format(str4, objArr2), e);
        }
    }
}
