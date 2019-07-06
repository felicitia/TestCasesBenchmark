package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

class EulerStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public EulerStepInterpolator() {
    }

    public EulerStepInterpolator(EulerStepInterpolator eulerStepInterpolator) {
        super(eulerStepInterpolator);
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new EulerStepInterpolator(this);
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        if (this.previousState == null || d > 0.5d) {
            for (int i = 0; i < this.interpolatedState.length; i++) {
                this.interpolatedState[i] = this.currentState[i] - (this.yDotK[0][i] * d2);
            }
            System.arraycopy(this.yDotK[0], 0, this.interpolatedDerivatives, 0, this.interpolatedDerivatives.length);
            return;
        }
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            this.interpolatedState[i2] = this.previousState[i2] + (this.h * d * this.yDotK[0][i2]);
        }
        System.arraycopy(this.yDotK[0], 0, this.interpolatedDerivatives, 0, this.interpolatedDerivatives.length);
    }
}
