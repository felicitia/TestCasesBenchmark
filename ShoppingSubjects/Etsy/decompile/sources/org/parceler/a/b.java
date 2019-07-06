package org.parceler.a;

import android.os.Parcel;

/* compiled from: BooleanArrayParcelConverter */
public class b implements org.parceler.b<boolean[]> {
    /* renamed from: a */
    public void toParcel(boolean[] zArr, Parcel parcel) {
        if (zArr == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(zArr.length);
        parcel.writeBooleanArray(zArr);
    }

    /* renamed from: a */
    public boolean[] fromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == -1) {
            return null;
        }
        boolean[] zArr = new boolean[readInt];
        parcel.readBooleanArray(zArr);
        return zArr;
    }
}
