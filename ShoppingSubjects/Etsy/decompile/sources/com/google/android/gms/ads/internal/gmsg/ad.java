package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.nn;
import java.util.Map;

final class ad implements ae<nn> {
    ad() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        if (map.keySet().contains("start")) {
            nnVar.zzak(true);
        }
        if (map.keySet().contains("stop")) {
            nnVar.zzak(false);
        }
    }
}
