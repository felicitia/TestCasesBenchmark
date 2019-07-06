package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.f;
import io.reactivex.q;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCache<T> extends a<T, T> {
    final a<T> b;
    final AtomicBoolean c;

    static final class ReplayDisposable<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 7058506693698832024L;
        volatile boolean cancelled;
        final Observer<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        int index;
        final a<T> state;

        ReplayDisposable(Observer<? super T> observer, a<T> aVar) {
            this.child = observer;
            this.state = aVar;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.b(this);
            }
        }

        public void replay() {
            if (getAndIncrement() == 0) {
                Observer<? super T> observer = this.child;
                int i = 1;
                while (!this.cancelled) {
                    int c = this.state.c();
                    if (c != 0) {
                        Object[] objArr = this.currentBuffer;
                        if (objArr == null) {
                            objArr = this.state.b();
                            this.currentBuffer = objArr;
                        }
                        int length = objArr.length - 1;
                        int i2 = this.index;
                        int i3 = this.currentIndexInBuffer;
                        while (i2 < c) {
                            if (!this.cancelled) {
                                if (i3 == length) {
                                    objArr = (Object[]) objArr[length];
                                    i3 = 0;
                                }
                                if (!NotificationLite.accept(objArr[i3], observer)) {
                                    i3++;
                                    i2++;
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                        if (!this.cancelled) {
                            this.index = i2;
                            this.currentIndexInBuffer = i3;
                            this.currentBuffer = objArr;
                        } else {
                            return;
                        }
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class a<T> extends f implements Observer<T> {
        static final ReplayDisposable[] d = new ReplayDisposable[0];
        static final ReplayDisposable[] e = new ReplayDisposable[0];
        final q<? extends T> a;
        final SequentialDisposable b;
        final AtomicReference<ReplayDisposable<T>[]> c;
        volatile boolean f;
        boolean g;

        public boolean a(ReplayDisposable<T> replayDisposable) {
            ReplayDisposable[] replayDisposableArr;
            ReplayDisposable[] replayDisposableArr2;
            do {
                replayDisposableArr = (ReplayDisposable[]) this.c.get();
                if (replayDisposableArr == e) {
                    return false;
                }
                int length = replayDisposableArr.length;
                replayDisposableArr2 = new ReplayDisposable[(length + 1)];
                System.arraycopy(replayDisposableArr, 0, replayDisposableArr2, 0, length);
                replayDisposableArr2[length] = replayDisposable;
            } while (!this.c.compareAndSet(replayDisposableArr, replayDisposableArr2));
            return true;
        }

        public void b(ReplayDisposable<T> replayDisposable) {
            ReplayDisposable[] replayDisposableArr;
            ReplayDisposable[] replayDisposableArr2;
            do {
                replayDisposableArr = (ReplayDisposable[]) this.c.get();
                int length = replayDisposableArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (replayDisposableArr[i2].equals(replayDisposable)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            replayDisposableArr2 = d;
                        } else {
                            ReplayDisposable[] replayDisposableArr3 = new ReplayDisposable[(length - 1)];
                            System.arraycopy(replayDisposableArr, 0, replayDisposableArr3, 0, i);
                            System.arraycopy(replayDisposableArr, i + 1, replayDisposableArr3, i, (length - i) - 1);
                            replayDisposableArr2 = replayDisposableArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.c.compareAndSet(replayDisposableArr, replayDisposableArr2));
        }

        public void onSubscribe(Disposable disposable) {
            this.b.update(disposable);
        }

        public void a() {
            this.a.subscribe(this);
            this.f = true;
        }

        public void onNext(T t) {
            if (!this.g) {
                a(NotificationLite.next(t));
                for (ReplayDisposable replay : (ReplayDisposable[]) this.c.get()) {
                    replay.replay();
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.g) {
                this.g = true;
                a(NotificationLite.error(th));
                this.b.dispose();
                for (ReplayDisposable replay : (ReplayDisposable[]) this.c.getAndSet(e)) {
                    replay.replay();
                }
            }
        }

        public void onComplete() {
            if (!this.g) {
                this.g = true;
                a(NotificationLite.complete());
                this.b.dispose();
                for (ReplayDisposable replay : (ReplayDisposable[]) this.c.getAndSet(e)) {
                    replay.replay();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        ReplayDisposable replayDisposable = new ReplayDisposable(observer, this.b);
        observer.onSubscribe(replayDisposable);
        this.b.a(replayDisposable);
        if (!this.c.get() && this.c.compareAndSet(false, true)) {
            this.b.a();
        }
        replayDisposable.replay();
    }
}
