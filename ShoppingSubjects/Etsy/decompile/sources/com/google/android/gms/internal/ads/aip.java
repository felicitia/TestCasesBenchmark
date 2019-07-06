package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

final class aip implements Executor {
    private final /* synthetic */ Handler a;

    aip(aho aho, Handler handler) {
        this.a = handler;
    }

    public final void execute(Runnable runnable) {
        this.a.post(runnable);
    }
}
