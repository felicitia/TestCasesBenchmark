package org.apache.commons.math3.ode;

public class FirstOrderConverter implements FirstOrderDifferentialEquations {
    private final int dimension;
    private final SecondOrderDifferentialEquations equations;
    private final double[] z = new double[this.dimension];
    private final double[] zDDot = new double[this.dimension];
    private final double[] zDot = new double[this.dimension];

    public FirstOrderConverter(SecondOrderDifferentialEquations secondOrderDifferentialEquations) {
        this.equations = secondOrderDifferentialEquations;
        this.dimension = secondOrderDifferentialEquations.getDimension();
    }

    public int getDimension() {
        return 2 * this.dimension;
    }

    public void computeDerivatives(double d, double[] dArr, double[] dArr2) {
        System.arraycopy(dArr, 0, this.z, 0, this.dimension);
        System.arraycopy(dArr, this.dimension, this.zDot, 0, this.dimension);
        this.equations.computeSecondDerivatives(d, this.z, this.zDot, this.zDDot);
        System.arraycopy(this.zDot, 0, dArr2, 0, this.dimension);
        System.arraycopy(this.zDDot, 0, dArr2, this.dimension, this.dimension);
    }
}
