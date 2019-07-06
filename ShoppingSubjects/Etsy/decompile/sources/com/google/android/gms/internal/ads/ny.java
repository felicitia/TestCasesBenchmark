package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

final class ny implements ae<nn> {
    private final /* synthetic */ nx a;

    ny(nx nxVar) {
        this.a = nxVar;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        if (map != null) {
            String str = (String) map.get(ResponseConstants.HEIGHT);
            if (!TextUtils.isEmpty(str)) {
                try {
                    int parseInt = Integer.parseInt(str);
                    synchronized (this.a) {
                        if (this.a.B != parseInt) {
                            this.a.B = parseInt;
                            this.a.requestLayout();
                        }
                    }
                } catch (Exception e) {
                    gv.c("Exception occurred while getting webview content height", e);
                }
            }
        }
    }
}
