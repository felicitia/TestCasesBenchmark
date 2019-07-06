package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzf extends AbstractSafeParcelable {
    public static final Creator<zzf> CREATOR = new zzg();
    private static final zzf zzcj = new zzf(1);
    private static final zzf zzck = new zzf(2);
    private static final zzf zzcl = new zzf(3);
    public final int status;

    public zzf(int i) {
        this.status = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.status);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
