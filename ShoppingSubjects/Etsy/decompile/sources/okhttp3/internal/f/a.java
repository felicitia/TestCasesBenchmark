package okhttp3.internal.f;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.c;
import okhttp3.internal.e.f;
import okio.e;
import okio.k;
import okio.m;
import okio.t;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: PublicSuffixDatabase */
public final class a {
    private static final byte[] a = {42};
    private static final String[] b = new String[0];
    private static final String[] c = {"*"};
    private static final a d = new a();
    private final AtomicBoolean e = new AtomicBoolean(false);
    private final CountDownLatch f = new CountDownLatch(1);
    private byte[] g;
    private byte[] h;

    public static a a() {
        return d;
    }

    public String a(String str) {
        int i;
        if (str == null) {
            throw new NullPointerException("domain == null");
        }
        String[] split = IDN.toUnicode(str).split("\\.");
        String[] a2 = a(split);
        if (split.length == a2.length && a2[0].charAt(0) != '!') {
            return null;
        }
        if (a2[0].charAt(0) == '!') {
            i = split.length - a2.length;
        } else {
            i = split.length - (a2.length + 1);
        }
        StringBuilder sb = new StringBuilder();
        String[] split2 = str.split("\\.");
        while (i < split2.length) {
            sb.append(split2[i]);
            sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            i++;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String[] a(java.lang.String[] r8) {
        /*
            r7 = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.e
            boolean r0 = r0.get()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0016
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.e
            boolean r0 = r0.compareAndSet(r1, r2)
            if (r0 == 0) goto L_0x0016
            r7.b()
            goto L_0x001b
        L_0x0016:
            java.util.concurrent.CountDownLatch r0 = r7.f     // Catch:{ InterruptedException -> 0x001b }
            r0.await()     // Catch:{ InterruptedException -> 0x001b }
        L_0x001b:
            monitor-enter(r7)
            byte[] r0 = r7.g     // Catch:{ all -> 0x00bf }
            if (r0 != 0) goto L_0x0028
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "Unable to load publicsuffixes.gz resource from the classpath."
            r8.<init>(r0)     // Catch:{ all -> 0x00bf }
            throw r8     // Catch:{ all -> 0x00bf }
        L_0x0028:
            monitor-exit(r7)     // Catch:{ all -> 0x00bf }
            int r0 = r8.length
            byte[][] r0 = new byte[r0][]
            r3 = r1
        L_0x002d:
            int r4 = r8.length
            if (r3 >= r4) goto L_0x003d
            r4 = r8[r3]
            java.nio.charset.Charset r5 = okhttp3.internal.c.e
            byte[] r4 = r4.getBytes(r5)
            r0[r3] = r4
            int r3 = r3 + 1
            goto L_0x002d
        L_0x003d:
            r8 = r1
        L_0x003e:
            r3 = 0
            int r4 = r0.length
            if (r8 >= r4) goto L_0x004e
            byte[] r4 = r7.g
            java.lang.String r4 = a(r4, r0, r8)
            if (r4 == 0) goto L_0x004b
            goto L_0x004f
        L_0x004b:
            int r8 = r8 + 1
            goto L_0x003e
        L_0x004e:
            r4 = r3
        L_0x004f:
            int r8 = r0.length
            if (r8 <= r2) goto L_0x006d
            java.lang.Object r8 = r0.clone()
            byte[][] r8 = (byte[][]) r8
            r5 = r1
        L_0x0059:
            int r6 = r8.length
            int r6 = r6 - r2
            if (r5 >= r6) goto L_0x006d
            byte[] r6 = a
            r8[r5] = r6
            byte[] r6 = r7.g
            java.lang.String r6 = a(r6, r8, r5)
            if (r6 == 0) goto L_0x006a
            goto L_0x006e
        L_0x006a:
            int r5 = r5 + 1
            goto L_0x0059
        L_0x006d:
            r6 = r3
        L_0x006e:
            if (r6 == 0) goto L_0x0080
        L_0x0070:
            int r8 = r0.length
            int r8 = r8 - r2
            if (r1 >= r8) goto L_0x0080
            byte[] r8 = r7.h
            java.lang.String r8 = a(r8, r0, r1)
            if (r8 == 0) goto L_0x007d
            goto L_0x0081
        L_0x007d:
            int r1 = r1 + 1
            goto L_0x0070
        L_0x0080:
            r8 = r3
        L_0x0081:
            if (r8 == 0) goto L_0x009b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "!"
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "\\."
            java.lang.String[] r8 = r8.split(r0)
            return r8
        L_0x009b:
            if (r4 != 0) goto L_0x00a2
            if (r6 != 0) goto L_0x00a2
            java.lang.String[] r8 = c
            return r8
        L_0x00a2:
            if (r4 == 0) goto L_0x00ab
            java.lang.String r8 = "\\."
            java.lang.String[] r8 = r4.split(r8)
            goto L_0x00ad
        L_0x00ab:
            java.lang.String[] r8 = b
        L_0x00ad:
            if (r6 == 0) goto L_0x00b6
            java.lang.String r0 = "\\."
            java.lang.String[] r0 = r6.split(r0)
            goto L_0x00b8
        L_0x00b6:
            java.lang.String[] r0 = b
        L_0x00b8:
            int r1 = r8.length
            int r2 = r0.length
            if (r1 <= r2) goto L_0x00bd
            goto L_0x00be
        L_0x00bd:
            r8 = r0
        L_0x00be:
            return r8
        L_0x00bf:
            r8 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x00bf }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.f.a.a(java.lang.String[]):java.lang.String[]");
    }

    private static String a(byte[] bArr, byte[][] bArr2, int i) {
        int i2;
        byte b2;
        int i3;
        byte[] bArr3 = bArr;
        byte[][] bArr4 = bArr2;
        int length = bArr3.length;
        int i4 = 0;
        while (i4 < length) {
            int i5 = (i4 + length) / 2;
            while (i5 > -1 && bArr3[i5] != 10) {
                i5--;
            }
            int i6 = i5 + 1;
            int i7 = 1;
            while (true) {
                i2 = i6 + i7;
                if (bArr3[i2] == 10) {
                    break;
                }
                i7++;
            }
            int i8 = i2 - i6;
            int i9 = i;
            boolean z = false;
            int i10 = 0;
            int i11 = 0;
            while (true) {
                if (z) {
                    b2 = 46;
                    z = false;
                } else {
                    b2 = bArr4[i9][i10] & 255;
                }
                i3 = b2 - (bArr3[i6 + i11] & 255);
                if (i3 == 0) {
                    i11++;
                    i10++;
                    if (i11 == i8) {
                        break;
                    } else if (bArr4[i9].length == i10) {
                        if (i9 == bArr4.length - 1) {
                            break;
                        }
                        i9++;
                        i10 = -1;
                        z = true;
                    }
                } else {
                    break;
                }
            }
            if (i3 >= 0) {
                if (i3 <= 0) {
                    int i12 = i8 - i11;
                    int length2 = bArr4[i9].length - i10;
                    while (true) {
                        i9++;
                        if (i9 >= bArr4.length) {
                            break;
                        }
                        length2 += bArr4[i9].length;
                    }
                    if (length2 >= i12) {
                        if (length2 <= i12) {
                            return new String(bArr3, i6, i8, c.e);
                        }
                    }
                }
                i4 = i2 + 1;
            }
            length = i6 - 1;
        }
        return null;
    }

    private void b() {
        boolean z = false;
        while (true) {
            try {
                c();
                break;
            } catch (InterruptedIOException unused) {
                z = true;
            } catch (IOException e2) {
                f.c().a(5, "Failed to read public suffix list", (Throwable) e2);
                if (z) {
                    Thread.currentThread().interrupt();
                }
                return;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    private void c() throws IOException {
        InputStream resourceAsStream = a.class.getResourceAsStream("publicsuffixes.gz");
        if (resourceAsStream != null) {
            e a2 = m.a((t) new k(m.a(resourceAsStream)));
            try {
                byte[] bArr = new byte[a2.k()];
                a2.a(bArr);
                byte[] bArr2 = new byte[a2.k()];
                a2.a(bArr2);
                synchronized (this) {
                    this.g = bArr;
                    this.h = bArr2;
                }
                this.f.countDown();
            } finally {
                c.a((Closeable) a2);
            }
        }
    }
}
