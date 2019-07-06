package android.arch.core.internal;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.HashMap;
import java.util.Map.Entry;

@RestrictTo({Scope.LIBRARY_GROUP})
public class FastSafeIterableMap<K, V> extends a<K, V> {
    private HashMap<K, c<K, V>> mHashMap = new HashMap<>();

    /* access modifiers changed from: protected */
    public c<K, V> get(K k) {
        return (c) this.mHashMap.get(k);
    }

    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        c cVar = get(k);
        if (cVar != null) {
            return cVar.b;
        }
        this.mHashMap.put(k, put(k, v));
        return null;
    }

    public V remove(@NonNull K k) {
        V remove = super.remove(k);
        this.mHashMap.remove(k);
        return remove;
    }

    public boolean contains(K k) {
        return this.mHashMap.containsKey(k);
    }

    public Entry<K, V> ceil(K k) {
        if (contains(k)) {
            return ((c) this.mHashMap.get(k)).d;
        }
        return null;
    }
}
