package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;

final class y implements Creator<zzi> {
    y() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder != null) {
            return new zzi(readStrongBinder);
        }
        return null;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzi[i];
    }
}
