package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

class GraggBulirschStoerStepInterpolator extends AbstractStepInterpolator {
    private static final long serialVersionUID = 20110928;
    private int currentDegree;
    private double[] errfac;
    private double[][] polynomials;
    private double[] y0Dot;
    private double[] y1;
    private double[] y1Dot;
    private double[][] yMidDots;

    public GraggBulirschStoerStepInterpolator() {
        this.y0Dot = null;
        this.y1 = null;
        this.y1Dot = null;
        this.yMidDots = null;
        resetTables(-1);
    }

    public GraggBulirschStoerStepInterpolator(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, double[][] dArr5, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        super(dArr, z, equationsMapper, equationsMapperArr);
        this.y0Dot = dArr2;
        this.y1 = dArr3;
        this.y1Dot = dArr4;
        this.yMidDots = dArr5;
        resetTables(dArr5.length + 4);
    }

    public GraggBulirschStoerStepInterpolator(GraggBulirschStoerStepInterpolator graggBulirschStoerStepInterpolator) {
        super(graggBulirschStoerStepInterpolator);
        int length = this.currentState.length;
        this.y0Dot = null;
        this.y1 = null;
        this.y1Dot = null;
        double[][] dArr = null;
        this.yMidDots = dArr;
        if (graggBulirschStoerStepInterpolator.polynomials == null) {
            this.polynomials = dArr;
            this.currentDegree = -1;
            return;
        }
        resetTables(graggBulirschStoerStepInterpolator.currentDegree);
        for (int i = 0; i < this.polynomials.length; i++) {
            this.polynomials[i] = new double[length];
            System.arraycopy(graggBulirschStoerStepInterpolator.polynomials[i], 0, this.polynomials[i], 0, length);
        }
        this.currentDegree = graggBulirschStoerStepInterpolator.currentDegree;
    }

    private void resetTables(int i) {
        if (i < 0) {
            this.polynomials = null;
            this.errfac = null;
            this.currentDegree = -1;
            return;
        }
        double[][] dArr = new double[(i + 1)][];
        if (this.polynomials != null) {
            System.arraycopy(this.polynomials, 0, dArr, 0, this.polynomials.length);
            for (int length = this.polynomials.length; length < dArr.length; length++) {
                dArr[length] = new double[this.currentState.length];
            }
        } else {
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2] = new double[this.currentState.length];
            }
        }
        this.polynomials = dArr;
        if (i <= 4) {
            this.errfac = null;
        } else {
            this.errfac = new double[(i - 4)];
            int i3 = 0;
            while (i3 < this.errfac.length) {
                int i4 = i3 + 5;
                this.errfac[i3] = 1.0d / ((double) (i4 * i4));
                int i5 = i3 + 1;
                double sqrt = 0.5d * FastMath.sqrt(((double) i5) / ((double) i4));
                int i6 = 0;
                while (i6 <= i3) {
                    double[] dArr2 = this.errfac;
                    i6++;
                    dArr2[i3] = dArr2[i3] * (sqrt / ((double) i6));
                }
                i3 = i5;
            }
        }
        this.currentDegree = 0;
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new GraggBulirschStoerStepInterpolator(this);
    }

    public void computeCoefficients(int i, double d) {
        int i2 = i;
        if (this.polynomials == null || this.polynomials.length <= i2 + 4) {
            resetTables(i2 + 4);
        }
        this.currentDegree = i2 + 4;
        char c = 0;
        int i3 = 0;
        while (i3 < this.currentState.length) {
            double d2 = d * this.y0Dot[i3];
            double d3 = d * this.y1Dot[i3];
            double d4 = this.y1[i3] - this.currentState[i3];
            double d5 = d4 - d3;
            double d6 = d2 - d4;
            this.polynomials[c][i3] = this.currentState[i3];
            this.polynomials[1][i3] = d4;
            this.polynomials[2][i3] = d5;
            this.polynomials[3][i3] = d6;
            if (i2 >= 0) {
                this.polynomials[4][i3] = (this.yMidDots[0][i3] - (((this.currentState[i3] + this.y1[i3]) * 0.5d) + (0.125d * (d5 + d6)))) * 16.0d;
                if (i2 > 0) {
                    this.polynomials[5][i3] = (this.yMidDots[1][i3] - (d4 + (0.25d * (d5 - d6)))) * 16.0d;
                    if (i2 > 1) {
                        this.polynomials[6][i3] = ((this.yMidDots[2][i3] - (d3 - d2)) + this.polynomials[4][i3]) * 16.0d;
                        if (i2 > 2) {
                            this.polynomials[7][i3] = ((this.yMidDots[3][i3] - (6.0d * (d6 - d5))) + (3.0d * this.polynomials[5][i3])) * 16.0d;
                            for (int i4 = 4; i4 <= i2; i4++) {
                                double d7 = ((double) i4) * 0.5d * ((double) (i4 - 1));
                                this.polynomials[i4 + 4][i3] = ((this.yMidDots[i4][i3] + (d7 * this.polynomials[i4 + 2][i3])) - ((((2.0d * d7) * ((double) (i4 - 2))) * ((double) (i4 - 3))) * this.polynomials[i4][i3])) * 16.0d;
                            }
                        }
                    }
                }
                i3++;
                c = 0;
            } else {
                return;
            }
        }
    }

    public double estimateError(double[] dArr) {
        double d = 0.0d;
        if (this.currentDegree < 5) {
            return 0.0d;
        }
        for (int i = 0; i < dArr.length; i++) {
            double d2 = this.polynomials[this.currentDegree][i] / dArr[i];
            d += d2 * d2;
        }
        return FastMath.sqrt(d / ((double) dArr.length)) * this.errfac[this.currentDegree - 5];
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3;
        int length = this.currentState.length;
        double d4 = 1.0d - d;
        double d5 = d - 0.5d;
        double d6 = d * d4;
        double d7 = d6 * d6;
        double d8 = d6 * 2.0d * (1.0d - (2.0d * d));
        double d9 = 1.0d / this.h;
        double d10 = 3.0d * d;
        double d11 = ((2.0d - d10) * d) / this.h;
        double d12 = (((d10 - 4.0d) * d) + 1.0d) / this.h;
        int i = 0;
        while (i < length) {
            double d13 = this.polynomials[0][i];
            double d14 = this.polynomials[1][i];
            double d15 = this.polynomials[2][i];
            double d16 = this.polynomials[3][i];
            this.interpolatedState[i] = d13 + ((d14 + (((d15 * d) + (d16 * d4)) * d4)) * d);
            this.interpolatedDerivatives[i] = (d14 * d9) + (d15 * d11) + (d16 * d12);
            if (this.currentDegree > 3) {
                double d17 = this.polynomials[this.currentDegree][i];
                int i2 = this.currentDegree - 1;
                double d18 = 0.0d;
                for (int i3 = 3; i2 > i3; i3 = 3) {
                    double d19 = 1.0d / ((double) (i2 - 3));
                    d18 = ((d18 * d5) + d17) * d19;
                    d17 = this.polynomials[i2][i] + (d17 * d19 * d5);
                    i2--;
                }
                double[] dArr = this.interpolatedState;
                dArr[i] = dArr[i] + (d7 * d17);
                double[] dArr2 = this.interpolatedDerivatives;
                d3 = d11;
                dArr2[i] = dArr2[i] + (((d18 * d7) + (d17 * d8)) / this.h);
            } else {
                d3 = d11;
            }
            i++;
            d11 = d3;
        }
        if (this.h == 0.0d) {
            System.arraycopy(this.yMidDots[1], 0, this.interpolatedDerivatives, 0, length);
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int length = this.currentState == null ? -1 : this.currentState.length;
        writeBaseExternal(objectOutput);
        objectOutput.writeInt(this.currentDegree);
        for (int i = 0; i <= this.currentDegree; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                objectOutput.writeDouble(this.polynomials[i][i2]);
            }
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        double readBaseExternal = readBaseExternal(objectInput);
        int length = this.currentState == null ? -1 : this.currentState.length;
        int readInt = objectInput.readInt();
        resetTables(readInt);
        this.currentDegree = readInt;
        for (int i = 0; i <= this.currentDegree; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                this.polynomials[i][i2] = objectInput.readDouble();
            }
        }
        setInterpolatedTime(readBaseExternal);
    }
}
