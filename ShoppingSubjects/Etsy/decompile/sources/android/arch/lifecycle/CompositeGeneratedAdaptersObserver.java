package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {
    private final c[] mGeneratedAdapters;

    CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.mGeneratedAdapters = cVarArr;
    }

    public void onStateChanged(e eVar, Event event) {
        g gVar = new g();
        for (c callMethods : this.mGeneratedAdapters) {
            callMethods.callMethods(eVar, event, false, gVar);
        }
        for (c callMethods2 : this.mGeneratedAdapters) {
            callMethods2.callMethods(eVar, event, true, gVar);
        }
    }
}
