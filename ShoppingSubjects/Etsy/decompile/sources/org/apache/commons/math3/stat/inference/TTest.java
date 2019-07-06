package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.util.FastMath;

public class TTest {
    /* access modifiers changed from: protected */
    public double df(double d, double d2, double d3, double d4) {
        double d5 = (d / d3) + (d2 / d4);
        return (d5 * d5) / (((d * d) / ((d3 * d3) * (d3 - 1.0d))) + ((d2 * d2) / ((d4 * d4) * (d4 - 1.0d))));
    }

    public double pairedT(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException {
        checkSampleData(dArr);
        checkSampleData(dArr2);
        double meanDifference = StatUtils.meanDifference(dArr, dArr2);
        return t(meanDifference, 0.0d, StatUtils.varianceDifference(dArr, dArr2, meanDifference), (double) dArr.length);
    }

    public double pairedTTest(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException {
        double meanDifference = StatUtils.meanDifference(dArr, dArr2);
        return tTest(meanDifference, 0.0d, StatUtils.varianceDifference(dArr, dArr2, meanDifference), (double) dArr.length);
    }

    public boolean pairedTTest(double[] dArr, double[] dArr2, double d) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        checkSignificanceLevel(d);
        return pairedTTest(dArr, dArr2) < d;
    }

    public double t(double d, double[] dArr) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(dArr);
        return t(StatUtils.mean(dArr), d, StatUtils.variance(dArr), (double) dArr.length);
    }

    public double t(double d, StatisticalSummary statisticalSummary) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(statisticalSummary);
        return t(statisticalSummary.getMean(), d, statisticalSummary.getVariance(), (double) statisticalSummary.getN());
    }

    public double homoscedasticT(double[] dArr, double[] dArr2) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(dArr);
        checkSampleData(dArr2);
        return homoscedasticT(StatUtils.mean(dArr), StatUtils.mean(dArr2), StatUtils.variance(dArr), StatUtils.variance(dArr2), (double) dArr.length, (double) dArr2.length);
    }

    public double t(double[] dArr, double[] dArr2) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(dArr);
        checkSampleData(dArr2);
        return t(StatUtils.mean(dArr), StatUtils.mean(dArr2), StatUtils.variance(dArr), StatUtils.variance(dArr2), (double) dArr.length, (double) dArr2.length);
    }

    public double t(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(statisticalSummary);
        checkSampleData(statisticalSummary2);
        return t(statisticalSummary.getMean(), statisticalSummary2.getMean(), statisticalSummary.getVariance(), statisticalSummary2.getVariance(), (double) statisticalSummary.getN(), (double) statisticalSummary2.getN());
    }

    public double homoscedasticT(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(statisticalSummary);
        checkSampleData(statisticalSummary2);
        return homoscedasticT(statisticalSummary.getMean(), statisticalSummary2.getMean(), statisticalSummary.getVariance(), statisticalSummary2.getVariance(), (double) statisticalSummary.getN(), (double) statisticalSummary2.getN());
    }

    public double tTest(double d, double[] dArr) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(dArr);
        return tTest(StatUtils.mean(dArr), d, StatUtils.variance(dArr), (double) dArr.length);
    }

    public boolean tTest(double d, double[] dArr, double d2) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        checkSignificanceLevel(d2);
        return tTest(d, dArr) < d2;
    }

    public double tTest(double d, StatisticalSummary statisticalSummary) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(statisticalSummary);
        return tTest(statisticalSummary.getMean(), d, statisticalSummary.getVariance(), (double) statisticalSummary.getN());
    }

    public boolean tTest(double d, StatisticalSummary statisticalSummary, double d2) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        checkSignificanceLevel(d2);
        return tTest(d, statisticalSummary) < d2;
    }

    public double tTest(double[] dArr, double[] dArr2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(dArr);
        checkSampleData(dArr2);
        return tTest(StatUtils.mean(dArr), StatUtils.mean(dArr2), StatUtils.variance(dArr), StatUtils.variance(dArr2), (double) dArr.length, (double) dArr2.length);
    }

    public double homoscedasticTTest(double[] dArr, double[] dArr2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(dArr);
        checkSampleData(dArr2);
        return homoscedasticTTest(StatUtils.mean(dArr), StatUtils.mean(dArr2), StatUtils.variance(dArr), StatUtils.variance(dArr2), (double) dArr.length, (double) dArr2.length);
    }

    public boolean tTest(double[] dArr, double[] dArr2, double d) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        checkSignificanceLevel(d);
        return tTest(dArr, dArr2) < d;
    }

    public boolean homoscedasticTTest(double[] dArr, double[] dArr2, double d) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        checkSignificanceLevel(d);
        return homoscedasticTTest(dArr, dArr2) < d;
    }

    public double tTest(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(statisticalSummary);
        checkSampleData(statisticalSummary2);
        return tTest(statisticalSummary.getMean(), statisticalSummary2.getMean(), statisticalSummary.getVariance(), statisticalSummary2.getVariance(), (double) statisticalSummary.getN(), (double) statisticalSummary2.getN());
    }

    public double homoscedasticTTest(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(statisticalSummary);
        checkSampleData(statisticalSummary2);
        return homoscedasticTTest(statisticalSummary.getMean(), statisticalSummary2.getMean(), statisticalSummary.getVariance(), statisticalSummary2.getVariance(), (double) statisticalSummary.getN(), (double) statisticalSummary2.getN());
    }

    public boolean tTest(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2, double d) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        checkSignificanceLevel(d);
        return tTest(statisticalSummary, statisticalSummary2) < d;
    }

    /* access modifiers changed from: protected */
    public double t(double d, double d2, double d3, double d4) {
        return (d - d2) / FastMath.sqrt(d3 / d4);
    }

    /* access modifiers changed from: protected */
    public double t(double d, double d2, double d3, double d4, double d5, double d6) {
        return (d - d2) / FastMath.sqrt((d3 / d5) + (d4 / d6));
    }

    /* access modifiers changed from: protected */
    public double homoscedasticT(double d, double d2, double d3, double d4, double d5, double d6) {
        return (d - d2) / FastMath.sqrt(((((d5 - 1.0d) * d3) + ((d6 - 1.0d) * d4)) / ((d5 + d6) - 2.0d)) * ((1.0d / d5) + (1.0d / d6)));
    }

    /* access modifiers changed from: protected */
    public double tTest(double d, double d2, double d3, double d4) throws MaxCountExceededException, MathIllegalArgumentException {
        return 2.0d * new TDistribution(d4 - 1.0d).cumulativeProbability(-FastMath.abs(t(d, d2, d3, d4)));
    }

    /* access modifiers changed from: protected */
    public double tTest(double d, double d2, double d3, double d4, double d5, double d6) throws MaxCountExceededException, NotStrictlyPositiveException {
        return 2.0d * new TDistribution(df(d3, d4, d5, d6)).cumulativeProbability(-FastMath.abs(t(d, d2, d3, d4, d5, d6)));
    }

    /* access modifiers changed from: protected */
    public double homoscedasticTTest(double d, double d2, double d3, double d4, double d5, double d6) throws MaxCountExceededException, NotStrictlyPositiveException {
        return 2.0d * new TDistribution((d5 + d6) - 2.0d).cumulativeProbability(-FastMath.abs(homoscedasticT(d, d2, d3, d4, d5, d6)));
    }

    private void checkSignificanceLevel(double d) throws OutOfRangeException {
        if (d <= 0.0d || d > 0.5d) {
            throw new OutOfRangeException(LocalizedFormats.SIGNIFICANCE_LEVEL, Double.valueOf(d), Double.valueOf(0.0d), Double.valueOf(0.5d));
        }
    }

    private void checkSampleData(double[] dArr) throws NullArgumentException, NumberIsTooSmallException {
        if (dArr == null) {
            throw new NullArgumentException();
        } else if (dArr.length < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DATA_FOR_T_STATISTIC, Integer.valueOf(dArr.length), Integer.valueOf(2), true);
        }
    }

    private void checkSampleData(StatisticalSummary statisticalSummary) throws NullArgumentException, NumberIsTooSmallException {
        if (statisticalSummary == null) {
            throw new NullArgumentException();
        } else if (statisticalSummary.getN() < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DATA_FOR_T_STATISTIC, Long.valueOf(statisticalSummary.getN()), Integer.valueOf(2), true);
        }
    }
}
