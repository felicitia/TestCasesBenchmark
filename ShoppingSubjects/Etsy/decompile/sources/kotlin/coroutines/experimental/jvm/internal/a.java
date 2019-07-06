package kotlin.coroutines.experimental.jvm.internal;

import com.etsy.android.lib.models.ResponseConstants;
import kotlin.coroutines.experimental.b;
import kotlin.coroutines.experimental.c;
import kotlin.coroutines.experimental.d;
import kotlin.jvm.internal.p;

/* compiled from: CoroutineIntrinsics.kt */
public final class a {
    public static final <T> b<T> a(b<? super T> bVar) {
        p.b(bVar, "continuation");
        CoroutineImpl coroutineImpl = (CoroutineImpl) (!(bVar instanceof CoroutineImpl) ? null : bVar);
        if (coroutineImpl == null) {
            return bVar;
        }
        b facade = coroutineImpl.getFacade();
        return facade != null ? facade : bVar;
    }

    public static final <T> b<T> a(d dVar, b<? super T> bVar) {
        p.b(dVar, ResponseConstants.CONTEXT);
        p.b(bVar, "continuation");
        c cVar = (c) dVar.a(c.a);
        if (cVar != null) {
            b<T> a = cVar.a(bVar);
            if (a != null) {
                return a;
            }
        }
        return bVar;
    }
}
