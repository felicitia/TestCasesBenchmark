package kotlin.text;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: Regex.kt */
final class MatcherMatchResult$groups$1$iterator$1 extends Lambda implements b<Integer, g> {
    final /* synthetic */ k.b this$0;

    MatcherMatchResult$groups$1$iterator$1(k.b bVar) {
        this.this$0 = bVar;
        super(1);
    }

    public /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final g invoke(int i) {
        return this.this$0.a(i);
    }
}
