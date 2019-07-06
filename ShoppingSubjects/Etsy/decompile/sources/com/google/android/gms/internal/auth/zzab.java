package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzo;
import com.google.android.gms.auth.api.accounttransfer.zzw;
import com.google.android.gms.common.api.Status;

public abstract class zzab extends zze implements zzaa {
    public zzab() {
        super("com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zze((Status) e.a(parcel, Status.CREATOR));
                break;
            case 2:
                zzd((Status) e.a(parcel, Status.CREATOR), (zzw) e.a(parcel, zzw.CREATOR));
                break;
            case 3:
                zzd((Status) e.a(parcel, Status.CREATOR), (zzo) e.a(parcel, zzo.CREATOR));
                break;
            case 4:
                zzi();
                break;
            case 5:
                onFailure((Status) e.a(parcel, Status.CREATOR));
                break;
            case 6:
                zzd(parcel.createByteArray());
                break;
            case 7:
                zzd((DeviceMetaData) e.a(parcel, DeviceMetaData.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
