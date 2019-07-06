package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class GammaDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 20120524;
    private final double densityPrefactor1;
    private final double densityPrefactor2;
    private final double maxLogY;
    private final double minY;
    private final double scale;
    private final double shape;
    private final double shiftedShape;
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

    public GammaDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, 1.0E-9d);
    }

    public GammaDistribution(double d, double d2, double d3) throws NotStrictlyPositiveException {
        this(new Well19937c(), d, d2, d3);
    }

    public GammaDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NotStrictlyPositiveException {
        super(randomGenerator);
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, Double.valueOf(d));
        } else if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(d2));
        } else {
            this.shape = d;
            this.scale = d2;
            this.solverAbsoluteAccuracy = d3;
            double d4 = 4.7421875d + d;
            this.shiftedShape = 0.5d + d4;
            this.densityPrefactor2 = (FastMath.sqrt(2.718281828459045d / (6.283185307179586d * this.shiftedShape)) * d) / Gamma.lanczos(d);
            this.densityPrefactor1 = (this.densityPrefactor2 / d2) * FastMath.pow(this.shiftedShape, -d) * FastMath.exp(d4);
            this.minY = d4 - FastMath.log(Double.MAX_VALUE);
            this.maxLogY = FastMath.log(Double.MAX_VALUE) / (d - 1.0d);
        }
    }

    @Deprecated
    public double getAlpha() {
        return this.shape;
    }

    public double getShape() {
        return this.shape;
    }

    @Deprecated
    public double getBeta() {
        return this.scale;
    }

    public double getScale() {
        return this.scale;
    }

    public double density(double d) {
        if (d < 0.0d) {
            return 0.0d;
        }
        double d2 = d / this.scale;
        if (d2 > this.minY && FastMath.log(d2) < this.maxLogY) {
            return this.densityPrefactor1 * FastMath.exp(-d2) * FastMath.pow(d2, this.shape - 1.0d);
        }
        double d3 = (d2 - this.shiftedShape) / this.shiftedShape;
        return (this.densityPrefactor2 / d) * FastMath.exp((((-d2) * 5.2421875d) / this.shiftedShape) + 4.7421875d + (this.shape * (FastMath.log1p(d3) - d3)));
    }

    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        return Gamma.regularizedGammaP(this.shape, d / this.scale);
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        return this.shape * this.scale;
    }

    public double getNumericalVariance() {
        return this.shape * this.scale * this.scale;
    }

    public double sample() {
        if (this.shape < 1.0d) {
            while (true) {
                double d = (this.shape / 2.718281828459045d) + 1.0d;
                double nextDouble = this.random.nextDouble() * d;
                if (nextDouble <= 1.0d) {
                    double pow = FastMath.pow(nextDouble, 1.0d / this.shape);
                    if (this.random.nextDouble() <= FastMath.exp(-pow)) {
                        return this.scale * pow;
                    }
                } else {
                    double log = -1.0d * FastMath.log((d - nextDouble) / this.shape);
                    if (this.random.nextDouble() <= FastMath.pow(log, this.shape - 1.0d)) {
                        return this.scale * log;
                    }
                }
            }
        } else {
            double d2 = this.shape - 0.3333333333333333d;
            double sqrt = 1.0d / (3.0d * FastMath.sqrt(d2));
            while (true) {
                double nextGaussian = this.random.nextGaussian();
                double d3 = (sqrt * nextGaussian) + 1.0d;
                double d4 = d3 * d3 * d3;
                if (d4 > 0.0d) {
                    double d5 = nextGaussian * nextGaussian;
                    double nextDouble2 = this.random.nextDouble();
                    if (nextDouble2 < 1.0d - ((0.0331d * d5) * d5)) {
                        return this.scale * d2 * d4;
                    }
                    if (FastMath.log(nextDouble2) < (0.5d * d5) + (((1.0d - d4) + FastMath.log(d4)) * d2)) {
                        return this.scale * d2 * d4;
                    }
                }
            }
        }
    }
}
