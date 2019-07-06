package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;

public final class Ac3Util {
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 384, 448, 512, 576, 640};
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};
    private static final int[] CHANNEL_COUNT_BY_ACMOD = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] SAMPLE_RATE_BY_FSCOD = {48000, 44100, 32000};
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = {24000, 22050, 16000};
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = {69, 87, 104, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static final class Ac3SyncFrameInfo {
        public final int channelCount;
        public final int frameSize;
        public final String mimeType;
        public final int sampleCount;
        public final int sampleRate;
        public final int streamType;

        private Ac3SyncFrameInfo(String str, int i, int i2, int i3, int i4, int i5) {
            this.mimeType = str;
            this.streamType = i;
            this.channelCount = i2;
            this.sampleRate = i3;
            this.frameSize = i4;
            this.sampleCount = i5;
        }
    }

    public static int getAc3SyncframeAudioSampleCount() {
        return 1536;
    }

    public static Format parseAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        int i = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & 192) >> 6];
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = CHANNEL_COUNT_BY_ACMOD[(readUnsignedByte & 56) >> 3];
        if ((readUnsignedByte & 4) != 0) {
            i2++;
        }
        return Format.createAudioSampleFormat(str, "audio/ac3", null, -1, -1, i2, i, null, drmInitData, 0, str2);
    }

    public static Format parseEAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.skipBytes(2);
        int i = SAMPLE_RATE_BY_FSCOD[(parsableByteArray2.readUnsignedByte() & 192) >> 6];
        int readUnsignedByte = parsableByteArray2.readUnsignedByte();
        int i2 = CHANNEL_COUNT_BY_ACMOD[(readUnsignedByte & 14) >> 1];
        if ((readUnsignedByte & 1) != 0) {
            i2++;
        }
        if (((parsableByteArray2.readUnsignedByte() & 30) >> 1) > 0 && (2 & parsableByteArray2.readUnsignedByte()) != 0) {
            i2 += 2;
        }
        int i3 = i2;
        String str3 = "audio/eac3";
        if (parsableByteArray2.bytesLeft() > 0 && (parsableByteArray2.readUnsignedByte() & 1) != 0) {
            str3 = "audio/eac3-joc";
        }
        return Format.createAudioSampleFormat(str, str3, null, -1, -1, i3, i, null, drmInitData, 0, str2);
    }

    public static Ac3SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray parsableBitArray) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String str;
        int i6;
        int i7;
        int i8;
        int i9;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int position = parsableBitArray.getPosition();
        parsableBitArray2.skipBits(40);
        boolean z = parsableBitArray2.readBits(5) == 16;
        parsableBitArray2.setPosition(position);
        if (z) {
            parsableBitArray2.skipBits(16);
            int readBits = parsableBitArray2.readBits(2);
            parsableBitArray2.skipBits(3);
            int readBits2 = (parsableBitArray2.readBits(11) + 1) * 2;
            int readBits3 = parsableBitArray2.readBits(2);
            if (readBits3 == 3) {
                i6 = SAMPLE_RATE_BY_FSCOD2[parsableBitArray2.readBits(2)];
                i8 = 3;
                i7 = 6;
            } else {
                i8 = parsableBitArray2.readBits(2);
                i7 = BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[i8];
                i6 = SAMPLE_RATE_BY_FSCOD[readBits3];
            }
            int i10 = i7 * 256;
            int readBits4 = parsableBitArray2.readBits(3);
            boolean readBit = parsableBitArray.readBit();
            int i11 = CHANNEL_COUNT_BY_ACMOD[readBits4] + (readBit ? 1 : 0);
            parsableBitArray2.skipBits(10);
            if (parsableBitArray.readBit()) {
                parsableBitArray2.skipBits(8);
            }
            if (readBits4 == 0) {
                parsableBitArray2.skipBits(5);
                if (parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
            }
            if (readBits == 1 && parsableBitArray.readBit()) {
                parsableBitArray2.skipBits(16);
            }
            if (parsableBitArray.readBit()) {
                if (readBits4 > 2) {
                    parsableBitArray2.skipBits(2);
                }
                if ((readBits4 & 1) != 0 && readBits4 > 2) {
                    parsableBitArray2.skipBits(6);
                }
                if ((readBits4 & 4) != 0) {
                    parsableBitArray2.skipBits(6);
                }
                if (readBit && parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(5);
                }
                if (readBits == 0) {
                    if (parsableBitArray.readBit()) {
                        parsableBitArray2.skipBits(6);
                    }
                    if (readBits4 == 0 && parsableBitArray.readBit()) {
                        parsableBitArray2.skipBits(6);
                    }
                    if (parsableBitArray.readBit()) {
                        parsableBitArray2.skipBits(6);
                    }
                    int readBits5 = parsableBitArray2.readBits(2);
                    if (readBits5 == 1) {
                        parsableBitArray2.skipBits(5);
                    } else if (readBits5 == 2) {
                        parsableBitArray2.skipBits(12);
                    } else if (readBits5 == 3) {
                        int readBits6 = parsableBitArray2.readBits(5);
                        if (parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(4);
                                }
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(4);
                                }
                            }
                        }
                        if (parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(7);
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(8);
                                }
                            }
                        }
                        parsableBitArray2.skipBits((readBits6 + 2) * 8);
                        parsableBitArray.byteAlign();
                    }
                    if (readBits4 < 2) {
                        if (parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(14);
                        }
                        if (readBits4 == 0 && parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(14);
                        }
                    }
                    if (parsableBitArray.readBit()) {
                        if (i8 == 0) {
                            parsableBitArray2.skipBits(5);
                        } else {
                            for (int i12 = 0; i12 < i7; i12++) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(5);
                                }
                            }
                        }
                    }
                }
            }
            if (parsableBitArray.readBit()) {
                parsableBitArray2.skipBits(5);
                if (readBits4 == 2) {
                    parsableBitArray2.skipBits(4);
                }
                if (readBits4 >= 6) {
                    parsableBitArray2.skipBits(2);
                }
                if (parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
                if (readBits4 == 0 && parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
                i9 = 3;
                if (readBits3 < 3) {
                    parsableBitArray.skipBit();
                }
            } else {
                i9 = 3;
            }
            if (readBits == 0 && i8 != i9) {
                parsableBitArray.skipBit();
            }
            if (readBits == 2 && (i8 == i9 || parsableBitArray.readBit())) {
                parsableBitArray2.skipBits(6);
            }
            String str2 = "audio/eac3";
            if (parsableBitArray.readBit() && parsableBitArray2.readBits(6) == 1 && parsableBitArray2.readBits(8) == 1) {
                str2 = "audio/eac3-joc";
            }
            i5 = readBits;
            str = str2;
            i2 = readBits2;
            i3 = i6;
            i = i10;
            i4 = i11;
        } else {
            String str3 = "audio/ac3";
            parsableBitArray2.skipBits(32);
            int readBits7 = parsableBitArray2.readBits(2);
            int ac3SyncframeSize = getAc3SyncframeSize(readBits7, parsableBitArray2.readBits(6));
            parsableBitArray2.skipBits(8);
            int readBits8 = parsableBitArray2.readBits(3);
            if (!((readBits8 & 1) == 0 || readBits8 == 1)) {
                parsableBitArray2.skipBits(2);
            }
            if ((readBits8 & 4) != 0) {
                parsableBitArray2.skipBits(2);
            }
            if (readBits8 == 2) {
                parsableBitArray2.skipBits(2);
            }
            str = str3;
            i2 = ac3SyncframeSize;
            i3 = SAMPLE_RATE_BY_FSCOD[readBits7];
            i4 = CHANNEL_COUNT_BY_ACMOD[readBits8] + (parsableBitArray.readBit() ? 1 : 0);
            i5 = -1;
            i = 1536;
        }
        Ac3SyncFrameInfo ac3SyncFrameInfo = new Ac3SyncFrameInfo(str, i5, i4, i3, i2, i);
        return ac3SyncFrameInfo;
    }

    public static int parseAc3SyncframeSize(byte[] bArr) {
        if (bArr.length < 5) {
            return -1;
        }
        return getAc3SyncframeSize((bArr[4] & 192) >> 6, bArr[4] & 63);
    }

    public static int parseEAc3SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        int i = 6;
        if (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3) {
            i = BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4];
        }
        return i * 256;
    }

    public static int parseTrueHdSyncframeAudioSampleCount(byte[] bArr) {
        if (bArr[4] == -8 && bArr[5] == 114 && bArr[6] == 111 && bArr[7] == -70) {
            return 40 << (bArr[8] & 7);
        }
        return 0;
    }

    public static int parseTrueHdSyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        if (byteBuffer.getInt(byteBuffer.position() + 4) != -1167101192) {
            return 0;
        }
        return 40 << (byteBuffer.get(byteBuffer.position() + 8) & 7);
    }

    private static int getAc3SyncframeSize(int i, int i2) {
        int i3 = i2 / 2;
        if (i < 0 || i >= SAMPLE_RATE_BY_FSCOD.length || i2 < 0 || i3 >= SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1.length) {
            return -1;
        }
        int i4 = SAMPLE_RATE_BY_FSCOD[i];
        if (i4 == 44100) {
            return (SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1[i3] + (i2 % 2)) * 2;
        }
        int i5 = BITRATE_BY_HALF_FRMSIZECOD[i3];
        return i4 == 32000 ? i5 * 6 : i5 * 4;
    }
}
