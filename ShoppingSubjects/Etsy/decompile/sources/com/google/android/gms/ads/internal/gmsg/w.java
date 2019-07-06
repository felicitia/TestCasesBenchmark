package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.nn;
import java.util.Map;

final class w implements ae<nn> {
    w() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        zzd zzub = nnVar.zzub();
        if (zzub != null) {
            zzub.close();
            return;
        }
        zzd zzuc = nnVar.zzuc();
        if (zzuc != null) {
            zzuc.close();
        } else {
            gv.e("A GMSG tried to close something that wasn't an overlay.");
        }
    }
}
