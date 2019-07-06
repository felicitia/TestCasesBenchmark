package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.util.MathUtils;

public class Mean extends AbstractStorelessUnivariateStatistic implements Serializable, WeightedEvaluation {
    private static final long serialVersionUID = -1296043746617791564L;
    protected boolean incMoment;
    protected FirstMoment moment;

    public Mean() {
        this.incMoment = true;
        this.moment = new FirstMoment();
    }

    public Mean(FirstMoment firstMoment) {
        this.moment = firstMoment;
        this.incMoment = false;
    }

    public Mean(Mean mean) throws NullArgumentException {
        copy(mean, this);
    }

    public void increment(double d) {
        if (this.incMoment) {
            this.moment.increment(d);
        }
    }

    public void clear() {
        if (this.incMoment) {
            this.moment.clear();
        }
    }

    public double getResult() {
        return this.moment.m1;
    }

    public long getN() {
        return this.moment.getN();
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (!test(dArr, i, i2)) {
            return Double.NaN;
        }
        double d = (double) i2;
        double evaluate = new Sum().evaluate(dArr, i, i2) / d;
        double d2 = 0.0d;
        for (int i3 = i; i3 < i + i2; i3++) {
            d2 += dArr[i3] - evaluate;
        }
        return evaluate + (d2 / d);
    }

    public double evaluate(double[] dArr, double[] dArr2, int i, int i2) throws MathIllegalArgumentException {
        if (!test(dArr, dArr2, i, i2)) {
            return Double.NaN;
        }
        Sum sum = new Sum();
        double evaluate = sum.evaluate(dArr2, i, i2);
        double evaluate2 = sum.evaluate(dArr, dArr2, i, i2) / evaluate;
        double d = 0.0d;
        for (int i3 = i; i3 < i + i2; i3++) {
            d += dArr2[i3] * (dArr[i3] - evaluate2);
        }
        return evaluate2 + (d / evaluate);
    }

    public double evaluate(double[] dArr, double[] dArr2) throws MathIllegalArgumentException {
        return evaluate(dArr, dArr2, 0, dArr.length);
    }

    public Mean copy() {
        Mean mean = new Mean();
        copy(this, mean);
        return mean;
    }

    public static void copy(Mean mean, Mean mean2) throws NullArgumentException {
        MathUtils.checkNotNull(mean);
        MathUtils.checkNotNull(mean2);
        mean2.setData(mean.getDataRef());
        mean2.incMoment = mean.incMoment;
        mean2.moment = mean.moment.copy();
    }
}
