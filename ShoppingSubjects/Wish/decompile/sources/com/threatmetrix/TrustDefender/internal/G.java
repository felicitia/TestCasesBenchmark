package com.threatmetrix.TrustDefender.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.PowerManager;

class G {

    /* renamed from: new reason: not valid java name */
    private static final String f173new = TL.m331if(G.class);

    G() {
    }

    @TargetApi(20)
    /* renamed from: new reason: not valid java name */
    static boolean m64new(Context context) {
        if (D.m156new() && C0012I.f388for >= W.f406this) {
            try {
                Object systemService = context.getSystemService("power");
                if (systemService != null) {
                    if (systemService instanceof PowerManager) {
                        return ((PowerManager) systemService).isInteractive();
                    }
                }
                return true;
            } catch (SecurityException unused) {
            } catch (Exception e) {
                TL.m338new(f173new, e.toString());
            }
        }
        return true;
    }
}
