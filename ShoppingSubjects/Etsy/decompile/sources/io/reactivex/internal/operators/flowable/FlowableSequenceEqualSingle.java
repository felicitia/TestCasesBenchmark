package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.a;
import io.reactivex.functions.d;
import io.reactivex.internal.a.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.v;
import io.reactivex.x;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;

public final class FlowableSequenceEqualSingle<T> extends v<Boolean> {
    final b<? extends T> a;
    final b<? extends T> b;
    final d<? super T, ? super T> c;
    final int d;

    static final class EqualCoordinator<T> extends AtomicInteger implements Disposable, a {
        private static final long serialVersionUID = -6178010334400373240L;
        final x<? super Boolean> actual;
        final d<? super T, ? super T> comparer;
        final AtomicThrowable error = new AtomicThrowable();
        final EqualSubscriber<T> first;
        final EqualSubscriber<T> second;
        T v1;
        T v2;

        EqualCoordinator(x<? super Boolean> xVar, int i, d<? super T, ? super T> dVar) {
            this.actual = xVar;
            this.comparer = dVar;
            this.first = new EqualSubscriber<>(this, i);
            this.second = new EqualSubscriber<>(this, i);
        }

        /* access modifiers changed from: 0000 */
        public void subscribe(b<? extends T> bVar, b<? extends T> bVar2) {
            bVar.subscribe(this.first);
            bVar2.subscribe(this.second);
        }

        public void dispose() {
            this.first.cancel();
            this.second.cancel();
            if (getAndIncrement() == 0) {
                this.first.clear();
                this.second.clear();
            }
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) this.first.get());
        }

        /* access modifiers changed from: 0000 */
        public void cancelAndClear() {
            this.first.cancel();
            this.first.clear();
            this.second.cancel();
            this.second.clear();
        }

        public void drain() {
            if (getAndIncrement() == 0) {
                int i = 1;
                do {
                    g<T> gVar = this.first.queue;
                    g<T> gVar2 = this.second.queue;
                    if (gVar != null && gVar2 != null) {
                        while (!isDisposed()) {
                            if (((Throwable) this.error.get()) != null) {
                                cancelAndClear();
                                this.actual.onError(this.error.terminate());
                                return;
                            }
                            boolean z = this.first.done;
                            T t = this.v1;
                            if (t == null) {
                                try {
                                    t = gVar.poll();
                                    this.v1 = t;
                                } catch (Throwable th) {
                                    a.b(th);
                                    cancelAndClear();
                                    this.error.addThrowable(th);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean z2 = t == null;
                            boolean z3 = this.second.done;
                            T t2 = this.v2;
                            if (t2 == null) {
                                try {
                                    t2 = gVar2.poll();
                                    this.v2 = t2;
                                } catch (Throwable th2) {
                                    a.b(th2);
                                    cancelAndClear();
                                    this.error.addThrowable(th2);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean z4 = t2 == null;
                            if (z && z3 && z2 && z4) {
                                this.actual.onSuccess(Boolean.valueOf(true));
                                return;
                            } else if (z && z3 && z2 != z4) {
                                cancelAndClear();
                                this.actual.onSuccess(Boolean.valueOf(false));
                                return;
                            } else if (!z2 && !z4) {
                                try {
                                    if (!this.comparer.a(t, t2)) {
                                        cancelAndClear();
                                        this.actual.onSuccess(Boolean.valueOf(false));
                                        return;
                                    }
                                    this.v1 = null;
                                    this.v2 = null;
                                    this.first.request();
                                    this.second.request();
                                } catch (Throwable th3) {
                                    a.b(th3);
                                    cancelAndClear();
                                    this.error.addThrowable(th3);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                        }
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (isDisposed()) {
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (((Throwable) this.error.get()) != null) {
                        cancelAndClear();
                        this.actual.onError(this.error.terminate());
                        return;
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        public void innerError(Throwable th) {
            if (this.error.addThrowable(th)) {
                drain();
            } else {
                io.reactivex.d.a.a(th);
            }
        }
    }

    public void b(x<? super Boolean> xVar) {
        EqualCoordinator equalCoordinator = new EqualCoordinator(xVar, this.d, this.c);
        xVar.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe(this.a, this.b);
    }
}
