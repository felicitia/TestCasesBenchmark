package com.google.android.gms.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.location.t;
import com.google.android.gms.internal.location.zza;

public final class zzw extends zza implements zzu {
    zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationCallback");
    }

    public final void onLocationAvailability(LocationAvailability locationAvailability) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        t.a(obtainAndWriteInterfaceToken, (Parcelable) locationAvailability);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }

    public final void onLocationResult(LocationResult locationResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        t.a(obtainAndWriteInterfaceToken, (Parcelable) locationResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
