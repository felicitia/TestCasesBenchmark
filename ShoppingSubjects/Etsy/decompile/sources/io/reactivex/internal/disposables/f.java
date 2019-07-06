package io.reactivex.internal.disposables;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.NotificationLite;

/* compiled from: ObserverFullArbiter */
public final class f<T> extends c implements Disposable {
    final Observer<? super T> b;
    final a<Object> c;
    volatile Disposable d = EmptyDisposable.INSTANCE;
    Disposable e;
    volatile boolean f;

    public f(Observer<? super T> observer, Disposable disposable, int i) {
        this.b = observer;
        this.e = disposable;
        this.c = new a<>(i);
    }

    public void dispose() {
        if (!this.f) {
            this.f = true;
            a();
        }
    }

    public boolean isDisposed() {
        Disposable disposable = this.e;
        return disposable != null ? disposable.isDisposed() : this.f;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        Disposable disposable = this.e;
        this.e = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public boolean a(Disposable disposable) {
        if (this.f) {
            return false;
        }
        this.c.a(this.d, NotificationLite.disposable(disposable));
        b();
        return true;
    }

    public boolean a(T t, Disposable disposable) {
        if (this.f) {
            return false;
        }
        this.c.a(disposable, NotificationLite.next(t));
        b();
        return true;
    }

    public void a(Throwable th, Disposable disposable) {
        if (this.f) {
            io.reactivex.d.a.a(th);
            return;
        }
        this.c.a(disposable, NotificationLite.error(th));
        b();
    }

    public void b(Disposable disposable) {
        this.c.a(disposable, NotificationLite.complete());
        b();
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        if (this.a.getAndIncrement() == 0) {
            a<Object> aVar = this.c;
            Observer<? super T> observer = this.b;
            int i = 1;
            while (true) {
                Object poll = aVar.poll();
                if (poll == null) {
                    i = this.a.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    Object poll2 = aVar.poll();
                    if (poll == this.d) {
                        if (NotificationLite.isDisposable(poll2)) {
                            Disposable disposable = NotificationLite.getDisposable(poll2);
                            this.d.dispose();
                            if (!this.f) {
                                this.d = disposable;
                            } else {
                                disposable.dispose();
                            }
                        } else if (NotificationLite.isError(poll2)) {
                            aVar.clear();
                            a();
                            Throwable error = NotificationLite.getError(poll2);
                            if (!this.f) {
                                this.f = true;
                                observer.onError(error);
                            } else {
                                io.reactivex.d.a.a(error);
                            }
                        } else if (NotificationLite.isComplete(poll2)) {
                            aVar.clear();
                            a();
                            if (!this.f) {
                                this.f = true;
                                observer.onComplete();
                            }
                        } else {
                            observer.onNext(NotificationLite.getValue(poll2));
                        }
                    }
                }
            }
        }
    }
}
