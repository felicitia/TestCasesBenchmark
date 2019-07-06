package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzv extends AbstractSafeParcelable {
    public static final Creator<zzv> CREATOR = new zzw();
    private final boolean zzai;

    public zzv(boolean z) {
        this.zzai = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzv)) {
            return false;
        }
        return this.zzai == ((zzv) obj).zzai;
    }

    public final int hashCode() {
        return this.zzai ? 1 : 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzai);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
