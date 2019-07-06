package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.ObjectWrapper;

final class aje extends a<zzqa> {
    private final /* synthetic */ FrameLayout a;
    private final /* synthetic */ FrameLayout b;
    private final /* synthetic */ Context c;
    private final /* synthetic */ aiz d;

    aje(aiz aiz, FrameLayout frameLayout, FrameLayout frameLayout2, Context context) {
        this.d = aiz;
        this.a = frameLayout;
        this.b = frameLayout2;
        this.c = context;
        super();
    }

    public final /* synthetic */ Object a() throws RemoteException {
        zzqa a2 = this.d.f.a(this.c, this.a, this.b);
        if (a2 != null) {
            return a2;
        }
        aiz.a(this.c, "native_ad_view_delegate");
        return new zzmm();
    }

    public final /* synthetic */ Object a(zzld zzld) throws RemoteException {
        return zzld.createNativeAdViewDelegate(ObjectWrapper.wrap(this.a), ObjectWrapper.wrap(this.b));
    }
}
