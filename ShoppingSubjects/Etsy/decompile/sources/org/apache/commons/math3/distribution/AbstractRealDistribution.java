package org.apache.commons.math3.distribution;

import java.io.Serializable;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractRealDistribution implements Serializable, RealDistribution {
    public static final double SOLVER_DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private static final long serialVersionUID = -38038050983108802L;
    protected final RandomGenerator random;
    @Deprecated
    protected RandomDataImpl randomData;
    private double solverAbsoluteAccuracy;

    public double probability(double d) {
        return 0.0d;
    }

    @Deprecated
    protected AbstractRealDistribution() {
        this.randomData = new RandomDataImpl();
        this.solverAbsoluteAccuracy = 1.0E-6d;
        this.random = null;
    }

    protected AbstractRealDistribution(RandomGenerator randomGenerator) {
        this.randomData = new RandomDataImpl();
        this.solverAbsoluteAccuracy = 1.0E-6d;
        this.random = randomGenerator;
    }

    @Deprecated
    public double cumulativeProbability(double d, double d2) throws NumberIsTooLargeException {
        return probability(d, d2);
    }

    public double probability(double d, double d2) {
        if (d <= d2) {
            return cumulativeProbability(d2) - cumulativeProbability(d);
        }
        throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Double.valueOf(d), Double.valueOf(d2), true);
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        final double d2 = d;
        boolean z = false;
        if (d2 < 0.0d || d2 > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(1));
        }
        double supportLowerBound = getSupportLowerBound();
        if (d2 == 0.0d) {
            return supportLowerBound;
        }
        double supportUpperBound = getSupportUpperBound();
        if (d2 == 1.0d) {
            return supportUpperBound;
        }
        double numericalMean = getNumericalMean();
        double sqrt = FastMath.sqrt(getNumericalVariance());
        if (!Double.isInfinite(numericalMean) && !Double.isNaN(numericalMean) && !Double.isInfinite(sqrt) && !Double.isNaN(sqrt)) {
            z = true;
        }
        if (supportLowerBound == Double.NEGATIVE_INFINITY) {
            if (z) {
                supportLowerBound = numericalMean - (FastMath.sqrt((1.0d - d2) / d2) * sqrt);
            } else {
                supportLowerBound = -1.0d;
                while (cumulativeProbability(supportLowerBound) >= d2) {
                    supportLowerBound *= 2.0d;
                }
            }
        }
        if (supportUpperBound == Double.POSITIVE_INFINITY) {
            if (z) {
                supportUpperBound = numericalMean + (sqrt * FastMath.sqrt(d2 / (1.0d - d2)));
            } else {
                supportUpperBound = 1.0d;
                while (cumulativeProbability(supportUpperBound) < d2) {
                    supportUpperBound *= 2.0d;
                }
            }
        }
        double d3 = supportUpperBound;
        double solve = UnivariateSolverUtils.solve(new UnivariateFunction() {
            public double value(double d) {
                return AbstractRealDistribution.this.cumulativeProbability(d) - d2;
            }
        }, supportLowerBound, d3, getSolverAbsoluteAccuracy());
        if (!isSupportConnected()) {
            double solverAbsoluteAccuracy2 = getSolverAbsoluteAccuracy();
            double d4 = solve - solverAbsoluteAccuracy2;
            if (d4 >= getSupportLowerBound()) {
                double cumulativeProbability = cumulativeProbability(solve);
                if (cumulativeProbability(d4) == cumulativeProbability) {
                    while (solve - supportLowerBound > solverAbsoluteAccuracy2) {
                        double d5 = 0.5d * (supportLowerBound + solve);
                        if (cumulativeProbability(d5) < cumulativeProbability) {
                            supportLowerBound = d5;
                        } else {
                            solve = d5;
                        }
                    }
                    return solve;
                }
            }
        }
        return solve;
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public void reseedRandomGenerator(long j) {
        this.random.setSeed(j);
        this.randomData.reSeed(j);
    }

    public double sample() {
        return inverseCumulativeProbability(this.random.nextDouble());
    }

    public double[] sample(int i) {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(i));
        }
        double[] dArr = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = sample();
        }
        return dArr;
    }
}
