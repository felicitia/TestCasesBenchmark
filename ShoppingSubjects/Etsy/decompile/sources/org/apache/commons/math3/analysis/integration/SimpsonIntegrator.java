package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class SimpsonIntegrator extends BaseAbstractUnivariateIntegrator {
    public static final int SIMPSON_MAX_ITERATIONS_COUNT = 64;

    public SimpsonIntegrator(double d, double d2, int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(d, d2, i, i2);
        if (i2 > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), Integer.valueOf(64), false);
        }
    }

    public SimpsonIntegrator(int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(i, i2);
        if (i2 > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), Integer.valueOf(64), false);
        }
    }

    public SimpsonIntegrator() {
        super(3, 64);
    }

    /* access modifiers changed from: protected */
    public double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
        double d;
        TrapezoidIntegrator trapezoidIntegrator = new TrapezoidIntegrator();
        if (getMinimalIterationCount() == 1) {
            return ((4.0d * trapezoidIntegrator.stage(this, 1)) - trapezoidIntegrator.stage(this, 0)) / 3.0d;
        }
        double d2 = 0.0d;
        double stage = trapezoidIntegrator.stage(this, 0);
        while (true) {
            double stage2 = trapezoidIntegrator.stage(this, this.iterations.getCount());
            this.iterations.incrementCount();
            d = ((4.0d * stage2) - stage) / 3.0d;
            if (this.iterations.getCount() >= getMinimalIterationCount()) {
                double abs = FastMath.abs(d - d2);
                if (abs <= getRelativeAccuracy() * (FastMath.abs(d2) + FastMath.abs(d)) * 0.5d || abs <= getAbsoluteAccuracy()) {
                    return d;
                }
            }
            d2 = d;
            stage = stage2;
        }
        return d;
    }
}
