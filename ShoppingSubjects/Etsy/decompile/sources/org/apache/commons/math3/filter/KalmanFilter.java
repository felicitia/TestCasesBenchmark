package org.apache.commons.math3.filter;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.CholeskyDecomposition;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.util.MathUtils;

public class KalmanFilter {
    private RealMatrix controlMatrix;
    private RealMatrix errorCovariance;
    private RealMatrix measurementMatrix;
    private RealMatrix measurementMatrixT;
    private final MeasurementModel measurementModel;
    private final ProcessModel processModel;
    private RealVector stateEstimation;
    private RealMatrix transitionMatrix = this.processModel.getStateTransitionMatrix();
    private RealMatrix transitionMatrixT;

    public KalmanFilter(ProcessModel processModel2, MeasurementModel measurementModel2) throws NullArgumentException, NonSquareMatrixException, DimensionMismatchException, MatrixDimensionMismatchException {
        MathUtils.checkNotNull(processModel2);
        MathUtils.checkNotNull(measurementModel2);
        this.processModel = processModel2;
        this.measurementModel = measurementModel2;
        MathUtils.checkNotNull(this.transitionMatrix);
        this.transitionMatrixT = this.transitionMatrix.transpose();
        if (this.processModel.getControlMatrix() == null) {
            this.controlMatrix = new Array2DRowRealMatrix();
        } else {
            this.controlMatrix = this.processModel.getControlMatrix();
        }
        this.measurementMatrix = this.measurementModel.getMeasurementMatrix();
        MathUtils.checkNotNull(this.measurementMatrix);
        this.measurementMatrixT = this.measurementMatrix.transpose();
        RealMatrix processNoise = this.processModel.getProcessNoise();
        MathUtils.checkNotNull(processNoise);
        RealMatrix measurementNoise = this.measurementModel.getMeasurementNoise();
        MathUtils.checkNotNull(measurementNoise);
        if (this.processModel.getInitialStateEstimate() == null) {
            this.stateEstimation = new ArrayRealVector(this.transitionMatrix.getColumnDimension());
        } else {
            this.stateEstimation = this.processModel.getInitialStateEstimate();
        }
        if (this.transitionMatrix.getColumnDimension() != this.stateEstimation.getDimension()) {
            throw new DimensionMismatchException(this.transitionMatrix.getColumnDimension(), this.stateEstimation.getDimension());
        }
        if (this.processModel.getInitialErrorCovariance() == null) {
            this.errorCovariance = processNoise.copy();
        } else {
            this.errorCovariance = this.processModel.getInitialErrorCovariance();
        }
        if (!this.transitionMatrix.isSquare()) {
            throw new NonSquareMatrixException(this.transitionMatrix.getRowDimension(), this.transitionMatrix.getColumnDimension());
        } else if (this.controlMatrix == null || this.controlMatrix.getRowDimension() <= 0 || this.controlMatrix.getColumnDimension() <= 0 || (this.controlMatrix.getRowDimension() == this.transitionMatrix.getRowDimension() && this.controlMatrix.getColumnDimension() == 1)) {
            MatrixUtils.checkAdditionCompatible(this.transitionMatrix, processNoise);
            if (this.measurementMatrix.getColumnDimension() != this.transitionMatrix.getRowDimension()) {
                throw new MatrixDimensionMismatchException(this.measurementMatrix.getRowDimension(), this.measurementMatrix.getColumnDimension(), this.measurementMatrix.getRowDimension(), this.transitionMatrix.getRowDimension());
            } else if (measurementNoise.getRowDimension() != this.measurementMatrix.getRowDimension() || measurementNoise.getColumnDimension() != 1) {
                throw new MatrixDimensionMismatchException(measurementNoise.getRowDimension(), measurementNoise.getColumnDimension(), this.measurementMatrix.getRowDimension(), 1);
            }
        } else {
            throw new MatrixDimensionMismatchException(this.controlMatrix.getRowDimension(), this.controlMatrix.getColumnDimension(), this.transitionMatrix.getRowDimension(), 1);
        }
    }

    public int getStateDimension() {
        return this.stateEstimation.getDimension();
    }

    public int getMeasurementDimension() {
        return this.measurementMatrix.getRowDimension();
    }

    public double[] getStateEstimation() {
        return this.stateEstimation.toArray();
    }

    public RealVector getStateEstimationVector() {
        return this.stateEstimation.copy();
    }

    public double[][] getErrorCovariance() {
        return this.errorCovariance.getData();
    }

    public RealMatrix getErrorCovarianceMatrix() {
        return this.errorCovariance.copy();
    }

    public void predict() {
        predict((RealVector) null);
    }

    public void predict(double[] dArr) throws DimensionMismatchException {
        predict((RealVector) new ArrayRealVector(dArr));
    }

    public void predict(RealVector realVector) throws DimensionMismatchException {
        if (realVector == null || realVector.getDimension() == this.controlMatrix.getColumnDimension()) {
            this.stateEstimation = this.transitionMatrix.operate(this.stateEstimation);
            if (realVector != null) {
                this.stateEstimation = this.stateEstimation.add(this.controlMatrix.operate(realVector));
            }
            this.errorCovariance = this.transitionMatrix.multiply(this.errorCovariance).multiply(this.transitionMatrixT).add(this.processModel.getProcessNoise());
            return;
        }
        throw new DimensionMismatchException(realVector.getDimension(), this.controlMatrix.getColumnDimension());
    }

    public void correct(double[] dArr) throws NullArgumentException, DimensionMismatchException, SingularMatrixException {
        correct((RealVector) new ArrayRealVector(dArr));
    }

    public void correct(RealVector realVector) throws NullArgumentException, DimensionMismatchException, SingularMatrixException {
        MathUtils.checkNotNull(realVector);
        if (realVector.getDimension() != this.measurementMatrix.getRowDimension()) {
            throw new DimensionMismatchException(realVector.getDimension(), this.measurementMatrix.getRowDimension());
        }
        RealMatrix inverse = new CholeskyDecomposition(this.measurementMatrix.multiply(this.errorCovariance).multiply(this.measurementMatrixT).add(this.measurementModel.getMeasurementNoise())).getSolver().getInverse();
        RealVector subtract = realVector.subtract(this.measurementMatrix.operate(this.stateEstimation));
        RealMatrix multiply = this.errorCovariance.multiply(this.measurementMatrixT).multiply(inverse);
        this.stateEstimation = this.stateEstimation.add(multiply.operate(subtract));
        this.errorCovariance = MatrixUtils.createRealIdentityMatrix(multiply.getRowDimension()).subtract(multiply.multiply(this.measurementMatrix)).multiply(this.errorCovariance);
    }
}
