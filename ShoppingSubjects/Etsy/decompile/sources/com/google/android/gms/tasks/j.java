package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class j<TResult, TContinuationResult> implements v<TResult> {
    private final Executor a;
    /* access modifiers changed from: private */
    public final a<TResult, TContinuationResult> b;
    /* access modifiers changed from: private */
    public final y<TContinuationResult> c;

    public j(@NonNull Executor executor, @NonNull a<TResult, TContinuationResult> aVar, @NonNull y<TContinuationResult> yVar) {
        this.a = executor;
        this.b = aVar;
        this.c = yVar;
    }

    public final void a(@NonNull f<TResult> fVar) {
        this.a.execute(new k(this, fVar));
    }
}
