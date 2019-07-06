package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class IoScheduler extends Scheduler {
    static final RxThreadFactory EVICTOR_THREAD_FACTORY;
    private static final TimeUnit KEEP_ALIVE_UNIT = TimeUnit.SECONDS;
    static final CachedWorkerPool NONE = new CachedWorkerPool(0, null);
    static final ThreadWorker SHUTDOWN_THREAD_WORKER = new ThreadWorker(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));
    static final RxThreadFactory WORKER_THREAD_FACTORY;
    final AtomicReference<CachedWorkerPool> pool = new AtomicReference<>(NONE);

    static final class CachedWorkerPool implements Runnable {
        final CompositeDisposable allWorkers;
        private final ScheduledExecutorService evictorService;
        private final Future<?> evictorTask;
        private final ConcurrentLinkedQueue<ThreadWorker> expiringWorkerQueue;
        private final long keepAliveTime;

        CachedWorkerPool(long j, TimeUnit timeUnit) {
            Future<?> future;
            this.keepAliveTime = timeUnit != null ? timeUnit.toNanos(j) : 0;
            this.expiringWorkerQueue = new ConcurrentLinkedQueue<>();
            this.allWorkers = new CompositeDisposable();
            ScheduledExecutorService scheduledExecutorService = null;
            if (timeUnit != null) {
                scheduledExecutorService = Executors.newScheduledThreadPool(1, IoScheduler.EVICTOR_THREAD_FACTORY);
                future = scheduledExecutorService.scheduleWithFixedDelay(this, this.keepAliveTime, this.keepAliveTime, TimeUnit.NANOSECONDS);
            } else {
                future = null;
            }
            this.evictorService = scheduledExecutorService;
            this.evictorTask = future;
        }

        public void run() {
            evictExpiredWorkers();
        }

        /* access modifiers changed from: 0000 */
        public ThreadWorker get() {
            if (this.allWorkers.isDisposed()) {
                return IoScheduler.SHUTDOWN_THREAD_WORKER;
            }
            while (!this.expiringWorkerQueue.isEmpty()) {
                ThreadWorker threadWorker = (ThreadWorker) this.expiringWorkerQueue.poll();
                if (threadWorker != null) {
                    return threadWorker;
                }
            }
            ThreadWorker threadWorker2 = new ThreadWorker(IoScheduler.WORKER_THREAD_FACTORY);
            this.allWorkers.add(threadWorker2);
            return threadWorker2;
        }

        /* access modifiers changed from: 0000 */
        public void release(ThreadWorker threadWorker) {
            threadWorker.setExpirationTime(now() + this.keepAliveTime);
            this.expiringWorkerQueue.offer(threadWorker);
        }

        /* access modifiers changed from: 0000 */
        public void evictExpiredWorkers() {
            if (!this.expiringWorkerQueue.isEmpty()) {
                long now = now();
                Iterator it = this.expiringWorkerQueue.iterator();
                while (it.hasNext()) {
                    ThreadWorker threadWorker = (ThreadWorker) it.next();
                    if (threadWorker.getExpirationTime() > now) {
                        return;
                    }
                    if (this.expiringWorkerQueue.remove(threadWorker)) {
                        this.allWorkers.remove(threadWorker);
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public long now() {
            return System.nanoTime();
        }

        /* access modifiers changed from: 0000 */
        public void shutdown() {
            this.allWorkers.dispose();
            if (this.evictorTask != null) {
                this.evictorTask.cancel(true);
            }
            if (this.evictorService != null) {
                this.evictorService.shutdownNow();
            }
        }
    }

    static final class EventLoopWorker extends Worker {
        final AtomicBoolean once = new AtomicBoolean();
        private final CachedWorkerPool pool;
        private final CompositeDisposable tasks;
        private final ThreadWorker threadWorker;

        EventLoopWorker(CachedWorkerPool cachedWorkerPool) {
            this.pool = cachedWorkerPool;
            this.tasks = new CompositeDisposable();
            this.threadWorker = cachedWorkerPool.get();
        }

        public void dispose() {
            if (this.once.compareAndSet(false, true)) {
                this.tasks.dispose();
                this.pool.release(this.threadWorker);
            }
        }

        public Disposable schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.tasks.isDisposed()) {
                return EmptyDisposable.INSTANCE;
            }
            return this.threadWorker.scheduleActual(runnable, j, timeUnit, this.tasks);
        }
    }

    static final class ThreadWorker extends NewThreadWorker {
        private long expirationTime = 0;

        ThreadWorker(ThreadFactory threadFactory) {
            super(threadFactory);
        }

        public long getExpirationTime() {
            return this.expirationTime;
        }

        public void setExpirationTime(long j) {
            this.expirationTime = j;
        }
    }

    static {
        NONE.shutdown();
        SHUTDOWN_THREAD_WORKER.dispose();
        int max = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        WORKER_THREAD_FACTORY = new RxThreadFactory("RxCachedThreadScheduler", max);
        EVICTOR_THREAD_FACTORY = new RxThreadFactory("RxCachedWorkerPoolEvictor", max);
    }

    public IoScheduler() {
        start();
    }

    public void start() {
        CachedWorkerPool cachedWorkerPool = new CachedWorkerPool(60, KEEP_ALIVE_UNIT);
        if (!this.pool.compareAndSet(NONE, cachedWorkerPool)) {
            cachedWorkerPool.shutdown();
        }
    }

    public Worker createWorker() {
        return new EventLoopWorker((CachedWorkerPool) this.pool.get());
    }
}
