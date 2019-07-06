package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

final class n extends zzaq {
    private final /* synthetic */ e a;

    n(a aVar, e eVar) {
        this.a = eVar;
    }

    public final void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
        this.a.onMapReady(new c(iGoogleMapDelegate));
    }
}
