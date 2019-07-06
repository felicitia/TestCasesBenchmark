package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.location.g;

final class m implements Notifier<g> {
    private final /* synthetic */ Location a;

    m(l lVar, Location location) {
        this.a = location;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((g) obj).a(this.a);
    }

    public final void onNotifyListenerFailed() {
    }
}
