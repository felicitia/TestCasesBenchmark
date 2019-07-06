package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

final class alv implements ae<Object> {
    private final /* synthetic */ az a;
    private final /* synthetic */ alr b;

    alv(alr alr, az azVar) {
        this.b = alr;
        this.a = azVar;
    }

    public final void zza(Object obj, Map<String, String> map) {
        nn nnVar = (nn) this.b.a.get();
        if (nnVar == null) {
            this.a.b("/hideOverlay", this);
        } else {
            nnVar.getView().setVisibility(8);
        }
    }
}
