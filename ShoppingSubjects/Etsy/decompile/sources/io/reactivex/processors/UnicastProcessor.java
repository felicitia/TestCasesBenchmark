package io.reactivex.processors;

import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class UnicastProcessor<T> extends a<T> {
    final a<T> b;
    final AtomicReference<Runnable> c;
    final boolean d;
    volatile boolean e;
    Throwable f;
    final AtomicReference<c<? super T>> g;
    volatile boolean h;
    final AtomicBoolean i;
    final BasicIntQueueSubscription<T> j;
    final AtomicLong k;
    boolean l;

    final class UnicastQueueSubscription extends BasicIntQueueSubscription<T> {
        private static final long serialVersionUID = -4896760517184205454L;

        UnicastQueueSubscription() {
        }

        public T poll() {
            return UnicastProcessor.this.b.poll();
        }

        public boolean isEmpty() {
            return UnicastProcessor.this.b.isEmpty();
        }

        public void clear() {
            UnicastProcessor.this.b.clear();
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            UnicastProcessor.this.l = true;
            return 2;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(UnicastProcessor.this.k, j);
                UnicastProcessor.this.i();
            }
        }

        public void cancel() {
            if (!UnicastProcessor.this.h) {
                UnicastProcessor.this.h = true;
                UnicastProcessor.this.h();
                if (!UnicastProcessor.this.l && UnicastProcessor.this.j.getAndIncrement() == 0) {
                    UnicastProcessor.this.b.clear();
                    UnicastProcessor.this.g.lazySet(null);
                }
            }
        }
    }

    public static <T> UnicastProcessor<T> f() {
        return new UnicastProcessor<>(a());
    }

    public static <T> UnicastProcessor<T> a(int i2) {
        return new UnicastProcessor<>(i2);
    }

    public static <T> UnicastProcessor<T> a(int i2, Runnable runnable) {
        io.reactivex.internal.functions.a.a(runnable, "onTerminate");
        return new UnicastProcessor<>(i2, runnable);
    }

    UnicastProcessor(int i2) {
        this(i2, null, true);
    }

    UnicastProcessor(int i2, Runnable runnable) {
        this(i2, runnable, true);
    }

    UnicastProcessor(int i2, Runnable runnable, boolean z) {
        this.b = new a<>(io.reactivex.internal.functions.a.a(i2, "capacityHint"));
        this.c = new AtomicReference<>(runnable);
        this.d = z;
        this.g = new AtomicReference<>();
        this.i = new AtomicBoolean();
        this.j = new UnicastQueueSubscription();
        this.k = new AtomicLong();
    }

    /* access modifiers changed from: 0000 */
    public void h() {
        Runnable runnable = (Runnable) this.c.get();
        if (runnable != null && this.c.compareAndSet(runnable, null)) {
            runnable.run();
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(c<? super T> cVar) {
        long j2;
        a<T> aVar = this.b;
        boolean z = !this.d;
        int i2 = 1;
        do {
            long j3 = this.k.get();
            long j4 = 0;
            while (true) {
                if (j3 == j4) {
                    j2 = j4;
                    break;
                }
                boolean z2 = this.e;
                Object poll = aVar.poll();
                boolean z3 = poll == null;
                j2 = j4;
                if (!a(z, z2, z3, cVar, aVar)) {
                    if (z3) {
                        break;
                    }
                    cVar.onNext(poll);
                    j4 = j2 + 1;
                } else {
                    return;
                }
            }
            c<? super T> cVar2 = cVar;
            if (j3 == j2) {
                if (a(z, this.e, aVar.isEmpty(), cVar2, aVar)) {
                    return;
                }
            }
            if (!(j2 == 0 || j3 == Long.MAX_VALUE)) {
                this.k.addAndGet(-j2);
            }
            i2 = this.j.addAndGet(-i2);
        } while (i2 != 0);
    }

    /* access modifiers changed from: 0000 */
    public void c(c<? super T> cVar) {
        a<T> aVar = this.b;
        int i2 = 1;
        boolean z = !this.d;
        while (!this.h) {
            boolean z2 = this.e;
            if (!z || !z2 || this.f == null) {
                cVar.onNext(null);
                if (z2) {
                    this.g.lazySet(null);
                    Throwable th = this.f;
                    if (th != null) {
                        cVar.onError(th);
                    } else {
                        cVar.onComplete();
                    }
                    return;
                }
                i2 = this.j.addAndGet(-i2);
                if (i2 == 0) {
                    return;
                }
            } else {
                aVar.clear();
                this.g.lazySet(null);
                cVar.onError(this.f);
                return;
            }
        }
        aVar.clear();
        this.g.lazySet(null);
    }

    /* access modifiers changed from: 0000 */
    public void i() {
        if (this.j.getAndIncrement() == 0) {
            int i2 = 1;
            c cVar = (c) this.g.get();
            while (cVar == null) {
                i2 = this.j.addAndGet(-i2);
                if (i2 != 0) {
                    cVar = (c) this.g.get();
                } else {
                    return;
                }
            }
            if (this.l) {
                c(cVar);
            } else {
                b(cVar);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(boolean z, boolean z2, boolean z3, c<? super T> cVar, a<T> aVar) {
        if (this.h) {
            aVar.clear();
            this.g.lazySet(null);
            return true;
        }
        if (z2) {
            if (z && this.f != null) {
                aVar.clear();
                this.g.lazySet(null);
                cVar.onError(this.f);
                return true;
            } else if (z3) {
                Throwable th = this.f;
                this.g.lazySet(null);
                if (th != null) {
                    cVar.onError(th);
                } else {
                    cVar.onComplete();
                }
                return true;
            }
        }
        return false;
    }

    public void onSubscribe(Subscription subscription) {
        if (this.e || this.h) {
            subscription.cancel();
        } else {
            subscription.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        if (!this.e && !this.h) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            this.b.offer(t);
            i();
        }
    }

    public void onError(Throwable th) {
        if (this.e || this.h) {
            io.reactivex.d.a.a(th);
            return;
        }
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        this.f = th;
        this.e = true;
        h();
        i();
    }

    public void onComplete() {
        if (!this.e && !this.h) {
            this.e = true;
            h();
            i();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        if (this.i.get() || !this.i.compareAndSet(false, true)) {
            EmptySubscription.error(new IllegalStateException("This processor allows only a single Subscriber"), cVar);
            return;
        }
        cVar.onSubscribe(this.j);
        this.g.set(cVar);
        if (this.h) {
            this.g.lazySet(null);
        } else {
            i();
        }
    }
}
