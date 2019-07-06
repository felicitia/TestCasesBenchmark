package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

class MidpointStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public MidpointStepInterpolator() {
    }

    public MidpointStepInterpolator(MidpointStepInterpolator midpointStepInterpolator) {
        super(midpointStepInterpolator);
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new MidpointStepInterpolator(this);
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = 2.0d * d;
        double d4 = 1.0d - d3;
        if (this.previousState == null || d > 0.5d) {
            double d5 = d2 * d;
            double d6 = d2 * (1.0d + d);
            for (int i = 0; i < this.interpolatedState.length; i++) {
                double d7 = this.yDotK[0][i];
                double d8 = this.yDotK[1][i];
                this.interpolatedState[i] = (this.currentState[i] + (d5 * d7)) - (d6 * d8);
                this.interpolatedDerivatives[i] = (d7 * d4) + (d8 * d3);
            }
            return;
        }
        double d9 = d * d2;
        double d10 = d * d * this.h;
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            double d11 = this.yDotK[0][i2];
            double d12 = this.yDotK[1][i2];
            this.interpolatedState[i2] = this.previousState[i2] + (d9 * d11) + (d10 * d12);
            this.interpolatedDerivatives[i2] = (d11 * d4) + (d12 * d3);
        }
    }
}
