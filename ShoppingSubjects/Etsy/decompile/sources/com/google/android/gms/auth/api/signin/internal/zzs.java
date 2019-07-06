package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zze;

public abstract class zzs extends zze implements zzr {
    public zzs() {
        super("com.google.android.gms.auth.api.signin.internal.IRevocationService");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzq();
                break;
            case 2:
                zzr();
                break;
            default:
                return false;
        }
        return true;
    }
}
