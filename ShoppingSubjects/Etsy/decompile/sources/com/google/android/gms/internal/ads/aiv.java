package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@bu
public final class aiv extends RemoteCreator<zzkv> {
    @VisibleForTesting
    public aiv() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public final zzks a(Context context, zzjn zzjn, String str, zzxn zzxn, int i) {
        try {
            IBinder zza = ((zzkv) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), zzjn, str, zzxn, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE, i);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            return queryLocalInterface instanceof zzks ? (zzks) queryLocalInterface : new zzku(zza);
        } catch (RemoteException | RemoteCreatorException e) {
            ka.a("Could not create remote AdManager.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        return queryLocalInterface instanceof zzkv ? (zzkv) queryLocalInterface : new zzkw(iBinder);
    }
}
