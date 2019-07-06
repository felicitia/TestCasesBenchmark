package com.google.android.exoplayer2.extractor;

public final class MpegAudioHeader {
    private static final int[] BITRATE_V1_L1 = {32, 64, 96, 128, 160, 192, 224, 256, 288, 320, 352, 384, 416, 448};
    private static final int[] BITRATE_V1_L2 = {32, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 384};
    private static final int[] BITRATE_V1_L3 = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320};
    private static final int[] BITRATE_V2 = {8, 16, 24, 32, 40, 48, 56, 64, 80, 96, 112, 128, 144, 160};
    private static final int[] BITRATE_V2_L1 = {32, 48, 56, 64, 80, 96, 112, 128, 144, 160, 176, 192, 224, 256};
    private static final String[] MIME_TYPE_BY_LAYER = {"audio/mpeg-L1", "audio/mpeg-L2", "audio/mpeg"};
    private static final int[] SAMPLING_RATE_V1 = {44100, 48000, 32000};
    public int bitrate;
    public int channels;
    public int frameSize;
    public String mimeType;
    public int sampleRate;
    public int samplesPerFrame;
    public int version;

    public static int getFrameSize(int i) {
        if ((i & -2097152) != -2097152) {
            return -1;
        }
        int i2 = (i >>> 19) & 3;
        if (i2 == 1) {
            return -1;
        }
        int i3 = (i >>> 17) & 3;
        if (i3 == 0) {
            return -1;
        }
        int i4 = (i >>> 12) & 15;
        if (i4 == 0 || i4 == 15) {
            return -1;
        }
        int i5 = (i >>> 10) & 3;
        if (i5 == 3) {
            return -1;
        }
        int i6 = SAMPLING_RATE_V1[i5];
        if (i2 == 2) {
            i6 /= 2;
        } else if (i2 == 0) {
            i6 /= 4;
        }
        int i7 = (i >>> 9) & 1;
        if (i3 == 3) {
            return ((((i2 == 3 ? BITRATE_V1_L1[i4 - 1] : BITRATE_V2_L1[i4 - 1]) * 12000) / i6) + i7) * 4;
        }
        int i8 = i2 == 3 ? i3 == 2 ? BITRATE_V1_L2[i4 - 1] : BITRATE_V1_L3[i4 - 1] : BITRATE_V2[i4 - 1];
        int i9 = 144000;
        if (i2 == 3) {
            return ((i8 * 144000) / i6) + i7;
        }
        if (i3 == 1) {
            i9 = 72000;
        }
        return ((i9 * i8) / i6) + i7;
    }

    public static boolean populateHeader(int i, MpegAudioHeader mpegAudioHeader) {
        int i2;
        int i3;
        int i4;
        if ((i & -2097152) != -2097152) {
            return false;
        }
        int i5 = (i >>> 19) & 3;
        if (i5 == 1) {
            return false;
        }
        int i6 = (i >>> 17) & 3;
        if (i6 == 0) {
            return false;
        }
        int i7 = (i >>> 12) & 15;
        if (i7 == 0 || i7 == 15) {
            return false;
        }
        int i8 = (i >>> 10) & 3;
        if (i8 == 3) {
            return false;
        }
        int i9 = SAMPLING_RATE_V1[i8];
        if (i5 == 2) {
            i9 /= 2;
        } else if (i5 == 0) {
            i9 /= 4;
        }
        int i10 = i9;
        int i11 = (i >>> 9) & 1;
        int i12 = 1152;
        if (i6 == 3) {
            i4 = i5 == 3 ? BITRATE_V1_L1[i7 - 1] : BITRATE_V2_L1[i7 - 1];
            i3 = (((i4 * 12000) / i10) + i11) * 4;
            i2 = 384;
        } else {
            int i13 = 144000;
            if (i5 == 3) {
                i4 = i6 == 2 ? BITRATE_V1_L2[i7 - 1] : BITRATE_V1_L3[i7 - 1];
                i3 = ((144000 * i4) / i10) + i11;
                i2 = 1152;
            } else {
                i4 = BITRATE_V2[i7 - 1];
                if (i6 == 1) {
                    i12 = 576;
                }
                if (i6 == 1) {
                    i13 = 72000;
                }
                i3 = ((i13 * i4) / i10) + i11;
                i2 = i12;
            }
        }
        mpegAudioHeader.setValues(i5, MIME_TYPE_BY_LAYER[3 - i6], i3, i10, ((i >> 6) & 3) == 3 ? 1 : 2, i4 * 1000, i2);
        return true;
    }

    private void setValues(int i, String str, int i2, int i3, int i4, int i5, int i6) {
        this.version = i;
        this.mimeType = str;
        this.frameSize = i2;
        this.sampleRate = i3;
        this.channels = i4;
        this.bitrate = i5;
        this.samplesPerFrame = i6;
    }
}
