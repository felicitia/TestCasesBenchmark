package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import java.util.HashMap;

public final class zzag extends zzi<zzag> {
    public String mCategory;
    public String zzuu;
    public long zzuv;
    public String zzuw;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("variableName", this.zzuu);
        hashMap.put("timeInMillis", Long.valueOf(this.zzuv));
        hashMap.put("category", this.mCategory);
        hashMap.put("label", this.zzuw);
        return zza((Object) hashMap);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzag zzag = (zzag) zzi;
        if (!TextUtils.isEmpty(this.zzuu)) {
            zzag.zzuu = this.zzuu;
        }
        if (this.zzuv != 0) {
            zzag.zzuv = this.zzuv;
        }
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzag.mCategory = this.mCategory;
        }
        if (!TextUtils.isEmpty(this.zzuw)) {
            zzag.zzuw = this.zzuw;
        }
    }
}
