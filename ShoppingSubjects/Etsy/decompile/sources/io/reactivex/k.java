package io.reactivex;

import io.reactivex.internal.functions.a;

/* compiled from: Maybe */
public abstract class k<T> implements o<T> {
    /* access modifiers changed from: protected */
    public abstract void b(m<? super T> mVar);

    public final void a(m<? super T> mVar) {
        a.a(mVar, "observer is null");
        m a = io.reactivex.d.a.a(this, mVar);
        a.a(a, "observer returned by the RxJavaPlugins hook is null");
        try {
            b(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }
}
