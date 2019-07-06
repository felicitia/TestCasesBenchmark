package org.apache.commons.math3.dfp;

public class DfpMath {
    private static final String POW_TRAP = "pow";

    private DfpMath() {
    }

    protected static Dfp[] split(DfpField dfpField, String str) {
        Dfp[] dfpArr = new Dfp[2];
        char[] cArr = new char[str.length()];
        boolean z = true;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= cArr.length) {
                i = 0;
                break;
            }
            cArr[i] = str.charAt(i);
            if (cArr[i] >= '1' && cArr[i] <= '9') {
                z = false;
            }
            if (cArr[i] == '.') {
                i2 += (400 - i2) % 4;
                z = false;
            }
            if (i2 == (dfpField.getRadixDigits() / 2) * 4) {
                break;
            }
            if (cArr[i] >= '0' && cArr[i] <= '9' && !z) {
                i2++;
            }
            i++;
        }
        dfpArr[0] = dfpField.newDfp(new String(cArr, 0, i));
        for (int i3 = 0; i3 < cArr.length; i3++) {
            cArr[i3] = str.charAt(i3);
            if (cArr[i3] >= '0' && cArr[i3] <= '9' && i3 < i) {
                cArr[i3] = '0';
            }
        }
        dfpArr[1] = dfpField.newDfp(new String(cArr));
        return dfpArr;
    }

    protected static Dfp[] split(Dfp dfp) {
        Dfp[] dfpArr = new Dfp[2];
        Dfp multiply = dfp.multiply(dfp.power10K(dfp.getRadixDigits() / 2));
        dfpArr[0] = dfp.add(multiply).subtract(multiply);
        dfpArr[1] = dfp.subtract(dfpArr[0]);
        return dfpArr;
    }

    protected static Dfp[] splitMult(Dfp[] dfpArr, Dfp[] dfpArr2) {
        Dfp[] dfpArr3 = new Dfp[2];
        dfpArr3[1] = dfpArr[0].getZero();
        dfpArr3[0] = dfpArr[0].multiply(dfpArr2[0]);
        if (dfpArr3[0].classify() == 1 || dfpArr3[0].equals(dfpArr3[1])) {
            return dfpArr3;
        }
        dfpArr3[1] = dfpArr[0].multiply(dfpArr2[1]).add(dfpArr[1].multiply(dfpArr2[0])).add(dfpArr[1].multiply(dfpArr2[1]));
        return dfpArr3;
    }

    protected static Dfp[] splitDiv(Dfp[] dfpArr, Dfp[] dfpArr2) {
        Dfp[] dfpArr3 = {dfpArr[0].divide(dfpArr2[0]), dfpArr[1].multiply(dfpArr2[0]).subtract(dfpArr[0].multiply(dfpArr2[1]))};
        dfpArr3[1] = dfpArr3[1].divide(dfpArr2[0].multiply(dfpArr2[0]).add(dfpArr2[0].multiply(dfpArr2[1])));
        return dfpArr3;
    }

    protected static Dfp splitPow(Dfp[] dfpArr, int i) {
        boolean z;
        Dfp[] dfpArr2 = new Dfp[2];
        Dfp[] dfpArr3 = {dfpArr[0].getOne(), dfpArr[0].getZero()};
        if (i == 0) {
            return dfpArr3[0].add(dfpArr3[1]);
        }
        if (i < 0) {
            i = -i;
            z = true;
        } else {
            z = false;
        }
        while (true) {
            dfpArr2[0] = new Dfp(dfpArr[0]);
            dfpArr2[1] = new Dfp(dfpArr[1]);
            Dfp[] dfpArr4 = dfpArr2;
            int i2 = 1;
            while (true) {
                int i3 = i2 * 2;
                if (i3 > i) {
                    break;
                }
                dfpArr4 = splitMult(dfpArr4, dfpArr4);
                i2 = i3;
            }
            i -= i2;
            dfpArr3 = splitMult(dfpArr3, dfpArr4);
            if (i < 1) {
                break;
            }
            dfpArr2 = dfpArr4;
        }
        dfpArr3[0] = dfpArr3[0].add(dfpArr3[1]);
        if (z) {
            dfpArr3[0] = dfpArr[0].getOne().divide(dfpArr3[0]);
        }
        return dfpArr3[0];
    }

    public static Dfp pow(Dfp dfp, int i) {
        boolean z;
        Dfp dfp2;
        Dfp one = dfp.getOne();
        if (i == 0) {
            return one;
        }
        if (i < 0) {
            i = -i;
            z = true;
        } else {
            z = false;
        }
        do {
            Dfp dfp3 = new Dfp(dfp);
            int i2 = 1;
            while (true) {
                dfp2 = new Dfp(dfp3);
                dfp3 = dfp3.multiply(dfp3);
                int i3 = i2 * 2;
                if (i <= i3) {
                    break;
                }
                i2 = i3;
            }
            i -= i2;
            one = one.multiply(dfp2);
        } while (i >= 1);
        if (z) {
            one = dfp.getOne().divide(one);
        }
        return dfp.newInstance(one);
    }

    public static Dfp exp(Dfp dfp) {
        Dfp rint = dfp.rint();
        Dfp subtract = dfp.subtract(rint);
        int intValue = rint.intValue();
        if (intValue > 2147483646) {
            return dfp.newInstance(1, 1);
        }
        if (intValue < -2147483646) {
            return dfp.newInstance();
        }
        return splitPow(dfp.getField().getESplit(), intValue).multiply(expInternal(subtract));
    }

    protected static Dfp expInternal(Dfp dfp) {
        Dfp one = dfp.getOne();
        Dfp one2 = dfp.getOne();
        Dfp one3 = dfp.getOne();
        Dfp dfp2 = new Dfp(one);
        for (int i = 1; i < 90; i++) {
            one2 = one2.multiply(dfp);
            one3 = one3.divide(i);
            one = one.add(one2.multiply(one3));
            if (one.equals(dfp2)) {
                break;
            }
            dfp2 = new Dfp(one);
        }
        return one;
    }

    public static Dfp log(Dfp dfp) {
        if (dfp.equals(dfp.getZero()) || dfp.lessThan(dfp.getZero()) || dfp.isNaN()) {
            dfp.getField().setIEEEFlagsBits(1);
            return dfp.dotrap(1, "ln", dfp, dfp.newInstance(1, 3));
        } else if (dfp.classify() == 1) {
            return dfp;
        } else {
            Dfp dfp2 = new Dfp(dfp);
            int log10K = dfp2.log10K();
            Dfp divide = dfp2.divide(pow(dfp.newInstance(10000), log10K));
            int intValue = divide.floor().intValue();
            int i = 0;
            while (intValue > 2) {
                intValue >>= 1;
                i++;
            }
            Dfp[] split = split(divide);
            Dfp[] dfpArr = new Dfp[2];
            dfpArr[0] = pow(dfp.getTwo(), i);
            split[0] = split[0].divide(dfpArr[0]);
            split[1] = split[1].divide(dfpArr[0]);
            dfpArr[0] = dfp.newInstance("1.33333");
            while (split[0].add(split[1]).greaterThan(dfpArr[0])) {
                split[0] = split[0].divide(2);
                split[1] = split[1].divide(2);
                i++;
            }
            Dfp[] logInternal = logInternal(split);
            StringBuilder sb = new StringBuilder();
            int i2 = 4 * log10K;
            sb.append(i + i2);
            split[0] = dfp.newInstance(sb.toString());
            split[1] = dfp.getZero();
            Dfp[] splitMult = splitMult(dfp.getField().getLn2Split(), split);
            logInternal[0] = logInternal[0].add(splitMult[0]);
            logInternal[1] = logInternal[1].add(splitMult[1]);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(i2);
            split[0] = dfp.newInstance(sb2.toString());
            split[1] = dfp.getZero();
            Dfp[] splitMult2 = splitMult(dfp.getField().getLn5Split(), split);
            logInternal[0] = logInternal[0].add(splitMult2[0]);
            logInternal[1] = logInternal[1].add(splitMult2[1]);
            return dfp.newInstance(logInternal[0].add(logInternal[1]));
        }
    }

    protected static Dfp[] logInternal(Dfp[] dfpArr) {
        int i = 1;
        Dfp add = dfpArr[0].divide(4).add(dfpArr[1].divide(4));
        Dfp divide = add.add(dfpArr[0].newInstance("-0.25")).divide(add.add(dfpArr[0].newInstance("0.25")));
        Dfp dfp = new Dfp(divide);
        Dfp dfp2 = new Dfp(divide);
        Dfp dfp3 = new Dfp(dfp);
        Dfp dfp4 = dfp;
        for (int i2 = 0; i2 < 10000; i2++) {
            dfp2 = dfp2.multiply(divide).multiply(divide);
            i += 2;
            dfp4 = dfp4.add(dfp2.divide(i));
            if (dfp4.equals(dfp3)) {
                break;
            }
            dfp3 = new Dfp(dfp4);
        }
        return split(dfp4.multiply(dfpArr[0].getTwo()));
    }

    public static Dfp pow(Dfp dfp, Dfp dfp2) {
        Dfp dfp3;
        if (dfp.getField().getRadixDigits() != dfp2.getField().getRadixDigits()) {
            dfp.getField().setIEEEFlagsBits(1);
            Dfp newInstance = dfp.newInstance(dfp.getZero());
            newInstance.nans = 3;
            return dfp.dotrap(1, POW_TRAP, dfp, newInstance);
        }
        Dfp zero = dfp.getZero();
        Dfp one = dfp.getOne();
        Dfp two = dfp.getTwo();
        boolean z = false;
        if (dfp2.equals(zero)) {
            return dfp.newInstance(one);
        }
        if (dfp2.equals(one)) {
            if (!dfp.isNaN()) {
                return dfp;
            }
            dfp.getField().setIEEEFlagsBits(1);
            return dfp.dotrap(1, POW_TRAP, dfp, dfp);
        } else if (dfp.isNaN() || dfp2.isNaN()) {
            dfp.getField().setIEEEFlagsBits(1);
            return dfp.dotrap(1, POW_TRAP, dfp, dfp.newInstance(1, 3));
        } else if (!dfp.equals(zero)) {
            if (dfp.lessThan(zero)) {
                dfp = dfp.negate();
                z = true;
            }
            if (!dfp.greaterThan(one) || dfp2.classify() != 1) {
                if (!dfp.lessThan(one) || dfp2.classify() != 1) {
                    if (dfp.equals(one) && dfp2.classify() == 1) {
                        dfp.getField().setIEEEFlagsBits(1);
                        return dfp.dotrap(1, POW_TRAP, dfp, dfp.newInstance(1, 3));
                    } else if (dfp.classify() == 1) {
                        if (z) {
                            if (dfp2.classify() != 0 || !dfp2.rint().equals(dfp2) || dfp2.remainder(two).equals(zero)) {
                                if (dfp2.greaterThan(zero)) {
                                    return dfp.newInstance(dfp.newInstance(1, 1));
                                }
                                return dfp.newInstance(zero);
                            } else if (dfp2.greaterThan(zero)) {
                                return dfp.newInstance(dfp.newInstance(-1, 1));
                            } else {
                                return dfp.newInstance(zero.negate());
                            }
                        } else if (dfp2.greaterThan(zero)) {
                            return dfp;
                        } else {
                            return dfp.newInstance(zero);
                        }
                    } else if (!z || dfp2.rint().equals(dfp2)) {
                        if (!dfp2.lessThan(dfp.newInstance(100000000)) || !dfp2.greaterThan(dfp.newInstance(-100000000))) {
                            dfp3 = exp(log(dfp).multiply(dfp2));
                        } else {
                            Dfp rint = dfp2.rint();
                            int intValue = rint.intValue();
                            Dfp subtract = dfp2.subtract(rint);
                            if (subtract.unequal(zero)) {
                                Dfp multiply = subtract.multiply(log(dfp));
                                Dfp rint2 = multiply.divide(dfp.getField().getLn2()).rint();
                                dfp3 = splitPow(split(dfp), intValue).multiply(pow(two, rint2.intValue())).multiply(exp(multiply.subtract(rint2.multiply(dfp.getField().getLn2()))));
                            } else {
                                dfp3 = splitPow(split(dfp), intValue);
                            }
                        }
                        if (z && dfp2.rint().equals(dfp2) && !dfp2.remainder(two).equals(zero)) {
                            dfp3 = dfp3.negate();
                        }
                        return dfp.newInstance(dfp3);
                    } else {
                        dfp.getField().setIEEEFlagsBits(1);
                        return dfp.dotrap(1, POW_TRAP, dfp, dfp.newInstance(1, 3));
                    }
                } else if (dfp2.greaterThan(zero)) {
                    return dfp.newInstance(zero);
                } else {
                    return dfp.newInstance(Dfp.copysign(dfp2, one));
                }
            } else if (dfp2.greaterThan(zero)) {
                return dfp2;
            } else {
                return dfp.newInstance(zero);
            }
        } else if (Dfp.copysign(one, dfp).greaterThan(zero)) {
            if (dfp2.greaterThan(zero)) {
                return dfp.newInstance(zero);
            }
            return dfp.newInstance(dfp.newInstance(1, 1));
        } else if (dfp2.classify() != 0 || !dfp2.rint().equals(dfp2) || dfp2.remainder(two).equals(zero)) {
            if (dfp2.greaterThan(zero)) {
                return dfp.newInstance(zero);
            }
            return dfp.newInstance(dfp.newInstance(1, 1));
        } else if (dfp2.greaterThan(zero)) {
            return dfp.newInstance(zero.negate());
        } else {
            return dfp.newInstance(dfp.newInstance(-1, 1));
        }
    }

    protected static Dfp sinInternal(Dfp[] dfpArr) {
        Dfp add = dfpArr[0].add(dfpArr[1]);
        Dfp multiply = add.multiply(add);
        Dfp one = dfpArr[0].getOne();
        Dfp dfp = new Dfp(add);
        Dfp dfp2 = add;
        for (int i = 3; i < 90; i += 2) {
            add = add.multiply(multiply).negate();
            one = one.divide((i - 1) * i);
            dfp2 = dfp2.add(add.multiply(one));
            if (dfp2.equals(dfp)) {
                break;
            }
            dfp = new Dfp(dfp2);
        }
        return dfp2;
    }

    protected static Dfp cosInternal(Dfp[] dfpArr) {
        Dfp one = dfpArr[0].getOne();
        Dfp add = dfpArr[0].add(dfpArr[1]);
        Dfp multiply = add.multiply(add);
        Dfp dfp = new Dfp(one);
        Dfp dfp2 = one;
        Dfp dfp3 = dfp2;
        for (int i = 2; i < 90; i += 2) {
            one = one.multiply(multiply).negate();
            dfp2 = dfp2.divide((i - 1) * i);
            dfp3 = dfp3.add(one.multiply(dfp2));
            if (dfp3.equals(dfp)) {
                break;
            }
            dfp = new Dfp(dfp3);
        }
        return dfp3;
    }

    public static Dfp sin(Dfp dfp) {
        boolean z;
        Dfp dfp2;
        Dfp pi = dfp.getField().getPi();
        Dfp zero = dfp.getField().getZero();
        Dfp remainder = dfp.remainder(pi.multiply(2));
        if (remainder.lessThan(zero)) {
            remainder = remainder.negate();
            z = true;
        } else {
            z = false;
        }
        if (remainder.greaterThan(pi.divide(2))) {
            remainder = pi.subtract(remainder);
        }
        if (remainder.lessThan(pi.divide(4))) {
            Dfp[] dfpArr = {remainder, zero};
            dfp2 = sinInternal(split(remainder));
        } else {
            Dfp[] piSplit = dfp.getField().getPiSplit();
            dfp2 = cosInternal(new Dfp[]{piSplit[0].divide(2).subtract(remainder), piSplit[1].divide(2)});
        }
        if (z) {
            dfp2 = dfp2.negate();
        }
        return dfp.newInstance(dfp2);
    }

    public static Dfp cos(Dfp dfp) {
        boolean z;
        Dfp dfp2;
        Dfp pi = dfp.getField().getPi();
        Dfp zero = dfp.getField().getZero();
        Dfp remainder = dfp.remainder(pi.multiply(2));
        if (remainder.lessThan(zero)) {
            remainder = remainder.negate();
        }
        if (remainder.greaterThan(pi.divide(2))) {
            remainder = pi.subtract(remainder);
            z = true;
        } else {
            z = false;
        }
        if (remainder.lessThan(pi.divide(4))) {
            dfp2 = cosInternal(new Dfp[]{remainder, zero});
        } else {
            Dfp[] piSplit = dfp.getField().getPiSplit();
            dfp2 = sinInternal(new Dfp[]{piSplit[0].divide(2).subtract(remainder), piSplit[1].divide(2)});
        }
        if (z) {
            dfp2 = dfp2.negate();
        }
        return dfp.newInstance(dfp2);
    }

    public static Dfp tan(Dfp dfp) {
        return sin(dfp).divide(cos(dfp));
    }

    protected static Dfp atanInternal(Dfp dfp) {
        Dfp dfp2 = new Dfp(dfp);
        Dfp dfp3 = new Dfp(dfp2);
        Dfp dfp4 = new Dfp(dfp2);
        for (int i = 3; i < 90; i += 2) {
            dfp3 = dfp3.multiply(dfp).multiply(dfp).negate();
            dfp2 = dfp2.add(dfp3.divide(i));
            if (dfp2.equals(dfp4)) {
                break;
            }
            dfp4 = new Dfp(dfp2);
        }
        return dfp2;
    }

    public static Dfp atan(Dfp dfp) {
        boolean z;
        boolean z2;
        boolean z3;
        Dfp zero = dfp.getField().getZero();
        Dfp one = dfp.getField().getOne();
        Dfp[] sqr2Split = dfp.getField().getSqr2Split();
        Dfp[] piSplit = dfp.getField().getPiSplit();
        Dfp add = sqr2Split[0].subtract(one).add(sqr2Split[1]);
        Dfp dfp2 = new Dfp(dfp);
        if (dfp2.lessThan(zero)) {
            dfp2 = dfp2.negate();
            z = true;
        } else {
            z = false;
        }
        if (dfp2.greaterThan(one)) {
            dfp2 = one.divide(dfp2);
            z2 = true;
        } else {
            z2 = false;
        }
        if (dfp2.greaterThan(add)) {
            Dfp[] dfpArr = {sqr2Split[0].subtract(one), sqr2Split[1]};
            Dfp[] split = split(dfp2);
            Dfp[] splitMult = splitMult(split, dfpArr);
            splitMult[0] = splitMult[0].add(one);
            split[0] = split[0].subtract(dfpArr[0]);
            split[1] = split[1].subtract(dfpArr[1]);
            Dfp[] splitDiv = splitDiv(split, splitMult);
            dfp2 = splitDiv[0].add(splitDiv[1]);
            z3 = true;
        } else {
            z3 = false;
        }
        Dfp atanInternal = atanInternal(dfp2);
        if (z3) {
            atanInternal = atanInternal.add(piSplit[0].divide(8)).add(piSplit[1].divide(8));
        }
        if (z2) {
            atanInternal = piSplit[0].divide(2).subtract(atanInternal).add(piSplit[1].divide(2));
        }
        if (z) {
            atanInternal = atanInternal.negate();
        }
        return dfp.newInstance(atanInternal);
    }

    public static Dfp asin(Dfp dfp) {
        return atan(dfp.divide(dfp.getOne().subtract(dfp.multiply(dfp)).sqrt()));
    }

    public static Dfp acos(Dfp dfp) {
        boolean lessThan = dfp.lessThan(dfp.getZero());
        Dfp copysign = Dfp.copysign(dfp, dfp.getOne());
        Dfp atan = atan(copysign.getOne().subtract(copysign.multiply(copysign)).sqrt().divide(copysign));
        if (lessThan) {
            atan = copysign.getField().getPi().subtract(atan);
        }
        return copysign.newInstance(atan);
    }
}
