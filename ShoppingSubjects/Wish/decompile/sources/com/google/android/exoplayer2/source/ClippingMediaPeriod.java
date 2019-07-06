package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaPeriod.Callback;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;

public final class ClippingMediaPeriod implements MediaPeriod, Callback {
    private Callback callback;
    long endUs;
    public final MediaPeriod mediaPeriod;
    private long pendingInitialDiscontinuityPositionUs;
    private ClippingSampleStream[] sampleStreams = new ClippingSampleStream[0];
    long startUs;

    private final class ClippingSampleStream implements SampleStream {
        public final SampleStream childStream;
        private boolean sentEos;

        public ClippingSampleStream(SampleStream sampleStream) {
            this.childStream = sampleStream;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        public boolean isReady() {
            return !ClippingMediaPeriod.this.isPendingInitialDiscontinuity() && this.childStream.isReady();
        }

        public void maybeThrowError() throws IOException {
            this.childStream.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            if (this.sentEos) {
                decoderInputBuffer.setFlags(4);
                return -4;
            }
            int readData = this.childStream.readData(formatHolder, decoderInputBuffer, z);
            if (readData == -5) {
                Format format = formatHolder.format;
                if (!(format.encoderDelay == -1 && format.encoderPadding == -1)) {
                    int i = 0;
                    int i2 = ClippingMediaPeriod.this.startUs != 0 ? 0 : format.encoderDelay;
                    if (ClippingMediaPeriod.this.endUs == Long.MIN_VALUE) {
                        i = format.encoderPadding;
                    }
                    formatHolder.format = format.copyWithGaplessInfo(i2, i);
                }
                return -5;
            } else if (ClippingMediaPeriod.this.endUs == Long.MIN_VALUE || ((readData != -4 || decoderInputBuffer.timeUs < ClippingMediaPeriod.this.endUs) && !(readData == -3 && ClippingMediaPeriod.this.getBufferedPositionUs() == Long.MIN_VALUE))) {
                if (readData == -4 && !decoderInputBuffer.isEndOfStream()) {
                    decoderInputBuffer.timeUs -= ClippingMediaPeriod.this.startUs;
                }
                return readData;
            } else {
                decoderInputBuffer.clear();
                decoderInputBuffer.setFlags(4);
                this.sentEos = true;
                return -4;
            }
        }

        public int skipData(long j) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            return this.childStream.skipData(ClippingMediaPeriod.this.startUs + j);
        }
    }

    public ClippingMediaPeriod(MediaPeriod mediaPeriod2, boolean z) {
        this.mediaPeriod = mediaPeriod2;
        this.pendingInitialDiscontinuityPositionUs = z ? 0 : -9223372036854775807L;
        this.startUs = -9223372036854775807L;
        this.endUs = -9223372036854775807L;
    }

    public void setClipping(long j, long j2) {
        this.startUs = j;
        this.endUs = j2;
    }

    public void prepare(Callback callback2, long j) {
        this.callback = callback2;
        this.mediaPeriod.prepare(this, this.startUs + j);
    }

    public void maybeThrowPrepareError() throws IOException {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        this.sampleStreams = new ClippingSampleStream[sampleStreamArr2.length];
        SampleStream[] sampleStreamArr3 = new SampleStream[sampleStreamArr2.length];
        int i = 0;
        while (true) {
            SampleStream sampleStream = null;
            if (i >= sampleStreamArr2.length) {
                break;
            }
            this.sampleStreams[i] = (ClippingSampleStream) sampleStreamArr2[i];
            if (this.sampleStreams[i] != null) {
                sampleStream = this.sampleStreams[i].childStream;
            }
            sampleStreamArr3[i] = sampleStream;
            i++;
        }
        long selectTracks = this.mediaPeriod.selectTracks(trackSelectionArr, zArr, sampleStreamArr3, zArr2, j + this.startUs) - this.startUs;
        this.pendingInitialDiscontinuityPositionUs = (!isPendingInitialDiscontinuity() || j != 0 || !shouldKeepInitialDiscontinuity(this.startUs, trackSelectionArr)) ? -9223372036854775807L : selectTracks;
        Assertions.checkState(selectTracks == j || (selectTracks >= 0 && (this.endUs == Long.MIN_VALUE || this.startUs + selectTracks <= this.endUs)));
        for (int i2 = 0; i2 < sampleStreamArr2.length; i2++) {
            if (sampleStreamArr3[i2] == null) {
                this.sampleStreams[i2] = null;
            } else if (sampleStreamArr2[i2] == null || this.sampleStreams[i2].childStream != sampleStreamArr3[i2]) {
                this.sampleStreams[i2] = new ClippingSampleStream(sampleStreamArr3[i2]);
            }
            sampleStreamArr2[i2] = this.sampleStreams[i2];
        }
        return selectTracks;
    }

    public void discardBuffer(long j, boolean z) {
        this.mediaPeriod.discardBuffer(j + this.startUs, z);
    }

    public void reevaluateBuffer(long j) {
        this.mediaPeriod.reevaluateBuffer(j + this.startUs);
    }

    public long readDiscontinuity() {
        if (isPendingInitialDiscontinuity()) {
            long j = this.pendingInitialDiscontinuityPositionUs;
            this.pendingInitialDiscontinuityPositionUs = -9223372036854775807L;
            long readDiscontinuity = readDiscontinuity();
            if (readDiscontinuity != -9223372036854775807L) {
                j = readDiscontinuity;
            }
            return j;
        }
        long readDiscontinuity2 = this.mediaPeriod.readDiscontinuity();
        if (readDiscontinuity2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        boolean z = false;
        Assertions.checkState(readDiscontinuity2 >= this.startUs);
        if (this.endUs == Long.MIN_VALUE || readDiscontinuity2 <= this.endUs) {
            z = true;
        }
        Assertions.checkState(z);
        return readDiscontinuity2 - this.startUs;
    }

    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs == Long.MIN_VALUE || (this.endUs != Long.MIN_VALUE && bufferedPositionUs >= this.endUs)) {
            return Long.MIN_VALUE;
        }
        return Math.max(0, bufferedPositionUs - this.startUs);
    }

    public long seekToUs(long j) {
        ClippingSampleStream[] clippingSampleStreamArr;
        this.pendingInitialDiscontinuityPositionUs = -9223372036854775807L;
        boolean z = false;
        for (ClippingSampleStream clippingSampleStream : this.sampleStreams) {
            if (clippingSampleStream != null) {
                clippingSampleStream.clearSentEos();
            }
        }
        long j2 = j + this.startUs;
        long seekToUs = this.mediaPeriod.seekToUs(j2);
        if (seekToUs == j2 || (seekToUs >= this.startUs && (this.endUs == Long.MIN_VALUE || seekToUs <= this.endUs))) {
            z = true;
        }
        Assertions.checkState(z);
        return seekToUs - this.startUs;
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        if (j == this.startUs) {
            return 0;
        }
        long j2 = j + this.startUs;
        return this.mediaPeriod.getAdjustedSeekPositionUs(j2, clipSeekParameters(j2, seekParameters)) - this.startUs;
    }

    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE || (this.endUs != Long.MIN_VALUE && nextLoadPositionUs >= this.endUs)) {
            return Long.MIN_VALUE;
        }
        return nextLoadPositionUs - this.startUs;
    }

    public boolean continueLoading(long j) {
        return this.mediaPeriod.continueLoading(j + this.startUs);
    }

    public void onPrepared(MediaPeriod mediaPeriod2) {
        Assertions.checkState((this.startUs == -9223372036854775807L || this.endUs == -9223372036854775807L) ? false : true);
        this.callback.onPrepared(this);
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod2) {
        this.callback.onContinueLoadingRequested(this);
    }

    /* access modifiers changed from: 0000 */
    public boolean isPendingInitialDiscontinuity() {
        return this.pendingInitialDiscontinuityPositionUs != -9223372036854775807L;
    }

    private SeekParameters clipSeekParameters(long j, SeekParameters seekParameters) {
        long j2;
        long min = Math.min(j - this.startUs, seekParameters.toleranceBeforeUs);
        if (this.endUs == Long.MIN_VALUE) {
            j2 = seekParameters.toleranceAfterUs;
        } else {
            j2 = Math.min(this.endUs - j, seekParameters.toleranceAfterUs);
        }
        if (min == seekParameters.toleranceBeforeUs && j2 == seekParameters.toleranceAfterUs) {
            return seekParameters;
        }
        return new SeekParameters(min, j2);
    }

    private static boolean shouldKeepInitialDiscontinuity(long j, TrackSelection[] trackSelectionArr) {
        if (j != 0) {
            for (TrackSelection trackSelection : trackSelectionArr) {
                if (trackSelection != null && !MimeTypes.isAudio(trackSelection.getSelectedFormat().sampleMimeType)) {
                    return true;
                }
            }
        }
        return false;
    }
}
