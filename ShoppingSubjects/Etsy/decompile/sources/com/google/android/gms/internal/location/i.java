package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.f;

final class i implements Notifier<f> {
    private final /* synthetic */ LocationResult a;

    i(h hVar, LocationResult locationResult) {
        this.a = locationResult;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((f) obj).a(this.a);
    }

    public final void onNotifyListenerFailed() {
    }
}
