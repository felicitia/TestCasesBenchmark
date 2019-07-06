package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.SeekMap.SeekPoints;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Util;

final class ConstantBitrateSeeker implements Seeker {
    private final int bitrate;
    private final long dataSize;
    private final long durationUs;
    private final long firstFramePosition;
    private final int frameSize;

    public ConstantBitrateSeeker(long j, long j2, MpegAudioHeader mpegAudioHeader) {
        this.firstFramePosition = j2;
        this.frameSize = mpegAudioHeader.frameSize;
        this.bitrate = mpegAudioHeader.bitrate;
        if (j == -1) {
            this.dataSize = -1;
            this.durationUs = -9223372036854775807L;
            return;
        }
        this.dataSize = j - j2;
        this.durationUs = getTimeUs(j);
    }

    public boolean isSeekable() {
        return this.dataSize != -1;
    }

    public SeekPoints getSeekPoints(long j) {
        if (this.dataSize == -1) {
            return new SeekPoints(new SeekPoint(0, this.firstFramePosition));
        }
        long constrainValue = Util.constrainValue((((((long) this.bitrate) * j) / 8000000) / ((long) this.frameSize)) * ((long) this.frameSize), 0, this.dataSize - ((long) this.frameSize));
        long j2 = this.firstFramePosition + constrainValue;
        long timeUs = getTimeUs(j2);
        SeekPoint seekPoint = new SeekPoint(timeUs, j2);
        if (timeUs >= j || constrainValue == this.dataSize - ((long) this.frameSize)) {
            return new SeekPoints(seekPoint);
        }
        long j3 = j2 + ((long) this.frameSize);
        return new SeekPoints(seekPoint, new SeekPoint(getTimeUs(j3), j3));
    }

    public long getTimeUs(long j) {
        return ((Math.max(0, j - this.firstFramePosition) * 1000000) * 8) / ((long) this.bitrate);
    }

    public long getDurationUs() {
        return this.durationUs;
    }
}
