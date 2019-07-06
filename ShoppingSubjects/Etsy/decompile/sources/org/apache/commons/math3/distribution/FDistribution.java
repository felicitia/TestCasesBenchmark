package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.util.FastMath;

public class FDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = -8516354193418641566L;
    private final double denominatorDegreesOfFreedom;
    private final double numeratorDegreesOfFreedom;
    private double numericalVariance;
    private boolean numericalVarianceIsCalculated;
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
        return false;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public FDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, 1.0E-9d);
    }

    public FDistribution(double d, double d2, double d3) throws NotStrictlyPositiveException {
        this(new Well19937c(), d, d2, d3);
    }

    public FDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NotStrictlyPositiveException {
        super(randomGenerator);
        this.numericalVariance = Double.NaN;
        this.numericalVarianceIsCalculated = false;
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, Double.valueOf(d));
        } else if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, Double.valueOf(d2));
        } else {
            this.numeratorDegreesOfFreedom = d;
            this.denominatorDegreesOfFreedom = d2;
            this.solverAbsoluteAccuracy = d3;
        }
    }

    public double density(double d) {
        double d2 = this.numeratorDegreesOfFreedom / 2.0d;
        double d3 = this.denominatorDegreesOfFreedom / 2.0d;
        double log = FastMath.log(d);
        double log2 = FastMath.log(this.numeratorDegreesOfFreedom);
        double log3 = FastMath.log(this.denominatorDegreesOfFreedom);
        double log4 = FastMath.log((this.numeratorDegreesOfFreedom * d) + this.denominatorDegreesOfFreedom);
        return FastMath.exp(((((((log2 * d2) + (d2 * log)) - log) + (log3 * d3)) - (d2 * log4)) - (log4 * d3)) - Beta.logBeta(d2, d3));
    }

    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        double d2 = this.numeratorDegreesOfFreedom;
        double d3 = this.denominatorDegreesOfFreedom;
        double d4 = d * d2;
        return Beta.regularizedBeta(d4 / (d3 + d4), 0.5d * d2, 0.5d * d3);
    }

    public double getNumeratorDegreesOfFreedom() {
        return this.numeratorDegreesOfFreedom;
    }

    public double getDenominatorDegreesOfFreedom() {
        return this.denominatorDegreesOfFreedom;
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        double denominatorDegreesOfFreedom2 = getDenominatorDegreesOfFreedom();
        if (denominatorDegreesOfFreedom2 > 2.0d) {
            return denominatorDegreesOfFreedom2 / (denominatorDegreesOfFreedom2 - 2.0d);
        }
        return Double.NaN;
    }

    public double getNumericalVariance() {
        if (!this.numericalVarianceIsCalculated) {
            this.numericalVariance = calculateNumericalVariance();
            this.numericalVarianceIsCalculated = true;
        }
        return this.numericalVariance;
    }

    /* access modifiers changed from: protected */
    public double calculateNumericalVariance() {
        double denominatorDegreesOfFreedom2 = getDenominatorDegreesOfFreedom();
        if (denominatorDegreesOfFreedom2 <= 4.0d) {
            return Double.NaN;
        }
        double numeratorDegreesOfFreedom2 = getNumeratorDegreesOfFreedom();
        double d = denominatorDegreesOfFreedom2 - 2.0d;
        return (((denominatorDegreesOfFreedom2 * denominatorDegreesOfFreedom2) * 2.0d) * ((numeratorDegreesOfFreedom2 + denominatorDegreesOfFreedom2) - 2.0d)) / ((numeratorDegreesOfFreedom2 * (d * d)) * (denominatorDegreesOfFreedom2 - 4.0d));
    }
}
