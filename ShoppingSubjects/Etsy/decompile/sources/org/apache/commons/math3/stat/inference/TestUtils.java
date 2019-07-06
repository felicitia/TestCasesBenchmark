package org.apache.commons.math3.stat.inference;

import java.util.Collection;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;

public class TestUtils {
    private static final ChiSquareTest CHI_SQUARE_TEST = new ChiSquareTest();
    private static final GTest G_TEST = new GTest();
    private static final OneWayAnova ONE_WAY_ANANOVA = new OneWayAnova();
    private static final TTest T_TEST = new TTest();

    private TestUtils() {
    }

    public static double homoscedasticT(double[] dArr, double[] dArr2) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.homoscedasticT(dArr, dArr2);
    }

    public static double homoscedasticT(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.homoscedasticT(statisticalSummary, statisticalSummary2);
    }

    public static boolean homoscedasticTTest(double[] dArr, double[] dArr2, double d) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.homoscedasticTTest(dArr, dArr2, d);
    }

    public static double homoscedasticTTest(double[] dArr, double[] dArr2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.homoscedasticTTest(dArr, dArr2);
    }

    public static double homoscedasticTTest(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.homoscedasticTTest(statisticalSummary, statisticalSummary2);
    }

    public static double pairedT(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException {
        return T_TEST.pairedT(dArr, dArr2);
    }

    public static boolean pairedTTest(double[] dArr, double[] dArr2, double d) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.pairedTTest(dArr, dArr2, d);
    }

    public static double pairedTTest(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.pairedTTest(dArr, dArr2);
    }

    public static double t(double d, double[] dArr) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.t(d, dArr);
    }

    public static double t(double d, StatisticalSummary statisticalSummary) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.t(d, statisticalSummary);
    }

    public static double t(double[] dArr, double[] dArr2) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.t(dArr, dArr2);
    }

    public static double t(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.t(statisticalSummary, statisticalSummary2);
    }

    public static boolean tTest(double d, double[] dArr, double d2) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.tTest(d, dArr, d2);
    }

    public static double tTest(double d, double[] dArr) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.tTest(d, dArr);
    }

    public static boolean tTest(double d, StatisticalSummary statisticalSummary, double d2) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.tTest(d, statisticalSummary, d2);
    }

    public static double tTest(double d, StatisticalSummary statisticalSummary) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.tTest(d, statisticalSummary);
    }

    public static boolean tTest(double[] dArr, double[] dArr2, double d) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.tTest(dArr, dArr2, d);
    }

    public static double tTest(double[] dArr, double[] dArr2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.tTest(dArr, dArr2);
    }

    public static boolean tTest(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2, double d) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.tTest(statisticalSummary, statisticalSummary2, d);
    }

    public static double tTest(StatisticalSummary statisticalSummary, StatisticalSummary statisticalSummary2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.tTest(statisticalSummary, statisticalSummary2);
    }

    public static double chiSquare(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        return CHI_SQUARE_TEST.chiSquare(dArr, jArr);
    }

    public static double chiSquare(long[][] jArr) throws NullArgumentException, NotPositiveException, DimensionMismatchException {
        return CHI_SQUARE_TEST.chiSquare(jArr);
    }

    public static boolean chiSquareTest(double[] dArr, long[] jArr, double d) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTest(dArr, jArr, d);
    }

    public static double chiSquareTest(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTest(dArr, jArr);
    }

    public static boolean chiSquareTest(long[][] jArr, double d) throws NullArgumentException, DimensionMismatchException, NotPositiveException, OutOfRangeException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTest(jArr, d);
    }

    public static double chiSquareTest(long[][] jArr) throws NullArgumentException, DimensionMismatchException, NotPositiveException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTest(jArr);
    }

    public static double chiSquareDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        return CHI_SQUARE_TEST.chiSquareDataSetsComparison(jArr, jArr2);
    }

    public static double chiSquareTestDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTestDataSetsComparison(jArr, jArr2);
    }

    public static boolean chiSquareTestDataSetsComparison(long[] jArr, long[] jArr2, double d) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTestDataSetsComparison(jArr, jArr2, d);
    }

    public static double oneWayAnovaFValue(Collection<double[]> collection) throws NullArgumentException, DimensionMismatchException {
        return ONE_WAY_ANANOVA.anovaFValue(collection);
    }

    public static double oneWayAnovaPValue(Collection<double[]> collection) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
        return ONE_WAY_ANANOVA.anovaPValue(collection);
    }

    public static boolean oneWayAnovaTest(Collection<double[]> collection, double d) throws NullArgumentException, DimensionMismatchException, OutOfRangeException, ConvergenceException, MaxCountExceededException {
        return ONE_WAY_ANANOVA.anovaTest(collection, d);
    }

    public static double g(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        return G_TEST.g(dArr, jArr);
    }

    public static double gTest(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return G_TEST.gTest(dArr, jArr);
    }

    public static double gTestIntrinsic(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return G_TEST.gTestIntrinsic(dArr, jArr);
    }

    public static boolean gTest(double[] dArr, long[] jArr, double d) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        return G_TEST.gTest(dArr, jArr, d);
    }

    public static double gDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        return G_TEST.gDataSetsComparison(jArr, jArr2);
    }

    public static double rootLogLikelihoodRatio(long j, long j2, long j3, long j4) throws DimensionMismatchException, NotPositiveException, ZeroException {
        return G_TEST.rootLogLikelihoodRatio(j, j2, j3, j4);
    }

    public static double gTestDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        return G_TEST.gTestDataSetsComparison(jArr, jArr2);
    }

    public static boolean gTestDataSetsComparison(long[] jArr, long[] jArr2, double d) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        return G_TEST.gTestDataSetsComparison(jArr, jArr2, d);
    }
}
