package com.otaliastudios.cameraview;

import java.util.concurrent.CountDownLatch;

class Task<T> {
    private int mCount;
    private CountDownLatch mLatch;
    private T mResult;

    Task() {
    }

    private boolean listening() {
        return this.mLatch != null;
    }

    /* access modifiers changed from: 0000 */
    public void start() {
        if (!listening()) {
            this.mCount++;
        }
    }

    /* access modifiers changed from: 0000 */
    public void end(T t) {
        if (this.mCount > 0) {
            this.mCount--;
            return;
        }
        if (listening()) {
            this.mResult = t;
            this.mLatch.countDown();
        }
    }
}
