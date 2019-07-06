package com.google.firebase.iid;

import java.util.concurrent.Executor;

final /* synthetic */ class ac implements Executor {
    static final Executor a = new ac();

    private ac() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
