package kotlin.text;

import kotlin.b.c;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: Strings.kt */
final class StringsKt__StringsKt$splitToSequence$2 extends Lambda implements b<c, String> {
    final /* synthetic */ CharSequence receiver$0;

    StringsKt__StringsKt$splitToSequence$2(CharSequence charSequence) {
        this.receiver$0 = charSequence;
        super(1);
    }

    public final String invoke(c cVar) {
        p.b(cVar, "it");
        return m.a(this.receiver$0, cVar);
    }
}
