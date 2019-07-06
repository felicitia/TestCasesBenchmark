package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.nn;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import java.util.Map;

final class s implements ae<nn> {
    private final /* synthetic */ zzxz a;
    private final /* synthetic */ c b;
    private final /* synthetic */ zzyc c;

    s(zzxz zzxz, c cVar, zzyc zzyc) {
        this.a = zzxz;
        this.b = cVar;
        this.c = zzyc;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        View view = nnVar.getView();
        if (view != null) {
            try {
                if (this.a == null) {
                    if (this.c != null) {
                        if (!this.c.getOverrideClickHandling()) {
                            this.c.zzj(ObjectWrapper.wrap(view));
                            this.b.a.onAdClicked();
                            return;
                        }
                        n.b(nnVar);
                    }
                } else if (!this.a.getOverrideClickHandling()) {
                    this.a.zzj(ObjectWrapper.wrap(view));
                    this.b.a.onAdClicked();
                } else {
                    n.b(nnVar);
                }
            } catch (RemoteException e) {
                gv.c("Unable to call handleClick on mapper", e);
            }
        }
    }
}
