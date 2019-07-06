package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import java.util.HashMap;

public final class zzac extends zzi<zzac> {
    public String zzua;
    public boolean zzub;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("description", this.zzua);
        hashMap.put("fatal", Boolean.valueOf(this.zzub));
        return zza((Object) hashMap);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzac zzac = (zzac) zzi;
        if (!TextUtils.isEmpty(this.zzua)) {
            zzac.zzua = this.zzua;
        }
        if (this.zzub) {
            zzac.zzub = this.zzub;
        }
    }
}
