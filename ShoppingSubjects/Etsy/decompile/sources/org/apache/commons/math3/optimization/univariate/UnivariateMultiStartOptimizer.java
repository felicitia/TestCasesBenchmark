package org.apache.commons.math3.optimization.univariate;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.random.RandomGenerator;

@Deprecated
public class UnivariateMultiStartOptimizer<FUNC extends UnivariateFunction> implements BaseUnivariateOptimizer<FUNC> {
    private RandomGenerator generator;
    private int maxEvaluations;
    private UnivariatePointValuePair[] optima;
    private final BaseUnivariateOptimizer<FUNC> optimizer;
    private int starts;
    private int totalEvaluations;

    public UnivariateMultiStartOptimizer(BaseUnivariateOptimizer<FUNC> baseUnivariateOptimizer, int i, RandomGenerator randomGenerator) {
        if (baseUnivariateOptimizer == null || randomGenerator == null) {
            throw new NullArgumentException();
        } else if (i < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        } else {
            this.optimizer = baseUnivariateOptimizer;
            this.starts = i;
            this.generator = randomGenerator;
        }
    }

    public ConvergenceChecker<UnivariatePointValuePair> getConvergenceChecker() {
        return this.optimizer.getConvergenceChecker();
    }

    public int getMaxEvaluations() {
        return this.maxEvaluations;
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

    public UnivariatePointValuePair optimize(int i, FUNC func, GoalType goalType, double d, double d2) {
        return optimize(i, func, goalType, d, d2, d + (0.5d * (d2 - d)));
    }

    public UnivariatePointValuePair optimize(int i, FUNC func, GoalType goalType, double d, double d2, double d3) {
        int i2;
        double d4;
        this.optima = new UnivariatePointValuePair[this.starts];
        this.totalEvaluations = 0;
        int i3 = 0;
        Throwable th = null;
        while (i3 < this.starts) {
            if (i3 == 0) {
                d4 = d3;
            } else {
                try {
                    d4 = d + (this.generator.nextDouble() * (d2 - d));
                } catch (RuntimeException e) {
                    e = e;
                    i2 = i3;
                    Throwable th2 = e;
                    this.optima[i2] = null;
                    th = th2;
                    this.totalEvaluations += this.optimizer.getEvaluations();
                    i3 = i2 + 1;
                }
            }
            i2 = i3;
            try {
                this.optima[i2] = this.optimizer.optimize(i - this.totalEvaluations, func, goalType, d, d2, d4);
            } catch (RuntimeException e2) {
                e = e2;
            }
            this.totalEvaluations += this.optimizer.getEvaluations();
            i3 = i2 + 1;
        }
        sortPairs(goalType);
        if (this.optima[0] != null) {
            return this.optima[0];
        }
        throw th;
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
