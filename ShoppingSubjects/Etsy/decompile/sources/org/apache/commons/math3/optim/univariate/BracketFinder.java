package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.Incrementor;

public class BracketFinder {
    private static final double EPS_MIN = 1.0E-21d;
    private static final double GOLD = 1.618034d;
    private final Incrementor evaluations;
    private double fHi;
    private double fLo;
    private double fMid;
    private final double growLimit;
    private double hi;
    private double lo;
    private double mid;

    public BracketFinder() {
        this(100.0d, 50);
    }

    public BracketFinder(double d, int i) {
        this.evaluations = new Incrementor();
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d));
        } else if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        } else {
            this.growLimit = d;
            this.evaluations.setMaximalCount(i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        r30 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a5, code lost:
        r9 = r5;
        r5 = r2;
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0137, code lost:
        r13 = r7;
        r2 = r15;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00cf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void search(org.apache.commons.math3.analysis.UnivariateFunction r37, org.apache.commons.math3.optim.nonlinear.scalar.GoalType r38, double r39, double r41) {
        /*
            r36 = this;
            r0 = r36
            r1 = r37
            org.apache.commons.math3.util.Incrementor r2 = r0.evaluations
            r2.resetCount()
            org.apache.commons.math3.optim.nonlinear.scalar.GoalType r2 = org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE
            r3 = r38
            if (r3 != r2) goto L_0x0014
            r2 = 1
        L_0x0010:
            r4 = r2
            r2 = r39
            goto L_0x0016
        L_0x0014:
            r2 = 0
            goto L_0x0010
        L_0x0016:
            double r5 = r0.eval(r1, r2)
            r7 = r41
            double r9 = r0.eval(r1, r7)
            if (r4 == 0) goto L_0x0027
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 >= 0) goto L_0x0031
            goto L_0x002b
        L_0x0027:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x0031
        L_0x002b:
            r32 = r2
            r2 = r7
            r7 = r32
            goto L_0x0036
        L_0x0031:
            r32 = r5
            r5 = r9
            r9 = r32
        L_0x0036:
            double r11 = r7 - r2
            r13 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r11 = r11 * r13
            double r11 = r11 + r7
            double r15 = r0.eval(r1, r11)
        L_0x0043:
            if (r4 == 0) goto L_0x004e
            int r17 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1))
            if (r17 >= 0) goto L_0x004a
            goto L_0x0052
        L_0x004a:
            r30 = r2
            goto L_0x0137
        L_0x004e:
            int r17 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1))
            if (r17 <= 0) goto L_0x004a
        L_0x0052:
            double r17 = r7 - r2
            double r19 = r5 - r15
            double r19 = r19 * r17
            double r21 = r7 - r11
            double r23 = r5 - r9
            double r23 = r23 * r21
            double r13 = r23 - r19
            double r25 = java.lang.Math.abs(r13)
            r27 = 4292743757239851855(0x3b92e3b40a0e9b4f, double:1.0E-21)
            int r29 = (r25 > r27 ? 1 : (r25 == r27 ? 0 : -1))
            if (r29 >= 0) goto L_0x0073
            r13 = 4297247356867222351(0x3ba2e3b40a0e9b4f, double:2.0E-21)
            goto L_0x0077
        L_0x0073:
            r25 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r13 = r13 * r25
        L_0x0077:
            double r21 = r21 * r23
            double r17 = r17 * r19
            double r21 = r21 - r17
            double r21 = r21 / r13
            double r13 = r7 - r21
            r30 = r2
            double r2 = r0.growLimit
            double r17 = r11 - r7
            double r2 = r2 * r17
            double r2 = r2 + r7
            double r19 = r13 - r11
            double r21 = r7 - r13
            double r21 = r21 * r19
            r23 = 0
            int r25 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r25 <= 0) goto L_0x00cf
            double r2 = r0.eval(r1, r13)
            if (r4 == 0) goto L_0x00a1
            int r19 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r19 >= 0) goto L_0x00aa
            goto L_0x00a5
        L_0x00a1:
            int r19 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r19 <= 0) goto L_0x00aa
        L_0x00a5:
            r9 = r5
            r5 = r2
            r2 = r15
            goto L_0x013b
        L_0x00aa:
            if (r4 == 0) goto L_0x00b7
            int r19 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r19 <= 0) goto L_0x00b1
            goto L_0x00bb
        L_0x00b1:
            r2 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            goto L_0x00bf
        L_0x00b7:
            int r19 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r19 >= 0) goto L_0x00b1
        L_0x00bb:
            r11 = r13
            r13 = r7
            goto L_0x0139
        L_0x00bf:
            double r13 = r2 * r17
            double r13 = r13 + r11
            double r2 = r0.eval(r1, r13)
            r9 = r5
            r5 = r15
            r21 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            goto L_0x012f
        L_0x00cf:
            double r9 = r13 - r2
            double r21 = r2 - r11
            double r21 = r21 * r9
            int r25 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r25 < 0) goto L_0x00ef
            double r9 = r0.eval(r1, r2)
            r21 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            r32 = r5
            r5 = r15
            r15 = r9
            r9 = r32
            r34 = r7
            r7 = r11
            r11 = r2
            r2 = r34
            goto L_0x0133
        L_0x00ef:
            double r2 = r11 - r13
            double r9 = r9 * r2
            int r2 = (r9 > r23 ? 1 : (r9 == r23 ? 0 : -1))
            if (r2 <= 0) goto L_0x0121
            double r2 = r0.eval(r1, r13)
            if (r4 == 0) goto L_0x0107
            int r9 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r9 >= 0) goto L_0x0101
            goto L_0x010b
        L_0x0101:
            r21 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            goto L_0x012d
        L_0x0107:
            int r9 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r9 <= 0) goto L_0x0101
        L_0x010b:
            r21 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r5 = r21 * r19
            double r5 = r5 + r13
            double r7 = r0.eval(r1, r5)
            r9 = r15
            r15 = r7
            r7 = r13
            r32 = r2
            r2 = r11
            r11 = r5
            r5 = r32
            goto L_0x0133
        L_0x0121:
            r21 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r13 = r21 * r17
            double r13 = r13 + r11
            double r2 = r0.eval(r1, r13)
        L_0x012d:
            r9 = r5
            r5 = r15
        L_0x012f:
            r15 = r2
            r2 = r7
            r7 = r11
            r11 = r13
        L_0x0133:
            r13 = r21
            goto L_0x0043
        L_0x0137:
            r13 = r7
            r2 = r15
        L_0x0139:
            r7 = r30
        L_0x013b:
            r0.lo = r7
            r0.fLo = r9
            r0.mid = r13
            r0.fMid = r5
            r0.hi = r11
            r0.fHi = r2
            double r1 = r0.lo
            double r3 = r0.hi
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x015f
            double r1 = r0.lo
            double r3 = r0.hi
            r0.lo = r3
            r0.hi = r1
            double r1 = r0.fLo
            double r3 = r0.fHi
            r0.fLo = r3
            r0.fHi = r1
        L_0x015f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.univariate.BracketFinder.search(org.apache.commons.math3.analysis.UnivariateFunction, org.apache.commons.math3.optim.nonlinear.scalar.GoalType, double, double):void");
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public double getLo() {
        return this.lo;
    }

    public double getFLo() {
        return this.fLo;
    }

    public double getHi() {
        return this.hi;
    }

    public double getFHi() {
        return this.fHi;
    }

    public double getMid() {
        return this.mid;
    }

    public double getFMid() {
        return this.fMid;
    }

    private double eval(UnivariateFunction univariateFunction, double d) {
        try {
            this.evaluations.incrementCount();
            return univariateFunction.value(d);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }
}
