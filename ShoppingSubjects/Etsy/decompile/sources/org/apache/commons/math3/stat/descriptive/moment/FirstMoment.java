package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

class FirstMoment extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = 6112755307178490473L;
    protected double dev;
    protected double m1;
    protected long n;
    protected double nDev;

    public FirstMoment() {
        this.n = 0;
        this.m1 = Double.NaN;
        this.dev = Double.NaN;
        this.nDev = Double.NaN;
    }

    public FirstMoment(FirstMoment firstMoment) throws NullArgumentException {
        copy(firstMoment, this);
    }

    public void increment(double d) {
        if (this.n == 0) {
            this.m1 = 0.0d;
        }
        this.n++;
        double d2 = (double) this.n;
        this.dev = d - this.m1;
        this.nDev = this.dev / d2;
        this.m1 += this.nDev;
    }

    public void clear() {
        this.m1 = Double.NaN;
        this.n = 0;
        this.dev = Double.NaN;
        this.nDev = Double.NaN;
    }

    public double getResult() {
        return this.m1;
    }

    public long getN() {
        return this.n;
    }

    public FirstMoment copy() {
        FirstMoment firstMoment = new FirstMoment();
        copy(this, firstMoment);
        return firstMoment;
    }

    public static void copy(FirstMoment firstMoment, FirstMoment firstMoment2) throws NullArgumentException {
        MathUtils.checkNotNull(firstMoment);
        MathUtils.checkNotNull(firstMoment2);
        firstMoment2.setData(firstMoment.getDataRef());
        firstMoment2.n = firstMoment.n;
        firstMoment2.m1 = firstMoment.m1;
        firstMoment2.dev = firstMoment.dev;
        firstMoment2.nDev = firstMoment.nDev;
    }
}
