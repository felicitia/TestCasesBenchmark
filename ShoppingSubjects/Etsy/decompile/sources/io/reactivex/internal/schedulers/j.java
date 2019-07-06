package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.u;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: TrampolineScheduler */
public final class j extends u {
    private static final j b = new j();

    /* compiled from: TrampolineScheduler */
    static final class a implements Runnable {
        private final Runnable a;
        private final c b;
        private final long c;

        a(Runnable runnable, c cVar, long j) {
            this.a = runnable;
            this.b = cVar;
            this.c = j;
        }

        public void run() {
            if (!this.b.c) {
                long a2 = this.b.a(TimeUnit.MILLISECONDS);
                if (this.c > a2) {
                    long j = this.c - a2;
                    if (j > 0) {
                        try {
                            Thread.sleep(j);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            io.reactivex.d.a.a((Throwable) e);
                            return;
                        }
                    }
                }
                if (!this.b.c) {
                    this.a.run();
                }
            }
        }
    }

    /* compiled from: TrampolineScheduler */
    static final class b implements Comparable<b> {
        final Runnable a;
        final long b;
        final int c;
        volatile boolean d;

        b(Runnable runnable, Long l, int i) {
            this.a = runnable;
            this.b = l.longValue();
            this.c = i;
        }

        /* renamed from: a */
        public int compareTo(b bVar) {
            int a2 = io.reactivex.internal.functions.a.a(this.b, bVar.b);
            return a2 == 0 ? io.reactivex.internal.functions.a.a(this.c, bVar.c) : a2;
        }
    }

    /* compiled from: TrampolineScheduler */
    static final class c extends io.reactivex.u.c implements Disposable {
        final PriorityBlockingQueue<b> a = new PriorityBlockingQueue<>();
        final AtomicInteger b = new AtomicInteger();
        volatile boolean c;
        private final AtomicInteger d = new AtomicInteger();

        /* compiled from: TrampolineScheduler */
        final class a implements Runnable {
            final b a;

            a(b bVar) {
                this.a = bVar;
            }

            public void run() {
                this.a.d = true;
                c.this.a.remove(this.a);
            }
        }

        c() {
        }

        public Disposable a(Runnable runnable) {
            return a(runnable, a(TimeUnit.MILLISECONDS));
        }

        public Disposable a(Runnable runnable, long j, TimeUnit timeUnit) {
            long a2 = a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return a(new a(runnable, this, a2), a2);
        }

        /* access modifiers changed from: 0000 */
        public Disposable a(Runnable runnable, long j) {
            if (this.c) {
                return EmptyDisposable.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.b.incrementAndGet());
            this.a.add(bVar);
            if (this.d.getAndIncrement() != 0) {
                return io.reactivex.disposables.b.a(new a(bVar));
            }
            int i = 1;
            while (!this.c) {
                b bVar2 = (b) this.a.poll();
                if (bVar2 == null) {
                    i = this.d.addAndGet(-i);
                    if (i == 0) {
                        return EmptyDisposable.INSTANCE;
                    }
                } else if (!bVar2.d) {
                    bVar2.a.run();
                }
            }
            this.a.clear();
            return EmptyDisposable.INSTANCE;
        }

        public void dispose() {
            this.c = true;
        }

        public boolean isDisposed() {
            return this.c;
        }
    }

    public static j c() {
        return b;
    }

    public io.reactivex.u.c a() {
        return new c();
    }

    j() {
    }

    public Disposable a(Runnable runnable) {
        runnable.run();
        return EmptyDisposable.INSTANCE;
    }

    public Disposable a(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            runnable.run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            io.reactivex.d.a.a((Throwable) e);
        }
        return EmptyDisposable.INSTANCE;
    }
}
