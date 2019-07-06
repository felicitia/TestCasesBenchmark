package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.a.b;
import io.reactivex.internal.disposables.DisposableHelper;

/* compiled from: BasicFuseableObserver */
public abstract class a<T, R> implements Observer<T>, b<R> {
    protected final Observer<? super R> a;
    protected Disposable b;
    protected b<T> c;
    protected boolean d;
    protected int e;

    /* access modifiers changed from: protected */
    public boolean a() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    public a(Observer<? super R> observer) {
        this.a = observer;
    }

    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.b, disposable)) {
            this.b = disposable;
            if (disposable instanceof b) {
                this.c = (b) disposable;
            }
            if (a()) {
                this.a.onSubscribe(this);
                b();
            }
        }
    }

    public void onError(Throwable th) {
        if (this.d) {
            io.reactivex.d.a.a(th);
            return;
        }
        this.d = true;
        this.a.onError(th);
    }

    /* access modifiers changed from: protected */
    public final void a(Throwable th) {
        io.reactivex.exceptions.a.b(th);
        this.b.dispose();
        onError(th);
    }

    public void onComplete() {
        if (!this.d) {
            this.d = true;
            this.a.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public final int a(int i) {
        b<T> bVar = this.c;
        if (bVar == null || (i & 4) != 0) {
            return 0;
        }
        int requestFusion = bVar.requestFusion(i);
        if (requestFusion != 0) {
            this.e = requestFusion;
        }
        return requestFusion;
    }

    public void dispose() {
        this.b.dispose();
    }

    public boolean isDisposed() {
        return this.b.isDisposed();
    }

    public boolean isEmpty() {
        return this.c.isEmpty();
    }

    public void clear() {
        this.c.clear();
    }

    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
