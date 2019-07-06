package org.parceler;

import android.os.Parcel;

/* compiled from: TypeRangeParcelConverter */
public interface f<L, U extends L> {
    U fromParcel(Parcel parcel);

    void toParcel(L l, Parcel parcel);
}
