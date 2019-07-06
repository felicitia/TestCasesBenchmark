package com.google.android.gms.ads.internal.gmsg;

import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.internal.ads.nn;
import java.util.Map;

final class ab implements ae<nn> {
    ab() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        String str = (String) map.get(ResponseConstants.ACTION);
        if ("pause".equals(str)) {
            nnVar.zzcl();
            return;
        }
        if ("resume".equals(str)) {
            nnVar.zzcm();
        }
    }
}
