package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrixPreservingVisitor;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class AdamsMoultonIntegrator extends AdamsIntegrator {
    private static final String METHOD_NAME = "Adams-Moulton";

    private class Corrector implements RealMatrixPreservingVisitor {
        private final double[] after;
        private final double[] before;
        private final double[] previous;
        private final double[] scaled;

        public Corrector(double[] dArr, double[] dArr2, double[] dArr3) {
            this.previous = dArr;
            this.scaled = dArr2;
            this.after = dArr3;
            this.before = (double[]) dArr3.clone();
        }

        public void start(int i, int i2, int i3, int i4, int i5, int i6) {
            Arrays.fill(this.after, 0.0d);
        }

        public void visit(int i, int i2, double d) {
            if ((i & 1) == 0) {
                double[] dArr = this.after;
                dArr[i2] = dArr[i2] - d;
                return;
            }
            double[] dArr2 = this.after;
            dArr2[i2] = dArr2[i2] + d;
        }

        public double end() {
            double d = 0.0d;
            for (int i = 0; i < this.after.length; i++) {
                double[] dArr = this.after;
                dArr[i] = dArr[i] + this.previous[i] + this.scaled[i];
                if (i < AdamsMoultonIntegrator.this.mainSetDimension) {
                    double max = FastMath.max(FastMath.abs(this.previous[i]), FastMath.abs(this.after[i]));
                    double d2 = (this.after[i] - this.before[i]) / (AdamsMoultonIntegrator.this.vecAbsoluteTolerance == null ? AdamsMoultonIntegrator.this.scalAbsoluteTolerance + (AdamsMoultonIntegrator.this.scalRelativeTolerance * max) : AdamsMoultonIntegrator.this.vecAbsoluteTolerance[i] + (AdamsMoultonIntegrator.this.vecRelativeTolerance[i] * max));
                    d += d2 * d2;
                }
            }
            return FastMath.sqrt(d / ((double) AdamsMoultonIntegrator.this.mainSetDimension));
        }
    }

    public AdamsMoultonIntegrator(int i, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(METHOD_NAME, i, i + 1, d, d2, d3, d4);
    }

    public AdamsMoultonIntegrator(int i, double d, double d2, double[] dArr, double[] dArr2) throws IllegalArgumentException {
        super(METHOD_NAME, i, i + 1, d, d2, dArr, dArr2);
    }

    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        double[] dArr;
        boolean z;
        ExpandableStatefulODE expandableStatefulODE2 = expandableStatefulODE;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        boolean z2 = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr2 = (double[]) completeState.clone();
        double[] dArr3 = new double[dArr2.length];
        double[] dArr4 = new double[dArr2.length];
        double[] dArr5 = new double[dArr2.length];
        Array2DRowRealMatrix array2DRowRealMatrix = null;
        NordsieckStepInterpolator nordsieckStepInterpolator = new NordsieckStepInterpolator();
        nordsieckStepInterpolator.reinitialize(dArr2, z2, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        NordsieckStepInterpolator nordsieckStepInterpolator2 = nordsieckStepInterpolator;
        double[] dArr6 = dArr4;
        double[] dArr7 = dArr5;
        double d2 = d;
        initIntegration(expandableStatefulODE.getTime(), completeState, d2);
        start(expandableStatefulODE.getTime(), dArr2, d2);
        nordsieckStepInterpolator2.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
        NordsieckStepInterpolator nordsieckStepInterpolator3 = nordsieckStepInterpolator2;
        nordsieckStepInterpolator3.storeTime(this.stepStart);
        double d3 = this.stepSize;
        nordsieckStepInterpolator3.rescale(d3);
        this.isLastStep = false;
        while (true) {
            double d4 = 10.0d;
            Array2DRowRealMatrix array2DRowRealMatrix2 = array2DRowRealMatrix;
            while (d4 >= 1.0d) {
                this.stepSize = d3;
                boolean z3 = z2;
                double d5 = this.stepStart + this.stepSize;
                nordsieckStepInterpolator3.setInterpolatedTime(d5);
                System.arraycopy(nordsieckStepInterpolator3.getInterpolatedState(), 0, dArr6, 0, completeState.length);
                computeDerivatives(d5, dArr6, dArr3);
                for (int i = 0; i < completeState.length; i++) {
                    dArr7[i] = this.stepSize * dArr3[i];
                }
                double[] dArr8 = dArr7;
                array2DRowRealMatrix2 = updateHighOrderDerivativesPhase1(this.nordsieck);
                updateHighOrderDerivativesPhase2(this.scaled, dArr8, array2DRowRealMatrix2);
                double walkInOptimizedOrder = array2DRowRealMatrix2.walkInOptimizedOrder((RealMatrixPreservingVisitor) new Corrector(dArr2, dArr8, dArr6));
                if (walkInOptimizedOrder >= 1.0d) {
                    dArr = dArr8;
                    boolean z4 = z3;
                    double filterStep = filterStep(this.stepSize * computeStepGrowShrinkFactor(walkInOptimizedOrder), z4, false);
                    nordsieckStepInterpolator3.rescale(filterStep);
                    double d6 = filterStep;
                    z = z4;
                    d3 = d6;
                } else {
                    dArr = dArr8;
                    z = z3;
                }
                dArr7 = dArr;
                z2 = z;
                d4 = walkInOptimizedOrder;
            }
            double d7 = d3;
            boolean z5 = z2;
            double[] dArr9 = dArr7;
            double d8 = d4;
            double d9 = this.stepStart + this.stepSize;
            computeDerivatives(d9, dArr6, dArr3);
            double[] dArr10 = new double[completeState.length];
            for (int i2 = 0; i2 < completeState.length; i2++) {
                dArr10[i2] = this.stepSize * dArr3[i2];
            }
            updateHighOrderDerivativesPhase2(dArr9, dArr10, array2DRowRealMatrix2);
            System.arraycopy(dArr6, 0, dArr2, 0, dArr2.length);
            nordsieckStepInterpolator3.reinitialize(d9, this.stepSize, dArr10, array2DRowRealMatrix2);
            nordsieckStepInterpolator3.storeTime(this.stepStart);
            nordsieckStepInterpolator3.shift();
            nordsieckStepInterpolator3.storeTime(d9);
            double d10 = d7;
            double d11 = d8;
            double[] dArr11 = dArr10;
            double[] dArr12 = dArr6;
            double[] dArr13 = dArr9;
            NordsieckStepInterpolator nordsieckStepInterpolator4 = nordsieckStepInterpolator3;
            Array2DRowRealMatrix array2DRowRealMatrix3 = array2DRowRealMatrix2;
            this.stepStart = acceptStep(nordsieckStepInterpolator3, dArr2, dArr3, d);
            this.scaled = dArr11;
            this.nordsieck = array2DRowRealMatrix3;
            if (!this.isLastStep) {
                nordsieckStepInterpolator4.storeTime(this.stepStart);
                if (this.resetOccurred) {
                    start(this.stepStart, dArr2, d);
                    nordsieckStepInterpolator4.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
                }
                double computeStepGrowShrinkFactor = this.stepSize * computeStepGrowShrinkFactor(d11);
                double d12 = this.stepStart + computeStepGrowShrinkFactor;
                d3 = filterStep(computeStepGrowShrinkFactor, z5, !z5 ? d12 <= d : d12 >= d);
                double d13 = this.stepStart + d3;
                if (!z5 ? d13 <= d : d13 >= d) {
                    d3 = d - this.stepStart;
                }
                nordsieckStepInterpolator4.rescale(d3);
            } else {
                d3 = d10;
            }
            if (this.isLastStep) {
                ExpandableStatefulODE expandableStatefulODE3 = expandableStatefulODE;
                expandableStatefulODE3.setTime(this.stepStart);
                expandableStatefulODE3.setCompleteState(dArr2);
                resetInternalState();
                return;
            }
            nordsieckStepInterpolator3 = nordsieckStepInterpolator4;
            array2DRowRealMatrix = array2DRowRealMatrix3;
            dArr6 = dArr12;
            dArr7 = dArr13;
            ExpandableStatefulODE expandableStatefulODE4 = expandableStatefulODE;
            z2 = z5;
        }
    }
}
