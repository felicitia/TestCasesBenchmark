package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.a;
import com.google.android.gms.internal.maps.zza;

public final class zzai extends zza implements zzah {
    zzai(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IOnLocationChangeListener");
    }

    public final void zza(Location location) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        a.a(obtainAndWriteInterfaceToken, (Parcelable) location);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
}
