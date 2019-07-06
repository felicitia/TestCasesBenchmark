package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.a.c;
import com.google.firebase.a.d;
import com.google.firebase.b.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public final class i implements b {
    private final List<a<?>> a;
    private final Map<Class<?>, m<?>> b = new HashMap();
    private final k c;

    public final Object a(Class cls) {
        return c.a(this, cls);
    }

    public i(Executor executor, Iterable<e> iterable, a<?>... aVarArr) {
        this.c = new k(executor);
        ArrayList arrayList = new ArrayList();
        arrayList.add(a.a(this.c, k.class, d.class, c.class));
        for (e components : iterable) {
            arrayList.addAll(components.getComponents());
        }
        Collections.addAll(arrayList, aVarArr);
        this.a = Collections.unmodifiableList(j.a((List<a<?>>) arrayList));
        for (a a2 : this.a) {
            a(a2);
        }
        a();
    }

    public final <T> a<T> b(Class<T> cls) {
        Preconditions.checkNotNull(cls, "Null interface requested.");
        return (a) this.b.get(cls);
    }

    public final void a(boolean z) {
        for (a aVar : this.a) {
            if (aVar.e() || (aVar.f() && z)) {
                a((Class) aVar.a().iterator().next());
            }
        }
        this.c.a();
    }

    private <T> void a(a<T> aVar) {
        m mVar = new m(aVar.c(), new o(aVar, this));
        for (Class put : aVar.a()) {
            this.b.put(put, mVar);
        }
    }

    private void a() {
        for (a aVar : this.a) {
            Iterator it = aVar.b().iterator();
            while (true) {
                if (it.hasNext()) {
                    f fVar = (f) it.next();
                    if (fVar.b() && !this.b.containsKey(fVar.a())) {
                        throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", new Object[]{aVar, fVar.a()}));
                    }
                }
            }
        }
    }
}
