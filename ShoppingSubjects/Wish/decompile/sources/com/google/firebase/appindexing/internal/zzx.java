package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzx extends AbstractSafeParcelable {
    public static final Creator<zzx> CREATOR = new zzy();
    private final int type;
    private final Thing[] zzdc;
    private final String[] zzdd;
    private final String[] zzde;

    zzx(int i, Thing[] thingArr, String[] strArr, String[] strArr2) {
        if (i != 6) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    break;
                default:
                    i = 0;
                    break;
            }
        }
        this.type = i;
        this.zzdc = thingArr;
        this.zzdd = strArr;
        this.zzde = strArr2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.type);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzdc, i, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzdd, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.zzde, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
