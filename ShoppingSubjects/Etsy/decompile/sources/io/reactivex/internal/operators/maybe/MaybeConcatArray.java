package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.g;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.b;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class MaybeConcatArray<T> extends g<T> {
    final o<? extends T>[] b;

    static final class ConcatMaybeObserver<T> extends AtomicInteger implements m<T>, Subscription {
        private static final long serialVersionUID = 3520831347801429610L;
        final c<? super T> actual;
        final AtomicReference<Object> current = new AtomicReference<>(NotificationLite.COMPLETE);
        final SequentialDisposable disposables = new SequentialDisposable();
        int index;
        long produced;
        final AtomicLong requested = new AtomicLong();
        final o<? extends T>[] sources;

        ConcatMaybeObserver(c<? super T> cVar, o<? extends T>[] oVarArr) {
            this.actual = cVar;
            this.sources = oVarArr;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.disposables.dispose();
        }

        public void onSubscribe(Disposable disposable) {
            this.disposables.replace(disposable);
        }

        public void onSuccess(T t) {
            this.current.lazySet(t);
            drain();
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.current.lazySet(NotificationLite.COMPLETE);
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                AtomicReference<Object> atomicReference = this.current;
                c<? super T> cVar = this.actual;
                SequentialDisposable sequentialDisposable = this.disposables;
                while (!sequentialDisposable.isDisposed()) {
                    Object obj = atomicReference.get();
                    if (obj != null) {
                        boolean z = true;
                        if (obj != NotificationLite.COMPLETE) {
                            long j = this.produced;
                            if (j != this.requested.get()) {
                                this.produced = j + 1;
                                atomicReference.lazySet(null);
                                cVar.onNext(obj);
                            } else {
                                z = false;
                            }
                        } else {
                            atomicReference.lazySet(null);
                        }
                        if (z && !sequentialDisposable.isDisposed()) {
                            int i = this.index;
                            if (i == this.sources.length) {
                                cVar.onComplete();
                                return;
                            } else {
                                this.index = i + 1;
                                this.sources[i].a(this);
                            }
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                atomicReference.lazySet(null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        ConcatMaybeObserver concatMaybeObserver = new ConcatMaybeObserver(cVar, this.b);
        cVar.onSubscribe(concatMaybeObserver);
        concatMaybeObserver.drain();
    }
}
