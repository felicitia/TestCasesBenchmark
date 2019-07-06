package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Skewness extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = 7101857578996691352L;
    protected boolean incMoment;
    protected ThirdMoment moment;

    public Skewness() {
        this.moment = null;
        this.incMoment = true;
        this.moment = new ThirdMoment();
    }

    public Skewness(ThirdMoment thirdMoment) {
        this.moment = null;
        this.incMoment = false;
        this.moment = thirdMoment;
    }

    public Skewness(Skewness skewness) throws NullArgumentException {
        this.moment = null;
        copy(skewness, this);
    }

    public void increment(double d) {
        if (this.incMoment) {
            this.moment.increment(d);
        }
    }

    public double getResult() {
        if (this.moment.n < 3) {
            return Double.NaN;
        }
        double d = this.moment.m2 / ((double) (this.moment.n - 1));
        if (d < 1.0E-19d) {
            return 0.0d;
        }
        double n = (double) this.moment.getN();
        return (this.moment.m3 * n) / ((((n - 1.0d) * (n - 2.0d)) * FastMath.sqrt(d)) * d);
    }

    public long getN() {
        return this.moment.getN();
    }

    public void clear() {
        if (this.incMoment) {
            this.moment.clear();
        }
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        int i3;
        double[] dArr2 = dArr;
        int i4 = i;
        int i5 = i2;
        if (!test(dArr, i, i2) || i5 <= 2) {
            return Double.NaN;
        }
        double evaluate = new Mean().evaluate(dArr2, i4, i5);
        double d = 0.0d;
        int i6 = i4;
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (true) {
            i3 = i4 + i5;
            if (i6 >= i3) {
                break;
            }
            double d4 = dArr2[i6] - evaluate;
            d2 += d4 * d4;
            d3 += d4;
            i6++;
        }
        double d5 = (double) i5;
        double d6 = (d2 - ((d3 * d3) / d5)) / ((double) (i5 - 1));
        while (i4 < i3) {
            double d7 = dArr2[i4] - evaluate;
            d += d7 * d7 * d7;
            i4++;
        }
        return (d5 / ((d5 - 1.0d) * (d5 - 2.0d))) * (d / (d6 * FastMath.sqrt(d6)));
    }

    public Skewness copy() {
        Skewness skewness = new Skewness();
        copy(this, skewness);
        return skewness;
    }

    public static void copy(Skewness skewness, Skewness skewness2) throws NullArgumentException {
        MathUtils.checkNotNull(skewness);
        MathUtils.checkNotNull(skewness2);
        skewness2.setData(skewness.getDataRef());
        skewness2.moment = new ThirdMoment(skewness.moment.copy());
        skewness2.incMoment = skewness.incMoment;
    }
}
