package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzef extends AbstractSafeParcelable {
    public static final Creator<zzef> CREATOR = new zzeg();
    public boolean active;
    public long creationTimestamp;
    public String origin;
    public String packageName;
    public long timeToLive;
    public String triggerEventName;
    public long triggerTimeout;
    public zzka zzage;
    public zzex zzagf;
    public zzex zzagg;
    public zzex zzagh;

    zzef(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        this.packageName = zzef.packageName;
        this.origin = zzef.origin;
        this.zzage = zzef.zzage;
        this.creationTimestamp = zzef.creationTimestamp;
        this.active = zzef.active;
        this.triggerEventName = zzef.triggerEventName;
        this.zzagf = zzef.zzagf;
        this.triggerTimeout = zzef.triggerTimeout;
        this.zzagg = zzef.zzagg;
        this.timeToLive = zzef.timeToLive;
        this.zzagh = zzef.zzagh;
    }

    zzef(String str, String str2, zzka zzka, long j, boolean z, String str3, zzex zzex, long j2, zzex zzex2, long j3, zzex zzex3) {
        this.packageName = str;
        this.origin = str2;
        this.zzage = zzka;
        this.creationTimestamp = j;
        this.active = z;
        this.triggerEventName = str3;
        this.zzagf = zzex;
        this.triggerTimeout = j2;
        this.zzagg = zzex2;
        this.timeToLive = j3;
        this.zzagh = zzex3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.origin, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzage, i, false);
        SafeParcelWriter.writeLong(parcel, 5, this.creationTimestamp);
        SafeParcelWriter.writeBoolean(parcel, 6, this.active);
        SafeParcelWriter.writeString(parcel, 7, this.triggerEventName, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzagf, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.triggerTimeout);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzagg, i, false);
        SafeParcelWriter.writeLong(parcel, 11, this.timeToLive);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzagh, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
