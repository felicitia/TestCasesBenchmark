package com.google.android.gms.location;

import android.support.v4.view.PointerIconCompat;
import com.google.android.gms.common.api.CommonStatusCodes;

public final class c extends CommonStatusCodes {
    public static String a(int i) {
        switch (i) {
            case 1000:
                return "GEOFENCE_NOT_AVAILABLE";
            case 1001:
                return "GEOFENCE_TOO_MANY_GEOFENCES";
            case PointerIconCompat.TYPE_HAND /*1002*/:
                return "GEOFENCE_TOO_MANY_PENDING_INTENTS";
            default:
                return CommonStatusCodes.getStatusCodeString(i);
        }
    }
}
