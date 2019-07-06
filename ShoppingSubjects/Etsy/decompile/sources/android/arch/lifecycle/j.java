package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.os.Handler;
import android.support.annotation.NonNull;

/* compiled from: ServiceLifecycleDispatcher */
public class j {
    private final LifecycleRegistry a;
    private final Handler b = new Handler();
    private a c;

    /* compiled from: ServiceLifecycleDispatcher */
    static class a implements Runnable {
        final Event a;
        private final LifecycleRegistry b;
        private boolean c = false;

        a(@NonNull LifecycleRegistry lifecycleRegistry, Event event) {
            this.b = lifecycleRegistry;
            this.a = event;
        }

        public void run() {
            if (!this.c) {
                this.b.handleLifecycleEvent(this.a);
                this.c = true;
            }
        }
    }

    public j(@NonNull e eVar) {
        this.a = new LifecycleRegistry(eVar);
    }

    private void a(Event event) {
        if (this.c != null) {
            this.c.run();
        }
        this.c = new a(this.a, event);
        this.b.postAtFrontOfQueue(this.c);
    }

    public void a() {
        a(Event.ON_CREATE);
    }

    public void b() {
        a(Event.ON_START);
    }

    public void c() {
        a(Event.ON_START);
    }

    public void d() {
        a(Event.ON_STOP);
        a(Event.ON_DESTROY);
    }

    public Lifecycle e() {
        return this.a;
    }
}
