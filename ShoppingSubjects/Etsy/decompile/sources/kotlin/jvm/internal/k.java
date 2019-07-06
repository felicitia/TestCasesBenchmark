package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.ap;

/* compiled from: ArrayIterators.kt */
final class k extends ap {
    private int a;
    private final short[] b;

    public k(short[] sArr) {
        p.b(sArr, "array");
        this.b = sArr;
    }

    public boolean hasNext() {
        return this.a < this.b.length;
    }

    public short b() {
        try {
            short[] sArr = this.b;
            int i = this.a;
            this.a = i + 1;
            return sArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
