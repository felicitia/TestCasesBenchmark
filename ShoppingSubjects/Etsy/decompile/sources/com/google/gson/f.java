package com.google.gson;

import com.google.gson.internal.a;
import com.google.gson.internal.a.l;
import com.google.gson.internal.a.n;
import com.google.gson.internal.c;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: GsonBuilder */
public final class f {
    private c a = c.a;
    private LongSerializationPolicy b = LongSerializationPolicy.DEFAULT;
    private d c = FieldNamingPolicy.IDENTITY;
    private final Map<Type, g<?>> d = new HashMap();
    private final List<r> e = new ArrayList();
    private final List<r> f = new ArrayList();
    private boolean g = false;
    private String h;
    private int i = 2;
    private int j = 2;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;

    public f a(Type type, Object obj) {
        boolean z = obj instanceof p;
        a.a(z || (obj instanceof j) || (obj instanceof g) || (obj instanceof q));
        if (obj instanceof g) {
            this.d.put(type, (g) obj);
        }
        if (z || (obj instanceof j)) {
            this.e.add(l.a(com.google.gson.a.a.a(type), obj));
        }
        if (obj instanceof q) {
            this.e.add(n.a(com.google.gson.a.a.a(type), (q) obj));
        }
        return this;
    }

    public e a() {
        ArrayList arrayList = new ArrayList(this.e.size() + this.f.size() + 3);
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(this.f);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        a(this.h, this.i, this.j, arrayList);
        e eVar = new e(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.b, arrayList);
        return eVar;
    }

    private void a(String str, int i2, int i3, List<r> list) {
        a aVar;
        a aVar2;
        a aVar3;
        if (str != null && !"".equals(str.trim())) {
            a aVar4 = new a(Date.class, str);
            aVar = new a(Timestamp.class, str);
            aVar3 = new a(java.sql.Date.class, str);
            aVar2 = aVar4;
        } else if (i2 != 2 && i3 != 2) {
            aVar2 = new a(Date.class, i2, i3);
            a aVar5 = new a(Timestamp.class, i2, i3);
            a aVar6 = new a(java.sql.Date.class, i2, i3);
            aVar = aVar5;
            aVar3 = aVar6;
        } else {
            return;
        }
        list.add(n.a(Date.class, (q<TT>) aVar2));
        list.add(n.a(Timestamp.class, (q<TT>) aVar));
        list.add(n.a(java.sql.Date.class, (q<TT>) aVar3));
    }
}
