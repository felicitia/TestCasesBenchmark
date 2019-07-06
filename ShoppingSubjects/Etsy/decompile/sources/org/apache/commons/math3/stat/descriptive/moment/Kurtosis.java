package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Kurtosis extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = 2784465764798260919L;
    protected boolean incMoment;
    protected FourthMoment moment;

    public Kurtosis() {
        this.incMoment = true;
        this.moment = new FourthMoment();
    }

    public Kurtosis(FourthMoment fourthMoment) {
        this.incMoment = false;
        this.moment = fourthMoment;
    }

    public Kurtosis(Kurtosis kurtosis) throws NullArgumentException {
        copy(kurtosis, this);
    }

    public void increment(double d) {
        if (this.incMoment) {
            this.moment.increment(d);
        }
    }

    public double getResult() {
        if (this.moment.getN() <= 3) {
            return Double.NaN;
        }
        double d = this.moment.m2 / ((double) (this.moment.n - 1));
        if (this.moment.n <= 3 || d < 1.0E-19d) {
            return 0.0d;
        }
        double d2 = (double) this.moment.n;
        double d3 = d2 - 1.0d;
        return ((((d2 + 1.0d) * d2) * this.moment.getResult()) - (((this.moment.m2 * 3.0d) * this.moment.m2) * d3)) / ((((d3 * (d2 - 2.0d)) * (d2 - 3.0d)) * d) * d);
    }

    public void clear() {
        if (this.incMoment) {
            this.moment.clear();
        }
    }

    public long getN() {
        return this.moment.getN();
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        double[] dArr2 = dArr;
        int i3 = i;
        int i4 = i2;
        if (!test(dArr, i, i2) || i4 <= 3) {
            return Double.NaN;
        }
        Variance variance = new Variance();
        variance.incrementAll(dArr2, i3, i4);
        double d = variance.moment.m1;
        double sqrt = FastMath.sqrt(variance.getResult());
        double d2 = 0.0d;
        for (int i5 = i3; i5 < i3 + i4; i5++) {
            d2 += FastMath.pow(dArr2[i5] - d, 4.0d);
        }
        double d3 = (double) i4;
        double d4 = (d3 + 1.0d) * d3;
        double d5 = d3 - 1.0d;
        double d6 = d3 - 2.0d;
        double d7 = d3 - 3.0d;
        double d8 = d4 / ((d5 * d6) * d7);
        return (d8 * (d2 / FastMath.pow(sqrt, 4.0d))) - ((3.0d * FastMath.pow(d5, 2.0d)) / (d6 * d7));
    }

    public Kurtosis copy() {
        Kurtosis kurtosis = new Kurtosis();
        copy(this, kurtosis);
        return kurtosis;
    }

    public static void copy(Kurtosis kurtosis, Kurtosis kurtosis2) throws NullArgumentException {
        MathUtils.checkNotNull(kurtosis);
        MathUtils.checkNotNull(kurtosis2);
        kurtosis2.setData(kurtosis.getDataRef());
        kurtosis2.moment = kurtosis.moment.copy();
        kurtosis2.incMoment = kurtosis.incMoment;
    }
}
