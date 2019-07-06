package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class OneoffTask extends Task {
    public static final Creator<OneoffTask> CREATOR = new f();
    private final long zzaj;
    private final long zzak;

    public static class a extends com.google.android.gms.gcm.Task.a {
        /* access modifiers changed from: private */
        public long j;
        /* access modifiers changed from: private */
        public long k;
    }

    @Deprecated
    private OneoffTask(Parcel parcel) {
        super(parcel);
        this.zzaj = parcel.readLong();
        this.zzak = parcel.readLong();
    }

    /* synthetic */ OneoffTask(Parcel parcel, f fVar) {
        this(parcel);
    }

    private OneoffTask(a aVar) {
        super((com.google.android.gms.gcm.Task.a) aVar);
        this.zzaj = aVar.j;
        this.zzak = aVar.k;
    }

    /* synthetic */ OneoffTask(a aVar, f fVar) {
        this(aVar);
    }

    public long getWindowEnd() {
        return this.zzak;
    }

    public long getWindowStart() {
        return this.zzaj;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("window_start", this.zzaj);
        bundle.putLong("window_end", this.zzak);
    }

    public String toString() {
        String obj = super.toString();
        long windowStart = getWindowStart();
        long windowEnd = getWindowEnd();
        StringBuilder sb = new StringBuilder(64 + String.valueOf(obj).length());
        sb.append(obj);
        sb.append(" windowStart=");
        sb.append(windowStart);
        sb.append(" windowEnd=");
        sb.append(windowEnd);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.zzaj);
        parcel.writeLong(this.zzak);
    }
}
