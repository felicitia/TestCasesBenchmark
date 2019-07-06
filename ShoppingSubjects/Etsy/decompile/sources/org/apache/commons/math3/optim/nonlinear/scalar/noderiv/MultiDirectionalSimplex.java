package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optim.PointValuePair;

public class MultiDirectionalSimplex extends AbstractSimplex {
    private static final double DEFAULT_GAMMA = 0.5d;
    private static final double DEFAULT_KHI = 2.0d;
    private final double gamma;
    private final double khi;

    public MultiDirectionalSimplex(int i) {
        this(i, 1.0d);
    }

    public MultiDirectionalSimplex(int i, double d) {
        this(i, d, DEFAULT_KHI, DEFAULT_GAMMA);
    }

    public MultiDirectionalSimplex(int i, double d, double d2) {
        this(i, 1.0d, d, d2);
    }

    public MultiDirectionalSimplex(int i, double d, double d2, double d3) {
        super(i, d);
        this.khi = d2;
        this.gamma = d3;
    }

    public MultiDirectionalSimplex(double[] dArr) {
        this(dArr, (double) DEFAULT_KHI, (double) DEFAULT_GAMMA);
    }

    public MultiDirectionalSimplex(double[] dArr, double d, double d2) {
        super(dArr);
        this.khi = d;
        this.gamma = d2;
    }

    public MultiDirectionalSimplex(double[][] dArr) {
        this(dArr, (double) DEFAULT_KHI, (double) DEFAULT_GAMMA);
    }

    public MultiDirectionalSimplex(double[][] dArr, double d, double d2) {
        super(dArr);
        this.khi = d;
        this.gamma = d2;
    }

    public void iterate(MultivariateFunction multivariateFunction, Comparator<PointValuePair> comparator) {
        PointValuePair[] points = getPoints();
        PointValuePair pointValuePair = points[0];
        PointValuePair evaluateNewSimplex = evaluateNewSimplex(multivariateFunction, points, 1.0d, comparator);
        if (comparator.compare(evaluateNewSimplex, pointValuePair) < 0) {
            PointValuePair[] points2 = getPoints();
            if (comparator.compare(evaluateNewSimplex, evaluateNewSimplex(multivariateFunction, points, this.khi, comparator)) <= 0) {
                setPoints(points2);
            }
            return;
        }
        evaluateNewSimplex(multivariateFunction, points, this.gamma, comparator);
    }

    private PointValuePair evaluateNewSimplex(MultivariateFunction multivariateFunction, PointValuePair[] pointValuePairArr, double d, Comparator<PointValuePair> comparator) {
        double[] pointRef = pointValuePairArr[0].getPointRef();
        setPoint(0, pointValuePairArr[0]);
        int dimension = getDimension();
        for (int i = 1; i < getSize(); i++) {
            double[] pointRef2 = pointValuePairArr[i].getPointRef();
            double[] dArr = new double[dimension];
            for (int i2 = 0; i2 < dimension; i2++) {
                dArr[i2] = pointRef[i2] + ((pointRef[i2] - pointRef2[i2]) * d);
            }
            setPoint(i, new PointValuePair(dArr, Double.NaN, false));
        }
        evaluate(multivariateFunction, comparator);
        return getPoint(0);
    }
}
