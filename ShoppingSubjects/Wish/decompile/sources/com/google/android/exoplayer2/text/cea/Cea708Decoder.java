package com.google.android.exoplayer2.text.cea;

import android.graphics.Color;
import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class Cea708Decoder extends CeaDecoder {
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final CueBuilder[] cueBuilders;
    private List<Cue> cues;
    private CueBuilder currentCueBuilder;
    private DtvCcPacket currentDtvCcPacket;
    private int currentWindow;
    private List<Cue> lastCues;
    private final int selectedServiceNumber;
    private final ParsableBitArray serviceBlockPacket = new ParsableBitArray();

    private static final class CueBuilder {
        public static final int COLOR_SOLID_BLACK = getArgbColorFromCeaColor(0, 0, 0, 0);
        public static final int COLOR_SOLID_WHITE = getArgbColorFromCeaColor(2, 2, 2, 0);
        public static final int COLOR_TRANSPARENT = getArgbColorFromCeaColor(0, 0, 0, 3);
        private static final int[] PEN_STYLE_BACKGROUND = {COLOR_SOLID_BLACK, COLOR_SOLID_BLACK, COLOR_SOLID_BLACK, COLOR_SOLID_BLACK, COLOR_SOLID_BLACK, COLOR_TRANSPARENT, COLOR_TRANSPARENT};
        private static final int[] PEN_STYLE_EDGE_TYPE = {0, 0, 0, 0, 0, 3, 3};
        private static final int[] PEN_STYLE_FONT_STYLE = {0, 1, 2, 3, 4, 3, 4};
        private static final int[] WINDOW_STYLE_FILL = {COLOR_SOLID_BLACK, COLOR_TRANSPARENT, COLOR_SOLID_BLACK, COLOR_SOLID_BLACK, COLOR_TRANSPARENT, COLOR_SOLID_BLACK, COLOR_SOLID_BLACK};
        private static final int[] WINDOW_STYLE_JUSTIFICATION = {0, 0, 0, 0, 0, 2, 0};
        private static final int[] WINDOW_STYLE_PRINT_DIRECTION = {0, 0, 0, 0, 0, 0, 2};
        private static final int[] WINDOW_STYLE_SCROLL_DIRECTION = {3, 3, 3, 3, 3, 3, 1};
        private static final boolean[] WINDOW_STYLE_WORD_WRAP = {false, false, false, true, true, true, false};
        private int anchorId;
        private int backgroundColor;
        private int backgroundColorStartPosition;
        private final SpannableStringBuilder captionStringBuilder = new SpannableStringBuilder();
        private boolean defined;
        private int foregroundColor;
        private int foregroundColorStartPosition;
        private int horizontalAnchor;
        private int italicsStartPosition;
        private int justification;
        private int penStyleId;
        private int priority;
        private boolean relativePositioning;
        private final List<SpannableString> rolledUpCaptions = new LinkedList();
        private int row;
        private int rowCount;
        private boolean rowLock;
        private int underlineStartPosition;
        private int verticalAnchor;
        private boolean visible;
        private int windowFillColor;
        private int windowStyleId;

        public CueBuilder() {
            reset();
        }

        public boolean isEmpty() {
            return !isDefined() || (this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0);
        }

        public void reset() {
            clear();
            this.defined = false;
            this.visible = false;
            this.priority = 4;
            this.relativePositioning = false;
            this.verticalAnchor = 0;
            this.horizontalAnchor = 0;
            this.anchorId = 0;
            this.rowCount = 15;
            this.rowLock = true;
            this.justification = 0;
            this.windowStyleId = 0;
            this.penStyleId = 0;
            this.windowFillColor = COLOR_SOLID_BLACK;
            this.foregroundColor = COLOR_SOLID_WHITE;
            this.backgroundColor = COLOR_SOLID_BLACK;
        }

        public void clear() {
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.clear();
            this.italicsStartPosition = -1;
            this.underlineStartPosition = -1;
            this.foregroundColorStartPosition = -1;
            this.backgroundColorStartPosition = -1;
            this.row = 0;
        }

        public boolean isDefined() {
            return this.defined;
        }

        public void setVisibility(boolean z) {
            this.visible = z;
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void defineWindow(boolean z, boolean z2, boolean z3, int i, boolean z4, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            boolean z5 = z2;
            int i9 = i7;
            int i10 = i8;
            this.defined = true;
            this.visible = z;
            this.rowLock = z5;
            this.priority = i;
            this.relativePositioning = z4;
            this.verticalAnchor = i2;
            this.horizontalAnchor = i3;
            this.anchorId = i6;
            int i11 = i4 + 1;
            if (this.rowCount != i11) {
                this.rowCount = i11;
                while (true) {
                    if ((!z5 || this.rolledUpCaptions.size() < this.rowCount) && this.rolledUpCaptions.size() < 15) {
                        break;
                    }
                    this.rolledUpCaptions.remove(0);
                }
            }
            if (!(i9 == 0 || this.windowStyleId == i9)) {
                this.windowStyleId = i9;
                int i12 = i9 - 1;
                setWindowAttributes(WINDOW_STYLE_FILL[i12], COLOR_TRANSPARENT, WINDOW_STYLE_WORD_WRAP[i12], 0, WINDOW_STYLE_PRINT_DIRECTION[i12], WINDOW_STYLE_SCROLL_DIRECTION[i12], WINDOW_STYLE_JUSTIFICATION[i12]);
            }
            if (i10 != 0 && this.penStyleId != i10) {
                this.penStyleId = i10;
                int i13 = i10 - 1;
                setPenAttributes(0, 1, 1, false, false, PEN_STYLE_EDGE_TYPE[i13], PEN_STYLE_FONT_STYLE[i13]);
                setPenColor(COLOR_SOLID_WHITE, PEN_STYLE_BACKGROUND[i13], COLOR_SOLID_BLACK);
            }
        }

        public void setWindowAttributes(int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
            this.windowFillColor = i;
            this.justification = i6;
        }

        public void setPenAttributes(int i, int i2, int i3, boolean z, boolean z2, int i4, int i5) {
            if (this.italicsStartPosition != -1) {
                if (!z) {
                    this.captionStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, this.captionStringBuilder.length(), 33);
                    this.italicsStartPosition = -1;
                }
            } else if (z) {
                this.italicsStartPosition = this.captionStringBuilder.length();
            }
            if (this.underlineStartPosition != -1) {
                if (!z2) {
                    this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, this.captionStringBuilder.length(), 33);
                    this.underlineStartPosition = -1;
                }
            } else if (z2) {
                this.underlineStartPosition = this.captionStringBuilder.length();
            }
        }

        public void setPenColor(int i, int i2, int i3) {
            if (!(this.foregroundColorStartPosition == -1 || this.foregroundColor == i)) {
                this.captionStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i != COLOR_SOLID_WHITE) {
                this.foregroundColorStartPosition = this.captionStringBuilder.length();
                this.foregroundColor = i;
            }
            if (!(this.backgroundColorStartPosition == -1 || this.backgroundColor == i2)) {
                this.captionStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i2 != COLOR_SOLID_BLACK) {
                this.backgroundColorStartPosition = this.captionStringBuilder.length();
                this.backgroundColor = i2;
            }
        }

        public void setPenLocation(int i, int i2) {
            if (this.row != i) {
                append(10);
            }
            this.row = i;
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
            }
        }

        public void append(char c) {
            if (c == 10) {
                this.rolledUpCaptions.add(buildSpannableString());
                this.captionStringBuilder.clear();
                if (this.italicsStartPosition != -1) {
                    this.italicsStartPosition = 0;
                }
                if (this.underlineStartPosition != -1) {
                    this.underlineStartPosition = 0;
                }
                if (this.foregroundColorStartPosition != -1) {
                    this.foregroundColorStartPosition = 0;
                }
                if (this.backgroundColorStartPosition != -1) {
                    this.backgroundColorStartPosition = 0;
                }
                while (true) {
                    if ((this.rowLock && this.rolledUpCaptions.size() >= this.rowCount) || this.rolledUpCaptions.size() >= 15) {
                        this.rolledUpCaptions.remove(0);
                    } else {
                        return;
                    }
                }
            } else {
                this.captionStringBuilder.append(c);
            }
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.italicsStartPosition != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, length, 33);
                }
                if (this.underlineStartPosition != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, length, 33);
                }
                if (this.foregroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, length, 33);
                }
                if (this.backgroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        public Cea708Cue build() {
            Alignment alignment;
            float f;
            float f2;
            if (isEmpty()) {
                return null;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i = 0; i < this.rolledUpCaptions.size(); i++) {
                spannableStringBuilder.append((CharSequence) this.rolledUpCaptions.get(i));
                spannableStringBuilder.append(10);
            }
            spannableStringBuilder.append(buildSpannableString());
            switch (this.justification) {
                case 0:
                case 3:
                    alignment = Alignment.ALIGN_NORMAL;
                    break;
                case 1:
                    alignment = Alignment.ALIGN_OPPOSITE;
                    break;
                case 2:
                    alignment = Alignment.ALIGN_CENTER;
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unexpected justification value: ");
                    sb.append(this.justification);
                    throw new IllegalArgumentException(sb.toString());
            }
            Alignment alignment2 = alignment;
            if (this.relativePositioning) {
                f2 = ((float) this.horizontalAnchor) / 99.0f;
                f = ((float) this.verticalAnchor) / 99.0f;
            } else {
                f2 = ((float) this.horizontalAnchor) / 209.0f;
                f = ((float) this.verticalAnchor) / 74.0f;
            }
            float f3 = (f2 * 0.9f) + 0.05f;
            float f4 = (f * 0.9f) + 0.05f;
            int i2 = this.anchorId % 3 == 0 ? 0 : this.anchorId % 3 == 1 ? 1 : 2;
            int i3 = this.anchorId / 3 == 0 ? 0 : this.anchorId / 3 == 1 ? 1 : 2;
            Cea708Cue cea708Cue = new Cea708Cue(spannableStringBuilder, alignment2, f4, 0, i2, f3, i3, Float.MIN_VALUE, this.windowFillColor != COLOR_SOLID_BLACK, this.windowFillColor, this.priority);
            return cea708Cue;
        }

        public static int getArgbColorFromCeaColor(int i, int i2, int i3) {
            return getArgbColorFromCeaColor(i, i2, i3, 0);
        }

        public static int getArgbColorFromCeaColor(int i, int i2, int i3, int i4) {
            int i5;
            Assertions.checkIndex(i, 0, 4);
            Assertions.checkIndex(i2, 0, 4);
            Assertions.checkIndex(i3, 0, 4);
            Assertions.checkIndex(i4, 0, 4);
            int i6 = 255;
            switch (i4) {
                case 2:
                    i5 = 127;
                    break;
                case 3:
                    i5 = 0;
                    break;
                default:
                    i5 = 255;
                    break;
            }
            int i7 = i > 1 ? 255 : 0;
            int i8 = i2 > 1 ? 255 : 0;
            if (i3 <= 1) {
                i6 = 0;
            }
            return Color.argb(i5, i7, i8, i6);
        }
    }

    private static final class DtvCcPacket {
        int currentIndex = 0;
        public final byte[] packetData;
        public final int packetSize;
        public final int sequenceNumber;

        public DtvCcPacket(int i, int i2) {
            this.sequenceNumber = i;
            this.packetSize = i2;
            this.packetData = new byte[((i2 * 2) - 1)];
        }
    }

    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        return super.dequeueOutputBuffer();
    }

    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    public /* bridge */ /* synthetic */ void setPositionUs(long j) {
        super.setPositionUs(j);
    }

    public Cea708Decoder(int i) {
        if (i == -1) {
            i = 1;
        }
        this.selectedServiceNumber = i;
        this.cueBuilders = new CueBuilder[8];
        for (int i2 = 0; i2 < 8; i2++) {
            this.cueBuilders[i2] = new CueBuilder();
        }
        this.currentCueBuilder = this.cueBuilders[0];
        resetCueBuilders();
    }

    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        this.currentWindow = 0;
        this.currentCueBuilder = this.cueBuilders[this.currentWindow];
        resetCueBuilders();
        this.currentDtvCcPacket = null;
    }

    /* access modifiers changed from: protected */
    public boolean isNewSubtitleDataAvailable() {
        return this.cues != this.lastCues;
    }

    /* access modifiers changed from: protected */
    public Subtitle createSubtitle() {
        this.lastCues = this.cues;
        return new CeaSubtitle(this.cues);
    }

    /* access modifiers changed from: protected */
    public void decode(SubtitleInputBuffer subtitleInputBuffer) {
        this.ccData.reset(subtitleInputBuffer.data.array(), subtitleInputBuffer.data.limit());
        while (this.ccData.bytesLeft() >= 3) {
            int readUnsignedByte = this.ccData.readUnsignedByte() & 7;
            int i = readUnsignedByte & 3;
            boolean z = false;
            boolean z2 = (readUnsignedByte & 4) == 4;
            byte readUnsignedByte2 = (byte) this.ccData.readUnsignedByte();
            byte readUnsignedByte3 = (byte) this.ccData.readUnsignedByte();
            if ((i == 2 || i == 3) && z2) {
                if (i == 3) {
                    finalizeCurrentPacket();
                    int i2 = (readUnsignedByte2 & 192) >> 6;
                    byte b = readUnsignedByte2 & 63;
                    if (b == 0) {
                        b = 64;
                    }
                    this.currentDtvCcPacket = new DtvCcPacket(i2, b);
                    byte[] bArr = this.currentDtvCcPacket.packetData;
                    DtvCcPacket dtvCcPacket = this.currentDtvCcPacket;
                    int i3 = dtvCcPacket.currentIndex;
                    dtvCcPacket.currentIndex = i3 + 1;
                    bArr[i3] = readUnsignedByte3;
                } else {
                    if (i == 2) {
                        z = true;
                    }
                    Assertions.checkArgument(z);
                    if (this.currentDtvCcPacket == null) {
                        Log.e("Cea708Decoder", "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                    } else {
                        byte[] bArr2 = this.currentDtvCcPacket.packetData;
                        DtvCcPacket dtvCcPacket2 = this.currentDtvCcPacket;
                        int i4 = dtvCcPacket2.currentIndex;
                        dtvCcPacket2.currentIndex = i4 + 1;
                        bArr2[i4] = readUnsignedByte2;
                        byte[] bArr3 = this.currentDtvCcPacket.packetData;
                        DtvCcPacket dtvCcPacket3 = this.currentDtvCcPacket;
                        int i5 = dtvCcPacket3.currentIndex;
                        dtvCcPacket3.currentIndex = i5 + 1;
                        bArr3[i5] = readUnsignedByte3;
                    }
                }
                if (this.currentDtvCcPacket.currentIndex == (this.currentDtvCcPacket.packetSize * 2) - 1) {
                    finalizeCurrentPacket();
                }
            }
        }
    }

    private void finalizeCurrentPacket() {
        if (this.currentDtvCcPacket != null) {
            processCurrentPacket();
            this.currentDtvCcPacket = null;
        }
    }

    private void processCurrentPacket() {
        if (this.currentDtvCcPacket.currentIndex != (this.currentDtvCcPacket.packetSize * 2) - 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("DtvCcPacket ended prematurely; size is ");
            sb.append((this.currentDtvCcPacket.packetSize * 2) - 1);
            sb.append(", but current index is ");
            sb.append(this.currentDtvCcPacket.currentIndex);
            sb.append(" (sequence number ");
            sb.append(this.currentDtvCcPacket.sequenceNumber);
            sb.append("); ignoring packet");
            Log.w("Cea708Decoder", sb.toString());
            return;
        }
        this.serviceBlockPacket.reset(this.currentDtvCcPacket.packetData, this.currentDtvCcPacket.currentIndex);
        int readBits = this.serviceBlockPacket.readBits(3);
        int readBits2 = this.serviceBlockPacket.readBits(5);
        if (readBits == 7) {
            this.serviceBlockPacket.skipBits(2);
            readBits += this.serviceBlockPacket.readBits(6);
        }
        if (readBits2 == 0) {
            if (readBits != 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("serviceNumber is non-zero (");
                sb2.append(readBits);
                sb2.append(") when blockSize is 0");
                Log.w("Cea708Decoder", sb2.toString());
            }
        } else if (readBits == this.selectedServiceNumber) {
            boolean z = false;
            while (this.serviceBlockPacket.bitsLeft() > 0) {
                int readBits3 = this.serviceBlockPacket.readBits(8);
                if (readBits3 == 16) {
                    int readBits4 = this.serviceBlockPacket.readBits(8);
                    if (readBits4 <= 31) {
                        handleC2Command(readBits4);
                    } else if (readBits4 <= 127) {
                        handleG2Character(readBits4);
                    } else if (readBits4 <= 159) {
                        handleC3Command(readBits4);
                    } else if (readBits4 <= 255) {
                        handleG3Character(readBits4);
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Invalid extended command: ");
                        sb3.append(readBits4);
                        Log.w("Cea708Decoder", sb3.toString());
                    }
                } else if (readBits3 <= 31) {
                    handleC0Command(readBits3);
                } else if (readBits3 <= 127) {
                    handleG0Character(readBits3);
                } else if (readBits3 <= 159) {
                    handleC1Command(readBits3);
                } else if (readBits3 <= 255) {
                    handleG1Character(readBits3);
                } else {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Invalid base command: ");
                    sb4.append(readBits3);
                    Log.w("Cea708Decoder", sb4.toString());
                }
                z = true;
            }
            if (z) {
                this.cues = getDisplayCues();
            }
        }
    }

    private void handleC0Command(int i) {
        if (i == 0) {
            return;
        }
        if (i == 3) {
            this.cues = getDisplayCues();
        } else if (i != 8) {
            switch (i) {
                case 12:
                    resetCueBuilders();
                    return;
                case 13:
                    this.currentCueBuilder.append(10);
                    return;
                case 14:
                    return;
                default:
                    if (i >= 17 && i <= 23) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Currently unsupported COMMAND_EXT1 Command: ");
                        sb.append(i);
                        Log.w("Cea708Decoder", sb.toString());
                        this.serviceBlockPacket.skipBits(8);
                        return;
                    } else if (i < 24 || i > 31) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Invalid C0 command: ");
                        sb2.append(i);
                        Log.w("Cea708Decoder", sb2.toString());
                        return;
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Currently unsupported COMMAND_P16 Command: ");
                        sb3.append(i);
                        Log.w("Cea708Decoder", sb3.toString());
                        this.serviceBlockPacket.skipBits(16);
                        return;
                    }
            }
        } else {
            this.currentCueBuilder.backspace();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0096, code lost:
        if (r2 > 8) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009e, code lost:
        if (r4.serviceBlockPacket.readBit() == false) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a0, code lost:
        r4.cueBuilders[8 - r2].reset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a9, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c8, code lost:
        if (r2 > 8) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d0, code lost:
        if (r4.serviceBlockPacket.readBit() == false) goto L_0x00dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d2, code lost:
        r4.cueBuilders[8 - r2].setVisibility(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00dc, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f6, code lost:
        if (r2 > 8) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00fe, code lost:
        if (r4.serviceBlockPacket.readBit() == false) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0100, code lost:
        r4.cueBuilders[8 - r2].clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0109, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleC1Command(int r5) {
        /*
            r4 = this;
            r0 = 16
            r1 = 8
            r2 = 1
            switch(r5) {
                case 128: goto L_0x010c;
                case 129: goto L_0x010c;
                case 130: goto L_0x010c;
                case 131: goto L_0x010c;
                case 132: goto L_0x010c;
                case 133: goto L_0x010c;
                case 134: goto L_0x010c;
                case 135: goto L_0x010c;
                case 136: goto L_0x00f6;
                case 137: goto L_0x00df;
                case 138: goto L_0x00c8;
                case 139: goto L_0x00ac;
                case 140: goto L_0x0096;
                case 141: goto L_0x008f;
                case 142: goto L_0x011a;
                case 143: goto L_0x008a;
                case 144: goto L_0x0076;
                case 145: goto L_0x0060;
                case 146: goto L_0x004c;
                default: goto L_0x0008;
            }
        L_0x0008:
            switch(r5) {
                case 151: goto L_0x0036;
                case 152: goto L_0x0023;
                case 153: goto L_0x0023;
                case 154: goto L_0x0023;
                case 155: goto L_0x0023;
                case 156: goto L_0x0023;
                case 157: goto L_0x0023;
                case 158: goto L_0x0023;
                case 159: goto L_0x0023;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.String r0 = "Cea708Decoder"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid C1 command: "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            android.util.Log.w(r0, r5)
            goto L_0x011a
        L_0x0023:
            int r5 = r5 + -152
            r4.handleDefineWindow(r5)
            int r0 = r4.currentWindow
            if (r0 == r5) goto L_0x011a
            r4.currentWindow = r5
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder[] r0 = r4.cueBuilders
            r5 = r0[r5]
            r4.currentCueBuilder = r5
            goto L_0x011a
        L_0x0036:
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder r5 = r4.currentCueBuilder
            boolean r5 = r5.isDefined()
            if (r5 != 0) goto L_0x0047
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r4.serviceBlockPacket
            r0 = 32
            r5.skipBits(r0)
            goto L_0x011a
        L_0x0047:
            r4.handleSetWindowAttributes()
            goto L_0x011a
        L_0x004c:
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder r5 = r4.currentCueBuilder
            boolean r5 = r5.isDefined()
            if (r5 != 0) goto L_0x005b
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r4.serviceBlockPacket
            r5.skipBits(r0)
            goto L_0x011a
        L_0x005b:
            r4.handleSetPenLocation()
            goto L_0x011a
        L_0x0060:
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder r5 = r4.currentCueBuilder
            boolean r5 = r5.isDefined()
            if (r5 != 0) goto L_0x0071
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r4.serviceBlockPacket
            r0 = 24
            r5.skipBits(r0)
            goto L_0x011a
        L_0x0071:
            r4.handleSetPenColor()
            goto L_0x011a
        L_0x0076:
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder r5 = r4.currentCueBuilder
            boolean r5 = r5.isDefined()
            if (r5 != 0) goto L_0x0085
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r4.serviceBlockPacket
            r5.skipBits(r0)
            goto L_0x011a
        L_0x0085:
            r4.handleSetPenAttributes()
            goto L_0x011a
        L_0x008a:
            r4.resetCueBuilders()
            goto L_0x011a
        L_0x008f:
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r4.serviceBlockPacket
            r5.skipBits(r1)
            goto L_0x011a
        L_0x0096:
            if (r2 > r1) goto L_0x011a
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r4.serviceBlockPacket
            boolean r5 = r5.readBit()
            if (r5 == 0) goto L_0x00a9
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder[] r5 = r4.cueBuilders
            int r0 = 8 - r2
            r5 = r5[r0]
            r5.reset()
        L_0x00a9:
            int r2 = r2 + 1
            goto L_0x0096
        L_0x00ac:
            r5 = 1
        L_0x00ad:
            if (r5 > r1) goto L_0x011a
            com.google.android.exoplayer2.util.ParsableBitArray r0 = r4.serviceBlockPacket
            boolean r0 = r0.readBit()
            if (r0 == 0) goto L_0x00c5
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder[] r0 = r4.cueBuilders
            int r3 = 8 - r5
            r0 = r0[r3]
            boolean r3 = r0.isVisible()
            r3 = r3 ^ r2
            r0.setVisibility(r3)
        L_0x00c5:
            int r5 = r5 + 1
            goto L_0x00ad
        L_0x00c8:
            if (r2 > r1) goto L_0x011a
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r4.serviceBlockPacket
            boolean r5 = r5.readBit()
            if (r5 == 0) goto L_0x00dc
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder[] r5 = r4.cueBuilders
            int r0 = 8 - r2
            r5 = r5[r0]
            r0 = 0
            r5.setVisibility(r0)
        L_0x00dc:
            int r2 = r2 + 1
            goto L_0x00c8
        L_0x00df:
            r5 = 1
        L_0x00e0:
            if (r5 > r1) goto L_0x011a
            com.google.android.exoplayer2.util.ParsableBitArray r0 = r4.serviceBlockPacket
            boolean r0 = r0.readBit()
            if (r0 == 0) goto L_0x00f3
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder[] r0 = r4.cueBuilders
            int r3 = 8 - r5
            r0 = r0[r3]
            r0.setVisibility(r2)
        L_0x00f3:
            int r5 = r5 + 1
            goto L_0x00e0
        L_0x00f6:
            if (r2 > r1) goto L_0x011a
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r4.serviceBlockPacket
            boolean r5 = r5.readBit()
            if (r5 == 0) goto L_0x0109
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder[] r5 = r4.cueBuilders
            int r0 = 8 - r2
            r5 = r5[r0]
            r5.clear()
        L_0x0109:
            int r2 = r2 + 1
            goto L_0x00f6
        L_0x010c:
            int r5 = r5 + -128
            int r0 = r4.currentWindow
            if (r0 == r5) goto L_0x011a
            r4.currentWindow = r5
            com.google.android.exoplayer2.text.cea.Cea708Decoder$CueBuilder[] r0 = r4.cueBuilders
            r5 = r0[r5]
            r4.currentCueBuilder = r5
        L_0x011a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea708Decoder.handleC1Command(int):void");
    }

    private void handleC2Command(int i) {
        if (i > 7) {
            if (i <= 15) {
                this.serviceBlockPacket.skipBits(8);
            } else if (i <= 23) {
                this.serviceBlockPacket.skipBits(16);
            } else if (i <= 31) {
                this.serviceBlockPacket.skipBits(24);
            }
        }
    }

    private void handleC3Command(int i) {
        if (i <= 135) {
            this.serviceBlockPacket.skipBits(32);
        } else if (i <= 143) {
            this.serviceBlockPacket.skipBits(40);
        } else if (i <= 159) {
            this.serviceBlockPacket.skipBits(2);
            this.serviceBlockPacket.skipBits(this.serviceBlockPacket.readBits(6) * 8);
        }
    }

    private void handleG0Character(int i) {
        if (i == 127) {
            this.currentCueBuilder.append(9835);
        } else {
            this.currentCueBuilder.append((char) (i & 255));
        }
    }

    private void handleG1Character(int i) {
        this.currentCueBuilder.append((char) (i & 255));
    }

    private void handleG2Character(int i) {
        if (i == 37) {
            this.currentCueBuilder.append(8230);
        } else if (i == 42) {
            this.currentCueBuilder.append(352);
        } else if (i == 44) {
            this.currentCueBuilder.append(338);
        } else if (i != 63) {
            switch (i) {
                case 32:
                    this.currentCueBuilder.append(' ');
                    return;
                case 33:
                    this.currentCueBuilder.append(160);
                    return;
                default:
                    switch (i) {
                        case 48:
                            this.currentCueBuilder.append(9608);
                            return;
                        case 49:
                            this.currentCueBuilder.append(8216);
                            return;
                        case 50:
                            this.currentCueBuilder.append(8217);
                            return;
                        case 51:
                            this.currentCueBuilder.append(8220);
                            return;
                        case 52:
                            this.currentCueBuilder.append(8221);
                            return;
                        case 53:
                            this.currentCueBuilder.append(8226);
                            return;
                        default:
                            switch (i) {
                                case 57:
                                    this.currentCueBuilder.append(8482);
                                    return;
                                case 58:
                                    this.currentCueBuilder.append(353);
                                    return;
                                default:
                                    switch (i) {
                                        case 60:
                                            this.currentCueBuilder.append(339);
                                            return;
                                        case 61:
                                            this.currentCueBuilder.append(8480);
                                            return;
                                        default:
                                            switch (i) {
                                                case 118:
                                                    this.currentCueBuilder.append(8539);
                                                    return;
                                                case 119:
                                                    this.currentCueBuilder.append(8540);
                                                    return;
                                                case 120:
                                                    this.currentCueBuilder.append(8541);
                                                    return;
                                                case 121:
                                                    this.currentCueBuilder.append(8542);
                                                    return;
                                                case 122:
                                                    this.currentCueBuilder.append(9474);
                                                    return;
                                                case 123:
                                                    this.currentCueBuilder.append(9488);
                                                    return;
                                                case 124:
                                                    this.currentCueBuilder.append(9492);
                                                    return;
                                                case 125:
                                                    this.currentCueBuilder.append(9472);
                                                    return;
                                                case 126:
                                                    this.currentCueBuilder.append(9496);
                                                    return;
                                                case 127:
                                                    this.currentCueBuilder.append(9484);
                                                    return;
                                                default:
                                                    StringBuilder sb = new StringBuilder();
                                                    sb.append("Invalid G2 character: ");
                                                    sb.append(i);
                                                    Log.w("Cea708Decoder", sb.toString());
                                                    return;
                                            }
                                    }
                            }
                    }
            }
        } else {
            this.currentCueBuilder.append(376);
        }
    }

    private void handleG3Character(int i) {
        if (i == 160) {
            this.currentCueBuilder.append(13252);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid G3 character: ");
        sb.append(i);
        Log.w("Cea708Decoder", sb.toString());
        this.currentCueBuilder.append('_');
    }

    private void handleSetPenAttributes() {
        this.currentCueBuilder.setPenAttributes(this.serviceBlockPacket.readBits(4), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBit(), this.serviceBlockPacket.readBit(), this.serviceBlockPacket.readBits(3), this.serviceBlockPacket.readBits(3));
    }

    private void handleSetPenColor() {
        int argbColorFromCeaColor = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2));
        int argbColorFromCeaColor2 = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2));
        this.serviceBlockPacket.skipBits(2);
        this.currentCueBuilder.setPenColor(argbColorFromCeaColor, argbColorFromCeaColor2, CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2)));
    }

    private void handleSetPenLocation() {
        this.serviceBlockPacket.skipBits(4);
        int readBits = this.serviceBlockPacket.readBits(4);
        this.serviceBlockPacket.skipBits(2);
        this.currentCueBuilder.setPenLocation(readBits, this.serviceBlockPacket.readBits(6));
    }

    private void handleSetWindowAttributes() {
        int argbColorFromCeaColor = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2));
        int readBits = this.serviceBlockPacket.readBits(2);
        int argbColorFromCeaColor2 = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2));
        if (this.serviceBlockPacket.readBit()) {
            readBits |= 4;
        }
        int i = readBits;
        boolean readBit = this.serviceBlockPacket.readBit();
        int readBits2 = this.serviceBlockPacket.readBits(2);
        int readBits3 = this.serviceBlockPacket.readBits(2);
        int readBits4 = this.serviceBlockPacket.readBits(2);
        this.serviceBlockPacket.skipBits(8);
        this.currentCueBuilder.setWindowAttributes(argbColorFromCeaColor, argbColorFromCeaColor2, readBit, i, readBits2, readBits3, readBits4);
    }

    private void handleDefineWindow(int i) {
        CueBuilder cueBuilder = this.cueBuilders[i];
        this.serviceBlockPacket.skipBits(2);
        boolean readBit = this.serviceBlockPacket.readBit();
        boolean readBit2 = this.serviceBlockPacket.readBit();
        boolean readBit3 = this.serviceBlockPacket.readBit();
        int readBits = this.serviceBlockPacket.readBits(3);
        boolean readBit4 = this.serviceBlockPacket.readBit();
        int readBits2 = this.serviceBlockPacket.readBits(7);
        int readBits3 = this.serviceBlockPacket.readBits(8);
        int readBits4 = this.serviceBlockPacket.readBits(4);
        int readBits5 = this.serviceBlockPacket.readBits(4);
        this.serviceBlockPacket.skipBits(2);
        int readBits6 = this.serviceBlockPacket.readBits(6);
        this.serviceBlockPacket.skipBits(2);
        cueBuilder.defineWindow(readBit, readBit2, readBit3, readBits, readBit4, readBits2, readBits3, readBits5, readBits6, readBits4, this.serviceBlockPacket.readBits(3), this.serviceBlockPacket.readBits(3));
    }

    private List<Cue> getDisplayCues() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            if (!this.cueBuilders[i].isEmpty() && this.cueBuilders[i].isVisible()) {
                arrayList.add(this.cueBuilders[i].build());
            }
        }
        Collections.sort(arrayList);
        return Collections.unmodifiableList(arrayList);
    }

    private void resetCueBuilders() {
        for (int i = 0; i < 8; i++) {
            this.cueBuilders[i].reset();
        }
    }
}
