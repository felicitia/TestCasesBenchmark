package org.apache.commons.math3.dfp;

public class DfpDec extends Dfp {
    protected DfpDec(DfpField dfpField) {
        super(dfpField);
    }

    protected DfpDec(DfpField dfpField, byte b) {
        super(dfpField, b);
    }

    protected DfpDec(DfpField dfpField, int i) {
        super(dfpField, i);
    }

    protected DfpDec(DfpField dfpField, long j) {
        super(dfpField, j);
    }

    protected DfpDec(DfpField dfpField, double d) {
        super(dfpField, d);
        round(0);
    }

    public DfpDec(Dfp dfp) {
        super(dfp);
        round(0);
    }

    protected DfpDec(DfpField dfpField, String str) {
        super(dfpField, str);
        round(0);
    }

    protected DfpDec(DfpField dfpField, byte b, byte b2) {
        super(dfpField, b, b2);
    }

    public Dfp newInstance() {
        return new DfpDec(getField());
    }

    public Dfp newInstance(byte b) {
        return new DfpDec(getField(), b);
    }

    public Dfp newInstance(int i) {
        return new DfpDec(getField(), i);
    }

    public Dfp newInstance(long j) {
        return new DfpDec(getField(), j);
    }

    public Dfp newInstance(double d) {
        return new DfpDec(getField(), d);
    }

    public Dfp newInstance(Dfp dfp) {
        if (getField().getRadixDigits() == dfp.getField().getRadixDigits()) {
            return new DfpDec(dfp);
        }
        getField().setIEEEFlagsBits(1);
        Dfp newInstance = newInstance(getZero());
        newInstance.nans = 3;
        return dotrap(1, "newInstance", dfp, newInstance);
    }

    public Dfp newInstance(String str) {
        return new DfpDec(getField(), str);
    }

    public Dfp newInstance(byte b, byte b2) {
        return new DfpDec(getField(), b, b2);
    }

    /* access modifiers changed from: protected */
    public int getDecimalDigits() {
        return (getRadixDigits() * 4) - 3;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0096, code lost:
        if (r4 != 0) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009f, code lost:
        if (r4 != 0) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a1, code lost:
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a7, code lost:
        if (r4 == 0) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00af, code lost:
        if ((r6 & 1) != 0) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b6, code lost:
        if (r4 == 0) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00be, code lost:
        if ((r6 & 1) != 1) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c1, code lost:
        if (r0 > 5) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00c4, code lost:
        if (r0 >= 5) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c9, code lost:
        if (r4 == 0) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00cc, code lost:
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00cd, code lost:
        if (r12 == false) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d2, code lost:
        if (r1 >= r11.mant.length) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d4, code lost:
        r12 = r11.mant[r1] + r8;
        r8 = r12 / 10000;
        r11.mant[r1] = r12 % 10000;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e4, code lost:
        if (r8 == 0) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e6, code lost:
        shiftRight();
        r11.mant[r11.mant.length - 1] = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f5, code lost:
        if (r11.exp >= -32767) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f7, code lost:
        getField().setIEEEFlagsBits(8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0100, code lost:
        return 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0106, code lost:
        if (r11.exp <= 32768) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0108, code lost:
        getField().setIEEEFlagsBits(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x010f, code lost:
        return 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0110, code lost:
        if (r0 != 0) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0112, code lost:
        if (r4 == 0) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0115, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0116, code lost:
        getField().setIEEEFlagsBits(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x011f, code lost:
        return 16;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int round(int r12) {
        /*
            r11 = this;
            int[] r0 = r11.mant
            int[] r1 = r11.mant
            r2 = 0
            r3 = 1
            int r1 = r1.length
            int r1 = r1 - r3
            r0 = r0[r1]
            if (r0 != 0) goto L_0x000d
            return r2
        L_0x000d:
            int[] r1 = r11.mant
            r4 = 1000(0x3e8, float:1.401E-42)
            r5 = 4
            int r1 = r1.length
            int r1 = r1 * r5
            r6 = r1
            r1 = r4
        L_0x0016:
            if (r1 <= r0) goto L_0x001d
            int r1 = r1 / 10
            int r6 = r6 + -1
            goto L_0x0016
        L_0x001d:
            int r0 = r11.getDecimalDigits()
            int r6 = r6 - r0
            int r1 = r6 / 4
            r7 = r2
            r8 = r3
        L_0x0026:
            int r9 = r6 % 4
            if (r7 >= r9) goto L_0x002f
            int r8 = r8 * 10
            int r7 = r7 + 1
            goto L_0x0026
        L_0x002f:
            int[] r6 = r11.mant
            r6 = r6[r1]
            if (r8 > r3) goto L_0x0042
            int[] r7 = r11.mant
            int r7 = r7.length
            int r7 = r7 * r5
            int r7 = r7 + -3
            if (r0 != r7) goto L_0x0042
            int r12 = super.round(r12)
            return r12
        L_0x0042:
            if (r8 != r3) goto L_0x005a
            int[] r0 = r11.mant
            int r7 = r1 + -1
            r0 = r0[r7]
            int r0 = r0 / r4
            int r0 = r0 % 10
            int[] r9 = r11.mant
            r10 = r9[r7]
            int r10 = r10 % r4
            r9[r7] = r10
            int[] r4 = r11.mant
            r4 = r4[r7]
            r12 = r12 | r4
            goto L_0x0064
        L_0x005a:
            int r0 = r6 * 10
            int r0 = r0 / r8
            int r0 = r0 % 10
            int r4 = r8 / 10
            int r4 = r6 % r4
            r12 = r12 | r4
        L_0x0064:
            r4 = r12
            r12 = r2
        L_0x0066:
            if (r12 >= r1) goto L_0x0074
            int[] r7 = r11.mant
            r7 = r7[r12]
            r4 = r4 | r7
            int[] r7 = r11.mant
            r7[r12] = r2
            int r12 = r12 + 1
            goto L_0x0066
        L_0x0074:
            int[] r12 = r11.mant
            int r6 = r6 / r8
            int r7 = r6 * r8
            r12[r1] = r7
            int[] r12 = org.apache.commons.math3.dfp.DfpDec.AnonymousClass1.$SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode
            org.apache.commons.math3.dfp.DfpField r7 = r11.getField()
            org.apache.commons.math3.dfp.DfpField$RoundingMode r7 = r7.getRoundingMode()
            int r7 = r7.ordinal()
            r12 = r12[r7]
            r7 = 5
            switch(r12) {
                case 1: goto L_0x00cc;
                case 2: goto L_0x00c7;
                case 3: goto L_0x00c4;
                case 4: goto L_0x00c1;
                case 5: goto L_0x00b2;
                case 6: goto L_0x00a3;
                case 7: goto L_0x0099;
                default: goto L_0x008f;
            }
        L_0x008f:
            byte r12 = r11.sign
            r6 = -1
            if (r12 != r6) goto L_0x00cc
            if (r0 != 0) goto L_0x00a1
            if (r4 == 0) goto L_0x00cc
            goto L_0x00a1
        L_0x0099:
            byte r12 = r11.sign
            if (r12 != r3) goto L_0x00cc
            if (r0 != 0) goto L_0x00a1
            if (r4 == 0) goto L_0x00cc
        L_0x00a1:
            r12 = r3
            goto L_0x00cd
        L_0x00a3:
            if (r0 > r7) goto L_0x00a1
            if (r0 != r7) goto L_0x00a9
            if (r4 != 0) goto L_0x00a1
        L_0x00a9:
            if (r0 != r7) goto L_0x00cc
            if (r4 != 0) goto L_0x00cc
            r12 = r6 & 1
            if (r12 != 0) goto L_0x00cc
            goto L_0x00a1
        L_0x00b2:
            if (r0 > r7) goto L_0x00a1
            if (r0 != r7) goto L_0x00b8
            if (r4 != 0) goto L_0x00a1
        L_0x00b8:
            if (r0 != r7) goto L_0x00cc
            if (r4 != 0) goto L_0x00cc
            r12 = r6 & 1
            if (r12 != r3) goto L_0x00cc
            goto L_0x00a1
        L_0x00c1:
            if (r0 <= r7) goto L_0x00cc
            goto L_0x00a1
        L_0x00c4:
            if (r0 < r7) goto L_0x00cc
            goto L_0x00a1
        L_0x00c7:
            if (r0 != 0) goto L_0x00a1
            if (r4 == 0) goto L_0x00cc
            goto L_0x00a1
        L_0x00cc:
            r12 = r2
        L_0x00cd:
            if (r12 == 0) goto L_0x00f1
        L_0x00cf:
            int[] r12 = r11.mant
            int r12 = r12.length
            if (r1 >= r12) goto L_0x00e4
            int[] r12 = r11.mant
            r12 = r12[r1]
            int r12 = r12 + r8
            int r8 = r12 / 10000
            int[] r6 = r11.mant
            int r12 = r12 % 10000
            r6[r1] = r12
            int r1 = r1 + 1
            goto L_0x00cf
        L_0x00e4:
            if (r8 == 0) goto L_0x00f1
            r11.shiftRight()
            int[] r12 = r11.mant
            int[] r1 = r11.mant
            int r1 = r1.length
            int r1 = r1 - r3
            r12[r1] = r8
        L_0x00f1:
            int r12 = r11.exp
            r1 = -32767(0xffffffffffff8001, float:NaN)
            if (r12 >= r1) goto L_0x0101
            org.apache.commons.math3.dfp.DfpField r12 = r11.getField()
            r0 = 8
            r12.setIEEEFlagsBits(r0)
            return r0
        L_0x0101:
            int r12 = r11.exp
            r1 = 32768(0x8000, float:4.5918E-41)
            if (r12 <= r1) goto L_0x0110
            org.apache.commons.math3.dfp.DfpField r12 = r11.getField()
            r12.setIEEEFlagsBits(r5)
            return r5
        L_0x0110:
            if (r0 != 0) goto L_0x0116
            if (r4 == 0) goto L_0x0115
            goto L_0x0116
        L_0x0115:
            return r2
        L_0x0116:
            org.apache.commons.math3.dfp.DfpField r12 = r11.getField()
            r0 = 16
            r12.setIEEEFlagsBits(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.DfpDec.round(int):int");
    }

    public Dfp nextAfter(Dfp dfp) {
        Dfp dfp2;
        Dfp dfp3;
        if (getField().getRadixDigits() != dfp.getField().getRadixDigits()) {
            getField().setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            return dotrap(1, "nextAfter", dfp, newInstance);
        }
        boolean lessThan = lessThan(dfp);
        if (equals(dfp)) {
            return newInstance(dfp);
        }
        if (lessThan(getZero())) {
            lessThan = !lessThan;
        }
        if (lessThan) {
            Dfp copysign = copysign(power10((log10() - getDecimalDigits()) + 1), this);
            if (equals(getZero())) {
                copysign = power10K((-32767 - this.mant.length) - 1);
            }
            if (copysign.equals(getZero())) {
                dfp2 = copysign(newInstance(getZero()), this);
            } else {
                dfp2 = add(copysign);
            }
        } else {
            Dfp copysign2 = copysign(power10(log10()), this);
            if (equals(copysign2)) {
                dfp3 = copysign2.divide(power10(getDecimalDigits()));
            } else {
                dfp3 = copysign2.divide(power10(getDecimalDigits() - 1));
            }
            if (equals(getZero())) {
                dfp3 = power10K((-32767 - this.mant.length) - 1);
            }
            if (dfp3.equals(getZero())) {
                dfp2 = copysign(newInstance(getZero()), this);
            } else {
                dfp2 = subtract(dfp3);
            }
        }
        if (dfp2.classify() == 1 && classify() != 1) {
            getField().setIEEEFlagsBits(16);
            dfp2 = dotrap(16, "nextAfter", dfp, dfp2);
        }
        if (dfp2.equals(getZero()) && !equals(getZero())) {
            getField().setIEEEFlagsBits(16);
            dfp2 = dotrap(16, "nextAfter", dfp, dfp2);
        }
        return dfp2;
    }
}
