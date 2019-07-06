package org.apache.commons.math3.ode;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

public abstract class MultistepIntegrator extends AdaptiveStepsizeIntegrator {
    private double exp;
    private double maxGrowth;
    private double minReduction;
    private final int nSteps;
    protected Array2DRowRealMatrix nordsieck;
    private double safety;
    protected double[] scaled;
    private FirstOrderIntegrator starter;

    private class CountingDifferentialEquations implements FirstOrderDifferentialEquations {
        private final int dimension;

        public CountingDifferentialEquations(int i) {
            this.dimension = i;
        }

        public void computeDerivatives(double d, double[] dArr, double[] dArr2) throws MaxCountExceededException, DimensionMismatchException {
            MultistepIntegrator.this.computeDerivatives(d, dArr, dArr2);
        }

        public int getDimension() {
            return this.dimension;
        }
    }

    private static class InitializationCompletedMarkerException extends RuntimeException {
        private static final long serialVersionUID = -1914085471038046418L;

        public InitializationCompletedMarkerException() {
            super(null);
        }
    }

    private class NordsieckInitializer implements StepHandler {
        private int count = 0;
        private final double[] t;
        private final double[][] y;
        private final double[][] yDot;

        public void init(double d, double[] dArr, double d2) {
        }

        public NordsieckInitializer(int i, int i2) {
            this.t = new double[i];
            this.y = (double[][]) Array.newInstance(double.class, new int[]{i, i2});
            this.yDot = (double[][]) Array.newInstance(double.class, new int[]{i, i2});
        }

        public void handleStep(StepInterpolator stepInterpolator, boolean z) throws MaxCountExceededException {
            double previousTime = stepInterpolator.getPreviousTime();
            double currentTime = stepInterpolator.getCurrentTime();
            if (this.count == 0) {
                stepInterpolator.setInterpolatedTime(previousTime);
                this.t[0] = previousTime;
                System.arraycopy(stepInterpolator.getInterpolatedState(), 0, this.y[0], 0, this.y[0].length);
                System.arraycopy(stepInterpolator.getInterpolatedDerivatives(), 0, this.yDot[0], 0, this.yDot[0].length);
            }
            this.count++;
            stepInterpolator.setInterpolatedTime(currentTime);
            this.t[this.count] = currentTime;
            System.arraycopy(stepInterpolator.getInterpolatedState(), 0, this.y[this.count], 0, this.y[this.count].length);
            System.arraycopy(stepInterpolator.getInterpolatedDerivatives(), 0, this.yDot[this.count], 0, this.yDot[this.count].length);
            if (this.count == this.t.length - 1) {
                MultistepIntegrator.this.stepStart = this.t[0];
                MultistepIntegrator.this.stepSize = (this.t[this.t.length - 1] - this.t[0]) / ((double) (this.t.length - 1));
                MultistepIntegrator.this.scaled = (double[]) this.yDot[0].clone();
                for (int i = 0; i < MultistepIntegrator.this.scaled.length; i++) {
                    double[] dArr = MultistepIntegrator.this.scaled;
                    dArr[i] = dArr[i] * MultistepIntegrator.this.stepSize;
                }
                MultistepIntegrator.this.nordsieck = MultistepIntegrator.this.initializeHighOrderDerivatives(MultistepIntegrator.this.stepSize, this.t, this.y, this.yDot);
                throw new InitializationCompletedMarkerException();
            }
        }
    }

    public interface NordsieckTransformer {
        Array2DRowRealMatrix initializeHighOrderDerivatives(double d, double[] dArr, double[][] dArr2, double[][] dArr3);
    }

    /* access modifiers changed from: protected */
    public abstract Array2DRowRealMatrix initializeHighOrderDerivatives(double d, double[] dArr, double[][] dArr2, double[][] dArr3);

    protected MultistepIntegrator(String str, int i, int i2, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        int i3 = i;
        super(str, d, d2, d3, d4);
        if (i3 < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.INTEGRATION_METHOD_NEEDS_AT_LEAST_TWO_PREVIOUS_POINTS, Integer.valueOf(i), Integer.valueOf(2), true);
        }
        DormandPrince853Integrator dormandPrince853Integrator = new DormandPrince853Integrator(d, d2, d3, d4);
        this.starter = dormandPrince853Integrator;
        this.nSteps = i3;
        this.exp = -1.0d / ((double) i2);
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(FastMath.pow(2.0d, -this.exp));
    }

    protected MultistepIntegrator(String str, int i, int i2, double d, double d2, double[] dArr, double[] dArr2) {
        super(str, d, d2, dArr, dArr2);
        DormandPrince853Integrator dormandPrince853Integrator = new DormandPrince853Integrator(d, d2, dArr, dArr2);
        this.starter = dormandPrince853Integrator;
        this.nSteps = i;
        this.exp = -1.0d / ((double) i2);
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(FastMath.pow(2.0d, -this.exp));
    }

    public ODEIntegrator getStarterIntegrator() {
        return this.starter;
    }

    public void setStarterIntegrator(FirstOrderIntegrator firstOrderIntegrator) {
        this.starter = firstOrderIntegrator;
    }

    /* access modifiers changed from: protected */
    public void start(double d, double[] dArr, double d2) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException {
        this.starter.clearEventHandlers();
        this.starter.clearStepHandlers();
        this.starter.addStepHandler(new NordsieckInitializer(this.nSteps, dArr.length));
        try {
            this.starter.integrate(new CountingDifferentialEquations(dArr.length), d, dArr, d2, new double[dArr.length]);
        } catch (InitializationCompletedMarkerException unused) {
        }
        this.starter.clearStepHandlers();
    }

    public double getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(double d) {
        this.minReduction = d;
    }

    public double getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(double d) {
        this.maxGrowth = d;
    }

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double d) {
        this.safety = d;
    }

    /* access modifiers changed from: protected */
    public double computeStepGrowShrinkFactor(double d) {
        return FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(d, this.exp)));
    }
}
