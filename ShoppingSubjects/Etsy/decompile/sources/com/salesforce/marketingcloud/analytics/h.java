package com.salesforce.marketingcloud.analytics;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class h extends c {
    public static final Creator<h> CREATOR = new Creator<h>() {
        /* renamed from: a */
        public h createFromParcel(Parcel parcel) {
            h hVar = new h((PiCart) parcel.readParcelable(PiCart.class.getClassLoader()), parcel.readString(), parcel.readDouble(), parcel.readDouble());
            return hVar;
        }

        /* renamed from: a */
        public h[] newArray(int i) {
            return new h[i];
        }
    };

    h(PiCart piCart, String str, double d, double d2) {
        super(piCart, str, d, d2);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(cart(), i);
        parcel.writeString(orderNumber());
        parcel.writeDouble(shipping());
        parcel.writeDouble(discount());
    }
}
