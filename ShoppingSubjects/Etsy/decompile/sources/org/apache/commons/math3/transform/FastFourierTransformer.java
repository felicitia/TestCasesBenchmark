package org.apache.commons.math3.transform;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class FastFourierTransformer implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final double[] W_SUB_N_I = {2.4492935982947064E-16d, -1.2246467991473532E-16d, -1.0d, -0.7071067811865475d, -0.3826834323650898d, -0.19509032201612825d, -0.0980171403295606d, -0.049067674327418015d, -0.024541228522912288d, -0.012271538285719925d, -0.006135884649154475d, -0.003067956762965976d, -0.0015339801862847655d, -7.669903187427045E-4d, -3.8349518757139556E-4d, -1.917475973107033E-4d, -9.587379909597734E-5d, -4.793689960306688E-5d, -2.396844980841822E-5d, -1.1984224905069705E-5d, -5.9921124526424275E-6d, -2.996056226334661E-6d, -1.4980281131690111E-6d, -7.490140565847157E-7d, -3.7450702829238413E-7d, -1.8725351414619535E-7d, -9.362675707309808E-8d, -4.681337853654909E-8d, -2.340668926827455E-8d, -1.1703344634137277E-8d, -5.8516723170686385E-9d, -2.9258361585343192E-9d, -1.4629180792671596E-9d, -7.314590396335798E-10d, -3.657295198167899E-10d, -1.8286475990839495E-10d, -9.143237995419748E-11d, -4.571618997709874E-11d, -2.285809498854937E-11d, -1.1429047494274685E-11d, -5.714523747137342E-12d, -2.857261873568671E-12d, -1.4286309367843356E-12d, -7.143154683921678E-13d, -3.571577341960839E-13d, -1.7857886709804195E-13d, -8.928943354902097E-14d, -4.4644716774510487E-14d, -2.2322358387255243E-14d, -1.1161179193627622E-14d, -5.580589596813811E-15d, -2.7902947984069054E-15d, -1.3951473992034527E-15d, -6.975736996017264E-16d, -3.487868498008632E-16d, -1.743934249004316E-16d, -8.71967124502158E-17d, -4.35983562251079E-17d, -2.179917811255395E-17d, -1.0899589056276974E-17d, -5.449794528138487E-18d, -2.7248972640692436E-18d, -1.3624486320346218E-18d};
    private static final double[] W_SUB_N_R = {1.0d, -1.0d, 6.123233995736766E-17d, 0.7071067811865476d, 0.9238795325112867d, 0.9807852804032304d, 0.9951847266721969d, 0.9987954562051724d, 0.9996988186962042d, 0.9999247018391445d, 0.9999811752826011d, 0.9999952938095762d, 0.9999988234517019d, 0.9999997058628822d, 0.9999999264657179d, 0.9999999816164293d, 0.9999999954041073d, 0.9999999988510269d, 0.9999999997127567d, 0.9999999999281892d, 0.9999999999820472d, 0.9999999999955118d, 0.999999999998878d, 0.9999999999997194d, 0.9999999999999298d, 0.9999999999999825d, 0.9999999999999957d, 0.9999999999999989d, 0.9999999999999998d, 0.9999999999999999d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d};
    static final long serialVersionUID = 20120210;
    private final DftNormalization normalization;

    @Deprecated
    private static class MultiDimensionalComplexMatrix implements Cloneable {
        protected int[] dimensionSize;
        protected Object multiDimensionalComplexArray;

        public MultiDimensionalComplexMatrix(Object obj) {
            this.multiDimensionalComplexArray = obj;
            int i = 0;
            for (Object obj2 = obj; obj2 instanceof Object[]; obj2 = obj2[0]) {
                i++;
            }
            this.dimensionSize = new int[i];
            int i2 = 0;
            while (obj instanceof Object[]) {
                Object[] objArr = obj;
                int i3 = i2 + 1;
                this.dimensionSize[i2] = objArr.length;
                obj = objArr[0];
                i2 = i3;
            }
        }

        public Complex get(int... iArr) throws DimensionMismatchException {
            if (iArr == null) {
                if (this.dimensionSize.length <= 0) {
                    return null;
                }
                throw new DimensionMismatchException(0, this.dimensionSize.length);
            } else if (iArr.length != this.dimensionSize.length) {
                throw new DimensionMismatchException(iArr.length, this.dimensionSize.length);
            } else {
                Object obj = this.multiDimensionalComplexArray;
                for (int i = 0; i < this.dimensionSize.length; i++) {
                    obj = obj[iArr[i]];
                }
                return (Complex) obj;
            }
        }

        public Complex set(Complex complex, int... iArr) throws DimensionMismatchException {
            if (iArr == null) {
                if (this.dimensionSize.length <= 0) {
                    return null;
                }
                throw new DimensionMismatchException(0, this.dimensionSize.length);
            } else if (iArr.length != this.dimensionSize.length) {
                throw new DimensionMismatchException(iArr.length, this.dimensionSize.length);
            } else {
                Object[] objArr = (Object[]) this.multiDimensionalComplexArray;
                for (int i = 0; i < this.dimensionSize.length - 1; i++) {
                    objArr = (Object[]) objArr[iArr[i]];
                }
                Complex complex2 = (Complex) objArr[iArr[this.dimensionSize.length - 1]];
                objArr[iArr[this.dimensionSize.length - 1]] = complex;
                return complex2;
            }
        }

        public int[] getDimensionSizes() {
            return (int[]) this.dimensionSize.clone();
        }

        public Object getArray() {
            return this.multiDimensionalComplexArray;
        }

        public Object clone() {
            MultiDimensionalComplexMatrix multiDimensionalComplexMatrix = new MultiDimensionalComplexMatrix(Array.newInstance(Complex.class, this.dimensionSize));
            clone(multiDimensionalComplexMatrix);
            return multiDimensionalComplexMatrix;
        }

        private void clone(MultiDimensionalComplexMatrix multiDimensionalComplexMatrix) {
            int[] iArr = new int[this.dimensionSize.length];
            int i = 1;
            for (int i2 : this.dimensionSize) {
                i *= i2;
            }
            int[][] iArr2 = (int[][]) Array.newInstance(int.class, new int[]{i, this.dimensionSize.length});
            for (int[] arraycopy : iArr2) {
                System.arraycopy(iArr, 0, arraycopy, 0, this.dimensionSize.length);
                for (int i3 = 0; i3 < this.dimensionSize.length; i3++) {
                    iArr[i3] = iArr[i3] + 1;
                    if (iArr[i3] < this.dimensionSize[i3]) {
                        break;
                    }
                    iArr[i3] = 0;
                }
            }
            for (int[] iArr3 : iArr2) {
                multiDimensionalComplexMatrix.set(get(iArr3), iArr3);
            }
        }
    }

    public FastFourierTransformer(DftNormalization dftNormalization) {
        this.normalization = dftNormalization;
    }

    private static void bitReversalShuffle2(double[] dArr, double[] dArr2) {
        int length = dArr.length;
        int i = length >> 1;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 < i2) {
                double d = dArr[i3];
                dArr[i3] = dArr[i2];
                dArr[i2] = d;
                double d2 = dArr2[i3];
                dArr2[i3] = dArr2[i2];
                dArr2[i2] = d2;
            }
            int i4 = i2;
            int i5 = i;
            while (i5 <= i4 && i5 > 0) {
                i4 -= i5;
                i5 >>= 1;
            }
            i2 = i5 + i4;
        }
    }

    private static void normalizeTransformedData(double[][] dArr, DftNormalization dftNormalization, TransformType transformType) {
        int i = 0;
        double[] dArr2 = dArr[0];
        double[] dArr3 = dArr[1];
        int length = dArr2.length;
        switch (dftNormalization) {
            case STANDARD:
                if (transformType == TransformType.INVERSE) {
                    double d = 1.0d / ((double) length);
                    while (i < length) {
                        dArr2[i] = dArr2[i] * d;
                        dArr3[i] = dArr3[i] * d;
                        i++;
                    }
                    return;
                }
                return;
            case UNITARY:
                double sqrt = 1.0d / FastMath.sqrt((double) length);
                while (i < length) {
                    dArr2[i] = dArr2[i] * sqrt;
                    dArr3[i] = dArr3[i] * sqrt;
                    i++;
                }
                return;
            default:
                throw new MathIllegalStateException();
        }
    }

    public static void transformInPlace(double[][] dArr, DftNormalization dftNormalization, TransformType transformType) {
        double[][] dArr2 = dArr;
        TransformType transformType2 = transformType;
        int i = 2;
        if (dArr2.length != 2) {
            throw new DimensionMismatchException(dArr2.length, 2);
        }
        int i2 = 0;
        double[] dArr3 = dArr2[0];
        double[] dArr4 = dArr2[1];
        if (dArr3.length != dArr4.length) {
            throw new DimensionMismatchException(dArr4.length, dArr3.length);
        }
        int length = dArr3.length;
        if (!ArithmeticUtils.isPowerOfTwo((long) length)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, Integer.valueOf(length));
        } else if (length != 1) {
            if (length == 2) {
                double d = dArr3[0];
                double d2 = dArr4[0];
                double d3 = dArr3[1];
                double d4 = dArr4[1];
                dArr3[0] = d + d3;
                dArr4[0] = d2 + d4;
                dArr3[1] = d - d3;
                dArr4[1] = d2 - d4;
                normalizeTransformedData(dArr, dftNormalization, transformType);
                return;
            }
            bitReversalShuffle2(dArr3, dArr4);
            if (transformType2 == TransformType.INVERSE) {
                for (int i3 = 0; i3 < length; i3 += 4) {
                    int i4 = i3 + 1;
                    int i5 = i3 + 2;
                    int i6 = i3 + 3;
                    double d5 = dArr3[i3];
                    double d6 = dArr4[i3];
                    double d7 = dArr3[i5];
                    double d8 = dArr4[i5];
                    double d9 = dArr3[i4];
                    double d10 = dArr4[i4];
                    double d11 = dArr3[i6];
                    double d12 = dArr4[i6];
                    dArr3[i3] = d5 + d7 + d9 + d11;
                    dArr4[i3] = d6 + d8 + d10 + d12;
                    double d13 = d5 - d9;
                    dArr3[i4] = d13 + (d12 - d8);
                    double d14 = d6 - d10;
                    dArr4[i4] = d14 + (d7 - d11);
                    dArr3[i5] = ((d5 - d7) + d9) - d11;
                    dArr4[i5] = ((d6 - d8) + d10) - d12;
                    dArr3[i6] = d13 + (d8 - d12);
                    dArr4[i6] = d14 + (d11 - d7);
                }
            } else {
                for (int i7 = 0; i7 < length; i7 += 4) {
                    int i8 = i7 + 1;
                    int i9 = i7 + 2;
                    int i10 = i7 + 3;
                    double d15 = dArr3[i7];
                    double d16 = dArr4[i7];
                    double d17 = dArr3[i9];
                    double d18 = dArr4[i9];
                    double d19 = dArr3[i8];
                    double d20 = dArr4[i8];
                    double d21 = dArr3[i10];
                    double d22 = dArr4[i10];
                    dArr3[i7] = d15 + d17 + d19 + d21;
                    dArr4[i7] = d16 + d18 + d20 + d22;
                    double d23 = d15 - d19;
                    dArr3[i8] = d23 + (d18 - d22);
                    double d24 = d16 - d20;
                    dArr4[i8] = d24 + (d21 - d17);
                    dArr3[i9] = ((d15 - d17) + d19) - d21;
                    dArr4[i9] = ((d16 - d18) + d20) - d22;
                    dArr3[i10] = d23 + (d22 - d18);
                    dArr4[i10] = d24 + (d17 - d21);
                }
            }
            int i11 = 4;
            while (i11 < length) {
                int i12 = i11 << 1;
                i++;
                double d25 = W_SUB_N_R[i];
                double d26 = W_SUB_N_I[i];
                if (transformType2 == TransformType.INVERSE) {
                    d26 = -d26;
                }
                int i13 = i2;
                while (i13 < length) {
                    int i14 = i13 + i11;
                    double d27 = 1.0d;
                    double d28 = 0.0d;
                    while (i2 < i11) {
                        int i15 = i13 + i2;
                        double d29 = dArr3[i15];
                        double d30 = dArr4[i15];
                        int i16 = i14 + i2;
                        double d31 = dArr3[i16];
                        double d32 = dArr4[i16];
                        double d33 = d27 * d31;
                        double d34 = d28 * d32;
                        dArr3[i15] = (d29 + d33) - d34;
                        double d35 = d32 * d27;
                        double d36 = d31 * d28;
                        dArr4[i15] = d30 + d35 + d36;
                        dArr3[i16] = d29 - (d33 - d34);
                        dArr4[i16] = d30 - (d35 + d36);
                        d28 = (d27 * d26) + (d28 * d25);
                        i2++;
                        d27 = (d27 * d25) - (d28 * d26);
                    }
                    i13 += i12;
                    i2 = 0;
                }
                i11 = i12;
            }
            normalizeTransformedData(dArr, dftNormalization, transformType);
        }
    }

    public Complex[] transform(double[] dArr, TransformType transformType) {
        double[][] dArr2 = {MathArrays.copyOf(dArr, dArr.length), new double[dArr.length]};
        transformInPlace(dArr2, this.normalization, transformType);
        return TransformUtils.createComplexArray(dArr2);
    }

    public Complex[] transform(UnivariateFunction univariateFunction, double d, double d2, int i, TransformType transformType) {
        return transform(FunctionUtils.sample(univariateFunction, d, d2, i), transformType);
    }

    public Complex[] transform(Complex[] complexArr, TransformType transformType) {
        double[][] createRealImaginaryArray = TransformUtils.createRealImaginaryArray(complexArr);
        transformInPlace(createRealImaginaryArray, this.normalization, transformType);
        return TransformUtils.createComplexArray(createRealImaginaryArray);
    }

    @Deprecated
    public Object mdfft(Object obj, TransformType transformType) {
        MultiDimensionalComplexMatrix multiDimensionalComplexMatrix = (MultiDimensionalComplexMatrix) new MultiDimensionalComplexMatrix(obj).clone();
        int[] dimensionSizes = multiDimensionalComplexMatrix.getDimensionSizes();
        for (int i = 0; i < dimensionSizes.length; i++) {
            mdfft(multiDimensionalComplexMatrix, transformType, i, new int[0]);
        }
        return multiDimensionalComplexMatrix.getArray();
    }

    @Deprecated
    private void mdfft(MultiDimensionalComplexMatrix multiDimensionalComplexMatrix, TransformType transformType, int i, int[] iArr) {
        int[] dimensionSizes = multiDimensionalComplexMatrix.getDimensionSizes();
        int i2 = 0;
        if (iArr.length == dimensionSizes.length) {
            Complex[] complexArr = new Complex[dimensionSizes[i]];
            for (int i3 = 0; i3 < dimensionSizes[i]; i3++) {
                iArr[i] = i3;
                complexArr[i3] = multiDimensionalComplexMatrix.get(iArr);
            }
            Complex[] transform = transform(complexArr, transformType);
            while (i2 < dimensionSizes[i]) {
                iArr[i] = i2;
                multiDimensionalComplexMatrix.set(transform[i2], iArr);
                i2++;
            }
            return;
        }
        int[] iArr2 = new int[(iArr.length + 1)];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        if (iArr.length == i) {
            iArr2[i] = 0;
            mdfft(multiDimensionalComplexMatrix, transformType, i, iArr2);
            return;
        }
        while (i2 < dimensionSizes[iArr.length]) {
            iArr2[iArr.length] = i2;
            mdfft(multiDimensionalComplexMatrix, transformType, i, iArr2);
            i2++;
        }
    }
}
