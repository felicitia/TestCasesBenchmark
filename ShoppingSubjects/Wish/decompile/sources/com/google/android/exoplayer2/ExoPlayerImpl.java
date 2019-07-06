package com.google.android.exoplayer2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.PlayerMessage.Target;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

final class ExoPlayerImpl implements ExoPlayer {
    private final TrackSelectorResult emptyTrackSelectorResult;
    private final Handler eventHandler;
    private boolean hasPendingPrepare;
    private boolean hasPendingSeek;
    private final ExoPlayerImplInternal internalPlayer;
    private final Handler internalPlayerHandler;
    private final CopyOnWriteArraySet<EventListener> listeners;
    private int maskingPeriodIndex;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    private int pendingOperationAcks;
    private final Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private PlaybackParameters playbackParameters;
    private final Renderer[] renderers;
    private int repeatMode;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Window window;

    @SuppressLint({"HandlerLeak"})
    public ExoPlayerImpl(Renderer[] rendererArr, TrackSelector trackSelector2, LoadControl loadControl, Clock clock) {
        Renderer[] rendererArr2 = rendererArr;
        StringBuilder sb = new StringBuilder();
        sb.append("Init ");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" [");
        sb.append("ExoPlayerLib/2.7.3");
        sb.append("] [");
        sb.append(Util.DEVICE_DEBUG_INFO);
        sb.append("]");
        Log.i("ExoPlayerImpl", sb.toString());
        Assertions.checkState(rendererArr2.length > 0);
        this.renderers = (Renderer[]) Assertions.checkNotNull(rendererArr2);
        this.trackSelector = (TrackSelector) Assertions.checkNotNull(trackSelector2);
        this.playWhenReady = false;
        this.repeatMode = 0;
        this.shuffleModeEnabled = false;
        this.listeners = new CopyOnWriteArraySet<>();
        TrackSelectorResult trackSelectorResult = new TrackSelectorResult(TrackGroupArray.EMPTY, new boolean[rendererArr2.length], new TrackSelectionArray(new TrackSelection[rendererArr2.length]), null, new RendererConfiguration[rendererArr2.length]);
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.window = new Window();
        this.period = new Period();
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.eventHandler = new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                ExoPlayerImpl.this.handleEvent(message);
            }
        };
        this.playbackInfo = new PlaybackInfo(Timeline.EMPTY, 0, this.emptyTrackSelectorResult);
        ExoPlayerImplInternal exoPlayerImplInternal = new ExoPlayerImplInternal(rendererArr2, trackSelector2, this.emptyTrackSelectorResult, loadControl, this.playWhenReady, this.repeatMode, this.shuffleModeEnabled, this.eventHandler, this, clock);
        this.internalPlayer = exoPlayerImplInternal;
        this.internalPlayerHandler = new Handler(this.internalPlayer.getPlaybackLooper());
    }

    public void addListener(EventListener eventListener) {
        this.listeners.add(eventListener);
    }

    public void removeListener(EventListener eventListener) {
        this.listeners.remove(eventListener);
    }

    public int getPlaybackState() {
        return this.playbackInfo.playbackState;
    }

    public void prepare(MediaSource mediaSource) {
        prepare(mediaSource, true, true);
    }

    public void prepare(MediaSource mediaSource, boolean z, boolean z2) {
        PlaybackInfo resetPlaybackInfo = getResetPlaybackInfo(z, z2, 2);
        this.hasPendingPrepare = true;
        this.pendingOperationAcks++;
        this.internalPlayer.prepare(mediaSource, z, z2);
        updatePlaybackInfo(resetPlaybackInfo, false, 4, 1, false);
    }

    public void setPlayWhenReady(boolean z) {
        if (this.playWhenReady != z) {
            this.playWhenReady = z;
            this.internalPlayer.setPlayWhenReady(z);
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((EventListener) it.next()).onPlayerStateChanged(z, this.playbackInfo.playbackState);
            }
        }
    }

    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    public void seekTo(long j) {
        seekTo(getCurrentWindowIndex(), j);
    }

    public void seekTo(int i, long j) {
        Timeline timeline = this.playbackInfo.timeline;
        if (i < 0 || (!timeline.isEmpty() && i >= timeline.getWindowCount())) {
            throw new IllegalSeekPositionException(timeline, i, j);
        }
        this.hasPendingSeek = true;
        this.pendingOperationAcks++;
        if (isPlayingAd()) {
            Log.w("ExoPlayerImpl", "seekTo ignored because an ad is playing");
            this.eventHandler.obtainMessage(0, 1, -1, this.playbackInfo).sendToTarget();
            return;
        }
        this.maskingWindowIndex = i;
        if (timeline.isEmpty()) {
            this.maskingWindowPositionMs = j == -9223372036854775807L ? 0 : j;
            this.maskingPeriodIndex = 0;
        } else {
            long defaultPositionUs = j == -9223372036854775807L ? timeline.getWindow(i, this.window).getDefaultPositionUs() : C.msToUs(j);
            Pair periodPosition = timeline.getPeriodPosition(this.window, this.period, i, defaultPositionUs);
            this.maskingWindowPositionMs = C.usToMs(defaultPositionUs);
            this.maskingPeriodIndex = ((Integer) periodPosition.first).intValue();
        }
        this.internalPlayer.seekTo(timeline, i, C.msToUs(j));
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((EventListener) it.next()).onPositionDiscontinuity(1);
        }
    }

    public void release() {
        StringBuilder sb = new StringBuilder();
        sb.append("Release ");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" [");
        sb.append("ExoPlayerLib/2.7.3");
        sb.append("] [");
        sb.append(Util.DEVICE_DEBUG_INFO);
        sb.append("] [");
        sb.append(ExoPlayerLibraryInfo.registeredModules());
        sb.append("]");
        Log.i("ExoPlayerImpl", sb.toString());
        this.internalPlayer.release();
        this.eventHandler.removeCallbacksAndMessages(null);
    }

    public PlayerMessage createMessage(Target target) {
        PlayerMessage playerMessage = new PlayerMessage(this.internalPlayer, target, this.playbackInfo.timeline, getCurrentWindowIndex(), this.internalPlayerHandler);
        return playerMessage;
    }

    public int getCurrentPeriodIndex() {
        if (shouldMaskPosition()) {
            return this.maskingPeriodIndex;
        }
        return this.playbackInfo.periodId.periodIndex;
    }

    public int getCurrentWindowIndex() {
        if (shouldMaskPosition()) {
            return this.maskingWindowIndex;
        }
        return this.playbackInfo.timeline.getPeriod(this.playbackInfo.periodId.periodIndex, this.period).windowIndex;
    }

    public long getDuration() {
        Timeline timeline = this.playbackInfo.timeline;
        if (timeline.isEmpty()) {
            return -9223372036854775807L;
        }
        if (!isPlayingAd()) {
            return timeline.getWindow(getCurrentWindowIndex(), this.window).getDurationMs();
        }
        MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
        timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
        return C.usToMs(this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup));
    }

    public long getCurrentPosition() {
        if (shouldMaskPosition()) {
            return this.maskingWindowPositionMs;
        }
        return playbackInfoPositionUsToWindowPositionMs(this.playbackInfo.positionUs);
    }

    public long getBufferedPosition() {
        if (shouldMaskPosition()) {
            return this.maskingWindowPositionMs;
        }
        return playbackInfoPositionUsToWindowPositionMs(this.playbackInfo.bufferedPositionUs);
    }

    public int getBufferedPercentage() {
        long bufferedPosition = getBufferedPosition();
        long duration = getDuration();
        if (bufferedPosition == -9223372036854775807L || duration == -9223372036854775807L) {
            return 0;
        }
        if (duration == 0) {
            return 100;
        }
        return Util.constrainValue((int) ((bufferedPosition * 100) / duration), 0, 100);
    }

    public boolean isPlayingAd() {
        return !shouldMaskPosition() && this.playbackInfo.periodId.isAd();
    }

    public int getRendererType(int i) {
        return this.renderers[i].getTrackType();
    }

    public TrackSelectionArray getCurrentTrackSelections() {
        return this.playbackInfo.trackSelectorResult.selections;
    }

    public Timeline getCurrentTimeline() {
        return this.playbackInfo.timeline;
    }

    /* access modifiers changed from: 0000 */
    public void handleEvent(Message message) {
        switch (message.what) {
            case 0:
                handlePlaybackInfo((PlaybackInfo) message.obj, message.arg1, message.arg2 != -1, message.arg2);
                return;
            case 1:
                PlaybackParameters playbackParameters2 = (PlaybackParameters) message.obj;
                if (!this.playbackParameters.equals(playbackParameters2)) {
                    this.playbackParameters = playbackParameters2;
                    Iterator it = this.listeners.iterator();
                    while (it.hasNext()) {
                        ((EventListener) it.next()).onPlaybackParametersChanged(playbackParameters2);
                    }
                    return;
                }
                return;
            case 2:
                ExoPlaybackException exoPlaybackException = (ExoPlaybackException) message.obj;
                Iterator it2 = this.listeners.iterator();
                while (it2.hasNext()) {
                    ((EventListener) it2.next()).onPlayerError(exoPlaybackException);
                }
                return;
            default:
                throw new IllegalStateException();
        }
    }

    private void handlePlaybackInfo(PlaybackInfo playbackInfo2, int i, boolean z, int i2) {
        this.pendingOperationAcks -= i;
        if (this.pendingOperationAcks == 0) {
            if (playbackInfo2.startPositionUs == -9223372036854775807L) {
                playbackInfo2 = playbackInfo2.fromNewPosition(playbackInfo2.periodId, 0, playbackInfo2.contentPositionUs);
            }
            PlaybackInfo playbackInfo3 = playbackInfo2;
            if ((!this.playbackInfo.timeline.isEmpty() || this.hasPendingPrepare) && playbackInfo3.timeline.isEmpty()) {
                this.maskingPeriodIndex = 0;
                this.maskingWindowIndex = 0;
                this.maskingWindowPositionMs = 0;
            }
            int i3 = this.hasPendingPrepare ? 0 : 2;
            boolean z2 = this.hasPendingSeek;
            this.hasPendingPrepare = false;
            this.hasPendingSeek = false;
            updatePlaybackInfo(playbackInfo3, z, i2, i3, z2);
        }
    }

    private PlaybackInfo getResetPlaybackInfo(boolean z, boolean z2, int i) {
        if (z) {
            this.maskingWindowIndex = 0;
            this.maskingPeriodIndex = 0;
            this.maskingWindowPositionMs = 0;
        } else {
            this.maskingWindowIndex = getCurrentWindowIndex();
            this.maskingPeriodIndex = getCurrentPeriodIndex();
            this.maskingWindowPositionMs = getCurrentPosition();
        }
        PlaybackInfo playbackInfo2 = new PlaybackInfo(z2 ? Timeline.EMPTY : this.playbackInfo.timeline, z2 ? null : this.playbackInfo.manifest, this.playbackInfo.periodId, this.playbackInfo.startPositionUs, this.playbackInfo.contentPositionUs, i, false, z2 ? this.emptyTrackSelectorResult : this.playbackInfo.trackSelectorResult);
        return playbackInfo2;
    }

    private void updatePlaybackInfo(PlaybackInfo playbackInfo2, boolean z, int i, int i2, boolean z2) {
        boolean z3 = true;
        boolean z4 = (this.playbackInfo.timeline == playbackInfo2.timeline && this.playbackInfo.manifest == playbackInfo2.manifest) ? false : true;
        boolean z5 = this.playbackInfo.playbackState != playbackInfo2.playbackState;
        boolean z6 = this.playbackInfo.isLoading != playbackInfo2.isLoading;
        if (this.playbackInfo.trackSelectorResult == playbackInfo2.trackSelectorResult) {
            z3 = false;
        }
        this.playbackInfo = playbackInfo2;
        if (z4 || i2 == 0) {
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((EventListener) it.next()).onTimelineChanged(this.playbackInfo.timeline, this.playbackInfo.manifest, i2);
            }
        }
        if (z) {
            Iterator it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                ((EventListener) it2.next()).onPositionDiscontinuity(i);
            }
        }
        if (z3) {
            this.trackSelector.onSelectionActivated(this.playbackInfo.trackSelectorResult.info);
            Iterator it3 = this.listeners.iterator();
            while (it3.hasNext()) {
                ((EventListener) it3.next()).onTracksChanged(this.playbackInfo.trackSelectorResult.groups, this.playbackInfo.trackSelectorResult.selections);
            }
        }
        if (z6) {
            Iterator it4 = this.listeners.iterator();
            while (it4.hasNext()) {
                ((EventListener) it4.next()).onLoadingChanged(this.playbackInfo.isLoading);
            }
        }
        if (z5) {
            Iterator it5 = this.listeners.iterator();
            while (it5.hasNext()) {
                ((EventListener) it5.next()).onPlayerStateChanged(this.playWhenReady, this.playbackInfo.playbackState);
            }
        }
        if (z2) {
            Iterator it6 = this.listeners.iterator();
            while (it6.hasNext()) {
                ((EventListener) it6.next()).onSeekProcessed();
            }
        }
    }

    private long playbackInfoPositionUsToWindowPositionMs(long j) {
        long usToMs = C.usToMs(j);
        if (this.playbackInfo.periodId.isAd()) {
            return usToMs;
        }
        this.playbackInfo.timeline.getPeriod(this.playbackInfo.periodId.periodIndex, this.period);
        return usToMs + this.period.getPositionInWindowMs();
    }

    private boolean shouldMaskPosition() {
        return this.playbackInfo.timeline.isEmpty() || this.pendingOperationAcks > 0;
    }
}
