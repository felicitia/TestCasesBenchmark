package com.google.android.exoplayer2.extractor.ts;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.TrackIdGenerator;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;
import java.util.Collections;

public final class H262Reader implements ElementaryStreamReader {
    private static final double[] FRAME_RATE_VALUES = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private final CsdBuffer csdBuffer = new CsdBuffer(128);
    private String formatId;
    private long frameDurationUs;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final boolean[] prefixFlags = new boolean[4];
    private boolean sampleHasPicture;
    private boolean sampleIsKeyframe;
    private long samplePosition;
    private long sampleTimeUs;
    private boolean startedFirstSample;
    private long totalBytesWritten;

    private static final class CsdBuffer {
        private static final byte[] START_CODE = {0, 0, 1};
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

        public CsdBuffer(int i) {
            this.data = new byte[i];
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.sequenceExtensionPosition = 0;
        }

        public boolean onStartCode(int i, int i2) {
            if (this.isFilling) {
                this.length -= i2;
                if (this.sequenceExtensionPosition == 0 && i == 181) {
                    this.sequenceExtensionPosition = this.length;
                } else {
                    this.isFilling = false;
                    return true;
                }
            } else if (i == 179) {
                this.isFilling = true;
            }
            onData(START_CODE, 0, START_CODE.length);
            return false;
        }

        public void onData(byte[] bArr, int i, int i2) {
            if (this.isFilling) {
                int i3 = i2 - i;
                if (this.data.length < this.length + i3) {
                    this.data = Arrays.copyOf(this.data, (this.length + i3) * 2);
                }
                System.arraycopy(bArr, i, this.data, this.length, i3);
                this.length += i3;
            }
        }
    }

    public void packetFinished() {
    }

    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        this.totalBytesWritten = 0;
        this.startedFirstSample = false;
    }

    public void createTracks(ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
    }

    public void packetStarted(long j, boolean z) {
        this.pesTimeUs = j;
    }

    public void consume(ParsableByteArray parsableByteArray) {
        byte[] bArr;
        int i;
        boolean z;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] bArr2 = parsableByteArray2.data;
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray2, parsableByteArray.bytesLeft());
        while (true) {
            int findNalUnit = NalUnitUtil.findNalUnit(bArr2, position, limit, this.prefixFlags);
            if (findNalUnit == limit) {
                break;
            }
            int i2 = findNalUnit + 3;
            byte b = parsableByteArray2.data[i2] & 255;
            if (!this.hasOutputFormat) {
                int i3 = findNalUnit - position;
                if (i3 > 0) {
                    this.csdBuffer.onData(bArr2, position, findNalUnit);
                }
                if (this.csdBuffer.onStartCode(b, i3 < 0 ? -i3 : 0)) {
                    Pair parseCsdBuffer = parseCsdBuffer(this.csdBuffer, this.formatId);
                    this.output.format((Format) parseCsdBuffer.first);
                    this.frameDurationUs = ((Long) parseCsdBuffer.second).longValue();
                    this.hasOutputFormat = true;
                }
            }
            if (b == 0 || b == 179) {
                int i4 = limit - findNalUnit;
                if (!this.startedFirstSample || !this.sampleHasPicture || !this.hasOutputFormat) {
                    i = limit;
                    bArr = bArr2;
                } else {
                    i = limit;
                    bArr = bArr2;
                    this.output.sampleMetadata(this.sampleTimeUs, this.sampleIsKeyframe ? 1 : 0, ((int) (this.totalBytesWritten - this.samplePosition)) - i4, i4, null);
                }
                if (!this.startedFirstSample || this.sampleHasPicture) {
                    this.samplePosition = this.totalBytesWritten - ((long) i4);
                    long j = this.pesTimeUs != -9223372036854775807L ? this.pesTimeUs : this.startedFirstSample ? this.sampleTimeUs + this.frameDurationUs : 0;
                    this.sampleTimeUs = j;
                    z = false;
                    this.sampleIsKeyframe = false;
                    this.pesTimeUs = -9223372036854775807L;
                    this.startedFirstSample = true;
                } else {
                    z = false;
                }
                if (b == 0) {
                    z = true;
                }
                this.sampleHasPicture = z;
            } else {
                if (b == 184) {
                    this.sampleIsKeyframe = true;
                }
                i = limit;
                bArr = bArr2;
            }
            position = i2;
            limit = i;
            bArr2 = bArr;
        }
        if (!this.hasOutputFormat) {
            this.csdBuffer.onData(bArr2, position, limit);
        }
    }

    private static Pair<Format, Long> parseCsdBuffer(CsdBuffer csdBuffer2, String str) {
        float f;
        float f2;
        CsdBuffer csdBuffer3 = csdBuffer2;
        byte[] copyOf = Arrays.copyOf(csdBuffer3.data, csdBuffer3.length);
        byte b = copyOf[5] & 255;
        int i = ((copyOf[4] & 255) << 4) | (b >> 4);
        byte b2 = ((b & 15) << 8) | (copyOf[6] & 255);
        switch ((copyOf[7] & 240) >> 4) {
            case 2:
                f2 = ((float) (b2 * 4)) / ((float) (i * 3));
                break;
            case 3:
                f2 = ((float) (b2 * 16)) / ((float) (i * 9));
                break;
            case 4:
                f2 = ((float) (b2 * 121)) / ((float) (i * 100));
                break;
            default:
                f = 1.0f;
                break;
        }
        f = f2;
        Format createVideoSampleFormat = Format.createVideoSampleFormat(str, "video/mpeg2", null, -1, -1, i, b2, -1.0f, Collections.singletonList(copyOf), -1, f, null);
        long j = 0;
        int i2 = (copyOf[7] & 15) - 1;
        if (i2 >= 0 && i2 < FRAME_RATE_VALUES.length) {
            double d = FRAME_RATE_VALUES[i2];
            int i3 = csdBuffer3.sequenceExtensionPosition + 9;
            int i4 = (copyOf[i3] & 96) >> 5;
            byte b3 = copyOf[i3] & 31;
            if (i4 != b3) {
                d *= (((double) i4) + 1.0d) / ((double) (b3 + 1));
            }
            j = (long) (1000000.0d / d);
        }
        return Pair.create(createVideoSampleFormat, Long.valueOf(j));
    }
}