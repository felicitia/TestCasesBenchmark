package com.onfido.android.sdk.capture.ui.camera;

public class Exif {
    private static int a(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        if (z) {
            i += i2 - 1;
            i3 = -1;
        } else {
            i3 = 1;
        }
        byte b = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 <= 0) {
                return b;
            }
            b = (bArr[i] & 255) | (b << 8);
            i += i3;
            i2 = i4;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0065, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0066, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0067, code lost:
        if (r2 <= 8) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0069, code lost:
        r3 = a(r10, r1, 4, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0070, code lost:
        if (r3 == 1229531648) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0075, code lost:
        if (r3 == 1296891946) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0077, code lost:
        android.util.Log.e("CameraExif", "Invalid byte order");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007e, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007f, code lost:
        if (r3 != 1229531648) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0082, code lost:
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0083, code lost:
        r3 = a(r10, r1 + 4, 4, r6) + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008c, code lost:
        if (r3 < 10) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008e, code lost:
        if (r3 <= r2) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0091, code lost:
        r1 = r1 + r3;
        r2 = r2 - r3;
        r3 = a(r10, r1 - 2, 2, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0099, code lost:
        r4 = r3 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009b, code lost:
        if (r3 <= 0) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x009f, code lost:
        if (r2 < 12) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a7, code lost:
        if (a(r10, r1, 2, r6) != 274) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ae, code lost:
        return a(r10, r1 + 8, 2, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00af, code lost:
        r1 = r1 + 12;
        r2 = r2 - 12;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b5, code lost:
        android.util.Log.e("CameraExif", "Invalid offset");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00bc, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00bd, code lost:
        android.util.Log.i("CameraExif", "Orientation not found");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00c4, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int exifOrientationIdentifier(byte[] r10) {
        /*
            r0 = 0
            if (r10 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
        L_0x0005:
            int r2 = r1 + 3
            int r3 = r10.length
            r4 = 4
            r5 = 8
            r6 = 1
            r7 = 2
            if (r2 >= r3) goto L_0x0066
            int r2 = r1 + 1
            byte r1 = r10[r1]
            r3 = 255(0xff, float:3.57E-43)
            r1 = r1 & r3
            if (r1 != r3) goto L_0x0065
            byte r1 = r10[r2]
            r1 = r1 & r3
            if (r1 != r3) goto L_0x001f
        L_0x001d:
            r1 = r2
            goto L_0x0005
        L_0x001f:
            int r2 = r2 + 1
            r3 = 216(0xd8, float:3.03E-43)
            if (r1 == r3) goto L_0x001d
            if (r1 != r6) goto L_0x0028
            goto L_0x001d
        L_0x0028:
            r3 = 217(0xd9, float:3.04E-43)
            if (r1 == r3) goto L_0x0065
            r3 = 218(0xda, float:3.05E-43)
            if (r1 != r3) goto L_0x0031
            goto L_0x0065
        L_0x0031:
            int r3 = a(r10, r2, r7, r0)
            if (r3 < r7) goto L_0x005d
            int r8 = r2 + r3
            int r9 = r10.length
            if (r8 <= r9) goto L_0x003d
            goto L_0x005d
        L_0x003d:
            r9 = 225(0xe1, float:3.15E-43)
            if (r1 != r9) goto L_0x005b
            if (r3 < r5) goto L_0x005b
            int r1 = r2 + 2
            int r1 = a(r10, r1, r4, r0)
            r9 = 1165519206(0x45786966, float:3974.5874)
            if (r1 != r9) goto L_0x005b
            int r1 = r2 + 6
            int r1 = a(r10, r1, r7, r0)
            if (r1 != 0) goto L_0x005b
            int r1 = r2 + 8
            int r2 = r3 + -8
            goto L_0x0067
        L_0x005b:
            r1 = r8
            goto L_0x0005
        L_0x005d:
            java.lang.String r10 = "CameraExif"
            java.lang.String r1 = "Invalid length"
            android.util.Log.e(r10, r1)
            return r0
        L_0x0065:
            r1 = r2
        L_0x0066:
            r2 = 0
        L_0x0067:
            if (r2 <= r5) goto L_0x00bd
            int r3 = a(r10, r1, r4, r0)
            r8 = 1229531648(0x49492a00, float:823968.0)
            if (r3 == r8) goto L_0x007f
            r9 = 1296891946(0x4d4d002a, float:2.14958752E8)
            if (r3 == r9) goto L_0x007f
            java.lang.String r10 = "CameraExif"
            java.lang.String r1 = "Invalid byte order"
            android.util.Log.e(r10, r1)
            return r0
        L_0x007f:
            if (r3 != r8) goto L_0x0082
            goto L_0x0083
        L_0x0082:
            r6 = 0
        L_0x0083:
            int r3 = r1 + 4
            int r3 = a(r10, r3, r4, r6)
            int r3 = r3 + r7
            r4 = 10
            if (r3 < r4) goto L_0x00b5
            if (r3 <= r2) goto L_0x0091
            goto L_0x00b5
        L_0x0091:
            int r1 = r1 + r3
            int r2 = r2 - r3
            int r3 = r1 + -2
            int r3 = a(r10, r3, r7, r6)
        L_0x0099:
            int r4 = r3 + -1
            if (r3 <= 0) goto L_0x00bd
            r3 = 12
            if (r2 < r3) goto L_0x00bd
            int r3 = a(r10, r1, r7, r6)
            r8 = 274(0x112, float:3.84E-43)
            if (r3 != r8) goto L_0x00af
            int r1 = r1 + r5
            int r10 = a(r10, r1, r7, r6)
            return r10
        L_0x00af:
            int r1 = r1 + 12
            int r2 = r2 + -12
            r3 = r4
            goto L_0x0099
        L_0x00b5:
            java.lang.String r10 = "CameraExif"
            java.lang.String r1 = "Invalid offset"
            android.util.Log.e(r10, r1)
            return r0
        L_0x00bd:
            java.lang.String r10 = "CameraExif"
            java.lang.String r1 = "Orientation not found"
            android.util.Log.i(r10, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.Exif.exifOrientationIdentifier(byte[]):int");
    }
}
