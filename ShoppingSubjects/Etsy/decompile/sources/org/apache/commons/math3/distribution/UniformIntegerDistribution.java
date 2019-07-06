package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class UniformIntegerDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 20120109;
    private final int lower;
    private final int upper;

    public boolean isSupportConnected() {
        return true;
    }

    public UniformIntegerDistribution(int i, int i2) throws NumberIsTooLargeException {
        this(new Well19937c(), i, i2);
    }

    public UniformIntegerDistribution(RandomGenerator randomGenerator, int i, int i2) throws NumberIsTooLargeException {
        super(randomGenerator);
        if (i >= i2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Integer.valueOf(i), Integer.valueOf(i2), false);
        }
        this.lower = i;
        this.upper = i2;
    }

    public double probability(int i) {
        if (i < this.lower || i > this.upper) {
            return 0.0d;
        }
        return 1.0d / ((double) ((this.upper - this.lower) + 1));
    }

    public double cumulativeProbability(int i) {
        if (i < this.lower) {
            return 0.0d;
        }
        if (i > this.upper) {
            return 1.0d;
        }
        return (((double) (i - this.lower)) + 1.0d) / (((double) (this.upper - this.lower)) + 1.0d);
    }

    public double getNumericalMean() {
        return 0.5d * ((double) (this.lower + this.upper));
    }

    public double getNumericalVariance() {
        double d = (double) ((this.upper - this.lower) + 1);
        return ((d * d) - 1.0d) / 12.0d;
    }

    public int getSupportLowerBound() {
        return this.lower;
    }

    public int getSupportUpperBound() {
        return this.upper;
    }

    public int sample() {
        double nextDouble = this.random.nextDouble();
        return (int) FastMath.floor((((double) this.upper) * nextDouble) + ((1.0d - nextDouble) * ((double) this.lower)) + nextDouble);
    }
}
