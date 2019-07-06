package org.m4m.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

class Segments {
    /* access modifiers changed from: private */
    public Pair<Long, Long> currentSegment = null;
    private Dictionary<Pair<Long, Long>, Long> lastSegmentSampleTime = new Hashtable();
    SegmentListener segmentListener = new SegmentListener();
    /* access modifiers changed from: private */
    public ArrayList<Pair<Long, Long>> segments = new ArrayList<>();

    public class SegmentListener {
        public SegmentListener() {
        }

        public void segmentAdd() {
            if (Segments.this.segments.size() == 1) {
                Segments.this.currentSegment = (Pair) Segments.this.segments.get(0);
            }
        }
    }

    public Segments(List<Pair<Long, Long>> list) {
        for (Pair add : list) {
            add(add);
        }
    }

    public boolean isInsideSegment(long j) {
        boolean z = true;
        if (this.segments.isEmpty()) {
            return true;
        }
        if (getSegmentByTime(j) == null) {
            z = false;
        }
        return z;
    }

    public void saveSampleTime(long j) {
        Pair segmentByTime = getSegmentByTime(j);
        if (segmentByTime != null) {
            Long l = (Long) this.lastSegmentSampleTime.get(segmentByTime);
            if (l == null) {
                this.lastSegmentSampleTime.put(segmentByTime, Long.valueOf(j));
                return;
            }
            if (l.longValue() < j) {
                this.lastSegmentSampleTime.put(segmentByTime, Long.valueOf(j));
            }
        }
    }

    public long shift(long j) {
        return getCurrentSegmentTimeShift(j) != 0 ? (j - getCurrentSegmentTimeShift(j)) + getPreviousSegmentsTimeShift(j) : j;
    }

    public Pair<Long, Long> getSegmentAfter(long j) {
        Iterator it = this.segments.iterator();
        while (it.hasNext()) {
            Pair<Long, Long> pair = (Pair) it.next();
            if (j < ((Long) pair.left).longValue()) {
                return pair;
            }
        }
        return null;
    }

    private Pair<Long, Long> getSegmentByTime(long j) {
        Iterator it = this.segments.iterator();
        while (it.hasNext()) {
            Pair<Long, Long> pair = (Pair) it.next();
            if (((Long) pair.left).longValue() <= j && j <= ((Long) pair.right).longValue()) {
                return pair;
            }
        }
        return null;
    }

    private long getCurrentSegmentTimeShift(long j) {
        Pair segmentByTime = getSegmentByTime(j);
        if (segmentByTime != null) {
            return ((Long) segmentByTime.left).longValue();
        }
        return 0;
    }

    private long getPreviousSegmentsTimeShift(long j) {
        Iterator it = this.segments.iterator();
        long j2 = 0;
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (((Long) pair.right).longValue() < j) {
                j2 += ((Long) this.lastSegmentSampleTime.get(pair)).longValue() - ((Long) pair.left).longValue();
            }
        }
        return j2;
    }

    public boolean isEmpty() {
        return this.segments.isEmpty();
    }

    public void add(Pair<Long, Long> pair) {
        Pair arrange = arrange(pair);
        if (arrange != null) {
            this.segments.add(arrange);
            this.segmentListener.segmentAdd();
        }
    }

    private Pair<Long, Long> arrange(Pair<Long, Long> pair) {
        Pair<Long, Long> pair2 = new Pair<>(pair.left, pair.right);
        Iterator it = this.segments.iterator();
        while (it.hasNext()) {
            Pair pair3 = (Pair) it.next();
            if (((Long) pair2.left).longValue() <= ((Long) pair3.left).longValue() && ((Long) pair3.right).longValue() <= ((Long) pair2.right).longValue()) {
                it.remove();
            }
        }
        if (getSegmentByTime(((Long) pair.left).longValue()) == null && getSegmentByTime(((Long) pair.right).longValue()) == null) {
            return pair2;
        }
        if (getSegmentByTime(((Long) pair.left).longValue()) == getSegmentByTime(((Long) pair.right).longValue())) {
            return null;
        }
        Pair segmentByTime = getSegmentByTime(((Long) pair.left).longValue());
        if (segmentByTime != null) {
            pair2.left = segmentByTime.right;
        }
        Pair segmentByTime2 = getSegmentByTime(((Long) pair.right).longValue());
        if (segmentByTime2 != null) {
            pair2.right = segmentByTime2.left;
        }
        return pair2;
    }

    public Pair<Long, Long> first() {
        return (Pair) this.segments.get(0);
    }

    public Collection<Pair<Long, Long>> asCollection() {
        return new ArrayList(this.segments);
    }

    public void removeOutOfBoundSegments(long j) {
        for (int i = 0; i < this.segments.size(); i++) {
            if (((Long) ((Pair) this.segments.get(i)).left).longValue() >= j) {
                this.segments.remove(i);
            }
        }
    }
}
