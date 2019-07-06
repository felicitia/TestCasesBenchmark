package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

final class z implements Runnable {
    private final /* synthetic */ y a;
    private final /* synthetic */ Callable b;

    z(y yVar, Callable callable) {
        this.a = yVar;
        this.b = callable;
    }

    public final void run() {
        try {
            this.a.a(this.b.call());
        } catch (Exception e) {
            this.a.a(e);
        }
    }
}
