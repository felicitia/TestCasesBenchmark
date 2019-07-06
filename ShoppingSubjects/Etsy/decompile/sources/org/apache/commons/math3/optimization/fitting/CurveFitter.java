package org.apache.commons.math3.optimization.fitting;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.analysis.DifferentiableMultivariateVectorFunction;
import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.optimization.MultivariateDifferentiableVectorOptimizer;
import org.apache.commons.math3.optimization.PointVectorValuePair;

@Deprecated
public class CurveFitter<T extends ParametricUnivariateFunction> {
    /* access modifiers changed from: private */
    public final List<WeightedObservedPoint> observations;
    @Deprecated
    private final DifferentiableMultivariateVectorOptimizer oldOptimizer;
    private final MultivariateDifferentiableVectorOptimizer optimizer;

    @Deprecated
    private class OldTheoreticalValuesFunction implements DifferentiableMultivariateVectorFunction {
        /* access modifiers changed from: private */
        public final ParametricUnivariateFunction f;

        public OldTheoreticalValuesFunction(ParametricUnivariateFunction parametricUnivariateFunction) {
            this.f = parametricUnivariateFunction;
        }

        public MultivariateMatrixFunction jacobian() {
            return new MultivariateMatrixFunction() {
                public double[][] value(double[] dArr) {
                    double[][] dArr2 = new double[CurveFitter.this.observations.size()][];
                    int i = 0;
                    for (WeightedObservedPoint x : CurveFitter.this.observations) {
                        int i2 = i + 1;
                        dArr2[i] = OldTheoreticalValuesFunction.this.f.gradient(x.getX(), dArr);
                        i = i2;
                    }
                    return dArr2;
                }
            };
        }

        public double[] value(double[] dArr) {
            double[] dArr2 = new double[CurveFitter.this.observations.size()];
            int i = 0;
            for (WeightedObservedPoint x : CurveFitter.this.observations) {
                int i2 = i + 1;
                dArr2[i] = this.f.value(x.getX(), dArr);
                i = i2;
            }
            return dArr2;
        }
    }

    private class TheoreticalValuesFunction implements MultivariateDifferentiableVectorFunction {
        private final ParametricUnivariateFunction f;

        public TheoreticalValuesFunction(ParametricUnivariateFunction parametricUnivariateFunction) {
            this.f = parametricUnivariateFunction;
        }

        public double[] value(double[] dArr) {
            double[] dArr2 = new double[CurveFitter.this.observations.size()];
            int i = 0;
            for (WeightedObservedPoint x : CurveFitter.this.observations) {
                int i2 = i + 1;
                dArr2[i] = this.f.value(x.getX(), dArr);
                i = i2;
            }
            return dArr2;
        }

        public DerivativeStructure[] value(DerivativeStructure[] derivativeStructureArr) {
            DerivativeStructure[] derivativeStructureArr2 = derivativeStructureArr;
            double[] dArr = new double[derivativeStructureArr2.length];
            for (int i = 0; i < derivativeStructureArr2.length; i++) {
                dArr[i] = derivativeStructureArr2[i].getValue();
            }
            DerivativeStructure[] derivativeStructureArr3 = new DerivativeStructure[CurveFitter.this.observations.size()];
            int i2 = 0;
            for (WeightedObservedPoint x : CurveFitter.this.observations) {
                DerivativeStructure derivativeStructure = new DerivativeStructure(derivativeStructureArr2.length, 1, this.f.value(x.getX(), dArr));
                for (int i3 = 0; i3 < derivativeStructureArr2.length; i3++) {
                    DerivativeStructure derivativeStructure2 = new DerivativeStructure(derivativeStructureArr2.length, 1, i3, 0.0d);
                    derivativeStructure = derivativeStructure.add(derivativeStructure2);
                }
                int i4 = i2 + 1;
                derivativeStructureArr3[i2] = derivativeStructure;
                i2 = i4;
            }
            return derivativeStructureArr3;
        }
    }

    public CurveFitter(DifferentiableMultivariateVectorOptimizer differentiableMultivariateVectorOptimizer) {
        this.oldOptimizer = differentiableMultivariateVectorOptimizer;
        this.optimizer = null;
        this.observations = new ArrayList();
    }

    public CurveFitter(MultivariateDifferentiableVectorOptimizer multivariateDifferentiableVectorOptimizer) {
        this.oldOptimizer = null;
        this.optimizer = multivariateDifferentiableVectorOptimizer;
        this.observations = new ArrayList();
    }

    public void addObservedPoint(double d, double d2) {
        addObservedPoint(1.0d, d, d2);
    }

    public void addObservedPoint(double d, double d2, double d3) {
        List<WeightedObservedPoint> list = this.observations;
        WeightedObservedPoint weightedObservedPoint = new WeightedObservedPoint(d, d2, d3);
        list.add(weightedObservedPoint);
    }

    public void addObservedPoint(WeightedObservedPoint weightedObservedPoint) {
        this.observations.add(weightedObservedPoint);
    }

    public WeightedObservedPoint[] getObservations() {
        return (WeightedObservedPoint[]) this.observations.toArray(new WeightedObservedPoint[this.observations.size()]);
    }

    public void clearObservations() {
        this.observations.clear();
    }

    public double[] fit(T t, double[] dArr) {
        return fit(Integer.MAX_VALUE, t, dArr);
    }

    public double[] fit(int i, T t, double[] dArr) {
        PointVectorValuePair pointVectorValuePair;
        double[] dArr2 = new double[this.observations.size()];
        double[] dArr3 = new double[this.observations.size()];
        int i2 = 0;
        for (WeightedObservedPoint weightedObservedPoint : this.observations) {
            dArr2[i2] = weightedObservedPoint.getY();
            dArr3[i2] = weightedObservedPoint.getWeight();
            i2++;
        }
        if (this.optimizer == null) {
            pointVectorValuePair = this.oldOptimizer.optimize(i, new OldTheoreticalValuesFunction(t), dArr2, dArr3, dArr);
        } else {
            pointVectorValuePair = this.optimizer.optimize(i, new TheoreticalValuesFunction(t), dArr2, dArr3, dArr);
        }
        return pointVectorValuePair.getPointRef();
    }
}
