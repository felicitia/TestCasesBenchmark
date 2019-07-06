package com.salesforce.marketingcloud.analytics;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class g extends b {
    public static final Creator<g> CREATOR = new Creator<g>() {
        /* renamed from: a */
        public g createFromParcel(Parcel parcel) {
            g gVar = new g(parcel.readString(), parcel.readInt(), parcel.readDouble(), parcel.readInt() == 0 ? parcel.readString() : null);
            return gVar;
        }

        /* renamed from: a */
        public g[] newArray(int i) {
            return new g[i];
        }
    };

    g(String str, int i, double d, String str2) {
        super(str, i, d, str2);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(item());
        parcel.writeInt(quantity());
        parcel.writeDouble(price());
        if (uniqueId() == null) {
            parcel.writeInt(1);
            return;
        }
        parcel.writeInt(0);
        parcel.writeString(uniqueId());
    }
}
