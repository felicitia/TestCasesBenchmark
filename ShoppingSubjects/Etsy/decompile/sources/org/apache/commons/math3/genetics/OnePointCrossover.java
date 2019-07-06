package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class OnePointCrossover<T> implements CrossoverPolicy {
    public ChromosomePair crossover(Chromosome chromosome, Chromosome chromosome2) throws DimensionMismatchException, MathIllegalArgumentException {
        if ((chromosome instanceof AbstractListChromosome) && (chromosome2 instanceof AbstractListChromosome)) {
            return crossover((AbstractListChromosome) chromosome, (AbstractListChromosome) chromosome2);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
    }

    private ChromosomePair crossover(AbstractListChromosome<T> abstractListChromosome, AbstractListChromosome<T> abstractListChromosome2) throws DimensionMismatchException {
        int length = abstractListChromosome.getLength();
        if (length != abstractListChromosome2.getLength()) {
            throw new DimensionMismatchException(abstractListChromosome2.getLength(), length);
        }
        List representation = abstractListChromosome.getRepresentation();
        List representation2 = abstractListChromosome2.getRepresentation();
        ArrayList arrayList = new ArrayList(abstractListChromosome.getLength());
        ArrayList arrayList2 = new ArrayList(abstractListChromosome2.getLength());
        int nextInt = 1 + GeneticAlgorithm.getRandomGenerator().nextInt(length - 2);
        for (int i = 0; i < nextInt; i++) {
            arrayList.add(representation.get(i));
            arrayList2.add(representation2.get(i));
        }
        while (nextInt < length) {
            arrayList.add(representation2.get(nextInt));
            arrayList2.add(representation.get(nextInt));
            nextInt++;
        }
        return new ChromosomePair(abstractListChromosome.newFixedLengthChromosome(arrayList), abstractListChromosome2.newFixedLengthChromosome(arrayList2));
    }
}
