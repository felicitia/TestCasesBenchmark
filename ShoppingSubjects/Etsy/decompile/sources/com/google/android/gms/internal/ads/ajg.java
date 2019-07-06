package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class ajg extends a<zzaap> {
    private final /* synthetic */ Activity a;
    private final /* synthetic */ aiz b;

    ajg(aiz aiz, Activity activity) {
        this.b = aiz;
        this.a = activity;
        super();
    }

    public final /* synthetic */ Object a() throws RemoteException {
        zzaap a2 = this.b.h.a(this.a);
        if (a2 != null) {
            return a2;
        }
        aiz.a(this.a, "ad_overlay");
        return null;
    }

    public final /* synthetic */ Object a(zzld zzld) throws RemoteException {
        return zzld.createAdOverlay(ObjectWrapper.wrap(this.a));
    }
}
