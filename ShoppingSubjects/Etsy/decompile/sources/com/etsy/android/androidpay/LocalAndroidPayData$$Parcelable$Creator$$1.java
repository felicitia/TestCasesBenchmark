package com.etsy.android.androidpay;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import org.parceler.a;

/* compiled from: LocalAndroidPayData$$Parcelable */
public final class LocalAndroidPayData$$Parcelable$Creator$$1 implements Creator<LocalAndroidPayData$$Parcelable> {
    public LocalAndroidPayData$$Parcelable createFromParcel(Parcel parcel) {
        return new LocalAndroidPayData$$Parcelable(LocalAndroidPayData$$Parcelable.read(parcel, new a()));
    }

    public LocalAndroidPayData$$Parcelable[] newArray(int i) {
        return new LocalAndroidPayData$$Parcelable[i];
    }
}
