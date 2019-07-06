package com.google.android.exoplayer2.extractor.mp4;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class AtomParsers {
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_meta = Util.getIntegerCodeForString("meta");
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_text = Util.getIntegerCodeForString("text");
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            boolean z2 = true;
            if (parsableByteArray.readInt() != 1) {
                z2 = false;
            }
            Assertions.checkState(z2, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long j;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                j = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                j = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = j;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    private static final class StsdData {
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize = this.data.readUnsignedIntToInt();
        private final int sampleCount = this.data.readUnsignedIntToInt();

        public StszSampleSizeBox(LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            return this.fixedSampleSize == 0 ? this.data.readUnsignedIntToInt() : this.fixedSampleSize;
        }

        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize = (this.data.readUnsignedIntToInt() & 255);
        private final int sampleCount = this.data.readUnsignedIntToInt();
        private int sampleIndex;

        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            if (this.fieldSize == 8) {
                return this.data.readUnsignedByte();
            }
            if (this.fieldSize == 16) {
                return this.data.readUnsignedShort();
            }
            int i = this.sampleIndex;
            this.sampleIndex = i + 1;
            if (i % 2 != 0) {
                return this.currentByte & 15;
            }
            this.currentByte = this.data.readUnsignedByte();
            return (this.currentByte & 240) >> 4;
        }
    }

    private static final class TkhdData {
        /* access modifiers changed from: private */
        public final long duration;
        /* access modifiers changed from: private */
        public final int id;
        /* access modifiers changed from: private */
        public final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    public static Track parseTrak(ContainerAtom containerAtom, LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long j2;
        LeafAtom leafAtom2;
        long[] jArr;
        long[] jArr2;
        Track track;
        ContainerAtom containerAtom2 = containerAtom;
        ContainerAtom containerAtomOfType = containerAtom2.getContainerAtomOfType(Atom.TYPE_mdia);
        int parseHdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (parseHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(containerAtom2.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long j3 = -9223372036854775807L;
        if (j == -9223372036854775807L) {
            j2 = parseTkhd.duration;
            leafAtom2 = leafAtom;
        } else {
            leafAtom2 = leafAtom;
            j2 = j;
        }
        long parseMvhd = parseMvhd(leafAtom2.data);
        if (j2 != -9223372036854775807L) {
            j3 = Util.scaleLargeTimestamp(j2, 1000000, parseMvhd);
        }
        long j4 = j3;
        ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair parseMdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData parseStsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, parseTkhd.id, parseTkhd.rotationDegrees, (String) parseMdhd.second, drmInitData, z2);
        if (!z) {
            Pair parseEdts = parseEdts(containerAtom2.getContainerAtomOfType(Atom.TYPE_edts));
            jArr = (long[]) parseEdts.second;
            jArr2 = (long[]) parseEdts.first;
        } else {
            jArr2 = null;
            jArr = null;
        }
        if (parseStsd.format == null) {
            track = null;
        } else {
            int access$100 = parseTkhd.id;
            long longValue = ((Long) parseMdhd.first).longValue();
            Format format = parseStsd.format;
            int i = parseStsd.requiredSampleTransformation;
            TrackEncryptionBox[] trackEncryptionBoxArr = parseStsd.trackEncryptionBoxes;
            int i2 = parseStsd.nalUnitLengthFieldLength;
            track = new Track(access$100, parseHdlr, longValue, parseMvhd, j4, format, i, trackEncryptionBoxArr, i2, jArr2, jArr);
        }
        return track;
    }

    /* JADX WARNING: Removed duplicated region for block: B:143:0x0357  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0359  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0365  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x03bc  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x03be  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x03c2  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x03c5  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x03c9  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x03cc  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x03d0  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x03d2  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x03d6  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x03d9  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x03e6  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x04c2  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x04c4  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x04cb  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x04e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.extractor.mp4.TrackSampleTable parseStbl(com.google.android.exoplayer2.extractor.mp4.Track r56, com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom r57, com.google.android.exoplayer2.extractor.GaplessInfoHolder r58) throws com.google.android.exoplayer2.ParserException {
        /*
            r0 = r56
            r1 = r57
            r2 = r58
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stsz
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r3 = r1.getLeafAtomOfType(r3)
            if (r3 == 0) goto L_0x0014
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$StszSampleSizeBox r4 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$StszSampleSizeBox
            r4.<init>(r3)
            goto L_0x0029
        L_0x0014:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stz2
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r3 = r1.getLeafAtomOfType(r3)
            if (r3 != 0) goto L_0x0024
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "Track has no sample table size information"
            r0.<init>(r1)
            throw r0
        L_0x0024:
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$Stz2SampleSizeBox r4 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$Stz2SampleSizeBox
            r4.<init>(r3)
        L_0x0029:
            int r3 = r4.getSampleCount()
            r5 = 0
            if (r3 != 0) goto L_0x0045
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r0 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            long[] r7 = new long[r5]
            int[] r8 = new int[r5]
            r9 = 0
            long[] r10 = new long[r5]
            int[] r11 = new int[r5]
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12)
            return r0
        L_0x0045:
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stco
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r6 = r1.getLeafAtomOfType(r6)
            r7 = 1
            if (r6 != 0) goto L_0x0056
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_co64
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r6 = r1.getLeafAtomOfType(r6)
            r8 = 1
            goto L_0x0057
        L_0x0056:
            r8 = 0
        L_0x0057:
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r6.data
            int r9 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stsc
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r9 = r1.getLeafAtomOfType(r9)
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r9.data
            int r10 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stts
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r10 = r1.getLeafAtomOfType(r10)
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r10.data
            int r11 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stss
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r11 = r1.getLeafAtomOfType(r11)
            r12 = 0
            if (r11 == 0) goto L_0x0075
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r11.data
            goto L_0x0076
        L_0x0075:
            r11 = r12
        L_0x0076:
            int r13 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_ctts
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r1 = r1.getLeafAtomOfType(r13)
            if (r1 == 0) goto L_0x0081
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r1.data
            goto L_0x0082
        L_0x0081:
            r1 = r12
        L_0x0082:
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$ChunkIterator r13 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$ChunkIterator
            r13.<init>(r9, r6, r8)
            r6 = 12
            r10.setPosition(r6)
            int r8 = r10.readUnsignedIntToInt()
            int r8 = r8 - r7
            int r9 = r10.readUnsignedIntToInt()
            int r14 = r10.readUnsignedIntToInt()
            if (r1 == 0) goto L_0x00a3
            r1.setPosition(r6)
            int r15 = r1.readUnsignedIntToInt()
            goto L_0x00a4
        L_0x00a3:
            r15 = 0
        L_0x00a4:
            r16 = -1
            if (r11 == 0) goto L_0x00ba
            r11.setPosition(r6)
            int r6 = r11.readUnsignedIntToInt()
            if (r6 <= 0) goto L_0x00b8
            int r12 = r11.readUnsignedIntToInt()
            int r16 = r12 + -1
            goto L_0x00bb
        L_0x00b8:
            r11 = r12
            goto L_0x00bb
        L_0x00ba:
            r6 = 0
        L_0x00bb:
            boolean r12 = r4.isFixedSampleSize()
            if (r12 == 0) goto L_0x00d5
            java.lang.String r12 = "audio/raw"
            com.google.android.exoplayer2.Format r5 = r0.format
            java.lang.String r5 = r5.sampleMimeType
            boolean r5 = r12.equals(r5)
            if (r5 == 0) goto L_0x00d5
            if (r8 != 0) goto L_0x00d5
            if (r15 != 0) goto L_0x00d5
            if (r6 != 0) goto L_0x00d5
            r5 = 1
            goto L_0x00d6
        L_0x00d5:
            r5 = 0
        L_0x00d6:
            r18 = 0
            if (r5 != 0) goto L_0x0231
            long[] r5 = new long[r3]
            int[] r12 = new int[r3]
            long[] r7 = new long[r3]
            r20 = r6
            int[] r6 = new int[r3]
            r29 = r8
            r28 = r9
            r27 = r10
            r10 = r14
            r2 = r16
            r22 = r18
            r9 = r20
            r0 = 0
            r8 = 0
            r16 = 0
            r25 = 0
            r26 = 0
            r20 = r15
            r14 = r22
        L_0x00fd:
            if (r8 >= r3) goto L_0x01b1
        L_0x00ff:
            if (r25 != 0) goto L_0x011d
            r30 = r3
            boolean r3 = r13.moveNext()
            com.google.android.exoplayer2.util.Assertions.checkState(r3)
            r31 = r9
            r32 = r10
            long r9 = r13.offset
            int r3 = r13.numSamples
            r25 = r3
            r22 = r9
            r3 = r30
            r9 = r31
            r10 = r32
            goto L_0x00ff
        L_0x011d:
            r30 = r3
            r31 = r9
            r32 = r10
            if (r1 == 0) goto L_0x0136
        L_0x0125:
            if (r16 != 0) goto L_0x0134
            if (r20 <= 0) goto L_0x0134
            int r16 = r1.readUnsignedIntToInt()
            int r26 = r1.readInt()
            int r20 = r20 + -1
            goto L_0x0125
        L_0x0134:
            int r16 = r16 + -1
        L_0x0136:
            r3 = r26
            r5[r8] = r22
            int r9 = r4.readNextSampleSize()
            r12[r8] = r9
            r9 = r12[r8]
            if (r9 <= r0) goto L_0x0146
            r0 = r12[r8]
        L_0x0146:
            long r9 = (long) r3
            long r33 = r14 + r9
            r7[r8] = r33
            if (r11 != 0) goto L_0x014f
            r9 = 1
            goto L_0x0150
        L_0x014f:
            r9 = 0
        L_0x0150:
            r6[r8] = r9
            if (r8 != r2) goto L_0x0166
            r9 = 1
            r6[r8] = r9
            int r10 = r31 + -1
            if (r10 <= 0) goto L_0x0160
            int r2 = r11.readUnsignedIntToInt()
            int r2 = r2 - r9
        L_0x0160:
            r9 = r2
            r35 = r5
            r36 = r6
            goto L_0x016d
        L_0x0166:
            r9 = r2
            r35 = r5
            r36 = r6
            r10 = r31
        L_0x016d:
            r2 = r32
            long r5 = (long) r2
            long r31 = r14 + r5
            int r28 = r28 + -1
            if (r28 != 0) goto L_0x018c
            r5 = r29
            if (r5 <= 0) goto L_0x0189
            r6 = r27
            int r2 = r6.readUnsignedIntToInt()
            int r14 = r6.readInt()
            int r29 = r5 + -1
            r28 = r2
            goto L_0x0193
        L_0x0189:
            r6 = r27
            goto L_0x0190
        L_0x018c:
            r6 = r27
            r5 = r29
        L_0x0190:
            r14 = r2
            r29 = r5
        L_0x0193:
            r2 = r12[r8]
            r37 = r3
            long r2 = (long) r2
            long r26 = r22 + r2
            int r25 = r25 + -1
            int r8 = r8 + 1
            r2 = r9
            r9 = r10
            r10 = r14
            r22 = r26
            r3 = r30
            r14 = r31
            r5 = r35
            r26 = r37
            r27 = r6
            r6 = r36
            goto L_0x00fd
        L_0x01b1:
            r30 = r3
            r35 = r5
            r36 = r6
            r31 = r9
            r2 = r26
            r5 = r29
            long r2 = (long) r2
            long r8 = r14 + r2
            if (r16 != 0) goto L_0x01c4
            r2 = 1
            goto L_0x01c5
        L_0x01c4:
            r2 = 0
        L_0x01c5:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r2)
        L_0x01c8:
            if (r20 <= 0) goto L_0x01dc
            int r2 = r1.readUnsignedIntToInt()
            if (r2 != 0) goto L_0x01d2
            r2 = 1
            goto L_0x01d3
        L_0x01d2:
            r2 = 0
        L_0x01d3:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r2)
            r1.readInt()
            int r20 = r20 + -1
            goto L_0x01c8
        L_0x01dc:
            if (r31 != 0) goto L_0x01e9
            if (r28 != 0) goto L_0x01e9
            if (r25 != 0) goto L_0x01e9
            if (r5 == 0) goto L_0x01e5
            goto L_0x01e9
        L_0x01e5:
            r3 = r0
            r0 = r56
            goto L_0x022a
        L_0x01e9:
            java.lang.String r1 = "AtomParsers"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Inconsistent stbl box for track "
            r2.append(r3)
            r3 = r0
            r0 = r56
            int r4 = r0.id
            r2.append(r4)
            java.lang.String r4 = ": remainingSynchronizationSamples "
            r2.append(r4)
            r10 = r31
            r2.append(r10)
            java.lang.String r4 = ", remainingSamplesAtTimestampDelta "
            r2.append(r4)
            r4 = r28
            r2.append(r4)
            java.lang.String r4 = ", remainingSamplesInChunk "
            r2.append(r4)
            r4 = r25
            r2.append(r4)
            java.lang.String r4 = ", remainingTimestampDeltaChanges "
            r2.append(r4)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            android.util.Log.w(r1, r2)
        L_0x022a:
            r4 = r7
            r2 = r12
            r1 = r35
            r5 = r36
            goto L_0x0268
        L_0x0231:
            r30 = r3
            int r1 = r13.length
            long[] r1 = new long[r1]
            int r2 = r13.length
            int[] r2 = new int[r2]
        L_0x023b:
            boolean r3 = r13.moveNext()
            if (r3 == 0) goto L_0x024e
            int r3 = r13.index
            long r5 = r13.offset
            r1[r3] = r5
            int r3 = r13.index
            int r5 = r13.numSamples
            r2[r3] = r5
            goto L_0x023b
        L_0x024e:
            int r3 = r4.readNextSampleSize()
            long r4 = (long) r14
            com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker$Results r1 = com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker.rechunk(r3, r1, r2, r4)
            long[] r5 = r1.offsets
            int[] r12 = r1.sizes
            int r2 = r1.maximumSize
            long[] r7 = r1.timestamps
            int[] r6 = r1.flags
            long r8 = r1.duration
            r3 = r2
            r1 = r5
            r5 = r6
            r4 = r7
            r2 = r12
        L_0x0268:
            r24 = 1000000(0xf4240, double:4.940656E-318)
            long r6 = r0.timescale
            r22 = r8
            r26 = r6
            long r6 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r22, r24, r26)
            long[] r10 = r0.editListDurations
            if (r10 == 0) goto L_0x04fb
            r10 = r58
            boolean r13 = r58.hasGaplessInfo()
            if (r13 == 0) goto L_0x0283
            goto L_0x04fb
        L_0x0283:
            long[] r13 = r0.editListDurations
            int r13 = r13.length
            r14 = 1
            if (r13 != r14) goto L_0x0313
            int r13 = r0.type
            if (r13 != r14) goto L_0x0313
            int r13 = r4.length
            r14 = 2
            if (r13 < r14) goto L_0x0313
            long[] r13 = r0.editListMediaTimes
            r14 = 0
            r15 = r13[r14]
            long[] r13 = r0.editListDurations
            r20 = r13[r14]
            long r11 = r0.timescale
            r38 = r15
            long r14 = r0.movieTimescale
            r22 = r11
            r24 = r14
            long r11 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r20, r22, r24)
            long r15 = r38 + r11
            r11 = 0
            r12 = r4[r11]
            int r11 = (r12 > r38 ? 1 : (r12 == r38 ? 0 : -1))
            if (r11 > 0) goto L_0x0313
            r11 = 1
            r12 = r4[r11]
            int r14 = (r38 > r12 ? 1 : (r38 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x0313
            int r12 = r4.length
            int r12 = r12 - r11
            r11 = r4[r12]
            int r13 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r13 >= 0) goto L_0x0313
            int r11 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r11 > 0) goto L_0x0313
            long r20 = r8 - r15
            r11 = 0
            r12 = r4[r11]
            long r22 = r38 - r12
            com.google.android.exoplayer2.Format r11 = r0.format
            int r11 = r11.sampleRate
            long r11 = (long) r11
            long r13 = r0.timescale
            r24 = r11
            r26 = r13
            long r11 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r22, r24, r26)
            com.google.android.exoplayer2.Format r13 = r0.format
            int r13 = r13.sampleRate
            long r13 = (long) r13
            r40 = r8
            long r8 = r0.timescale
            r22 = r13
            r24 = r8
            long r8 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r20, r22, r24)
            int r13 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1))
            if (r13 != 0) goto L_0x02f3
            int r13 = (r8 > r18 ? 1 : (r8 == r18 ? 0 : -1))
            if (r13 == 0) goto L_0x0315
        L_0x02f3:
            r13 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 > 0) goto L_0x0315
            int r15 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r15 > 0) goto L_0x0315
            int r11 = (int) r11
            r10.encoderDelay = r11
            int r8 = (int) r8
            r10.encoderPadding = r8
            long r8 = r0.timescale
            r10 = 1000000(0xf4240, double:4.940656E-318)
            com.google.android.exoplayer2.util.Util.scaleLargeTimestampsInPlace(r4, r10, r8)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r8 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r8
        L_0x0313:
            r40 = r8
        L_0x0315:
            long[] r8 = r0.editListDurations
            int r8 = r8.length
            r9 = 1
            if (r8 != r9) goto L_0x0352
            long[] r8 = r0.editListDurations
            r9 = 0
            r10 = r8[r9]
            int r8 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1))
            if (r8 != 0) goto L_0x0352
            long[] r6 = r0.editListMediaTimes
            r7 = r6[r9]
            r6 = 0
        L_0x0329:
            int r9 = r4.length
            if (r6 >= r9) goto L_0x033f
            r9 = r4[r6]
            long r11 = r9 - r7
            r13 = 1000000(0xf4240, double:4.940656E-318)
            long r9 = r0.timescale
            r15 = r9
            long r9 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r11, r13, r15)
            r4[r6] = r9
            int r6 = r6 + 1
            goto L_0x0329
        L_0x033f:
            r6 = 0
            long r9 = r40 - r7
            r11 = 1000000(0xf4240, double:4.940656E-318)
            long r13 = r0.timescale
            long r6 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r9, r11, r13)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r8 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r8
        L_0x0352:
            int r8 = r0.type
            r9 = 1
            if (r8 != r9) goto L_0x0359
            r8 = 1
            goto L_0x035a
        L_0x0359:
            r8 = 0
        L_0x035a:
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
        L_0x035e:
            long[] r13 = r0.editListDurations
            int r13 = r13.length
            r14 = -1
            if (r9 >= r13) goto L_0x03b0
            long[] r13 = r0.editListMediaTimes
            r42 = r6
            r6 = r13[r9]
            int r13 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r13 == 0) goto L_0x039d
            long[] r13 = r0.editListDurations
            r20 = r13[r9]
            long r13 = r0.timescale
            r44 = r2
            r45 = r3
            long r2 = r0.movieTimescale
            r22 = r13
            r24 = r2
            long r2 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r20, r22, r24)
            r13 = 1
            int r14 = com.google.android.exoplayer2.util.Util.binarySearchCeil(r4, r6, r13, r13)
            r46 = r1
            long r0 = r6 + r2
            r2 = 0
            int r0 = com.google.android.exoplayer2.util.Util.binarySearchCeil(r4, r0, r8, r2)
            int r1 = r0 - r14
            int r11 = r11 + r1
            if (r12 == r14) goto L_0x0398
            r1 = 1
            goto L_0x0399
        L_0x0398:
            r1 = 0
        L_0x0399:
            r1 = r1 | r10
            r12 = r0
            r10 = r1
            goto L_0x03a3
        L_0x039d:
            r46 = r1
            r44 = r2
            r45 = r3
        L_0x03a3:
            int r9 = r9 + 1
            r6 = r42
            r2 = r44
            r3 = r45
            r1 = r46
            r0 = r56
            goto L_0x035e
        L_0x03b0:
            r46 = r1
            r44 = r2
            r45 = r3
            r42 = r6
            r0 = r30
            if (r11 == r0) goto L_0x03be
            r0 = 1
            goto L_0x03bf
        L_0x03be:
            r0 = 0
        L_0x03bf:
            r0 = r0 | r10
            if (r0 == 0) goto L_0x03c5
            long[] r1 = new long[r11]
            goto L_0x03c7
        L_0x03c5:
            r1 = r46
        L_0x03c7:
            if (r0 == 0) goto L_0x03cc
            int[] r2 = new int[r11]
            goto L_0x03ce
        L_0x03cc:
            r2 = r44
        L_0x03ce:
            if (r0 == 0) goto L_0x03d2
            r3 = 0
            goto L_0x03d4
        L_0x03d2:
            r3 = r45
        L_0x03d4:
            if (r0 == 0) goto L_0x03d9
            int[] r6 = new int[r11]
            goto L_0x03da
        L_0x03d9:
            r6 = r5
        L_0x03da:
            long[] r7 = new long[r11]
            r11 = r3
            r3 = r56
            r9 = 0
            r10 = 0
        L_0x03e1:
            long[] r12 = r3.editListDurations
            int r12 = r12.length
            if (r9 >= r12) goto L_0x049d
            long[] r12 = r3.editListMediaTimes
            r14 = r12[r9]
            long[] r12 = r3.editListDurations
            r26 = r12[r9]
            r12 = -1
            int r16 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r16 == 0) goto L_0x047a
            long r12 = r3.timescale
            r47 = r5
            r48 = r6
            long r5 = r3.movieTimescale
            r20 = r26
            r22 = r12
            r24 = r5
            long r5 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r20, r22, r24)
            long r12 = r14 + r5
            r5 = 1
            int r6 = com.google.android.exoplayer2.util.Util.binarySearchCeil(r4, r14, r5, r5)
            r5 = 0
            int r12 = com.google.android.exoplayer2.util.Util.binarySearchCeil(r4, r12, r8, r5)
            if (r0 == 0) goto L_0x042e
            int r13 = r12 - r6
            r5 = r46
            java.lang.System.arraycopy(r5, r6, r1, r10, r13)
            r49 = r8
            r8 = r44
            java.lang.System.arraycopy(r8, r6, r2, r10, r13)
            r51 = r1
            r50 = r11
            r11 = r47
            r1 = r48
            java.lang.System.arraycopy(r11, r6, r1, r10, r13)
            goto L_0x043c
        L_0x042e:
            r51 = r1
            r49 = r8
            r50 = r11
            r8 = r44
            r5 = r46
            r11 = r47
            r1 = r48
        L_0x043c:
            r13 = r50
        L_0x043e:
            if (r6 >= r12) goto L_0x0476
            r22 = 1000000(0xf4240, double:4.940656E-318)
            r53 = r11
            r52 = r12
            long r11 = r3.movieTimescale
            r20 = r18
            r24 = r11
            long r11 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r20, r22, r24)
            r16 = r4[r6]
            long r20 = r16 - r14
            r54 = r14
            long r14 = r3.timescale
            r24 = r14
            long r14 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r20, r22, r24)
            long r16 = r11 + r14
            r7[r10] = r16
            if (r0 == 0) goto L_0x046b
            r11 = r2[r10]
            if (r11 <= r13) goto L_0x046b
            r13 = r8[r6]
        L_0x046b:
            int r10 = r10 + 1
            int r6 = r6 + 1
            r12 = r52
            r11 = r53
            r14 = r54
            goto L_0x043e
        L_0x0476:
            r53 = r11
            r11 = r13
            goto L_0x0487
        L_0x047a:
            r51 = r1
            r53 = r5
            r1 = r6
            r49 = r8
            r50 = r11
            r8 = r44
            r5 = r46
        L_0x0487:
            r6 = 0
            long r12 = r18 + r26
            int r9 = r9 + 1
            r6 = r1
            r46 = r5
            r44 = r8
            r18 = r12
            r8 = r49
            r1 = r51
            r5 = r53
            r14 = -1
            goto L_0x03e1
        L_0x049d:
            r51 = r1
            r53 = r5
            r1 = r6
            r50 = r11
            r8 = r44
            r5 = r46
            r22 = 1000000(0xf4240, double:4.940656E-318)
            long r9 = r3.timescale
            r20 = r18
            r24 = r9
            long r26 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r20, r22, r24)
            r0 = 0
            r6 = 0
        L_0x04b7:
            int r9 = r1.length
            if (r0 >= r9) goto L_0x04c9
            if (r6 != 0) goto L_0x04c9
            r9 = r1[r0]
            r10 = 1
            r9 = r9 & r10
            if (r9 == 0) goto L_0x04c4
            r9 = 1
            goto L_0x04c5
        L_0x04c4:
            r9 = 0
        L_0x04c5:
            r6 = r6 | r9
            int r0 = r0 + 1
            goto L_0x04b7
        L_0x04c9:
            if (r6 != 0) goto L_0x04e9
            java.lang.String r0 = "AtomParsers"
            java.lang.String r1 = "Ignoring edit list: Edited sample sequence does not contain a sync sample."
            android.util.Log.w(r0, r1)
            long r0 = r3.timescale
            r2 = 1000000(0xf4240, double:4.940656E-318)
            com.google.android.exoplayer2.util.Util.scaleLargeTimestampsInPlace(r4, r2, r0)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r9
            r1 = r5
            r2 = r8
            r3 = r45
            r5 = r53
            r6 = r42
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r9
        L_0x04e9:
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r0 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r20 = r0
            r21 = r51
            r22 = r2
            r23 = r50
            r24 = r7
            r25 = r1
            r20.<init>(r21, r22, r23, r24, r25, r26)
            return r0
        L_0x04fb:
            r8 = r2
            r45 = r3
            r53 = r5
            r42 = r6
            r3 = r0
            r5 = r1
            long r0 = r3.timescale
            r2 = 1000000(0xf4240, double:4.940656E-318)
            com.google.android.exoplayer2.util.Util.scaleLargeTimestampsInPlace(r4, r2, r0)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r9
            r1 = r5
            r2 = r8
            r3 = r45
            r5 = r53
            r6 = r42
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseStbl(com.google.android.exoplayer2.extractor.mp4.Track, com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom, com.google.android.exoplayer2.extractor.GaplessInfoHolder):com.google.android.exoplayer2.extractor.mp4.TrackSampleTable");
    }

    public static Metadata parseUdta(LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(position);
                return parseMetaAtom(parsableByteArray, position + readInt);
            }
            parsableByteArray.skipBytes(readInt - 8);
        }
        return null;
    }

    private static Metadata parseMetaAtom(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.skipBytes(readInt - 8);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Entry>) arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i = 16;
        }
        parsableByteArray.skipBytes(i);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                z = true;
                break;
            } else if (parsableByteArray.data[position + i3] != -1) {
                z = false;
                break;
            } else {
                i3++;
            }
        }
        long j = -9223372036854775807L;
        if (z) {
            parsableByteArray.skipBytes(i);
        } else {
            long readUnsignedInt = parseFullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (readUnsignedInt != 0) {
                j = readUnsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && readInt4 == -65536 && readInt5 == 0) {
            i2 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && readInt4 == 65536 && readInt5 == 0) {
            i2 = 270;
        } else if (readInt2 == -65536 && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i2 = 180;
        }
        return new TkhdData(readInt, j, i2);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        int readInt = parsableByteArray.readInt();
        if (readInt == TYPE_soun) {
            return 1;
        }
        if (readInt == TYPE_vide) {
            return 2;
        }
        if (readInt == TYPE_text || readInt == TYPE_sbtl || readInt == TYPE_subt || readInt == TYPE_clcp) {
            return 3;
        }
        return readInt == TYPE_meta ? 4 : -1;
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        parsableByteArray.skipBytes(i);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((char) (((readUnsignedShort >> 10) & 31) + 96));
        sb.append((char) (((readUnsignedShort >> 5) & 31) + 96));
        sb.append((char) ((readUnsignedShort & 31) + 96));
        return Pair.create(Long.valueOf(readUnsignedInt), sb.toString());
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i3 = 0; i3 < readInt; i3++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            Assertions.checkArgument(readInt2 > 0, "childAtomSize should be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == Atom.TYPE_avc1 || readInt3 == Atom.TYPE_avc3 || readInt3 == Atom.TYPE_encv || readInt3 == Atom.TYPE_mp4v || readInt3 == Atom.TYPE_hvc1 || readInt3 == Atom.TYPE_hev1 || readInt3 == Atom.TYPE_s263 || readInt3 == Atom.TYPE_vp08 || readInt3 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray2, readInt3, position, readInt2, i, i2, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_mp4a || readInt3 == Atom.TYPE_enca || readInt3 == Atom.TYPE_ac_3 || readInt3 == Atom.TYPE_ec_3 || readInt3 == Atom.TYPE_dtsc || readInt3 == Atom.TYPE_dtse || readInt3 == Atom.TYPE_dtsh || readInt3 == Atom.TYPE_dtsl || readInt3 == Atom.TYPE_samr || readInt3 == Atom.TYPE_sawb || readInt3 == Atom.TYPE_lpcm || readInt3 == Atom.TYPE_sowt || readInt3 == Atom.TYPE__mp3 || readInt3 == Atom.TYPE_alac) {
                parseAudioSampleEntry(parsableByteArray2, readInt3, position, readInt2, i, str, z, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_TTML || readInt3 == Atom.TYPE_tx3g || readInt3 == Atom.TYPE_wvtt || readInt3 == Atom.TYPE_stpp || readInt3 == Atom.TYPE_c608) {
                parseTextSampleEntry(parsableByteArray2, readInt3, position, readInt2, i, str, stsdData);
            } else if (readInt3 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), "application/x-camera-motion", null, -1, null);
            }
            parsableByteArray2.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) throws ParserException {
        String str2;
        String str3;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i5 = i;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition(i2 + 8 + 8);
        List list = null;
        long j = Long.MAX_VALUE;
        if (i5 == Atom.TYPE_TTML) {
            str2 = "application/ttml+xml";
        } else if (i5 == Atom.TYPE_tx3g) {
            int i6 = (i3 - 8) - 8;
            byte[] bArr = new byte[i6];
            parsableByteArray2.readBytes(bArr, 0, i6);
            list = Collections.singletonList(bArr);
            str3 = "application/x-quicktime-tx3g";
            stsdData2.format = Format.createTextSampleFormat(Integer.toString(i4), str3, null, -1, 0, str, -1, null, j, list);
        } else if (i5 == Atom.TYPE_wvtt) {
            str2 = "application/x-mp4-vtt";
        } else if (i5 == Atom.TYPE_stpp) {
            str2 = "application/ttml+xml";
            j = 0;
        } else if (i5 == Atom.TYPE_c608) {
            str2 = "application/x-mp4-cea-608";
            stsdData2.requiredSampleTransformation = 1;
        } else {
            throw new IllegalStateException();
        }
        str3 = str2;
        stsdData2.format = Format.createTextSampleFormat(Integer.toString(i4), str3, null, -1, 0, str, -1, null, j, list);
    }

    /* JADX WARNING: Removed duplicated region for block: B:74:0x014a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x014b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void parseVideoSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray r22, int r23, int r24, int r25, int r26, int r27, com.google.android.exoplayer2.drm.DrmInitData r28, com.google.android.exoplayer2.extractor.mp4.AtomParsers.StsdData r29, int r30) throws com.google.android.exoplayer2.ParserException {
        /*
            r0 = r22
            r1 = r24
            r2 = r25
            r3 = r28
            r4 = r29
            int r5 = r1 + 8
            int r5 = r5 + 8
            r0.setPosition(r5)
            r5 = 16
            r0.skipBytes(r5)
            int r11 = r22.readUnsignedShort()
            int r12 = r22.readUnsignedShort()
            r5 = 50
            r0.skipBytes(r5)
            int r5 = r22.getPosition()
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_encv
            r7 = 0
            r8 = r23
            if (r8 != r6) goto L_0x0055
            android.util.Pair r6 = parseSampleEntryEncryptionData(r0, r1, r2)
            if (r6 == 0) goto L_0x0052
            java.lang.Object r8 = r6.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            if (r3 != 0) goto L_0x0040
            r3 = r7
            goto L_0x004a
        L_0x0040:
            java.lang.Object r9 = r6.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r9 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r9
            java.lang.String r9 = r9.schemeType
            com.google.android.exoplayer2.drm.DrmInitData r3 = r3.copyWithSchemeType(r9)
        L_0x004a:
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox[] r9 = r4.trackEncryptionBoxes
            java.lang.Object r6 = r6.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r6 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r6
            r9[r30] = r6
        L_0x0052:
            r0.setPosition(r5)
        L_0x0055:
            r20 = r3
            r3 = -1
            r9 = 1065353216(0x3f800000, float:1.0)
            r14 = r7
            r17 = r14
            r3 = 0
            r16 = 1065353216(0x3f800000, float:1.0)
            r18 = -1
        L_0x0062:
            int r9 = r5 - r1
            if (r9 >= r2) goto L_0x0148
            r0.setPosition(r5)
            int r9 = r22.getPosition()
            int r10 = r22.readInt()
            if (r10 != 0) goto L_0x007c
            int r13 = r22.getPosition()
            int r13 = r13 - r1
            if (r13 != r2) goto L_0x007c
            goto L_0x0148
        L_0x007c:
            if (r10 <= 0) goto L_0x0080
            r15 = 1
            goto L_0x0081
        L_0x0080:
            r15 = 0
        L_0x0081:
            java.lang.String r6 = "childAtomSize should be positive"
            com.google.android.exoplayer2.util.Assertions.checkArgument(r15, r6)
            int r6 = r22.readInt()
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_avcC
            r13 = 3
            if (r6 != r15) goto L_0x00b0
            if (r7 != 0) goto L_0x0093
            r6 = 1
            goto L_0x0094
        L_0x0093:
            r6 = 0
        L_0x0094:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            java.lang.String r7 = "video/avc"
            int r9 = r9 + 8
            r0.setPosition(r9)
            com.google.android.exoplayer2.video.AvcConfig r6 = com.google.android.exoplayer2.video.AvcConfig.parse(r22)
            java.util.List<byte[]> r14 = r6.initializationData
            int r9 = r6.nalUnitLengthFieldLength
            r4.nalUnitLengthFieldLength = r9
            if (r3 != 0) goto L_0x0145
            float r6 = r6.pixelWidthAspectRatio
            r16 = r6
            goto L_0x0145
        L_0x00b0:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_hvcC
            if (r6 != r15) goto L_0x00cf
            if (r7 != 0) goto L_0x00b8
            r6 = 1
            goto L_0x00b9
        L_0x00b8:
            r6 = 0
        L_0x00b9:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            java.lang.String r7 = "video/hevc"
            int r9 = r9 + 8
            r0.setPosition(r9)
            com.google.android.exoplayer2.video.HevcConfig r6 = com.google.android.exoplayer2.video.HevcConfig.parse(r22)
            java.util.List<byte[]> r14 = r6.initializationData
            int r6 = r6.nalUnitLengthFieldLength
            r4.nalUnitLengthFieldLength = r6
            goto L_0x0145
        L_0x00cf:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_vpcC
            if (r6 != r15) goto L_0x00e7
            if (r7 != 0) goto L_0x00d7
            r6 = 1
            goto L_0x00d8
        L_0x00d7:
            r6 = 0
        L_0x00d8:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_vp08
            if (r8 != r6) goto L_0x00e4
            java.lang.String r6 = "video/x-vnd.on2.vp8"
        L_0x00e1:
            r7 = r6
            goto L_0x0145
        L_0x00e4:
            java.lang.String r6 = "video/x-vnd.on2.vp9"
            goto L_0x00e1
        L_0x00e7:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_d263
            if (r6 != r15) goto L_0x00f6
            if (r7 != 0) goto L_0x00ef
            r6 = 1
            goto L_0x00f0
        L_0x00ef:
            r6 = 0
        L_0x00f0:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            java.lang.String r7 = "video/3gpp"
            goto L_0x0145
        L_0x00f6:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_esds
            if (r6 != r15) goto L_0x0111
            if (r7 != 0) goto L_0x00fe
            r6 = 1
            goto L_0x00ff
        L_0x00fe:
            r6 = 0
        L_0x00ff:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            android.util.Pair r6 = parseEsdsFromParent(r0, r9)
            java.lang.Object r7 = r6.first
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r6 = r6.second
            java.util.List r14 = java.util.Collections.singletonList(r6)
            goto L_0x0145
        L_0x0111:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_pasp
            if (r6 != r15) goto L_0x011b
            float r16 = parsePaspFromParent(r0, r9)
            r3 = 1
            goto L_0x0145
        L_0x011b:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_sv3d
            if (r6 != r15) goto L_0x0124
            byte[] r17 = parseProjFromParent(r0, r9, r10)
            goto L_0x0145
        L_0x0124:
            int r9 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_st3d
            if (r6 != r9) goto L_0x0145
            int r6 = r22.readUnsignedByte()
            r0.skipBytes(r13)
            if (r6 != 0) goto L_0x0145
            int r6 = r22.readUnsignedByte()
            switch(r6) {
                case 0: goto L_0x0143;
                case 1: goto L_0x0140;
                case 2: goto L_0x013c;
                case 3: goto L_0x0139;
                default: goto L_0x0138;
            }
        L_0x0138:
            goto L_0x0145
        L_0x0139:
            r18 = 3
            goto L_0x0145
        L_0x013c:
            r6 = 2
            r18 = 2
            goto L_0x0145
        L_0x0140:
            r18 = 1
            goto L_0x0145
        L_0x0143:
            r18 = 0
        L_0x0145:
            int r5 = r5 + r10
            goto L_0x0062
        L_0x0148:
            if (r7 != 0) goto L_0x014b
            return
        L_0x014b:
            java.lang.String r6 = java.lang.Integer.toString(r26)
            r8 = 0
            r9 = -1
            r10 = -1
            r13 = -1082130432(0xffffffffbf800000, float:-1.0)
            r19 = 0
            r15 = r27
            com.google.android.exoplayer2.Format r0 = com.google.android.exoplayer2.Format.createVideoSampleFormat(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r4.format = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseVideoSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray, int, int, int, int, int, com.google.android.exoplayer2.drm.DrmInitData, com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    private static Pair<long[], long[]> parseEdts(ContainerAtom containerAtom) {
        if (containerAtom != null) {
            LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst);
            if (leafAtomOfType != null) {
                ParsableByteArray parsableByteArray = leafAtomOfType.data;
                parsableByteArray.setPosition(8);
                int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                long[] jArr = new long[readUnsignedIntToInt];
                long[] jArr2 = new long[readUnsignedIntToInt];
                for (int i = 0; i < readUnsignedIntToInt; i++) {
                    jArr[i] = parseFullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
                    jArr2[i] = parseFullAtomVersion == 1 ? parsableByteArray.readLong() : (long) parsableByteArray.readInt();
                    if (parsableByteArray.readShort() != 1) {
                        throw new IllegalArgumentException("Unsupported media rate.");
                    }
                    parsableByteArray.skipBytes(2);
                }
                return Pair.create(jArr, jArr2);
            }
        }
        return Pair.create(null, null);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) throws ParserException {
        int i6;
        int i7;
        int i8;
        List list;
        String str2;
        DrmInitData drmInitData2;
        int i9;
        byte[] bArr;
        StsdData stsdData2;
        int i10;
        String str3;
        byte[] bArr2;
        int i11;
        int i12;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i13 = i2;
        int i14 = i3;
        String str4 = str;
        DrmInitData drmInitData3 = drmInitData;
        StsdData stsdData3 = stsdData;
        parsableByteArray2.setPosition(i13 + 8 + 8);
        if (z) {
            i6 = parsableByteArray.readUnsignedShort();
            parsableByteArray2.skipBytes(6);
        } else {
            parsableByteArray2.skipBytes(8);
            i6 = 0;
        }
        if (i6 == 0 || i6 == 1) {
            int readUnsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray2.skipBytes(6);
            i8 = parsableByteArray.readUnsignedFixedPoint1616();
            if (i6 == 1) {
                parsableByteArray2.skipBytes(16);
            }
            i7 = readUnsignedShort;
        } else if (i6 == 2) {
            parsableByteArray2.skipBytes(16);
            i8 = (int) Math.round(parsableByteArray.readDouble());
            i7 = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray2.skipBytes(20);
        } else {
            return;
        }
        int position = parsableByteArray.getPosition();
        int i15 = i;
        if (i15 == Atom.TYPE_enca) {
            Pair parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray2, i13, i14);
            if (parseSampleEntryEncryptionData != null) {
                i15 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData3 == null) {
                    drmInitData3 = null;
                } else {
                    drmInitData3 = drmInitData3.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData3.trackEncryptionBoxes[i5] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray2.setPosition(position);
        }
        DrmInitData drmInitData4 = drmInitData3;
        String str5 = i15 == Atom.TYPE_ac_3 ? "audio/ac3" : i15 == Atom.TYPE_ec_3 ? "audio/eac3" : i15 == Atom.TYPE_dtsc ? "audio/vnd.dts" : (i15 == Atom.TYPE_dtsh || i15 == Atom.TYPE_dtsl) ? "audio/vnd.dts.hd" : i15 == Atom.TYPE_dtse ? "audio/vnd.dts.hd;profile=lbr" : i15 == Atom.TYPE_samr ? "audio/3gpp" : i15 == Atom.TYPE_sawb ? "audio/amr-wb" : (i15 == Atom.TYPE_lpcm || i15 == Atom.TYPE_sowt) ? "audio/raw" : i15 == Atom.TYPE__mp3 ? "audio/mpeg" : i15 == Atom.TYPE_alac ? "audio/alac" : null;
        String str6 = str5;
        int i16 = i8;
        int i17 = i7;
        int i18 = position;
        byte[] bArr3 = null;
        while (i18 - i13 < i14) {
            parsableByteArray2.setPosition(i18);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_esds || (z && readInt2 == Atom.TYPE_wave)) {
                byte[] bArr4 = bArr3;
                String str7 = str6;
                i9 = i18;
                drmInitData2 = drmInitData4;
                stsdData2 = stsdData3;
                if (readInt2 == Atom.TYPE_esds) {
                    i10 = i9;
                } else {
                    i10 = findEsdsPosition(parsableByteArray2, i9, readInt);
                }
                if (i10 != -1) {
                    Pair parseEsdsFromParent = parseEsdsFromParent(parsableByteArray2, i10);
                    str3 = (String) parseEsdsFromParent.first;
                    bArr = (byte[]) parseEsdsFromParent.second;
                    if ("audio/mp4a-latm".equals(str3)) {
                        Pair parseAacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                        i16 = ((Integer) parseAacAudioSpecificConfig.first).intValue();
                        i17 = ((Integer) parseAacAudioSpecificConfig.second).intValue();
                    }
                } else {
                    str3 = str7;
                    bArr = bArr4;
                }
                str2 = str3;
            } else {
                if (readInt2 == Atom.TYPE_dac3) {
                    parsableByteArray2.setPosition(i18 + 8);
                    stsdData3.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray2, Integer.toString(i4), str4, drmInitData4);
                } else if (readInt2 == Atom.TYPE_dec3) {
                    parsableByteArray2.setPosition(i18 + 8);
                    stsdData3.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray2, Integer.toString(i4), str4, drmInitData4);
                } else {
                    if (readInt2 == Atom.TYPE_ddts) {
                        i11 = readInt;
                        str2 = str6;
                        i12 = i18;
                        drmInitData2 = drmInitData4;
                        bArr2 = bArr3;
                        stsdData2 = stsdData3;
                        stsdData2.format = Format.createAudioSampleFormat(Integer.toString(i4), str6, null, -1, -1, i17, i16, null, drmInitData2, 0, str4);
                    } else {
                        i11 = readInt;
                        bArr2 = bArr3;
                        str2 = str6;
                        i12 = i18;
                        drmInitData2 = drmInitData4;
                        stsdData2 = stsdData3;
                        if (readInt2 == Atom.TYPE_alac) {
                            readInt = i11;
                            bArr = new byte[readInt];
                            i9 = i12;
                            parsableByteArray2.setPosition(i9);
                            parsableByteArray2.readBytes(bArr, 0, readInt);
                        }
                    }
                    readInt = i11;
                    i9 = i12;
                    bArr = bArr2;
                }
                bArr2 = bArr3;
                str2 = str6;
                i9 = i18;
                drmInitData2 = drmInitData4;
                stsdData2 = stsdData3;
                bArr = bArr2;
            }
            i18 = i9 + readInt;
            stsdData3 = stsdData2;
            bArr3 = bArr;
            drmInitData4 = drmInitData2;
            str6 = str2;
            i14 = i3;
        }
        byte[] bArr5 = bArr3;
        String str8 = str6;
        DrmInitData drmInitData5 = drmInitData4;
        StsdData stsdData4 = stsdData3;
        if (stsdData4.format == null) {
            String str9 = str8;
            if (str9 != null) {
                int i19 = "audio/raw".equals(str9) ? 2 : -1;
                String num = Integer.toString(i4);
                byte[] bArr6 = bArr5;
                if (bArr6 == null) {
                    list = null;
                } else {
                    list = Collections.singletonList(bArr6);
                }
                stsdData4.format = Format.createAudioSampleFormat(num, str9, null, -1, -1, i17, i16, i19, list, drmInitData5, 0, str4);
            }
        }
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if ("audio/mpeg".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts.hd".equals(mimeTypeFromMp4ObjectType)) {
            return Pair.create(mimeTypeFromMp4ObjectType, null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        return Pair.create(mimeTypeFromMp4ObjectType, bArr);
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf) {
                Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt);
                if (parseCommonEncryptionSinfFromParent != null) {
                    return parseCommonEncryptionSinfFromParent;
                }
            }
            position += readInt;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        String str = null;
        Object obj = null;
        int i4 = -1;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_frma) {
                obj = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                str = parsableByteArray.readString(4);
            } else if (readInt2 == Atom.TYPE_schi) {
                i4 = i3;
                i5 = readInt;
            }
            i3 += readInt;
        }
        if (!"cenc".equals(str) && !"cbc1".equals(str) && !"cens".equals(str) && !"cbcs".equals(str)) {
            return null;
        }
        boolean z = true;
        Assertions.checkArgument(obj != null, "frma atom is mandatory");
        Assertions.checkArgument(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, str);
        if (parseSchiFromParent == null) {
            z = false;
        }
        Assertions.checkArgument(z, "tenc atom is mandatory");
        return Pair.create(obj, parseSchiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (parseFullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = readUnsignedByte & 15;
                    i4 = (readUnsignedByte & 240) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, bArr2.length);
                if (z && readUnsignedByte2 == 0) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[readUnsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, readUnsignedByte3);
                }
                TrackEncryptionBox trackEncryptionBox = new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i4, i3, bArr);
                return trackEncryptionBox;
            }
            i5 += readInt;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, readInt + i3);
            }
            i3 += readInt;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }
}
