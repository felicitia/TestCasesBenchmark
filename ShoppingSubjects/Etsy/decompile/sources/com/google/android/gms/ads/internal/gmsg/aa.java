package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.nn;
import java.util.Map;

final class aa implements ae<nn> {
    aa() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        if (((Boolean) ajh.f().a(akl.bt)).booleanValue()) {
            nnVar.zzaj(!Boolean.parseBoolean((String) map.get("disabled")));
        }
    }
}
