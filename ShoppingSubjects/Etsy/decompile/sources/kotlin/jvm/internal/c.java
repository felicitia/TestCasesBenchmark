package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.n;

/* compiled from: ArrayIterators.kt */
final class c extends n {
    private int a;
    private final char[] b;

    public c(char[] cArr) {
        p.b(cArr, "array");
        this.b = cArr;
    }

    public boolean hasNext() {
        return this.a < this.b.length;
    }

    public char b() {
        try {
            char[] cArr = this.b;
            int i = this.a;
            this.a = i + 1;
            return cArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
