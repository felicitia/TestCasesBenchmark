package org.apache.commons.math3.util;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

public class Decimal64 extends Number implements Comparable<Decimal64>, FieldElement<Decimal64> {
    public static final Decimal64 NAN = new Decimal64(Double.NaN);
    public static final Decimal64 NEGATIVE_INFINITY = new Decimal64(Double.NEGATIVE_INFINITY);
    public static final Decimal64 ONE = new Decimal64(1.0d);
    public static final Decimal64 POSITIVE_INFINITY = new Decimal64(Double.POSITIVE_INFINITY);
    public static final Decimal64 ZERO = new Decimal64(0.0d);
    private static final long serialVersionUID = 20120227;
    private final double value;

    public Decimal64(double d) {
        this.value = d;
    }

    public Field<Decimal64> getField() {
        return Decimal64Field.getInstance();
    }

    public Decimal64 add(Decimal64 decimal64) {
        return new Decimal64(this.value + decimal64.value);
    }

    public Decimal64 subtract(Decimal64 decimal64) {
        return new Decimal64(this.value - decimal64.value);
    }

    public Decimal64 negate() {
        return new Decimal64(-this.value);
    }

    public Decimal64 multiply(Decimal64 decimal64) {
        return new Decimal64(this.value * decimal64.value);
    }

    public Decimal64 multiply(int i) {
        return new Decimal64(((double) i) * this.value);
    }

    public Decimal64 divide(Decimal64 decimal64) {
        return new Decimal64(this.value / decimal64.value);
    }

    public Decimal64 reciprocal() {
        return new Decimal64(1.0d / this.value);
    }

    public byte byteValue() {
        return (byte) ((int) this.value);
    }

    public short shortValue() {
        return (short) ((int) this.value);
    }

    public int intValue() {
        return (int) this.value;
    }

    public long longValue() {
        return (long) this.value;
    }

    public float floatValue() {
        return (float) this.value;
    }

    public double doubleValue() {
        return this.value;
    }

    public int compareTo(Decimal64 decimal64) {
        return Double.compare(this.value, decimal64.value);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof Decimal64)) {
            return false;
        }
        if (Double.doubleToLongBits(this.value) == Double.doubleToLongBits(((Decimal64) obj).value)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.value);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    public String toString() {
        return Double.toString(this.value);
    }

    public boolean isInfinite() {
        return Double.isInfinite(this.value);
    }

    public boolean isNaN() {
        return Double.isNaN(this.value);
    }
}
