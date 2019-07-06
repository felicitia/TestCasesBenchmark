package kotlin.sequences;

import kotlin.jvm.a.a;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;

/* compiled from: Sequences.kt */
class f extends e {
    public static final <T> c<T> a(a<? extends T> aVar, b<? super T, ? extends T> bVar) {
        p.b(aVar, "seedFunction");
        p.b(bVar, "nextFunction");
        return new b<>(aVar, bVar);
    }
}
