package org.apache.commons.math3.dfp;

import java.util.Arrays;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.dfp.DfpField.RoundingMode;

public class Dfp implements FieldElement<Dfp> {
    private static final String ADD_TRAP = "add";
    private static final String ALIGN_TRAP = "align";
    private static final String DIVIDE_TRAP = "divide";
    public static final int ERR_SCALE = 32760;
    public static final byte FINITE = 0;
    private static final String GREATER_THAN_TRAP = "greaterThan";
    public static final byte INFINITE = 1;
    private static final String LESS_THAN_TRAP = "lessThan";
    public static final int MAX_EXP = 32768;
    public static final int MIN_EXP = -32767;
    private static final String MULTIPLY_TRAP = "multiply";
    private static final String NAN_STRING = "NaN";
    private static final String NEG_INFINITY_STRING = "-Infinity";
    private static final String NEW_INSTANCE_TRAP = "newInstance";
    private static final String NEXT_AFTER_TRAP = "nextAfter";
    private static final String POS_INFINITY_STRING = "Infinity";
    public static final byte QNAN = 3;
    public static final int RADIX = 10000;
    public static final byte SNAN = 2;
    private static final String SQRT_TRAP = "sqrt";
    private static final String TRUNC_TRAP = "trunc";
    protected int exp;
    private final DfpField field;
    protected int[] mant;
    protected byte nans;
    protected byte sign;

    /* access modifiers changed from: protected */
    public Dfp trap(int i, String str, Dfp dfp, Dfp dfp2, Dfp dfp3) {
        return dfp2;
    }

    protected Dfp(DfpField dfpField) {
        this.mant = new int[dfpField.getRadixDigits()];
        this.sign = 1;
        this.exp = 0;
        this.nans = 0;
        this.field = dfpField;
    }

    protected Dfp(DfpField dfpField, byte b) {
        this(dfpField, (long) b);
    }

    protected Dfp(DfpField dfpField, int i) {
        this(dfpField, (long) i);
    }

    protected Dfp(DfpField dfpField, long j) {
        boolean z;
        this.mant = new int[dfpField.getRadixDigits()];
        this.nans = 0;
        this.field = dfpField;
        if (j == Long.MIN_VALUE) {
            z = true;
            j++;
        } else {
            z = false;
        }
        if (j < 0) {
            this.sign = -1;
            j = -j;
        } else {
            this.sign = 1;
        }
        this.exp = 0;
        while (j != 0) {
            System.arraycopy(this.mant, this.mant.length - this.exp, this.mant, (this.mant.length - 1) - this.exp, this.exp);
            this.mant[this.mant.length - 1] = (int) (j % 10000);
            j /= 10000;
            this.exp++;
        }
        if (z) {
            for (int i = 0; i < this.mant.length - 1; i++) {
                if (this.mant[i] != 0) {
                    int[] iArr = this.mant;
                    iArr[i] = iArr[i] + 1;
                    return;
                }
            }
        }
    }

    protected Dfp(DfpField dfpField, double d) {
        DfpField dfpField2 = dfpField;
        this.mant = new int[dfpField.getRadixDigits()];
        this.sign = 1;
        this.exp = 0;
        this.nans = 0;
        this.field = dfpField2;
        long doubleToLongBits = Double.doubleToLongBits(d);
        long j = doubleToLongBits & 4503599627370495L;
        int i = ((int) ((doubleToLongBits & 9218868437227405312L) >> 52)) - 1023;
        if (i == -1023) {
            if (d == 0.0d) {
                if ((doubleToLongBits & Long.MIN_VALUE) != 0) {
                    this.sign = -1;
                }
                return;
            }
            i++;
            while ((j & 4503599627370496L) == 0) {
                i--;
                j <<= 1;
            }
            j &= 4503599627370495L;
        }
        if (i == 1024) {
            if (d != d) {
                this.sign = 1;
                this.nans = 3;
            } else if (d < 0.0d) {
                this.sign = -1;
                this.nans = 1;
            } else {
                this.sign = 1;
                this.nans = 1;
            }
            return;
        }
        Dfp multiply = new Dfp(dfpField2, j).divide(new Dfp(dfpField2, 4503599627370496L)).add(dfpField.getOne()).multiply(DfpMath.pow(dfpField.getTwo(), i));
        if ((doubleToLongBits & Long.MIN_VALUE) != 0) {
            multiply = multiply.negate();
        }
        System.arraycopy(multiply.mant, 0, this.mant, 0, this.mant.length);
        this.sign = multiply.sign;
        this.exp = multiply.exp;
        this.nans = multiply.nans;
    }

    public Dfp(Dfp dfp) {
        this.mant = (int[]) dfp.mant.clone();
        this.sign = dfp.sign;
        this.exp = dfp.exp;
        this.nans = dfp.nans;
        this.field = dfp.field;
    }

    protected Dfp(DfpField dfpField, String str) {
        int i;
        String str2;
        int i2;
        String str3 = str;
        this.mant = new int[dfpField.getRadixDigits()];
        int i3 = 1;
        this.sign = 1;
        this.exp = 0;
        this.nans = 0;
        this.field = dfpField;
        char[] cArr = new char[((getRadixDigits() * 4) + 8)];
        if (str3.equals(POS_INFINITY_STRING)) {
            this.sign = 1;
            this.nans = 1;
        } else if (str3.equals(NEG_INFINITY_STRING)) {
            this.sign = -1;
            this.nans = 1;
        } else if (str3.equals(NAN_STRING)) {
            this.sign = 1;
            this.nans = 3;
        } else {
            int indexOf = str3.indexOf("e");
            if (indexOf == -1) {
                indexOf = str3.indexOf("E");
            }
            if (indexOf != -1) {
                str2 = str3.substring(0, indexOf);
                String substring = str3.substring(indexOf + 1);
                boolean z = false;
                i = 0;
                for (int i4 = 0; i4 < substring.length(); i4++) {
                    if (substring.charAt(i4) == '-') {
                        z = true;
                    } else if (substring.charAt(i4) >= '0' && substring.charAt(i4) <= '9') {
                        i = ((i * 10) + substring.charAt(i4)) - 48;
                    }
                }
                if (z) {
                    i = -i;
                }
            } else {
                str2 = str3;
                i = 0;
            }
            if (str2.indexOf("-") != -1) {
                this.sign = -1;
            }
            int i5 = 0;
            boolean z2 = false;
            int i6 = 0;
            while (true) {
                if (str2.charAt(i5) >= '1' && str2.charAt(i5) <= '9') {
                    break;
                }
                if (z2 && str2.charAt(i5) == '0') {
                    i6--;
                }
                if (str2.charAt(i5) == '.') {
                    z2 = true;
                }
                i5++;
                if (i5 == str2.length()) {
                    break;
                }
            }
            cArr[0] = '0';
            cArr[1] = '0';
            cArr[2] = '0';
            cArr[3] = '0';
            boolean z3 = z2;
            int i7 = i6;
            int i8 = 0;
            int i9 = 4;
            while (i2 != str2.length() && i9 != (this.mant.length * 4) + 4 + i3) {
                if (str2.charAt(i2) == '.') {
                    i2++;
                    i7 = i8;
                    i3 = 1;
                    z3 = true;
                } else {
                    if (str2.charAt(i2) < '0' || str2.charAt(i2) > '9') {
                        i2++;
                    } else {
                        cArr[i9] = str2.charAt(i2);
                        i9++;
                        i2++;
                        i8++;
                    }
                    i3 = 1;
                }
            }
            if (z3 && i9 != 4) {
                while (true) {
                    i9--;
                    if (i9 == 4 || cArr[i9] != '0') {
                        break;
                    }
                    i8--;
                }
            }
            if (z3 && i8 == 0) {
                i7 = 0;
            }
            if (!z3) {
                i7 = i9 - 4;
            }
            int i10 = (i8 - i3) + 4;
            while (i10 > 4 && cArr[i10] == '0') {
                i10--;
            }
            int i11 = ((400 - i7) - (i % 4)) % 4;
            int i12 = 4 - i11;
            int i13 = i7 + i11;
            while (i10 - i12 < this.mant.length * 4) {
                for (int i14 = 0; i14 < 4; i14++) {
                    i10++;
                    cArr[i10] = '0';
                }
            }
            for (int length = this.mant.length - i3; length >= 0; length--) {
                this.mant[length] = ((cArr[i12] - '0') * 1000) + ((cArr[i12 + 1] - '0') * 100) + ((cArr[i12 + 2] - '0') * 10) + (cArr[i12 + 3] - '0');
                i12 += 4;
            }
            this.exp = (i13 + i) / 4;
            if (i12 < cArr.length) {
                round((cArr[i12] - '0') * 1000);
            }
        }
    }

    protected Dfp(DfpField dfpField, byte b, byte b2) {
        this.field = dfpField;
        this.mant = new int[dfpField.getRadixDigits()];
        this.sign = b;
        this.exp = 0;
        this.nans = b2;
    }

    public Dfp newInstance() {
        return new Dfp(getField());
    }

    public Dfp newInstance(byte b) {
        return new Dfp(getField(), b);
    }

    public Dfp newInstance(int i) {
        return new Dfp(getField(), i);
    }

    public Dfp newInstance(long j) {
        return new Dfp(getField(), j);
    }

    public Dfp newInstance(double d) {
        return new Dfp(getField(), d);
    }

    public Dfp newInstance(Dfp dfp) {
        if (this.field.getRadixDigits() == dfp.field.getRadixDigits()) {
            return new Dfp(dfp);
        }
        this.field.setIEEEFlagsBits(1);
        Dfp newInstance = newInstance(getZero());
        newInstance.nans = 3;
        return dotrap(1, NEW_INSTANCE_TRAP, dfp, newInstance);
    }

    public Dfp newInstance(String str) {
        return new Dfp(this.field, str);
    }

    public Dfp newInstance(byte b, byte b2) {
        return this.field.newDfp(b, b2);
    }

    public DfpField getField() {
        return this.field;
    }

    public int getRadixDigits() {
        return this.field.getRadixDigits();
    }

    public Dfp getZero() {
        return this.field.getZero();
    }

    public Dfp getOne() {
        return this.field.getOne();
    }

    public Dfp getTwo() {
        return this.field.getTwo();
    }

    /* access modifiers changed from: protected */
    public void shiftLeft() {
        for (int length = this.mant.length - 1; length > 0; length--) {
            this.mant[length] = this.mant[length - 1];
        }
        this.mant[0] = 0;
        this.exp--;
    }

    /* access modifiers changed from: protected */
    public void shiftRight() {
        int i = 0;
        while (i < this.mant.length - 1) {
            int i2 = i + 1;
            this.mant[i] = this.mant[i2];
            i = i2;
        }
        this.mant[this.mant.length - 1] = 0;
        this.exp++;
    }

    /* access modifiers changed from: protected */
    public int align(int i) {
        int i2 = this.exp - i;
        int i3 = i2 < 0 ? -i2 : i2;
        if (i2 == 0) {
            return 0;
        }
        if (i3 > this.mant.length + 1) {
            Arrays.fill(this.mant, 0);
            this.exp = i;
            this.field.setIEEEFlagsBits(16);
            dotrap(16, ALIGN_TRAP, this, this);
            return 0;
        }
        boolean z = false;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i2 < 0) {
                if (i4 != 0) {
                    z = true;
                }
                i4 = this.mant[0];
                shiftRight();
            } else {
                shiftLeft();
            }
        }
        if (z) {
            this.field.setIEEEFlagsBits(16);
            dotrap(16, ALIGN_TRAP, this, this);
        }
        return i4;
    }

    public boolean lessThan(Dfp dfp) {
        boolean z = false;
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            dotrap(1, LESS_THAN_TRAP, dfp, newInstance);
            return false;
        } else if (isNaN() || dfp.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, dfp, newInstance(getZero()));
            return false;
        } else {
            if (compare(this, dfp) < 0) {
                z = true;
            }
            return z;
        }
    }

    public boolean greaterThan(Dfp dfp) {
        boolean z = false;
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            dotrap(1, GREATER_THAN_TRAP, dfp, newInstance);
            return false;
        } else if (isNaN() || dfp.isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, GREATER_THAN_TRAP, dfp, newInstance(getZero()));
            return false;
        } else {
            if (compare(this, dfp) > 0) {
                z = true;
            }
            return z;
        }
    }

    public boolean negativeOrNull() {
        boolean z = false;
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign < 0 || (this.mant[this.mant.length - 1] == 0 && !isInfinite())) {
            z = true;
        }
        return z;
    }

    public boolean strictlyNegative() {
        boolean z = false;
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign < 0 && (this.mant[this.mant.length - 1] != 0 || isInfinite())) {
            z = true;
        }
        return z;
    }

    public boolean positiveOrNull() {
        boolean z = false;
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign > 0 || (this.mant[this.mant.length - 1] == 0 && !isInfinite())) {
            z = true;
        }
        return z;
    }

    public boolean strictlyPositive() {
        boolean z = false;
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign > 0 && (this.mant[this.mant.length - 1] != 0 || isInfinite())) {
            z = true;
        }
        return z;
    }

    public Dfp abs() {
        Dfp newInstance = newInstance(this);
        newInstance.sign = 1;
        return newInstance;
    }

    public boolean isInfinite() {
        return this.nans == 1;
    }

    public boolean isNaN() {
        return this.nans == 3 || this.nans == 2;
    }

    public boolean isZero() {
        boolean z = false;
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.mant[this.mant.length - 1] == 0 && !isInfinite()) {
            z = true;
        }
        return z;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof Dfp)) {
            return false;
        }
        Dfp dfp = (Dfp) obj;
        if (isNaN() || dfp.isNaN() || this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            return false;
        }
        if (compare(this, dfp) == 0) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return 17 + (this.sign << 8) + (this.nans << 16) + this.exp + Arrays.hashCode(this.mant);
    }

    public boolean unequal(Dfp dfp) {
        boolean z = false;
        if (isNaN() || dfp.isNaN() || this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            return false;
        }
        if (greaterThan(dfp) || lessThan(dfp)) {
            z = true;
        }
        return z;
    }

    private static int compare(Dfp dfp, Dfp dfp2) {
        if (dfp.mant[dfp.mant.length - 1] == 0 && dfp2.mant[dfp2.mant.length - 1] == 0 && dfp.nans == 0 && dfp2.nans == 0) {
            return 0;
        }
        if (dfp.sign != dfp2.sign) {
            if (dfp.sign == -1) {
                return -1;
            }
            return 1;
        } else if (dfp.nans == 1 && dfp2.nans == 0) {
            return dfp.sign;
        } else {
            if (dfp.nans == 0 && dfp2.nans == 1) {
                return -dfp2.sign;
            }
            if (dfp.nans == 1 && dfp2.nans == 1) {
                return 0;
            }
            if (!(dfp2.mant[dfp2.mant.length - 1] == 0 || dfp.mant[dfp2.mant.length - 1] == 0)) {
                if (dfp.exp < dfp2.exp) {
                    return -dfp.sign;
                }
                if (dfp.exp > dfp2.exp) {
                    return dfp.sign;
                }
            }
            for (int length = dfp.mant.length - 1; length >= 0; length--) {
                if (dfp.mant[length] > dfp2.mant[length]) {
                    return dfp.sign;
                }
                if (dfp.mant[length] < dfp2.mant[length]) {
                    return -dfp.sign;
                }
            }
            return 0;
        }
    }

    public Dfp rint() {
        return trunc(RoundingMode.ROUND_HALF_EVEN);
    }

    public Dfp floor() {
        return trunc(RoundingMode.ROUND_FLOOR);
    }

    public Dfp ceil() {
        return trunc(RoundingMode.ROUND_CEIL);
    }

    public Dfp remainder(Dfp dfp) {
        Dfp subtract = subtract(divide(dfp).rint().multiply(dfp));
        if (subtract.mant[this.mant.length - 1] == 0) {
            subtract.sign = this.sign;
        }
        return subtract;
    }

    /* access modifiers changed from: protected */
    public Dfp trunc(RoundingMode roundingMode) {
        if (isNaN()) {
            return newInstance(this);
        }
        if (this.nans == 1) {
            return newInstance(this);
        }
        if (this.mant[this.mant.length - 1] == 0) {
            return newInstance(this);
        }
        if (this.exp < 0) {
            this.field.setIEEEFlagsBits(16);
            return dotrap(16, TRUNC_TRAP, this, newInstance(getZero()));
        } else if (this.exp >= this.mant.length) {
            return newInstance(this);
        } else {
            Dfp newInstance = newInstance(this);
            boolean z = false;
            for (int i = 0; i < this.mant.length - newInstance.exp; i++) {
                z |= newInstance.mant[i] != 0;
                newInstance.mant[i] = 0;
            }
            if (!z) {
                return newInstance;
            }
            switch (roundingMode) {
                case ROUND_FLOOR:
                    if (newInstance.sign == -1) {
                        newInstance = newInstance.add(newInstance(-1));
                        break;
                    }
                    break;
                case ROUND_CEIL:
                    if (newInstance.sign == 1) {
                        newInstance = newInstance.add(getOne());
                        break;
                    }
                    break;
                default:
                    Dfp newInstance2 = newInstance("0.5");
                    Dfp subtract = subtract(newInstance);
                    subtract.sign = 1;
                    if (subtract.greaterThan(newInstance2)) {
                        subtract = newInstance(getOne());
                        subtract.sign = this.sign;
                        newInstance = newInstance.add(subtract);
                    }
                    if (subtract.equals(newInstance2) && newInstance.exp > 0 && (newInstance.mant[this.mant.length - newInstance.exp] & 1) != 0) {
                        Dfp newInstance3 = newInstance(getOne());
                        newInstance3.sign = this.sign;
                        newInstance = newInstance.add(newInstance3);
                        break;
                    }
            }
            this.field.setIEEEFlagsBits(16);
            return dotrap(16, TRUNC_TRAP, this, newInstance);
        }
    }

    public int intValue() {
        Dfp rint = rint();
        if (rint.greaterThan(newInstance(Integer.MAX_VALUE))) {
            return Integer.MAX_VALUE;
        }
        if (rint.lessThan(newInstance(Integer.MIN_VALUE))) {
            return Integer.MIN_VALUE;
        }
        int i = 0;
        int length = this.mant.length;
        while (true) {
            length--;
            if (length < this.mant.length - rint.exp) {
                break;
            }
            i = (i * 10000) + rint.mant[length];
        }
        if (rint.sign == -1) {
            i = -i;
        }
        return i;
    }

    public int log10K() {
        return this.exp - 1;
    }

    public Dfp power10K(int i) {
        Dfp newInstance = newInstance(getOne());
        newInstance.exp = i + 1;
        return newInstance;
    }

    public int log10() {
        if (this.mant[this.mant.length - 1] > 1000) {
            return (this.exp * 4) - 1;
        }
        if (this.mant[this.mant.length - 1] > 100) {
            return (this.exp * 4) - 2;
        }
        if (this.mant[this.mant.length - 1] > 10) {
            return (this.exp * 4) - 3;
        }
        return (this.exp * 4) - 4;
    }

    public Dfp power10(int i) {
        Dfp newInstance = newInstance(getOne());
        if (i >= 0) {
            newInstance.exp = (i / 4) + 1;
        } else {
            newInstance.exp = (i + 1) / 4;
        }
        switch (((i % 4) + 4) % 4) {
            case 0:
                return newInstance;
            case 1:
                return newInstance.multiply(10);
            case 2:
                return newInstance.multiply(100);
            default:
                return newInstance.multiply(1000);
        }
    }

    /* access modifiers changed from: protected */
    public int complement(int i) {
        int i2 = 10000 - i;
        for (int i3 = 0; i3 < this.mant.length; i3++) {
            this.mant[i3] = (10000 - this.mant[i3]) - 1;
        }
        int i4 = i2 / 10000;
        int i5 = i2 - (i4 * 10000);
        for (int i6 = 0; i6 < this.mant.length; i6++) {
            int i7 = this.mant[i6] + i4;
            i4 = i7 / 10000;
            this.mant[i6] = i7 - (i4 * 10000);
        }
        return i5;
    }

    public Dfp add(Dfp dfp) {
        int i;
        int i2;
        Dfp dfp2 = dfp;
        if (this.field.getRadixDigits() != dfp2.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            return dotrap(1, ADD_TRAP, dfp2, newInstance);
        }
        if (!(this.nans == 0 && dfp2.nans == 0)) {
            if (isNaN()) {
                return this;
            }
            if (dfp.isNaN()) {
                return dfp2;
            }
            if (this.nans == 1 && dfp2.nans == 0) {
                return this;
            }
            if (dfp2.nans == 1 && this.nans == 0) {
                return dfp2;
            }
            if (dfp2.nans == 1 && this.nans == 1 && this.sign == dfp2.sign) {
                return dfp2;
            }
            if (dfp2.nans == 1 && this.nans == 1 && this.sign != dfp2.sign) {
                this.field.setIEEEFlagsBits(1);
                Dfp newInstance2 = newInstance(getZero());
                newInstance2.nans = 3;
                return dotrap(1, ADD_TRAP, dfp2, newInstance2);
            }
        }
        Dfp newInstance3 = newInstance(this);
        Dfp newInstance4 = newInstance(dfp);
        Dfp newInstance5 = newInstance(getZero());
        byte b = newInstance3.sign;
        byte b2 = newInstance4.sign;
        newInstance3.sign = 1;
        newInstance4.sign = 1;
        byte b3 = compare(newInstance3, newInstance4) > 0 ? b : b2;
        if (newInstance4.mant[this.mant.length - 1] == 0) {
            newInstance4.exp = newInstance3.exp;
        }
        if (newInstance3.mant[this.mant.length - 1] == 0) {
            newInstance3.exp = newInstance4.exp;
        }
        if (newInstance3.exp < newInstance4.exp) {
            i = newInstance3.align(newInstance4.exp);
            i2 = 0;
        } else {
            i2 = newInstance4.align(newInstance3.exp);
            i = 0;
        }
        if (b != b2) {
            if (b == b3) {
                i2 = newInstance4.complement(i2);
            } else {
                i = newInstance3.complement(i);
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.mant.length; i4++) {
            int i5 = newInstance3.mant[i4] + newInstance4.mant[i4] + i3;
            i3 = i5 / 10000;
            newInstance5.mant[i4] = i5 - (i3 * 10000);
        }
        newInstance5.exp = newInstance3.exp;
        newInstance5.sign = b3;
        if (i3 != 0 && b == b2) {
            int i6 = newInstance5.mant[0];
            newInstance5.shiftRight();
            newInstance5.mant[this.mant.length - 1] = i3;
            int round = newInstance5.round(i6);
            if (round != 0) {
                newInstance5 = dotrap(round, ADD_TRAP, dfp2, newInstance5);
            }
        }
        for (int i7 = 0; i7 < this.mant.length && newInstance5.mant[this.mant.length - 1] == 0; i7++) {
            newInstance5.shiftLeft();
            if (i7 == 0) {
                newInstance5.mant[0] = i + i2;
                i2 = 0;
                i = 0;
            }
        }
        if (newInstance5.mant[this.mant.length - 1] == 0) {
            newInstance5.exp = 0;
            if (b != b2) {
                newInstance5.sign = 1;
            }
        }
        int round2 = newInstance5.round(i + i2);
        if (round2 != 0) {
            newInstance5 = dotrap(round2, ADD_TRAP, dfp2, newInstance5);
        }
        return newInstance5;
    }

    public Dfp negate() {
        Dfp newInstance = newInstance(this);
        newInstance.sign = (byte) (-newInstance.sign);
        return newInstance;
    }

    public Dfp subtract(Dfp dfp) {
        return add(dfp.negate());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        if (r8 > 5000) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (r8 >= 5000) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        if (r8 != 0) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        if ((r7.mant[0] & 1) != 1) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        if (r8 != 0) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        if (r0 == false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        r0 = 0;
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
        if (r0 >= r7.mant.length) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0051, code lost:
        r4 = r7.mant[r0] + r1;
        r1 = r4 / 10000;
        r7.mant[r0] = r4 - (r1 * 10000);
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0062, code lost:
        if (r1 == 0) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0064, code lost:
        shiftRight();
        r7.mant[r7.mant.length - 1] = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0073, code lost:
        if (r7.exp >= -32767) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0075, code lost:
        r7.field.setIEEEFlagsBits(8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007c, code lost:
        return 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0082, code lost:
        if (r7.exp <= 32768) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0084, code lost:
        r7.field.setIEEEFlagsBits(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008a, code lost:
        return 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008b, code lost:
        if (r8 == 0) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008d, code lost:
        r7.field.setIEEEFlagsBits(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0094, code lost:
        return 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0095, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
        if (r8 != 0) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        if ((r7.mant[0] & 1) != 0) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0028, code lost:
        r0 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int round(int r8) {
        /*
            r7 = this;
            int[] r0 = org.apache.commons.math3.dfp.Dfp.AnonymousClass1.$SwitchMap$org$apache$commons$math3$dfp$DfpField$RoundingMode
            org.apache.commons.math3.dfp.DfpField r1 = r7.field
            org.apache.commons.math3.dfp.DfpField$RoundingMode r1 = r1.getRoundingMode()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 5000(0x1388, float:7.006E-42)
            r2 = 0
            r3 = 1
            switch(r0) {
                case 2: goto L_0x0041;
                case 3: goto L_0x0035;
                case 4: goto L_0x0033;
                case 5: goto L_0x0030;
                case 6: goto L_0x002d;
                case 7: goto L_0x002a;
                case 8: goto L_0x001d;
                default: goto L_0x0015;
            }
        L_0x0015:
            byte r0 = r7.sign
            r1 = -1
            if (r0 != r1) goto L_0x0033
            if (r8 == 0) goto L_0x0033
            goto L_0x0028
        L_0x001d:
            if (r8 > r1) goto L_0x0028
            if (r8 != r1) goto L_0x0033
            int[] r0 = r7.mant
            r0 = r0[r2]
            r0 = r0 & r3
            if (r0 != 0) goto L_0x0033
        L_0x0028:
            r0 = r3
            goto L_0x0048
        L_0x002a:
            if (r8 <= r1) goto L_0x0033
            goto L_0x0028
        L_0x002d:
            if (r8 < r1) goto L_0x0033
            goto L_0x0028
        L_0x0030:
            if (r8 == 0) goto L_0x0033
            goto L_0x0028
        L_0x0033:
            r0 = r2
            goto L_0x0048
        L_0x0035:
            if (r8 > r1) goto L_0x0028
            if (r8 != r1) goto L_0x0033
            int[] r0 = r7.mant
            r0 = r0[r2]
            r0 = r0 & r3
            if (r0 != r3) goto L_0x0033
            goto L_0x0028
        L_0x0041:
            byte r0 = r7.sign
            if (r0 != r3) goto L_0x0033
            if (r8 == 0) goto L_0x0033
            goto L_0x0028
        L_0x0048:
            if (r0 == 0) goto L_0x006f
            r0 = r2
            r1 = r3
        L_0x004c:
            int[] r4 = r7.mant
            int r4 = r4.length
            if (r0 >= r4) goto L_0x0062
            int[] r4 = r7.mant
            r4 = r4[r0]
            int r4 = r4 + r1
            int r1 = r4 / 10000
            int[] r5 = r7.mant
            int r6 = r1 * 10000
            int r4 = r4 - r6
            r5[r0] = r4
            int r0 = r0 + 1
            goto L_0x004c
        L_0x0062:
            if (r1 == 0) goto L_0x006f
            r7.shiftRight()
            int[] r0 = r7.mant
            int[] r4 = r7.mant
            int r4 = r4.length
            int r4 = r4 - r3
            r0[r4] = r1
        L_0x006f:
            int r0 = r7.exp
            r1 = -32767(0xffffffffffff8001, float:NaN)
            if (r0 >= r1) goto L_0x007d
            org.apache.commons.math3.dfp.DfpField r8 = r7.field
            r0 = 8
            r8.setIEEEFlagsBits(r0)
            return r0
        L_0x007d:
            int r0 = r7.exp
            r1 = 32768(0x8000, float:4.5918E-41)
            if (r0 <= r1) goto L_0x008b
            org.apache.commons.math3.dfp.DfpField r8 = r7.field
            r0 = 4
            r8.setIEEEFlagsBits(r0)
            return r0
        L_0x008b:
            if (r8 == 0) goto L_0x0095
            org.apache.commons.math3.dfp.DfpField r8 = r7.field
            r0 = 16
            r8.setIEEEFlagsBits(r0)
            return r0
        L_0x0095:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.Dfp.round(int):int");
    }

    public Dfp multiply(Dfp dfp) {
        int i;
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            return dotrap(1, MULTIPLY_TRAP, dfp, newInstance);
        }
        Dfp newInstance2 = newInstance(getZero());
        if (!(this.nans == 0 && dfp.nans == 0)) {
            if (isNaN()) {
                return this;
            }
            if (dfp.isNaN()) {
                return dfp;
            }
            if (this.nans == 1 && dfp.nans == 0 && dfp.mant[this.mant.length - 1] != 0) {
                Dfp newInstance3 = newInstance(this);
                newInstance3.sign = (byte) (this.sign * dfp.sign);
                return newInstance3;
            } else if (dfp.nans == 1 && this.nans == 0 && this.mant[this.mant.length - 1] != 0) {
                Dfp newInstance4 = newInstance(dfp);
                newInstance4.sign = (byte) (this.sign * dfp.sign);
                return newInstance4;
            } else if (dfp.nans == 1 && this.nans == 1) {
                Dfp newInstance5 = newInstance(this);
                newInstance5.sign = (byte) (this.sign * dfp.sign);
                return newInstance5;
            } else if ((dfp.nans == 1 && this.nans == 0 && this.mant[this.mant.length - 1] == 0) || (this.nans == 1 && dfp.nans == 0 && dfp.mant[this.mant.length - 1] == 0)) {
                this.field.setIEEEFlagsBits(1);
                Dfp newInstance6 = newInstance(getZero());
                newInstance6.nans = 3;
                return dotrap(1, MULTIPLY_TRAP, dfp, newInstance6);
            }
        }
        int[] iArr = new int[(this.mant.length * 2)];
        for (int i2 = 0; i2 < this.mant.length; i2++) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.mant.length; i4++) {
                int i5 = i2 + i4;
                int i6 = (this.mant[i2] * dfp.mant[i4]) + iArr[i5] + i3;
                i3 = i6 / 10000;
                iArr[i5] = i6 - (i3 * 10000);
            }
            iArr[this.mant.length + i2] = i3;
        }
        int length = (this.mant.length * 2) - 1;
        int length2 = (this.mant.length * 2) - 1;
        while (true) {
            if (length2 < 0) {
                break;
            } else if (iArr[length2] != 0) {
                length = length2;
                break;
            } else {
                length2--;
            }
        }
        for (int i7 = 0; i7 < this.mant.length; i7++) {
            newInstance2.mant[(this.mant.length - i7) - 1] = iArr[length - i7];
        }
        newInstance2.exp = (((this.exp + dfp.exp) + length) - (2 * this.mant.length)) + 1;
        newInstance2.sign = (byte) (this.sign == dfp.sign ? 1 : -1);
        if (newInstance2.mant[this.mant.length - 1] == 0) {
            newInstance2.exp = 0;
        }
        if (length > this.mant.length - 1) {
            i = newInstance2.round(iArr[length - this.mant.length]);
        } else {
            i = newInstance2.round(0);
        }
        if (i != 0) {
            newInstance2 = dotrap(i, MULTIPLY_TRAP, dfp, newInstance2);
        }
        return newInstance2;
    }

    public Dfp multiply(int i) {
        if (i < 0 || i >= 10000) {
            return multiply(newInstance(i));
        }
        return multiplyFast(i);
    }

    private Dfp multiplyFast(int i) {
        int i2;
        Dfp newInstance = newInstance(this);
        if (this.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (this.nans == 1 && i != 0) {
                return newInstance(this);
            }
            if (this.nans == 1 && i == 0) {
                this.field.setIEEEFlagsBits(1);
                Dfp newInstance2 = newInstance(getZero());
                newInstance2.nans = 3;
                return dotrap(1, MULTIPLY_TRAP, newInstance(getZero()), newInstance2);
            }
        }
        if (i < 0 || i >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance3 = newInstance(getZero());
            newInstance3.nans = 3;
            return dotrap(1, MULTIPLY_TRAP, newInstance3, newInstance3);
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.mant.length; i4++) {
            int i5 = (this.mant[i4] * i) + i3;
            i3 = i5 / 10000;
            newInstance.mant[i4] = i5 - (i3 * 10000);
        }
        if (i3 != 0) {
            i2 = newInstance.mant[0];
            newInstance.shiftRight();
            newInstance.mant[this.mant.length - 1] = i3;
        } else {
            i2 = 0;
        }
        if (newInstance.mant[this.mant.length - 1] == 0) {
            newInstance.exp = 0;
        }
        int round = newInstance.round(i2);
        if (round != 0) {
            newInstance = dotrap(round, MULTIPLY_TRAP, newInstance, newInstance);
        }
        return newInstance;
    }

    public Dfp divide(Dfp dfp) {
        int i;
        int i2;
        int i3;
        int i4;
        Dfp dfp2 = dfp;
        int i5 = 1;
        if (this.field.getRadixDigits() != dfp2.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            return dotrap(1, DIVIDE_TRAP, dfp2, newInstance);
        }
        Dfp newInstance2 = newInstance(getZero());
        if (!(this.nans == 0 && dfp2.nans == 0)) {
            if (isNaN()) {
                return this;
            }
            if (dfp.isNaN()) {
                return dfp2;
            }
            if (this.nans == 1 && dfp2.nans == 0) {
                Dfp newInstance3 = newInstance(this);
                newInstance3.sign = (byte) (this.sign * dfp2.sign);
                return newInstance3;
            } else if (dfp2.nans == 1 && this.nans == 0) {
                Dfp newInstance4 = newInstance(getZero());
                newInstance4.sign = (byte) (this.sign * dfp2.sign);
                return newInstance4;
            } else if (dfp2.nans == 1 && this.nans == 1) {
                this.field.setIEEEFlagsBits(1);
                Dfp newInstance5 = newInstance(getZero());
                newInstance5.nans = 3;
                return dotrap(1, DIVIDE_TRAP, dfp2, newInstance5);
            }
        }
        int i6 = 2;
        if (dfp2.mant[this.mant.length - 1] == 0) {
            this.field.setIEEEFlagsBits(2);
            Dfp newInstance6 = newInstance(getZero());
            newInstance6.sign = (byte) (this.sign * dfp2.sign);
            newInstance6.nans = 1;
            return dotrap(2, DIVIDE_TRAP, dfp2, newInstance6);
        }
        int i7 = 0;
        int[] iArr = new int[(this.mant.length + 1)];
        int[] iArr2 = new int[(this.mant.length + 2)];
        int[] iArr3 = new int[(this.mant.length + 1)];
        iArr[this.mant.length] = 0;
        iArr2[this.mant.length] = 0;
        iArr2[this.mant.length + 1] = 0;
        iArr3[this.mant.length] = 0;
        for (int i8 = 0; i8 < this.mant.length; i8++) {
            iArr[i8] = this.mant[i8];
            iArr2[i8] = 0;
            iArr3[i8] = 0;
        }
        int length = this.mant.length + 1;
        int i9 = 0;
        int i10 = 0;
        while (length >= 0) {
            int i11 = (iArr[this.mant.length] * 10000) + iArr[this.mant.length - i2];
            int i12 = i11 / (dfp2.mant[this.mant.length - i2] + i2);
            int i13 = (i11 + i2) / dfp2.mant[this.mant.length - i2];
            int i14 = i10;
            int i15 = i3;
            while (i15 == 0) {
                i14 = (i12 + i13) / i;
                int i16 = i3;
                int i17 = i16;
                while (i16 < this.mant.length + i2) {
                    int i18 = ((i16 < this.mant.length ? dfp2.mant[i16] : 0) * i14) + i17;
                    int i19 = i18 / 10000;
                    iArr3[i16] = i18 - (i19 * 10000);
                    i16++;
                    i17 = i19;
                    i2 = 1;
                }
                int i20 = 1;
                for (int i21 = 0; i21 < this.mant.length + 1; i21++) {
                    int i22 = (9999 - iArr3[i21]) + iArr[i21] + i20;
                    i20 = i22 / 10000;
                    iArr3[i21] = i22 - (i20 * 10000);
                }
                if (i20 == 0) {
                    i13 = i14 - 1;
                    i3 = 0;
                    i2 = 1;
                    i = 2;
                } else {
                    int i23 = ((iArr3[this.mant.length] * 10000) + iArr3[this.mant.length - 1]) / (dfp2.mant[this.mant.length - 1] + 1);
                    if (i23 >= 2) {
                        i12 = i14 + i23;
                        i3 = 0;
                        i = 2;
                        i2 = 1;
                    } else {
                        int i24 = 0;
                        for (int length2 = this.mant.length - 1; length2 >= 0; length2--) {
                            if (dfp2.mant[length2] > iArr3[length2]) {
                                i24 = 1;
                            }
                            if (dfp2.mant[length2] < iArr3[length2]) {
                                break;
                            }
                        }
                        i15 = iArr3[this.mant.length] != 0 ? 0 : i24;
                        if (i15 == 0) {
                            i12 = i14 + 1;
                        }
                        i = 2;
                        i3 = 0;
                        i2 = 1;
                    }
                }
            }
            int i25 = i;
            iArr2[length] = i14;
            if (!(i14 == 0 && i9 == 0)) {
                i9++;
            }
            if ((this.field.getRoundingMode() == RoundingMode.ROUND_DOWN && i9 == this.mant.length) || i9 > this.mant.length) {
                break;
            }
            iArr[0] = 0;
            int i26 = 0;
            while (i26 < this.mant.length) {
                int i27 = i26 + 1;
                iArr[i27] = iArr3[i26];
                i26 = i27;
            }
            length--;
            i6 = i25;
            i10 = i14;
            i7 = 0;
            i5 = 1;
        }
        int length3 = this.mant.length;
        int length4 = this.mant.length + 1;
        while (true) {
            if (length4 < 0) {
                break;
            } else if (iArr2[length4] != 0) {
                length3 = length4;
                break;
            } else {
                length4--;
            }
        }
        for (int i28 = 0; i28 < this.mant.length; i28++) {
            newInstance2.mant[(this.mant.length - i28) - 1] = iArr2[length3 - i28];
        }
        newInstance2.exp = ((this.exp - dfp2.exp) + length3) - this.mant.length;
        newInstance2.sign = (byte) (this.sign == dfp2.sign ? 1 : -1);
        if (newInstance2.mant[this.mant.length - 1] == 0) {
            newInstance2.exp = 0;
        }
        if (length3 > this.mant.length - 1) {
            i4 = newInstance2.round(iArr2[length3 - this.mant.length]);
        } else {
            i4 = newInstance2.round(0);
        }
        if (i4 != 0) {
            newInstance2 = dotrap(i4, DIVIDE_TRAP, dfp2, newInstance2);
        }
        return newInstance2;
    }

    public Dfp divide(int i) {
        if (this.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (this.nans == 1) {
                return newInstance(this);
            }
        }
        if (i == 0) {
            this.field.setIEEEFlagsBits(2);
            Dfp newInstance = newInstance(getZero());
            newInstance.sign = this.sign;
            newInstance.nans = 1;
            return dotrap(2, DIVIDE_TRAP, getZero(), newInstance);
        } else if (i < 0 || i >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance2 = newInstance(getZero());
            newInstance2.nans = 3;
            return dotrap(1, DIVIDE_TRAP, newInstance2, newInstance2);
        } else {
            Dfp newInstance3 = newInstance(this);
            int i2 = 0;
            for (int length = this.mant.length - 1; length >= 0; length--) {
                int i3 = (i2 * 10000) + newInstance3.mant[length];
                int i4 = i3 / i;
                i2 = i3 - (i4 * i);
                newInstance3.mant[length] = i4;
            }
            if (newInstance3.mant[this.mant.length - 1] == 0) {
                newInstance3.shiftLeft();
                int i5 = i2 * 10000;
                int i6 = i5 / i;
                i2 = i5 - (i6 * i);
                newInstance3.mant[0] = i6;
            }
            int round = newInstance3.round((i2 * 10000) / i);
            if (round != 0) {
                newInstance3 = dotrap(round, DIVIDE_TRAP, newInstance3, newInstance3);
            }
            return newInstance3;
        }
    }

    public Dfp reciprocal() {
        return this.field.getOne().divide(this);
    }

    public Dfp sqrt() {
        Dfp add;
        if (this.nans == 0 && this.mant[this.mant.length - 1] == 0) {
            return newInstance(this);
        }
        if (this.nans != 0) {
            if (this.nans == 1 && this.sign == 1) {
                return newInstance(this);
            }
            if (this.nans == 3) {
                return newInstance(this);
            }
            if (this.nans == 2) {
                this.field.setIEEEFlagsBits(1);
                return dotrap(1, SQRT_TRAP, null, newInstance(this));
            }
        }
        if (this.sign == -1) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(this);
            newInstance.nans = 3;
            return dotrap(1, SQRT_TRAP, null, newInstance);
        }
        Dfp newInstance2 = newInstance(this);
        if (newInstance2.exp < -1 || newInstance2.exp > 1) {
            newInstance2.exp = this.exp / 2;
        }
        int i = newInstance2.mant[this.mant.length - 1] / 2000;
        if (i != 0) {
            switch (i) {
                case 2:
                    newInstance2.mant[this.mant.length - 1] = 1500;
                    break;
                case 3:
                    newInstance2.mant[this.mant.length - 1] = 2200;
                    break;
                default:
                    newInstance2.mant[this.mant.length - 1] = 3000;
                    break;
            }
        } else {
            newInstance2.mant[this.mant.length - 1] = (newInstance2.mant[this.mant.length - 1] / 2) + 1;
        }
        newInstance(newInstance2);
        Dfp zero = getZero();
        getZero();
        Dfp dfp = zero;
        Dfp dfp2 = newInstance2;
        Dfp dfp3 = dfp;
        while (true) {
            if (dfp2.unequal(dfp3)) {
                Dfp newInstance3 = newInstance(dfp2);
                newInstance3.sign = -1;
                Dfp divide = newInstance3.add(divide(dfp2)).divide(2);
                add = dfp2.add(divide);
                if (!add.equals(dfp3) && divide.mant[this.mant.length - 1] != 0) {
                    dfp3 = dfp2;
                    dfp2 = add;
                }
            }
        }
        dfp2 = add;
        return dfp2;
    }

    public String toString() {
        if (this.nans != 0) {
            if (this.nans != 1) {
                return NAN_STRING;
            }
            return this.sign < 0 ? NEG_INFINITY_STRING : POS_INFINITY_STRING;
        } else if (this.exp > this.mant.length || this.exp < -1) {
            return dfp2sci();
        } else {
            return dfp2string();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ab  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String dfp2sci() {
        /*
            r13 = this;
            int[] r0 = r13.mant
            r1 = 0
            r2 = 1
            int r0 = r0.length
            int r0 = r0 * 4
            char[] r0 = new char[r0]
            int[] r3 = r13.mant
            int r3 = r3.length
            int r3 = r3 * 4
            int r3 = r3 + 20
            char[] r3 = new char[r3]
            int[] r4 = r13.mant
            int r4 = r4.length
            int r4 = r4 - r2
            r5 = r1
        L_0x0017:
            r6 = 48
            if (r4 < 0) goto L_0x0052
            int r7 = r5 + 1
            int[] r8 = r13.mant
            r8 = r8[r4]
            int r8 = r8 / 1000
            int r8 = r8 + r6
            char r8 = (char) r8
            r0[r5] = r8
            int r5 = r7 + 1
            int[] r8 = r13.mant
            r8 = r8[r4]
            int r8 = r8 / 100
            int r8 = r8 % 10
            int r8 = r8 + r6
            char r8 = (char) r8
            r0[r7] = r8
            int r7 = r5 + 1
            int[] r8 = r13.mant
            r8 = r8[r4]
            int r8 = r8 / 10
            int r8 = r8 % 10
            int r8 = r8 + r6
            char r8 = (char) r8
            r0[r5] = r8
            int r5 = r7 + 1
            int[] r8 = r13.mant
            r8 = r8[r4]
            int r8 = r8 % 10
            int r8 = r8 + r6
            char r6 = (char) r8
            r0[r7] = r6
            int r4 = r4 + -1
            goto L_0x0017
        L_0x0052:
            r4 = r1
        L_0x0053:
            int r5 = r0.length
            if (r4 >= r5) goto L_0x005e
            char r5 = r0[r4]
            if (r5 == r6) goto L_0x005b
            goto L_0x005e
        L_0x005b:
            int r4 = r4 + 1
            goto L_0x0053
        L_0x005e:
            byte r5 = r13.sign
            r7 = 45
            r8 = -1
            if (r5 != r8) goto L_0x0069
            r3[r1] = r7
            r5 = r2
            goto L_0x006a
        L_0x0069:
            r5 = r1
        L_0x006a:
            r8 = 101(0x65, float:1.42E-43)
            r9 = 46
            int r10 = r0.length
            if (r4 == r10) goto L_0x00bd
            int r10 = r5 + 1
            int r11 = r4 + 1
            char r12 = r0[r4]
            r3[r5] = r12
            int r5 = r10 + 1
            r3[r10] = r9
        L_0x007d:
            int r9 = r0.length
            if (r11 >= r9) goto L_0x008b
            int r9 = r5 + 1
            int r10 = r11 + 1
            char r11 = r0[r11]
            r3[r5] = r11
            r5 = r9
            r11 = r10
            goto L_0x007d
        L_0x008b:
            int r0 = r5 + 1
            r3[r5] = r8
            int r5 = r13.exp
            int r5 = r5 * 4
            int r5 = r5 - r4
            int r5 = r5 - r2
            if (r5 >= 0) goto L_0x0099
            int r2 = -r5
            goto L_0x009a
        L_0x0099:
            r2 = r5
        L_0x009a:
            r4 = 1000000000(0x3b9aca00, float:0.0047237873)
        L_0x009d:
            if (r4 <= r2) goto L_0x00a2
            int r4 = r4 / 10
            goto L_0x009d
        L_0x00a2:
            if (r5 >= 0) goto L_0x00a9
            int r5 = r0 + 1
            r3[r0] = r7
        L_0x00a8:
            r0 = r5
        L_0x00a9:
            if (r4 <= 0) goto L_0x00b7
            int r5 = r0 + 1
            int r7 = r2 / r4
            int r7 = r7 + r6
            char r7 = (char) r7
            r3[r0] = r7
            int r2 = r2 % r4
            int r4 = r4 / 10
            goto L_0x00a8
        L_0x00b7:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r3, r1, r0)
            return r2
        L_0x00bd:
            int r0 = r5 + 1
            r3[r5] = r6
            int r2 = r0 + 1
            r3[r0] = r9
            int r0 = r2 + 1
            r3[r2] = r6
            int r2 = r0 + 1
            r3[r0] = r8
            r3[r2] = r6
            java.lang.String r0 = new java.lang.String
            r2 = 5
            r0.<init>(r3, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.Dfp.dfp2sci():java.lang.String");
    }

    /* access modifiers changed from: protected */
    public String dfp2string() {
        int i;
        int i2;
        boolean z = false;
        int i3 = 1;
        char[] cArr = new char[((this.mant.length * 4) + 20)];
        int i4 = this.exp;
        cArr[0] = ' ';
        if (i4 <= 0) {
            cArr[1] = '0';
            i = 3;
            cArr[2] = ClassUtils.PACKAGE_SEPARATOR_CHAR;
            z = true;
        } else {
            i = 1;
        }
        while (i4 < 0) {
            int i5 = i + 1;
            cArr[i] = '0';
            int i6 = i5 + 1;
            cArr[i5] = '0';
            int i7 = i6 + 1;
            cArr[i6] = '0';
            i = i7 + 1;
            cArr[i7] = '0';
            i4++;
        }
        for (int length = this.mant.length - 1; length >= 0; length--) {
            int i8 = i + 1;
            cArr[i] = (char) ((this.mant[length] / 1000) + 48);
            int i9 = i8 + 1;
            cArr[i8] = (char) (((this.mant[length] / 100) % 10) + 48);
            int i10 = i9 + 1;
            cArr[i9] = (char) (((this.mant[length] / 10) % 10) + 48);
            int i11 = i10 + 1;
            cArr[i10] = (char) ((this.mant[length] % 10) + 48);
            i4--;
            if (i4 == 0) {
                int i12 = i11 + 1;
                cArr[i11] = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                i11 = i12;
                z = true;
            }
        }
        while (i4 > 0) {
            int i13 = i + 1;
            cArr[i] = '0';
            int i14 = i13 + 1;
            cArr[i13] = '0';
            int i15 = i14 + 1;
            cArr[i14] = '0';
            i = i15 + 1;
            cArr[i15] = '0';
            i4--;
        }
        if (!z) {
            i2 = i + 1;
            cArr[i] = ClassUtils.PACKAGE_SEPARATOR_CHAR;
        } else {
            i2 = i;
        }
        while (cArr[i3] == '0') {
            i3++;
        }
        if (cArr[i3] == '.') {
            i3--;
        }
        while (cArr[i2 - 1] == '0') {
            i2--;
        }
        if (this.sign < 0) {
            i3--;
            cArr[i3] = '-';
        }
        return new String(cArr, i3, i2 - i3);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.dfp.Dfp dotrap(int r10, java.lang.String r11, org.apache.commons.math3.dfp.Dfp r12, org.apache.commons.math3.dfp.Dfp r13) {
        /*
            r9 = this;
            r0 = 4
            r1 = 1
            if (r10 == r0) goto L_0x00a4
            r0 = 8
            if (r10 == r0) goto L_0x0082
            r0 = 3
            switch(r10) {
                case 1: goto L_0x0072;
                case 2: goto L_0x000f;
                default: goto L_0x000c;
            }
        L_0x000c:
            r7 = r13
            goto L_0x00b9
        L_0x000f:
            byte r2 = r9.nans
            if (r2 != 0) goto L_0x0030
            int[] r2 = r9.mant
            int[] r3 = r9.mant
            int r3 = r3.length
            int r3 = r3 - r1
            r2 = r2[r3]
            if (r2 == 0) goto L_0x0030
            org.apache.commons.math3.dfp.Dfp r2 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r2 = r9.newInstance(r2)
            byte r3 = r9.sign
            byte r4 = r12.sign
            int r3 = r3 * r4
            byte r3 = (byte) r3
            r2.sign = r3
            r2.nans = r1
            goto L_0x0031
        L_0x0030:
            r2 = r13
        L_0x0031:
            byte r3 = r9.nans
            if (r3 != 0) goto L_0x0049
            int[] r3 = r9.mant
            int[] r4 = r9.mant
            int r4 = r4.length
            int r4 = r4 - r1
            r3 = r3[r4]
            if (r3 != 0) goto L_0x0049
            org.apache.commons.math3.dfp.Dfp r2 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r2 = r9.newInstance(r2)
            r2.nans = r0
        L_0x0049:
            byte r3 = r9.nans
            if (r3 == r1) goto L_0x0051
            byte r3 = r9.nans
            if (r3 != r0) goto L_0x005b
        L_0x0051:
            org.apache.commons.math3.dfp.Dfp r2 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r2 = r9.newInstance(r2)
            r2.nans = r0
        L_0x005b:
            byte r3 = r9.nans
            if (r3 == r1) goto L_0x0067
            byte r1 = r9.nans
            r3 = 2
            if (r1 != r3) goto L_0x0065
            goto L_0x0067
        L_0x0065:
            r7 = r2
            goto L_0x00b9
        L_0x0067:
            org.apache.commons.math3.dfp.Dfp r1 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r1 = r9.newInstance(r1)
            r1.nans = r0
            goto L_0x0080
        L_0x0072:
            org.apache.commons.math3.dfp.Dfp r1 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r1 = r9.newInstance(r1)
            byte r2 = r13.sign
            r1.sign = r2
            r1.nans = r0
        L_0x0080:
            r7 = r1
            goto L_0x00b9
        L_0x0082:
            int r0 = r13.exp
            int[] r1 = r9.mant
            int r1 = r1.length
            int r0 = r0 + r1
            r1 = -32767(0xffffffffffff8001, float:NaN)
            if (r0 >= r1) goto L_0x0099
            org.apache.commons.math3.dfp.Dfp r0 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r0 = r9.newInstance(r0)
            byte r1 = r13.sign
            r0.sign = r1
            goto L_0x009d
        L_0x0099:
            org.apache.commons.math3.dfp.Dfp r0 = r9.newInstance(r13)
        L_0x009d:
            int r1 = r13.exp
            int r1 = r1 + 32760
            r13.exp = r1
            goto L_0x00b8
        L_0x00a4:
            int r0 = r13.exp
            int r0 = r0 + -32760
            r13.exp = r0
            org.apache.commons.math3.dfp.Dfp r0 = r9.getZero()
            org.apache.commons.math3.dfp.Dfp r0 = r9.newInstance(r0)
            byte r2 = r13.sign
            r0.sign = r2
            r0.nans = r1
        L_0x00b8:
            r7 = r0
        L_0x00b9:
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r8 = r13
            org.apache.commons.math3.dfp.Dfp r10 = r3.trap(r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.Dfp.dotrap(int, java.lang.String, org.apache.commons.math3.dfp.Dfp, org.apache.commons.math3.dfp.Dfp):org.apache.commons.math3.dfp.Dfp");
    }

    public int classify() {
        return this.nans;
    }

    public static Dfp copysign(Dfp dfp, Dfp dfp2) {
        Dfp newInstance = dfp.newInstance(dfp);
        newInstance.sign = dfp2.sign;
        return newInstance;
    }

    public Dfp nextAfter(Dfp dfp) {
        Dfp dfp2;
        if (this.field.getRadixDigits() != dfp.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp newInstance = newInstance(getZero());
            newInstance.nans = 3;
            return dotrap(1, NEXT_AFTER_TRAP, dfp, newInstance);
        }
        boolean lessThan = lessThan(dfp);
        if (compare(this, dfp) == 0) {
            return newInstance(dfp);
        }
        if (lessThan(getZero())) {
            lessThan = !lessThan;
        }
        if (lessThan) {
            Dfp newInstance2 = newInstance(getOne());
            newInstance2.exp = (this.exp - this.mant.length) + 1;
            newInstance2.sign = this.sign;
            if (equals(getZero())) {
                newInstance2.exp = -32767 - this.mant.length;
            }
            dfp2 = add(newInstance2);
        } else {
            Dfp newInstance3 = newInstance(getOne());
            newInstance3.exp = this.exp;
            newInstance3.sign = this.sign;
            if (equals(newInstance3)) {
                newInstance3.exp = this.exp - this.mant.length;
            } else {
                newInstance3.exp = (this.exp - this.mant.length) + 1;
            }
            if (equals(getZero())) {
                newInstance3.exp = -32767 - this.mant.length;
            }
            dfp2 = subtract(newInstance3);
        }
        if (dfp2.classify() == 1 && classify() != 1) {
            this.field.setIEEEFlagsBits(16);
            dfp2 = dotrap(16, NEXT_AFTER_TRAP, dfp, dfp2);
        }
        if (dfp2.equals(getZero()) && !equals(getZero())) {
            this.field.setIEEEFlagsBits(16);
            dfp2 = dotrap(16, NEXT_AFTER_TRAP, dfp, dfp2);
        }
        return dfp2;
    }

    public double toDouble() {
        boolean z;
        Dfp dfp;
        double d = Double.POSITIVE_INFINITY;
        if (isInfinite()) {
            return lessThan(getZero()) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        if (isNaN()) {
            return Double.NaN;
        }
        int compare = compare(this, getZero());
        double d2 = 0.0d;
        if (compare == 0) {
            if (this.sign < 0) {
                d2 = -0.0d;
            }
            return d2;
        }
        if (compare < 0) {
            dfp = negate();
            z = true;
        } else {
            dfp = this;
            z = false;
        }
        int log10 = (int) (((double) dfp.log10()) * 3.32d);
        if (log10 < 0) {
            log10--;
        }
        Dfp pow = DfpMath.pow(getTwo(), log10);
        while (true) {
            if (!pow.lessThan(dfp) && !pow.equals(dfp)) {
                break;
            }
            pow = pow.multiply(2);
            log10++;
        }
        int i = log10 - 1;
        Dfp divide = dfp.divide(DfpMath.pow(getTwo(), i));
        if (i > -1023) {
            divide = divide.subtract(getOne());
        }
        if (i < -1074) {
            return 0.0d;
        }
        if (i > 1023) {
            if (z) {
                d = Double.NEGATIVE_INFINITY;
            }
            return d;
        }
        String dfp2 = divide.multiply(newInstance(4503599627370496L)).rint().toString();
        long parseLong = Long.parseLong(dfp2.substring(0, dfp2.length() - 1));
        if (parseLong == 4503599627370496L) {
            parseLong = 0;
            i++;
        }
        if (i <= -1023) {
            i--;
        }
        while (i < -1023) {
            i++;
            parseLong >>>= 1;
        }
        double longBitsToDouble = Double.longBitsToDouble(parseLong | ((((long) i) + 1023) << 52));
        if (z) {
            longBitsToDouble = -longBitsToDouble;
        }
        return longBitsToDouble;
    }

    public double[] toSplitDouble() {
        double[] dArr = new double[2];
        dArr[0] = Double.longBitsToDouble(Double.doubleToLongBits(toDouble()) & -1073741824);
        dArr[1] = subtract(newInstance(dArr[0])).toDouble();
        return dArr;
    }
}
