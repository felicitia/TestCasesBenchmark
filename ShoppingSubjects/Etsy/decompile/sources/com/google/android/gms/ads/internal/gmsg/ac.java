package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.nn;
import java.util.Map;

final class ac implements ae<nn> {
    ac() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        if (map.keySet().contains("start")) {
            nnVar.zzuf().zzva();
        } else if (map.keySet().contains("stop")) {
            nnVar.zzuf().zzvb();
        } else {
            if (map.keySet().contains("cancel")) {
                nnVar.zzuf().zzvc();
            }
        }
    }
}
