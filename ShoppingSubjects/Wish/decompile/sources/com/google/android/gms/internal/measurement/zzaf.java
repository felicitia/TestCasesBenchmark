package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import java.util.HashMap;

public final class zzaf extends zzi<zzaf> {
    public String zzur;
    public String zzus;
    public String zzut;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("network", this.zzur);
        hashMap.put("action", this.zzus);
        hashMap.put("target", this.zzut);
        return zza((Object) hashMap);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzaf zzaf = (zzaf) zzi;
        if (!TextUtils.isEmpty(this.zzur)) {
            zzaf.zzur = this.zzur;
        }
        if (!TextUtils.isEmpty(this.zzus)) {
            zzaf.zzus = this.zzus;
        }
        if (!TextUtils.isEmpty(this.zzut)) {
            zzaf.zzut = this.zzut;
        }
    }
}
