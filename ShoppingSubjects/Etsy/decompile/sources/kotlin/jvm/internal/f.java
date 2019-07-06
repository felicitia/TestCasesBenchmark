package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.ad;

/* compiled from: ArrayIterators.kt */
final class f extends ad {
    private int a;
    private final int[] b;

    public f(int[] iArr) {
        p.b(iArr, "array");
        this.b = iArr;
    }

    public boolean hasNext() {
        return this.a < this.b.length;
    }

    public int b() {
        try {
            int[] iArr = this.b;
            int i = this.a;
            this.a = i + 1;
            return iArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
