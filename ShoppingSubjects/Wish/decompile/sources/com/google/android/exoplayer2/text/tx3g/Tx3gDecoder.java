package com.google.android.exoplayer2.text.tx3g;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.nio.charset.Charset;
import java.util.List;

public final class Tx3gDecoder extends SimpleSubtitleDecoder {
    private static final int TYPE_STYL = Util.getIntegerCodeForString("styl");
    private static final int TYPE_TBOX = Util.getIntegerCodeForString("tbox");
    private int calculatedVideoTrackHeight;
    private boolean customVerticalPlacement;
    private int defaultColorRgba;
    private int defaultFontFace;
    private String defaultFontFamily;
    private float defaultVerticalPlacement;
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();

    public Tx3gDecoder(List<byte[]> list) {
        super("Tx3gDecoder");
        decodeInitializationData(list);
    }

    private void decodeInitializationData(List<byte[]> list) {
        boolean z = false;
        if (list != null && list.size() == 1 && (((byte[]) list.get(0)).length == 48 || ((byte[]) list.get(0)).length == 53)) {
            byte[] bArr = (byte[]) list.get(0);
            this.defaultFontFace = bArr[24];
            this.defaultColorRgba = ((bArr[26] & 255) << 24) | ((bArr[27] & 255) << 16) | ((bArr[28] & 255) << 8) | (bArr[29] & 255);
            this.defaultFontFamily = "Serif".equals(new String(bArr, 43, bArr.length - 43)) ? "serif" : "sans-serif";
            this.calculatedVideoTrackHeight = bArr[25] * 20;
            if ((bArr[0] & 32) != 0) {
                z = true;
            }
            this.customVerticalPlacement = z;
            if (this.customVerticalPlacement) {
                this.defaultVerticalPlacement = ((float) ((bArr[11] & 255) | ((bArr[10] & 255) << 8))) / ((float) this.calculatedVideoTrackHeight);
                this.defaultVerticalPlacement = Util.constrainValue(this.defaultVerticalPlacement, 0.0f, 0.95f);
                return;
            }
            this.defaultVerticalPlacement = 0.85f;
            return;
        }
        this.defaultFontFace = 0;
        this.defaultColorRgba = -1;
        this.defaultFontFamily = "sans-serif";
        this.customVerticalPlacement = false;
        this.defaultVerticalPlacement = 0.85f;
    }

    /* JADX WARNING: type inference failed for: r4v4 */
    /* JADX WARNING: type inference failed for: r4v5, types: [boolean] */
    /* JADX WARNING: type inference failed for: r4v6 */
    /* JADX WARNING: type inference failed for: r4v7, types: [int] */
    /* JADX WARNING: type inference failed for: r4v8, types: [int] */
    /* JADX WARNING: type inference failed for: r4v9 */
    /* JADX WARNING: type inference failed for: r4v10 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v4
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], int, ?[boolean, int, float, short, byte, char]]
      uses: [boolean, ?[int, byte, short, char], int]
      mth insns count: 71
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$69/1017384824.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.text.Subtitle decode(byte[] r10, int r11, boolean r12) throws com.google.android.exoplayer2.text.SubtitleDecoderException {
        /*
            r9 = this;
            com.google.android.exoplayer2.util.ParsableByteArray r12 = r9.parsableByteArray
            r12.reset(r10, r11)
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r9.parsableByteArray
            java.lang.String r10 = readSubtitleText(r10)
            boolean r11 = r10.isEmpty()
            if (r11 == 0) goto L_0x0014
            com.google.android.exoplayer2.text.tx3g.Tx3gSubtitle r10 = com.google.android.exoplayer2.text.tx3g.Tx3gSubtitle.EMPTY
            return r10
        L_0x0014:
            android.text.SpannableStringBuilder r11 = new android.text.SpannableStringBuilder
            r11.<init>(r10)
            int r1 = r9.defaultFontFace
            r2 = 0
            r3 = 0
            int r4 = r11.length()
            r5 = 16711680(0xff0000, float:2.3418052E-38)
            r0 = r11
            attachFontFace(r0, r1, r2, r3, r4, r5)
            int r1 = r9.defaultColorRgba
            r2 = -1
            int r4 = r11.length()
            attachColor(r0, r1, r2, r3, r4, r5)
            java.lang.String r1 = r9.defaultFontFamily
            java.lang.String r2 = "sans-serif"
            int r4 = r11.length()
            attachFontFamily(r0, r1, r2, r3, r4, r5)
            float r10 = r9.defaultVerticalPlacement
            r3 = r10
        L_0x003f:
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r9.parsableByteArray
            int r10 = r10.bytesLeft()
            r12 = 8
            if (r10 < r12) goto L_0x00ad
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r9.parsableByteArray
            int r10 = r10.getPosition()
            com.google.android.exoplayer2.util.ParsableByteArray r12 = r9.parsableByteArray
            int r12 = r12.readInt()
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.parsableByteArray
            int r0 = r0.readInt()
            int r1 = TYPE_STYL
            r2 = 2
            r4 = 0
            r5 = 1
            if (r0 != r1) goto L_0x007f
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.parsableByteArray
            int r0 = r0.bytesLeft()
            if (r0 < r2) goto L_0x006b
            goto L_0x006c
        L_0x006b:
            r5 = 0
        L_0x006c:
            assertTrue(r5)
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.parsableByteArray
            int r0 = r0.readUnsignedShort()
        L_0x0075:
            if (r4 >= r0) goto L_0x00a6
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r9.parsableByteArray
            r9.applyStyleRecord(r1, r11)
            int r4 = r4 + 1
            goto L_0x0075
        L_0x007f:
            int r1 = TYPE_TBOX
            if (r0 != r1) goto L_0x00a6
            boolean r0 = r9.customVerticalPlacement
            if (r0 == 0) goto L_0x00a6
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.parsableByteArray
            int r0 = r0.bytesLeft()
            if (r0 < r2) goto L_0x0090
            r4 = 1
        L_0x0090:
            assertTrue(r4)
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.parsableByteArray
            int r0 = r0.readUnsignedShort()
            float r0 = (float) r0
            int r1 = r9.calculatedVideoTrackHeight
            float r1 = (float) r1
            float r0 = r0 / r1
            r1 = 0
            r2 = 1064514355(0x3f733333, float:0.95)
            float r3 = com.google.android.exoplayer2.util.Util.constrainValue(r0, r1, r2)
        L_0x00a6:
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r9.parsableByteArray
            int r10 = r10 + r12
            r0.setPosition(r10)
            goto L_0x003f
        L_0x00ad:
            com.google.android.exoplayer2.text.tx3g.Tx3gSubtitle r10 = new com.google.android.exoplayer2.text.tx3g.Tx3gSubtitle
            com.google.android.exoplayer2.text.Cue r12 = new com.google.android.exoplayer2.text.Cue
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 1
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = 1
            r0 = r12
            r1 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r10.<init>(r12)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.tx3g.Tx3gDecoder.decode(byte[], int, boolean):com.google.android.exoplayer2.text.Subtitle");
    }

    private static String readSubtitleText(ParsableByteArray parsableByteArray2) throws SubtitleDecoderException {
        assertTrue(parsableByteArray2.bytesLeft() >= 2);
        int readUnsignedShort = parsableByteArray2.readUnsignedShort();
        if (readUnsignedShort == 0) {
            return "";
        }
        if (parsableByteArray2.bytesLeft() >= 2) {
            char peekChar = parsableByteArray2.peekChar();
            if (peekChar == 65279 || peekChar == 65534) {
                return parsableByteArray2.readString(readUnsignedShort, Charset.forName("UTF-16"));
            }
        }
        return parsableByteArray2.readString(readUnsignedShort, Charset.forName("UTF-8"));
    }

    private void applyStyleRecord(ParsableByteArray parsableByteArray2, SpannableStringBuilder spannableStringBuilder) throws SubtitleDecoderException {
        assertTrue(parsableByteArray2.bytesLeft() >= 12);
        int readUnsignedShort = parsableByteArray2.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray2.readUnsignedShort();
        parsableByteArray2.skipBytes(2);
        int readUnsignedByte = parsableByteArray2.readUnsignedByte();
        parsableByteArray2.skipBytes(1);
        int readInt = parsableByteArray2.readInt();
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        int i = readUnsignedShort;
        int i2 = readUnsignedShort2;
        attachFontFace(spannableStringBuilder2, readUnsignedByte, this.defaultFontFace, i, i2, 0);
        attachColor(spannableStringBuilder2, readInt, this.defaultColorRgba, i, i2, 0);
    }

    private static void attachFontFace(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            int i6 = i5 | 33;
            boolean z = true;
            boolean z2 = (i & 1) != 0;
            boolean z3 = (i & 2) != 0;
            if (z2) {
                if (z3) {
                    spannableStringBuilder.setSpan(new StyleSpan(3), i3, i4, i6);
                } else {
                    spannableStringBuilder.setSpan(new StyleSpan(1), i3, i4, i6);
                }
            } else if (z3) {
                spannableStringBuilder.setSpan(new StyleSpan(2), i3, i4, i6);
            }
            if ((i & 4) == 0) {
                z = false;
            }
            if (z) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i3, i4, i6);
            }
            if (!z && !z2 && !z3) {
                spannableStringBuilder.setSpan(new StyleSpan(0), i3, i4, i6);
            }
        }
    }

    private static void attachColor(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i >>> 8) | ((i & 255) << 24)), i3, i4, i5 | 33);
        }
    }

    private static void attachFontFamily(SpannableStringBuilder spannableStringBuilder, String str, String str2, int i, int i2, int i3) {
        if (str != str2) {
            spannableStringBuilder.setSpan(new TypefaceSpan(str), i, i2, i3 | 33);
        }
    }

    private static void assertTrue(boolean z) throws SubtitleDecoderException {
        if (!z) {
            throw new SubtitleDecoderException("Unexpected subtitle format.");
        }
    }
}
