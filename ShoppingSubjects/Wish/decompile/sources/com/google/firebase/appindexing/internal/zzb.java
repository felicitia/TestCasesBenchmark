package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzb extends AbstractSafeParcelable {
    public static final Creator<zzb> CREATOR = new zzw();
    private int zzaq = 0;
    private final boolean zzca;
    private final boolean zzcb;
    private final String zzcg;
    private final String zzch;
    private final byte[] zzci;

    zzb(int i, boolean z, String str, String str2, byte[] bArr, boolean z2) {
        this.zzaq = i;
        this.zzca = z;
        this.zzcg = str;
        this.zzch = str2;
        this.zzci = bArr;
        this.zzcb = z2;
    }

    public zzb(boolean z, String str, String str2, byte[] bArr, boolean z2) {
        this.zzca = z;
        this.zzcg = null;
        this.zzch = null;
        this.zzci = null;
        this.zzcb = false;
    }

    public final String toString() {
        byte[] bArr;
        StringBuilder sb = new StringBuilder();
        sb.append("MetadataImpl { ");
        sb.append("{ eventStatus: '");
        sb.append(this.zzaq);
        sb.append("' } ");
        sb.append("{ uploadable: '");
        sb.append(this.zzca);
        sb.append("' } ");
        if (this.zzcg != null) {
            sb.append("{ completionToken: '");
            sb.append(this.zzcg);
            sb.append("' } ");
        }
        if (this.zzch != null) {
            sb.append("{ accountName: '");
            sb.append(this.zzch);
            sb.append("' } ");
        }
        if (this.zzci != null) {
            sb.append("{ ssbContext: [ ");
            for (byte b : this.zzci) {
                sb.append("0x");
                sb.append(Integer.toHexString(b));
                sb.append(" ");
            }
            sb.append("] } ");
        }
        sb.append("{ contextOnly: '");
        sb.append(this.zzcb);
        sb.append("' } ");
        sb.append("}");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzaq);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzca);
        SafeParcelWriter.writeString(parcel, 3, this.zzcg, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzch, false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.zzci, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzcb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final void zzd(int i) {
        this.zzaq = i;
    }
}
