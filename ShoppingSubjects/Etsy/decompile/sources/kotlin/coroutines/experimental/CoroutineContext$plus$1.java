package kotlin.coroutines.experimental;

import kotlin.coroutines.experimental.d.b;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: CoroutineContext.kt */
final class CoroutineContext$plus$1 extends Lambda implements m<d, b, d> {
    public static final CoroutineContext$plus$1 INSTANCE = new CoroutineContext$plus$1();

    CoroutineContext$plus$1() {
        super(2);
    }

    public final d invoke(d dVar, b bVar) {
        a aVar;
        p.b(dVar, "acc");
        p.b(bVar, "element");
        d b = dVar.b(bVar.a());
        if (b == e.a) {
            return bVar;
        }
        c cVar = (c) b.a(c.a);
        if (cVar == null) {
            aVar = new a(b, bVar);
        } else {
            d b2 = b.b(c.a);
            if (b2 == e.a) {
                aVar = new a(bVar, cVar);
            } else {
                aVar = new a(new a(b2, bVar), cVar);
            }
        }
        return aVar;
    }
}
