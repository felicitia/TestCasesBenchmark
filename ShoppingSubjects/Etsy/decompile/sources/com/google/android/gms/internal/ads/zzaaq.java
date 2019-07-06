package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

public abstract class zzaaq extends zzek implements zzaap {
    public zzaaq() {
        super("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }

    public static zzaap zzu(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        return queryLocalInterface instanceof zzaap ? (zzaap) queryLocalInterface : new zzaar(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                onCreate((Bundle) aej.a(parcel, Bundle.CREATOR));
                break;
            case 2:
                onRestart();
                break;
            case 3:
                onStart();
                break;
            case 4:
                onResume();
                break;
            case 5:
                onPause();
                break;
            case 6:
                Bundle bundle = (Bundle) aej.a(parcel, Bundle.CREATOR);
                onSaveInstanceState(bundle);
                parcel2.writeNoException();
                aej.b(parcel2, bundle);
                break;
            case 7:
                onStop();
                break;
            case 8:
                onDestroy();
                break;
            case 9:
                zzax();
                break;
            case 10:
                onBackPressed();
                break;
            case 11:
                boolean zznj = zznj();
                parcel2.writeNoException();
                aej.a(parcel2, zznj);
                break;
            case 12:
                onActivityResult(parcel.readInt(), parcel.readInt(), (Intent) aej.a(parcel, Intent.CREATOR));
                break;
            case 13:
                zzo(Stub.asInterface(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
