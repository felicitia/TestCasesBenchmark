package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.g;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.b;
import io.reactivex.m;
import io.reactivex.o;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class MaybeConcatIterable<T> extends g<T> {
    final Iterable<? extends o<? extends T>> b;

    static final class ConcatMaybeObserver<T> extends AtomicInteger implements m<T>, Subscription {
        private static final long serialVersionUID = 3520831347801429610L;
        final c<? super T> actual;
        final AtomicReference<Object> current = new AtomicReference<>(NotificationLite.COMPLETE);
        final SequentialDisposable disposables = new SequentialDisposable();
        long produced;
        final AtomicLong requested = new AtomicLong();
        final Iterator<? extends o<? extends T>> sources;

        ConcatMaybeObserver(c<? super T> cVar, Iterator<? extends o<? extends T>> it) {
            this.actual = cVar;
            this.sources = it;
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
                            try {
                                if (this.sources.hasNext()) {
                                    try {
                                        ((o) a.a(this.sources.next(), "The source Iterator returned a null MaybeSource")).a(this);
                                    } catch (Throwable th) {
                                        io.reactivex.exceptions.a.b(th);
                                        cVar.onError(th);
                                        return;
                                    }
                                } else {
                                    cVar.onComplete();
                                }
                            } catch (Throwable th2) {
                                io.reactivex.exceptions.a.b(th2);
                                cVar.onError(th2);
                                return;
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
        try {
            ConcatMaybeObserver concatMaybeObserver = new ConcatMaybeObserver(cVar, (Iterator) a.a(this.b.iterator(), "The sources Iterable returned a null Iterator"));
            cVar.onSubscribe(concatMaybeObserver);
            concatMaybeObserver.drain();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
