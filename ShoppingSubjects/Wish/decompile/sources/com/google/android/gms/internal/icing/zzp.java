package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

public final class zzp extends AbstractSafeParcelable implements Result {
    public static final Creator<zzp> CREATOR = new zzq();
    private Status zzv;
    private List<zzx> zzw;
    @Deprecated
    private String[] zzx;

    public zzp() {
    }

    zzp(Status status, List<zzx> list, String[] strArr) {
        this.zzv = status;
        this.zzw = list;
        this.zzx = strArr;
    }

    public final Status getStatus() {
        return this.zzv;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzv, i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzw, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzx, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
