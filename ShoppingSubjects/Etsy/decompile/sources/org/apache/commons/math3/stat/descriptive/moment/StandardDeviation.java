package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class StandardDeviation extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = 5728716329662425188L;
    private Variance variance;

    public StandardDeviation() {
        this.variance = null;
        this.variance = new Variance();
    }

    public StandardDeviation(SecondMoment secondMoment) {
        this.variance = null;
        this.variance = new Variance(secondMoment);
    }

    public StandardDeviation(StandardDeviation standardDeviation) throws NullArgumentException {
        this.variance = null;
        copy(standardDeviation, this);
    }

    public StandardDeviation(boolean z) {
        this.variance = null;
        this.variance = new Variance(z);
    }

    public StandardDeviation(boolean z, SecondMoment secondMoment) {
        this.variance = null;
        this.variance = new Variance(z, secondMoment);
    }

    public void increment(double d) {
        this.variance.increment(d);
    }

    public long getN() {
        return this.variance.getN();
    }

    public double getResult() {
        return FastMath.sqrt(this.variance.getResult());
    }

    public void clear() {
        this.variance.clear();
    }

    public double evaluate(double[] dArr) throws MathIllegalArgumentException {
        return FastMath.sqrt(this.variance.evaluate(dArr));
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return FastMath.sqrt(this.variance.evaluate(dArr, i, i2));
    }

    public double evaluate(double[] dArr, double d, int i, int i2) throws MathIllegalArgumentException {
        return FastMath.sqrt(this.variance.evaluate(dArr, d, i, i2));
    }

    public double evaluate(double[] dArr, double d) throws MathIllegalArgumentException {
        return FastMath.sqrt(this.variance.evaluate(dArr, d));
    }

    public boolean isBiasCorrected() {
        return this.variance.isBiasCorrected();
    }

    public void setBiasCorrected(boolean z) {
        this.variance.setBiasCorrected(z);
    }

    public StandardDeviation copy() {
        StandardDeviation standardDeviation = new StandardDeviation();
        copy(this, standardDeviation);
        return standardDeviation;
    }

    public static void copy(StandardDeviation standardDeviation, StandardDeviation standardDeviation2) throws NullArgumentException {
        MathUtils.checkNotNull(standardDeviation);
        MathUtils.checkNotNull(standardDeviation2);
        standardDeviation2.setData(standardDeviation.getDataRef());
        standardDeviation2.variance = standardDeviation.variance.copy();
    }
}
