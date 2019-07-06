package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;

/* compiled from: Sequences.kt */
public final class a<T> implements c<T> {
    /* access modifiers changed from: private */
    public final c<T> a;
    /* access modifiers changed from: private */
    public final boolean b;
    /* access modifiers changed from: private */
    public final b<T, Boolean> c;

    /* renamed from: kotlin.sequences.a$a reason: collision with other inner class name */
    /* compiled from: Sequences.kt */
    public static final class C0193a implements Iterator<T> {
        final /* synthetic */ a a;
        private final Iterator<T> b;
        private int c = -1;
        private T d;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C0193a(a aVar) {
            this.a = aVar;
            this.b = aVar.a.a();
        }

        private final void a() {
            while (this.b.hasNext()) {
                T next = this.b.next();
                if (((Boolean) this.a.c.invoke(next)).booleanValue() == this.a.b) {
                    this.d = next;
                    this.c = 1;
                    return;
                }
            }
            this.c = 0;
        }

        public T next() {
            if (this.c == -1) {
                a();
            }
            if (this.c == 0) {
                throw new NoSuchElementException();
            }
            T t = this.d;
            this.d = null;
            this.c = -1;
            return t;
        }

        public boolean hasNext() {
            if (this.c == -1) {
                a();
            }
            return this.c == 1;
        }
    }

    public a(c<? extends T> cVar, boolean z, b<? super T, Boolean> bVar) {
        p.b(cVar, "sequence");
        p.b(bVar, "predicate");
        this.a = cVar;
        this.b = z;
        this.c = bVar;
    }

    public Iterator<T> a() {
        return new C0193a<>(this);
    }
}
