package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class BrentSolver extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public BrentSolver() {
        this(1.0E-6d);
    }

    public BrentSolver(double d) {
        super(d);
    }

    public BrentSolver(double d, double d2) {
        super(d, d2);
    }

    public BrentSolver(double d, double d2, double d3) {
        super(d, d2, d3);
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws NoBracketingException, TooManyEvaluationsException, NumberIsTooLargeException {
        double min = getMin();
        double max = getMax();
        double startValue = getStartValue();
        double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, startValue, max);
        double computeObjectiveValue = computeObjectiveValue(startValue);
        if (FastMath.abs(computeObjectiveValue) <= functionValueAccuracy) {
            return startValue;
        }
        double computeObjectiveValue2 = computeObjectiveValue(min);
        if (FastMath.abs(computeObjectiveValue2) <= functionValueAccuracy) {
            return min;
        }
        if (computeObjectiveValue * computeObjectiveValue2 < 0.0d) {
            return brent(min, startValue, computeObjectiveValue2, computeObjectiveValue);
        }
        double d = computeObjectiveValue2;
        double d2 = computeObjectiveValue;
        double computeObjectiveValue3 = computeObjectiveValue(max);
        if (FastMath.abs(computeObjectiveValue3) <= functionValueAccuracy) {
            return max;
        }
        if (d2 * computeObjectiveValue3 < 0.0d) {
            return brent(startValue, max, d2, computeObjectiveValue3);
        }
        NoBracketingException noBracketingException = new NoBracketingException(min, max, d, computeObjectiveValue3);
        throw noBracketingException;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private double brent(double r46, double r48, double r50, double r52) {
        /*
            r45 = this;
            double r4 = r48 - r46
            double r6 = r45.getAbsoluteAccuracy()
            double r8 = r45.getRelativeAccuracy()
            r10 = r46
            r16 = r10
            r0 = r50
            r14 = r0
            r2 = r52
            r12 = r4
            r18 = r12
            r4 = r48
        L_0x0018:
            double r20 = org.apache.commons.math3.util.FastMath.abs(r0)
            double r22 = org.apache.commons.math3.util.FastMath.abs(r2)
            int r24 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
            if (r24 >= 0) goto L_0x0029
            r14 = r0
            r0 = r2
            r16 = r4
            goto L_0x0033
        L_0x0029:
            r41 = r2
            r2 = r14
            r14 = r41
            r43 = r4
            r4 = r10
            r10 = r43
        L_0x0033:
            r20 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r22 = r20 * r8
            double r24 = org.apache.commons.math3.util.FastMath.abs(r10)
            double r22 = r22 * r24
            double r22 = r22 + r6
            double r24 = r4 - r10
            r26 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r28 = r6
            double r6 = r26 * r24
            double r24 = org.apache.commons.math3.util.FastMath.abs(r6)
            int r30 = (r24 > r22 ? 1 : (r24 == r22 ? 0 : -1))
            if (r30 <= 0) goto L_0x0125
            r31 = r8
            r8 = 0
            boolean r24 = org.apache.commons.math3.util.Precision.equals(r14, r8)
            if (r24 == 0) goto L_0x005b
            goto L_0x0125
        L_0x005b:
            double r24 = org.apache.commons.math3.util.FastMath.abs(r12)
            int r30 = (r24 > r22 ? 1 : (r24 == r22 ? 0 : -1))
            if (r30 < 0) goto L_0x00cb
            double r24 = org.apache.commons.math3.util.FastMath.abs(r2)
            double r33 = org.apache.commons.math3.util.FastMath.abs(r14)
            int r30 = (r24 > r33 ? 1 : (r24 == r33 ? 0 : -1))
            if (r30 > 0) goto L_0x0070
            goto L_0x00cb
        L_0x0070:
            double r24 = r14 / r2
            int r30 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            r33 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r30 != 0) goto L_0x0085
            double r20 = r20 * r6
            double r20 = r20 * r24
            double r33 = r33 - r24
        L_0x007e:
            r39 = r4
            r2 = r20
            r4 = r33
            goto L_0x00a3
        L_0x0085:
            double r2 = r2 / r0
            double r35 = r14 / r0
            double r20 = r20 * r6
            double r20 = r20 * r2
            double r37 = r2 - r35
            double r20 = r20 * r37
            double r16 = r10 - r16
            double r35 = r35 - r33
            double r16 = r16 * r35
            double r20 = r20 - r16
            double r20 = r20 * r24
            double r2 = r2 - r33
            double r2 = r2 * r35
            double r24 = r24 - r33
            double r33 = r2 * r24
            goto L_0x007e
        L_0x00a3:
            int r16 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r16 <= 0) goto L_0x00a9
            double r4 = -r4
            goto L_0x00aa
        L_0x00a9:
            double r2 = -r2
        L_0x00aa:
            r16 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            double r16 = r16 * r6
            double r16 = r16 * r4
            double r8 = r22 * r4
            double r8 = org.apache.commons.math3.util.FastMath.abs(r8)
            double r16 = r16 - r8
            int r8 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
            if (r8 >= 0) goto L_0x00cd
            double r26 = r26 * r12
            double r8 = r26 * r4
            double r8 = org.apache.commons.math3.util.FastMath.abs(r8)
            int r12 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r12 < 0) goto L_0x00c9
            goto L_0x00cd
        L_0x00c9:
            double r2 = r2 / r4
            goto L_0x00d0
        L_0x00cb:
            r39 = r4
        L_0x00cd:
            r2 = r6
            r18 = r2
        L_0x00d0:
            double r4 = org.apache.commons.math3.util.FastMath.abs(r2)
            int r8 = (r4 > r22 ? 1 : (r4 == r22 ? 0 : -1))
            if (r8 <= 0) goto L_0x00e0
            double r4 = r10 + r2
            r6 = r45
            r7 = r4
            r4 = 0
            goto L_0x00f2
        L_0x00e0:
            r4 = 0
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 <= 0) goto L_0x00ed
            double r22 = r10 + r22
            r6 = r45
            r7 = r22
            goto L_0x00f2
        L_0x00ed:
            double r6 = r10 - r22
            r7 = r6
            r6 = r45
        L_0x00f2:
            double r12 = r6.computeObjectiveValue(r7)
            int r9 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r9 <= 0) goto L_0x00fe
            int r9 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r9 > 0) goto L_0x0106
        L_0x00fe:
            int r9 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r9 > 0) goto L_0x010f
            int r9 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r9 > 0) goto L_0x010f
        L_0x0106:
            double r0 = r7 - r10
            r2 = r0
            r18 = r2
            r39 = r10
            r0 = r14
            goto L_0x0115
        L_0x010f:
            r41 = r2
            r2 = r18
            r18 = r41
        L_0x0115:
            r4 = r7
            r16 = r10
            r6 = r28
            r8 = r31
            r10 = r39
            r41 = r2
            r2 = r12
            r12 = r41
            goto L_0x0018
        L_0x0125:
            r6 = r45
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.BrentSolver.brent(double, double, double, double):double");
    }
}
