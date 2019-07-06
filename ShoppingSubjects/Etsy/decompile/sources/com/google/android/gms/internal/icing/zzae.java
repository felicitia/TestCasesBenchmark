package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzae extends zzb implements zzad {
    public zzae() {
        super("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((Status) y.a(parcel, Status.CREATOR));
                break;
            case 2:
                zza((Status) y.a(parcel, Status.CREATOR), (ParcelFileDescriptor) y.a(parcel, ParcelFileDescriptor.CREATOR));
                break;
            case 3:
                zza((Status) y.a(parcel, Status.CREATOR), y.a(parcel));
                break;
            case 4:
                zza((zzp) y.a(parcel, zzp.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
