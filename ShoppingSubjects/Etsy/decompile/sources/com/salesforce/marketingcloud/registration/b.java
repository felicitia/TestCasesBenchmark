package com.salesforce.marketingcloud.registration;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b extends C$AutoValue_Attribute {
    public static final Creator<b> CREATOR = new Creator<b>() {
        /* renamed from: a */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel.readString(), parcel.readString());
        }

        /* renamed from: a */
        public b[] newArray(int i) {
            return new b[i];
        }
    };

    b(String str, String str2) {
        super(str, str2);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key());
        parcel.writeString(value());
    }
}
