package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.e;
import com.google.android.gms.internal.auth.zzd;

public final class zzh extends zzd implements zzf {
    zzh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.account.IWorkAccountService");
    }

    public final void zzd(zzd zzd, Account account) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzd);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) account);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzd zzd, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzd);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    public final void zze(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
