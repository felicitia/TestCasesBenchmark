package org.parceler.a;

import android.os.Parcel;
import android.util.SparseArray;
import org.parceler.b;

/* compiled from: SparseArrayParcelConverter */
public abstract class l<T> implements b<SparseArray<T>> {
    public abstract T a(Parcel parcel);

    public abstract void a(T t, Parcel parcel);

    /* renamed from: a */
    public void toParcel(SparseArray<T> sparseArray, Parcel parcel) {
        if (sparseArray == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            parcel.writeInt(sparseArray.keyAt(i));
            a((T) sparseArray.valueAt(i), parcel);
        }
    }

    /* renamed from: b */
    public SparseArray<T> fromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        SparseArray sparseArray = new SparseArray(readInt);
        for (int i = 0; i < readInt; i++) {
            sparseArray.append(parcel.readInt(), a(parcel));
        }
        return sparseArray;
    }
}
