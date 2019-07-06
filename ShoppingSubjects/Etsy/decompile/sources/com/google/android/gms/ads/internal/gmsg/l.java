package com.google.android.gms.ads.internal.gmsg;

import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import java.util.Map;

@bu
public final class l implements ae<Object> {
    private final m a;

    public l(m mVar) {
        this.a = mVar;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get(ResponseConstants.NAME);
        if (str == null) {
            gv.e("App event with no name parameter.");
        } else {
            this.a.onAppEvent(str, (String) map.get("info"));
        }
    }
}
