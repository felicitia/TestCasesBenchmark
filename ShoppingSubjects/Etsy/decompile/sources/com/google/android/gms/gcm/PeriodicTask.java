package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class PeriodicTask extends Task {
    public static final Creator<PeriodicTask> CREATOR = new h();
    protected long mFlexInSeconds;
    protected long mIntervalInSeconds;

    public static class a extends com.google.android.gms.gcm.Task.a {
        /* access modifiers changed from: private */
        public long j;
        /* access modifiers changed from: private */
        public long k;
    }

    @Deprecated
    private PeriodicTask(Parcel parcel) {
        super(parcel);
        this.mIntervalInSeconds = -1;
        this.mFlexInSeconds = -1;
        this.mIntervalInSeconds = parcel.readLong();
        this.mFlexInSeconds = Math.min(parcel.readLong(), this.mIntervalInSeconds);
    }

    /* synthetic */ PeriodicTask(Parcel parcel, h hVar) {
        this(parcel);
    }

    private PeriodicTask(a aVar) {
        super((com.google.android.gms.gcm.Task.a) aVar);
        this.mIntervalInSeconds = -1;
        this.mFlexInSeconds = -1;
        this.mIntervalInSeconds = aVar.j;
        this.mFlexInSeconds = Math.min(aVar.k, this.mIntervalInSeconds);
    }

    /* synthetic */ PeriodicTask(a aVar, h hVar) {
        this(aVar);
    }

    public long getFlex() {
        return this.mFlexInSeconds;
    }

    public long getPeriod() {
        return this.mIntervalInSeconds;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("period", this.mIntervalInSeconds);
        bundle.putLong("period_flex", this.mFlexInSeconds);
    }

    public String toString() {
        String obj = super.toString();
        long period = getPeriod();
        long flex = getFlex();
        StringBuilder sb = new StringBuilder(54 + String.valueOf(obj).length());
        sb.append(obj);
        sb.append(" period=");
        sb.append(period);
        sb.append(" flex=");
        sb.append(flex);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.mIntervalInSeconds);
        parcel.writeLong(this.mFlexInSeconds);
    }
}
