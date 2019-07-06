package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

public class SemiVariance extends AbstractUnivariateStatistic implements Serializable {
    public static final Direction DOWNSIDE_VARIANCE = Direction.DOWNSIDE;
    public static final Direction UPSIDE_VARIANCE = Direction.UPSIDE;
    private static final long serialVersionUID = -2653430366886024994L;
    private boolean biasCorrected = true;
    private Direction varianceDirection = Direction.DOWNSIDE;

    public enum Direction {
        UPSIDE(true),
        DOWNSIDE(false);
        
        private boolean direction;

        private Direction(boolean z) {
            this.direction = z;
        }

        /* access modifiers changed from: 0000 */
        public boolean getDirection() {
            return this.direction;
        }
    }

    public SemiVariance() {
    }

    public SemiVariance(boolean z) {
        this.biasCorrected = z;
    }

    public SemiVariance(Direction direction) {
        this.varianceDirection = direction;
    }

    public SemiVariance(boolean z, Direction direction) {
        this.biasCorrected = z;
        this.varianceDirection = direction;
    }

    public SemiVariance(SemiVariance semiVariance) throws NullArgumentException {
        copy(semiVariance, this);
    }

    public SemiVariance copy() {
        SemiVariance semiVariance = new SemiVariance();
        copy(this, semiVariance);
        return semiVariance;
    }

    public static void copy(SemiVariance semiVariance, SemiVariance semiVariance2) throws NullArgumentException {
        MathUtils.checkNotNull(semiVariance);
        MathUtils.checkNotNull(semiVariance2);
        semiVariance2.setData(semiVariance.getDataRef());
        semiVariance2.biasCorrected = semiVariance.biasCorrected;
        semiVariance2.varianceDirection = semiVariance.varianceDirection;
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return evaluate(dArr, new Mean().evaluate(dArr, i, i2), this.varianceDirection, this.biasCorrected, 0, dArr.length);
    }

    public double evaluate(double[] dArr, Direction direction) throws MathIllegalArgumentException {
        return evaluate(dArr, new Mean().evaluate(dArr), direction, this.biasCorrected, 0, dArr.length);
    }

    public double evaluate(double[] dArr, double d) throws MathIllegalArgumentException {
        return evaluate(dArr, d, this.varianceDirection, this.biasCorrected, 0, dArr.length);
    }

    public double evaluate(double[] dArr, double d, Direction direction) throws MathIllegalArgumentException {
        return evaluate(dArr, d, direction, this.biasCorrected, 0, dArr.length);
    }

    public double evaluate(double[] dArr, double d, Direction direction, boolean z, int i, int i2) throws MathIllegalArgumentException {
        test(dArr, i, i2);
        if (dArr.length == 0) {
            return Double.NaN;
        }
        double d2 = 0.0d;
        if (dArr.length == 1) {
            return 0.0d;
        }
        boolean direction2 = direction.getDirection();
        while (i < i2) {
            if ((dArr[i] > d) == direction2) {
                double d3 = dArr[i] - d;
                d2 += d3 * d3;
            }
            i++;
        }
        return z ? d2 / (((double) i2) - 1.0d) : d2 / ((double) i2);
    }

    public boolean isBiasCorrected() {
        return this.biasCorrected;
    }

    public void setBiasCorrected(boolean z) {
        this.biasCorrected = z;
    }

    public Direction getVarianceDirection() {
        return this.varianceDirection;
    }

    public void setVarianceDirection(Direction direction) {
        this.varianceDirection = direction;
    }
}
