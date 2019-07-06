package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class UnivariateSolverUtils {
    public static boolean isSequence(double d, double d2, double d3) {
        return d < d2 && d2 < d3;
    }

    public static double midpoint(double d, double d2) {
        return (d + d2) * 0.5d;
    }

    private UnivariateSolverUtils() {
    }

    public static double solve(UnivariateFunction univariateFunction, double d, double d2) throws NullArgumentException, NoBracketingException {
        if (univariateFunction != null) {
            return new BrentSolver().solve(Integer.MAX_VALUE, univariateFunction, d, d2);
        }
        throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
    }

    public static double solve(UnivariateFunction univariateFunction, double d, double d2, double d3) throws NullArgumentException, NoBracketingException {
        if (univariateFunction != null) {
            return new BrentSolver(d3).solve(Integer.MAX_VALUE, univariateFunction, d, d2);
        }
        throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0067, code lost:
        if (r18 >= 0.0d) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
        if (r18 <= 0.0d) goto L_0x007a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0087 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static double forceSide(int r26, org.apache.commons.math3.analysis.UnivariateFunction r27, org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver<org.apache.commons.math3.analysis.UnivariateFunction> r28, double r29, double r31, double r33, org.apache.commons.math3.analysis.solvers.AllowedSolution r35) throws org.apache.commons.math3.exception.NoBracketingException {
        /*
            r2 = r27
            r3 = r31
            r5 = r33
            org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.ANY_SIDE
            r9 = r35
            if (r9 != r1) goto L_0x000d
            return r29
        L_0x000d:
            double r10 = r28.getAbsoluteAccuracy()
            double r12 = r28.getRelativeAccuracy()
            double r12 = r12 * r29
            double r12 = org.apache.commons.math3.util.FastMath.abs(r12)
            double r10 = org.apache.commons.math3.util.FastMath.max(r10, r12)
            double r12 = r29 - r10
            double r12 = org.apache.commons.math3.util.FastMath.max(r3, r12)
            double r14 = r2.value(r12)
            r16 = r12
            double r12 = r29 + r10
            double r12 = org.apache.commons.math3.util.FastMath.min(r5, r12)
            double r18 = r2.value(r12)
            int r1 = r26 + -2
            r20 = r18
            r18 = r14
            r14 = r16
        L_0x003d:
            r16 = r12
        L_0x003f:
            r12 = 0
            r13 = 1
            if (r1 <= 0) goto L_0x0098
            r22 = 0
            int r24 = (r18 > r22 ? 1 : (r18 == r22 ? 0 : -1))
            if (r24 < 0) goto L_0x004d
            int r24 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
            if (r24 <= 0) goto L_0x0055
        L_0x004d:
            int r24 = (r18 > r22 ? 1 : (r18 == r22 ? 0 : -1))
            if (r24 > 0) goto L_0x0061
            int r24 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
            if (r24 < 0) goto L_0x0061
        L_0x0055:
            r0 = r28
            r3 = r14
            r5 = r16
            r7 = r29
            double r0 = r0.solve(r1, r2, r3, r5, r7, r9)
            return r0
        L_0x0061:
            int r24 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r24 >= 0) goto L_0x0070
            int r24 = (r18 > r22 ? 1 : (r18 == r22 ? 0 : -1))
            if (r24 < 0) goto L_0x006a
            goto L_0x007a
        L_0x006a:
            r25 = r13
            r13 = r12
            r12 = r25
            goto L_0x007a
        L_0x0070:
            int r24 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r24 <= 0) goto L_0x0079
            int r24 = (r18 > r22 ? 1 : (r18 == r22 ? 0 : -1))
            if (r24 > 0) goto L_0x006a
            goto L_0x007a
        L_0x0079:
            r12 = r13
        L_0x007a:
            if (r13 == 0) goto L_0x0087
            double r14 = r14 - r10
            double r14 = org.apache.commons.math3.util.FastMath.max(r3, r14)
            double r18 = r2.value(r14)
            int r1 = r1 + -1
        L_0x0087:
            if (r12 == 0) goto L_0x003f
            double r12 = r16 + r10
            double r12 = org.apache.commons.math3.util.FastMath.min(r5, r12)
            double r16 = r2.value(r12)
            int r1 = r1 + -1
            r20 = r16
            goto L_0x003d
        L_0x0098:
            org.apache.commons.math3.exception.NoBracketingException r2 = new org.apache.commons.math3.exception.NoBracketingException
            org.apache.commons.math3.exception.util.LocalizedFormats r9 = org.apache.commons.math3.exception.util.LocalizedFormats.FAILED_BRACKETING
            r10 = 5
            java.lang.Object[] r10 = new java.lang.Object[r10]
            int r1 = r26 - r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r10[r12] = r1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r26)
            r10[r13] = r0
            java.lang.Double r0 = java.lang.Double.valueOf(r29)
            r1 = 2
            r10[r1] = r0
            r0 = 3
            java.lang.Double r1 = java.lang.Double.valueOf(r31)
            r10[r0] = r1
            r0 = 4
            java.lang.Double r1 = java.lang.Double.valueOf(r33)
            r10[r0] = r1
            r12 = r2
            r13 = r9
            r22 = r10
            r12.<init>(r13, r14, r16, r18, r20, r22)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide(int, org.apache.commons.math3.analysis.UnivariateFunction, org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver, double, double, double, org.apache.commons.math3.analysis.solvers.AllowedSolution):double");
    }

    public static double[] bracket(UnivariateFunction univariateFunction, double d, double d2, double d3) throws NullArgumentException, NotStrictlyPositiveException, NoBracketingException {
        return bracket(univariateFunction, d, d2, d3, Integer.MAX_VALUE);
    }

    public static double[] bracket(UnivariateFunction univariateFunction, double d, double d2, double d3, int i) throws NullArgumentException, NotStrictlyPositiveException, NoBracketingException {
        double value;
        double value2;
        double d4;
        UnivariateFunction univariateFunction2 = univariateFunction;
        double d5 = d2;
        double d6 = d3;
        int i2 = i;
        if (univariateFunction2 == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        } else if (i2 <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.INVALID_MAX_ITERATIONS, Integer.valueOf(i));
        } else {
            verifySequence(d5, d, d6);
            double d7 = d;
            double d8 = d7;
            int i3 = 0;
            while (true) {
                d7 = FastMath.max(d7 - 1.0d, d5);
                d8 = FastMath.min(d8 + 1.0d, d6);
                value = univariateFunction2.value(d7);
                value2 = univariateFunction2.value(d8);
                i3++;
                d4 = value * value2;
                if (d4 <= 0.0d || i3 >= i2 || (d7 <= d5 && d8 >= d6)) {
                }
            }
            if (d4 > 0.0d) {
                NoBracketingException noBracketingException = new NoBracketingException(LocalizedFormats.FAILED_BRACKETING, d7, d8, value, value2, Integer.valueOf(i3), Integer.valueOf(i), Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3));
                throw noBracketingException;
            }
            return new double[]{d7, d8};
        }
    }

    public static boolean isBracketing(UnivariateFunction univariateFunction, double d, double d2) throws NullArgumentException {
        if (univariateFunction == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        double value = univariateFunction.value(d);
        double value2 = univariateFunction.value(d2);
        if ((value < 0.0d || value2 > 0.0d) && (value > 0.0d || value2 < 0.0d)) {
            return false;
        }
        return true;
    }

    public static void verifyInterval(double d, double d2) throws NumberIsTooLargeException {
        if (d >= d2) {
            throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, Double.valueOf(d), Double.valueOf(d2), false);
        }
    }

    public static void verifySequence(double d, double d2, double d3) throws NumberIsTooLargeException {
        verifyInterval(d, d2);
        verifyInterval(d2, d3);
    }

    public static void verifyBracketing(UnivariateFunction univariateFunction, double d, double d2) throws NullArgumentException, NoBracketingException {
        if (univariateFunction == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        verifyInterval(d, d2);
        if (!isBracketing(univariateFunction, d, d2)) {
            NoBracketingException noBracketingException = new NoBracketingException(d, d2, univariateFunction.value(d), univariateFunction.value(d2));
            throw noBracketingException;
        }
    }
}
