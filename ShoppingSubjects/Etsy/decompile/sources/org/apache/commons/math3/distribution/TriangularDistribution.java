package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class TriangularDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = 20120112;
    private final double a;
    private final double b;
    private final double c;
    private final double solverAbsoluteAccuracy;

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    public TriangularDistribution(double d, double d2, double d3) throws NumberIsTooLargeException, NumberIsTooSmallException {
        this(new Well19937c(), d, d2, d3);
    }

    public TriangularDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NumberIsTooLargeException, NumberIsTooSmallException {
        super(randomGenerator);
        if (d >= d3) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(d), Double.valueOf(d3), false);
        } else if (d2 < d) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, Double.valueOf(d2), Double.valueOf(d), true);
        } else if (d2 > d3) {
            throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_TOO_LARGE, Double.valueOf(d2), Double.valueOf(d3), true);
        } else {
            this.a = d;
            this.c = d2;
            this.b = d3;
            this.solverAbsoluteAccuracy = FastMath.max(FastMath.ulp(d), FastMath.ulp(d3));
        }
    }

    public double getMode() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double density(double d) {
        if (d < this.a) {
            return 0.0d;
        }
        if (this.a <= d && d < this.c) {
            return (2.0d * (d - this.a)) / ((this.b - this.a) * (this.c - this.a));
        }
        if (d == this.c) {
            return 2.0d / (this.b - this.a);
        }
        if (this.c >= d || d > this.b) {
            return 0.0d;
        }
        return (2.0d * (this.b - d)) / ((this.b - this.a) * (this.b - this.c));
    }

    public double cumulativeProbability(double d) {
        if (d < this.a) {
            return 0.0d;
        }
        if (this.a <= d && d < this.c) {
            return ((d - this.a) * (d - this.a)) / ((this.b - this.a) * (this.c - this.a));
        }
        if (d == this.c) {
            return (this.c - this.a) / (this.b - this.a);
        }
        if (this.c >= d || d > this.b) {
            return 1.0d;
        }
        return 1.0d - (((this.b - d) * (this.b - d)) / ((this.b - this.a) * (this.b - this.c)));
    }

    public double getNumericalMean() {
        return ((this.a + this.b) + this.c) / 3.0d;
    }

    public double getNumericalVariance() {
        return ((((((this.a * this.a) + (this.b * this.b)) + (this.c * this.c)) - (this.a * this.b)) - (this.a * this.c)) - (this.b * this.c)) / 18.0d;
    }

    public double getSupportLowerBound() {
        return this.a;
    }

    public double getSupportUpperBound() {
        return this.b;
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(1));
        } else if (d == 0.0d) {
            return this.a;
        } else {
            if (d == 1.0d) {
                return this.b;
            }
            if (d < (this.c - this.a) / (this.b - this.a)) {
                return this.a + FastMath.sqrt(d * (this.b - this.a) * (this.c - this.a));
            }
            return this.b - FastMath.sqrt(((1.0d - d) * (this.b - this.a)) * (this.b - this.c));
        }
    }
}
