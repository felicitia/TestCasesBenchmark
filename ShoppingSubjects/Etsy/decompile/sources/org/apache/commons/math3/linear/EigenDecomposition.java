package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class EigenDecomposition {
    private static final double EPSILON = 1.0E-12d;
    private RealMatrix cachedD;
    private RealMatrix cachedV;
    private RealMatrix cachedVt;
    private ArrayRealVector[] eigenvectors;
    private double[] imagEigenvalues;
    private final boolean isSymmetric;
    private double[] main;
    private byte maxIter;
    private double[] realEigenvalues;
    private double[] secondary;
    private TriDiagonalTransformer transformer;

    private static class Solver implements DecompositionSolver {
        private final ArrayRealVector[] eigenvectors;
        private double[] imagEigenvalues;
        private double[] realEigenvalues;

        private Solver(double[] dArr, double[] dArr2, ArrayRealVector[] arrayRealVectorArr) {
            this.realEigenvalues = dArr;
            this.imagEigenvalues = dArr2;
            this.eigenvectors = arrayRealVectorArr;
        }

        public RealVector solve(RealVector realVector) {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int length = this.realEigenvalues.length;
            if (realVector.getDimension() != length) {
                throw new DimensionMismatchException(realVector.getDimension(), length);
            }
            double[] dArr = new double[length];
            for (int i = 0; i < length; i++) {
                ArrayRealVector arrayRealVector = this.eigenvectors[i];
                double[] dataRef = arrayRealVector.getDataRef();
                double dotProduct = arrayRealVector.dotProduct(realVector) / this.realEigenvalues[i];
                for (int i2 = 0; i2 < length; i2++) {
                    dArr[i2] = dArr[i2] + (dataRef[i2] * dotProduct);
                }
            }
            return new ArrayRealVector(dArr, false);
        }

        public RealMatrix solve(RealMatrix realMatrix) {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int length = this.realEigenvalues.length;
            if (realMatrix.getRowDimension() != length) {
                throw new DimensionMismatchException(realMatrix.getRowDimension(), length);
            }
            int columnDimension = realMatrix.getColumnDimension();
            double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{length, columnDimension});
            double[] dArr2 = new double[length];
            for (int i = 0; i < columnDimension; i++) {
                for (int i2 = 0; i2 < length; i2++) {
                    dArr2[i2] = realMatrix.getEntry(i2, i);
                    dArr[i2][i] = 0.0d;
                }
                RealMatrix realMatrix2 = realMatrix;
                for (int i3 = 0; i3 < length; i3++) {
                    ArrayRealVector arrayRealVector = this.eigenvectors[i3];
                    double[] dataRef = arrayRealVector.getDataRef();
                    double d = 0.0d;
                    for (int i4 = 0; i4 < length; i4++) {
                        d += arrayRealVector.getEntry(i4) * dArr2[i4];
                    }
                    double d2 = d / this.realEigenvalues[i3];
                    for (int i5 = 0; i5 < length; i5++) {
                        double[] dArr3 = dArr[i5];
                        dArr3[i] = dArr3[i] + (dataRef[i5] * d2);
                    }
                }
            }
            return new Array2DRowRealMatrix(dArr, false);
        }

        public boolean isNonSingular() {
            for (int i = 0; i < this.realEigenvalues.length; i++) {
                if (this.realEigenvalues[i] == 0.0d && this.imagEigenvalues[i] == 0.0d) {
                    return false;
                }
            }
            return true;
        }

        public RealMatrix getInverse() {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int length = this.realEigenvalues.length;
            double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{length, length});
            for (int i = 0; i < length; i++) {
                double[] dArr2 = dArr[i];
                for (int i2 = 0; i2 < length; i2++) {
                    double d = 0.0d;
                    for (int i3 = 0; i3 < length; i3++) {
                        double[] dataRef = this.eigenvectors[i3].getDataRef();
                        d += (dataRef[i] * dataRef[i2]) / this.realEigenvalues[i3];
                    }
                    dArr2[i2] = d;
                }
            }
            return MatrixUtils.createRealMatrix(dArr);
        }
    }

    public EigenDecomposition(RealMatrix realMatrix) throws MathArithmeticException {
        this.maxIter = 30;
        this.isSymmetric = MatrixUtils.isSymmetric(realMatrix, ((double) (10 * realMatrix.getRowDimension() * realMatrix.getColumnDimension())) * Precision.EPSILON);
        if (this.isSymmetric) {
            transformToTridiagonal(realMatrix);
            findEigenVectors(this.transformer.getQ().getData());
            return;
        }
        findEigenVectorsFromSchur(transformToSchur(realMatrix));
    }

    @Deprecated
    public EigenDecomposition(RealMatrix realMatrix, double d) throws MathArithmeticException {
        this(realMatrix);
    }

    public EigenDecomposition(double[] dArr, double[] dArr2) {
        this.maxIter = 30;
        this.isSymmetric = true;
        this.main = (double[]) dArr.clone();
        this.secondary = (double[]) dArr2.clone();
        this.transformer = null;
        int length = dArr.length;
        double[][] dArr3 = (double[][]) Array.newInstance(double.class, new int[]{length, length});
        for (int i = 0; i < length; i++) {
            dArr3[i][i] = 1.0d;
        }
        findEigenVectors(dArr3);
    }

    @Deprecated
    public EigenDecomposition(double[] dArr, double[] dArr2, double d) {
        this(dArr, dArr2);
    }

    public RealMatrix getV() {
        if (this.cachedV == null) {
            int length = this.eigenvectors.length;
            this.cachedV = MatrixUtils.createRealMatrix(length, length);
            for (int i = 0; i < length; i++) {
                this.cachedV.setColumnVector(i, this.eigenvectors[i]);
            }
        }
        return this.cachedV;
    }

    public RealMatrix getD() {
        if (this.cachedD == null) {
            this.cachedD = MatrixUtils.createRealDiagonalMatrix(this.realEigenvalues);
            for (int i = 0; i < this.imagEigenvalues.length; i++) {
                if (Precision.compareTo(this.imagEigenvalues[i], 0.0d, 1.0E-12d) > 0) {
                    this.cachedD.setEntry(i, i + 1, this.imagEigenvalues[i]);
                } else if (Precision.compareTo(this.imagEigenvalues[i], 0.0d, 1.0E-12d) < 0) {
                    this.cachedD.setEntry(i, i - 1, this.imagEigenvalues[i]);
                }
            }
        }
        return this.cachedD;
    }

    public RealMatrix getVT() {
        if (this.cachedVt == null) {
            int length = this.eigenvectors.length;
            this.cachedVt = MatrixUtils.createRealMatrix(length, length);
            for (int i = 0; i < length; i++) {
                this.cachedVt.setRowVector(i, this.eigenvectors[i]);
            }
        }
        return this.cachedVt;
    }

    public boolean hasComplexEigenvalues() {
        for (double equals : this.imagEigenvalues) {
            if (!Precision.equals(equals, 0.0d, 1.0E-12d)) {
                return true;
            }
        }
        return false;
    }

    public double[] getRealEigenvalues() {
        return (double[]) this.realEigenvalues.clone();
    }

    public double getRealEigenvalue(int i) {
        return this.realEigenvalues[i];
    }

    public double[] getImagEigenvalues() {
        return (double[]) this.imagEigenvalues.clone();
    }

    public double getImagEigenvalue(int i) {
        return this.imagEigenvalues[i];
    }

    public RealVector getEigenvector(int i) {
        return this.eigenvectors[i].copy();
    }

    public double getDeterminant() {
        double d = 1.0d;
        for (double d2 : this.realEigenvalues) {
            d *= d2;
        }
        return d;
    }

    public RealMatrix getSquareRoot() {
        if (!this.isSymmetric) {
            throw new MathUnsupportedOperationException();
        }
        double[] dArr = new double[this.realEigenvalues.length];
        for (int i = 0; i < this.realEigenvalues.length; i++) {
            double d = this.realEigenvalues[i];
            if (d <= 0.0d) {
                throw new MathUnsupportedOperationException();
            }
            dArr[i] = FastMath.sqrt(d);
        }
        RealMatrix createRealDiagonalMatrix = MatrixUtils.createRealDiagonalMatrix(dArr);
        RealMatrix v = getV();
        return v.multiply(createRealDiagonalMatrix).multiply(getVT());
    }

    public DecompositionSolver getSolver() {
        if (!hasComplexEigenvalues()) {
            return new Solver(this.realEigenvalues, this.imagEigenvalues, this.eigenvectors);
        }
        throw new MathUnsupportedOperationException();
    }

    private void transformToTridiagonal(RealMatrix realMatrix) {
        this.transformer = new TriDiagonalTransformer(realMatrix);
        this.main = this.transformer.getMainDiagonalRef();
        this.secondary = this.transformer.getSecondaryDiagonalRef();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01eb, code lost:
        r5 = r5 + 1;
        r4 = r30;
        r3 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void findEigenVectors(double[][] r38) {
        /*
            r37 = this;
            r0 = r37
            java.lang.Object r1 = r38.clone()
            double[][] r1 = (double[][]) r1
            double[] r2 = r0.main
            r3 = 0
            int r2 = r2.length
            double[] r4 = new double[r2]
            r0.realEigenvalues = r4
            double[] r4 = new double[r2]
            r0.imagEigenvalues = r4
            double[] r4 = new double[r2]
            r5 = r3
        L_0x0017:
            int r6 = r2 + -1
            if (r5 >= r6) goto L_0x002c
            double[] r6 = r0.realEigenvalues
            double[] r7 = r0.main
            r8 = r7[r5]
            r6[r5] = r8
            double[] r6 = r0.secondary
            r7 = r6[r5]
            r4[r5] = r7
            int r5 = r5 + 1
            goto L_0x0017
        L_0x002c:
            double[] r5 = r0.realEigenvalues
            double[] r7 = r0.main
            r8 = r7[r6]
            r5[r6] = r8
            r7 = 0
            r4[r6] = r7
            r5 = r3
            r9 = r7
        L_0x003a:
            if (r5 >= r2) goto L_0x0063
            double[] r11 = r0.realEigenvalues
            r12 = r11[r5]
            double r11 = org.apache.commons.math3.util.FastMath.abs(r12)
            int r13 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r13 <= 0) goto L_0x0050
            double[] r9 = r0.realEigenvalues
            r10 = r9[r5]
            double r9 = org.apache.commons.math3.util.FastMath.abs(r10)
        L_0x0050:
            r11 = r4[r5]
            double r11 = org.apache.commons.math3.util.FastMath.abs(r11)
            int r13 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r13 <= 0) goto L_0x0060
            r9 = r4[r5]
            double r9 = org.apache.commons.math3.util.FastMath.abs(r9)
        L_0x0060:
            int r5 = r5 + 1
            goto L_0x003a
        L_0x0063:
            int r5 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r5 == 0) goto L_0x008f
            r5 = r3
        L_0x0068:
            if (r5 >= r2) goto L_0x008f
            double[] r11 = r0.realEigenvalues
            r12 = r11[r5]
            double r11 = org.apache.commons.math3.util.FastMath.abs(r12)
            double r13 = org.apache.commons.math3.util.Precision.EPSILON
            double r13 = r13 * r9
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 > 0) goto L_0x007d
            double[] r11 = r0.realEigenvalues
            r11[r5] = r7
        L_0x007d:
            r11 = r4[r5]
            double r11 = org.apache.commons.math3.util.FastMath.abs(r11)
            double r13 = org.apache.commons.math3.util.Precision.EPSILON
            double r13 = r13 * r9
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 > 0) goto L_0x008c
            r4[r5] = r7
        L_0x008c:
            int r5 = r5 + 1
            goto L_0x0068
        L_0x008f:
            r5 = r3
        L_0x0090:
            if (r5 >= r2) goto L_0x01fb
            r9 = r3
        L_0x0093:
            r10 = r5
        L_0x0094:
            if (r10 >= r6) goto L_0x00b9
            double[] r11 = r0.realEigenvalues
            r12 = r11[r10]
            double r11 = org.apache.commons.math3.util.FastMath.abs(r12)
            double[] r13 = r0.realEigenvalues
            int r14 = r10 + 1
            r7 = r13[r14]
            double r7 = org.apache.commons.math3.util.FastMath.abs(r7)
            double r11 = r11 + r7
            r7 = r4[r10]
            double r7 = org.apache.commons.math3.util.FastMath.abs(r7)
            double r7 = r7 + r11
            int r13 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r13 != 0) goto L_0x00b5
            goto L_0x00b9
        L_0x00b5:
            r10 = r14
            r7 = 0
            goto L_0x0094
        L_0x00b9:
            if (r10 == r5) goto L_0x01e7
            byte r7 = r0.maxIter
            if (r9 != r7) goto L_0x00cf
            org.apache.commons.math3.exception.MaxCountExceededException r1 = new org.apache.commons.math3.exception.MaxCountExceededException
            org.apache.commons.math3.exception.util.LocalizedFormats r2 = org.apache.commons.math3.exception.util.LocalizedFormats.CONVERGENCE_FAILED
            byte r4 = r0.maxIter
            java.lang.Byte r4 = java.lang.Byte.valueOf(r4)
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r1.<init>(r2, r4, r3)
            throw r1
        L_0x00cf:
            int r9 = r9 + 1
            double[] r7 = r0.realEigenvalues
            int r8 = r5 + 1
            r11 = r7[r8]
            double[] r7 = r0.realEigenvalues
            r13 = r7[r5]
            double r11 = r11 - r13
            r7 = r4[r5]
            r13 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r7 = r7 * r13
            double r11 = r11 / r7
            double r7 = r11 * r11
            r18 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r7 = r18 + r7
            double r7 = org.apache.commons.math3.util.FastMath.sqrt(r7)
            r15 = 0
            int r20 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r20 >= 0) goto L_0x0104
            double[] r15 = r0.realEigenvalues
            r20 = r15[r10]
            double[] r15 = r0.realEigenvalues
            r22 = r15[r5]
            double r20 = r20 - r22
            r22 = r4[r5]
            double r11 = r11 - r7
            double r22 = r22 / r11
            double r20 = r20 + r22
            goto L_0x0115
        L_0x0104:
            double[] r15 = r0.realEigenvalues
            r20 = r15[r10]
            double[] r15 = r0.realEigenvalues
            r22 = r15[r5]
            double r20 = r20 - r22
            r22 = r4[r5]
            double r11 = r11 + r7
            double r22 = r22 / r11
            double r20 = r20 + r22
        L_0x0115:
            int r11 = r10 + -1
            r26 = r7
            r7 = r18
            r13 = r20
            r22 = 0
            r20 = r7
        L_0x0121:
            if (r11 < r5) goto L_0x01cf
            r26 = r4[r11]
            double r7 = r7 * r26
            r26 = r4[r11]
            double r20 = r20 * r26
            double r26 = org.apache.commons.math3.util.FastMath.abs(r7)
            double r28 = org.apache.commons.math3.util.FastMath.abs(r13)
            int r12 = (r26 > r28 ? 1 : (r26 == r28 ? 0 : -1))
            if (r12 < 0) goto L_0x0153
            double r26 = r13 / r7
            double r28 = r26 * r26
            r30 = r4
            double r3 = r28 + r18
            double r3 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            int r12 = r11 + 1
            double r7 = r7 * r3
            r30[r12] = r7
            double r7 = r18 / r3
            double r26 = r26 * r7
        L_0x014c:
            r35 = r3
            r3 = r26
            r26 = r35
            goto L_0x0169
        L_0x0153:
            r30 = r4
            double r7 = r7 / r13
            double r3 = r7 * r7
            double r3 = r3 + r18
            double r3 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            int r12 = r11 + 1
            double r26 = r13 * r3
            r30[r12] = r26
            double r26 = r18 / r3
            double r7 = r7 * r26
            goto L_0x014c
        L_0x0169:
            int r12 = r11 + 1
            r28 = r30[r12]
            r15 = 0
            int r17 = (r28 > r15 ? 1 : (r28 == r15 ? 0 : -1))
            if (r17 != 0) goto L_0x017e
            double[] r3 = r0.realEigenvalues
            r7 = r3[r12]
            double r7 = r7 - r22
            r3[r12] = r7
            r30[r10] = r15
            goto L_0x01d1
        L_0x017e:
            double[] r13 = r0.realEigenvalues
            r14 = r13[r12]
            double r14 = r14 - r22
            double[] r13 = r0.realEigenvalues
            r22 = r13[r11]
            double r22 = r22 - r14
            double r22 = r22 * r7
            r24 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r26 = r24 * r3
            double r26 = r26 * r20
            double r26 = r22 + r26
            double r22 = r7 * r26
            double[] r13 = r0.realEigenvalues
            double r14 = r14 + r22
            r13[r12] = r14
            double r13 = r3 * r26
            double r13 = r13 - r20
            r15 = 0
        L_0x01a1:
            if (r15 >= r2) goto L_0x01c6
            r20 = r1[r15]
            r28 = r20[r12]
            r20 = r1[r15]
            r21 = r1[r15]
            r31 = r21[r11]
            double r31 = r31 * r7
            double r33 = r3 * r28
            double r31 = r31 + r33
            r20[r12] = r31
            r20 = r1[r15]
            r21 = r1[r15]
            r31 = r21[r11]
            double r31 = r31 * r3
            double r28 = r28 * r7
            double r31 = r31 - r28
            r20[r11] = r31
            int r15 = r15 + 1
            goto L_0x01a1
        L_0x01c6:
            int r11 = r11 + -1
            r20 = r3
            r4 = r30
            r3 = 0
            goto L_0x0121
        L_0x01cf:
            r30 = r4
        L_0x01d1:
            r3 = 0
            int r7 = (r26 > r3 ? 1 : (r26 == r3 ? 0 : -1))
            if (r7 != 0) goto L_0x01da
            if (r11 < r5) goto L_0x01da
            goto L_0x01e9
        L_0x01da:
            double[] r7 = r0.realEigenvalues
            r11 = r7[r5]
            double r11 = r11 - r22
            r7[r5] = r11
            r30[r5] = r13
            r30[r10] = r3
            goto L_0x01e9
        L_0x01e7:
            r30 = r4
        L_0x01e9:
            if (r10 != r5) goto L_0x01f4
            int r5 = r5 + 1
            r4 = r30
            r3 = 0
            r7 = 0
            goto L_0x0090
        L_0x01f4:
            r4 = r30
            r3 = 0
            r7 = 0
            goto L_0x0093
        L_0x01fb:
            r3 = 0
        L_0x01fc:
            if (r3 >= r2) goto L_0x0240
            double[] r4 = r0.realEigenvalues
            r5 = r4[r3]
            int r4 = r3 + 1
            r8 = r3
            r6 = r5
            r5 = r4
        L_0x0207:
            if (r5 >= r2) goto L_0x021a
            double[] r9 = r0.realEigenvalues
            r10 = r9[r5]
            int r9 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r9 <= 0) goto L_0x0217
            double[] r6 = r0.realEigenvalues
            r7 = r6[r5]
            r6 = r7
            r8 = r5
        L_0x0217:
            int r5 = r5 + 1
            goto L_0x0207
        L_0x021a:
            if (r8 == r3) goto L_0x023e
            double[] r5 = r0.realEigenvalues
            double[] r9 = r0.realEigenvalues
            r10 = r9[r3]
            r5[r8] = r10
            double[] r5 = r0.realEigenvalues
            r5[r3] = r6
            r5 = 0
        L_0x0229:
            if (r5 >= r2) goto L_0x023e
            r6 = r1[r5]
            r9 = r6[r3]
            r6 = r1[r5]
            r7 = r1[r5]
            r11 = r7[r8]
            r6[r3] = r11
            r6 = r1[r5]
            r6[r8] = r9
            int r5 = r5 + 1
            goto L_0x0229
        L_0x023e:
            r3 = r4
            goto L_0x01fc
        L_0x0240:
            r3 = 0
            r4 = 0
        L_0x0243:
            if (r3 >= r2) goto L_0x025c
            double[] r6 = r0.realEigenvalues
            r7 = r6[r3]
            double r6 = org.apache.commons.math3.util.FastMath.abs(r7)
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 <= 0) goto L_0x0259
            double[] r4 = r0.realEigenvalues
            r5 = r4[r3]
            double r4 = org.apache.commons.math3.util.FastMath.abs(r5)
        L_0x0259:
            int r3 = r3 + 1
            goto L_0x0243
        L_0x025c:
            r6 = 0
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 == 0) goto L_0x0280
            r3 = 0
        L_0x0263:
            if (r3 >= r2) goto L_0x0280
            double[] r6 = r0.realEigenvalues
            r7 = r6[r3]
            double r6 = org.apache.commons.math3.util.FastMath.abs(r7)
            double r8 = org.apache.commons.math3.util.Precision.EPSILON
            double r8 = r8 * r4
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x027b
            double[] r6 = r0.realEigenvalues
            r7 = 0
            r6[r3] = r7
            goto L_0x027d
        L_0x027b:
            r7 = 0
        L_0x027d:
            int r3 = r3 + 1
            goto L_0x0263
        L_0x0280:
            org.apache.commons.math3.linear.ArrayRealVector[] r3 = new org.apache.commons.math3.linear.ArrayRealVector[r2]
            r0.eigenvectors = r3
            double[] r3 = new double[r2]
            r4 = 0
        L_0x0287:
            if (r4 >= r2) goto L_0x02a1
            r5 = 0
        L_0x028a:
            if (r5 >= r2) goto L_0x0295
            r6 = r1[r5]
            r7 = r6[r4]
            r3[r5] = r7
            int r5 = r5 + 1
            goto L_0x028a
        L_0x0295:
            org.apache.commons.math3.linear.ArrayRealVector[] r5 = r0.eigenvectors
            org.apache.commons.math3.linear.ArrayRealVector r6 = new org.apache.commons.math3.linear.ArrayRealVector
            r6.<init>(r3)
            r5[r4] = r6
            int r4 = r4 + 1
            goto L_0x0287
        L_0x02a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors(double[][]):void");
    }

    private SchurTransformer transformToSchur(RealMatrix realMatrix) {
        SchurTransformer schurTransformer = new SchurTransformer(realMatrix);
        double[][] data = schurTransformer.getT().getData();
        int i = 0;
        this.realEigenvalues = new double[data.length];
        this.imagEigenvalues = new double[data.length];
        while (i < this.realEigenvalues.length) {
            if (i != this.realEigenvalues.length - 1) {
                int i2 = i + 1;
                if (!Precision.equals(data[i2][i], 0.0d, 1.0E-12d)) {
                    double d = data[i2][i2];
                    double d2 = 0.5d * (data[i][i] - d);
                    double sqrt = FastMath.sqrt(FastMath.abs((d2 * d2) + (data[i2][i] * data[i][i2])));
                    double d3 = d + d2;
                    this.realEigenvalues[i] = d3;
                    this.imagEigenvalues[i] = sqrt;
                    this.realEigenvalues[i2] = d3;
                    this.imagEigenvalues[i2] = -sqrt;
                    i = i2;
                    i++;
                }
            }
            this.realEigenvalues[i] = data[i][i];
            i++;
        }
        return schurTransformer;
    }

    private Complex cdiv(double d, double d2, double d3, double d4) {
        return new Complex(d, d2).divide(new Complex(d3, d4));
    }

    private void findEigenVectorsFromSchur(SchurTransformer schurTransformer) throws MathArithmeticException {
        int i;
        boolean z;
        double[][] dArr;
        double[][] dArr2;
        int i2;
        int i3;
        double d;
        int i4;
        double d2;
        int i5;
        int i6;
        double[][] dArr3;
        int i7;
        double[][] dArr4;
        int i8;
        double d3;
        double d4;
        int i9;
        double d5;
        double d6;
        double d7;
        EigenDecomposition eigenDecomposition = this;
        double[][] data = schurTransformer.getT().getData();
        double[][] data2 = schurTransformer.getP().getData();
        double d8 = 0.0d;
        int length = data.length;
        double d9 = 0.0d;
        for (int i10 = 0; i10 < length; i10++) {
            for (int max = FastMath.max(i10 - 1, 0); max < length; max++) {
                d9 += FastMath.abs(data[i10][max]);
            }
        }
        if (Precision.equals(d9, 0.0d, 1.0E-12d)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        int i11 = length - 1;
        int i12 = i11;
        double d10 = 0.0d;
        double d11 = 0.0d;
        double d12 = 0.0d;
        while (i12 >= 0) {
            double d13 = eigenDecomposition.realEigenvalues[i12];
            double d14 = eigenDecomposition.imagEigenvalues[i12];
            if (Precision.equals(d14, d8)) {
                data[i12][i12] = 4607182418800017408;
                int i13 = i12 - 1;
                int i14 = i12;
                double d15 = d10;
                double d16 = d11;
                while (i13 >= 0) {
                    double d17 = data[i13][i13] - d13;
                    double d18 = d8;
                    for (int i15 = i14; i15 <= i12; i15++) {
                        d18 += data[i13][i15] * data[i15][i12];
                    }
                    int i16 = i11;
                    int i17 = length;
                    if (((double) Precision.compareTo(eigenDecomposition.imagEigenvalues[i13], 0.0d, 1.0E-12d)) < 0.0d) {
                        d15 = d18;
                        d16 = d17;
                    } else {
                        if (!Precision.equals(eigenDecomposition.imagEigenvalues[i13], 0.0d)) {
                            int i18 = i13 + 1;
                            double d19 = data[i13][i18];
                            double d20 = data[i18][i13];
                            double d21 = ((d19 * d15) - (d16 * d18)) / (((eigenDecomposition.realEigenvalues[i13] - d13) * (eigenDecomposition.realEigenvalues[i13] - d13)) + (eigenDecomposition.imagEigenvalues[i13] * eigenDecomposition.imagEigenvalues[i13]));
                            data[i13][i12] = d21;
                            if (FastMath.abs(d19) > FastMath.abs(d16)) {
                                data[i18][i12] = ((-d18) - (d17 * d21)) / d19;
                            } else {
                                data[i18][i12] = ((-d15) - (d20 * d21)) / d16;
                            }
                        } else if (d17 != 0.0d) {
                            data[i13][i12] = (-d18) / d17;
                        } else {
                            data[i13][i12] = (-d18) / (Precision.EPSILON * d9);
                        }
                        double abs = FastMath.abs(data[i13][i12]);
                        if (Precision.EPSILON * abs * abs > 1.0d) {
                            for (int i19 = i13; i19 <= i12; i19++) {
                                data[i19][i12] = data[i19][i12] / abs;
                            }
                        }
                        i14 = i13;
                    }
                    i13--;
                    d12 = d18;
                    i11 = i16;
                    length = i17;
                    d8 = 0.0d;
                }
                i3 = length;
                d10 = d15;
                d11 = d16;
                i2 = i11;
                i4 = i12;
                dArr = data;
                dArr2 = data2;
                d = d8;
            } else {
                int i20 = i11;
                i3 = length;
                if (d14 < d8) {
                    int i21 = i12 - 1;
                    if (FastMath.abs(data[i12][i21]) > FastMath.abs(data[i21][i12])) {
                        data[i21][i21] = d14 / data[i12][i21];
                        data[i21][i12] = (-(data[i12][i12] - d13)) / data[i12][i21];
                        d2 = d14;
                        i5 = i12;
                        i6 = i20;
                    } else {
                        d2 = d14;
                        i5 = i12;
                        i6 = i20;
                        Complex cdiv = eigenDecomposition.cdiv(0.0d, -data[i21][i12], data[i21][i21] - d13, d2);
                        data[i21][i21] = cdiv.getReal();
                        data[i21][i5] = cdiv.getImaginary();
                    }
                    data[i5][i21] = 0;
                    data[i5][i5] = 4607182418800017408;
                    int i22 = i5 - 2;
                    int i23 = i21;
                    double d22 = d10;
                    double d23 = d11;
                    double d24 = d12;
                    while (i22 >= 0) {
                        double d25 = d23;
                        double d26 = d22;
                        double d27 = 0.0d;
                        double d28 = 0.0d;
                        for (int i24 = i23; i24 <= i5; i24++) {
                            d28 += data[i22][i24] * data[i24][i21];
                            d27 += data[i22][i24] * data[i24][i5];
                        }
                        int i25 = i23;
                        double d29 = data[i22][i22] - d13;
                        double d30 = d24;
                        if (((double) Precision.compareTo(eigenDecomposition.imagEigenvalues[i22], 0.0d, 1.0E-12d)) < 0.0d) {
                            d3 = d27;
                            d24 = d28;
                            dArr3 = data;
                            dArr4 = data2;
                            i7 = i21;
                            i8 = i6;
                            i9 = i5;
                            d4 = d2;
                            i23 = i25;
                            d23 = d29;
                        } else {
                            i8 = i6;
                            int i26 = i5;
                            if (Precision.equals(eigenDecomposition.imagEigenvalues[i22], 0.0d)) {
                                double d31 = d25;
                                double d32 = d30;
                                double d33 = d26;
                                Complex cdiv2 = eigenDecomposition.cdiv(-d28, -d27, d29, d2);
                                data[i22][i21] = cdiv2.getReal();
                                data[i22][i26] = cdiv2.getImaginary();
                                dArr3 = data;
                                dArr4 = data2;
                                i7 = i21;
                                d3 = d33;
                                d4 = d2;
                                d6 = d31;
                                d5 = d32;
                            } else {
                                double d34 = d26;
                                double d35 = d25;
                                double d36 = d29;
                                double d37 = d30;
                                int i27 = i22 + 1;
                                double d38 = data[i22][i27];
                                dArr4 = data2;
                                i7 = i21;
                                double d39 = data[i27][i22];
                                double d40 = d34;
                                double d41 = d2;
                                double d42 = d27;
                                double d43 = (((eigenDecomposition.realEigenvalues[i22] - d13) * (eigenDecomposition.realEigenvalues[i22] - d13)) + (eigenDecomposition.imagEigenvalues[i22] * eigenDecomposition.imagEigenvalues[i22])) - (d41 * d41);
                                dArr3 = data;
                                double d44 = (eigenDecomposition.realEigenvalues[i22] - d13) * 2.0d * d41;
                                double d45 = d28;
                                if (!Precision.equals(d43, 0.0d) || !Precision.equals(d44, 0.0d)) {
                                    d7 = d44;
                                    d6 = d35;
                                } else {
                                    d7 = d44;
                                    d6 = d35;
                                    d43 = Precision.EPSILON * d9 * (FastMath.abs(d36) + FastMath.abs(d41) + FastMath.abs(d38) + FastMath.abs(d39) + FastMath.abs(d6));
                                }
                                double d46 = d39;
                                double d47 = d37;
                                double d48 = d47;
                                double d49 = d38;
                                double d50 = d36;
                                double d51 = d40;
                                d4 = d41;
                                double d52 = d42;
                                double d53 = d45;
                                Complex cdiv3 = cdiv(((d38 * d47) - (d6 * d45)) + (d41 * d42), ((d38 * d40) - (d6 * d42)) - (d41 * d45), d43, d7);
                                dArr3[i22][i7] = cdiv3.getReal();
                                dArr3[i22][i26] = cdiv3.getImaginary();
                                if (FastMath.abs(d49) > FastMath.abs(d6) + FastMath.abs(d4)) {
                                    dArr3[i27][i7] = (((-d53) - (d50 * dArr3[i22][i7])) + (d4 * dArr3[i22][i26])) / d49;
                                    dArr3[i27][i26] = (((-d52) - (d50 * dArr3[i22][i26])) - (d4 * dArr3[i22][i7])) / d49;
                                    d5 = d48;
                                    d3 = d51;
                                } else {
                                    d5 = d48;
                                    double d54 = d51;
                                    d3 = d54;
                                    Complex cdiv4 = cdiv((-d5) - (d46 * dArr3[i22][i7]), (-d54) - (dArr3[i22][i26] * d46), d6, d4);
                                    dArr3[i27][i7] = cdiv4.getReal();
                                    dArr3[i27][i26] = cdiv4.getImaginary();
                                }
                            }
                            double max2 = FastMath.max(FastMath.abs(dArr3[i22][i7]), FastMath.abs(dArr3[i22][i26]));
                            if (Precision.EPSILON * max2 * max2 > 1.0d) {
                                i9 = i26;
                                for (int i28 = i22; i28 <= i9; i28++) {
                                    dArr3[i28][i7] = dArr3[i28][i7] / max2;
                                    dArr3[i28][i9] = dArr3[i28][i9] / max2;
                                }
                            } else {
                                i9 = i26;
                            }
                            d23 = d6;
                            d24 = d5;
                            i23 = i22;
                        }
                        i22--;
                        eigenDecomposition = this;
                        d2 = d4;
                        i6 = i8;
                        data2 = dArr4;
                        i21 = i7;
                        data = dArr3;
                        i5 = i9;
                        d22 = d3;
                    }
                    double d55 = d22;
                    dArr = data;
                    dArr2 = data2;
                    i2 = i6;
                    i4 = i5;
                    d = 0.0d;
                    d12 = d24;
                    d10 = d55;
                    d11 = d23;
                } else {
                    i4 = i12;
                    dArr = data;
                    dArr2 = data2;
                    i2 = i20;
                    d = 0.0d;
                }
            }
            i12 = i4 - 1;
            eigenDecomposition = this;
            d8 = d;
            length = i3;
            i11 = i2;
            data2 = dArr2;
            data = dArr;
        }
        int i29 = i11;
        double[][] dArr5 = data;
        double[][] dArr6 = data2;
        double d56 = d8;
        int i30 = length;
        int i31 = 0;
        while (i31 < i30) {
            boolean z2 = true;
            if (i31 < 0) {
                z = true;
                i = i29;
            } else {
                i = i29;
                z = false;
            }
            if (i31 <= i) {
                z2 = false;
            }
            if (z || z2) {
                for (int i32 = i31; i32 < i30; i32++) {
                    dArr6[i31][i32] = dArr5[i31][i32];
                }
            }
            i31++;
            i29 = i;
        }
        int i33 = i29;
        for (int i34 = i33; i34 >= 0; i34--) {
            for (int i35 = 0; i35 <= i33; i35++) {
                double d57 = d56;
                for (int i36 = 0; i36 <= FastMath.min(i34, i33); i36++) {
                    d57 += dArr6[i35][i36] * dArr5[i36][i34];
                }
                dArr6[i35][i34] = d57;
            }
        }
        this.eigenvectors = new ArrayRealVector[i30];
        double[] dArr7 = new double[i30];
        for (int i37 = 0; i37 < i30; i37++) {
            for (int i38 = 0; i38 < i30; i38++) {
                dArr7[i38] = dArr6[i38][i37];
            }
            this.eigenvectors[i37] = new ArrayRealVector(dArr7);
        }
    }
}
