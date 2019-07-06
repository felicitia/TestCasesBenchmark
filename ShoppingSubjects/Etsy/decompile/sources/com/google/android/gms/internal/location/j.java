package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.f;

final class j implements Notifier<f> {
    private final /* synthetic */ LocationAvailability a;

    j(h hVar, LocationAvailability locationAvailability) {
        this.a = locationAvailability;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((f) obj).a(this.a);
    }

    public final void onNotifyListenerFailed() {
    }
}
