package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class IterativeLegendreGaussIntegrator extends BaseAbstractUnivariateIntegrator {
    private static final GaussIntegratorFactory FACTORY = new GaussIntegratorFactory();
    private final int numberOfPoints;

    public IterativeLegendreGaussIntegrator(int i, double d, double d2, int i2, int i3) throws NotStrictlyPositiveException, NumberIsTooSmallException {
        super(d, d2, i2, i3);
        this.numberOfPoints = i;
    }

    public IterativeLegendreGaussIntegrator(int i, double d, double d2) {
        this(i, d, d2, 3, Integer.MAX_VALUE);
    }

    public IterativeLegendreGaussIntegrator(int i, int i2, int i3) {
        this(i, 1.0E-6d, 1.0E-15d, i2, i3);
    }

    /* access modifiers changed from: protected */
    public double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
        double stage = stage(1);
        int i = 2;
        while (true) {
            double stage2 = stage(i);
            double abs = FastMath.abs(stage2 - stage);
            double max = FastMath.max(getAbsoluteAccuracy(), getRelativeAccuracy() * (FastMath.abs(stage) + FastMath.abs(stage2)) * 0.5d);
            if (this.iterations.getCount() + 1 >= getMinimalIterationCount() && abs <= max) {
                return stage2;
            }
            i = FastMath.max((int) (FastMath.min(4.0d, FastMath.pow(abs / max, 0.5d / ((double) this.numberOfPoints))) * ((double) i)), i + 1);
            this.iterations.incrementCount();
            stage = stage2;
        }
    }

    private double stage(int i) throws TooManyEvaluationsException {
        int i2 = i;
        AnonymousClass1 r2 = new UnivariateFunction() {
            public double value(double d) {
                return IterativeLegendreGaussIntegrator.this.computeObjectiveValue(d);
            }
        };
        double min = getMin();
        double max = (getMax() - min) / ((double) i2);
        double d = 0.0d;
        for (int i3 = 0; i3 < i2; i3++) {
            double d2 = min + (((double) i3) * max);
            d += FACTORY.legendreHighPrecision(this.numberOfPoints, d2, d2 + max).integrate(r2);
        }
        return d;
    }
}
