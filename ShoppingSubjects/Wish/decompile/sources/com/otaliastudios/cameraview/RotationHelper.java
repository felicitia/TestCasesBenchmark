package com.otaliastudios.cameraview;

class RotationHelper {
    static byte[] rotate(byte[] bArr, int i, int i2, int i3) {
        byte[] bArr2 = bArr;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (i6 == 0) {
            return bArr2;
        }
        if (i6 % 90 != 0 || i6 < 0 || i6 > 270) {
            throw new IllegalArgumentException("0 <= rotation < 360, rotation % 90 == 0");
        }
        byte[] bArr3 = new byte[bArr2.length];
        int i7 = i4 * i5;
        boolean z = i6 % 180 != 0;
        boolean z2 = i6 % 270 != 0;
        boolean z3 = i6 >= 180;
        for (int i8 = 0; i8 < i5; i8++) {
            for (int i9 = 0; i9 < i4; i9++) {
                int i10 = (i8 * i4) + i9;
                int i11 = ((i8 >> 1) * i4) + i7 + (i9 & -2);
                int i12 = i11 + 1;
                int i13 = z ? i5 : i4;
                int i14 = z ? i4 : i5;
                int i15 = z ? i8 : i9;
                int i16 = z ? i9 : i8;
                if (z2) {
                    i15 = (i13 - i15) - 1;
                }
                if (z3) {
                    i16 = (i14 - i16) - 1;
                }
                int i17 = i7 + ((i16 >> 1) * i13) + (i15 & -2);
                int i18 = i17 + 1;
                bArr3[(i16 * i13) + i15] = (byte) (bArr2[i10] & 255);
                bArr3[i17] = (byte) (bArr2[i11] & 255);
                bArr3[i18] = (byte) (bArr2[i12] & 255);
            }
        }
        return bArr3;
    }
}
