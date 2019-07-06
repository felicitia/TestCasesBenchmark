package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.a.a;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.c;

public final class FlowableRange extends g<Integer> {
    final int b;
    final int c;

    static abstract class BaseRangeSubscription extends BasicQueueSubscription<Integer> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final int end;
        int index;

        /* access modifiers changed from: 0000 */
        public abstract void fastPath();

        public final int requestFusion(int i) {
            return i & 1;
        }

        /* access modifiers changed from: 0000 */
        public abstract void slowPath(long j);

        BaseRangeSubscription(int i, int i2) {
            this.index = i;
            this.end = i2;
        }

        public final Integer poll() {
            int i = this.index;
            if (i == this.end) {
                return null;
            }
            this.index = i + 1;
            return Integer.valueOf(i);
        }

        public final boolean isEmpty() {
            return this.index == this.end;
        }

        public final void clear() {
            this.index = this.end;
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

    static final class RangeConditionalSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final a<? super Integer> actual;

        RangeConditionalSubscription(a<? super Integer> aVar, int i, int i2) {
            super(i, i2);
            this.actual = aVar;
        }

        /* access modifiers changed from: 0000 */
        public void fastPath() {
            int i = this.end;
            a<? super Integer> aVar = this.actual;
            int i2 = this.index;
            while (i2 != i) {
                if (!this.cancelled) {
                    aVar.tryOnNext(Integer.valueOf(i2));
                    i2++;
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
            int i = this.end;
            int i2 = this.index;
            a<? super Integer> aVar = this.actual;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2 || i2 == i) {
                        if (i2 == i) {
                            if (!this.cancelled) {
                                aVar.onComplete();
                            }
                            return;
                        }
                        j2 = get();
                        if (j3 == j2) {
                            this.index = i2;
                            j2 = addAndGet(-j3);
                        }
                    } else if (!this.cancelled) {
                        if (aVar.tryOnNext(Integer.valueOf(i2))) {
                            j3++;
                        }
                        i2++;
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }

    static final class RangeSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final c<? super Integer> actual;

        RangeSubscription(c<? super Integer> cVar, int i, int i2) {
            super(i, i2);
            this.actual = cVar;
        }

        /* access modifiers changed from: 0000 */
        public void fastPath() {
            int i = this.end;
            c<? super Integer> cVar = this.actual;
            int i2 = this.index;
            while (i2 != i) {
                if (!this.cancelled) {
                    cVar.onNext(Integer.valueOf(i2));
                    i2++;
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
            int i = this.end;
            int i2 = this.index;
            c<? super Integer> cVar = this.actual;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2 || i2 == i) {
                        if (i2 == i) {
                            if (!this.cancelled) {
                                cVar.onComplete();
                            }
                            return;
                        }
                        j2 = get();
                        if (j3 == j2) {
                            this.index = i2;
                            j2 = addAndGet(-j3);
                        }
                    } else if (!this.cancelled) {
                        cVar.onNext(Integer.valueOf(i2));
                        i2++;
                        j3++;
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }

    public void a(c<? super Integer> cVar) {
        if (cVar instanceof a) {
            cVar.onSubscribe(new RangeConditionalSubscription((a) cVar, this.b, this.c));
        } else {
            cVar.onSubscribe(new RangeSubscription(cVar, this.b, this.c));
        }
    }
}
