package kotlin.collections;

import java.util.Map.Entry;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: AbstractMap.kt */
final class AbstractMap$toString$1 extends Lambda implements b<Entry<? extends K, ? extends V>, String> {
    final /* synthetic */ e this$0;

    AbstractMap$toString$1(e eVar) {
        this.this$0 = eVar;
        super(1);
    }

    public final String invoke(Entry<? extends K, ? extends V> entry) {
        p.b(entry, "it");
        return this.this$0.b(entry);
    }
}
