package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.u;
import io.reactivex.u.c;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: SingleScheduler */
public final class i extends u {
    static final RxThreadFactory d = new RxThreadFactory("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    static final ScheduledExecutorService e = Executors.newScheduledThreadPool(0);
    final ThreadFactory b;
    final AtomicReference<ScheduledExecutorService> c;

    /* compiled from: SingleScheduler */
    static final class a extends c {
        final ScheduledExecutorService a;
        final io.reactivex.disposables.a b = new io.reactivex.disposables.a();
        volatile boolean c;

        a(ScheduledExecutorService scheduledExecutorService) {
            this.a = scheduledExecutorService;
        }

        public Disposable a(Runnable runnable, long j, TimeUnit timeUnit) {
            Future future;
            if (this.c) {
                return EmptyDisposable.INSTANCE;
            }
            ScheduledRunnable scheduledRunnable = new ScheduledRunnable(io.reactivex.d.a.a(runnable), this.b);
            this.b.a((Disposable) scheduledRunnable);
            if (j <= 0) {
                try {
                    future = this.a.submit(scheduledRunnable);
                } catch (RejectedExecutionException e) {
                    dispose();
                    io.reactivex.d.a.a((Throwable) e);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                future = this.a.schedule(scheduledRunnable, j, timeUnit);
            }
            scheduledRunnable.setFuture(future);
            return scheduledRunnable;
        }

        public void dispose() {
            if (!this.c) {
                this.c = true;
                this.b.dispose();
            }
        }

        public boolean isDisposed() {
            return this.c;
        }
    }

    static {
        e.shutdown();
    }

    public i() {
        this(d);
    }

    public i(ThreadFactory threadFactory) {
        this.c = new AtomicReference<>();
        this.b = threadFactory;
        this.c.lazySet(a(threadFactory));
    }

    static ScheduledExecutorService a(ThreadFactory threadFactory) {
        return h.a(threadFactory);
    }

    public void b() {
        ScheduledExecutorService scheduledExecutorService;
        ScheduledExecutorService scheduledExecutorService2 = null;
        do {
            scheduledExecutorService = (ScheduledExecutorService) this.c.get();
            if (scheduledExecutorService != e) {
                if (scheduledExecutorService2 != null) {
                    scheduledExecutorService2.shutdown();
                }
                return;
            } else if (scheduledExecutorService2 == null) {
                scheduledExecutorService2 = a(this.b);
            }
        } while (!this.c.compareAndSet(scheduledExecutorService, scheduledExecutorService2));
    }

    public c a() {
        return new a((ScheduledExecutorService) this.c.get());
    }

    public Disposable a(Runnable runnable, long j, TimeUnit timeUnit) {
        Future future;
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(io.reactivex.d.a.a(runnable));
        if (j <= 0) {
            try {
                future = ((ScheduledExecutorService) this.c.get()).submit(scheduledDirectTask);
            } catch (RejectedExecutionException e2) {
                io.reactivex.d.a.a((Throwable) e2);
                return EmptyDisposable.INSTANCE;
            }
        } else {
            future = ((ScheduledExecutorService) this.c.get()).schedule(scheduledDirectTask, j, timeUnit);
        }
        scheduledDirectTask.setFuture(future);
        return scheduledDirectTask;
    }

    public Disposable a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Future future;
        Runnable a2 = io.reactivex.d.a.a(runnable);
        if (j2 <= 0) {
            ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) this.c.get();
            c cVar = new c(a2, scheduledExecutorService);
            if (j <= 0) {
                try {
                    future = scheduledExecutorService.submit(cVar);
                } catch (RejectedExecutionException e2) {
                    io.reactivex.d.a.a((Throwable) e2);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                future = scheduledExecutorService.schedule(cVar, j, timeUnit);
            }
            cVar.a(future);
            return cVar;
        }
        ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(a2);
        try {
            scheduledDirectPeriodicTask.setFuture(((ScheduledExecutorService) this.c.get()).scheduleAtFixedRate(scheduledDirectPeriodicTask, j, j2, timeUnit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e3) {
            io.reactivex.d.a.a((Throwable) e3);
            return EmptyDisposable.INSTANCE;
        }
    }
}
