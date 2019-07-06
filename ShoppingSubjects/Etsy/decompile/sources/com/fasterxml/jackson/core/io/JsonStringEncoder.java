package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.lang.ref.SoftReference;

public final class JsonStringEncoder {
    private static final byte[] HEX_BYTES = CharTypes.copyHexBytes();
    private static final char[] HEX_CHARS = CharTypes.copyHexChars();
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal<>();
    protected ByteArrayBuilder _byteBuilder;
    protected final char[] _quoteBuffer = new char[6];
    protected TextBuffer _textBuffer;

    public JsonStringEncoder() {
        this._quoteBuffer[0] = '\\';
        this._quoteBuffer[2] = '0';
        this._quoteBuffer[3] = '0';
    }

    public static JsonStringEncoder getInstance() {
        JsonStringEncoder jsonStringEncoder;
        SoftReference softReference = (SoftReference) _threadEncoder.get();
        if (softReference == null) {
            jsonStringEncoder = null;
        } else {
            jsonStringEncoder = (JsonStringEncoder) softReference.get();
        }
        if (jsonStringEncoder != null) {
            return jsonStringEncoder;
        }
        JsonStringEncoder jsonStringEncoder2 = new JsonStringEncoder();
        _threadEncoder.set(new SoftReference(jsonStringEncoder2));
        return jsonStringEncoder2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        if (r9 >= 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        r1 = _appendNumericEscape(r1, r11._quoteBuffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        r1 = _appendNamedEscape(r9, r11._quoteBuffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        r9 = r6 + r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        if (r9 <= r7.length) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        r9 = r7.length - r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        if (r9 <= 0) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r7, r6, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        r6 = r0.finishCurrentSegment();
        r1 = r1 - r9;
        java.lang.System.arraycopy(r11._quoteBuffer, r9, r6, 0, r1);
        r7 = r6;
        r6 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005b, code lost:
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r7, r6, r1);
        r6 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        r8 = r1 + 1;
        r1 = r12.charAt(r1);
        r9 = r2[r1];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] quoteAsString(java.lang.String r12) {
        /*
            r11 = this;
            com.fasterxml.jackson.core.util.TextBuffer r0 = r11._textBuffer
            if (r0 != 0) goto L_0x000c
            com.fasterxml.jackson.core.util.TextBuffer r0 = new com.fasterxml.jackson.core.util.TextBuffer
            r1 = 0
            r0.<init>(r1)
            r11._textBuffer = r0
        L_0x000c:
            char[] r1 = r0.emptyAndGetCurrentSegment()
            int[] r2 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            r3 = 0
            int r4 = r2.length
            int r5 = r12.length()
            r7 = r1
            r1 = r3
            r6 = r1
        L_0x001d:
            if (r1 >= r5) goto L_0x0078
        L_0x001f:
            char r8 = r12.charAt(r1)
            if (r8 >= r4) goto L_0x0063
            r9 = r2[r8]
            if (r9 == 0) goto L_0x0063
            int r8 = r1 + 1
            char r1 = r12.charAt(r1)
            r9 = r2[r1]
            if (r9 >= 0) goto L_0x003a
            char[] r9 = r11._quoteBuffer
            int r1 = r11._appendNumericEscape(r1, r9)
            goto L_0x0040
        L_0x003a:
            char[] r1 = r11._quoteBuffer
            int r1 = r11._appendNamedEscape(r9, r1)
        L_0x0040:
            int r9 = r6 + r1
            int r10 = r7.length
            if (r9 <= r10) goto L_0x005b
            int r9 = r7.length
            int r9 = r9 - r6
            if (r9 <= 0) goto L_0x004e
            char[] r10 = r11._quoteBuffer
            java.lang.System.arraycopy(r10, r3, r7, r6, r9)
        L_0x004e:
            char[] r6 = r0.finishCurrentSegment()
            int r1 = r1 - r9
            char[] r7 = r11._quoteBuffer
            java.lang.System.arraycopy(r7, r9, r6, r3, r1)
            r7 = r6
            r6 = r1
            goto L_0x0061
        L_0x005b:
            char[] r10 = r11._quoteBuffer
            java.lang.System.arraycopy(r10, r3, r7, r6, r1)
            r6 = r9
        L_0x0061:
            r1 = r8
            goto L_0x001d
        L_0x0063:
            int r9 = r7.length
            if (r6 < r9) goto L_0x006c
            char[] r6 = r0.finishCurrentSegment()
            r7 = r6
            r6 = r3
        L_0x006c:
            int r9 = r6 + 1
            r7[r6] = r8
            int r1 = r1 + 1
            if (r1 < r5) goto L_0x0076
            r6 = r9
            goto L_0x0078
        L_0x0076:
            r6 = r9
            goto L_0x001f
        L_0x0078:
            r0.setCurrentLength(r6)
            char[] r12 = r0.contentsAsArray()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
        if (r4 < r5.length) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        r5 = r0.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004a, code lost:
        r7 = r2 + 1;
        r2 = r12.charAt(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0050, code lost:
        if (r2 > 127) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0052, code lost:
        r4 = _appendByteEscape(r2, r6[r2], r0, r4);
        r5 = r0.getCurrentSegment();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0062, code lost:
        if (r2 > 2047) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        r6 = r4 + 1;
        r5[r4] = (byte) (192 | (r2 >> 6));
        r2 = (r2 & '?') | 128;
        r4 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0077, code lost:
        if (r2 < SURR1_FIRST) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007c, code lost:
        if (r2 <= SURR2_LAST) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0082, code lost:
        if (r2 <= SURR1_LAST) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0084, code lost:
        _illegalSurrogate(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0087, code lost:
        if (r7 < r1) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0089, code lost:
        _illegalSurrogate(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008c, code lost:
        r6 = r7 + 1;
        r2 = _convertSurrogate(r2, r12.charAt(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0099, code lost:
        if (r2 <= 1114111) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009b, code lost:
        _illegalSurrogate(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009e, code lost:
        r7 = r4 + 1;
        r5[r4] = (byte) (240 | (r2 >> 18));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a9, code lost:
        if (r7 < r5.length) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ab, code lost:
        r5 = r0.finishCurrentSegment();
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b0, code lost:
        r4 = r7 + 1;
        r5[r7] = (byte) (((r2 >> 12) & 63) | 128);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bb, code lost:
        if (r4 < r5.length) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bd, code lost:
        r5 = r0.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c3, code lost:
        r7 = r4 + 1;
        r5[r4] = (byte) (((r2 >> 6) & 63) | 128);
        r2 = (r2 & '?') | 128;
        r4 = r7;
        r7 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d3, code lost:
        r6 = r4 + 1;
        r5[r4] = (byte) (224 | (r2 >> 12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00de, code lost:
        if (r6 < r5.length) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e0, code lost:
        r5 = r0.finishCurrentSegment();
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e5, code lost:
        r4 = r6 + 1;
        r5[r6] = (byte) (((r2 >> 6) & 63) | 128);
        r2 = (r2 & '?') | 128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f3, code lost:
        if (r4 < r5.length) goto L_0x00fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f5, code lost:
        r5 = r0.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00fb, code lost:
        r6 = r4 + 1;
        r5[r4] = (byte) r2;
        r4 = r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] quoteAsUTF8(java.lang.String r12) {
        /*
            r11 = this;
            com.fasterxml.jackson.core.util.ByteArrayBuilder r0 = r11._byteBuilder
            if (r0 != 0) goto L_0x000c
            com.fasterxml.jackson.core.util.ByteArrayBuilder r0 = new com.fasterxml.jackson.core.util.ByteArrayBuilder
            r1 = 0
            r0.<init>(r1)
            r11._byteBuilder = r0
        L_0x000c:
            int r1 = r12.length()
            byte[] r2 = r0.resetAndGetFirstSegment()
            r3 = 0
            r5 = r2
            r2 = r3
            r4 = r2
        L_0x0018:
            if (r2 >= r1) goto L_0x0103
            int[] r6 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
        L_0x001e:
            char r7 = r12.charAt(r2)
            r8 = 127(0x7f, float:1.78E-43)
            if (r7 > r8) goto L_0x0042
            r9 = r6[r7]
            if (r9 == 0) goto L_0x002b
            goto L_0x0042
        L_0x002b:
            int r8 = r5.length
            if (r4 < r8) goto L_0x0034
            byte[] r4 = r0.finishCurrentSegment()
            r5 = r4
            r4 = r3
        L_0x0034:
            int r8 = r4 + 1
            byte r7 = (byte) r7
            r5[r4] = r7
            int r2 = r2 + 1
            if (r2 < r1) goto L_0x0040
            r4 = r8
            goto L_0x0103
        L_0x0040:
            r4 = r8
            goto L_0x001e
        L_0x0042:
            int r7 = r5.length
            if (r4 < r7) goto L_0x004a
            byte[] r5 = r0.finishCurrentSegment()
            r4 = r3
        L_0x004a:
            int r7 = r2 + 1
            char r2 = r12.charAt(r2)
            if (r2 > r8) goto L_0x005e
            r5 = r6[r2]
            int r4 = r11._appendByteEscape(r2, r5, r0, r4)
            byte[] r5 = r0.getCurrentSegment()
        L_0x005c:
            r2 = r7
            goto L_0x0018
        L_0x005e:
            r6 = 2047(0x7ff, float:2.868E-42)
            r8 = 128(0x80, float:1.794E-43)
            if (r2 > r6) goto L_0x0074
            int r6 = r4 + 1
            r9 = 192(0xc0, float:2.69E-43)
            int r10 = r2 >> 6
            r9 = r9 | r10
            byte r9 = (byte) r9
            r5[r4] = r9
            r2 = r2 & 63
            r2 = r2 | r8
            r4 = r6
            goto L_0x00f2
        L_0x0074:
            r6 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r6) goto L_0x00d3
            r6 = 57343(0xdfff, float:8.0355E-41)
            if (r2 <= r6) goto L_0x007f
            goto L_0x00d3
        L_0x007f:
            r6 = 56319(0xdbff, float:7.892E-41)
            if (r2 <= r6) goto L_0x0087
            _illegalSurrogate(r2)
        L_0x0087:
            if (r7 < r1) goto L_0x008c
            _illegalSurrogate(r2)
        L_0x008c:
            int r6 = r7 + 1
            char r7 = r12.charAt(r7)
            int r2 = _convertSurrogate(r2, r7)
            r7 = 1114111(0x10ffff, float:1.561202E-39)
            if (r2 <= r7) goto L_0x009e
            _illegalSurrogate(r2)
        L_0x009e:
            int r7 = r4 + 1
            r9 = 240(0xf0, float:3.36E-43)
            int r10 = r2 >> 18
            r9 = r9 | r10
            byte r9 = (byte) r9
            r5[r4] = r9
            int r4 = r5.length
            if (r7 < r4) goto L_0x00b0
            byte[] r5 = r0.finishCurrentSegment()
            r7 = r3
        L_0x00b0:
            int r4 = r7 + 1
            int r9 = r2 >> 12
            r9 = r9 & 63
            r9 = r9 | r8
            byte r9 = (byte) r9
            r5[r7] = r9
            int r7 = r5.length
            if (r4 < r7) goto L_0x00c3
            byte[] r4 = r0.finishCurrentSegment()
            r5 = r4
            r4 = r3
        L_0x00c3:
            int r7 = r4 + 1
            int r9 = r2 >> 6
            r9 = r9 & 63
            r9 = r9 | r8
            byte r9 = (byte) r9
            r5[r4] = r9
            r2 = r2 & 63
            r2 = r2 | r8
            r4 = r7
            r7 = r6
            goto L_0x00f2
        L_0x00d3:
            int r6 = r4 + 1
            r9 = 224(0xe0, float:3.14E-43)
            int r10 = r2 >> 12
            r9 = r9 | r10
            byte r9 = (byte) r9
            r5[r4] = r9
            int r4 = r5.length
            if (r6 < r4) goto L_0x00e5
            byte[] r5 = r0.finishCurrentSegment()
            r6 = r3
        L_0x00e5:
            int r4 = r6 + 1
            int r9 = r2 >> 6
            r9 = r9 & 63
            r9 = r9 | r8
            byte r9 = (byte) r9
            r5[r6] = r9
            r2 = r2 & 63
            r2 = r2 | r8
        L_0x00f2:
            int r6 = r5.length
            if (r4 < r6) goto L_0x00fb
            byte[] r4 = r0.finishCurrentSegment()
            r5 = r4
            r4 = r3
        L_0x00fb:
            int r6 = r4 + 1
            byte r2 = (byte) r2
            r5[r4] = r2
            r4 = r6
            goto L_0x005c
        L_0x0103:
            com.fasterxml.jackson.core.util.ByteArrayBuilder r12 = r11._byteBuilder
            byte[] r12 = r12.completeAndCoalesce(r4)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._byteBuilder = byteArrayBuilder;
        }
        int length = str.length();
        byte[] resetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int length2 = resetAndGetFirstSegment.length;
        byte[] bArr = resetAndGetFirstSegment;
        int i2 = 0;
        int i3 = length2;
        int i4 = 0;
        loop0:
        while (true) {
            if (i2 >= length) {
                break;
            }
            int i5 = i2 + 1;
            int charAt = str.charAt(i2);
            while (charAt <= 127) {
                if (i4 >= i3) {
                    byte[] finishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    i3 = finishCurrentSegment.length;
                    bArr = finishCurrentSegment;
                    i4 = 0;
                }
                int i6 = i4 + 1;
                bArr[i4] = (byte) charAt;
                if (i5 >= length) {
                    i4 = i6;
                    break loop0;
                }
                int i7 = i5 + 1;
                int charAt2 = str.charAt(i5);
                i5 = i7;
                charAt = charAt2;
                i4 = i6;
            }
            if (i4 >= i3) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i3 = bArr.length;
                i4 = 0;
            }
            if (charAt < 2048) {
                int i8 = i4 + 1;
                bArr[i4] = (byte) (192 | (charAt >> 6));
                i = i8;
            } else if (charAt < SURR1_FIRST || charAt > SURR2_LAST) {
                int i9 = i4 + 1;
                bArr[i4] = (byte) (224 | (charAt >> 12));
                if (i9 >= i3) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i9 = 0;
                    i3 = bArr.length;
                }
                i = i9 + 1;
                bArr[i9] = (byte) (((charAt >> 6) & 63) | 128);
            } else {
                if (charAt > SURR1_LAST) {
                    _illegalSurrogate(charAt);
                }
                if (i5 >= length) {
                    _illegalSurrogate(charAt);
                }
                int i10 = i5 + 1;
                charAt = _convertSurrogate(charAt, str.charAt(i5));
                if (charAt > 1114111) {
                    _illegalSurrogate(charAt);
                }
                int i11 = i4 + 1;
                bArr[i4] = (byte) (240 | (charAt >> 18));
                if (i11 >= i3) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i3 = bArr.length;
                    i11 = 0;
                }
                int i12 = i11 + 1;
                bArr[i11] = (byte) (((charAt >> 12) & 63) | 128);
                if (i12 >= i3) {
                    byte[] finishCurrentSegment2 = byteArrayBuilder.finishCurrentSegment();
                    i3 = finishCurrentSegment2.length;
                    bArr = finishCurrentSegment2;
                    i12 = 0;
                }
                int i13 = i12 + 1;
                bArr[i12] = (byte) (((charAt >> 6) & 63) | 128);
                i = i13;
                i5 = i10;
            }
            if (i >= i3) {
                byte[] finishCurrentSegment3 = byteArrayBuilder.finishCurrentSegment();
                i3 = finishCurrentSegment3.length;
                bArr = finishCurrentSegment3;
                i = 0;
            }
            int i14 = i + 1;
            bArr[i] = (byte) ((charAt & 63) | 128);
            i2 = i5;
            i4 = i14;
        }
        return this._byteBuilder.completeAndCoalesce(i4);
    }

    private int _appendNumericEscape(int i, char[] cArr) {
        cArr[1] = 'u';
        cArr[4] = HEX_CHARS[i >> 4];
        cArr[5] = HEX_CHARS[i & 15];
        return 6;
    }

    private int _appendNamedEscape(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByteEscape(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HEX_BYTES[i4 >> 4]);
                byteArrayBuilder.append(HEX_BYTES[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(HEX_BYTES[i >> 4]);
            byteArrayBuilder.append(HEX_BYTES[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    protected static int _convertSurrogate(int i, int i2) {
        if (i2 >= SURR2_FIRST && i2 <= SURR2_LAST) {
            return 65536 + ((i - SURR1_FIRST) << 10) + (i2 - SURR2_FIRST);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Broken surrogate pair: first char 0x");
        sb.append(Integer.toHexString(i));
        sb.append(", second 0x");
        sb.append(Integer.toHexString(i2));
        sb.append("; illegal combination");
        throw new IllegalArgumentException(sb.toString());
    }

    protected static void _illegalSurrogate(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }
}
