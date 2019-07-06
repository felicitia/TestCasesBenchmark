package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.a.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.x;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TestObserver<T> extends BaseTestConsumer<T, TestObserver<T>> implements Observer<T>, c, Disposable, m<T>, x<T> {
    private final Observer<? super T> i;
    private final AtomicReference<Disposable> j;
    private b<T> k;

    enum EmptyObserver implements Observer<Object> {
        INSTANCE;

        public void onComplete() {
        }

        public void onError(Throwable th) {
        }

        public void onNext(Object obj) {
        }

        public void onSubscribe(Disposable disposable) {
        }
    }

    public void onSubscribe(Disposable disposable) {
        this.e = Thread.currentThread();
        if (disposable == null) {
            this.c.add(new NullPointerException("onSubscribe received a null Subscription"));
        } else if (!this.j.compareAndSet(null, disposable)) {
            disposable.dispose();
            if (this.j.get() != DisposableHelper.DISPOSED) {
                List list = this.c;
                StringBuilder sb = new StringBuilder();
                sb.append("onSubscribe received multiple subscriptions: ");
                sb.append(disposable);
                list.add(new IllegalStateException(sb.toString()));
            }
        } else {
            if (this.g != 0 && (disposable instanceof b)) {
                this.k = (b) disposable;
                int requestFusion = this.k.requestFusion(this.g);
                this.h = requestFusion;
                if (requestFusion == 1) {
                    this.f = true;
                    this.e = Thread.currentThread();
                    while (true) {
                        try {
                            Object poll = this.k.poll();
                            if (poll == null) {
                                break;
                            }
                            this.b.add(poll);
                        } catch (Throwable th) {
                            this.c.add(th);
                        }
                    }
                    this.d++;
                    this.j.lazySet(DisposableHelper.DISPOSED);
                    return;
                }
            }
            this.i.onSubscribe(disposable);
        }
    }

    public void onNext(T t) {
        if (!this.f) {
            this.f = true;
            if (this.j.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        this.e = Thread.currentThread();
        if (this.h == 2) {
            while (true) {
                try {
                    Object poll = this.k.poll();
                    if (poll == null) {
                        break;
                    }
                    this.b.add(poll);
                } catch (Throwable th) {
                    this.c.add(th);
                    this.k.dispose();
                }
            }
            return;
        }
        this.b.add(t);
        if (t == null) {
            this.c.add(new NullPointerException("onNext received a null value"));
        }
        this.i.onNext(t);
    }

    public void onError(Throwable th) {
        if (!this.f) {
            this.f = true;
            if (this.j.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.e = Thread.currentThread();
            if (th == null) {
                this.c.add(new NullPointerException("onError received a null Throwable"));
            } else {
                this.c.add(th);
            }
            this.i.onError(th);
        } finally {
            this.a.countDown();
        }
    }

    public void onComplete() {
        if (!this.f) {
            this.f = true;
            if (this.j.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.e = Thread.currentThread();
            this.d++;
            this.i.onComplete();
        } finally {
            this.a.countDown();
        }
    }

    public final void dispose() {
        DisposableHelper.dispose(this.j);
    }

    public final boolean isDisposed() {
        return DisposableHelper.isDisposed((Disposable) this.j.get());
    }

    public void onSuccess(T t) {
        onNext(t);
        onComplete();
    }
}
