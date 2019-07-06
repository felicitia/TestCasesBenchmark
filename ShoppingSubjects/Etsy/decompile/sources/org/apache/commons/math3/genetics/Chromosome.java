package org.apache.commons.math3.genetics;

public abstract class Chromosome implements Comparable<Chromosome>, Fitness {
    private static final double NO_FITNESS = Double.NEGATIVE_INFINITY;
    private double fitness = NO_FITNESS;

    /* access modifiers changed from: protected */
    public boolean isSame(Chromosome chromosome) {
        return false;
    }

    public double getFitness() {
        if (this.fitness == NO_FITNESS) {
            this.fitness = fitness();
        }
        return this.fitness;
    }

    public int compareTo(Chromosome chromosome) {
        return Double.valueOf(getFitness()).compareTo(Double.valueOf(chromosome.getFitness()));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Incorrect type for immutable var: ssa=org.apache.commons.math3.genetics.Population, code=org.apache.commons.math3.genetics.Population<org.apache.commons.math3.genetics.Chromosome>, for r3v0, types: [org.apache.commons.math3.genetics.Population<org.apache.commons.math3.genetics.Chromosome>, org.apache.commons.math3.genetics.Population] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.genetics.Chromosome findSameChromosome(org.apache.commons.math3.genetics.Population<org.apache.commons.math3.genetics.Chromosome> r3) {
        /*
            r2 = this;
            java.util.Iterator r3 = r3.iterator()
        L_0x0004:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0017
            java.lang.Object r0 = r3.next()
            org.apache.commons.math3.genetics.Chromosome r0 = (org.apache.commons.math3.genetics.Chromosome) r0
            boolean r1 = r2.isSame(r0)
            if (r1 == 0) goto L_0x0004
            return r0
        L_0x0017:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.genetics.Chromosome.findSameChromosome(org.apache.commons.math3.genetics.Population):org.apache.commons.math3.genetics.Chromosome");
    }

    public void searchForFitnessUpdate(Population population) {
        Chromosome findSameChromosome = findSameChromosome(population);
        if (findSameChromosome != null) {
            this.fitness = findSameChromosome.getFitness();
        }
    }
}
