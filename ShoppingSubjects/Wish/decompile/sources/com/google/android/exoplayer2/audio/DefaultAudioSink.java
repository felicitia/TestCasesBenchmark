package com.google.android.exoplayer2.audio;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioAttributes.Builder;
import android.media.AudioFormat;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.audio.AudioSink.InitializationException;
import com.google.android.exoplayer2.audio.AudioSink.Listener;
import com.google.android.exoplayer2.audio.AudioSink.WriteException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;

public final class DefaultAudioSink implements AudioSink {
    public static boolean enablePreV21AudioSessionWorkaround = false;
    public static boolean failOnSpuriousAudioTimestamp = false;
    private AudioAttributes audioAttributes;
    private final AudioCapabilities audioCapabilities;
    private AudioProcessor[] audioProcessors;
    private int audioSessionId;
    private boolean audioTimestampSet;
    private AudioTrack audioTrack;
    private final AudioTrackUtil audioTrackUtil;
    private ByteBuffer avSyncHeader;
    private int bufferSize;
    private long bufferSizeUs;
    private int bytesUntilNextAvSync;
    private boolean canApplyPlaybackParameters;
    private int channelConfig;
    private final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    private int drainingAudioProcessorIndex;
    private PlaybackParameters drainingPlaybackParameters;
    private final boolean enableConvertHighResIntPcmToFloat;
    private int framesPerEncodedSample;
    private Method getLatencyMethod;
    private boolean handledEndOfStream;
    private boolean hasData;
    private ByteBuffer inputBuffer;
    private int inputSampleRate;
    private boolean isInputPcm;
    private AudioTrack keepSessionIdAudioTrack;
    private long lastFeedElapsedRealtimeMs;
    private long lastPlayheadSampleTimeUs;
    private long lastTimestampSampleTimeUs;
    private long latencyUs;
    private Listener listener;
    private int nextPlayheadOffsetIndex;
    private ByteBuffer outputBuffer;
    private ByteBuffer[] outputBuffers;
    private int outputEncoding;
    private int outputPcmFrameSize;
    private int pcmFrameSize;
    private PlaybackParameters playbackParameters;
    private final ArrayDeque<PlaybackParametersCheckpoint> playbackParametersCheckpoints;
    private long playbackParametersOffsetUs;
    private long playbackParametersPositionUs;
    private int playheadOffsetCount;
    private final long[] playheadOffsets;
    private boolean playing;
    private byte[] preV21OutputBuffer;
    private int preV21OutputBufferOffset;
    private boolean processingEnabled;
    /* access modifiers changed from: private */
    public final ConditionVariable releasingConditionVariable;
    private long resumeSystemTimeUs;
    private int sampleRate;
    private boolean shouldConvertHighResIntPcmToFloat;
    private long smoothedPlayheadOffsetUs;
    private final SonicAudioProcessor sonicAudioProcessor;
    private int startMediaTimeState;
    private long startMediaTimeUs;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private final AudioProcessor[] toFloatPcmAvailableAudioProcessors;
    private final AudioProcessor[] toIntPcmAvailableAudioProcessors;
    private final TrimmingAudioProcessor trimmingAudioProcessor;
    private boolean tunneling;
    private float volume;
    private long writtenEncodedFrames;
    private long writtenPcmBytes;

    private static class AudioTrackUtil {
        protected AudioTrack audioTrack;
        private long endPlaybackHeadPosition;
        private long forceResetWorkaroundTimeMs;
        private long lastRawPlaybackHeadPosition;
        private boolean needsPassthroughWorkaround;
        private long passthroughWorkaroundPauseOffset;
        private long rawPlaybackHeadWrapCount;
        private int sampleRate;
        private long stopPlaybackHeadPosition;
        private long stopTimestampUs;

        public boolean updateTimestamp() {
            return false;
        }

        private AudioTrackUtil() {
        }

        public void reconfigure(AudioTrack audioTrack2, boolean z) {
            this.audioTrack = audioTrack2;
            this.needsPassthroughWorkaround = z;
            this.stopTimestampUs = -9223372036854775807L;
            this.forceResetWorkaroundTimeMs = -9223372036854775807L;
            this.lastRawPlaybackHeadPosition = 0;
            this.rawPlaybackHeadWrapCount = 0;
            this.passthroughWorkaroundPauseOffset = 0;
            if (audioTrack2 != null) {
                this.sampleRate = audioTrack2.getSampleRate();
            }
        }

        public void handleEndOfStream(long j) {
            this.stopPlaybackHeadPosition = getPlaybackHeadPosition();
            this.stopTimestampUs = SystemClock.elapsedRealtime() * 1000;
            this.endPlaybackHeadPosition = j;
            this.audioTrack.stop();
        }

        public void pause() {
            if (this.stopTimestampUs == -9223372036854775807L) {
                this.audioTrack.pause();
            }
        }

        public boolean needsReset(long j) {
            return this.forceResetWorkaroundTimeMs != -9223372036854775807L && j > 0 && SystemClock.elapsedRealtime() - this.forceResetWorkaroundTimeMs >= 200;
        }

        public long getPlaybackHeadPosition() {
            long j;
            if (this.stopTimestampUs != -9223372036854775807L) {
                return Math.min(this.endPlaybackHeadPosition, this.stopPlaybackHeadPosition + ((((SystemClock.elapsedRealtime() * 1000) - this.stopTimestampUs) * ((long) this.sampleRate)) / 1000000));
            }
            int playState = this.audioTrack.getPlayState();
            if (playState == 1) {
                return 0;
            }
            long playbackHeadPosition = ((long) this.audioTrack.getPlaybackHeadPosition()) & 4294967295L;
            if (this.needsPassthroughWorkaround) {
                if (playState == 2 && playbackHeadPosition == 0) {
                    this.passthroughWorkaroundPauseOffset = this.lastRawPlaybackHeadPosition;
                }
                j = playbackHeadPosition + this.passthroughWorkaroundPauseOffset;
            } else {
                j = playbackHeadPosition;
            }
            if (Util.SDK_INT <= 28) {
                if (j == 0 && this.lastRawPlaybackHeadPosition > 0 && playState == 3) {
                    if (this.forceResetWorkaroundTimeMs == -9223372036854775807L) {
                        this.forceResetWorkaroundTimeMs = SystemClock.elapsedRealtime();
                    }
                    return this.lastRawPlaybackHeadPosition;
                }
                this.forceResetWorkaroundTimeMs = -9223372036854775807L;
            }
            if (this.lastRawPlaybackHeadPosition > j) {
                this.rawPlaybackHeadWrapCount++;
            }
            this.lastRawPlaybackHeadPosition = j;
            return j + (this.rawPlaybackHeadWrapCount << 32);
        }

        public long getPositionUs() {
            return (getPlaybackHeadPosition() * 1000000) / ((long) this.sampleRate);
        }

        public long getTimestampNanoTime() {
            throw new UnsupportedOperationException();
        }

        public long getTimestampFramePosition() {
            throw new UnsupportedOperationException();
        }
    }

    @TargetApi(19)
    private static class AudioTrackUtilV19 extends AudioTrackUtil {
        private final AudioTimestamp audioTimestamp = new AudioTimestamp();
        private long lastRawTimestampFramePosition;
        private long lastTimestampFramePosition;
        private long rawTimestampFramePositionWrapCount;

        public AudioTrackUtilV19() {
            super();
        }

        public void reconfigure(AudioTrack audioTrack, boolean z) {
            super.reconfigure(audioTrack, z);
            this.rawTimestampFramePositionWrapCount = 0;
            this.lastRawTimestampFramePosition = 0;
            this.lastTimestampFramePosition = 0;
        }

        public boolean updateTimestamp() {
            boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (timestamp) {
                long j = this.audioTimestamp.framePosition;
                if (this.lastRawTimestampFramePosition > j) {
                    this.rawTimestampFramePositionWrapCount++;
                }
                this.lastRawTimestampFramePosition = j;
                this.lastTimestampFramePosition = j + (this.rawTimestampFramePositionWrapCount << 32);
            }
            return timestamp;
        }

        public long getTimestampNanoTime() {
            return this.audioTimestamp.nanoTime;
        }

        public long getTimestampFramePosition() {
            return this.lastTimestampFramePosition;
        }
    }

    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        public InvalidAudioTrackTimestampException(String str) {
            super(str);
        }
    }

    private static final class PlaybackParametersCheckpoint {
        /* access modifiers changed from: private */
        public final long mediaTimeUs;
        /* access modifiers changed from: private */
        public final PlaybackParameters playbackParameters;
        /* access modifiers changed from: private */
        public final long positionUs;

        private PlaybackParametersCheckpoint(PlaybackParameters playbackParameters2, long j, long j2) {
            this.playbackParameters = playbackParameters2;
            this.mediaTimeUs = j;
            this.positionUs = j2;
        }
    }

    private static boolean isEncodingPcm(int i) {
        return i == 3 || i == 2 || i == Integer.MIN_VALUE || i == 1073741824 || i == 4;
    }

    public DefaultAudioSink(AudioCapabilities audioCapabilities2, AudioProcessor[] audioProcessorArr) {
        this(audioCapabilities2, audioProcessorArr, false);
    }

    public DefaultAudioSink(AudioCapabilities audioCapabilities2, AudioProcessor[] audioProcessorArr, boolean z) {
        this.audioCapabilities = audioCapabilities2;
        this.enableConvertHighResIntPcmToFloat = z;
        this.releasingConditionVariable = new ConditionVariable(true);
        if (Util.SDK_INT >= 18) {
            try {
                this.getLatencyMethod = AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException unused) {
            }
        }
        if (Util.SDK_INT >= 19) {
            this.audioTrackUtil = new AudioTrackUtilV19();
        } else {
            this.audioTrackUtil = new AudioTrackUtil();
        }
        this.channelMappingAudioProcessor = new ChannelMappingAudioProcessor();
        this.trimmingAudioProcessor = new TrimmingAudioProcessor();
        this.sonicAudioProcessor = new SonicAudioProcessor();
        this.toIntPcmAvailableAudioProcessors = new AudioProcessor[(audioProcessorArr.length + 4)];
        this.toIntPcmAvailableAudioProcessors[0] = new ResamplingAudioProcessor();
        this.toIntPcmAvailableAudioProcessors[1] = this.channelMappingAudioProcessor;
        this.toIntPcmAvailableAudioProcessors[2] = this.trimmingAudioProcessor;
        System.arraycopy(audioProcessorArr, 0, this.toIntPcmAvailableAudioProcessors, 3, audioProcessorArr.length);
        this.toIntPcmAvailableAudioProcessors[audioProcessorArr.length + 3] = this.sonicAudioProcessor;
        this.toFloatPcmAvailableAudioProcessors = new AudioProcessor[]{new FloatResamplingAudioProcessor()};
        this.playheadOffsets = new long[10];
        this.volume = 1.0f;
        this.startMediaTimeState = 0;
        this.audioAttributes = AudioAttributes.DEFAULT;
        this.audioSessionId = 0;
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.drainingAudioProcessorIndex = -1;
        this.audioProcessors = new AudioProcessor[0];
        this.outputBuffers = new ByteBuffer[0];
        this.playbackParametersCheckpoints = new ArrayDeque<>();
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    public boolean isEncodingSupported(int i) {
        boolean z = true;
        if (isEncodingPcm(i)) {
            if (i == 4 && Util.SDK_INT < 21) {
                z = false;
            }
            return z;
        }
        if (this.audioCapabilities == null || !this.audioCapabilities.supportsEncoding(i)) {
            z = false;
        }
        return z;
    }

    public long getCurrentPositionUs(boolean z) {
        long j;
        if (!hasCurrentPositionUs()) {
            return Long.MIN_VALUE;
        }
        if (this.audioTrack.getPlayState() == 3) {
            maybeSampleSyncParams();
        }
        long nanoTime = System.nanoTime() / 1000;
        if (this.audioTimestampSet) {
            j = framesToDurationUs(this.audioTrackUtil.getTimestampFramePosition() + durationUsToFrames(nanoTime - (this.audioTrackUtil.getTimestampNanoTime() / 1000)));
        } else {
            if (this.playheadOffsetCount == 0) {
                j = this.audioTrackUtil.getPositionUs();
            } else {
                j = nanoTime + this.smoothedPlayheadOffsetUs;
            }
            if (!z) {
                j -= this.latencyUs;
            }
        }
        return this.startMediaTimeUs + applySpeedup(Math.min(j, framesToDurationUs(getWrittenFrames())));
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x017c  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0121  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void configure(int r9, int r10, int r11, int r12, int[] r13, int r14, int r15) throws com.google.android.exoplayer2.audio.AudioSink.ConfigurationException {
        /*
            r8 = this;
            r8.inputSampleRate = r11
            boolean r0 = isEncodingPcm(r9)
            r8.isInputPcm = r0
            boolean r0 = r8.enableConvertHighResIntPcmToFloat
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x001e
            r0 = 1073741824(0x40000000, float:2.0)
            boolean r0 = r8.isEncodingSupported(r0)
            if (r0 == 0) goto L_0x001e
            boolean r0 = com.google.android.exoplayer2.util.Util.isEncodingHighResolutionIntegerPcm(r9)
            if (r0 == 0) goto L_0x001e
            r0 = 1
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            r8.shouldConvertHighResIntPcmToFloat = r0
            boolean r0 = r8.isInputPcm
            if (r0 == 0) goto L_0x002b
            int r0 = com.google.android.exoplayer2.util.Util.getPcmFrameSize(r9, r10)
            r8.pcmFrameSize = r0
        L_0x002b:
            boolean r0 = r8.isInputPcm
            r3 = 4
            if (r0 == 0) goto L_0x0034
            if (r9 == r3) goto L_0x0034
            r0 = 1
            goto L_0x0035
        L_0x0034:
            r0 = 0
        L_0x0035:
            if (r0 == 0) goto L_0x003d
            boolean r4 = r8.shouldConvertHighResIntPcmToFloat
            if (r4 != 0) goto L_0x003d
            r4 = 1
            goto L_0x003e
        L_0x003d:
            r4 = 0
        L_0x003e:
            r8.canApplyPlaybackParameters = r4
            if (r0 == 0) goto L_0x007c
            com.google.android.exoplayer2.audio.TrimmingAudioProcessor r4 = r8.trimmingAudioProcessor
            r4.setTrimSampleCount(r14, r15)
            com.google.android.exoplayer2.audio.ChannelMappingAudioProcessor r14 = r8.channelMappingAudioProcessor
            r14.setChannelMap(r13)
            com.google.android.exoplayer2.audio.AudioProcessor[] r13 = r8.getAvailableAudioProcessors()
            int r14 = r13.length
            r4 = r9
            r15 = r11
            r9 = 0
            r11 = 0
        L_0x0055:
            if (r9 >= r14) goto L_0x007a
            r5 = r13[r9]
            boolean r6 = r5.configure(r15, r10, r4)     // Catch:{ UnhandledFormatException -> 0x0073 }
            r11 = r11 | r6
            boolean r6 = r5.isActive()
            if (r6 == 0) goto L_0x0070
            int r10 = r5.getOutputChannelCount()
            int r15 = r5.getOutputSampleRateHz()
            int r4 = r5.getOutputEncoding()
        L_0x0070:
            int r9 = r9 + 1
            goto L_0x0055
        L_0x0073:
            r9 = move-exception
            com.google.android.exoplayer2.audio.AudioSink$ConfigurationException r10 = new com.google.android.exoplayer2.audio.AudioSink$ConfigurationException
            r10.<init>(r9)
            throw r10
        L_0x007a:
            r9 = r4
            goto L_0x007e
        L_0x007c:
            r15 = r11
            r11 = 0
        L_0x007e:
            r13 = 12
            r14 = 252(0xfc, float:3.53E-43)
            switch(r10) {
                case 1: goto L_0x00b0;
                case 2: goto L_0x00ae;
                case 3: goto L_0x00ab;
                case 4: goto L_0x00a8;
                case 5: goto L_0x00a5;
                case 6: goto L_0x00a2;
                case 7: goto L_0x009f;
                case 8: goto L_0x009c;
                default: goto L_0x0085;
            }
        L_0x0085:
            com.google.android.exoplayer2.audio.AudioSink$ConfigurationException r9 = new com.google.android.exoplayer2.audio.AudioSink$ConfigurationException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "Unsupported channel count: "
            r11.append(r12)
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r9.<init>(r10)
            throw r9
        L_0x009c:
            int r3 = com.google.android.exoplayer2.C.CHANNEL_OUT_7POINT1_SURROUND
            goto L_0x00b0
        L_0x009f:
            r3 = 1276(0x4fc, float:1.788E-42)
            goto L_0x00b0
        L_0x00a2:
            r3 = 252(0xfc, float:3.53E-43)
            goto L_0x00b0
        L_0x00a5:
            r3 = 220(0xdc, float:3.08E-43)
            goto L_0x00b0
        L_0x00a8:
            r3 = 204(0xcc, float:2.86E-43)
            goto L_0x00b0
        L_0x00ab:
            r3 = 28
            goto L_0x00b0
        L_0x00ae:
            r3 = 12
        L_0x00b0:
            int r4 = com.google.android.exoplayer2.util.Util.SDK_INT
            r5 = 23
            r6 = 7
            r7 = 5
            if (r4 > r5) goto L_0x00d7
            java.lang.String r4 = "foster"
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.DEVICE
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00d7
            java.lang.String r4 = "NVIDIA"
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.MANUFACTURER
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x00d7
            r4 = 3
            if (r10 == r4) goto L_0x00d8
            if (r10 == r7) goto L_0x00d8
            if (r10 == r6) goto L_0x00d4
            goto L_0x00d7
        L_0x00d4:
            int r14 = com.google.android.exoplayer2.C.CHANNEL_OUT_7POINT1_SURROUND
            goto L_0x00d8
        L_0x00d7:
            r14 = r3
        L_0x00d8:
            int r3 = com.google.android.exoplayer2.util.Util.SDK_INT
            r4 = 25
            if (r3 > r4) goto L_0x00ef
            java.lang.String r3 = "fugu"
            java.lang.String r4 = com.google.android.exoplayer2.util.Util.DEVICE
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00ef
            boolean r3 = r8.isInputPcm
            if (r3 != 0) goto L_0x00ef
            if (r10 != r1) goto L_0x00ef
            goto L_0x00f0
        L_0x00ef:
            r13 = r14
        L_0x00f0:
            if (r11 != 0) goto L_0x0105
            boolean r11 = r8.isInitialized()
            if (r11 == 0) goto L_0x0105
            int r11 = r8.outputEncoding
            if (r11 != r9) goto L_0x0105
            int r11 = r8.sampleRate
            if (r11 != r15) goto L_0x0105
            int r11 = r8.channelConfig
            if (r11 != r13) goto L_0x0105
            return
        L_0x0105:
            r8.reset()
            r8.processingEnabled = r0
            r8.sampleRate = r15
            r8.channelConfig = r13
            r8.outputEncoding = r9
            boolean r9 = r8.isInputPcm
            if (r9 == 0) goto L_0x011c
            int r9 = r8.outputEncoding
            int r9 = com.google.android.exoplayer2.util.Util.getPcmFrameSize(r9, r10)
            r8.outputPcmFrameSize = r9
        L_0x011c:
            if (r12 == 0) goto L_0x0121
            r8.bufferSize = r12
            goto L_0x0178
        L_0x0121:
            boolean r9 = r8.isInputPcm
            if (r9 == 0) goto L_0x015a
            int r9 = r8.outputEncoding
            int r9 = android.media.AudioTrack.getMinBufferSize(r15, r13, r9)
            r10 = -2
            if (r9 == r10) goto L_0x012f
            goto L_0x0130
        L_0x012f:
            r1 = 0
        L_0x0130:
            com.google.android.exoplayer2.util.Assertions.checkState(r1)
            int r10 = r9 * 4
            r11 = 250000(0x3d090, double:1.235164E-318)
            long r11 = r8.durationUsToFrames(r11)
            int r11 = (int) r11
            int r12 = r8.outputPcmFrameSize
            int r11 = r11 * r12
            long r12 = (long) r9
            r14 = 750000(0xb71b0, double:3.70549E-318)
            long r14 = r8.durationUsToFrames(r14)
            int r9 = r8.outputPcmFrameSize
            long r0 = (long) r9
            long r14 = r14 * r0
            long r12 = java.lang.Math.max(r12, r14)
            int r9 = (int) r12
            int r9 = com.google.android.exoplayer2.util.Util.constrainValue(r10, r11, r9)
            r8.bufferSize = r9
            goto L_0x0178
        L_0x015a:
            int r9 = r8.outputEncoding
            if (r9 == r7) goto L_0x0174
            int r9 = r8.outputEncoding
            r10 = 6
            if (r9 != r10) goto L_0x0164
            goto L_0x0174
        L_0x0164:
            int r9 = r8.outputEncoding
            if (r9 != r6) goto L_0x016e
            r9 = 49152(0xc000, float:6.8877E-41)
            r8.bufferSize = r9
            goto L_0x0178
        L_0x016e:
            r9 = 294912(0x48000, float:4.1326E-40)
            r8.bufferSize = r9
            goto L_0x0178
        L_0x0174:
            r9 = 20480(0x5000, float:2.8699E-41)
            r8.bufferSize = r9
        L_0x0178:
            boolean r9 = r8.isInputPcm
            if (r9 == 0) goto L_0x0187
            int r9 = r8.bufferSize
            int r10 = r8.outputPcmFrameSize
            int r9 = r9 / r10
            long r9 = (long) r9
            long r9 = r8.framesToDurationUs(r9)
            goto L_0x018c
        L_0x0187:
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x018c:
            r8.bufferSizeUs = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DefaultAudioSink.configure(int, int, int, int, int[], int, int):void");
    }

    private void resetAudioProcessors() {
        AudioProcessor[] availableAudioProcessors;
        ArrayList arrayList = new ArrayList();
        for (AudioProcessor audioProcessor : getAvailableAudioProcessors()) {
            if (audioProcessor.isActive()) {
                arrayList.add(audioProcessor);
            } else {
                audioProcessor.flush();
            }
        }
        int size = arrayList.size();
        this.audioProcessors = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[size]);
        this.outputBuffers = new ByteBuffer[size];
        for (int i = 0; i < size; i++) {
            AudioProcessor audioProcessor2 = this.audioProcessors[i];
            audioProcessor2.flush();
            this.outputBuffers[i] = audioProcessor2.getOutput();
        }
    }

    private void initialize() throws InitializationException {
        this.releasingConditionVariable.block();
        this.audioTrack = initializeAudioTrack();
        setPlaybackParameters(this.playbackParameters);
        resetAudioProcessors();
        int audioSessionId2 = this.audioTrack.getAudioSessionId();
        if (enablePreV21AudioSessionWorkaround && Util.SDK_INT < 21) {
            if (!(this.keepSessionIdAudioTrack == null || audioSessionId2 == this.keepSessionIdAudioTrack.getAudioSessionId())) {
                releaseKeepSessionIdAudioTrack();
            }
            if (this.keepSessionIdAudioTrack == null) {
                this.keepSessionIdAudioTrack = initializeKeepSessionIdAudioTrack(audioSessionId2);
            }
        }
        if (this.audioSessionId != audioSessionId2) {
            this.audioSessionId = audioSessionId2;
            if (this.listener != null) {
                this.listener.onAudioSessionId(audioSessionId2);
            }
        }
        this.audioTrackUtil.reconfigure(this.audioTrack, needsPassthroughWorkarounds());
        setVolumeInternal();
        this.hasData = false;
    }

    public void play() {
        this.playing = true;
        if (isInitialized()) {
            this.resumeSystemTimeUs = System.nanoTime() / 1000;
            this.audioTrack.play();
        }
    }

    public void handleDiscontinuity() {
        if (this.startMediaTimeState == 1) {
            this.startMediaTimeState = 2;
        }
    }

    public boolean handleBuffer(ByteBuffer byteBuffer, long j) throws InitializationException, WriteException {
        int i;
        ByteBuffer byteBuffer2 = byteBuffer;
        long j2 = j;
        Assertions.checkArgument(this.inputBuffer == null || byteBuffer2 == this.inputBuffer);
        if (!isInitialized()) {
            initialize();
            if (this.playing) {
                play();
            }
        }
        if (needsPassthroughWorkarounds()) {
            if (this.audioTrack.getPlayState() == 2) {
                this.hasData = false;
                return false;
            } else if (this.audioTrack.getPlayState() == 1 && this.audioTrackUtil.getPlaybackHeadPosition() != 0) {
                return false;
            }
        }
        boolean z = this.hasData;
        this.hasData = hasPendingData();
        if (z && !this.hasData && this.audioTrack.getPlayState() != 1 && this.listener != null) {
            this.listener.onUnderrun(this.bufferSize, C.usToMs(this.bufferSizeUs), SystemClock.elapsedRealtime() - this.lastFeedElapsedRealtimeMs);
        }
        if (this.inputBuffer == null) {
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (!this.isInputPcm && this.framesPerEncodedSample == 0) {
                this.framesPerEncodedSample = getFramesPerEncodedSample(this.outputEncoding, byteBuffer2);
                if (this.framesPerEncodedSample == 0) {
                    return true;
                }
            }
            if (this.drainingPlaybackParameters != null) {
                if (!drainAudioProcessorsToEndOfStream()) {
                    return false;
                }
                ArrayDeque<PlaybackParametersCheckpoint> arrayDeque = this.playbackParametersCheckpoints;
                PlaybackParametersCheckpoint playbackParametersCheckpoint = r11;
                PlaybackParametersCheckpoint playbackParametersCheckpoint2 = new PlaybackParametersCheckpoint(this.drainingPlaybackParameters, Math.max(0, j2), framesToDurationUs(getWrittenFrames()));
                arrayDeque.add(playbackParametersCheckpoint);
                this.drainingPlaybackParameters = null;
                resetAudioProcessors();
            }
            if (this.startMediaTimeState == 0) {
                this.startMediaTimeUs = Math.max(0, j2);
                this.startMediaTimeState = 1;
            } else {
                long inputFramesToDurationUs = this.startMediaTimeUs + inputFramesToDurationUs(getSubmittedFrames());
                if (this.startMediaTimeState != 1 || Math.abs(inputFramesToDurationUs - j2) <= 200000) {
                    i = 2;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Discontinuity detected [expected ");
                    sb.append(inputFramesToDurationUs);
                    sb.append(", got ");
                    sb.append(j2);
                    sb.append("]");
                    Log.e("AudioTrack", sb.toString());
                    i = 2;
                    this.startMediaTimeState = 2;
                }
                if (this.startMediaTimeState == i) {
                    this.startMediaTimeUs += j2 - inputFramesToDurationUs;
                    this.startMediaTimeState = 1;
                    if (this.listener != null) {
                        this.listener.onPositionDiscontinuity();
                    }
                }
            }
            if (this.isInputPcm) {
                this.submittedPcmBytes += (long) byteBuffer.remaining();
            } else {
                this.submittedEncodedFrames += (long) this.framesPerEncodedSample;
            }
            this.inputBuffer = byteBuffer2;
        }
        if (this.processingEnabled) {
            processBuffers(j2);
        } else {
            writeBuffer(this.inputBuffer, j2);
        }
        if (!this.inputBuffer.hasRemaining()) {
            this.inputBuffer = null;
            return true;
        } else if (!this.audioTrackUtil.needsReset(getWrittenFrames())) {
            return false;
        } else {
            Log.w("AudioTrack", "Resetting stalled audio track");
            reset();
            return true;
        }
    }

    private void processBuffers(long j) throws WriteException {
        int length = this.audioProcessors.length;
        int i = length;
        while (i >= 0) {
            ByteBuffer byteBuffer = i > 0 ? this.outputBuffers[i - 1] : this.inputBuffer != null ? this.inputBuffer : AudioProcessor.EMPTY_BUFFER;
            if (i == length) {
                writeBuffer(byteBuffer, j);
            } else {
                AudioProcessor audioProcessor = this.audioProcessors[i];
                audioProcessor.queueInput(byteBuffer);
                ByteBuffer output = audioProcessor.getOutput();
                this.outputBuffers[i] = output;
                if (output.hasRemaining()) {
                    i++;
                }
            }
            if (!byteBuffer.hasRemaining()) {
                i--;
            } else {
                return;
            }
        }
    }

    private void writeBuffer(ByteBuffer byteBuffer, long j) throws WriteException {
        if (byteBuffer.hasRemaining()) {
            boolean z = true;
            int i = 0;
            if (this.outputBuffer != null) {
                Assertions.checkArgument(this.outputBuffer == byteBuffer);
            } else {
                this.outputBuffer = byteBuffer;
                if (Util.SDK_INT < 21) {
                    int remaining = byteBuffer.remaining();
                    if (this.preV21OutputBuffer == null || this.preV21OutputBuffer.length < remaining) {
                        this.preV21OutputBuffer = new byte[remaining];
                    }
                    int position = byteBuffer.position();
                    byteBuffer.get(this.preV21OutputBuffer, 0, remaining);
                    byteBuffer.position(position);
                    this.preV21OutputBufferOffset = 0;
                }
            }
            int remaining2 = byteBuffer.remaining();
            if (Util.SDK_INT < 21) {
                int playbackHeadPosition = this.bufferSize - ((int) (this.writtenPcmBytes - (this.audioTrackUtil.getPlaybackHeadPosition() * ((long) this.outputPcmFrameSize))));
                if (playbackHeadPosition > 0) {
                    i = this.audioTrack.write(this.preV21OutputBuffer, this.preV21OutputBufferOffset, Math.min(remaining2, playbackHeadPosition));
                    if (i > 0) {
                        this.preV21OutputBufferOffset += i;
                        byteBuffer.position(byteBuffer.position() + i);
                    }
                }
            } else if (this.tunneling) {
                if (j == -9223372036854775807L) {
                    z = false;
                }
                Assertions.checkState(z);
                i = writeNonBlockingWithAvSyncV21(this.audioTrack, byteBuffer, remaining2, j);
            } else {
                i = writeNonBlockingV21(this.audioTrack, byteBuffer, remaining2);
            }
            this.lastFeedElapsedRealtimeMs = SystemClock.elapsedRealtime();
            if (i < 0) {
                throw new WriteException(i);
            }
            if (this.isInputPcm) {
                this.writtenPcmBytes += (long) i;
            }
            if (i == remaining2) {
                if (!this.isInputPcm) {
                    this.writtenEncodedFrames += (long) this.framesPerEncodedSample;
                }
                this.outputBuffer = null;
            }
        }
    }

    public void playToEndOfStream() throws WriteException {
        if (!this.handledEndOfStream && isInitialized() && drainAudioProcessorsToEndOfStream()) {
            this.audioTrackUtil.handleEndOfStream(getWrittenFrames());
            this.bytesUntilNextAvSync = 0;
            this.handledEndOfStream = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean drainAudioProcessorsToEndOfStream() throws com.google.android.exoplayer2.audio.AudioSink.WriteException {
        /*
            r8 = this;
            int r0 = r8.drainingAudioProcessorIndex
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L_0x0014
            boolean r0 = r8.processingEnabled
            if (r0 == 0) goto L_0x000d
            r0 = 0
            goto L_0x0010
        L_0x000d:
            com.google.android.exoplayer2.audio.AudioProcessor[] r0 = r8.audioProcessors
            int r0 = r0.length
        L_0x0010:
            r8.drainingAudioProcessorIndex = r0
        L_0x0012:
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            int r4 = r8.drainingAudioProcessorIndex
            com.google.android.exoplayer2.audio.AudioProcessor[] r5 = r8.audioProcessors
            int r5 = r5.length
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 >= r5) goto L_0x003c
            com.google.android.exoplayer2.audio.AudioProcessor[] r4 = r8.audioProcessors
            int r5 = r8.drainingAudioProcessorIndex
            r4 = r4[r5]
            if (r0 == 0) goto L_0x002c
            r4.queueEndOfStream()
        L_0x002c:
            r8.processBuffers(r6)
            boolean r0 = r4.isEnded()
            if (r0 != 0) goto L_0x0036
            return r3
        L_0x0036:
            int r0 = r8.drainingAudioProcessorIndex
            int r0 = r0 + r2
            r8.drainingAudioProcessorIndex = r0
            goto L_0x0012
        L_0x003c:
            java.nio.ByteBuffer r0 = r8.outputBuffer
            if (r0 == 0) goto L_0x004a
            java.nio.ByteBuffer r0 = r8.outputBuffer
            r8.writeBuffer(r0, r6)
            java.nio.ByteBuffer r0 = r8.outputBuffer
            if (r0 == 0) goto L_0x004a
            return r3
        L_0x004a:
            r8.drainingAudioProcessorIndex = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DefaultAudioSink.drainAudioProcessorsToEndOfStream():boolean");
    }

    public boolean isEnded() {
        return !isInitialized() || (this.handledEndOfStream && !hasPendingData());
    }

    public boolean hasPendingData() {
        return isInitialized() && (getWrittenFrames() > this.audioTrackUtil.getPlaybackHeadPosition() || overrideHasPendingData());
    }

    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters2) {
        if (!isInitialized() || this.canApplyPlaybackParameters) {
            PlaybackParameters playbackParameters3 = new PlaybackParameters(this.sonicAudioProcessor.setSpeed(playbackParameters2.speed), this.sonicAudioProcessor.setPitch(playbackParameters2.pitch));
            PlaybackParameters playbackParameters4 = this.drainingPlaybackParameters != null ? this.drainingPlaybackParameters : !this.playbackParametersCheckpoints.isEmpty() ? ((PlaybackParametersCheckpoint) this.playbackParametersCheckpoints.getLast()).playbackParameters : this.playbackParameters;
            if (!playbackParameters3.equals(playbackParameters4)) {
                if (isInitialized()) {
                    this.drainingPlaybackParameters = playbackParameters3;
                } else {
                    this.playbackParameters = playbackParameters3;
                }
            }
            return this.playbackParameters;
        }
        this.playbackParameters = PlaybackParameters.DEFAULT;
        return this.playbackParameters;
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    public void setAudioAttributes(AudioAttributes audioAttributes2) {
        if (!this.audioAttributes.equals(audioAttributes2)) {
            this.audioAttributes = audioAttributes2;
            if (!this.tunneling) {
                reset();
                this.audioSessionId = 0;
            }
        }
    }

    public void enableTunnelingV21(int i) {
        Assertions.checkState(Util.SDK_INT >= 21);
        if (!this.tunneling || this.audioSessionId != i) {
            this.tunneling = true;
            this.audioSessionId = i;
            reset();
        }
    }

    public void disableTunneling() {
        if (this.tunneling) {
            this.tunneling = false;
            this.audioSessionId = 0;
            reset();
        }
    }

    public void setVolume(float f) {
        if (this.volume != f) {
            this.volume = f;
            setVolumeInternal();
        }
    }

    private void setVolumeInternal() {
        if (isInitialized()) {
            if (Util.SDK_INT >= 21) {
                setVolumeInternalV21(this.audioTrack, this.volume);
            } else {
                setVolumeInternalV3(this.audioTrack, this.volume);
            }
        }
    }

    public void pause() {
        this.playing = false;
        if (isInitialized()) {
            resetSyncParams();
            this.audioTrackUtil.pause();
        }
    }

    public void reset() {
        if (isInitialized()) {
            this.submittedPcmBytes = 0;
            this.submittedEncodedFrames = 0;
            this.writtenPcmBytes = 0;
            this.writtenEncodedFrames = 0;
            this.framesPerEncodedSample = 0;
            if (this.drainingPlaybackParameters != null) {
                this.playbackParameters = this.drainingPlaybackParameters;
                this.drainingPlaybackParameters = null;
            } else if (!this.playbackParametersCheckpoints.isEmpty()) {
                this.playbackParameters = ((PlaybackParametersCheckpoint) this.playbackParametersCheckpoints.getLast()).playbackParameters;
            }
            this.playbackParametersCheckpoints.clear();
            this.playbackParametersOffsetUs = 0;
            this.playbackParametersPositionUs = 0;
            this.inputBuffer = null;
            this.outputBuffer = null;
            for (int i = 0; i < this.audioProcessors.length; i++) {
                AudioProcessor audioProcessor = this.audioProcessors[i];
                audioProcessor.flush();
                this.outputBuffers[i] = audioProcessor.getOutput();
            }
            this.handledEndOfStream = false;
            this.drainingAudioProcessorIndex = -1;
            this.avSyncHeader = null;
            this.bytesUntilNextAvSync = 0;
            this.startMediaTimeState = 0;
            this.latencyUs = 0;
            resetSyncParams();
            if (this.audioTrack.getPlayState() == 3) {
                this.audioTrack.pause();
            }
            final AudioTrack audioTrack2 = this.audioTrack;
            this.audioTrack = null;
            this.audioTrackUtil.reconfigure(null, false);
            this.releasingConditionVariable.close();
            new Thread() {
                public void run() {
                    try {
                        audioTrack2.flush();
                        audioTrack2.release();
                    } finally {
                        DefaultAudioSink.this.releasingConditionVariable.open();
                    }
                }
            }.start();
        }
    }

    public void release() {
        reset();
        releaseKeepSessionIdAudioTrack();
        for (AudioProcessor reset : this.toIntPcmAvailableAudioProcessors) {
            reset.reset();
        }
        for (AudioProcessor reset2 : this.toFloatPcmAvailableAudioProcessors) {
            reset2.reset();
        }
        this.audioSessionId = 0;
        this.playing = false;
    }

    private void releaseKeepSessionIdAudioTrack() {
        if (this.keepSessionIdAudioTrack != null) {
            final AudioTrack audioTrack2 = this.keepSessionIdAudioTrack;
            this.keepSessionIdAudioTrack = null;
            new Thread() {
                public void run() {
                    audioTrack2.release();
                }
            }.start();
        }
    }

    private boolean hasCurrentPositionUs() {
        return isInitialized() && this.startMediaTimeState != 0;
    }

    private long applySpeedup(long j) {
        while (!this.playbackParametersCheckpoints.isEmpty() && j >= ((PlaybackParametersCheckpoint) this.playbackParametersCheckpoints.getFirst()).positionUs) {
            PlaybackParametersCheckpoint playbackParametersCheckpoint = (PlaybackParametersCheckpoint) this.playbackParametersCheckpoints.remove();
            this.playbackParameters = playbackParametersCheckpoint.playbackParameters;
            this.playbackParametersPositionUs = playbackParametersCheckpoint.positionUs;
            this.playbackParametersOffsetUs = playbackParametersCheckpoint.mediaTimeUs - this.startMediaTimeUs;
        }
        if (this.playbackParameters.speed == 1.0f) {
            return (j + this.playbackParametersOffsetUs) - this.playbackParametersPositionUs;
        }
        if (this.playbackParametersCheckpoints.isEmpty()) {
            return this.playbackParametersOffsetUs + this.sonicAudioProcessor.scaleDurationForSpeedup(j - this.playbackParametersPositionUs);
        }
        return this.playbackParametersOffsetUs + Util.getMediaDurationForPlayoutDuration(j - this.playbackParametersPositionUs, this.playbackParameters.speed);
    }

    private void maybeSampleSyncParams() {
        long positionUs = this.audioTrackUtil.getPositionUs();
        if (positionUs != 0) {
            long nanoTime = System.nanoTime() / 1000;
            if (nanoTime - this.lastPlayheadSampleTimeUs >= 30000) {
                this.playheadOffsets[this.nextPlayheadOffsetIndex] = positionUs - nanoTime;
                this.nextPlayheadOffsetIndex = (this.nextPlayheadOffsetIndex + 1) % 10;
                if (this.playheadOffsetCount < 10) {
                    this.playheadOffsetCount++;
                }
                this.lastPlayheadSampleTimeUs = nanoTime;
                this.smoothedPlayheadOffsetUs = 0;
                for (int i = 0; i < this.playheadOffsetCount; i++) {
                    this.smoothedPlayheadOffsetUs += this.playheadOffsets[i] / ((long) this.playheadOffsetCount);
                }
            }
            if (!needsPassthroughWorkarounds() && nanoTime - this.lastTimestampSampleTimeUs >= 500000) {
                this.audioTimestampSet = this.audioTrackUtil.updateTimestamp();
                if (this.audioTimestampSet) {
                    long timestampNanoTime = this.audioTrackUtil.getTimestampNanoTime() / 1000;
                    long timestampFramePosition = this.audioTrackUtil.getTimestampFramePosition();
                    if (timestampNanoTime < this.resumeSystemTimeUs) {
                        this.audioTimestampSet = false;
                    } else if (Math.abs(timestampNanoTime - nanoTime) > 5000000) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Spurious audio timestamp (system clock mismatch): ");
                        sb.append(timestampFramePosition);
                        sb.append(", ");
                        sb.append(timestampNanoTime);
                        sb.append(", ");
                        sb.append(nanoTime);
                        sb.append(", ");
                        sb.append(positionUs);
                        sb.append(", ");
                        sb.append(getSubmittedFrames());
                        sb.append(", ");
                        sb.append(getWrittenFrames());
                        String sb2 = sb.toString();
                        if (failOnSpuriousAudioTimestamp) {
                            throw new InvalidAudioTrackTimestampException(sb2);
                        }
                        Log.w("AudioTrack", sb2);
                        this.audioTimestampSet = false;
                    } else if (Math.abs(framesToDurationUs(timestampFramePosition) - positionUs) > 5000000) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Spurious audio timestamp (frame position mismatch): ");
                        sb3.append(timestampFramePosition);
                        sb3.append(", ");
                        sb3.append(timestampNanoTime);
                        sb3.append(", ");
                        sb3.append(nanoTime);
                        sb3.append(", ");
                        sb3.append(positionUs);
                        sb3.append(", ");
                        sb3.append(getSubmittedFrames());
                        sb3.append(", ");
                        sb3.append(getWrittenFrames());
                        String sb4 = sb3.toString();
                        if (failOnSpuriousAudioTimestamp) {
                            throw new InvalidAudioTrackTimestampException(sb4);
                        }
                        Log.w("AudioTrack", sb4);
                        this.audioTimestampSet = false;
                    }
                }
                if (this.getLatencyMethod != null && this.isInputPcm) {
                    try {
                        this.latencyUs = (((long) ((Integer) this.getLatencyMethod.invoke(this.audioTrack, null)).intValue()) * 1000) - this.bufferSizeUs;
                        this.latencyUs = Math.max(this.latencyUs, 0);
                        if (this.latencyUs > 5000000) {
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("Ignoring impossibly large audio latency: ");
                            sb5.append(this.latencyUs);
                            Log.w("AudioTrack", sb5.toString());
                            this.latencyUs = 0;
                        }
                    } catch (Exception unused) {
                        this.getLatencyMethod = null;
                    }
                }
                this.lastTimestampSampleTimeUs = nanoTime;
            }
        }
    }

    private boolean isInitialized() {
        return this.audioTrack != null;
    }

    private long inputFramesToDurationUs(long j) {
        return (j * 1000000) / ((long) this.inputSampleRate);
    }

    private long framesToDurationUs(long j) {
        return (j * 1000000) / ((long) this.sampleRate);
    }

    private long durationUsToFrames(long j) {
        return (j * ((long) this.sampleRate)) / 1000000;
    }

    private long getSubmittedFrames() {
        return this.isInputPcm ? this.submittedPcmBytes / ((long) this.pcmFrameSize) : this.submittedEncodedFrames;
    }

    private long getWrittenFrames() {
        return this.isInputPcm ? this.writtenPcmBytes / ((long) this.outputPcmFrameSize) : this.writtenEncodedFrames;
    }

    private void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0;
        this.audioTimestampSet = false;
        this.lastTimestampSampleTimeUs = 0;
    }

    private boolean needsPassthroughWorkarounds() {
        return Util.SDK_INT < 23 && (this.outputEncoding == 5 || this.outputEncoding == 6);
    }

    private boolean overrideHasPendingData() {
        return needsPassthroughWorkarounds() && this.audioTrack.getPlayState() == 2 && this.audioTrack.getPlaybackHeadPosition() == 0;
    }

    private AudioTrack initializeAudioTrack() throws InitializationException {
        AudioTrack audioTrack2;
        if (Util.SDK_INT >= 21) {
            audioTrack2 = createAudioTrackV21();
        } else {
            int streamTypeForAudioUsage = Util.getStreamTypeForAudioUsage(this.audioAttributes.usage);
            if (this.audioSessionId == 0) {
                audioTrack2 = new AudioTrack(streamTypeForAudioUsage, this.sampleRate, this.channelConfig, this.outputEncoding, this.bufferSize, 1);
            } else {
                audioTrack2 = new AudioTrack(streamTypeForAudioUsage, this.sampleRate, this.channelConfig, this.outputEncoding, this.bufferSize, 1, this.audioSessionId);
            }
        }
        int state = audioTrack2.getState();
        if (state == 1) {
            return audioTrack2;
        }
        try {
            audioTrack2.release();
        } catch (Exception unused) {
        }
        throw new InitializationException(state, this.sampleRate, this.channelConfig, this.bufferSize);
    }

    @TargetApi(21)
    private AudioTrack createAudioTrackV21() {
        AudioAttributes audioAttributesV21;
        if (this.tunneling) {
            audioAttributesV21 = new Builder().setContentType(3).setFlags(16).setUsage(1).build();
        } else {
            audioAttributesV21 = this.audioAttributes.getAudioAttributesV21();
        }
        AudioTrack audioTrack2 = new AudioTrack(audioAttributesV21, new AudioFormat.Builder().setChannelMask(this.channelConfig).setEncoding(this.outputEncoding).setSampleRate(this.sampleRate).build(), this.bufferSize, 1, this.audioSessionId != 0 ? this.audioSessionId : 0);
        return audioTrack2;
    }

    private AudioTrack initializeKeepSessionIdAudioTrack(int i) {
        AudioTrack audioTrack2 = new AudioTrack(3, 4000, 4, 2, 2, 0, i);
        return audioTrack2;
    }

    private AudioProcessor[] getAvailableAudioProcessors() {
        return this.shouldConvertHighResIntPcmToFloat ? this.toFloatPcmAvailableAudioProcessors : this.toIntPcmAvailableAudioProcessors;
    }

    private static int getFramesPerEncodedSample(int i, ByteBuffer byteBuffer) {
        if (i == 7 || i == 8) {
            return DtsUtil.parseDtsAudioSampleCount(byteBuffer);
        }
        if (i == 5) {
            return Ac3Util.getAc3SyncframeAudioSampleCount();
        }
        if (i == 6) {
            return Ac3Util.parseEAc3SyncframeAudioSampleCount(byteBuffer);
        }
        if (i == 14) {
            return Ac3Util.parseTrueHdSyncframeAudioSampleCount(byteBuffer) * 8;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected audio encoding: ");
        sb.append(i);
        throw new IllegalStateException(sb.toString());
    }

    @TargetApi(21)
    private static int writeNonBlockingV21(AudioTrack audioTrack2, ByteBuffer byteBuffer, int i) {
        return audioTrack2.write(byteBuffer, i, 1);
    }

    @TargetApi(21)
    private int writeNonBlockingWithAvSyncV21(AudioTrack audioTrack2, ByteBuffer byteBuffer, int i, long j) {
        if (this.avSyncHeader == null) {
            this.avSyncHeader = ByteBuffer.allocate(16);
            this.avSyncHeader.order(ByteOrder.BIG_ENDIAN);
            this.avSyncHeader.putInt(1431633921);
        }
        if (this.bytesUntilNextAvSync == 0) {
            this.avSyncHeader.putInt(4, i);
            this.avSyncHeader.putLong(8, j * 1000);
            this.avSyncHeader.position(0);
            this.bytesUntilNextAvSync = i;
        }
        int remaining = this.avSyncHeader.remaining();
        if (remaining > 0) {
            int write = audioTrack2.write(this.avSyncHeader, remaining, 1);
            if (write < 0) {
                this.bytesUntilNextAvSync = 0;
                return write;
            } else if (write < remaining) {
                return 0;
            }
        }
        int writeNonBlockingV21 = writeNonBlockingV21(audioTrack2, byteBuffer, i);
        if (writeNonBlockingV21 < 0) {
            this.bytesUntilNextAvSync = 0;
            return writeNonBlockingV21;
        }
        this.bytesUntilNextAvSync -= writeNonBlockingV21;
        return writeNonBlockingV21;
    }

    @TargetApi(21)
    private static void setVolumeInternalV21(AudioTrack audioTrack2, float f) {
        audioTrack2.setVolume(f);
    }

    private static void setVolumeInternalV3(AudioTrack audioTrack2, float f) {
        audioTrack2.setStereoVolume(f, f);
    }
}
