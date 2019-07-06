package com.crittercism.internal;

import android.support.annotation.NonNull;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public final class co implements ScheduledExecutorService {
    private ScheduledThreadPoolExecutor a;
    private String b;

    static class a implements ThreadFactory {
        private String a;

        public a(String str) {
            this.a = str;
        }

        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, this.a);
        }
    }

    static class b<T> implements Callable<T> {
        private Callable<T> a;

        public b(Callable<T> callable) {
            this.a = callable;
        }

        public final T call() {
            try {
                return this.a.call();
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
                return null;
            }
        }
    }

    static class c implements Runnable {
        private Runnable a;
        private String b;

        public c(Runnable runnable) {
            this.a = runnable;
        }

        public final void run() {
            try {
                this.a.run();
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                cm.b(th);
            }
        }

        public final String toString() {
            if (this.b != null) {
                return this.b;
            }
            return this.a.toString();
        }
    }

    private co(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, String str) {
        this.a = scheduledThreadPoolExecutor;
        this.b = str;
    }

    public static ScheduledExecutorService a(String str) {
        return new co(new ScheduledThreadPoolExecutor(3, new a(str)), str);
    }

    public static ScheduledExecutorService b(String str) {
        return new co(new ScheduledThreadPoolExecutor(1, new a(str)), str);
    }

    @NonNull
    public final ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.a.schedule(new c(runnable), j, timeUnit);
    }

    @NonNull
    public final <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        return this.a.schedule(new b(callable), j, timeUnit);
    }

    @NonNull
    public final ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.a.scheduleAtFixedRate(new c(runnable), j, j2, timeUnit);
    }

    @NonNull
    public final ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.a.scheduleWithFixedDelay(new c(runnable), j, j2, timeUnit);
    }

    public final void shutdown() {
        StringBuilder sb = new StringBuilder("Shutting down queue. ");
        sb.append(toString());
        cm.a(sb.toString());
        this.a.shutdown();
    }

    @NonNull
    public final List<Runnable> shutdownNow() {
        return this.a.shutdownNow();
    }

    public final boolean isShutdown() {
        return this.a.isShutdown();
    }

    public final boolean isTerminated() {
        return this.a.isTerminated();
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) {
        return this.a.awaitTermination(j, timeUnit);
    }

    @NonNull
    public final <T> Future<T> submit(Callable<T> callable) {
        return this.a.submit(new b(callable));
    }

    @NonNull
    public final <T> Future<T> submit(Runnable runnable, T t) {
        return this.a.submit(new c(runnable), t);
    }

    @NonNull
    public final Future<?> submit(Runnable runnable) {
        return this.a.submit(new c(runnable));
    }

    @NonNull
    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public final <T> T invokeAny(Collection<? extends Callable<T>> collection) {
        throw new UnsupportedOperationException();
    }

    public final <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public final void execute(Runnable runnable) {
        this.a.execute(new c(runnable));
    }

    public final String toString() {
        BlockingQueue<Runnable> queue = this.a.getQueue();
        StringBuilder sb = new StringBuilder("ProtectedExecutorService(");
        sb.append(this.b);
        sb.append(") size = ");
        sb.append(queue.size());
        sb.append("\n");
        String sb2 = sb.toString();
        for (Runnable obj : queue) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(obj.toString());
            sb3.append("\n");
            sb2 = sb3.toString();
        }
        return sb2;
    }
}
