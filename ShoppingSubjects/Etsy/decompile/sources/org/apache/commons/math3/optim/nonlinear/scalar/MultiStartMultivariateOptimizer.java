package org.apache.commons.math3.optim.nonlinear.scalar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.random.RandomVectorGenerator;

public class MultiStartMultivariateOptimizer extends BaseMultiStartMultivariateOptimizer<PointValuePair> {
    private final List<PointValuePair> optima = new ArrayList();
    /* access modifiers changed from: private */
    public final MultivariateOptimizer optimizer;

    public MultiStartMultivariateOptimizer(MultivariateOptimizer multivariateOptimizer, int i, RandomVectorGenerator randomVectorGenerator) throws NullArgumentException, NotStrictlyPositiveException {
        super(multivariateOptimizer, i, randomVectorGenerator);
        this.optimizer = multivariateOptimizer;
    }

    public PointValuePair[] getOptima() {
        Collections.sort(this.optima, getPairComparator());
        return (PointValuePair[]) this.optima.toArray(new PointValuePair[0]);
    }

    /* access modifiers changed from: protected */
    public void store(PointValuePair pointValuePair) {
        this.optima.add(pointValuePair);
    }

    /* access modifiers changed from: protected */
    public void clear() {
        this.optima.clear();
    }

    private Comparator<PointValuePair> getPairComparator() {
        return new Comparator<PointValuePair>() {
            public int compare(PointValuePair pointValuePair, PointValuePair pointValuePair2) {
                if (pointValuePair == null) {
                    return pointValuePair2 == null ? 0 : 1;
                } else if (pointValuePair2 == null) {
                    return -1;
                } else {
                    double doubleValue = ((Double) pointValuePair.getValue()).doubleValue();
                    double doubleValue2 = ((Double) pointValuePair2.getValue()).doubleValue();
                    return MultiStartMultivariateOptimizer.this.optimizer.getGoalType() == GoalType.MINIMIZE ? Double.compare(doubleValue, doubleValue2) : Double.compare(doubleValue2, doubleValue);
                }
            }
        };
    }
}
