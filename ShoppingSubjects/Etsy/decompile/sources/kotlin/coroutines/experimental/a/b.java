package kotlin.coroutines.experimental.a;

import com.etsy.android.lib.models.ResponseConstants;
import kotlin.TypeCastException;
import kotlin.coroutines.experimental.d;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.h;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;

/* compiled from: IntrinsicsJvm.kt */
class b {

    /* compiled from: IntrinsicsJvm.kt */
    public static final class a implements kotlin.coroutines.experimental.b<h> {
        final /* synthetic */ kotlin.coroutines.experimental.b a;
        final /* synthetic */ m b;
        final /* synthetic */ Object c;
        final /* synthetic */ kotlin.coroutines.experimental.b d;

        public a(kotlin.coroutines.experimental.b bVar, m mVar, Object obj, kotlin.coroutines.experimental.b bVar2) {
            this.a = bVar;
            this.b = mVar;
            this.c = obj;
            this.d = bVar2;
        }

        public d getContext() {
            return this.a.getContext();
        }

        /* renamed from: a */
        public void resume(h hVar) {
            p.b(hVar, ResponseConstants.VALUE);
            kotlin.coroutines.experimental.b bVar = this.a;
            try {
                m mVar = this.b;
                if (mVar == null) {
                    throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
                }
                Object invoke = ((m) v.b(mVar, 2)).invoke(this.c, this.d);
                if (invoke == a.a()) {
                    return;
                }
                if (bVar == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                }
                bVar.resume(invoke);
            } catch (Throwable th) {
                bVar.resumeWithException(th);
            }
        }

        public void resumeWithException(Throwable th) {
            p.b(th, "exception");
            this.a.resumeWithException(th);
        }
    }

    public static final <R, T> kotlin.coroutines.experimental.b<h> a(m<? super R, ? super kotlin.coroutines.experimental.b<? super T>, ? extends Object> mVar, R r, kotlin.coroutines.experimental.b<? super T> bVar) {
        p.b(mVar, "$receiver");
        p.b(bVar, "completion");
        if (!(mVar instanceof CoroutineImpl)) {
            return kotlin.coroutines.experimental.jvm.internal.a.a(bVar.getContext(), new a(bVar, mVar, r, bVar));
        }
        kotlin.coroutines.experimental.b create = ((CoroutineImpl) mVar).create(r, bVar);
        if (create != null) {
            return ((CoroutineImpl) create).getFacade();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
    }
}
