package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;

final class uj extends vk {
    private static final byte[] c = new byte[16];

    uj(byte[] bArr, int i) throws InvalidKeyException {
        super(bArr, i);
    }

    private static void a(int[] iArr, int i, int i2, int i3, int i4) {
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = a(iArr[i4] ^ iArr[i], 16);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = a(iArr[i2] ^ iArr[i3], 12);
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = a(iArr[i] ^ iArr[i4], 8);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = a(iArr[i2] ^ iArr[i3], 7);
    }

    /* access modifiers changed from: 0000 */
    public final int a() {
        return 12;
    }

    /* access modifiers changed from: 0000 */
    public final ByteBuffer a(byte[] bArr, int i) {
        int i2 = 16;
        int[] iArr = new int[16];
        int i3 = 13;
        int i4 = 4;
        System.arraycopy(vk.a, 0, iArr, 0, a.length);
        int[] a = a(ByteBuffer.wrap(this.b.a()));
        System.arraycopy(a, 0, iArr, 4, a.length);
        iArr[12] = i;
        System.arraycopy(a(ByteBuffer.wrap(bArr)), 0, iArr, 13, 3);
        int[] iArr2 = (int[]) iArr.clone();
        int i5 = 0;
        while (i5 < 10) {
            a(iArr2, 0, i4, 8, 12);
            a(iArr2, 1, 5, 9, i3);
            a(iArr2, 2, 6, 10, 14);
            a(iArr2, 3, 7, 11, 15);
            a(iArr2, 0, 5, 10, 15);
            a(iArr2, 1, 6, 11, 12);
            a(iArr2, 2, 7, 8, 13);
            a(iArr2, 3, 4, 9, 14);
            i5++;
            i4 = 4;
            i3 = 13;
            i2 = 16;
        }
        int i6 = 0;
        for (int i7 = i2; i6 < i7; i7 = 16) {
            iArr[i6] = iArr[i6] + iArr2[i6];
            i6++;
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(iArr, 0, 16);
        return order;
    }
}
