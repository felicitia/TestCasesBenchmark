package org.apache.commons.math3.ode.nonstiff;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.util.FastMath;

public abstract class EmbeddedRungeKuttaIntegrator extends AdaptiveStepsizeIntegrator {
    private final double[][] a;
    private final double[] b;
    private final double[] c;
    private final double exp = (-1.0d / ((double) getOrder()));
    private final boolean fsal;
    private double maxGrowth;
    private double minReduction;
    private final RungeKuttaStepInterpolator prototype;
    private double safety;

    /* access modifiers changed from: protected */
    public abstract double estimateError(double[][] dArr, double[] dArr2, double[] dArr3, double d);

    public abstract int getOrder();

    protected EmbeddedRungeKuttaIntegrator(String str, boolean z, double[] dArr, double[][] dArr2, double[] dArr3, RungeKuttaStepInterpolator rungeKuttaStepInterpolator, double d, double d2, double d3, double d4) {
        super(str, d, d2, d3, d4);
        this.fsal = z;
        this.c = dArr;
        this.a = dArr2;
        this.b = dArr3;
        this.prototype = rungeKuttaStepInterpolator;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(10.0d);
    }

    protected EmbeddedRungeKuttaIntegrator(String str, boolean z, double[] dArr, double[][] dArr2, double[] dArr3, RungeKuttaStepInterpolator rungeKuttaStepInterpolator, double d, double d2, double[] dArr4, double[] dArr5) {
        super(str, d, d2, dArr4, dArr5);
        this.fsal = z;
        this.c = dArr;
        this.a = dArr2;
        this.b = dArr3;
        this.prototype = rungeKuttaStepInterpolator;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(10.0d);
    }

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double d) {
        this.safety = d;
    }

    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        int i;
        double[] dArr;
        int i2;
        double[] dArr2;
        double[] dArr3;
        double[] dArr4;
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator;
        double[] dArr5;
        boolean z;
        double[] dArr6;
        double[] dArr7;
        boolean z2;
        double d2;
        boolean z3;
        ExpandableStatefulODE expandableStatefulODE2 = expandableStatefulODE;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        int i3 = 0;
        boolean z4 = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr8 = (double[]) completeState.clone();
        int length = this.c.length + 1;
        double[][] dArr9 = (double[][]) Array.newInstance(double.class, new int[]{length, dArr8.length});
        double[] dArr10 = (double[]) completeState.clone();
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator2 = (RungeKuttaStepInterpolator) this.prototype.copy();
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator3 = rungeKuttaStepInterpolator2;
        double[] dArr11 = new double[dArr8.length];
        double[] dArr12 = dArr10;
        int i4 = length;
        rungeKuttaStepInterpolator2.reinitialize(this, dArr10, dArr9, z4, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        rungeKuttaStepInterpolator3.storeTime(expandableStatefulODE.getTime());
        this.stepStart = expandableStatefulODE.getTime();
        double d3 = 0.0d;
        initIntegration(expandableStatefulODE.getTime(), completeState, d);
        this.isLastStep = false;
        boolean z5 = true;
        while (true) {
            rungeKuttaStepInterpolator3.shift();
            boolean z6 = z5;
            double d4 = 10.0d;
            while (d4 >= 1.0d) {
                if (z6 || !this.fsal) {
                    computeDerivatives(this.stepStart, dArr3, dArr9[i2]);
                }
                if (z6) {
                    double[] dArr13 = new double[this.mainSetDimension];
                    if (this.vecAbsoluteTolerance == null) {
                        int i5 = i2;
                        while (i5 < dArr13.length) {
                            dArr13[i5] = this.scalAbsoluteTolerance + (this.scalRelativeTolerance * FastMath.abs(dArr3[i5]));
                            i5++;
                            ExpandableStatefulODE expandableStatefulODE3 = expandableStatefulODE;
                        }
                    } else {
                        for (int i6 = i2; i6 < dArr13.length; i6++) {
                            dArr13[i6] = this.vecAbsoluteTolerance[i6] + (this.vecRelativeTolerance[i6] * FastMath.abs(dArr3[i6]));
                        }
                    }
                    int order = getOrder();
                    double d5 = this.stepStart;
                    double[] dArr14 = dArr3;
                    dArr6 = dArr3;
                    double[] dArr15 = dArr9[i2];
                    dArr7 = dArr2;
                    z = z4;
                    d2 = initializeStep(z4, order, dArr13, d5, dArr14, dArr15, dArr, dArr9[1]);
                    z2 = false;
                } else {
                    dArr6 = dArr3;
                    dArr7 = dArr2;
                    z = z4;
                    z2 = z6;
                    d2 = d3;
                }
                this.stepSize = d2;
                if (z) {
                    if (this.stepStart + this.stepSize >= d) {
                        this.stepSize = d - this.stepStart;
                    }
                } else if (this.stepStart + this.stepSize <= d) {
                    this.stepSize = d - this.stepStart;
                }
                int i7 = i;
                int i8 = 1;
                while (i8 < i7) {
                    for (int i9 = 0; i9 < dArr7.length; i9++) {
                        int i10 = i8 - 1;
                        double d6 = this.a[i10][0] * dArr9[0][i9];
                        for (int i11 = 1; i11 < i8; i11++) {
                            d6 += this.a[i10][i11] * dArr9[i11][i9];
                        }
                        dArr[i9] = dArr6[i9] + (this.stepSize * d6);
                    }
                    double d7 = d2;
                    computeDerivatives(this.stepStart + (this.c[i8 - 1] * this.stepSize), dArr, dArr9[i8]);
                    i8++;
                    d2 = d7;
                }
                double d8 = d2;
                double[] dArr16 = dArr;
                for (int i12 = 0; i12 < dArr7.length; i12++) {
                    double d9 = this.b[0] * dArr9[0][i12];
                    for (int i13 = 1; i13 < i7; i13++) {
                        d9 += this.b[i13] * dArr9[i13][i12];
                    }
                    dArr16[i12] = dArr6[i12] + (this.stepSize * d9);
                }
                d4 = estimateError(dArr9, dArr6, dArr16, this.stepSize);
                if (d4 >= 1.0d) {
                    boolean z7 = z2;
                    int i14 = i7;
                    d3 = filterStep(this.stepSize * FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(d4, this.exp))), z, false);
                    dArr2 = dArr7;
                    dArr = dArr16;
                    z3 = z;
                    z6 = z7;
                    i = i14;
                    ExpandableStatefulODE expandableStatefulODE4 = expandableStatefulODE;
                    i2 = 0;
                    dArr3 = dArr6;
                } else {
                    boolean z8 = z2;
                    i = i7;
                    dArr2 = dArr7;
                    dArr3 = dArr6;
                    dArr = dArr16;
                    z3 = z;
                    d3 = d8;
                    z6 = z8;
                    ExpandableStatefulODE expandableStatefulODE5 = expandableStatefulODE;
                    i2 = 0;
                }
            }
            double[] dArr17 = dArr3;
            double[] dArr18 = dArr2;
            int i15 = i2;
            double[] dArr19 = dArr;
            int i16 = i;
            boolean z9 = z4;
            rungeKuttaStepInterpolator3.storeTime(this.stepStart + this.stepSize);
            System.arraycopy(dArr19, i15, dArr17, i15, dArr18.length);
            double[] dArr20 = dArr11;
            System.arraycopy(dArr9[i16 - 1], i15, dArr20, i15, dArr18.length);
            double d10 = d4;
            this.stepStart = acceptStep(rungeKuttaStepInterpolator3, dArr17, dArr20, d);
            System.arraycopy(dArr17, i15, dArr19, i15, dArr17.length);
            if (!this.isLastStep) {
                rungeKuttaStepInterpolator3.storeTime(this.stepStart);
                if (this.fsal) {
                    System.arraycopy(dArr20, i15, dArr9[i15], i15, dArr18.length);
                }
                dArr5 = dArr20;
                dArr4 = dArr19;
                rungeKuttaStepInterpolator = rungeKuttaStepInterpolator3;
                double min = this.stepSize * FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(d10, this.exp)));
                double d11 = this.stepStart + min;
                double filterStep = filterStep(min, z9, !z9 ? d11 <= d : d11 >= d);
                double d12 = this.stepStart + filterStep;
                if (!z9 ? d12 <= d : d12 >= d) {
                    filterStep = d - this.stepStart;
                }
                d3 = filterStep;
            } else {
                dArr5 = dArr20;
                dArr4 = dArr19;
                rungeKuttaStepInterpolator = rungeKuttaStepInterpolator3;
            }
            if (this.isLastStep) {
                ExpandableStatefulODE expandableStatefulODE6 = expandableStatefulODE;
                expandableStatefulODE6.setTime(this.stepStart);
                expandableStatefulODE6.setCompleteState(dArr17);
                resetInternalState();
                return;
            }
            z5 = z6;
            completeState = dArr18;
            dArr8 = dArr17;
            z4 = z9;
            i4 = i16;
            dArr11 = dArr5;
            rungeKuttaStepInterpolator3 = rungeKuttaStepInterpolator;
            dArr12 = dArr4;
            ExpandableStatefulODE expandableStatefulODE7 = expandableStatefulODE;
            i3 = 0;
        }
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
}
