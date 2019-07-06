package com.salesforce.marketingcloud.proximity;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class d extends a {
    public static final Creator<d> CREATOR = new Creator<d>() {
        /* renamed from: a */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
        }

        /* renamed from: a */
        public d[] newArray(int i) {
            return new d[i];
        }
    };

    d(String str, String str2, int i, int i2) {
        super(str, str2, i, i2);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(a());
        parcel.writeString(b());
        parcel.writeInt(c());
        parcel.writeInt(d());
    }
}
