package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzl extends AbstractSafeParcelable {
    public static final Creator<zzl> CREATOR = new zzm();
    private static final int zzo = Integer.parseInt("-1");
    private static final zzs zzp = new zzt("SsbContext").zzb(true).zzc("blob").zzb();
    private final String zzq;
    private final zzs zzr;
    public final int zzs;
    private final byte[] zzt;

    zzl(String str, zzs zzs2, int i, byte[] bArr) {
        String str2;
        boolean z = i == zzo || zzr.zza(i) != null;
        StringBuilder sb = new StringBuilder(32);
        sb.append("Invalid section type ");
        sb.append(i);
        Preconditions.checkArgument(z, sb.toString());
        this.zzq = str;
        this.zzr = zzs2;
        this.zzs = i;
        this.zzt = bArr;
        if (this.zzs == zzo || zzr.zza(this.zzs) != null) {
            str2 = (this.zzq == null || this.zzt == null) ? null : "Both content and blobContent set";
        } else {
            int i2 = this.zzs;
            StringBuilder sb2 = new StringBuilder(32);
            sb2.append("Invalid section type ");
            sb2.append(i2);
            str2 = sb2.toString();
        }
        if (str2 != null) {
            throw new IllegalArgumentException(str2);
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzq, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzr, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzs);
        SafeParcelWriter.writeByteArray(parcel, 5, this.zzt, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
