package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;

class ReflectiveGenericLifecycleObserver implements GenericLifecycleObserver {
    private final Object a;
    private final C0001a b = a.a.b(this.a.getClass());

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.a = obj;
    }

    public void onStateChanged(e eVar, Event event) {
        this.b.a(eVar, event, this.a);
    }
}
