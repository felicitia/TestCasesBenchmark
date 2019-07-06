package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;

public final class NewThreadScheduler extends Scheduler {
    private static final NewThreadScheduler INSTANCE = new NewThreadScheduler();
    private static final RxThreadFactory THREAD_FACTORY = new RxThreadFactory("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    public static NewThreadScheduler instance() {
        return INSTANCE;
    }

    private NewThreadScheduler() {
    }

    public Worker createWorker() {
        return new NewThreadWorker(THREAD_FACTORY);
    }
}
