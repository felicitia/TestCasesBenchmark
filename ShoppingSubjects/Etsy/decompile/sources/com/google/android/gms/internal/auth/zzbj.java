package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

public abstract class zzbj extends zze implements zzbi {
    public zzbj() {
        super("com.google.android.gms.auth.api.internal.IAuthCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzd((ProxyResponse) e.a(parcel, ProxyResponse.CREATOR));
                break;
            case 2:
                zzf(parcel.readString());
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
