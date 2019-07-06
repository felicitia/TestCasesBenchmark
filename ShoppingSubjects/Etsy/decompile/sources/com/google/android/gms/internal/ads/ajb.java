package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;

final class ajb extends a<zzks> {
    private final /* synthetic */ Context a;
    private final /* synthetic */ zzjn b;
    private final /* synthetic */ String c;
    private final /* synthetic */ aiz d;

    ajb(aiz aiz, Context context, zzjn zzjn, String str) {
        this.d = aiz;
        this.a = context;
        this.b = zzjn;
        this.c = str;
        super();
    }

    public final /* synthetic */ Object a() throws RemoteException {
        zzks a2 = this.d.c.a(this.a, this.b, this.c, null, 3);
        if (a2 != null) {
            return a2;
        }
        aiz.a(this.a, "search");
        return new zzmj();
    }

    public final /* synthetic */ Object a(zzld zzld) throws RemoteException {
        return zzld.createSearchAdManager(ObjectWrapper.wrap(this.a), this.b, this.c, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }
}
