package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class r<TResult> implements v<TResult> {
    private final Executor a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    /* access modifiers changed from: private */
    public d c;

    public r(@NonNull Executor executor, @NonNull d dVar) {
        this.a = executor;
        this.c = dVar;
    }

    public final void a(@NonNull f<TResult> fVar) {
        if (!fVar.b() && !fVar.c()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.a.execute(new s(this, fVar));
                }
            }
        }
    }
}
