package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;

public class Incrementor {
    private int count;
    private final MaxCountExceededCallback maxCountCallback;
    private int maximalCount;

    public interface MaxCountExceededCallback {
        void trigger(int i);
    }

    public Incrementor() {
        this(0);
    }

    public Incrementor(int i) {
        this(i, new MaxCountExceededCallback() {
            public void trigger(int i) {
                throw new MaxCountExceededException(Integer.valueOf(i));
            }
        });
    }

    public Incrementor(int i, MaxCountExceededCallback maxCountExceededCallback) {
        this.count = 0;
        if (maxCountExceededCallback == null) {
            throw new NullArgumentException();
        }
        this.maximalCount = i;
        this.maxCountCallback = maxCountExceededCallback;
    }

    public void setMaximalCount(int i) {
        this.maximalCount = i;
    }

    public int getMaximalCount() {
        return this.maximalCount;
    }

    public int getCount() {
        return this.count;
    }

    public boolean canIncrement() {
        return this.count < this.maximalCount;
    }

    public void incrementCount(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            incrementCount();
        }
    }

    public void incrementCount() {
        int i = this.count + 1;
        this.count = i;
        if (i > this.maximalCount) {
            this.maxCountCallback.trigger(this.maximalCount);
        }
    }

    public void resetCount() {
        this.count = 0;
    }
}
