package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.t;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableZip<T, R> extends q<R> {
    final t<? extends T>[] a;
    final Iterable<? extends t<? extends T>> b;
    final g<? super Object[], ? extends R> c;
    final int d;
    final boolean e;

    static final class ZipCoordinator<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 2983708048395377667L;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        final boolean delayError;
        final a<T, R>[] observers;
        final T[] row;
        final g<? super Object[], ? extends R> zipper;

        ZipCoordinator(Observer<? super R> observer, g<? super Object[], ? extends R> gVar, int i, boolean z) {
            this.actual = observer;
            this.zipper = gVar;
            this.observers = new a[i];
            this.row = (Object[]) new Object[i];
            this.delayError = z;
        }

        public void subscribe(t<? extends T>[] tVarArr, int i) {
            a<T, R>[] aVarArr = this.observers;
            int length = aVarArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                aVarArr[i2] = new a<>(this, i);
            }
            lazySet(0);
            this.actual.onSubscribe(this);
            for (int i3 = 0; i3 < length && !this.cancelled; i3++) {
                tVarArr[i3].subscribe(aVarArr[i3]);
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void cancel() {
            clear();
            cancelSources();
        }

        /* access modifiers changed from: 0000 */
        public void cancelSources() {
            for (a<T, R> a : this.observers) {
                a.a();
            }
        }

        /* access modifiers changed from: 0000 */
        public void clear() {
            for (a<T, R> aVar : this.observers) {
                aVar.b.clear();
            }
        }

        public void drain() {
            int i;
            if (getAndIncrement() == 0) {
                a<T, R>[] aVarArr = this.observers;
                Observer<? super R> observer = this.actual;
                T[] tArr = this.row;
                boolean z = this.delayError;
                int i2 = 1;
                while (true) {
                    int length = aVarArr.length;
                    int i3 = 0;
                    int i4 = 0;
                    int i5 = 0;
                    while (i3 < length) {
                        a<T, R> aVar = aVarArr[i3];
                        if (tArr[i4] == null) {
                            boolean z2 = aVar.c;
                            T poll = aVar.b.poll();
                            boolean z3 = poll == null;
                            i = i3;
                            if (!checkTerminated(z2, z3, observer, z, aVar)) {
                                if (!z3) {
                                    tArr[i4] = poll;
                                } else {
                                    i5++;
                                }
                            } else {
                                return;
                            }
                        } else {
                            a<T, R> aVar2 = aVar;
                            i = i3;
                            if (aVar2.c && !z) {
                                Throwable th = aVar2.d;
                                if (th != null) {
                                    cancel();
                                    observer.onError(th);
                                    return;
                                }
                            }
                        }
                        i4++;
                        i3 = i + 1;
                    }
                    if (i5 != 0) {
                        i2 = addAndGet(-i2);
                        if (i2 == 0) {
                            return;
                        }
                    } else {
                        try {
                            observer.onNext(io.reactivex.internal.functions.a.a(this.zipper.apply(tArr.clone()), "The zipper returned a null value"));
                            Arrays.fill(tArr, null);
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            io.reactivex.exceptions.a.b(th3);
                            cancel();
                            observer.onError(th3);
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, Observer<? super R> observer, boolean z3, a<?, ?> aVar) {
            if (this.cancelled) {
                cancel();
                return true;
            }
            if (z) {
                if (!z3) {
                    Throwable th = aVar.d;
                    if (th != null) {
                        cancel();
                        observer.onError(th);
                        return true;
                    } else if (z2) {
                        cancel();
                        observer.onComplete();
                        return true;
                    }
                } else if (z2) {
                    Throwable th2 = aVar.d;
                    cancel();
                    if (th2 != null) {
                        observer.onError(th2);
                    } else {
                        observer.onComplete();
                    }
                    return true;
                }
            }
            return false;
        }
    }

    static final class a<T, R> implements Observer<T> {
        final ZipCoordinator<T, R> a;
        final io.reactivex.internal.queue.a<T> b;
        volatile boolean c;
        Throwable d;
        final AtomicReference<Disposable> e = new AtomicReference<>();

        a(ZipCoordinator<T, R> zipCoordinator, int i) {
            this.a = zipCoordinator;
            this.b = new io.reactivex.internal.queue.a<>(i);
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.e, disposable);
        }

        public void onNext(T t) {
            this.b.offer(t);
            this.a.drain();
        }

        public void onError(Throwable th) {
            this.d = th;
            this.c = true;
            this.a.drain();
        }

        public void onComplete() {
            this.c = true;
            this.a.drain();
        }

        public void a() {
            DisposableHelper.dispose(this.e);
        }
    }

    public void a(Observer<? super R> observer) {
        t<? extends T>[] tVarArr;
        int i;
        t<? extends T>[] tVarArr2 = this.a;
        if (tVarArr2 == null) {
            q[] qVarArr = new q[8];
            tVarArr = qVarArr;
            i = 0;
            for (t<? extends T> tVar : this.b) {
                if (i == tVarArr.length) {
                    t<? extends T>[] tVarArr3 = new t[((i >> 2) + i)];
                    System.arraycopy(tVarArr, 0, tVarArr3, 0, i);
                    tVarArr = tVarArr3;
                }
                int i2 = i + 1;
                tVarArr[i] = tVar;
                i = i2;
            }
        } else {
            tVarArr = tVarArr2;
            i = tVarArr2.length;
        }
        if (i == 0) {
            EmptyDisposable.complete(observer);
        } else {
            new ZipCoordinator(observer, this.c, i, this.e).subscribe(tVarArr, this.d);
        }
    }
}
