package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.IterationManager;
import org.apache.commons.math3.util.MathUtils;

public abstract class PreconditionedIterativeLinearSolver extends IterativeLinearSolver {
    public abstract RealVector solveInPlace(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException;

    public PreconditionedIterativeLinearSolver(int i) {
        super(i);
    }

    public PreconditionedIterativeLinearSolver(IterationManager iterationManager) throws NullArgumentException {
        super(iterationManager);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(realVector2);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, realVector2.copy());
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(realLinearOperator);
        ArrayRealVector arrayRealVector = new ArrayRealVector(realLinearOperator.getColumnDimension());
        arrayRealVector.set(0.0d);
        return solveInPlace(realLinearOperator, null, realVector, arrayRealVector);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(realVector2);
        return solveInPlace(realLinearOperator, null, realVector, realVector2.copy());
    }

    protected static void checkParameters(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException {
        checkParameters(realLinearOperator, realVector, realVector2);
        if (realLinearOperator2 == null) {
            return;
        }
        if (realLinearOperator2.getColumnDimension() != realLinearOperator2.getRowDimension()) {
            throw new NonSquareOperatorException(realLinearOperator2.getColumnDimension(), realLinearOperator2.getRowDimension());
        } else if (realLinearOperator2.getRowDimension() != realLinearOperator.getRowDimension()) {
            throw new DimensionMismatchException(realLinearOperator2.getRowDimension(), realLinearOperator.getRowDimension());
        }
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(realLinearOperator);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, new ArrayRealVector(realLinearOperator.getColumnDimension()));
    }

    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        return solveInPlace(realLinearOperator, null, realVector, realVector2);
    }
}
