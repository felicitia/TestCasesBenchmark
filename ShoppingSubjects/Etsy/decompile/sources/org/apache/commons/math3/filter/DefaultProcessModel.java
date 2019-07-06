package org.apache.commons.math3.filter;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class DefaultProcessModel implements ProcessModel {
    private RealMatrix controlMatrix;
    private RealMatrix initialErrorCovMatrix;
    private RealVector initialStateEstimateVector;
    private RealMatrix processNoiseCovMatrix;
    private RealMatrix stateTransitionMatrix;

    public DefaultProcessModel(double[][] dArr, double[][] dArr2, double[][] dArr3, double[] dArr4, double[][] dArr5) throws NullArgumentException, NoDataException, DimensionMismatchException {
        this((RealMatrix) new Array2DRowRealMatrix(dArr), (RealMatrix) new Array2DRowRealMatrix(dArr2), (RealMatrix) new Array2DRowRealMatrix(dArr3), (RealVector) new ArrayRealVector(dArr4), (RealMatrix) new Array2DRowRealMatrix(dArr5));
    }

    public DefaultProcessModel(double[][] dArr, double[][] dArr2, double[][] dArr3) throws NullArgumentException, NoDataException, DimensionMismatchException {
        this((RealMatrix) new Array2DRowRealMatrix(dArr), (RealMatrix) new Array2DRowRealMatrix(dArr2), (RealMatrix) new Array2DRowRealMatrix(dArr3), (RealVector) null, (RealMatrix) null);
    }

    public DefaultProcessModel(RealMatrix realMatrix, RealMatrix realMatrix2, RealMatrix realMatrix3, RealVector realVector, RealMatrix realMatrix4) {
        this.stateTransitionMatrix = realMatrix;
        this.controlMatrix = realMatrix2;
        this.processNoiseCovMatrix = realMatrix3;
        this.initialStateEstimateVector = realVector;
        this.initialErrorCovMatrix = realMatrix4;
    }

    public RealMatrix getStateTransitionMatrix() {
        return this.stateTransitionMatrix;
    }

    public RealMatrix getControlMatrix() {
        return this.controlMatrix;
    }

    public RealMatrix getProcessNoise() {
        return this.processNoiseCovMatrix;
    }

    public RealVector getInitialStateEstimate() {
        return this.initialStateEstimateVector;
    }

    public RealMatrix getInitialErrorCovariance() {
        return this.initialErrorCovMatrix;
    }
}
