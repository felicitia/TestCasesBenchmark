package org.apache.commons.math3.optimization.direct;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
public class CMAESOptimizer extends BaseAbstractMultivariateSimpleBoundsOptimizer<MultivariateFunction> implements MultivariateOptimizer {
    public static final int DEFAULT_CHECKFEASABLECOUNT = 0;
    public static final int DEFAULT_DIAGONALONLY = 0;
    public static final boolean DEFAULT_ISACTIVECMA = true;
    public static final int DEFAULT_MAXITERATIONS = 30000;
    public static final RandomGenerator DEFAULT_RANDOMGENERATOR = new MersenneTwister();
    public static final double DEFAULT_STOPFITNESS = 0.0d;
    private RealMatrix B;
    private RealMatrix BD;
    private RealMatrix C;
    private RealMatrix D;
    private double cc;
    private double ccov1;
    private double ccov1Sep;
    private double ccovmu;
    private double ccovmuSep;
    private int checkFeasableCount;
    private double chiN;
    private double cs;
    private double damps;
    private RealMatrix diagC;
    private RealMatrix diagD;
    private int diagonalOnly;
    private int dimension;
    private double[] fitnessHistory;
    private boolean generateStatistics;
    private int historySize;
    private double[] inputSigma;
    private boolean isActiveCMA;
    /* access modifiers changed from: private */
    public boolean isMinimize;
    private int iterations;
    private int lambda;
    private double logMu2;
    private int maxIterations;
    private int mu;
    private double mueff;
    private double normps;
    private RealMatrix pc;
    private RealMatrix ps;
    private RandomGenerator random;
    private double sigma;
    private List<RealMatrix> statisticsDHistory;
    private List<Double> statisticsFitnessHistory;
    private List<RealMatrix> statisticsMeanHistory;
    private List<Double> statisticsSigmaHistory;
    private double stopFitness;
    private double stopTolFun;
    private double stopTolHistFun;
    private double stopTolUpX;
    private double stopTolX;
    private RealMatrix weights;
    private RealMatrix xmean;

    private static class DoubleIndex implements Comparable<DoubleIndex> {
        /* access modifiers changed from: private */
        public final int index;
        private final double value;

        DoubleIndex(double d, int i) {
            this.value = d;
            this.index = i;
        }

        public int compareTo(DoubleIndex doubleIndex) {
            return Double.compare(this.value, doubleIndex.value);
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DoubleIndex)) {
                return false;
            }
            if (Double.compare(this.value, ((DoubleIndex) obj).value) != 0) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.value);
            return (int) (((1438542 ^ (doubleToLongBits >>> 32)) ^ doubleToLongBits) & -1);
        }
    }

    private class FitnessFunction {
        private final boolean isRepairMode = true;
        private double valueRange = 1.0d;

        public FitnessFunction() {
        }

        public double value(double[] dArr) {
            double d;
            if (this.isRepairMode) {
                double[] repair = repair(dArr);
                d = CMAESOptimizer.this.computeObjectiveValue(repair) + penalty(dArr, repair);
            } else {
                d = CMAESOptimizer.this.computeObjectiveValue(dArr);
            }
            return CMAESOptimizer.this.isMinimize ? d : -d;
        }

        public boolean isFeasible(double[] dArr) {
            double[] lowerBound = CMAESOptimizer.this.getLowerBound();
            double[] upperBound = CMAESOptimizer.this.getUpperBound();
            for (int i = 0; i < dArr.length; i++) {
                if (dArr[i] < lowerBound[i] || dArr[i] > upperBound[i]) {
                    return false;
                }
            }
            return true;
        }

        public void setValueRange(double d) {
            this.valueRange = d;
        }

        /* access modifiers changed from: private */
        public double[] repair(double[] dArr) {
            double[] lowerBound = CMAESOptimizer.this.getLowerBound();
            double[] upperBound = CMAESOptimizer.this.getUpperBound();
            double[] dArr2 = new double[dArr.length];
            for (int i = 0; i < dArr.length; i++) {
                if (dArr[i] < lowerBound[i]) {
                    dArr2[i] = lowerBound[i];
                } else if (dArr[i] > upperBound[i]) {
                    dArr2[i] = upperBound[i];
                } else {
                    dArr2[i] = dArr[i];
                }
            }
            return dArr2;
        }

        private double penalty(double[] dArr, double[] dArr2) {
            double d = 0.0d;
            for (int i = 0; i < dArr.length; i++) {
                d += Math.abs(dArr[i] - dArr2[i]) * this.valueRange;
            }
            return CMAESOptimizer.this.isMinimize ? d : -d;
        }
    }

    public static class PopulationSize implements OptimizationData {
        private final int lambda;

        public PopulationSize(int i) throws NotStrictlyPositiveException {
            if (i <= 0) {
                throw new NotStrictlyPositiveException(Integer.valueOf(i));
            }
            this.lambda = i;
        }

        public int getPopulationSize() {
            return this.lambda;
        }
    }

    public static class Sigma implements OptimizationData {
        private final double[] sigma;

        public Sigma(double[] dArr) throws NotPositiveException {
            for (int i = 0; i < dArr.length; i++) {
                if (dArr[i] < 0.0d) {
                    throw new NotPositiveException(Double.valueOf(dArr[i]));
                }
            }
            this.sigma = (double[]) dArr.clone();
        }

        public double[] getSigma() {
            return (double[]) this.sigma.clone();
        }
    }

    public CMAESOptimizer() {
        this(0);
    }

    public CMAESOptimizer(int i) {
        this(i, null, 30000, 0.0d, true, 0, 0, DEFAULT_RANDOMGENERATOR, false, null);
    }

    @Deprecated
    public CMAESOptimizer(int i, double[] dArr) {
        this(i, dArr, 30000, 0.0d, true, 0, 0, DEFAULT_RANDOMGENERATOR, false);
    }

    @Deprecated
    public CMAESOptimizer(int i, double[] dArr, int i2, double d, boolean z, int i3, int i4, RandomGenerator randomGenerator, boolean z2) {
        this(i, dArr, i2, d, z, i3, i4, randomGenerator, z2, new SimpleValueChecker());
    }

    @Deprecated
    public CMAESOptimizer(int i, double[] dArr, int i2, double d, boolean z, int i3, int i4, RandomGenerator randomGenerator, boolean z2, ConvergenceChecker<PointValuePair> convergenceChecker) {
        double[] dArr2;
        super(convergenceChecker);
        this.diagonalOnly = 0;
        this.isMinimize = true;
        this.generateStatistics = false;
        this.statisticsSigmaHistory = new ArrayList();
        this.statisticsMeanHistory = new ArrayList();
        this.statisticsFitnessHistory = new ArrayList();
        this.statisticsDHistory = new ArrayList();
        this.lambda = i;
        if (dArr == null) {
            dArr2 = null;
        } else {
            dArr2 = (double[]) dArr.clone();
        }
        this.inputSigma = dArr2;
        this.maxIterations = i2;
        this.stopFitness = d;
        this.isActiveCMA = z;
        this.diagonalOnly = i3;
        this.checkFeasableCount = i4;
        this.random = randomGenerator;
        this.generateStatistics = z2;
    }

    public CMAESOptimizer(int i, double d, boolean z, int i2, int i3, RandomGenerator randomGenerator, boolean z2, ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
        this.diagonalOnly = 0;
        this.isMinimize = true;
        this.generateStatistics = false;
        this.statisticsSigmaHistory = new ArrayList();
        this.statisticsMeanHistory = new ArrayList();
        this.statisticsFitnessHistory = new ArrayList();
        this.statisticsDHistory = new ArrayList();
        this.maxIterations = i;
        this.stopFitness = d;
        this.isActiveCMA = z;
        this.diagonalOnly = i2;
        this.checkFeasableCount = i3;
        this.random = randomGenerator;
        this.generateStatistics = z2;
    }

    public List<Double> getStatisticsSigmaHistory() {
        return this.statisticsSigmaHistory;
    }

    public List<RealMatrix> getStatisticsMeanHistory() {
        return this.statisticsMeanHistory;
    }

    public List<Double> getStatisticsFitnessHistory() {
        return this.statisticsFitnessHistory;
    }

    public List<RealMatrix> getStatisticsDHistory() {
        return this.statisticsDHistory;
    }

    /* access modifiers changed from: protected */
    public PointValuePair optimizeInternal(int i, MultivariateFunction multivariateFunction, GoalType goalType, OptimizationData... optimizationDataArr) {
        parseOptimizationData(optimizationDataArr);
        return super.optimizeInternal(i, multivariateFunction, goalType, optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        RealMatrix realMatrix;
        double d;
        PointValuePair pointValuePair;
        double d2;
        PointValuePair pointValuePair2;
        int i;
        PointValuePair pointValuePair3;
        checkParameters();
        this.isMinimize = getGoalType().equals(GoalType.MINIMIZE);
        FitnessFunction fitnessFunction = new FitnessFunction();
        double[] startPoint = getStartPoint();
        int i2 = 0;
        this.dimension = startPoint.length;
        initializeCMA(startPoint);
        this.iterations = 0;
        double value = fitnessFunction.value(startPoint);
        push(this.fitnessHistory, value);
        PointValuePair pointValuePair4 = new PointValuePair(getStartPoint(), this.isMinimize ? value : -value);
        int i3 = 1;
        this.iterations = 1;
        double d3 = value;
        PointValuePair pointValuePair5 = pointValuePair4;
        PointValuePair pointValuePair6 = null;
        while (this.iterations <= this.maxIterations) {
            RealMatrix randn1 = randn1(this.dimension, this.lambda);
            RealMatrix zeros = zeros(this.dimension, this.lambda);
            double[] dArr = new double[this.lambda];
            int i4 = i2;
            while (i4 < this.lambda) {
                int i5 = i2;
                RealMatrix realMatrix2 = null;
                while (i5 < this.checkFeasableCount + i3) {
                    if (this.diagonalOnly <= 0) {
                        realMatrix2 = this.xmean.add(this.BD.multiply(randn1.getColumnMatrix(i4)).scalarMultiply(this.sigma));
                    } else {
                        realMatrix2 = this.xmean.add(times(this.diagD, randn1.getColumnMatrix(i4)).scalarMultiply(this.sigma));
                    }
                    if (i5 >= this.checkFeasableCount || fitnessFunction.isFeasible(realMatrix2.getColumn(i2))) {
                        break;
                    }
                    randn1.setColumn(i4, randn(this.dimension));
                    i5++;
                    i3 = 1;
                }
                copyColumn(realMatrix2, i2, zeros, i4);
                try {
                    dArr[i4] = fitnessFunction.value(zeros.getColumn(i4));
                    i4++;
                    i3 = 1;
                } catch (TooManyEvaluationsException unused) {
                }
            }
            int[] sortedIndices = sortedIndices(dArr);
            RealMatrix realMatrix3 = this.xmean;
            RealMatrix selectColumns = selectColumns(zeros, MathArrays.copyOf(sortedIndices, this.mu));
            this.xmean = selectColumns.multiply(this.weights);
            RealMatrix selectColumns2 = selectColumns(randn1, MathArrays.copyOf(sortedIndices, this.mu));
            boolean updateEvolutionPaths = updateEvolutionPaths(selectColumns2.multiply(this.weights), realMatrix3);
            if (this.diagonalOnly <= 0) {
                updateCovariance(updateEvolutionPaths, selectColumns, randn1, sortedIndices, realMatrix3);
            } else {
                updateCovarianceDiagonalOnly(updateEvolutionPaths, selectColumns2);
            }
            int[] iArr = sortedIndices;
            this.sigma *= Math.exp(Math.min(1.0d, (((this.normps / this.chiN) - 1.0d) * this.cs) / this.damps));
            double d4 = dArr[iArr[0]];
            int[] iArr2 = iArr;
            double d5 = dArr[iArr2[iArr2.length - 1]];
            if (d3 > d4) {
                PointValuePair pointValuePair7 = new PointValuePair(fitnessFunction.repair(selectColumns.getColumn(0)), this.isMinimize ? d4 : -d4);
                if (getConvergenceChecker() != null && pointValuePair5 != null && getConvergenceChecker().converged(this.iterations, pointValuePair7, pointValuePair5)) {
                    return pointValuePair7;
                }
                d3 = d4;
                pointValuePair6 = pointValuePair5;
                pointValuePair5 = pointValuePair7;
            }
            if (this.stopFitness != 0.0d) {
                if (d4 < (this.isMinimize ? this.stopFitness : -this.stopFitness)) {
                    break;
                }
            }
            double[] column = sqrt(this.diagC).getColumn(0);
            double[] column2 = this.pc.getColumn(0);
            PointValuePair pointValuePair8 = pointValuePair5;
            int i6 = 0;
            while (true) {
                if (i6 >= this.dimension) {
                    realMatrix = selectColumns;
                    d = d3;
                    pointValuePair = pointValuePair6;
                    break;
                }
                d = d3;
                pointValuePair = pointValuePair6;
                double[] dArr2 = column2;
                realMatrix = selectColumns;
                if (this.sigma * Math.max(Math.abs(column2[i6]), column[i6]) > this.stopTolX) {
                    break;
                } else if (i6 >= this.dimension - 1) {
                    return pointValuePair8;
                } else {
                    i6++;
                    d3 = d;
                    pointValuePair6 = pointValuePair;
                    selectColumns = realMatrix;
                    column2 = dArr2;
                }
            }
            for (int i7 = 0; i7 < this.dimension; i7++) {
                if (this.sigma * column[i7] > this.stopTolUpX) {
                    return pointValuePair8;
                }
            }
            double min = min(this.fitnessHistory);
            double max = max(this.fitnessHistory);
            if (this.iterations > 2) {
                d2 = d5;
                if (Math.max(max, d5) - Math.min(min, d4) < this.stopTolFun) {
                    return pointValuePair8;
                }
            } else {
                d2 = d5;
            }
            if ((this.iterations > this.fitnessHistory.length && max - min < this.stopTolHistFun) || max(this.diagD) / min(this.diagD) > 1.0E7d) {
                return pointValuePair8;
            }
            if (getConvergenceChecker() != null) {
                i = 0;
                PointValuePair pointValuePair9 = new PointValuePair(realMatrix.getColumn(0), this.isMinimize ? d4 : -d4);
                if (pointValuePair != null && getConvergenceChecker().converged(this.iterations, pointValuePair9, pointValuePair)) {
                    return pointValuePair8;
                }
                pointValuePair2 = pointValuePair9;
            } else {
                pointValuePair2 = pointValuePair;
                i = 0;
            }
            if (d == dArr[iArr2[(int) (0.1d + (((double) this.lambda) / 4.0d))]]) {
                pointValuePair3 = pointValuePair2;
                this.sigma *= Math.exp((this.cs / this.damps) + 0.2d);
            } else {
                pointValuePair3 = pointValuePair2;
            }
            if (this.iterations > 2 && Math.max(max, d4) - Math.min(min, d4) == 0.0d) {
                this.sigma *= Math.exp((this.cs / this.damps) + 0.2d);
            }
            push(this.fitnessHistory, d4);
            fitnessFunction.setValueRange(d2 - d4);
            if (this.generateStatistics) {
                this.statisticsSigmaHistory.add(Double.valueOf(this.sigma));
                this.statisticsFitnessHistory.add(Double.valueOf(d4));
                this.statisticsMeanHistory.add(this.xmean.transpose());
                this.statisticsDHistory.add(this.diagD.transpose().scalarMultiply(100000.0d));
            }
            this.iterations++;
            i3 = 1;
            i2 = i;
            pointValuePair5 = pointValuePair8;
            d3 = d;
            pointValuePair6 = pointValuePair3;
        }
        return pointValuePair5;
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (Sigma sigma2 : optimizationDataArr) {
            if (sigma2 instanceof Sigma) {
                this.inputSigma = sigma2.getSigma();
            } else if (sigma2 instanceof PopulationSize) {
                this.lambda = ((PopulationSize) sigma2).getPopulationSize();
            }
        }
    }

    private void checkParameters() {
        double[] startPoint = getStartPoint();
        double[] lowerBound = getLowerBound();
        double[] upperBound = getUpperBound();
        if (this.inputSigma == null) {
            return;
        }
        if (this.inputSigma.length != startPoint.length) {
            throw new DimensionMismatchException(this.inputSigma.length, startPoint.length);
        }
        int i = 0;
        while (i < startPoint.length) {
            if (this.inputSigma[i] < 0.0d) {
                throw new NotPositiveException(Double.valueOf(this.inputSigma[i]));
            } else if (this.inputSigma[i] > upperBound[i] - lowerBound[i]) {
                throw new OutOfRangeException(Double.valueOf(this.inputSigma[i]), Integer.valueOf(0), Double.valueOf(upperBound[i] - lowerBound[i]));
            } else {
                i++;
            }
        }
    }

    private void initializeCMA(double[] dArr) {
        double[] dArr2 = dArr;
        if (this.lambda <= 0) {
            this.lambda = ((int) (Math.log((double) this.dimension) * 3.0d)) + 4;
        }
        double[][] dArr3 = (double[][]) Array.newInstance(double.class, new int[]{dArr2.length, 1});
        int i = 0;
        while (i < dArr2.length) {
            dArr3[i][0] = this.inputSigma == null ? 0.3d : this.inputSigma[i];
            i++;
        }
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(dArr3, false);
        this.sigma = max((RealMatrix) array2DRowRealMatrix);
        this.stopTolUpX = 1000.0d * max((RealMatrix) array2DRowRealMatrix);
        this.stopTolX = 1.0E-11d * max((RealMatrix) array2DRowRealMatrix);
        this.stopTolFun = 1.0E-12d;
        this.stopTolHistFun = 1.0E-13d;
        this.mu = this.lambda / 2;
        this.logMu2 = Math.log(((double) this.mu) + 0.5d);
        this.weights = log(sequence(1.0d, (double) this.mu, 1.0d)).scalarMultiply(-1.0d).scalarAdd(this.logMu2);
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i2 = 0; i2 < this.mu; i2++) {
            double entry = this.weights.getEntry(i2, 0);
            d += entry;
            d2 += entry * entry;
        }
        this.weights = this.weights.scalarMultiply(1.0d / d);
        this.mueff = (d * d) / d2;
        this.cc = ((this.mueff / ((double) this.dimension)) + 4.0d) / (((double) (this.dimension + 4)) + ((this.mueff * 2.0d) / ((double) this.dimension)));
        this.cs = (this.mueff + 2.0d) / ((((double) this.dimension) + this.mueff) + 3.0d);
        this.damps = (((Math.max(0.0d, Math.sqrt((this.mueff - 1.0d) / ((double) (this.dimension + 1))) - 1.0d) * 2.0d) + 1.0d) * Math.max(0.3d, 1.0d - (((double) this.dimension) / (1.0E-6d + ((double) this.maxIterations))))) + this.cs;
        this.ccov1 = 2.0d / (((((double) this.dimension) + 1.3d) * (((double) this.dimension) + 1.3d)) + this.mueff);
        this.ccovmu = Math.min(1.0d - this.ccov1, (2.0d * ((this.mueff - 2.0d) + (1.0d / this.mueff))) / (((double) ((this.dimension + 2) * (this.dimension + 2))) + this.mueff));
        this.ccov1Sep = Math.min(1.0d, (this.ccov1 * (((double) this.dimension) + 1.5d)) / 3.0d);
        this.ccovmuSep = Math.min(1.0d - this.ccov1, (this.ccovmu * (((double) this.dimension) + 1.5d)) / 3.0d);
        this.chiN = Math.sqrt((double) this.dimension) * ((1.0d - (1.0d / (4.0d * ((double) this.dimension)))) + (1.0d / ((21.0d * ((double) this.dimension)) * ((double) this.dimension))));
        this.xmean = MatrixUtils.createColumnRealMatrix(dArr);
        this.diagD = array2DRowRealMatrix.scalarMultiply(1.0d / this.sigma);
        this.diagC = square(this.diagD);
        this.pc = zeros(this.dimension, 1);
        this.ps = zeros(this.dimension, 1);
        this.normps = this.ps.getFrobeniusNorm();
        this.B = eye(this.dimension, this.dimension);
        this.D = ones(this.dimension, 1);
        this.BD = times(this.B, repmat(this.diagD.transpose(), this.dimension, 1));
        this.C = this.B.multiply(diag(square(this.D)).multiply(this.B.transpose()));
        this.historySize = 10 + ((int) (((double) (30 * this.dimension)) / ((double) this.lambda)));
        this.fitnessHistory = new double[this.historySize];
        for (int i3 = 0; i3 < this.historySize; i3++) {
            this.fitnessHistory[i3] = Double.MAX_VALUE;
        }
    }

    private boolean updateEvolutionPaths(RealMatrix realMatrix, RealMatrix realMatrix2) {
        this.ps = this.ps.scalarMultiply(1.0d - this.cs).add(this.B.multiply(realMatrix).scalarMultiply(Math.sqrt(this.cs * (2.0d - this.cs) * this.mueff)));
        this.normps = this.ps.getFrobeniusNorm();
        boolean z = (this.normps / Math.sqrt(1.0d - Math.pow(1.0d - this.cs, (double) (2 * this.iterations)))) / this.chiN < 1.4d + (2.0d / (((double) this.dimension) + 1.0d));
        this.pc = this.pc.scalarMultiply(1.0d - this.cc);
        if (z) {
            this.pc = this.pc.add(this.xmean.subtract(realMatrix2).scalarMultiply(Math.sqrt((this.cc * (2.0d - this.cc)) * this.mueff) / this.sigma));
        }
        return z;
    }

    private void updateCovarianceDiagonalOnly(boolean z, RealMatrix realMatrix) {
        this.diagC = this.diagC.scalarMultiply((z ? 0.0d : this.ccov1Sep * this.cc * (2.0d - this.cc)) + ((1.0d - this.ccov1Sep) - this.ccovmuSep)).add(square(this.pc).scalarMultiply(this.ccov1Sep)).add(times(this.diagC, square(realMatrix).multiply(this.weights)).scalarMultiply(this.ccovmuSep));
        this.diagD = sqrt(this.diagC);
        if (this.diagonalOnly > 1 && this.iterations > this.diagonalOnly) {
            this.diagonalOnly = 0;
            this.B = eye(this.dimension, this.dimension);
            this.BD = diag(this.diagD);
            this.C = diag(this.diagC);
        }
    }

    private void updateCovariance(boolean z, RealMatrix realMatrix, RealMatrix realMatrix2, int[] iArr, RealMatrix realMatrix3) {
        double d;
        double d2;
        if (this.ccov1 + this.ccovmu > 0.0d) {
            RealMatrix scalarMultiply = realMatrix.subtract(repmat(realMatrix3, 1, this.mu)).scalarMultiply(1.0d / this.sigma);
            RealMatrix scalarMultiply2 = this.pc.multiply(this.pc.transpose()).scalarMultiply(this.ccov1);
            if (z) {
                d2 = 0.0d;
            } else {
                d2 = this.ccov1 * this.cc * (2.0d - this.cc);
            }
            double d3 = d2 + ((1.0d - this.ccov1) - this.ccovmu);
            if (this.isActiveCMA) {
                double pow = (((1.0d - this.ccovmu) * 0.25d) * this.mueff) / (Math.pow((double) (this.dimension + 2), 1.5d) + (2.0d * this.mueff));
                RealMatrix selectColumns = selectColumns(realMatrix2, MathArrays.copyOf(reverse(iArr), this.mu));
                RealMatrix sqrt = sqrt(sumRows(square(selectColumns)));
                int[] sortedIndices = sortedIndices(sqrt.getRow(0));
                RealMatrix selectColumns2 = selectColumns(divide(selectColumns(sqrt, reverse(sortedIndices)), selectColumns(sqrt, sortedIndices)), inverse(sortedIndices));
                d = 0.33999999999999997d / square(selectColumns2).multiply(this.weights).getEntry(0, 0);
                if (pow <= d) {
                    d = pow;
                }
                RealMatrix multiply = this.BD.multiply(times(selectColumns, repmat(selectColumns2, this.dimension, 1)));
                double d4 = 0.5d * d;
                double d5 = d3 + d4;
                this.C = this.C.scalarMultiply(d5).add(scalarMultiply2).add(scalarMultiply.scalarMultiply(this.ccovmu + d4).multiply(times(repmat(this.weights, 1, this.dimension), scalarMultiply.transpose()))).subtract(multiply.multiply(diag(this.weights)).multiply(multiply.transpose()).scalarMultiply(d));
                updateBD(d);
            }
            this.C = this.C.scalarMultiply(d3).add(scalarMultiply2).add(scalarMultiply.scalarMultiply(this.ccovmu).multiply(times(repmat(this.weights, 1, this.dimension), scalarMultiply.transpose())));
        }
        d = 0.0d;
        updateBD(d);
    }

    private void updateBD(double d) {
        if (this.ccov1 + this.ccovmu + d > 0.0d && (((((double) this.iterations) % 1.0d) / ((this.ccov1 + this.ccovmu) + d)) / ((double) this.dimension)) / 10.0d < 1.0d) {
            this.C = triu(this.C, 0).add(triu(this.C, 1).transpose());
            EigenDecomposition eigenDecomposition = new EigenDecomposition(this.C);
            this.B = eigenDecomposition.getV();
            this.D = eigenDecomposition.getD();
            this.diagD = diag(this.D);
            if (min(this.diagD) <= 0.0d) {
                for (int i = 0; i < this.dimension; i++) {
                    if (this.diagD.getEntry(i, 0) < 0.0d) {
                        this.diagD.setEntry(i, 0, 0.0d);
                    }
                }
                double max = max(this.diagD) / 1.0E14d;
                this.C = this.C.add(eye(this.dimension, this.dimension).scalarMultiply(max));
                this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(max));
            }
            if (max(this.diagD) > min(this.diagD) * 1.0E14d) {
                double max2 = (max(this.diagD) / 1.0E14d) - min(this.diagD);
                this.C = this.C.add(eye(this.dimension, this.dimension).scalarMultiply(max2));
                this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(max2));
            }
            this.diagC = diag(this.C);
            this.diagD = sqrt(this.diagD);
            this.BD = times(this.B, repmat(this.diagD.transpose(), this.dimension, 1));
        }
    }

    private static void push(double[] dArr, double d) {
        for (int length = dArr.length - 1; length > 0; length--) {
            dArr[length] = dArr[length - 1];
        }
        dArr[0] = d;
    }

    private int[] sortedIndices(double[] dArr) {
        DoubleIndex[] doubleIndexArr = new DoubleIndex[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            doubleIndexArr[i] = new DoubleIndex(dArr[i], i);
        }
        Arrays.sort(doubleIndexArr);
        int[] iArr = new int[dArr.length];
        for (int i2 = 0; i2 < dArr.length; i2++) {
            iArr[i2] = doubleIndexArr[i2].index;
        }
        return iArr;
    }

    private static RealMatrix log(RealMatrix realMatrix) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{realMatrix.getRowDimension(), realMatrix.getColumnDimension()});
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = Math.log(realMatrix.getEntry(i, i2));
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix sqrt(RealMatrix realMatrix) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{realMatrix.getRowDimension(), realMatrix.getColumnDimension()});
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = Math.sqrt(realMatrix.getEntry(i, i2));
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix square(RealMatrix realMatrix) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{realMatrix.getRowDimension(), realMatrix.getColumnDimension()});
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                double entry = realMatrix.getEntry(i, i2);
                dArr[i][i2] = entry * entry;
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix times(RealMatrix realMatrix, RealMatrix realMatrix2) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{realMatrix.getRowDimension(), realMatrix.getColumnDimension()});
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = realMatrix.getEntry(i, i2) * realMatrix2.getEntry(i, i2);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix divide(RealMatrix realMatrix, RealMatrix realMatrix2) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{realMatrix.getRowDimension(), realMatrix.getColumnDimension()});
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = realMatrix.getEntry(i, i2) / realMatrix2.getEntry(i, i2);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix selectColumns(RealMatrix realMatrix, int[] iArr) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{realMatrix.getRowDimension(), iArr.length});
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                dArr[i][i2] = realMatrix.getEntry(i, iArr[i2]);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix triu(RealMatrix realMatrix, int i) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{realMatrix.getRowDimension(), realMatrix.getColumnDimension()});
        int i2 = 0;
        while (i2 < realMatrix.getRowDimension()) {
            int i3 = 0;
            while (i3 < realMatrix.getColumnDimension()) {
                dArr[i2][i3] = i2 <= i3 - i ? realMatrix.getEntry(i2, i3) : 0.0d;
                i3++;
            }
            i2++;
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix sumRows(RealMatrix realMatrix) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{1, realMatrix.getColumnDimension()});
        for (int i = 0; i < realMatrix.getColumnDimension(); i++) {
            double d = 0.0d;
            for (int i2 = 0; i2 < realMatrix.getRowDimension(); i2++) {
                d += realMatrix.getEntry(i2, i);
            }
            dArr[0][i] = d;
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix diag(RealMatrix realMatrix) {
        if (realMatrix.getColumnDimension() == 1) {
            double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{realMatrix.getRowDimension(), realMatrix.getRowDimension()});
            for (int i = 0; i < realMatrix.getRowDimension(); i++) {
                dArr[i][i] = realMatrix.getEntry(i, 0);
            }
            return new Array2DRowRealMatrix(dArr, false);
        }
        double[][] dArr2 = (double[][]) Array.newInstance(double.class, new int[]{realMatrix.getRowDimension(), 1});
        for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
            dArr2[i2][0] = realMatrix.getEntry(i2, i2);
        }
        return new Array2DRowRealMatrix(dArr2, false);
    }

    private static void copyColumn(RealMatrix realMatrix, int i, RealMatrix realMatrix2, int i2) {
        for (int i3 = 0; i3 < realMatrix.getRowDimension(); i3++) {
            realMatrix2.setEntry(i3, i2, realMatrix.getEntry(i3, i));
        }
    }

    private static RealMatrix ones(int i, int i2) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{i, i2});
        for (int i3 = 0; i3 < i; i3++) {
            Arrays.fill(dArr[i3], 1.0d);
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix eye(int i, int i2) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{i, i2});
        for (int i3 = 0; i3 < i; i3++) {
            if (i3 < i2) {
                dArr[i3][i3] = 1.0d;
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix zeros(int i, int i2) {
        return new Array2DRowRealMatrix(i, i2);
    }

    private static RealMatrix repmat(RealMatrix realMatrix, int i, int i2) {
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        int i3 = i * rowDimension;
        int i4 = i2 * columnDimension;
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{i3, i4});
        for (int i5 = 0; i5 < i3; i5++) {
            for (int i6 = 0; i6 < i4; i6++) {
                dArr[i5][i6] = realMatrix.getEntry(i5 % rowDimension, i6 % columnDimension);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix sequence(double d, double d2, double d3) {
        int i = (int) (((d2 - d) / d3) + 1.0d);
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{i, 1});
        double d4 = d;
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2][0] = d4;
            d4 += d3;
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static double max(RealMatrix realMatrix) {
        double d = -1.7976931348623157E308d;
        int i = 0;
        while (i < realMatrix.getRowDimension()) {
            double d2 = d;
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                double entry = realMatrix.getEntry(i, i2);
                if (d2 < entry) {
                    d2 = entry;
                }
            }
            i++;
            d = d2;
        }
        return d;
    }

    private static double min(RealMatrix realMatrix) {
        double d = Double.MAX_VALUE;
        int i = 0;
        while (i < realMatrix.getRowDimension()) {
            double d2 = d;
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                double entry = realMatrix.getEntry(i, i2);
                if (d2 > entry) {
                    d2 = entry;
                }
            }
            i++;
            d = d2;
        }
        return d;
    }

    private static double max(double[] dArr) {
        double d = -1.7976931348623157E308d;
        for (int i = 0; i < dArr.length; i++) {
            if (d < dArr[i]) {
                d = dArr[i];
            }
        }
        return d;
    }

    private static double min(double[] dArr) {
        double d = Double.MAX_VALUE;
        for (int i = 0; i < dArr.length; i++) {
            if (d > dArr[i]) {
                d = dArr[i];
            }
        }
        return d;
    }

    private static int[] inverse(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[iArr[i]] = i;
        }
        return iArr2;
    }

    private static int[] reverse(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[(iArr.length - i) - 1];
        }
        return iArr2;
    }

    private double[] randn(int i) {
        double[] dArr = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = this.random.nextGaussian();
        }
        return dArr;
    }

    private RealMatrix randn1(int i, int i2) {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{i, i2});
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                dArr[i3][i4] = this.random.nextGaussian();
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }
}
