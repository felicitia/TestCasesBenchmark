package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzi;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Promotion {
    private Map<String, String> zzux = new HashMap();

    public String toString() {
        return zzi.zza((Map) this.zzux);
    }

    public final Map<String, String> zzn(String str) {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzux.entrySet()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf((String) entry.getKey());
            hashMap.put(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), (String) entry.getValue());
        }
        return hashMap;
    }
}
