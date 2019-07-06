package org.apache.commons.math3.ode.nonstiff;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.FieldVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

public class AdamsNordsieckTransformer {
    private static final Map<Integer, AdamsNordsieckTransformer> CACHE = new HashMap();
    private final double[] c1;
    private final Array2DRowRealMatrix update;

    private AdamsNordsieckTransformer(int i) {
        FieldMatrix buildP = buildP(i);
        FieldDecompositionSolver solver = new FieldLUDecomposition(buildP).getSolver();
        BigFraction[] bigFractionArr = new BigFraction[i];
        Arrays.fill(bigFractionArr, BigFraction.ONE);
        BigFraction[] bigFractionArr2 = (BigFraction[]) solver.solve((FieldVector<T>) new ArrayFieldVector<T>((T[]) bigFractionArr, false)).toArray();
        BigFraction[][] bigFractionArr3 = (BigFraction[][]) buildP.getData();
        for (int length = bigFractionArr3.length - 1; length > 0; length--) {
            bigFractionArr3[length] = bigFractionArr3[length - 1];
        }
        bigFractionArr3[0] = new BigFraction[i];
        Arrays.fill(bigFractionArr3[0], BigFraction.ZERO);
        this.update = MatrixUtils.bigFractionMatrixToRealMatrix(solver.solve((FieldMatrix<T>) new Array2DRowFieldMatrix<T>((T[][]) bigFractionArr3, false)));
        this.c1 = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.c1[i2] = bigFractionArr2[i2].doubleValue();
        }
    }

    public static AdamsNordsieckTransformer getInstance(int i) {
        AdamsNordsieckTransformer adamsNordsieckTransformer;
        synchronized (CACHE) {
            adamsNordsieckTransformer = (AdamsNordsieckTransformer) CACHE.get(Integer.valueOf(i));
            if (adamsNordsieckTransformer == null) {
                adamsNordsieckTransformer = new AdamsNordsieckTransformer(i);
                CACHE.put(Integer.valueOf(i), adamsNordsieckTransformer);
            }
        }
        return adamsNordsieckTransformer;
    }

    public int getNSteps() {
        return this.c1.length;
    }

    private FieldMatrix<BigFraction> buildP(int i) {
        BigFraction[][] bigFractionArr = (BigFraction[][]) Array.newInstance(BigFraction.class, new int[]{i, i});
        int i2 = 0;
        while (i2 < bigFractionArr.length) {
            BigFraction[] bigFractionArr2 = bigFractionArr[i2];
            i2++;
            int i3 = -i2;
            int i4 = i3;
            for (int i5 = 0; i5 < bigFractionArr2.length; i5++) {
                bigFractionArr2[i5] = new BigFraction((i5 + 2) * i4);
                i4 *= i3;
            }
        }
        return new Array2DRowFieldMatrix((T[][]) bigFractionArr, false);
    }

    public Array2DRowRealMatrix initializeHighOrderDerivatives(double d, double[] dArr, double[][] dArr2, double[][] dArr3) {
        double[][] dArr4 = dArr2;
        int i = 2;
        int i2 = 0;
        double[][] dArr5 = (double[][]) Array.newInstance(double.class, new int[]{(dArr4.length - 1) * 2, this.c1.length});
        double[][] dArr6 = (double[][]) Array.newInstance(double.class, new int[]{(dArr4.length - 1) * 2, dArr4[0].length});
        double[] dArr7 = dArr4[0];
        double[] dArr8 = dArr3[0];
        int i3 = 1;
        while (i3 < dArr4.length) {
            double d2 = dArr[i3] - dArr[i2];
            double d3 = d2 / d;
            double d4 = 1.0d / d;
            int i4 = i * i3;
            int i5 = i4 - 2;
            double[] dArr9 = dArr5[i5];
            int i6 = i4 - 1;
            double[] dArr10 = dArr5[i6];
            for (int i7 = i2; i7 < dArr9.length; i7++) {
                d4 *= d3;
                dArr9[i7] = d2 * d4;
                dArr10[i7] = ((double) (i7 + 2)) * d4;
            }
            double[] dArr11 = dArr4[i3];
            double[] dArr12 = dArr3[i3];
            double[] dArr13 = dArr6[i5];
            double[] dArr14 = dArr6[i6];
            for (int i8 = 0; i8 < dArr11.length; i8++) {
                dArr13[i8] = (dArr11[i8] - dArr7[i8]) - (dArr8[i8] * d2);
                dArr14[i8] = dArr12[i8] - dArr8[i8];
            }
            i3++;
            i = 2;
            i2 = 0;
        }
        return new Array2DRowRealMatrix(new QRDecomposition(new Array2DRowRealMatrix(dArr5, false)).getSolver().solve((RealMatrix) new Array2DRowRealMatrix(dArr6, false)).getData(), false);
    }

    public Array2DRowRealMatrix updateHighOrderDerivativesPhase1(Array2DRowRealMatrix array2DRowRealMatrix) {
        return this.update.multiply(array2DRowRealMatrix);
    }

    public void updateHighOrderDerivativesPhase2(double[] dArr, double[] dArr2, Array2DRowRealMatrix array2DRowRealMatrix) {
        double[][] dataRef = array2DRowRealMatrix.getDataRef();
        for (int i = 0; i < dataRef.length; i++) {
            double[] dArr3 = dataRef[i];
            double d = this.c1[i];
            for (int i2 = 0; i2 < dArr3.length; i2++) {
                dArr3[i2] = dArr3[i2] + ((dArr[i2] - dArr2[i2]) * d);
            }
        }
    }
}
