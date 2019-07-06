package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class f implements Creator<OneoffTask> {
    f() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new OneoffTask(parcel, (f) null);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new OneoffTask[i];
    }
}
