package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optim.PointValuePair;

public class NelderMeadSimplex extends AbstractSimplex {
    private static final double DEFAULT_GAMMA = 0.5d;
    private static final double DEFAULT_KHI = 2.0d;
    private static final double DEFAULT_RHO = 1.0d;
    private static final double DEFAULT_SIGMA = 0.5d;
    private final double gamma;
    private final double khi;
    private final double rho;
    private final double sigma;

    public NelderMeadSimplex(int i) {
        this(i, DEFAULT_RHO);
    }

    public NelderMeadSimplex(int i, double d) {
        this(i, d, DEFAULT_RHO, DEFAULT_KHI, 0.5d, 0.5d);
    }

    public NelderMeadSimplex(int i, double d, double d2, double d3, double d4, double d5) {
        super(i, d);
        this.rho = d2;
        this.khi = d3;
        this.gamma = d4;
        this.sigma = d5;
    }

    public NelderMeadSimplex(int i, double d, double d2, double d3, double d4) {
        this(i, DEFAULT_RHO, d, d2, d3, d4);
    }

    public NelderMeadSimplex(double[] dArr) {
        this(dArr, (double) DEFAULT_RHO, (double) DEFAULT_KHI, 0.5d, 0.5d);
    }

    public NelderMeadSimplex(double[] dArr, double d, double d2, double d3, double d4) {
        super(dArr);
        this.rho = d;
        this.khi = d2;
        this.gamma = d3;
        this.sigma = d4;
    }

    public NelderMeadSimplex(double[][] dArr) {
        this(dArr, (double) DEFAULT_RHO, (double) DEFAULT_KHI, 0.5d, 0.5d);
    }

    public NelderMeadSimplex(double[][] dArr, double d, double d2, double d3, double d4) {
        super(dArr);
        this.rho = d;
        this.khi = d2;
        this.gamma = d3;
        this.sigma = d4;
    }

    public void iterate(MultivariateFunction multivariateFunction, Comparator<PointValuePair> comparator) {
        MultivariateFunction multivariateFunction2 = multivariateFunction;
        Comparator<PointValuePair> comparator2 = comparator;
        int dimension = getDimension();
        PointValuePair point = getPoint(0);
        PointValuePair point2 = getPoint(dimension - 1);
        PointValuePair point3 = getPoint(dimension);
        double[] pointRef = point3.getPointRef();
        double[] dArr = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            double[] pointRef2 = getPoint(i).getPointRef();
            for (int i2 = 0; i2 < dimension; i2++) {
                dArr[i2] = dArr[i2] + pointRef2[i2];
            }
        }
        double d = DEFAULT_RHO / ((double) dimension);
        for (int i3 = 0; i3 < dimension; i3++) {
            dArr[i3] = dArr[i3] * d;
        }
        double[] dArr2 = new double[dimension];
        for (int i4 = 0; i4 < dimension; i4++) {
            dArr2[i4] = dArr[i4] + (this.rho * (dArr[i4] - pointRef[i4]));
        }
        PointValuePair pointValuePair = new PointValuePair(dArr2, multivariateFunction2.value(dArr2), false);
        if (comparator2.compare(point, pointValuePair) <= 0 && comparator2.compare(pointValuePair, point2) < 0) {
            replaceWorstPoint(pointValuePair, comparator2);
        } else if (comparator2.compare(pointValuePair, point) < 0) {
            double[] dArr3 = new double[dimension];
            for (int i5 = 0; i5 < dimension; i5++) {
                dArr3[i5] = dArr[i5] + (this.khi * (dArr2[i5] - dArr[i5]));
            }
            PointValuePair pointValuePair2 = new PointValuePair(dArr3, multivariateFunction2.value(dArr3), false);
            if (comparator2.compare(pointValuePair2, pointValuePair) < 0) {
                replaceWorstPoint(pointValuePair2, comparator2);
            } else {
                replaceWorstPoint(pointValuePair, comparator2);
            }
        } else {
            if (comparator2.compare(pointValuePair, point3) < 0) {
                double[] dArr4 = new double[dimension];
                for (int i6 = 0; i6 < dimension; i6++) {
                    dArr4[i6] = dArr[i6] + (this.gamma * (dArr2[i6] - dArr[i6]));
                }
                PointValuePair pointValuePair3 = new PointValuePair(dArr4, multivariateFunction2.value(dArr4), false);
                if (comparator2.compare(pointValuePair3, pointValuePair) <= 0) {
                    replaceWorstPoint(pointValuePair3, comparator2);
                    return;
                }
            } else {
                double[] dArr5 = new double[dimension];
                for (int i7 = 0; i7 < dimension; i7++) {
                    dArr5[i7] = dArr[i7] - (this.gamma * (dArr[i7] - pointRef[i7]));
                }
                PointValuePair pointValuePair4 = new PointValuePair(dArr5, multivariateFunction2.value(dArr5), false);
                if (comparator2.compare(pointValuePair4, point3) < 0) {
                    replaceWorstPoint(pointValuePair4, comparator2);
                    return;
                }
            }
            double[] pointRef3 = getPoint(0).getPointRef();
            for (int i8 = 1; i8 <= dimension; i8++) {
                double[] point4 = getPoint(i8).getPoint();
                for (int i9 = 0; i9 < dimension; i9++) {
                    point4[i9] = pointRef3[i9] + (this.sigma * (point4[i9] - pointRef3[i9]));
                }
                setPoint(i8, new PointValuePair(point4, Double.NaN, false));
            }
            evaluate(multivariateFunction, comparator);
        }
    }
}
