package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.a;
import com.google.android.gms.ads.formats.g;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzsb extends zzrj {
    /* access modifiers changed from: private */
    public final g zzblf;

    public zzsb(g gVar) {
        this.zzblf = gVar;
    }

    public final void zza(zzks zzks, IObjectWrapper iObjectWrapper) {
        if (zzks != null && iObjectWrapper != null) {
            PublisherAdView publisherAdView = new PublisherAdView((Context) ObjectWrapper.unwrap(iObjectWrapper));
            a aVar = null;
            try {
                if (zzks.zzbx() instanceof zzjf) {
                    zzjf zzjf = (zzjf) zzks.zzbx();
                    publisherAdView.setAdListener(zzjf != null ? zzjf.getAdListener() : null);
                }
            } catch (RemoteException e) {
                ka.b("", e);
            }
            try {
                if (zzks.zzbw() instanceof zzjp) {
                    zzjp zzjp = (zzjp) zzks.zzbw();
                    if (zzjp != null) {
                        aVar = zzjp.getAppEventListener();
                    }
                    publisherAdView.setAppEventListener(aVar);
                }
            } catch (RemoteException e2) {
                ka.b("", e2);
            }
            jp.a.post(new amk(this, publisherAdView, zzks));
        }
    }
}
