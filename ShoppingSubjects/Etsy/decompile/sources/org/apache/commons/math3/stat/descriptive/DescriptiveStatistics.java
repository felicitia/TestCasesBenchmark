package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.ResizableDoubleArray;

public class DescriptiveStatistics implements Serializable, StatisticalSummary {
    public static final int INFINITE_WINDOW = -1;
    private static final String SET_QUANTILE_METHOD_NAME = "setQuantile";
    private static final long serialVersionUID = 4133067267405273064L;
    private ResizableDoubleArray eDA = new ResizableDoubleArray();
    private UnivariateStatistic geometricMeanImpl = new GeometricMean();
    private UnivariateStatistic kurtosisImpl = new Kurtosis();
    private UnivariateStatistic maxImpl = new Max();
    private UnivariateStatistic meanImpl = new Mean();
    private UnivariateStatistic minImpl = new Min();
    private UnivariateStatistic percentileImpl = new Percentile();
    private UnivariateStatistic skewnessImpl = new Skewness();
    private UnivariateStatistic sumImpl = new Sum();
    private UnivariateStatistic sumsqImpl = new SumOfSquares();
    private UnivariateStatistic varianceImpl = new Variance();
    protected int windowSize = -1;

    public DescriptiveStatistics() {
    }

    public DescriptiveStatistics(int i) throws MathIllegalArgumentException {
        setWindowSize(i);
    }

    public DescriptiveStatistics(double[] dArr) {
        if (dArr != null) {
            this.eDA = new ResizableDoubleArray(dArr);
        }
    }

    public DescriptiveStatistics(DescriptiveStatistics descriptiveStatistics) throws NullArgumentException {
        copy(descriptiveStatistics, this);
    }

    public void addValue(double d) {
        if (this.windowSize == -1) {
            this.eDA.addElement(d);
        } else if (getN() == ((long) this.windowSize)) {
            this.eDA.addElementRolling(d);
        } else if (getN() < ((long) this.windowSize)) {
            this.eDA.addElement(d);
        }
    }

    public void removeMostRecentValue() throws MathIllegalStateException {
        try {
            this.eDA.discardMostRecentElements(1);
        } catch (MathIllegalArgumentException unused) {
            throw new MathIllegalStateException(LocalizedFormats.NO_DATA, new Object[0]);
        }
    }

    public double replaceMostRecentValue(double d) throws MathIllegalStateException {
        return this.eDA.substituteMostRecentElement(d);
    }

    public double getMean() {
        return apply(this.meanImpl);
    }

    public double getGeometricMean() {
        return apply(this.geometricMeanImpl);
    }

    public double getVariance() {
        return apply(this.varianceImpl);
    }

    public double getPopulationVariance() {
        return apply(new Variance(false));
    }

    public double getStandardDeviation() {
        if (getN() <= 0) {
            return Double.NaN;
        }
        if (getN() > 1) {
            return FastMath.sqrt(getVariance());
        }
        return 0.0d;
    }

    public double getSkewness() {
        return apply(this.skewnessImpl);
    }

    public double getKurtosis() {
        return apply(this.kurtosisImpl);
    }

    public double getMax() {
        return apply(this.maxImpl);
    }

    public double getMin() {
        return apply(this.minImpl);
    }

    public long getN() {
        return (long) this.eDA.getNumElements();
    }

    public double getSum() {
        return apply(this.sumImpl);
    }

    public double getSumsq() {
        return apply(this.sumsqImpl);
    }

    public void clear() {
        this.eDA.clear();
    }

    public int getWindowSize() {
        return this.windowSize;
    }

    public void setWindowSize(int i) throws MathIllegalArgumentException {
        if (i >= 1 || i == -1) {
            this.windowSize = i;
            if (i != -1 && i < this.eDA.getNumElements()) {
                this.eDA.discardFrontElements(this.eDA.getNumElements() - i);
                return;
            }
            return;
        }
        throw new MathIllegalArgumentException(LocalizedFormats.NOT_POSITIVE_WINDOW_SIZE, Integer.valueOf(i));
    }

    public double[] getValues() {
        return this.eDA.getElements();
    }

    public double[] getSortedValues() {
        double[] values = getValues();
        Arrays.sort(values);
        return values;
    }

    public double getElement(int i) {
        return this.eDA.getElement(i);
    }

    public double getPercentile(double d) throws MathIllegalStateException, MathIllegalArgumentException {
        if (this.percentileImpl instanceof Percentile) {
            ((Percentile) this.percentileImpl).setQuantile(d);
        } else {
            try {
                this.percentileImpl.getClass().getMethod(SET_QUANTILE_METHOD_NAME, new Class[]{Double.TYPE}).invoke(this.percentileImpl, new Object[]{Double.valueOf(d)});
            } catch (NoSuchMethodException unused) {
                throw new MathIllegalStateException(LocalizedFormats.PERCENTILE_IMPLEMENTATION_UNSUPPORTED_METHOD, this.percentileImpl.getClass().getName(), SET_QUANTILE_METHOD_NAME);
            } catch (IllegalAccessException unused2) {
                throw new MathIllegalStateException(LocalizedFormats.PERCENTILE_IMPLEMENTATION_CANNOT_ACCESS_METHOD, SET_QUANTILE_METHOD_NAME, this.percentileImpl.getClass().getName());
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(e.getCause());
            }
        }
        return apply(this.percentileImpl);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = "\n";
        sb.append("DescriptiveStatistics:");
        sb.append(str);
        sb.append("n: ");
        sb.append(getN());
        sb.append(str);
        sb.append("min: ");
        sb.append(getMin());
        sb.append(str);
        sb.append("max: ");
        sb.append(getMax());
        sb.append(str);
        sb.append("mean: ");
        sb.append(getMean());
        sb.append(str);
        sb.append("std dev: ");
        sb.append(getStandardDeviation());
        sb.append(str);
        try {
            sb.append("median: ");
            sb.append(getPercentile(50.0d));
            sb.append(str);
        } catch (MathIllegalStateException unused) {
            sb.append("median: unavailable");
            sb.append(str);
        }
        sb.append("skewness: ");
        sb.append(getSkewness());
        sb.append(str);
        sb.append("kurtosis: ");
        sb.append(getKurtosis());
        sb.append(str);
        return sb.toString();
    }

    public double apply(UnivariateStatistic univariateStatistic) {
        return this.eDA.compute(univariateStatistic);
    }

    public synchronized UnivariateStatistic getMeanImpl() {
        return this.meanImpl;
    }

    public synchronized void setMeanImpl(UnivariateStatistic univariateStatistic) {
        this.meanImpl = univariateStatistic;
    }

    public synchronized UnivariateStatistic getGeometricMeanImpl() {
        return this.geometricMeanImpl;
    }

    public synchronized void setGeometricMeanImpl(UnivariateStatistic univariateStatistic) {
        this.geometricMeanImpl = univariateStatistic;
    }

    public synchronized UnivariateStatistic getKurtosisImpl() {
        return this.kurtosisImpl;
    }

    public synchronized void setKurtosisImpl(UnivariateStatistic univariateStatistic) {
        this.kurtosisImpl = univariateStatistic;
    }

    public synchronized UnivariateStatistic getMaxImpl() {
        return this.maxImpl;
    }

    public synchronized void setMaxImpl(UnivariateStatistic univariateStatistic) {
        this.maxImpl = univariateStatistic;
    }

    public synchronized UnivariateStatistic getMinImpl() {
        return this.minImpl;
    }

    public synchronized void setMinImpl(UnivariateStatistic univariateStatistic) {
        this.minImpl = univariateStatistic;
    }

    public synchronized UnivariateStatistic getPercentileImpl() {
        return this.percentileImpl;
    }

    public synchronized void setPercentileImpl(UnivariateStatistic univariateStatistic) throws MathIllegalArgumentException {
        try {
            univariateStatistic.getClass().getMethod(SET_QUANTILE_METHOD_NAME, new Class[]{Double.TYPE}).invoke(univariateStatistic, new Object[]{Double.valueOf(50.0d)});
            this.percentileImpl = univariateStatistic;
        } catch (NoSuchMethodException unused) {
            throw new MathIllegalArgumentException(LocalizedFormats.PERCENTILE_IMPLEMENTATION_UNSUPPORTED_METHOD, univariateStatistic.getClass().getName(), SET_QUANTILE_METHOD_NAME);
        } catch (IllegalAccessException unused2) {
            throw new MathIllegalArgumentException(LocalizedFormats.PERCENTILE_IMPLEMENTATION_CANNOT_ACCESS_METHOD, SET_QUANTILE_METHOD_NAME, univariateStatistic.getClass().getName());
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e.getCause());
        }
    }

    public synchronized UnivariateStatistic getSkewnessImpl() {
        return this.skewnessImpl;
    }

    public synchronized void setSkewnessImpl(UnivariateStatistic univariateStatistic) {
        this.skewnessImpl = univariateStatistic;
    }

    public synchronized UnivariateStatistic getVarianceImpl() {
        return this.varianceImpl;
    }

    public synchronized void setVarianceImpl(UnivariateStatistic univariateStatistic) {
        this.varianceImpl = univariateStatistic;
    }

    public synchronized UnivariateStatistic getSumsqImpl() {
        return this.sumsqImpl;
    }

    public synchronized void setSumsqImpl(UnivariateStatistic univariateStatistic) {
        this.sumsqImpl = univariateStatistic;
    }

    public synchronized UnivariateStatistic getSumImpl() {
        return this.sumImpl;
    }

    public synchronized void setSumImpl(UnivariateStatistic univariateStatistic) {
        this.sumImpl = univariateStatistic;
    }

    public DescriptiveStatistics copy() {
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        copy(this, descriptiveStatistics);
        return descriptiveStatistics;
    }

    public static void copy(DescriptiveStatistics descriptiveStatistics, DescriptiveStatistics descriptiveStatistics2) throws NullArgumentException {
        MathUtils.checkNotNull(descriptiveStatistics);
        MathUtils.checkNotNull(descriptiveStatistics2);
        descriptiveStatistics2.eDA = descriptiveStatistics.eDA.copy();
        descriptiveStatistics2.windowSize = descriptiveStatistics.windowSize;
        descriptiveStatistics2.maxImpl = descriptiveStatistics.maxImpl.copy();
        descriptiveStatistics2.meanImpl = descriptiveStatistics.meanImpl.copy();
        descriptiveStatistics2.minImpl = descriptiveStatistics.minImpl.copy();
        descriptiveStatistics2.sumImpl = descriptiveStatistics.sumImpl.copy();
        descriptiveStatistics2.varianceImpl = descriptiveStatistics.varianceImpl.copy();
        descriptiveStatistics2.sumsqImpl = descriptiveStatistics.sumsqImpl.copy();
        descriptiveStatistics2.geometricMeanImpl = descriptiveStatistics.geometricMeanImpl.copy();
        descriptiveStatistics2.kurtosisImpl = descriptiveStatistics.kurtosisImpl;
        descriptiveStatistics2.skewnessImpl = descriptiveStatistics.skewnessImpl;
        descriptiveStatistics2.percentileImpl = descriptiveStatistics.percentileImpl;
    }
}
