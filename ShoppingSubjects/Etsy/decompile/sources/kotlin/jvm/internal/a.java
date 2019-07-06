package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.l;

/* compiled from: ArrayIterators.kt */
final class a extends l {
    private int a;
    private final boolean[] b;

    public a(boolean[] zArr) {
        p.b(zArr, "array");
        this.b = zArr;
    }

    public boolean hasNext() {
        return this.a < this.b.length;
    }

    public boolean b() {
        try {
            boolean[] zArr = this.b;
            int i = this.a;
            this.a = i + 1;
            return zArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
