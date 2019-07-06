package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;

/* compiled from: Sequences.kt */
public final class i<T, R> implements c<R> {
    /* access modifiers changed from: private */
    public final c<T> a;
    /* access modifiers changed from: private */
    public final b<T, R> b;

    /* compiled from: Sequences.kt */
    public static final class a implements Iterator<R> {
        final /* synthetic */ i a;
        private final Iterator<T> b;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        a(i iVar) {
            this.a = iVar;
            this.b = iVar.a.a();
        }

        public R next() {
            return this.a.b.invoke(this.b.next());
        }

        public boolean hasNext() {
            return this.b.hasNext();
        }
    }

    public i(c<? extends T> cVar, b<? super T, ? extends R> bVar) {
        p.b(cVar, "sequence");
        p.b(bVar, "transformer");
        this.a = cVar;
        this.b = bVar;
    }

    public Iterator<R> a() {
        return new a<>(this);
    }
}
