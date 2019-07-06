package kotlin.sequences;

import kotlin.collections.ac;
import kotlin.jvm.a.b;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$filterIndexed$1 extends Lambda implements b<ac<? extends T>, Boolean> {
    final /* synthetic */ m $predicate;

    SequencesKt___SequencesKt$filterIndexed$1(m mVar) {
        this.$predicate = mVar;
        super(1);
    }

    public /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((ac) obj));
    }

    public final boolean invoke(ac<? extends T> acVar) {
        p.b(acVar, "it");
        return ((Boolean) this.$predicate.invoke(Integer.valueOf(acVar.a()), acVar.b())).booleanValue();
    }
}
