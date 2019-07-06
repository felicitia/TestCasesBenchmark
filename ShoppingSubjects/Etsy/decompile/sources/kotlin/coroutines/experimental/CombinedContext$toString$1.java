package kotlin.coroutines.experimental;

import kotlin.coroutines.experimental.d.b;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: CoroutineContextImpl.kt */
final class CombinedContext$toString$1 extends Lambda implements m<String, b, String> {
    public static final CombinedContext$toString$1 INSTANCE = new CombinedContext$toString$1();

    CombinedContext$toString$1() {
        super(2);
    }

    public final String invoke(String str, b bVar) {
        p.b(str, "acc");
        p.b(bVar, "element");
        if (str.length() == 0) {
            return bVar.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(", ");
        sb.append(bVar);
        return sb.toString();
    }
}
