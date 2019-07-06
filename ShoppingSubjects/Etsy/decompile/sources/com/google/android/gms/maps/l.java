package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbq;

final class l extends zzbq {
    private final /* synthetic */ f a;

    l(a aVar, f fVar) {
        this.a = fVar;
    }

    public final void zza(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) throws RemoteException {
        this.a.a(new i(iStreetViewPanoramaDelegate));
    }
}
