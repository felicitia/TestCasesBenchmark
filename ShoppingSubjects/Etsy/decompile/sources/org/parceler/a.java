package org.parceler;

import java.util.ArrayList;
import java.util.List;

/* compiled from: IdentityCollection */
public final class a {
    private static final Object a = new Object();
    private final List<Object> b = new ArrayList();

    public a() {
        a((Object) null);
    }

    public boolean a(int i) {
        return i < this.b.size();
    }

    public int a() {
        return a(a);
    }

    public boolean b(int i) {
        return this.b.get(i) == a;
    }

    public void a(int i, Object obj) {
        this.b.remove(i);
        this.b.add(i, obj);
    }

    public int a(Object obj) {
        this.b.add(obj);
        return this.b.size() - 1;
    }

    public <T> T c(int i) {
        return this.b.get(i);
    }

    public int b(Object obj) {
        for (int i = 0; i < this.b.size(); i++) {
            if (this.b.get(i) == obj) {
                return i;
            }
        }
        return -1;
    }
}
