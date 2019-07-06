package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;

final class ajc extends a<zzks> {
    private final /* synthetic */ Context a;
    private final /* synthetic */ zzjn b;
    private final /* synthetic */ String c;
    private final /* synthetic */ zzxn d;
    private final /* synthetic */ aiz e;

    ajc(aiz aiz, Context context, zzjn zzjn, String str, zzxn zzxn) {
        this.e = aiz;
        this.a = context;
        this.b = zzjn;
        this.c = str;
        this.d = zzxn;
        super();
    }

    public final /* synthetic */ Object a() throws RemoteException {
        zzks a2 = this.e.c.a(this.a, this.b, this.c, this.d, 2);
        if (a2 != null) {
            return a2;
        }
        aiz.a(this.a, "interstitial");
        return new zzmj();
    }

    public final /* synthetic */ Object a(zzld zzld) throws RemoteException {
        return zzld.createInterstitialAdManager(ObjectWrapper.wrap(this.a), this.b, this.c, this.d, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }
}
