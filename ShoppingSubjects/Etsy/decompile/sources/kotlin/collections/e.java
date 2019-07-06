package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: AbstractMap.kt */
public abstract class e<K, V> implements Map<K, V> {
    public static final a a = new a(null);

    /* compiled from: AbstractMap.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    public abstract Set b();

    protected e() {
    }

    public final Set<Entry<K, V>> entrySet() {
        return b();
    }

    public final int size() {
        return a();
    }

    public final boolean a(Entry<?, ?> entry) {
        if (!(entry instanceof Entry)) {
            return false;
        }
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (this == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
        }
        Map map = this;
        Object obj = map.get(key);
        if (!p.a(value, obj)) {
            return false;
        }
        if (obj == null) {
            if (this == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
            } else if (!map.containsKey(key)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (size() != map.size()) {
            return false;
        }
        Iterable entrySet = map.entrySet();
        if (!(entrySet instanceof Collection) || !((Collection) entrySet).isEmpty()) {
            Iterator it = entrySet.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!a((Entry) it.next())) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return z;
    }

    public int hashCode() {
        return entrySet().hashCode();
    }

    public int a() {
        return entrySet().size();
    }

    public String toString() {
        return o.a(entrySet(), ", ", VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, 0, null, new AbstractMap$toString$1(this), 24, null);
    }

    /* access modifiers changed from: private */
    public final String b(Entry<? extends K, ? extends V> entry) {
        StringBuilder sb = new StringBuilder();
        sb.append(a(entry.getKey()));
        sb.append("=");
        sb.append(a(entry.getValue()));
        return sb.toString();
    }

    private final String a(Object obj) {
        return obj == this ? "(this Map)" : String.valueOf(obj);
    }
}
