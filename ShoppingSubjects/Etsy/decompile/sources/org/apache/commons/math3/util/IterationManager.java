package org.apache.commons.math3.util;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback;

public class IterationManager {
    private final Incrementor iterations;
    private final Collection<IterationListener> listeners = new CopyOnWriteArrayList();

    public IterationManager(int i) {
        this.iterations = new Incrementor(i);
    }

    public IterationManager(int i, MaxCountExceededCallback maxCountExceededCallback) {
        this.iterations = new Incrementor(i, maxCountExceededCallback);
    }

    public void addIterationListener(IterationListener iterationListener) {
        this.listeners.add(iterationListener);
    }

    public void fireInitializationEvent(IterationEvent iterationEvent) {
        for (IterationListener initializationPerformed : this.listeners) {
            initializationPerformed.initializationPerformed(iterationEvent);
        }
    }

    public void fireIterationPerformedEvent(IterationEvent iterationEvent) {
        for (IterationListener iterationPerformed : this.listeners) {
            iterationPerformed.iterationPerformed(iterationEvent);
        }
    }

    public void fireIterationStartedEvent(IterationEvent iterationEvent) {
        for (IterationListener iterationStarted : this.listeners) {
            iterationStarted.iterationStarted(iterationEvent);
        }
    }

    public void fireTerminationEvent(IterationEvent iterationEvent) {
        for (IterationListener terminationPerformed : this.listeners) {
            terminationPerformed.terminationPerformed(iterationEvent);
        }
    }

    public int getIterations() {
        return this.iterations.getCount();
    }

    public int getMaxIterations() {
        return this.iterations.getMaximalCount();
    }

    public void incrementIterationCount() throws MaxCountExceededException {
        this.iterations.incrementCount();
    }

    public void removeIterationListener(IterationListener iterationListener) {
        this.listeners.remove(iterationListener);
    }

    public void resetIterationCount() {
        this.iterations.resetCount();
    }
}
