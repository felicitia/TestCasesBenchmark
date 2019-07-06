package okio;

import java.util.AbstractList;
import java.util.RandomAccess;

/* compiled from: Options */
public final class n extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] a;

    private n(ByteString[] byteStringArr) {
        this.a = byteStringArr;
    }

    public static n a(ByteString... byteStringArr) {
        return new n((ByteString[]) byteStringArr.clone());
    }

    /* renamed from: a */
    public ByteString get(int i) {
        return this.a[i];
    }

    public int size() {
        return this.a.length;
    }
}
