package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

public class BracketingNthOrderBrentSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private static final int DEFAULT_MAXIMAL_ORDER = 5;
    private static final int MAXIMAL_AGING = 2;
    private static final double REDUCTION_FACTOR = 0.0625d;
    private AllowedSolution allowed;
    private final int maximalOrder;

    public BracketingNthOrderBrentSolver() {
        this(1.0E-6d, 5);
    }

    public BracketingNthOrderBrentSolver(double d, int i) throws NumberIsTooSmallException {
        super(d);
        if (i < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), Integer.valueOf(2), true);
        }
        this.maximalOrder = i;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    public BracketingNthOrderBrentSolver(double d, double d2, int i) throws NumberIsTooSmallException {
        super(d, d2);
        if (i < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), Integer.valueOf(2), true);
        }
        this.maximalOrder = i;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    public BracketingNthOrderBrentSolver(double d, double d2, double d3, int i) throws NumberIsTooSmallException {
        super(d, d2, d3);
        if (i < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), Integer.valueOf(2), true);
        }
        this.maximalOrder = i;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    public int getMaximalOrder() {
        return this.maximalOrder;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01de  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double doSolve() throws org.apache.commons.math3.exception.TooManyEvaluationsException, org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r39 = this;
            r7 = r39
            int r0 = r7.maximalOrder
            r8 = 1
            int r0 = r0 + r8
            double[] r9 = new double[r0]
            int r0 = r7.maximalOrder
            int r0 = r0 + r8
            double[] r10 = new double[r0]
            double r0 = r39.getMin()
            r11 = 0
            r9[r11] = r0
            double r0 = r39.getStartValue()
            r9[r8] = r0
            double r0 = r39.getMax()
            r12 = 2
            r9[r12] = r0
            r1 = r9[r11]
            r3 = r9[r8]
            r5 = r9[r12]
            r0 = r7
            r0.verifySequence(r1, r3, r5)
            r0 = r9[r8]
            double r0 = r7.computeObjectiveValue(r0)
            r10[r8] = r0
            r0 = r10[r8]
            r13 = 0
            boolean r0 = org.apache.commons.math3.util.Precision.equals(r0, r13, r8)
            if (r0 == 0) goto L_0x0040
            r0 = r9[r8]
            return r0
        L_0x0040:
            r0 = r9[r11]
            double r0 = r7.computeObjectiveValue(r0)
            r10[r11] = r0
            r0 = r10[r11]
            boolean r0 = org.apache.commons.math3.util.Precision.equals(r0, r13, r8)
            if (r0 == 0) goto L_0x0053
            r0 = r9[r11]
            return r0
        L_0x0053:
            r0 = r10[r11]
            r2 = r10[r8]
            double r0 = r0 * r2
            int r2 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r2 >= 0) goto L_0x005f
            r0 = r8
            r1 = r12
            goto L_0x007e
        L_0x005f:
            r0 = r9[r12]
            double r0 = r7.computeObjectiveValue(r0)
            r10[r12] = r0
            r0 = r10[r12]
            boolean r0 = org.apache.commons.math3.util.Precision.equals(r0, r13, r8)
            if (r0 == 0) goto L_0x0072
            r0 = r9[r12]
            return r0
        L_0x0072:
            r0 = r10[r8]
            r2 = r10[r12]
            double r0 = r0 * r2
            int r2 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r2 >= 0) goto L_0x022c
            r0 = 3
            r1 = r0
            r0 = r12
        L_0x007e:
            int r2 = r9.length
            double[] r15 = new double[r2]
            int r2 = r0 + -1
            r3 = r9[r2]
            r5 = r10[r2]
            double r16 = org.apache.commons.math3.util.FastMath.abs(r5)
            r18 = r9[r0]
            r13 = r10[r0]
            double r22 = org.apache.commons.math3.util.FastMath.abs(r13)
            r25 = r0
            r24 = r1
            r0 = r11
            r1 = r22
            r37 = r5
            r5 = r18
            r18 = r13
            r13 = r3
            r3 = r16
            r16 = r37
        L_0x00a5:
            double r22 = r39.getAbsoluteAccuracy()
            double r26 = r39.getRelativeAccuracy()
            r28 = r9
            double r8 = org.apache.commons.math3.util.FastMath.abs(r13)
            r29 = r13
            double r12 = org.apache.commons.math3.util.FastMath.abs(r5)
            double r8 = org.apache.commons.math3.util.FastMath.max(r8, r12)
            double r26 = r26 * r8
            double r22 = r22 + r26
            double r8 = r5 - r29
            int r12 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r12 <= 0) goto L_0x01f6
            double r12 = org.apache.commons.math3.util.FastMath.max(r3, r1)
            double r22 = r39.getFunctionValueAccuracy()
            int r14 = (r12 > r22 ? 1 : (r12 == r22 ? 0 : -1))
            if (r14 >= 0) goto L_0x00d5
            goto L_0x01f6
        L_0x00d5:
            r14 = 2
            if (r0 < r14) goto L_0x00f6
            int r14 = r0 + -2
            r22 = 1
            int r23 = r22 << r14
            int r12 = r23 + -1
            double r12 = (double) r12
            int r14 = r14 + 1
            r35 = r0
            r33 = r1
            double r0 = (double) r14
            double r22 = r12 * r16
            r26 = 4589168020290535424(0x3fb0000000000000, double:0.0625)
            double r26 = r26 * r0
            double r26 = r26 * r18
            double r22 = r22 - r26
            double r12 = r12 + r0
            double r22 = r22 / r12
            goto L_0x0117
        L_0x00f6:
            r35 = r0
            r33 = r1
            r0 = r14
            if (r11 < r0) goto L_0x0115
            int r0 = r11 + -2
            int r1 = r0 + 1
            double r1 = (double) r1
            r12 = 1
            int r0 = r12 << r0
            int r0 = r0 - r12
            double r12 = (double) r0
            double r22 = r12 * r18
            r26 = 4589168020290535424(0x3fb0000000000000, double:0.0625)
            double r26 = r26 * r1
            double r26 = r26 * r16
            double r22 = r22 - r26
            double r1 = r1 + r12
            double r22 = r22 / r1
            goto L_0x0117
        L_0x0115:
            r22 = 0
        L_0x0117:
            r13 = r24
            r12 = 0
        L_0x011a:
            int r0 = r13 - r12
            r14 = r28
            java.lang.System.arraycopy(r14, r12, r15, r12, r0)
            r26 = r35
            r0 = r7
            r31 = r33
            r1 = r22
            r33 = r3
            r3 = r15
            r4 = r10
            r35 = r5
            r5 = r12
            r6 = r13
            double r0 = r0.guessX(r1, r3, r4, r5, r6)
            int r2 = (r0 > r29 ? 1 : (r0 == r29 ? 0 : -1))
            if (r2 <= 0) goto L_0x0141
            int r2 = (r0 > r35 ? 1 : (r0 == r35 ? 0 : -1))
            if (r2 < 0) goto L_0x013d
            goto L_0x0141
        L_0x013d:
            r1 = r0
            r0 = r25
            goto L_0x0150
        L_0x0141:
            r0 = r25
            int r1 = r0 - r12
            int r2 = r13 - r0
            if (r1 < r2) goto L_0x014c
            int r12 = r12 + 1
            goto L_0x014e
        L_0x014c:
            int r13 = r13 + -1
        L_0x014e:
            r1 = 9221120237041090560(0x7ff8000000000000, double:NaN)
        L_0x0150:
            boolean r3 = java.lang.Double.isNaN(r1)
            if (r3 == 0) goto L_0x0169
            int r3 = r13 - r12
            r4 = 1
            if (r3 > r4) goto L_0x015c
            goto L_0x0169
        L_0x015c:
            r25 = r0
            r28 = r14
            r3 = r33
            r5 = r35
            r35 = r26
            r33 = r31
            goto L_0x011a
        L_0x0169:
            boolean r3 = java.lang.Double.isNaN(r1)
            if (r3 == 0) goto L_0x0177
            r1 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r1 = r1 * r8
            double r1 = r29 + r1
            int r12 = r0 + -1
            r13 = r0
        L_0x0177:
            double r3 = r7.computeObjectiveValue(r1)
            r5 = 1
            r8 = 0
            boolean r6 = org.apache.commons.math3.util.Precision.equals(r3, r8, r5)
            if (r6 == 0) goto L_0x0185
            return r1
        L_0x0185:
            r5 = r24
            r6 = 2
            if (r5 <= r6) goto L_0x019b
            int r6 = r13 - r12
            if (r6 == r5) goto L_0x019b
            r8 = 0
            java.lang.System.arraycopy(r14, r12, r14, r8, r6)
            java.lang.System.arraycopy(r10, r12, r10, r8, r6)
            int r25 = r0 - r12
            r5 = r6
        L_0x0198:
            r0 = r25
            goto L_0x01b1
        L_0x019b:
            int r6 = r14.length
            if (r5 != r6) goto L_0x01b1
            int r5 = r5 + -1
            int r6 = r14.length
            r8 = 1
            int r6 = r6 + r8
            r9 = 2
            int r6 = r6 / r9
            if (r0 < r6) goto L_0x01b1
            r6 = 0
            java.lang.System.arraycopy(r14, r8, r14, r6, r5)
            java.lang.System.arraycopy(r10, r8, r10, r6, r5)
            int r25 = r0 + -1
            goto L_0x0198
        L_0x01b1:
            int r6 = r0 + 1
            int r8 = r5 - r0
            java.lang.System.arraycopy(r14, r0, r14, r6, r8)
            r14[r0] = r1
            java.lang.System.arraycopy(r10, r0, r10, r6, r8)
            r10[r0] = r3
            r8 = 1
            int r24 = r5 + 1
            double r12 = r3 * r16
            r20 = 0
            int r5 = (r12 > r20 ? 1 : (r12 == r20 ? 0 : -1))
            if (r5 > 0) goto L_0x01de
            double r5 = org.apache.commons.math3.util.FastMath.abs(r3)
            int r9 = r26 + 1
            r25 = r0
            r18 = r3
            r0 = r9
            r3 = r33
            r11 = 0
            r37 = r1
            r1 = r5
            r5 = r37
            goto L_0x01f0
        L_0x01de:
            double r12 = org.apache.commons.math3.util.FastMath.abs(r3)
            int r11 = r11 + 1
            r29 = r1
            r16 = r3
            r25 = r6
            r3 = r12
            r1 = r31
            r5 = r35
            r0 = 0
        L_0x01f0:
            r9 = r14
            r13 = r29
            r12 = 2
            goto L_0x00a5
        L_0x01f6:
            r31 = r1
            r33 = r3
            r35 = r5
            int[] r0 = org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution
            org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = r7.allowed
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x0224;
                case 2: goto L_0x0223;
                case 3: goto L_0x0222;
                case 4: goto L_0x0218;
                case 5: goto L_0x020f;
                default: goto L_0x0209;
            }
        L_0x0209:
            org.apache.commons.math3.exception.MathInternalError r0 = new org.apache.commons.math3.exception.MathInternalError
            r0.<init>()
            throw r0
        L_0x020f:
            r0 = 0
            int r2 = (r16 > r0 ? 1 : (r16 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0217
            r29 = r35
        L_0x0217:
            return r29
        L_0x0218:
            r0 = 0
            int r2 = (r16 > r0 ? 1 : (r16 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x021f
            goto L_0x0221
        L_0x021f:
            r29 = r35
        L_0x0221:
            return r29
        L_0x0222:
            return r35
        L_0x0223:
            return r29
        L_0x0224:
            int r0 = (r33 > r31 ? 1 : (r33 == r31 ? 0 : -1))
            if (r0 >= 0) goto L_0x0229
            goto L_0x022b
        L_0x0229:
            r29 = r35
        L_0x022b:
            return r29
        L_0x022c:
            r14 = r9
            org.apache.commons.math3.exception.NoBracketingException r0 = new org.apache.commons.math3.exception.NoBracketingException
            r1 = 0
            r2 = r14[r1]
            r4 = 2
            r11 = r14[r4]
            r13 = r10[r1]
            r15 = r10[r4]
            r8 = r0
            r9 = r2
            r8.<init>(r9, r11, r13, r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve():double");
    }

    private double guessX(double d, double[] dArr, double[] dArr2, int i, int i2) {
        int i3;
        int i4 = i;
        int i5 = i4;
        while (true) {
            i3 = i2 - 1;
            if (i5 >= i3) {
                break;
            }
            int i6 = i5 + 1;
            int i7 = i6 - i4;
            while (i3 > i5) {
                dArr[i3] = (dArr[i3] - dArr[i3 - 1]) / (dArr2[i3] - dArr2[i3 - i7]);
                i3--;
            }
            i5 = i6;
        }
        double d2 = 0.0d;
        while (i3 >= i4) {
            d2 = (d2 * (d - dArr2[i3])) + dArr[i3];
            i3--;
        }
        return d2;
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(i, univariateFunction, d, d2);
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, double d3, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(i, univariateFunction, d, d2, d3);
    }
}
