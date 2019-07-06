package io.ag.crypto;

import java.nio.ByteBuffer;
import java.security.MessageDigestSpi;

/* compiled from: GA */
public final class SHA224 extends MessageDigestSpi {
    private static int[] a = {-1056596264, 914150663, 812702999, -150054599, -4191439, 1750603025, 1694076839, -1090891868};
    private static int[] b = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};
    private ByteBuffer c = ByteBuffer.allocate(64);
    private int d = 0;
    private int[] e = ((int[]) a.clone());

    private void a() {
        int i;
        this.c.rewind();
        int[] iArr = new int[64];
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            iArr[i2] = this.c.getInt();
            i2++;
        }
        this.c.clear();
        for (i = 16; i < 64; i++) {
            int i3 = iArr[i - 15];
            int i4 = (i3 >>> 3) ^ (((i3 >>> 7) | (i3 << 25)) ^ ((i3 >>> 18) | (i3 << 14)));
            int i5 = iArr[i - 2];
            iArr[i] = iArr[i - 16] + i4 + iArr[i - 7] + ((i5 >>> 10) ^ (((i5 >>> 17) | (i5 << 15)) ^ ((i5 >>> 19) | (i5 << 13))));
        }
        int i6 = this.e[0];
        int i7 = this.e[1];
        int i8 = this.e[2];
        int i9 = this.e[3];
        int i10 = this.e[4];
        int i11 = this.e[5];
        int i12 = this.e[6];
        int i13 = i9;
        int i14 = i8;
        int i15 = i7;
        int i16 = i6;
        int i17 = 0;
        int i18 = i11;
        int i19 = i10;
        int i20 = this.e[7];
        int i21 = i12;
        int i22 = i18;
        while (i17 < 64) {
            int i23 = ((((i16 >>> 2) | (i16 << 30)) ^ ((i16 >>> 13) | (i16 << 19))) ^ ((i16 >>> 22) | (i16 << 10))) + (((i16 & i15) ^ (i16 & i14)) ^ (i15 & i14));
            int i24 = i20 + ((((i19 >>> 6) | (i19 << 26)) ^ ((i19 >>> 11) | (i19 << 21))) ^ ((i19 >>> 25) | (i19 << 7))) + ((i19 & i22) ^ ((i19 ^ -1) & i21)) + b[i17] + iArr[i17];
            int i25 = i13 + i24;
            i17++;
            int i26 = i15;
            i15 = i16;
            i16 = i24 + i23;
            i20 = i21;
            i21 = i22;
            i22 = i19;
            i19 = i25;
            i13 = i14;
            i14 = i26;
        }
        int[] iArr2 = this.e;
        iArr2[0] = iArr2[0] + i16;
        int[] iArr3 = this.e;
        iArr3[1] = iArr3[1] + i15;
        int[] iArr4 = this.e;
        iArr4[2] = iArr4[2] + i14;
        int[] iArr5 = this.e;
        iArr5[3] = iArr5[3] + i13;
        int[] iArr6 = this.e;
        iArr6[4] = iArr6[4] + i19;
        int[] iArr7 = this.e;
        iArr7[5] = iArr7[5] + i22;
        int[] iArr8 = this.e;
        iArr8[6] = iArr8[6] + i21;
        int[] iArr9 = this.e;
        iArr9[7] = iArr9[7] + i20;
    }

    /* access modifiers changed from: protected */
    public final byte[] engineDigest() {
        int i;
        long j = ((long) this.d) << 3;
        engineUpdate(Byte.MIN_VALUE);
        while (true) {
            if ((this.d + 8) % 64 == 0) {
                break;
            }
            engineUpdate(0);
        }
        this.c.putLong(j);
        a();
        ByteBuffer allocate = ByteBuffer.allocate(28);
        for (i = 0; i < 7; i++) {
            allocate.putInt(this.e[i]);
        }
        engineReset();
        return allocate.array();
    }

    /* access modifiers changed from: protected */
    public final void engineReset() {
        this.c.clear();
        this.d = 0;
        this.e = (int[]) a.clone();
    }

    /* access modifiers changed from: protected */
    public final void engineUpdate(byte b2) {
        this.c.put(b2);
        this.d++;
        if (this.d % 64 == 0) {
            a();
        }
    }

    /* access modifiers changed from: protected */
    public final void engineUpdate(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            engineUpdate(bArr[i3]);
        }
    }
}
