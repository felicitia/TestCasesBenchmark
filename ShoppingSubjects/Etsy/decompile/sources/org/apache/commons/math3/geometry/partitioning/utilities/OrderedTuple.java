package org.apache.commons.math3.geometry.partitioning.utilities;

import java.util.Arrays;
import org.apache.commons.math3.util.FastMath;

public class OrderedTuple implements Comparable<OrderedTuple> {
    private static final long EXPONENT_MASK = 9218868437227405312L;
    private static final long IMPLICIT_ONE = 4503599627370496L;
    private static final long MANTISSA_MASK = 4503599627370495L;
    private static final long SIGN_MASK = Long.MIN_VALUE;
    private double[] components;
    private long[] encoding;
    private int lsb = Integer.MAX_VALUE;
    private boolean nan = false;
    private boolean negInf = false;
    private int offset;
    private boolean posInf = false;

    private static int computeLSB(long j) {
        long j2 = -4294967296L;
        int i = 32;
        int i2 = 0;
        while (i != 0) {
            if ((j & j2) == j) {
                i2 |= i;
                j >>= i;
            }
            i >>= 1;
            j2 >>= i;
        }
        return i2;
    }

    private static int computeMSB(long j) {
        long j2 = 4294967295L;
        int i = 32;
        int i2 = 0;
        while (i != 0) {
            if ((j & j2) != j) {
                i2 |= i;
                j >>= i;
            }
            i >>= 1;
            j2 >>= i;
        }
        return i2;
    }

    private static int exponent(long j) {
        return ((int) ((j & EXPONENT_MASK) >> 52)) - 1075;
    }

    private static long mantissa(long j) {
        return (j & EXPONENT_MASK) == 0 ? (j & MANTISSA_MASK) << 1 : IMPLICIT_ONE | (j & MANTISSA_MASK);
    }

    private static long sign(long j) {
        return j & SIGN_MASK;
    }

    public OrderedTuple(double... dArr) {
        this.components = (double[]) dArr.clone();
        int i = Integer.MIN_VALUE;
        for (int i2 = 0; i2 < dArr.length; i2++) {
            if (Double.isInfinite(dArr[i2])) {
                if (dArr[i2] < 0.0d) {
                    this.negInf = true;
                } else {
                    this.posInf = true;
                }
            } else if (Double.isNaN(dArr[i2])) {
                this.nan = true;
            } else {
                long doubleToLongBits = Double.doubleToLongBits(dArr[i2]);
                long mantissa = mantissa(doubleToLongBits);
                if (mantissa != 0) {
                    int exponent = exponent(doubleToLongBits);
                    i = FastMath.max(i, computeMSB(mantissa) + exponent);
                    this.lsb = FastMath.min(this.lsb, exponent + computeLSB(mantissa));
                }
            }
        }
        if (this.posInf && this.negInf) {
            this.posInf = false;
            this.negInf = false;
            this.nan = true;
        }
        if (this.lsb <= i) {
            encode(i + 16);
            return;
        }
        this.encoding = new long[]{0};
    }

    private void encode(int i) {
        this.offset = i + 31;
        this.offset -= this.offset % 32;
        if (this.encoding == null || this.encoding.length != 1 || this.encoding[0] != 0) {
            this.encoding = new long[(this.components.length * ((((this.offset + 1) - this.lsb) + 62) / 63))];
            int i2 = this.offset;
            long j = 0;
            int i3 = 0;
            int i4 = 62;
            while (i3 < this.encoding.length) {
                int i5 = i4;
                long j2 = j;
                int i6 = i3;
                for (int i7 = 0; i7 < this.components.length; i7++) {
                    if (getBit(i7, i2) != 0) {
                        j2 |= 1 << i5;
                    }
                    int i8 = i5 - 1;
                    if (i5 == 0) {
                        int i9 = i6 + 1;
                        this.encoding[i6] = j2;
                        j2 = 0;
                        i5 = 62;
                        i6 = i9;
                    } else {
                        i5 = i8;
                    }
                }
                i2--;
                i3 = i6;
                j = j2;
                i4 = i5;
            }
        }
    }

    public int compareTo(OrderedTuple orderedTuple) {
        if (this.components.length != orderedTuple.components.length) {
            return this.components.length - orderedTuple.components.length;
        }
        if (this.nan) {
            return 1;
        }
        if (orderedTuple.nan || this.negInf || orderedTuple.posInf) {
            return -1;
        }
        if (this.posInf || orderedTuple.negInf) {
            return 1;
        }
        if (this.offset < orderedTuple.offset) {
            encode(orderedTuple.offset);
        } else if (this.offset > orderedTuple.offset) {
            orderedTuple.encode(this.offset);
        }
        int min = FastMath.min(this.encoding.length, orderedTuple.encoding.length);
        for (int i = 0; i < min; i++) {
            if (this.encoding[i] < orderedTuple.encoding[i]) {
                return -1;
            }
            if (this.encoding[i] > orderedTuple.encoding[i]) {
                return 1;
            }
        }
        if (this.encoding.length < orderedTuple.encoding.length) {
            return -1;
        }
        if (this.encoding.length > orderedTuple.encoding.length) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OrderedTuple)) {
            return false;
        }
        if (compareTo((OrderedTuple) obj) != 0) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 71;
        int hashCode = ((((((((Arrays.hashCode(this.components) * 37) + this.offset) * 37) + this.lsb) * 37) + (this.posInf ? 97 : 71)) * 37) + (this.negInf ? 97 : 71)) * 37;
        if (this.nan) {
            i = 97;
        }
        return hashCode + i;
    }

    public double[] getComponents() {
        return (double[]) this.components.clone();
    }

    private int getBit(int i, int i2) {
        long doubleToLongBits = Double.doubleToLongBits(this.components[i]);
        int exponent = exponent(doubleToLongBits);
        int i3 = 0;
        if (i2 < exponent || i2 > this.offset) {
            return 0;
        }
        if (i2 == this.offset) {
            if (sign(doubleToLongBits) == 0) {
                i3 = 1;
            }
            return i3;
        } else if (i2 > exponent + 52) {
            if (sign(doubleToLongBits) != 0) {
                i3 = 1;
            }
            return i3;
        } else {
            return (int) (((sign(doubleToLongBits) == 0 ? mantissa(doubleToLongBits) : -mantissa(doubleToLongBits)) >> (i2 - exponent)) & 1);
        }
    }
}
