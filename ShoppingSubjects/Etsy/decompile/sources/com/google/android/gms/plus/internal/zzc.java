package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.server.response.SafeParcelResponse;
import com.google.android.gms.internal.plus.a;
import com.google.android.gms.internal.plus.zzb;
import com.google.android.gms.internal.plus.zzr;

public abstract class zzc extends zzb implements zzb {
    public zzc() {
        super("com.google.android.gms.plus.internal.IPlusCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza(parcel.readInt(), (Bundle) a.a(parcel, Bundle.CREATOR), (Bundle) a.a(parcel, Bundle.CREATOR));
                break;
            case 2:
                zza(parcel.readInt(), (Bundle) a.a(parcel, Bundle.CREATOR), (ParcelFileDescriptor) a.a(parcel, ParcelFileDescriptor.CREATOR));
                break;
            case 3:
                zzb(parcel.readString());
                break;
            case 4:
                zza((DataHolder) a.a(parcel, DataHolder.CREATOR), parcel.readString());
                break;
            case 5:
                zza(parcel.readInt(), (Bundle) a.a(parcel, Bundle.CREATOR), (SafeParcelResponse) a.a(parcel, SafeParcelResponse.CREATOR));
                break;
            case 6:
                zza((DataHolder) a.a(parcel, DataHolder.CREATOR), parcel.readString(), parcel.readString());
                break;
            case 7:
                zza(parcel.readInt(), (Bundle) a.a(parcel, Bundle.CREATOR));
                break;
            case 8:
                zzc(parcel.readString());
                break;
            case 9:
                zza(parcel.readInt(), (zzr) a.a(parcel, zzr.CREATOR));
                break;
            case 10:
                zza((Status) a.a(parcel, Status.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
