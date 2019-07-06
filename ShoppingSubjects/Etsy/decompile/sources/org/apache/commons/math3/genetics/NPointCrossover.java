package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;

public class NPointCrossover<T> implements CrossoverPolicy {
    private final int crossoverPoints;

    public NPointCrossover(int i) throws NotStrictlyPositiveException {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.crossoverPoints = i;
    }

    public int getCrossoverPoints() {
        return this.crossoverPoints;
    }

    public ChromosomePair crossover(Chromosome chromosome, Chromosome chromosome2) throws DimensionMismatchException, MathIllegalArgumentException {
        if ((chromosome instanceof AbstractListChromosome) && (chromosome2 instanceof AbstractListChromosome)) {
            return mate((AbstractListChromosome) chromosome, (AbstractListChromosome) chromosome2);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
    }

    private ChromosomePair mate(AbstractListChromosome<T> abstractListChromosome, AbstractListChromosome<T> abstractListChromosome2) throws DimensionMismatchException, NumberIsTooLargeException {
        int length = abstractListChromosome.getLength();
        if (length != abstractListChromosome2.getLength()) {
            throw new DimensionMismatchException(abstractListChromosome2.getLength(), length);
        }
        int i = 0;
        if (this.crossoverPoints >= length) {
            throw new NumberIsTooLargeException(Integer.valueOf(this.crossoverPoints), Integer.valueOf(length), false);
        }
        List representation = abstractListChromosome.getRepresentation();
        List representation2 = abstractListChromosome2.getRepresentation();
        ArrayList arrayList = new ArrayList(abstractListChromosome.getLength());
        ArrayList arrayList2 = new ArrayList(abstractListChromosome2.getLength());
        RandomGenerator randomGenerator = GeneticAlgorithm.getRandomGenerator();
        ArrayList arrayList3 = arrayList;
        ArrayList arrayList4 = arrayList2;
        int i2 = this.crossoverPoints;
        int i3 = 0;
        while (i < this.crossoverPoints) {
            int nextInt = 1 + i3 + randomGenerator.nextInt((length - i3) - i2);
            while (i3 < nextInt) {
                arrayList3.add(representation.get(i3));
                arrayList4.add(representation2.get(i3));
                i3++;
            }
            i++;
            i2--;
            i3 = nextInt;
            ArrayList arrayList5 = arrayList4;
            arrayList4 = arrayList3;
            arrayList3 = arrayList5;
        }
        while (i3 < length) {
            arrayList3.add(representation.get(i3));
            arrayList4.add(representation2.get(i3));
            i3++;
        }
        return new ChromosomePair(abstractListChromosome.newFixedLengthChromosome(arrayList), abstractListChromosome2.newFixedLengthChromosome(arrayList2));
    }
}
