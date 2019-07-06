package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class WeibullDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 8589540077390120676L;
    private double numericalMean;
    private boolean numericalMeanIsCalculated;
    private double numericalVariance;
    private boolean numericalVarianceIsCalculated;
    private final double scale;
    private final double shape;
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

    public WeibullDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, 1.0E-9d);
    }

    public WeibullDistribution(double d, double d2, double d3) {
        this(new Well19937c(), d, d2, d3);
    }

    public WeibullDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NotStrictlyPositiveException {
        super(randomGenerator);
        this.numericalMean = Double.NaN;
        this.numericalMeanIsCalculated = false;
        this.numericalVariance = Double.NaN;
        this.numericalVarianceIsCalculated = false;
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, Double.valueOf(d));
        } else if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(d2));
        } else {
            this.scale = d2;
            this.shape = d;
            this.solverAbsoluteAccuracy = d3;
        }
    }

    public double getShape() {
        return this.shape;
    }

    public double getScale() {
        return this.scale;
    }

    public double density(double d) {
        if (d < 0.0d) {
            return 0.0d;
        }
        double d2 = d / this.scale;
        double pow = FastMath.pow(d2, this.shape - 1.0d);
        return (this.shape / this.scale) * pow * FastMath.exp(-(d2 * pow));
    }

    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        return 1.0d - FastMath.exp(-FastMath.pow(d / this.scale, this.shape));
    }

    public double inverseCumulativeProbability(double d) {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(0.0d), Double.valueOf(1.0d));
        } else if (d == 0.0d) {
            return 0.0d;
        } else {
            if (d == 1.0d) {
                return Double.POSITIVE_INFINITY;
            }
            return this.scale * FastMath.pow(-FastMath.log(1.0d - d), 1.0d / this.shape);
        }
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        if (!this.numericalMeanIsCalculated) {
            this.numericalMean = calculateNumericalMean();
            this.numericalMeanIsCalculated = true;
        }
        return this.numericalMean;
    }

    /* access modifiers changed from: protected */
    public double calculateNumericalMean() {
        return getScale() * FastMath.exp(Gamma.logGamma(1.0d + (1.0d / getShape())));
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
        double shape2 = getShape();
        double scale2 = getScale();
        double numericalMean2 = getNumericalMean();
        return ((scale2 * scale2) * FastMath.exp(Gamma.logGamma(1.0d + (2.0d / shape2)))) - (numericalMean2 * numericalMean2);
    }
}
