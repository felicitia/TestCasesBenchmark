package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.d;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.q;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableSequenceEqual<T> extends q<Boolean> {
    final t<? extends T> a;
    final t<? extends T> b;
    final d<? super T, ? super T> c;
    final int d;

    static final class EqualCoordinator<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -6178010334400373240L;
        final Observer<? super Boolean> actual;
        volatile boolean cancelled;
        final d<? super T, ? super T> comparer;
        final t<? extends T> first;
        final a<T>[] observers;
        final ArrayCompositeDisposable resources = new ArrayCompositeDisposable(2);
        final t<? extends T> second;
        T v1;
        T v2;

        EqualCoordinator(Observer<? super Boolean> observer, int i, t<? extends T> tVar, t<? extends T> tVar2, d<? super T, ? super T> dVar) {
            this.actual = observer;
            this.first = tVar;
            this.second = tVar2;
            this.comparer = dVar;
            a<T>[] aVarArr = new a[2];
            this.observers = aVarArr;
            aVarArr[0] = new a<>(this, 0, i);
            aVarArr[1] = new a<>(this, 1, i);
        }

        /* access modifiers changed from: 0000 */
        public boolean setDisposable(Disposable disposable, int i) {
            return this.resources.setResource(i, disposable);
        }

        /* access modifiers changed from: 0000 */
        public void subscribe() {
            a<T>[] aVarArr = this.observers;
            this.first.subscribe(aVarArr[0]);
            this.second.subscribe(aVarArr[1]);
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.resources.dispose();
                if (getAndIncrement() == 0) {
                    a<T>[] aVarArr = this.observers;
                    aVarArr[0].b.clear();
                    aVarArr[1].b.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void cancel(io.reactivex.internal.queue.a<T> aVar, io.reactivex.internal.queue.a<T> aVar2) {
            this.cancelled = true;
            aVar.clear();
            aVar2.clear();
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                a<T>[] aVarArr = this.observers;
                a<T> aVar = aVarArr[0];
                io.reactivex.internal.queue.a<T> aVar2 = aVar.b;
                a<T> aVar3 = aVarArr[1];
                io.reactivex.internal.queue.a<T> aVar4 = aVar3.b;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = aVar.d;
                    if (z) {
                        Throwable th = aVar.e;
                        if (th != null) {
                            cancel(aVar2, aVar4);
                            this.actual.onError(th);
                            return;
                        }
                    }
                    boolean z2 = aVar3.d;
                    if (z2) {
                        Throwable th2 = aVar3.e;
                        if (th2 != null) {
                            cancel(aVar2, aVar4);
                            this.actual.onError(th2);
                            return;
                        }
                    }
                    if (this.v1 == null) {
                        this.v1 = aVar2.poll();
                    }
                    boolean z3 = this.v1 == null;
                    if (this.v2 == null) {
                        this.v2 = aVar4.poll();
                    }
                    boolean z4 = this.v2 == null;
                    if (z && z2 && z3 && z4) {
                        this.actual.onNext(Boolean.valueOf(true));
                        this.actual.onComplete();
                        return;
                    } else if (!z || !z2 || z3 == z4) {
                        if (!z3 && !z4) {
                            try {
                                if (!this.comparer.a(this.v1, this.v2)) {
                                    cancel(aVar2, aVar4);
                                    this.actual.onNext(Boolean.valueOf(false));
                                    this.actual.onComplete();
                                    return;
                                }
                                this.v1 = null;
                                this.v2 = null;
                            } catch (Throwable th3) {
                                io.reactivex.exceptions.a.b(th3);
                                cancel(aVar2, aVar4);
                                this.actual.onError(th3);
                                return;
                            }
                        }
                        if (z3 || z4) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        }
                    } else {
                        cancel(aVar2, aVar4);
                        this.actual.onNext(Boolean.valueOf(false));
                        this.actual.onComplete();
                        return;
                    }
                }
                aVar2.clear();
                aVar4.clear();
            }
        }
    }

    static final class a<T> implements Observer<T> {
        final EqualCoordinator<T> a;
        final io.reactivex.internal.queue.a<T> b;
        final int c;
        volatile boolean d;
        Throwable e;

        a(EqualCoordinator<T> equalCoordinator, int i, int i2) {
            this.a = equalCoordinator;
            this.c = i;
            this.b = new io.reactivex.internal.queue.a<>(i2);
        }

        public void onSubscribe(Disposable disposable) {
            this.a.setDisposable(disposable, this.c);
        }

        public void onNext(T t) {
            this.b.offer(t);
            this.a.drain();
        }

        public void onError(Throwable th) {
            this.e = th;
            this.d = true;
            this.a.drain();
        }

        public void onComplete() {
            this.d = true;
            this.a.drain();
        }
    }

    public void a(Observer<? super Boolean> observer) {
        EqualCoordinator equalCoordinator = new EqualCoordinator(observer, this.d, this.a, this.b, this.c);
        observer.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe();
    }
}
