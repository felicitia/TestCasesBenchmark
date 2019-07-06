package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.source.TrackGroup;

public final class FixedTrackSelection extends BaseTrackSelection {
    private final Object data;
    private final int reason;

    public int getSelectedIndex() {
        return 0;
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i) {
        this(trackGroup, i, 0, null);
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i, int i2, Object obj) {
        super(trackGroup, i);
        this.reason = i2;
        this.data = obj;
    }
}
