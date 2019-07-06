package org.apache.commons.math3.stat;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;

public final class StatUtils {
    private static final GeometricMean GEOMETRIC_MEAN = new GeometricMean();
    private static final UnivariateStatistic MAX = new Max();
    private static final UnivariateStatistic MEAN = new Mean();
    private static final UnivariateStatistic MIN = new Min();
    private static final Percentile PERCENTILE = new Percentile();
    private static final UnivariateStatistic PRODUCT = new Product();
    private static final UnivariateStatistic SUM = new Sum();
    private static final UnivariateStatistic SUM_OF_LOGS = new SumOfLogs();
    private static final UnivariateStatistic SUM_OF_SQUARES = new SumOfSquares();
    private static final Variance VARIANCE = new Variance();

    private StatUtils() {
    }

    public static double sum(double[] dArr) throws MathIllegalArgumentException {
        return SUM.evaluate(dArr);
    }

    public static double sum(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return SUM.evaluate(dArr, i, i2);
    }

    public static double sumSq(double[] dArr) throws MathIllegalArgumentException {
        return SUM_OF_SQUARES.evaluate(dArr);
    }

    public static double sumSq(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return SUM_OF_SQUARES.evaluate(dArr, i, i2);
    }

    public static double product(double[] dArr) throws MathIllegalArgumentException {
        return PRODUCT.evaluate(dArr);
    }

    public static double product(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return PRODUCT.evaluate(dArr, i, i2);
    }

    public static double sumLog(double[] dArr) throws MathIllegalArgumentException {
        return SUM_OF_LOGS.evaluate(dArr);
    }

    public static double sumLog(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return SUM_OF_LOGS.evaluate(dArr, i, i2);
    }

    public static double mean(double[] dArr) throws MathIllegalArgumentException {
        return MEAN.evaluate(dArr);
    }

    public static double mean(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return MEAN.evaluate(dArr, i, i2);
    }

    public static double geometricMean(double[] dArr) throws MathIllegalArgumentException {
        return GEOMETRIC_MEAN.evaluate(dArr);
    }

    public static double geometricMean(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return GEOMETRIC_MEAN.evaluate(dArr, i, i2);
    }

    public static double variance(double[] dArr) throws MathIllegalArgumentException {
        return VARIANCE.evaluate(dArr);
    }

    public static double variance(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return VARIANCE.evaluate(dArr, i, i2);
    }

    public static double variance(double[] dArr, double d, int i, int i2) throws MathIllegalArgumentException {
        return VARIANCE.evaluate(dArr, d, i, i2);
    }

    public static double variance(double[] dArr, double d) throws MathIllegalArgumentException {
        return VARIANCE.evaluate(dArr, d);
    }

    public static double populationVariance(double[] dArr) throws MathIllegalArgumentException {
        return new Variance(false).evaluate(dArr);
    }

    public static double populationVariance(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return new Variance(false).evaluate(dArr, i, i2);
    }

    public static double populationVariance(double[] dArr, double d, int i, int i2) throws MathIllegalArgumentException {
        return new Variance(false).evaluate(dArr, d, i, i2);
    }

    public static double populationVariance(double[] dArr, double d) throws MathIllegalArgumentException {
        return new Variance(false).evaluate(dArr, d);
    }

    public static double max(double[] dArr) throws MathIllegalArgumentException {
        return MAX.evaluate(dArr);
    }

    public static double max(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return MAX.evaluate(dArr, i, i2);
    }

    public static double min(double[] dArr) throws MathIllegalArgumentException {
        return MIN.evaluate(dArr);
    }

    public static double min(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return MIN.evaluate(dArr, i, i2);
    }

    public static double percentile(double[] dArr, double d) throws MathIllegalArgumentException {
        return PERCENTILE.evaluate(dArr, d);
    }

    public static double percentile(double[] dArr, int i, int i2, double d) throws MathIllegalArgumentException {
        return PERCENTILE.evaluate(dArr, i, i2, d);
    }

    public static double sumDifference(double[] dArr, double[] dArr2) throws DimensionMismatchException, NoDataException {
        int length = dArr.length;
        if (length != dArr2.length) {
            throw new DimensionMismatchException(length, dArr2.length);
        } else if (length <= 0) {
            throw new NoDataException(LocalizedFormats.INSUFFICIENT_DIMENSION);
        } else {
            double d = 0.0d;
            for (int i = 0; i < length; i++) {
                d += dArr[i] - dArr2[i];
            }
            return d;
        }
    }

    public static double meanDifference(double[] dArr, double[] dArr2) throws DimensionMismatchException, NoDataException {
        return sumDifference(dArr, dArr2) / ((double) dArr.length);
    }

    public static double varianceDifference(double[] dArr, double[] dArr2, double d) throws DimensionMismatchException, NumberIsTooSmallException {
        int length = dArr.length;
        if (length != dArr2.length) {
            throw new DimensionMismatchException(length, dArr2.length);
        } else if (length < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(length), Integer.valueOf(2), true);
        } else {
            double d2 = 0.0d;
            double d3 = 0.0d;
            for (int i = 0; i < length; i++) {
                double d4 = (dArr[i] - dArr2[i]) - d;
                d2 += d4 * d4;
                d3 += d4;
            }
            return (d2 - ((d3 * d3) / ((double) length))) / ((double) (length - 1));
        }
    }

    public static double[] normalize(double[] dArr) {
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        for (double addValue : dArr) {
            descriptiveStatistics.addValue(addValue);
        }
        double mean = descriptiveStatistics.getMean();
        double standardDeviation = descriptiveStatistics.getStandardDeviation();
        double[] dArr2 = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = (dArr[i] - mean) / standardDeviation;
        }
        return dArr2;
    }
}
