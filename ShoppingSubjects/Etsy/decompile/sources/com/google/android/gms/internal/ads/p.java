package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@bu
public final class p extends RemoteCreator<zzaas> {
    public p() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    public final zzaap a(Activity activity) {
        try {
            IBinder zzp = ((zzaas) getRemoteCreatorInstance(activity)).zzp(ObjectWrapper.wrap(activity));
            if (zzp == null) {
                return null;
            }
            IInterface queryLocalInterface = zzp.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
            return queryLocalInterface instanceof zzaap ? (zzaap) queryLocalInterface : new zzaar(zzp);
        } catch (RemoteException | RemoteCreatorException e) {
            ka.c("Could not create remote AdOverlay.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
        return queryLocalInterface instanceof zzaas ? (zzaas) queryLocalInterface : new zzaat(iBinder);
    }
}
