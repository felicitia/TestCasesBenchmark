package com.facebook.internal;

import com.facebook.f;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/* compiled from: LockOnGetVariable */
public class s<T> {
    /* access modifiers changed from: private */
    public T a;
    /* access modifiers changed from: private */
    public CountDownLatch b = new CountDownLatch(1);

    public s(final Callable<T> callable) {
        f.d().execute(new FutureTask(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            /* renamed from: a */
            public Void call() throws Exception {
                try {
                    s.this.a = callable.call();
                    s.this.b.countDown();
                    return null;
                } catch (Throwable th) {
                    s.this.b.countDown();
                    throw th;
                }
            }
        }));
    }

    public T a() {
        b();
        return this.a;
    }

    private void b() {
        if (this.b != null) {
            try {
                this.b.await();
            } catch (InterruptedException unused) {
            }
        }
    }
}
