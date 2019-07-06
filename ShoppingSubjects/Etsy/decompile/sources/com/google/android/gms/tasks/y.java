package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

final class y<TResult> extends f<TResult> {
    private final Object a = new Object();
    private final w<TResult> b = new w<>();
    private boolean c;
    private volatile boolean d;
    private TResult e;
    private Exception f;

    y() {
    }

    private final void g() {
        Preconditions.checkState(this.c, "Task is not yet complete");
    }

    private final void h() {
        Preconditions.checkState(!this.c, "Task is already complete");
    }

    private final void i() {
        if (this.d) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    private final void j() {
        synchronized (this.a) {
            if (this.c) {
                this.b.a((f<TResult>) this);
            }
        }
    }

    @NonNull
    public final <TContinuationResult> f<TContinuationResult> a(@NonNull a<TResult, TContinuationResult> aVar) {
        return a(h.a, aVar);
    }

    @NonNull
    public final f<TResult> a(@NonNull c<TResult> cVar) {
        return a(h.a, cVar);
    }

    @NonNull
    public final <TContinuationResult> f<TContinuationResult> a(@NonNull Executor executor, @NonNull a<TResult, TContinuationResult> aVar) {
        y yVar = new y();
        this.b.a((v<TResult>) new j<TResult>(executor, aVar, yVar));
        j();
        return yVar;
    }

    @NonNull
    public final f<TResult> a(@NonNull Executor executor, @NonNull b bVar) {
        this.b.a((v<TResult>) new n<TResult>(executor, bVar));
        j();
        return this;
    }

    @NonNull
    public final f<TResult> a(@NonNull Executor executor, @NonNull c<TResult> cVar) {
        this.b.a((v<TResult>) new p<TResult>(executor, cVar));
        j();
        return this;
    }

    @NonNull
    public final f<TResult> a(@NonNull Executor executor, @NonNull d dVar) {
        this.b.a((v<TResult>) new r<TResult>(executor, dVar));
        j();
        return this;
    }

    @NonNull
    public final f<TResult> a(@NonNull Executor executor, @NonNull e<? super TResult> eVar) {
        this.b.a((v<TResult>) new t<TResult>(executor, eVar));
        j();
        return this;
    }

    public final <X extends Throwable> TResult a(@NonNull Class<X> cls) throws Throwable {
        TResult tresult;
        synchronized (this.a) {
            g();
            i();
            if (cls.isInstance(this.f)) {
                throw ((Throwable) cls.cast(this.f));
            } else if (this.f != null) {
                throw new RuntimeExecutionException(this.f);
            } else {
                tresult = this.e;
            }
        }
        return tresult;
    }

    public final void a(@NonNull Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.a) {
            h();
            this.c = true;
            this.f = exc;
        }
        this.b.a((f<TResult>) this);
    }

    public final void a(TResult tresult) {
        synchronized (this.a) {
            h();
            this.c = true;
            this.e = tresult;
        }
        this.b.a((f<TResult>) this);
    }

    public final boolean a() {
        boolean z;
        synchronized (this.a) {
            z = this.c;
        }
        return z;
    }

    @NonNull
    public final <TContinuationResult> f<TContinuationResult> b(@NonNull Executor executor, @NonNull a<TResult, f<TContinuationResult>> aVar) {
        y yVar = new y();
        this.b.a((v<TResult>) new l<TResult>(executor, aVar, yVar));
        j();
        return yVar;
    }

    public final boolean b() {
        boolean z;
        synchronized (this.a) {
            z = this.c && !this.d && this.f == null;
        }
        return z;
    }

    public final boolean b(@NonNull Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.a) {
            if (this.c) {
                return false;
            }
            this.c = true;
            this.f = exc;
            this.b.a((f<TResult>) this);
            return true;
        }
    }

    public final boolean b(TResult tresult) {
        synchronized (this.a) {
            if (this.c) {
                return false;
            }
            this.c = true;
            this.e = tresult;
            this.b.a((f<TResult>) this);
            return true;
        }
    }

    public final boolean c() {
        return this.d;
    }

    public final TResult d() {
        TResult tresult;
        synchronized (this.a) {
            g();
            i();
            if (this.f != null) {
                throw new RuntimeExecutionException(this.f);
            }
            tresult = this.e;
        }
        return tresult;
    }

    @Nullable
    public final Exception e() {
        Exception exc;
        synchronized (this.a) {
            exc = this.f;
        }
        return exc;
    }

    public final boolean f() {
        synchronized (this.a) {
            if (this.c) {
                return false;
            }
            this.c = true;
            this.d = true;
            this.b.a((f<TResult>) this);
            return true;
        }
    }
}
