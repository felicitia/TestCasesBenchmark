package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class SingularValueDecomposition {
    private static final double EPS = 2.220446049250313E-16d;
    private static final double TINY = 1.6033346880071782E-291d;
    private RealMatrix cachedS;
    private final RealMatrix cachedU;
    private RealMatrix cachedUt;
    private final RealMatrix cachedV;
    private RealMatrix cachedVt;
    private final int m;
    private final int n;
    /* access modifiers changed from: private */
    public final double[] singularValues;
    private final double tol;
    private final boolean transposed;

    private static class Solver implements DecompositionSolver {
        private boolean nonSingular;
        private final RealMatrix pseudoInverse;

        private Solver(double[] dArr, RealMatrix realMatrix, RealMatrix realMatrix2, boolean z, double d) {
            double[][] data = realMatrix.getData();
            for (int i = 0; i < dArr.length; i++) {
                double d2 = dArr[i] > d ? 1.0d / dArr[i] : 0.0d;
                double[] dArr2 = data[i];
                for (int i2 = 0; i2 < dArr2.length; i2++) {
                    dArr2[i2] = dArr2[i2] * d2;
                }
            }
            this.pseudoInverse = realMatrix2.multiply(new Array2DRowRealMatrix(data, false));
            this.nonSingular = z;
        }

        public RealVector solve(RealVector realVector) {
            return this.pseudoInverse.operate(realVector);
        }

        public RealMatrix solve(RealMatrix realMatrix) {
            return this.pseudoInverse.multiply(realMatrix);
        }

        public boolean isNonSingular() {
            return this.nonSingular;
        }

        public RealMatrix getInverse() {
            return this.pseudoInverse;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:192:0x03b1  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x03cc  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x0530  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x058d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SingularValueDecomposition(org.apache.commons.math3.linear.RealMatrix r34) {
        /*
            r33 = this;
            r0 = r33
            r33.<init>()
            int r1 = r34.getRowDimension()
            int r2 = r34.getColumnDimension()
            r3 = 0
            r4 = 1
            if (r1 >= r2) goto L_0x0028
            r0.transposed = r4
            org.apache.commons.math3.linear.RealMatrix r1 = r34.transpose()
            double[][] r1 = r1.getData()
            int r2 = r34.getColumnDimension()
            r0.m = r2
            int r2 = r34.getRowDimension()
            r0.n = r2
            goto L_0x003a
        L_0x0028:
            r0.transposed = r3
            double[][] r1 = r34.getData()
            int r2 = r34.getRowDimension()
            r0.m = r2
            int r2 = r34.getColumnDimension()
            r0.n = r2
        L_0x003a:
            int r2 = r0.n
            double[] r2 = new double[r2]
            r0.singularValues = r2
            int r2 = r0.m
            int r5 = r0.n
            int[] r2 = new int[]{r2, r5}
            java.lang.Class<double> r5 = double.class
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r5, r2)
            double[][] r2 = (double[][]) r2
            int r5 = r0.n
            int r6 = r0.n
            int[] r5 = new int[]{r5, r6}
            java.lang.Class<double> r6 = double.class
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r6, r5)
            double[][] r5 = (double[][]) r5
            int r6 = r0.n
            double[] r6 = new double[r6]
            int r7 = r0.m
            double[] r7 = new double[r7]
            int r8 = r0.m
            int r8 = r8 - r4
            int r9 = r0.n
            int r8 = org.apache.commons.math3.util.FastMath.min(r8, r9)
            int r9 = r0.n
            r10 = 2
            int r9 = r9 - r10
            int r9 = org.apache.commons.math3.util.FastMath.max(r3, r9)
            r11 = r3
        L_0x007a:
            int r12 = org.apache.commons.math3.util.FastMath.max(r8, r9)
            r15 = 0
            if (r11 >= r12) goto L_0x01fa
            if (r11 >= r8) goto L_0x00e8
            double[] r12 = r0.singularValues
            r12[r11] = r15
            r12 = r11
        L_0x0089:
            int r10 = r0.m
            if (r12 >= r10) goto L_0x00a6
            double[] r10 = r0.singularValues
            double[] r3 = r0.singularValues
            r17 = r5
            r4 = r3[r11]
            r3 = r1[r12]
            r13 = r3[r11]
            double r3 = org.apache.commons.math3.util.FastMath.hypot(r4, r13)
            r10[r11] = r3
            int r12 = r12 + 1
            r5 = r17
            r3 = 0
            r4 = 1
            goto L_0x0089
        L_0x00a6:
            r17 = r5
            double[] r3 = r0.singularValues
            r4 = r3[r11]
            int r3 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r3 == 0) goto L_0x00de
            r3 = r1[r11]
            r4 = r3[r11]
            int r3 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r3 >= 0) goto L_0x00c1
            double[] r3 = r0.singularValues
            double[] r4 = r0.singularValues
            r12 = r4[r11]
            double r4 = -r12
            r3[r11] = r4
        L_0x00c1:
            r3 = r11
        L_0x00c2:
            int r4 = r0.m
            if (r3 >= r4) goto L_0x00d5
            r4 = r1[r3]
            r12 = r4[r11]
            double[] r5 = r0.singularValues
            r20 = r5[r11]
            double r12 = r12 / r20
            r4[r11] = r12
            int r3 = r3 + 1
            goto L_0x00c2
        L_0x00d5:
            r3 = r1[r11]
            r4 = r3[r11]
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r4 = r4 + r12
            r3[r11] = r4
        L_0x00de:
            double[] r3 = r0.singularValues
            double[] r4 = r0.singularValues
            r12 = r4[r11]
            double r4 = -r12
            r3[r11] = r4
            goto L_0x00ea
        L_0x00e8:
            r17 = r5
        L_0x00ea:
            int r3 = r11 + 1
            r4 = r3
        L_0x00ed:
            int r5 = r0.n
            if (r4 >= r5) goto L_0x0136
            if (r11 >= r8) goto L_0x012d
            double[] r5 = r0.singularValues
            r12 = r5[r11]
            int r5 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r5 == 0) goto L_0x012d
            r5 = r11
            r12 = r15
        L_0x00fd:
            int r10 = r0.m
            if (r5 >= r10) goto L_0x0110
            r10 = r1[r5]
            r20 = r10[r11]
            r10 = r1[r5]
            r22 = r10[r4]
            double r20 = r20 * r22
            double r12 = r12 + r20
            int r5 = r5 + 1
            goto L_0x00fd
        L_0x0110:
            double r12 = -r12
            r5 = r1[r11]
            r20 = r5[r11]
            double r12 = r12 / r20
            r5 = r11
        L_0x0118:
            int r10 = r0.m
            if (r5 >= r10) goto L_0x012d
            r10 = r1[r5]
            r20 = r10[r4]
            r14 = r1[r5]
            r22 = r14[r11]
            double r22 = r22 * r12
            double r20 = r20 + r22
            r10[r4] = r20
            int r5 = r5 + 1
            goto L_0x0118
        L_0x012d:
            r5 = r1[r11]
            r12 = r5[r4]
            r6[r4] = r12
            int r4 = r4 + 1
            goto L_0x00ed
        L_0x0136:
            if (r11 >= r8) goto L_0x0148
            r4 = r11
        L_0x0139:
            int r5 = r0.m
            if (r4 >= r5) goto L_0x0148
            r5 = r2[r4]
            r10 = r1[r4]
            r12 = r10[r11]
            r5[r11] = r12
            int r4 = r4 + 1
            goto L_0x0139
        L_0x0148:
            if (r11 >= r9) goto L_0x01ee
            r6[r11] = r15
            r4 = r3
        L_0x014d:
            int r5 = r0.n
            if (r4 >= r5) goto L_0x0162
            r12 = r6[r11]
            r24 = r9
            r9 = r6[r4]
            double r9 = org.apache.commons.math3.util.FastMath.hypot(r12, r9)
            r6[r11] = r9
            int r4 = r4 + 1
            r9 = r24
            goto L_0x014d
        L_0x0162:
            r24 = r9
            r4 = r6[r11]
            int r9 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r9 == 0) goto L_0x018b
            r4 = r6[r3]
            int r9 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r9 >= 0) goto L_0x0175
            r4 = r6[r11]
            double r4 = -r4
            r6[r11] = r4
        L_0x0175:
            r4 = r3
        L_0x0176:
            int r5 = r0.n
            if (r4 >= r5) goto L_0x0184
            r9 = r6[r4]
            r12 = r6[r11]
            double r9 = r9 / r12
            r6[r4] = r9
            int r4 = r4 + 1
            goto L_0x0176
        L_0x0184:
            r4 = r6[r3]
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r4 = r4 + r9
            r6[r3] = r4
        L_0x018b:
            r4 = r6[r11]
            double r4 = -r4
            r6[r11] = r4
            int r4 = r0.m
            if (r3 >= r4) goto L_0x01e0
            r4 = r6[r11]
            int r9 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r9 == 0) goto L_0x01e0
            r4 = r3
        L_0x019b:
            int r5 = r0.m
            if (r4 >= r5) goto L_0x01a4
            r7[r4] = r15
            int r4 = r4 + 1
            goto L_0x019b
        L_0x01a4:
            r4 = r3
        L_0x01a5:
            int r5 = r0.n
            if (r4 >= r5) goto L_0x01c0
            r5 = r3
        L_0x01aa:
            int r9 = r0.m
            if (r5 >= r9) goto L_0x01bd
            r9 = r7[r5]
            r12 = r6[r4]
            r14 = r1[r5]
            r15 = r14[r4]
            double r12 = r12 * r15
            double r9 = r9 + r12
            r7[r5] = r9
            int r5 = r5 + 1
            goto L_0x01aa
        L_0x01bd:
            int r4 = r4 + 1
            goto L_0x01a5
        L_0x01c0:
            r4 = r3
        L_0x01c1:
            int r5 = r0.n
            if (r4 >= r5) goto L_0x01e0
            r9 = r6[r4]
            double r9 = -r9
            r12 = r6[r3]
            double r9 = r9 / r12
            r5 = r3
        L_0x01cc:
            int r12 = r0.m
            if (r5 >= r12) goto L_0x01dd
            r12 = r1[r5]
            r13 = r12[r4]
            r15 = r7[r5]
            double r15 = r15 * r9
            double r13 = r13 + r15
            r12[r4] = r13
            int r5 = r5 + 1
            goto L_0x01cc
        L_0x01dd:
            int r4 = r4 + 1
            goto L_0x01c1
        L_0x01e0:
            r4 = r3
        L_0x01e1:
            int r5 = r0.n
            if (r4 >= r5) goto L_0x01f0
            r5 = r17[r4]
            r9 = r6[r4]
            r5[r11] = r9
            int r4 = r4 + 1
            goto L_0x01e1
        L_0x01ee:
            r24 = r9
        L_0x01f0:
            r11 = r3
            r5 = r17
            r9 = r24
            r3 = 0
            r4 = 1
            r10 = 2
            goto L_0x007a
        L_0x01fa:
            r17 = r5
            r24 = r9
            int r3 = r0.n
            int r4 = r0.n
            if (r8 >= r4) goto L_0x020c
            double[] r4 = r0.singularValues
            r5 = r1[r8]
            r9 = r5[r8]
            r4[r8] = r9
        L_0x020c:
            int r4 = r0.m
            if (r4 >= r3) goto L_0x0216
            double[] r4 = r0.singularValues
            int r5 = r3 + -1
            r4[r5] = r15
        L_0x0216:
            int r9 = r24 + 1
            if (r9 >= r3) goto L_0x0222
            r1 = r1[r24]
            int r4 = r3 + -1
            r4 = r1[r4]
            r6[r24] = r4
        L_0x0222:
            int r1 = r3 + -1
            r6[r1] = r15
            r4 = r8
        L_0x0227:
            int r5 = r0.n
            if (r4 >= r5) goto L_0x0240
            r5 = 0
        L_0x022c:
            int r7 = r0.m
            if (r5 >= r7) goto L_0x0237
            r7 = r2[r5]
            r7[r4] = r15
            int r5 = r5 + 1
            goto L_0x022c
        L_0x0237:
            r5 = r2[r4]
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r5[r4] = r9
            int r4 = r4 + 1
            goto L_0x0227
        L_0x0240:
            r4 = 1
            int r8 = r8 - r4
        L_0x0242:
            if (r8 < 0) goto L_0x02c2
            double[] r4 = r0.singularValues
            r9 = r4[r8]
            int r4 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x02ad
            int r4 = r8 + 1
        L_0x024e:
            int r5 = r0.n
            if (r4 >= r5) goto L_0x0284
            r5 = r8
            r9 = r15
        L_0x0254:
            int r7 = r0.m
            if (r5 >= r7) goto L_0x0265
            r7 = r2[r5]
            r11 = r7[r8]
            r7 = r2[r5]
            r13 = r7[r4]
            double r11 = r11 * r13
            double r9 = r9 + r11
            int r5 = r5 + 1
            goto L_0x0254
        L_0x0265:
            double r9 = -r9
            r5 = r2[r8]
            r11 = r5[r8]
            double r9 = r9 / r11
            r5 = r8
        L_0x026c:
            int r7 = r0.m
            if (r5 >= r7) goto L_0x0281
            r7 = r2[r5]
            r11 = r7[r4]
            r13 = r2[r5]
            r20 = r13[r8]
            double r20 = r20 * r9
            double r11 = r11 + r20
            r7[r4] = r11
            int r5 = r5 + 1
            goto L_0x026c
        L_0x0281:
            int r4 = r4 + 1
            goto L_0x024e
        L_0x0284:
            r4 = r8
        L_0x0285:
            int r5 = r0.m
            if (r4 >= r5) goto L_0x0295
            r5 = r2[r4]
            r7 = r2[r4]
            r9 = r7[r8]
            double r9 = -r9
            r5[r8] = r9
            int r4 = r4 + 1
            goto L_0x0285
        L_0x0295:
            r4 = r2[r8]
            r5 = r2[r8]
            r9 = r5[r8]
            r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r13 = r11 + r9
            r4[r8] = r13
            r4 = 0
        L_0x02a2:
            int r5 = r8 + -1
            if (r4 >= r5) goto L_0x02bf
            r5 = r2[r4]
            r5[r8] = r15
            int r4 = r4 + 1
            goto L_0x02a2
        L_0x02ad:
            r4 = 0
        L_0x02ae:
            int r5 = r0.m
            if (r4 >= r5) goto L_0x02b9
            r5 = r2[r4]
            r5[r8] = r15
            int r4 = r4 + 1
            goto L_0x02ae
        L_0x02b9:
            r4 = r2[r8]
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r4[r8] = r9
        L_0x02bf:
            int r8 = r8 + -1
            goto L_0x0242
        L_0x02c2:
            int r4 = r0.n
            r5 = 1
            int r4 = r4 - r5
        L_0x02c6:
            if (r4 < 0) goto L_0x0323
            r5 = r24
            if (r4 >= r5) goto L_0x030c
            r7 = r6[r4]
            int r9 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r9 == 0) goto L_0x030c
            int r7 = r4 + 1
            r8 = r7
        L_0x02d5:
            int r9 = r0.n
            if (r8 >= r9) goto L_0x030c
            r9 = r7
            r10 = r15
        L_0x02db:
            int r12 = r0.n
            if (r9 >= r12) goto L_0x02ed
            r12 = r17[r9]
            r13 = r12[r4]
            r12 = r17[r9]
            r20 = r12[r8]
            double r13 = r13 * r20
            double r10 = r10 + r13
            int r9 = r9 + 1
            goto L_0x02db
        L_0x02ed:
            double r9 = -r10
            r11 = r17[r7]
            r12 = r11[r4]
            double r9 = r9 / r12
            r11 = r7
        L_0x02f4:
            int r12 = r0.n
            if (r11 >= r12) goto L_0x0309
            r12 = r17[r11]
            r13 = r12[r8]
            r20 = r17[r11]
            r21 = r20[r4]
            double r21 = r21 * r9
            double r13 = r13 + r21
            r12[r8] = r13
            int r11 = r11 + 1
            goto L_0x02f4
        L_0x0309:
            int r8 = r8 + 1
            goto L_0x02d5
        L_0x030c:
            r7 = 0
        L_0x030d:
            int r8 = r0.n
            if (r7 >= r8) goto L_0x0318
            r8 = r17[r7]
            r8[r4] = r15
            int r7 = r7 + 1
            goto L_0x030d
        L_0x0318:
            r7 = r17[r4]
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r7[r4] = r8
            int r4 = r4 + -1
            r24 = r5
            goto L_0x02c6
        L_0x0323:
            r4 = 4372995238176751616(0x3cb0000000000000, double:2.220446049250313E-16)
            if (r3 <= 0) goto L_0x0678
            int r7 = r3 + -2
            r8 = r7
        L_0x032a:
            if (r8 < 0) goto L_0x0353
            double[] r11 = r0.singularValues
            r12 = r11[r8]
            double r11 = org.apache.commons.math3.util.FastMath.abs(r12)
            double[] r13 = r0.singularValues
            int r14 = r8 + 1
            r9 = r13[r14]
            double r9 = org.apache.commons.math3.util.FastMath.abs(r9)
            double r11 = r11 + r9
            double r11 = r11 * r4
            r9 = 256705178760118272(0x390000000000000, double:1.6033346880071782E-291)
            double r11 = r11 + r9
            r9 = r6[r8]
            double r9 = org.apache.commons.math3.util.FastMath.abs(r9)
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 > 0) goto L_0x0350
            r6[r8] = r15
            goto L_0x0353
        L_0x0350:
            int r8 = r8 + -1
            goto L_0x032a
        L_0x0353:
            if (r8 != r7) goto L_0x035c
            r4 = 4
            r26 = r1
            r25 = r2
        L_0x035a:
            r1 = 1
            goto L_0x03ad
        L_0x035c:
            int r9 = r3 + -1
            r10 = r9
        L_0x035f:
            if (r10 < r8) goto L_0x039d
            if (r10 != r8) goto L_0x0364
            goto L_0x039d
        L_0x0364:
            if (r10 == r3) goto L_0x036d
            r11 = r6[r10]
            double r11 = org.apache.commons.math3.util.FastMath.abs(r11)
            goto L_0x036e
        L_0x036d:
            r11 = r15
        L_0x036e:
            int r13 = r8 + 1
            if (r10 == r13) goto L_0x037b
            int r13 = r10 + -1
            r13 = r6[r13]
            double r13 = org.apache.commons.math3.util.FastMath.abs(r13)
            goto L_0x037c
        L_0x037b:
            r13 = r15
        L_0x037c:
            double r11 = r11 + r13
            double[] r13 = r0.singularValues
            r26 = r1
            r25 = r2
            r1 = r13[r10]
            double r1 = org.apache.commons.math3.util.FastMath.abs(r1)
            double r11 = r11 * r4
            r13 = 256705178760118272(0x390000000000000, double:1.6033346880071782E-291)
            double r11 = r11 + r13
            int r18 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r18 > 0) goto L_0x0396
            double[] r1 = r0.singularValues
            r1[r10] = r15
            goto L_0x03a1
        L_0x0396:
            int r10 = r10 + -1
            r2 = r25
            r1 = r26
            goto L_0x035f
        L_0x039d:
            r26 = r1
            r25 = r2
        L_0x03a1:
            if (r10 != r8) goto L_0x03a5
            r4 = 3
            goto L_0x035a
        L_0x03a5:
            if (r10 != r9) goto L_0x03aa
            r1 = 1
            r4 = 1
            goto L_0x03ad
        L_0x03aa:
            r8 = r10
            r1 = 1
            r4 = 2
        L_0x03ad:
            int r8 = r8 + r1
            switch(r4) {
                case 1: goto L_0x058d;
                case 2: goto L_0x0530;
                case 3: goto L_0x03cc;
                default: goto L_0x03b1;
            }
        L_0x03b1:
            double[] r1 = r0.singularValues
            r4 = r1[r8]
            r1 = 0
            int r7 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r7 > 0) goto L_0x060e
            double[] r4 = r0.singularValues
            double[] r5 = r0.singularValues
            r9 = r5[r8]
            int r5 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r5 >= 0) goto L_0x05fa
            double[] r5 = r0.singularValues
            r9 = r5[r8]
            double r9 = -r9
            goto L_0x05fb
        L_0x03cc:
            double[] r1 = r0.singularValues
            int r2 = r3 + -1
            r4 = r1[r2]
            double r4 = org.apache.commons.math3.util.FastMath.abs(r4)
            double[] r1 = r0.singularValues
            r9 = r1[r7]
            double r9 = org.apache.commons.math3.util.FastMath.abs(r9)
            double r4 = org.apache.commons.math3.util.FastMath.max(r4, r9)
            r9 = r6[r7]
            double r9 = org.apache.commons.math3.util.FastMath.abs(r9)
            double r4 = org.apache.commons.math3.util.FastMath.max(r4, r9)
            double[] r1 = r0.singularValues
            r9 = r1[r8]
            double r9 = org.apache.commons.math3.util.FastMath.abs(r9)
            double r4 = org.apache.commons.math3.util.FastMath.max(r4, r9)
            r9 = r6[r8]
            double r9 = org.apache.commons.math3.util.FastMath.abs(r9)
            double r4 = org.apache.commons.math3.util.FastMath.max(r4, r9)
            double[] r1 = r0.singularValues
            r9 = r1[r2]
            double r9 = r9 / r4
            double[] r1 = r0.singularValues
            r11 = r1[r7]
            double r11 = r11 / r4
            r13 = r6[r7]
            double r13 = r13 / r4
            double[] r1 = r0.singularValues
            r18 = r1[r8]
            double r18 = r18 / r4
            r20 = r6[r8]
            double r20 = r20 / r4
            double r4 = r11 + r9
            double r11 = r11 - r9
            double r4 = r4 * r11
            double r11 = r13 * r13
            double r4 = r4 + r11
            r11 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r4 = r4 / r11
            double r13 = r13 * r9
            double r13 = r13 * r13
            int r1 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r1 != 0) goto L_0x0430
            int r1 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r1 == 0) goto L_0x042e
            goto L_0x0430
        L_0x042e:
            r4 = r15
            goto L_0x043f
        L_0x0430:
            double r11 = r4 * r4
            double r11 = r11 + r13
            double r11 = org.apache.commons.math3.util.FastMath.sqrt(r11)
            int r1 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r1 >= 0) goto L_0x043c
            double r11 = -r11
        L_0x043c:
            double r4 = r4 + r11
            double r4 = r13 / r4
        L_0x043f:
            double r11 = r18 + r9
            double r9 = r18 - r9
            double r11 = r11 * r9
            double r11 = r11 + r4
            double r18 = r18 * r20
            r1 = r8
            r4 = r18
        L_0x044a:
            if (r1 >= r2) goto L_0x0528
            double r9 = org.apache.commons.math3.util.FastMath.hypot(r11, r4)
            double r11 = r11 / r9
            double r4 = r4 / r9
            if (r1 == r8) goto L_0x0458
            int r13 = r1 + -1
            r6[r13] = r9
        L_0x0458:
            double[] r9 = r0.singularValues
            r13 = r9[r1]
            double r13 = r13 * r11
            r9 = r6[r1]
            double r9 = r9 * r4
            double r13 = r13 + r9
            r9 = r6[r1]
            double r9 = r9 * r11
            double[] r15 = r0.singularValues
            r18 = r15[r1]
            double r18 = r18 * r4
            double r9 = r9 - r18
            r6[r1] = r9
            double[] r9 = r0.singularValues
            int r10 = r1 + 1
            r15 = r9[r10]
            r28 = r2
            r27 = r3
            double r2 = r4 * r15
            double[] r9 = r0.singularValues
            double[] r15 = r0.singularValues
            r18 = r15[r10]
            double r18 = r18 * r11
            r9[r10] = r18
            r9 = 0
        L_0x0485:
            int r15 = r0.n
            if (r9 >= r15) goto L_0x04b9
            r15 = r17[r9]
            r18 = r15[r1]
            double r18 = r18 * r11
            r15 = r17[r9]
            r20 = r15[r10]
            double r20 = r20 * r4
            double r18 = r18 + r20
            r15 = r17[r9]
            r29 = r7
            r30 = r8
            double r7 = -r4
            r16 = r17[r9]
            r20 = r16[r1]
            double r7 = r7 * r20
            r16 = r17[r9]
            r20 = r16[r10]
            double r20 = r20 * r11
            double r7 = r7 + r20
            r15[r10] = r7
            r7 = r17[r9]
            r7[r1] = r18
            int r9 = r9 + 1
            r7 = r29
            r8 = r30
            goto L_0x0485
        L_0x04b9:
            r29 = r7
            r30 = r8
            double r4 = org.apache.commons.math3.util.FastMath.hypot(r13, r2)
            double r13 = r13 / r4
            double r2 = r2 / r4
            double[] r7 = r0.singularValues
            r7[r1] = r4
            r4 = r6[r1]
            double r4 = r4 * r13
            double[] r7 = r0.singularValues
            r8 = r7[r10]
            double r8 = r8 * r2
            double r11 = r4 + r8
            double[] r4 = r0.singularValues
            double r7 = -r2
            r15 = r6[r1]
            double r15 = r15 * r7
            double[] r5 = r0.singularValues
            r18 = r5[r10]
            double r18 = r18 * r13
            double r15 = r15 + r18
            r4[r10] = r15
            r4 = r6[r10]
            double r4 = r4 * r2
            r15 = r6[r10]
            double r15 = r15 * r13
            r6[r10] = r15
            int r9 = r0.m
            r15 = 1
            int r9 = r9 - r15
            if (r1 >= r9) goto L_0x051b
            r9 = 0
        L_0x04f0:
            int r15 = r0.m
            if (r9 >= r15) goto L_0x051b
            r15 = r25[r9]
            r18 = r15[r1]
            double r18 = r18 * r13
            r15 = r25[r9]
            r20 = r15[r10]
            double r20 = r20 * r2
            double r18 = r18 + r20
            r15 = r25[r9]
            r16 = r25[r9]
            r20 = r16[r1]
            double r20 = r20 * r7
            r16 = r25[r9]
            r22 = r16[r10]
            double r22 = r22 * r13
            double r20 = r20 + r22
            r15[r10] = r20
            r15 = r25[r9]
            r15[r1] = r18
            int r9 = r9 + 1
            goto L_0x04f0
        L_0x051b:
            r1 = r10
            r3 = r27
            r2 = r28
            r7 = r29
            r8 = r30
            r15 = 0
            goto L_0x044a
        L_0x0528:
            r27 = r3
            r29 = r7
            r6[r29] = r11
            goto L_0x05f3
        L_0x0530:
            r27 = r3
            r30 = r8
            int r8 = r30 + -1
            r1 = r6[r8]
            r3 = 0
            r6[r8] = r3
            r4 = r1
            r3 = r27
            r1 = r30
        L_0x0541:
            if (r1 >= r3) goto L_0x05f3
            double[] r2 = r0.singularValues
            r9 = r2[r1]
            double r9 = org.apache.commons.math3.util.FastMath.hypot(r9, r4)
            double[] r2 = r0.singularValues
            r11 = r2[r1]
            double r11 = r11 / r9
            double r4 = r4 / r9
            double[] r2 = r0.singularValues
            r2[r1] = r9
            double r9 = -r4
            r13 = r6[r1]
            double r13 = r13 * r9
            r15 = r6[r1]
            double r15 = r15 * r11
            r6[r1] = r15
            r2 = 0
        L_0x055f:
            int r7 = r0.m
            if (r2 >= r7) goto L_0x0589
            r7 = r25[r2]
            r15 = r7[r1]
            double r15 = r15 * r11
            r7 = r25[r2]
            r18 = r7[r8]
            double r18 = r18 * r4
            double r15 = r15 + r18
            r7 = r25[r2]
            r18 = r25[r2]
            r19 = r18[r1]
            double r19 = r19 * r9
            r18 = r25[r2]
            r21 = r18[r8]
            double r21 = r21 * r11
            double r19 = r19 + r21
            r7[r8] = r19
            r7 = r25[r2]
            r7[r1] = r15
            int r2 = r2 + 1
            goto L_0x055f
        L_0x0589:
            int r1 = r1 + 1
            r4 = r13
            goto L_0x0541
        L_0x058d:
            r29 = r7
            r30 = r8
            r1 = r6[r29]
            r4 = 0
            r6[r29] = r4
            r4 = r1
            r1 = r29
        L_0x059a:
            if (r1 < r8) goto L_0x05f3
            double[] r2 = r0.singularValues
            r9 = r2[r1]
            double r9 = org.apache.commons.math3.util.FastMath.hypot(r9, r4)
            double[] r2 = r0.singularValues
            r11 = r2[r1]
            double r11 = r11 / r9
            double r13 = r4 / r9
            double[] r2 = r0.singularValues
            r2[r1] = r9
            if (r1 == r8) goto L_0x05bc
            double r4 = -r13
            int r2 = r1 + -1
            r9 = r6[r2]
            double r4 = r4 * r9
            r9 = r6[r2]
            double r9 = r9 * r11
            r6[r2] = r9
        L_0x05bc:
            r2 = 0
        L_0x05bd:
            int r7 = r0.n
            if (r2 >= r7) goto L_0x05ee
            r7 = r17[r2]
            r9 = r7[r1]
            double r9 = r9 * r11
            r7 = r17[r2]
            int r15 = r3 + -1
            r18 = r7[r15]
            double r18 = r18 * r13
            double r9 = r9 + r18
            r7 = r17[r2]
            r31 = r4
            double r4 = -r13
            r16 = r17[r2]
            r18 = r16[r1]
            double r4 = r4 * r18
            r16 = r17[r2]
            r18 = r16[r15]
            double r18 = r18 * r11
            double r4 = r4 + r18
            r7[r15] = r4
            r4 = r17[r2]
            r4[r1] = r9
            int r2 = r2 + 1
            r4 = r31
            goto L_0x05bd
        L_0x05ee:
            r31 = r4
            int r1 = r1 + -1
            goto L_0x059a
        L_0x05f3:
            r7 = r26
            r1 = 0
            r9 = 1
            goto L_0x0672
        L_0x05fa:
            r9 = r1
        L_0x05fb:
            r4[r8] = r9
            r7 = r26
            r4 = 0
        L_0x0600:
            if (r4 > r7) goto L_0x0610
            r5 = r17[r4]
            r9 = r17[r4]
            r10 = r9[r8]
            double r9 = -r10
            r5[r8] = r9
            int r4 = r4 + 1
            goto L_0x0600
        L_0x060e:
            r7 = r26
        L_0x0610:
            if (r8 >= r7) goto L_0x066f
            double[] r4 = r0.singularValues
            r9 = r4[r8]
            double[] r4 = r0.singularValues
            int r5 = r8 + 1
            r11 = r4[r5]
            int r4 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r4 < 0) goto L_0x0621
            goto L_0x066f
        L_0x0621:
            double[] r4 = r0.singularValues
            r9 = r4[r8]
            double[] r4 = r0.singularValues
            double[] r11 = r0.singularValues
            r12 = r11[r5]
            r4[r8] = r12
            double[] r4 = r0.singularValues
            r4[r5] = r9
            int r4 = r0.n
            r9 = 1
            int r4 = r4 - r9
            if (r8 >= r4) goto L_0x064f
            r4 = 0
        L_0x0638:
            int r9 = r0.n
            if (r4 >= r9) goto L_0x064f
            r9 = r17[r4]
            r10 = r9[r5]
            r9 = r17[r4]
            r12 = r17[r4]
            r13 = r12[r8]
            r9[r5] = r13
            r9 = r17[r4]
            r9[r8] = r10
            int r4 = r4 + 1
            goto L_0x0638
        L_0x064f:
            int r4 = r0.m
            r9 = 1
            int r4 = r4 - r9
            if (r8 >= r4) goto L_0x066d
            r4 = 0
        L_0x0656:
            int r10 = r0.m
            if (r4 >= r10) goto L_0x066d
            r10 = r25[r4]
            r11 = r10[r5]
            r10 = r25[r4]
            r13 = r25[r4]
            r14 = r13[r8]
            r10[r5] = r14
            r10 = r25[r4]
            r10[r8] = r11
            int r4 = r4 + 1
            goto L_0x0656
        L_0x066d:
            r8 = r5
            goto L_0x0610
        L_0x066f:
            r9 = 1
            int r3 = r3 + -1
        L_0x0672:
            r15 = r1
            r1 = r7
            r2 = r25
            goto L_0x0323
        L_0x0678:
            r25 = r2
            int r1 = r0.m
            double r1 = (double) r1
            double[] r3 = r0.singularValues
            r6 = 0
            r6 = r3[r6]
            double r1 = r1 * r6
            double r1 = r1 * r4
            double r3 = org.apache.commons.math3.util.Precision.SAFE_MIN
            double r3 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            double r1 = org.apache.commons.math3.util.FastMath.max(r1, r3)
            r0.tol = r1
            boolean r1 = r0.transposed
            if (r1 != 0) goto L_0x06a5
            r2 = r25
            org.apache.commons.math3.linear.RealMatrix r1 = org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(r2)
            r0.cachedU = r1
            r5 = r17
            org.apache.commons.math3.linear.RealMatrix r1 = org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(r5)
            r0.cachedV = r1
            goto L_0x06b5
        L_0x06a5:
            r5 = r17
            r2 = r25
            org.apache.commons.math3.linear.RealMatrix r1 = org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(r5)
            r0.cachedU = r1
            org.apache.commons.math3.linear.RealMatrix r1 = org.apache.commons.math3.linear.MatrixUtils.createRealMatrix(r2)
            r0.cachedV = r1
        L_0x06b5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.SingularValueDecomposition.<init>(org.apache.commons.math3.linear.RealMatrix):void");
    }

    public RealMatrix getU() {
        return this.cachedU;
    }

    public RealMatrix getUT() {
        if (this.cachedUt == null) {
            this.cachedUt = getU().transpose();
        }
        return this.cachedUt;
    }

    public RealMatrix getS() {
        if (this.cachedS == null) {
            this.cachedS = MatrixUtils.createRealDiagonalMatrix(this.singularValues);
        }
        return this.cachedS;
    }

    public double[] getSingularValues() {
        return (double[]) this.singularValues.clone();
    }

    public RealMatrix getV() {
        return this.cachedV;
    }

    public RealMatrix getVT() {
        if (this.cachedVt == null) {
            this.cachedVt = getV().transpose();
        }
        return this.cachedVt;
    }

    public RealMatrix getCovariance(double d) {
        int length = this.singularValues.length;
        int i = 0;
        while (i < length && this.singularValues[i] >= d) {
            i++;
        }
        if (i == 0) {
            throw new NumberIsTooLargeException(LocalizedFormats.TOO_LARGE_CUTOFF_SINGULAR_VALUE, Double.valueOf(d), Double.valueOf(this.singularValues[0]), true);
        }
        final double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{i, length});
        getVT().walkInOptimizedOrder((RealMatrixPreservingVisitor) new DefaultRealMatrixPreservingVisitor() {
            public void visit(int i, int i2, double d) {
                dArr[i][i2] = d / SingularValueDecomposition.this.singularValues[i];
            }
        }, 0, i - 1, 0, length - 1);
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(dArr, false);
        return array2DRowRealMatrix.transpose().multiply(array2DRowRealMatrix);
    }

    public double getNorm() {
        return this.singularValues[0];
    }

    public double getConditionNumber() {
        return this.singularValues[0] / this.singularValues[this.n - 1];
    }

    public double getInverseConditionNumber() {
        return this.singularValues[this.n - 1] / this.singularValues[0];
    }

    public int getRank() {
        int i = 0;
        for (int i2 = 0; i2 < this.singularValues.length; i2++) {
            if (this.singularValues[i2] > this.tol) {
                i++;
            }
        }
        return i;
    }

    public DecompositionSolver getSolver() {
        Solver solver = new Solver(this.singularValues, getUT(), getV(), getRank() == this.m, this.tol);
        return solver;
    }
}
