package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.OptimizationData;

public class Weight implements OptimizationData {
    private final RealMatrix weightMatrix;

    public Weight(double[] dArr) {
        int length = dArr.length;
        this.weightMatrix = MatrixUtils.createRealMatrix(length, length);
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
