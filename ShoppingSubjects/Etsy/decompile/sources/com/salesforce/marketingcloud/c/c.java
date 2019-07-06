package com.salesforce.marketingcloud.c;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;
import java.util.Map;

final class c extends a {
    public static final Creator<c> CREATOR = new Creator<c>() {
        /* renamed from: a */
        public c createFromParcel(Parcel parcel) {
            c cVar = new c(parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readHashMap(String.class.getClassLoader()));
            return cVar;
        }

        /* renamed from: a */
        public c[] newArray(int i) {
            return new c[i];
        }
    };

    c(String str, String str2, int i, long j, long j2, Map<String, List<String>> map) {
        super(str, str2, i, j, j2, map);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (a() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(a());
        }
        if (b() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(b());
        }
        parcel.writeInt(c());
        parcel.writeLong(d());
        parcel.writeLong(e());
        parcel.writeMap(f());
    }
}
