package org.apache.commons.math3.analysis;

public interface DifferentiableUnivariateMatrixFunction extends UnivariateMatrixFunction {
    UnivariateMatrixFunction derivative();
}
