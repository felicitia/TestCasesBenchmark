package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class CauchyDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 8589540077390120676L;
    private final double median;
    private final double scale;
    private final double solverAbsoluteAccuracy;

    public double getNumericalMean() {
        return Double.NaN;
    }

    public double getNumericalVariance() {
        return Double.NaN;
    }

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

    public CauchyDistribution() {
        this(0.0d, 1.0d);
    }

    public CauchyDistribution(double d, double d2) {
        this(d, d2, 1.0E-9d);
    }

    public CauchyDistribution(double d, double d2, double d3) {
        this(new Well19937c(), d, d2, d3);
    }

    public CauchyDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) {
        super(randomGenerator);
        if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(d2));
        }
        this.scale = d2;
        this.median = d;
        this.solverAbsoluteAccuracy = d3;
    }

    public double cumulativeProbability(double d) {
        return 0.5d + (FastMath.atan((d - this.median) / this.scale) / 3.141592653589793d);
    }

    public double getMedian() {
        return this.median;
    }

    public double getScale() {
        return this.scale;
    }

    public double density(double d) {
        double d2 = d - this.median;
        return 0.3183098861837907d * (this.scale / ((d2 * d2) + (this.scale * this.scale)));
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(1));
        } else if (d == 0.0d) {
            return Double.NEGATIVE_INFINITY;
        } else {
            if (d == 1.0d) {
                return Double.POSITIVE_INFINITY;
            }
            return this.median + (this.scale * FastMath.tan(3.141592653589793d * (d - 0.5d)));
        }
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }
}
