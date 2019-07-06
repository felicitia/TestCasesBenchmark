package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.m;

/* compiled from: ArrayIterators.kt */
final class b extends m {
    private int a;
    private final byte[] b;

    public b(byte[] bArr) {
        p.b(bArr, "array");
        this.b = bArr;
    }

    public boolean hasNext() {
        return this.a < this.b.length;
    }

    public byte b() {
        try {
            byte[] bArr = this.b;
            int i = this.a;
            this.a = i + 1;
            return bArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
