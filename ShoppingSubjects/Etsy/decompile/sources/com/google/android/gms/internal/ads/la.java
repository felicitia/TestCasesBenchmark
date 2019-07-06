package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.ads.internal.ao;
import java.util.concurrent.Executor;

final class la implements Executor {
    private final Handler a = new zzakc(Looper.getMainLooper());

    la() {
    }

    public final void execute(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            try {
                runnable.run();
            } catch (Throwable th) {
                ao.e();
                hd.a(ao.i().m(), th);
                throw th;
            }
        } else {
            this.a.post(runnable);
        }
    }
}
