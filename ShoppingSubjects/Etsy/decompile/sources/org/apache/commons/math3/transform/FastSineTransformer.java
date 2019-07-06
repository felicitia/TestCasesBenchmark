package org.apache.commons.math3.transform;

import java.io.Serializable;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

public class FastSineTransformer implements Serializable, RealTransformer {
    static final long serialVersionUID = 20120211;
    private final DstNormalization normalization;

    public FastSineTransformer(DstNormalization dstNormalization) {
        this.normalization = dstNormalization;
    }

    public double[] transform(double[] dArr, TransformType transformType) {
        if (this.normalization == DstNormalization.ORTHOGONAL_DST_I) {
            return TransformUtils.scaleArray(fst(dArr), FastMath.sqrt(2.0d / ((double) dArr.length)));
        } else if (transformType == TransformType.FORWARD) {
            return fst(dArr);
        } else {
            return TransformUtils.scaleArray(fst(dArr), 2.0d / ((double) dArr.length));
        }
    }

    public double[] transform(UnivariateFunction univariateFunction, double d, double d2, int i, TransformType transformType) {
        double[] sample = FunctionUtils.sample(univariateFunction, d, d2, i);
        sample[0] = 0.0d;
        return transform(sample, transformType);
    }

    /* access modifiers changed from: protected */
    public double[] fst(double[] dArr) throws MathIllegalArgumentException {
        double[] dArr2 = dArr;
        double[] dArr3 = new double[dArr2.length];
        if (!ArithmeticUtils.isPowerOfTwo((long) dArr2.length)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, Integer.valueOf(dArr2.length));
        } else if (dArr2[0] != 0.0d) {
            throw new MathIllegalArgumentException(LocalizedFormats.FIRST_ELEMENT_NOT_ZERO, Double.valueOf(dArr2[0]));
        } else {
            int length = dArr2.length;
            if (length == 1) {
                dArr3[0] = 0.0d;
                return dArr3;
            }
            double[] dArr4 = new double[length];
            dArr4[0] = 0.0d;
            int i = length >> 1;
            dArr4[i] = 2.0d * dArr2[i];
            for (int i2 = 1; i2 < i; i2++) {
                int i3 = length - i2;
                double sin = FastMath.sin((((double) i2) * 3.141592653589793d) / ((double) length)) * (dArr2[i2] + dArr2[i3]);
                double d = 0.5d * (dArr2[i2] - dArr2[i3]);
                dArr4[i2] = sin + d;
                dArr4[i3] = sin - d;
            }
            Complex[] transform = new FastFourierTransformer(DftNormalization.STANDARD).transform(dArr4, TransformType.FORWARD);
            dArr3[0] = 0.0d;
            dArr3[1] = 0.5d * transform[0].getReal();
            for (int i4 = 1; i4 < i; i4++) {
                int i5 = 2 * i4;
                dArr3[i5] = -transform[i4].getImaginary();
                dArr3[i5 + 1] = transform[i4].getReal() + dArr3[i5 - 1];
            }
            return dArr3;
        }
    }
}
