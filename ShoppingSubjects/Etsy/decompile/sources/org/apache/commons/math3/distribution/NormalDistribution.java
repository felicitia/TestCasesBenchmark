package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;

public class NormalDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final double SQRT2 = FastMath.sqrt(2.0d);
    private static final double SQRT2PI = FastMath.sqrt(6.283185307179586d);
    private static final long serialVersionUID = 8589540077390120676L;
    private final double mean;
    private final double solverAbsoluteAccuracy;
    private final double standardDeviation;

    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
    }

    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return false;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public NormalDistribution() {
        this(0.0d, 1.0d);
    }

    public NormalDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, 1.0E-9d);
    }

    public NormalDistribution(double d, double d2, double d3) throws NotStrictlyPositiveException {
        this(new Well19937c(), d, d2, d3);
    }

    public NormalDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NotStrictlyPositiveException {
        super(randomGenerator);
        if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.STANDARD_DEVIATION, Double.valueOf(d2));
        }
        this.mean = d;
        this.standardDeviation = d2;
        this.solverAbsoluteAccuracy = d3;
    }

    public double getMean() {
        return this.mean;
    }

    public double getStandardDeviation() {
        return this.standardDeviation;
    }

    public double density(double d) {
        double d2 = (d - this.mean) / this.standardDeviation;
        return FastMath.exp((-0.5d * d2) * d2) / (this.standardDeviation * SQRT2PI);
    }

    public double cumulativeProbability(double d) {
        double d2 = d - this.mean;
        double d3 = 1.0d;
        if (FastMath.abs(d2) <= 40.0d * this.standardDeviation) {
            return 0.5d * (1.0d + Erf.erf(d2 / (this.standardDeviation * SQRT2)));
        }
        if (d2 < 0.0d) {
            d3 = 0.0d;
        }
        return d3;
    }

    @Deprecated
    public double cumulativeProbability(double d, double d2) throws NumberIsTooLargeException {
        return probability(d, d2);
    }

    public double probability(double d, double d2) throws NumberIsTooLargeException {
        if (d > d2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Double.valueOf(d), Double.valueOf(d2), true);
        }
        double d3 = this.standardDeviation * SQRT2;
        return 0.5d * Erf.erf((d - this.mean) / d3, (d2 - this.mean) / d3);
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        return getMean();
    }

    public double getNumericalVariance() {
        double standardDeviation2 = getStandardDeviation();
        return standardDeviation2 * standardDeviation2;
    }

    public double sample() {
        return (this.standardDeviation * this.random.nextGaussian()) + this.mean;
    }
}
