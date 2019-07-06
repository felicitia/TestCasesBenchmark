package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.zzar;

public abstract class zzv extends zzb implements zzu {
    public zzv() {
        super("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza(parcel.readInt(), (MaskedWallet) i.a(parcel, MaskedWallet.CREATOR), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 2:
                zza(parcel.readInt(), (FullWallet) i.a(parcel, FullWallet.CREATOR), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 3:
                zza(parcel.readInt(), i.a(parcel), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 4:
                zza(parcel.readInt(), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 6:
                zzb(parcel.readInt(), i.a(parcel), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 7:
                zza((Status) i.a(parcel, Status.CREATOR), (zzf) i.a(parcel, zzf.CREATOR), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 8:
                zza((Status) i.a(parcel, Status.CREATOR), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 9:
                zza((Status) i.a(parcel, Status.CREATOR), i.a(parcel), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 10:
                zza((Status) i.a(parcel, Status.CREATOR), (zzh) i.a(parcel, zzh.CREATOR), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 11:
                zzb((Status) i.a(parcel, Status.CREATOR), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 12:
                zza((Status) i.a(parcel, Status.CREATOR), (zzar) i.a(parcel, zzar.CREATOR), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 13:
                zzc((Status) i.a(parcel, Status.CREATOR), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 14:
                zza((Status) i.a(parcel, Status.CREATOR), (PaymentData) i.a(parcel, PaymentData.CREATOR), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            case 15:
                zza((Status) i.a(parcel, Status.CREATOR), (zzj) i.a(parcel, zzj.CREATOR), (Bundle) i.a(parcel, Bundle.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
