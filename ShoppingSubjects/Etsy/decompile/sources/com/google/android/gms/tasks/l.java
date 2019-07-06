package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class l<TResult, TContinuationResult> implements b, d, e<TContinuationResult>, v<TResult> {
    private final Executor a;
    /* access modifiers changed from: private */
    public final a<TResult, f<TContinuationResult>> b;
    /* access modifiers changed from: private */
    public final y<TContinuationResult> c;

    public l(@NonNull Executor executor, @NonNull a<TResult, f<TContinuationResult>> aVar, @NonNull y<TContinuationResult> yVar) {
        this.a = executor;
        this.b = aVar;
        this.c = yVar;
    }

    public final void a() {
        this.c.f();
    }

    public final void a(@NonNull f<TResult> fVar) {
        this.a.execute(new m(this, fVar));
    }

    public final void a(@NonNull Exception exc) {
        this.c.a(exc);
    }

    public final void a(TContinuationResult tcontinuationresult) {
        this.c.a(tcontinuationresult);
    }
}
