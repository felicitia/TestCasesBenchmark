package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class BinaryMutation implements MutationPolicy {
    public Chromosome mutate(Chromosome chromosome) throws MathIllegalArgumentException {
        int i = 0;
        if (!(chromosome instanceof BinaryChromosome)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_BINARY_CHROMOSOME, new Object[0]);
        }
        BinaryChromosome binaryChromosome = (BinaryChromosome) chromosome;
        ArrayList arrayList = new ArrayList(binaryChromosome.getRepresentation());
        int nextInt = GeneticAlgorithm.getRandomGenerator().nextInt(binaryChromosome.getLength());
        if (((Integer) binaryChromosome.getRepresentation().get(nextInt)).intValue() == 0) {
            i = 1;
        }
        arrayList.set(nextInt, Integer.valueOf(i));
        return binaryChromosome.newFixedLengthChromosome(arrayList);
    }
}
