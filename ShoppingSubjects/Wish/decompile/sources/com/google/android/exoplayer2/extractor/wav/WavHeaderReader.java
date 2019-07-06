package com.google.android.exoplayer2.extractor.wav;

import android.util.Log;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

final class WavHeaderReader {

    private static final class ChunkHeader {
        public final int id;
        public final long size;

        private ChunkHeader(int i, long j) {
            this.id = i;
            this.size = j;
        }

        public static ChunkHeader peek(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws IOException, InterruptedException {
            extractorInput.peekFully(parsableByteArray.data, 0, 8);
            parsableByteArray.setPosition(0);
            return new ChunkHeader(parsableByteArray.readInt(), parsableByteArray.readLittleEndianUnsignedInt());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0103  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.extractor.wav.WavHeader peek(com.google.android.exoplayer2.extractor.ExtractorInput r15) throws java.io.IOException, java.lang.InterruptedException {
        /*
            com.google.android.exoplayer2.util.Assertions.checkNotNull(r15)
            com.google.android.exoplayer2.util.ParsableByteArray r0 = new com.google.android.exoplayer2.util.ParsableByteArray
            r1 = 16
            r0.<init>(r1)
            com.google.android.exoplayer2.extractor.wav.WavHeaderReader$ChunkHeader r2 = com.google.android.exoplayer2.extractor.wav.WavHeaderReader.ChunkHeader.peek(r15, r0)
            int r2 = r2.id
            java.lang.String r3 = "RIFF"
            int r3 = com.google.android.exoplayer2.util.Util.getIntegerCodeForString(r3)
            r4 = 0
            if (r2 == r3) goto L_0x001a
            return r4
        L_0x001a:
            byte[] r2 = r0.data
            r3 = 4
            r5 = 0
            r15.peekFully(r2, r5, r3)
            r0.setPosition(r5)
            int r2 = r0.readInt()
            java.lang.String r6 = "WAVE"
            int r6 = com.google.android.exoplayer2.util.Util.getIntegerCodeForString(r6)
            if (r2 == r6) goto L_0x0047
            java.lang.String r15 = "WavHeaderReader"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unsupported RIFF format: "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r15, r0)
            return r4
        L_0x0047:
            com.google.android.exoplayer2.extractor.wav.WavHeaderReader$ChunkHeader r2 = com.google.android.exoplayer2.extractor.wav.WavHeaderReader.ChunkHeader.peek(r15, r0)
        L_0x004b:
            int r6 = r2.id
            java.lang.String r7 = "fmt "
            int r7 = com.google.android.exoplayer2.util.Util.getIntegerCodeForString(r7)
            if (r6 == r7) goto L_0x0060
            long r6 = r2.size
            int r2 = (int) r6
            r15.advancePeekPosition(r2)
            com.google.android.exoplayer2.extractor.wav.WavHeaderReader$ChunkHeader r2 = com.google.android.exoplayer2.extractor.wav.WavHeaderReader.ChunkHeader.peek(r15, r0)
            goto L_0x004b
        L_0x0060:
            long r6 = r2.size
            r8 = 16
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r6 = 1
            if (r10 < 0) goto L_0x006b
            r7 = 1
            goto L_0x006c
        L_0x006b:
            r7 = 0
        L_0x006c:
            com.google.android.exoplayer2.util.Assertions.checkState(r7)
            byte[] r7 = r0.data
            r15.peekFully(r7, r5, r1)
            r0.setPosition(r5)
            int r7 = r0.readLittleEndianUnsignedShort()
            int r9 = r0.readLittleEndianUnsignedShort()
            int r10 = r0.readLittleEndianUnsignedIntToInt()
            int r11 = r0.readLittleEndianUnsignedIntToInt()
            int r12 = r0.readLittleEndianUnsignedShort()
            int r13 = r0.readLittleEndianUnsignedShort()
            int r0 = r9 * r13
            int r0 = r0 / 8
            if (r12 == r0) goto L_0x00b4
            com.google.android.exoplayer2.ParserException r15 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Expected block alignment: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = "; got: "
            r1.append(r0)
            r1.append(r12)
            java.lang.String r0 = r1.toString()
            r15.<init>(r0)
            throw r15
        L_0x00b4:
            if (r7 == r6) goto L_0x00dd
            r0 = 3
            if (r7 == r0) goto L_0x00d5
            r0 = 65534(0xfffe, float:9.1833E-41)
            if (r7 == r0) goto L_0x00dd
            java.lang.String r15 = "WavHeaderReader"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unsupported WAV format type: "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r15, r0)
            return r4
        L_0x00d5:
            r0 = 32
            if (r13 != r0) goto L_0x00da
            goto L_0x00db
        L_0x00da:
            r3 = 0
        L_0x00db:
            r14 = r3
            goto L_0x00e2
        L_0x00dd:
            int r0 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r13)
            r14 = r0
        L_0x00e2:
            if (r14 != 0) goto L_0x0103
            java.lang.String r15 = "WavHeaderReader"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unsupported WAV bit depth "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r1 = " for type "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r15, r0)
            return r4
        L_0x0103:
            long r2 = r2.size
            int r0 = (int) r2
            int r0 = r0 - r1
            r15.advancePeekPosition(r0)
            com.google.android.exoplayer2.extractor.wav.WavHeader r15 = new com.google.android.exoplayer2.extractor.wav.WavHeader
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13, r14)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.wav.WavHeaderReader.peek(com.google.android.exoplayer2.extractor.ExtractorInput):com.google.android.exoplayer2.extractor.wav.WavHeader");
    }

    public static void skipToData(ExtractorInput extractorInput, WavHeader wavHeader) throws IOException, InterruptedException {
        Assertions.checkNotNull(extractorInput);
        Assertions.checkNotNull(wavHeader);
        extractorInput.resetPeekPosition();
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        ChunkHeader peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        while (peek.id != Util.getIntegerCodeForString("data")) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring unknown WAV chunk: ");
            sb.append(peek.id);
            Log.w("WavHeaderReader", sb.toString());
            long j = peek.size + 8;
            if (peek.id == Util.getIntegerCodeForString("RIFF")) {
                j = 12;
            }
            if (j > 2147483647L) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Chunk is too large (~2GB+) to skip; id: ");
                sb2.append(peek.id);
                throw new ParserException(sb2.toString());
            }
            extractorInput.skipFully((int) j);
            peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        }
        extractorInput.skipFully(8);
        wavHeader.setDataBounds(extractorInput.getPosition(), peek.size);
    }
}
