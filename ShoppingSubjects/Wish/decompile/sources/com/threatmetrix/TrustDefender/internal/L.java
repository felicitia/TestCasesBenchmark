package com.threatmetrix.TrustDefender.internal;

public final class L {

    /* renamed from: byte reason: not valid java name */
    static final int[] f330byte = new int[256];

    /* renamed from: do reason: not valid java name */
    static final byte[] f331do = new byte[256];

    /* renamed from: for reason: not valid java name */
    static final byte[] f332for = new byte[256];

    /* renamed from: if reason: not valid java name */
    static final int[] f333if = new int[256];

    /* renamed from: int reason: not valid java name */
    static final int[] f334int = new int[256];

    /* renamed from: new reason: not valid java name */
    static final int[] f335new = new int[256];

    /* renamed from: try reason: not valid java name */
    static final int[] f336try = new int[10];

    static {
        byte b;
        byte b2 = 1;
        byte b3 = 1;
        do {
            b2 = (byte) (((b2 & 128) != 0 ? (byte) 27 : 0) ^ ((b2 << 1) ^ b2));
            byte b4 = (byte) (b3 ^ (b3 << 1));
            byte b5 = (byte) (b4 ^ (b4 << 2));
            byte b6 = (byte) (b5 ^ (b5 << 4));
            b3 = (byte) (b6 ^ ((b6 & 128) != 0 ? (byte) 9 : 0));
            b = b2 & 255;
            byte b7 = b3 & 255;
            f331do[b] = (byte) (((((b3 ^ 99) ^ ((b7 << 1) | (b7 >> 7))) ^ ((b7 << 2) | (b7 >> 6))) ^ ((b7 << 3) | (b7 >> 5))) ^ ((b7 >> 4) | (b7 << 4)));
        } while (b != 1);
        f331do[0] = 99;
        for (int i = 0; i < 256; i++) {
            byte b8 = f331do[i] & 255;
            f332for[b8] = (byte) i;
            int i2 = i << 1;
            if (i2 >= 256) {
                i2 ^= 283;
            }
            int i3 = i2 << 1;
            if (i3 >= 256) {
                i3 ^= 283;
            }
            int i4 = i3 << 1;
            if (i4 >= 256) {
                i4 ^= 283;
            }
            int i5 = i4 ^ i;
            int i6 = ((i2 ^ (i3 ^ i4)) << 24) | (i5 << 16) | ((i5 ^ i3) << 8) | (i5 ^ i2);
            f333if[b8] = i6;
            f335new[b8] = (i6 >>> 8) | (i6 << 24);
            f334int[b8] = (i6 >>> 16) | (i6 << 16);
            f330byte[b8] = (i6 << 8) | (i6 >>> 24);
        }
        f336try[0] = 16777216;
        int i7 = 1;
        for (int i8 = 1; i8 < 10; i8++) {
            i7 <<= 1;
            if (i7 >= 256) {
                i7 ^= 283;
            }
            f336try[i8] = i7 << 24;
        }
    }

    /* renamed from: do reason: not valid java name */
    private static int[] m138do(byte[] bArr, int[] iArr, int i) throws IllegalArgumentException {
        if (bArr.length != 16) {
            throw new IllegalArgumentException();
        }
        int i2 = 4;
        int[] iArr2 = new int[((i + 1) * 4)];
        int i3 = i * 4;
        int i4 = i3 + 1;
        iArr2[0] = iArr[i3];
        int i5 = i4 + 1;
        int i6 = iArr[i4];
        int i7 = 1;
        iArr2[1] = i6;
        int i8 = i5 + 1;
        iArr2[2] = iArr[i5];
        iArr2[3] = iArr[i8];
        int i9 = i8 - 7;
        while (i7 < i) {
            int i10 = i9 + 1;
            int i11 = iArr[i9];
            int i12 = i2 + 1;
            iArr2[i2] = f330byte[f331do[i11 & 255] & 255] ^ ((f333if[f331do[i11 >>> 24] & 255] ^ f335new[f331do[(i11 >>> 16) & 255] & 255]) ^ f334int[f331do[(i11 >>> 8) & 255] & 255]);
            int i13 = i10 + 1;
            int i14 = iArr[i10];
            int i15 = i12 + 1;
            iArr2[i12] = f330byte[f331do[i14 & 255] & 255] ^ ((f333if[f331do[i14 >>> 24] & 255] ^ f335new[f331do[(i14 >>> 16) & 255] & 255]) ^ f334int[f331do[(i14 >>> 8) & 255] & 255]);
            int i16 = i13 + 1;
            int i17 = iArr[i13];
            int i18 = i15 + 1;
            iArr2[i15] = f330byte[f331do[i17 & 255] & 255] ^ ((f333if[f331do[i17 >>> 24] & 255] ^ f335new[f331do[(i17 >>> 16) & 255] & 255]) ^ f334int[f331do[(i17 >>> 8) & 255] & 255]);
            int i19 = iArr[i16];
            int i20 = i18 + 1;
            iArr2[i18] = f330byte[f331do[i19 & 255] & 255] ^ ((f333if[f331do[i19 >>> 24] & 255] ^ f335new[f331do[(i19 >>> 16) & 255] & 255]) ^ f334int[f331do[(i19 >>> 8) & 255] & 255]);
            i9 = i16 - 7;
            i7++;
            i2 = i20;
        }
        int i21 = i2 + 1;
        int i22 = i9 + 1;
        iArr2[i2] = iArr[i9];
        int i23 = i21 + 1;
        int i24 = i22 + 1;
        iArr2[i21] = iArr[i22];
        int i25 = i23 + 1;
        int i26 = i24 + 1;
        iArr2[i23] = iArr[i24];
        iArr2[i25] = iArr[i26];
        return iArr2;
    }

    /* renamed from: int reason: not valid java name */
    public static byte[][] m140int(int i) {
        byte[][] bArr = new byte[4][];
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i >>> (i2 << 3);
            bArr[i2] = new byte[]{(byte) (i3 & 3), (byte) ((i3 >> 2) & 3), (byte) ((i3 >> 4) & 3), (byte) ((i3 >> 6) & 3)};
        }
        return bArr;
    }

    /* renamed from: for reason: not valid java name */
    static int[] m139for(byte[] bArr, int i) throws IllegalArgumentException {
        if (bArr.length != 16) {
            throw new IllegalArgumentException();
        }
        int i2 = (i + 1) * 4;
        int[] iArr = new int[i2];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 4) {
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            byte b = (bArr[i4] << 24) | ((bArr[i5] & 255) << 16);
            int i7 = i6 + 1;
            byte b2 = b | ((bArr[i6] & 255) << 8);
            int i8 = i7 + 1;
            iArr[i3] = b2 | (bArr[i7] & 255);
            i3++;
            i4 = i8;
        }
        int i9 = 4;
        int i10 = 0;
        int i11 = 0;
        while (i9 < i2) {
            int i12 = iArr[i9 - 1];
            if (i10 == 0) {
                i12 = ((((f331do[(i12 >>> 16) & 255] << 24) | ((f331do[(i12 >>> 8) & 255] & 255) << 16)) | ((f331do[i12 & 255] & 255) << 8)) | (f331do[i12 >>> 24] & 255)) ^ f336try[i11];
                i11++;
                i10 = 4;
            }
            iArr[i9] = i12 ^ iArr[i9 - 4];
            i9++;
            i10--;
        }
        return m138do(bArr, iArr, i);
    }
}
