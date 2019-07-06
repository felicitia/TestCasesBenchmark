package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class StepNormalizer implements StepHandler {
    private final StepNormalizerBounds bounds;
    private double firstTime;
    private boolean forward;
    private double h;
    private final FixedStepHandler handler;
    private double[] lastDerivatives;
    private double[] lastState;
    private double lastTime;
    private final StepNormalizerMode mode;

    public StepNormalizer(double d, FixedStepHandler fixedStepHandler) {
        this(d, fixedStepHandler, StepNormalizerMode.INCREMENT, StepNormalizerBounds.FIRST);
    }

    public StepNormalizer(double d, FixedStepHandler fixedStepHandler, StepNormalizerMode stepNormalizerMode) {
        this(d, fixedStepHandler, stepNormalizerMode, StepNormalizerBounds.FIRST);
    }

    public StepNormalizer(double d, FixedStepHandler fixedStepHandler, StepNormalizerBounds stepNormalizerBounds) {
        this(d, fixedStepHandler, StepNormalizerMode.INCREMENT, stepNormalizerBounds);
    }

    public StepNormalizer(double d, FixedStepHandler fixedStepHandler, StepNormalizerMode stepNormalizerMode, StepNormalizerBounds stepNormalizerBounds) {
        this.h = FastMath.abs(d);
        this.handler = fixedStepHandler;
        this.mode = stepNormalizerMode;
        this.bounds = stepNormalizerBounds;
        this.firstTime = Double.NaN;
        this.lastTime = Double.NaN;
        this.lastState = null;
        this.lastDerivatives = null;
        this.forward = true;
    }

    public void init(double d, double[] dArr, double d2) {
        this.firstTime = Double.NaN;
        this.lastTime = Double.NaN;
        this.lastState = null;
        this.lastDerivatives = null;
        this.forward = true;
        this.handler.init(d, dArr, d2);
    }

    public void handleStep(StepInterpolator stepInterpolator, boolean z) throws MaxCountExceededException {
        boolean z2 = false;
        if (this.lastState == null) {
            this.firstTime = stepInterpolator.getPreviousTime();
            this.lastTime = stepInterpolator.getPreviousTime();
            stepInterpolator.setInterpolatedTime(this.lastTime);
            this.lastState = (double[]) stepInterpolator.getInterpolatedState().clone();
            this.lastDerivatives = (double[]) stepInterpolator.getInterpolatedDerivatives().clone();
            this.forward = stepInterpolator.getCurrentTime() >= this.lastTime;
            if (!this.forward) {
                this.h = -this.h;
            }
        }
        double floor = this.mode == StepNormalizerMode.INCREMENT ? this.lastTime + this.h : (FastMath.floor(this.lastTime / this.h) + 1.0d) * this.h;
        if (this.mode == StepNormalizerMode.MULTIPLES && Precision.equals(floor, this.lastTime, 1)) {
            floor += this.h;
        }
        boolean isNextInStep = isNextInStep(floor, stepInterpolator);
        while (isNextInStep) {
            doNormalizedStep(false);
            storeStep(stepInterpolator, floor);
            floor += this.h;
            isNextInStep = isNextInStep(floor, stepInterpolator);
        }
        if (z) {
            if (this.bounds.lastIncluded() && this.lastTime != stepInterpolator.getCurrentTime()) {
                z2 = true;
            }
            doNormalizedStep(!z2);
            if (z2) {
                storeStep(stepInterpolator, stepInterpolator.getCurrentTime());
                doNormalizedStep(true);
            }
        }
    }

    private boolean isNextInStep(double d, StepInterpolator stepInterpolator) {
        if (this.forward) {
            if (d > stepInterpolator.getCurrentTime()) {
                return false;
            }
        } else if (d < stepInterpolator.getCurrentTime()) {
            return false;
        }
        return true;
    }

    private void doNormalizedStep(boolean z) {
        if (this.bounds.firstIncluded() || this.firstTime != this.lastTime) {
            this.handler.handleStep(this.lastTime, this.lastState, this.lastDerivatives, z);
        }
    }

    private void storeStep(StepInterpolator stepInterpolator, double d) throws MaxCountExceededException {
        this.lastTime = d;
        stepInterpolator.setInterpolatedTime(this.lastTime);
        System.arraycopy(stepInterpolator.getInterpolatedState(), 0, this.lastState, 0, this.lastState.length);
        System.arraycopy(stepInterpolator.getInterpolatedDerivatives(), 0, this.lastDerivatives, 0, this.lastDerivatives.length);
    }
}
