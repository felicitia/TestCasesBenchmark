package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

public class Max extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = -5593383832225844641L;
    private long n;
    private double value;

    public Max() {
        this.n = 0;
        this.value = Double.NaN;
    }

    public Max(Max max) throws NullArgumentException {
        copy(max, this);
    }

    public void increment(double d) {
        if (d > this.value || Double.isNaN(this.value)) {
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
            if (!Double.isNaN(dArr[i3]) && d <= dArr[i3]) {
                d = dArr[i3];
            }
        }
        return d;
    }

    public Max copy() {
        Max max = new Max();
        copy(this, max);
        return max;
    }

    public static void copy(Max max, Max max2) throws NullArgumentException {
        MathUtils.checkNotNull(max);
        MathUtils.checkNotNull(max2);
        max2.setData(max.getDataRef());
        max2.n = max.n;
        max2.value = max.value;
    }
}
