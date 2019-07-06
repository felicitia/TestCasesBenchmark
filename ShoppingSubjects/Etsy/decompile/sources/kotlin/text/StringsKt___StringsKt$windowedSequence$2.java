package kotlin.text;

import kotlin.b.d;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: _Strings.kt */
final class StringsKt___StringsKt$windowedSequence$2 extends Lambda implements b<Integer, R> {
    final /* synthetic */ int $size;
    final /* synthetic */ b $transform;
    final /* synthetic */ CharSequence receiver$0;

    StringsKt___StringsKt$windowedSequence$2(CharSequence charSequence, b bVar, int i) {
        this.receiver$0 = charSequence;
        this.$transform = bVar;
        this.$size = i;
        super(1);
    }

    public /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final R invoke(int i) {
        return this.$transform.invoke(this.receiver$0.subSequence(i, d.d(this.$size + i, this.receiver$0.length())));
    }
}
