package com.google.android.exoplayer2.mediacodec;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodec.CodecException;
import android.media.MediaCodec.CryptoException;
import android.media.MediaCodec.CryptoInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@TargetApi(16)
public abstract class MediaCodecRenderer extends BaseRenderer {
    private static final byte[] ADAPTATION_WORKAROUND_BUFFER = Util.getBytesFromHexString("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private final DecoderInputBuffer buffer;
    private MediaCodec codec;
    private int codecAdaptationWorkaroundMode;
    private long codecHotswapDeadlineMs;
    private MediaCodecInfo codecInfo;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsDiscardToSpsWorkaround;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosOutputExceptionWorkaround;
    private boolean codecNeedsEosPropagationWorkaround;
    private boolean codecNeedsFlushWorkaround;
    private boolean codecNeedsMonoChannelCountWorkaround;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private int codecReinitializationState;
    private final List<Long> decodeOnlyPresentationTimestamps;
    protected DecoderCounters decoderCounters;
    private DrmSession<FrameworkMediaCrypto> drmSession;
    private final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private Format format;
    private final FormatHolder formatHolder;
    private ByteBuffer[] inputBuffers;
    private int inputIndex;
    private boolean inputStreamEnded;
    private final MediaCodecSelector mediaCodecSelector;
    private ByteBuffer outputBuffer;
    private final BufferInfo outputBufferInfo;
    private ByteBuffer[] outputBuffers;
    private int outputIndex;
    private boolean outputStreamEnded;
    private DrmSession<FrameworkMediaCrypto> pendingDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    private boolean shouldSkipOutputBuffer;
    private boolean waitingForFirstSyncFrame;
    private boolean waitingForKeys;

    public static class DecoderInitializationException extends Exception {
        public final String decoderName;
        public final String diagnosticInfo;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format format, Throwable th, boolean z, int i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Decoder init failed: [");
            sb.append(i);
            sb.append("], ");
            sb.append(format);
            super(sb.toString(), th);
            this.mimeType = format.sampleMimeType;
            this.secureDecoderRequired = z;
            this.decoderName = null;
            this.diagnosticInfo = buildCustomDiagnosticInfo(i);
        }

        public DecoderInitializationException(Format format, Throwable th, boolean z, String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("Decoder init failed: ");
            sb.append(str);
            sb.append(", ");
            sb.append(format);
            super(sb.toString(), th);
            this.mimeType = format.sampleMimeType;
            this.secureDecoderRequired = z;
            this.decoderName = str;
            this.diagnosticInfo = Util.SDK_INT >= 21 ? getDiagnosticInfoV21(th) : null;
        }

        @TargetApi(21)
        private static String getDiagnosticInfoV21(Throwable th) {
            if (th instanceof CodecException) {
                return ((CodecException) th).getDiagnosticInfo();
            }
            return null;
        }

        private static String buildCustomDiagnosticInfo(int i) {
            String str = i < 0 ? "neg_" : "";
            StringBuilder sb = new StringBuilder();
            sb.append("com.google.android.exoplayer.MediaCodecTrackRenderer_");
            sb.append(str);
            sb.append(Math.abs(i));
            return sb.toString();
        }
    }

    /* access modifiers changed from: protected */
    public boolean canReconfigureCodec(MediaCodec mediaCodec, boolean z, Format format2, Format format3) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format2, MediaCrypto mediaCrypto) throws DecoderQueryException;

    /* access modifiers changed from: protected */
    public long getDequeueOutputBufferTimeoutUs() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onCodecInitialized(String str, long j, long j2) {
    }

    /* access modifiers changed from: protected */
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void onProcessedOutputBuffer(long j) {
    }

    /* access modifiers changed from: protected */
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws ExoPlaybackException;

    /* access modifiers changed from: protected */
    public void renderToEndOfStream() throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract int supportsFormat(MediaCodecSelector mediaCodecSelector2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, Format format2) throws DecoderQueryException;

    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    public MediaCodecRenderer(int i, MediaCodecSelector mediaCodecSelector2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, boolean z) {
        super(i);
        Assertions.checkState(Util.SDK_INT >= 16);
        this.mediaCodecSelector = (MediaCodecSelector) Assertions.checkNotNull(mediaCodecSelector2);
        this.drmSessionManager = drmSessionManager2;
        this.playClearSamplesWithoutKeys = z;
        this.buffer = new DecoderInputBuffer(0);
        this.flagsOnlyBuffer = DecoderInputBuffer.newFlagsOnlyInstance();
        this.formatHolder = new FormatHolder();
        this.decodeOnlyPresentationTimestamps = new ArrayList();
        this.outputBufferInfo = new BufferInfo();
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
    }

    public final int supportsFormat(Format format2) throws ExoPlaybackException {
        try {
            return supportsFormat(this.mediaCodecSelector, this.drmSessionManager, format2);
        } catch (DecoderQueryException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    /* access modifiers changed from: protected */
    public MediaCodecInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector2, Format format2, boolean z) throws DecoderQueryException {
        return mediaCodecSelector2.getDecoderInfo(format2.sampleMimeType, z);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b A[SYNTHETIC, Splitter:B:15:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void maybeInitCodec() throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r11 = this;
            android.media.MediaCodec r0 = r11.codec
            if (r0 != 0) goto L_0x015d
            com.google.android.exoplayer2.Format r0 = r11.format
            if (r0 != 0) goto L_0x000a
            goto L_0x015d
        L_0x000a:
            com.google.android.exoplayer2.drm.DrmSession<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r0 = r11.pendingDrmSession
            r11.drmSession = r0
            com.google.android.exoplayer2.Format r0 = r11.format
            java.lang.String r0 = r0.sampleMimeType
            com.google.android.exoplayer2.drm.DrmSession<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r1 = r11.drmSession
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L_0x0035
            com.google.android.exoplayer2.drm.DrmSession<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r1 = r11.drmSession
            com.google.android.exoplayer2.drm.ExoMediaCrypto r1 = r1.getMediaCrypto()
            com.google.android.exoplayer2.drm.FrameworkMediaCrypto r1 = (com.google.android.exoplayer2.drm.FrameworkMediaCrypto) r1
            if (r1 != 0) goto L_0x002c
            com.google.android.exoplayer2.drm.DrmSession<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r1 = r11.drmSession
            com.google.android.exoplayer2.drm.DrmSession$DrmSessionException r1 = r1.getError()
            if (r1 == 0) goto L_0x002b
            goto L_0x0035
        L_0x002b:
            return
        L_0x002c:
            android.media.MediaCrypto r4 = r1.getWrappedMediaCrypto()
            boolean r1 = r1.requiresSecureDecoderComponent(r0)
            goto L_0x0037
        L_0x0035:
            r4 = r3
            r1 = 0
        L_0x0037:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5 = r11.codecInfo
            if (r5 != 0) goto L_0x00a0
            com.google.android.exoplayer2.mediacodec.MediaCodecSelector r5 = r11.mediaCodecSelector     // Catch:{ DecoderQueryException -> 0x0081 }
            com.google.android.exoplayer2.Format r6 = r11.format     // Catch:{ DecoderQueryException -> 0x0081 }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5 = r11.getDecoderInfo(r5, r6, r1)     // Catch:{ DecoderQueryException -> 0x0081 }
            r11.codecInfo = r5     // Catch:{ DecoderQueryException -> 0x0081 }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5 = r11.codecInfo     // Catch:{ DecoderQueryException -> 0x0081 }
            if (r5 != 0) goto L_0x008f
            if (r1 == 0) goto L_0x008f
            com.google.android.exoplayer2.mediacodec.MediaCodecSelector r5 = r11.mediaCodecSelector     // Catch:{ DecoderQueryException -> 0x0081 }
            com.google.android.exoplayer2.Format r6 = r11.format     // Catch:{ DecoderQueryException -> 0x0081 }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r2 = r11.getDecoderInfo(r5, r6, r2)     // Catch:{ DecoderQueryException -> 0x0081 }
            r11.codecInfo = r2     // Catch:{ DecoderQueryException -> 0x0081 }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r2 = r11.codecInfo     // Catch:{ DecoderQueryException -> 0x0081 }
            if (r2 == 0) goto L_0x008f
            java.lang.String r2 = "MediaCodecRenderer"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ DecoderQueryException -> 0x0081 }
            r5.<init>()     // Catch:{ DecoderQueryException -> 0x0081 }
            java.lang.String r6 = "Drm session requires secure decoder for "
            r5.append(r6)     // Catch:{ DecoderQueryException -> 0x0081 }
            r5.append(r0)     // Catch:{ DecoderQueryException -> 0x0081 }
            java.lang.String r0 = ", but no secure decoder available. Trying to proceed with "
            r5.append(r0)     // Catch:{ DecoderQueryException -> 0x0081 }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = r11.codecInfo     // Catch:{ DecoderQueryException -> 0x0081 }
            java.lang.String r0 = r0.name     // Catch:{ DecoderQueryException -> 0x0081 }
            r5.append(r0)     // Catch:{ DecoderQueryException -> 0x0081 }
            java.lang.String r0 = "."
            r5.append(r0)     // Catch:{ DecoderQueryException -> 0x0081 }
            java.lang.String r0 = r5.toString()     // Catch:{ DecoderQueryException -> 0x0081 }
            android.util.Log.w(r2, r0)     // Catch:{ DecoderQueryException -> 0x0081 }
            goto L_0x008f
        L_0x0081:
            r0 = move-exception
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r2 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r5 = r11.format
            r6 = -49998(0xffffffffffff3cb2, float:NaN)
            r2.<init>(r5, r0, r1, r6)
            r11.throwDecoderInitError(r2)
        L_0x008f:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = r11.codecInfo
            if (r0 != 0) goto L_0x00a0
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r0 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r2 = r11.format
            r5 = -49999(0xffffffffffff3cb1, float:NaN)
            r0.<init>(r2, r3, r1, r5)
            r11.throwDecoderInitError(r0)
        L_0x00a0:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = r11.codecInfo
            boolean r0 = r11.shouldInitCodec(r0)
            if (r0 != 0) goto L_0x00a9
            return
        L_0x00a9:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = r11.codecInfo
            java.lang.String r0 = r0.name
            int r2 = r11.codecAdaptationWorkaroundMode(r0)
            r11.codecAdaptationWorkaroundMode = r2
            com.google.android.exoplayer2.Format r2 = r11.format
            boolean r2 = codecNeedsDiscardToSpsWorkaround(r0, r2)
            r11.codecNeedsDiscardToSpsWorkaround = r2
            boolean r2 = codecNeedsFlushWorkaround(r0)
            r11.codecNeedsFlushWorkaround = r2
            boolean r2 = codecNeedsEosPropagationWorkaround(r0)
            r11.codecNeedsEosPropagationWorkaround = r2
            boolean r2 = codecNeedsEosFlushWorkaround(r0)
            r11.codecNeedsEosFlushWorkaround = r2
            boolean r2 = codecNeedsEosOutputExceptionWorkaround(r0)
            r11.codecNeedsEosOutputExceptionWorkaround = r2
            com.google.android.exoplayer2.Format r2 = r11.format
            boolean r2 = codecNeedsMonoChannelCountWorkaround(r0, r2)
            r11.codecNeedsMonoChannelCountWorkaround = r2
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x012a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012a }
            r5.<init>()     // Catch:{ Exception -> 0x012a }
            java.lang.String r6 = "createCodec:"
            r5.append(r6)     // Catch:{ Exception -> 0x012a }
            r5.append(r0)     // Catch:{ Exception -> 0x012a }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x012a }
            com.google.android.exoplayer2.util.TraceUtil.beginSection(r5)     // Catch:{ Exception -> 0x012a }
            android.media.MediaCodec r5 = android.media.MediaCodec.createByCodecName(r0)     // Catch:{ Exception -> 0x012a }
            r11.codec = r5     // Catch:{ Exception -> 0x012a }
            com.google.android.exoplayer2.util.TraceUtil.endSection()     // Catch:{ Exception -> 0x012a }
            java.lang.String r5 = "configureCodec"
            com.google.android.exoplayer2.util.TraceUtil.beginSection(r5)     // Catch:{ Exception -> 0x012a }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5 = r11.codecInfo     // Catch:{ Exception -> 0x012a }
            android.media.MediaCodec r6 = r11.codec     // Catch:{ Exception -> 0x012a }
            com.google.android.exoplayer2.Format r7 = r11.format     // Catch:{ Exception -> 0x012a }
            r11.configureCodec(r5, r6, r7, r4)     // Catch:{ Exception -> 0x012a }
            com.google.android.exoplayer2.util.TraceUtil.endSection()     // Catch:{ Exception -> 0x012a }
            java.lang.String r4 = "startCodec"
            com.google.android.exoplayer2.util.TraceUtil.beginSection(r4)     // Catch:{ Exception -> 0x012a }
            android.media.MediaCodec r4 = r11.codec     // Catch:{ Exception -> 0x012a }
            r4.start()     // Catch:{ Exception -> 0x012a }
            com.google.android.exoplayer2.util.TraceUtil.endSection()     // Catch:{ Exception -> 0x012a }
            long r7 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x012a }
            r4 = 0
            long r9 = r7 - r2
            r5 = r11
            r6 = r0
            r5.onCodecInitialized(r6, r7, r9)     // Catch:{ Exception -> 0x012a }
            r11.getCodecBuffers()     // Catch:{ Exception -> 0x012a }
            goto L_0x0135
        L_0x012a:
            r2 = move-exception
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r3 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r4 = r11.format
            r3.<init>(r4, r2, r1, r0)
            r11.throwDecoderInitError(r3)
        L_0x0135:
            int r0 = r11.getState()
            r1 = 2
            if (r0 != r1) goto L_0x0145
            long r0 = android.os.SystemClock.elapsedRealtime()
            r2 = 1000(0x3e8, double:4.94E-321)
            long r4 = r0 + r2
            goto L_0x014a
        L_0x0145:
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x014a:
            r11.codecHotswapDeadlineMs = r4
            r11.resetInputBuffer()
            r11.resetOutputBuffer()
            r0 = 1
            r11.waitingForFirstSyncFrame = r0
            com.google.android.exoplayer2.decoder.DecoderCounters r1 = r11.decoderCounters
            int r2 = r1.decoderInitCount
            int r2 = r2 + r0
            r1.decoderInitCount = r2
            return
        L_0x015d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.maybeInitCodec():void");
    }

    private void throwDecoderInitError(DecoderInitializationException decoderInitializationException) throws ExoPlaybackException {
        throw ExoPlaybackException.createForRenderer(decoderInitializationException, getIndex());
    }

    /* access modifiers changed from: protected */
    public final MediaCodec getCodec() {
        return this.codec;
    }

    /* access modifiers changed from: protected */
    public final MediaCodecInfo getCodecInfo() {
        return this.codecInfo;
    }

    /* access modifiers changed from: protected */
    public final MediaFormat getMediaFormatForPlayback(Format format2) {
        MediaFormat frameworkMediaFormatV16 = format2.getFrameworkMediaFormatV16();
        if (Util.SDK_INT >= 23) {
            configureMediaFormatForPlaybackV23(frameworkMediaFormatV16);
        }
        return frameworkMediaFormatV16;
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z) throws ExoPlaybackException {
        this.decoderCounters = new DecoderCounters();
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if (this.codec != null) {
            flushCodec();
        }
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.format = null;
        try {
            releaseCodec();
            try {
                if (this.drmSession != null) {
                    this.drmSessionManager.releaseSession(this.drmSession);
                }
                try {
                    if (!(this.pendingDrmSession == null || this.pendingDrmSession == this.drmSession)) {
                        this.drmSessionManager.releaseSession(this.pendingDrmSession);
                    }
                } finally {
                    this.drmSession = null;
                    this.pendingDrmSession = null;
                }
            } catch (Throwable th) {
                if (!(this.pendingDrmSession == null || this.pendingDrmSession == this.drmSession)) {
                    this.drmSessionManager.releaseSession(this.pendingDrmSession);
                }
                throw th;
            } finally {
                this.drmSession = null;
                this.pendingDrmSession = null;
            }
        } catch (Throwable th2) {
            try {
                if (!(this.pendingDrmSession == null || this.pendingDrmSession == this.drmSession)) {
                    this.drmSessionManager.releaseSession(this.pendingDrmSession);
                }
                throw th2;
            } finally {
                this.drmSession = null;
                this.pendingDrmSession = null;
            }
        } finally {
            this.drmSession = null;
            this.pendingDrmSession = null;
        }
    }

    /* access modifiers changed from: protected */
    public void releaseCodec() {
        this.codecHotswapDeadlineMs = -9223372036854775807L;
        resetInputBuffer();
        resetOutputBuffer();
        this.waitingForKeys = false;
        this.shouldSkipOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        resetCodecBuffers();
        this.codecInfo = null;
        this.codecReconfigured = false;
        this.codecReceivedBuffers = false;
        this.codecNeedsDiscardToSpsWorkaround = false;
        this.codecNeedsFlushWorkaround = false;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecNeedsEosPropagationWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsMonoChannelCountWorkaround = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.codecReceivedEos = false;
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
        if (this.codec != null) {
            this.decoderCounters.decoderReleaseCount++;
            try {
                this.codec.stop();
                try {
                    this.codec.release();
                    this.codec = null;
                    if (this.drmSession != null && this.pendingDrmSession != this.drmSession) {
                        try {
                            this.drmSessionManager.releaseSession(this.drmSession);
                        } finally {
                            this.drmSession = null;
                        }
                    }
                } catch (Throwable th) {
                    this.codec = null;
                    if (!(this.drmSession == null || this.pendingDrmSession == this.drmSession)) {
                        this.drmSessionManager.releaseSession(this.drmSession);
                    }
                    throw th;
                } finally {
                    this.drmSession = null;
                }
            } catch (Throwable th2) {
                this.codec = null;
                if (!(this.drmSession == null || this.pendingDrmSession == this.drmSession)) {
                    try {
                        this.drmSessionManager.releaseSession(this.drmSession);
                    } finally {
                        this.drmSession = null;
                    }
                }
                throw th2;
            } finally {
                this.drmSession = null;
            }
        }
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        if (this.outputStreamEnded) {
            renderToEndOfStream();
            return;
        }
        if (this.format == null) {
            this.flagsOnlyBuffer.clear();
            int readSource = readSource(this.formatHolder, this.flagsOnlyBuffer, true);
            if (readSource == -5) {
                onInputFormatChanged(this.formatHolder.format);
            } else if (readSource == -4) {
                Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                this.inputStreamEnded = true;
                processEndOfStream();
                return;
            } else {
                return;
            }
        }
        maybeInitCodec();
        if (this.codec != null) {
            TraceUtil.beginSection("drainAndFeed");
            do {
            } while (drainOutputBuffer(j, j2));
            do {
            } while (feedInputBuffer());
            TraceUtil.endSection();
        } else {
            this.decoderCounters.skippedInputBufferCount += skipSource(j);
            this.flagsOnlyBuffer.clear();
            int readSource2 = readSource(this.formatHolder, this.flagsOnlyBuffer, false);
            if (readSource2 == -5) {
                onInputFormatChanged(this.formatHolder.format);
            } else if (readSource2 == -4) {
                Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                this.inputStreamEnded = true;
                processEndOfStream();
            }
        }
        this.decoderCounters.ensureUpdated();
    }

    /* access modifiers changed from: protected */
    public void flushCodec() throws ExoPlaybackException {
        this.codecHotswapDeadlineMs = -9223372036854775807L;
        resetInputBuffer();
        resetOutputBuffer();
        this.waitingForFirstSyncFrame = true;
        this.waitingForKeys = false;
        this.shouldSkipOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        if (this.codecNeedsFlushWorkaround || (this.codecNeedsEosFlushWorkaround && this.codecReceivedEos)) {
            releaseCodec();
            maybeInitCodec();
        } else if (this.codecReinitializationState != 0) {
            releaseCodec();
            maybeInitCodec();
        } else {
            this.codec.flush();
            this.codecReceivedBuffers = false;
        }
        if (this.codecReconfigured && this.format != null) {
            this.codecReconfigurationState = 1;
        }
    }

    private boolean feedInputBuffer() throws ExoPlaybackException {
        int i;
        int i2;
        if (this.codec == null || this.codecReinitializationState == 2 || this.inputStreamEnded) {
            return false;
        }
        if (this.inputIndex < 0) {
            this.inputIndex = this.codec.dequeueInputBuffer(0);
            if (this.inputIndex < 0) {
                return false;
            }
            this.buffer.data = getInputBuffer(this.inputIndex);
            this.buffer.clear();
        }
        if (this.codecReinitializationState == 1) {
            if (!this.codecNeedsEosPropagationWorkaround) {
                this.codecReceivedEos = true;
                this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
                resetInputBuffer();
            }
            this.codecReinitializationState = 2;
            return false;
        } else if (this.codecNeedsAdaptationWorkaroundBuffer) {
            this.codecNeedsAdaptationWorkaroundBuffer = false;
            this.buffer.data.put(ADAPTATION_WORKAROUND_BUFFER);
            this.codec.queueInputBuffer(this.inputIndex, 0, ADAPTATION_WORKAROUND_BUFFER.length, 0, 0);
            resetInputBuffer();
            this.codecReceivedBuffers = true;
            return true;
        } else {
            if (this.waitingForKeys) {
                i2 = -4;
                i = 0;
            } else {
                if (this.codecReconfigurationState == 1) {
                    for (int i3 = 0; i3 < this.format.initializationData.size(); i3++) {
                        this.buffer.data.put((byte[]) this.format.initializationData.get(i3));
                    }
                    this.codecReconfigurationState = 2;
                }
                int position = this.buffer.data.position();
                i = position;
                i2 = readSource(this.formatHolder, this.buffer, false);
            }
            if (i2 == -3) {
                return false;
            }
            if (i2 == -5) {
                if (this.codecReconfigurationState == 2) {
                    this.buffer.clear();
                    this.codecReconfigurationState = 1;
                }
                onInputFormatChanged(this.formatHolder.format);
                return true;
            } else if (this.buffer.isEndOfStream()) {
                if (this.codecReconfigurationState == 2) {
                    this.buffer.clear();
                    this.codecReconfigurationState = 1;
                }
                this.inputStreamEnded = true;
                if (!this.codecReceivedBuffers) {
                    processEndOfStream();
                    return false;
                }
                try {
                    if (!this.codecNeedsEosPropagationWorkaround) {
                        this.codecReceivedEos = true;
                        this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
                        resetInputBuffer();
                    }
                    return false;
                } catch (CryptoException e) {
                    throw ExoPlaybackException.createForRenderer(e, getIndex());
                }
            } else if (!this.waitingForFirstSyncFrame || this.buffer.isKeyFrame()) {
                this.waitingForFirstSyncFrame = false;
                boolean isEncrypted = this.buffer.isEncrypted();
                this.waitingForKeys = shouldWaitForKeys(isEncrypted);
                if (this.waitingForKeys) {
                    return false;
                }
                if (this.codecNeedsDiscardToSpsWorkaround && !isEncrypted) {
                    NalUnitUtil.discardToSps(this.buffer.data);
                    if (this.buffer.data.position() == 0) {
                        return true;
                    }
                    this.codecNeedsDiscardToSpsWorkaround = false;
                }
                try {
                    long j = this.buffer.timeUs;
                    if (this.buffer.isDecodeOnly()) {
                        this.decodeOnlyPresentationTimestamps.add(Long.valueOf(j));
                    }
                    this.buffer.flip();
                    onQueueInputBuffer(this.buffer);
                    if (isEncrypted) {
                        this.codec.queueSecureInputBuffer(this.inputIndex, 0, getFrameworkCryptoInfo(this.buffer, i), j, 0);
                    } else {
                        this.codec.queueInputBuffer(this.inputIndex, 0, this.buffer.data.limit(), j, 0);
                    }
                    resetInputBuffer();
                    this.codecReceivedBuffers = true;
                    this.codecReconfigurationState = 0;
                    this.decoderCounters.inputBufferCount++;
                    return true;
                } catch (CryptoException e2) {
                    throw ExoPlaybackException.createForRenderer(e2, getIndex());
                }
            } else {
                this.buffer.clear();
                if (this.codecReconfigurationState == 2) {
                    this.codecReconfigurationState = 1;
                }
                return true;
            }
        }
    }

    private void getCodecBuffers() {
        if (Util.SDK_INT < 21) {
            this.inputBuffers = this.codec.getInputBuffers();
            this.outputBuffers = this.codec.getOutputBuffers();
        }
    }

    private void resetCodecBuffers() {
        if (Util.SDK_INT < 21) {
            this.inputBuffers = null;
            this.outputBuffers = null;
        }
    }

    private ByteBuffer getInputBuffer(int i) {
        if (Util.SDK_INT >= 21) {
            return this.codec.getInputBuffer(i);
        }
        return this.inputBuffers[i];
    }

    private ByteBuffer getOutputBuffer(int i) {
        if (Util.SDK_INT >= 21) {
            return this.codec.getOutputBuffer(i);
        }
        return this.outputBuffers[i];
    }

    private boolean hasOutputBuffer() {
        return this.outputIndex >= 0;
    }

    private void resetInputBuffer() {
        this.inputIndex = -1;
        this.buffer.data = null;
    }

    private void resetOutputBuffer() {
        this.outputIndex = -1;
        this.outputBuffer = null;
    }

    private static CryptoInfo getFrameworkCryptoInfo(DecoderInputBuffer decoderInputBuffer, int i) {
        CryptoInfo frameworkCryptoInfoV16 = decoderInputBuffer.cryptoInfo.getFrameworkCryptoInfoV16();
        if (i == 0) {
            return frameworkCryptoInfoV16;
        }
        if (frameworkCryptoInfoV16.numBytesOfClearData == null) {
            frameworkCryptoInfoV16.numBytesOfClearData = new int[1];
        }
        int[] iArr = frameworkCryptoInfoV16.numBytesOfClearData;
        iArr[0] = iArr[0] + i;
        return frameworkCryptoInfoV16;
    }

    private boolean shouldWaitForKeys(boolean z) throws ExoPlaybackException {
        if (this.drmSession == null || (!z && this.playClearSamplesWithoutKeys)) {
            return false;
        }
        int state = this.drmSession.getState();
        boolean z2 = true;
        if (state == 1) {
            throw ExoPlaybackException.createForRenderer(this.drmSession.getError(), getIndex());
        }
        if (state == 4) {
            z2 = false;
        }
        return z2;
    }

    /* access modifiers changed from: protected */
    public void onInputFormatChanged(Format format2) throws ExoPlaybackException {
        Format format3 = this.format;
        this.format = format2;
        boolean z = true;
        if (!Util.areEqual(this.format.drmInitData, format3 == null ? null : format3.drmInitData)) {
            if (this.format.drmInitData == null) {
                this.pendingDrmSession = null;
            } else if (this.drmSessionManager == null) {
                throw ExoPlaybackException.createForRenderer(new IllegalStateException("Media requires a DrmSessionManager"), getIndex());
            } else {
                this.pendingDrmSession = this.drmSessionManager.acquireSession(Looper.myLooper(), this.format.drmInitData);
                if (this.pendingDrmSession == this.drmSession) {
                    this.drmSessionManager.releaseSession(this.pendingDrmSession);
                }
            }
        }
        if (this.pendingDrmSession == this.drmSession && this.codec != null && canReconfigureCodec(this.codec, this.codecInfo.adaptive, format3, this.format)) {
            this.codecReconfigured = true;
            this.codecReconfigurationState = 1;
            if (!(this.codecAdaptationWorkaroundMode == 2 || (this.codecAdaptationWorkaroundMode == 1 && this.format.width == format3.width && this.format.height == format3.height))) {
                z = false;
            }
            this.codecNeedsAdaptationWorkaroundBuffer = z;
        } else if (this.codecReceivedBuffers) {
            this.codecReinitializationState = 1;
        } else {
            releaseCodec();
            maybeInitCodec();
        }
    }

    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    public boolean isReady() {
        return this.format != null && !this.waitingForKeys && (isSourceReady() || hasOutputBuffer() || (this.codecHotswapDeadlineMs != -9223372036854775807L && SystemClock.elapsedRealtime() < this.codecHotswapDeadlineMs));
    }

    private boolean drainOutputBuffer(long j, long j2) throws ExoPlaybackException {
        boolean z;
        int i;
        if (!hasOutputBuffer()) {
            if (!this.codecNeedsEosOutputExceptionWorkaround || !this.codecReceivedEos) {
                i = this.codec.dequeueOutputBuffer(this.outputBufferInfo, getDequeueOutputBufferTimeoutUs());
            } else {
                try {
                    i = this.codec.dequeueOutputBuffer(this.outputBufferInfo, getDequeueOutputBufferTimeoutUs());
                } catch (IllegalStateException unused) {
                    processEndOfStream();
                    if (this.outputStreamEnded) {
                        releaseCodec();
                    }
                    return false;
                }
            }
            if (i >= 0) {
                if (this.shouldSkipAdaptationWorkaroundOutputBuffer) {
                    this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
                    this.codec.releaseOutputBuffer(i, false);
                    return true;
                } else if ((this.outputBufferInfo.flags & 4) != 0) {
                    processEndOfStream();
                    return false;
                } else {
                    this.outputIndex = i;
                    this.outputBuffer = getOutputBuffer(i);
                    if (this.outputBuffer != null) {
                        this.outputBuffer.position(this.outputBufferInfo.offset);
                        this.outputBuffer.limit(this.outputBufferInfo.offset + this.outputBufferInfo.size);
                    }
                    this.shouldSkipOutputBuffer = shouldSkipOutputBuffer(this.outputBufferInfo.presentationTimeUs);
                }
            } else if (i == -2) {
                processOutputFormat();
                return true;
            } else if (i == -3) {
                processOutputBuffersChanged();
                return true;
            } else {
                if (this.codecNeedsEosPropagationWorkaround && (this.inputStreamEnded || this.codecReinitializationState == 2)) {
                    processEndOfStream();
                }
                return false;
            }
        }
        if (!this.codecNeedsEosOutputExceptionWorkaround || !this.codecReceivedEos) {
            z = processOutputBuffer(j, j2, this.codec, this.outputBuffer, this.outputIndex, this.outputBufferInfo.flags, this.outputBufferInfo.presentationTimeUs, this.shouldSkipOutputBuffer);
        } else {
            try {
                z = processOutputBuffer(j, j2, this.codec, this.outputBuffer, this.outputIndex, this.outputBufferInfo.flags, this.outputBufferInfo.presentationTimeUs, this.shouldSkipOutputBuffer);
            } catch (IllegalStateException unused2) {
                processEndOfStream();
                if (this.outputStreamEnded) {
                    releaseCodec();
                }
                return false;
            }
        }
        if (!z) {
            return false;
        }
        onProcessedOutputBuffer(this.outputBufferInfo.presentationTimeUs);
        resetOutputBuffer();
        return true;
    }

    private void processOutputFormat() throws ExoPlaybackException {
        MediaFormat outputFormat = this.codec.getOutputFormat();
        if (this.codecAdaptationWorkaroundMode != 0 && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
            return;
        }
        if (this.codecNeedsMonoChannelCountWorkaround) {
            outputFormat.setInteger("channel-count", 1);
        }
        onOutputFormatChanged(this.codec, outputFormat);
    }

    private void processOutputBuffersChanged() {
        if (Util.SDK_INT < 21) {
            this.outputBuffers = this.codec.getOutputBuffers();
        }
    }

    private void processEndOfStream() throws ExoPlaybackException {
        if (this.codecReinitializationState == 2) {
            releaseCodec();
            maybeInitCodec();
            return;
        }
        this.outputStreamEnded = true;
        renderToEndOfStream();
    }

    private boolean shouldSkipOutputBuffer(long j) {
        int size = this.decodeOnlyPresentationTimestamps.size();
        for (int i = 0; i < size; i++) {
            if (((Long) this.decodeOnlyPresentationTimestamps.get(i)).longValue() == j) {
                this.decodeOnlyPresentationTimestamps.remove(i);
                return true;
            }
        }
        return false;
    }

    @TargetApi(23)
    private static void configureMediaFormatForPlaybackV23(MediaFormat mediaFormat) {
        mediaFormat.setInteger("priority", 0);
    }

    private static boolean codecNeedsFlushWorkaround(String str) {
        return Util.SDK_INT < 18 || (Util.SDK_INT == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) || (Util.SDK_INT == 19 && Util.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str)));
    }

    private int codecAdaptationWorkaroundMode(String str) {
        if (Util.SDK_INT > 25 || !"OMX.Exynos.avc.dec.secure".equals(str) || (!Util.MODEL.startsWith("SM-T585") && !Util.MODEL.startsWith("SM-A510") && !Util.MODEL.startsWith("SM-A520") && !Util.MODEL.startsWith("SM-J700"))) {
            return (Util.SDK_INT >= 24 || (!"OMX.Nvidia.h264.decode".equals(str) && !"OMX.Nvidia.h264.decode.secure".equals(str)) || (!"flounder".equals(Util.DEVICE) && !"flounder_lte".equals(Util.DEVICE) && !"grouper".equals(Util.DEVICE) && !"tilapia".equals(Util.DEVICE))) ? 0 : 1;
        }
        return 2;
    }

    private static boolean codecNeedsDiscardToSpsWorkaround(String str, Format format2) {
        return Util.SDK_INT < 21 && format2.initializationData.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
    }

    private static boolean codecNeedsEosPropagationWorkaround(String str) {
        return Util.SDK_INT <= 17 && ("OMX.rk.video_decoder.avc".equals(str) || "OMX.allwinner.video.decoder.avc".equals(str));
    }

    private static boolean codecNeedsEosFlushWorkaround(String str) {
        return (Util.SDK_INT <= 23 && "OMX.google.vorbis.decoder".equals(str)) || (Util.SDK_INT <= 19 && "hb2000".equals(Util.DEVICE) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str)));
    }

    private static boolean codecNeedsEosOutputExceptionWorkaround(String str) {
        return Util.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str);
    }

    private static boolean codecNeedsMonoChannelCountWorkaround(String str, Format format2) {
        if (Util.SDK_INT > 18 || format2.channelCount != 1 || !"OMX.MTK.AUDIO.DECODER.MP3".equals(str)) {
            return false;
        }
        return true;
    }
}
