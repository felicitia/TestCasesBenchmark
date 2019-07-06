package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzad extends zzd implements zzac {
    zzad(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService");
    }

    public final void zzd(zzaa zzaa, zzae zzae) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzaa);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) zzae);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzaa zzaa, zzag zzag) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzaa);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) zzag);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzaa zzaa, zzai zzai) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzaa);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) zzai);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzaa zzaa, zzak zzak) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzaa);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) zzak);
        transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzaa zzaa, zzy zzy) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzaa);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) zzy);
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }
}
