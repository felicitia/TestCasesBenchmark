package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class t<TResult> implements v<TResult> {
    private final Executor a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    /* access modifiers changed from: private */
    public e<? super TResult> c;

    public t(@NonNull Executor executor, @NonNull e<? super TResult> eVar) {
        this.a = executor;
        this.c = eVar;
    }

    public final void a(@NonNull f<TResult> fVar) {
        if (fVar.b()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.a.execute(new u(this, fVar));
                }
            }
        }
    }
}
