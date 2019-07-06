package com.google.android.gms.internal.measurement;

import android.app.job.JobParameters;

final /* synthetic */ class el implements Runnable {
    private final ej a;
    private final aq b;
    private final JobParameters c;

    el(ej ejVar, aq aqVar, JobParameters jobParameters) {
        this.a = ejVar;
        this.b = aqVar;
        this.c = jobParameters;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
