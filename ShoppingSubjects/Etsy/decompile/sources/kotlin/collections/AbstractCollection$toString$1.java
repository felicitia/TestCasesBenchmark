package kotlin.collections;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: AbstractCollection.kt */
final class AbstractCollection$toString$1 extends Lambda implements b<E, CharSequence> {
    final /* synthetic */ a this$0;

    AbstractCollection$toString$1(a aVar) {
        this.this$0 = aVar;
        super(1);
    }

    public final CharSequence invoke(E e) {
        return e == this.this$0 ? "(this Collection)" : String.valueOf(e);
    }
}
