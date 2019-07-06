package org.apache.commons.math3.analysis.solvers;

public class RegulaFalsiSolver extends BaseSecantSolver {
    public RegulaFalsiSolver() {
        super(1.0E-6d, Method.REGULA_FALSI);
    }

    public RegulaFalsiSolver(double d) {
        super(d, Method.REGULA_FALSI);
    }

    public RegulaFalsiSolver(double d, double d2) {
        super(d, d2, Method.REGULA_FALSI);
    }

    public RegulaFalsiSolver(double d, double d2, double d3) {
        super(d, d2, d3, Method.REGULA_FALSI);
    }
}
