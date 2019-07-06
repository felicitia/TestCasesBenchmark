package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class x implements Executor {
    x() {
    }

    public final void execute(@NonNull Runnable runnable) {
        runnable.run();
    }
}
