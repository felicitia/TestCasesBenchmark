package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;
import java.util.Comparator;

public abstract class BaseTrackSelection implements TrackSelection {
    private final long[] blacklistUntilTimes;
    private final Format[] formats;
    protected final TrackGroup group;
    private int hashCode;
    protected final int length;
    protected final int[] tracks;

    private static final class DecreasingBandwidthComparator implements Comparator<Format> {
        private DecreasingBandwidthComparator() {
        }

        public int compare(Format format, Format format2) {
            return format2.bitrate - format.bitrate;
        }
    }

    public void disable() {
    }

    public void enable() {
    }

    public void onPlaybackSpeed(float f) {
    }

    public BaseTrackSelection(TrackGroup trackGroup, int... iArr) {
        Assertions.checkState(iArr.length > 0);
        this.group = (TrackGroup) Assertions.checkNotNull(trackGroup);
        this.length = iArr.length;
        this.formats = new Format[this.length];
        for (int i = 0; i < iArr.length; i++) {
            this.formats[i] = trackGroup.getFormat(iArr[i]);
        }
        Arrays.sort(this.formats, new DecreasingBandwidthComparator());
        this.tracks = new int[this.length];
        for (int i2 = 0; i2 < this.length; i2++) {
            this.tracks[i2] = trackGroup.indexOf(this.formats[i2]);
        }
        this.blacklistUntilTimes = new long[this.length];
    }

    public final TrackGroup getTrackGroup() {
        return this.group;
    }

    public final int length() {
        return this.tracks.length;
    }

    public final Format getFormat(int i) {
        return this.formats[i];
    }

    public final int getIndexInTrackGroup(int i) {
        return this.tracks[i];
    }

    public final Format getSelectedFormat() {
        return this.formats[getSelectedIndex()];
    }

    /* access modifiers changed from: protected */
    public final boolean isBlacklisted(int i, long j) {
        return this.blacklistUntilTimes[i] > j;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = (System.identityHashCode(this.group) * 31) + Arrays.hashCode(this.tracks);
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseTrackSelection baseTrackSelection = (BaseTrackSelection) obj;
        if (this.group != baseTrackSelection.group || !Arrays.equals(this.tracks, baseTrackSelection.tracks)) {
            z = false;
        }
        return z;
    }
}
