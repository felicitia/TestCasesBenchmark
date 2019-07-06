package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

public class SynchronizedSummaryStatistics extends SummaryStatistics {
    private static final long serialVersionUID = 1909861009042253704L;

    public SynchronizedSummaryStatistics() {
    }

    public SynchronizedSummaryStatistics(SynchronizedSummaryStatistics synchronizedSummaryStatistics) throws NullArgumentException {
        copy(synchronizedSummaryStatistics, this);
    }

    public synchronized StatisticalSummary getSummary() {
        return super.getSummary();
    }

    public synchronized void addValue(double d) {
        super.addValue(d);
    }

    public synchronized long getN() {
        return super.getN();
    }

    public synchronized double getSum() {
        return super.getSum();
    }

    public synchronized double getSumsq() {
        return super.getSumsq();
    }

    public synchronized double getMean() {
        return super.getMean();
    }

    public synchronized double getStandardDeviation() {
        return super.getStandardDeviation();
    }

    public synchronized double getVariance() {
        return super.getVariance();
    }

    public synchronized double getPopulationVariance() {
        return super.getPopulationVariance();
    }

    public synchronized double getMax() {
        return super.getMax();
    }

    public synchronized double getMin() {
        return super.getMin();
    }

    public synchronized double getGeometricMean() {
        return super.getGeometricMean();
    }

    public synchronized String toString() {
        return super.toString();
    }

    public synchronized void clear() {
        super.clear();
    }

    public synchronized boolean equals(Object obj) {
        return super.equals(obj);
    }

    public synchronized int hashCode() {
        return super.hashCode();
    }

    public synchronized StorelessUnivariateStatistic getSumImpl() {
        return super.getSumImpl();
    }

    public synchronized void setSumImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        super.setSumImpl(storelessUnivariateStatistic);
    }

    public synchronized StorelessUnivariateStatistic getSumsqImpl() {
        return super.getSumsqImpl();
    }

    public synchronized void setSumsqImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        super.setSumsqImpl(storelessUnivariateStatistic);
    }

    public synchronized StorelessUnivariateStatistic getMinImpl() {
        return super.getMinImpl();
    }

    public synchronized void setMinImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        super.setMinImpl(storelessUnivariateStatistic);
    }

    public synchronized StorelessUnivariateStatistic getMaxImpl() {
        return super.getMaxImpl();
    }

    public synchronized void setMaxImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        super.setMaxImpl(storelessUnivariateStatistic);
    }

    public synchronized StorelessUnivariateStatistic getSumLogImpl() {
        return super.getSumLogImpl();
    }

    public synchronized void setSumLogImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        super.setSumLogImpl(storelessUnivariateStatistic);
    }

    public synchronized StorelessUnivariateStatistic getGeoMeanImpl() {
        return super.getGeoMeanImpl();
    }

    public synchronized void setGeoMeanImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        super.setGeoMeanImpl(storelessUnivariateStatistic);
    }

    public synchronized StorelessUnivariateStatistic getMeanImpl() {
        return super.getMeanImpl();
    }

    public synchronized void setMeanImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        super.setMeanImpl(storelessUnivariateStatistic);
    }

    public synchronized StorelessUnivariateStatistic getVarianceImpl() {
        return super.getVarianceImpl();
    }

    public synchronized void setVarianceImpl(StorelessUnivariateStatistic storelessUnivariateStatistic) throws MathIllegalStateException {
        super.setVarianceImpl(storelessUnivariateStatistic);
    }

    public synchronized SynchronizedSummaryStatistics copy() {
        SynchronizedSummaryStatistics synchronizedSummaryStatistics;
        synchronizedSummaryStatistics = new SynchronizedSummaryStatistics();
        copy(this, synchronizedSummaryStatistics);
        return synchronizedSummaryStatistics;
    }

    public static void copy(SynchronizedSummaryStatistics synchronizedSummaryStatistics, SynchronizedSummaryStatistics synchronizedSummaryStatistics2) throws NullArgumentException {
        MathUtils.checkNotNull(synchronizedSummaryStatistics);
        MathUtils.checkNotNull(synchronizedSummaryStatistics2);
        synchronized (synchronizedSummaryStatistics) {
            synchronized (synchronizedSummaryStatistics2) {
                SummaryStatistics.copy(synchronizedSummaryStatistics, synchronizedSummaryStatistics2);
            }
        }
    }
}
