package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class SummaryStatistics implements Serializable, StatisticalSummary {
    private static final long serialVersionUID = -2021321786743555871L;
    private GeometricMean geoMean = new GeometricMean(this.sumLog);
    private StorelessUnivariateStatistic geoMeanImpl = this.geoMean;
    private Max max = new Max();
    private StorelessUnivariateStatistic maxImpl = this.max;
    private Mean mean = new Mean((FirstMoment) this.secondMoment);
    private StorelessUnivariateStatistic meanImpl = this.mean;
    private Min min = new Min();
    private StorelessUnivariateStatistic minImpl = this.min;
    private long n = 0;
    private SecondMoment secondMoment = new SecondMoment();
    private Sum sum = new Sum();
    private StorelessUnivariateStatistic sumImpl = this.sum;
    private SumOfLogs sumLog = new SumOfLogs();
    private StorelessUnivariateStatistic sumLogImpl = this.sumLog;
    private SumOfSquares sumsq = new SumOfSquares();
    private StorelessUnivariateStatistic sumsqImpl = this.sumsq;
    private Variance variance = new Variance(this.secondMoment);
    private StorelessUnivariateStatistic varianceImpl = this.variance;

    public SummaryStatistics() {
    }

    public SummaryStatistics(SummaryStatistics summaryStatistics) throws NullArgumentException {
        copy(summaryStatistics, this);
    }

    public StatisticalSummary getSummary() {
        StatisticalSummaryValues statisticalSummaryValues = new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
        return statisticalSummaryValues;
    }

    public void addValue(double d) {
        this.sumImpl.increment(d);
        this.sumsqImpl.increment(d);
        this.minImpl.increment(d);
        this.maxImpl.increment(d);
        this.sumLogImpl.increment(d);
        this.secondMoment.increment(d);
        if (this.meanImpl != this.mean) {
            this.meanImpl.increment(d);
        }
        if (this.varianceImpl != this.variance) {
            this.varianceImpl.increment(d);
        }
        if (this.geoMeanImpl != this.geoMean) {
            this.geoMeanImpl.increment(d);
        }
        this.n++;
    }

    public long getN() {
        return this.n;
    }

    public double getSum() {
        return this.sumImpl.getResult();
    }

    public double getSumsq() {
        return this.sumsqImpl.getResult();
    }

    public double getMean() {
        return this.meanImpl.getResult();
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

    public double getVariance() {
        return this.varianceImpl.getResult();
    }

    public double getPopulationVariance() {
        Variance variance2 = new Variance(this.secondMoment);
        variance2.setBiasCorrected(false);
        return variance2.getResult();
    }

    public double getMax() {
        return this.maxImpl.getResult();
    }

    public double getMin() {
        return this.minImpl.getResult();
    }

    public double getGeometricMean() {
        return this.geoMeanImpl.getResult();
    }

    public double getSumOfLogs() {
        return this.sumLogImpl.getResult();
    }

    public double getSecondMoment() {
        return this.secondMoment.getResult();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = "\n";
        sb.append("SummaryStatistics:");
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
        sb.append("geometric mean: ");
        sb.append(getGeometricMean());
        sb.append(str);
        sb.append("variance: ");
        sb.append(getVariance());
        sb.append(str);
        sb.append("sum of squares: ");
        sb.append(getSumsq());
        sb.append(str);
        sb.append("standard deviation: ");
        sb.append(getStandardDeviation());
        sb.append(str);
        sb.append("sum of logs: ");
        sb.append(getSumOfLogs());
        sb.append(str);
        return sb.toString();
    }

    public void clear() {
        this.n = 0;
        this.minImpl.clear();
        this.maxImpl.clear();
        this.sumImpl.clear();
        this.sumLogImpl.clear();
        this.sumsqImpl.clear();
        this.geoMeanImpl.clear();
        this.secondMoment.clear();
        if (this.meanImpl != this.mean) {
            this.meanImpl.clear();
        }
        if (this.varianceImpl != this.variance) {
            this.varianceImpl.clear();
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SummaryStatistics)) {
            return false;
        }
        SummaryStatistics summaryStatistics = (SummaryStatistics) obj;
        if (!Precision.equalsIncludingNaN(summaryStatistics.getGeometricMean(), getGeometricMean()) || !Precision.equalsIncludingNaN(summaryStatistics.getMax(), getMax()) || !Precision.equalsIncludingNaN(summaryStatistics.getMean(), getMean()) || !Precision.equalsIncludingNaN(summaryStatistics.getMin(), getMin()) || !Precision.equalsIncludingNaN((float) summaryStatistics.getN(), (float) getN()) || !Precision.equalsIncludingNaN(summaryStatistics.getSum(), getSum()) || !Precision.equalsIncludingNaN(summaryStatistics.getSumsq(), getSumsq()) || !Precision.equalsIncludingNaN(summaryStatistics.getVariance(), getVariance())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((((((((((MathUtils.hash(getGeometricMean()) + 31) * 31) + MathUtils.hash(getGeometricMean())) * 31) + MathUtils.hash(getMax())) * 31) + MathUtils.hash(getMean())) * 31) + MathUtils.hash(getMin())) * 31) + MathUtils.hash((double) getN())) * 31) + MathUtils.hash(getSum())) * 31) + MathUtils.hash(getSumsq())) * 31) + MathUtils.hash(getVariance());
    }

    public StorelessUnivariateStatistic getSumImpl() {
        return this.sumImpl;
    }

    public void setSumImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.sumImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getSumsqImpl() {
        return this.sumsqImpl;
    }

    public void setSumsqImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.sumsqImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getMinImpl() {
        return this.minImpl;
    }

    public void setMinImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.minImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getMaxImpl() {
        return this.maxImpl;
    }

    public void setMaxImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.maxImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getSumLogImpl() {
        return this.sumLogImpl;
    }

    public void setSumLogImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.sumLogImpl = storelessUnivariateStatistic;
        this.geoMean.setSumLogImpl(storelessUnivariateStatistic);
    }

    public StorelessUnivariateStatistic getGeoMeanImpl() {
        return this.geoMeanImpl;
    }

    public void setGeoMeanImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.geoMeanImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getMeanImpl() {
        return this.meanImpl;
    }

    public void setMeanImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.meanImpl = storelessUnivariateStatistic;
    }

    public StorelessUnivariateStatistic getVarianceImpl() {
        return this.varianceImpl;
    }

    public void setVarianceImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        checkEmpty();
        this.varianceImpl = storelessUnivariateStatistic;
    }

    private void checkEmpty() throws MathIllegalStateException {
        if (this.n > 0) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, Long.valueOf(this.n));
        }
    }

    public SummaryStatistics copy() {
        SummaryStatistics summaryStatistics = new SummaryStatistics();
        copy(this, summaryStatistics);
        return summaryStatistics;
    }

    public static void copy(SummaryStatistics summaryStatistics, SummaryStatistics summaryStatistics2) throws NullArgumentException {
        MathUtils.checkNotNull(summaryStatistics);
        MathUtils.checkNotNull(summaryStatistics2);
        summaryStatistics2.maxImpl = summaryStatistics.maxImpl.copy();
        summaryStatistics2.minImpl = summaryStatistics.minImpl.copy();
        summaryStatistics2.sumImpl = summaryStatistics.sumImpl.copy();
        summaryStatistics2.sumLogImpl = summaryStatistics.sumLogImpl.copy();
        summaryStatistics2.sumsqImpl = summaryStatistics.sumsqImpl.copy();
        summaryStatistics2.secondMoment = summaryStatistics.secondMoment.copy();
        summaryStatistics2.n = summaryStatistics.n;
        if (summaryStatistics.getVarianceImpl() instanceof Variance) {
            summaryStatistics2.varianceImpl = new Variance(summaryStatistics2.secondMoment);
        } else {
            summaryStatistics2.varianceImpl = summaryStatistics.varianceImpl.copy();
        }
        if (summaryStatistics.meanImpl instanceof Mean) {
            summaryStatistics2.meanImpl = new Mean((FirstMoment) summaryStatistics2.secondMoment);
        } else {
            summaryStatistics2.meanImpl = summaryStatistics.meanImpl.copy();
        }
        if (summaryStatistics.getGeoMeanImpl() instanceof GeometricMean) {
            summaryStatistics2.geoMeanImpl = new GeometricMean((SumOfLogs) summaryStatistics2.sumLogImpl);
        } else {
            summaryStatistics2.geoMeanImpl = summaryStatistics.geoMeanImpl.copy();
        }
        if (summaryStatistics.geoMean == summaryStatistics.geoMeanImpl) {
            summaryStatistics2.geoMean = (GeometricMean) summaryStatistics2.geoMeanImpl;
        } else {
            GeometricMean.copy(summaryStatistics.geoMean, summaryStatistics2.geoMean);
        }
        if (summaryStatistics.max == summaryStatistics.maxImpl) {
            summaryStatistics2.max = (Max) summaryStatistics2.maxImpl;
        } else {
            Max.copy(summaryStatistics.max, summaryStatistics2.max);
        }
        if (summaryStatistics.mean == summaryStatistics.meanImpl) {
            summaryStatistics2.mean = (Mean) summaryStatistics2.meanImpl;
        } else {
            Mean.copy(summaryStatistics.mean, summaryStatistics2.mean);
        }
        if (summaryStatistics.min == summaryStatistics.minImpl) {
            summaryStatistics2.min = (Min) summaryStatistics2.minImpl;
        } else {
            Min.copy(summaryStatistics.min, summaryStatistics2.min);
        }
        if (summaryStatistics.sum == summaryStatistics.sumImpl) {
            summaryStatistics2.sum = (Sum) summaryStatistics2.sumImpl;
        } else {
            Sum.copy(summaryStatistics.sum, summaryStatistics2.sum);
        }
        if (summaryStatistics.variance == summaryStatistics.varianceImpl) {
            summaryStatistics2.variance = (Variance) summaryStatistics2.varianceImpl;
        } else {
            Variance.copy(summaryStatistics.variance, summaryStatistics2.variance);
        }
        if (summaryStatistics.sumLog == summaryStatistics.sumLogImpl) {
            summaryStatistics2.sumLog = (SumOfLogs) summaryStatistics2.sumLogImpl;
        } else {
            SumOfLogs.copy(summaryStatistics.sumLog, summaryStatistics2.sumLog);
        }
        if (summaryStatistics.sumsq == summaryStatistics.sumsqImpl) {
            summaryStatistics2.sumsq = (SumOfSquares) summaryStatistics2.sumsqImpl;
        } else {
            SumOfSquares.copy(summaryStatistics.sumsq, summaryStatistics2.sumsq);
        }
    }
}
