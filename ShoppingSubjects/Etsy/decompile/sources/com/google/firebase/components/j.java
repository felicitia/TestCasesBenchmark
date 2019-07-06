package com.google.firebase.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

final class j {

    static class a {
        private final a<?> a;
        private final Set<a> b = new HashSet();
        private final Set<a> c = new HashSet();

        a(a<?> aVar) {
            this.a = aVar;
        }

        /* access modifiers changed from: 0000 */
        public final void a(a aVar) {
            this.b.add(aVar);
        }

        /* access modifiers changed from: 0000 */
        public final void b(a aVar) {
            this.c.add(aVar);
        }

        /* access modifiers changed from: 0000 */
        public final Set<a> a() {
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public final void c(a aVar) {
            this.c.remove(aVar);
        }

        /* access modifiers changed from: 0000 */
        public final a<?> b() {
            return this.a;
        }

        /* access modifiers changed from: 0000 */
        public final boolean c() {
            return this.c.isEmpty();
        }

        /* access modifiers changed from: 0000 */
        public final boolean d() {
            return this.b.isEmpty();
        }
    }

    private static Set<a> a(Set<a> set) {
        HashSet hashSet = new HashSet();
        for (a aVar : set) {
            if (aVar.c()) {
                hashSet.add(aVar);
            }
        }
        return hashSet;
    }

    static List<a<?>> a(List<a<?>> list) {
        HashMap hashMap = new HashMap(list.size());
        for (a aVar : list) {
            a aVar2 = new a(aVar);
            Iterator it = aVar.a().iterator();
            while (true) {
                if (it.hasNext()) {
                    Class cls = (Class) it.next();
                    if (hashMap.put(cls, aVar2) != null) {
                        throw new IllegalArgumentException(String.format("Multiple components provide %s.", new Object[]{cls}));
                    }
                }
            }
        }
        for (a aVar3 : hashMap.values()) {
            for (f fVar : aVar3.b().b()) {
                if (fVar.c()) {
                    a aVar4 = (a) hashMap.get(fVar.a());
                    if (aVar4 != null) {
                        aVar3.a(aVar4);
                        aVar4.b(aVar3);
                    }
                }
            }
        }
        HashSet<a> hashSet = new HashSet<>(hashMap.values());
        Set a2 = a((Set<a>) hashSet);
        ArrayList arrayList = new ArrayList();
        while (!a2.isEmpty()) {
            a aVar5 = (a) a2.iterator().next();
            a2.remove(aVar5);
            arrayList.add(aVar5.b());
            for (a aVar6 : aVar5.a()) {
                aVar6.c(aVar5);
                if (aVar6.c()) {
                    a2.add(aVar6);
                }
            }
        }
        if (arrayList.size() == list.size()) {
            Collections.reverse(arrayList);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        for (a aVar7 : hashSet) {
            if (!aVar7.c() && !aVar7.d()) {
                arrayList2.add(aVar7.b());
            }
        }
        throw new DependencyCycleException(arrayList2);
    }
}
