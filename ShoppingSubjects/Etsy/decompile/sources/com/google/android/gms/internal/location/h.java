package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.f;
import com.google.android.gms.location.zzv;

final class h extends zzv {
    private final ListenerHolder<f> a;

    public final void onLocationAvailability(LocationAvailability locationAvailability) {
        this.a.notifyListener(new j(this, locationAvailability));
    }

    public final void onLocationResult(LocationResult locationResult) {
        this.a.notifyListener(new i(this, locationResult));
    }
}
