package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaSource.Listener;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;

public final class ClippingMediaSource extends CompositeMediaSource<Void> {
    private IllegalClippingException clippingError;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private final MediaSource mediaSource;
    private Listener sourceListener;
    private final long startUs;

    private static final class ClippingTimeline extends ForwardingTimeline {
        private final long endUs;
        private final long startUs;

        public ClippingTimeline(Timeline timeline, long j, long j2) throws IllegalClippingException {
            super(timeline);
            if (timeline.getPeriodCount() != 1) {
                throw new IllegalClippingException(0);
            } else if (timeline.getPeriod(0, new Period()).getPositionInWindowUs() != 0) {
                throw new IllegalClippingException(1);
            } else {
                Window window = timeline.getWindow(0, new Window(), false);
                if (j2 == Long.MIN_VALUE) {
                    j2 = window.durationUs;
                }
                if (window.durationUs != -9223372036854775807L) {
                    if (j2 > window.durationUs) {
                        j2 = window.durationUs;
                    }
                    if (j != 0 && !window.isSeekable) {
                        throw new IllegalClippingException(2);
                    } else if (j > j2) {
                        throw new IllegalClippingException(3);
                    }
                }
                this.startUs = j;
                this.endUs = j2;
            }
        }

        public Window getWindow(int i, Window window, boolean z, long j) {
            long j2;
            Window window2 = this.timeline.getWindow(0, window, z, j);
            window2.durationUs = this.endUs != -9223372036854775807L ? this.endUs - this.startUs : -9223372036854775807L;
            if (window2.defaultPositionUs != -9223372036854775807L) {
                window2.defaultPositionUs = Math.max(window2.defaultPositionUs, this.startUs);
                if (this.endUs == -9223372036854775807L) {
                    j2 = window2.defaultPositionUs;
                } else {
                    j2 = Math.min(window2.defaultPositionUs, this.endUs);
                }
                window2.defaultPositionUs = j2;
                window2.defaultPositionUs -= this.startUs;
            }
            long usToMs = C.usToMs(this.startUs);
            if (window2.presentationStartTimeMs != -9223372036854775807L) {
                window2.presentationStartTimeMs += usToMs;
            }
            if (window2.windowStartTimeMs != -9223372036854775807L) {
                window2.windowStartTimeMs += usToMs;
            }
            return window2;
        }

        public Period getPeriod(int i, Period period, boolean z) {
            Period period2 = this.timeline.getPeriod(0, period, z);
            long j = -9223372036854775807L;
            if (this.endUs != -9223372036854775807L) {
                j = this.endUs - this.startUs;
            }
            period2.durationUs = j;
            return period2;
        }
    }

    public static final class IllegalClippingException extends IOException {
        public final int reason;

        public IllegalClippingException(int i) {
            this.reason = i;
        }
    }

    public void prepareSource(ExoPlayer exoPlayer, boolean z, Listener listener) {
        super.prepareSource(exoPlayer, z, listener);
        this.sourceListener = listener;
        prepareChildSource(null, this.mediaSource);
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        if (this.clippingError != null) {
            throw this.clippingError;
        }
        super.maybeThrowSourceInfoRefreshError();
    }

    public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator), this.enableInitialDiscontinuity);
        this.mediaPeriods.add(clippingMediaPeriod);
        clippingMediaPeriod.setClipping(this.startUs, this.endUs);
        return clippingMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
    }

    public void releaseSource() {
        super.releaseSource();
        this.clippingError = null;
        this.sourceListener = null;
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Void voidR, MediaSource mediaSource2, Timeline timeline, Object obj) {
        if (this.clippingError == null) {
            try {
                ClippingTimeline clippingTimeline = new ClippingTimeline(timeline, this.startUs, this.endUs);
                this.sourceListener.onSourceInfoRefreshed(this, clippingTimeline, obj);
                int size = this.mediaPeriods.size();
                for (int i = 0; i < size; i++) {
                    ((ClippingMediaPeriod) this.mediaPeriods.get(i)).setClipping(this.startUs, this.endUs);
                }
            } catch (IllegalClippingException e) {
                this.clippingError = e;
            }
        }
    }
}
