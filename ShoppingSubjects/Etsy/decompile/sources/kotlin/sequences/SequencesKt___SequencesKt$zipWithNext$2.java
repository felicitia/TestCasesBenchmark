package kotlin.sequences;

import java.util.Iterator;
import kotlin.coroutines.experimental.a.a;
import kotlin.coroutines.experimental.b;
import kotlin.coroutines.experimental.f;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.h;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.p;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$zipWithNext$2 extends CoroutineImpl implements m<f<? super R>, b<? super h>, Object> {
    final /* synthetic */ m $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    private f p$;
    final /* synthetic */ c receiver$0;

    SequencesKt___SequencesKt$zipWithNext$2(c cVar, m mVar, b bVar) {
        this.receiver$0 = cVar;
        this.$transform = mVar;
        super(2, bVar);
    }

    public final b<h> create(f<? super R> fVar, b<? super h> bVar) {
        p.b(fVar, "$receiver");
        p.b(bVar, "continuation");
        SequencesKt___SequencesKt$zipWithNext$2 sequencesKt___SequencesKt$zipWithNext$2 = new SequencesKt___SequencesKt$zipWithNext$2(this.receiver$0, this.$transform, bVar);
        sequencesKt___SequencesKt$zipWithNext$2.p$ = fVar;
        return sequencesKt___SequencesKt$zipWithNext$2;
    }

    public final Object invoke(f<? super R> fVar, b<? super h> bVar) {
        p.b(fVar, "$receiver");
        p.b(bVar, "continuation");
        return ((SequencesKt___SequencesKt$zipWithNext$2) create(fVar, bVar)).doResume(h.a, null);
    }

    public final Object doResume(Object obj, Throwable th) {
        Object obj2;
        f fVar;
        Iterator it;
        Object a = a.a();
        switch (this.label) {
            case 0:
                if (th == null) {
                    f fVar2 = this.p$;
                    Iterator a2 = this.receiver$0.a();
                    if (a2.hasNext()) {
                        fVar = fVar2;
                        obj2 = a2.next();
                        it = a2;
                        break;
                    } else {
                        return h.a;
                    }
                } else {
                    throw th;
                }
            case 1:
                Object obj3 = this.L$3;
                Object obj4 = this.L$2;
                it = (Iterator) this.L$1;
                fVar = (f) this.L$0;
                if (th == null) {
                    obj2 = obj3;
                    break;
                } else {
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Object obj5 = a;
        while (it.hasNext()) {
            Object next = it.next();
            Object invoke = this.$transform.invoke(obj2, next);
            this.L$0 = fVar;
            this.L$1 = it;
            this.L$2 = obj2;
            this.L$3 = next;
            this.label = 1;
            if (fVar.a(invoke, this) == obj5) {
                return obj5;
            }
            obj2 = next;
        }
        return h.a;
    }
}
