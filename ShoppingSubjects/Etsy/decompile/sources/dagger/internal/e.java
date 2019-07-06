package dagger.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: MapProviderFactory */
public final class e<K, V> implements c<Map<K, javax.a.a<V>>> {
    private static final e<Object, Object> a = new e<>(Collections.emptyMap());
    private final Map<K, javax.a.a<V>> b;

    /* compiled from: MapProviderFactory */
    public static final class a<K, V> {
        private final LinkedHashMap<K, javax.a.a<V>> a;

        private a(int i) {
            this.a = a.a(i);
        }

        public e<K, V> a() {
            return new e<>(this.a);
        }

        public a<K, V> a(K k, javax.a.a<V> aVar) {
            if (k == null) {
                throw new NullPointerException("The key is null");
            } else if (aVar == null) {
                throw new NullPointerException("The provider of the value is null");
            } else {
                this.a.put(k, aVar);
                return this;
            }
        }
    }

    public static <K, V> a<K, V> a(int i) {
        return new a<>(i);
    }

    public static <K, V> e<K, V> a() {
        return a;
    }

    private e(Map<K, javax.a.a<V>> map) {
        this.b = Collections.unmodifiableMap(map);
    }

    /* renamed from: c */
    public Map<K, javax.a.a<V>> b() {
        return this.b;
    }
}
