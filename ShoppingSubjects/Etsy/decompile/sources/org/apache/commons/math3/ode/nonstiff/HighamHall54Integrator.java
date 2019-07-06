package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.util.FastMath;

public class HighamHall54Integrator extends EmbeddedRungeKuttaIntegrator {
    private static final String METHOD_NAME = "Higham-Hall 5(4)";
    private static final double[][] STATIC_A = {new double[]{0.2222222222222222d}, new double[]{0.08333333333333333d, 0.25d}, new double[]{0.125d, 0.0d, 0.375d}, new double[]{0.182d, -0.27d, 0.624d, 0.064d}, new double[]{-0.55d, 1.35d, 2.4d, -7.2d, 5.0d}, new double[]{0.08333333333333333d, 0.0d, 0.84375d, -1.3333333333333333d, 1.3020833333333333d, 0.10416666666666667d}};
    private static final double[] STATIC_B = {0.08333333333333333d, 0.0d, 0.84375d, -1.3333333333333333d, 1.3020833333333333d, 0.10416666666666667d, 0.0d};
    private static final double[] STATIC_C = {0.2222222222222222d, 0.3333333333333333d, 0.5d, 0.6d, 1.0d, 1.0d};
    private static final double[] STATIC_E = {-0.05d, 0.0d, 0.50625d, -1.2d, 0.78125d, 0.0625d, -0.1d};

    public int getOrder() {
        return 5;
    }

    public HighamHall54Integrator(double d, double d2, double d3, double d4) {
        super(METHOD_NAME, false, STATIC_C, STATIC_A, STATIC_B, (RungeKuttaStepInterpolator) new HighamHall54StepInterpolator(), d, d2, d3, d4);
    }

    public HighamHall54Integrator(double d, double d2, double[] dArr, double[] dArr2) {
        super(METHOD_NAME, false, STATIC_C, STATIC_A, STATIC_B, (RungeKuttaStepInterpolator) new HighamHall54StepInterpolator(), d, d2, dArr, dArr2);
    }

    /* access modifiers changed from: protected */
    public double estimateError(double[][] dArr, double[] dArr2, double[] dArr3, double d) {
        double d2 = 0.0d;
        for (int i = 0; i < this.mainSetDimension; i++) {
            double d3 = STATIC_E[0] * dArr[0][i];
            for (int i2 = 1; i2 < STATIC_E.length; i2++) {
                d3 += STATIC_E[i2] * dArr[i2][i];
            }
            double max = FastMath.max(FastMath.abs(dArr2[i]), FastMath.abs(dArr3[i]));
            double d4 = (d3 * d) / (this.vecAbsoluteTolerance == null ? this.scalAbsoluteTolerance + (this.scalRelativeTolerance * max) : this.vecAbsoluteTolerance[i] + (this.vecRelativeTolerance[i] * max));
            d2 += d4 * d4;
        }
        return FastMath.sqrt(d2 / ((double) this.mainSetDimension));
    }
}
