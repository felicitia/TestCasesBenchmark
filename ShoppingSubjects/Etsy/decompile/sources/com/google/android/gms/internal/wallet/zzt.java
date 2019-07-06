package com.google.android.gms.internal.wallet;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public final class zzt extends zza implements zzs {
    zzt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
    }

    public final zzl zza(IObjectWrapper iObjectWrapper, IFragmentWrapper iFragmentWrapper, WalletFragmentOptions walletFragmentOptions, zzo zzo) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        i.a(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        i.a(obtainAndWriteInterfaceToken, (IInterface) iFragmentWrapper);
        i.a(obtainAndWriteInterfaceToken, (Parcelable) walletFragmentOptions);
        i.a(obtainAndWriteInterfaceToken, (IInterface) zzo);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzl zza = zzm.zza(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zza;
    }
}
