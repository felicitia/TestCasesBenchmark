package com.facebook.appevents;

import android.content.Context;
import com.facebook.f;
import java.util.HashMap;
import java.util.Set;

/* compiled from: AppEventCollection */
class b {
    private final HashMap<AccessTokenAppIdPair, f> a = new HashMap<>();

    public synchronized void a(PersistedEvents persistedEvents) {
        if (persistedEvents != null) {
            for (AccessTokenAppIdPair accessTokenAppIdPair : persistedEvents.keySet()) {
                f b = b(accessTokenAppIdPair);
                for (AppEvent a2 : persistedEvents.get(accessTokenAppIdPair)) {
                    b.a(a2);
                }
            }
        }
    }

    public synchronized void a(AccessTokenAppIdPair accessTokenAppIdPair, AppEvent appEvent) {
        b(accessTokenAppIdPair).a(appEvent);
    }

    public synchronized Set<AccessTokenAppIdPair> a() {
        return this.a.keySet();
    }

    public synchronized f a(AccessTokenAppIdPair accessTokenAppIdPair) {
        return (f) this.a.get(accessTokenAppIdPair);
    }

    public synchronized int b() {
        int i;
        i = 0;
        for (f a2 : this.a.values()) {
            i += a2.a();
        }
        return i;
    }

    private synchronized f b(AccessTokenAppIdPair accessTokenAppIdPair) {
        f fVar;
        fVar = (f) this.a.get(accessTokenAppIdPair);
        if (fVar == null) {
            Context f = f.f();
            fVar = new f(com.facebook.internal.b.a(f), AppEventsLogger.b(f));
        }
        this.a.put(accessTokenAppIdPair, fVar);
        return fVar;
    }
}
