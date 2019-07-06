package com.bumptech.glide.g;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/* compiled from: LruCache */
public class e<T, Y> {
    private final LinkedHashMap<T, Y> a = new LinkedHashMap<>(100, 0.75f, true);
    private int b;
    private final int c;
    private int d = 0;

    /* access modifiers changed from: protected */
    public int a(Y y) {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void a(T t, Y y) {
    }

    public e(int i) {
        this.c = i;
        this.b = i;
    }

    public int b() {
        return this.d;
    }

    public Y b(T t) {
        return this.a.get(t);
    }

    public Y b(T t, Y y) {
        if (a(y) >= this.b) {
            a(t, y);
            return null;
        }
        Y put = this.a.put(t, y);
        if (y != null) {
            this.d += a(y);
        }
        if (put != null) {
            this.d -= a(put);
        }
        c();
        return put;
    }

    public Y c(T t) {
        Y remove = this.a.remove(t);
        if (remove != null) {
            this.d -= a(remove);
        }
        return remove;
    }

    public void a() {
        b(0);
    }

    /* access modifiers changed from: protected */
    public void b(int i) {
        while (this.d > i) {
            Entry entry = (Entry) this.a.entrySet().iterator().next();
            Object value = entry.getValue();
            this.d -= a(value);
            Object key = entry.getKey();
            this.a.remove(key);
            a(key, value);
        }
    }

    private void c() {
        b(this.b);
    }
}
