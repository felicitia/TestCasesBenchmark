package com.jenzz.appstate.a;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.jenzz.appstate.AppState;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({Scope.LIBRARY})
/* compiled from: DefaultAppStateRecognizer */
public final class c implements a {
    @NonNull
    private final b a = new b();
    @NonNull
    private final ActivityLifecycleCallbacks b = new a();
    @NonNull
    private final ComponentCallbacks2 c = new C0157c();
    @NonNull
    private final BroadcastReceiver d = new b();
    /* access modifiers changed from: private */
    @NonNull
    public final AtomicBoolean e = new AtomicBoolean(true);
    @NonNull
    private final Application f;
    @NonNull
    private AppState g = AppState.BACKGROUND;

    /* compiled from: DefaultAppStateRecognizer */
    private class a extends d {
        private a() {
        }

        public void onActivityStarted(Activity activity) {
            if (c.this.e.compareAndSet(true, false)) {
                c.this.e();
                return;
            }
            if (c.this.d()) {
                c.this.e();
            }
        }
    }

    /* compiled from: DefaultAppStateRecognizer */
    private class b extends BroadcastReceiver {
        private b() {
        }

        public void onReceive(Context context, Intent intent) {
            if (c.this.c()) {
                c.this.f();
            }
        }
    }

    /* renamed from: com.jenzz.appstate.a.c$c reason: collision with other inner class name */
    /* compiled from: DefaultAppStateRecognizer */
    private class C0157c extends e {
        private C0157c() {
        }

        public void onTrimMemory(int i) {
            if (i >= 20 && c.this.c()) {
                c.this.f();
            }
        }
    }

    public c(@NonNull Application application) {
        this.f = application;
    }

    public void a(@NonNull com.jenzz.appstate.a aVar) {
        this.a.a(aVar);
    }

    public void a() {
        this.f.registerActivityLifecycleCallbacks(this.b);
        this.f.registerComponentCallbacks(this.c);
        this.f.registerReceiver(this.d, new IntentFilter("android.intent.action.SCREEN_OFF"));
    }

    @NonNull
    public AppState b() {
        return this.g;
    }

    /* access modifiers changed from: private */
    public boolean c() {
        return this.g == AppState.FOREGROUND;
    }

    /* access modifiers changed from: private */
    public boolean d() {
        return this.g == AppState.BACKGROUND;
    }

    /* access modifiers changed from: private */
    public void e() {
        this.g = AppState.FOREGROUND;
        this.a.a();
    }

    /* access modifiers changed from: private */
    public void f() {
        this.g = AppState.BACKGROUND;
        this.a.b();
    }
}
