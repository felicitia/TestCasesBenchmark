package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.gv;
import java.util.Map;

final class y implements ae<Object> {
    y() {
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = "Received log message: ";
        String valueOf = String.valueOf((String) map.get("string"));
        gv.d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
