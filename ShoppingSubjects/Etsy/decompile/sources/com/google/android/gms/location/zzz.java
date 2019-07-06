package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.location.t;
import com.google.android.gms.internal.location.zza;

public final class zzz extends zza implements zzx {
    zzz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationListener");
    }

    public final void onLocationChanged(Location location) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        t.a(obtainAndWriteInterfaceToken, (Parcelable) location);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
