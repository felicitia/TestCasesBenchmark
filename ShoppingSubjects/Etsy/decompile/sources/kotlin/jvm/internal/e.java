package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.ab;

/* compiled from: ArrayIterators.kt */
final class e extends ab {
    private int a;
    private final float[] b;

    public e(float[] fArr) {
        p.b(fArr, "array");
        this.b = fArr;
    }

    public boolean hasNext() {
        return this.a < this.b.length;
    }

    public float b() {
        try {
            float[] fArr = this.b;
            int i = this.a;
            this.a = i + 1;
            return fArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
