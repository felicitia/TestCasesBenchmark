package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.e;
import com.google.android.gms.internal.auth.zze;

public abstract class zzu extends zze implements zzt {
    public zzu() {
        super("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 101:
                zzd((GoogleSignInAccount) e.a(parcel, GoogleSignInAccount.CREATOR), (Status) e.a(parcel, Status.CREATOR));
                break;
            case 102:
                zzg((Status) e.a(parcel, Status.CREATOR));
                break;
            case 103:
                zzh((Status) e.a(parcel, Status.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
