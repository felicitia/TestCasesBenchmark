package com.google.android.gms.maps;

import com.google.android.gms.maps.c.C0141c;
import com.google.android.gms.maps.internal.zzm;
import com.google.android.gms.maps.model.CameraPosition;

final class s extends zzm {
    private final /* synthetic */ C0141c a;

    s(c cVar, C0141c cVar2) {
        this.a = cVar2;
    }

    public final void onCameraChange(CameraPosition cameraPosition) {
        this.a.a(cameraPosition);
    }
}
