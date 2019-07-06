package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.util.FastMath;

public abstract class AdaptiveStepsizeIntegrator extends AbstractIntegrator {
    private double initialStep;
    protected int mainSetDimension;
    private double maxStep;
    private double minStep;
    protected double scalAbsoluteTolerance;
    protected double scalRelativeTolerance;
    protected double[] vecAbsoluteTolerance;
    protected double[] vecRelativeTolerance;

    public abstract void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

    public AdaptiveStepsizeIntegrator(String str, double d, double d2, double d3, double d4) {
        super(str);
        setStepSizeControl(d, d2, d3, d4);
        resetInternalState();
    }

    public AdaptiveStepsizeIntegrator(String str, double d, double d2, double[] dArr, double[] dArr2) {
        super(str);
        setStepSizeControl(d, d2, dArr, dArr2);
        resetInternalState();
    }

    public void setStepSizeControl(double d, double d2, double d3, double d4) {
        this.minStep = FastMath.abs(d);
        this.maxStep = FastMath.abs(d2);
        this.initialStep = -1.0d;
        this.scalAbsoluteTolerance = d3;
        this.scalRelativeTolerance = d4;
        this.vecAbsoluteTolerance = null;
        this.vecRelativeTolerance = null;
    }

    public void setStepSizeControl(double d, double d2, double[] dArr, double[] dArr2) {
        this.minStep = FastMath.abs(d);
        this.maxStep = FastMath.abs(d2);
        this.initialStep = -1.0d;
        this.scalAbsoluteTolerance = 0.0d;
        this.scalRelativeTolerance = 0.0d;
        this.vecAbsoluteTolerance = (double[]) dArr.clone();
        this.vecRelativeTolerance = (double[]) dArr2.clone();
    }

    public void setInitialStepSize(double d) {
        if (d < this.minStep || d > this.maxStep) {
            this.initialStep = -1.0d;
        } else {
            this.initialStep = d;
        }
    }

    /* access modifiers changed from: protected */
    public void sanityChecks(ExpandableStatefulODE expandableStatefulODE, double d) throws DimensionMismatchException, NumberIsTooSmallException {
        super.sanityChecks(expandableStatefulODE, d);
        this.mainSetDimension = expandableStatefulODE.getPrimaryMapper().getDimension();
        if (this.vecAbsoluteTolerance != null && this.vecAbsoluteTolerance.length != this.mainSetDimension) {
            throw new DimensionMismatchException(this.mainSetDimension, this.vecAbsoluteTolerance.length);
        } else if (this.vecRelativeTolerance != null && this.vecRelativeTolerance.length != this.mainSetDimension) {
            throw new DimensionMismatchException(this.mainSetDimension, this.vecRelativeTolerance.length);
        }
    }

    public double initializeStep(boolean z, int i, double[] dArr, double d, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5) throws MaxCountExceededException, DimensionMismatchException {
        double[] dArr6 = dArr;
        double[] dArr7 = dArr2;
        double[] dArr8 = dArr4;
        double[] dArr9 = dArr5;
        if (this.initialStep > 0.0d) {
            return z ? this.initialStep : -this.initialStep;
        }
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (int i2 = 0; i2 < dArr6.length; i2++) {
            double d4 = dArr7[i2] / dArr6[i2];
            d2 += d4 * d4;
            double d5 = dArr3[i2] / dArr6[i2];
            d3 += d5 * d5;
        }
        double sqrt = (d2 < 1.0E-10d || d3 < 1.0E-10d) ? 1.0E-6d : FastMath.sqrt(d2 / d3) * 0.01d;
        if (!z) {
            sqrt = -sqrt;
        }
        for (int i3 = 0; i3 < dArr7.length; i3++) {
            dArr8[i3] = dArr7[i3] + (dArr3[i3] * sqrt);
        }
        computeDerivatives(d + sqrt, dArr8, dArr9);
        double d6 = 0.0d;
        for (int i4 = 0; i4 < dArr6.length; i4++) {
            double d7 = (dArr9[i4] - dArr3[i4]) / dArr6[i4];
            d6 += d7 * d7;
        }
        double max = FastMath.max(FastMath.sqrt(d3), FastMath.sqrt(d6) / sqrt);
        double max2 = FastMath.max(FastMath.min(100.0d * FastMath.abs(sqrt), max < 1.0E-15d ? FastMath.max(1.0E-6d, 0.001d * FastMath.abs(sqrt)) : FastMath.pow(0.01d / max, 1.0d / ((double) i))), 1.0E-12d * FastMath.abs(d));
        if (max2 < getMinStep()) {
            max2 = getMinStep();
        }
        if (max2 > getMaxStep()) {
            max2 = getMaxStep();
        }
        if (!z) {
            max2 = -max2;
        }
        return max2;
    }

    /* access modifiers changed from: protected */
    public double filterStep(double d, boolean z, boolean z2) throws NumberIsTooSmallException {
        if (FastMath.abs(d) < this.minStep) {
            if (z2) {
                d = z ? this.minStep : -this.minStep;
            } else {
                throw new NumberIsTooSmallException(LocalizedFormats.MINIMAL_STEPSIZE_REACHED_DURING_INTEGRATION, Double.valueOf(FastMath.abs(d)), Double.valueOf(this.minStep), true);
            }
        }
        if (d > this.maxStep) {
            return this.maxStep;
        }
        return d < (-this.maxStep) ? -this.maxStep : d;
    }

    public double getCurrentStepStart() {
        return this.stepStart;
    }

    /* access modifiers changed from: protected */
    public void resetInternalState() {
        this.stepStart = Double.NaN;
        this.stepSize = FastMath.sqrt(this.minStep * this.maxStep);
    }

    public double getMinStep() {
        return this.minStep;
    }

    public double getMaxStep() {
        return this.maxStep;
    }
}
