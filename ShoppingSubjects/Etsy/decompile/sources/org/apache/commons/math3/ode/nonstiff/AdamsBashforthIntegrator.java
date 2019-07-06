package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class AdamsBashforthIntegrator extends AdamsIntegrator {
    private static final String METHOD_NAME = "Adams-Bashforth";

    public AdamsBashforthIntegrator(int i, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(METHOD_NAME, i, i, d, d2, d3, d4);
    }

    public AdamsBashforthIntegrator(int i, double d, double d2, double[] dArr, double[] dArr2) throws IllegalArgumentException {
        super(METHOD_NAME, i, i, d, d2, dArr, dArr2);
    }

    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        int i;
        double d2;
        boolean z;
        NordsieckStepInterpolator nordsieckStepInterpolator;
        boolean z2;
        double d3;
        double d4;
        ExpandableStatefulODE expandableStatefulODE2 = expandableStatefulODE;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        int i2 = 0;
        boolean z3 = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr = (double[]) completeState.clone();
        double[] dArr2 = new double[dArr.length];
        NordsieckStepInterpolator nordsieckStepInterpolator2 = new NordsieckStepInterpolator();
        nordsieckStepInterpolator2.reinitialize(dArr, z3, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        NordsieckStepInterpolator nordsieckStepInterpolator3 = nordsieckStepInterpolator2;
        double d5 = d;
        initIntegration(expandableStatefulODE.getTime(), completeState, d5);
        start(expandableStatefulODE.getTime(), dArr, d5);
        nordsieckStepInterpolator3.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
        NordsieckStepInterpolator nordsieckStepInterpolator4 = nordsieckStepInterpolator3;
        nordsieckStepInterpolator4.storeTime(this.stepStart);
        int rowDimension = this.nordsieck.getRowDimension() - 1;
        double d6 = this.stepSize;
        nordsieckStepInterpolator4.rescale(d6);
        this.isLastStep = false;
        while (true) {
            double d7 = 10.0d;
            while (d7 >= 1.0d) {
                this.stepSize = d2;
                double d8 = 0.0d;
                int i3 = i;
                while (i3 < this.mainSetDimension) {
                    double abs = FastMath.abs(dArr[i3]);
                    if (this.vecAbsoluteTolerance == null) {
                        d3 = d2;
                        d4 = this.scalAbsoluteTolerance + (this.scalRelativeTolerance * abs);
                    } else {
                        d3 = d2;
                        d4 = this.vecAbsoluteTolerance[i3] + (this.vecRelativeTolerance[i3] * abs);
                    }
                    double entry = this.nordsieck.getEntry(rowDimension, i3) / d4;
                    d8 += entry * entry;
                    i3++;
                    d2 = d3;
                    ExpandableStatefulODE expandableStatefulODE3 = expandableStatefulODE;
                }
                double d9 = d2;
                d7 = FastMath.sqrt(d8 / ((double) this.mainSetDimension));
                if (d7 >= 1.0d) {
                    d2 = filterStep(this.stepSize * computeStepGrowShrinkFactor(d7), z3, false);
                    nordsieckStepInterpolator4.rescale(d2);
                    i = 0;
                    ExpandableStatefulODE expandableStatefulODE4 = expandableStatefulODE;
                } else {
                    d2 = d9;
                    ExpandableStatefulODE expandableStatefulODE5 = expandableStatefulODE;
                    i = 0;
                }
            }
            double d10 = d2;
            int i4 = i;
            double d11 = this.stepSize + this.stepStart;
            nordsieckStepInterpolator4.shift();
            nordsieckStepInterpolator4.setInterpolatedTime(d11);
            System.arraycopy(nordsieckStepInterpolator4.getInterpolatedState(), i4, dArr, i4, completeState.length);
            computeDerivatives(d11, dArr, dArr2);
            double[] dArr3 = new double[completeState.length];
            int i5 = i4;
            while (i5 < completeState.length) {
                double[] dArr4 = dArr3;
                dArr4[i5] = this.stepSize * dArr2[i5];
                i5++;
                dArr3 = dArr4;
            }
            double[] dArr5 = dArr3;
            Array2DRowRealMatrix updateHighOrderDerivativesPhase1 = updateHighOrderDerivativesPhase1(this.nordsieck);
            updateHighOrderDerivativesPhase2(this.scaled, dArr3, updateHighOrderDerivativesPhase1);
            double d12 = d11;
            nordsieckStepInterpolator4.reinitialize(d12, this.stepSize, dArr3, updateHighOrderDerivativesPhase1);
            nordsieckStepInterpolator4.storeTime(d11);
            boolean z4 = z3;
            double d13 = d7;
            NordsieckStepInterpolator nordsieckStepInterpolator5 = nordsieckStepInterpolator4;
            int i6 = rowDimension;
            this.stepStart = acceptStep(nordsieckStepInterpolator4, dArr, dArr2, d);
            this.scaled = dArr3;
            this.nordsieck = updateHighOrderDerivativesPhase1;
            nordsieckStepInterpolator5.reinitialize(d12, this.stepSize, this.scaled, this.nordsieck);
            if (!this.isLastStep) {
                nordsieckStepInterpolator = nordsieckStepInterpolator5;
                nordsieckStepInterpolator.storeTime(this.stepStart);
                if (this.resetOccurred) {
                    start(this.stepStart, dArr, d);
                    nordsieckStepInterpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
                }
                double computeStepGrowShrinkFactor = this.stepSize * computeStepGrowShrinkFactor(d13);
                double d14 = this.stepStart + computeStepGrowShrinkFactor;
                if (!z4 ? d14 > d : d14 < d) {
                    z = z4;
                    z2 = false;
                } else {
                    z = z4;
                    z2 = true;
                }
                d6 = filterStep(computeStepGrowShrinkFactor, z, z2);
                double d15 = this.stepStart + d6;
                if (!z ? d15 <= d : d15 >= d) {
                    d6 = d - this.stepStart;
                }
                nordsieckStepInterpolator.rescale(d6);
            } else {
                z = z4;
                nordsieckStepInterpolator = nordsieckStepInterpolator5;
                d6 = d10;
            }
            if (this.isLastStep) {
                ExpandableStatefulODE expandableStatefulODE6 = expandableStatefulODE;
                expandableStatefulODE6.setTime(this.stepStart);
                expandableStatefulODE6.setCompleteState(dArr);
                resetInternalState();
                return;
            }
            nordsieckStepInterpolator4 = nordsieckStepInterpolator;
            z3 = z;
            rowDimension = i6;
            ExpandableStatefulODE expandableStatefulODE7 = expandableStatefulODE;
            i2 = 0;
        }
    }
}
