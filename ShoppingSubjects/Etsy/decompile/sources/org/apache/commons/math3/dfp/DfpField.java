package org.apache.commons.math3.dfp;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

public class DfpField implements Field<Dfp> {
    public static final int FLAG_DIV_ZERO = 2;
    public static final int FLAG_INEXACT = 16;
    public static final int FLAG_INVALID = 1;
    public static final int FLAG_OVERFLOW = 4;
    public static final int FLAG_UNDERFLOW = 8;
    private static String eString;
    private static String ln10String;
    private static String ln2String;
    private static String ln5String;
    private static String piString;
    private static String sqr2ReciprocalString;
    private static String sqr2String;
    private static String sqr3ReciprocalString;
    private static String sqr3String;
    private final Dfp e;
    private final Dfp[] eSplit;
    private int ieeeFlags;
    private final Dfp ln10;
    private final Dfp ln2;
    private final Dfp[] ln2Split;
    private final Dfp ln5;
    private final Dfp[] ln5Split;
    private final Dfp one;
    private final Dfp pi;
    private final Dfp[] piSplit;
    private RoundingMode rMode;
    private final int radixDigits;
    private final Dfp sqr2;
    private final Dfp sqr2Reciprocal;
    private final Dfp[] sqr2Split;
    private final Dfp sqr3;
    private final Dfp sqr3Reciprocal;
    private final Dfp two;
    private final Dfp zero;

    public enum RoundingMode {
        ROUND_DOWN,
        ROUND_UP,
        ROUND_HALF_UP,
        ROUND_HALF_DOWN,
        ROUND_HALF_EVEN,
        ROUND_HALF_ODD,
        ROUND_CEIL,
        ROUND_FLOOR
    }

    public DfpField(int i) {
        this(i, true);
    }

    private DfpField(int i, boolean z) {
        int i2 = 4;
        if (i >= 13) {
            i2 = (i + 3) / 4;
        }
        this.radixDigits = i2;
        this.rMode = RoundingMode.ROUND_HALF_EVEN;
        this.ieeeFlags = 0;
        this.zero = new Dfp(this, 0);
        this.one = new Dfp(this, 1);
        this.two = new Dfp(this, 2);
        if (z) {
            synchronized (DfpField.class) {
                computeStringConstants(i < 67 ? 200 : i * 3);
                this.sqr2 = new Dfp(this, sqr2String);
                this.sqr2Split = split(sqr2String);
                this.sqr2Reciprocal = new Dfp(this, sqr2ReciprocalString);
                this.sqr3 = new Dfp(this, sqr3String);
                this.sqr3Reciprocal = new Dfp(this, sqr3ReciprocalString);
                this.pi = new Dfp(this, piString);
                this.piSplit = split(piString);
                this.e = new Dfp(this, eString);
                this.eSplit = split(eString);
                this.ln2 = new Dfp(this, ln2String);
                this.ln2Split = split(ln2String);
                this.ln5 = new Dfp(this, ln5String);
                this.ln5Split = split(ln5String);
                this.ln10 = new Dfp(this, ln10String);
            }
            return;
        }
        this.sqr2 = null;
        this.sqr2Split = null;
        this.sqr2Reciprocal = null;
        this.sqr3 = null;
        this.sqr3Reciprocal = null;
        this.pi = null;
        this.piSplit = null;
        this.e = null;
        this.eSplit = null;
        this.ln2 = null;
        this.ln2Split = null;
        this.ln5 = null;
        this.ln5Split = null;
        this.ln10 = null;
    }

    public int getRadixDigits() {
        return this.radixDigits;
    }

    public void setRoundingMode(RoundingMode roundingMode) {
        this.rMode = roundingMode;
    }

    public RoundingMode getRoundingMode() {
        return this.rMode;
    }

    public int getIEEEFlags() {
        return this.ieeeFlags;
    }

    public void clearIEEEFlags() {
        this.ieeeFlags = 0;
    }

    public void setIEEEFlags(int i) {
        this.ieeeFlags = i & 31;
    }

    public void setIEEEFlagsBits(int i) {
        this.ieeeFlags = (i & 31) | this.ieeeFlags;
    }

    public Dfp newDfp() {
        return new Dfp(this);
    }

    public Dfp newDfp(byte b) {
        return new Dfp(this, b);
    }

    public Dfp newDfp(int i) {
        return new Dfp(this, i);
    }

    public Dfp newDfp(long j) {
        return new Dfp(this, j);
    }

    public Dfp newDfp(double d) {
        return new Dfp(this, d);
    }

    public Dfp newDfp(Dfp dfp) {
        return new Dfp(dfp);
    }

    public Dfp newDfp(String str) {
        return new Dfp(this, str);
    }

    public Dfp newDfp(byte b, byte b2) {
        return new Dfp(this, b, b2);
    }

    public Dfp getZero() {
        return this.zero;
    }

    public Dfp getOne() {
        return this.one;
    }

    public Class<? extends FieldElement<Dfp>> getRuntimeClass() {
        return Dfp.class;
    }

    public Dfp getTwo() {
        return this.two;
    }

    public Dfp getSqr2() {
        return this.sqr2;
    }

    public Dfp[] getSqr2Split() {
        return (Dfp[]) this.sqr2Split.clone();
    }

    public Dfp getSqr2Reciprocal() {
        return this.sqr2Reciprocal;
    }

    public Dfp getSqr3() {
        return this.sqr3;
    }

    public Dfp getSqr3Reciprocal() {
        return this.sqr3Reciprocal;
    }

    public Dfp getPi() {
        return this.pi;
    }

    public Dfp[] getPiSplit() {
        return (Dfp[]) this.piSplit.clone();
    }

    public Dfp getE() {
        return this.e;
    }

    public Dfp[] getESplit() {
        return (Dfp[]) this.eSplit.clone();
    }

    public Dfp getLn2() {
        return this.ln2;
    }

    public Dfp[] getLn2Split() {
        return (Dfp[]) this.ln2Split.clone();
    }

    public Dfp getLn5() {
        return this.ln5;
    }

    public Dfp[] getLn5Split() {
        return (Dfp[]) this.ln5Split.clone();
    }

    public Dfp getLn10() {
        return this.ln10;
    }

    private Dfp[] split(String str) {
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
            if (i2 == (this.radixDigits / 2) * 4) {
                break;
            }
            if (cArr[i] >= '0' && cArr[i] <= '9' && !z) {
                i2++;
            }
            i++;
        }
        dfpArr[0] = new Dfp(this, new String(cArr, 0, i));
        for (int i3 = 0; i3 < cArr.length; i3++) {
            cArr[i3] = str.charAt(i3);
            if (cArr[i3] >= '0' && cArr[i3] <= '9' && i3 < i) {
                cArr[i3] = '0';
            }
        }
        dfpArr[1] = new Dfp(this, new String(cArr));
        return dfpArr;
    }

    private static void computeStringConstants(int i) {
        if (sqr2String == null || sqr2String.length() < i - 3) {
            DfpField dfpField = new DfpField(i, false);
            Dfp dfp = new Dfp(dfpField, 1);
            Dfp dfp2 = new Dfp(dfpField, 2);
            Dfp dfp3 = new Dfp(dfpField, 3);
            Dfp sqrt = dfp2.sqrt();
            sqr2String = sqrt.toString();
            sqr2ReciprocalString = dfp.divide(sqrt).toString();
            Dfp sqrt2 = dfp3.sqrt();
            sqr3String = sqrt2.toString();
            sqr3ReciprocalString = dfp.divide(sqrt2).toString();
            piString = computePi(dfp, dfp2, dfp3).toString();
            eString = computeExp(dfp, dfp).toString();
            ln2String = computeLn(dfp2, dfp, dfp2).toString();
            ln5String = computeLn(new Dfp(dfpField, 5), dfp, dfp2).toString();
            ln10String = computeLn(new Dfp(dfpField, 10), dfp, dfp2).toString();
        }
    }

    private static Dfp computePi(Dfp dfp, Dfp dfp2, Dfp dfp3) {
        Dfp sqrt = dfp2.sqrt();
        Dfp subtract = sqrt.subtract(dfp);
        Dfp add = dfp2.add(dfp2);
        Dfp multiply = dfp2.multiply(dfp3.subtract(dfp2.multiply(sqrt)));
        int i = 1;
        while (i < 20) {
            Dfp multiply2 = subtract.multiply(subtract);
            Dfp sqrt2 = dfp.subtract(multiply2.multiply(multiply2)).sqrt().sqrt();
            Dfp divide = dfp.subtract(sqrt2).divide(dfp.add(sqrt2));
            dfp2 = dfp2.multiply(add);
            Dfp add2 = dfp.add(divide);
            Dfp multiply3 = add2.multiply(add2);
            multiply = multiply.multiply(multiply3.multiply(multiply3)).subtract(dfp2.multiply(divide).multiply(dfp.add(divide).add(divide.multiply(divide))));
            if (divide.equals(subtract)) {
                break;
            }
            i++;
            subtract = divide;
        }
        return dfp.divide(multiply);
    }

    public static Dfp computeExp(Dfp dfp, Dfp dfp2) {
        Dfp dfp3 = new Dfp(dfp2);
        Dfp dfp4 = new Dfp(dfp2);
        Dfp dfp5 = new Dfp(dfp2);
        Dfp dfp6 = new Dfp(dfp2);
        Dfp dfp7 = new Dfp(dfp2);
        for (int i = 0; i < 10000; i++) {
            dfp7 = dfp7.multiply(dfp);
            dfp3 = dfp3.add(dfp7.divide(dfp5));
            dfp6 = dfp6.add(dfp2);
            dfp5 = dfp5.multiply(dfp6);
            if (dfp3.equals(dfp4)) {
                break;
            }
            dfp4 = new Dfp(dfp3);
        }
        return dfp3;
    }

    public static Dfp computeLn(Dfp dfp, Dfp dfp2, Dfp dfp3) {
        Dfp divide = dfp.add(new Dfp(dfp.getField(), -1)).divide(dfp.add(dfp2));
        Dfp dfp4 = new Dfp(divide);
        Dfp dfp5 = new Dfp(divide);
        Dfp dfp6 = new Dfp(dfp4);
        int i = 1;
        for (int i2 = 0; i2 < 10000; i2++) {
            dfp5 = dfp5.multiply(divide).multiply(divide);
            i += 2;
            dfp4 = dfp4.add(dfp5.divide(i));
            if (dfp4.equals(dfp6)) {
                break;
            }
            dfp6 = new Dfp(dfp4);
        }
        return dfp4.multiply(dfp3);
    }
}
