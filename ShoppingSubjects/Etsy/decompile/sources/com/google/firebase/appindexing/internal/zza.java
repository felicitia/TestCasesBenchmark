package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.firebase.appindexing.a;
import org.apache.commons.math3.geometry.VectorFormat;

@Class(creator = "ActionImplCreator")
@Reserved({1000})
public final class zza extends AbstractSafeParcelable implements a {
    public static final Creator<zza> CREATOR = new zzc();
    @Field(getter = "getPropertyBundle", id = 7)
    private final Bundle zzaw;
    @Field(getter = "getActionType", id = 1)
    private final String zzbu;
    @Field(getter = "getObjectName", id = 2)
    private final String zzbv;
    @Field(getter = "getObjectUrl", id = 3)
    private final String zzbw;
    @Field(getter = "getObjectSameAs", id = 4)
    private final String zzbx;
    @Field(getter = "getMetadata", id = 5)
    private final zzb zzby;
    @Field(getter = "getActionStatus", id = 6)
    private final String zzbz;

    @Constructor
    public zza(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) String str3, @Param(id = 4) String str4, @Param(id = 5) zzb zzb, @Param(id = 6) String str5, @Param(id = 7) Bundle bundle) {
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
        sb.append(VectorFormat.DEFAULT_SUFFIX);
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
