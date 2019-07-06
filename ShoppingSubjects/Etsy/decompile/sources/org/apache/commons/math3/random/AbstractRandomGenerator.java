package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractRandomGenerator implements RandomGenerator {
    private double cachedNormalDeviate = Double.NaN;

    public abstract double nextDouble();

    public abstract void setSeed(long j);

    public void clear() {
        this.cachedNormalDeviate = Double.NaN;
    }

    public void setSeed(int i) {
        setSeed((long) i);
    }

    public void setSeed(int[] iArr) {
        long j = 0;
        int i = 0;
        while (i < iArr.length) {
            i++;
            j = (j * 4294967291L) + ((long) iArr[i]);
        }
        setSeed(j);
    }

    public void nextBytes(byte[] bArr) {
        int i;
        for (int i2 = 0; i2 < bArr.length; i2 = i) {
            int nextInt = nextInt();
            i = i2;
            int i3 = 0;
            while (i3 < 3) {
                if (i3 > 0) {
                    nextInt >>= 8;
                }
                int i4 = i + 1;
                bArr[i] = (byte) nextInt;
                if (i4 != bArr.length) {
                    i3++;
                    i = i4;
                } else {
                    return;
                }
            }
        }
    }

    public int nextInt() {
        return (int) (((2.0d * nextDouble()) - 1.0d) * 2.147483647E9d);
    }

    public int nextInt(int i) {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        int nextDouble = (int) (nextDouble() * ((double) i));
        return nextDouble < i ? nextDouble : i - 1;
    }

    public long nextLong() {
        return (long) (((2.0d * nextDouble()) - 1.0d) * 9.223372036854776E18d);
    }

    public boolean nextBoolean() {
        return nextDouble() <= 0.5d;
    }

    public float nextFloat() {
        return (float) nextDouble();
    }

    public double nextGaussian() {
        if (!Double.isNaN(this.cachedNormalDeviate)) {
            double d = this.cachedNormalDeviate;
            this.cachedNormalDeviate = Double.NaN;
            return d;
        }
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 1.0d;
        while (d4 >= 1.0d) {
            d3 = (nextDouble() * 2.0d) - 1.0d;
            d2 = (2.0d * nextDouble()) - 1.0d;
            d4 = (d3 * d3) + (d2 * d2);
        }
        if (d4 != 0.0d) {
            d4 = FastMath.sqrt((-2.0d * FastMath.log(d4)) / d4);
        }
        this.cachedNormalDeviate = d2 * d4;
        return d3 * d4;
    }
}
