package com.google.firebase.appindexing.internal;

import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.d;
import com.google.android.gms.tasks.f;
import java.util.concurrent.Executor;

@VisibleForTesting
final class g {
    final /* synthetic */ f a;
    /* access modifiers changed from: private */
    public final zzx b;
    /* access modifiers changed from: private */
    public final com.google.android.gms.tasks.g<Void> c = new com.google.android.gms.tasks.g<>();

    public g(f fVar, zzx zzx) {
        this.a = fVar;
        this.b = zzx;
    }

    public final f<Void> a() {
        return this.c.a();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Exception exc) {
        g gVar;
        synchronized (this.a.c) {
            if (this.a.c.peek() == this) {
                this.a.c.remove();
                this.a.d = 0;
                gVar = (g) this.a.c.peek();
            } else {
                gVar = null;
            }
        }
        this.c.b(exc);
        if (gVar != null) {
            gVar.b();
        }
    }

    public final void b() {
        synchronized (this.a.c) {
            Preconditions.checkState(this.a.d == 0);
            this.a.d = 1;
        }
        this.a.a.doWrite((TaskApiCall<A, TResult>) new i<A,TResult>(this)).a((Executor) this.a, (d) new h(this));
    }
}
