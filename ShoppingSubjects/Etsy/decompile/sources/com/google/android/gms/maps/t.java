package com.google.android.gms.maps;

import com.google.android.gms.maps.c.e;
import com.google.android.gms.maps.internal.zzak;
import com.google.android.gms.maps.model.LatLng;

final class t extends zzak {
    private final /* synthetic */ e a;

    t(c cVar, e eVar) {
        this.a = eVar;
    }

    public final void onMapClick(LatLng latLng) {
        this.a.a(latLng);
    }
}
