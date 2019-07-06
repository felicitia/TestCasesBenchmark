package org.apache.commons.math3.stat;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class Frequency implements Serializable {
    private static final long serialVersionUID = -3845586908418844111L;
    private final TreeMap<Comparable<?>, Long> freqTable;

    private static class NaturalComparator<T extends Comparable<T>> implements Serializable, Comparator<Comparable<T>> {
        private static final long serialVersionUID = -3852193713161395148L;

        private NaturalComparator() {
        }

        public int compare(Comparable<T> comparable, Comparable<T> comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    public Frequency() {
        this.freqTable = new TreeMap<>();
    }

    public Frequency(Comparator<?> comparator) {
        this.freqTable = new TreeMap<>(comparator);
    }

    public String toString() {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("Value \t Freq. \t Pct. \t Cum Pct. \n");
        for (Comparable comparable : this.freqTable.keySet()) {
            sb.append(comparable);
            sb.append(9);
            sb.append(getCount(comparable));
            sb.append(9);
            sb.append(percentInstance.format(getPct(comparable)));
            sb.append(9);
            sb.append(percentInstance.format(getCumPct(comparable)));
            sb.append(10);
        }
        return sb.toString();
    }

    public void addValue(Comparable<?> comparable) throws MathIllegalArgumentException {
        incrementValue(comparable, 1);
    }

    public void incrementValue(Comparable<?> comparable, long j) {
        Comparable<?> valueOf = comparable instanceof Integer ? Long.valueOf(((Integer) comparable).longValue()) : comparable;
        try {
            Long l = (Long) this.freqTable.get(valueOf);
            if (l == null) {
                this.freqTable.put(valueOf, Long.valueOf(j));
            } else {
                this.freqTable.put(valueOf, Long.valueOf(l.longValue() + j));
            }
        } catch (ClassCastException unused) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSTANCES_NOT_COMPARABLE_TO_EXISTING_VALUES, comparable.getClass().getName());
        }
    }

    public void addValue(int i) throws MathIllegalArgumentException {
        addValue((Comparable<?>) Long.valueOf((long) i));
    }

    public void addValue(long j) throws MathIllegalArgumentException {
        addValue((Comparable<?>) Long.valueOf(j));
    }

    public void addValue(char c) throws MathIllegalArgumentException {
        addValue((Comparable<?>) Character.valueOf(c));
    }

    public void clear() {
        this.freqTable.clear();
    }

    public Iterator<Comparable<?>> valuesIterator() {
        return this.freqTable.keySet().iterator();
    }

    public Iterator<Entry<Comparable<?>, Long>> entrySetIterator() {
        return this.freqTable.entrySet().iterator();
    }

    public long getSumFreq() {
        long j = 0;
        for (Long longValue : this.freqTable.values()) {
            j += longValue.longValue();
        }
        return j;
    }

    public long getCount(Comparable<?> comparable) {
        if (comparable instanceof Integer) {
            return getCount(((Integer) comparable).longValue());
        }
        long j = 0;
        try {
            Long l = (Long) this.freqTable.get(comparable);
            if (l != null) {
                j = l.longValue();
            }
        } catch (ClassCastException unused) {
        }
        return j;
    }

    public long getCount(int i) {
        return getCount((Comparable<?>) Long.valueOf((long) i));
    }

    public long getCount(long j) {
        return getCount((Comparable<?>) Long.valueOf(j));
    }

    public long getCount(char c) {
        return getCount((Comparable<?>) Character.valueOf(c));
    }

    public int getUniqueCount() {
        return this.freqTable.keySet().size();
    }

    public double getPct(Comparable<?> comparable) {
        long sumFreq = getSumFreq();
        if (sumFreq == 0) {
            return Double.NaN;
        }
        return ((double) getCount(comparable)) / ((double) sumFreq);
    }

    public double getPct(int i) {
        return getPct((Comparable<?>) Long.valueOf((long) i));
    }

    public double getPct(long j) {
        return getPct((Comparable<?>) Long.valueOf(j));
    }

    public double getPct(char c) {
        return getPct((Comparable<?>) Character.valueOf(c));
    }

    public long getCumFreq(Comparable<?> comparable) {
        if (getSumFreq() == 0) {
            return 0;
        }
        if (comparable instanceof Integer) {
            return getCumFreq(((Integer) comparable).longValue());
        }
        Comparator comparator = this.freqTable.comparator();
        if (comparator == null) {
            comparator = new NaturalComparator();
        }
        try {
            Long l = (Long) this.freqTable.get(comparable);
            long longValue = l != null ? l.longValue() : 0;
            if (comparator.compare(comparable, this.freqTable.firstKey()) < 0) {
                return 0;
            }
            if (comparator.compare(comparable, this.freqTable.lastKey()) >= 0) {
                return getSumFreq();
            }
            Iterator valuesIterator = valuesIterator();
            while (valuesIterator.hasNext()) {
                Comparable comparable2 = (Comparable) valuesIterator.next();
                if (comparator.compare(comparable, comparable2) <= 0) {
                    return longValue;
                }
                longValue += getCount(comparable2);
            }
            return longValue;
        } catch (ClassCastException unused) {
            return 0;
        }
    }

    public long getCumFreq(int i) {
        return getCumFreq((Comparable<?>) Long.valueOf((long) i));
    }

    public long getCumFreq(long j) {
        return getCumFreq((Comparable<?>) Long.valueOf(j));
    }

    public long getCumFreq(char c) {
        return getCumFreq((Comparable<?>) Character.valueOf(c));
    }

    public double getCumPct(Comparable<?> comparable) {
        long sumFreq = getSumFreq();
        if (sumFreq == 0) {
            return Double.NaN;
        }
        return ((double) getCumFreq(comparable)) / ((double) sumFreq);
    }

    public double getCumPct(int i) {
        return getCumPct((Comparable<?>) Long.valueOf((long) i));
    }

    public double getCumPct(long j) {
        return getCumPct((Comparable<?>) Long.valueOf(j));
    }

    public double getCumPct(char c) {
        return getCumPct((Comparable<?>) Character.valueOf(c));
    }

    public void merge(Frequency frequency) {
        Iterator entrySetIterator = frequency.entrySetIterator();
        while (entrySetIterator.hasNext()) {
            Entry entry = (Entry) entrySetIterator.next();
            incrementValue((Comparable) entry.getKey(), ((Long) entry.getValue()).longValue());
        }
    }

    public void merge(Collection<Frequency> collection) {
        for (Frequency merge : collection) {
            merge(merge);
        }
    }

    public int hashCode() {
        return 31 + (this.freqTable == null ? 0 : this.freqTable.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Frequency)) {
            return false;
        }
        Frequency frequency = (Frequency) obj;
        if (this.freqTable == null) {
            if (frequency.freqTable != null) {
                return false;
            }
        } else if (!this.freqTable.equals(frequency.freqTable)) {
            return false;
        }
        return true;
    }
}
