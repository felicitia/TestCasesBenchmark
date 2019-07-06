package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

public class PoissonDistribution extends AbstractIntegerDistribution {
    public static final double DEFAULT_EPSILON = 1.0E-12d;
    public static final int DEFAULT_MAX_ITERATIONS = 10000000;
    private static final long serialVersionUID = -3349935121172596109L;
    private final double epsilon;
    private final ExponentialDistribution exponential;
    private final int maxIterations;
    private final double mean;
    private final NormalDistribution normal;

    public int getSupportLowerBound() {
        return 0;
    }

    public int getSupportUpperBound() {
        return Integer.MAX_VALUE;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public PoissonDistribution(double d) throws NotStrictlyPositiveException {
        this(d, 1.0E-12d, DEFAULT_MAX_ITERATIONS);
    }

    public PoissonDistribution(double d, double d2, int i) throws NotStrictlyPositiveException {
        this(new Well19937c(), d, d2, i);
    }

    public PoissonDistribution(RandomGenerator randomGenerator, double d, double d2, int i) throws NotStrictlyPositiveException {
        double d3 = d;
        super(randomGenerator);
        if (d3 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(d));
        }
        this.mean = d3;
        this.epsilon = d2;
        this.maxIterations = i;
        NormalDistribution normalDistribution = new NormalDistribution(randomGenerator, d3, FastMath.sqrt(d), 1.0E-9d);
        this.normal = normalDistribution;
        ExponentialDistribution exponentialDistribution = new ExponentialDistribution(randomGenerator, 1.0d, 1.0E-9d);
        this.exponential = exponentialDistribution;
    }

    public PoissonDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, DEFAULT_MAX_ITERATIONS);
    }

    public PoissonDistribution(double d, int i) {
        this(d, 1.0E-12d, i);
    }

    public double getMean() {
        return this.mean;
    }

    public double probability(int i) {
        if (i < 0 || i == Integer.MAX_VALUE) {
            return 0.0d;
        }
        if (i == 0) {
            return FastMath.exp(-this.mean);
        }
        double d = (double) i;
        return FastMath.exp((-SaddlePointExpansion.getStirlingError(d)) - SaddlePointExpansion.getDeviancePart(d, this.mean)) / FastMath.sqrt(6.283185307179586d * d);
    }

    public double cumulativeProbability(int i) {
        if (i < 0) {
            return 0.0d;
        }
        if (i == Integer.MAX_VALUE) {
            return 1.0d;
        }
        return Gamma.regularizedGammaQ(((double) i) + 1.0d, this.mean, this.epsilon, this.maxIterations);
    }

    public double normalApproximateProbability(int i) {
        return this.normal.cumulativeProbability(((double) i) + 0.5d);
    }

    public double getNumericalMean() {
        return getMean();
    }

    public double getNumericalVariance() {
        return getMean();
    }

    public int sample() {
        return (int) FastMath.min(nextPoisson(this.mean), 2147483647L);
    }

    private long nextPoisson(double d) {
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        double d8;
        PoissonDistribution poissonDistribution = this;
        double d9 = d;
        long j = 0;
        double d10 = 1.0d;
        if (d9 < 40.0d) {
            double exp = FastMath.exp(-d9);
            while (((double) j) < 1000.0d * d9) {
                d10 *= poissonDistribution.random.nextDouble();
                if (d10 < exp) {
                    return j;
                }
                j++;
            }
            return j;
        }
        double floor = FastMath.floor(d);
        double d11 = d9 - floor;
        double log = FastMath.log(floor);
        double factorialLog = ArithmeticUtils.factorialLog((int) floor);
        if (d11 >= Double.MIN_VALUE) {
            j = poissonDistribution.nextPoisson(d11);
        }
        double sqrt = FastMath.sqrt(FastMath.log(((32.0d * floor) / 3.141592653589793d) + 1.0d) * floor);
        double d12 = sqrt / 2.0d;
        double d13 = 2.0d * floor;
        double d14 = d13 + sqrt;
        double sqrt2 = FastMath.sqrt(3.141592653589793d * d14) * FastMath.exp(0.0d * floor);
        double d15 = d14 / sqrt;
        long j2 = j;
        double exp2 = FastMath.exp(((-sqrt) * (1.0d + sqrt)) / d14) * d15;
        double d16 = sqrt2 + exp2 + 1.0d;
        double d17 = sqrt2 / d16;
        double d18 = exp2 / d16;
        double d19 = 1.0d / (8.0d * floor);
        while (true) {
            double nextDouble = poissonDistribution.random.nextDouble();
            if (nextDouble <= d17) {
                double nextGaussian = poissonDistribution.random.nextGaussian();
                d5 = factorialLog;
                d7 = (FastMath.sqrt(floor + d12) * nextGaussian) - 0.5d;
                if (d7 <= sqrt) {
                    d4 = log;
                    if (d7 < (-floor)) {
                        factorialLog = d5;
                        log = d4;
                    } else {
                        d3 = d7 < 0.0d ? FastMath.floor(d7) : FastMath.ceil(d7);
                        d2 = d18;
                        d8 = ((-poissonDistribution.exponential.sample()) - ((nextGaussian * nextGaussian) / 2.0d)) + d19;
                        d6 = 1.0d;
                    }
                } else {
                    factorialLog = d5;
                }
            } else {
                d4 = log;
                d5 = factorialLog;
                if (nextDouble > d17 + d18) {
                    break;
                }
                d7 = sqrt + (poissonDistribution.exponential.sample() * d15);
                d2 = d18;
                d6 = 1.0d;
                d8 = (-poissonDistribution.exponential.sample()) - (((d7 + 1.0d) * sqrt) / d14);
                d3 = FastMath.ceil(d7);
            }
            int i = d7 < 0.0d ? 1 : 0;
            double d20 = d3 + d6;
            double d21 = (d3 * d20) / d13;
            double d22 = sqrt;
            if (d8 < (-d21) && i == 0) {
                floor += d3;
                break;
            }
            double d23 = ((((2.0d * d3) + 1.0d) / (6.0d * floor)) - 1.0d) * d21;
            double d24 = d15;
            if (d8 < d23 - ((d21 * d21) / (3.0d * ((((double) i) * d20) + floor)))) {
                floor += d3;
                break;
            }
            if (d8 <= d23) {
                double d25 = d3 + floor;
                if (d8 < ((d3 * d4) - ArithmeticUtils.factorialLog((int) d25)) + d5) {
                    floor = d25;
                    break;
                }
            }
            factorialLog = d5;
            log = d4;
            d18 = d2;
            sqrt = d22;
            d15 = d24;
            poissonDistribution = this;
        }
        return j2 + ((long) floor);
    }
}
