package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final class lb implements Executor {
    lb() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
