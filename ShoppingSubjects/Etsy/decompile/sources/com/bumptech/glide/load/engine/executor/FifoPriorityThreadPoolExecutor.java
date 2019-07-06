package com.bumptech.glide.load.engine.executor;

import android.os.Process;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FifoPriorityThreadPoolExecutor extends ThreadPoolExecutor {
    private final AtomicInteger a;
    private final UncaughtThrowableStrategy b;

    public enum UncaughtThrowableStrategy {
        IGNORE,
        LOG {
            /* access modifiers changed from: protected */
            public void handle(Throwable th) {
                if (Log.isLoggable("PriorityExecutor", 6)) {
                    Log.e("PriorityExecutor", "Request threw uncaught throwable", th);
                }
            }
        },
        THROW {
            /* access modifiers changed from: protected */
            public void handle(Throwable th) {
                super.handle(th);
                throw new RuntimeException(th);
            }
        };

        /* access modifiers changed from: protected */
        public void handle(Throwable th) {
        }
    }

    public static class a implements ThreadFactory {
        int a = 0;

        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("fifo-pool-thread-");
            sb.append(this.a);
            AnonymousClass1 r0 = new Thread(runnable, sb.toString()) {
                public void run() {
                    Process.setThreadPriority(10);
                    super.run();
                }
            };
            this.a++;
            return r0;
        }
    }

    static class b<T> extends FutureTask<T> implements Comparable<b<?>> {
        private final int a;
        private final int b;

        public b(Runnable runnable, T t, int i) {
            super(runnable, t);
            if (!(runnable instanceof a)) {
                throw new IllegalArgumentException("FifoPriorityThreadPoolExecutor must be given Runnables that implement Prioritized");
            }
            this.a = ((a) runnable).b();
            this.b = i;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            if (this.b == bVar.b && this.a == bVar.a) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return (31 * this.a) + this.b;
        }

        /* renamed from: a */
        public int compareTo(b<?> bVar) {
            int i = this.a - bVar.a;
            return i == 0 ? this.b - bVar.b : i;
        }
    }

    public FifoPriorityThreadPoolExecutor(int i) {
        this(i, UncaughtThrowableStrategy.LOG);
    }

    public FifoPriorityThreadPoolExecutor(int i, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        this(i, i, 0, TimeUnit.MILLISECONDS, new a(), uncaughtThrowableStrategy);
    }

    public FifoPriorityThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        super(i, i2, j, timeUnit, new PriorityBlockingQueue(), threadFactory);
        this.a = new AtomicInteger();
        this.b = uncaughtThrowableStrategy;
    }

    /* access modifiers changed from: protected */
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new b(runnable, t, this.a.getAndIncrement());
    }

    /* access modifiers changed from: protected */
    public void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (th == null && (runnable instanceof Future)) {
            Future future = (Future) runnable;
            if (future.isDone() && !future.isCancelled()) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    this.b.handle(e);
                } catch (ExecutionException e2) {
                    this.b.handle(e2);
                }
            }
        }
    }
}
