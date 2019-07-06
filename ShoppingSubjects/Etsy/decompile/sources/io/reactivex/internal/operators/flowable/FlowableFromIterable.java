package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.c;

public final class FlowableFromIterable<T> extends g<T> {
    final Iterable<? extends T> b;

    static abstract class BaseRangeSubscription<T> extends BasicQueueSubscription<T> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        Iterator<? extends T> it;
        boolean once;

        /* access modifiers changed from: 0000 */
        public abstract void fastPath();

        public final int requestFusion(int i) {
            return i & 1;
        }

        /* access modifiers changed from: 0000 */
        public abstract void slowPath(long j);

        BaseRangeSubscription(Iterator<? extends T> it2) {
            this.it = it2;
        }

        public final T poll() {
            if (this.it == null) {
                return null;
            }
            if (!this.once) {
                this.once = true;
            } else if (!this.it.hasNext()) {
                return null;
            }
            return a.a(this.it.next(), "Iterator.next() returned a null value");
        }

        public final boolean isEmpty() {
            return this.it == null || !this.it.hasNext();
        }

        public final void clear() {
            this.it = null;
        }

        public final void request(long j) {
            if (SubscriptionHelper.validate(j) && b.a((AtomicLong) this, j) == 0) {
                if (j == Long.MAX_VALUE) {
                    fastPath();
                } else {
                    slowPath(j);
                }
            }
        }

        public final void cancel() {
            this.cancelled = true;
        }
    }

    static final class IteratorConditionalSubscription<T> extends BaseRangeSubscription<T> {
        private static final long serialVersionUID = -6022804456014692607L;
        final io.reactivex.internal.a.a<? super T> actual;

        IteratorConditionalSubscription(io.reactivex.internal.a.a<? super T> aVar, Iterator<? extends T> it) {
            super(it);
            this.actual = aVar;
        }

        /* access modifiers changed from: 0000 */
        public void fastPath() {
            Iterator it = this.it;
            io.reactivex.internal.a.a<? super T> aVar = this.actual;
            while (!this.cancelled) {
                try {
                    Object next = it.next();
                    if (!this.cancelled) {
                        if (next == null) {
                            aVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                            return;
                        }
                        aVar.tryOnNext(next);
                        if (!this.cancelled) {
                            try {
                                if (!it.hasNext()) {
                                    if (!this.cancelled) {
                                        aVar.onComplete();
                                    }
                                    return;
                                }
                            } catch (Throwable th) {
                                io.reactivex.exceptions.a.b(th);
                                aVar.onError(th);
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    aVar.onError(th2);
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void slowPath(long j) {
            Iterator it = this.it;
            io.reactivex.internal.a.a<? super T> aVar = this.actual;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2) {
                        j2 = get();
                        if (j3 == j2) {
                            j2 = addAndGet(-j3);
                        }
                    } else if (!this.cancelled) {
                        try {
                            Object next = it.next();
                            if (!this.cancelled) {
                                if (next == null) {
                                    aVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                                    return;
                                }
                                boolean tryOnNext = aVar.tryOnNext(next);
                                if (!this.cancelled) {
                                    try {
                                        if (!it.hasNext()) {
                                            if (!this.cancelled) {
                                                aVar.onComplete();
                                            }
                                            return;
                                        } else if (tryOnNext) {
                                            j3++;
                                        }
                                    } catch (Throwable th) {
                                        io.reactivex.exceptions.a.b(th);
                                        aVar.onError(th);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            io.reactivex.exceptions.a.b(th2);
                            aVar.onError(th2);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }

    static final class IteratorSubscription<T> extends BaseRangeSubscription<T> {
        private static final long serialVersionUID = -6022804456014692607L;
        final c<? super T> actual;

        IteratorSubscription(c<? super T> cVar, Iterator<? extends T> it) {
            super(it);
            this.actual = cVar;
        }

        /* access modifiers changed from: 0000 */
        public void fastPath() {
            Iterator it = this.it;
            c<? super T> cVar = this.actual;
            while (!this.cancelled) {
                try {
                    Object next = it.next();
                    if (!this.cancelled) {
                        if (next == null) {
                            cVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                            return;
                        }
                        cVar.onNext(next);
                        if (!this.cancelled) {
                            try {
                                if (!it.hasNext()) {
                                    if (!this.cancelled) {
                                        cVar.onComplete();
                                    }
                                    return;
                                }
                            } catch (Throwable th) {
                                io.reactivex.exceptions.a.b(th);
                                cVar.onError(th);
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    cVar.onError(th2);
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void slowPath(long j) {
            Iterator it = this.it;
            c<? super T> cVar = this.actual;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2) {
                        j2 = get();
                        if (j3 == j2) {
                            j2 = addAndGet(-j3);
                        }
                    } else if (!this.cancelled) {
                        try {
                            Object next = it.next();
                            if (!this.cancelled) {
                                if (next == null) {
                                    cVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                                    return;
                                }
                                cVar.onNext(next);
                                if (!this.cancelled) {
                                    try {
                                        if (!it.hasNext()) {
                                            if (!this.cancelled) {
                                                cVar.onComplete();
                                            }
                                            return;
                                        }
                                        j3++;
                                    } catch (Throwable th) {
                                        io.reactivex.exceptions.a.b(th);
                                        cVar.onError(th);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            io.reactivex.exceptions.a.b(th2);
                            cVar.onError(th2);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }

    public void a(c<? super T> cVar) {
        try {
            a(cVar, this.b.iterator());
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }

    public static <T> void a(c<? super T> cVar, Iterator<? extends T> it) {
        try {
            if (!it.hasNext()) {
                EmptySubscription.complete(cVar);
                return;
            }
            if (cVar instanceof io.reactivex.internal.a.a) {
                cVar.onSubscribe(new IteratorConditionalSubscription((io.reactivex.internal.a.a) cVar, it));
            } else {
                cVar.onSubscribe(new IteratorSubscription(cVar, it));
            }
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
