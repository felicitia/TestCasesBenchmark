package com.google.firebase.perf.metrics;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.concurrent.atomic.AtomicLong;

public class zza implements Parcelable {
    public static final Creator<zza> CREATOR = new zzb();
    private final String mName;
    private AtomicLong zzdm;

    public zza(String str) {
        this.mName = str;
        this.zzdm = new AtomicLong(0);
    }

    public int describeContents() {
        return 0;
    }

    public final void zzi(long j) {
        this.zzdm.addAndGet(j);
    }

    /* access modifiers changed from: 0000 */
    public final long getCount() {
        return this.zzdm.get();
    }

    /* access modifiers changed from: 0000 */
    public final void zzj(long j) {
        this.zzdm.set(j);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeLong(this.zzdm.get());
    }

    private zza(Parcel parcel) {
        this.mName = parcel.readString();
        this.zzdm = new AtomicLong(parcel.readLong());
    }

    /* synthetic */ zza(Parcel parcel, zzb zzb) {
        this(parcel);
    }
}
