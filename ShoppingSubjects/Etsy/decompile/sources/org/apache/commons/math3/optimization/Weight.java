package org.apache.commons.math3.optimization;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;

@Deprecated
public class Weight implements OptimizationData {
    private final RealMatrix weightMatrix;

    public Weight(double[] dArr) {
        int length = dArr.length;
        this.weightMatrix = new Array2DRowRealMatrix(length, length);
        for (int i = 0; i < length; i++) {
            this.weightMatrix.setEntry(i, i, dArr[i]);
        }
    }

    public Weight(RealMatrix realMatrix) {
        if (realMatrix.getColumnDimension() != realMatrix.getRowDimension()) {
            throw new NonSquareMatrixException(realMatrix.getColumnDimension(), realMatrix.getRowDimension());
        }
        this.weightMatrix = realMatrix.copy();
    }

    public RealMatrix getWeight() {
        return this.weightMatrix.copy();
    }
}
