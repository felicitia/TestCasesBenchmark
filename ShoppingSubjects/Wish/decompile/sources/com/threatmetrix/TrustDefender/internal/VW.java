package com.threatmetrix.TrustDefender.internal;

import android.location.Location;
import java.lang.reflect.Method;

final class VW extends D2 {

    /* renamed from: for reason: not valid java name */
    private static final Method f558for = m44for(Location.class, "isFromMockProvider", new Class[0]);

    VW() {
    }

    /* renamed from: new reason: not valid java name */
    static boolean m347new(Location location) {
        if (C0012I.f388for >= W.f404long) {
            Boolean bool = (Boolean) m39do((Object) location, f558for, new Object[0]);
            if (bool != null) {
                return bool.booleanValue();
            }
        }
        return false;
    }
}
