package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.ae;

/* compiled from: ArrayIterators.kt */
final class j extends ae {
    private int a;
    private final long[] b;

    public j(long[] jArr) {
        p.b(jArr, "array");
        this.b = jArr;
    }

    public boolean hasNext() {
        return this.a < this.b.length;
    }

    public long b() {
        try {
            long[] jArr = this.b;
            int i = this.a;
            this.a = i + 1;
            return jArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
