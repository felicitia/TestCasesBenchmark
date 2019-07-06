package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class BetaDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = -1221965979403477668L;
    private final double alpha;
    private final double beta;
    private final double solverAbsoluteAccuracy;
    private double z;

    public double getSupportLowerBound() {
        return 0.0d;
    }

    public double getSupportUpperBound() {
        return 1.0d;
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

    public BetaDistribution(double d, double d2) {
        this(d, d2, 1.0E-9d);
    }

    public BetaDistribution(double d, double d2, double d3) {
        this(new Well19937c(), d, d2, d3);
    }

    public BetaDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) {
        super(randomGenerator);
        this.alpha = d;
        this.beta = d2;
        this.z = Double.NaN;
        this.solverAbsoluteAccuracy = d3;
    }

    public double getAlpha() {
        return this.alpha;
    }

    public double getBeta() {
        return this.beta;
    }

    private void recomputeZ() {
        if (Double.isNaN(this.z)) {
            this.z = (Gamma.logGamma(this.alpha) + Gamma.logGamma(this.beta)) - Gamma.logGamma(this.alpha + this.beta);
        }
    }

    public double density(double d) {
        recomputeZ();
        if (d < 0.0d || d > 1.0d) {
            return 0.0d;
        }
        if (d == 0.0d) {
            if (this.alpha >= 1.0d) {
                return 0.0d;
            }
            throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_0_FOR_SOME_ALPHA, Double.valueOf(this.alpha), Integer.valueOf(1), false);
        } else if (d != 1.0d) {
            return FastMath.exp((((this.alpha - 1.0d) * FastMath.log(d)) + ((this.beta - 1.0d) * FastMath.log1p(-d))) - this.z);
        } else if (this.beta >= 1.0d) {
            return 0.0d;
        } else {
            throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_1_FOR_SOME_BETA, Double.valueOf(this.beta), Integer.valueOf(1), false);
        }
    }

    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        if (d >= 1.0d) {
            return 1.0d;
        }
        return Beta.regularizedBeta(d, this.alpha, this.beta);
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        double alpha2 = getAlpha();
        return alpha2 / (getBeta() + alpha2);
    }

    public double getNumericalVariance() {
        double alpha2 = getAlpha();
        double beta2 = getBeta();
        double d = alpha2 + beta2;
        return (alpha2 * beta2) / ((d * d) * (d + 1.0d));
    }
}
