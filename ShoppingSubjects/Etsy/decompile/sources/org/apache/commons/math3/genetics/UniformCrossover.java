package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;

public class UniformCrossover<T> implements CrossoverPolicy {
    private final double ratio;

    public UniformCrossover(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.CROSSOVER_RATE, Double.valueOf(d), Double.valueOf(0.0d), Double.valueOf(1.0d));
        }
        this.ratio = d;
    }

    public double getRatio() {
        return this.ratio;
    }

    public ChromosomePair crossover(Chromosome chromosome, Chromosome chromosome2) throws DimensionMismatchException, MathIllegalArgumentException {
        if ((chromosome instanceof AbstractListChromosome) && (chromosome2 instanceof AbstractListChromosome)) {
            return mate((AbstractListChromosome) chromosome, (AbstractListChromosome) chromosome2);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
    }

    private ChromosomePair mate(AbstractListChromosome<T> abstractListChromosome, AbstractListChromosome<T> abstractListChromosome2) throws DimensionMismatchException {
        int length = abstractListChromosome.getLength();
        if (length != abstractListChromosome2.getLength()) {
            throw new DimensionMismatchException(abstractListChromosome2.getLength(), length);
        }
        List representation = abstractListChromosome.getRepresentation();
        List representation2 = abstractListChromosome2.getRepresentation();
        ArrayList arrayList = new ArrayList(abstractListChromosome.getLength());
        ArrayList arrayList2 = new ArrayList(abstractListChromosome2.getLength());
        RandomGenerator randomGenerator = GeneticAlgorithm.getRandomGenerator();
        for (int i = 0; i < length; i++) {
            if (randomGenerator.nextDouble() < this.ratio) {
                arrayList.add(representation2.get(i));
                arrayList2.add(representation.get(i));
            } else {
                arrayList.add(representation.get(i));
                arrayList2.add(representation2.get(i));
            }
        }
        return new ChromosomePair(abstractListChromosome.newFixedLengthChromosome(arrayList), abstractListChromosome2.newFixedLengthChromosome(arrayList2));
    }
}
