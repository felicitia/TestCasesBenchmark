package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.math3.exception.NullArgumentException;

public class AggregateSummaryStatistics implements Serializable, StatisticalSummary {
    private static final long serialVersionUID = -8207112444016386906L;
    private final SummaryStatistics statistics;
    private final SummaryStatistics statisticsPrototype;

    private static class AggregatingSummaryStatistics extends SummaryStatistics {
        private static final long serialVersionUID = 1;
        private final SummaryStatistics aggregateStatistics;

        public AggregatingSummaryStatistics(SummaryStatistics summaryStatistics) {
            this.aggregateStatistics = summaryStatistics;
        }

        public void addValue(double d) {
            super.addValue(d);
            synchronized (this.aggregateStatistics) {
                this.aggregateStatistics.addValue(d);
            }
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AggregatingSummaryStatistics)) {
                return false;
            }
            AggregatingSummaryStatistics aggregatingSummaryStatistics = (AggregatingSummaryStatistics) obj;
            if (!super.equals(aggregatingSummaryStatistics) || !this.aggregateStatistics.equals(aggregatingSummaryStatistics.aggregateStatistics)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return 123 + super.hashCode() + this.aggregateStatistics.hashCode();
        }
    }

    public AggregateSummaryStatistics() {
        this(new SummaryStatistics());
    }

    public AggregateSummaryStatistics(SummaryStatistics summaryStatistics) throws NullArgumentException {
        this(summaryStatistics, summaryStatistics == null ? null : new SummaryStatistics(summaryStatistics));
    }

    public AggregateSummaryStatistics(SummaryStatistics summaryStatistics, SummaryStatistics summaryStatistics2) {
        if (summaryStatistics == null) {
            summaryStatistics = new SummaryStatistics();
        }
        this.statisticsPrototype = summaryStatistics;
        if (summaryStatistics2 == null) {
            summaryStatistics2 = new SummaryStatistics();
        }
        this.statistics = summaryStatistics2;
    }

    public double getMax() {
        double max;
        synchronized (this.statistics) {
            max = this.statistics.getMax();
        }
        return max;
    }

    public double getMean() {
        double mean;
        synchronized (this.statistics) {
            mean = this.statistics.getMean();
        }
        return mean;
    }

    public double getMin() {
        double min;
        synchronized (this.statistics) {
            min = this.statistics.getMin();
        }
        return min;
    }

    public long getN() {
        long n;
        synchronized (this.statistics) {
            n = this.statistics.getN();
        }
        return n;
    }

    public double getStandardDeviation() {
        double standardDeviation;
        synchronized (this.statistics) {
            standardDeviation = this.statistics.getStandardDeviation();
        }
        return standardDeviation;
    }

    public double getSum() {
        double sum;
        synchronized (this.statistics) {
            sum = this.statistics.getSum();
        }
        return sum;
    }

    public double getVariance() {
        double variance;
        synchronized (this.statistics) {
            variance = this.statistics.getVariance();
        }
        return variance;
    }

    public double getSumOfLogs() {
        double sumOfLogs;
        synchronized (this.statistics) {
            sumOfLogs = this.statistics.getSumOfLogs();
        }
        return sumOfLogs;
    }

    public double getGeometricMean() {
        double geometricMean;
        synchronized (this.statistics) {
            geometricMean = this.statistics.getGeometricMean();
        }
        return geometricMean;
    }

    public double getSumsq() {
        double sumsq;
        synchronized (this.statistics) {
            sumsq = this.statistics.getSumsq();
        }
        return sumsq;
    }

    public double getSecondMoment() {
        double secondMoment;
        synchronized (this.statistics) {
            secondMoment = this.statistics.getSecondMoment();
        }
        return secondMoment;
    }

    public StatisticalSummary getSummary() {
        StatisticalSummaryValues statisticalSummaryValues;
        synchronized (this.statistics) {
            statisticalSummaryValues = new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
        }
        return statisticalSummaryValues;
    }

    public SummaryStatistics createContributingStatistics() {
        AggregatingSummaryStatistics aggregatingSummaryStatistics = new AggregatingSummaryStatistics(this.statistics);
        SummaryStatistics.copy(this.statisticsPrototype, aggregatingSummaryStatistics);
        return aggregatingSummaryStatistics;
    }

    public static StatisticalSummaryValues aggregate(Collection<SummaryStatistics> collection) {
        double d;
        double d2;
        if (collection == null) {
            return null;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            return null;
        }
        SummaryStatistics summaryStatistics = (SummaryStatistics) it.next();
        long n = summaryStatistics.getN();
        double min = summaryStatistics.getMin();
        double sum = summaryStatistics.getSum();
        double max = summaryStatistics.getMax();
        double secondMoment = summaryStatistics.getSecondMoment();
        double d3 = sum;
        double mean = summaryStatistics.getMean();
        while (it.hasNext()) {
            SummaryStatistics summaryStatistics2 = (SummaryStatistics) it.next();
            if (summaryStatistics2.getMin() < min || Double.isNaN(min)) {
                min = summaryStatistics2.getMin();
            }
            if (summaryStatistics2.getMax() > max || Double.isNaN(max)) {
                max = summaryStatistics2.getMax();
            }
            d3 += summaryStatistics2.getSum();
            double d4 = (double) n;
            double n2 = (double) summaryStatistics2.getN();
            long j = (long) (d4 + n2);
            double mean2 = summaryStatistics2.getMean() - mean;
            double d5 = (double) j;
            secondMoment = secondMoment + summaryStatistics2.getSecondMoment() + ((((mean2 * mean2) * d4) * n2) / d5);
            n = j;
            mean = d3 / d5;
        }
        if (n == 0) {
            d2 = Double.NaN;
        } else if (n == 1) {
            d2 = 0.0d;
        } else {
            d = secondMoment / ((double) (n - 1));
            StatisticalSummaryValues statisticalSummaryValues = new StatisticalSummaryValues(mean, d, n, max, min, d3);
            return statisticalSummaryValues;
        }
        d = d2;
        StatisticalSummaryValues statisticalSummaryValues2 = new StatisticalSummaryValues(mean, d, n, max, min, d3);
        return statisticalSummaryValues2;
    }
}
