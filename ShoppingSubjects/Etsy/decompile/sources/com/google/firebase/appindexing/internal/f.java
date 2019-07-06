package com.google.firebase.appindexing.internal;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.c;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

@VisibleForTesting
final class f implements c<Void>, Executor {
    /* access modifiers changed from: private */
    @NonNull
    public final GoogleApi<?> a;
    @NonNull
    private final Handler b;
    /* access modifiers changed from: private */
    public final Queue<g> c = new ArrayDeque();
    /* access modifiers changed from: private */
    public int d = 0;

    public f(@NonNull GoogleApi<?> googleApi) {
        this.a = googleApi;
        this.b = new Handler(googleApi.getLooper());
    }

    public final com.google.android.gms.tasks.f<Void> a(zzx zzx) {
        boolean isEmpty;
        g gVar = new g(this, zzx);
        com.google.android.gms.tasks.f<Void> a2 = gVar.a();
        a2.a((Executor) this, (c<TResult>) this);
        synchronized (this.c) {
            isEmpty = this.c.isEmpty();
            this.c.add(gVar);
        }
        if (isEmpty) {
            gVar.b();
        }
        return a2;
    }

    public final void execute(Runnable runnable) {
        this.b.post(runnable);
    }

    public final void onComplete(@NonNull com.google.android.gms.tasks.f<Void> fVar) {
        g gVar;
        synchronized (this.c) {
            if (this.d == 2) {
                gVar = (g) this.c.peek();
                Preconditions.checkState(gVar != null);
            } else {
                gVar = null;
            }
            this.d = 0;
        }
        if (gVar != null) {
            gVar.b();
        }
    }
}
