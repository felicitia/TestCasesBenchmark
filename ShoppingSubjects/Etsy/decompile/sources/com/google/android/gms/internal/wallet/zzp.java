package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzp extends zzb implements zzo {
    public zzp() {
        super("com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        zza(parcel.readInt(), parcel.readInt(), (Bundle) i.a(parcel, Bundle.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
