package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

class GillStepInterpolator extends RungeKuttaStepInterpolator {
    private static final double ONE_MINUS_INV_SQRT_2 = (1.0d - FastMath.sqrt(0.5d));
    private static final double ONE_PLUS_INV_SQRT_2 = (1.0d + FastMath.sqrt(0.5d));
    private static final long serialVersionUID = 20111120;

    public GillStepInterpolator() {
    }

    public GillStepInterpolator(GillStepInterpolator gillStepInterpolator) {
        super(gillStepInterpolator);
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new GillStepInterpolator(this);
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = 2.0d * d;
        double d4 = d3 * d3;
        double d5 = ((d3 - 3.0d) * d) + 1.0d;
        double d6 = (1.0d - d) * d3;
        double d7 = ONE_MINUS_INV_SQRT_2 * d6;
        double d8 = d6 * ONE_PLUS_INV_SQRT_2;
        double d9 = (d3 - 1.0d) * d;
        if (this.previousState == null || d > 0.5d) {
            double d10 = d2 / 6.0d;
            double d11 = ((2.0d + d3) - d4) * d10;
            double d12 = ((1.0d - (5.0d * d)) + d4) * d10;
            double d13 = ONE_MINUS_INV_SQRT_2 * d11;
            double d14 = d11 * ONE_PLUS_INV_SQRT_2;
            double d15 = d10 * (1.0d + d + d4);
            for (int i = 0; i < this.interpolatedState.length; i++) {
                double d16 = this.yDotK[0][i];
                double d17 = this.yDotK[1][i];
                double d18 = this.yDotK[2][i];
                double d19 = this.yDotK[3][i];
                this.interpolatedState[i] = (((this.currentState[i] - (d12 * d16)) - (d13 * d17)) - (d14 * d18)) - (d15 * d19);
                this.interpolatedDerivatives[i] = (d16 * d5) + (d17 * d7) + (d18 * d8) + (d19 * d9);
            }
            return;
        }
        double d20 = (this.h * d) / 6.0d;
        double d21 = ((6.0d * d) - d4) * d20;
        double d22 = ((6.0d - (9.0d * d)) + d4) * d20;
        double d23 = ONE_MINUS_INV_SQRT_2 * d21;
        double d24 = d21 * ONE_PLUS_INV_SQRT_2;
        double d25 = d20 * ((-3.0d * d) + d4);
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            double d26 = this.yDotK[0][i2];
            double d27 = this.yDotK[1][i2];
            double d28 = this.yDotK[2][i2];
            double d29 = this.yDotK[3][i2];
            this.interpolatedState[i2] = this.previousState[i2] + (d22 * d26) + (d23 * d27) + (d24 * d28) + (d25 * d29);
            this.interpolatedDerivatives[i2] = (d26 * d5) + (d27 * d7) + (d28 * d8) + (d29 * d9);
        }
    }
}
