package okhttp3.internal.http2;

import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import java.io.ByteArrayOutputStream;
import okio.ByteString;

/* compiled from: Huffman */
class i {
    private static final int[] a = {8184, 8388568, 268435426, 268435427, 268435428, 268435429, 268435430, 268435431, 268435432, 16777194, 1073741820, 268435433, 268435434, 1073741821, 268435435, 268435436, 268435437, 268435438, 268435439, 268435440, 268435441, 268435442, 1073741822, 268435443, 268435444, 268435445, 268435446, 268435447, 268435448, 268435449, 268435450, 268435451, 20, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW, 4090, 8185, 21, 248, 2042, PointerIconCompat.TYPE_ZOOM_IN, PointerIconCompat.TYPE_ZOOM_OUT, 249, 2043, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 22, 23, 24, 0, 1, 2, 25, 26, 27, 28, 29, 30, 31, 92, 251, 32764, 32, 4091, PointerIconCompat.TYPE_GRAB, 8186, 33, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 252, 115, 253, 8187, 524272, 8188, 16380, 34, 32765, 3, 35, 4, 36, 5, 37, 38, 39, 6, 116, 117, 40, 41, 42, 7, 43, 118, 44, 8, 9, 45, 119, 120, 121, 122, 123, 32766, 2044, 16381, 8189, 268435452, 1048550, 4194258, 1048551, 1048552, 4194259, 4194260, 4194261, 8388569, 4194262, 8388570, 8388571, 8388572, 8388573, 8388574, 16777195, 8388575, 16777196, 16777197, 4194263, 8388576, 16777198, 8388577, 8388578, 8388579, 8388580, 2097116, 4194264, 8388581, 4194265, 8388582, 8388583, 16777199, 4194266, 2097117, 1048553, 4194267, 4194268, 8388584, 8388585, 2097118, 8388586, 4194269, 4194270, 16777200, 2097119, 4194271, 8388587, 8388588, 2097120, 2097121, 4194272, 2097122, 8388589, 4194273, 8388590, 8388591, 1048554, 4194274, 4194275, 4194276, 8388592, 4194277, 4194278, 8388593, 67108832, 67108833, 1048555, 524273, 4194279, 8388594, 4194280, 33554412, 67108834, 67108835, 67108836, 134217694, 134217695, 67108837, 16777201, 33554413, 524274, 2097123, 67108838, 134217696, 134217697, 67108839, 134217698, 16777202, 2097124, 2097125, 67108840, 67108841, 268435453, 134217699, 134217700, 134217701, 1048556, 16777203, 1048557, 2097126, 4194281, 2097127, 2097128, 8388595, 4194282, 4194283, 33554414, 33554415, 16777204, 16777205, 67108842, 8388596, 67108843, 134217702, 67108844, 67108845, 134217703, 134217704, 134217705, 134217706, 134217707, 268435454, 134217708, 134217709, 134217710, 134217711, 134217712, 67108846};
    private static final byte[] b = {13, 23, 28, 28, 28, 28, 28, 28, 28, 24, 30, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 28, 6, 10, 10, 12, 13, 6, 8, 11, 10, 10, 8, 11, 8, 6, 6, 6, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 8, 15, 6, 12, 10, 13, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 13, 19, 13, 14, 6, 15, 5, 6, 5, 6, 5, 6, 6, 6, 5, 7, 7, 6, 6, 6, 5, 6, 7, 6, 5, 5, 6, 7, 7, 7, 7, 7, 15, 11, 14, 13, 28, 20, 22, 20, 20, 22, 22, 22, 23, 22, 23, 23, 23, 23, 23, 24, 23, 24, 24, 22, 23, 24, 23, 23, 23, 23, 21, 22, 23, 22, 23, 23, 24, 22, 21, 20, 22, 22, 23, 23, 21, 23, 22, 22, 24, 21, 22, 23, 23, 21, 21, 22, 21, 23, 22, 23, 23, 20, 22, 22, 22, 23, 22, 22, 23, 26, 26, 20, 19, 22, 23, 22, 25, 26, 26, 26, 27, 27, 26, 24, 25, 19, 21, 26, 27, 27, 26, 27, 24, 21, 21, 26, 26, 28, 27, 27, 27, 20, 24, 20, 21, 22, 21, 21, 23, 22, 22, 25, 25, 24, 24, 26, 23, 26, 27, 26, 26, 27, 27, 27, 27, 27, 28, 27, 27, 27, 27, 27, 26};
    private static final i c = new i();
    private final a d = new a();

    /* compiled from: Huffman */
    private static final class a {
        final a[] a;
        final int b;
        final int c;

        a() {
            this.a = new a[256];
            this.b = 0;
            this.c = 0;
        }

        a(int i, int i2) {
            this.a = null;
            this.b = i;
            int i3 = i2 & 7;
            if (i3 == 0) {
                i3 = 8;
            }
            this.c = i3;
        }
    }

    public static i a() {
        return c;
    }

    private i() {
        b();
    }

    /* JADX WARNING: type inference failed for: r3v3, types: [int] */
    /* JADX WARNING: type inference failed for: r3v4, types: [int, long] */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(okio.ByteString r11, okio.d r12) throws java.io.IOException {
        /*
            r10 = this;
            r0 = 0
            r1 = 0
            r3 = r0
        L_0x0004:
            int r4 = r11.size()
            r5 = 8
            r6 = 255(0xff, float:3.57E-43)
            if (r0 >= r4) goto L_0x002f
            byte r4 = r11.getByte(r0)
            r4 = r4 & r6
            int[] r6 = a
            r6 = r6[r4]
            byte[] r7 = b
            byte r4 = r7[r4]
            long r1 = r1 << r4
            long r6 = (long) r6
            long r8 = r1 | r6
            int r3 = r3 + r4
        L_0x0020:
            if (r3 < r5) goto L_0x002b
            int r3 = r3 + -8
            long r1 = r8 >> r3
            int r1 = (int) r1
            r12.k(r1)
            goto L_0x0020
        L_0x002b:
            int r0 = r0 + 1
            r1 = r8
            goto L_0x0004
        L_0x002f:
            if (r3 <= 0) goto L_0x003d
            int r5 = r5 - r3
            long r0 = r1 << r5
            int r11 = r6 >>> r3
            long r2 = (long) r11
            long r4 = r0 | r2
            int r11 = (int) r4
            r12.k(r11)
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.i.a(okio.ByteString, okio.d):void");
    }

    /* access modifiers changed from: 0000 */
    public int a(ByteString byteString) {
        long j = 0;
        int i = 0;
        while (i < byteString.size()) {
            byte b2 = byteString.getByte(i) & 255;
            i++;
            j += (long) b[b2];
        }
        return (int) ((j + 7) >> 3);
    }

    /* access modifiers changed from: 0000 */
    public byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a aVar = this.d;
        byte b2 = 0;
        int i = 0;
        for (byte b3 : bArr) {
            b2 = (b2 << 8) | (b3 & 255);
            i += 8;
            while (i >= 8) {
                aVar = aVar.a[(b2 >>> (i - 8)) & 255];
                if (aVar.a == null) {
                    byteArrayOutputStream.write(aVar.b);
                    i -= aVar.c;
                    aVar = this.d;
                } else {
                    i -= 8;
                }
            }
        }
        while (i > 0) {
            a aVar2 = aVar.a[(b2 << (8 - i)) & 255];
            if (aVar2.a != null || aVar2.c > i) {
                break;
            }
            byteArrayOutputStream.write(aVar2.b);
            i -= aVar2.c;
            aVar = this.d;
        }
        return byteArrayOutputStream.toByteArray();
    }

    private void b() {
        for (int i = 0; i < b.length; i++) {
            a(i, a[i], b[i]);
        }
    }

    private void a(int i, int i2, byte b2) {
        a aVar = new a(i, b2);
        a aVar2 = this.d;
        while (b2 > 8) {
            b2 = (byte) (b2 - 8);
            int i3 = (i2 >>> b2) & 255;
            if (aVar2.a == null) {
                throw new IllegalStateException("invalid dictionary: prefix not unique");
            }
            if (aVar2.a[i3] == null) {
                aVar2.a[i3] = new a();
            }
            aVar2 = aVar2.a[i3];
        }
        int i4 = 8 - b2;
        int i5 = (i2 << i4) & 255;
        int i6 = 1 << i4;
        for (int i7 = i5; i7 < i5 + i6; i7++) {
            aVar2.a[i7] = aVar;
        }
    }
}
