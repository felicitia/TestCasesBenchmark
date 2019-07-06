package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigInteger;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

public class Fraction extends Number implements Serializable, Comparable<Fraction>, FieldElement<Fraction> {
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
    public static final Fraction MINUS_ONE = new Fraction(-1, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction TWO = new Fraction(2, 1);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction ZERO = new Fraction(0, 1);
    private static final long serialVersionUID = 3698073679419233275L;
    private final int denominator;
    private final int numerator;

    public Fraction(double d) throws FractionConversionException {
        this(d, 1.0E-5d, 100);
    }

    public Fraction(double d, double d2, int i) throws FractionConversionException {
        this(d, d2, Integer.MAX_VALUE, i);
    }

    public Fraction(double d, int i) throws FractionConversionException {
        this(d, 0.0d, i, 100);
    }

    private Fraction(double d, double d2, int i, int i2) throws FractionConversionException {
        long j;
        long j2;
        long j3;
        long j4;
        double d3 = d;
        int i3 = i;
        int i4 = i2;
        long floor = (long) FastMath.floor(d);
        if (FastMath.abs(floor) > 2147483647L) {
            FractionConversionException fractionConversionException = new FractionConversionException(d3, floor, 1);
            throw fractionConversionException;
        }
        int i5 = 1;
        if (FastMath.abs(((double) floor) - d3) < d2) {
            this.numerator = (int) floor;
            this.denominator = 1;
            return;
        }
        int i6 = 0;
        boolean z = false;
        long j5 = 0;
        long j6 = 1;
        long j7 = 1;
        long j8 = floor;
        long j9 = j8;
        double d4 = d3;
        while (true) {
            int i7 = i6 + i5;
            double d5 = 1.0d / (d4 - ((double) j8));
            long floor2 = (long) FastMath.floor(d5);
            double d6 = d4;
            j = (floor2 * j9) + j6;
            double d7 = d5;
            j2 = (floor2 * j7) + j5;
            if (FastMath.abs(j) > 2147483647L || FastMath.abs(j2) > 2147483647L) {
                FractionConversionException fractionConversionException2 = new FractionConversionException(d3, j, j2);
            } else {
                long j10 = floor2;
                long j11 = j8;
                double d8 = ((double) j) / ((double) j2);
                int i8 = i7;
                if (i8 >= i4 || FastMath.abs(d8 - d3) <= d2 || j2 >= ((long) i3)) {
                    j4 = j9;
                    j3 = j7;
                    j10 = j11;
                    z = true;
                } else {
                    j4 = j;
                    j6 = j9;
                    j5 = j7;
                    d6 = d7;
                    j3 = j2;
                }
                if (!z) {
                    i6 = i8;
                    j7 = j3;
                    d4 = d6;
                    j8 = j10;
                    i5 = 1;
                    j9 = j4;
                } else if (i8 >= i4) {
                    throw new FractionConversionException(d3, i4);
                } else {
                    if (j2 < ((long) i3)) {
                        this.numerator = (int) j;
                        this.denominator = (int) j2;
                    } else {
                        this.numerator = (int) j4;
                        this.denominator = (int) j3;
                    }
                    return;
                }
            }
        }
        FractionConversionException fractionConversionException22 = new FractionConversionException(d3, j, j2);
        throw fractionConversionException22;
    }

    public Fraction(int i) {
        this(i, 1);
    }

    public Fraction(int i, int i2) {
        if (i2 == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
        }
        if (i2 < 0) {
            if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
            }
            i = -i;
            i2 = -i2;
        }
        int gcd = ArithmeticUtils.gcd(i, i2);
        if (gcd > 1) {
            i /= gcd;
            i2 /= gcd;
        }
        if (i2 < 0) {
            i = -i;
            i2 = -i2;
        }
        this.numerator = i;
        this.denominator = i2;
    }

    public Fraction abs() {
        if (this.numerator >= 0) {
            return this;
        }
        return negate();
    }

    public int compareTo(Fraction fraction) {
        long j = ((long) this.numerator) * ((long) fraction.denominator);
        long j2 = ((long) this.denominator) * ((long) fraction.numerator);
        if (j < j2) {
            return -1;
        }
        return j > j2 ? 1 : 0;
    }

    public double doubleValue() {
        return ((double) this.numerator) / ((double) this.denominator);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction fraction = (Fraction) obj;
        if (!(this.numerator == fraction.numerator && this.denominator == fraction.denominator)) {
            z = false;
        }
        return z;
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int hashCode() {
        return (37 * (629 + this.numerator)) + this.denominator;
    }

    public int intValue() {
        return (int) doubleValue();
    }

    public long longValue() {
        return (long) doubleValue();
    }

    public Fraction negate() {
        if (this.numerator != Integer.MIN_VALUE) {
            return new Fraction(-this.numerator, this.denominator);
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(this.numerator), Integer.valueOf(this.denominator));
    }

    public Fraction reciprocal() {
        return new Fraction(this.denominator, this.numerator);
    }

    public Fraction add(Fraction fraction) {
        return addSub(fraction, true);
    }

    public Fraction add(int i) {
        return new Fraction(this.numerator + (i * this.denominator), this.denominator);
    }

    public Fraction subtract(Fraction fraction) {
        return addSub(fraction, false);
    }

    public Fraction subtract(int i) {
        return new Fraction(this.numerator - (i * this.denominator), this.denominator);
    }

    private Fraction addSub(Fraction fraction, boolean z) {
        int i;
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        } else if (this.numerator == 0) {
            if (!z) {
                fraction = fraction.negate();
            }
            return fraction;
        } else if (fraction.numerator == 0) {
            return this;
        } else {
            int gcd = ArithmeticUtils.gcd(this.denominator, fraction.denominator);
            if (gcd == 1) {
                int mulAndCheck = ArithmeticUtils.mulAndCheck(this.numerator, fraction.denominator);
                int mulAndCheck2 = ArithmeticUtils.mulAndCheck(fraction.numerator, this.denominator);
                return new Fraction(z ? ArithmeticUtils.addAndCheck(mulAndCheck, mulAndCheck2) : ArithmeticUtils.subAndCheck(mulAndCheck, mulAndCheck2), ArithmeticUtils.mulAndCheck(this.denominator, fraction.denominator));
            }
            BigInteger multiply = BigInteger.valueOf((long) this.numerator).multiply(BigInteger.valueOf((long) (fraction.denominator / gcd)));
            BigInteger multiply2 = BigInteger.valueOf((long) fraction.numerator).multiply(BigInteger.valueOf((long) (this.denominator / gcd)));
            BigInteger add = z ? multiply.add(multiply2) : multiply.subtract(multiply2);
            int intValue = add.mod(BigInteger.valueOf((long) gcd)).intValue();
            if (intValue == 0) {
                i = gcd;
            } else {
                i = ArithmeticUtils.gcd(intValue, gcd);
            }
            BigInteger divide = add.divide(BigInteger.valueOf((long) i));
            if (divide.bitLength() <= 31) {
                return new Fraction(divide.intValue(), ArithmeticUtils.mulAndCheck(this.denominator / gcd, fraction.denominator / i));
            }
            throw new MathArithmeticException(LocalizedFormats.NUMERATOR_OVERFLOW_AFTER_MULTIPLY, divide);
        }
    }

    public Fraction multiply(Fraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        } else if (this.numerator == 0 || fraction.numerator == 0) {
            return ZERO;
        } else {
            int gcd = ArithmeticUtils.gcd(this.numerator, fraction.denominator);
            int gcd2 = ArithmeticUtils.gcd(fraction.numerator, this.denominator);
            return getReducedFraction(ArithmeticUtils.mulAndCheck(this.numerator / gcd, fraction.numerator / gcd2), ArithmeticUtils.mulAndCheck(this.denominator / gcd2, fraction.denominator / gcd));
        }
    }

    public Fraction multiply(int i) {
        return new Fraction(this.numerator * i, this.denominator);
    }

    public Fraction divide(Fraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        } else if (fraction.numerator != 0) {
            return multiply(fraction.reciprocal());
        } else {
            throw new MathArithmeticException(LocalizedFormats.ZERO_FRACTION_TO_DIVIDE_BY, Integer.valueOf(fraction.numerator), Integer.valueOf(fraction.denominator));
        }
    }

    public Fraction divide(int i) {
        return new Fraction(this.numerator, this.denominator * i);
    }

    public double percentageValue() {
        return 100.0d * doubleValue();
    }

    public static Fraction getReducedFraction(int i, int i2) {
        if (i2 == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
        } else if (i == 0) {
            return ZERO;
        } else {
            if (i2 == Integer.MIN_VALUE && (i & 1) == 0) {
                i /= 2;
                i2 /= 2;
            }
            if (i2 < 0) {
                if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                    throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, Integer.valueOf(i), Integer.valueOf(i2));
                }
                i = -i;
                i2 = -i2;
            }
            int gcd = ArithmeticUtils.gcd(i, i2);
            return new Fraction(i / gcd, i2 / gcd);
        }
    }

    public String toString() {
        if (this.denominator == 1) {
            return Integer.toString(this.numerator);
        }
        if (this.numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.numerator);
        sb.append(" / ");
        sb.append(this.denominator);
        return sb.toString();
    }

    public FractionField getField() {
        return FractionField.getInstance();
    }
}
