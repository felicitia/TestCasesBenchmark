package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

public final class zzbl extends zzd implements zzbk {
    zzbl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.internal.IAuthService");
    }

    public final void zzd(zzbi zzbi, ProxyRequest proxyRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (IInterface) zzbi);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) proxyRequest);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
