package com.salesforce.marketingcloud.registration;

import android.support.annotation.NonNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

class e implements Map<String, String> {
    private final HashMap<String, String> a;
    private final TreeMap<String, String> b;

    e() {
        this.a = new HashMap<>();
        this.b = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    e(Map<String, String> map) {
        this();
        if (map != null) {
            this.a.putAll(map);
            for (String str : this.a.keySet()) {
                this.b.put(str, str);
            }
        }
    }

    /* renamed from: a */
    public String get(Object obj) {
        String str = (String) this.b.get(obj);
        if (str != null) {
            return (String) this.a.get(str);
        }
        return null;
    }

    /* renamed from: a */
    public String put(String str, String str2) {
        String str3 = null;
        if (str == null) {
            return null;
        }
        String str4 = (String) this.b.get(str);
        if (str4 != null) {
            str3 = (String) this.a.remove(str4);
        }
        this.b.put(str, str);
        this.a.put(str, str2);
        return str3;
    }

    /* renamed from: b */
    public String remove(Object obj) {
        return (String) this.a.remove((String) this.b.remove(obj));
    }

    public void clear() {
        this.a.clear();
        this.b.clear();
    }

    public boolean containsKey(Object obj) {
        return this.b.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.a.containsValue(obj);
    }

    @NonNull
    public Set<Entry<String, String>> entrySet() {
        return this.a.entrySet();
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    @NonNull
    public Set<String> keySet() {
        return this.a.keySet();
    }

    public void putAll(@NonNull Map<? extends String, ? extends String> map) {
        if (map != null && !map.isEmpty()) {
            for (Entry entry : map.entrySet()) {
                put((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public int size() {
        return this.a.size();
    }

    @NonNull
    public Collection<String> values() {
        return this.a.values();
    }
}
