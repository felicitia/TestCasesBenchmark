package com.google.android.gms.internal.identity;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.identity.intents.UserAddressRequest;

public final class zzj extends zza implements zzi {
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.identity.intents.internal.IAddressService");
    }

    public final void zza(zzg zzg, UserAddressRequest userAddressRequest, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        a.a(obtainAndWriteInterfaceToken, (IInterface) zzg);
        a.a(obtainAndWriteInterfaceToken, (Parcelable) userAddressRequest);
        a.a(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
}
