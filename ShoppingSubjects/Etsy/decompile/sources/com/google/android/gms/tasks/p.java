package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class p<TResult> implements v<TResult> {
    private final Executor a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    /* access modifiers changed from: private */
    public c<TResult> c;

    public p(@NonNull Executor executor, @NonNull c<TResult> cVar) {
        this.a = executor;
        this.c = cVar;
    }

    public final void a(@NonNull f<TResult> fVar) {
        synchronized (this.b) {
            if (this.c != null) {
                this.a.execute(new q(this, fVar));
            }
        }
    }
}
