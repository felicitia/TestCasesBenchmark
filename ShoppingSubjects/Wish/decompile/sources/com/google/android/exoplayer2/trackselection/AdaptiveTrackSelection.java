package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Clock;

public class AdaptiveTrackSelection extends BaseTrackSelection {
    private final float bandwidthFraction;
    private final BandwidthMeter bandwidthMeter;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    private final Clock clock;
    private long lastBufferEvaluationMs = -9223372036854775807L;
    private final long maxDurationForQualityDecreaseUs;
    private final int maxInitialBitrate;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    private final long minTimeBetweenBufferReevaluationMs;
    private float playbackSpeed = 1.0f;
    private int reason = 1;
    private int selectedIndex = determineIdealSelectedIndex(Long.MIN_VALUE);

    public static final class Factory implements com.google.android.exoplayer2.trackselection.TrackSelection.Factory {
        private final float bandwidthFraction;
        private final BandwidthMeter bandwidthMeter;
        private final float bufferedFractionToLiveEdgeForQualityIncrease;
        private final Clock clock;
        private final int maxDurationForQualityDecreaseMs;
        private final int maxInitialBitrate;
        private final int minDurationForQualityIncreaseMs;
        private final int minDurationToRetainAfterDiscardMs;
        private final long minTimeBetweenBufferReevaluationMs;

        public Factory(BandwidthMeter bandwidthMeter2) {
            this(bandwidthMeter2, 800000, 10000, 25000, 25000, 0.75f, 0.75f, 2000, Clock.DEFAULT);
        }

        public Factory(BandwidthMeter bandwidthMeter2, int i, int i2, int i3, int i4, float f, float f2, long j, Clock clock2) {
            this.bandwidthMeter = bandwidthMeter2;
            this.maxInitialBitrate = i;
            this.minDurationForQualityIncreaseMs = i2;
            this.maxDurationForQualityDecreaseMs = i3;
            this.minDurationToRetainAfterDiscardMs = i4;
            this.bandwidthFraction = f;
            this.bufferedFractionToLiveEdgeForQualityIncrease = f2;
            this.minTimeBetweenBufferReevaluationMs = j;
            this.clock = clock2;
        }

        public AdaptiveTrackSelection createTrackSelection(TrackGroup trackGroup, int... iArr) {
            AdaptiveTrackSelection adaptiveTrackSelection = new AdaptiveTrackSelection(trackGroup, iArr, this.bandwidthMeter, this.maxInitialBitrate, (long) this.minDurationForQualityIncreaseMs, (long) this.maxDurationForQualityDecreaseMs, (long) this.minDurationToRetainAfterDiscardMs, this.bandwidthFraction, this.bufferedFractionToLiveEdgeForQualityIncrease, this.minTimeBetweenBufferReevaluationMs, this.clock);
            return adaptiveTrackSelection;
        }
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthMeter bandwidthMeter2, int i, long j, long j2, long j3, float f, float f2, long j4, Clock clock2) {
        super(trackGroup, iArr);
        this.bandwidthMeter = bandwidthMeter2;
        this.maxInitialBitrate = i;
        this.minDurationForQualityIncreaseUs = j * 1000;
        this.maxDurationForQualityDecreaseUs = j2 * 1000;
        this.minDurationToRetainAfterDiscardUs = j3 * 1000;
        this.bandwidthFraction = f;
        this.bufferedFractionToLiveEdgeForQualityIncrease = f2;
        this.minTimeBetweenBufferReevaluationMs = j4;
        this.clock = clock2;
    }

    public void enable() {
        this.lastBufferEvaluationMs = -9223372036854775807L;
    }

    public void onPlaybackSpeed(float f) {
        this.playbackSpeed = f;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    private int determineIdealSelectedIndex(long j) {
        long bitrateEstimate = this.bandwidthMeter.getBitrateEstimate();
        long j2 = bitrateEstimate == -1 ? (long) this.maxInitialBitrate : (long) (((float) bitrateEstimate) * this.bandwidthFraction);
        int i = 0;
        for (int i2 = 0; i2 < this.length; i2++) {
            if (j == Long.MIN_VALUE || !isBlacklisted(i2, j)) {
                if (((long) Math.round(((float) getFormat(i2).bitrate) * this.playbackSpeed)) <= j2) {
                    return i2;
                }
                i = i2;
            }
        }
        return i;
    }
}
