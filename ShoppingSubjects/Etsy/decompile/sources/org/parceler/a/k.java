package org.parceler.a;

import android.os.Parcel;
import org.parceler.b;

/* compiled from: NullableParcelConverter */
public abstract class k<T> implements b<T> {
    public abstract void a(T t, Parcel parcel);

    public abstract T b(Parcel parcel);

    public void toParcel(T t, Parcel parcel) {
        if (t == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        a(t, parcel);
    }

    public T fromParcel(Parcel parcel) {
        if (parcel.readInt() == -1) {
            return null;
        }
        return b(parcel);
    }
}
