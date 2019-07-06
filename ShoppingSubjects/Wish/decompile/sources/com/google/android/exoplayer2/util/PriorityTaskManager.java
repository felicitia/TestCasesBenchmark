package com.google.android.exoplayer2.util;

import java.io.IOException;
import java.util.PriorityQueue;

public final class PriorityTaskManager {
    private int highestPriority;
    private final Object lock;
    private final PriorityQueue<Integer> queue;

    public static class PriorityTooLowException extends IOException {
    }

    public void add(int i) {
        synchronized (this.lock) {
            this.queue.add(Integer.valueOf(i));
            this.highestPriority = Math.max(this.highestPriority, i);
        }
    }

    public void remove(int i) {
        synchronized (this.lock) {
            this.queue.remove(Integer.valueOf(i));
            this.highestPriority = this.queue.isEmpty() ? Integer.MIN_VALUE : ((Integer) this.queue.peek()).intValue();
            this.lock.notifyAll();
        }
    }
}
