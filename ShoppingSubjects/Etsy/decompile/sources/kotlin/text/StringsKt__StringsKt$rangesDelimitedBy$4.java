package kotlin.text;

import java.util.List;
import kotlin.Pair;
import kotlin.f;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: Strings.kt */
final class StringsKt__StringsKt$rangesDelimitedBy$4 extends Lambda implements m<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>> {
    final /* synthetic */ List $delimitersList;
    final /* synthetic */ boolean $ignoreCase;

    StringsKt__StringsKt$rangesDelimitedBy$4(List list, boolean z) {
        this.$delimitersList = list;
        this.$ignoreCase = z;
        super(2);
    }

    public /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((CharSequence) obj, ((Number) obj2).intValue());
    }

    public final Pair<Integer, Integer> invoke(CharSequence charSequence, int i) {
        p.b(charSequence, "$receiver");
        Pair a = v.b(charSequence, this.$delimitersList, i, this.$ignoreCase, false);
        if (a != null) {
            return f.a(a.getFirst(), Integer.valueOf(((String) a.getSecond()).length()));
        }
        return null;
    }
}
