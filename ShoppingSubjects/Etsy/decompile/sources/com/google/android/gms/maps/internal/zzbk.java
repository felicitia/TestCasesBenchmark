package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.a;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

public abstract class zzbk extends zzb implements zzbj {
    public zzbk() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onStreetViewPanoramaChange((StreetViewPanoramaLocation) a.a(parcel, StreetViewPanoramaLocation.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
