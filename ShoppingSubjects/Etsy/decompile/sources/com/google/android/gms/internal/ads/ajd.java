package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;

final class ajd extends a<zzkn> {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;
    private final /* synthetic */ zzxn c;
    private final /* synthetic */ aiz d;

    ajd(aiz aiz, Context context, String str, zzxn zzxn) {
        this.d = aiz;
        this.a = context;
        this.b = str;
        this.c = zzxn;
        super();
    }

    public final /* synthetic */ Object a() throws RemoteException {
        zzkn a2 = this.d.d.a(this.a, this.b, this.c);
        if (a2 != null) {
            return a2;
        }
        aiz.a(this.a, "native_ad");
        return new zzmf();
    }

    public final /* synthetic */ Object a(zzld zzld) throws RemoteException {
        return zzld.createAdLoaderBuilder(ObjectWrapper.wrap(this.a), this.b, this.c, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }
}
