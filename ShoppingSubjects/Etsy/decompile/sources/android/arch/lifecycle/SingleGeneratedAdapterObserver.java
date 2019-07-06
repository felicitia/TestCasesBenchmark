package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
    private final c mGeneratedAdapter;

    SingleGeneratedAdapterObserver(c cVar) {
        this.mGeneratedAdapter = cVar;
    }

    public void onStateChanged(e eVar, Event event) {
        this.mGeneratedAdapter.callMethods(eVar, event, false, null);
        this.mGeneratedAdapter.callMethods(eVar, event, true, null);
    }
}
