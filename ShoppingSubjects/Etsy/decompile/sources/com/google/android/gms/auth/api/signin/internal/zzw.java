package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.internal.auth.e;
import com.google.android.gms.internal.auth.zzd;

public final class zzw extends zzd implements zzv {
    zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }

    public final void zzd(zzt zzt, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzt);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) googleSignInOptions);
        transactAndReadExceptionReturnVoid(101, obtainAndWriteInterfaceToken);
    }

    public final void zze(zzt zzt, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzt);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) googleSignInOptions);
        transactAndReadExceptionReturnVoid(102, obtainAndWriteInterfaceToken);
    }

    public final void zzf(zzt zzt, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzt);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) googleSignInOptions);
        transactAndReadExceptionReturnVoid(103, obtainAndWriteInterfaceToken);
    }
}
