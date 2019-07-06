package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzhp extends zzej implements zzho {
    zzhp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.cache.ICacheService");
    }

    public final zzhi zza(zzhl zzhl) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        aej.a(obtainAndWriteInterfaceToken, (Parcelable) zzhl);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzhi zzhi = (zzhi) aej.a(transactAndReadException, zzhi.CREATOR);
        transactAndReadException.recycle();
        return zzhi;
    }
}
