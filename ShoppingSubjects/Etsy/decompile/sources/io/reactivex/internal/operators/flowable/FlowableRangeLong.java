package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.a.a;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.c;

public final class FlowableRangeLong extends g<Long> {
    final long b;
    final long c;

    static abstract class BaseRangeSubscription extends BasicQueueSubscription<Long> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final long end;
        long index;

        /* access modifiers changed from: 0000 */
        public abstract void fastPath();

        public final int requestFusion(int i) {
            return i & 1;
        }

        /* access modifiers changed from: 0000 */
        public abstract void slowPath(long j);

        BaseRangeSubscription(long j, long j2) {
            this.index = j;
            this.end = j2;
        }

        public final Long poll() {
            long j = this.index;
            if (j == this.end) {
                return null;
            }
            this.index = j + 1;
            return Long.valueOf(j);
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
        final a<? super Long> actual;

        RangeConditionalSubscription(a<? super Long> aVar, long j, long j2) {
            super(j, j2);
            this.actual = aVar;
        }

        /* access modifiers changed from: 0000 */
        public void fastPath() {
            long j = this.end;
            a<? super Long> aVar = this.actual;
            long j2 = this.index;
            while (j2 != j) {
                if (!this.cancelled) {
                    aVar.tryOnNext(Long.valueOf(j2));
                    j2++;
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
            long j2 = this.end;
            long j3 = this.index;
            a<? super Long> aVar = this.actual;
            long j4 = j;
            long j5 = j3;
            do {
                long j6 = 0;
                while (true) {
                    if (j6 == j4 || j5 == j2) {
                        if (j5 == j2) {
                            if (!this.cancelled) {
                                aVar.onComplete();
                            }
                            return;
                        }
                        j4 = get();
                        if (j6 == j4) {
                            this.index = j5;
                            j4 = addAndGet(-j6);
                        }
                    } else if (!this.cancelled) {
                        if (aVar.tryOnNext(Long.valueOf(j5))) {
                            j6++;
                        }
                        j5++;
                    } else {
                        return;
                    }
                }
            } while (j4 != 0);
        }
    }

    static final class RangeSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final c<? super Long> actual;

        RangeSubscription(c<? super Long> cVar, long j, long j2) {
            super(j, j2);
            this.actual = cVar;
        }

        /* access modifiers changed from: 0000 */
        public void fastPath() {
            long j = this.end;
            c<? super Long> cVar = this.actual;
            long j2 = this.index;
            while (j2 != j) {
                if (!this.cancelled) {
                    cVar.onNext(Long.valueOf(j2));
                    j2++;
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
            long j2 = this.end;
            long j3 = this.index;
            c<? super Long> cVar = this.actual;
            long j4 = j3;
            long j5 = j;
            do {
                long j6 = 0;
                while (true) {
                    if (j6 == j5 || j4 == j2) {
                        if (j4 == j2) {
                            if (!this.cancelled) {
                                cVar.onComplete();
                            }
                            return;
                        }
                        j5 = get();
                        if (j6 == j5) {
                            this.index = j4;
                            j5 = addAndGet(-j6);
                        }
                    } else if (!this.cancelled) {
                        cVar.onNext(Long.valueOf(j4));
                        j4++;
                        j6++;
                    } else {
                        return;
                    }
                }
            } while (j5 != 0);
        }
    }

    public void a(c<? super Long> cVar) {
        if (cVar instanceof a) {
            RangeConditionalSubscription rangeConditionalSubscription = new RangeConditionalSubscription((a) cVar, this.b, this.c);
            cVar.onSubscribe(rangeConditionalSubscription);
            return;
        }
        RangeSubscription rangeSubscription = new RangeSubscription(cVar, this.b, this.c);
        cVar.onSubscribe(rangeSubscription);
    }
}
