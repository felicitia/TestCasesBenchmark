package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.events.EventState;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.Precision;

public abstract class AbstractIntegrator implements FirstOrderIntegrator {
    private Incrementor evaluations;
    private Collection<EventState> eventsStates;
    private transient ExpandableStatefulODE expandable;
    protected boolean isLastStep;
    private final String name;
    protected boolean resetOccurred;
    private boolean statesInitialized;
    protected Collection<StepHandler> stepHandlers;
    protected double stepSize;
    protected double stepStart;

    public abstract void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

    public AbstractIntegrator(String str) {
        this.name = str;
        this.stepHandlers = new ArrayList();
        this.stepStart = Double.NaN;
        this.stepSize = Double.NaN;
        this.eventsStates = new ArrayList();
        this.statesInitialized = false;
        this.evaluations = new Incrementor();
        setMaxEvaluations(-1);
        this.evaluations.resetCount();
    }

    protected AbstractIntegrator() {
        this(null);
    }

    public String getName() {
        return this.name;
    }

    public void addStepHandler(StepHandler stepHandler) {
        this.stepHandlers.add(stepHandler);
    }

    public Collection<StepHandler> getStepHandlers() {
        return Collections.unmodifiableCollection(this.stepHandlers);
    }

    public void clearStepHandlers() {
        this.stepHandlers.clear();
    }

    public void addEventHandler(EventHandler eventHandler, double d, double d2, int i) {
        addEventHandler(eventHandler, d, d2, i, new BracketingNthOrderBrentSolver(d2, 5));
    }

    public void addEventHandler(EventHandler eventHandler, double d, double d2, int i, UnivariateSolver univariateSolver) {
        Collection<EventState> collection = this.eventsStates;
        EventState eventState = new EventState(eventHandler, d, d2, i, univariateSolver);
        collection.add(eventState);
    }

    public Collection<EventHandler> getEventHandlers() {
        ArrayList arrayList = new ArrayList();
        for (EventState eventHandler : this.eventsStates) {
            arrayList.add(eventHandler.getEventHandler());
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    public void clearEventHandlers() {
        this.eventsStates.clear();
    }

    public double getCurrentStepStart() {
        return this.stepStart;
    }

    public double getCurrentSignedStepsize() {
        return this.stepSize;
    }

    public void setMaxEvaluations(int i) {
        Incrementor incrementor = this.evaluations;
        if (i < 0) {
            i = Integer.MAX_VALUE;
        }
        incrementor.setMaximalCount(i);
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    /* access modifiers changed from: protected */
    public void initIntegration(double d, double[] dArr, double d2) {
        this.evaluations.resetCount();
        for (EventState eventHandler : this.eventsStates) {
            eventHandler.getEventHandler().init(d, dArr, d2);
        }
        for (StepHandler init : this.stepHandlers) {
            init.init(d, dArr, d2);
        }
        setStateInitialized(false);
    }

    /* access modifiers changed from: protected */
    public void setEquations(ExpandableStatefulODE expandableStatefulODE) {
        this.expandable = expandableStatefulODE;
    }

    public double integrate(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double d, double[] dArr, double d2, double[] dArr2) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException {
        if (dArr.length != firstOrderDifferentialEquations.getDimension()) {
            throw new DimensionMismatchException(dArr.length, firstOrderDifferentialEquations.getDimension());
        } else if (dArr2.length != firstOrderDifferentialEquations.getDimension()) {
            throw new DimensionMismatchException(dArr2.length, firstOrderDifferentialEquations.getDimension());
        } else {
            ExpandableStatefulODE expandableStatefulODE = new ExpandableStatefulODE(firstOrderDifferentialEquations);
            expandableStatefulODE.setTime(d);
            expandableStatefulODE.setPrimaryState(dArr);
            integrate(expandableStatefulODE, d2);
            System.arraycopy(expandableStatefulODE.getPrimaryState(), 0, dArr2, 0, dArr2.length);
            return expandableStatefulODE.getTime();
        }
    }

    public void computeDerivatives(double d, double[] dArr, double[] dArr2) throws MaxCountExceededException, DimensionMismatchException {
        this.evaluations.incrementCount();
        this.expandable.computeDerivatives(d, dArr, dArr2);
    }

    /* access modifiers changed from: protected */
    public void setStateInitialized(boolean z) {
        this.statesInitialized = z;
    }

    /* access modifiers changed from: protected */
    public double acceptStep(AbstractStepInterpolator abstractStepInterpolator, double[] dArr, double[] dArr2, double d) throws MaxCountExceededException, DimensionMismatchException, NoBracketingException {
        AbstractStepInterpolator abstractStepInterpolator2 = abstractStepInterpolator;
        double[] dArr3 = dArr;
        double globalPreviousTime = abstractStepInterpolator.getGlobalPreviousTime();
        double globalCurrentTime = abstractStepInterpolator.getGlobalCurrentTime();
        boolean z = true;
        if (!this.statesInitialized) {
            for (EventState reinitializeBegin : this.eventsStates) {
                reinitializeBegin.reinitializeBegin(abstractStepInterpolator2);
            }
            this.statesInitialized = true;
        }
        final int i = abstractStepInterpolator.isForward() ? 1 : -1;
        TreeSet<EventState> treeSet = new TreeSet<>(new Comparator<EventState>() {
            public int compare(EventState eventState, EventState eventState2) {
                return i * Double.compare(eventState.getEventTime(), eventState2.getEventTime());
            }
        });
        for (EventState eventState : this.eventsStates) {
            if (eventState.evaluateStep(abstractStepInterpolator2)) {
                treeSet.add(eventState);
            }
        }
        while (!treeSet.isEmpty()) {
            Iterator it = treeSet.iterator();
            EventState eventState2 = (EventState) it.next();
            it.remove();
            double eventTime = eventState2.getEventTime();
            abstractStepInterpolator2.setSoftPreviousTime(globalPreviousTime);
            abstractStepInterpolator2.setSoftCurrentTime(eventTime);
            abstractStepInterpolator2.setInterpolatedTime(eventTime);
            double[] dArr4 = (double[]) abstractStepInterpolator.getInterpolatedState().clone();
            eventState2.stepAccepted(eventTime, dArr4);
            this.isLastStep = eventState2.stop();
            for (StepHandler handleStep : this.stepHandlers) {
                handleStep.handleStep(abstractStepInterpolator2, this.isLastStep);
            }
            if (this.isLastStep) {
                System.arraycopy(dArr4, 0, dArr3, 0, dArr3.length);
                for (EventState stepAccepted : treeSet) {
                    stepAccepted.stepAccepted(eventTime, dArr4);
                }
                return eventTime;
            } else if (eventState2.reset(eventTime, dArr4)) {
                System.arraycopy(dArr4, 0, dArr3, 0, dArr3.length);
                computeDerivatives(eventTime, dArr3, dArr2);
                this.resetOccurred = true;
                for (EventState stepAccepted2 : treeSet) {
                    stepAccepted2.stepAccepted(eventTime, dArr4);
                }
                return eventTime;
            } else {
                double[] dArr5 = dArr2;
                abstractStepInterpolator2.setSoftPreviousTime(eventTime);
                abstractStepInterpolator2.setSoftCurrentTime(globalCurrentTime);
                if (eventState2.evaluateStep(abstractStepInterpolator2)) {
                    treeSet.add(eventState2);
                }
                globalPreviousTime = eventTime;
            }
        }
        abstractStepInterpolator2.setInterpolatedTime(globalCurrentTime);
        double[] interpolatedState = abstractStepInterpolator.getInterpolatedState();
        for (EventState eventState3 : this.eventsStates) {
            eventState3.stepAccepted(globalCurrentTime, interpolatedState);
            this.isLastStep = this.isLastStep || eventState3.stop();
        }
        if (!this.isLastStep && !Precision.equals(globalCurrentTime, d, 1)) {
            z = false;
        }
        this.isLastStep = z;
        for (StepHandler handleStep2 : this.stepHandlers) {
            handleStep2.handleStep(abstractStepInterpolator2, this.isLastStep);
        }
        return globalCurrentTime;
    }

    /* access modifiers changed from: protected */
    public void sanityChecks(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException {
        double ulp = 1000.0d * FastMath.ulp(FastMath.max(FastMath.abs(expandableStatefulODE.getTime()), FastMath.abs(d)));
        double abs = FastMath.abs(expandableStatefulODE.getTime() - d);
        if (abs <= ulp) {
            throw new NumberIsTooSmallException(LocalizedFormats.TOO_SMALL_INTEGRATION_INTERVAL, Double.valueOf(abs), Double.valueOf(ulp), false);
        }
    }
}
