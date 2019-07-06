package org.apache.commons.math3.stat.ranking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public class NaturalRanking implements RankingAlgorithm {
    public static final NaNStrategy DEFAULT_NAN_STRATEGY = NaNStrategy.FAILED;
    public static final TiesStrategy DEFAULT_TIES_STRATEGY = TiesStrategy.AVERAGE;
    private final NaNStrategy nanStrategy;
    private final RandomData randomData;
    private final TiesStrategy tiesStrategy;

    private static class IntDoublePair implements Comparable<IntDoublePair> {
        private final int position;
        private final double value;

        public IntDoublePair(double d, int i) {
            this.value = d;
            this.position = i;
        }

        public int compareTo(IntDoublePair intDoublePair) {
            return Double.compare(this.value, intDoublePair.value);
        }

        public double getValue() {
            return this.value;
        }

        public int getPosition() {
            return this.position;
        }
    }

    public NaturalRanking() {
        this.tiesStrategy = DEFAULT_TIES_STRATEGY;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = null;
    }

    public NaturalRanking(TiesStrategy tiesStrategy2) {
        this.tiesStrategy = tiesStrategy2;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = new RandomDataImpl();
    }

    public NaturalRanking(NaNStrategy naNStrategy) {
        this.nanStrategy = naNStrategy;
        this.tiesStrategy = DEFAULT_TIES_STRATEGY;
        this.randomData = null;
    }

    public NaturalRanking(NaNStrategy naNStrategy, TiesStrategy tiesStrategy2) {
        this.nanStrategy = naNStrategy;
        this.tiesStrategy = tiesStrategy2;
        this.randomData = new RandomDataImpl();
    }

    public NaturalRanking(RandomGenerator randomGenerator) {
        this.tiesStrategy = TiesStrategy.RANDOM;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = new RandomDataImpl(randomGenerator);
    }

    public NaturalRanking(NaNStrategy naNStrategy, RandomGenerator randomGenerator) {
        this.nanStrategy = naNStrategy;
        this.tiesStrategy = TiesStrategy.RANDOM;
        this.randomData = new RandomDataImpl(randomGenerator);
    }

    public NaNStrategy getNanStrategy() {
        return this.nanStrategy;
    }

    public TiesStrategy getTiesStrategy() {
        return this.tiesStrategy;
    }

    public double[] rank(double[] dArr) {
        IntDoublePair[] intDoublePairArr = new IntDoublePair[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            intDoublePairArr[i] = new IntDoublePair(dArr[i], i);
        }
        List list = null;
        switch (this.nanStrategy) {
            case MAXIMAL:
                recodeNaNs(intDoublePairArr, Double.POSITIVE_INFINITY);
                break;
            case MINIMAL:
                recodeNaNs(intDoublePairArr, Double.NEGATIVE_INFINITY);
                break;
            case REMOVED:
                intDoublePairArr = removeNaNs(intDoublePairArr);
                break;
            case FIXED:
                list = getNanPositions(intDoublePairArr);
                break;
            case FAILED:
                list = getNanPositions(intDoublePairArr);
                if (list.size() > 0) {
                    throw new NotANumberException();
                }
                break;
            default:
                throw new MathInternalError();
        }
        Arrays.sort(intDoublePairArr);
        double[] dArr2 = new double[intDoublePairArr.length];
        dArr2[intDoublePairArr[0].getPosition()] = (double) 1;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(intDoublePairArr[0].getPosition()));
        int i2 = 1;
        for (int i3 = 1; i3 < intDoublePairArr.length; i3++) {
            if (Double.compare(intDoublePairArr[i3].getValue(), intDoublePairArr[i3 - 1].getValue()) > 0) {
                i2 = i3 + 1;
                if (arrayList.size() > 1) {
                    resolveTie(dArr2, arrayList);
                }
                arrayList = new ArrayList();
                arrayList.add(Integer.valueOf(intDoublePairArr[i3].getPosition()));
            } else {
                arrayList.add(Integer.valueOf(intDoublePairArr[i3].getPosition()));
            }
            dArr2[intDoublePairArr[i3].getPosition()] = (double) i2;
        }
        if (arrayList.size() > 1) {
            resolveTie(dArr2, arrayList);
        }
        if (this.nanStrategy == NaNStrategy.FIXED) {
            restoreNaNs(dArr2, list);
        }
        return dArr2;
    }

    private IntDoublePair[] removeNaNs(IntDoublePair[] intDoublePairArr) {
        if (!containsNaNs(intDoublePairArr)) {
            return intDoublePairArr;
        }
        IntDoublePair[] intDoublePairArr2 = new IntDoublePair[intDoublePairArr.length];
        int i = 0;
        for (int i2 = 0; i2 < intDoublePairArr.length; i2++) {
            if (Double.isNaN(intDoublePairArr[i2].getValue())) {
                for (int i3 = i2 + 1; i3 < intDoublePairArr.length; i3++) {
                    intDoublePairArr[i3] = new IntDoublePair(intDoublePairArr[i3].getValue(), intDoublePairArr[i3].getPosition() - 1);
                }
            } else {
                intDoublePairArr2[i] = new IntDoublePair(intDoublePairArr[i2].getValue(), intDoublePairArr[i2].getPosition());
                i++;
            }
        }
        IntDoublePair[] intDoublePairArr3 = new IntDoublePair[i];
        System.arraycopy(intDoublePairArr2, 0, intDoublePairArr3, 0, i);
        return intDoublePairArr3;
    }

    private void recodeNaNs(IntDoublePair[] intDoublePairArr, double d) {
        for (int i = 0; i < intDoublePairArr.length; i++) {
            if (Double.isNaN(intDoublePairArr[i].getValue())) {
                intDoublePairArr[i] = new IntDoublePair(d, intDoublePairArr[i].getPosition());
            }
        }
    }

    private boolean containsNaNs(IntDoublePair[] intDoublePairArr) {
        for (IntDoublePair value : intDoublePairArr) {
            if (Double.isNaN(value.getValue())) {
                return true;
            }
        }
        return false;
    }

    private void resolveTie(double[] dArr, List<Integer> list) {
        int i = 0;
        double d = dArr[((Integer) list.get(0)).intValue()];
        int size = list.size();
        switch (this.tiesStrategy) {
            case AVERAGE:
                fill(dArr, list, (((d * 2.0d) + ((double) size)) - 1.0d) / 2.0d);
                return;
            case MAXIMUM:
                fill(dArr, list, (d + ((double) size)) - 1.0d);
                return;
            case MINIMUM:
                fill(dArr, list, d);
                return;
            case RANDOM:
                long round = FastMath.round(d);
                for (Integer intValue : list) {
                    dArr[intValue.intValue()] = (double) this.randomData.nextLong(round, (round + ((long) size)) - 1);
                }
                return;
            case SEQUENTIAL:
                long round2 = FastMath.round(d);
                for (Integer intValue2 : list) {
                    int i2 = i + 1;
                    dArr[intValue2.intValue()] = (double) (round2 + ((long) i));
                    i = i2;
                }
                return;
            default:
                throw new MathInternalError();
        }
    }

    private void fill(double[] dArr, List<Integer> list, double d) {
        for (Integer intValue : list) {
            dArr[intValue.intValue()] = d;
        }
    }

    private void restoreNaNs(double[] dArr, List<Integer> list) {
        if (list.size() != 0) {
            for (Integer intValue : list) {
                dArr[intValue.intValue()] = Double.NaN;
            }
        }
    }

    private List<Integer> getNanPositions(IntDoublePair[] intDoublePairArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < intDoublePairArr.length; i++) {
            if (Double.isNaN(intDoublePairArr[i].getValue())) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }
}
