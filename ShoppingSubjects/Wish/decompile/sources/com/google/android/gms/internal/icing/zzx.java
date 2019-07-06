package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;

public final class zzx extends AbstractSafeParcelable {
    public static final Creator<zzx> CREATOR = new zzz();
    private final zzj zzaj;
    private final long zzak;
    private int zzal;
    private final String zzam;
    private final zzg zzan;
    private final boolean zzao;
    private int zzap;
    private int zzaq;

    zzx(zzj zzj, long j, int i, String str, zzg zzg, boolean z, int i2, int i3) {
        this.zzaj = zzj;
        this.zzak = j;
        this.zzal = i;
        this.zzam = str;
        this.zzan = zzg;
        this.zzao = z;
        this.zzap = i2;
        this.zzaq = i3;
    }

    public final String toString() {
        return String.format(Locale.US, "UsageInfo[documentId=%s, timestamp=%d, usageType=%d, status=%d]", new Object[]{this.zzaj, Long.valueOf(this.zzak), Integer.valueOf(this.zzal), Integer.valueOf(this.zzaq)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzaj, i, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzak);
        SafeParcelWriter.writeInt(parcel, 3, this.zzal);
        SafeParcelWriter.writeString(parcel, 4, this.zzam, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzan, i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzao);
        SafeParcelWriter.writeInt(parcel, 7, this.zzap);
        SafeParcelWriter.writeInt(parcel, 8, this.zzaq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
