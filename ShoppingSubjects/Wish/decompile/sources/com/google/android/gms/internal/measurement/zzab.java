package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import java.util.HashMap;

public final class zzab extends zzi<zzab> {
    private String category;
    private String label;
    private long value;
    private String zztz;

    public final String getAction() {
        return this.zztz;
    }

    public final String getLabel() {
        return this.label;
    }

    public final long getValue() {
        return this.value;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("category", this.category);
        hashMap.put("action", this.zztz);
        hashMap.put("label", this.label);
        hashMap.put("value", Long.valueOf(this.value));
        return zza((Object) hashMap);
    }

    public final String zzax() {
        return this.category;
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzab zzab = (zzab) zzi;
        if (!TextUtils.isEmpty(this.category)) {
            zzab.category = this.category;
        }
        if (!TextUtils.isEmpty(this.zztz)) {
            zzab.zztz = this.zztz;
        }
        if (!TextUtils.isEmpty(this.label)) {
            zzab.label = this.label;
        }
        if (this.value != 0) {
            zzab.value = this.value;
        }
    }
}
