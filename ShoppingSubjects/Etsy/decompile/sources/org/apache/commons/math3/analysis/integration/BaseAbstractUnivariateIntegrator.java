package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.MathUtils;

public abstract class BaseAbstractUnivariateIntegrator implements UnivariateIntegrator {
    public static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-15d;
    public static final int DEFAULT_MAX_ITERATIONS_COUNT = Integer.MAX_VALUE;
    public static final int DEFAULT_MIN_ITERATIONS_COUNT = 3;
    public static final double DEFAULT_RELATIVE_ACCURACY = 1.0E-6d;
    private final double absoluteAccuracy;
    private final Incrementor evaluations;
    private UnivariateFunction function;
    protected final Incrementor iterations;
    private double max;
    private double min;
    private final int minimalIterationCount;
    private final double relativeAccuracy;

    /* access modifiers changed from: protected */
    public abstract double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException;

    protected BaseAbstractUnivariateIntegrator(double d, double d2, int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException {
        this.relativeAccuracy = d;
        this.absoluteAccuracy = d2;
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        } else if (i2 <= i) {
            throw new NumberIsTooSmallException(Integer.valueOf(i2), Integer.valueOf(i), false);
        } else {
            this.minimalIterationCount = i;
            this.iterations = new Incrementor();
            this.iterations.setMaximalCount(i2);
            this.evaluations = new Incrementor();
        }
    }

    protected BaseAbstractUnivariateIntegrator(double d, double d2) {
        this(d, d2, 3, Integer.MAX_VALUE);
    }

    protected BaseAbstractUnivariateIntegrator(int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException {
        this(1.0E-6d, 1.0E-15d, i, i2);
    }

    public double getRelativeAccuracy() {
        return this.relativeAccuracy;
    }

    public double getAbsoluteAccuracy() {
        return this.absoluteAccuracy;
    }

    public int getMinimalIterationCount() {
        return this.minimalIterationCount;
    }

    public int getMaximalIterationCount() {
        return this.iterations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public int getIterations() {
        return this.iterations.getCount();
    }

    /* access modifiers changed from: protected */
    public double getMin() {
        return this.min;
    }

    /* access modifiers changed from: protected */
    public double getMax() {
        return this.max;
    }

    /* access modifiers changed from: protected */
    public double computeObjectiveValue(double d) throws TooManyEvaluationsException {
        try {
            this.evaluations.incrementCount();
            return this.function.value(d);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }

    /* access modifiers changed from: protected */
    public void setup(int i, UnivariateFunction univariateFunction, double d, double d2) throws NullArgumentException, MathIllegalArgumentException {
        MathUtils.checkNotNull(univariateFunction);
        UnivariateSolverUtils.verifyInterval(d, d2);
        this.min = d;
        this.max = d2;
        this.function = univariateFunction;
        this.evaluations.setMaximalCount(i);
        this.evaluations.resetCount();
        this.iterations.resetCount();
    }

    public double integrate(int i, UnivariateFunction univariateFunction, double d, double d2) throws TooManyEvaluationsException, MaxCountExceededException, MathIllegalArgumentException, NullArgumentException {
        setup(i, univariateFunction, d, d2);
        return doIntegrate();
    }
}
