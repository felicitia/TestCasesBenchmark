package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

public final class d {
    private d() {
    }

    private static <T extends Parcelable> T a(@Nullable Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(d.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(d.class.getClassLoader());
        return bundle2.getParcelable(str);
    }

    public static void a(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        if (bundle != null && bundle2 != null) {
            Parcelable a = a(bundle, "MapOptions");
            if (a != null) {
                a(bundle2, "MapOptions", a);
            }
            Parcelable a2 = a(bundle, "StreetViewPanoramaOptions");
            if (a2 != null) {
                a(bundle2, "StreetViewPanoramaOptions", a2);
            }
            Parcelable a3 = a(bundle, "camera");
            if (a3 != null) {
                a(bundle2, "camera", a3);
            }
            if (bundle.containsKey("position")) {
                bundle2.putString("position", bundle.getString("position"));
            }
            if (bundle.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
                bundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", bundle.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
            }
        }
    }

    public static void a(Bundle bundle, String str, Parcelable parcelable) {
        bundle.setClassLoader(d.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(d.class.getClassLoader());
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }
}
