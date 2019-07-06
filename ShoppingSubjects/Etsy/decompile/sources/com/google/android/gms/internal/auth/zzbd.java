package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

public final class zzbd extends zzd implements zzbc {
    zzbd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    public final void zzd(zzba zzba) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzba);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzba zzba, CredentialRequest credentialRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzba);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) credentialRequest);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzba zzba, zzay zzay) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzba);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) zzay);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    public final void zzd(zzba zzba, zzbe zzbe) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzba);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) zzbe);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
}
