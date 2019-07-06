package kotlin.collections;

import com.etsy.android.lib.models.ResponseConstants;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.jvm.internal.p;

/* compiled from: Maps.kt */
final class EmptyMap implements Serializable, Map {
    public static final EmptyMap INSTANCE = new EmptyMap();
    private static final long serialVersionUID = 8246714829545688274L;

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean containsKey(Object obj) {
        return false;
    }

    public boolean containsValue(Void voidR) {
        p.b(voidR, ResponseConstants.VALUE);
        return false;
    }

    public Void get(Object obj) {
        return null;
    }

    public int getSize() {
        return 0;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Void put(Object obj, Void voidR) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void putAll(Map map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public String toString() {
        return "{}";
    }

    private EmptyMap() {
    }

    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof Void) {
            return containsValue((Void) obj);
        }
        return false;
    }

    public final Set<Entry> entrySet() {
        return getEntries();
    }

    public final /* bridge */ Object get(Object obj) {
        return get(obj);
    }

    public final Set<Object> keySet() {
        return getKeys();
    }

    public final int size() {
        return getSize();
    }

    public final Collection values() {
        return getValues();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Map) && ((Map) obj).isEmpty();
    }

    public Set<Entry> getEntries() {
        return EmptySet.INSTANCE;
    }

    public Set<Object> getKeys() {
        return EmptySet.INSTANCE;
    }

    public Collection getValues() {
        return EmptyList.INSTANCE;
    }

    private final Object readResolve() {
        return INSTANCE;
    }
}
