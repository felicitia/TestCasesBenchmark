package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractListChromosome<T> extends Chromosome {
    private final List<T> representation;

    /* access modifiers changed from: protected */
    public abstract void checkValidity(List<T> list) throws InvalidRepresentationException;

    public abstract AbstractListChromosome<T> newFixedLengthChromosome(List<T> list);

    public AbstractListChromosome(List<T> list) throws InvalidRepresentationException {
        checkValidity(list);
        this.representation = Collections.unmodifiableList(new ArrayList(list));
    }

    public AbstractListChromosome(T[] tArr) throws InvalidRepresentationException {
        this(Arrays.asList(tArr));
    }

    /* access modifiers changed from: protected */
    public List<T> getRepresentation() {
        return this.representation;
    }

    public int getLength() {
        return getRepresentation().size();
    }

    public String toString() {
        return String.format("(f=%s %s)", new Object[]{Double.valueOf(getFitness()), getRepresentation()});
    }
}
