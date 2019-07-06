package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.RankingAlgorithm;

public class SpearmansCorrelation {
    private final RealMatrix data;
    private final PearsonsCorrelation rankCorrelation;
    private final RankingAlgorithm rankingAlgorithm;

    public SpearmansCorrelation() {
        this((RankingAlgorithm) new NaturalRanking());
    }

    public SpearmansCorrelation(RankingAlgorithm rankingAlgorithm2) {
        this.data = null;
        this.rankingAlgorithm = rankingAlgorithm2;
        this.rankCorrelation = null;
    }

    public SpearmansCorrelation(RealMatrix realMatrix) {
        this(realMatrix, new NaturalRanking());
    }

    public SpearmansCorrelation(RealMatrix realMatrix, RankingAlgorithm rankingAlgorithm2) {
        this.data = realMatrix.copy();
        this.rankingAlgorithm = rankingAlgorithm2;
        rankTransform(this.data);
        this.rankCorrelation = new PearsonsCorrelation(this.data);
    }

    public RealMatrix getCorrelationMatrix() {
        return this.rankCorrelation.getCorrelationMatrix();
    }

    public PearsonsCorrelation getRankCorrelation() {
        return this.rankCorrelation;
    }

    public RealMatrix computeCorrelationMatrix(RealMatrix realMatrix) {
        RealMatrix copy = realMatrix.copy();
        rankTransform(copy);
        return new PearsonsCorrelation().computeCorrelationMatrix(copy);
    }

    public RealMatrix computeCorrelationMatrix(double[][] dArr) {
        return computeCorrelationMatrix((RealMatrix) new BlockRealMatrix(dArr));
    }

    public double correlation(double[] dArr, double[] dArr2) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else if (dArr.length >= 2) {
            return new PearsonsCorrelation().correlation(this.rankingAlgorithm.rank(dArr), this.rankingAlgorithm.rank(dArr2));
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(dArr.length), Integer.valueOf(2));
        }
    }

    private void rankTransform(RealMatrix realMatrix) {
        for (int i = 0; i < realMatrix.getColumnDimension(); i++) {
            realMatrix.setColumn(i, this.rankingAlgorithm.rank(realMatrix.getColumn(i)));
        }
    }
}
