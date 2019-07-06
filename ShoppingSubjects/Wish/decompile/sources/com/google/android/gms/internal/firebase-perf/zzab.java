package com.google.android.gms.internal.firebase-perf;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzab implements Creator<zzaa> {
    zzab() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaa[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzaa(parcel, null);
    }
}
