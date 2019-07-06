package com.onfido.c.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.onfido.c.a.b.b;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class t implements Map<String, Object> {
    private final Map<String, Object> a;

    static class a<T extends t> {
        private final SharedPreferences a;
        private final d b;
        private final String c;
        private final Class<T> d;
        private T e;

        a(Context context, d dVar, String str, String str2, Class<T> cls) {
            this.b = dVar;
            this.a = b.d(context, str2);
            this.c = str;
            this.d = cls;
        }

        /* access modifiers changed from: 0000 */
        public T a() {
            if (this.e == null) {
                String string = this.a.getString(this.c, null);
                if (b.a((CharSequence) string)) {
                    return null;
                }
                try {
                    this.e = b(this.b.a(string));
                } catch (IOException unused) {
                    return null;
                }
            }
            return this.e;
        }

        /* access modifiers changed from: 0000 */
        public void a(T t) {
            this.e = t;
            this.a.edit().putString(this.c, this.b.a((Map<?, ?>) t)).apply();
        }

        /* access modifiers changed from: 0000 */
        public T b(Map<String, Object> map) {
            return t.a((Map) map, this.d);
        }

        /* access modifiers changed from: 0000 */
        public boolean b() {
            return this.a.contains(this.c);
        }
    }

    public t() {
        this.a = new LinkedHashMap();
    }

    public t(Map<String, Object> map) {
        if (map == null) {
            throw new IllegalArgumentException("Map must not be null.");
        }
        this.a = map;
    }

    private <T extends t> T a(Object obj, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        if (cls.isAssignableFrom(obj.getClass())) {
            return (t) obj;
        }
        if (obj instanceof Map) {
            return a((Map) obj, cls);
        }
        return null;
    }

    static <T extends t> T a(Map map, Class<T> cls) {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{Map.class});
            declaredConstructor.setAccessible(true);
            return (t) declaredConstructor.newInstance(new Object[]{map});
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not create instance of ");
            sb.append(cls.getCanonicalName());
            sb.append(".\n");
            sb.append(e);
            throw new AssertionError(sb.toString());
        }
    }

    public long a(String str, long j) {
        Object obj = get(str);
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf((String) obj).longValue();
            } catch (NumberFormatException unused) {
            }
        }
        return j;
    }

    public t a(Object obj) {
        Object obj2 = get(obj);
        if (obj2 instanceof t) {
            return (t) obj2;
        }
        if (obj2 instanceof Map) {
            return new t((Map) obj2);
        }
        return null;
    }

    public <T extends t> T a(String str, Class<T> cls) {
        return a(get(str), cls);
    }

    public <T extends Enum<T>> T a(Class<T> cls, String str) {
        if (cls == null) {
            throw new IllegalArgumentException("enumType may not be null");
        }
        T t = get(str);
        if (cls.isInstance(t)) {
            return (Enum) t;
        }
        if (t instanceof String) {
            return Enum.valueOf(cls, (String) t);
        }
        return null;
    }

    public t b(String str, Object obj) {
        this.a.put(str, obj);
        return this;
    }

    public String b(String str) {
        Object obj = get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    public boolean b(String str, boolean z) {
        Boolean valueOf;
        Object obj = get(str);
        if (obj instanceof Boolean) {
            valueOf = (Boolean) obj;
        } else if (!(obj instanceof String)) {
            return z;
        } else {
            valueOf = Boolean.valueOf((String) obj);
        }
        return valueOf.booleanValue();
    }

    /* renamed from: c */
    public Object put(String str, Object obj) {
        return this.a.put(str, obj);
    }

    public void clear() {
        this.a.clear();
    }

    public boolean containsKey(Object obj) {
        return this.a.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.a.containsValue(obj);
    }

    public Set<Entry<String, Object>> entrySet() {
        return this.a.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || this.a.equals(obj);
    }

    public Object get(Object obj) {
        return this.a.get(obj);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    public Set<String> keySet() {
        return this.a.keySet();
    }

    public void putAll(Map<? extends String, ?> map) {
        this.a.putAll(map);
    }

    public Object remove(Object obj) {
        return this.a.remove(obj);
    }

    public int size() {
        return this.a.size();
    }

    public String toString() {
        return this.a.toString();
    }

    public Collection<Object> values() {
        return this.a.values();
    }
}
