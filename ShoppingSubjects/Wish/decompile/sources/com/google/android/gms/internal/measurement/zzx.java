package com.google.android.gms.internal.measurement;

import com.google.android.gms.analytics.zzi;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class zzx extends zzi<zzx> {
    private Map<Integer, Double> zzts = new HashMap(4);

    public final String toString() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzts.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 6);
            sb.append("metric");
            sb.append(valueOf);
            hashMap.put(sb.toString(), entry.getValue());
        }
        return zza((Object) hashMap);
    }

    public final Map<Integer, Double> zzar() {
        return Collections.unmodifiableMap(this.zzts);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        ((zzx) zzi).zzts.putAll(this.zzts);
    }
}
