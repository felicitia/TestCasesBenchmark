package android.arch.core.internal;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: SafeIterableMap */
public class a<K, V> implements Iterable<Entry<K, V>> {
    private c<K, V> mEnd;
    private WeakHashMap<f<K, V>, Boolean> mIterators = new WeakHashMap<>();
    private int mSize = 0;
    /* access modifiers changed from: private */
    public c<K, V> mStart;

    /* renamed from: android.arch.core.internal.a$a reason: collision with other inner class name */
    /* compiled from: SafeIterableMap */
    static class C0000a<K, V> extends e<K, V> {
        C0000a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        /* access modifiers changed from: 0000 */
        public c<K, V> a(c<K, V> cVar) {
            return cVar.c;
        }

        /* access modifiers changed from: 0000 */
        public c<K, V> b(c<K, V> cVar) {
            return cVar.d;
        }
    }

    /* compiled from: SafeIterableMap */
    private static class b<K, V> extends e<K, V> {
        b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        /* access modifiers changed from: 0000 */
        public c<K, V> a(c<K, V> cVar) {
            return cVar.d;
        }

        /* access modifiers changed from: 0000 */
        public c<K, V> b(c<K, V> cVar) {
            return cVar.c;
        }
    }

    /* compiled from: SafeIterableMap */
    static class c<K, V> implements Entry<K, V> {
        @NonNull
        final K a;
        @NonNull
        final V b;
        c<K, V> c;
        c<K, V> d;

        c(@NonNull K k, @NonNull V v) {
            this.a = k;
            this.b = v;
        }

        @NonNull
        public K getKey() {
            return this.a;
        }

        @NonNull
        public V getValue() {
            return this.b;
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append("=");
            sb.append(this.b);
            return sb.toString();
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (!this.a.equals(cVar.a) || !this.b.equals(cVar.b)) {
                z = false;
            }
            return z;
        }
    }

    /* compiled from: SafeIterableMap */
    private class d implements f<K, V>, Iterator<Entry<K, V>> {
        private c<K, V> b;
        private boolean c;

        private d() {
            this.c = true;
        }

        public void a_(@NonNull c<K, V> cVar) {
            if (cVar == this.b) {
                this.b = this.b.d;
                this.c = this.b == null;
            }
        }

        public boolean hasNext() {
            boolean z = false;
            if (this.c) {
                if (a.this.mStart != null) {
                    z = true;
                }
                return z;
            }
            if (!(this.b == null || this.b.c == null)) {
                z = true;
            }
            return z;
        }

        /* renamed from: a */
        public Entry<K, V> next() {
            if (this.c) {
                this.c = false;
                this.b = a.this.mStart;
            } else {
                this.b = this.b != null ? this.b.c : null;
            }
            return this.b;
        }
    }

    /* compiled from: SafeIterableMap */
    private static abstract class e<K, V> implements f<K, V>, Iterator<Entry<K, V>> {
        c<K, V> a;
        c<K, V> b;

        /* access modifiers changed from: 0000 */
        public abstract c<K, V> a(c<K, V> cVar);

        /* access modifiers changed from: 0000 */
        public abstract c<K, V> b(c<K, V> cVar);

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.a = cVar2;
            this.b = cVar;
        }

        public boolean hasNext() {
            return this.b != null;
        }

        public void a_(@NonNull c<K, V> cVar) {
            if (this.a == cVar && cVar == this.b) {
                this.b = null;
                this.a = null;
            }
            if (this.a == cVar) {
                this.a = b(this.a);
            }
            if (this.b == cVar) {
                this.b = b();
            }
        }

        private c<K, V> b() {
            if (this.b == this.a || this.a == null) {
                return null;
            }
            return a(this.b);
        }

        /* renamed from: a */
        public Entry<K, V> next() {
            c<K, V> cVar = this.b;
            this.b = b();
            return cVar;
        }
    }

    /* compiled from: SafeIterableMap */
    interface f<K, V> {
        void a_(@NonNull c<K, V> cVar);
    }

    /* access modifiers changed from: protected */
    public c<K, V> get(K k) {
        c<K, V> cVar = this.mStart;
        while (cVar != null && !cVar.a.equals(k)) {
            cVar = cVar.c;
        }
        return cVar;
    }

    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        c cVar = get(k);
        if (cVar != null) {
            return cVar.b;
        }
        put(k, v);
        return null;
    }

    /* access modifiers changed from: protected */
    public c<K, V> put(@NonNull K k, @NonNull V v) {
        c<K, V> cVar = new c<>(k, v);
        this.mSize++;
        if (this.mEnd == null) {
            this.mStart = cVar;
            this.mEnd = this.mStart;
            return cVar;
        }
        this.mEnd.c = cVar;
        cVar.d = this.mEnd;
        this.mEnd = cVar;
        return cVar;
    }

    public V remove(@NonNull K k) {
        c cVar = get(k);
        if (cVar == null) {
            return null;
        }
        this.mSize--;
        if (!this.mIterators.isEmpty()) {
            for (f a_ : this.mIterators.keySet()) {
                a_.a_(cVar);
            }
        }
        if (cVar.d != null) {
            cVar.d.c = cVar.c;
        } else {
            this.mStart = cVar.c;
        }
        if (cVar.c != null) {
            cVar.c.d = cVar.d;
        } else {
            this.mEnd = cVar.d;
        }
        cVar.c = null;
        cVar.d = null;
        return cVar.b;
    }

    public int size() {
        return this.mSize;
    }

    @NonNull
    public Iterator<Entry<K, V>> iterator() {
        C0000a aVar = new C0000a(this.mStart, this.mEnd);
        this.mIterators.put(aVar, Boolean.valueOf(false));
        return aVar;
    }

    public Iterator<Entry<K, V>> descendingIterator() {
        b bVar = new b(this.mEnd, this.mStart);
        this.mIterators.put(bVar, Boolean.valueOf(false));
        return bVar;
    }

    public d iteratorWithAdditions() {
        d dVar = new d<>();
        this.mIterators.put(dVar, Boolean.valueOf(false));
        return dVar;
    }

    public Entry<K, V> eldest() {
        return this.mStart;
    }

    public Entry<K, V> newest() {
        return this.mEnd;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (size() != aVar.size()) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = aVar.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Entry entry = (Entry) it.next();
            Object next = it2.next();
            if ((entry == null && next != null) || (entry != null && !entry.equals(next))) {
                return false;
            }
        }
        if (it.hasNext() || it2.hasNext()) {
            z = false;
        }
        return z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(((Entry) it.next()).toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
