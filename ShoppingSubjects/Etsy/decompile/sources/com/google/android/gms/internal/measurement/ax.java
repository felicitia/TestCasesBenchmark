package com.google.android.gms.internal.measurement;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.Map;

@WorkerThread
final class ax implements Runnable {
    private final aw a;
    private final int b;
    private final Throwable c;
    private final byte[] d;
    private final String e;
    private final Map<String, List<String>> f;

    private ax(String str, aw awVar, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        Preconditions.checkNotNull(awVar);
        this.a = awVar;
        this.b = i;
        this.c = th;
        this.d = bArr;
        this.e = str;
        this.f = map;
    }

    public final void run() {
        this.a.a(this.e, this.b, this.c, this.d, this.f);
    }
}
