package org.m4m.domain;

import java.util.Hashtable;

public class FileSegment {
    public Pair<Long, Long> pair;
    private Hashtable<Integer, Integer> trackIdMap = new Hashtable<>();
    private Hashtable<MediaFormatType, Integer> trackTypeMap = new Hashtable<>();

    public FileSegment(long j, long j2) {
        this.pair = new Pair<>(Long.valueOf(j), Long.valueOf(j2));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FileSegment)) {
            return false;
        }
        return this.pair.equals(((FileSegment) obj).pair);
    }

    public int hashCode() {
        return this.pair.hashCode();
    }
}
