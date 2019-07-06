package org.apache.commons.math3.dfp;

import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.Incrementor;

public class BracketingNthOrderBrentSolverDFP {
    private static final int MAXIMAL_AGING = 2;
    private final Dfp absoluteAccuracy;
    private final Incrementor evaluations = new Incrementor();
    private final Dfp functionValueAccuracy;
    private final int maximalOrder;
    private final Dfp relativeAccuracy;

    public BracketingNthOrderBrentSolverDFP(Dfp dfp, Dfp dfp2, Dfp dfp3, int i) throws NumberIsTooSmallException {
        if (i < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), Integer.valueOf(2), true);
        }
        this.maximalOrder = i;
        this.absoluteAccuracy = dfp2;
        this.relativeAccuracy = dfp;
        this.functionValueAccuracy = dfp3;
    }

    public int getMaximalOrder() {
        return this.maximalOrder;
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public Dfp getAbsoluteAccuracy() {
        return this.absoluteAccuracy;
    }

    public Dfp getRelativeAccuracy() {
        return this.relativeAccuracy;
    }

    public Dfp getFunctionValueAccuracy() {
        return this.functionValueAccuracy;
    }

    public Dfp solve(int i, UnivariateDfpFunction univariateDfpFunction, Dfp dfp, Dfp dfp2, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        return solve(i, univariateDfpFunction, dfp, dfp2, dfp.add(dfp2).divide(2), allowedSolution);
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01e0  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x019c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.dfp.Dfp solve(int r29, org.apache.commons.math3.dfp.UnivariateDfpFunction r30, org.apache.commons.math3.dfp.Dfp r31, org.apache.commons.math3.dfp.Dfp r32, org.apache.commons.math3.dfp.Dfp r33, org.apache.commons.math3.analysis.solvers.AllowedSolution r34) throws org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r28 = this;
            r6 = r28
            r7 = r30
            org.apache.commons.math3.util.MathUtils.checkNotNull(r30)
            org.apache.commons.math3.util.Incrementor r0 = r6.evaluations
            r1 = r29
            r0.setMaximalCount(r1)
            org.apache.commons.math3.util.Incrementor r0 = r6.evaluations
            r0.resetCount()
            org.apache.commons.math3.dfp.Dfp r8 = r33.getZero()
            r0 = 3
            r9 = 1
            org.apache.commons.math3.dfp.Dfp r10 = r8.newInstance(r9, r0)
            int r1 = r6.maximalOrder
            int r1 = r1 + r9
            org.apache.commons.math3.dfp.Dfp[] r11 = new org.apache.commons.math3.dfp.Dfp[r1]
            int r1 = r6.maximalOrder
            int r1 = r1 + r9
            org.apache.commons.math3.dfp.Dfp[] r12 = new org.apache.commons.math3.dfp.Dfp[r1]
            r13 = 0
            r11[r13] = r31
            r11[r9] = r33
            r14 = 2
            r11[r14] = r32
            org.apache.commons.math3.util.Incrementor r1 = r6.evaluations
            r1.incrementCount()
            r1 = r11[r9]
            org.apache.commons.math3.dfp.Dfp r1 = r7.value(r1)
            r12[r9] = r1
            r1 = r12[r9]
            boolean r1 = r1.isZero()
            if (r1 == 0) goto L_0x0047
            r0 = r11[r9]
            return r0
        L_0x0047:
            org.apache.commons.math3.util.Incrementor r1 = r6.evaluations
            r1.incrementCount()
            r1 = r11[r13]
            org.apache.commons.math3.dfp.Dfp r1 = r7.value(r1)
            r12[r13] = r1
            r1 = r12[r13]
            boolean r1 = r1.isZero()
            if (r1 == 0) goto L_0x005f
            r0 = r11[r13]
            return r0
        L_0x005f:
            r1 = r12[r13]
            r2 = r12[r9]
            org.apache.commons.math3.dfp.Dfp r1 = r1.multiply(r2)
            boolean r1 = r1.negativeOrNull()
            if (r1 == 0) goto L_0x0070
            r0 = r9
            r1 = r14
            goto L_0x0098
        L_0x0070:
            org.apache.commons.math3.util.Incrementor r1 = r6.evaluations
            r1.incrementCount()
            r1 = r11[r14]
            org.apache.commons.math3.dfp.Dfp r1 = r7.value(r1)
            r12[r14] = r1
            r1 = r12[r14]
            boolean r1 = r1.isZero()
            if (r1 == 0) goto L_0x0088
            r0 = r11[r14]
            return r0
        L_0x0088:
            r1 = r12[r9]
            r2 = r12[r14]
            org.apache.commons.math3.dfp.Dfp r1 = r1.multiply(r2)
            boolean r1 = r1.negativeOrNull()
            if (r1 == 0) goto L_0x0240
            r1 = r0
            r0 = r14
        L_0x0098:
            int r2 = r11.length
            org.apache.commons.math3.dfp.Dfp[] r15 = new org.apache.commons.math3.dfp.Dfp[r2]
            int r2 = r0 + -1
            r3 = r11[r2]
            r2 = r12[r2]
            org.apache.commons.math3.dfp.Dfp r5 = r3.abs()
            org.apache.commons.math3.dfp.Dfp r4 = r2.abs()
            r13 = r11[r0]
            r9 = r12[r0]
            org.apache.commons.math3.dfp.Dfp r14 = r13.abs()
            org.apache.commons.math3.dfp.Dfp r16 = r9.abs()
            r19 = r1
            r1 = r2
            r18 = r8
            r17 = r10
            r2 = 0
            r10 = 0
            r8 = r0
            r0 = r9
            r9 = r4
            r4 = r13
            r13 = r16
        L_0x00c4:
            boolean r16 = r5.lessThan(r14)
            if (r16 == 0) goto L_0x00ce
            r20 = r5
            r5 = r14
            goto L_0x00d0
        L_0x00ce:
            r20 = r5
        L_0x00d0:
            boolean r16 = r9.lessThan(r13)
            if (r16 == 0) goto L_0x00dc
            r22 = r9
            r21 = r14
            r14 = r13
            goto L_0x00e2
        L_0x00dc:
            r22 = r9
            r21 = r14
            r14 = r22
        L_0x00e2:
            org.apache.commons.math3.dfp.Dfp r9 = r6.absoluteAccuracy
            r23 = r13
            org.apache.commons.math3.dfp.Dfp r13 = r6.relativeAccuracy
            org.apache.commons.math3.dfp.Dfp r5 = r13.multiply(r5)
            org.apache.commons.math3.dfp.Dfp r5 = r9.add(r5)
            org.apache.commons.math3.dfp.Dfp r9 = r4.subtract(r3)
            org.apache.commons.math3.dfp.Dfp r5 = r9.subtract(r5)
            boolean r5 = r5.negativeOrNull()
            if (r5 != 0) goto L_0x0207
            org.apache.commons.math3.dfp.Dfp r5 = r6.functionValueAccuracy
            boolean r5 = r14.lessThan(r5)
            if (r5 == 0) goto L_0x0108
            goto L_0x0207
        L_0x0108:
            r5 = 16
            r9 = 2
            if (r2 < r9) goto L_0x0117
            org.apache.commons.math3.dfp.Dfp r5 = r0.divide(r5)
            org.apache.commons.math3.dfp.Dfp r5 = r5.negate()
        L_0x0115:
            r9 = r5
            goto L_0x0124
        L_0x0117:
            if (r10 < r9) goto L_0x0122
            org.apache.commons.math3.dfp.Dfp r5 = r1.divide(r5)
            org.apache.commons.math3.dfp.Dfp r5 = r5.negate()
            goto L_0x0115
        L_0x0122:
            r9 = r18
        L_0x0124:
            r14 = r19
            r13 = 0
        L_0x0127:
            int r5 = r14 - r13
            java.lang.System.arraycopy(r11, r13, r15, r13, r5)
            r16 = r0
            r0 = r6
            r5 = r1
            r1 = r9
            r24 = r2
            r2 = r15
            r25 = r9
            r9 = r3
            r3 = r12
            r26 = r15
            r15 = r4
            r4 = r13
            r27 = r10
            r10 = r5
            r5 = r14
            org.apache.commons.math3.dfp.Dfp r0 = r0.guessX(r1, r2, r3, r4, r5)
            boolean r1 = r0.greaterThan(r9)
            if (r1 == 0) goto L_0x0150
            boolean r1 = r0.lessThan(r15)
            if (r1 != 0) goto L_0x015d
        L_0x0150:
            int r0 = r8 - r13
            int r1 = r14 - r8
            if (r0 < r1) goto L_0x0159
            int r13 = r13 + 1
            goto L_0x015b
        L_0x0159:
            int r14 = r14 + -1
        L_0x015b:
            r0 = r17
        L_0x015d:
            boolean r1 = r0.isNaN()
            if (r1 == 0) goto L_0x0177
            int r1 = r14 - r13
            r2 = 1
            if (r1 > r2) goto L_0x0169
            goto L_0x0177
        L_0x0169:
            r3 = r9
            r1 = r10
            r4 = r15
            r0 = r16
            r2 = r24
            r9 = r25
            r15 = r26
            r10 = r27
            goto L_0x0127
        L_0x0177:
            boolean r1 = r0.isNaN()
            if (r1 == 0) goto L_0x018d
            org.apache.commons.math3.dfp.Dfp r0 = r15.subtract(r9)
            r1 = 2
            org.apache.commons.math3.dfp.Dfp r0 = r0.divide(r1)
            org.apache.commons.math3.dfp.Dfp r0 = r9.add(r0)
            int r13 = r8 + -1
            r14 = r8
        L_0x018d:
            org.apache.commons.math3.util.Incrementor r1 = r6.evaluations
            r1.incrementCount()
            org.apache.commons.math3.dfp.Dfp r1 = r7.value(r0)
            boolean r2 = r1.isZero()
            if (r2 == 0) goto L_0x019d
            return r0
        L_0x019d:
            r2 = r19
            r3 = 2
            if (r2 <= r3) goto L_0x01b0
            int r3 = r14 - r13
            if (r3 == r2) goto L_0x01b0
            r4 = 0
            java.lang.System.arraycopy(r11, r13, r11, r4, r3)
            java.lang.System.arraycopy(r12, r13, r12, r4, r3)
            int r8 = r8 - r13
            r2 = r3
            goto L_0x01c5
        L_0x01b0:
            int r3 = r11.length
            if (r2 != r3) goto L_0x01c5
            int r2 = r2 + -1
            int r3 = r11.length
            r4 = 1
            int r3 = r3 + r4
            r5 = 2
            int r3 = r3 / r5
            if (r8 < r3) goto L_0x01c5
            r3 = 0
            java.lang.System.arraycopy(r11, r4, r11, r3, r2)
            java.lang.System.arraycopy(r12, r4, r12, r3, r2)
            int r8 = r8 + -1
        L_0x01c5:
            int r3 = r8 + 1
            int r4 = r2 - r8
            java.lang.System.arraycopy(r11, r8, r11, r3, r4)
            r11[r8] = r0
            java.lang.System.arraycopy(r12, r8, r12, r3, r4)
            r12[r8] = r1
            r4 = 1
            int r19 = r2 + 1
            org.apache.commons.math3.dfp.Dfp r2 = r1.multiply(r10)
            boolean r2 = r2.negativeOrNull()
            if (r2 == 0) goto L_0x01f0
            org.apache.commons.math3.dfp.Dfp r2 = r1.abs()
            int r3 = r24 + 1
            r15 = r0
            r0 = r1
            r13 = r2
            r2 = r3
            r3 = r9
            r1 = r10
            r9 = r22
            r10 = 0
            goto L_0x01fe
        L_0x01f0:
            org.apache.commons.math3.dfp.Dfp r2 = r1.abs()
            int r10 = r27 + 1
            r9 = r2
            r8 = r3
            r13 = r23
            r2 = 0
            r3 = r0
            r0 = r16
        L_0x01fe:
            r4 = r15
            r5 = r20
            r14 = r21
            r15 = r26
            goto L_0x00c4
        L_0x0207:
            r10 = r1
            r9 = r3
            r15 = r4
            int[] r0 = org.apache.commons.math3.dfp.BracketingNthOrderBrentSolverDFP.AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution
            int r1 = r34.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x0233;
                case 2: goto L_0x0232;
                case 3: goto L_0x0231;
                case 4: goto L_0x0226;
                case 5: goto L_0x021c;
                default: goto L_0x0215;
            }
        L_0x0215:
            org.apache.commons.math3.exception.MathInternalError r0 = new org.apache.commons.math3.exception.MathInternalError
            r1 = 0
            r0.<init>(r1)
            throw r0
        L_0x021c:
            r0 = r18
            boolean r0 = r10.lessThan(r0)
            if (r0 == 0) goto L_0x0225
            r9 = r15
        L_0x0225:
            return r9
        L_0x0226:
            r0 = r18
            boolean r0 = r10.lessThan(r0)
            if (r0 == 0) goto L_0x022f
            goto L_0x0230
        L_0x022f:
            r9 = r15
        L_0x0230:
            return r9
        L_0x0231:
            return r15
        L_0x0232:
            return r9
        L_0x0233:
            r4 = r22
            r13 = r23
            boolean r0 = r4.lessThan(r13)
            if (r0 == 0) goto L_0x023e
            goto L_0x023f
        L_0x023e:
            r9 = r15
        L_0x023f:
            return r9
        L_0x0240:
            org.apache.commons.math3.exception.NoBracketingException r0 = new org.apache.commons.math3.exception.NoBracketingException
            r1 = 0
            r2 = r11[r1]
            double r8 = r2.toDouble()
            r2 = 2
            r3 = r11[r2]
            double r10 = r3.toDouble()
            r1 = r12[r1]
            double r3 = r1.toDouble()
            r1 = r12[r2]
            double r14 = r1.toDouble()
            r7 = r0
            r12 = r3
            r7.<init>(r8, r10, r12, r14)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.BracketingNthOrderBrentSolverDFP.solve(int, org.apache.commons.math3.dfp.UnivariateDfpFunction, org.apache.commons.math3.dfp.Dfp, org.apache.commons.math3.dfp.Dfp, org.apache.commons.math3.dfp.Dfp, org.apache.commons.math3.analysis.solvers.AllowedSolution):org.apache.commons.math3.dfp.Dfp");
    }

    private Dfp guessX(Dfp dfp, Dfp[] dfpArr, Dfp[] dfpArr2, int i, int i2) {
        int i3;
        int i4 = i;
        while (true) {
            i3 = i2 - 1;
            if (i4 >= i3) {
                break;
            }
            int i5 = i4 + 1;
            int i6 = i5 - i;
            while (i3 > i4) {
                dfpArr[i3] = dfpArr[i3].subtract(dfpArr[i3 - 1]).divide(dfpArr2[i3].subtract(dfpArr2[i3 - i6]));
                i3--;
            }
            i4 = i5;
        }
        Dfp zero = dfp.getZero();
        while (i3 >= i) {
            zero = dfpArr[i3].add(zero.multiply(dfp.subtract(dfpArr2[i3])));
            i3--;
        }
        return zero;
    }
}
