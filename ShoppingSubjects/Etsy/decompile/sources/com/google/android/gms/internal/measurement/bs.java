package com.google.android.gms.internal.measurement;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class bs<V> extends FutureTask<V> implements Comparable<bs> {
    final boolean a;
    private final long b = bq.k.getAndIncrement();
    private final String c;
    private final /* synthetic */ bq d;

    bs(bq bqVar, Runnable runnable, boolean z, String str) {
        this.d = bqVar;
        super(runnable, null);
        Preconditions.checkNotNull(str);
        this.c = str;
        this.a = false;
        if (this.b == Long.MAX_VALUE) {
            bqVar.r().h_().a("Tasks index overflow");
        }
    }

    bs(bq bqVar, Callable<V> callable, boolean z, String str) {
        this.d = bqVar;
        super(callable);
        Preconditions.checkNotNull(str);
        this.c = str;
        this.a = z;
        if (this.b == Long.MAX_VALUE) {
            bqVar.r().h_().a("Tasks index overflow");
        }
    }

    public final /* synthetic */ int compareTo(@NonNull Object obj) {
        bs bsVar = (bs) obj;
        if (this.a != bsVar.a) {
            return this.a ? -1 : 1;
        }
        if (this.b < bsVar.b) {
            return -1;
        }
        if (this.b > bsVar.b) {
            return 1;
        }
        this.d.r().g().a("Two tasks share the same index. index", Long.valueOf(this.b));
        return 0;
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        this.d.r().h_().a(this.c, th);
        if (th instanceof zzgj) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }
}
