package org.apache.commons.codec.binary;

public class Base32 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;
    private static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 63, -1, -1, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private static final byte[] ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 50, 51, 52, 53, 54, 55};
    private static final byte[] HEX_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 63, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};
    private static final byte[] HEX_ENCODE_TABLE = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86};
    private static final int MASK_5BITS = 31;
    private long bitWorkArea;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;

    public Base32() {
        this(false);
    }

    public Base32(boolean z) {
        this(0, null, z);
    }

    public Base32(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base32(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public Base32(int i, byte[] bArr, boolean z) {
        super(5, 8, i, bArr == null ? 0 : bArr.length);
        if (z) {
            this.encodeTable = HEX_ENCODE_TABLE;
            this.decodeTable = HEX_DECODE_TABLE;
        } else {
            this.encodeTable = ENCODE_TABLE;
            this.decodeTable = DECODE_TABLE;
        }
        if (i <= 0) {
            this.encodeSize = 8;
            this.lineSeparator = null;
        } else if (bArr == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("lineLength ");
            sb.append(i);
            sb.append(" > 0, but lineSeparator is null");
            throw new IllegalArgumentException(sb.toString());
        } else if (containsAlphabetOrPad(bArr)) {
            String newStringUtf8 = StringUtils.newStringUtf8(bArr);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("lineSeparator must not contain Base32 characters: [");
            sb2.append(newStringUtf8);
            sb2.append("]");
            throw new IllegalArgumentException(sb2.toString());
        } else {
            this.encodeSize = 8 + bArr.length;
            this.lineSeparator = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.lineSeparator, 0, bArr.length);
        }
        this.decodeSize = this.encodeSize - 1;
    }

    /* access modifiers changed from: 0000 */
    public void decode(byte[] bArr, int i, int i2) {
        int i3;
        int i4 = i2;
        if (!this.eof) {
            boolean z = true;
            if (i4 < 0) {
                this.eof = true;
            }
            int i5 = 0;
            int i6 = i;
            while (true) {
                if (i5 >= i4) {
                    break;
                }
                int i7 = i6 + 1;
                byte b = bArr[i6];
                if (b == 61) {
                    this.eof = z;
                    break;
                }
                ensureBufferSize(this.decodeSize);
                if (b >= 0 && b < this.decodeTable.length) {
                    byte b2 = this.decodeTable[b];
                    if (b2 >= 0) {
                        this.modulus = (this.modulus + (z ? 1 : 0)) % 8;
                        i3 = i5;
                        this.bitWorkArea = (this.bitWorkArea << 5) + ((long) b2);
                        if (this.modulus == 0) {
                            byte[] bArr2 = this.buffer;
                            int i8 = this.pos;
                            this.pos = i8 + 1;
                            bArr2[i8] = (byte) ((int) ((this.bitWorkArea >> 32) & 255));
                            byte[] bArr3 = this.buffer;
                            int i9 = this.pos;
                            this.pos = i9 + 1;
                            bArr3[i9] = (byte) ((int) ((this.bitWorkArea >> 24) & 255));
                            byte[] bArr4 = this.buffer;
                            int i10 = this.pos;
                            this.pos = i10 + 1;
                            bArr4[i10] = (byte) ((int) ((this.bitWorkArea >> 16) & 255));
                            byte[] bArr5 = this.buffer;
                            int i11 = this.pos;
                            this.pos = i11 + 1;
                            bArr5[i11] = (byte) ((int) ((this.bitWorkArea >> 8) & 255));
                            byte[] bArr6 = this.buffer;
                            int i12 = this.pos;
                            this.pos = i12 + 1;
                            bArr6[i12] = (byte) ((int) (this.bitWorkArea & 255));
                        }
                        i5 = i3 + 1;
                        i6 = i7;
                        z = true;
                    }
                }
                i3 = i5;
                i5 = i3 + 1;
                i6 = i7;
                z = true;
            }
            if (this.eof && this.modulus >= 2) {
                ensureBufferSize(this.decodeSize);
                switch (this.modulus) {
                    case 2:
                        byte[] bArr7 = this.buffer;
                        int i13 = this.pos;
                        this.pos = i13 + 1;
                        bArr7[i13] = (byte) ((int) ((this.bitWorkArea >> 2) & 255));
                        break;
                    case 3:
                        byte[] bArr8 = this.buffer;
                        int i14 = this.pos;
                        this.pos = i14 + 1;
                        bArr8[i14] = (byte) ((int) ((this.bitWorkArea >> 7) & 255));
                        break;
                    case 4:
                        this.bitWorkArea >>= 4;
                        byte[] bArr9 = this.buffer;
                        int i15 = this.pos;
                        this.pos = i15 + 1;
                        bArr9[i15] = (byte) ((int) ((this.bitWorkArea >> 8) & 255));
                        byte[] bArr10 = this.buffer;
                        int i16 = this.pos;
                        this.pos = i16 + 1;
                        bArr10[i16] = (byte) ((int) (this.bitWorkArea & 255));
                        break;
                    case 5:
                        this.bitWorkArea >>= 1;
                        byte[] bArr11 = this.buffer;
                        int i17 = this.pos;
                        this.pos = i17 + 1;
                        bArr11[i17] = (byte) ((int) ((this.bitWorkArea >> 16) & 255));
                        byte[] bArr12 = this.buffer;
                        int i18 = this.pos;
                        this.pos = i18 + 1;
                        bArr12[i18] = (byte) ((int) ((this.bitWorkArea >> 8) & 255));
                        byte[] bArr13 = this.buffer;
                        int i19 = this.pos;
                        this.pos = i19 + 1;
                        bArr13[i19] = (byte) ((int) (this.bitWorkArea & 255));
                        break;
                    case 6:
                        this.bitWorkArea >>= 6;
                        byte[] bArr14 = this.buffer;
                        int i20 = this.pos;
                        this.pos = i20 + 1;
                        bArr14[i20] = (byte) ((int) ((this.bitWorkArea >> 16) & 255));
                        byte[] bArr15 = this.buffer;
                        int i21 = this.pos;
                        this.pos = i21 + 1;
                        bArr15[i21] = (byte) ((int) ((this.bitWorkArea >> 8) & 255));
                        byte[] bArr16 = this.buffer;
                        int i22 = this.pos;
                        this.pos = i22 + 1;
                        bArr16[i22] = (byte) ((int) (this.bitWorkArea & 255));
                        break;
                    case 7:
                        this.bitWorkArea >>= 3;
                        byte[] bArr17 = this.buffer;
                        int i23 = this.pos;
                        this.pos = i23 + 1;
                        bArr17[i23] = (byte) ((int) ((this.bitWorkArea >> 24) & 255));
                        byte[] bArr18 = this.buffer;
                        int i24 = this.pos;
                        this.pos = i24 + 1;
                        bArr18[i24] = (byte) ((int) ((this.bitWorkArea >> 16) & 255));
                        byte[] bArr19 = this.buffer;
                        int i25 = this.pos;
                        this.pos = i25 + 1;
                        bArr19[i25] = (byte) ((int) ((this.bitWorkArea >> 8) & 255));
                        byte[] bArr20 = this.buffer;
                        int i26 = this.pos;
                        this.pos = i26 + 1;
                        bArr20[i26] = (byte) ((int) (this.bitWorkArea & 255));
                        break;
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v42 */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r2v2, types: [byte, int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void encode(byte[] r13, int r14, int r15) {
        /*
            r12 = this;
            boolean r0 = r12.eof
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            r1 = 1
            if (r15 >= 0) goto L_0x025a
            r12.eof = r1
            int r13 = r12.modulus
            if (r13 != 0) goto L_0x0014
            int r13 = r12.lineLength
            if (r13 != 0) goto L_0x0014
            return
        L_0x0014:
            int r13 = r12.encodeSize
            r12.ensureBufferSize(r13)
            int r13 = r12.pos
            int r14 = r12.modulus
            r15 = 4
            r2 = 3
            r3 = 2
            r4 = 61
            switch(r14) {
                case 1: goto L_0x01cf;
                case 2: goto L_0x0153;
                case 3: goto L_0x00c9;
                case 4: goto L_0x0027;
                default: goto L_0x0025;
            }
        L_0x0025:
            goto L_0x0234
        L_0x0027:
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            byte[] r1 = r12.encodeTable
            long r5 = r12.bitWorkArea
            r7 = 27
            long r5 = r5 >> r7
            int r5 = (int) r5
            r5 = r5 & 31
            byte r1 = r1[r5]
            r14[r15] = r1
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            byte[] r1 = r12.encodeTable
            long r5 = r12.bitWorkArea
            r7 = 22
            long r5 = r5 >> r7
            int r5 = (int) r5
            r5 = r5 & 31
            byte r1 = r1[r5]
            r14[r15] = r1
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            byte[] r1 = r12.encodeTable
            long r5 = r12.bitWorkArea
            r7 = 17
            long r5 = r5 >> r7
            int r5 = (int) r5
            r5 = r5 & 31
            byte r1 = r1[r5]
            r14[r15] = r1
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            byte[] r1 = r12.encodeTable
            long r5 = r12.bitWorkArea
            r7 = 12
            long r5 = r5 >> r7
            int r5 = (int) r5
            r5 = r5 & 31
            byte r1 = r1[r5]
            r14[r15] = r1
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            byte[] r1 = r12.encodeTable
            long r5 = r12.bitWorkArea
            r7 = 7
            long r5 = r5 >> r7
            int r5 = (int) r5
            r5 = r5 & 31
            byte r1 = r1[r5]
            r14[r15] = r1
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            byte[] r1 = r12.encodeTable
            long r5 = r12.bitWorkArea
            long r5 = r5 >> r3
            int r3 = (int) r5
            r3 = r3 & 31
            byte r1 = r1[r3]
            r14[r15] = r1
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            byte[] r1 = r12.encodeTable
            long r5 = r12.bitWorkArea
            long r2 = r5 << r2
            int r2 = (int) r2
            r2 = r2 & 31
            byte r1 = r1[r2]
            r14[r15] = r1
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            goto L_0x0234
        L_0x00c9:
            byte[] r14 = r12.buffer
            int r2 = r12.pos
            int r3 = r2 + 1
            r12.pos = r3
            byte[] r3 = r12.encodeTable
            long r5 = r12.bitWorkArea
            r7 = 19
            long r5 = r5 >> r7
            int r5 = (int) r5
            r5 = r5 & 31
            byte r3 = r3[r5]
            r14[r2] = r3
            byte[] r14 = r12.buffer
            int r2 = r12.pos
            int r3 = r2 + 1
            r12.pos = r3
            byte[] r3 = r12.encodeTable
            long r5 = r12.bitWorkArea
            r7 = 14
            long r5 = r5 >> r7
            int r5 = (int) r5
            r5 = r5 & 31
            byte r3 = r3[r5]
            r14[r2] = r3
            byte[] r14 = r12.buffer
            int r2 = r12.pos
            int r3 = r2 + 1
            r12.pos = r3
            byte[] r3 = r12.encodeTable
            long r5 = r12.bitWorkArea
            r7 = 9
            long r5 = r5 >> r7
            int r5 = (int) r5
            r5 = r5 & 31
            byte r3 = r3[r5]
            r14[r2] = r3
            byte[] r14 = r12.buffer
            int r2 = r12.pos
            int r3 = r2 + 1
            r12.pos = r3
            byte[] r3 = r12.encodeTable
            long r5 = r12.bitWorkArea
            long r5 = r5 >> r15
            int r15 = (int) r5
            r15 = r15 & 31
            byte r15 = r3[r15]
            r14[r2] = r15
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r2 = r15 + 1
            r12.pos = r2
            byte[] r2 = r12.encodeTable
            long r5 = r12.bitWorkArea
            long r5 = r5 << r1
            int r1 = (int) r5
            r1 = r1 & 31
            byte r1 = r2[r1]
            r14[r15] = r1
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            goto L_0x0234
        L_0x0153:
            byte[] r14 = r12.buffer
            int r2 = r12.pos
            int r3 = r2 + 1
            r12.pos = r3
            byte[] r3 = r12.encodeTable
            long r5 = r12.bitWorkArea
            r7 = 11
            long r5 = r5 >> r7
            int r5 = (int) r5
            r5 = r5 & 31
            byte r3 = r3[r5]
            r14[r2] = r3
            byte[] r14 = r12.buffer
            int r2 = r12.pos
            int r3 = r2 + 1
            r12.pos = r3
            byte[] r3 = r12.encodeTable
            long r5 = r12.bitWorkArea
            r7 = 6
            long r5 = r5 >> r7
            int r5 = (int) r5
            r5 = r5 & 31
            byte r3 = r3[r5]
            r14[r2] = r3
            byte[] r14 = r12.buffer
            int r2 = r12.pos
            int r3 = r2 + 1
            r12.pos = r3
            byte[] r3 = r12.encodeTable
            long r5 = r12.bitWorkArea
            long r5 = r5 >> r1
            int r1 = (int) r5
            r1 = r1 & 31
            byte r1 = r3[r1]
            r14[r2] = r1
            byte[] r14 = r12.buffer
            int r1 = r12.pos
            int r2 = r1 + 1
            r12.pos = r2
            byte[] r2 = r12.encodeTable
            long r5 = r12.bitWorkArea
            long r5 = r5 << r15
            int r15 = (int) r5
            r15 = r15 & 31
            byte r15 = r2[r15]
            r14[r1] = r15
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            goto L_0x0234
        L_0x01cf:
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            byte[] r1 = r12.encodeTable
            long r5 = r12.bitWorkArea
            long r5 = r5 >> r2
            int r2 = (int) r5
            r2 = r2 & 31
            byte r1 = r1[r2]
            r14[r15] = r1
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            byte[] r1 = r12.encodeTable
            long r5 = r12.bitWorkArea
            long r2 = r5 << r3
            int r2 = (int) r2
            r2 = r2 & 31
            byte r1 = r1[r2]
            r14[r15] = r1
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            int r1 = r15 + 1
            r12.pos = r1
            r14[r15] = r4
        L_0x0234:
            int r14 = r12.currentLinePos
            int r15 = r12.pos
            int r15 = r15 - r13
            int r14 = r14 + r15
            r12.currentLinePos = r14
            int r13 = r12.lineLength
            if (r13 <= 0) goto L_0x0355
            int r13 = r12.currentLinePos
            if (r13 <= 0) goto L_0x0355
            byte[] r13 = r12.lineSeparator
            byte[] r14 = r12.buffer
            int r15 = r12.pos
            byte[] r1 = r12.lineSeparator
            int r1 = r1.length
            java.lang.System.arraycopy(r13, r0, r14, r15, r1)
            int r13 = r12.pos
            byte[] r14 = r12.lineSeparator
            int r14 = r14.length
            int r13 = r13 + r14
            r12.pos = r13
            goto L_0x0355
        L_0x025a:
            r2 = r14
            r14 = r0
        L_0x025c:
            if (r14 >= r15) goto L_0x0355
            int r3 = r12.encodeSize
            r12.ensureBufferSize(r3)
            int r3 = r12.modulus
            int r3 = r3 + r1
            r4 = 5
            int r3 = r3 % r4
            r12.modulus = r3
            int r3 = r2 + 1
            byte r2 = r13[r2]
            if (r2 >= 0) goto L_0x0272
            int r2 = r2 + 256
        L_0x0272:
            long r5 = r12.bitWorkArea
            r7 = 8
            long r5 = r5 << r7
            long r8 = (long) r2
            long r10 = r5 + r8
            r12.bitWorkArea = r10
            int r2 = r12.modulus
            if (r2 != 0) goto L_0x0350
            byte[] r2 = r12.buffer
            int r5 = r12.pos
            int r6 = r5 + 1
            r12.pos = r6
            byte[] r6 = r12.encodeTable
            long r8 = r12.bitWorkArea
            r10 = 35
            long r8 = r8 >> r10
            int r8 = (int) r8
            r8 = r8 & 31
            byte r6 = r6[r8]
            r2[r5] = r6
            byte[] r2 = r12.buffer
            int r5 = r12.pos
            int r6 = r5 + 1
            r12.pos = r6
            byte[] r6 = r12.encodeTable
            long r8 = r12.bitWorkArea
            r10 = 30
            long r8 = r8 >> r10
            int r8 = (int) r8
            r8 = r8 & 31
            byte r6 = r6[r8]
            r2[r5] = r6
            byte[] r2 = r12.buffer
            int r5 = r12.pos
            int r6 = r5 + 1
            r12.pos = r6
            byte[] r6 = r12.encodeTable
            long r8 = r12.bitWorkArea
            r10 = 25
            long r8 = r8 >> r10
            int r8 = (int) r8
            r8 = r8 & 31
            byte r6 = r6[r8]
            r2[r5] = r6
            byte[] r2 = r12.buffer
            int r5 = r12.pos
            int r6 = r5 + 1
            r12.pos = r6
            byte[] r6 = r12.encodeTable
            long r8 = r12.bitWorkArea
            r10 = 20
            long r8 = r8 >> r10
            int r8 = (int) r8
            r8 = r8 & 31
            byte r6 = r6[r8]
            r2[r5] = r6
            byte[] r2 = r12.buffer
            int r5 = r12.pos
            int r6 = r5 + 1
            r12.pos = r6
            byte[] r6 = r12.encodeTable
            long r8 = r12.bitWorkArea
            r10 = 15
            long r8 = r8 >> r10
            int r8 = (int) r8
            r8 = r8 & 31
            byte r6 = r6[r8]
            r2[r5] = r6
            byte[] r2 = r12.buffer
            int r5 = r12.pos
            int r6 = r5 + 1
            r12.pos = r6
            byte[] r6 = r12.encodeTable
            long r8 = r12.bitWorkArea
            r10 = 10
            long r8 = r8 >> r10
            int r8 = (int) r8
            r8 = r8 & 31
            byte r6 = r6[r8]
            r2[r5] = r6
            byte[] r2 = r12.buffer
            int r5 = r12.pos
            int r6 = r5 + 1
            r12.pos = r6
            byte[] r6 = r12.encodeTable
            long r8 = r12.bitWorkArea
            long r8 = r8 >> r4
            int r4 = (int) r8
            r4 = r4 & 31
            byte r4 = r6[r4]
            r2[r5] = r4
            byte[] r2 = r12.buffer
            int r4 = r12.pos
            int r5 = r4 + 1
            r12.pos = r5
            byte[] r5 = r12.encodeTable
            long r8 = r12.bitWorkArea
            int r6 = (int) r8
            r6 = r6 & 31
            byte r5 = r5[r6]
            r2[r4] = r5
            int r2 = r12.currentLinePos
            int r2 = r2 + r7
            r12.currentLinePos = r2
            int r2 = r12.lineLength
            if (r2 <= 0) goto L_0x0350
            int r2 = r12.lineLength
            int r4 = r12.currentLinePos
            if (r2 > r4) goto L_0x0350
            byte[] r2 = r12.lineSeparator
            byte[] r4 = r12.buffer
            int r5 = r12.pos
            byte[] r6 = r12.lineSeparator
            int r6 = r6.length
            java.lang.System.arraycopy(r2, r0, r4, r5, r6)
            int r2 = r12.pos
            byte[] r4 = r12.lineSeparator
            int r4 = r4.length
            int r2 = r2 + r4
            r12.pos = r2
            r12.currentLinePos = r0
        L_0x0350:
            int r14 = r14 + 1
            r2 = r3
            goto L_0x025c
        L_0x0355:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.binary.Base32.encode(byte[], int, int):void");
    }

    public boolean isInAlphabet(byte b) {
        return b >= 0 && b < this.decodeTable.length && this.decodeTable[b] != -1;
    }
}
