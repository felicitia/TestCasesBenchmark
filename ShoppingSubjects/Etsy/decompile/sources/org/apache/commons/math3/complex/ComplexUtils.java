package org.apache.commons.math3.complex;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class ComplexUtils {
    private ComplexUtils() {
    }

    public static Complex polar2Complex(double d, double d2) throws MathIllegalArgumentException {
        if (d >= 0.0d) {
            return new Complex(FastMath.cos(d2) * d, d * FastMath.sin(d2));
        }
        throw new MathIllegalArgumentException(LocalizedFormats.NEGATIVE_COMPLEX_MODULE, Double.valueOf(d));
    }

    public static Complex[] convertToComplex(double[] dArr) {
        Complex[] complexArr = new Complex[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            complexArr[i] = new Complex(dArr[i], 0.0d);
        }
        return complexArr;
    }
}
