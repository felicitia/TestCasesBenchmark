package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

public class SecondMoment extends FirstMoment implements Serializable {
    private static final long serialVersionUID = 3942403127395076445L;
    protected double m2;

    public /* bridge */ /* synthetic */ long getN() {
        return super.getN();
    }

    public SecondMoment() {
        this.m2 = Double.NaN;
    }

    public SecondMoment(SecondMoment secondMoment) throws NullArgumentException {
        super(secondMoment);
        this.m2 = secondMoment.m2;
    }

    public void increment(double d) {
        if (this.n < 1) {
            this.m2 = 0.0d;
            this.m1 = 0.0d;
        }
        super.increment(d);
        this.m2 += (((double) this.n) - 1.0d) * this.dev * this.nDev;
    }

    public void clear() {
        super.clear();
        this.m2 = Double.NaN;
    }

    public double getResult() {
        return this.m2;
    }

    public SecondMoment copy() {
        SecondMoment secondMoment = new SecondMoment();
        copy(this, secondMoment);
        return secondMoment;
    }

    public static void copy(SecondMoment secondMoment, SecondMoment secondMoment2) throws NullArgumentException {
        MathUtils.checkNotNull(secondMoment);
        MathUtils.checkNotNull(secondMoment2);
        FirstMoment.copy(secondMoment, secondMoment2);
        secondMoment2.m2 = secondMoment.m2;
    }
}
