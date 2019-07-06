package org.apache.commons.math3.util;

import java.math.BigDecimal;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class Precision {
    public static final double EPSILON = Double.longBitsToDouble(4368491638549381120L);
    private static final long EXPONENT_OFFSET = 1023;
    public static final double SAFE_MIN = Double.longBitsToDouble(4503599627370496L);
    private static final long SGN_MASK = Long.MIN_VALUE;
    private static final int SGN_MASK_FLOAT = Integer.MIN_VALUE;

    public static double representableDelta(double d, double d2) {
        return (d2 + d) - d;
    }

    private Precision() {
    }

    public static int compareTo(double d, double d2, double d3) {
        if (equals(d, d2, d3)) {
            return 0;
        }
        return d < d2 ? -1 : 1;
    }

    public static int compareTo(double d, double d2, int i) {
        if (equals(d, d2, i)) {
            return 0;
        }
        return d < d2 ? -1 : 1;
    }

    public static boolean equals(float f, float f2) {
        return equals(f, f2, 1);
    }

    public static boolean equalsIncludingNaN(float f, float f2) {
        return (Float.isNaN(f) && Float.isNaN(f2)) || equals(f, f2, 1);
    }

    public static boolean equals(float f, float f2, float f3) {
        return equals(f, f2, 1) || FastMath.abs(f2 - f) <= f3;
    }

    public static boolean equalsIncludingNaN(float f, float f2, float f3) {
        return equalsIncludingNaN(f, f2) || FastMath.abs(f2 - f) <= f3;
    }

    public static boolean equals(float f, float f2, int i) {
        int floatToIntBits = Float.floatToIntBits(f);
        int floatToIntBits2 = Float.floatToIntBits(f2);
        if (floatToIntBits < 0) {
            floatToIntBits = Integer.MIN_VALUE - floatToIntBits;
        }
        if (floatToIntBits2 < 0) {
            floatToIntBits2 = Integer.MIN_VALUE - floatToIntBits2;
        }
        if (!(FastMath.abs(floatToIntBits - floatToIntBits2) <= i) || Float.isNaN(f) || Float.isNaN(f2)) {
            return false;
        }
        return true;
    }

    public static boolean equalsIncludingNaN(float f, float f2, int i) {
        return (Float.isNaN(f) && Float.isNaN(f2)) || equals(f, f2, i);
    }

    public static boolean equals(double d, double d2) {
        return equals(d, d2, 1);
    }

    public static boolean equalsIncludingNaN(double d, double d2) {
        return (Double.isNaN(d) && Double.isNaN(d2)) || equals(d, d2, 1);
    }

    public static boolean equals(double d, double d2, double d3) {
        return equals(d, d2, 1) || FastMath.abs(d2 - d) <= d3;
    }

    public static boolean equalsWithRelativeTolerance(double d, double d2, double d3) {
        boolean z = true;
        if (equals(d, d2, 1)) {
            return true;
        }
        if (FastMath.abs((d - d2) / FastMath.max(FastMath.abs(d), FastMath.abs(d2))) > d3) {
            z = false;
        }
        return z;
    }

    public static boolean equalsIncludingNaN(double d, double d2, double d3) {
        return equalsIncludingNaN(d, d2) || FastMath.abs(d2 - d) <= d3;
    }

    public static boolean equals(double d, double d2, int i) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        long doubleToLongBits2 = Double.doubleToLongBits(d2);
        if (!(FastMath.abs(((doubleToLongBits > 0 ? 1 : (doubleToLongBits == 0 ? 0 : -1)) < 0 ? SGN_MASK - doubleToLongBits : doubleToLongBits) - ((doubleToLongBits2 > 0 ? 1 : (doubleToLongBits2 == 0 ? 0 : -1)) < 0 ? SGN_MASK - doubleToLongBits2 : doubleToLongBits2)) <= ((long) i)) || Double.isNaN(d) || Double.isNaN(d2)) {
            return false;
        }
        return true;
    }

    public static boolean equalsIncludingNaN(double d, double d2, int i) {
        return (Double.isNaN(d) && Double.isNaN(d2)) || equals(d, d2, i);
    }

    public static double round(double d, int i) {
        return round(d, i, 4);
    }

    public static double round(double d, int i, int i2) {
        try {
            return new BigDecimal(Double.toString(d)).setScale(i, i2).doubleValue();
        } catch (NumberFormatException unused) {
            if (Double.isInfinite(d)) {
                return d;
            }
            return Double.NaN;
        }
    }

    public static float round(float f, int i) {
        return round(f, i, 4);
    }

    public static float round(float f, int i, int i2) throws MathArithmeticException, MathIllegalArgumentException {
        float copySign = FastMath.copySign(1.0f, f);
        float pow = ((float) FastMath.pow(10.0d, i)) * copySign;
        return ((float) roundUnscaled((double) (f * pow), (double) copySign, i2)) / pow;
    }

    private static double roundUnscaled(double d, double d2, int i) throws MathArithmeticException, MathIllegalArgumentException {
        switch (i) {
            case 0:
                return FastMath.ceil(FastMath.nextAfter(d, Double.POSITIVE_INFINITY));
            case 1:
                return FastMath.floor(FastMath.nextAfter(d, Double.NEGATIVE_INFINITY));
            case 2:
                if (d2 == -1.0d) {
                    return FastMath.floor(FastMath.nextAfter(d, Double.NEGATIVE_INFINITY));
                }
                return FastMath.ceil(FastMath.nextAfter(d, Double.POSITIVE_INFINITY));
            case 3:
                if (d2 == -1.0d) {
                    return FastMath.ceil(FastMath.nextAfter(d, Double.POSITIVE_INFINITY));
                }
                return FastMath.floor(FastMath.nextAfter(d, Double.NEGATIVE_INFINITY));
            case 4:
                double nextAfter = FastMath.nextAfter(d, Double.POSITIVE_INFINITY);
                if (nextAfter - FastMath.floor(nextAfter) >= 0.5d) {
                    return FastMath.ceil(nextAfter);
                }
                return FastMath.floor(nextAfter);
            case 5:
                double nextAfter2 = FastMath.nextAfter(d, Double.NEGATIVE_INFINITY);
                if (nextAfter2 - FastMath.floor(nextAfter2) > 0.5d) {
                    return FastMath.ceil(nextAfter2);
                }
                return FastMath.floor(nextAfter2);
            case 6:
                double floor = d - FastMath.floor(d);
                if (floor > 0.5d) {
                    return FastMath.ceil(d);
                }
                if (floor < 0.5d) {
                    return FastMath.floor(d);
                }
                if (FastMath.floor(d) / 2.0d == FastMath.floor(Math.floor(d) / 2.0d)) {
                    return FastMath.floor(d);
                }
                return FastMath.ceil(d);
            case 7:
                if (d == FastMath.floor(d)) {
                    return d;
                }
                throw new MathArithmeticException();
            default:
                throw new MathIllegalArgumentException(LocalizedFormats.INVALID_ROUNDING_METHOD, Integer.valueOf(i), "ROUND_CEILING", Integer.valueOf(2), "ROUND_DOWN", Integer.valueOf(1), "ROUND_FLOOR", Integer.valueOf(3), "ROUND_HALF_DOWN", Integer.valueOf(5), "ROUND_HALF_EVEN", Integer.valueOf(6), "ROUND_HALF_UP", Integer.valueOf(4), "ROUND_UNNECESSARY", Integer.valueOf(7), "ROUND_UP", Integer.valueOf(0));
        }
    }
}
