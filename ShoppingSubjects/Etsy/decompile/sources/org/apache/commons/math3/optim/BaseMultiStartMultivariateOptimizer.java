package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.random.RandomVectorGenerator;

public abstract class BaseMultiStartMultivariateOptimizer<PAIR> extends BaseMultivariateOptimizer<PAIR> {
    private RandomVectorGenerator generator;
    private int initialGuessIndex = -1;
    private int maxEvalIndex = -1;
    private OptimizationData[] optimData;
    private final BaseMultivariateOptimizer<PAIR> optimizer;
    private int starts;
    private int totalEvaluations;

    /* access modifiers changed from: protected */
    public abstract void clear();

    public abstract PAIR[] getOptima();

    /* access modifiers changed from: protected */
    public abstract void store(PAIR pair);

    public BaseMultiStartMultivariateOptimizer(BaseMultivariateOptimizer<PAIR> baseMultivariateOptimizer, int i, RandomVectorGenerator randomVectorGenerator) {
        super(baseMultivariateOptimizer.getConvergenceChecker());
        if (i < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.optimizer = baseMultivariateOptimizer;
        this.starts = i;
        this.generator = randomVectorGenerator;
    }

    public int getEvaluations() {
        return this.totalEvaluations;
    }

    public PAIR optimize(OptimizationData... optimizationDataArr) {
        this.optimData = optimizationDataArr;
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public PAIR doOptimize() {
        double[] dArr;
        for (int i = 0; i < this.optimData.length; i++) {
            if (this.optimData[i] instanceof MaxEval) {
                this.optimData[i] = null;
                this.maxEvalIndex = i;
            }
            if (this.optimData[i] instanceof InitialGuess) {
                this.optimData[i] = null;
                this.initialGuessIndex = i;
            }
        }
        if (this.maxEvalIndex == -1) {
            throw new MathIllegalStateException();
        } else if (this.initialGuessIndex == -1) {
            throw new MathIllegalStateException();
        } else {
            this.totalEvaluations = 0;
            clear();
            int maxEvaluations = getMaxEvaluations();
            getLowerBound();
            getUpperBound();
            double[] startPoint = getStartPoint();
            Throwable e = null;
            for (int i2 = 0; i2 < this.starts; i2++) {
                try {
                    this.optimData[this.maxEvalIndex] = new MaxEval(maxEvaluations - this.totalEvaluations);
                    if (i2 == 0) {
                        dArr = startPoint;
                    } else {
                        dArr = this.generator.nextVector();
                    }
                    this.optimData[this.initialGuessIndex] = new InitialGuess(dArr);
                    store(this.optimizer.optimize(this.optimData));
                } catch (RuntimeException e2) {
                    e = e2;
                }
                this.totalEvaluations += this.optimizer.getEvaluations();
            }
            PAIR[] optima = getOptima();
            if (optima.length != 0) {
                return optima[0];
            }
            throw e;
        }
    }
}
