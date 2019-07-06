package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class CycleCrossover<T> implements CrossoverPolicy {
    private final boolean randomStart;

    public CycleCrossover() {
        this(false);
    }

    public CycleCrossover(boolean z) {
        this.randomStart = z;
    }

    public boolean isRandomStart() {
        return this.randomStart;
    }

    public ChromosomePair crossover(Chromosome chromosome, Chromosome chromosome2) throws DimensionMismatchException, MathIllegalArgumentException {
        if ((chromosome instanceof AbstractListChromosome) && (chromosome2 instanceof AbstractListChromosome)) {
            return mate((AbstractListChromosome) chromosome, (AbstractListChromosome) chromosome2);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
    }

    /* access modifiers changed from: protected */
    public ChromosomePair mate(AbstractListChromosome<T> abstractListChromosome, AbstractListChromosome<T> abstractListChromosome2) throws DimensionMismatchException {
        int length = abstractListChromosome.getLength();
        if (length != abstractListChromosome2.getLength()) {
            throw new DimensionMismatchException(abstractListChromosome2.getLength(), length);
        }
        List representation = abstractListChromosome.getRepresentation();
        List representation2 = abstractListChromosome2.getRepresentation();
        ArrayList arrayList = new ArrayList(abstractListChromosome2.getRepresentation());
        ArrayList arrayList2 = new ArrayList(abstractListChromosome.getRepresentation());
        HashSet hashSet = new HashSet(length);
        ArrayList<Integer> arrayList3 = new ArrayList<>(length);
        int nextInt = this.randomStart ? GeneticAlgorithm.getRandomGenerator().nextInt(length) : 0;
        int i = 1;
        while (hashSet.size() < length) {
            arrayList3.add(Integer.valueOf(nextInt));
            for (int indexOf = representation.indexOf(representation2.get(nextInt)); indexOf != ((Integer) arrayList3.get(0)).intValue(); indexOf = representation.indexOf(representation2.get(indexOf))) {
                arrayList3.add(Integer.valueOf(indexOf));
            }
            int i2 = i + 1;
            if (i % 2 != 0) {
                for (Integer intValue : arrayList3) {
                    int intValue2 = intValue.intValue();
                    Object obj = arrayList.get(intValue2);
                    arrayList.set(intValue2, arrayList2.get(intValue2));
                    arrayList2.set(intValue2, obj);
                }
            }
            hashSet.addAll(arrayList3);
            int intValue3 = (((Integer) arrayList3.get(0)).intValue() + 1) % length;
            while (hashSet.contains(Integer.valueOf(intValue3)) && hashSet.size() < length) {
                intValue3++;
                if (intValue3 >= length) {
                    intValue3 = 0;
                }
            }
            arrayList3.clear();
            int i3 = intValue3;
            i = i2;
            nextInt = i3;
        }
        return new ChromosomePair(abstractListChromosome.newFixedLengthChromosome(arrayList), abstractListChromosome2.newFixedLengthChromosome(arrayList2));
    }
}
