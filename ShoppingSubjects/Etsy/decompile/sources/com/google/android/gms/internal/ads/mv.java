package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

@bu
public final class mv implements ae<mo> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        mo moVar = (mo) obj;
        if (((Boolean) ajh.f().a(akl.bu)).booleanValue()) {
            zzarl zztm = moVar.zztm();
            if (zztm == null) {
                try {
                    zzarl zzarl = new zzarl(moVar, Float.parseFloat((String) map.get("duration")), "1".equals(map.get("customControlsAllowed")), "1".equals(map.get("clickToExpandAllowed")));
                    moVar.zza(zzarl);
                    zztm = zzarl;
                } catch (NullPointerException | NumberFormatException e) {
                    gv.b("Unable to parse videoMeta message.", e);
                    ao.i().a(e, "VideoMetaGmsgHandler.onGmsg");
                }
            }
            boolean equals = "1".equals(map.get("muted"));
            float parseFloat = Float.parseFloat((String) map.get("currentTime"));
            int parseInt = Integer.parseInt((String) map.get("playbackState"));
            if (parseInt < 0 || 3 < parseInt) {
                parseInt = 0;
            }
            String str = (String) map.get("aspectRatio");
            float parseFloat2 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
            if (gv.a(3)) {
                StringBuilder sb = new StringBuilder(79 + String.valueOf(str).length());
                sb.append("Video Meta GMSG: isMuted : ");
                sb.append(equals);
                sb.append(" , playbackState : ");
                sb.append(parseInt);
                sb.append(" , aspectRatio : ");
                sb.append(str);
                gv.b(sb.toString());
            }
            zztm.zza(parseFloat, parseInt, equals, parseFloat2);
        }
    }
}
