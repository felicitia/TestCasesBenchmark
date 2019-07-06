package com.google.firebase.appindexing.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.internal.icing.y;
import com.google.android.gms.internal.icing.zza;

public final class zzt extends zza implements zzs {
    zzt(IBinder iBinder) {
        super(iBinder, "com.google.firebase.appindexing.internal.IAppIndexingService");
    }

    public final zzf zza(IStatusCallback iStatusCallback, zzx zzx) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        y.a(obtainAndWriteInterfaceToken, (IInterface) iStatusCallback);
        y.a(obtainAndWriteInterfaceToken, (Parcelable) zzx);
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken);
        zzf zzf = (zzf) y.a(transactAndReadException, zzf.CREATOR);
        transactAndReadException.recycle();
        return zzf;
    }
}
