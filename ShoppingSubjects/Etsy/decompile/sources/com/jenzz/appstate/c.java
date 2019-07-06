package com.jenzz.appstate;

import android.app.Application;
import android.support.annotation.NonNull;
import com.jenzz.appstate.a.a;

/* compiled from: RxAppStateMonitor */
public final class c implements b {
    @NonNull
    private final a a;

    @NonNull
    public static b a(@NonNull Application application) {
        return new c(application);
    }

    private c(@NonNull Application application) {
        this.a = new com.jenzz.appstate.a.c(application);
    }

    public void a() {
        this.a.a();
    }

    public void a(@NonNull a aVar) {
        this.a.a(aVar);
    }

    public boolean b() {
        return this.a.b() == AppState.BACKGROUND;
    }
}
