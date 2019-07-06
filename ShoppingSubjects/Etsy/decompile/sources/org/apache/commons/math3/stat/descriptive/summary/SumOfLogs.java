package org.apache.commons.math3.stat.descriptive.summary;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class SumOfLogs extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = -370076995648386763L;
    private int n;
    private double value;

    public SumOfLogs() {
        this.value = 0.0d;
        this.n = 0;
    }

    public SumOfLogs(SumOfLogs sumOfLogs) throws NullArgumentException {
        copy(sumOfLogs, this);
    }

    public void increment(double d) {
        this.value += FastMath.log(d);
        this.n++;
    }

    public double getResult() {
        return this.value;
    }

    public long getN() {
        return (long) this.n;
    }

    public void clear() {
        this.value = 0.0d;
        this.n = 0;
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (!test(dArr, i, i2, true)) {
            return Double.NaN;
        }
        double d = 0.0d;
        for (int i3 = i; i3 < i + i2; i3++) {
            d += FastMath.log(dArr[i3]);
        }
        return d;
    }

    public SumOfLogs copy() {
        SumOfLogs sumOfLogs = new SumOfLogs();
        copy(this, sumOfLogs);
        return sumOfLogs;
    }

    public static void copy(SumOfLogs sumOfLogs, SumOfLogs sumOfLogs2) throws NullArgumentException {
        MathUtils.checkNotNull(sumOfLogs);
        MathUtils.checkNotNull(sumOfLogs2);
        sumOfLogs2.setData(sumOfLogs.getDataRef());
        sumOfLogs2.n = sumOfLogs.n;
        sumOfLogs2.value = sumOfLogs.value;
    }
}
