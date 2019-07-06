package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzrk extends zzej implements zzri {
    zzrk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnPublisherAdViewLoadedListener");
    }

    public final void zza(zzks zzks, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        aej.a(obtainAndWriteInterfaceToken, (IInterface) zzks);
        aej.a(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
