package com.bumptech.glide.b;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* compiled from: GifHeaderParser */
public class d {
    private final byte[] a = new byte[256];
    private ByteBuffer b;
    private c c;
    private int d = 0;

    public d a(byte[] bArr) {
        c();
        if (bArr != null) {
            this.b = ByteBuffer.wrap(bArr);
            this.b.rewind();
            this.b.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.b = null;
            this.c.b = 2;
        }
        return this;
    }

    public void a() {
        this.b = null;
        this.c = null;
    }

    private void c() {
        this.b = null;
        Arrays.fill(this.a, 0);
        this.c = new c();
        this.d = 0;
    }

    public c b() {
        if (this.b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        } else if (o()) {
            return this.c;
        } else {
            h();
            if (!o()) {
                d();
                if (this.c.c < 0) {
                    this.c.b = 1;
                }
            }
            return this.c;
        }
    }

    private void d() {
        boolean z = false;
        while (!z && !o()) {
            int m = m();
            if (m == 33) {
                int m2 = m();
                if (m2 == 1) {
                    k();
                } else if (m2 != 249) {
                    switch (m2) {
                        case 254:
                            k();
                            break;
                        case 255:
                            l();
                            String str = "";
                            for (int i = 0; i < 11; i++) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(str);
                                sb.append((char) this.a[i]);
                                str = sb.toString();
                            }
                            if (!str.equals("NETSCAPE2.0")) {
                                k();
                                break;
                            } else {
                                g();
                                break;
                            }
                        default:
                            k();
                            break;
                    }
                } else {
                    this.c.d = new b();
                    e();
                }
            } else if (m == 44) {
                if (this.c.d == null) {
                    this.c.d = new b();
                }
                f();
            } else if (m != 59) {
                this.c.b = 1;
            } else {
                z = true;
            }
        }
    }

    private void e() {
        m();
        int m = m();
        this.c.d.g = (m & 28) >> 2;
        boolean z = true;
        if (this.c.d.g == 0) {
            this.c.d.g = 1;
        }
        b bVar = this.c.d;
        if ((m & 1) == 0) {
            z = false;
        }
        bVar.f = z;
        int n = n();
        if (n < 3) {
            n = 10;
        }
        this.c.d.i = n * 10;
        this.c.d.h = m();
        m();
    }

    private void f() {
        this.c.d.a = n();
        this.c.d.b = n();
        this.c.d.c = n();
        this.c.d.d = n();
        int m = m();
        boolean z = false;
        boolean z2 = (m & 128) != 0;
        int pow = (int) Math.pow(2.0d, (double) ((m & 7) + 1));
        b bVar = this.c.d;
        if ((m & 64) != 0) {
            z = true;
        }
        bVar.e = z;
        if (z2) {
            this.c.d.k = a(pow);
        } else {
            this.c.d.k = null;
        }
        this.c.d.j = this.b.position();
        j();
        if (!o()) {
            this.c.c++;
            this.c.e.add(this.c.d);
        }
    }

    private void g() {
        do {
            l();
            if (this.a[0] == 1) {
                this.c.m = (this.a[1] & 255) | ((this.a[2] & 255) << 8);
            }
            if (this.d <= 0) {
                return;
            }
        } while (!o());
    }

    private void h() {
        String str = "";
        for (int i = 0; i < 6; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append((char) m());
            str = sb.toString();
        }
        if (!str.startsWith("GIF")) {
            this.c.b = 1;
            return;
        }
        i();
        if (this.c.h && !o()) {
            this.c.a = a(this.c.i);
            this.c.l = this.c.a[this.c.j];
        }
    }

    private void i() {
        this.c.f = n();
        this.c.g = n();
        int m = m();
        this.c.h = (m & 128) != 0;
        this.c.i = 2 << (m & 7);
        this.c.j = m();
        this.c.k = m();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int[] a(int r11) {
        /*
            r10 = this;
            r0 = 3
            int r1 = r0 * r11
            byte[] r1 = new byte[r1]
            r2 = 0
            java.nio.ByteBuffer r3 = r10.b     // Catch:{ BufferUnderflowException -> 0x0037 }
            r3.get(r1)     // Catch:{ BufferUnderflowException -> 0x0037 }
            r3 = 256(0x100, float:3.59E-43)
            int[] r3 = new int[r3]     // Catch:{ BufferUnderflowException -> 0x0037 }
            r2 = 0
            r4 = r2
        L_0x0011:
            if (r2 >= r11) goto L_0x004d
            int r5 = r4 + 1
            byte r4 = r1[r4]     // Catch:{ BufferUnderflowException -> 0x0035 }
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r6 = r5 + 1
            byte r5 = r1[r5]     // Catch:{ BufferUnderflowException -> 0x0035 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r7 = r6 + 1
            byte r6 = r1[r6]     // Catch:{ BufferUnderflowException -> 0x0035 }
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r8 = r2 + 1
            r9 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            int r4 = r4 << 16
            r4 = r4 | r9
            int r5 = r5 << 8
            r4 = r4 | r5
            r4 = r4 | r6
            r3[r2] = r4     // Catch:{ BufferUnderflowException -> 0x0035 }
            r4 = r7
            r2 = r8
            goto L_0x0011
        L_0x0035:
            r11 = move-exception
            goto L_0x0039
        L_0x0037:
            r11 = move-exception
            r3 = r2
        L_0x0039:
            java.lang.String r1 = "GifHeaderParser"
            boolean r0 = android.util.Log.isLoggable(r1, r0)
            if (r0 == 0) goto L_0x0048
            java.lang.String r0 = "GifHeaderParser"
            java.lang.String r1 = "Format Error Reading Color Table"
            android.util.Log.d(r0, r1, r11)
        L_0x0048:
            com.bumptech.glide.b.c r11 = r10.c
            r0 = 1
            r11.b = r0
        L_0x004d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.b.d.a(int):int[]");
    }

    private void j() {
        m();
        k();
    }

    private void k() {
        int m;
        do {
            m = m();
            this.b.position(this.b.position() + m);
        } while (m > 0);
    }

    private int l() {
        this.d = m();
        int i = 0;
        if (this.d > 0) {
            int i2 = 0;
            while (i < this.d) {
                try {
                    i2 = this.d - i;
                    this.b.get(this.a, i, i2);
                    i += i2;
                } catch (Exception e) {
                    if (Log.isLoggable("GifHeaderParser", 3)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Error Reading Block n: ");
                        sb.append(i);
                        sb.append(" count: ");
                        sb.append(i2);
                        sb.append(" blockSize: ");
                        sb.append(this.d);
                        Log.d("GifHeaderParser", sb.toString(), e);
                    }
                    this.c.b = 1;
                }
            }
        }
        return i;
    }

    private int m() {
        try {
            return this.b.get() & 255;
        } catch (Exception unused) {
            this.c.b = 1;
            return 0;
        }
    }

    private int n() {
        return this.b.getShort();
    }

    private boolean o() {
        return this.c.b != 0;
    }
}
