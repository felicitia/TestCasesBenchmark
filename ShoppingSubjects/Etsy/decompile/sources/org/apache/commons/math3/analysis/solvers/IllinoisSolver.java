package org.apache.commons.math3.analysis.solvers;

public class IllinoisSolver extends BaseSecantSolver {
    public IllinoisSolver() {
        super(1.0E-6d, Method.ILLINOIS);
    }

    public IllinoisSolver(double d) {
        super(d, Method.ILLINOIS);
    }

    public IllinoisSolver(double d, double d2) {
        super(d, d2, Method.ILLINOIS);
    }

    public IllinoisSolver(double d, double d2, double d3) {
        super(d, d2, d3, Method.PEGASUS);
    }
}
