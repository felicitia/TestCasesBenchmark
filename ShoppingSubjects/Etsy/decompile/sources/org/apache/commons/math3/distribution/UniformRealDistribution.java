package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

public class UniformRealDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 20120109;
    private final double lower;
    private final double solverAbsoluteAccuracy;
    private final double upper;

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    public UniformRealDistribution() {
        this(0.0d, 1.0d);
    }

    public UniformRealDistribution(double d, double d2) throws NumberIsTooLargeException {
        this(d, d2, 1.0E-9d);
    }

    public UniformRealDistribution(double d, double d2, double d3) throws NumberIsTooLargeException {
        this(new Well19937c(), d, d2, d3);
    }

    public UniformRealDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NumberIsTooLargeException {
        super(randomGenerator);
        if (d >= d2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(d), Double.valueOf(d2), false);
        }
        this.lower = d;
        this.upper = d2;
        this.solverAbsoluteAccuracy = d3;
    }

    public double density(double d) {
        if (d < this.lower || d > this.upper) {
            return 0.0d;
        }
        return 1.0d / (this.upper - this.lower);
    }

    public double cumulativeProbability(double d) {
        if (d <= this.lower) {
            return 0.0d;
        }
        if (d >= this.upper) {
            return 1.0d;
        }
        return (d - this.lower) / (this.upper - this.lower);
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        return 0.5d * (this.lower + this.upper);
    }

    public double getNumericalVariance() {
        double d = this.upper - this.lower;
        return (d * d) / 12.0d;
    }

    public double getSupportLowerBound() {
        return this.lower;
    }

    public double getSupportUpperBound() {
        return this.upper;
    }

    public double sample() {
        double nextDouble = this.random.nextDouble();
        return (this.upper * nextDouble) + ((1.0d - nextDouble) * this.lower);
    }
}
