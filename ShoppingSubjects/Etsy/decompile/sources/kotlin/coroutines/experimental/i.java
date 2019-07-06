package kotlin.coroutines.experimental;

import java.util.Iterator;
import kotlin.coroutines.experimental.a.a;
import kotlin.h;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.p;

/* compiled from: SequenceBuilder.kt */
class i {
    public static final <T> Iterator<T> a(m<? super f<? super T>, ? super b<? super h>, ? extends Object> mVar) {
        p.b(mVar, "builderAction");
        g gVar = new g();
        gVar.a(a.a(mVar, gVar, gVar));
        return gVar;
    }
}
