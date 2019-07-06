package com.google.android.exoplayer2.extractor.mkv;

import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekMap.Unseekable;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput.CryptoData;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class MatroskaExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() {
        public Extractor[] createExtractors() {
            return new Extractor[]{new MatroskaExtractor()};
        }
    };
    /* access modifiers changed from: private */
    public static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final byte[] SSA_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] SUBRIP_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    /* access modifiers changed from: private */
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    private final class InnerEbmlReaderOutput implements EbmlReaderOutput {
        private InnerEbmlReaderOutput() {
        }

        public int getElementType(int i) {
            return MatroskaExtractor.this.getElementType(i);
        }

        public boolean isLevel1Element(int i) {
            return MatroskaExtractor.this.isLevel1Element(i);
        }

        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }

        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    private static final class Track {
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public DrmInitData drmInitData;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        /* access modifiers changed from: private */
        public String language;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = 200;
            this.primaryRChromaticityX = -1.0f;
            this.primaryRChromaticityY = -1.0f;
            this.primaryGChromaticityX = -1.0f;
            this.primaryGChromaticityY = -1.0f;
            this.primaryBChromaticityX = -1.0f;
            this.primaryBChromaticityY = -1.0f;
            this.whitePointChromaticityX = -1.0f;
            this.whitePointChromaticityY = -1.0f;
            this.maxMasteringLuminance = -1.0f;
            this.minMasteringLuminance = -1.0f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.flagDefault = true;
            this.language = "eng";
        }

        /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d5, code lost:
            r12 = r1;
            r18 = r2;
            r2 = null;
            r15 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:117:0x025a, code lost:
            r12 = r1;
            r2 = null;
            r15 = 4096;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:119:0x0268, code lost:
            r12 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:130:0x030a, code lost:
            r12 = r1;
            r2 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:138:0x0326, code lost:
            r12 = r1;
            r2 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:139:0x0328, code lost:
            r15 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:140:0x0329, code lost:
            r18 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:141:0x032b, code lost:
            r1 = r0.flagDefault | false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:142:0x0330, code lost:
            if (r0.flagForced == false) goto L_0x0333;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:143:0x0332, code lost:
            r6 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:144:0x0333, code lost:
            r1 = r1 | r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:145:0x0338, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.isAudio(r12) == false) goto L_0x035b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:146:0x033a, code lost:
            r1 = com.google.android.exoplayer2.Format.createAudioSampleFormat(java.lang.Integer.toString(r28), r12, null, -1, r15, r0.channelCount, r0.sampleRate, r18, r2, r0.drmInitData, r1 ? 1 : 0, r0.language);
            r8 = 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:148:0x035f, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.isVideo(r12) == false) goto L_0x03d5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:150:0x0363, code lost:
            if (r0.displayUnit != 0) goto L_0x037b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:152:0x0367, code lost:
            if (r0.displayWidth != -1) goto L_0x036c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:153:0x0369, code lost:
            r1 = r0.width;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:154:0x036c, code lost:
            r1 = r0.displayWidth;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:155:0x036e, code lost:
            r0.displayWidth = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:156:0x0372, code lost:
            if (r0.displayHeight != -1) goto L_0x0377;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:157:0x0374, code lost:
            r1 = r0.height;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:158:0x0377, code lost:
            r1 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:159:0x0379, code lost:
            r0.displayHeight = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:161:0x037f, code lost:
            if (r0.displayWidth == -1) goto L_0x0397;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:163:0x0383, code lost:
            if (r0.displayHeight == -1) goto L_0x0397;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:164:0x0385, code lost:
            r21 = ((float) (r0.height * r0.displayWidth)) / ((float) (r0.width * r0.displayHeight));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:165:0x0397, code lost:
            r21 = -1.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:167:0x039b, code lost:
            if (r0.hasColorInfo == false) goto L_0x03ac;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:168:0x039d, code lost:
            r10 = new com.google.android.exoplayer2.video.ColorInfo(r0.colorSpace, r0.colorRange, r0.colorTransfer, getHdrStaticInfo());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:169:0x03ac, code lost:
            r24 = r10;
            r1 = com.google.android.exoplayer2.Format.createVideoSampleFormat(java.lang.Integer.toString(r28), r12, null, -1, r15, r0.width, r0.height, -1.0f, r2, -1, r21, r0.projectionData, r0.stereoMode, r24, r0.drmInitData);
            r8 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:171:0x03db, code lost:
            if ("application/x-subrip".equals(r12) == false) goto L_0x03eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:172:0x03dd, code lost:
            r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(java.lang.Integer.toString(r28), r12, r1 ? 1 : 0, r0.language, r0.drmInitData);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:174:0x03f1, code lost:
            if ("text/x-ssa".equals(r12) == false) goto L_0x0421;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:175:0x03f3, code lost:
            r2 = new java.util.ArrayList(2);
            r2.add(com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.access$300());
            r2.add(r0.codecPrivate);
            r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(java.lang.Integer.toString(r28), r12, null, -1, r1 ? 1 : 0, r0.language, -1, r0.drmInitData, Long.MAX_VALUE, r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:177:0x0427, code lost:
            if ("application/vobsub".equals(r12) != false) goto L_0x0442;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:179:0x042f, code lost:
            if ("application/pgs".equals(r12) != false) goto L_0x0442;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:181:0x0437, code lost:
            if ("application/dvbsubs".equals(r12) == false) goto L_0x043a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:183:0x0441, code lost:
            throw new com.google.android.exoplayer2.ParserException("Unexpected MIME type.");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:184:0x0442, code lost:
            r1 = com.google.android.exoplayer2.Format.createImageSampleFormat(java.lang.Integer.toString(r28), r12, null, -1, r1 ? 1 : 0, r2, r0.language, r0.drmInitData);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:185:0x0457, code lost:
            r0.output = r27.track(r0.number, r8);
            r0.output.format(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:186:0x0466, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput r27, int r28) throws com.google.android.exoplayer2.ParserException {
            /*
                r26 = this;
                r0 = r26
                java.lang.String r1 = r0.codecId
                int r2 = r1.hashCode()
                r3 = 4
                r4 = 8
                r5 = 1
                r6 = 0
                r7 = 2
                r8 = 3
                r9 = -1
                switch(r2) {
                    case -2095576542: goto L_0x0156;
                    case -2095575984: goto L_0x014c;
                    case -1985379776: goto L_0x0141;
                    case -1784763192: goto L_0x0136;
                    case -1730367663: goto L_0x012b;
                    case -1482641358: goto L_0x0120;
                    case -1482641357: goto L_0x0115;
                    case -1373388978: goto L_0x010a;
                    case -933872740: goto L_0x00ff;
                    case -538363189: goto L_0x00f4;
                    case -538363109: goto L_0x00e9;
                    case -425012669: goto L_0x00dd;
                    case -356037306: goto L_0x00d1;
                    case 62923557: goto L_0x00c5;
                    case 62923603: goto L_0x00b9;
                    case 62927045: goto L_0x00ad;
                    case 82338133: goto L_0x00a2;
                    case 82338134: goto L_0x0097;
                    case 99146302: goto L_0x008b;
                    case 444813526: goto L_0x007f;
                    case 542569478: goto L_0x0073;
                    case 725957860: goto L_0x0067;
                    case 738597099: goto L_0x005b;
                    case 855502857: goto L_0x0050;
                    case 1422270023: goto L_0x0044;
                    case 1809237540: goto L_0x0039;
                    case 1950749482: goto L_0x002d;
                    case 1950789798: goto L_0x0021;
                    case 1951062397: goto L_0x0015;
                    default: goto L_0x0013;
                }
            L_0x0013:
                goto L_0x0160
            L_0x0015:
                java.lang.String r2 = "A_OPUS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 11
                goto L_0x0161
            L_0x0021:
                java.lang.String r2 = "A_FLAC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 21
                goto L_0x0161
            L_0x002d:
                java.lang.String r2 = "A_EAC3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 16
                goto L_0x0161
            L_0x0039:
                java.lang.String r2 = "V_MPEG2"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 2
                goto L_0x0161
            L_0x0044:
                java.lang.String r2 = "S_TEXT/UTF8"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 24
                goto L_0x0161
            L_0x0050:
                java.lang.String r2 = "V_MPEGH/ISO/HEVC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 7
                goto L_0x0161
            L_0x005b:
                java.lang.String r2 = "S_TEXT/ASS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 25
                goto L_0x0161
            L_0x0067:
                java.lang.String r2 = "A_PCM/INT/LIT"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 23
                goto L_0x0161
            L_0x0073:
                java.lang.String r2 = "A_DTS/EXPRESS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 19
                goto L_0x0161
            L_0x007f:
                java.lang.String r2 = "V_THEORA"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 9
                goto L_0x0161
            L_0x008b:
                java.lang.String r2 = "S_HDMV/PGS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 27
                goto L_0x0161
            L_0x0097:
                java.lang.String r2 = "V_VP9"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 1
                goto L_0x0161
            L_0x00a2:
                java.lang.String r2 = "V_VP8"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 0
                goto L_0x0161
            L_0x00ad:
                java.lang.String r2 = "A_DTS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 18
                goto L_0x0161
            L_0x00b9:
                java.lang.String r2 = "A_AC3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 15
                goto L_0x0161
            L_0x00c5:
                java.lang.String r2 = "A_AAC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 12
                goto L_0x0161
            L_0x00d1:
                java.lang.String r2 = "A_DTS/LOSSLESS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 20
                goto L_0x0161
            L_0x00dd:
                java.lang.String r2 = "S_VOBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 26
                goto L_0x0161
            L_0x00e9:
                java.lang.String r2 = "V_MPEG4/ISO/AVC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 6
                goto L_0x0161
            L_0x00f4:
                java.lang.String r2 = "V_MPEG4/ISO/ASP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 4
                goto L_0x0161
            L_0x00ff:
                java.lang.String r2 = "S_DVBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 28
                goto L_0x0161
            L_0x010a:
                java.lang.String r2 = "V_MS/VFW/FOURCC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 8
                goto L_0x0161
            L_0x0115:
                java.lang.String r2 = "A_MPEG/L3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 14
                goto L_0x0161
            L_0x0120:
                java.lang.String r2 = "A_MPEG/L2"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 13
                goto L_0x0161
            L_0x012b:
                java.lang.String r2 = "A_VORBIS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 10
                goto L_0x0161
            L_0x0136:
                java.lang.String r2 = "A_TRUEHD"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 17
                goto L_0x0161
            L_0x0141:
                java.lang.String r2 = "A_MS/ACM"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 22
                goto L_0x0161
            L_0x014c:
                java.lang.String r2 = "V_MPEG4/ISO/SP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 3
                goto L_0x0161
            L_0x0156:
                java.lang.String r2 = "V_MPEG4/ISO/AP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 5
                goto L_0x0161
            L_0x0160:
                r1 = -1
            L_0x0161:
                r2 = 4096(0x1000, float:5.74E-42)
                r10 = 0
                switch(r1) {
                    case 0: goto L_0x0324;
                    case 1: goto L_0x0321;
                    case 2: goto L_0x031e;
                    case 3: goto L_0x030d;
                    case 4: goto L_0x030d;
                    case 5: goto L_0x030d;
                    case 6: goto L_0x02f7;
                    case 7: goto L_0x02e3;
                    case 8: goto L_0x02c4;
                    case 9: goto L_0x02c1;
                    case 10: goto L_0x02b1;
                    case 11: goto L_0x026b;
                    case 12: goto L_0x0260;
                    case 13: goto L_0x0258;
                    case 14: goto L_0x0255;
                    case 15: goto L_0x0251;
                    case 16: goto L_0x024d;
                    case 17: goto L_0x0242;
                    case 18: goto L_0x023e;
                    case 19: goto L_0x023e;
                    case 20: goto L_0x023a;
                    case 21: goto L_0x0231;
                    case 22: goto L_0x01dc;
                    case 23: goto L_0x01a7;
                    case 24: goto L_0x01a3;
                    case 25: goto L_0x019f;
                    case 26: goto L_0x0195;
                    case 27: goto L_0x0191;
                    case 28: goto L_0x016f;
                    default: goto L_0x0167;
                }
            L_0x0167:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Unrecognized codec identifier."
                r1.<init>(r2)
                throw r1
            L_0x016f:
                java.lang.String r1 = "application/dvbsubs"
                byte[] r2 = new byte[r3]
                byte[] r3 = r0.codecPrivate
                byte r3 = r3[r6]
                r2[r6] = r3
                byte[] r3 = r0.codecPrivate
                byte r3 = r3[r5]
                r2[r5] = r3
                byte[] r3 = r0.codecPrivate
                byte r3 = r3[r7]
                r2[r7] = r3
                byte[] r3 = r0.codecPrivate
                byte r3 = r3[r8]
                r2[r8] = r3
                java.util.List r2 = java.util.Collections.singletonList(r2)
                goto L_0x0268
            L_0x0191:
                java.lang.String r1 = "application/pgs"
                goto L_0x0326
            L_0x0195:
                java.lang.String r1 = "application/vobsub"
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
                goto L_0x0268
            L_0x019f:
                java.lang.String r1 = "text/x-ssa"
                goto L_0x0326
            L_0x01a3:
                java.lang.String r1 = "application/x-subrip"
                goto L_0x0326
            L_0x01a7:
                java.lang.String r1 = "audio/raw"
                int r2 = r0.audioBitDepth
                int r2 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r2)
                if (r2 != 0) goto L_0x01d5
                java.lang.String r1 = "audio/x-unknown"
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Unsupported PCM bit depth: "
                r3.append(r4)
                int r4 = r0.audioBitDepth
                r3.append(r4)
                java.lang.String r4 = ". Setting mimeType to "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                android.util.Log.w(r2, r3)
                goto L_0x0326
            L_0x01d5:
                r12 = r1
                r18 = r2
                r2 = r10
                r15 = -1
                goto L_0x032b
            L_0x01dc:
                java.lang.String r1 = "audio/raw"
                com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r2.<init>(r3)
                boolean r2 = parseMsAcmCodecPrivate(r2)
                if (r2 == 0) goto L_0x0217
                int r2 = r0.audioBitDepth
                int r2 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r2)
                if (r2 != 0) goto L_0x01d5
                java.lang.String r1 = "audio/x-unknown"
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Unsupported PCM bit depth: "
                r3.append(r4)
                int r4 = r0.audioBitDepth
                r3.append(r4)
                java.lang.String r4 = ". Setting mimeType to "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                android.util.Log.w(r2, r3)
                goto L_0x0326
            L_0x0217:
                java.lang.String r1 = "audio/x-unknown"
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Non-PCM MS/ACM is unsupported. Setting mimeType to "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                android.util.Log.w(r2, r3)
                goto L_0x0326
            L_0x0231:
                java.lang.String r1 = "audio/flac"
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
                goto L_0x0268
            L_0x023a:
                java.lang.String r1 = "audio/vnd.dts.hd"
                goto L_0x0326
            L_0x023e:
                java.lang.String r1 = "audio/vnd.dts"
                goto L_0x0326
            L_0x0242:
                java.lang.String r1 = "audio/true-hd"
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker r2 = new com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker
                r2.<init>()
                r0.trueHdSampleRechunker = r2
                goto L_0x0326
            L_0x024d:
                java.lang.String r1 = "audio/eac3"
                goto L_0x0326
            L_0x0251:
                java.lang.String r1 = "audio/ac3"
                goto L_0x0326
            L_0x0255:
                java.lang.String r1 = "audio/mpeg"
                goto L_0x025a
            L_0x0258:
                java.lang.String r1 = "audio/mpeg-L2"
            L_0x025a:
                r12 = r1
                r2 = r10
                r15 = 4096(0x1000, float:5.74E-42)
                goto L_0x0329
            L_0x0260:
                java.lang.String r1 = "audio/mp4a-latm"
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
            L_0x0268:
                r12 = r1
                goto L_0x0328
            L_0x026b:
                java.lang.String r1 = "audio/opus"
                r2 = 5760(0x1680, float:8.071E-42)
                java.util.ArrayList r3 = new java.util.ArrayList
                r3.<init>(r8)
                byte[] r11 = r0.codecPrivate
                r3.add(r11)
                java.nio.ByteBuffer r11 = java.nio.ByteBuffer.allocate(r4)
                java.nio.ByteOrder r12 = java.nio.ByteOrder.nativeOrder()
                java.nio.ByteBuffer r11 = r11.order(r12)
                long r12 = r0.codecDelayNs
                java.nio.ByteBuffer r11 = r11.putLong(r12)
                byte[] r11 = r11.array()
                r3.add(r11)
                java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r4)
                java.nio.ByteOrder r11 = java.nio.ByteOrder.nativeOrder()
                java.nio.ByteBuffer r4 = r4.order(r11)
                long r11 = r0.seekPreRollNs
                java.nio.ByteBuffer r4 = r4.putLong(r11)
                byte[] r4 = r4.array()
                r3.add(r4)
                r12 = r1
                r2 = r3
                r15 = 5760(0x1680, float:8.071E-42)
                goto L_0x0329
            L_0x02b1:
                java.lang.String r1 = "audio/vorbis"
                r2 = 8192(0x2000, float:1.14794E-41)
                byte[] r3 = r0.codecPrivate
                java.util.List r3 = parseVorbisCodecPrivate(r3)
                r12 = r1
                r2 = r3
                r15 = 8192(0x2000, float:1.14794E-41)
                goto L_0x0329
            L_0x02c1:
                java.lang.String r1 = "video/x-unknown"
                goto L_0x0326
            L_0x02c4:
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r2 = r0.codecPrivate
                r1.<init>(r2)
                java.util.List r1 = parseFourCcVc1Private(r1)
                if (r1 == 0) goto L_0x02d9
                java.lang.String r2 = "video/wvc1"
            L_0x02d3:
                r12 = r2
                r15 = -1
                r18 = -1
                r2 = r1
                goto L_0x032b
            L_0x02d9:
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.String r3 = "Unsupported FourCC. Setting mimeType to video/x-unknown"
                android.util.Log.w(r2, r3)
                java.lang.String r2 = "video/x-unknown"
                goto L_0x02d3
            L_0x02e3:
                java.lang.String r1 = "video/hevc"
                com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r2.<init>(r3)
                com.google.android.exoplayer2.video.HevcConfig r2 = com.google.android.exoplayer2.video.HevcConfig.parse(r2)
                java.util.List<byte[]> r3 = r2.initializationData
                int r2 = r2.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r2
                goto L_0x030a
            L_0x02f7:
                java.lang.String r1 = "video/avc"
                com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r2.<init>(r3)
                com.google.android.exoplayer2.video.AvcConfig r2 = com.google.android.exoplayer2.video.AvcConfig.parse(r2)
                java.util.List<byte[]> r3 = r2.initializationData
                int r2 = r2.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r2
            L_0x030a:
                r12 = r1
                r2 = r3
                goto L_0x0328
            L_0x030d:
                java.lang.String r1 = "video/mp4v-es"
                byte[] r2 = r0.codecPrivate
                if (r2 != 0) goto L_0x0316
                r2 = r10
                goto L_0x0268
            L_0x0316:
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
                goto L_0x0268
            L_0x031e:
                java.lang.String r1 = "video/mpeg2"
                goto L_0x0326
            L_0x0321:
                java.lang.String r1 = "video/x-vnd.on2.vp9"
                goto L_0x0326
            L_0x0324:
                java.lang.String r1 = "video/x-vnd.on2.vp8"
            L_0x0326:
                r12 = r1
                r2 = r10
            L_0x0328:
                r15 = -1
            L_0x0329:
                r18 = -1
            L_0x032b:
                boolean r1 = r0.flagDefault
                r1 = r1 | r6
                boolean r3 = r0.flagForced
                if (r3 == 0) goto L_0x0333
                r6 = 2
            L_0x0333:
                r1 = r1 | r6
                boolean r3 = com.google.android.exoplayer2.util.MimeTypes.isAudio(r12)
                if (r3 == 0) goto L_0x035b
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                int r3 = r0.channelCount
                int r4 = r0.sampleRate
                com.google.android.exoplayer2.drm.DrmInitData r6 = r0.drmInitData
                java.lang.String r7 = r0.language
                r16 = r3
                r17 = r4
                r19 = r2
                r20 = r6
                r21 = r1
                r22 = r7
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createAudioSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
                r8 = 1
                goto L_0x0457
            L_0x035b:
                boolean r3 = com.google.android.exoplayer2.util.MimeTypes.isVideo(r12)
                if (r3 == 0) goto L_0x03d5
                int r1 = r0.displayUnit
                if (r1 != 0) goto L_0x037b
                int r1 = r0.displayWidth
                if (r1 != r9) goto L_0x036c
                int r1 = r0.width
                goto L_0x036e
            L_0x036c:
                int r1 = r0.displayWidth
            L_0x036e:
                r0.displayWidth = r1
                int r1 = r0.displayHeight
                if (r1 != r9) goto L_0x0377
                int r1 = r0.height
                goto L_0x0379
            L_0x0377:
                int r1 = r0.displayHeight
            L_0x0379:
                r0.displayHeight = r1
            L_0x037b:
                r1 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r3 = r0.displayWidth
                if (r3 == r9) goto L_0x0397
                int r3 = r0.displayHeight
                if (r3 == r9) goto L_0x0397
                int r1 = r0.height
                int r3 = r0.displayWidth
                int r1 = r1 * r3
                float r1 = (float) r1
                int r3 = r0.width
                int r4 = r0.displayHeight
                int r3 = r3 * r4
                float r3 = (float) r3
                float r1 = r1 / r3
                r21 = r1
                goto L_0x0399
            L_0x0397:
                r21 = -1082130432(0xffffffffbf800000, float:-1.0)
            L_0x0399:
                boolean r1 = r0.hasColorInfo
                if (r1 == 0) goto L_0x03ac
                byte[] r1 = r26.getHdrStaticInfo()
                com.google.android.exoplayer2.video.ColorInfo r10 = new com.google.android.exoplayer2.video.ColorInfo
                int r3 = r0.colorSpace
                int r4 = r0.colorRange
                int r5 = r0.colorTransfer
                r10.<init>(r3, r4, r5, r1)
            L_0x03ac:
                r24 = r10
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                int r1 = r0.width
                int r3 = r0.height
                r18 = -1082130432(0xffffffffbf800000, float:-1.0)
                r20 = -1
                byte[] r4 = r0.projectionData
                int r5 = r0.stereoMode
                com.google.android.exoplayer2.drm.DrmInitData r6 = r0.drmInitData
                r16 = r1
                r17 = r3
                r19 = r2
                r22 = r4
                r23 = r5
                r25 = r6
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createVideoSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
                r8 = 2
                goto L_0x0457
            L_0x03d5:
                java.lang.String r3 = "application/x-subrip"
                boolean r3 = r3.equals(r12)
                if (r3 == 0) goto L_0x03eb
                java.lang.String r2 = java.lang.Integer.toString(r28)
                java.lang.String r3 = r0.language
                com.google.android.exoplayer2.drm.DrmInitData r4 = r0.drmInitData
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(r2, r12, r1, r3, r4)
                goto L_0x0457
            L_0x03eb:
                java.lang.String r3 = "text/x-ssa"
                boolean r3 = r3.equals(r12)
                if (r3 == 0) goto L_0x0421
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>(r7)
                byte[] r3 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.SSA_DIALOGUE_FORMAT
                r2.add(r3)
                byte[] r3 = r0.codecPrivate
                r2.add(r3)
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                java.lang.String r3 = r0.language
                r17 = -1
                com.google.android.exoplayer2.drm.DrmInitData r4 = r0.drmInitData
                r19 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r15 = r1
                r16 = r3
                r18 = r4
                r21 = r2
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r21)
                goto L_0x0457
            L_0x0421:
                java.lang.String r3 = "application/vobsub"
                boolean r3 = r3.equals(r12)
                if (r3 != 0) goto L_0x0442
                java.lang.String r3 = "application/pgs"
                boolean r3 = r3.equals(r12)
                if (r3 != 0) goto L_0x0442
                java.lang.String r3 = "application/dvbsubs"
                boolean r3 = r3.equals(r12)
                if (r3 == 0) goto L_0x043a
                goto L_0x0442
            L_0x043a:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Unexpected MIME type."
                r1.<init>(r2)
                throw r1
            L_0x0442:
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                java.lang.String r3 = r0.language
                com.google.android.exoplayer2.drm.DrmInitData r4 = r0.drmInitData
                r15 = r1
                r16 = r2
                r17 = r3
                r18 = r4
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createImageSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18)
            L_0x0457:
                int r2 = r0.number
                r3 = r27
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r3.track(r2, r8)
                r0.output = r2
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r0.output
                r2.format(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track.initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput, int):void");
        }

        public void outputPendingSampleMetadata() {
            if (this.trueHdSampleRechunker != null) {
                this.trueHdSampleRechunker.outputPendingSampleMetadata(this);
            }
        }

        public void reset() {
            if (this.trueHdSampleRechunker != null) {
                this.trueHdSampleRechunker.reset();
            }
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.put(0);
            wrap.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            wrap.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            wrap.putShort((short) this.maxContentLuminance);
            wrap.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static List<byte[]> parseFourCcVc1Private(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                if (parsableByteArray.readLittleEndianUnsignedInt() != 826496599) {
                    return null;
                }
                byte[] bArr = parsableByteArray.data;
                for (int position = parsableByteArray.getPosition() + 20; position < bArr.length - 4; position++) {
                    if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length));
                    }
                }
                throw new ParserException("Failed to find FourCC VC1 initialization data");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC VC1 codec private");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] != 2) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i = 1;
                int i2 = 0;
                while (bArr[i] == -1) {
                    i2 += 255;
                    i++;
                }
                int i3 = i + 1;
                int i4 = i2 + bArr[i];
                int i5 = 0;
                while (bArr[i3] == -1) {
                    i5 += 255;
                    i3++;
                }
                int i6 = i3 + 1;
                int i7 = i5 + bArr[i3];
                if (bArr[i6] != 1) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr2 = new byte[i4];
                System.arraycopy(bArr, i6, bArr2, 0, i4);
                int i8 = i6 + i4;
                if (bArr[i8] != 3) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i9 = i8 + i7;
                if (bArr[i9] != 5) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr3 = new byte[(bArr.length - i9)];
                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                boolean z = true;
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != 65534) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                if (!(parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits())) {
                    z = false;
                }
                return z;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }

    private static final class TrueHdSampleRechunker {
        private int blockFlags;
        private int chunkSize;
        private boolean foundSyncframe;
        private int sampleCount;
        private final byte[] syncframePrefix = new byte[12];
        private long timeUs;

        public void reset() {
            this.foundSyncframe = false;
        }

        public void startSample(ExtractorInput extractorInput, int i, int i2) throws IOException, InterruptedException {
            if (!this.foundSyncframe) {
                extractorInput.peekFully(this.syncframePrefix, 0, 12);
                extractorInput.resetPeekPosition();
                if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) != -1) {
                    this.foundSyncframe = true;
                    this.sampleCount = 0;
                } else {
                    return;
                }
            }
            if (this.sampleCount == 0) {
                this.blockFlags = i;
                this.chunkSize = 0;
            }
            this.chunkSize += i2;
        }

        public void sampleMetadata(Track track, long j) {
            if (this.foundSyncframe) {
                int i = this.sampleCount;
                this.sampleCount = i + 1;
                if (i == 0) {
                    this.timeUs = j;
                }
                if (this.sampleCount >= 8) {
                    track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                    this.sampleCount = 0;
                }
            }
        }

        public void outputPendingSampleMetadata(Track track) {
            if (this.foundSyncframe && this.sampleCount > 0) {
                track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                this.sampleCount = 0;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int getElementType(int i) {
        switch (i) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case 17026:
            case 2274716:
                return 3;
            case 160:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
                return 5;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isLevel1Element(int i) {
        return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
    }

    public void release() {
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int i) {
        this.segmentContentPosition = -1;
        this.timecodeScale = -9223372036854775807L;
        this.durationTimecode = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = -9223372036854775807L;
        this.reader = ebmlReader;
        this.reader.init(new InnerEbmlReaderOutput());
        boolean z = true;
        if ((i & 1) != 0) {
            z = false;
        }
        this.seekForCuesEnabled = z;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return new Sniffer().sniff(extractorInput);
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public void seek(long j, long j2) {
        this.clusterTimecodeUs = -9223372036854775807L;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
        for (int i = 0; i < this.tracks.size(); i++) {
            ((Track) this.tracks.valueAt(i)).reset();
        }
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        this.sampleRead = false;
        boolean z = true;
        while (z && !this.sampleRead) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i = 0; i < this.tracks.size(); i++) {
            ((Track) this.tracks.valueAt(i)).outputPendingSampleMetadata();
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        if (i == 160) {
            this.sampleSeenReferenceBlock = false;
        } else if (i == 174) {
            this.currentTrack = new Track();
        } else if (i == 187) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i == 19899) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i == 20533) {
            this.currentTrack.hasContentEncryption = true;
        } else if (i == 21968) {
            this.currentTrack.hasColorInfo = true;
        } else if (i == 25152) {
        } else {
            if (i != 408125543) {
                if (i == 475249515) {
                    this.cueTimesUs = new LongArray();
                    this.cueClusterPositions = new LongArray();
                } else if (i != 524531317 || this.sentSeekMap) {
                } else {
                    if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                        this.extractorOutput.seekMap(new Unseekable(this.durationUs));
                        this.sentSeekMap = true;
                        return;
                    }
                    this.seekForCues = true;
                }
            } else if (this.segmentContentPosition == -1 || this.segmentContentPosition == j) {
                this.segmentContentPosition = j;
                this.segmentContentSize = j2;
            } else {
                throw new ParserException("Multiple Segment elements not supported");
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void endMasterElement(int i) throws ParserException {
        if (i != 160) {
            if (i == 174) {
                if (isCodecSupported(this.currentTrack.codecId)) {
                    this.currentTrack.initializeOutput(this.extractorOutput, this.currentTrack.number);
                    this.tracks.put(this.currentTrack.number, this.currentTrack);
                }
                this.currentTrack = null;
            } else if (i != 19899) {
                if (i != 25152) {
                    if (i != 28032) {
                        if (i == 357149030) {
                            if (this.timecodeScale == -9223372036854775807L) {
                                this.timecodeScale = 1000000;
                            }
                            if (this.durationTimecode != -9223372036854775807L) {
                                this.durationUs = scaleTimecodeToUs(this.durationTimecode);
                            }
                        } else if (i != 374648427) {
                            if (i == 475249515 && !this.sentSeekMap) {
                                this.extractorOutput.seekMap(buildSeekMap());
                                this.sentSeekMap = true;
                            }
                        } else if (this.tracks.size() == 0) {
                            throw new ParserException("No valid tracks were found");
                        } else {
                            this.extractorOutput.endTracks();
                        }
                    } else if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                        throw new ParserException("Combining encryption and compression is not supported");
                    }
                } else if (this.currentTrack.hasContentEncryption) {
                    if (this.currentTrack.cryptoData == null) {
                        throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
                    }
                    this.currentTrack.drmInitData = new DrmInitData(new SchemeData(C.UUID_NIL, "video/webm", this.currentTrack.cryptoData.encryptionKey));
                }
            } else if (this.seekEntryId == -1 || this.seekEntryPosition == -1) {
                throw new ParserException("Mandatory element SeekID or SeekPosition not found");
            } else if (this.seekEntryId == 475249515) {
                this.cuesContentPosition = this.seekEntryPosition;
            }
        } else if (this.blockState == 2) {
            if (!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }
            commitSampleToOutput((Track) this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
        }
    }

    /* access modifiers changed from: 0000 */
    public void integerElement(int i, long j) throws ParserException {
        boolean z = false;
        switch (i) {
            case 131:
                this.currentTrack.type = (int) j;
                return;
            case 136:
                Track track = this.currentTrack;
                if (j == 1) {
                    z = true;
                }
                track.flagForced = z;
                return;
            case 155:
                this.blockDurationUs = scaleTimecodeToUs(j);
                return;
            case 159:
                this.currentTrack.channelCount = (int) j;
                return;
            case 176:
                this.currentTrack.width = (int) j;
                return;
            case 179:
                this.cueTimesUs.add(scaleTimecodeToUs(j));
                return;
            case 186:
                this.currentTrack.height = (int) j;
                return;
            case 215:
                this.currentTrack.number = (int) j;
                return;
            case 231:
                this.clusterTimecodeUs = scaleTimecodeToUs(j);
                return;
            case 241:
                if (!this.seenClusterPositionForCurrentCuePoint) {
                    this.cueClusterPositions.add(j);
                    this.seenClusterPositionForCurrentCuePoint = true;
                    return;
                }
                return;
            case 251:
                this.sampleSeenReferenceBlock = true;
                return;
            case 16980:
                if (j != 3) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("ContentCompAlgo ");
                    sb.append(j);
                    sb.append(" not supported");
                    throw new ParserException(sb.toString());
                }
                return;
            case 17029:
                if (j < 1 || j > 2) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("DocTypeReadVersion ");
                    sb2.append(j);
                    sb2.append(" not supported");
                    throw new ParserException(sb2.toString());
                }
                return;
            case 17143:
                if (j != 1) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("EBMLReadVersion ");
                    sb3.append(j);
                    sb3.append(" not supported");
                    throw new ParserException(sb3.toString());
                }
                return;
            case 18401:
                if (j != 5) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("ContentEncAlgo ");
                    sb4.append(j);
                    sb4.append(" not supported");
                    throw new ParserException(sb4.toString());
                }
                return;
            case 18408:
                if (j != 1) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("AESSettingsCipherMode ");
                    sb5.append(j);
                    sb5.append(" not supported");
                    throw new ParserException(sb5.toString());
                }
                return;
            case 20529:
                if (j != 0) {
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("ContentEncodingOrder ");
                    sb6.append(j);
                    sb6.append(" not supported");
                    throw new ParserException(sb6.toString());
                }
                return;
            case 20530:
                if (j != 1) {
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("ContentEncodingScope ");
                    sb7.append(j);
                    sb7.append(" not supported");
                    throw new ParserException(sb7.toString());
                }
                return;
            case 21420:
                this.seekEntryPosition = j + this.segmentContentPosition;
                return;
            case 21432:
                int i2 = (int) j;
                if (i2 == 3) {
                    this.currentTrack.stereoMode = 1;
                    return;
                } else if (i2 != 15) {
                    switch (i2) {
                        case 0:
                            this.currentTrack.stereoMode = 0;
                            return;
                        case 1:
                            this.currentTrack.stereoMode = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.currentTrack.stereoMode = 3;
                    return;
                }
            case 21680:
                this.currentTrack.displayWidth = (int) j;
                return;
            case 21682:
                this.currentTrack.displayUnit = (int) j;
                return;
            case 21690:
                this.currentTrack.displayHeight = (int) j;
                return;
            case 21930:
                Track track2 = this.currentTrack;
                if (j == 1) {
                    z = true;
                }
                track2.flagDefault = z;
                return;
            case 21945:
                switch ((int) j) {
                    case 1:
                        this.currentTrack.colorRange = 2;
                        return;
                    case 2:
                        this.currentTrack.colorRange = 1;
                        return;
                    default:
                        return;
                }
            case 21946:
                int i3 = (int) j;
                if (i3 != 1) {
                    if (i3 == 16) {
                        this.currentTrack.colorTransfer = 6;
                        return;
                    } else if (i3 != 18) {
                        switch (i3) {
                            case 6:
                            case 7:
                                break;
                            default:
                                return;
                        }
                    } else {
                        this.currentTrack.colorTransfer = 7;
                        return;
                    }
                }
                this.currentTrack.colorTransfer = 3;
                return;
            case 21947:
                this.currentTrack.hasColorInfo = true;
                int i4 = (int) j;
                if (i4 == 1) {
                    this.currentTrack.colorSpace = 1;
                    return;
                } else if (i4 != 9) {
                    switch (i4) {
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            this.currentTrack.colorSpace = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.currentTrack.colorSpace = 6;
                    return;
                }
            case 21948:
                this.currentTrack.maxContentLuminance = (int) j;
                return;
            case 21949:
                this.currentTrack.maxFrameAverageLuminance = (int) j;
                return;
            case 22186:
                this.currentTrack.codecDelayNs = j;
                return;
            case 22203:
                this.currentTrack.seekPreRollNs = j;
                return;
            case 25188:
                this.currentTrack.audioBitDepth = (int) j;
                return;
            case 2352003:
                this.currentTrack.defaultSampleDurationNs = (int) j;
                return;
            case 2807729:
                this.timecodeScale = j;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void floatElement(int i, double d) {
        if (i == 181) {
            this.currentTrack.sampleRate = (int) d;
        } else if (i != 17545) {
            switch (i) {
                case 21969:
                    this.currentTrack.primaryRChromaticityX = (float) d;
                    return;
                case 21970:
                    this.currentTrack.primaryRChromaticityY = (float) d;
                    return;
                case 21971:
                    this.currentTrack.primaryGChromaticityX = (float) d;
                    return;
                case 21972:
                    this.currentTrack.primaryGChromaticityY = (float) d;
                    return;
                case 21973:
                    this.currentTrack.primaryBChromaticityX = (float) d;
                    return;
                case 21974:
                    this.currentTrack.primaryBChromaticityY = (float) d;
                    return;
                case 21975:
                    this.currentTrack.whitePointChromaticityX = (float) d;
                    return;
                case 21976:
                    this.currentTrack.whitePointChromaticityY = (float) d;
                    return;
                case 21977:
                    this.currentTrack.maxMasteringLuminance = (float) d;
                    return;
                case 21978:
                    this.currentTrack.minMasteringLuminance = (float) d;
                    return;
                default:
                    return;
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    /* access modifiers changed from: 0000 */
    public void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            this.currentTrack.codecId = str;
        } else if (i != 17026) {
            if (i == 2274716) {
                this.currentTrack.language = str;
            }
        } else if (!"webm".equals(str) && !"matroska".equals(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append("DocType ");
            sb.append(str);
            sb.append(" not supported");
            throw new ParserException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException {
        long j;
        byte b;
        int i3 = i;
        int i4 = i2;
        ExtractorInput extractorInput2 = extractorInput;
        int i5 = 0;
        int i6 = 1;
        if (i3 == 161 || i3 == 163) {
            if (this.blockState == 0) {
                this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(extractorInput2, false, true, 8);
                this.blockTrackNumberLength = this.varintReader.getLastLength();
                this.blockDurationUs = -9223372036854775807L;
                this.blockState = 1;
                this.scratch.reset();
            }
            Track track = (Track) this.tracks.get(this.blockTrackNumber);
            if (track == null) {
                extractorInput2.skipFully(i4 - this.blockTrackNumberLength);
                this.blockState = 0;
                return;
            }
            if (this.blockState == 1) {
                readScratch(extractorInput2, 3);
                int i7 = (this.scratch.data[2] & 6) >> 1;
                byte b2 = 255;
                if (i7 == 0) {
                    this.blockLacingSampleCount = 1;
                    this.blockLacingSampleSizes = ensureArrayCapacity(this.blockLacingSampleSizes, 1);
                    this.blockLacingSampleSizes[0] = (i4 - this.blockTrackNumberLength) - 3;
                } else if (i3 != 163) {
                    throw new ParserException("Lacing only supported in SimpleBlocks.");
                } else {
                    readScratch(extractorInput2, 4);
                    this.blockLacingSampleCount = (this.scratch.data[3] & 255) + 1;
                    this.blockLacingSampleSizes = ensureArrayCapacity(this.blockLacingSampleSizes, this.blockLacingSampleCount);
                    if (i7 == 2) {
                        Arrays.fill(this.blockLacingSampleSizes, 0, this.blockLacingSampleCount, ((i4 - this.blockTrackNumberLength) - 4) / this.blockLacingSampleCount);
                    } else if (i7 == 1) {
                        int i8 = 4;
                        int i9 = 0;
                        for (int i10 = 0; i10 < this.blockLacingSampleCount - 1; i10++) {
                            this.blockLacingSampleSizes[i10] = 0;
                            do {
                                i8++;
                                readScratch(extractorInput2, i8);
                                b = this.scratch.data[i8 - 1] & 255;
                                int[] iArr = this.blockLacingSampleSizes;
                                iArr[i10] = iArr[i10] + b;
                            } while (b == 255);
                            i9 += this.blockLacingSampleSizes[i10];
                        }
                        this.blockLacingSampleSizes[this.blockLacingSampleCount - 1] = ((i4 - this.blockTrackNumberLength) - i8) - i9;
                    } else if (i7 == 3) {
                        int i11 = 0;
                        int i12 = 4;
                        int i13 = 0;
                        while (i11 < this.blockLacingSampleCount - i6) {
                            this.blockLacingSampleSizes[i11] = i5;
                            i12++;
                            readScratch(extractorInput2, i12);
                            int i14 = i12 - 1;
                            if (this.scratch.data[i14] == 0) {
                                throw new ParserException("No valid varint length mask found");
                            }
                            long j2 = 0;
                            int i15 = 0;
                            while (true) {
                                if (i15 >= 8) {
                                    break;
                                }
                                int i16 = i6 << (7 - i15);
                                if ((this.scratch.data[i14] & i16) != 0) {
                                    i12 += i15;
                                    readScratch(extractorInput2, i12);
                                    int i17 = i14 + 1;
                                    j2 = (long) (this.scratch.data[i14] & b2 & (i16 ^ -1));
                                    while (i17 < i12) {
                                        i17++;
                                        j2 = (j2 << 8) | ((long) (this.scratch.data[i17] & b2));
                                        b2 = 255;
                                    }
                                    if (i11 > 0) {
                                        j = j2 - ((1 << ((i15 * 7) + 6)) - 1);
                                    }
                                } else {
                                    i15++;
                                    i6 = 1;
                                    b2 = 255;
                                }
                            }
                            j = j2;
                            if (j < -2147483648L || j > 2147483647L) {
                                throw new ParserException("EBML lacing sample size out of range.");
                            }
                            int i18 = (int) j;
                            int[] iArr2 = this.blockLacingSampleSizes;
                            if (i11 != 0) {
                                i18 += this.blockLacingSampleSizes[i11 - 1];
                            }
                            iArr2[i11] = i18;
                            i13 += this.blockLacingSampleSizes[i11];
                            i11++;
                            i5 = 0;
                            i6 = 1;
                            b2 = 255;
                        }
                        this.blockLacingSampleSizes[this.blockLacingSampleCount - 1] = ((i4 - this.blockTrackNumberLength) - i12) - i13;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unexpected lacing value: ");
                        sb.append(i7);
                        throw new ParserException(sb.toString());
                    }
                }
                this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((long) ((this.scratch.data[0] << 8) | (this.scratch.data[1] & 255)));
                this.blockFlags = ((track.type == 2 || (i3 == 163 && (this.scratch.data[2] & 128) == 128)) ? 1 : 0) | ((this.scratch.data[2] & 8) == 8 ? Integer.MIN_VALUE : 0);
                this.blockState = 2;
                this.blockLacingSampleIndex = 0;
            }
            if (i3 == 163) {
                while (this.blockLacingSampleIndex < this.blockLacingSampleCount) {
                    writeSampleData(extractorInput2, track, this.blockLacingSampleSizes[this.blockLacingSampleIndex]);
                    commitSampleToOutput(track, this.blockTimeUs + ((long) ((this.blockLacingSampleIndex * track.defaultSampleDurationNs) / 1000)));
                    this.blockLacingSampleIndex++;
                }
                this.blockState = 0;
            } else {
                writeSampleData(extractorInput2, track, this.blockLacingSampleSizes[0]);
            }
        } else if (i3 == 16981) {
            this.currentTrack.sampleStrippedBytes = new byte[i4];
            extractorInput2.readFully(this.currentTrack.sampleStrippedBytes, 0, i4);
        } else if (i3 == 18402) {
            byte[] bArr = new byte[i4];
            extractorInput2.readFully(bArr, 0, i4);
            this.currentTrack.cryptoData = new CryptoData(1, bArr, 0, 0);
        } else if (i3 == 21419) {
            Arrays.fill(this.seekEntryIdBytes.data, 0);
            extractorInput2.readFully(this.seekEntryIdBytes.data, 4 - i4, i4);
            this.seekEntryIdBytes.setPosition(0);
            this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
        } else if (i3 == 25506) {
            this.currentTrack.codecPrivate = new byte[i4];
            extractorInput2.readFully(this.currentTrack.codecPrivate, 0, i4);
        } else if (i3 != 30322) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unexpected id: ");
            sb2.append(i3);
            throw new ParserException(sb2.toString());
        } else {
            this.currentTrack.projectionData = new byte[i4];
            extractorInput2.readFully(this.currentTrack.projectionData, 0, i4);
        }
    }

    private void commitSampleToOutput(Track track, long j) {
        Track track2 = track;
        if (track2.trueHdSampleRechunker != null) {
            track2.trueHdSampleRechunker.sampleMetadata(track2, j);
        } else {
            long j2 = j;
            if ("S_TEXT/UTF8".equals(track2.codecId)) {
                commitSubtitleSample(track2, "%02d:%02d:%02d,%03d", 19, 1000, SUBRIP_TIMECODE_EMPTY);
            } else if ("S_TEXT/ASS".equals(track2.codecId)) {
                commitSubtitleSample(track2, "%01d:%02d:%02d:%02d", 21, 10000, SSA_TIMECODE_EMPTY);
            }
            track2.output.sampleMetadata(j2, this.blockFlags, this.sampleBytesWritten, 0, track2.cryptoData);
        }
        this.sampleRead = true;
        resetSample();
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (this.scratch.limit() < i) {
            if (this.scratch.capacity() < i) {
                this.scratch.reset(Arrays.copyOf(this.scratch.data, Math.max(this.scratch.data.length * 2, i)), this.scratch.limit());
            }
            extractorInput.readFully(this.scratch.data, this.scratch.limit(), i - this.scratch.limit());
            this.scratch.setLimit(i);
        }
    }

    private void writeSampleData(ExtractorInput extractorInput, Track track, int i) throws IOException, InterruptedException {
        if ("S_TEXT/UTF8".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
        } else if ("S_TEXT/ASS".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
        } else {
            TrackOutput trackOutput = track.output;
            boolean z = true;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i2 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.data, 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.data[0] & 128) == 128) {
                            throw new ParserException("Extension bit is set in signal byte");
                        }
                        this.sampleSignalByte = this.scratch.data[0];
                        this.sampleSignalByteRead = true;
                    }
                    if ((this.sampleSignalByte & 1) == 1) {
                        boolean z2 = (this.sampleSignalByte & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] bArr = this.scratch.data;
                            if (!z2) {
                                i2 = 0;
                            }
                            bArr[0] = (byte) (i2 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8);
                            this.sampleBytesWritten += 8;
                        }
                        if (z2) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.data, 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i3 = this.samplePartitionCount * 4;
                            this.scratch.reset(i3);
                            extractorInput.readFully(this.scratch.data, 0, i3);
                            this.sampleBytesRead += i3;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            int i4 = (s * 6) + 2;
                            if (this.encryptionSubsampleDataBuffer == null || this.encryptionSubsampleDataBuffer.capacity() < i4) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i4);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            int i5 = 0;
                            int i6 = 0;
                            while (i5 < this.samplePartitionCount) {
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i5 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i6));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i6);
                                }
                                i5++;
                                i6 = readUnsignedIntToInt;
                            }
                            int i7 = (i - this.sampleBytesRead) - i6;
                            if (this.samplePartitionCount % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i7);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i7);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i4);
                            trackOutput.sampleData(this.encryptionSubsampleData, i4);
                            this.sampleBytesWritten += i4;
                        }
                    }
                } else if (track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
                }
                this.sampleEncodingHandled = true;
            }
            int limit = i + this.sampleStrippedBytes.limit();
            if ("V_MPEG4/ISO/AVC".equals(track.codecId) || "V_MPEGH/ISO/HEVC".equals(track.codecId)) {
                byte[] bArr2 = this.nalLength.data;
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = 0;
                int i8 = track.nalUnitLengthFieldLength;
                int i9 = 4 - track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < limit) {
                    if (this.sampleCurrentNalBytesRemaining == 0) {
                        readToTarget(extractorInput, bArr2, i9, i8);
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        this.sampleCurrentNalBytesRemaining -= readToOutput(extractorInput, trackOutput, this.sampleCurrentNalBytesRemaining);
                    }
                }
            } else {
                if (track.trueHdSampleRechunker != null) {
                    if (this.sampleStrippedBytes.limit() != 0) {
                        z = false;
                    }
                    Assertions.checkState(z);
                    track.trueHdSampleRechunker.startSample(extractorInput, this.blockFlags, limit);
                }
                while (this.sampleBytesRead < limit) {
                    readToOutput(extractorInput, trackOutput, limit - this.sampleBytesRead);
                }
            }
            if ("A_VORBIS".equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
        }
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException, InterruptedException {
        int length = bArr.length + i;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.data = Arrays.copyOf(bArr, length + i);
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.data, 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.data, bArr.length, i);
        this.subtitleSample.reset(length);
    }

    private void commitSubtitleSample(Track track, String str, int i, long j, byte[] bArr) {
        setSampleDuration(this.subtitleSample.data, this.blockDurationUs, str, i, j, bArr);
        track.output.sampleData(this.subtitleSample, this.subtitleSample.limit());
        this.sampleBytesWritten += this.subtitleSample.limit();
    }

    private static void setSampleDuration(byte[] bArr, long j, String str, int i, long j2, byte[] bArr2) {
        byte[] bArr3;
        byte[] bArr4;
        if (j == -9223372036854775807L) {
            bArr4 = bArr2;
            bArr3 = bArr4;
        } else {
            int i2 = (int) (j / 3600000000L);
            long j3 = j - (((long) (i2 * 3600)) * 1000000);
            int i3 = (int) (j3 / 60000000);
            long j4 = j3 - (((long) (i3 * 60)) * 1000000);
            int i4 = (int) (j4 / 1000000);
            int i5 = (int) ((j4 - (((long) i4) * 1000000)) / j2);
            bArr3 = Util.getUtf8Bytes(String.format(Locale.US, str, new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)}));
            bArr4 = bArr2;
        }
        System.arraycopy(bArr3, 0, bArr, i, bArr4.length);
    }

    private void readToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int min = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + min, i2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, min);
        }
        this.sampleBytesRead += i2;
    }

    private int readToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException, InterruptedException {
        int i2;
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft > 0) {
            i2 = Math.min(i, bytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, i2);
        } else {
            i2 = trackOutput.sampleData(extractorInput, i, false);
        }
        this.sampleBytesRead += i2;
        this.sampleBytesWritten += i2;
        return i2;
    }

    private SeekMap buildSeekMap() {
        if (this.segmentContentPosition == -1 || this.durationUs == -9223372036854775807L || this.cueTimesUs == null || this.cueTimesUs.size() == 0 || this.cueClusterPositions == null || this.cueClusterPositions.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return new Unseekable(this.durationUs);
        }
        int size = this.cueTimesUs.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jArr3[i2] = this.cueTimesUs.get(i2);
            jArr[i2] = this.segmentContentPosition + this.cueClusterPositions.get(i2);
        }
        while (true) {
            int i3 = size - 1;
            if (i < i3) {
                int i4 = i + 1;
                iArr[i] = (int) (jArr[i4] - jArr[i]);
                jArr2[i] = jArr3[i4] - jArr3[i];
                i = i4;
            } else {
                iArr[i3] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i3]);
                jArr2[i3] = this.durationUs - jArr3[i3];
                this.cueTimesUs = null;
                this.cueClusterPositions = null;
                return new ChunkIndex(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        } else if (!this.sentSeekMap || this.seekPositionAfterBuildingCues == -1) {
            return false;
        } else {
            positionHolder.position = this.seekPositionAfterBuildingCues;
            this.seekPositionAfterBuildingCues = -1;
            return true;
        }
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        if (this.timecodeScale == -9223372036854775807L) {
            throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
        }
        return Util.scaleLargeTimestamp(j, this.timecodeScale, 1000);
    }

    private static boolean isCodecSupported(String str) {
        return "V_VP8".equals(str) || "V_VP9".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_TEXT/ASS".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str);
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }
}
