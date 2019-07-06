package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class UnivariatePeriodicInterpolator implements UnivariateInterpolator {
    public static final int DEFAULT_EXTEND = 5;
    private final int extend;
    private final UnivariateInterpolator interpolator;
    /* access modifiers changed from: private */
    public final double period;

    public UnivariatePeriodicInterpolator(UnivariateInterpolator univariateInterpolator, double d, int i) {
        this.interpolator = univariateInterpolator;
        this.period = d;
        this.extend = i;
    }

    public UnivariatePeriodicInterpolator(UnivariateInterpolator univariateInterpolator, double d) {
        this(univariateInterpolator, d, 5);
    }

    public UnivariateFunction interpolate(double[] dArr, double[] dArr2) throws NumberIsTooSmallException {
        double[] dArr3 = dArr;
        if (dArr3.length < this.extend) {
            throw new NumberIsTooSmallException(Integer.valueOf(dArr3.length), Integer.valueOf(this.extend), true);
        }
        MathArrays.checkOrder(dArr);
        final double d = dArr3[0];
        int length = dArr3.length + (this.extend * 2);
        double[] dArr4 = new double[length];
        double[] dArr5 = new double[length];
        int i = 0;
        while (i < dArr3.length) {
            int i2 = i + this.extend;
            int i3 = i;
            dArr4[i2] = MathUtils.reduce(dArr3[i], this.period, d);
            dArr5[i2] = dArr2[i3];
            i = i3 + 1;
        }
        int i4 = 0;
        while (i4 < this.extend) {
            int length2 = (dArr3.length - this.extend) + i4;
            int i5 = i4;
            double d2 = d;
            dArr4[i5] = MathUtils.reduce(dArr3[length2], this.period, d2) - this.period;
            dArr5[i5] = dArr2[length2];
            int i6 = (length - this.extend) + i5;
            dArr4[i6] = MathUtils.reduce(dArr3[i5], this.period, d2) + this.period;
            dArr5[i6] = dArr2[i5];
            i4 = i5 + 1;
        }
        MathArrays.sortInPlace(dArr4, dArr5);
        final UnivariateFunction interpolate = this.interpolator.interpolate(dArr4, dArr5);
        return new UnivariateFunction() {
            public double value(double d) {
                return interpolate.value(MathUtils.reduce(d, UnivariatePeriodicInterpolator.this.period, d));
            }
        };
    }
}
