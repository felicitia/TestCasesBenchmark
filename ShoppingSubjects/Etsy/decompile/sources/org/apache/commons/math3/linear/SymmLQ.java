package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.IterationManager;
import org.apache.commons.math3.util.MathUtils;

public class SymmLQ extends PreconditionedIterativeLinearSolver {
    private static final String OPERATOR = "operator";
    private static final String THRESHOLD = "threshold";
    private static final String VECTOR = "vector";
    private static final String VECTOR1 = "vector1";
    private static final String VECTOR2 = "vector2";
    private final boolean check;
    private final double delta;

    private static class State {
        static final double CBRT_MACH_PREC = FastMath.cbrt(MACH_PREC);
        static final double MACH_PREC = FastMath.ulp(1.0d);
        private final RealLinearOperator a;
        private final RealVector b;
        private boolean bIsNull;
        private double beta;
        private double beta1;
        private double bstep;
        private double cgnorm;
        private final boolean check;
        private double dbar;
        private final double delta;
        private double gammaZeta;
        private double gbar;
        private double gmax;
        private double gmin;
        private final boolean goodb;
        private boolean hasConverged;
        private double lqnorm;
        private final RealLinearOperator m;
        private final RealVector mb;
        private double minusEpsZeta;
        private double oldb;
        private RealVector r1;
        private RealVector r2;
        private double rnorm;
        private final double shift;
        private double snprod;
        private double tnorm;
        private RealVector wbar;
        private final RealVector xL;
        private RealVector y;
        private double ynorm2;

        public State(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, boolean z, double d, double d2, boolean z2) {
            this.a = realLinearOperator;
            this.m = realLinearOperator2;
            this.b = realVector;
            this.xL = new ArrayRealVector(realVector.getDimension());
            this.goodb = z;
            this.shift = d;
            if (realLinearOperator2 != null) {
                realVector = realLinearOperator2.operate(realVector);
            }
            this.mb = realVector;
            this.hasConverged = false;
            this.check = z2;
            this.delta = d2;
        }

        private static void checkSymmetry(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2, RealVector realVector3) throws NonSelfAdjointOperatorException {
            double dotProduct = realVector2.dotProduct(realVector2);
            double d = (MACH_PREC + dotProduct) * CBRT_MACH_PREC;
            if (FastMath.abs(dotProduct - realVector.dotProduct(realVector3)) > d) {
                NonSelfAdjointOperatorException nonSelfAdjointOperatorException = new NonSelfAdjointOperatorException();
                ExceptionContext context = nonSelfAdjointOperatorException.getContext();
                context.setValue("operator", realLinearOperator);
                context.setValue(SymmLQ.VECTOR1, realVector);
                context.setValue(SymmLQ.VECTOR2, realVector2);
                context.setValue(SymmLQ.THRESHOLD, Double.valueOf(d));
                throw nonSelfAdjointOperatorException;
            }
        }

        private static void throwNPDLOException(RealLinearOperator realLinearOperator, RealVector realVector) throws NonPositiveDefiniteOperatorException {
            NonPositiveDefiniteOperatorException nonPositiveDefiniteOperatorException = new NonPositiveDefiniteOperatorException();
            ExceptionContext context = nonPositiveDefiniteOperatorException.getContext();
            context.setValue("operator", realLinearOperator);
            context.setValue("vector", realVector);
            throw nonPositiveDefiniteOperatorException;
        }

        private static void daxpy(double d, RealVector realVector, RealVector realVector2) {
            int dimension = realVector.getDimension();
            for (int i = 0; i < dimension; i++) {
                realVector2.setEntry(i, (realVector.getEntry(i) * d) + realVector2.getEntry(i));
            }
        }

        private static void daxpbypz(double d, RealVector realVector, double d2, RealVector realVector2, RealVector realVector3) {
            int dimension = realVector3.getDimension();
            for (int i = 0; i < dimension; i++) {
                realVector3.setEntry(i, (realVector.getEntry(i) * d) + (realVector2.getEntry(i) * d2) + realVector3.getEntry(i));
            }
        }

        /* access modifiers changed from: 0000 */
        public void refineSolution(RealVector realVector) {
            int dimension = this.xL.getDimension();
            int i = 0;
            if (this.lqnorm >= this.cgnorm) {
                double sqrt = this.gammaZeta / (this.gbar == 0.0d ? FastMath.sqrt(this.tnorm) * MACH_PREC : this.gbar);
                double d = (this.bstep + (this.snprod * sqrt)) / this.beta1;
                if (!this.goodb) {
                    while (i < dimension) {
                        realVector.setEntry(i, this.xL.getEntry(i) + (this.wbar.getEntry(i) * sqrt));
                        i++;
                    }
                    return;
                }
                while (i < dimension) {
                    realVector.setEntry(i, this.xL.getEntry(i) + (this.wbar.getEntry(i) * sqrt) + (this.mb.getEntry(i) * d));
                    i++;
                }
            } else if (!this.goodb) {
                realVector.setSubVector(0, this.xL);
            } else {
                double d2 = this.bstep / this.beta1;
                while (i < dimension) {
                    realVector.setEntry(i, this.xL.getEntry(i) + (this.mb.getEntry(i) * d2));
                    i++;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void init() {
            this.xL.set(0.0d);
            this.r1 = this.b.copy();
            this.y = this.m == null ? this.b.copy() : this.m.operate(this.r1);
            if (this.m != null && this.check) {
                checkSymmetry(this.m, this.r1, this.y, this.m.operate(this.y));
            }
            this.beta1 = this.r1.dotProduct(this.y);
            if (this.beta1 < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            if (this.beta1 == 0.0d) {
                this.bIsNull = true;
                return;
            }
            this.bIsNull = false;
            this.beta1 = FastMath.sqrt(this.beta1);
            RealVector mapMultiply = this.y.mapMultiply(1.0d / this.beta1);
            this.y = this.a.operate(mapMultiply);
            if (this.check) {
                checkSymmetry(this.a, mapMultiply, this.y, this.a.operate(this.y));
            }
            daxpy(-this.shift, mapMultiply, this.y);
            double dotProduct = mapMultiply.dotProduct(this.y);
            daxpy((-dotProduct) / this.beta1, this.r1, this.y);
            daxpy((-mapMultiply.dotProduct(this.y)) / mapMultiply.dotProduct(mapMultiply), mapMultiply, this.y);
            this.r2 = this.y.copy();
            if (this.m != null) {
                this.y = this.m.operate(this.r2);
            }
            this.oldb = this.beta1;
            this.beta = this.r2.dotProduct(this.y);
            if (this.beta < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            this.beta = FastMath.sqrt(this.beta);
            this.cgnorm = this.beta1;
            this.gbar = dotProduct;
            this.dbar = this.beta;
            this.gammaZeta = this.beta1;
            this.minusEpsZeta = 0.0d;
            this.bstep = 0.0d;
            this.snprod = 1.0d;
            this.tnorm = (dotProduct * dotProduct) + (this.beta * this.beta);
            this.ynorm2 = 0.0d;
            this.gmax = FastMath.abs(dotProduct) + MACH_PREC;
            this.gmin = this.gmax;
            if (this.goodb) {
                this.wbar = new ArrayRealVector(this.a.getRowDimension());
                this.wbar.set(0.0d);
            } else {
                this.wbar = mapMultiply;
            }
            updateNorms();
        }

        /* access modifiers changed from: 0000 */
        public void update() {
            RealVector mapMultiply = this.y.mapMultiply(1.0d / this.beta);
            this.y = this.a.operate(mapMultiply);
            daxpbypz(-this.shift, mapMultiply, (-this.beta) / this.oldb, this.r1, this.y);
            double dotProduct = mapMultiply.dotProduct(this.y);
            daxpy((-dotProduct) / this.beta, this.r2, this.y);
            this.r1 = this.r2;
            this.r2 = this.y;
            if (this.m != null) {
                this.y = this.m.operate(this.r2);
            }
            this.oldb = this.beta;
            this.beta = this.r2.dotProduct(this.y);
            if (this.beta < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            this.beta = FastMath.sqrt(this.beta);
            this.tnorm += (dotProduct * dotProduct) + (this.oldb * this.oldb) + (this.beta * this.beta);
            double sqrt = FastMath.sqrt((this.gbar * this.gbar) + (this.oldb * this.oldb));
            double d = this.gbar / sqrt;
            double d2 = this.oldb / sqrt;
            double d3 = (this.dbar * d) + (d2 * dotProduct);
            this.gbar = (this.dbar * d2) - (dotProduct * d);
            double d4 = this.beta * d2;
            this.dbar = (-d) * this.beta;
            double d5 = this.gammaZeta / sqrt;
            double d6 = d5 * d;
            double d7 = d5 * d2;
            double d8 = d4;
            int dimension = this.xL.getDimension();
            int i = 0;
            while (i < dimension) {
                int i2 = dimension;
                double entry = this.xL.getEntry(i);
                double entry2 = mapMultiply.getEntry(i);
                double entry3 = this.wbar.getEntry(i);
                double d9 = d6;
                this.xL.setEntry(i, entry + (entry3 * d6) + (entry2 * d7));
                this.wbar.setEntry(i, (entry3 * d2) - (entry2 * d));
                i++;
                dimension = i2;
                d6 = d9;
            }
            this.bstep += this.snprod * d * d5;
            this.snprod *= d2;
            this.gmax = FastMath.max(this.gmax, sqrt);
            this.gmin = FastMath.min(this.gmin, sqrt);
            this.ynorm2 += d5 * d5;
            this.gammaZeta = this.minusEpsZeta - (d3 * d5);
            this.minusEpsZeta = (-d8) * d5;
            updateNorms();
        }

        private void updateNorms() {
            double d;
            double sqrt = FastMath.sqrt(this.tnorm);
            double d2 = MACH_PREC * sqrt;
            double sqrt2 = sqrt * FastMath.sqrt(this.ynorm2);
            double d3 = MACH_PREC * sqrt2;
            double d4 = sqrt2 * this.delta;
            if (this.gbar != 0.0d) {
                d2 = this.gbar;
            }
            this.lqnorm = FastMath.sqrt((this.gammaZeta * this.gammaZeta) + (this.minusEpsZeta * this.minusEpsZeta));
            this.cgnorm = ((this.snprod * this.beta1) * this.beta) / FastMath.abs(d2);
            if (this.lqnorm <= this.cgnorm) {
                d = this.gmax / this.gmin;
            } else {
                d = this.gmax / FastMath.min(this.gmin, FastMath.abs(d2));
            }
            if (MACH_PREC * d >= 0.1d) {
                throw new IllConditionedOperatorException(d);
            } else if (this.beta1 <= d3) {
                throw new SingularOperatorException();
            } else {
                this.rnorm = FastMath.min(this.cgnorm, this.lqnorm);
                this.hasConverged = this.cgnorm <= d3 || this.cgnorm <= d4;
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean hasConverged() {
            return this.hasConverged;
        }

        /* access modifiers changed from: 0000 */
        public boolean bEqualsNullVector() {
            return this.bIsNull;
        }

        /* access modifiers changed from: 0000 */
        public boolean betaEqualsZero() {
            return this.beta < MACH_PREC;
        }

        /* access modifiers changed from: 0000 */
        public double getNormOfResidual() {
            return this.rnorm;
        }
    }

    public SymmLQ(int i, double d, boolean z) {
        super(i);
        this.delta = d;
        this.check = z;
    }

    public SymmLQ(IterationManager iterationManager, double d, boolean z) {
        super(iterationManager);
        this.delta = d;
        this.check = z;
    }

    public final boolean getCheck() {
        return this.check;
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(realLinearOperator);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, new ArrayRealVector(realLinearOperator.getColumnDimension()), false, 0.0d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, boolean z, double d) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(realLinearOperator);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, new ArrayRealVector(realLinearOperator.getColumnDimension()), z, d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(realVector2);
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, realVector2.copy(), false, 0.0d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(realLinearOperator);
        ArrayRealVector arrayRealVector = new ArrayRealVector(realLinearOperator.getColumnDimension());
        arrayRealVector.set(0.0d);
        return solveInPlace(realLinearOperator, null, realVector, arrayRealVector, false, 0.0d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector, boolean z, double d) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(realLinearOperator);
        return solveInPlace(realLinearOperator, null, realVector, new ArrayRealVector(realLinearOperator.getColumnDimension()), z, d);
    }

    public RealVector solve(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(realVector2);
        return solveInPlace(realLinearOperator, null, realVector, realVector2.copy(), false, 0.0d);
    }

    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        return solveInPlace(realLinearOperator, realLinearOperator2, realVector, realVector2, false, 0.0d);
    }

    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealLinearOperator realLinearOperator2, RealVector realVector, RealVector realVector2, boolean z, double d) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        RealVector realVector3 = realVector2;
        checkParameters(realLinearOperator, realLinearOperator2, realVector, realVector2);
        IterationManager iterationManager = getIterationManager();
        iterationManager.resetIterationCount();
        iterationManager.incrementIterationCount();
        State state = new State(realLinearOperator, realLinearOperator2, realVector, z, d, this.delta, this.check);
        state.init();
        state.refineSolution(realVector3);
        State state2 = state;
        DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent = new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector3, realVector, state.getNormOfResidual());
        if (state2.bEqualsNullVector()) {
            iterationManager.fireTerminationEvent(defaultIterativeLinearSolverEvent);
            return realVector3;
        }
        boolean z2 = state2.betaEqualsZero() || state2.hasConverged();
        iterationManager.fireInitializationEvent(defaultIterativeLinearSolverEvent);
        if (!z2) {
            do {
                iterationManager.incrementIterationCount();
                RealVector realVector4 = realVector3;
                RealVector realVector5 = realVector;
                DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent2 = new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector4, realVector5, state2.getNormOfResidual());
                iterationManager.fireIterationStartedEvent(defaultIterativeLinearSolverEvent2);
                state2.update();
                state2.refineSolution(realVector3);
                DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent3 = new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector4, realVector5, state2.getNormOfResidual());
                iterationManager.fireIterationPerformedEvent(defaultIterativeLinearSolverEvent3);
            } while (!state2.hasConverged());
        }
        DefaultIterativeLinearSolverEvent defaultIterativeLinearSolverEvent4 = new DefaultIterativeLinearSolverEvent(this, iterationManager.getIterations(), realVector3, realVector, state2.getNormOfResidual());
        iterationManager.fireTerminationEvent(defaultIterativeLinearSolverEvent4);
        return realVector3;
    }

    public RealVector solveInPlace(RealLinearOperator realLinearOperator, RealVector realVector, RealVector realVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        return solveInPlace(realLinearOperator, null, realVector, realVector2, false, 0.0d);
    }
}
