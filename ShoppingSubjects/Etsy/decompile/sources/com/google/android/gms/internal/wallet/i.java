package com.google.android.gms.internal.wallet;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class i {
    private static final ClassLoader a = i.class.getClassLoader();

    private i() {
    }

    public static <T extends Parcelable> T a(Parcel parcel, Creator<T> creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return (Parcelable) creator.createFromParcel(parcel);
    }

    public static void a(Parcel parcel, IInterface iInterface) {
        parcel.writeStrongBinder(iInterface == null ? null : iInterface.asBinder());
    }

    public static void a(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }

    public static void a(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    public static boolean a(Parcel parcel) {
        return parcel.readInt() != 0;
    }
}
