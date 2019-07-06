package kotlin.io;

import java.util.ArrayList;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: ReadWrite.kt */
final class TextStreamsKt$readLines$1 extends Lambda implements b<String, h> {
    final /* synthetic */ ArrayList $result;

    TextStreamsKt$readLines$1(ArrayList arrayList) {
        this.$result = arrayList;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return h.a;
    }

    public final void invoke(String str) {
        p.b(str, "it");
        this.$result.add(str);
    }
}
