package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.DefaultMediaClock.PlaybackParameterListener;
import com.google.android.exoplayer2.PlayerMessage.Sender;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSource.Listener;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector.InvalidationListener;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

final class ExoPlayerImplInternal implements Callback, PlaybackParameterListener, Sender, MediaPeriod.Callback, Listener, InvalidationListener {
    private final long backBufferDurationUs;
    private final Clock clock;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private Renderer[] enabledRenderers;
    private final Handler eventHandler;
    private final HandlerWrapper handler;
    private final HandlerThread internalPlaybackThread;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private MediaSource mediaSource;
    private int nextPendingMessageIndex;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private int pendingPrepareCount;
    private final Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private final PlaybackInfoUpdate playbackInfoUpdate;
    private final ExoPlayer player;
    private final MediaPeriodQueue queue = new MediaPeriodQueue();
    private boolean rebuffering;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private int repeatMode;
    private final boolean retainBackBufferFromKeyframe;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Window window;

    private static final class MediaSourceRefreshInfo {
        public final Object manifest;
        public final MediaSource source;
        public final Timeline timeline;

        public MediaSourceRefreshInfo(MediaSource mediaSource, Timeline timeline2, Object obj) {
            this.source = mediaSource;
            this.timeline = timeline2;
            this.manifest = obj;
        }
    }

    private static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.message = playerMessage;
        }

        public void setResolvedPosition(int i, long j, Object obj) {
            this.resolvedPeriodIndex = i;
            this.resolvedPeriodTimeUs = j;
            this.resolvedPeriodUid = obj;
        }

        public int compareTo(PendingMessageInfo pendingMessageInfo) {
            int i = 1;
            if ((this.resolvedPeriodUid == null) != (pendingMessageInfo.resolvedPeriodUid == null)) {
                if (this.resolvedPeriodUid != null) {
                    i = -1;
                }
                return i;
            } else if (this.resolvedPeriodUid == null) {
                return 0;
            } else {
                int i2 = this.resolvedPeriodIndex - pendingMessageInfo.resolvedPeriodIndex;
                if (i2 != 0) {
                    return i2;
                }
                return Util.compareLong(this.resolvedPeriodTimeUs, pendingMessageInfo.resolvedPeriodTimeUs);
            }
        }
    }

    private static final class PlaybackInfoUpdate {
        /* access modifiers changed from: private */
        public int discontinuityReason;
        private PlaybackInfo lastPlaybackInfo;
        /* access modifiers changed from: private */
        public int operationAcks;
        /* access modifiers changed from: private */
        public boolean positionDiscontinuity;

        private PlaybackInfoUpdate() {
        }

        public boolean hasPendingUpdate(PlaybackInfo playbackInfo) {
            return playbackInfo != this.lastPlaybackInfo || this.operationAcks > 0 || this.positionDiscontinuity;
        }

        public void reset(PlaybackInfo playbackInfo) {
            this.lastPlaybackInfo = playbackInfo;
            this.operationAcks = 0;
            this.positionDiscontinuity = false;
        }

        public void incrementPendingOperationAcks(int i) {
            this.operationAcks += i;
        }

        public void setPositionDiscontinuity(int i) {
            boolean z = true;
            if (!this.positionDiscontinuity || this.discontinuityReason == 4) {
                this.positionDiscontinuity = true;
                this.discontinuityReason = i;
                return;
            }
            if (i != 4) {
                z = false;
            }
            Assertions.checkArgument(z);
        }
    }

    private static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline2, int i, long j) {
            this.timeline = timeline2;
            this.windowIndex = i;
            this.windowPositionUs = j;
        }
    }

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector2, TrackSelectorResult trackSelectorResult, LoadControl loadControl2, boolean z, int i, boolean z2, Handler handler2, ExoPlayer exoPlayer, Clock clock2) {
        this.renderers = rendererArr;
        this.trackSelector = trackSelector2;
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.loadControl = loadControl2;
        this.playWhenReady = z;
        this.repeatMode = i;
        this.shuffleModeEnabled = z2;
        this.eventHandler = handler2;
        this.player = exoPlayer;
        this.clock = clock2;
        this.backBufferDurationUs = loadControl2.getBackBufferDurationUs();
        this.retainBackBufferFromKeyframe = loadControl2.retainBackBufferFromKeyframe();
        this.seekParameters = SeekParameters.DEFAULT;
        this.playbackInfo = new PlaybackInfo(Timeline.EMPTY, -9223372036854775807L, trackSelectorResult);
        this.playbackInfoUpdate = new PlaybackInfoUpdate();
        this.rendererCapabilities = new RendererCapabilities[rendererArr.length];
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            rendererArr[i2].setIndex(i2);
            this.rendererCapabilities[i2] = rendererArr[i2].getCapabilities();
        }
        this.mediaClock = new DefaultMediaClock(this, clock2);
        this.pendingMessages = new ArrayList<>();
        this.enabledRenderers = new Renderer[0];
        this.window = new Window();
        this.period = new Period();
        trackSelector2.init(this);
        this.internalPlaybackThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.internalPlaybackThread.start();
        this.handler = clock2.createHandler(this.internalPlaybackThread.getLooper(), this);
    }

    public void prepare(MediaSource mediaSource2, boolean z, boolean z2) {
        this.handler.obtainMessage(0, z ? 1 : 0, z2 ? 1 : 0, mediaSource2).sendToTarget();
    }

    public void setPlayWhenReady(boolean z) {
        this.handler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
    }

    public void seekTo(Timeline timeline, int i, long j) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, i, j)).sendToTarget();
    }

    public synchronized void sendMessage(PlayerMessage playerMessage) {
        if (this.released) {
            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            playerMessage.markAsProcessed(false);
            return;
        }
        this.handler.obtainMessage(14, playerMessage).sendToTarget();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void release() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.released     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            com.google.android.exoplayer2.util.HandlerWrapper r0 = r2.handler     // Catch:{ all -> 0x0023 }
            r1 = 7
            r0.sendEmptyMessage(r1)     // Catch:{ all -> 0x0023 }
            r0 = 0
        L_0x000e:
            boolean r1 = r2.released     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x0018
            r2.wait()     // Catch:{ InterruptedException -> 0x0016 }
            goto L_0x000e
        L_0x0016:
            r0 = 1
            goto L_0x000e
        L_0x0018:
            if (r0 == 0) goto L_0x0021
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0023 }
            r0.interrupt()     // Catch:{ all -> 0x0023 }
        L_0x0021:
            monitor-exit(r2)
            return
        L_0x0023:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.release():void");
    }

    public Looper getPlaybackLooper() {
        return this.internalPlaybackThread.getLooper();
    }

    public void onSourceInfoRefreshed(MediaSource mediaSource2, Timeline timeline, Object obj) {
        this.handler.obtainMessage(8, new MediaSourceRefreshInfo(mediaSource2, timeline, obj)).sendToTarget();
    }

    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(10, mediaPeriod).sendToTarget();
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        this.eventHandler.obtainMessage(1, playbackParameters).sendToTarget();
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
    }

    public boolean handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    prepareInternal((MediaSource) message.obj, message.arg1 != 0, message.arg2 != 0);
                    break;
                case 1:
                    setPlayWhenReadyInternal(message.arg1 != 0);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) message.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) message.obj);
                    break;
                case 6:
                    stopInternal(message.arg1 != 0, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handleSourceInfoRefreshed((MediaSourceRefreshInfo) message.obj);
                    break;
                case 9:
                    handlePeriodPrepared((MediaPeriod) message.obj);
                    break;
                case 10:
                    handleContinueLoadingRequested((MediaPeriod) message.obj);
                    break;
                case 11:
                    reselectTracksInternal();
                    break;
                case 12:
                    setRepeatModeInternal(message.arg1);
                    break;
                case 13:
                    setShuffleModeEnabledInternal(message.arg1 != 0);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) message.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) message.obj);
                    break;
                default:
                    return false;
            }
            maybeNotifyPlaybackInfoChanged();
        } catch (ExoPlaybackException e) {
            Log.e("ExoPlayerImplInternal", "Playback error.", e);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, e).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        } catch (IOException e2) {
            Log.e("ExoPlayerImplInternal", "Source error.", e2);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, ExoPlaybackException.createForSource(e2)).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        } catch (RuntimeException e3) {
            Log.e("ExoPlayerImplInternal", "Internal runtime error.", e3);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, ExoPlaybackException.createForUnexpected(e3)).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        }
        return true;
    }

    private void setState(int i) {
        if (this.playbackInfo.playbackState != i) {
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(i);
        }
    }

    private void setIsLoading(boolean z) {
        if (this.playbackInfo.isLoading != z) {
            this.playbackInfo = this.playbackInfo.copyWithIsLoading(z);
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        if (this.playbackInfoUpdate.hasPendingUpdate(this.playbackInfo)) {
            this.eventHandler.obtainMessage(0, this.playbackInfoUpdate.operationAcks, this.playbackInfoUpdate.positionDiscontinuity ? this.playbackInfoUpdate.discontinuityReason : -1, this.playbackInfo).sendToTarget();
            this.playbackInfoUpdate.reset(this.playbackInfo);
        }
    }

    private void prepareInternal(MediaSource mediaSource2, boolean z, boolean z2) {
        this.pendingPrepareCount++;
        resetInternal(true, z, z2);
        this.loadControl.onPrepared();
        this.mediaSource = mediaSource2;
        setState(2);
        mediaSource2.prepareSource(this.player, true, this);
        this.handler.sendEmptyMessage(2);
    }

    private void setPlayWhenReadyInternal(boolean z) throws ExoPlaybackException {
        this.rebuffering = false;
        this.playWhenReady = z;
        if (!z) {
            stopRenderers();
            updatePlaybackPositions();
        } else if (this.playbackInfo.playbackState == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (this.playbackInfo.playbackState == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setRepeatModeInternal(int i) throws ExoPlaybackException {
        this.repeatMode = i;
        if (!this.queue.updateRepeatMode(i)) {
            seekToCurrentPosition(true);
        }
    }

    private void setShuffleModeEnabledInternal(boolean z) throws ExoPlaybackException {
        this.shuffleModeEnabled = z;
        if (!this.queue.updateShuffleModeEnabled(z)) {
            seekToCurrentPosition(true);
        }
    }

    private void seekToCurrentPosition(boolean z) throws ExoPlaybackException {
        MediaPeriodId mediaPeriodId = this.queue.getPlayingPeriod().info.id;
        long seekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs, true);
        if (seekToPeriodPosition != this.playbackInfo.positionUs) {
            this.playbackInfo = this.playbackInfo.fromNewPosition(mediaPeriodId, seekToPeriodPosition, this.playbackInfo.contentPositionUs);
            if (z) {
                this.playbackInfoUpdate.setPositionDiscontinuity(4);
            }
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        this.rebuffering = false;
        this.mediaClock.start();
        for (Renderer start : this.enabledRenderers) {
            start.start();
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.mediaClock.stop();
        for (Renderer ensureStopped : this.enabledRenderers) {
            ensureStopped(ensureStopped);
        }
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        long j;
        if (this.queue.hasPlayingPeriod()) {
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            long readDiscontinuity = playingPeriod.mediaPeriod.readDiscontinuity();
            if (readDiscontinuity != -9223372036854775807L) {
                resetRendererPosition(readDiscontinuity);
                if (readDiscontinuity != this.playbackInfo.positionUs) {
                    this.playbackInfo = this.playbackInfo.fromNewPosition(this.playbackInfo.periodId, readDiscontinuity, this.playbackInfo.contentPositionUs);
                    this.playbackInfoUpdate.setPositionDiscontinuity(4);
                }
            } else {
                this.rendererPositionUs = this.mediaClock.syncAndGetPositionUs();
                long periodTime = playingPeriod.toPeriodTime(this.rendererPositionUs);
                maybeTriggerPendingMessages(this.playbackInfo.positionUs, periodTime);
                this.playbackInfo.positionUs = periodTime;
            }
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            if (this.enabledRenderers.length == 0) {
                j = playingPeriod.info.durationUs;
            } else {
                j = playingPeriod.getBufferedPositionUs(true);
            }
            playbackInfo2.bufferedPositionUs = j;
        }
    }

    private void doSomeWork() throws ExoPlaybackException, IOException {
        Renderer[] rendererArr;
        long uptimeMillis = this.clock.uptimeMillis();
        updatePeriods();
        if (!this.queue.hasPlayingPeriod()) {
            maybeThrowPeriodPrepareError();
            scheduleNextWork(uptimeMillis, 10);
            return;
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        TraceUtil.beginSection("doSomeWork");
        updatePlaybackPositions();
        long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
        playingPeriod.mediaPeriod.discardBuffer(this.playbackInfo.positionUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
        boolean z = true;
        boolean z2 = true;
        for (Renderer renderer : this.enabledRenderers) {
            renderer.render(this.rendererPositionUs, elapsedRealtime);
            z = z && renderer.isEnded();
            boolean z3 = renderer.isReady() || renderer.isEnded() || rendererWaitingForNextStream(renderer);
            if (!z3) {
                renderer.maybeThrowStreamError();
            }
            z2 = z2 && z3;
        }
        if (!z2) {
            maybeThrowPeriodPrepareError();
        }
        long j = playingPeriod.info.durationUs;
        if (z && ((j == -9223372036854775807L || j <= this.playbackInfo.positionUs) && playingPeriod.info.isFinal)) {
            setState(4);
            stopRenderers();
        } else if (this.playbackInfo.playbackState == 2 && shouldTransitionToReadyState(z2)) {
            setState(3);
            if (this.playWhenReady) {
                startRenderers();
            }
        } else if (this.playbackInfo.playbackState == 3 && (this.enabledRenderers.length != 0 ? !z2 : !isTimelineReady())) {
            this.rebuffering = this.playWhenReady;
            setState(2);
            stopRenderers();
        }
        if (this.playbackInfo.playbackState == 2) {
            for (Renderer maybeThrowStreamError : this.enabledRenderers) {
                maybeThrowStreamError.maybeThrowStreamError();
            }
        }
        if ((this.playWhenReady && this.playbackInfo.playbackState == 3) || this.playbackInfo.playbackState == 2) {
            scheduleNextWork(uptimeMillis, 10);
        } else if (this.enabledRenderers.length == 0 || this.playbackInfo.playbackState == 4) {
            this.handler.removeMessages(2);
        } else {
            scheduleNextWork(uptimeMillis, 1000);
        }
        TraceUtil.endSection();
    }

    private void scheduleNextWork(long j, long j2) {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessageAtTime(2, j + j2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void seekToInternal(com.google.android.exoplayer2.ExoPlayerImplInternal.SeekPosition r24) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r23 = this;
            r1 = r23
            r2 = r24
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r3 = r1.playbackInfoUpdate
            r4 = 1
            r3.incrementPendingOperationAcks(r4)
            android.util.Pair r3 = r1.resolveSeekPosition(r2, r4)
            r7 = 0
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r3 != 0) goto L_0x0025
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r3 = new com.google.android.exoplayer2.source.MediaSource$MediaPeriodId
            int r10 = r23.getFirstPeriodIndex()
            r3.<init>(r10)
            r10 = r3
            r13 = r8
            r19 = r13
            r3 = 1
            goto L_0x005a
        L_0x0025:
            java.lang.Object r10 = r3.first
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            java.lang.Object r11 = r3.second
            java.lang.Long r11 = (java.lang.Long) r11
            long r11 = r11.longValue()
            com.google.android.exoplayer2.MediaPeriodQueue r13 = r1.queue
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r10 = r13.resolveMediaPeriodIdForAds(r10, r11)
            boolean r13 = r10.isAd()
            if (r13 == 0) goto L_0x0047
            r19 = r11
            r3 = 1
            r13 = 0
            goto L_0x005a
        L_0x0047:
            java.lang.Object r3 = r3.second
            java.lang.Long r3 = (java.lang.Long) r3
            long r13 = r3.longValue()
            long r5 = r2.windowPositionUs
            int r3 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r3 != 0) goto L_0x0057
            r3 = 1
            goto L_0x0058
        L_0x0057:
            r3 = 0
        L_0x0058:
            r19 = r11
        L_0x005a:
            r5 = 2
            com.google.android.exoplayer2.source.MediaSource r6 = r1.mediaSource     // Catch:{ all -> 0x00e7 }
            if (r6 == 0) goto L_0x00d0
            int r6 = r1.pendingPrepareCount     // Catch:{ all -> 0x00e7 }
            if (r6 <= 0) goto L_0x0065
            goto L_0x00d0
        L_0x0065:
            int r2 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x0071
            r2 = 4
            r1.setState(r2)     // Catch:{ all -> 0x00e7 }
            r1.resetInternal(r7, r4, r7)     // Catch:{ all -> 0x00e7 }
            goto L_0x00d2
        L_0x0071:
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.playbackInfo     // Catch:{ all -> 0x00e7 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r2.periodId     // Catch:{ all -> 0x00e7 }
            boolean r2 = r10.equals(r2)     // Catch:{ all -> 0x00e7 }
            if (r2 == 0) goto L_0x00c1
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r1.queue     // Catch:{ all -> 0x00e7 }
            com.google.android.exoplayer2.MediaPeriodHolder r2 = r2.getPlayingPeriod()     // Catch:{ all -> 0x00e7 }
            if (r2 == 0) goto L_0x0092
            r8 = 0
            int r6 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x0092
            com.google.android.exoplayer2.source.MediaPeriod r2 = r2.mediaPeriod     // Catch:{ all -> 0x00e7 }
            com.google.android.exoplayer2.SeekParameters r6 = r1.seekParameters     // Catch:{ all -> 0x00e7 }
            long r8 = r2.getAdjustedSeekPositionUs(r13, r6)     // Catch:{ all -> 0x00e7 }
            goto L_0x0093
        L_0x0092:
            r8 = r13
        L_0x0093:
            long r11 = com.google.android.exoplayer2.C.usToMs(r8)     // Catch:{ all -> 0x00e7 }
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.playbackInfo     // Catch:{ all -> 0x00e7 }
            r21 = r8
            long r7 = r2.positionUs     // Catch:{ all -> 0x00e7 }
            long r6 = com.google.android.exoplayer2.C.usToMs(r7)     // Catch:{ all -> 0x00e7 }
            int r2 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x00be
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.playbackInfo     // Catch:{ all -> 0x00e7 }
            long r6 = r2.positionUs     // Catch:{ all -> 0x00e7 }
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.playbackInfo
            r15 = r2
            r16 = r10
            r17 = r6
            com.google.android.exoplayer2.PlaybackInfo r2 = r15.fromNewPosition(r16, r17, r19)
            r1.playbackInfo = r2
            if (r3 == 0) goto L_0x00bd
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r2 = r1.playbackInfoUpdate
            r2.setPositionDiscontinuity(r5)
        L_0x00bd:
            return
        L_0x00be:
            r6 = r21
            goto L_0x00c2
        L_0x00c1:
            r6 = r13
        L_0x00c2:
            long r6 = r1.seekToPeriodPosition(r10, r6)     // Catch:{ all -> 0x00e7 }
            int r2 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x00cb
            goto L_0x00cc
        L_0x00cb:
            r4 = 0
        L_0x00cc:
            r3 = r3 | r4
            r17 = r6
            goto L_0x00d4
        L_0x00d0:
            r1.pendingInitialSeekPosition = r2     // Catch:{ all -> 0x00e7 }
        L_0x00d2:
            r17 = r13
        L_0x00d4:
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.playbackInfo
            r15 = r2
            r16 = r10
            com.google.android.exoplayer2.PlaybackInfo r2 = r15.fromNewPosition(r16, r17, r19)
            r1.playbackInfo = r2
            if (r3 == 0) goto L_0x00e6
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r2 = r1.playbackInfoUpdate
            r2.setPositionDiscontinuity(r5)
        L_0x00e6:
            return
        L_0x00e7:
            r0 = move-exception
            r2 = r0
            com.google.android.exoplayer2.PlaybackInfo r4 = r1.playbackInfo
            r15 = r4
            r16 = r10
            r17 = r13
            com.google.android.exoplayer2.PlaybackInfo r4 = r15.fromNewPosition(r16, r17, r19)
            r1.playbackInfo = r4
            if (r3 == 0) goto L_0x00fd
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r3 = r1.playbackInfoUpdate
            r3.setPositionDiscontinuity(r5)
        L_0x00fd:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.seekToInternal(com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition):void");
    }

    private long seekToPeriodPosition(MediaPeriodId mediaPeriodId, long j) throws ExoPlaybackException {
        return seekToPeriodPosition(mediaPeriodId, j, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod());
    }

    private long seekToPeriodPosition(MediaPeriodId mediaPeriodId, long j, boolean z) throws ExoPlaybackException {
        stopRenderers();
        this.rebuffering = false;
        setState(2);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder mediaPeriodHolder = playingPeriod;
        while (true) {
            if (mediaPeriodHolder == null) {
                break;
            } else if (shouldKeepPeriodHolder(mediaPeriodId, j, mediaPeriodHolder)) {
                this.queue.removeAfter(mediaPeriodHolder);
                break;
            } else {
                mediaPeriodHolder = this.queue.advancePlayingPeriod();
            }
        }
        if (playingPeriod != mediaPeriodHolder || z) {
            for (Renderer disableRenderer : this.enabledRenderers) {
                disableRenderer(disableRenderer);
            }
            this.enabledRenderers = new Renderer[0];
            playingPeriod = null;
        }
        if (mediaPeriodHolder != null) {
            updatePlayingPeriodRenderers(playingPeriod);
            if (mediaPeriodHolder.hasEnabledTracks) {
                j = mediaPeriodHolder.mediaPeriod.seekToUs(j);
                mediaPeriodHolder.mediaPeriod.discardBuffer(j - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            }
            resetRendererPosition(j);
            maybeContinueLoading();
        } else {
            this.queue.clear();
            resetRendererPosition(j);
        }
        this.handler.sendEmptyMessage(2);
        return j;
    }

    private boolean shouldKeepPeriodHolder(MediaPeriodId mediaPeriodId, long j, MediaPeriodHolder mediaPeriodHolder) {
        if (mediaPeriodId.equals(mediaPeriodHolder.info.id) && mediaPeriodHolder.prepared) {
            this.playbackInfo.timeline.getPeriod(mediaPeriodHolder.info.id.periodIndex, this.period);
            int adGroupIndexAfterPositionUs = this.period.getAdGroupIndexAfterPositionUs(j);
            if (adGroupIndexAfterPositionUs == -1 || this.period.getAdGroupTimeUs(adGroupIndexAfterPositionUs) == mediaPeriodHolder.info.endPositionUs) {
                return true;
            }
        }
        return false;
    }

    private void resetRendererPosition(long j) throws ExoPlaybackException {
        long j2;
        if (!this.queue.hasPlayingPeriod()) {
            j2 = j + 60000000;
        } else {
            j2 = this.queue.getPlayingPeriod().toRendererTime(j);
        }
        this.rendererPositionUs = j2;
        this.mediaClock.resetPosition(this.rendererPositionUs);
        for (Renderer resetPosition : this.enabledRenderers) {
            resetPosition.resetPosition(this.rendererPositionUs);
        }
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) {
        this.mediaClock.setPlaybackParameters(playbackParameters);
    }

    private void setSeekParametersInternal(SeekParameters seekParameters2) {
        this.seekParameters = seekParameters2;
    }

    private void stopInternal(boolean z, boolean z2) {
        resetInternal(true, z, z);
        this.playbackInfoUpdate.incrementPendingOperationAcks(this.pendingPrepareCount + (z2 ? 1 : 0));
        this.pendingPrepareCount = 0;
        this.loadControl.onStopped();
        setState(1);
    }

    private void releaseInternal() {
        resetInternal(true, true, true);
        this.loadControl.onReleased();
        setState(1);
        this.internalPlaybackThread.quit();
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    private int getFirstPeriodIndex() {
        Timeline timeline = this.playbackInfo.timeline;
        if (timeline.isEmpty()) {
            return 0;
        }
        return timeline.getWindow(timeline.getFirstWindowIndex(this.shuffleModeEnabled), this.window).firstPeriodIndex;
    }

    private void resetInternal(boolean z, boolean z2, boolean z3) {
        this.handler.removeMessages(2);
        this.rebuffering = false;
        this.mediaClock.stop();
        this.rendererPositionUs = 60000000;
        for (Renderer disableRenderer : this.enabledRenderers) {
            try {
                disableRenderer(disableRenderer);
            } catch (ExoPlaybackException | RuntimeException e) {
                Log.e("ExoPlayerImplInternal", "Stop failed.", e);
            }
        }
        this.enabledRenderers = new Renderer[0];
        this.queue.clear();
        setIsLoading(false);
        if (z2) {
            this.pendingInitialSeekPosition = null;
        }
        if (z3) {
            this.queue.setTimeline(Timeline.EMPTY);
            Iterator it = this.pendingMessages.iterator();
            while (it.hasNext()) {
                ((PendingMessageInfo) it.next()).message.markAsProcessed(false);
            }
            this.pendingMessages.clear();
            this.nextPendingMessageIndex = 0;
        }
        Timeline timeline = z3 ? Timeline.EMPTY : this.playbackInfo.timeline;
        Object obj = z3 ? null : this.playbackInfo.manifest;
        MediaPeriodId mediaPeriodId = z2 ? new MediaPeriodId(getFirstPeriodIndex()) : this.playbackInfo.periodId;
        long j = -9223372036854775807L;
        long j2 = z2 ? -9223372036854775807L : this.playbackInfo.positionUs;
        if (!z2) {
            j = this.playbackInfo.contentPositionUs;
        }
        PlaybackInfo playbackInfo2 = new PlaybackInfo(timeline, obj, mediaPeriodId, j2, j, this.playbackInfo.playbackState, false, z3 ? this.emptyTrackSelectorResult : this.playbackInfo.trackSelectorResult);
        this.playbackInfo = playbackInfo2;
        if (z && this.mediaSource != null) {
            this.mediaSource.releaseSource();
            this.mediaSource = null;
        }
    }

    private void sendMessageInternal(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getPositionMs() == -9223372036854775807L) {
            sendMessageToTarget(playerMessage);
        } else if (this.mediaSource == null || this.pendingPrepareCount > 0) {
            this.pendingMessages.add(new PendingMessageInfo(playerMessage));
        } else {
            PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(playerMessage);
            if (resolvePendingMessagePosition(pendingMessageInfo)) {
                this.pendingMessages.add(pendingMessageInfo);
                Collections.sort(this.pendingMessages);
                return;
            }
            playerMessage.markAsProcessed(false);
        }
    }

    private void sendMessageToTarget(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getHandler().getLooper() == this.handler.getLooper()) {
            deliverMessage(playerMessage);
            if (this.playbackInfo.playbackState == 3 || this.playbackInfo.playbackState == 2) {
                this.handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        this.handler.obtainMessage(15, playerMessage).sendToTarget();
    }

    private void sendMessageToTargetThread(final PlayerMessage playerMessage) {
        playerMessage.getHandler().post(new Runnable() {
            public void run() {
                try {
                    ExoPlayerImplInternal.this.deliverMessage(playerMessage);
                } catch (ExoPlaybackException e) {
                    Log.e("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", e);
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void deliverMessage(PlayerMessage playerMessage) throws ExoPlaybackException {
        try {
            playerMessage.getTarget().handleMessage(playerMessage.getType(), playerMessage.getPayload());
        } finally {
            playerMessage.markAsProcessed(true);
        }
    }

    private void resolvePendingMessagePositions() {
        for (int size = this.pendingMessages.size() - 1; size >= 0; size--) {
            if (!resolvePendingMessagePosition((PendingMessageInfo) this.pendingMessages.get(size))) {
                ((PendingMessageInfo) this.pendingMessages.get(size)).message.markAsProcessed(false);
                this.pendingMessages.remove(size);
            }
        }
        Collections.sort(this.pendingMessages);
    }

    private boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo) {
        if (pendingMessageInfo.resolvedPeriodUid == null) {
            Pair resolveSeekPosition = resolveSeekPosition(new SeekPosition(pendingMessageInfo.message.getTimeline(), pendingMessageInfo.message.getWindowIndex(), C.msToUs(pendingMessageInfo.message.getPositionMs())), false);
            if (resolveSeekPosition == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(((Integer) resolveSeekPosition.first).intValue(), ((Long) resolveSeekPosition.second).longValue(), this.playbackInfo.timeline.getPeriod(((Integer) resolveSeekPosition.first).intValue(), this.period, true).uid);
        } else {
            int indexOfPeriod = this.playbackInfo.timeline.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid);
            if (indexOfPeriod == -1) {
                return false;
            }
            pendingMessageInfo.resolvedPeriodIndex = indexOfPeriod;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00a6 A[ADDED_TO_REGION, EDGE_INSN: B:62:0x00a6->B:75:0x00a6 ?: BREAK  
    EDGE_INSN: B:62:0x00a6->B:75:0x00a6 ?: BREAK  
    EDGE_INSN: B:62:0x00a6->B:75:0x00a6 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void maybeTriggerPendingMessages(long r7, long r9) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r6 = this;
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r0 = r6.pendingMessages
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00ef
            com.google.android.exoplayer2.PlaybackInfo r0 = r6.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.periodId
            boolean r0 = r0.isAd()
            if (r0 == 0) goto L_0x0014
            goto L_0x00ef
        L_0x0014:
            com.google.android.exoplayer2.PlaybackInfo r0 = r6.playbackInfo
            long r0 = r0.startPositionUs
            int r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0021
            r0 = 1
            long r2 = r7 - r0
            r7 = r2
        L_0x0021:
            com.google.android.exoplayer2.PlaybackInfo r0 = r6.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.periodId
            int r0 = r0.periodIndex
            int r1 = r6.nextPendingMessageIndex
            r2 = 0
            if (r1 <= 0) goto L_0x0039
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r1 = r6.pendingMessages
            int r3 = r6.nextPendingMessageIndex
            int r3 = r3 + -1
            java.lang.Object r1 = r1.get(r3)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x003a
        L_0x0039:
            r1 = r2
        L_0x003a:
            if (r1 == 0) goto L_0x0061
            int r3 = r1.resolvedPeriodIndex
            if (r3 > r0) goto L_0x004a
            int r3 = r1.resolvedPeriodIndex
            if (r3 != r0) goto L_0x0061
            long r3 = r1.resolvedPeriodTimeUs
            int r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r1 <= 0) goto L_0x0061
        L_0x004a:
            int r1 = r6.nextPendingMessageIndex
            int r1 = r1 + -1
            r6.nextPendingMessageIndex = r1
            int r1 = r6.nextPendingMessageIndex
            if (r1 <= 0) goto L_0x0039
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r1 = r6.pendingMessages
            int r3 = r6.nextPendingMessageIndex
            int r3 = r3 + -1
            java.lang.Object r1 = r1.get(r3)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x003a
        L_0x0061:
            int r1 = r6.nextPendingMessageIndex
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x0076
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r1 = r6.pendingMessages
            int r3 = r6.nextPendingMessageIndex
            java.lang.Object r1 = r1.get(r3)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x0077
        L_0x0076:
            r1 = r2
        L_0x0077:
            if (r1 == 0) goto L_0x00a6
            java.lang.Object r3 = r1.resolvedPeriodUid
            if (r3 == 0) goto L_0x00a6
            int r3 = r1.resolvedPeriodIndex
            if (r3 < r0) goto L_0x008b
            int r3 = r1.resolvedPeriodIndex
            if (r3 != r0) goto L_0x00a6
            long r3 = r1.resolvedPeriodTimeUs
            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r5 > 0) goto L_0x00a6
        L_0x008b:
            int r1 = r6.nextPendingMessageIndex
            int r1 = r1 + 1
            r6.nextPendingMessageIndex = r1
            int r1 = r6.nextPendingMessageIndex
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x0076
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r1 = r6.pendingMessages
            int r3 = r6.nextPendingMessageIndex
            java.lang.Object r1 = r1.get(r3)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x0077
        L_0x00a6:
            if (r1 == 0) goto L_0x00ee
            java.lang.Object r3 = r1.resolvedPeriodUid
            if (r3 == 0) goto L_0x00ee
            int r3 = r1.resolvedPeriodIndex
            if (r3 != r0) goto L_0x00ee
            long r3 = r1.resolvedPeriodTimeUs
            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x00ee
            long r3 = r1.resolvedPeriodTimeUs
            int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r5 > 0) goto L_0x00ee
            com.google.android.exoplayer2.PlayerMessage r3 = r1.message
            r6.sendMessageToTarget(r3)
            com.google.android.exoplayer2.PlayerMessage r1 = r1.message
            boolean r1 = r1.getDeleteAfterDelivery()
            if (r1 == 0) goto L_0x00d1
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r1 = r6.pendingMessages
            int r3 = r6.nextPendingMessageIndex
            r1.remove(r3)
            goto L_0x00d7
        L_0x00d1:
            int r1 = r6.nextPendingMessageIndex
            int r1 = r1 + 1
            r6.nextPendingMessageIndex = r1
        L_0x00d7:
            int r1 = r6.nextPendingMessageIndex
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x00ec
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r1 = r6.pendingMessages
            int r3 = r6.nextPendingMessageIndex
            java.lang.Object r1 = r1.get(r3)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x00a6
        L_0x00ec:
            r1 = r2
            goto L_0x00a6
        L_0x00ee:
            return
        L_0x00ef:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.maybeTriggerPendingMessages(long, long):void");
    }

    private void ensureStopped(Renderer renderer) throws ExoPlaybackException {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private void disableRenderer(Renderer renderer) throws ExoPlaybackException {
        this.mediaClock.onRendererDisabled(renderer);
        ensureStopped(renderer);
        renderer.disable();
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            float f = this.mediaClock.getPlaybackParameters().speed;
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
            boolean z = true;
            while (playingPeriod != null && playingPeriod.prepared) {
                if (playingPeriod.selectTracks(f)) {
                    if (z) {
                        MediaPeriodHolder playingPeriod2 = this.queue.getPlayingPeriod();
                        boolean[] zArr = new boolean[this.renderers.length];
                        long applyTrackSelection = playingPeriod2.applyTrackSelection(this.playbackInfo.positionUs, this.queue.removeAfter(playingPeriod2), zArr);
                        updateLoadControlTrackSelection(playingPeriod2.trackSelectorResult);
                        if (!(this.playbackInfo.playbackState == 4 || applyTrackSelection == this.playbackInfo.positionUs)) {
                            this.playbackInfo = this.playbackInfo.fromNewPosition(this.playbackInfo.periodId, applyTrackSelection, this.playbackInfo.contentPositionUs);
                            this.playbackInfoUpdate.setPositionDiscontinuity(4);
                            resetRendererPosition(applyTrackSelection);
                        }
                        boolean[] zArr2 = new boolean[this.renderers.length];
                        int i = 0;
                        for (int i2 = 0; i2 < this.renderers.length; i2++) {
                            Renderer renderer = this.renderers[i2];
                            zArr2[i2] = renderer.getState() != 0;
                            SampleStream sampleStream = playingPeriod2.sampleStreams[i2];
                            if (sampleStream != null) {
                                i++;
                            }
                            if (zArr2[i2]) {
                                if (sampleStream != renderer.getStream()) {
                                    disableRenderer(renderer);
                                } else if (zArr[i2]) {
                                    renderer.resetPosition(this.rendererPositionUs);
                                }
                            }
                        }
                        this.playbackInfo = this.playbackInfo.copyWithTrackSelectorResult(playingPeriod2.trackSelectorResult);
                        enableRenderers(zArr2, i);
                    } else {
                        this.queue.removeAfter(playingPeriod);
                        if (playingPeriod.prepared) {
                            playingPeriod.applyTrackSelection(Math.max(playingPeriod.info.startPositionUs, playingPeriod.toPeriodTime(this.rendererPositionUs)), false);
                            updateLoadControlTrackSelection(playingPeriod.trackSelectorResult);
                        }
                    }
                    if (this.playbackInfo.playbackState != 4) {
                        maybeContinueLoading();
                        updatePlaybackPositions();
                        this.handler.sendEmptyMessage(2);
                    }
                    return;
                }
                if (playingPeriod == readingPeriod) {
                    z = false;
                }
                playingPeriod = playingPeriod.next;
            }
        }
    }

    private void updateLoadControlTrackSelection(TrackSelectorResult trackSelectorResult) {
        this.loadControl.onTracksSelected(this.renderers, trackSelectorResult.groups, trackSelectorResult.selections);
    }

    private void updateTrackSelectionPlaybackSpeed(float f) {
        TrackSelection[] all;
        for (MediaPeriodHolder frontPeriod = this.queue.getFrontPeriod(); frontPeriod != null; frontPeriod = frontPeriod.next) {
            if (frontPeriod.trackSelectorResult != null) {
                for (TrackSelection trackSelection : frontPeriod.trackSelectorResult.selections.getAll()) {
                    if (trackSelection != null) {
                        trackSelection.onPlaybackSpeed(f);
                    }
                }
            }
        }
    }

    private boolean shouldTransitionToReadyState(boolean z) {
        if (this.enabledRenderers.length == 0) {
            return isTimelineReady();
        }
        boolean z2 = false;
        if (!z) {
            return false;
        }
        if (!this.playbackInfo.isLoading) {
            return true;
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        long bufferedPositionUs = loadingPeriod.getBufferedPositionUs(!loadingPeriod.info.isFinal);
        if (bufferedPositionUs == Long.MIN_VALUE || this.loadControl.shouldStartPlayback(bufferedPositionUs - loadingPeriod.toPeriodTime(this.rendererPositionUs), this.mediaClock.getPlaybackParameters().speed, this.rebuffering)) {
            z2 = true;
        }
        return z2;
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long j = playingPeriod.info.durationUs;
        return j == -9223372036854775807L || this.playbackInfo.positionUs < j || (playingPeriod.next != null && (playingPeriod.next.prepared || playingPeriod.next.info.id.isAd()));
    }

    private void maybeThrowPeriodPrepareError() throws IOException {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (loadingPeriod != null && !loadingPeriod.prepared && (readingPeriod == null || readingPeriod.next == loadingPeriod)) {
            Renderer[] rendererArr = this.enabledRenderers;
            int length = rendererArr.length;
            int i = 0;
            while (i < length) {
                if (rendererArr[i].hasReadStreamToEnd()) {
                    i++;
                } else {
                    return;
                }
            }
            loadingPeriod.mediaPeriod.maybeThrowPrepareError();
        }
    }

    private void handleSourceInfoRefreshed(MediaSourceRefreshInfo mediaSourceRefreshInfo) throws ExoPlaybackException {
        MediaSourceRefreshInfo mediaSourceRefreshInfo2 = mediaSourceRefreshInfo;
        if (mediaSourceRefreshInfo2.source == this.mediaSource) {
            Timeline timeline = this.playbackInfo.timeline;
            Timeline timeline2 = mediaSourceRefreshInfo2.timeline;
            Object obj = mediaSourceRefreshInfo2.manifest;
            this.queue.setTimeline(timeline2);
            this.playbackInfo = this.playbackInfo.copyWithTimeline(timeline2, obj);
            resolvePendingMessagePositions();
            long j = 0;
            if (this.pendingPrepareCount > 0) {
                this.playbackInfoUpdate.incrementPendingOperationAcks(this.pendingPrepareCount);
                this.pendingPrepareCount = 0;
                if (this.pendingInitialSeekPosition != null) {
                    Pair resolveSeekPosition = resolveSeekPosition(this.pendingInitialSeekPosition, true);
                    this.pendingInitialSeekPosition = null;
                    if (resolveSeekPosition == null) {
                        handleSourceInfoRefreshEndedPlayback();
                    } else {
                        int intValue = ((Integer) resolveSeekPosition.first).intValue();
                        long longValue = ((Long) resolveSeekPosition.second).longValue();
                        MediaPeriodId resolveMediaPeriodIdForAds = this.queue.resolveMediaPeriodIdForAds(intValue, longValue);
                        this.playbackInfo = this.playbackInfo.fromNewPosition(resolveMediaPeriodIdForAds, resolveMediaPeriodIdForAds.isAd() ? 0 : longValue, longValue);
                    }
                } else if (this.playbackInfo.startPositionUs == -9223372036854775807L) {
                    if (timeline2.isEmpty()) {
                        handleSourceInfoRefreshEndedPlayback();
                    } else {
                        Pair periodPosition = getPeriodPosition(timeline2, timeline2.getFirstWindowIndex(this.shuffleModeEnabled), -9223372036854775807L);
                        int intValue2 = ((Integer) periodPosition.first).intValue();
                        long longValue2 = ((Long) periodPosition.second).longValue();
                        MediaPeriodId resolveMediaPeriodIdForAds2 = this.queue.resolveMediaPeriodIdForAds(intValue2, longValue2);
                        this.playbackInfo = this.playbackInfo.fromNewPosition(resolveMediaPeriodIdForAds2, resolveMediaPeriodIdForAds2.isAd() ? 0 : longValue2, longValue2);
                    }
                }
                return;
            }
            int i = this.playbackInfo.periodId.periodIndex;
            long j2 = this.playbackInfo.contentPositionUs;
            if (timeline.isEmpty()) {
                if (!timeline2.isEmpty()) {
                    MediaPeriodId resolveMediaPeriodIdForAds3 = this.queue.resolveMediaPeriodIdForAds(i, j2);
                    PlaybackInfo playbackInfo2 = this.playbackInfo;
                    if (!resolveMediaPeriodIdForAds3.isAd()) {
                        j = j2;
                    }
                    this.playbackInfo = playbackInfo2.fromNewPosition(resolveMediaPeriodIdForAds3, j, j2);
                }
                return;
            }
            MediaPeriodHolder frontPeriod = this.queue.getFrontPeriod();
            int indexOfPeriod = timeline2.getIndexOfPeriod(frontPeriod == null ? timeline.getPeriod(i, this.period, true).uid : frontPeriod.uid);
            if (indexOfPeriod == -1) {
                int resolveSubsequentPeriod = resolveSubsequentPeriod(i, timeline, timeline2);
                if (resolveSubsequentPeriod == -1) {
                    handleSourceInfoRefreshEndedPlayback();
                    return;
                }
                Pair periodPosition2 = getPeriodPosition(timeline2, timeline2.getPeriod(resolveSubsequentPeriod, this.period).windowIndex, -9223372036854775807L);
                int intValue3 = ((Integer) periodPosition2.first).intValue();
                long longValue3 = ((Long) periodPosition2.second).longValue();
                MediaPeriodId resolveMediaPeriodIdForAds4 = this.queue.resolveMediaPeriodIdForAds(intValue3, longValue3);
                timeline2.getPeriod(intValue3, this.period, true);
                if (frontPeriod != null) {
                    Object obj2 = this.period.uid;
                    frontPeriod.info = frontPeriod.info.copyWithPeriodIndex(-1);
                    while (frontPeriod.next != null) {
                        frontPeriod = frontPeriod.next;
                        if (frontPeriod.uid.equals(obj2)) {
                            frontPeriod.info = this.queue.getUpdatedMediaPeriodInfo(frontPeriod.info, intValue3);
                        } else {
                            frontPeriod.info = frontPeriod.info.copyWithPeriodIndex(-1);
                        }
                    }
                }
                if (!resolveMediaPeriodIdForAds4.isAd()) {
                    j = longValue3;
                }
                this.playbackInfo = this.playbackInfo.fromNewPosition(resolveMediaPeriodIdForAds4, seekToPeriodPosition(resolveMediaPeriodIdForAds4, j), longValue3);
                return;
            }
            if (indexOfPeriod != i) {
                this.playbackInfo = this.playbackInfo.copyWithPeriodIndex(indexOfPeriod);
            }
            MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
            if (mediaPeriodId.isAd()) {
                MediaPeriodId resolveMediaPeriodIdForAds5 = this.queue.resolveMediaPeriodIdForAds(indexOfPeriod, j2);
                if (!resolveMediaPeriodIdForAds5.equals(mediaPeriodId)) {
                    if (!resolveMediaPeriodIdForAds5.isAd()) {
                        j = j2;
                    }
                    this.playbackInfo = this.playbackInfo.fromNewPosition(resolveMediaPeriodIdForAds5, seekToPeriodPosition(resolveMediaPeriodIdForAds5, j), j2);
                    return;
                }
            }
            if (!this.queue.updateQueuedPeriods(mediaPeriodId, this.rendererPositionUs)) {
                seekToCurrentPosition(false);
            }
        }
    }

    private void handleSourceInfoRefreshEndedPlayback() {
        setState(4);
        resetInternal(false, true, false);
    }

    private int resolveSubsequentPeriod(int i, Timeline timeline, Timeline timeline2) {
        int periodCount = timeline.getPeriodCount();
        int i2 = i;
        int i3 = -1;
        for (int i4 = 0; i4 < periodCount && i3 == -1; i4++) {
            i2 = timeline.getNextPeriodIndex(i2, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if (i2 == -1) {
                break;
            }
            i3 = timeline2.getIndexOfPeriod(timeline.getPeriod(i2, this.period, true).uid);
        }
        return i3;
    }

    private Pair<Integer, Long> resolveSeekPosition(SeekPosition seekPosition, boolean z) {
        Timeline timeline = this.playbackInfo.timeline;
        Timeline timeline2 = seekPosition.timeline;
        if (timeline.isEmpty()) {
            return null;
        }
        if (timeline2.isEmpty()) {
            timeline2 = timeline;
        }
        try {
            Pair<Integer, Long> periodPosition = timeline2.getPeriodPosition(this.window, this.period, seekPosition.windowIndex, seekPosition.windowPositionUs);
            if (timeline == timeline2) {
                return periodPosition;
            }
            int indexOfPeriod = timeline.getIndexOfPeriod(timeline2.getPeriod(((Integer) periodPosition.first).intValue(), this.period, true).uid);
            if (indexOfPeriod != -1) {
                return Pair.create(Integer.valueOf(indexOfPeriod), periodPosition.second);
            }
            if (z) {
                int resolveSubsequentPeriod = resolveSubsequentPeriod(((Integer) periodPosition.first).intValue(), timeline2, timeline);
                if (resolveSubsequentPeriod != -1) {
                    return getPeriodPosition(timeline, timeline.getPeriod(resolveSubsequentPeriod, this.period).windowIndex, -9223372036854775807L);
                }
            }
            return null;
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalSeekPositionException(timeline, seekPosition.windowIndex, seekPosition.windowPositionUs);
        }
    }

    private Pair<Integer, Long> getPeriodPosition(Timeline timeline, int i, long j) {
        return timeline.getPeriodPosition(this.window, this.period, i, j);
    }

    private void updatePeriods() throws ExoPlaybackException, IOException {
        if (this.mediaSource != null) {
            if (this.pendingPrepareCount > 0) {
                this.mediaSource.maybeThrowSourceInfoRefreshError();
                return;
            }
            maybeUpdateLoadingPeriod();
            MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
            if (loadingPeriod == null || loadingPeriod.isFullyBuffered()) {
                setIsLoading(false);
            } else if (!this.playbackInfo.isLoading) {
                maybeContinueLoading();
            }
            if (this.queue.hasPlayingPeriod()) {
                MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
                MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
                boolean z = false;
                while (this.playWhenReady && playingPeriod != readingPeriod && this.rendererPositionUs >= playingPeriod.next.rendererPositionOffsetUs) {
                    if (z) {
                        maybeNotifyPlaybackInfoChanged();
                    }
                    int i = playingPeriod.info.isLastInTimelinePeriod ? 0 : 3;
                    MediaPeriodHolder advancePlayingPeriod = this.queue.advancePlayingPeriod();
                    updatePlayingPeriodRenderers(playingPeriod);
                    this.playbackInfo = this.playbackInfo.fromNewPosition(advancePlayingPeriod.info.id, advancePlayingPeriod.info.startPositionUs, advancePlayingPeriod.info.contentPositionUs);
                    this.playbackInfoUpdate.setPositionDiscontinuity(i);
                    updatePlaybackPositions();
                    playingPeriod = advancePlayingPeriod;
                    z = true;
                }
                if (readingPeriod.info.isFinal) {
                    for (int i2 = 0; i2 < this.renderers.length; i2++) {
                        Renderer renderer = this.renderers[i2];
                        SampleStream sampleStream = readingPeriod.sampleStreams[i2];
                        if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                            renderer.setCurrentStreamFinal();
                        }
                    }
                } else if (readingPeriod.next != null && readingPeriod.next.prepared) {
                    int i3 = 0;
                    while (i3 < this.renderers.length) {
                        Renderer renderer2 = this.renderers[i3];
                        SampleStream sampleStream2 = readingPeriod.sampleStreams[i3];
                        if (renderer2.getStream() == sampleStream2 && (sampleStream2 == null || renderer2.hasReadStreamToEnd())) {
                            i3++;
                        } else {
                            return;
                        }
                    }
                    TrackSelectorResult trackSelectorResult = readingPeriod.trackSelectorResult;
                    MediaPeriodHolder advanceReadingPeriod = this.queue.advanceReadingPeriod();
                    TrackSelectorResult trackSelectorResult2 = advanceReadingPeriod.trackSelectorResult;
                    boolean z2 = advanceReadingPeriod.mediaPeriod.readDiscontinuity() != -9223372036854775807L;
                    for (int i4 = 0; i4 < this.renderers.length; i4++) {
                        Renderer renderer3 = this.renderers[i4];
                        if (trackSelectorResult.renderersEnabled[i4]) {
                            if (z2) {
                                renderer3.setCurrentStreamFinal();
                            } else if (!renderer3.isCurrentStreamFinal()) {
                                TrackSelection trackSelection = trackSelectorResult2.selections.get(i4);
                                boolean z3 = trackSelectorResult2.renderersEnabled[i4];
                                boolean z4 = this.rendererCapabilities[i4].getTrackType() == 5;
                                RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i4];
                                RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i4];
                                if (!z3 || !rendererConfiguration2.equals(rendererConfiguration) || z4) {
                                    renderer3.setCurrentStreamFinal();
                                } else {
                                    renderer3.replaceStream(getFormats(trackSelection), advanceReadingPeriod.sampleStreams[i4], advanceReadingPeriod.getRendererOffset());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void maybeUpdateLoadingPeriod() throws IOException {
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        if (this.queue.shouldLoadNextMediaPeriod()) {
            MediaPeriodInfo nextMediaPeriodInfo = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo);
            if (nextMediaPeriodInfo == null) {
                this.mediaSource.maybeThrowSourceInfoRefreshError();
                return;
            }
            this.queue.enqueueNextMediaPeriod(this.rendererCapabilities, 60000000, this.trackSelector, this.loadControl.getAllocator(), this.mediaSource, this.playbackInfo.timeline.getPeriod(nextMediaPeriodInfo.id.periodIndex, this.period, true).uid, nextMediaPeriodInfo).prepare(this, nextMediaPeriodInfo.startPositionUs);
            setIsLoading(true);
        }
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (this.queue.isLoading(mediaPeriod)) {
            updateLoadControlTrackSelection(this.queue.handleLoadingPeriodPrepared(this.mediaClock.getPlaybackParameters().speed));
            if (!this.queue.hasPlayingPeriod()) {
                resetRendererPosition(this.queue.advancePlayingPeriod().info.startPositionUs);
                updatePlayingPeriodRenderers(null);
            }
            maybeContinueLoading();
        }
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            this.queue.reevaluateBuffer(this.rendererPositionUs);
            maybeContinueLoading();
        }
    }

    private void maybeContinueLoading() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        long nextLoadPositionUs = loadingPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            setIsLoading(false);
            return;
        }
        boolean shouldContinueLoading = this.loadControl.shouldContinueLoading(nextLoadPositionUs - loadingPeriod.toPeriodTime(this.rendererPositionUs), this.mediaClock.getPlaybackParameters().speed);
        setIsLoading(shouldContinueLoading);
        if (shouldContinueLoading) {
            loadingPeriod.continueLoading(this.rendererPositionUs);
        }
    }

    private void updatePlayingPeriodRenderers(MediaPeriodHolder mediaPeriodHolder) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null && mediaPeriodHolder != playingPeriod) {
            boolean[] zArr = new boolean[this.renderers.length];
            int i = 0;
            for (int i2 = 0; i2 < this.renderers.length; i2++) {
                Renderer renderer = this.renderers[i2];
                zArr[i2] = renderer.getState() != 0;
                if (playingPeriod.trackSelectorResult.renderersEnabled[i2]) {
                    i++;
                }
                if (zArr[i2] && (!playingPeriod.trackSelectorResult.renderersEnabled[i2] || (renderer.isCurrentStreamFinal() && renderer.getStream() == mediaPeriodHolder.sampleStreams[i2]))) {
                    disableRenderer(renderer);
                }
            }
            this.playbackInfo = this.playbackInfo.copyWithTrackSelectorResult(playingPeriod.trackSelectorResult);
            enableRenderers(zArr, i);
        }
    }

    private void enableRenderers(boolean[] zArr, int i) throws ExoPlaybackException {
        this.enabledRenderers = new Renderer[i];
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        int i2 = 0;
        for (int i3 = 0; i3 < this.renderers.length; i3++) {
            if (playingPeriod.trackSelectorResult.renderersEnabled[i3]) {
                int i4 = i2 + 1;
                enableRenderer(i3, zArr[i3], i2);
                i2 = i4;
            }
        }
    }

    private void enableRenderer(int i, boolean z, int i2) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        Renderer renderer = this.renderers[i];
        this.enabledRenderers[i2] = renderer;
        if (renderer.getState() == 0) {
            RendererConfiguration rendererConfiguration = playingPeriod.trackSelectorResult.rendererConfigurations[i];
            Format[] formats = getFormats(playingPeriod.trackSelectorResult.selections.get(i));
            boolean z2 = this.playWhenReady && this.playbackInfo.playbackState == 3;
            renderer.enable(rendererConfiguration, formats, playingPeriod.sampleStreams[i], this.rendererPositionUs, !z && z2, playingPeriod.getRendererOffset());
            this.mediaClock.onRendererEnabled(renderer);
            if (z2) {
                renderer.start();
            }
        }
    }

    private boolean rendererWaitingForNextStream(Renderer renderer) {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        return readingPeriod.next != null && readingPeriod.next.prepared && renderer.hasReadStreamToEnd();
    }

    private static Format[] getFormats(TrackSelection trackSelection) {
        int length = trackSelection != null ? trackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        for (int i = 0; i < length; i++) {
            formatArr[i] = trackSelection.getFormat(i);
        }
        return formatArr;
    }
}
