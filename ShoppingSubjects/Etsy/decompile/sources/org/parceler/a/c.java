package org.parceler.a;

import android.os.Parcel;
import org.parceler.b;

/* compiled from: CharArrayParcelConverter */
public class c implements b<char[]> {
    /* renamed from: a */
    public void toParcel(char[] cArr, Parcel parcel) {
        if (cArr == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(cArr.length);
        parcel.writeCharArray(cArr);
    }

    /* renamed from: a */
    public char[] fromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == -1) {
            return null;
        }
        char[] cArr = new char[readInt];
        parcel.readCharArray(cArr);
        return cArr;
    }
}
