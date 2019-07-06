package com.google.android.gms.internal.ads_identifier;

import android.os.Parcel;

public class a {
    private static final ClassLoader a = a.class.getClassLoader();

    private a() {
    }

    public static void a(Parcel parcel, boolean z) {
        parcel.writeInt(1);
    }

    public static boolean a(Parcel parcel) {
        return parcel.readInt() != 0;
    }
}
