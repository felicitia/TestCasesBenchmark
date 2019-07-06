package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@bu
public final class aiu extends RemoteCreator<zzkq> {
    public aiu() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public final zzkn a(Context context, String str, zzxn zzxn) {
        try {
            IBinder zza = ((zzkq) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), str, zzxn, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            return queryLocalInterface instanceof zzkn ? (zzkn) queryLocalInterface : new zzkp(zza);
        } catch (RemoteException | RemoteCreatorException e) {
            ka.c("Could not create remote builder for AdLoader.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        return queryLocalInterface instanceof zzkq ? (zzkq) queryLocalInterface : new zzkr(iBinder);
    }
}
