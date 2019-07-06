package com.google.android.gms.internal.firebase-perf;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzs implements Creator<zzr> {
    zzs() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzr(parcel, null);
    }
}
