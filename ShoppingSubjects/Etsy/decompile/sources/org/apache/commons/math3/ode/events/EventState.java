package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.PegasusSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.events.EventHandler.Action;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class EventState {
    private final double convergence;
    private boolean forward;
    private double g0 = Double.NaN;
    private boolean g0Positive = true;
    /* access modifiers changed from: private */
    public final EventHandler handler;
    private boolean increasing = true;
    private final double maxCheckInterval;
    private final int maxIterationCount;
    private Action nextAction = Action.CONTINUE;
    private boolean pendingEvent = false;
    private double pendingEventTime = Double.NaN;
    private double previousEventTime = Double.NaN;
    private final UnivariateSolver solver;
    private double t0 = Double.NaN;

    private static class LocalMaxCountExceededException extends RuntimeException {
        private static final long serialVersionUID = 20120901;
        private final MaxCountExceededException wrapped;

        public LocalMaxCountExceededException(MaxCountExceededException maxCountExceededException) {
            this.wrapped = maxCountExceededException;
        }

        public MaxCountExceededException getException() {
            return this.wrapped;
        }
    }

    public EventState(EventHandler eventHandler, double d, double d2, int i, UnivariateSolver univariateSolver) {
        this.handler = eventHandler;
        this.maxCheckInterval = d;
        this.convergence = FastMath.abs(d2);
        this.maxIterationCount = i;
        this.solver = univariateSolver;
    }

    public EventHandler getEventHandler() {
        return this.handler;
    }

    public double getMaxCheckInterval() {
        return this.maxCheckInterval;
    }

    public double getConvergence() {
        return this.convergence;
    }

    public int getMaxIterationCount() {
        return this.maxIterationCount;
    }

    public void reinitializeBegin(StepInterpolator stepInterpolator) throws MaxCountExceededException {
        this.t0 = stepInterpolator.getPreviousTime();
        stepInterpolator.setInterpolatedTime(this.t0);
        this.g0 = this.handler.g(this.t0, stepInterpolator.getInterpolatedState());
        if (this.g0 == 0.0d) {
            double max = this.t0 + (0.5d * FastMath.max(this.solver.getAbsoluteAccuracy(), FastMath.abs(this.solver.getRelativeAccuracy() * this.t0)));
            stepInterpolator.setInterpolatedTime(max);
            this.g0 = this.handler.g(max, stepInterpolator.getInterpolatedState());
        }
        this.g0Positive = this.g0 >= 0.0d;
    }

    public boolean evaluateStep(StepInterpolator stepInterpolator) throws MaxCountExceededException, NoBracketingException {
        AnonymousClass1 r7;
        AnonymousClass1 r24;
        int i;
        double d;
        final StepInterpolator stepInterpolator2 = stepInterpolator;
        try {
            this.forward = stepInterpolator.isForward();
            double currentTime = stepInterpolator.getCurrentTime() - this.t0;
            boolean z = false;
            if (FastMath.abs(currentTime) < this.convergence) {
                return false;
            }
            boolean z2 = true;
            int max = FastMath.max(1, (int) FastMath.ceil(FastMath.abs(currentTime) / this.maxCheckInterval));
            double d2 = currentTime / ((double) max);
            AnonymousClass1 r15 = new UnivariateFunction() {
                public double value(double d) throws LocalMaxCountExceededException {
                    try {
                        stepInterpolator2.setInterpolatedTime(d);
                        return EventState.this.handler.g(d, stepInterpolator2.getInterpolatedState());
                    } catch (MaxCountExceededException e) {
                        throw new LocalMaxCountExceededException(e);
                    }
                }
            };
            double d3 = this.t0;
            double d4 = this.g0;
            int i2 = 0;
            double d5 = d3;
            while (i2 < max) {
                int i3 = max;
                double d6 = (((double) (i2 + 1)) * d2) + this.t0;
                stepInterpolator2.setInterpolatedTime(d6);
                double g = this.handler.g(d6, stepInterpolator.getInterpolatedState());
                if (this.g0Positive ^ (g >= 0.0d ? z2 : false)) {
                    this.increasing = g >= d4 ? z2 : false;
                    if (this.solver instanceof BracketedUnivariateSolver) {
                        BracketedUnivariateSolver bracketedUnivariateSolver = (BracketedUnivariateSolver) this.solver;
                        if (this.forward) {
                            i = i2;
                            r24 = r15;
                            d = bracketedUnivariateSolver.solve(this.maxIterationCount, r15, d5, d6, AllowedSolution.RIGHT_SIDE);
                        } else {
                            i = i2;
                            r24 = r15;
                            d = bracketedUnivariateSolver.solve(this.maxIterationCount, r24, d6, d5, AllowedSolution.LEFT_SIDE);
                        }
                    } else {
                        i = i2;
                        r24 = r15;
                        double solve = this.forward ? this.solver.solve(this.maxIterationCount, r24, d5, d6) : this.solver.solve(this.maxIterationCount, r24, d6, d5);
                        int evaluations = this.maxIterationCount - this.solver.getEvaluations();
                        PegasusSolver pegasusSolver = new PegasusSolver(this.solver.getRelativeAccuracy(), this.solver.getAbsoluteAccuracy());
                        d = this.forward ? UnivariateSolverUtils.forceSide(evaluations, r24, pegasusSolver, solve, d5, d6, AllowedSolution.RIGHT_SIDE) : UnivariateSolverUtils.forceSide(evaluations, r24, pegasusSolver, solve, d6, d5, AllowedSolution.LEFT_SIDE);
                    }
                    if (Double.isNaN(this.previousEventTime) || FastMath.abs(d - d5) > this.convergence || FastMath.abs(d - this.previousEventTime) > this.convergence) {
                        r7 = r24;
                        if (!Double.isNaN(this.previousEventTime)) {
                            if (FastMath.abs(this.previousEventTime - d) <= this.convergence) {
                                i2 = i;
                            }
                        }
                        this.pendingEventTime = d;
                        this.pendingEvent = true;
                        return true;
                    }
                    d6 = this.forward ? d5 + this.convergence : d5 - this.convergence;
                    r7 = r24;
                    g = r7.value(d6);
                    i2 = i - 1;
                } else {
                    int i4 = i2;
                    r7 = r15;
                }
                d5 = d6;
                d4 = g;
                i2++;
                r15 = r7;
                max = i3;
                z2 = true;
                z = false;
            }
            boolean z3 = z;
            this.pendingEvent = z3;
            this.pendingEventTime = Double.NaN;
            return z3;
        } catch (LocalMaxCountExceededException e) {
            throw e.getException();
        }
    }

    public double getEventTime() {
        if (this.pendingEvent) {
            return this.pendingEventTime;
        }
        return this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
    }

    public void stepAccepted(double d, double[] dArr) {
        this.t0 = d;
        this.g0 = this.handler.g(d, dArr);
        boolean z = false;
        if (!this.pendingEvent || FastMath.abs(this.pendingEventTime - d) > this.convergence) {
            if (this.g0 >= 0.0d) {
                z = true;
            }
            this.g0Positive = z;
            this.nextAction = Action.CONTINUE;
            return;
        }
        this.previousEventTime = d;
        this.g0Positive = this.increasing;
        EventHandler eventHandler = this.handler;
        if (!(this.increasing ^ this.forward)) {
            z = true;
        }
        this.nextAction = eventHandler.eventOccurred(d, dArr, z);
    }

    public boolean stop() {
        return this.nextAction == Action.STOP;
    }

    public boolean reset(double d, double[] dArr) {
        boolean z = false;
        if (!this.pendingEvent || FastMath.abs(this.pendingEventTime - d) > this.convergence) {
            return false;
        }
        if (this.nextAction == Action.RESET_STATE) {
            this.handler.resetState(d, dArr);
        }
        this.pendingEvent = false;
        this.pendingEventTime = Double.NaN;
        if (this.nextAction == Action.RESET_STATE || this.nextAction == Action.RESET_DERIVATIVES) {
            z = true;
        }
        return z;
    }
}
