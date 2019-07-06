package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;

final class MediaPeriodQueue {
    private int length;
    private MediaPeriodHolder loading;
    private long nextWindowSequenceNumber;
    private final Period period = new Period();
    private MediaPeriodHolder playing;
    private MediaPeriodHolder reading;
    private int repeatMode;
    private boolean shuffleModeEnabled;
    private Timeline timeline;
    private final Window window = new Window();

    public void setTimeline(Timeline timeline2) {
        this.timeline = timeline2;
    }

    public boolean updateRepeatMode(int i) {
        this.repeatMode = i;
        return updateForPlaybackModeChange();
    }

    public boolean updateShuffleModeEnabled(boolean z) {
        this.shuffleModeEnabled = z;
        return updateForPlaybackModeChange();
    }

    public boolean isLoading(MediaPeriod mediaPeriod) {
        return this.loading != null && this.loading.mediaPeriod == mediaPeriod;
    }

    public void reevaluateBuffer(long j) {
        if (this.loading != null) {
            this.loading.reevaluateBuffer(j);
        }
    }

    public boolean shouldLoadNextMediaPeriod() {
        return this.loading == null || (!this.loading.info.isFinal && this.loading.isFullyBuffered() && this.loading.info.durationUs != -9223372036854775807L && this.length < 100);
    }

    public MediaPeriodInfo getNextMediaPeriodInfo(long j, PlaybackInfo playbackInfo) {
        if (this.loading == null) {
            return getFirstMediaPeriodInfo(playbackInfo);
        }
        return getFollowingMediaPeriodInfo(this.loading, j);
    }

    public MediaPeriod enqueueNextMediaPeriod(RendererCapabilities[] rendererCapabilitiesArr, long j, TrackSelector trackSelector, Allocator allocator, MediaSource mediaSource, Object obj, MediaPeriodInfo mediaPeriodInfo) {
        MediaPeriodInfo mediaPeriodInfo2;
        long rendererOffset;
        if (this.loading == null) {
            mediaPeriodInfo2 = mediaPeriodInfo;
            rendererOffset = mediaPeriodInfo2.startPositionUs + j;
        } else {
            mediaPeriodInfo2 = mediaPeriodInfo;
            rendererOffset = this.loading.getRendererOffset() + this.loading.info.durationUs;
        }
        MediaPeriodHolder mediaPeriodHolder = new MediaPeriodHolder(rendererCapabilitiesArr, rendererOffset, trackSelector, allocator, mediaSource, obj, mediaPeriodInfo2);
        if (this.loading != null) {
            Assertions.checkState(hasPlayingPeriod());
            this.loading.next = mediaPeriodHolder;
        }
        this.loading = mediaPeriodHolder;
        this.length++;
        return mediaPeriodHolder.mediaPeriod;
    }

    public TrackSelectorResult handleLoadingPeriodPrepared(float f) throws ExoPlaybackException {
        return this.loading.handlePrepared(f);
    }

    public MediaPeriodHolder getLoadingPeriod() {
        return this.loading;
    }

    public MediaPeriodHolder getPlayingPeriod() {
        return this.playing;
    }

    public MediaPeriodHolder getReadingPeriod() {
        return this.reading;
    }

    public MediaPeriodHolder getFrontPeriod() {
        return hasPlayingPeriod() ? this.playing : this.loading;
    }

    public boolean hasPlayingPeriod() {
        return this.playing != null;
    }

    public MediaPeriodHolder advanceReadingPeriod() {
        Assertions.checkState((this.reading == null || this.reading.next == null) ? false : true);
        this.reading = this.reading.next;
        return this.reading;
    }

    public MediaPeriodHolder advancePlayingPeriod() {
        if (this.playing != null) {
            if (this.playing == this.reading) {
                this.reading = this.playing.next;
            }
            this.playing.release();
            this.playing = this.playing.next;
            this.length--;
            if (this.length == 0) {
                this.loading = null;
            }
        } else {
            this.playing = this.loading;
            this.reading = this.loading;
        }
        return this.playing;
    }

    public boolean removeAfter(MediaPeriodHolder mediaPeriodHolder) {
        boolean z = false;
        Assertions.checkState(mediaPeriodHolder != null);
        this.loading = mediaPeriodHolder;
        while (mediaPeriodHolder.next != null) {
            mediaPeriodHolder = mediaPeriodHolder.next;
            if (mediaPeriodHolder == this.reading) {
                this.reading = this.playing;
                z = true;
            }
            mediaPeriodHolder.release();
            this.length--;
        }
        this.loading.next = null;
        return z;
    }

    public void clear() {
        MediaPeriodHolder frontPeriod = getFrontPeriod();
        if (frontPeriod != null) {
            frontPeriod.release();
            removeAfter(frontPeriod);
        }
        this.playing = null;
        this.loading = null;
        this.reading = null;
        this.length = 0;
    }

    public boolean updateQueuedPeriods(MediaPeriodId mediaPeriodId, long j) {
        int i = mediaPeriodId.periodIndex;
        MediaPeriodHolder mediaPeriodHolder = null;
        MediaPeriodHolder frontPeriod = getFrontPeriod();
        while (frontPeriod != null) {
            if (mediaPeriodHolder == null) {
                frontPeriod.info = getUpdatedMediaPeriodInfo(frontPeriod.info, i);
            } else if (i == -1 || !frontPeriod.uid.equals(this.timeline.getPeriod(i, this.period, true).uid)) {
                return !removeAfter(mediaPeriodHolder);
            } else {
                MediaPeriodInfo followingMediaPeriodInfo = getFollowingMediaPeriodInfo(mediaPeriodHolder, j);
                if (followingMediaPeriodInfo == null) {
                    return !removeAfter(mediaPeriodHolder);
                }
                frontPeriod.info = getUpdatedMediaPeriodInfo(frontPeriod.info, i);
                if (!canKeepMediaPeriodHolder(frontPeriod, followingMediaPeriodInfo)) {
                    return !removeAfter(mediaPeriodHolder);
                }
            }
            if (frontPeriod.info.isLastInTimelinePeriod) {
                i = this.timeline.getNextPeriodIndex(i, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            }
            MediaPeriodHolder mediaPeriodHolder2 = frontPeriod;
            frontPeriod = frontPeriod.next;
            mediaPeriodHolder = mediaPeriodHolder2;
        }
        return true;
    }

    public MediaPeriodInfo getUpdatedMediaPeriodInfo(MediaPeriodInfo mediaPeriodInfo, int i) {
        return getUpdatedMediaPeriodInfo(mediaPeriodInfo, mediaPeriodInfo.id.copyWithPeriodIndex(i));
    }

    public MediaPeriodId resolveMediaPeriodIdForAds(int i, long j) {
        return resolveMediaPeriodIdForAds(i, j, resolvePeriodIndexToWindowSequenceNumber(i));
    }

    private MediaPeriodId resolveMediaPeriodIdForAds(int i, long j, long j2) {
        this.timeline.getPeriod(i, this.period);
        int adGroupIndexForPositionUs = this.period.getAdGroupIndexForPositionUs(j);
        if (adGroupIndexForPositionUs == -1) {
            return new MediaPeriodId(i, j2);
        }
        MediaPeriodId mediaPeriodId = new MediaPeriodId(i, adGroupIndexForPositionUs, this.period.getFirstAdIndexToPlay(adGroupIndexForPositionUs), j2);
        return mediaPeriodId;
    }

    private long resolvePeriodIndexToWindowSequenceNumber(int i) {
        Object obj = this.timeline.getPeriod(i, this.period, true).uid;
        for (MediaPeriodHolder frontPeriod = getFrontPeriod(); frontPeriod != null; frontPeriod = frontPeriod.next) {
            if (frontPeriod.uid.equals(obj)) {
                return frontPeriod.info.id.windowSequenceNumber;
            }
        }
        int i2 = this.period.windowIndex;
        for (MediaPeriodHolder frontPeriod2 = getFrontPeriod(); frontPeriod2 != null; frontPeriod2 = frontPeriod2.next) {
            int indexOfPeriod = this.timeline.getIndexOfPeriod(frontPeriod2.uid);
            if (indexOfPeriod != -1 && this.timeline.getPeriod(indexOfPeriod, this.period).windowIndex == i2) {
                return frontPeriod2.info.id.windowSequenceNumber;
            }
        }
        long j = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = j + 1;
        return j;
    }

    private boolean canKeepMediaPeriodHolder(MediaPeriodHolder mediaPeriodHolder, MediaPeriodInfo mediaPeriodInfo) {
        MediaPeriodInfo mediaPeriodInfo2 = mediaPeriodHolder.info;
        return mediaPeriodInfo2.startPositionUs == mediaPeriodInfo.startPositionUs && mediaPeriodInfo2.endPositionUs == mediaPeriodInfo.endPositionUs && mediaPeriodInfo2.id.equals(mediaPeriodInfo.id);
    }

    private boolean updateForPlaybackModeChange() {
        MediaPeriodHolder frontPeriod = getFrontPeriod();
        boolean z = true;
        if (frontPeriod == null) {
            return true;
        }
        while (true) {
            int nextPeriodIndex = this.timeline.getNextPeriodIndex(frontPeriod.info.id.periodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            while (frontPeriod.next != null && !frontPeriod.info.isLastInTimelinePeriod) {
                frontPeriod = frontPeriod.next;
            }
            if (nextPeriodIndex == -1 || frontPeriod.next == null || frontPeriod.next.info.id.periodIndex != nextPeriodIndex) {
                boolean removeAfter = removeAfter(frontPeriod);
                frontPeriod.info = getUpdatedMediaPeriodInfo(frontPeriod.info, frontPeriod.info.id);
            } else {
                frontPeriod = frontPeriod.next;
            }
        }
        boolean removeAfter2 = removeAfter(frontPeriod);
        frontPeriod.info = getUpdatedMediaPeriodInfo(frontPeriod.info, frontPeriod.info.id);
        if (removeAfter2 && hasPlayingPeriod()) {
            z = false;
        }
        return z;
    }

    private MediaPeriodInfo getFirstMediaPeriodInfo(PlaybackInfo playbackInfo) {
        return getMediaPeriodInfo(playbackInfo.periodId, playbackInfo.contentPositionUs, playbackInfo.startPositionUs);
    }

    private MediaPeriodInfo getFollowingMediaPeriodInfo(MediaPeriodHolder mediaPeriodHolder, long j) {
        long j2;
        int i;
        long j3;
        MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder;
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder2.info;
        MediaPeriodInfo mediaPeriodInfo2 = null;
        if (mediaPeriodInfo.isLastInTimelinePeriod) {
            int nextPeriodIndex = this.timeline.getNextPeriodIndex(mediaPeriodInfo.id.periodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if (nextPeriodIndex == -1) {
                return null;
            }
            int i2 = this.timeline.getPeriod(nextPeriodIndex, this.period, true).windowIndex;
            Object obj = this.period.uid;
            long j4 = mediaPeriodInfo.id.windowSequenceNumber;
            long j5 = 0;
            if (this.timeline.getWindow(i2, this.window).firstPeriodIndex == nextPeriodIndex) {
                long rendererOffset = (mediaPeriodHolder.getRendererOffset() + mediaPeriodInfo.durationUs) - j;
                Timeline timeline2 = this.timeline;
                Pair periodPosition = timeline2.getPeriodPosition(this.window, this.period, i2, -9223372036854775807L, Math.max(0, rendererOffset));
                if (periodPosition == null) {
                    return null;
                }
                int intValue = ((Integer) periodPosition.first).intValue();
                long longValue = ((Long) periodPosition.second).longValue();
                if (mediaPeriodHolder2.next == null || !mediaPeriodHolder2.next.uid.equals(obj)) {
                    j3 = this.nextWindowSequenceNumber;
                    this.nextWindowSequenceNumber = j3 + 1;
                } else {
                    j3 = mediaPeriodHolder2.next.info.id.windowSequenceNumber;
                }
                j5 = longValue;
                j2 = j3;
                i = intValue;
            } else {
                i = nextPeriodIndex;
                j2 = j4;
            }
            long j6 = j5;
            return getMediaPeriodInfo(resolveMediaPeriodIdForAds(i, j6, j2), j6, j5);
        }
        MediaPeriodId mediaPeriodId = mediaPeriodInfo.id;
        this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
        if (mediaPeriodId.isAd()) {
            int i3 = mediaPeriodId.adGroupIndex;
            int adCountInAdGroup = this.period.getAdCountInAdGroup(i3);
            if (adCountInAdGroup == -1) {
                return null;
            }
            int nextAdIndexToPlay = this.period.getNextAdIndexToPlay(i3, mediaPeriodId.adIndexInAdGroup);
            if (nextAdIndexToPlay < adCountInAdGroup) {
                if (this.period.isAdAvailable(i3, nextAdIndexToPlay)) {
                    mediaPeriodInfo2 = getMediaPeriodInfoForAd(mediaPeriodId.periodIndex, i3, nextAdIndexToPlay, mediaPeriodInfo.contentPositionUs, mediaPeriodId.windowSequenceNumber);
                }
                return mediaPeriodInfo2;
            }
            return getMediaPeriodInfoForContent(mediaPeriodId.periodIndex, mediaPeriodInfo.contentPositionUs, mediaPeriodId.windowSequenceNumber);
        } else if (mediaPeriodInfo.endPositionUs != Long.MIN_VALUE) {
            int adGroupIndexForPositionUs = this.period.getAdGroupIndexForPositionUs(mediaPeriodInfo.endPositionUs);
            if (adGroupIndexForPositionUs == -1) {
                return getMediaPeriodInfoForContent(mediaPeriodId.periodIndex, mediaPeriodInfo.endPositionUs, mediaPeriodId.windowSequenceNumber);
            }
            int firstAdIndexToPlay = this.period.getFirstAdIndexToPlay(adGroupIndexForPositionUs);
            if (this.period.isAdAvailable(adGroupIndexForPositionUs, firstAdIndexToPlay)) {
                mediaPeriodInfo2 = getMediaPeriodInfoForAd(mediaPeriodId.periodIndex, adGroupIndexForPositionUs, firstAdIndexToPlay, mediaPeriodInfo.endPositionUs, mediaPeriodId.windowSequenceNumber);
            }
            return mediaPeriodInfo2;
        } else {
            int adGroupCount = this.period.getAdGroupCount();
            if (adGroupCount == 0) {
                return null;
            }
            int i4 = adGroupCount - 1;
            if (this.period.getAdGroupTimeUs(i4) != Long.MIN_VALUE || this.period.hasPlayedAdGroup(i4)) {
                return null;
            }
            int firstAdIndexToPlay2 = this.period.getFirstAdIndexToPlay(i4);
            if (!this.period.isAdAvailable(i4, firstAdIndexToPlay2)) {
                return null;
            }
            long durationUs = this.period.getDurationUs();
            return getMediaPeriodInfoForAd(mediaPeriodId.periodIndex, i4, firstAdIndexToPlay2, durationUs, mediaPeriodId.windowSequenceNumber);
        }
    }

    private MediaPeriodInfo getUpdatedMediaPeriodInfo(MediaPeriodInfo mediaPeriodInfo, MediaPeriodId mediaPeriodId) {
        long j;
        long durationUs;
        long j2 = mediaPeriodInfo.startPositionUs;
        long j3 = mediaPeriodInfo.endPositionUs;
        boolean isLastInPeriod = isLastInPeriod(mediaPeriodId, j3);
        boolean isLastInTimeline = isLastInTimeline(mediaPeriodId, isLastInPeriod);
        this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
        if (mediaPeriodId.isAd()) {
            durationUs = this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
        } else if (j3 == Long.MIN_VALUE) {
            durationUs = this.period.getDurationUs();
        } else {
            j = j3;
            MediaPeriodInfo mediaPeriodInfo2 = new MediaPeriodInfo(mediaPeriodId, j2, j3, mediaPeriodInfo.contentPositionUs, j, isLastInPeriod, isLastInTimeline);
            return mediaPeriodInfo2;
        }
        j = durationUs;
        MediaPeriodInfo mediaPeriodInfo22 = new MediaPeriodInfo(mediaPeriodId, j2, j3, mediaPeriodInfo.contentPositionUs, j, isLastInPeriod, isLastInTimeline);
        return mediaPeriodInfo22;
    }

    private MediaPeriodInfo getMediaPeriodInfo(MediaPeriodId mediaPeriodId, long j, long j2) {
        this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
        if (!mediaPeriodId.isAd()) {
            return getMediaPeriodInfoForContent(mediaPeriodId.periodIndex, j2, mediaPeriodId.windowSequenceNumber);
        } else if (!this.period.isAdAvailable(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup)) {
            return null;
        } else {
            return getMediaPeriodInfoForAd(mediaPeriodId.periodIndex, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, j, mediaPeriodId.windowSequenceNumber);
        }
    }

    private MediaPeriodInfo getMediaPeriodInfoForAd(int i, int i2, int i3, long j, long j2) {
        MediaPeriodId mediaPeriodId = new MediaPeriodId(i, i2, i3, j2);
        boolean isLastInPeriod = isLastInPeriod(mediaPeriodId, Long.MIN_VALUE);
        boolean isLastInTimeline = isLastInTimeline(mediaPeriodId, isLastInPeriod);
        MediaPeriodInfo mediaPeriodInfo = new MediaPeriodInfo(mediaPeriodId, i3 == this.period.getFirstAdIndexToPlay(i2) ? this.period.getAdResumePositionUs() : 0, Long.MIN_VALUE, j, this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period).getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup), isLastInPeriod, isLastInTimeline);
        return mediaPeriodInfo;
    }

    private MediaPeriodInfo getMediaPeriodInfoForContent(int i, long j, long j2) {
        long j3;
        MediaPeriodId mediaPeriodId = new MediaPeriodId(i, j2);
        this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
        long j4 = j;
        int adGroupIndexAfterPositionUs = this.period.getAdGroupIndexAfterPositionUs(j4);
        if (adGroupIndexAfterPositionUs == -1) {
            j3 = Long.MIN_VALUE;
        } else {
            j3 = this.period.getAdGroupTimeUs(adGroupIndexAfterPositionUs);
        }
        boolean isLastInPeriod = isLastInPeriod(mediaPeriodId, j3);
        MediaPeriodInfo mediaPeriodInfo = new MediaPeriodInfo(mediaPeriodId, j4, j3, -9223372036854775807L, j3 == Long.MIN_VALUE ? this.period.getDurationUs() : j3, isLastInPeriod, isLastInTimeline(mediaPeriodId, isLastInPeriod));
        return mediaPeriodInfo;
    }

    private boolean isLastInPeriod(MediaPeriodId mediaPeriodId, long j) {
        int adGroupCount = this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period).getAdGroupCount();
        boolean z = true;
        if (adGroupCount == 0) {
            return true;
        }
        int i = adGroupCount - 1;
        boolean isAd = mediaPeriodId.isAd();
        if (this.period.getAdGroupTimeUs(i) != Long.MIN_VALUE) {
            if (isAd || j != Long.MIN_VALUE) {
                z = false;
            }
            return z;
        }
        int adCountInAdGroup = this.period.getAdCountInAdGroup(i);
        if (adCountInAdGroup == -1) {
            return false;
        }
        if (!(isAd && mediaPeriodId.adGroupIndex == i && mediaPeriodId.adIndexInAdGroup == adCountInAdGroup + -1) && (isAd || this.period.getFirstAdIndexToPlay(i) != adCountInAdGroup)) {
            z = false;
        }
        return z;
    }

    private boolean isLastInTimeline(MediaPeriodId mediaPeriodId, boolean z) {
        return !this.timeline.getWindow(this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period).windowIndex, this.window).isDynamic && this.timeline.isLastPeriod(mediaPeriodId.periodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled) && z;
    }
}
