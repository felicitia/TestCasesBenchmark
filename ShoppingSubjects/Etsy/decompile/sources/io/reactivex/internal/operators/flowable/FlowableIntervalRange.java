package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.schedulers.j;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableIntervalRange extends g<Long> {
    final u b;
    final long c;
    final long d;
    final long e;
    final long f;
    final TimeUnit g;

    static final class IntervalRangeSubscriber extends AtomicLong implements Runnable, Subscription {
        private static final long serialVersionUID = -2809475196591179431L;
        final c<? super Long> actual;
        long count;
        final long end;
        final AtomicReference<Disposable> resource = new AtomicReference<>();

        IntervalRangeSubscriber(c<? super Long> cVar, long j, long j2) {
            this.actual = cVar;
            this.count = j;
            this.end = j2;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a((AtomicLong) this, j);
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this.resource);
        }

        public void run() {
            if (this.resource.get() != DisposableHelper.DISPOSED) {
                long j = get();
                if (j != 0) {
                    long j2 = this.count;
                    this.actual.onNext(Long.valueOf(j2));
                    if (j2 == this.end) {
                        if (this.resource.get() != DisposableHelper.DISPOSED) {
                            this.actual.onComplete();
                        }
                        DisposableHelper.dispose(this.resource);
                        return;
                    }
                    this.count = j2 + 1;
                    if (j != Long.MAX_VALUE) {
                        decrementAndGet();
                    }
                } else {
                    c<? super Long> cVar = this.actual;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Can't deliver value ");
                    sb.append(this.count);
                    sb.append(" due to lack of requests");
                    cVar.onError(new MissingBackpressureException(sb.toString()));
                    DisposableHelper.dispose(this.resource);
                }
            }
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.setOnce(this.resource, disposable);
        }
    }

    public void a(c<? super Long> cVar) {
        IntervalRangeSubscriber intervalRangeSubscriber = new IntervalRangeSubscriber(cVar, this.c, this.d);
        cVar.onSubscribe(intervalRangeSubscriber);
        u uVar = this.b;
        if (uVar instanceof j) {
            u.c a = uVar.a();
            intervalRangeSubscriber.setResource(a);
            a.a(intervalRangeSubscriber, this.e, this.f, this.g);
            return;
        }
        intervalRangeSubscriber.setResource(uVar.a(intervalRangeSubscriber, this.e, this.f, this.g));
    }
}
