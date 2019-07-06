package org.apache.commons.math3.analysis.solvers;

public class PegasusSolver extends BaseSecantSolver {
    public PegasusSolver() {
        super(1.0E-6d, Method.PEGASUS);
    }

    public PegasusSolver(double d) {
        super(d, Method.PEGASUS);
    }

    public PegasusSolver(double d, double d2) {
        super(d, d2, Method.PEGASUS);
    }

    public PegasusSolver(double d, double d2, double d3) {
        super(d, d2, d3, Method.PEGASUS);
    }
}
