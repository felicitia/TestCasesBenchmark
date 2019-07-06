package org.apache.commons.math3.optimization;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomVectorGenerator;

@Deprecated
public class BaseMultivariateMultiStartOptimizer<FUNC extends MultivariateFunction> implements BaseMultivariateOptimizer<FUNC> {
    private RandomVectorGenerator generator;
    private int maxEvaluations;
    private PointValuePair[] optima;
    private final BaseMultivariateOptimizer<FUNC> optimizer;
    private int starts;
    private int totalEvaluations;

    protected BaseMultivariateMultiStartOptimizer(BaseMultivariateOptimizer<FUNC> baseMultivariateOptimizer, int i, RandomVectorGenerator randomVectorGenerator) {
        if (baseMultivariateOptimizer == null || randomVectorGenerator == null) {
            throw new NullArgumentException();
        } else if (i < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        } else {
            this.optimizer = baseMultivariateOptimizer;
            this.starts = i;
            this.generator = randomVectorGenerator;
        }
    }

    public PointValuePair[] getOptima() {
        if (this.optima != null) {
            return (PointValuePair[]) this.optima.clone();
        }
        throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
    }

    public int getMaxEvaluations() {
        return this.maxEvaluations;
    }

    public int getEvaluations() {
        return this.totalEvaluations;
    }

    public ConvergenceChecker<PointValuePair> getConvergenceChecker() {
        return this.optimizer.getConvergenceChecker();
    }

    public PointValuePair optimize(int i, FUNC func, GoalType goalType, double[] dArr) {
        this.maxEvaluations = i;
        this.optima = new PointValuePair[this.starts];
        this.totalEvaluations = 0;
        int i2 = 0;
        Throwable e = null;
        while (i2 < this.starts) {
            try {
                this.optima[i2] = this.optimizer.optimize(i - this.totalEvaluations, func, goalType, i2 == 0 ? dArr : this.generator.nextVector());
            } catch (RuntimeException e2) {
                e = e2;
                this.optima[i2] = null;
            }
            this.totalEvaluations += this.optimizer.getEvaluations();
            i2++;
        }
        sortPairs(goalType);
        if (this.optima[0] != null) {
            return this.optima[0];
        }
        throw e;
    }

    private void sortPairs(final GoalType goalType) {
        Arrays.sort(this.optima, new Comparator<PointValuePair>() {
            public int compare(PointValuePair pointValuePair, PointValuePair pointValuePair2) {
                if (pointValuePair == null) {
                    return pointValuePair2 == null ? 0 : 1;
                } else if (pointValuePair2 == null) {
                    return -1;
                } else {
                    double doubleValue = ((Double) pointValuePair.getValue()).doubleValue();
                    double doubleValue2 = ((Double) pointValuePair2.getValue()).doubleValue();
                    return goalType == GoalType.MINIMIZE ? Double.compare(doubleValue, doubleValue2) : Double.compare(doubleValue2, doubleValue);
                }
            }
        });
    }
}
