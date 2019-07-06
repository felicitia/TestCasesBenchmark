package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

class ClassicalRungeKuttaStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public ClassicalRungeKuttaStepInterpolator() {
    }

    public ClassicalRungeKuttaStepInterpolator(ClassicalRungeKuttaStepInterpolator classicalRungeKuttaStepInterpolator) {
        super(classicalRungeKuttaStepInterpolator);
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new ClassicalRungeKuttaStepInterpolator(this);
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = d;
        double d4 = 1.0d - d3;
        double d5 = 2.0d * d3;
        double d6 = 1.0d - d5;
        double d7 = d4 * d6;
        double d8 = d5 * d4;
        double d9 = (-d3) * d6;
        if (this.previousState == null || d3 > 0.5d) {
            double d10 = d7;
            double d11 = 4.0d * d3;
            double d12 = d2 / 6.0d;
            double d13 = -d11;
            double d14 = (((d13 + 5.0d) * d3) - 1.0d) * d12;
            double d15 = (((d11 - 2.0d) * d3) - 2.0d) * d12;
            double d16 = d12 * (((d13 - 1.0d) * d3) - 1.0d);
            for (int i = 0; i < this.interpolatedState.length; i++) {
                double d17 = this.yDotK[0][i];
                double d18 = this.yDotK[1][i] + this.yDotK[2][i];
                double d19 = this.yDotK[3][i];
                this.interpolatedState[i] = this.currentState[i] + (d14 * d17) + (d15 * d18) + (d16 * d19);
                this.interpolatedDerivatives[i] = (d17 * d10) + (d18 * d8) + (d19 * d9);
            }
            return;
        }
        double d20 = 4.0d * d3 * d3;
        double d21 = (this.h * d3) / 6.0d;
        double d22 = ((6.0d - (9.0d * d3)) + d20) * d21;
        double d23 = ((6.0d * d3) - d20) * d21;
        double d24 = d21 * ((-3.0d * d3) + d20);
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            double d25 = this.yDotK[0][i2];
            double d26 = this.yDotK[1][i2] + this.yDotK[2][i2];
            double d27 = this.yDotK[3][i2];
            this.interpolatedState[i2] = this.previousState[i2] + (d22 * d25) + (d23 * d26) + (d24 * d27);
            this.interpolatedDerivatives[i2] = (d25 * d7) + (d26 * d8) + (d27 * d9);
        }
    }
}
