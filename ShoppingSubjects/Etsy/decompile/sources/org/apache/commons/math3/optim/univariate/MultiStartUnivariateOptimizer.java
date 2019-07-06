package org.apache.commons.math3.optim.univariate;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.random.RandomGenerator;

public class MultiStartUnivariateOptimizer extends UnivariateOptimizer {
    private RandomGenerator generator;
    private int maxEvalIndex = -1;
    private OptimizationData[] optimData;
    private UnivariatePointValuePair[] optima;
    private final UnivariateOptimizer optimizer;
    private int searchIntervalIndex = -1;
    private int starts;
    private int totalEvaluations;

    public MultiStartUnivariateOptimizer(UnivariateOptimizer univariateOptimizer, int i, RandomGenerator randomGenerator) {
        super(univariateOptimizer.getConvergenceChecker());
        if (i < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.optimizer = univariateOptimizer;
        this.starts = i;
        this.generator = randomGenerator;
    }

    public int getEvaluations() {
        return this.totalEvaluations;
    }

    public UnivariatePointValuePair[] getOptima() {
        if (this.optima != null) {
            return (UnivariatePointValuePair[]) this.optima.clone();
        }
        throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
    }

    public UnivariatePointValuePair optimize(OptimizationData... optimizationDataArr) {
        this.optimData = optimizationDataArr;
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public UnivariatePointValuePair doOptimize() {
        double d;
        for (int i = 0; i < this.optimData.length; i++) {
            if (this.optimData[i] instanceof MaxEval) {
                this.optimData[i] = null;
                this.maxEvalIndex = i;
            } else if (this.optimData[i] instanceof SearchInterval) {
                this.optimData[i] = null;
                this.searchIntervalIndex = i;
            }
        }
        if (this.maxEvalIndex == -1) {
            throw new MathIllegalStateException();
        } else if (this.searchIntervalIndex == -1) {
            throw new MathIllegalStateException();
        } else {
            this.optima = new UnivariatePointValuePair[this.starts];
            this.totalEvaluations = 0;
            int maxEvaluations = getMaxEvaluations();
            double min = getMin();
            double max = getMax();
            double startValue = getStartValue();
            Throwable th = null;
            for (int i2 = 0; i2 < this.starts; i2++) {
                try {
                    this.optimData[this.maxEvalIndex] = new MaxEval(maxEvaluations - this.totalEvaluations);
                    if (i2 == 0) {
                        d = startValue;
                    } else {
                        d = (this.generator.nextDouble() * (max - min)) + min;
                    }
                    OptimizationData[] optimizationDataArr = this.optimData;
                    int i3 = this.searchIntervalIndex;
                    SearchInterval searchInterval = new SearchInterval(min, max, d);
                    optimizationDataArr[i3] = searchInterval;
                    this.optima[i2] = this.optimizer.optimize(this.optimData);
                } catch (RuntimeException e) {
                    Throwable th2 = e;
                    this.optima[i2] = null;
                    th = th2;
                }
                this.totalEvaluations += this.optimizer.getEvaluations();
            }
            sortPairs(getGoalType());
            if (this.optima[0] != null) {
                return this.optima[0];
            }
            throw th;
        }
    }

    private void sortPairs(final GoalType goalType) {
        Arrays.sort(this.optima, new Comparator<UnivariatePointValuePair>() {
            public int compare(UnivariatePointValuePair univariatePointValuePair, UnivariatePointValuePair univariatePointValuePair2) {
                if (univariatePointValuePair == null) {
                    return univariatePointValuePair2 == null ? 0 : 1;
                } else if (univariatePointValuePair2 == null) {
                    return -1;
                } else {
                    double value = univariatePointValuePair.getValue();
                    double value2 = univariatePointValuePair2.getValue();
                    return goalType == GoalType.MINIMIZE ? Double.compare(value, value2) : Double.compare(value2, value);
                }
            }
        });
    }
}
