package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzi;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ProductAction {
    private Map<String, String> zzux;

    public final Map<String, String> build() {
        return new HashMap(this.zzux);
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzux.entrySet()) {
            hashMap.put(((String) entry.getKey()).startsWith("&") ? ((String) entry.getKey()).substring(1) : (String) entry.getKey(), (String) entry.getValue());
        }
        return zzi.zza((Map) hashMap);
    }
}
