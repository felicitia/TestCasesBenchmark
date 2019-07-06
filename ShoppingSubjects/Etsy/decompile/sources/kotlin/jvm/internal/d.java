package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.z;

/* compiled from: ArrayIterators.kt */
final class d extends z {
    private int a;
    private final double[] b;

    public d(double[] dArr) {
        p.b(dArr, "array");
        this.b = dArr;
    }

    public boolean hasNext() {
        return this.a < this.b.length;
    }

    public double b() {
        try {
            double[] dArr = this.b;
            int i = this.a;
            this.a = i + 1;
            return dArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
