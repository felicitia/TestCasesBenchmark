package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class RombergIntegrator extends BaseAbstractUnivariateIntegrator {
    public static final int ROMBERG_MAX_ITERATIONS_COUNT = 32;

    public RombergIntegrator(double d, double d2, int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(d, d2, i, i2);
        if (i2 > 32) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), Integer.valueOf(32), false);
        }
    }

    public RombergIntegrator(int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(i, i2);
        if (i2 > 32) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), Integer.valueOf(32), false);
        }
    }

    public RombergIntegrator() {
        super(3, 32);
    }

    /* access modifiers changed from: protected */
    public double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
        int maximalCount = this.iterations.getMaximalCount() + 1;
        double[] dArr = new double[maximalCount];
        double[] dArr2 = new double[maximalCount];
        TrapezoidIntegrator trapezoidIntegrator = new TrapezoidIntegrator();
        char c = 0;
        dArr2[0] = trapezoidIntegrator.stage(this, 0);
        this.iterations.incrementCount();
        double d = dArr2[0];
        while (true) {
            int count = this.iterations.getCount();
            dArr[c] = trapezoidIntegrator.stage(this, count);
            this.iterations.incrementCount();
            int i = 1;
            while (i <= count) {
                double d2 = d;
                double d3 = (double) ((1 << (2 * i)) - 1);
                int i2 = i - 1;
                double d4 = dArr[i2];
                dArr[i] = d4 + ((d4 - dArr2[i2]) / d3);
                i++;
                d = d2;
            }
            double d5 = d;
            d = dArr[count];
            if (count >= getMinimalIterationCount()) {
                double abs = FastMath.abs(d - d5);
                if (abs <= getRelativeAccuracy() * (FastMath.abs(d5) + FastMath.abs(d)) * 0.5d || abs <= getAbsoluteAccuracy()) {
                    return d;
                }
            }
            c = 0;
            double[] dArr3 = dArr;
            dArr = dArr2;
            dArr2 = dArr3;
        }
        return d;
    }
}
