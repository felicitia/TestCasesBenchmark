package kotlin.coroutines.experimental.jvm.internal;

import kotlin.TypeCastException;
import kotlin.coroutines.experimental.a.a;
import kotlin.coroutines.experimental.b;
import kotlin.coroutines.experimental.d;
import kotlin.h;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: CoroutineImpl.kt */
public abstract class CoroutineImpl extends Lambda implements b<Object> {
    private final d _context;
    private b<Object> _facade;
    protected b<Object> completion;
    protected int label;

    /* access modifiers changed from: protected */
    public abstract Object doResume(Object obj, Throwable th);

    public CoroutineImpl(int i, b<Object> bVar) {
        super(i);
        this.completion = bVar;
        this.label = this.completion != null ? 0 : -1;
        b<Object> bVar2 = this.completion;
        this._context = bVar2 != null ? bVar2.getContext() : null;
    }

    public d getContext() {
        d dVar = this._context;
        if (dVar == null) {
            p.a();
        }
        return dVar;
    }

    public final b<Object> getFacade() {
        if (this._facade == null) {
            d dVar = this._context;
            if (dVar == null) {
                p.a();
            }
            this._facade = a.a(dVar, this);
        }
        b<Object> bVar = this._facade;
        if (bVar == null) {
            p.a();
        }
        return bVar;
    }

    public void resume(Object obj) {
        b<Object> bVar = this.completion;
        if (bVar == null) {
            p.a();
        }
        try {
            Object doResume = doResume(obj, null);
            if (doResume == a.a()) {
                return;
            }
            if (bVar == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
            }
            bVar.resume(doResume);
        } catch (Throwable th) {
            bVar.resumeWithException(th);
        }
    }

    public void resumeWithException(Throwable th) {
        p.b(th, "exception");
        b<Object> bVar = this.completion;
        if (bVar == null) {
            p.a();
        }
        try {
            Object doResume = doResume(null, th);
            if (doResume == a.a()) {
                return;
            }
            if (bVar == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
            }
            bVar.resume(doResume);
        } catch (Throwable th2) {
            bVar.resumeWithException(th2);
        }
    }

    public b<h> create(b<?> bVar) {
        p.b(bVar, "completion");
        throw new IllegalStateException("create(Continuation) has not been overridden");
    }

    public b<h> create(Object obj, b<?> bVar) {
        p.b(bVar, "completion");
        throw new IllegalStateException("create(Any?;Continuation) has not been overridden");
    }
}
