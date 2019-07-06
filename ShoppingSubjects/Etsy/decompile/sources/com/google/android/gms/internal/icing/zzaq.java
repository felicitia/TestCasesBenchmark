package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.GoogleNowAuthState;

public abstract class zzaq extends zzb implements zzap {
    public zzaq() {
        super("com.google.android.gms.search.internal.ISearchAuthCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((Status) y.a(parcel, Status.CREATOR), (GoogleNowAuthState) y.a(parcel, GoogleNowAuthState.CREATOR));
                break;
            case 2:
                zzb((Status) y.a(parcel, Status.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
