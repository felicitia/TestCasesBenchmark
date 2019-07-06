package kotlin.text;

import kotlin.Pair;
import kotlin.f;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: Strings.kt */
final class StringsKt__StringsKt$rangesDelimitedBy$2 extends Lambda implements m<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>> {
    final /* synthetic */ char[] $delimiters;
    final /* synthetic */ boolean $ignoreCase;

    StringsKt__StringsKt$rangesDelimitedBy$2(char[] cArr, boolean z) {
        this.$delimiters = cArr;
        this.$ignoreCase = z;
        super(2);
    }

    public /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((CharSequence) obj, ((Number) obj2).intValue());
    }

    public final Pair<Integer, Integer> invoke(CharSequence charSequence, int i) {
        p.b(charSequence, "$receiver");
        int a = m.a(charSequence, this.$delimiters, i, this.$ignoreCase);
        if (a < 0) {
            return null;
        }
        return f.a(Integer.valueOf(a), Integer.valueOf(1));
    }
}
