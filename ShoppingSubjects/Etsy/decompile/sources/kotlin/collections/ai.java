package kotlin.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: Maps.kt */
class ai extends ah {
    public static final <K, V> Map<K, V> a() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        if (emptyMap != null) {
            return emptyMap;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
    }

    public static final <K, V> Map<K, V> a(Pair<? extends K, ? extends V>... pairArr) {
        p.b(pairArr, "pairs");
        return pairArr.length > 0 ? af.a(pairArr, (M) new LinkedHashMap(af.a(pairArr.length))) : af.a();
    }

    public static final <K, V> HashMap<K, V> b(Pair<? extends K, ? extends V>... pairArr) {
        p.b(pairArr, "pairs");
        HashMap<K, V> hashMap = new HashMap<>(af.a(pairArr.length));
        af.a((Map<? super K, ? super V>) hashMap, pairArr);
        return hashMap;
    }

    public static final int a(int i) {
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return i + (i / 3);
        }
        return Integer.MAX_VALUE;
    }

    public static final <K, V> void a(Map<? super K, ? super V> map, Pair<? extends K, ? extends V>[] pairArr) {
        p.b(map, "$receiver");
        p.b(pairArr, "pairs");
        for (Pair<? extends K, ? extends V> pair : pairArr) {
            map.put(pair.component1(), pair.component2());
        }
    }

    public static final <K, V, M extends Map<? super K, ? super V>> M a(Pair<? extends K, ? extends V>[] pairArr, M m) {
        p.b(pairArr, "$receiver");
        p.b(m, "destination");
        af.a((Map<? super K, ? super V>) m, pairArr);
        return m;
    }
}
