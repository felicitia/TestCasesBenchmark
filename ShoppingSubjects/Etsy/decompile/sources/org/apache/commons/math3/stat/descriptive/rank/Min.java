package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

public class Min extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = -2941995784909003131L;
    private long n;
    private double value;

    public Min() {
        this.n = 0;
        this.value = Double.NaN;
    }

    public Min(Min min) throws NullArgumentException {
        copy(min, this);
    }

    public void increment(double d) {
        if (d < this.value || Double.isNaN(this.value)) {
            this.value = d;
        }
        this.n++;
    }

    public void clear() {
        this.value = Double.NaN;
        this.n = 0;
    }

    public double getResult() {
        return this.value;
    }

    public long getN() {
        return this.n;
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (!test(dArr, i, i2)) {
            return Double.NaN;
        }
        double d = dArr[i];
        for (int i3 = i; i3 < i + i2; i3++) {
            if (!Double.isNaN(dArr[i3]) && d >= dArr[i3]) {
                d = dArr[i3];
            }
        }
        return d;
    }

    public Min copy() {
        Min min = new Min();
        copy(this, min);
        return min;
    }

    public static void copy(Min min, Min min2) throws NullArgumentException {
        MathUtils.checkNotNull(min);
        MathUtils.checkNotNull(min2);
        min2.setData(min.getDataRef());
        min2.n = min.n;
        min2.value = min.value;
    }
}
