package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.util.FastMath;

public abstract class RungeKuttaIntegrator extends AbstractIntegrator {
    private final double[][] a;
    private final double[] b;
    private final double[] c;
    private final RungeKuttaStepInterpolator prototype;
    private final double step;

    protected RungeKuttaIntegrator(String str, double[] dArr, double[][] dArr2, double[] dArr3, RungeKuttaStepInterpolator rungeKuttaStepInterpolator, double d) {
        super(str);
        this.c = dArr;
        this.a = dArr2;
        this.b = dArr3;
        this.prototype = rungeKuttaStepInterpolator;
        this.step = FastMath.abs(d);
    }

    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        ExpandableStatefulODE expandableStatefulODE2 = expandableStatefulODE;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        int i = 0;
        boolean z = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr = (double[]) completeState.clone();
        int length = this.c.length + 1;
        double[][] dArr2 = new double[length][];
        for (int i2 = 0; i2 < length; i2++) {
            dArr2[i2] = new double[completeState.length];
        }
        double[] dArr3 = (double[]) completeState.clone();
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator = (RungeKuttaStepInterpolator) this.prototype.copy();
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator2 = rungeKuttaStepInterpolator;
        double[] dArr4 = new double[completeState.length];
        double[] dArr5 = dArr3;
        double[][] dArr6 = dArr2;
        int i3 = length;
        rungeKuttaStepInterpolator.reinitialize(this, dArr3, dArr2, z, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        rungeKuttaStepInterpolator2.storeTime(expandableStatefulODE.getTime());
        this.stepStart = expandableStatefulODE.getTime();
        this.stepSize = z ? this.step : -this.step;
        initIntegration(expandableStatefulODE.getTime(), completeState, d);
        this.isLastStep = false;
        while (true) {
            rungeKuttaStepInterpolator2.shift();
            computeDerivatives(this.stepStart, dArr, dArr6[i]);
            int i4 = i3;
            int i5 = 1;
            while (i5 < i4) {
                int i6 = i;
                while (i6 < completeState.length) {
                    int i7 = i5 - 1;
                    double d2 = this.a[i7][i] * dArr6[i][i6];
                    for (int i8 = 1; i8 < i5; i8++) {
                        d2 += this.a[i7][i8] * dArr6[i8][i6];
                    }
                    dArr5[i6] = dArr[i6] + (this.stepSize * d2);
                    i6++;
                    ExpandableStatefulODE expandableStatefulODE3 = expandableStatefulODE;
                    i = 0;
                }
                computeDerivatives(this.stepStart + (this.c[i5 - 1] * this.stepSize), dArr5, dArr6[i5]);
                i5++;
                ExpandableStatefulODE expandableStatefulODE4 = expandableStatefulODE;
                i = 0;
            }
            double[] dArr7 = dArr5;
            for (int i9 = 0; i9 < completeState.length; i9++) {
                double d3 = this.b[0] * dArr6[0][i9];
                for (int i10 = 1; i10 < i4; i10++) {
                    d3 += this.b[i10] * dArr6[i10][i9];
                }
                dArr7[i9] = dArr[i9] + (this.stepSize * d3);
            }
            rungeKuttaStepInterpolator2.storeTime(this.stepStart + this.stepSize);
            System.arraycopy(dArr7, 0, dArr, 0, completeState.length);
            double[] dArr8 = dArr4;
            System.arraycopy(dArr6[i4 - 1], 0, dArr8, 0, completeState.length);
            this.stepStart = acceptStep(rungeKuttaStepInterpolator2, dArr, dArr8, d);
            if (!this.isLastStep) {
                rungeKuttaStepInterpolator2.storeTime(this.stepStart);
                double d4 = this.stepStart + this.stepSize;
                if (!z ? d4 <= d : d4 >= d) {
                    this.stepSize = d - this.stepStart;
                }
            }
            if (this.isLastStep) {
                ExpandableStatefulODE expandableStatefulODE5 = expandableStatefulODE;
                expandableStatefulODE5.setTime(this.stepStart);
                expandableStatefulODE5.setCompleteState(dArr);
                this.stepStart = Double.NaN;
                this.stepSize = Double.NaN;
                return;
            }
            i3 = i4;
            dArr5 = dArr7;
            i = 0;
            dArr4 = dArr8;
            ExpandableStatefulODE expandableStatefulODE6 = expandableStatefulODE;
        }
    }
}
