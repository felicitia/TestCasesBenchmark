package com.google.android.gms.internal.icing;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzas extends zza implements zzar {
    zzas(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.search.internal.ISearchAuthService");
    }

    public final void zza(zzap zzap, String str, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        y.a(obtainAndWriteInterfaceToken, (IInterface) zzap);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzap zzap, String str, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        y.a(obtainAndWriteInterfaceToken, (IInterface) zzap);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
}
