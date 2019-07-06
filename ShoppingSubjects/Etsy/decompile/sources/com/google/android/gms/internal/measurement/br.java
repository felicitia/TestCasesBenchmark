package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread.UncaughtExceptionHandler;

final class br implements UncaughtExceptionHandler {
    private final String a;
    private final /* synthetic */ bq b;

    public br(bq bqVar, String str) {
        this.b = bqVar;
        Preconditions.checkNotNull(str);
        this.a = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.b.r().h_().a(this.a, th);
    }
}
