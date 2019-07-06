package org.apache.commons.math3.distribution;

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

public class ChiSquaredDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = -8352658048349159782L;
    private final GammaDistribution gamma;
    private final double solverAbsoluteAccuracy;

    public double getSupportLowerBound() {
        return 0.0d;
    }

    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public ChiSquaredDistribution(double d) {
        this(d, 1.0E-9d);
    }

    public ChiSquaredDistribution(double d, double d2) {
        this(new Well19937c(), d, d2);
    }

    public ChiSquaredDistribution(RandomGenerator randomGenerator, double d, double d2) {
        super(randomGenerator);
        this.gamma = new GammaDistribution(d / 2.0d, 2.0d);
        this.solverAbsoluteAccuracy = d2;
    }

    public double getDegreesOfFreedom() {
        return this.gamma.getShape() * 2.0d;
    }

    public double density(double d) {
        return this.gamma.density(d);
    }

    public double cumulativeProbability(double d) {
        return this.gamma.cumulativeProbability(d);
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        return getDegreesOfFreedom();
    }

    public double getNumericalVariance() {
        return 2.0d * getDegreesOfFreedom();
    }
}
