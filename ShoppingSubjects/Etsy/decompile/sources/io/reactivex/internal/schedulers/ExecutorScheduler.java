package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.u;
import io.reactivex.u.c;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ExecutorScheduler extends u {
    static final u c = io.reactivex.e.a.c();
    final Executor b;

    static final class DelayedRunnable extends AtomicReference<Runnable> implements Disposable, Runnable {
        private static final long serialVersionUID = -4101336210206799084L;
        final SequentialDisposable direct = new SequentialDisposable();
        final SequentialDisposable timed = new SequentialDisposable();

        DelayedRunnable(Runnable runnable) {
            super(runnable);
        }

        public void run() {
            Runnable runnable = (Runnable) get();
            if (runnable != null) {
                try {
                    runnable.run();
                } finally {
                    lazySet(null);
                    this.timed.lazySet(DisposableHelper.DISPOSED);
                    this.direct.lazySet(DisposableHelper.DISPOSED);
                }
            }
        }

        public boolean isDisposed() {
            return get() == null;
        }

        public void dispose() {
            if (getAndSet(null) != null) {
                this.timed.dispose();
                this.direct.dispose();
            }
        }
    }

    public static final class ExecutorWorker extends c implements Runnable {
        final Executor a;
        final MpscLinkedQueue<Runnable> b;
        volatile boolean c;
        final AtomicInteger d = new AtomicInteger();
        final io.reactivex.disposables.a e = new io.reactivex.disposables.a();

        static final class BooleanRunnable extends AtomicBoolean implements Disposable, Runnable {
            private static final long serialVersionUID = -2421395018820541164L;
            final Runnable actual;

            BooleanRunnable(Runnable runnable) {
                this.actual = runnable;
            }

            public void run() {
                if (!get()) {
                    try {
                        this.actual.run();
                    } finally {
                        lazySet(true);
                    }
                }
            }

            public void dispose() {
                lazySet(true);
            }

            public boolean isDisposed() {
                return get();
            }
        }

        final class a implements Runnable {
            private final SequentialDisposable b;
            private final Runnable c;

            a(SequentialDisposable sequentialDisposable, Runnable runnable) {
                this.b = sequentialDisposable;
                this.c = runnable;
            }

            public void run() {
                this.b.replace(ExecutorWorker.this.a(this.c));
            }
        }

        public ExecutorWorker(Executor executor) {
            this.a = executor;
            this.b = new MpscLinkedQueue<>();
        }

        public Disposable a(Runnable runnable) {
            if (this.c) {
                return EmptyDisposable.INSTANCE;
            }
            BooleanRunnable booleanRunnable = new BooleanRunnable(io.reactivex.d.a.a(runnable));
            this.b.offer(booleanRunnable);
            if (this.d.getAndIncrement() == 0) {
                try {
                    this.a.execute(this);
                } catch (RejectedExecutionException e2) {
                    this.c = true;
                    this.b.clear();
                    io.reactivex.d.a.a((Throwable) e2);
                    return EmptyDisposable.INSTANCE;
                }
            }
            return booleanRunnable;
        }

        public Disposable a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (j <= 0) {
                return a(runnable);
            }
            if (this.c) {
                return EmptyDisposable.INSTANCE;
            }
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            ScheduledRunnable scheduledRunnable = new ScheduledRunnable(new a(sequentialDisposable2, io.reactivex.d.a.a(runnable)), this.e);
            this.e.a((Disposable) scheduledRunnable);
            if (this.a instanceof ScheduledExecutorService) {
                try {
                    scheduledRunnable.setFuture(((ScheduledExecutorService) this.a).schedule(scheduledRunnable, j, timeUnit));
                } catch (RejectedExecutionException e2) {
                    this.c = true;
                    io.reactivex.d.a.a((Throwable) e2);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                scheduledRunnable.setFuture(new b(ExecutorScheduler.c.a(scheduledRunnable, j, timeUnit)));
            }
            sequentialDisposable.replace(scheduledRunnable);
            return sequentialDisposable2;
        }

        public void dispose() {
            if (!this.c) {
                this.c = true;
                this.e.dispose();
                if (this.d.getAndIncrement() == 0) {
                    this.b.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.c;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
            r1 = r3.d.addAndGet(-r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
            if (r1 != 0) goto L_0x0003;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
            if (r3.c == false) goto L_0x001b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
            r0.clear();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
                io.reactivex.internal.queue.MpscLinkedQueue<java.lang.Runnable> r0 = r3.b
                r1 = 1
            L_0x0003:
                boolean r2 = r3.c
                if (r2 == 0) goto L_0x000b
                r0.clear()
                return
            L_0x000b:
                java.lang.Object r2 = r0.poll()
                java.lang.Runnable r2 = (java.lang.Runnable) r2
                if (r2 != 0) goto L_0x0025
                boolean r2 = r3.c
                if (r2 == 0) goto L_0x001b
                r0.clear()
                return
            L_0x001b:
                java.util.concurrent.atomic.AtomicInteger r2 = r3.d
                int r1 = -r1
                int r1 = r2.addAndGet(r1)
                if (r1 != 0) goto L_0x0003
                return
            L_0x0025:
                r2.run()
                boolean r2 = r3.c
                if (r2 == 0) goto L_0x000b
                r0.clear()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.schedulers.ExecutorScheduler.ExecutorWorker.run():void");
        }
    }

    final class a implements Runnable {
        private final DelayedRunnable b;

        a(DelayedRunnable delayedRunnable) {
            this.b = delayedRunnable;
        }

        public void run() {
            this.b.direct.replace(ExecutorScheduler.this.a(this.b));
        }
    }

    public c a() {
        return new ExecutorWorker(this.b);
    }

    public Disposable a(Runnable runnable) {
        Runnable a2 = io.reactivex.d.a.a(runnable);
        try {
            if (this.b instanceof ExecutorService) {
                ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(a2);
                scheduledDirectTask.setFuture(((ExecutorService) this.b).submit(scheduledDirectTask));
                return scheduledDirectTask;
            }
            BooleanRunnable booleanRunnable = new BooleanRunnable(a2);
            this.b.execute(booleanRunnable);
            return booleanRunnable;
        } catch (RejectedExecutionException e) {
            io.reactivex.d.a.a((Throwable) e);
            return EmptyDisposable.INSTANCE;
        }
    }

    public Disposable a(Runnable runnable, long j, TimeUnit timeUnit) {
        Runnable a2 = io.reactivex.d.a.a(runnable);
        if (this.b instanceof ScheduledExecutorService) {
            try {
                ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(a2);
                scheduledDirectTask.setFuture(((ScheduledExecutorService) this.b).schedule(scheduledDirectTask, j, timeUnit));
                return scheduledDirectTask;
            } catch (RejectedExecutionException e) {
                io.reactivex.d.a.a((Throwable) e);
                return EmptyDisposable.INSTANCE;
            }
        } else {
            DelayedRunnable delayedRunnable = new DelayedRunnable(a2);
            delayedRunnable.timed.replace(c.a(new a(delayedRunnable), j, timeUnit));
            return delayedRunnable;
        }
    }

    public Disposable a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        if (!(this.b instanceof ScheduledExecutorService)) {
            return super.a(runnable, j, j2, timeUnit);
        }
        try {
            ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(io.reactivex.d.a.a(runnable));
            scheduledDirectPeriodicTask.setFuture(((ScheduledExecutorService) this.b).scheduleAtFixedRate(scheduledDirectPeriodicTask, j, j2, timeUnit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e) {
            io.reactivex.d.a.a((Throwable) e);
            return EmptyDisposable.INSTANCE;
        }
    }
}
