package com.google.android.gms.internal.firebase-perf;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.concurrent.TimeUnit;

public class zzaa implements Parcelable {
    public static final Creator<zzaa> CREATOR = new zzab();
    private long zzfc;
    private long zzfd;

    public zzaa() {
        this.zzfc = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());
        this.zzfd = System.nanoTime();
    }

    public int describeContents() {
        return 0;
    }

    public final void reset() {
        this.zzfc = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());
        this.zzfd = System.nanoTime();
    }

    public final long zzar() {
        return this.zzfc;
    }

    public final long zzas() {
        return TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - this.zzfd);
    }

    public final long zza(zzaa zzaa) {
        return TimeUnit.NANOSECONDS.toMicros(zzaa.zzfd - this.zzfd);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.zzfc);
        parcel.writeLong(this.zzfd);
    }

    private zzaa(Parcel parcel) {
        this.zzfc = parcel.readLong();
        this.zzfd = parcel.readLong();
    }

    /* synthetic */ zzaa(Parcel parcel, zzab zzab) {
        this(parcel);
    }
}
