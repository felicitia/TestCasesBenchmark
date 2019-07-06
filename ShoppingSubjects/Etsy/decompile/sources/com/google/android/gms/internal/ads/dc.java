package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

public final class dc implements ae<Object> {
    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("request_id");
        String str2 = "Invalid request: ";
        String valueOf = String.valueOf((String) map.get("errors"));
        gv.e(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        cu.f.b(str);
    }
}
