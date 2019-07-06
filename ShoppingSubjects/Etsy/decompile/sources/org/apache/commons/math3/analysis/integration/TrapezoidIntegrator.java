package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class TrapezoidIntegrator extends BaseAbstractUnivariateIntegrator {
    public static final int TRAPEZOID_MAX_ITERATIONS_COUNT = 64;
    private double s;

    public TrapezoidIntegrator(double d, double d2, int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(d, d2, i, i2);
        if (i2 > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), Integer.valueOf(64), false);
        }
    }

    public TrapezoidIntegrator(int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(i, i2);
        if (i2 > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), Integer.valueOf(64), false);
        }
    }

    public TrapezoidIntegrator() {
        super(3, 64);
    }

    /* access modifiers changed from: 0000 */
    public double stage(BaseAbstractUnivariateIntegrator baseAbstractUnivariateIntegrator, int i) throws TooManyEvaluationsException {
        BaseAbstractUnivariateIntegrator baseAbstractUnivariateIntegrator2 = baseAbstractUnivariateIntegrator;
        if (i == 0) {
            double max = baseAbstractUnivariateIntegrator.getMax();
            double min = baseAbstractUnivariateIntegrator.getMin();
            this.s = 0.5d * (max - min) * (baseAbstractUnivariateIntegrator2.computeObjectiveValue(min) + baseAbstractUnivariateIntegrator2.computeObjectiveValue(max));
            return this.s;
        }
        long j = 1 << (i - 1);
        double max2 = baseAbstractUnivariateIntegrator.getMax();
        double min2 = baseAbstractUnivariateIntegrator.getMin();
        double d = (max2 - min2) / ((double) j);
        double d2 = min2 + (0.5d * d);
        double d3 = 0.0d;
        for (long j2 = 0; j2 < j; j2++) {
            d3 += baseAbstractUnivariateIntegrator2.computeObjectiveValue(d2);
            d2 += d;
        }
        this.s = 0.5d * (this.s + (d3 * d));
        return this.s;
    }

    /* access modifiers changed from: protected */
    public double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
        double stage;
        double stage2 = stage(this, 0);
        this.iterations.incrementCount();
        while (true) {
            int count = this.iterations.getCount();
            stage = stage(this, count);
            if (count >= getMinimalIterationCount()) {
                double abs = FastMath.abs(stage - stage2);
                if (abs <= getRelativeAccuracy() * (FastMath.abs(stage2) + FastMath.abs(stage)) * 0.5d || abs <= getAbsoluteAccuracy()) {
                    return stage;
                }
            }
            this.iterations.incrementCount();
            stage2 = stage;
        }
        return stage;
    }
}
