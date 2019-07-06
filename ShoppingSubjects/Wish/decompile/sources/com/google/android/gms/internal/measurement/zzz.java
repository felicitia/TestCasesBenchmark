package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import java.util.HashMap;

public final class zzz extends zzi<zzz> {
    private String zztt;
    public int zztu;
    public int zztv;
    public int zztw;
    public int zztx;
    public int zzty;

    public final String getLanguage() {
        return this.zztt;
    }

    public final void setLanguage(String str) {
        this.zztt = str;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("language", this.zztt);
        hashMap.put("screenColors", Integer.valueOf(this.zztu));
        hashMap.put("screenWidth", Integer.valueOf(this.zztv));
        hashMap.put("screenHeight", Integer.valueOf(this.zztw));
        hashMap.put("viewportWidth", Integer.valueOf(this.zztx));
        hashMap.put("viewportHeight", Integer.valueOf(this.zzty));
        return zza((Object) hashMap);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzz zzz = (zzz) zzi;
        if (this.zztu != 0) {
            zzz.zztu = this.zztu;
        }
        if (this.zztv != 0) {
            zzz.zztv = this.zztv;
        }
        if (this.zztw != 0) {
            zzz.zztw = this.zztw;
        }
        if (this.zztx != 0) {
            zzz.zztx = this.zztx;
        }
        if (this.zzty != 0) {
            zzz.zzty = this.zzty;
        }
        if (!TextUtils.isEmpty(this.zztt)) {
            zzz.zztt = this.zztt;
        }
    }
}
