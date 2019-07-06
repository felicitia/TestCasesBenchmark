package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.a;
import io.reactivex.f;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.c;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;

public final class FlowableGenerate<T, S> extends g<T> {
    final Callable<S> b;
    final c<S, f<T>, S> c;
    final Consumer<? super S> d;

    static final class GeneratorSubscription<T, S> extends AtomicLong implements f<T>, Subscription {
        private static final long serialVersionUID = 7565982551505011832L;
        final org.reactivestreams.c<? super T> actual;
        volatile boolean cancelled;
        final Consumer<? super S> disposeState;
        final c<S, ? super f<T>, S> generator;
        boolean hasNext;
        S state;
        boolean terminate;

        GeneratorSubscription(org.reactivestreams.c<? super T> cVar, c<S, ? super f<T>, S> cVar2, Consumer<? super S> consumer, S s) {
            this.actual = cVar;
            this.generator = cVar2;
            this.disposeState = consumer;
            this.state = s;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j) && b.a((AtomicLong) this, j) == 0) {
                S s = this.state;
                c<S, ? super f<T>, S> cVar = this.generator;
                long j2 = j;
                do {
                    long j3 = 0;
                    while (true) {
                        if (j3 == j2) {
                            j2 = get();
                            if (j3 == j2) {
                                this.state = s;
                                j2 = addAndGet(-j3);
                            }
                        } else if (this.cancelled) {
                            this.state = null;
                            a(s);
                            return;
                        } else {
                            this.hasNext = false;
                            try {
                                S apply = cVar.apply(s, this);
                                if (this.terminate) {
                                    this.cancelled = true;
                                    this.state = null;
                                    a(apply);
                                    return;
                                }
                                s = apply;
                                j3++;
                            } catch (Throwable th) {
                                a.b(th);
                                this.cancelled = true;
                                this.state = null;
                                onError(th);
                                a(s);
                                return;
                            }
                        }
                    }
                } while (j2 != 0);
            }
        }

        private void a(S s) {
            try {
                this.disposeState.accept(s);
            } catch (Throwable th) {
                a.b(th);
                io.reactivex.d.a.a(th);
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                if (b.a((AtomicLong) this, 1) == 0) {
                    S s = this.state;
                    this.state = null;
                    a(s);
                }
            }
        }

        public void onNext(T t) {
            if (this.terminate) {
                return;
            }
            if (this.hasNext) {
                onError(new IllegalStateException("onNext already called in this generate turn"));
            } else if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                this.hasNext = true;
                this.actual.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (this.terminate) {
                io.reactivex.d.a.a(th);
                return;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.terminate = true;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.terminate) {
                this.terminate = true;
                this.actual.onComplete();
            }
        }
    }

    public void a(org.reactivestreams.c<? super T> cVar) {
        try {
            cVar.onSubscribe(new GeneratorSubscription(cVar, this.c, this.d, this.b.call()));
        } catch (Throwable th) {
            a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
