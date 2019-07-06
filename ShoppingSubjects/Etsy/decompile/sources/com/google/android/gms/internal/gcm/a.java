package com.google.android.gms.internal.gcm;

import android.os.Parcel;
import android.os.Parcelable;

public class a {
    private static final ClassLoader a = a.class.getClassLoader();

    private a() {
    }

    public static void a(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }
}
