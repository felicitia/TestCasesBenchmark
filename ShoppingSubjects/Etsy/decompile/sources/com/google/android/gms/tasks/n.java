package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class n<TResult> implements v<TResult> {
    private final Executor a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    /* access modifiers changed from: private */
    public b c;

    public n(@NonNull Executor executor, @NonNull b bVar) {
        this.a = executor;
        this.c = bVar;
    }

    public final void a(@NonNull f fVar) {
        if (fVar.c()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.a.execute(new o(this));
                }
            }
        }
    }
}
