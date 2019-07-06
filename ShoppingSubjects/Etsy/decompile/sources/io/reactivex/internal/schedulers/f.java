package io.reactivex.internal.schedulers;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.u.c;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* compiled from: NewThreadWorker */
public class f extends c implements Disposable {
    volatile boolean a;
    private final ScheduledExecutorService b;

    public f(ThreadFactory threadFactory) {
        this.b = h.a(threadFactory);
    }

    public Disposable a(Runnable runnable) {
        return a(runnable, 0, null);
    }

    public Disposable a(Runnable runnable, long j, TimeUnit timeUnit) {
        if (this.a) {
            return EmptyDisposable.INSTANCE;
        }
        return a(runnable, j, timeUnit, null);
    }

    public Disposable b(Runnable runnable, long j, TimeUnit timeUnit) {
        Future future;
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(a.a(runnable));
        if (j <= 0) {
            try {
                future = this.b.submit(scheduledDirectTask);
            } catch (RejectedExecutionException e) {
                a.a((Throwable) e);
                return EmptyDisposable.INSTANCE;
            }
        } else {
            future = this.b.schedule(scheduledDirectTask, j, timeUnit);
        }
        scheduledDirectTask.setFuture(future);
        return scheduledDirectTask;
    }

    public Disposable b(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Future future;
        Runnable a2 = a.a(runnable);
        if (j2 <= 0) {
            c cVar = new c(a2, this.b);
            if (j <= 0) {
                try {
                    future = this.b.submit(cVar);
                } catch (RejectedExecutionException e) {
                    a.a((Throwable) e);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                future = this.b.schedule(cVar, j, timeUnit);
            }
            cVar.a(future);
            return cVar;
        }
        ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(a2);
        try {
            scheduledDirectPeriodicTask.setFuture(this.b.scheduleAtFixedRate(scheduledDirectPeriodicTask, j, j2, timeUnit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e2) {
            a.a((Throwable) e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public ScheduledRunnable a(Runnable runnable, long j, TimeUnit timeUnit, io.reactivex.internal.disposables.a aVar) {
        Future future;
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(a.a(runnable), aVar);
        if (aVar != null && !aVar.a(scheduledRunnable)) {
            return scheduledRunnable;
        }
        if (j <= 0) {
            try {
                future = this.b.submit(scheduledRunnable);
            } catch (RejectedExecutionException e) {
                if (aVar != null) {
                    aVar.b(scheduledRunnable);
                }
                a.a((Throwable) e);
            }
        } else {
            future = this.b.schedule(scheduledRunnable, j, timeUnit);
        }
        scheduledRunnable.setFuture(future);
        return scheduledRunnable;
    }

    public void dispose() {
        if (!this.a) {
            this.a = true;
            this.b.shutdownNow();
        }
    }

    public void b() {
        if (!this.a) {
            this.a = true;
            this.b.shutdown();
        }
    }

    public boolean isDisposed() {
        return this.a;
    }
}
