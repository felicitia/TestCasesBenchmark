package com.google.android.gms.internal.icing;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.firebase.appindexing.internal.zza;

public final class zzac extends zza implements zzab {
    zzac(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
    }

    public final void zza(zzad zzad, String str, zzx[] zzxArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        y.a(obtainAndWriteInterfaceToken, (IInterface) zzad);
        obtainAndWriteInterfaceToken.writeString(null);
        obtainAndWriteInterfaceToken.writeTypedArray(zzxArr, 0);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzad zzad, zza[] zzaArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        y.a(obtainAndWriteInterfaceToken, (IInterface) zzad);
        obtainAndWriteInterfaceToken.writeTypedArray(zzaArr, 0);
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }
}
