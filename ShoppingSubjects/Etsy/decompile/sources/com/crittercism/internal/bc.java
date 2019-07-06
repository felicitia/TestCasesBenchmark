package com.crittercism.internal;

import com.crittercism.internal.ay.a;
import com.crittercism.internal.bi;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import org.json.JSONArray;

public final class bc<T extends bi> implements ay<T> {
    private final SortedMap<String, T> a = new TreeMap();
    private final List<a> b = new LinkedList();
    private int c;

    public bc(int i) {
        this.c = i;
    }

    public final synchronized boolean a(T t) {
        if (this.a.size() >= this.c) {
            this.a.remove(this.a.firstKey());
        }
        this.a.put(t.f(), t);
        synchronized (this.b) {
            for (a a2 : this.b) {
                a2.a();
            }
        }
        return true;
    }

    public final void a(String str) {
        this.a.remove(str);
    }

    public final JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        for (bi g : b()) {
            jSONArray.put(g.g());
        }
        return jSONArray;
    }

    public final List<T> b() {
        return new LinkedList(this.a.values());
    }

    public final List<T> d() {
        return b();
    }

    public final boolean c() {
        return this.a.size() > 0;
    }

    public final void a(List<T> list) {
        for (T f : list) {
            this.a.remove(f.f());
        }
    }

    public final void a(a aVar) {
        synchronized (this.b) {
            this.b.add(aVar);
        }
    }
}
