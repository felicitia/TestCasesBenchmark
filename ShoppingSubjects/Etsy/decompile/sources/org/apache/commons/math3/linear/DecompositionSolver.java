package org.apache.commons.math3.linear;

public interface DecompositionSolver {
    RealMatrix getInverse();

    boolean isNonSingular();

    RealMatrix solve(RealMatrix realMatrix);

    RealVector solve(RealVector realVector);
}
