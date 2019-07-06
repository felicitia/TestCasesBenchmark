package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;

public abstract class BaseMultivariateOptimizer<PAIR> extends BaseOptimizer<PAIR> {
    private double[] lowerBound;
    private double[] start;
    private double[] upperBound;

    protected BaseMultivariateOptimizer(ConvergenceChecker<PAIR> convergenceChecker) {
        super(convergenceChecker);
    }

    public PAIR optimize(OptimizationData... optimizationDataArr) {
        parseOptimizationData(optimizationDataArr);
        checkParameters();
        return super.optimize(optimizationDataArr);
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (InitialGuess initialGuess : optimizationDataArr) {
            if (initialGuess instanceof InitialGuess) {
                this.start = initialGuess.getInitialGuess();
            } else if (initialGuess instanceof SimpleBounds) {
                SimpleBounds simpleBounds = (SimpleBounds) initialGuess;
                this.lowerBound = simpleBounds.getLower();
                this.upperBound = simpleBounds.getUpper();
            }
        }
    }

    public double[] getStartPoint() {
        if (this.start == null) {
            return null;
        }
        return (double[]) this.start.clone();
    }

    public double[] getLowerBound() {
        if (this.lowerBound == null) {
            return null;
        }
        return (double[]) this.lowerBound.clone();
    }

    public double[] getUpperBound() {
        if (this.upperBound == null) {
            return null;
        }
        return (double[]) this.upperBound.clone();
    }

    private void checkParameters() {
        if (this.start != null) {
            int length = this.start.length;
            if (this.lowerBound != null) {
                if (this.lowerBound.length != length) {
                    throw new DimensionMismatchException(this.lowerBound.length, length);
                }
                for (int i = 0; i < length; i++) {
                    double d = this.start[i];
                    double d2 = this.lowerBound[i];
                    if (d < d2) {
                        throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(d2), true);
                    }
                }
            }
            if (this.upperBound == null) {
                return;
            }
            if (this.upperBound.length != length) {
                throw new DimensionMismatchException(this.upperBound.length, length);
            }
            for (int i2 = 0; i2 < length; i2++) {
                double d3 = this.start[i2];
                double d4 = this.upperBound[i2];
                if (d3 > d4) {
                    throw new NumberIsTooLargeException(Double.valueOf(d3), Double.valueOf(d4), true);
                }
            }
        }
    }
}
