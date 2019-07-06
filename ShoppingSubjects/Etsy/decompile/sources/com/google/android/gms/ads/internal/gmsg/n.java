package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.internal.ads.aky;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.nn;
import java.util.Map;

@bu
public final class n implements ae<nn> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        String str = (String) map.get(ResponseConstants.ACTION);
        if ("tick".equals(str)) {
            String str2 = (String) map.get(ResponseConstants.LABEL);
            String str3 = (String) map.get("start_label");
            String str4 = (String) map.get("timestamp");
            if (TextUtils.isEmpty(str2)) {
                gv.e("No label given for CSI tick.");
            } else if (TextUtils.isEmpty(str4)) {
                gv.e("No timestamp given for CSI tick.");
            } else {
                try {
                    long elapsedRealtime = ao.l().elapsedRealtime() + (Long.parseLong(str4) - ao.l().currentTimeMillis());
                    if (TextUtils.isEmpty(str3)) {
                        str3 = "native:view_load";
                    }
                    nnVar.zztp().a(str2, str3, elapsedRealtime);
                } catch (NumberFormatException e) {
                    gv.c("Malformed timestamp for CSI tick.", e);
                }
            }
        } else if ("experiment".equals(str)) {
            String str5 = (String) map.get(ResponseConstants.VALUE);
            if (TextUtils.isEmpty(str5)) {
                gv.e("No value given for CSI experiment.");
                return;
            }
            aky a = nnVar.zztp().a();
            if (a == null) {
                gv.e("No ticker for WebView, dropping experiment ID.");
            } else {
                a.a("e", str5);
            }
        } else {
            if ("extra".equals(str)) {
                String str6 = (String) map.get(ResponseConstants.NAME);
                String str7 = (String) map.get(ResponseConstants.VALUE);
                if (TextUtils.isEmpty(str7)) {
                    gv.e("No value given for CSI extra.");
                } else if (TextUtils.isEmpty(str6)) {
                    gv.e("No name given for CSI extra.");
                } else {
                    aky a2 = nnVar.zztp().a();
                    if (a2 == null) {
                        gv.e("No ticker for WebView, dropping extra parameter.");
                        return;
                    }
                    a2.a(str6, str7);
                }
            }
        }
    }
}
