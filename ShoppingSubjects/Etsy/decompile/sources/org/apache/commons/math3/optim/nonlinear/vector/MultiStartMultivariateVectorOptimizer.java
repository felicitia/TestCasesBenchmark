package org.apache.commons.math3.optim.nonlinear.vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.random.RandomVectorGenerator;

public class MultiStartMultivariateVectorOptimizer extends BaseMultiStartMultivariateOptimizer<PointVectorValuePair> {
    private final List<PointVectorValuePair> optima = new ArrayList();
    /* access modifiers changed from: private */
    public final MultivariateVectorOptimizer optimizer;

    public MultiStartMultivariateVectorOptimizer(MultivariateVectorOptimizer multivariateVectorOptimizer, int i, RandomVectorGenerator randomVectorGenerator) throws NullArgumentException, NotStrictlyPositiveException {
        super(multivariateVectorOptimizer, i, randomVectorGenerator);
        this.optimizer = multivariateVectorOptimizer;
    }

    public PointVectorValuePair[] getOptima() {
        Collections.sort(this.optima, getPairComparator());
        return (PointVectorValuePair[]) this.optima.toArray(new PointVectorValuePair[0]);
    }

    /* access modifiers changed from: protected */
    public void store(PointVectorValuePair pointVectorValuePair) {
        this.optima.add(pointVectorValuePair);
    }

    /* access modifiers changed from: protected */
    public void clear() {
        this.optima.clear();
    }

    private Comparator<PointVectorValuePair> getPairComparator() {
        return new Comparator<PointVectorValuePair>() {
            private final RealVector target = new ArrayRealVector(MultiStartMultivariateVectorOptimizer.this.optimizer.getTarget(), false);
            private final RealMatrix weight = MultiStartMultivariateVectorOptimizer.this.optimizer.getWeight();

            public int compare(PointVectorValuePair pointVectorValuePair, PointVectorValuePair pointVectorValuePair2) {
                if (pointVectorValuePair == null) {
                    return pointVectorValuePair2 == null ? 0 : 1;
                } else if (pointVectorValuePair2 == null) {
                    return -1;
                } else {
                    return Double.compare(weightedResidual(pointVectorValuePair), weightedResidual(pointVectorValuePair2));
                }
            }

            private double weightedResidual(PointVectorValuePair pointVectorValuePair) {
                RealVector subtract = this.target.subtract(new ArrayRealVector(pointVectorValuePair.getValueRef(), false));
                return subtract.dotProduct(this.weight.operate(subtract));
            }
        };
    }
}
