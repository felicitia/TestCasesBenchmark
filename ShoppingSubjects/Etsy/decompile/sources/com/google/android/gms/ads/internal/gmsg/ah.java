package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import java.util.Map;

@bu
public final class ah implements ae<Object> {
    private final ai a;

    public ah(ai aiVar) {
        this.a = aiVar;
    }

    public final void zza(Object obj, Map<String, String> map) {
        boolean equals = "1".equals(map.get("transparentBackground"));
        boolean equals2 = "1".equals(map.get("blur"));
        float f = 0.0f;
        try {
            if (map.get("blurRadius") != null) {
                f = Float.parseFloat((String) map.get("blurRadius"));
            }
        } catch (NumberFormatException e) {
            gv.b("Fail to parse float", e);
        }
        this.a.zzd(equals);
        this.a.zza(equals2, f);
    }
}
