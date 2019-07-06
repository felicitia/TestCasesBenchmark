package org.apache.commons.math3.genetics;

import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class ElitisticListPopulation extends ListPopulation {
    private double elitismRate = 0.9d;

    public ElitisticListPopulation(List<Chromosome> list, int i, double d) throws NullArgumentException, NotPositiveException, NumberIsTooLargeException, OutOfRangeException {
        super(list, i);
        setElitismRate(d);
    }

    public ElitisticListPopulation(int i, double d) throws NotPositiveException, OutOfRangeException {
        super(i);
        setElitismRate(d);
    }

    public Population nextGeneration() {
        ElitisticListPopulation elitisticListPopulation = new ElitisticListPopulation(getPopulationLimit(), getElitismRate());
        List chromosomeList = getChromosomeList();
        Collections.sort(chromosomeList);
        for (int ceil = (int) FastMath.ceil((1.0d - getElitismRate()) * ((double) chromosomeList.size())); ceil < chromosomeList.size(); ceil++) {
            elitisticListPopulation.addChromosome((Chromosome) chromosomeList.get(ceil));
        }
        return elitisticListPopulation;
    }

    public void setElitismRate(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.ELITISM_RATE, Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(1));
        }
        this.elitismRate = d;
    }

    public double getElitismRate() {
        return this.elitismRate;
    }
}
