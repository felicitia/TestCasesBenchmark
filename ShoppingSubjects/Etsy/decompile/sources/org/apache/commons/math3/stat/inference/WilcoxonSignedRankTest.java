package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.TiesStrategy;
import org.apache.commons.math3.util.FastMath;

public class WilcoxonSignedRankTest {
    private NaturalRanking naturalRanking;

    private double calculateExactPValue(double d, int i) {
        int i2 = 1 << i;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < i; i6++) {
                if (((i4 >> i6) & 1) == 1) {
                    i5 += i6 + 1;
                }
            }
            if (((double) i5) >= d) {
                i3++;
            }
        }
        return (2.0d * ((double) i3)) / ((double) i2);
    }

    public WilcoxonSignedRankTest() {
        this.naturalRanking = new NaturalRanking(NaNStrategy.FIXED, TiesStrategy.AVERAGE);
    }

    public WilcoxonSignedRankTest(NaNStrategy naNStrategy, TiesStrategy tiesStrategy) {
        this.naturalRanking = new NaturalRanking(naNStrategy, tiesStrategy);
    }

    private void ensureDataConformance(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException {
        if (dArr == null || dArr2 == null) {
            throw new NullArgumentException();
        } else if (dArr.length == 0 || dArr2.length == 0) {
            throw new NoDataException();
        } else if (dArr2.length != dArr.length) {
            throw new DimensionMismatchException(dArr2.length, dArr.length);
        }
    }

    private double[] calculateDifferences(double[] dArr, double[] dArr2) {
        double[] dArr3 = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = dArr2[i] - dArr[i];
        }
        return dArr3;
    }

    private double[] calculateAbsoluteDifferences(double[] dArr) throws NullArgumentException, NoDataException {
        if (dArr == null) {
            throw new NullArgumentException();
        } else if (dArr.length == 0) {
            throw new NoDataException();
        } else {
            double[] dArr2 = new double[dArr.length];
            for (int i = 0; i < dArr.length; i++) {
                dArr2[i] = FastMath.abs(dArr[i]);
            }
            return dArr2;
        }
    }

    public double wilcoxonSignedRank(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException {
        ensureDataConformance(dArr, dArr2);
        double[] calculateDifferences = calculateDifferences(dArr, dArr2);
        double[] rank = this.naturalRanking.rank(calculateAbsoluteDifferences(calculateDifferences));
        double d = 0.0d;
        for (int i = 0; i < calculateDifferences.length; i++) {
            if (calculateDifferences[i] > 0.0d) {
                d += rank[i];
            }
        }
        int length = dArr.length;
        return FastMath.max(d, (((double) (length * (length + 1))) / 2.0d) - d);
    }

    private double calculateAsymptoticPValue(double d, int i) {
        double d2 = ((double) ((i + 1) * i)) / 4.0d;
        return 2.0d * new NormalDistribution(0.0d, 1.0d).cumulativeProbability(((d - d2) - 0.5d) / FastMath.sqrt((((double) ((2 * i) + 1)) / 6.0d) * d2));
    }

    public double wilcoxonSignedRankTest(double[] dArr, double[] dArr2, boolean z) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooLargeException, ConvergenceException, MaxCountExceededException {
        ensureDataConformance(dArr, dArr2);
        int length = dArr.length;
        double wilcoxonSignedRank = wilcoxonSignedRank(dArr, dArr2);
        if (z && length > 30) {
            throw new NumberIsTooLargeException(Integer.valueOf(length), Integer.valueOf(30), true);
        } else if (z) {
            return calculateExactPValue(wilcoxonSignedRank, length);
        } else {
            return calculateAsymptoticPValue((((double) ((length + 1) * length)) / 2.0d) - wilcoxonSignedRank, length);
        }
    }
}
