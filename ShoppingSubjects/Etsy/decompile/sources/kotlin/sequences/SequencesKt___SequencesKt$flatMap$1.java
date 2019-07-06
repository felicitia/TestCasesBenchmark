package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$flatMap$1 extends Lambda implements b<c<? extends R>, Iterator<? extends R>> {
    public static final SequencesKt___SequencesKt$flatMap$1 INSTANCE = new SequencesKt___SequencesKt$flatMap$1();

    SequencesKt___SequencesKt$flatMap$1() {
        super(1);
    }

    public final Iterator<R> invoke(c<? extends R> cVar) {
        p.b(cVar, "it");
        return cVar.a();
    }
}
