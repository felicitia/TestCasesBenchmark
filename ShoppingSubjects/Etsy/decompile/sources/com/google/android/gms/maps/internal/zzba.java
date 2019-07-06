package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.a;
import com.google.android.gms.internal.maps.zzb;

public abstract class zzba extends zzb implements zzaz {
    public zzba() {
        super("com.google.android.gms.maps.internal.IOnMyLocationClickListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onMyLocationClick((Location) a.a(parcel, Location.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
