package io.reactivex.internal.operators.flowable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.f;
import io.reactivex.g;
import io.reactivex.h;
import io.reactivex.i;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableCreate<T> extends g<T> {
    final i<T> b;
    final BackpressureStrategy c;

    static abstract class BaseEmitter<T> extends AtomicLong implements h<T>, Subscription {
        private static final long serialVersionUID = 7326289992464377023L;
        final c<? super T> actual;
        final SequentialDisposable serial = new SequentialDisposable();

        /* access modifiers changed from: 0000 */
        public void onRequested() {
        }

        /* access modifiers changed from: 0000 */
        public void onUnsubscribed() {
        }

        BaseEmitter(c<? super T> cVar) {
            this.actual = cVar;
        }

        public void onComplete() {
            complete();
        }

        /* access modifiers changed from: protected */
        public void complete() {
            if (!isCancelled()) {
                try {
                    this.actual.onComplete();
                } finally {
                    this.serial.dispose();
                }
            }
        }

        public final void onError(Throwable th) {
            if (!tryOnError(th)) {
                a.a(th);
            }
        }

        public boolean tryOnError(Throwable th) {
            return error(th);
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: protected */
        public boolean error(Throwable th) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (isCancelled()) {
                return false;
            }
            try {
                this.actual.onError(th);
                this.serial.dispose();
                return true;
            } catch (Throwable th2) {
                this.serial.dispose();
                throw th2;
            }
        }

        public final void cancel() {
            this.serial.dispose();
            onUnsubscribed();
        }

        public final boolean isCancelled() {
            return this.serial.isDisposed();
        }

        public final void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a((AtomicLong) this, j);
                onRequested();
            }
        }

        public final void setDisposable(Disposable disposable) {
            this.serial.update(disposable);
        }

        public final void setCancellable(f fVar) {
            setDisposable(new CancellableDisposable(fVar));
        }

        public final long requested() {
            return get();
        }

        public final h<T> serialize() {
            return new SerializedEmitter(this);
        }
    }

    static final class BufferAsyncEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 2427151001689639875L;
        volatile boolean done;
        Throwable error;
        final io.reactivex.internal.queue.a<T> queue;
        final AtomicInteger wip = new AtomicInteger();

        BufferAsyncEmitter(c<? super T> cVar, int i) {
            super(cVar);
            this.queue = new io.reactivex.internal.queue.a<>(i);
        }

        public void onNext(T t) {
            if (!this.done && !isCancelled()) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                this.queue.offer(t);
                drain();
            }
        }

        public boolean tryOnError(Throwable th) {
            if (this.done || isCancelled()) {
                return false;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.error = th;
            this.done = true;
            drain();
            return true;
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void onRequested() {
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (this.wip.getAndIncrement() == 0) {
                c cVar = this.actual;
                io.reactivex.internal.queue.a<T> aVar = this.queue;
                int i = 1;
                do {
                    long j = get();
                    long j2 = 0;
                    while (j2 != j) {
                        if (isCancelled()) {
                            aVar.clear();
                            return;
                        }
                        boolean z = this.done;
                        Object poll = aVar.poll();
                        boolean z2 = poll == null;
                        if (z && z2) {
                            Throwable th = this.error;
                            if (th != null) {
                                error(th);
                            } else {
                                complete();
                            }
                            return;
                        } else if (z2) {
                            break;
                        } else {
                            cVar.onNext(poll);
                            j2++;
                        }
                    }
                    if (j2 == j) {
                        if (isCancelled()) {
                            aVar.clear();
                            return;
                        }
                        boolean z3 = this.done;
                        boolean isEmpty = aVar.isEmpty();
                        if (z3 && isEmpty) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                error(th2);
                            } else {
                                complete();
                            }
                            return;
                        }
                    }
                    if (j2 != 0) {
                        b.c(this, j2);
                    }
                    i = this.wip.addAndGet(-i);
                } while (i != 0);
            }
        }
    }

    static final class DropAsyncEmitter<T> extends NoOverflowBaseAsyncEmitter<T> {
        private static final long serialVersionUID = 8360058422307496563L;

        /* access modifiers changed from: 0000 */
        public void onOverflow() {
        }

        DropAsyncEmitter(c<? super T> cVar) {
            super(cVar);
        }
    }

    static final class ErrorAsyncEmitter<T> extends NoOverflowBaseAsyncEmitter<T> {
        private static final long serialVersionUID = 338953216916120960L;

        ErrorAsyncEmitter(c<? super T> cVar) {
            super(cVar);
        }

        /* access modifiers changed from: 0000 */
        public void onOverflow() {
            onError(new MissingBackpressureException("create: could not emit value due to lack of requests"));
        }
    }

    static final class LatestAsyncEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4023437720691792495L;
        volatile boolean done;
        Throwable error;
        final AtomicReference<T> queue = new AtomicReference<>();
        final AtomicInteger wip = new AtomicInteger();

        LatestAsyncEmitter(c<? super T> cVar) {
            super(cVar);
        }

        public void onNext(T t) {
            if (!this.done && !isCancelled()) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                this.queue.set(t);
                drain();
            }
        }

        public boolean tryOnError(Throwable th) {
            if (this.done || isCancelled()) {
                return false;
            }
            if (th == null) {
                onError(new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources."));
            }
            this.error = th;
            this.done = true;
            drain();
            return true;
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void onRequested() {
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.lazySet(null);
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            boolean z;
            if (this.wip.getAndIncrement() == 0) {
                c cVar = this.actual;
                AtomicReference<T> atomicReference = this.queue;
                int i = 1;
                do {
                    long j = get();
                    long j2 = 0;
                    while (true) {
                        z = false;
                        if (j2 == j) {
                            break;
                        } else if (isCancelled()) {
                            atomicReference.lazySet(null);
                            return;
                        } else {
                            boolean z2 = this.done;
                            Object andSet = atomicReference.getAndSet(null);
                            boolean z3 = andSet == null;
                            if (z2 && z3) {
                                Throwable th = this.error;
                                if (th != null) {
                                    error(th);
                                } else {
                                    complete();
                                }
                                return;
                            } else if (z3) {
                                break;
                            } else {
                                cVar.onNext(andSet);
                                j2++;
                            }
                        }
                    }
                    if (j2 == j) {
                        if (isCancelled()) {
                            atomicReference.lazySet(null);
                            return;
                        }
                        boolean z4 = this.done;
                        if (atomicReference.get() == null) {
                            z = true;
                        }
                        if (z4 && z) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                error(th2);
                            } else {
                                complete();
                            }
                            return;
                        }
                    }
                    if (j2 != 0) {
                        b.c(this, j2);
                    }
                    i = this.wip.addAndGet(-i);
                } while (i != 0);
            }
        }
    }

    static final class MissingEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 3776720187248809713L;

        MissingEmitter(c<? super T> cVar) {
            super(cVar);
        }

        public void onNext(T t) {
            long j;
            if (!isCancelled()) {
                if (t != null) {
                    this.actual.onNext(t);
                    do {
                        j = get();
                        if (j == 0) {
                            break;
                        }
                    } while (!compareAndSet(j, j - 1));
                    return;
                }
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            }
        }
    }

    static abstract class NoOverflowBaseAsyncEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4127754106204442833L;

        /* access modifiers changed from: 0000 */
        public abstract void onOverflow();

        NoOverflowBaseAsyncEmitter(c<? super T> cVar) {
            super(cVar);
        }

        public final void onNext(T t) {
            if (!isCancelled()) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                if (get() != 0) {
                    this.actual.onNext(t);
                    b.c(this, 1);
                } else {
                    onOverflow();
                }
            }
        }
    }

    static final class SerializedEmitter<T> extends AtomicInteger implements h<T> {
        private static final long serialVersionUID = 4883307006032401862L;
        volatile boolean done;
        final BaseEmitter<T> emitter;
        final AtomicThrowable error = new AtomicThrowable();
        final io.reactivex.internal.a.f<T> queue = new io.reactivex.internal.queue.a(16);

        public h<T> serialize() {
            return this;
        }

        SerializedEmitter(BaseEmitter<T> baseEmitter) {
            this.emitter = baseEmitter;
        }

        public void onNext(T t) {
            if (!this.emitter.isCancelled() && !this.done) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                if (get() != 0 || !compareAndSet(0, 1)) {
                    io.reactivex.internal.a.f<T> fVar = this.queue;
                    synchronized (fVar) {
                        fVar.offer(t);
                    }
                    if (getAndIncrement() != 0) {
                        return;
                    }
                } else {
                    this.emitter.onNext(t);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                drainLoop();
            }
        }

        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                a.a(th);
            }
        }

        public boolean tryOnError(Throwable th) {
            if (this.emitter.isCancelled() || this.done) {
                return false;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (!this.error.addThrowable(th)) {
                return false;
            }
            this.done = true;
            drain();
            return true;
        }

        public void onComplete() {
            if (!this.emitter.isCancelled() && !this.done) {
                this.done = true;
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainLoop() {
            BaseEmitter<T> baseEmitter = this.emitter;
            io.reactivex.internal.a.f<T> fVar = this.queue;
            AtomicThrowable atomicThrowable = this.error;
            int i = 1;
            while (!baseEmitter.isCancelled()) {
                if (atomicThrowable.get() != null) {
                    fVar.clear();
                    baseEmitter.onError(atomicThrowable.terminate());
                    return;
                }
                boolean z = this.done;
                Object poll = fVar.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    baseEmitter.onComplete();
                    return;
                } else if (z2) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    baseEmitter.onNext(poll);
                }
            }
            fVar.clear();
        }

        public void setDisposable(Disposable disposable) {
            this.emitter.setDisposable(disposable);
        }

        public void setCancellable(f fVar) {
            this.emitter.setCancellable(fVar);
        }

        public long requested() {
            return this.emitter.requested();
        }

        public boolean isCancelled() {
            return this.emitter.isCancelled();
        }
    }

    public void a(c<? super T> cVar) {
        BaseEmitter baseEmitter;
        switch (this.c) {
            case MISSING:
                baseEmitter = new MissingEmitter(cVar);
                break;
            case ERROR:
                baseEmitter = new ErrorAsyncEmitter(cVar);
                break;
            case DROP:
                baseEmitter = new DropAsyncEmitter(cVar);
                break;
            case LATEST:
                baseEmitter = new LatestAsyncEmitter(cVar);
                break;
            default:
                baseEmitter = new BufferAsyncEmitter(cVar, a());
                break;
        }
        cVar.onSubscribe(baseEmitter);
        try {
            this.b.a(baseEmitter);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            baseEmitter.onError(th);
        }
    }
}
