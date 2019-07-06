package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.g;
import com.google.android.gms.location.zzy;

final class l extends zzy {
    private final ListenerHolder<g> a;

    l(ListenerHolder<g> listenerHolder) {
        this.a = listenerHolder;
    }

    public final synchronized void a() {
        this.a.clear();
    }

    public final synchronized void onLocationChanged(Location location) {
        this.a.notifyListener(new m(this, location));
    }
}
