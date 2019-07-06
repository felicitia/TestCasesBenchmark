package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.ae;
import java.lang.ref.WeakReference;
import java.util.Map;

public final class alq implements ae<Object> {
    private final WeakReference<alm> a;
    private final String b;

    public alq(alm alm, String str) {
        this.a = new WeakReference<>(alm);
        this.b = str;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("ads_id");
        String str2 = (String) map.get("eventName");
        if (!TextUtils.isEmpty(str) && this.b.equals(str)) {
            try {
                Integer.parseInt((String) map.get("eventType"));
            } catch (Exception e) {
                gv.b("Parse Scion log event type error", e);
            }
            if ("_ai".equals(str2)) {
                alm alm = (alm) this.a.get();
                if (alm != null) {
                    alm.zzbr();
                }
                return;
            }
            if ("_ac".equals(str2)) {
                alm alm2 = (alm) this.a.get();
                if (alm2 != null) {
                    alm2.zzbs();
                }
            }
        }
    }
}
