package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.appindexing.Action;

public final class zza extends AbstractSafeParcelable implements Action {
    public static final Creator<zza> CREATOR = new zzc();
    private final Bundle zzaw;
    private final String zzbu;
    private final String zzbv;
    private final String zzbw;
    private final String zzbx;
    private final zzb zzby;
    private final String zzbz;

    public zza(String str, String str2, String str3, String str4, zzb zzb, String str5, Bundle bundle) {
        this.zzbu = str;
        this.zzbv = str2;
        this.zzbw = str3;
        this.zzbx = str4;
        this.zzby = zzb;
        this.zzbz = str5;
        if (bundle != null) {
            this.zzaw = bundle;
        } else {
            this.zzaw = Bundle.EMPTY;
        }
        this.zzaw.setClassLoader(getClass().getClassLoader());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ActionImpl { ");
        sb.append("{ actionType: '");
        sb.append(this.zzbu);
        sb.append("' } ");
        sb.append("{ objectName: '");
        sb.append(this.zzbv);
        sb.append("' } ");
        sb.append("{ objectUrl: '");
        sb.append(this.zzbw);
        sb.append("' } ");
        if (this.zzbx != null) {
            sb.append("{ objectSameAs: '");
            sb.append(this.zzbx);
            sb.append("' } ");
        }
        if (this.zzby != null) {
            sb.append("{ metadata: '");
            sb.append(this.zzby.toString());
            sb.append("' } ");
        }
        if (this.zzbz != null) {
            sb.append("{ actionStatus: '");
            sb.append(this.zzbz);
            sb.append("' } ");
        }
        if (!this.zzaw.isEmpty()) {
            sb.append("{ ");
            sb.append(this.zzaw);
            sb.append(" } ");
        }
        sb.append("}");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzbu, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzbv, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbw, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbx, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzby, i, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzbz, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzaw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzb zzj() {
        return this.zzby;
    }
}
