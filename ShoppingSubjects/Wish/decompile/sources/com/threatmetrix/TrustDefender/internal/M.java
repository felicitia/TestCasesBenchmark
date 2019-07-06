package com.threatmetrix.TrustDefender.internal;

import android.annotation.TargetApi;
import android.app.admin.DevicePolicyManager;
import android.content.Context;

class M {

    /* renamed from: int reason: not valid java name */
    private static final String f337int = TL.m331if(M.class);

    M() {
    }

    @TargetApi(11)
    /* renamed from: do reason: not valid java name */
    static int m141do(Context context) {
        if (!O.f415if) {
            return 16;
        }
        if (C0012I.f388for < W.f402if) {
            return 1;
        }
        try {
            Object systemService = context.getSystemService("device_policy");
            if (systemService != null) {
                if (systemService instanceof DevicePolicyManager) {
                    return ((DevicePolicyManager) systemService).getStorageEncryptionStatus();
                }
            }
            return 16;
        } catch (SecurityException unused) {
            return 16;
        } catch (Exception e) {
            TL.m338new(f337int, e.toString());
            return 16;
        }
    }
}
