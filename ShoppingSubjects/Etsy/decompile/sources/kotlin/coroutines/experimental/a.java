package kotlin.coroutines.experimental;

import com.etsy.android.lib.models.ResponseConstants;
import kotlin.TypeCastException;
import kotlin.coroutines.experimental.d.b;
import kotlin.coroutines.experimental.d.c;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.p;

/* compiled from: CoroutineContextImpl.kt */
public final class a implements d {
    private final d a;
    private final b b;

    public a(d dVar, b bVar) {
        p.b(dVar, "left");
        p.b(bVar, "element");
        this.a = dVar;
        this.b = bVar;
    }

    public <E extends b> E a(c<E> cVar) {
        p.b(cVar, ResponseConstants.KEY);
        d dVar = this;
        while (true) {
            a aVar = (a) dVar;
            E a2 = aVar.b.a(cVar);
            if (a2 != null) {
                return a2;
            }
            dVar = aVar.a;
            if (!(dVar instanceof a)) {
                return dVar.a(cVar);
            }
        }
    }

    public <R> R a(R r, m<? super R, ? super b, ? extends R> mVar) {
        p.b(mVar, "operation");
        return mVar.invoke(this.a.a(r, mVar), this.b);
    }

    public d b(c<?> cVar) {
        d dVar;
        p.b(cVar, ResponseConstants.KEY);
        if (this.b.a(cVar) != null) {
            return this.a;
        }
        d b2 = this.a.b(cVar);
        if (b2 == this.a) {
            dVar = this;
        } else if (b2 == e.a) {
            dVar = this.b;
        } else {
            dVar = new a(b2, this.b);
        }
        return dVar;
    }

    private final int a() {
        if (this.a instanceof a) {
            return ((a) this.a).a() + 1;
        }
        return 2;
    }

    private final boolean a(b bVar) {
        return p.a((Object) a(bVar.a()), (Object) bVar);
    }

    private final boolean a(a aVar) {
        while (a(aVar.b)) {
            d dVar = aVar.a;
            if (dVar instanceof a) {
                aVar = (a) dVar;
            } else if (dVar != null) {
                return a((b) dVar);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.CoroutineContext.Element");
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0019, code lost:
        if (r3.a(r2) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            r0 = r2
            kotlin.coroutines.experimental.a r0 = (kotlin.coroutines.experimental.a) r0
            if (r0 == r3) goto L_0x001e
            boolean r0 = r3 instanceof kotlin.coroutines.experimental.a
            if (r0 == 0) goto L_0x001c
            kotlin.coroutines.experimental.a r3 = (kotlin.coroutines.experimental.a) r3
            int r0 = r3.a()
            int r1 = r2.a()
            if (r0 != r1) goto L_0x001c
            boolean r3 = r3.a(r2)
            if (r3 == 0) goto L_0x001c
            goto L_0x001e
        L_0x001c:
            r3 = 0
            goto L_0x001f
        L_0x001e:
            r3 = 1
        L_0x001f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.coroutines.experimental.a.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return this.a.hashCode() + this.b.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append((String) a("", CombinedContext$toString$1.INSTANCE));
        sb.append("]");
        return sb.toString();
    }
}
