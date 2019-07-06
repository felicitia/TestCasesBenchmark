package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class ZipfDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = -140627372283420404L;
    private final double exponent;
    private final int numberOfElements;
    private double numericalMean;
    private boolean numericalMeanIsCalculated;
    private double numericalVariance;
    private boolean numericalVarianceIsCalculated;

    public int getSupportLowerBound() {
        return 1;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public ZipfDistribution(int i, double d) {
        this(new Well19937c(), i, d);
    }

    public ZipfDistribution(RandomGenerator randomGenerator, int i, double d) throws NotStrictlyPositiveException {
        super(randomGenerator);
        this.numericalMean = Double.NaN;
        this.numericalMeanIsCalculated = false;
        this.numericalVariance = Double.NaN;
        this.numericalVarianceIsCalculated = false;
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, Integer.valueOf(i));
        } else if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.EXPONENT, Double.valueOf(d));
        } else {
            this.numberOfElements = i;
            this.exponent = d;
        }
    }

    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    public double getExponent() {
        return this.exponent;
    }

    public double probability(int i) {
        if (i <= 0 || i > this.numberOfElements) {
            return 0.0d;
        }
        return (1.0d / FastMath.pow((double) i, this.exponent)) / generalizedHarmonic(this.numberOfElements, this.exponent);
    }

    public double cumulativeProbability(int i) {
        if (i <= 0) {
            return 0.0d;
        }
        if (i >= this.numberOfElements) {
            return 1.0d;
        }
        return generalizedHarmonic(i, this.exponent) / generalizedHarmonic(this.numberOfElements, this.exponent);
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
        int numberOfElements2 = getNumberOfElements();
        double exponent2 = getExponent();
        return generalizedHarmonic(numberOfElements2, exponent2 - 1.0d) / generalizedHarmonic(numberOfElements2, exponent2);
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
        int numberOfElements2 = getNumberOfElements();
        double exponent2 = getExponent();
        double generalizedHarmonic = generalizedHarmonic(numberOfElements2, exponent2 - 2.0d);
        double generalizedHarmonic2 = generalizedHarmonic(numberOfElements2, exponent2 - 1.0d);
        double generalizedHarmonic3 = generalizedHarmonic(numberOfElements2, exponent2);
        return (generalizedHarmonic / generalizedHarmonic3) - ((generalizedHarmonic2 * generalizedHarmonic2) / (generalizedHarmonic3 * generalizedHarmonic3));
    }

    private double generalizedHarmonic(int i, double d) {
        double d2 = 0.0d;
        while (i > 0) {
            d2 += 1.0d / FastMath.pow((double) i, d);
            i--;
        }
        return d2;
    }

    public int getSupportUpperBound() {
        return getNumberOfElements();
    }
}
