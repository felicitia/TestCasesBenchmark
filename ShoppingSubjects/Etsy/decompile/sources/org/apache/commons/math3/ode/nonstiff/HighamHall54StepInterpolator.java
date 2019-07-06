package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

class HighamHall54StepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public HighamHall54StepInterpolator() {
    }

    public HighamHall54StepInterpolator(HighamHall54StepInterpolator highamHall54StepInterpolator) {
        super(highamHall54StepInterpolator);
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new HighamHall54StepInterpolator(this);
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = (d * (-7.5d + (d * (16.0d - (10.0d * d))))) + 1.0d;
        double d4 = d * (28.6875d + (d * (-91.125d + (67.5d * d))));
        double d5 = d * (-44.0d + (d * (152.0d - (120.0d * d))));
        double d6 = d * (23.4375d + (d * (-78.125d + (62.5d * d))));
        double d7 = 5.0d * d;
        double d8 = (d7 / 8.0d) * ((2.0d * d) - 1.0d);
        if (this.previousState == null || d > 0.5d) {
            double d9 = d * d;
            double d10 = d6;
            double d11 = this.h * (-0.08333333333333333d + ((1.0d + (d * (-3.75d + (d * (5.333333333333333d + ((-5.0d * d) / 2.0d)))))) * d));
            double d12 = d5;
            double d13 = this.h * (-0.84375d + ((14.34375d + (d * (-30.375d + ((135.0d * d) / 8.0d)))) * d9));
            double d14 = d4;
            double d15 = this.h * (1.3333333333333333d + ((-22.0d + (d * (50.666666666666664d + (-30.0d * d)))) * d9));
            double d16 = d3;
            double d17 = this.h * (-1.3020833333333333d + ((11.71875d + (d * (-26.041666666666668d + ((125.0d * d) / 8.0d)))) * d9));
            double d18 = this.h * (-0.10416666666666667d + (d9 * (-0.3125d + (d7 / 12.0d))));
            for (int i = 0; i < this.interpolatedState.length; i++) {
                double d19 = this.yDotK[0][i];
                double d20 = this.yDotK[2][i];
                double d21 = this.yDotK[3][i];
                double d22 = this.yDotK[4][i];
                double d23 = this.yDotK[5][i];
                this.interpolatedState[i] = this.currentState[i] + (d11 * d19) + (d13 * d20) + (d15 * d21) + (d17 * d22) + (d18 * d23);
                this.interpolatedDerivatives[i] = (d19 * d16) + (d20 * d14) + (d12 * d21) + (d10 * d22) + (d23 * d8);
            }
            return;
        }
        double d24 = this.h * d;
        double d25 = (1.0d + (d * (-3.75d + (d * (5.333333333333333d - (2.5d * d)))))) * d24;
        double d26 = d * (14.34375d + (d * (-30.375d + ((135.0d * d) / 8.0d)))) * d24;
        double d27 = d * (-22.0d + (d * (50.666666666666664d + (-30.0d * d)))) * d24;
        double d28 = d * (11.71875d + (d * (-26.041666666666668d + ((125.0d * d) / 8.0d)))) * d24;
        double d29 = d24 * d * (-0.3125d + (d7 / 12.0d));
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            double d30 = this.yDotK[0][i2];
            double d31 = this.yDotK[2][i2];
            double d32 = this.yDotK[3][i2];
            double d33 = this.yDotK[4][i2];
            double d34 = this.yDotK[5][i2];
            this.interpolatedState[i2] = this.previousState[i2] + (d25 * d30) + (d26 * d31) + (d27 * d32) + (d28 * d33) + (d29 * d34);
            this.interpolatedDerivatives[i2] = (d30 * d3) + (d31 * d4) + (d32 * d5) + (d33 * d6) + (d34 * d8);
        }
    }
}
