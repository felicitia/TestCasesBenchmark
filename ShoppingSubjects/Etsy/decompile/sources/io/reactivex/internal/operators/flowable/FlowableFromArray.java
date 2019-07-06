package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.a.a;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.c;

public final class FlowableFromArray<T> extends g<T> {
    final T[] b;

    static final class ArrayConditionalSubscription<T> extends BaseArraySubscription<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final a<? super T> actual;

        ArrayConditionalSubscription(a<? super T> aVar, T[] tArr) {
            super(tArr);
            this.actual = aVar;
        }

        /* access modifiers changed from: 0000 */
        public void fastPath() {
            Object[] objArr = this.array;
            int length = objArr.length;
            a<? super T> aVar = this.actual;
            int i = this.index;
            while (i != length) {
                if (!this.cancelled) {
                    Object obj = objArr[i];
                    if (obj == null) {
                        aVar.onError(new NullPointerException("array element is null"));
                        return;
                    } else {
                        aVar.tryOnNext(obj);
                        i++;
                    }
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                aVar.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void slowPath(long j) {
            Object[] objArr = this.array;
            int length = objArr.length;
            int i = this.index;
            a<? super T> aVar = this.actual;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2 || i == length) {
                        if (i == length) {
                            if (!this.cancelled) {
                                aVar.onComplete();
                            }
                            return;
                        }
                        j2 = get();
                        if (j3 == j2) {
                            this.index = i;
                            j2 = addAndGet(-j3);
                        }
                    } else if (!this.cancelled) {
                        Object obj = objArr[i];
                        if (obj == null) {
                            aVar.onError(new NullPointerException("array element is null"));
                            return;
                        }
                        if (aVar.tryOnNext(obj)) {
                            j3++;
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }

    static final class ArraySubscription<T> extends BaseArraySubscription<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final c<? super T> actual;

        ArraySubscription(c<? super T> cVar, T[] tArr) {
            super(tArr);
            this.actual = cVar;
        }

        /* access modifiers changed from: 0000 */
        public void fastPath() {
            Object[] objArr = this.array;
            int length = objArr.length;
            c<? super T> cVar = this.actual;
            int i = this.index;
            while (i != length) {
                if (!this.cancelled) {
                    Object obj = objArr[i];
                    if (obj == null) {
                        cVar.onError(new NullPointerException("array element is null"));
                        return;
                    } else {
                        cVar.onNext(obj);
                        i++;
                    }
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                cVar.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void slowPath(long j) {
            Object[] objArr = this.array;
            int length = objArr.length;
            int i = this.index;
            c<? super T> cVar = this.actual;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2 || i == length) {
                        if (i == length) {
                            if (!this.cancelled) {
                                cVar.onComplete();
                            }
                            return;
                        }
                        j2 = get();
                        if (j3 == j2) {
                            this.index = i;
                            j2 = addAndGet(-j3);
                        }
                    } else if (!this.cancelled) {
                        Object obj = objArr[i];
                        if (obj == null) {
                            cVar.onError(new NullPointerException("array element is null"));
                            return;
                        }
                        cVar.onNext(obj);
                        i++;
                        j3++;
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }

    static abstract class BaseArraySubscription<T> extends BasicQueueSubscription<T> {
        private static final long serialVersionUID = -2252972430506210021L;
        final T[] array;
        volatile boolean cancelled;
        int index;

        /* access modifiers changed from: 0000 */
        public abstract void fastPath();

        public final int requestFusion(int i) {
            return i & 1;
        }

        /* access modifiers changed from: 0000 */
        public abstract void slowPath(long j);

        BaseArraySubscription(T[] tArr) {
            this.array = tArr;
        }

        public final T poll() {
            int i = this.index;
            T[] tArr = this.array;
            if (i == tArr.length) {
                return null;
            }
            this.index = i + 1;
            return io.reactivex.internal.functions.a.a(tArr[i], "array element is null");
        }

        public final boolean isEmpty() {
            return this.index == this.array.length;
        }

        public final void clear() {
            this.index = this.array.length;
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

    public void a(c<? super T> cVar) {
        if (cVar instanceof a) {
            cVar.onSubscribe(new ArrayConditionalSubscription((a) cVar, this.b));
        } else {
            cVar.onSubscribe(new ArraySubscription(cVar, this.b));
        }
    }
}
