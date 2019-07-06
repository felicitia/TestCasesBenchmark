package com.facebook.internal;

import com.facebook.FacebookSdk;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

public class LockOnGetVariable<T> {
    /* access modifiers changed from: private */
    public CountDownLatch initLatch = new CountDownLatch(1);
    /* access modifiers changed from: private */
    public T value;

    public LockOnGetVariable(final Callable<T> callable) {
        FacebookSdk.getExecutor().execute(new FutureTask(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                try {
                    LockOnGetVariable.this.value = callable.call();
                    LockOnGetVariable.this.initLatch.countDown();
                    return null;
                } catch (Throwable th) {
                    LockOnGetVariable.this.initLatch.countDown();
                    throw th;
                }
            }
        }));
    }
}
