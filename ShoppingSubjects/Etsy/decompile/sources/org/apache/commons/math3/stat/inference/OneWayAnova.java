package org.apache.commons.math3.stat.inference;

import java.util.Collection;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;

public class OneWayAnova {

    private static class AnovaStats {
        /* access modifiers changed from: private */
        public final double F;
        /* access modifiers changed from: private */
        public final int dfbg;
        /* access modifiers changed from: private */
        public final int dfwg;

        private AnovaStats(int i, int i2, double d) {
            this.dfbg = i;
            this.dfwg = i2;
            this.F = d;
        }
    }

    public double anovaFValue(Collection<double[]> collection) throws NullArgumentException, DimensionMismatchException {
        return anovaStats(collection).F;
    }

    public double anovaPValue(Collection<double[]> collection) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
        AnovaStats anovaStats = anovaStats(collection);
        return 1.0d - new FDistribution((double) anovaStats.dfbg, (double) anovaStats.dfwg).cumulativeProbability(anovaStats.F);
    }

    public boolean anovaTest(Collection<double[]> collection, double d) throws NullArgumentException, DimensionMismatchException, OutOfRangeException, ConvergenceException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return anovaPValue(collection) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), Integer.valueOf(0), Double.valueOf(0.5d));
    }

    private AnovaStats anovaStats(Collection<double[]> collection) throws NullArgumentException, DimensionMismatchException {
        if (collection == null) {
            throw new NullArgumentException();
        } else if (collection.size() < 2) {
            throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_CATEGORIES_REQUIRED, collection.size(), 2);
        } else {
            for (double[] dArr : collection) {
                if (dArr.length <= 1) {
                    throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_VALUES_IN_CATEGORY_REQUIRED, dArr.length, 2);
                }
            }
            Sum sum = new Sum();
            SumOfSquares sumOfSquares = new SumOfSquares();
            double d = 0.0d;
            int i = 0;
            int i2 = 0;
            for (double[] dArr2 : collection) {
                Sum sum2 = new Sum();
                SumOfSquares sumOfSquares2 = new SumOfSquares();
                int i3 = i;
                int i4 = 0;
                int i5 = 0;
                while (i4 < dArr2.length) {
                    double d2 = d;
                    double d3 = dArr2[i4];
                    i5++;
                    sum2.increment(d3);
                    sumOfSquares2.increment(d3);
                    i3++;
                    sum.increment(d3);
                    sumOfSquares.increment(d3);
                    i4++;
                    d = d2;
                }
                i2 += i5 - 1;
                d += sumOfSquares2.getResult() - ((sum2.getResult() * sum2.getResult()) / ((double) i5));
                i = i3;
            }
            double d4 = d;
            int size = collection.size() - 1;
            AnovaStats anovaStats = new AnovaStats(size, i2, (((sumOfSquares.getResult() - ((sum.getResult() * sum.getResult()) / ((double) i))) - d4) / ((double) size)) / (d4 / ((double) i2)));
            return anovaStats;
        }
    }
}
