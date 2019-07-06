package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback;

public abstract class BaseOptimizer<PAIR> {
    private ConvergenceChecker<PAIR> checker;
    protected final Incrementor evaluations = new Incrementor(0, new MaxEvalCallback());
    protected final Incrementor iterations = new Incrementor(0, new MaxIterCallback());

    private static class MaxEvalCallback implements MaxCountExceededCallback {
        private MaxEvalCallback() {
        }

        public void trigger(int i) {
            throw new TooManyEvaluationsException(Integer.valueOf(i));
        }
    }

    private static class MaxIterCallback implements MaxCountExceededCallback {
        private MaxIterCallback() {
        }

        public void trigger(int i) {
            throw new TooManyIterationsException(Integer.valueOf(i));
        }
    }

    /* access modifiers changed from: protected */
    public abstract PAIR doOptimize();

    protected BaseOptimizer(ConvergenceChecker<PAIR> convergenceChecker) {
        this.checker = convergenceChecker;
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public int getMaxIterations() {
        return this.iterations.getMaximalCount();
    }

    public int getIterations() {
        return this.iterations.getCount();
    }

    public ConvergenceChecker<PAIR> getConvergenceChecker() {
        return this.checker;
    }

    public PAIR optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException, TooManyIterationsException {
        parseOptimizationData(optimizationDataArr);
        this.evaluations.resetCount();
        this.iterations.resetCount();
        return doOptimize();
    }

    /* access modifiers changed from: protected */
    public void incrementEvaluationCount() throws TooManyEvaluationsException {
        this.evaluations.incrementCount();
    }

    /* access modifiers changed from: protected */
    public void incrementIterationCount() throws TooManyIterationsException {
        this.iterations.incrementCount();
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (MaxEval maxEval : optimizationDataArr) {
            if (maxEval instanceof MaxEval) {
                this.evaluations.setMaximalCount(maxEval.getMaxEval());
            } else if (maxEval instanceof MaxIter) {
                this.iterations.setMaximalCount(((MaxIter) maxEval).getMaxIter());
            }
        }
    }
}
