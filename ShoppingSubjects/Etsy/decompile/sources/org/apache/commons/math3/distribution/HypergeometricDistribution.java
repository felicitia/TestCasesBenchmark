package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class HypergeometricDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = -436928820673516179L;
    private final int numberOfSuccesses;
    private double numericalVariance;
    private boolean numericalVarianceIsCalculated;
    private final int populationSize;
    private final int sampleSize;

    public boolean isSupportConnected() {
        return true;
    }

    public HypergeometricDistribution(int i, int i2, int i3) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        this(new Well19937c(), i, i2, i3);
    }

    public HypergeometricDistribution(RandomGenerator randomGenerator, int i, int i2, int i3) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        super(randomGenerator);
        this.numericalVariance = Double.NaN;
        this.numericalVarianceIsCalculated = false;
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.POPULATION_SIZE, Integer.valueOf(i));
        } else if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_SUCCESSES, Integer.valueOf(i2));
        } else if (i3 < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(i3));
        } else if (i2 > i) {
            throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_OF_SUCCESS_LARGER_THAN_POPULATION_SIZE, Integer.valueOf(i2), Integer.valueOf(i), true);
        } else if (i3 > i) {
            throw new NumberIsTooLargeException(LocalizedFormats.SAMPLE_SIZE_LARGER_THAN_POPULATION_SIZE, Integer.valueOf(i3), Integer.valueOf(i), true);
        } else {
            this.numberOfSuccesses = i2;
            this.populationSize = i;
            this.sampleSize = i3;
        }
    }

    public double cumulativeProbability(int i) {
        int[] domain = getDomain(this.populationSize, this.numberOfSuccesses, this.sampleSize);
        if (i < domain[0]) {
            return 0.0d;
        }
        if (i >= domain[1]) {
            return 1.0d;
        }
        return innerCumulativeProbability(domain[0], i, 1);
    }

    private int[] getDomain(int i, int i2, int i3) {
        return new int[]{getLowerDomain(i, i2, i3), getUpperDomain(i2, i3)};
    }

    private int getLowerDomain(int i, int i2, int i3) {
        return FastMath.max(0, i2 - (i - i3));
    }

    public int getNumberOfSuccesses() {
        return this.numberOfSuccesses;
    }

    public int getPopulationSize() {
        return this.populationSize;
    }

    public int getSampleSize() {
        return this.sampleSize;
    }

    private int getUpperDomain(int i, int i2) {
        return FastMath.min(i2, i);
    }

    public double probability(int i) {
        int[] domain = getDomain(this.populationSize, this.numberOfSuccesses, this.sampleSize);
        if (i < domain[0] || i > domain[1]) {
            return 0.0d;
        }
        double d = ((double) this.sampleSize) / ((double) this.populationSize);
        double d2 = ((double) (this.populationSize - this.sampleSize)) / ((double) this.populationSize);
        return FastMath.exp((SaddlePointExpansion.logBinomialProbability(i, this.numberOfSuccesses, d, d2) + SaddlePointExpansion.logBinomialProbability(this.sampleSize - i, this.populationSize - this.numberOfSuccesses, d, d2)) - SaddlePointExpansion.logBinomialProbability(this.sampleSize, this.populationSize, d, d2));
    }

    public double upperCumulativeProbability(int i) {
        int[] domain = getDomain(this.populationSize, this.numberOfSuccesses, this.sampleSize);
        if (i <= domain[0]) {
            return 1.0d;
        }
        if (i > domain[1]) {
            return 0.0d;
        }
        return innerCumulativeProbability(domain[1], i, -1);
    }

    private double innerCumulativeProbability(int i, int i2, int i3) {
        double probability = probability(i);
        while (i != i2) {
            i += i3;
            probability += probability(i);
        }
        return probability;
    }

    public double getNumericalMean() {
        return ((double) (getSampleSize() * getNumberOfSuccesses())) / ((double) getPopulationSize());
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
        double populationSize2 = (double) getPopulationSize();
        double numberOfSuccesses2 = (double) getNumberOfSuccesses();
        double sampleSize2 = (double) getSampleSize();
        return (((sampleSize2 * numberOfSuccesses2) * (populationSize2 - sampleSize2)) * (populationSize2 - numberOfSuccesses2)) / ((populationSize2 * populationSize2) * (populationSize2 - 1.0d));
    }

    public int getSupportLowerBound() {
        return FastMath.max(0, (getSampleSize() + getNumberOfSuccesses()) - getPopulationSize());
    }

    public int getSupportUpperBound() {
        return FastMath.min(getNumberOfSuccesses(), getSampleSize());
    }
}
