package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

class ThreeEighthesStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public ThreeEighthesStepInterpolator() {
    }

    public ThreeEighthesStepInterpolator(ThreeEighthesStepInterpolator threeEighthesStepInterpolator) {
        super(threeEighthesStepInterpolator);
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new ThreeEighthesStepInterpolator(this);
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = 0.75d * d;
        double d4 = 4.0d * d;
        double d5 = ((d4 - 5.0d) * d3) + 1.0d;
        double d6 = (5.0d - (6.0d * d)) * d3;
        double d7 = ((2.0d * d) - 1.0d) * d3;
        if (this.previousState == null || d > 0.5d) {
            double d8 = d3;
            double d9 = d6;
            double d10 = d2 / 8.0d;
            double d11 = d4 * d;
            double d12 = ((1.0d - (7.0d * d)) + (2.0d * d11)) * d10;
            double d13 = 3.0d * d10;
            double d14 = 1.0d + d;
            double d15 = (d14 - d11) * d13;
            double d16 = d13 * d14;
            double d17 = d10 * (d14 + d11);
            int i = 0;
            while (i < this.interpolatedState.length) {
                double d18 = this.yDotK[0][i];
                double d19 = this.yDotK[1][i];
                double d20 = this.yDotK[2][i];
                double d21 = this.yDotK[3][i];
                double d22 = d5;
                this.interpolatedState[i] = (((this.currentState[i] - (d12 * d18)) - (d15 * d19)) - (d16 * d20)) - (d17 * d21);
                this.interpolatedDerivatives[i] = (d22 * d18) + (d19 * d9) + (d8 * d20) + (d21 * d7);
                i++;
                d5 = d22;
            }
            return;
        }
        double d23 = d6;
        double d24 = (d * this.h) / 8.0d;
        double d25 = d4 * d;
        double d26 = ((8.0d - (15.0d * d)) + (2.0d * d25)) * d24;
        double d27 = 3.0d * d24;
        double d28 = ((5.0d * d) - d25) * d27;
        double d29 = d27 * d;
        double d30 = d24 * ((-3.0d * d) + d25);
        int i2 = 0;
        while (i2 < this.interpolatedState.length) {
            double d31 = this.yDotK[0][i2];
            double d32 = this.yDotK[1][i2];
            double d33 = this.yDotK[2][i2];
            double d34 = this.yDotK[3][i2];
            double d35 = d3;
            this.interpolatedState[i2] = this.previousState[i2] + (d26 * d31) + (d28 * d32) + (d29 * d33) + (d30 * d34);
            this.interpolatedDerivatives[i2] = (d31 * d5) + (d32 * d23) + (d35 * d33) + (d34 * d7);
            i2++;
            d3 = d35;
        }
    }
}
