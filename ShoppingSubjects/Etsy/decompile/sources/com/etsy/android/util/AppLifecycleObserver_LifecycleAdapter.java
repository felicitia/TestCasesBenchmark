package com.etsy.android.util;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.c;
import android.arch.lifecycle.e;
import android.arch.lifecycle.g;

public class AppLifecycleObserver_LifecycleAdapter implements c {
    final AppLifecycleObserver mReceiver;

    AppLifecycleObserver_LifecycleAdapter(AppLifecycleObserver appLifecycleObserver) {
        this.mReceiver = appLifecycleObserver;
    }

    public void callMethods(e eVar, Event event, boolean z, g gVar) {
        boolean z2 = gVar != null;
        if (!z) {
            if (event == Event.ON_START) {
                if (!z2 || gVar.a("onEnterForeground", 1)) {
                    this.mReceiver.onEnterForeground();
                }
            } else if (event == Event.ON_STOP) {
                if (!z2 || gVar.a("onEnterBackground", 1)) {
                    this.mReceiver.onEnterBackground();
                }
            }
        }
    }
}
