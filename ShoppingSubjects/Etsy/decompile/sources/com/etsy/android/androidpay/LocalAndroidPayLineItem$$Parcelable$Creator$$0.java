package com.etsy.android.androidpay;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import org.parceler.a;

/* compiled from: LocalAndroidPayLineItem$$Parcelable */
public final class LocalAndroidPayLineItem$$Parcelable$Creator$$0 implements Creator<LocalAndroidPayLineItem$$Parcelable> {
    public LocalAndroidPayLineItem$$Parcelable createFromParcel(Parcel parcel) {
        return new LocalAndroidPayLineItem$$Parcelable(LocalAndroidPayLineItem$$Parcelable.read(parcel, new a()));
    }

    public LocalAndroidPayLineItem$$Parcelable[] newArray(int i) {
        return new LocalAndroidPayLineItem$$Parcelable[i];
    }
}
