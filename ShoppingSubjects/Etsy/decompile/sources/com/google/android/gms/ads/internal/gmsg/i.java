package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.zzaig;
import java.util.Map;

@bu
public final class i implements ae<Object> {
    private final j a;

    public i(j jVar) {
        this.a = jVar;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get(ResponseConstants.ACTION);
        if ("grant".equals(str)) {
            zzaig zzaig = null;
            try {
                int parseInt = Integer.parseInt((String) map.get(ResponseConstants.AMOUNT));
                String str2 = (String) map.get("type");
                if (!TextUtils.isEmpty(str2)) {
                    zzaig = new zzaig(str2, parseInt);
                }
            } catch (NumberFormatException e) {
                gv.c("Unable to parse reward amount.", e);
            }
            this.a.zzb(zzaig);
        } else if ("video_start".equals(str)) {
            this.a.zzdk();
        } else {
            if ("video_complete".equals(str)) {
                if (((Boolean) ajh.f().a(akl.ax)).booleanValue()) {
                    if (((Boolean) ajh.f().a(akl.ax)).booleanValue()) {
                        this.a.zzdl();
                    }
                }
            }
        }
    }
}
