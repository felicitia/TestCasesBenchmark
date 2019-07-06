package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class RandomKeyMutation implements MutationPolicy {
    public Chromosome mutate(Chromosome chromosome) throws MathIllegalArgumentException {
        if (!(chromosome instanceof RandomKey)) {
            throw new MathIllegalArgumentException(LocalizedFormats.RANDOMKEY_MUTATION_WRONG_CLASS, chromosome.getClass().getSimpleName());
        }
        RandomKey randomKey = (RandomKey) chromosome;
        List representation = randomKey.getRepresentation();
        int nextInt = GeneticAlgorithm.getRandomGenerator().nextInt(representation.size());
        ArrayList arrayList = new ArrayList(representation);
        arrayList.set(nextInt, Double.valueOf(GeneticAlgorithm.getRandomGenerator().nextDouble()));
        return randomKey.newFixedLengthChromosome(arrayList);
    }
}
