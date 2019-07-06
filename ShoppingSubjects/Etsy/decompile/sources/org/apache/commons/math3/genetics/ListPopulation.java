package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class ListPopulation implements Population {
    private List<Chromosome> chromosomes;
    private int populationLimit;

    public ListPopulation(int i) throws NotPositiveException {
        this(Collections.emptyList(), i);
    }

    public ListPopulation(List<Chromosome> list, int i) throws NullArgumentException, NotPositiveException, NumberIsTooLargeException {
        if (list == null) {
            throw new NullArgumentException();
        } else if (i <= 0) {
            throw new NotPositiveException(LocalizedFormats.POPULATION_LIMIT_NOT_POSITIVE, Integer.valueOf(i));
        } else if (list.size() > i) {
            throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, Integer.valueOf(list.size()), Integer.valueOf(i), false);
        } else {
            this.populationLimit = i;
            this.chromosomes = new ArrayList(i);
            this.chromosomes.addAll(list);
        }
    }

    @Deprecated
    public void setChromosomes(List<Chromosome> list) throws NullArgumentException, NumberIsTooLargeException {
        if (list == null) {
            throw new NullArgumentException();
        } else if (list.size() > this.populationLimit) {
            throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, Integer.valueOf(list.size()), Integer.valueOf(this.populationLimit), false);
        } else {
            this.chromosomes.clear();
            this.chromosomes.addAll(list);
        }
    }

    public void addChromosomes(Collection<Chromosome> collection) throws NumberIsTooLargeException {
        if (this.chromosomes.size() + collection.size() > this.populationLimit) {
            throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, Integer.valueOf(this.chromosomes.size()), Integer.valueOf(this.populationLimit), false);
        }
        this.chromosomes.addAll(collection);
    }

    public List<Chromosome> getChromosomes() {
        return Collections.unmodifiableList(this.chromosomes);
    }

    /* access modifiers changed from: protected */
    public List<Chromosome> getChromosomeList() {
        return this.chromosomes;
    }

    public void addChromosome(Chromosome chromosome) throws NumberIsTooLargeException {
        if (this.chromosomes.size() >= this.populationLimit) {
            throw new NumberIsTooLargeException(LocalizedFormats.LIST_OF_CHROMOSOMES_BIGGER_THAN_POPULATION_SIZE, Integer.valueOf(this.chromosomes.size()), Integer.valueOf(this.populationLimit), false);
        }
        this.chromosomes.add(chromosome);
    }

    public Chromosome getFittestChromosome() {
        Chromosome chromosome = (Chromosome) this.chromosomes.get(0);
        for (Chromosome chromosome2 : this.chromosomes) {
            if (chromosome2.compareTo(chromosome) > 0) {
                chromosome = chromosome2;
            }
        }
        return chromosome;
    }

    public int getPopulationLimit() {
        return this.populationLimit;
    }

    public void setPopulationLimit(int i) throws NotPositiveException, NumberIsTooSmallException {
        if (i <= 0) {
            throw new NotPositiveException(LocalizedFormats.POPULATION_LIMIT_NOT_POSITIVE, Integer.valueOf(i));
        } else if (i < this.chromosomes.size()) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), Integer.valueOf(this.chromosomes.size()), true);
        } else {
            this.populationLimit = i;
        }
    }

    public int getPopulationSize() {
        return this.chromosomes.size();
    }

    public String toString() {
        return this.chromosomes.toString();
    }

    public Iterator<Chromosome> iterator() {
        return getChromosomes().iterator();
    }
}
