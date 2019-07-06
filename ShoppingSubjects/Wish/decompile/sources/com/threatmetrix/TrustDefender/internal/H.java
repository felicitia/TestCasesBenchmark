package com.threatmetrix.TrustDefender.internal;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;

class H {

    /* renamed from: for reason: not valid java name */
    private static final String f174for = TL.m331if(H.class);

    H() {
    }

    @TargetApi(23)
    /* renamed from: if reason: not valid java name */
    static String m65if(Context context) {
        String str = "";
        try {
            Object systemService = context.getSystemService("fingerprint");
            if (systemService != null && (systemService instanceof FingerprintManager)) {
                FingerprintManager fingerprintManager = (FingerprintManager) systemService;
                if (fingerprintManager.isHardwareDetected()) {
                    String concat = str.concat("B");
                    try {
                        str = fingerprintManager.hasEnrolledFingerprints() ? concat.concat("C") : concat;
                    } catch (SecurityException unused) {
                        return concat;
                    }
                }
            }
            Object systemService2 = context.getSystemService("keyguard");
            if (systemService2 != null && (systemService2 instanceof KeyguardManager) && ((KeyguardManager) systemService2).isDeviceSecure()) {
                return str.concat("D");
            }
        } catch (SecurityException unused2) {
        }
        return str;
    }
}
