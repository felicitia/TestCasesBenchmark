package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.util.FastMath;

public class Sinc implements DifferentiableUnivariateFunction, UnivariateDifferentiableFunction {
    private static final double SHORTCUT = 0.006d;
    private final boolean normalized;

    public Sinc() {
        this(false);
    }

    public Sinc(boolean z) {
        this.normalized = z;
    }

    public double value(double d) {
        if (this.normalized) {
            d *= 3.141592653589793d;
        }
        if (FastMath.abs(d) > SHORTCUT) {
            return FastMath.sin(d) / d;
        }
        double d2 = d * d;
        return (((d2 - 20.0d) * d2) + 120.0d) / 120.0d;
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        double[] dArr;
        double d;
        int i;
        double d2;
        double d3;
        double d4;
        Sinc sinc = this;
        double value = (sinc.normalized ? 3.141592653589793d : 1.0d) * derivativeStructure.getValue();
        double d5 = value * value;
        int i2 = 1;
        double[] dArr2 = new double[(derivativeStructure.getOrder() + 1)];
        int i3 = 0;
        if (FastMath.abs(value) <= SHORTCUT) {
            while (i3 < dArr2.length) {
                int i4 = i3 / 2;
                if ((i3 & 1) == 0) {
                    double d6 = value;
                    dArr2[i3] = ((double) ((i4 & 1) == 0 ? i2 : -1)) * ((1.0d / ((double) (i3 + 1))) - (((1.0d / ((double) ((2 * i3) + 6))) - (d5 / ((double) ((24 * i3) + 120)))) * d5));
                    d2 = d6;
                } else {
                    double d7 = value;
                    if ((i4 & 1) == 0) {
                        d3 = d7;
                        d4 = -d3;
                    } else {
                        d3 = d7;
                        d4 = d3;
                    }
                    d2 = d3;
                    dArr2[i3] = d4 * ((1.0d / ((double) (i3 + 2))) - (((1.0d / ((double) ((6 * i3) + 24))) - (d5 / ((double) ((120 * i3) + 720)))) * d5));
                }
                i3++;
                value = d2;
                i2 = 1;
            }
        } else {
            double d8 = 1.0d / value;
            double cos = FastMath.cos(value);
            double sin = FastMath.sin(value);
            dArr2[0] = d8 * sin;
            double[] dArr3 = new double[dArr2.length];
            dArr3[0] = 1.0d;
            double d9 = d8;
            int i5 = 1;
            while (i5 < dArr2.length) {
                double d10 = 0.0d;
                if ((i5 & 1) == 0) {
                    dArr3[i5] = 0.0d;
                    i = i5;
                    d = 0.0d;
                } else {
                    i = i5 - 1;
                    dArr3[i5] = dArr3[i];
                    d = dArr3[i5];
                }
                while (i > 1) {
                    double[] dArr4 = dArr2;
                    int i6 = i - 1;
                    dArr3[i] = (((double) (i - i5)) * dArr3[i]) - dArr3[i6];
                    d10 = (d10 * d5) + dArr3[i];
                    dArr3[i6] = (((double) (i6 - i5)) * dArr3[i6]) + dArr3[i - 2];
                    d = (d * d5) + dArr3[i6];
                    i -= 2;
                    dArr2 = dArr4;
                }
                double[] dArr5 = dArr2;
                int i7 = i5;
                dArr3[0] = dArr3[0] * ((double) (-i5));
                d9 *= d8;
                dArr5[i7] = ((((d10 * d5) + dArr3[0]) * sin) + (d * value * cos)) * d9;
                i5 = i7 + 1;
                dArr2 = dArr5;
                sinc = this;
            }
        }
        double[] dArr6 = dArr2;
        if (sinc.normalized) {
            dArr = dArr6;
            double d11 = 3.141592653589793d;
            for (int i8 = 1; i8 < dArr.length; i8++) {
                dArr[i8] = dArr[i8] * d11;
                d11 *= 3.141592653589793d;
            }
        } else {
            dArr = dArr6;
        }
        return derivativeStructure.compose(dArr);
    }
}
