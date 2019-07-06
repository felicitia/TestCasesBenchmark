package com.onfido.android.sdk.capture.common.permissions;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import kotlin.jvm.internal.Intrinsics;

public class RuntimePermissionsManager {
    private final Context a;

    public RuntimePermissionsManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.a = context;
    }

    public boolean hasPermission(String str) {
        Intrinsics.checkParameterIsNotNull(str, "permission");
        return ActivityCompat.checkSelfPermission(this.a, str) == 0;
    }

    public void requestPermissions(Activity activity, String[] strArr, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        ActivityCompat.requestPermissions(activity, strArr, i);
    }

    public boolean werePermissionsGranted(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        int length = iArr.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= length) {
                return true;
            }
            if (iArr[i] != 0) {
                z = false;
            }
            if (!z) {
                return false;
            }
            i++;
        }
    }
}
