package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.util.IterationManager;

public class ConjugateGradient extends PreconditionedIterativeLinearSolver {
    public static final String OPERATOR = "operator";
    public static final String VECTOR = "vector";
    private boolean check;
    private final double delta;

    public ConjugateGradient(int i, double d, boolean z) {
        super(i);
        this.delta = d;
        this.check = z;
    }

    public ConjugateGradient(IterationManager iterationManager, double d, boolean z) throws NullArgumentException {
        super(iterationManager);
        this.delta = d;
        this.check = z;
    }

    public final boolean getCheck() {
        return this.check;
    }

    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonPositiveDefiniteOperatorException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonPositiveDefiniteOperatorException {
        RealVector realVector3;
        RealVector realVector4;
        RealVector realVector5;
        ConjugateGradient conjugateGradient = this;
        RealLinearOperator realLinearOperator3 = realLinearOperator;
        RealLinearOperator realLinearOperator4 = realLinearOperator2;
        checkParameters(realLinearOperator, realLinearOperator2, realVector, realVector2);
        IterationManager iterationManager = getIterationManager();
        iterationManager.resetIterationCount();
        double norm = conjugateGradient.delta * realVector.getNorm();
        RealVector unmodifiableRealVector = RealVector.unmodifiableRealVector(realVector);
        iterationManager.incrementIterationCount();
        RealVector unmodifiableRealVector2 = RealVector.unmodifiableRealVector(realVector2);
        RealVector copy = realVector2.copy();
        RealVector combine = realVector.combine(1.0d, -1.0d, realLinearOperator3.operate(copy));
        RealVector unmodifiableRealVector3 = RealVector.unmodifiableRealVector(combine);
        double norm2 = combine.getNorm();
        RealVector realVector6 = realLinearOperator4 == null ? combine : null;
        DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent = r0;
        RealVector realVector7 = copy;
        RealVector realVector8 = combine;
        DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent2 = new DefaultIterativeLinearSolverEvent(conjugateGradient, iterationManager.getIterations(), unmodifiableRealVector2, unmodifiableRealVector, unmodifiableRealVector3, norm2);
        iterationManager.fireInitializationEvent(defaultIterativeLinearSolverEvent);
        if (norm2 <= norm) {
            iterationManager.fireTerminationEvent(defaultIterativeLinearSolverEvent);
            return realVector2;
        }
        double d = norm2;
        double d2 = 0.0d;
        while (true) {
            iterationManager.incrementIterationCount();
            DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent3 = new DefaultIterativeLinearSolverEvent(conjugateGradient, iterationManager.getIterations(), unmodifiableRealVector2, unmodifiableRealVector, unmodifiableRealVector3, d);
            iterationManager.fireIterationStartedEvent(defaultIterativeLinearSolverEvent3);
            if (realLinearOperator4 != null) {
                realVector3 = realVector8;
                realVector4 = realLinearOperator4.operate(realVector3);
            } else {
                realVector3 = realVector8;
                realVector4 = realVector6;
            }
            double dotProduct = realVector3.dotProduct(realVector4);
            if (!conjugateGradient.check || dotProduct > 0.0d) {
                if (iterationManager.getIterations() == 2) {
                    realVector5 = realVector7;
                    realVector5.setSubVector(0, realVector4);
                } else {
                    realVector5 = realVector7;
                    realVector5.combineToSelf(dotProduct / d2, 1.0d, realVector4);
                }
                RealLinearOperator realLinearOperator5 = realLinearOperator;
                RealVector operate = realLinearOperator5.operate(realVector5);
                double dotProduct2 = realVector5.dotProduct(operate);
                if (!conjugateGradient.check || dotProduct2 > 0.0d) {
                    double d3 = dotProduct / dotProduct2;
                    RealVector realVector9 = operate;
                    realVector2.combineToSelf(1.0d, d3, realVector5);
                    realVector3.combineToSelf(1.0d, -d3, realVector9);
                    double norm3 = realVector3.getNorm();
                    ConjugateGradient conjugateGradient2 = conjugateGradient;
                    DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent4 = r0;
                    RealVector realVector10 = realVector4;
                    RealVector realVector11 = realVector5;
                    DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent5 = new DefaultIterativeLinearSolverEvent(conjugateGradient2, iterationManager.getIterations(), unmodifiableRealVector2, unmodifiableRealVector, unmodifiableRealVector3, norm3);
                    iterationManager.fireIterationPerformedEvent(defaultIterativeLinearSolverEvent4);
                    if (norm3 <= norm) {
                        iterationManager.fireTerminationEvent(defaultIterativeLinearSolverEvent4);
                        return realVector2;
                    }
                    realVector8 = realVector3;
                    d = norm3;
                    realVector7 = realVector11;
                    realVector6 = realVector10;
                    d2 = dotProduct;
                    conjugateGradient = this;
                } else {
                    NonPositiveDefiniteOperatorException nonPositiveDefiniteOperatorException = new NonPositiveDefiniteOperatorException();
                    ExceptionContext context = nonPositiveDefiniteOperatorException.getContext();
                    context.setValue(OPERATOR, realLinearOperator5);
                    context.setValue(VECTOR, realVector5);
                    throw nonPositiveDefiniteOperatorException;
                }
            } else {
                NonPositiveDefiniteOperatorException nonPositiveDefiniteOperatorException2 = new NonPositiveDefiniteOperatorException();
                ExceptionContext context2 = nonPositiveDefiniteOperatorException2.getContext();
                context2.setValue(OPERATOR, realLinearOperator4);
                context2.setValue(VECTOR, realVector3);
                throw nonPositiveDefiniteOperatorException2;
            }
        }
    }
}
