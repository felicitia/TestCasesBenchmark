package org.apache.commons.math3.analysis.differentiation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class DSCompiler {
    private static AtomicReference<DSCompiler[][]> compilers = new AtomicReference<>(null);
    private final int[][][] compIndirection;
    private final int[][] derivativesIndirection;
    private final int[] lowerIndirection;
    private final int[][][] multIndirection;
    private final int order;
    private final int parameters;
    private final int[][] sizes;

    private DSCompiler(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2) {
        this.parameters = i;
        this.order = i2;
        this.sizes = compileSizes(i, i2, dSCompiler);
        this.derivativesIndirection = compileDerivativesIndirection(i, i2, dSCompiler, dSCompiler2);
        this.lowerIndirection = compileLowerIndirection(i, i2, dSCompiler, dSCompiler2);
        this.multIndirection = compileMultiplicationIndirection(i, i2, dSCompiler, dSCompiler2, this.lowerIndirection);
        this.compIndirection = compileCompositionIndirection(i, i2, dSCompiler, dSCompiler2, this.sizes, this.derivativesIndirection);
    }

    public static DSCompiler getCompiler(int i, int i2) {
        int i3;
        int i4;
        DSCompiler dSCompiler;
        DSCompiler[][] dSCompilerArr = (DSCompiler[][]) compilers.get();
        if (dSCompilerArr != null && dSCompilerArr.length > i && dSCompilerArr[i].length > i2 && dSCompilerArr[i][i2] != null) {
            return dSCompilerArr[i][i2];
        }
        if (dSCompilerArr == null) {
            i3 = 0;
        } else {
            i3 = dSCompilerArr.length;
        }
        int max = FastMath.max(i, i3);
        if (dSCompilerArr == null) {
            i4 = 0;
        } else {
            i4 = dSCompilerArr[0].length;
        }
        DSCompiler[][] dSCompilerArr2 = (DSCompiler[][]) Array.newInstance(DSCompiler.class, new int[]{max + 1, FastMath.max(i2, i4) + 1});
        if (dSCompilerArr != null) {
            for (int i5 = 0; i5 < dSCompilerArr.length; i5++) {
                System.arraycopy(dSCompilerArr[i5], 0, dSCompilerArr2[i5], 0, dSCompilerArr[i5].length);
            }
        }
        for (int i6 = 0; i6 <= i + i2; i6++) {
            for (int max2 = FastMath.max(0, i6 - i); max2 <= FastMath.min(i2, i6); max2++) {
                int i7 = i6 - max2;
                if (dSCompilerArr2[i7][max2] == null) {
                    DSCompiler dSCompiler2 = null;
                    if (i7 == 0) {
                        dSCompiler = null;
                    } else {
                        dSCompiler = dSCompilerArr2[i7 - 1][max2];
                    }
                    if (max2 != 0) {
                        dSCompiler2 = dSCompilerArr2[i7][max2 - 1];
                    }
                    dSCompilerArr2[i7][max2] = new DSCompiler(i7, max2, dSCompiler, dSCompiler2);
                }
            }
        }
        compilers.compareAndSet(dSCompilerArr, dSCompilerArr2);
        return dSCompilerArr2[i][i2];
    }

    private static int[][] compileSizes(int i, int i2, DSCompiler dSCompiler) {
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{i + 1, i2 + 1});
        int i3 = 0;
        if (i == 0) {
            Arrays.fill(iArr[0], 1);
        } else {
            System.arraycopy(dSCompiler.sizes, 0, iArr, 0, i);
            iArr[i][0] = 1;
            while (i3 < i2) {
                int i4 = i3 + 1;
                iArr[i][i4] = iArr[i][i3] + iArr[i - 1][i4];
                i3 = i4;
            }
        }
        return iArr;
    }

    private static int[][] compileDerivativesIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2) {
        if (i == 0 || i2 == 0) {
            return (int[][]) Array.newInstance(int.class, new int[]{1, i});
        }
        int length = dSCompiler.derivativesIndirection.length;
        int length2 = dSCompiler2.derivativesIndirection.length;
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{length + length2, i});
        for (int i3 = 0; i3 < length; i3++) {
            System.arraycopy(dSCompiler.derivativesIndirection[i3], 0, iArr[i3], 0, i - 1);
        }
        for (int i4 = 0; i4 < length2; i4++) {
            int i5 = length + i4;
            System.arraycopy(dSCompiler2.derivativesIndirection[i4], 0, iArr[i5], 0, i);
            int[] iArr2 = iArr[i5];
            int i6 = i - 1;
            iArr2[i6] = iArr2[i6] + 1;
        }
        return iArr;
    }

    private static int[] compileLowerIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2) {
        if (i == 0 || i2 <= 1) {
            return new int[]{0};
        }
        int length = dSCompiler.lowerIndirection.length;
        int length2 = dSCompiler2.lowerIndirection.length;
        int[] iArr = new int[(length + length2)];
        System.arraycopy(dSCompiler.lowerIndirection, 0, iArr, 0, length);
        for (int i3 = 0; i3 < length2; i3++) {
            iArr[length + i3] = dSCompiler.getSize() + dSCompiler2.lowerIndirection[i3];
        }
        return iArr;
    }

    private static int[][][] compileMultiplicationIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2, int[] iArr) {
        DSCompiler dSCompiler3 = dSCompiler;
        DSCompiler dSCompiler4 = dSCompiler2;
        int i3 = 3;
        int i4 = 0;
        if (i == 0 || i2 == 0) {
            return new int[][][]{new int[][]{new int[]{1, 0, 0}}};
        }
        int length = dSCompiler3.multIndirection.length;
        int length2 = dSCompiler4.multIndirection.length;
        int[][][] iArr2 = new int[(length + length2)][][];
        System.arraycopy(dSCompiler3.multIndirection, 0, iArr2, 0, length);
        int i5 = 0;
        while (i5 < length2) {
            int[][] iArr3 = dSCompiler4.multIndirection[i5];
            ArrayList arrayList = new ArrayList();
            for (int i6 = i4; i6 < iArr3.length; i6++) {
                int[] iArr4 = new int[i3];
                iArr4[i4] = iArr3[i6][i4];
                iArr4[1] = iArr[iArr3[i6][1]];
                iArr4[2] = iArr3[i6][2] + length;
                arrayList.add(iArr4);
                int[] iArr5 = new int[i3];
                iArr5[i4] = iArr3[i6][i4];
                iArr5[1] = iArr3[i6][1] + length;
                iArr5[2] = iArr[iArr3[i6][2]];
                arrayList.add(iArr5);
            }
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            int i7 = i4;
            while (i7 < arrayList.size()) {
                int[] iArr6 = (int[]) arrayList.get(i7);
                if (iArr6[i4] > 0) {
                    for (int i8 = i7 + 1; i8 < arrayList.size(); i8++) {
                        int[] iArr7 = (int[]) arrayList.get(i8);
                        if (iArr6[1] == iArr7[1] && iArr6[2] == iArr7[2]) {
                            iArr6[0] = iArr6[0] + iArr7[0];
                            iArr7[0] = 0;
                        }
                    }
                    arrayList2.add(iArr6);
                }
                i7++;
                i4 = 0;
            }
            iArr2[length + i5] = (int[][]) arrayList2.toArray(new int[arrayList2.size()][]);
            i5++;
            i3 = 3;
            i4 = 0;
        }
        return iArr2;
    }

    private static int[][][] compileCompositionIndirection(int i, int i2, DSCompiler dSCompiler, DSCompiler dSCompiler2, int[][] iArr, int[][] iArr2) {
        int i3 = i;
        int i4 = i2;
        DSCompiler dSCompiler3 = dSCompiler;
        DSCompiler dSCompiler4 = dSCompiler2;
        int[][] iArr3 = iArr;
        int i5 = 0;
        int i6 = 1;
        if (i3 == 0 || i4 == 0) {
            return new int[][][]{new int[][]{new int[]{1, 0}}};
        }
        int length = dSCompiler3.compIndirection.length;
        int length2 = dSCompiler4.compIndirection.length;
        int[][][] iArr4 = new int[(length + length2)][][];
        System.arraycopy(dSCompiler3.compIndirection, 0, iArr4, 0, length);
        int i7 = 0;
        while (i7 < length2) {
            ArrayList arrayList = new ArrayList();
            int[][] iArr5 = dSCompiler4.compIndirection[i7];
            int length3 = iArr5.length;
            int i8 = i5;
            while (i8 < length3) {
                int[] iArr6 = iArr5[i8];
                int[] iArr7 = new int[(iArr6.length + i6)];
                iArr7[i5] = iArr6[i5];
                iArr7[i6] = iArr6[i6] + 1;
                int[] iArr8 = new int[i3];
                int i9 = i3 - 1;
                iArr8[i9] = i6;
                iArr7[iArr6.length] = getPartialDerivativeIndex(i3, i4, iArr3, iArr8);
                int i10 = i8;
                int i11 = 2;
                while (i11 < iArr6.length) {
                    int i12 = length2;
                    int[] iArr9 = iArr6;
                    int i13 = i10;
                    int i14 = length3;
                    int[][] iArr10 = iArr5;
                    int[][][] iArr11 = iArr4;
                    ArrayList arrayList2 = arrayList;
                    int i15 = i7;
                    iArr7[i11] = convertIndex(iArr6[i11], i3, dSCompiler4.derivativesIndirection, i3, i4, iArr3);
                    i11++;
                    iArr6 = iArr9;
                    arrayList = arrayList2;
                    length3 = i14;
                    iArr5 = iArr10;
                    length2 = i12;
                    iArr4 = iArr11;
                    i7 = i15;
                    i10 = i13;
                }
                int[][] iArr12 = iArr5;
                int i16 = i7;
                int i17 = length2;
                int[][][] iArr13 = iArr4;
                int i18 = i10;
                int[] iArr14 = iArr6;
                int i19 = length3;
                ArrayList arrayList3 = arrayList;
                Arrays.sort(iArr7, 2, iArr7.length);
                arrayList3.add(iArr7);
                int i20 = 2;
                while (i20 < iArr14.length) {
                    int[] iArr15 = new int[iArr14.length];
                    iArr15[0] = iArr14[0];
                    iArr15[1] = iArr14[1];
                    int i21 = 2;
                    while (i21 < iArr14.length) {
                        int i22 = i21;
                        iArr15[i22] = convertIndex(iArr14[i21], i3, dSCompiler4.derivativesIndirection, i3, i4, iArr3);
                        if (i22 == i20) {
                            System.arraycopy(iArr2[iArr15[i22]], 0, iArr8, 0, i3);
                            iArr8[i9] = iArr8[i9] + 1;
                            iArr15[i22] = getPartialDerivativeIndex(i3, i4, iArr3, iArr8);
                        }
                        i21 = i22 + 1;
                        dSCompiler4 = dSCompiler2;
                    }
                    Arrays.sort(iArr15, 2, iArr15.length);
                    arrayList3.add(iArr15);
                    i20++;
                    dSCompiler4 = dSCompiler2;
                }
                i8 = i18 + 1;
                arrayList = arrayList3;
                length3 = i19;
                iArr5 = iArr12;
                length2 = i17;
                iArr4 = iArr13;
                i7 = i16;
                dSCompiler4 = dSCompiler2;
                i5 = 0;
                i6 = 1;
            }
            int i23 = i7;
            int i24 = length2;
            int[][][] iArr16 = iArr4;
            ArrayList arrayList4 = arrayList;
            ArrayList arrayList5 = new ArrayList(arrayList4.size());
            for (int i25 = 0; i25 < arrayList4.size(); i25++) {
                int[] iArr17 = (int[]) arrayList4.get(i25);
                if (iArr17[0] > 0) {
                    for (int i26 = i25 + 1; i26 < arrayList4.size(); i26++) {
                        int[] iArr18 = (int[]) arrayList4.get(i26);
                        boolean z = iArr17.length == iArr18.length;
                        int i27 = 1;
                        while (z && i27 < iArr17.length) {
                            z &= iArr17[i27] == iArr18[i27];
                            i27++;
                        }
                        if (z) {
                            iArr17[0] = iArr17[0] + iArr18[0];
                            iArr18[0] = 0;
                        }
                    }
                    arrayList5.add(iArr17);
                }
            }
            iArr16[length + i23] = (int[][]) arrayList5.toArray(new int[arrayList5.size()][]);
            i7 = i23 + 1;
            length2 = i24;
            iArr4 = iArr16;
            dSCompiler4 = dSCompiler2;
            i5 = 0;
            i6 = 1;
        }
        return iArr4;
    }

    public int getPartialDerivativeIndex(int... iArr) throws DimensionMismatchException, NumberIsTooLargeException {
        if (iArr.length == getFreeParameters()) {
            return getPartialDerivativeIndex(this.parameters, this.order, this.sizes, iArr);
        }
        throw new DimensionMismatchException(iArr.length, getFreeParameters());
    }

    private static int getPartialDerivativeIndex(int i, int i2, int[][] iArr, int... iArr2) throws NumberIsTooLargeException {
        int i3 = 0;
        int i4 = i2;
        int i5 = 0;
        for (int i6 = i - 1; i6 >= 0; i6--) {
            int i7 = iArr2[i6];
            i3 += i7;
            if (i3 > i2) {
                throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(i2), true);
            }
            while (true) {
                int i8 = i7 - 1;
                if (i7 <= 0) {
                    break;
                }
                i5 += iArr[i6][i4];
                i7 = i8;
                i4--;
            }
        }
        return i5;
    }

    private static int convertIndex(int i, int i2, int[][] iArr, int i3, int i4, int[][] iArr2) {
        int[] iArr3 = new int[i3];
        System.arraycopy(iArr[i], 0, iArr3, 0, FastMath.min(i2, i3));
        return getPartialDerivativeIndex(i3, i4, iArr2, iArr3);
    }

    public int[] getPartialDerivativeOrders(int i) {
        return this.derivativesIndirection[i];
    }

    public int getFreeParameters() {
        return this.parameters;
    }

    public int getOrder() {
        return this.order;
    }

    public int getSize() {
        return this.sizes[this.parameters][this.order];
    }

    public void linearCombination(double d, double[] dArr, int i, double d2, double[] dArr2, int i2, double[] dArr3, int i3) {
        for (int i4 = 0; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = MathArrays.linearCombination(d, dArr[i + i4], d2, dArr2[i2 + i4]);
        }
    }

    public void linearCombination(double d, double[] dArr, int i, double d2, double[] dArr2, int i2, double d3, double[] dArr3, int i3, double[] dArr4, int i4) {
        for (int i5 = 0; i5 < getSize(); i5++) {
            dArr4[i4 + i5] = MathArrays.linearCombination(d, dArr[i + i5], d2, dArr2[i2 + i5], d3, dArr3[i3 + i5]);
        }
    }

    public void linearCombination(double d, double[] dArr, int i, double d2, double[] dArr2, int i2, double d3, double[] dArr3, int i3, double d4, double[] dArr4, int i4, double[] dArr5, int i5) {
        for (int i6 = 0; i6 < getSize(); i6++) {
            dArr5[i5 + i6] = MathArrays.linearCombination(d, dArr[i + i6], d2, dArr2[i2 + i6], d3, dArr3[i3 + i6], d4, dArr4[i4 + i6]);
        }
    }

    public void add(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        for (int i4 = 0; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = dArr[i + i4] + dArr2[i2 + i4];
        }
    }

    public void subtract(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        for (int i4 = 0; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = dArr[i + i4] - dArr2[i2 + i4];
        }
    }

    public void multiply(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        for (int i4 = 0; i4 < this.multIndirection.length; i4++) {
            int[][] iArr = this.multIndirection[i4];
            double d = 0.0d;
            for (int i5 = 0; i5 < iArr.length; i5++) {
                d += ((double) iArr[i5][0]) * dArr[i + iArr[i5][1]] * dArr2[i2 + iArr[i5][2]];
            }
            dArr3[i3 + i4] = d;
        }
    }

    public void divide(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double[] dArr4 = new double[getSize()];
        int i4 = i;
        pow(dArr2, i4, -1, dArr4, 0);
        multiply(dArr, i4, dArr4, 0, dArr3, i3);
    }

    public void remainder(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double d = dArr[i] % dArr2[i2];
        double rint = FastMath.rint((dArr[i] - d) / dArr2[i2]);
        dArr3[i3] = d;
        for (int i4 = 1; i4 < getSize(); i4++) {
            dArr3[i3 + i4] = dArr[i + i4] - (dArr2[i2 + i4] * rint);
        }
    }

    public void pow(double[] dArr, int i, double d, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        double pow = FastMath.pow(dArr[i], d - ((double) this.order));
        for (int i3 = this.order; i3 > 0; i3--) {
            dArr3[i3] = pow;
            pow *= dArr[i];
        }
        dArr3[0] = pow;
        double d2 = d;
        for (int i4 = 1; i4 <= this.order; i4++) {
            dArr3[i4] = dArr3[i4] * d2;
            d2 *= d - ((double) i4);
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void pow(double[] dArr, int i, int i2, double[] dArr2, int i3) {
        if (i2 == 0) {
            dArr2[i3] = 1.0d;
            Arrays.fill(dArr2, i3 + 1, i3 + getSize(), 0.0d);
            return;
        }
        double[] dArr3 = new double[(this.order + 1)];
        if (i2 > 0) {
            int min = FastMath.min(this.order, i2);
            double pow = FastMath.pow(dArr[i], i2 - min);
            while (min > 0) {
                dArr3[min] = pow;
                pow *= dArr[i];
                min--;
            }
            dArr3[0] = pow;
        } else {
            double d = 1.0d / dArr[i];
            double pow2 = FastMath.pow(d, -i2);
            for (int i4 = 0; i4 <= this.order; i4++) {
                dArr3[i4] = pow2;
                pow2 *= d;
            }
        }
        double d2 = (double) i2;
        for (int i5 = 1; i5 <= this.order; i5++) {
            dArr3[i5] = dArr3[i5] * d2;
            d2 *= (double) (i2 - i5);
        }
        compose(dArr, i, dArr3, dArr2, i3);
    }

    public void pow(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double[] dArr4 = new double[getSize()];
        log(dArr, i, dArr4, 0);
        double[] dArr5 = new double[getSize()];
        multiply(dArr4, 0, dArr2, i2, dArr5, 0);
        exp(dArr5, 0, dArr3, i3);
    }

    public void rootN(double[] dArr, int i, int i2, double[] dArr2, int i3) {
        double d;
        int i4 = i2;
        double[] dArr3 = new double[(this.order + 1)];
        if (i4 == 2) {
            dArr3[0] = FastMath.sqrt(dArr[i]);
            d = 0.5d / dArr3[0];
        } else if (i4 == 3) {
            dArr3[0] = FastMath.cbrt(dArr[i]);
            d = 1.0d / ((3.0d * dArr3[0]) * dArr3[0]);
        } else {
            double d2 = (double) i4;
            dArr3[0] = FastMath.pow(dArr[i], 1.0d / d2);
            d = 1.0d / (d2 * FastMath.pow(dArr3[0], i4 - 1));
        }
        double d3 = 1.0d / ((double) i4);
        double d4 = 1.0d / dArr[i];
        for (int i5 = 1; i5 <= this.order; i5++) {
            dArr3[i5] = d;
            d *= (d3 - ((double) i5)) * d4;
        }
        compose(dArr, i, dArr3, dArr2, i3);
    }

    public void exp(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(1 + this.order)];
        Arrays.fill(dArr3, FastMath.exp(dArr[i]));
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void expm1(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.expm1(dArr[i]);
        Arrays.fill(dArr3, 1, this.order + 1, FastMath.exp(dArr[i]));
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void log(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.log(dArr[i]);
        if (this.order > 0) {
            double d = 1.0d / dArr[i];
            double d2 = d;
            for (int i3 = 1; i3 <= this.order; i3++) {
                dArr3[i3] = d2;
                d2 *= ((double) (-i3)) * d;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void log1p(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.log1p(dArr[i]);
        if (this.order > 0) {
            double d = 1.0d / (dArr[i] + 1.0d);
            double d2 = d;
            for (int i3 = 1; i3 <= this.order; i3++) {
                dArr3[i3] = d2;
                d2 *= ((double) (-i3)) * d;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void log10(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.log10(dArr[i]);
        if (this.order > 0) {
            double d = 1.0d / dArr[i];
            double log = d / FastMath.log(10.0d);
            for (int i3 = 1; i3 <= this.order; i3++) {
                dArr3[i3] = log;
                log *= ((double) (-i3)) * d;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void cos(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.cos(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = -FastMath.sin(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = -dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void sin(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.sin(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = FastMath.cos(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = -dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void tan(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        boolean z;
        int i3;
        double[] dArr3 = new double[(this.order + 1)];
        double tan = FastMath.tan(dArr[i]);
        boolean z2 = false;
        dArr3[0] = tan;
        if (this.order > 0) {
            int i4 = 2;
            double[] dArr4 = new double[(this.order + 2)];
            dArr4[1] = 1.0d;
            double d2 = tan * tan;
            int i5 = 1;
            while (i5 <= this.order) {
                int i6 = i5 + 1;
                dArr4[i6] = ((double) i5) * dArr4[i5];
                int i7 = i6;
                double d3 = 0.0d;
                while (i7 >= 0) {
                    d3 = (d3 * d2) + dArr4[i7];
                    if (i7 > i4) {
                        int i8 = i7 - 1;
                        int i9 = i7 - 3;
                        d = d2;
                        dArr4[i7 - 2] = (((double) i8) * dArr4[i8]) + (((double) i9) * dArr4[i9]);
                        i3 = 2;
                    } else {
                        i3 = i4;
                        d = d2;
                        if (i7 == i3) {
                            z = false;
                            dArr4[0] = dArr4[1];
                            i7 -= 2;
                            i4 = i3;
                            z2 = z;
                            d2 = d;
                        }
                    }
                    z = false;
                    i7 -= 2;
                    i4 = i3;
                    z2 = z;
                    d2 = d;
                }
                boolean z3 = z2;
                int i10 = i4;
                double d4 = d2;
                if ((i5 & 1) == 0) {
                    d3 *= tan;
                }
                dArr3[i5] = d3;
                i4 = i10;
                z2 = z3;
                i5 = i6;
                d2 = d4;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void acos(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double[] dArr3 = new double[(this.order + 1)];
        double d2 = dArr[i];
        dArr3[0] = FastMath.acos(d2);
        if (this.order > 0) {
            double[] dArr4 = new double[this.order];
            dArr4[0] = -1.0d;
            double d3 = d2 * d2;
            double d4 = 1.0d / (1.0d - d3);
            double sqrt = FastMath.sqrt(d4);
            dArr3[1] = dArr4[0] * sqrt;
            double d5 = sqrt;
            int i3 = 2;
            while (i3 <= this.order) {
                double d6 = 0.0d;
                int i4 = i3 - 1;
                dArr4[i4] = ((double) i4) * dArr4[i3 - 2];
                while (i4 >= 0) {
                    d6 = (d6 * d3) + dArr4[i4];
                    if (i4 > 2) {
                        int i5 = i4 - 1;
                        d = d3;
                        dArr4[i4 - 2] = (((double) i5) * dArr4[i5]) + (((double) ((2 * i3) - i4)) * dArr4[i4 - 3]);
                    } else {
                        d = d3;
                        if (i4 == 2) {
                            dArr4[0] = dArr4[1];
                            i4 -= 2;
                            d3 = d;
                        }
                    }
                    i4 -= 2;
                    d3 = d;
                }
                double d7 = d3;
                if ((i3 & 1) == 0) {
                    d6 *= d2;
                }
                d5 *= d4;
                dArr3[i3] = d6 * d5;
                i3++;
                d3 = d7;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void asin(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double[] dArr3 = new double[(this.order + 1)];
        double d2 = dArr[i];
        dArr3[0] = FastMath.asin(d2);
        if (this.order > 0) {
            double[] dArr4 = new double[this.order];
            dArr4[0] = 1.0d;
            double d3 = d2 * d2;
            double d4 = 1.0d / (1.0d - d3);
            double sqrt = FastMath.sqrt(d4);
            dArr3[1] = dArr4[0] * sqrt;
            double d5 = sqrt;
            int i3 = 2;
            while (i3 <= this.order) {
                double d6 = 0.0d;
                int i4 = i3 - 1;
                dArr4[i4] = ((double) i4) * dArr4[i3 - 2];
                while (i4 >= 0) {
                    d6 = (d6 * d3) + dArr4[i4];
                    if (i4 > 2) {
                        int i5 = i4 - 1;
                        d = d3;
                        dArr4[i4 - 2] = (((double) i5) * dArr4[i5]) + (((double) ((2 * i3) - i4)) * dArr4[i4 - 3]);
                    } else {
                        d = d3;
                        if (i4 == 2) {
                            dArr4[0] = dArr4[1];
                            i4 -= 2;
                            d3 = d;
                        }
                    }
                    i4 -= 2;
                    d3 = d;
                }
                double d7 = d3;
                if ((i3 & 1) == 0) {
                    d6 *= d2;
                }
                d5 *= d4;
                dArr3[i3] = d6 * d5;
                i3++;
                d3 = d7;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atan(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        int i3;
        int i4;
        double[] dArr3 = new double[(this.order + 1)];
        double d2 = dArr[i];
        dArr3[0] = FastMath.atan(d2);
        if (this.order > 0) {
            double[] dArr4 = new double[this.order];
            dArr4[0] = 1.0d;
            double d3 = d2 * d2;
            double d4 = 1.0d / (1.0d + d3);
            dArr3[1] = dArr4[0] * d4;
            int i5 = 2;
            double d5 = d4;
            int i6 = 2;
            while (i6 <= this.order) {
                double d6 = 0.0d;
                int i7 = i6 - 1;
                dArr4[i7] = ((double) (-i6)) * dArr4[i6 - 2];
                while (i7 >= 0) {
                    d6 = (d6 * d3) + dArr4[i7];
                    if (i7 > i5) {
                        int i8 = i7 - 1;
                        i3 = i6;
                        d = d3;
                        dArr4[i7 - 2] = (((double) i8) * dArr4[i8]) + (((double) (i8 - (2 * i3))) * dArr4[i7 - 3]);
                        i4 = 2;
                    } else {
                        d = d3;
                        i4 = i5;
                        i3 = i6;
                        if (i7 == i4) {
                            dArr4[0] = dArr4[1];
                            i7 -= 2;
                            i5 = i4;
                            i6 = i3;
                            d3 = d;
                        }
                    }
                    i7 -= 2;
                    i5 = i4;
                    i6 = i3;
                    d3 = d;
                }
                double d7 = d3;
                int i9 = i5;
                int i10 = i6;
                if ((i10 & 1) == 0) {
                    d6 *= d2;
                }
                d5 *= d4;
                dArr3[i10] = d6 * d5;
                i6 = i10 + 1;
                i5 = i9;
                d3 = d7;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atan2(double[] dArr, int i, double[] dArr2, int i2, double[] dArr3, int i3) {
        double[] dArr4 = new double[getSize()];
        multiply(dArr2, i2, dArr2, i2, dArr4, 0);
        double[] dArr5 = new double[getSize()];
        double[] dArr6 = dArr5;
        multiply(dArr, i, dArr, i, dArr6, 0);
        add(dArr4, 0, dArr5, 0, dArr6, 0);
        rootN(dArr5, 0, 2, dArr4, 0);
        if (dArr2[i2] >= 0.0d) {
            add(dArr4, 0, dArr2, i2, dArr5, 0);
            divide(dArr, i, dArr5, 0, dArr4, 0);
            atan(dArr4, 0, dArr5, 0);
            for (int i4 = 0; i4 < dArr5.length; i4++) {
                dArr3[i3 + i4] = dArr5[i4] * 2.0d;
            }
            return;
        }
        subtract(dArr4, 0, dArr2, i2, dArr5, 0);
        divide(dArr, i, dArr5, 0, dArr4, 0);
        atan(dArr4, 0, dArr5, 0);
        dArr3[i3] = (dArr5[0] <= 0.0d ? -3.141592653589793d : 3.141592653589793d) - (2.0d * dArr5[0]);
        for (int i5 = 1; i5 < dArr5.length; i5++) {
            dArr3[i3 + i5] = -2.0d * dArr5[i5];
        }
    }

    public void cosh(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.cosh(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = FastMath.sinh(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void sinh(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3 = new double[(this.order + 1)];
        dArr3[0] = FastMath.sinh(dArr[i]);
        if (this.order > 0) {
            dArr3[1] = FastMath.cosh(dArr[i]);
            for (int i3 = 2; i3 <= this.order; i3++) {
                dArr3[i3] = dArr3[i3 - 2];
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void tanh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        boolean z;
        int i3;
        double[] dArr3 = new double[(this.order + 1)];
        double tanh = FastMath.tanh(dArr[i]);
        boolean z2 = false;
        dArr3[0] = tanh;
        if (this.order > 0) {
            int i4 = 2;
            double[] dArr4 = new double[(this.order + 2)];
            dArr4[1] = 1.0d;
            double d2 = tanh * tanh;
            int i5 = 1;
            while (i5 <= this.order) {
                int i6 = i5 + 1;
                dArr4[i6] = ((double) (-i5)) * dArr4[i5];
                int i7 = i6;
                double d3 = 0.0d;
                while (i7 >= 0) {
                    d3 = (d3 * d2) + dArr4[i7];
                    if (i7 > i4) {
                        int i8 = i7 - 1;
                        int i9 = i7 - 3;
                        d = d2;
                        dArr4[i7 - 2] = (((double) i8) * dArr4[i8]) - (((double) i9) * dArr4[i9]);
                        i3 = 2;
                    } else {
                        i3 = i4;
                        d = d2;
                        if (i7 == i3) {
                            z = false;
                            dArr4[0] = dArr4[1];
                            i7 -= 2;
                            i4 = i3;
                            z2 = z;
                            d2 = d;
                        }
                    }
                    z = false;
                    i7 -= 2;
                    i4 = i3;
                    z2 = z;
                    d2 = d;
                }
                boolean z3 = z2;
                int i10 = i4;
                double d4 = d2;
                if ((i5 & 1) == 0) {
                    d3 *= tanh;
                }
                dArr3[i5] = d3;
                i4 = i10;
                z2 = z3;
                i5 = i6;
                d2 = d4;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void acosh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        int i3;
        double[] dArr3 = new double[(this.order + 1)];
        double d2 = dArr[i];
        dArr3[0] = FastMath.acosh(d2);
        if (this.order > 0) {
            double[] dArr4 = new double[this.order];
            dArr4[0] = 1.0d;
            double d3 = d2 * d2;
            double d4 = 1.0d / (d3 - 1.0d);
            double sqrt = FastMath.sqrt(d4);
            dArr3[1] = dArr4[0] * sqrt;
            int i4 = 2;
            double d5 = sqrt;
            int i5 = 2;
            while (i5 <= this.order) {
                double d6 = 0.0d;
                int i6 = i5 - 1;
                dArr4[i6] = ((double) (1 - i5)) * dArr4[i5 - 2];
                while (i6 >= 0) {
                    d6 = (d6 * d3) + dArr4[i6];
                    if (i6 > i4) {
                        d = d3;
                        dArr4[i6 - 2] = (((double) (1 - i6)) * dArr4[i6 - 1]) + (((double) (i6 - (i4 * i5))) * dArr4[i6 - 3]);
                        i3 = 2;
                    } else {
                        d = d3;
                        i3 = i4;
                        if (i6 == i3) {
                            dArr4[0] = -dArr4[1];
                            i6 -= 2;
                            i4 = i3;
                            d3 = d;
                        }
                    }
                    i6 -= 2;
                    i4 = i3;
                    d3 = d;
                }
                double d7 = d3;
                int i7 = i4;
                if ((i5 & 1) == 0) {
                    d6 *= d2;
                }
                d5 *= d4;
                dArr3[i5] = d6 * d5;
                i5++;
                i4 = i7;
                d3 = d7;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void asinh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        double[] dArr3 = new double[(this.order + 1)];
        double d2 = dArr[i];
        dArr3[0] = FastMath.asinh(d2);
        if (this.order > 0) {
            double[] dArr4 = new double[this.order];
            dArr4[0] = 1.0d;
            double d3 = d2 * d2;
            double d4 = 1.0d / (1.0d + d3);
            double sqrt = FastMath.sqrt(d4);
            dArr3[1] = dArr4[0] * sqrt;
            double d5 = sqrt;
            int i3 = 2;
            while (i3 <= this.order) {
                double d6 = 0.0d;
                int i4 = i3 - 1;
                dArr4[i4] = ((double) (1 - i3)) * dArr4[i3 - 2];
                while (i4 >= 0) {
                    d6 = (d6 * d3) + dArr4[i4];
                    if (i4 > 2) {
                        int i5 = i4 - 1;
                        d = d3;
                        dArr4[i4 - 2] = (((double) i5) * dArr4[i5]) + (((double) (i4 - (2 * i3))) * dArr4[i4 - 3]);
                    } else {
                        d = d3;
                        if (i4 == 2) {
                            dArr4[0] = dArr4[1];
                            i4 -= 2;
                            d3 = d;
                        }
                    }
                    i4 -= 2;
                    d3 = d;
                }
                double d7 = d3;
                if ((i3 & 1) == 0) {
                    d6 *= d2;
                }
                d5 *= d4;
                dArr3[i3] = d6 * d5;
                i3++;
                d3 = d7;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atanh(double[] dArr, int i, double[] dArr2, int i2) {
        double d;
        int i3;
        int i4;
        double[] dArr3 = new double[(this.order + 1)];
        double d2 = dArr[i];
        dArr3[0] = FastMath.atanh(d2);
        if (this.order > 0) {
            double[] dArr4 = new double[this.order];
            dArr4[0] = 1.0d;
            double d3 = d2 * d2;
            double d4 = 1.0d / (1.0d - d3);
            dArr3[1] = dArr4[0] * d4;
            int i5 = 2;
            double d5 = d4;
            int i6 = 2;
            while (i6 <= this.order) {
                double d6 = 0.0d;
                int i7 = i6 - 1;
                dArr4[i7] = ((double) i6) * dArr4[i6 - 2];
                while (i7 >= 0) {
                    d6 = (d6 * d3) + dArr4[i7];
                    if (i7 > i5) {
                        int i8 = i7 - 1;
                        i3 = i6;
                        d = d3;
                        dArr4[i7 - 2] = (((double) i8) * dArr4[i8]) + (((double) (((2 * i3) - i7) + 1)) * dArr4[i7 - 3]);
                        i4 = 2;
                    } else {
                        d = d3;
                        i4 = i5;
                        i3 = i6;
                        if (i7 == i4) {
                            dArr4[0] = dArr4[1];
                            i7 -= 2;
                            i5 = i4;
                            i6 = i3;
                            d3 = d;
                        }
                    }
                    i7 -= 2;
                    i5 = i4;
                    i6 = i3;
                    d3 = d;
                }
                double d7 = d3;
                int i9 = i5;
                int i10 = i6;
                if ((i10 & 1) == 0) {
                    d6 *= d2;
                }
                d5 *= d4;
                dArr3[i10] = d6 * d5;
                i6 = i10 + 1;
                i5 = i9;
                d3 = d7;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void compose(double[] dArr, int i, double[] dArr2, double[] dArr3, int i2) {
        for (int i3 = 0; i3 < this.compIndirection.length; i3++) {
            int[][] iArr = this.compIndirection[i3];
            double d = 0.0d;
            for (int[] iArr2 : iArr) {
                double d2 = ((double) iArr2[0]) * dArr2[iArr2[1]];
                for (int i4 = 2; i4 < iArr2.length; i4++) {
                    d2 *= dArr[i + iArr2[i4]];
                }
                d += d2;
            }
            dArr3[i2 + i3] = d;
        }
    }

    public double taylor(double[] dArr, int i, double... dArr2) {
        double d = 0.0d;
        for (int size = getSize() - 1; size >= 0; size--) {
            int[] partialDerivativeOrders = getPartialDerivativeOrders(size);
            double d2 = dArr[i + size];
            for (int i2 = 0; i2 < partialDerivativeOrders.length; i2++) {
                if (partialDerivativeOrders[i2] > 0) {
                    d2 *= FastMath.pow(dArr2[i2], partialDerivativeOrders[i2]) / ((double) ArithmeticUtils.factorial(partialDerivativeOrders[i2]));
                }
            }
            d += d2;
        }
        return d;
    }

    public void checkCompatibility(DSCompiler dSCompiler) throws DimensionMismatchException {
        if (this.parameters != dSCompiler.parameters) {
            throw new DimensionMismatchException(this.parameters, dSCompiler.parameters);
        } else if (this.order != dSCompiler.order) {
            throw new DimensionMismatchException(this.order, dSCompiler.order);
        }
    }
}
