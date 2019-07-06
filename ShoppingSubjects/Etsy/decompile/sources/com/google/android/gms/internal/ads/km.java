package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeoutException;

final /* synthetic */ class km implements Runnable {
    private final le a;

    km(le leVar) {
        this.a = leVar;
    }

    public final void run() {
        this.a.a(new TimeoutException());
    }
}
