package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;

public final class Mp3Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() {
        public Extractor[] createExtractors() {
            return new Extractor[]{new Mp3Extractor()};
        }
    };
    private static final int SEEK_HEADER_INFO = Util.getIntegerCodeForString("Info");
    private static final int SEEK_HEADER_VBRI = Util.getIntegerCodeForString("VBRI");
    private static final int SEEK_HEADER_XING = Util.getIntegerCodeForString("Xing");
    private long basisTimeUs;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private Metadata metadata;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private Seeker seeker;
    private final MpegAudioHeader synchronizedHeader;
    private int synchronizedHeaderData;
    private TrackOutput trackOutput;

    interface Seeker extends SeekMap {
        long getTimeUs(long j);
    }

    private static boolean headersMatch(int i, long j) {
        return ((long) (i & -128000)) == (j & -128000);
    }

    public void release() {
    }

    public Mp3Extractor() {
        this(0);
    }

    public Mp3Extractor(int i) {
        this(i, -9223372036854775807L);
    }

    public Mp3Extractor(int i, long j) {
        this.flags = i;
        this.forcedFirstSampleTimestampUs = j;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioHeader();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = -9223372036854775807L;
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return synchronize(extractorInput, true);
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        this.trackOutput = this.extractorOutput.track(0, 1);
        this.extractorOutput.endTracks();
    }

    public void seek(long j, long j2) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = -9223372036854775807L;
        this.samplesRead = 0;
        this.sampleBytesRemaining = 0;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        if (this.synchronizedHeaderData == 0) {
            try {
                synchronize(extractorInput, false);
            } catch (EOFException unused) {
                return -1;
            }
        } else {
            ExtractorInput extractorInput2 = extractorInput;
        }
        if (this.seeker == null) {
            this.seeker = maybeReadSeekFrame(extractorInput);
            if (this.seeker == null || (!this.seeker.isSeekable() && (this.flags & 1) != 0)) {
                this.seeker = getConstantBitrateSeeker(extractorInput);
            }
            this.extractorOutput.seekMap(this.seeker);
            this.trackOutput.format(Format.createAudioSampleFormat(null, this.synchronizedHeader.mimeType, null, -1, 4096, this.synchronizedHeader.channels, this.synchronizedHeader.sampleRate, -1, this.gaplessInfoHolder.encoderDelay, this.gaplessInfoHolder.encoderPadding, null, null, 0, null, (this.flags & 2) != 0 ? null : this.metadata));
        }
        return readSample(extractorInput);
    }

    private int readSample(ExtractorInput extractorInput) throws IOException, InterruptedException {
        if (this.sampleBytesRemaining == 0) {
            extractorInput.resetPeekPosition();
            if (!extractorInput.peekFully(this.scratch.data, 0, 4, true)) {
                return -1;
            }
            this.scratch.setPosition(0);
            int readInt = this.scratch.readInt();
            if (!headersMatch(readInt, (long) this.synchronizedHeaderData) || MpegAudioHeader.getFrameSize(readInt) == -1) {
                extractorInput.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
            MpegAudioHeader.populateHeader(readInt, this.synchronizedHeader);
            if (this.basisTimeUs == -9223372036854775807L) {
                this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                if (this.forcedFirstSampleTimestampUs != -9223372036854775807L) {
                    this.basisTimeUs += this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0);
                }
            }
            this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
        }
        int sampleData = this.trackOutput.sampleData(extractorInput, this.sampleBytesRemaining, true);
        if (sampleData == -1) {
            return -1;
        }
        this.sampleBytesRemaining -= sampleData;
        if (this.sampleBytesRemaining > 0) {
            return 0;
        }
        this.trackOutput.sampleMetadata(this.basisTimeUs + ((this.samplesRead * 1000000) / ((long) this.synchronizedHeader.sampleRate)), 1, this.synchronizedHeader.frameSize, 0, null);
        this.samplesRead += (long) this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    private boolean synchronize(ExtractorInput extractorInput, boolean z) throws IOException, InterruptedException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = z ? 16384 : 131072;
        extractorInput.resetPeekPosition();
        if (extractorInput.getPosition() == 0) {
            peekId3Data(extractorInput);
            int peekPosition = (int) extractorInput.getPeekPosition();
            if (!z) {
                extractorInput.skipFully(peekPosition);
            }
            i = peekPosition;
            i4 = 0;
            i3 = 0;
            i2 = 0;
        } else {
            i4 = 0;
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        while (true) {
            if (!extractorInput.peekFully(this.scratch.data, 0, 4, i4 > 0)) {
                break;
            }
            this.scratch.setPosition(0);
            int readInt = this.scratch.readInt();
            if (i3 == 0 || headersMatch(readInt, (long) i3)) {
                int frameSize = MpegAudioHeader.getFrameSize(readInt);
                if (frameSize != -1) {
                    i5 = i4 + 1;
                    if (i5 != 1) {
                        if (i5 == 4) {
                            break;
                        }
                    } else {
                        MpegAudioHeader.populateHeader(readInt, this.synchronizedHeader);
                        i3 = readInt;
                    }
                    extractorInput.advancePeekPosition(frameSize - 4);
                }
            }
            int i7 = i2 + 1;
            if (i2 != i6) {
                if (z) {
                    extractorInput.resetPeekPosition();
                    extractorInput.advancePeekPosition(i + i7);
                } else {
                    extractorInput.skipFully(1);
                }
                i2 = i7;
                i5 = 0;
                i3 = 0;
            } else if (z) {
                return false;
            } else {
                throw new ParserException("Searched too many bytes.");
            }
        }
        if (z) {
            extractorInput.skipFully(i + i2);
        } else {
            extractorInput.resetPeekPosition();
        }
        this.synchronizedHeaderData = i3;
        return true;
    }

    private void peekId3Data(ExtractorInput extractorInput) throws IOException, InterruptedException {
        int i = 0;
        while (true) {
            extractorInput.peekFully(this.scratch.data, 0, 10);
            this.scratch.setPosition(0);
            if (this.scratch.readUnsignedInt24() != Id3Decoder.ID3_TAG) {
                extractorInput.resetPeekPosition();
                extractorInput.advancePeekPosition(i);
                return;
            }
            this.scratch.skipBytes(3);
            int readSynchSafeInt = this.scratch.readSynchSafeInt();
            int i2 = readSynchSafeInt + 10;
            if (this.metadata == null) {
                byte[] bArr = new byte[i2];
                System.arraycopy(this.scratch.data, 0, bArr, 0, 10);
                extractorInput.peekFully(bArr, 10, readSynchSafeInt);
                this.metadata = new Id3Decoder((this.flags & 2) != 0 ? GaplessInfoHolder.GAPLESS_INFO_ID3_FRAME_PREDICATE : null).decode(bArr, i2);
                if (this.metadata != null) {
                    this.gaplessInfoHolder.setFromMetadata(this.metadata);
                }
            } else {
                extractorInput.advancePeekPosition(readSynchSafeInt);
            }
            i += i2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.Seeker maybeReadSeekFrame(com.google.android.exoplayer2.extractor.ExtractorInput r10) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r9 = this;
            com.google.android.exoplayer2.util.ParsableByteArray r5 = new com.google.android.exoplayer2.util.ParsableByteArray
            com.google.android.exoplayer2.extractor.MpegAudioHeader r0 = r9.synchronizedHeader
            int r0 = r0.frameSize
            r5.<init>(r0)
            byte[] r0 = r5.data
            com.google.android.exoplayer2.extractor.MpegAudioHeader r1 = r9.synchronizedHeader
            int r1 = r1.frameSize
            r6 = 0
            r10.peekFully(r0, r6, r1)
            com.google.android.exoplayer2.extractor.MpegAudioHeader r0 = r9.synchronizedHeader
            int r0 = r0.version
            r1 = 1
            r0 = r0 & r1
            r2 = 21
            if (r0 == 0) goto L_0x002b
            com.google.android.exoplayer2.extractor.MpegAudioHeader r0 = r9.synchronizedHeader
            int r0 = r0.channels
            if (r0 == r1) goto L_0x0028
            r2 = 36
            r7 = 36
            goto L_0x0036
        L_0x0028:
            r7 = 21
            goto L_0x0036
        L_0x002b:
            com.google.android.exoplayer2.extractor.MpegAudioHeader r0 = r9.synchronizedHeader
            int r0 = r0.channels
            if (r0 == r1) goto L_0x0032
            goto L_0x0028
        L_0x0032:
            r2 = 13
            r7 = 13
        L_0x0036:
            int r8 = getSeekFrameHeader(r5, r7)
            int r0 = SEEK_HEADER_XING
            if (r8 == r0) goto L_0x0062
            int r0 = SEEK_HEADER_INFO
            if (r8 != r0) goto L_0x0043
            goto L_0x0062
        L_0x0043:
            int r0 = SEEK_HEADER_VBRI
            if (r8 != r0) goto L_0x005d
            long r0 = r10.getLength()
            long r2 = r10.getPosition()
            com.google.android.exoplayer2.extractor.MpegAudioHeader r4 = r9.synchronizedHeader
            com.google.android.exoplayer2.extractor.mp3.VbriSeeker r0 = com.google.android.exoplayer2.extractor.mp3.VbriSeeker.create(r0, r2, r4, r5)
            com.google.android.exoplayer2.extractor.MpegAudioHeader r1 = r9.synchronizedHeader
            int r1 = r1.frameSize
            r10.skipFully(r1)
            goto L_0x00b2
        L_0x005d:
            r0 = 0
            r10.resetPeekPosition()
            goto L_0x00b2
        L_0x0062:
            long r0 = r10.getLength()
            long r2 = r10.getPosition()
            com.google.android.exoplayer2.extractor.MpegAudioHeader r4 = r9.synchronizedHeader
            com.google.android.exoplayer2.extractor.mp3.XingSeeker r0 = com.google.android.exoplayer2.extractor.mp3.XingSeeker.create(r0, r2, r4, r5)
            if (r0 == 0) goto L_0x009a
            com.google.android.exoplayer2.extractor.GaplessInfoHolder r1 = r9.gaplessInfoHolder
            boolean r1 = r1.hasGaplessInfo()
            if (r1 != 0) goto L_0x009a
            r10.resetPeekPosition()
            int r7 = r7 + 141
            r10.advancePeekPosition(r7)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r9.scratch
            byte[] r1 = r1.data
            r2 = 3
            r10.peekFully(r1, r6, r2)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r9.scratch
            r1.setPosition(r6)
            com.google.android.exoplayer2.extractor.GaplessInfoHolder r1 = r9.gaplessInfoHolder
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r9.scratch
            int r2 = r2.readUnsignedInt24()
            r1.setFromXingHeaderValue(r2)
        L_0x009a:
            com.google.android.exoplayer2.extractor.MpegAudioHeader r1 = r9.synchronizedHeader
            int r1 = r1.frameSize
            r10.skipFully(r1)
            if (r0 == 0) goto L_0x00b2
            boolean r1 = r0.isSeekable()
            if (r1 != 0) goto L_0x00b2
            int r1 = SEEK_HEADER_INFO
            if (r8 != r1) goto L_0x00b2
            com.google.android.exoplayer2.extractor.mp3.Mp3Extractor$Seeker r10 = r9.getConstantBitrateSeeker(r10)
            return r10
        L_0x00b2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.maybeReadSeekFrame(com.google.android.exoplayer2.extractor.ExtractorInput):com.google.android.exoplayer2.extractor.mp3.Mp3Extractor$Seeker");
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput extractorInput) throws IOException, InterruptedException {
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        MpegAudioHeader.populateHeader(this.scratch.readInt(), this.synchronizedHeader);
        ConstantBitrateSeeker constantBitrateSeeker = new ConstantBitrateSeeker(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader);
        return constantBitrateSeeker;
    }

    private static int getSeekFrameHeader(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.limit() >= i + 4) {
            parsableByteArray.setPosition(i);
            int readInt = parsableByteArray.readInt();
            if (readInt == SEEK_HEADER_XING || readInt == SEEK_HEADER_INFO) {
                return readInt;
            }
        }
        if (parsableByteArray.limit() >= 40) {
            parsableByteArray.setPosition(36);
            if (parsableByteArray.readInt() == SEEK_HEADER_VBRI) {
                return SEEK_HEADER_VBRI;
            }
        }
        return 0;
    }
}
