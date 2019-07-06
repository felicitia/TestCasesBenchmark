package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.ResizableDoubleArray;

public class ExponentialDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final double[] EXPONENTIAL_SA_QI;
    private static final long serialVersionUID = 2401296428283614780L;
    private final double mean;
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

    static {
        double log = FastMath.log(2.0d);
        ResizableDoubleArray resizableDoubleArray = new ResizableDoubleArray(20);
        double d = 0.0d;
        int i = 1;
        while (d < 1.0d) {
            d += FastMath.pow(log, i) / ((double) ArithmeticUtils.factorial(i));
            resizableDoubleArray.addElement(d);
            i++;
        }
        EXPONENTIAL_SA_QI = resizableDoubleArray.getElements();
    }

    public ExponentialDistribution(double d) {
        this(d, 1.0E-9d);
    }

    public ExponentialDistribution(double d, double d2) {
        this(new Well19937c(), d, d2);
    }

    public ExponentialDistribution(RandomGenerator randomGenerator, double d, double d2) throws NotStrictlyPositiveException {
        super(randomGenerator);
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(d));
        }
        this.mean = d;
        this.solverAbsoluteAccuracy = d2;
    }

    public double getMean() {
        return this.mean;
    }

    public double density(double d) {
        if (d < 0.0d) {
            return 0.0d;
        }
        return FastMath.exp((-d) / this.mean) / this.mean;
    }

    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        return 1.0d - FastMath.exp((-d) / this.mean);
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(0.0d), Double.valueOf(1.0d));
        } else if (d == 1.0d) {
            return Double.POSITIVE_INFINITY;
        } else {
            return FastMath.log(1.0d - d) * (-this.mean);
        }
    }

    public double sample() {
        double nextDouble = this.random.nextDouble();
        double d = 0.0d;
        while (nextDouble < 0.5d) {
            d += EXPONENTIAL_SA_QI[0];
            nextDouble *= 2.0d;
        }
        double d2 = nextDouble + (nextDouble - 1.0d);
        if (d2 <= EXPONENTIAL_SA_QI[0]) {
            return this.mean * (d + d2);
        }
        double nextDouble2 = this.random.nextDouble();
        int i = 0;
        do {
            i++;
            double nextDouble3 = this.random.nextDouble();
            if (nextDouble3 < nextDouble2) {
                nextDouble2 = nextDouble3;
            }
        } while (d2 > EXPONENTIAL_SA_QI[i]);
        return this.mean * (d + (nextDouble2 * EXPONENTIAL_SA_QI[0]));
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        return getMean();
    }

    public double getNumericalVariance() {
        double mean2 = getMean();
        return mean2 * mean2;
    }
}
