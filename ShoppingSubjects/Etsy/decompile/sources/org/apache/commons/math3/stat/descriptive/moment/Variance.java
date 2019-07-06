package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
import org.apache.commons.math3.util.MathUtils;

public class Variance extends AbstractStorelessUnivariateStatistic implements Serializable, WeightedEvaluation {
    private static final long serialVersionUID = -9111962718267217978L;
    protected boolean incMoment;
    private boolean isBiasCorrected;
    protected SecondMoment moment;

    public Variance() {
        this.moment = null;
        this.incMoment = true;
        this.isBiasCorrected = true;
        this.moment = new SecondMoment();
    }

    public Variance(SecondMoment secondMoment) {
        this.moment = null;
        this.incMoment = true;
        this.isBiasCorrected = true;
        this.incMoment = false;
        this.moment = secondMoment;
    }

    public Variance(boolean z) {
        this.moment = null;
        this.incMoment = true;
        this.isBiasCorrected = true;
        this.moment = new SecondMoment();
        this.isBiasCorrected = z;
    }

    public Variance(boolean z, SecondMoment secondMoment) {
        this.moment = null;
        this.incMoment = true;
        this.isBiasCorrected = true;
        this.incMoment = false;
        this.moment = secondMoment;
        this.isBiasCorrected = z;
    }

    public Variance(Variance variance) throws NullArgumentException {
        this.moment = null;
        this.incMoment = true;
        this.isBiasCorrected = true;
        copy(variance, this);
    }

    public void increment(double d) {
        if (this.incMoment) {
            this.moment.increment(d);
        }
    }

    public double getResult() {
        if (this.moment.n == 0) {
            return Double.NaN;
        }
        if (this.moment.n == 1) {
            return 0.0d;
        }
        if (this.isBiasCorrected) {
            return this.moment.m2 / (((double) this.moment.n) - 1.0d);
        }
        return this.moment.m2 / ((double) this.moment.n);
    }

    public long getN() {
        return this.moment.getN();
    }

    public void clear() {
        if (this.incMoment) {
            this.moment.clear();
        }
    }

    public double evaluate(double[] dArr) throws MathIllegalArgumentException {
        if (dArr != null) {
            return evaluate(dArr, 0, dArr.length);
        }
        throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (test(dArr, i, i2)) {
            clear();
            if (i2 == 1) {
                return 0.0d;
            }
            if (i2 > 1) {
                return evaluate(dArr, new Mean().evaluate(dArr, i, i2), i, i2);
            }
        }
        return Double.NaN;
    }

    public double evaluate(double[] dArr, double[] dArr2, int i, int i2) throws MathIllegalArgumentException {
        if (test(dArr, dArr2, i, i2)) {
            clear();
            if (i2 == 1) {
                return 0.0d;
            }
            if (i2 > 1) {
                return evaluate(dArr, dArr2, new Mean().evaluate(dArr, dArr2, i, i2), i, i2);
            }
        }
        return Double.NaN;
    }

    public double evaluate(double[] dArr, double[] dArr2) throws MathIllegalArgumentException {
        return evaluate(dArr, dArr2, 0, dArr.length);
    }

    public double evaluate(double[] dArr, double d, int i, int i2) throws MathIllegalArgumentException {
        double d2 = 0.0d;
        if (test(dArr, i, i2)) {
            if (i2 == 1) {
                return 0.0d;
            }
            if (i2 > 1) {
                double d3 = 0.0d;
                for (int i3 = i; i3 < i + i2; i3++) {
                    double d4 = dArr[i3] - d;
                    d2 += d4 * d4;
                    d3 += d4;
                }
                double d5 = (double) i2;
                return this.isBiasCorrected ? (d2 - ((d3 * d3) / d5)) / (d5 - 1.0d) : (d2 - ((d3 * d3) / d5)) / d5;
            }
        }
        return Double.NaN;
    }

    public double evaluate(double[] dArr, double d) throws MathIllegalArgumentException {
        return evaluate(dArr, d, 0, dArr.length);
    }

    public double evaluate(double[] dArr, double[] dArr2, double d, int i, int i2) throws MathIllegalArgumentException {
        int i3;
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        int i4 = i;
        int i5 = i2;
        double d2 = 0.0d;
        if (test(dArr3, dArr4, i4, i5)) {
            if (i5 == 1) {
                return 0.0d;
            }
            if (i5 > 1) {
                int i6 = i4;
                double d3 = 0.0d;
                double d4 = 0.0d;
                while (true) {
                    i3 = i4 + i5;
                    if (i6 >= i3) {
                        break;
                    }
                    double d5 = dArr3[i6] - d;
                    d3 += dArr4[i6] * d5 * d5;
                    d4 += dArr4[i6] * d5;
                    i6++;
                }
                while (i4 < i3) {
                    d2 += dArr4[i4];
                    i4++;
                }
                return this.isBiasCorrected ? (d3 - ((d4 * d4) / d2)) / (d2 - 1.0d) : (d3 - ((d4 * d4) / d2)) / d2;
            }
        }
        return Double.NaN;
    }

    public double evaluate(double[] dArr, double[] dArr2, double d) throws MathIllegalArgumentException {
        return evaluate(dArr, dArr2, d, 0, dArr.length);
    }

    public boolean isBiasCorrected() {
        return this.isBiasCorrected;
    }

    public void setBiasCorrected(boolean z) {
        this.isBiasCorrected = z;
    }

    public Variance copy() {
        Variance variance = new Variance();
        copy(this, variance);
        return variance;
    }

    public static void copy(Variance variance, Variance variance2) throws NullArgumentException {
        MathUtils.checkNotNull(variance);
        MathUtils.checkNotNull(variance2);
        variance2.setData(variance.getDataRef());
        variance2.moment = variance.moment.copy();
        variance2.isBiasCorrected = variance.isBiasCorrected;
        variance2.incMoment = variance.incMoment;
    }
}
