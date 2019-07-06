package org.apache.commons.math3.optimization.general;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.SimpleVectorValueChecker;

@Deprecated
public class GaussNewtonOptimizer extends AbstractLeastSquaresOptimizer {
    private final boolean useLU;

    @Deprecated
    public GaussNewtonOptimizer() {
        this(true);
    }

    public GaussNewtonOptimizer(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        this(true, convergenceChecker);
    }

    @Deprecated
    public GaussNewtonOptimizer(boolean z) {
        this(z, new SimpleVectorValueChecker());
    }

    public GaussNewtonOptimizer(boolean z, ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        super(convergenceChecker);
        this.useLU = z;
    }

    public PointVectorValuePair doOptimize() {
        ConvergenceChecker convergenceChecker = getConvergenceChecker();
        if (convergenceChecker == null) {
            throw new NullArgumentException();
        }
        int length = getTarget().length;
        RealMatrix weight = getWeight();
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = weight.getEntry(i, i);
        }
        double[] startPoint = getStartPoint();
        int length2 = startPoint.length;
        PointVectorValuePair pointVectorValuePair = null;
        boolean z = false;
        int i2 = 0;
        while (!z) {
            i2++;
            double[] computeObjectiveValue = computeObjectiveValue(startPoint);
            double[] computeResiduals = computeResiduals(computeObjectiveValue);
            RealMatrix computeWeightedJacobian = computeWeightedJacobian(startPoint);
            PointVectorValuePair pointVectorValuePair2 = new PointVectorValuePair(startPoint, computeObjectiveValue);
            double[] dArr2 = new double[length2];
            double[][] dArr3 = (double[][]) Array.newInstance(double.class, new int[]{length2, length2});
            for (int i3 = 0; i3 < length; i3++) {
                double[] row = computeWeightedJacobian.getRow(i3);
                double d = dArr[i3];
                double d2 = computeResiduals[i3] * d;
                for (int i4 = 0; i4 < length2; i4++) {
                    dArr2[i4] = dArr2[i4] + (row[i4] * d2);
                }
                int i5 = 0;
                while (i5 < length2) {
                    double[] dArr4 = dArr3[i5];
                    double d3 = row[i5] * d;
                    int i6 = length;
                    for (int i7 = 0; i7 < length2; i7++) {
                        dArr4[i7] = dArr4[i7] + (row[i7] * d3);
                    }
                    i5++;
                    length = i6;
                }
                int i8 = length;
            }
            int i9 = length;
            try {
                BlockRealMatrix blockRealMatrix = new BlockRealMatrix(dArr3);
                double[] array = (this.useLU ? new LUDecomposition(blockRealMatrix).getSolver() : new QRDecomposition(blockRealMatrix).getSolver()).solve((RealVector) new ArrayRealVector(dArr2, false)).toArray();
                for (int i10 = 0; i10 < length2; i10++) {
                    startPoint[i10] = startPoint[i10] + array[i10];
                }
                if (pointVectorValuePair != null) {
                    boolean converged = convergenceChecker.converged(i2, pointVectorValuePair, pointVectorValuePair2);
                    if (converged) {
                        this.cost = computeCost(computeResiduals);
                        this.point = pointVectorValuePair2.getPoint();
                        return pointVectorValuePair2;
                    }
                    z = converged;
                }
                pointVectorValuePair = pointVectorValuePair2;
                length = i9;
            } catch (SingularMatrixException unused) {
                throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, new Object[0]);
            }
        }
        throw new MathInternalError();
    }
}
