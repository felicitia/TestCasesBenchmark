package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.collections.IntIterator;

/* compiled from: ProgressionIterators.kt */
public final class IntProgressionIterator extends IntIterator {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    public IntProgressionIterator(int i, int i2, int i3) {
        this.step = i3;
        this.finalElement = i2;
        boolean z = false;
        if (this.step <= 0 ? i >= i2 : i <= i2) {
            z = true;
        }
        this.hasNext = z;
        if (!this.hasNext) {
            i = this.finalElement;
        }
        this.next = i;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public int nextInt() {
        int i = this.next;
        if (i != this.finalElement) {
            this.next += this.step;
        } else if (!this.hasNext) {
            throw new NoSuchElementException();
        } else {
            this.hasNext = false;
        }
        return i;
    }
}
