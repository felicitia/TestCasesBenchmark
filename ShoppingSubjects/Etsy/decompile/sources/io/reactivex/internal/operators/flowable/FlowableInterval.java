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

public final class FlowableInterval extends g<Long> {
    final u b;
    final long c;
    final long d;
    final TimeUnit e;

    static final class IntervalSubscriber extends AtomicLong implements Runnable, Subscription {
        private static final long serialVersionUID = -2809475196591179431L;
        final c<? super Long> actual;
        long count;
        final AtomicReference<Disposable> resource = new AtomicReference<>();

        IntervalSubscriber(c<? super Long> cVar) {
            this.actual = cVar;
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
            if (this.resource.get() == DisposableHelper.DISPOSED) {
                return;
            }
            if (get() != 0) {
                c<? super Long> cVar = this.actual;
                long j = this.count;
                this.count = j + 1;
                cVar.onNext(Long.valueOf(j));
                b.c(this, 1);
                return;
            }
            c<? super Long> cVar2 = this.actual;
            StringBuilder sb = new StringBuilder();
            sb.append("Can't deliver value ");
            sb.append(this.count);
            sb.append(" due to lack of requests");
            cVar2.onError(new MissingBackpressureException(sb.toString()));
            DisposableHelper.dispose(this.resource);
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.setOnce(this.resource, disposable);
        }
    }

    public void a(c<? super Long> cVar) {
        IntervalSubscriber intervalSubscriber = new IntervalSubscriber(cVar);
        cVar.onSubscribe(intervalSubscriber);
        u uVar = this.b;
        if (uVar instanceof j) {
            u.c a = uVar.a();
            intervalSubscriber.setResource(a);
            a.a(intervalSubscriber, this.c, this.d, this.e);
            return;
        }
        intervalSubscriber.setResource(uVar.a(intervalSubscriber, this.c, this.d, this.e));
    }
}
