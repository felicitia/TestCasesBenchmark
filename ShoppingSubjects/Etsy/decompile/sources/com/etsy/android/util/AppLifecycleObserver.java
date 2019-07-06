package com.etsy.android.util;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.d;
import android.arch.lifecycle.i;
import com.etsy.android.lib.core.posts.b;
import com.etsy.android.lib.logger.l;
import kotlin.jvm.internal.p;

/* compiled from: AppLifecycleObserver.kt */
public final class AppLifecycleObserver implements d {
    private final l logCat;

    public AppLifecycleObserver(l lVar) {
        p.b(lVar, "logCat");
        this.logCat = lVar;
    }

    @i(a = Event.ON_START)
    public final void onEnterForeground() {
        this.logCat.c("App is foregrounded");
        b.a();
    }

    @i(a = Event.ON_STOP)
    public final void onEnterBackground() {
        this.logCat.c("App is backgrounded");
        b.b();
    }
}
