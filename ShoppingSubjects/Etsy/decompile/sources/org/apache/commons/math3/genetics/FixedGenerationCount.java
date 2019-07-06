package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

public class FixedGenerationCount implements StoppingCondition {
    private final int maxGenerations;
    private int numGenerations = 0;

    public FixedGenerationCount(int i) throws NumberIsTooSmallException {
        if (i <= 0) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), Integer.valueOf(1), true);
        }
        this.maxGenerations = i;
    }

    public boolean isSatisfied(Population population) {
        if (this.numGenerations >= this.maxGenerations) {
            return true;
        }
        this.numGenerations++;
        return false;
    }

    public int getNumGenerations() {
        return this.numGenerations;
    }
}
