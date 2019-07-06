package org.apache.commons.math3.optimization.direct;

import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;

@Deprecated
public class SimplexOptimizer extends BaseAbstractMultivariateOptimizer<MultivariateFunction> implements MultivariateOptimizer {
    private AbstractSimplex simplex;

    @Deprecated
    public SimplexOptimizer() {
        this(new SimpleValueChecker());
    }

    public SimplexOptimizer(ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    public SimplexOptimizer(double d, double d2) {
        this(new SimpleValueChecker(d, d2));
    }

    @Deprecated
    public void setSimplex(AbstractSimplex abstractSimplex) {
        parseOptimizationData(abstractSimplex);
    }

    /* access modifiers changed from: protected */
    public PointValuePair optimizeInternal(int i, MultivariateFunction multivariateFunction, GoalType goalType, OptimizationData... optimizationDataArr) {
        parseOptimizationData(optimizationDataArr);
        return super.optimizeInternal(i, multivariateFunction, goalType, optimizationDataArr);
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (AbstractSimplex abstractSimplex : optimizationDataArr) {
            if (abstractSimplex instanceof AbstractSimplex) {
                this.simplex = abstractSimplex;
            }
        }
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        if (this.simplex == null) {
            throw new NullArgumentException();
        }
        AnonymousClass1 r0 = new MultivariateFunction() {
            public double value(double[] dArr) {
                return SimplexOptimizer.this.computeObjectiveValue(dArr);
            }
        };
        final boolean z = getGoalType() == GoalType.MINIMIZE;
        AnonymousClass2 r2 = new Comparator<PointValuePair>() {
            public int compare(PointValuePair pointValuePair, PointValuePair pointValuePair2) {
                double doubleValue = ((Double) pointValuePair.getValue()).doubleValue();
                double doubleValue2 = ((Double) pointValuePair2.getValue()).doubleValue();
                return z ? Double.compare(doubleValue, doubleValue2) : Double.compare(doubleValue2, doubleValue);
            }
        };
        this.simplex.build(getStartPoint());
        this.simplex.evaluate(r0, r2);
        ConvergenceChecker convergenceChecker = getConvergenceChecker();
        PointValuePair[] pointValuePairArr = null;
        int i = 0;
        while (true) {
            if (i > 0) {
                boolean z2 = true;
                for (int i2 = 0; i2 < this.simplex.getSize(); i2++) {
                    z2 = z2 && convergenceChecker.converged(i, pointValuePairArr[i2], this.simplex.getPoint(i2));
                }
                if (z2) {
                    return this.simplex.getPoint(0);
                }
            }
            pointValuePairArr = this.simplex.getPoints();
            this.simplex.iterate(r0, r2);
            i++;
        }
    }
}
