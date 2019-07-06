package io.reactivex;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.schedulers.f;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.TimeUnit;

/* compiled from: Scheduler */
public abstract class u {
    static final long a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    /* compiled from: Scheduler */
    static final class a implements Disposable, Runnable {
        final Runnable a;
        final c b;
        Thread c;

        a(Runnable runnable, c cVar) {
            this.a = runnable;
            this.b = cVar;
        }

        public void run() {
            this.c = Thread.currentThread();
            try {
                this.a.run();
            } finally {
                dispose();
                this.c = null;
            }
        }

        public void dispose() {
            if (this.c != Thread.currentThread() || !(this.b instanceof f)) {
                this.b.dispose();
            } else {
                ((f) this.b).b();
            }
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }
    }

    /* compiled from: Scheduler */
    static class b implements Disposable, Runnable {
        final Runnable a;
        final c b;
        volatile boolean c;

        b(Runnable runnable, c cVar) {
            this.a = runnable;
            this.b = cVar;
        }

        public void run() {
            if (!this.c) {
                try {
                    this.a.run();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.b.dispose();
                    throw ExceptionHelper.a(th);
                }
            }
        }

        public void dispose() {
            this.c = true;
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.c;
        }
    }

    /* compiled from: Scheduler */
    public static abstract class c implements Disposable {

        /* compiled from: Scheduler */
        final class a implements Runnable {
            final Runnable a;
            final SequentialDisposable b;
            final long c;
            long d;
            long e;
            long f;

            a(long j, Runnable runnable, long j2, SequentialDisposable sequentialDisposable, long j3) {
                this.a = runnable;
                this.b = sequentialDisposable;
                this.c = j3;
                this.e = j2;
                this.f = j;
            }

            public void run() {
                long j;
                this.a.run();
                if (!this.b.isDisposed()) {
                    long a2 = c.this.a(TimeUnit.NANOSECONDS);
                    if (a2 + u.a < this.e || a2 >= this.e + this.c + u.a) {
                        long j2 = a2 + this.c;
                        long j3 = this.c;
                        long j4 = this.d + 1;
                        this.d = j4;
                        this.f = j2 - (j3 * j4);
                        j = j2;
                    } else {
                        long j5 = this.f;
                        long j6 = this.d + 1;
                        this.d = j6;
                        j = j5 + (j6 * this.c);
                    }
                    this.e = a2;
                    this.b.replace(c.this.a(this, j - a2, TimeUnit.NANOSECONDS));
                }
            }
        }

        public abstract Disposable a(Runnable runnable, long j, TimeUnit timeUnit);

        public Disposable a(Runnable runnable) {
            return a(runnable, 0, TimeUnit.NANOSECONDS);
        }

        public Disposable a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            long j3 = j;
            TimeUnit timeUnit2 = timeUnit;
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            Runnable a2 = io.reactivex.d.a.a(runnable);
            long nanos = timeUnit2.toNanos(j2);
            long a3 = a(TimeUnit.NANOSECONDS);
            SequentialDisposable sequentialDisposable3 = sequentialDisposable;
            a aVar = r0;
            a aVar2 = new a(a3 + timeUnit2.toNanos(j3), a2, a3, sequentialDisposable2, nanos);
            Disposable a4 = a(aVar, j3, timeUnit2);
            if (a4 == EmptyDisposable.INSTANCE) {
                return a4;
            }
            sequentialDisposable3.replace(a4);
            return sequentialDisposable2;
        }

        public long a(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public abstract c a();

    public void b() {
    }

    public long a(TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public Disposable a(Runnable runnable) {
        return a(runnable, 0, TimeUnit.NANOSECONDS);
    }

    public Disposable a(Runnable runnable, long j, TimeUnit timeUnit) {
        c a2 = a();
        a aVar = new a(io.reactivex.d.a.a(runnable), a2);
        a2.a(aVar, j, timeUnit);
        return aVar;
    }

    public Disposable a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        c a2 = a();
        b bVar = new b(io.reactivex.d.a.a(runnable), a2);
        Disposable a3 = a2.a(bVar, j, j2, timeUnit);
        return a3 == EmptyDisposable.INSTANCE ? a3 : bVar;
    }
}
