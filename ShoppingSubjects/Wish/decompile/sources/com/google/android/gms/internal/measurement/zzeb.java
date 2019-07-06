package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzeb extends AbstractSafeParcelable {
    public static final Creator<zzeb> CREATOR = new zzec();
    public final String packageName;
    public final String zzafa;
    public final String zzafc;
    public final long zzafg;
    public final String zzafh;
    public final long zzafi;
    public final long zzafj;
    public final boolean zzafk;
    public final long zzafl;
    public final boolean zzafm;
    public final boolean zzafn;
    public final String zzafy;
    public final boolean zzafz;
    public final long zzaga;
    public final int zzagb;
    public final boolean zzagc;
    public final String zztg;

    zzeb(String str, String str2, String str3, long j, String str4, long j2, long j3, String str5, boolean z, boolean z2, String str6, long j4, long j5, int i, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        this.packageName = str;
        this.zzafa = TextUtils.isEmpty(str2) ? null : str2;
        this.zztg = str3;
        this.zzafg = j;
        this.zzafh = str4;
        this.zzafi = j2;
        this.zzafj = j3;
        this.zzafy = str5;
        this.zzafk = z;
        this.zzafz = z2;
        this.zzafc = str6;
        this.zzafl = j4;
        this.zzaga = j5;
        this.zzagb = i;
        this.zzafm = z3;
        this.zzafn = z4;
        this.zzagc = z5;
    }

    zzeb(String str, String str2, String str3, String str4, long j, long j2, String str5, boolean z, boolean z2, long j3, String str6, long j4, long j5, int i, boolean z3, boolean z4, boolean z5) {
        this.packageName = str;
        this.zzafa = str2;
        this.zztg = str3;
        this.zzafg = j3;
        this.zzafh = str4;
        this.zzafi = j;
        this.zzafj = j2;
        this.zzafy = str5;
        this.zzafk = z;
        this.zzafz = z2;
        this.zzafc = str6;
        this.zzafl = j4;
        this.zzaga = j5;
        this.zzagb = i;
        this.zzafm = z3;
        this.zzafn = z4;
        this.zzagc = z5;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzafa, false);
        SafeParcelWriter.writeString(parcel, 4, this.zztg, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzafh, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzafi);
        SafeParcelWriter.writeLong(parcel, 7, this.zzafj);
        SafeParcelWriter.writeString(parcel, 8, this.zzafy, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzafk);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzafz);
        SafeParcelWriter.writeLong(parcel, 11, this.zzafg);
        SafeParcelWriter.writeString(parcel, 12, this.zzafc, false);
        SafeParcelWriter.writeLong(parcel, 13, this.zzafl);
        SafeParcelWriter.writeLong(parcel, 14, this.zzaga);
        SafeParcelWriter.writeInt(parcel, 15, this.zzagb);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzafm);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzafn);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzagc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
