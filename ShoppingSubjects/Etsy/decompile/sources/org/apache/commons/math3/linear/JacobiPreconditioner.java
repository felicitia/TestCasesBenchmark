package org.apache.commons.math3.linear;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Sqrt;
import org.apache.commons.math3.util.MathArrays;

public class JacobiPreconditioner extends RealLinearOperator {
    private final ArrayRealVector diag;

    public JacobiPreconditioner(double[] dArr, boolean z) {
        this.diag = new ArrayRealVector(dArr, z);
    }

    public static JacobiPreconditioner create(RealLinearOperator realLinearOperator) throws NonSquareOperatorException {
        int columnDimension = realLinearOperator.getColumnDimension();
        if (realLinearOperator.getRowDimension() != columnDimension) {
            throw new NonSquareOperatorException(realLinearOperator.getRowDimension(), columnDimension);
        }
        double[] dArr = new double[columnDimension];
        if (realLinearOperator instanceof AbstractRealMatrix) {
            AbstractRealMatrix abstractRealMatrix = (AbstractRealMatrix) realLinearOperator;
            for (int i = 0; i < columnDimension; i++) {
                dArr[i] = abstractRealMatrix.getEntry(i, i);
            }
        } else {
            ArrayRealVector arrayRealVector = new ArrayRealVector(columnDimension);
            for (int i2 = 0; i2 < columnDimension; i2++) {
                arrayRealVector.set(0.0d);
                arrayRealVector.setEntry(i2, 1.0d);
                dArr[i2] = realLinearOperator.operate(arrayRealVector).getEntry(i2);
            }
        }
        return new JacobiPreconditioner(dArr, false);
    }

    public int getColumnDimension() {
        return this.diag.getDimension();
    }

    public int getRowDimension() {
        return this.diag.getDimension();
    }

    public RealVector operate(RealVector realVector) {
        return new ArrayRealVector(MathArrays.ebeDivide(realVector.toArray(), this.diag.toArray()), false);
    }

    public RealLinearOperator sqrt() {
        final ArrayRealVector map = this.diag.map((UnivariateFunction) new Sqrt());
        return new RealLinearOperator() {
            public RealVector operate(RealVector realVector) {
                return new ArrayRealVector(MathArrays.ebeDivide(realVector.toArray(), map.toArray()), false);
            }

            public int getRowDimension() {
                return map.getDimension();
            }

            public int getColumnDimension() {
                return map.getDimension();
            }
        };
    }
}
