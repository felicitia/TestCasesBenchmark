package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class RandomKey<T> extends AbstractListChromosome<Double> implements PermutationChromosome<T> {
    private final List<Integer> baseSeqPermutation;
    private final List<Double> sortedRepresentation;

    public RandomKey(List<Double> list) throws InvalidRepresentationException {
        super(list);
        ArrayList arrayList = new ArrayList(getRepresentation());
        Collections.sort(arrayList);
        this.sortedRepresentation = Collections.unmodifiableList(arrayList);
        this.baseSeqPermutation = Collections.unmodifiableList(decodeGeneric(baseSequence(getLength()), getRepresentation(), this.sortedRepresentation));
    }

    public RandomKey(Double[] dArr) throws InvalidRepresentationException {
        this(Arrays.asList(dArr));
    }

    public List<T> decode(List<T> list) {
        return decodeGeneric(list, getRepresentation(), this.sortedRepresentation);
    }

    private static <S> List<S> decodeGeneric(List<S> list, List<Double> list2, List<Double> list3) throws DimensionMismatchException {
        int size = list.size();
        if (list2.size() != size) {
            throw new DimensionMismatchException(list2.size(), size);
        } else if (list3.size() != size) {
            throw new DimensionMismatchException(list3.size(), size);
        } else {
            ArrayList arrayList = new ArrayList(list2);
            ArrayList arrayList2 = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                int indexOf = arrayList.indexOf(list3.get(i));
                arrayList2.add(list.get(indexOf));
                arrayList.set(indexOf, null);
            }
            return arrayList2;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isSame(Chromosome chromosome) {
        if (!(chromosome instanceof RandomKey)) {
            return false;
        }
        RandomKey randomKey = (RandomKey) chromosome;
        if (getLength() != randomKey.getLength()) {
            return false;
        }
        List<Integer> list = this.baseSeqPermutation;
        List<Integer> list2 = randomKey.baseSeqPermutation;
        for (int i = 0; i < getLength(); i++) {
            if (list.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void checkValidity(List<Double> list) throws InvalidRepresentationException {
        for (Double doubleValue : list) {
            double doubleValue2 = doubleValue.doubleValue();
            if (doubleValue2 >= 0.0d) {
                if (doubleValue2 > 1.0d) {
                }
            }
            throw new InvalidRepresentationException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, Double.valueOf(doubleValue2), Integer.valueOf(0), Integer.valueOf(1));
        }
    }

    public static final List<Double> randomPermutation(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Double.valueOf(GeneticAlgorithm.getRandomGenerator().nextDouble()));
        }
        return arrayList;
    }

    public static final List<Double> identityPermutation(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Double.valueOf(((double) i2) / ((double) i)));
        }
        return arrayList;
    }

    public static <S> List<Double> comparatorPermutation(List<S> list, Comparator<S> comparator) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, comparator);
        return inducedPermutation(list, arrayList);
    }

    public static <S> List<Double> inducedPermutation(List<S> list, List<S> list2) throws DimensionMismatchException, MathIllegalArgumentException {
        if (list.size() != list2.size()) {
            throw new DimensionMismatchException(list2.size(), list.size());
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(list);
        Double[] dArr = new Double[size];
        for (int i = 0; i < size; i++) {
            int indexOf = arrayList.indexOf(list2.get(i));
            if (indexOf == -1) {
                throw new MathIllegalArgumentException(LocalizedFormats.DIFFERENT_ORIG_AND_PERMUTED_DATA, new Object[0]);
            }
            dArr[indexOf] = Double.valueOf(((double) i) / ((double) size));
            arrayList.set(indexOf, null);
        }
        return Arrays.asList(dArr);
    }

    public String toString() {
        return String.format("(f=%s pi=(%s))", new Object[]{Double.valueOf(getFitness()), this.baseSeqPermutation});
    }

    private static List<Integer> baseSequence(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }
}
