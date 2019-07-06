package com.bumptech.glide.load.resource.bitmap;

import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecyclableBufferedInputStream extends FilterInputStream {
    private volatile byte[] a;
    private int b;
    private int c;
    private int d = -1;
    private int e;

    public static class InvalidMarkException extends RuntimeException {
        private static final long serialVersionUID = -4338378848813561757L;

        public InvalidMarkException(String str) {
            super(str);
        }
    }

    public boolean markSupported() {
        return true;
    }

    public RecyclableBufferedInputStream(InputStream inputStream, byte[] bArr) {
        super(inputStream);
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("buffer is null or empty");
        }
        this.a = bArr;
    }

    public synchronized int available() throws IOException {
        InputStream inputStream;
        inputStream = this.in;
        if (this.a != null) {
            if (inputStream != null) {
            }
        }
        throw b();
        return (this.b - this.e) + inputStream.available();
    }

    private static IOException b() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public synchronized void a() {
        this.c = this.a.length;
    }

    public void close() throws IOException {
        this.a = null;
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private int a(InputStream inputStream, byte[] bArr) throws IOException {
        int i;
        if (this.d == -1 || this.e - this.d >= this.c) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                this.d = -1;
                this.e = 0;
                this.b = read;
            }
            return read;
        }
        if (this.d == 0 && this.c > bArr.length && this.b == bArr.length) {
            int length = bArr.length * 2;
            if (length > this.c) {
                length = this.c;
            }
            if (Log.isLoggable("BufferedIs", 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("allocate buffer of length: ");
                sb.append(length);
                Log.d("BufferedIs", sb.toString());
            }
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.a = bArr2;
            bArr = bArr2;
        } else if (this.d > 0) {
            System.arraycopy(bArr, this.d, bArr, 0, bArr.length - this.d);
        }
        this.e -= this.d;
        this.d = 0;
        this.b = 0;
        int read2 = inputStream.read(bArr, this.e, bArr.length - this.e);
        if (read2 <= 0) {
            i = this.e;
        } else {
            i = this.e + read2;
        }
        this.b = i;
        return read2;
    }

    public synchronized void mark(int i) {
        this.c = Math.max(this.c, i);
        this.d = this.e;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:12:0x0019=Splitter:B:12:0x0019, B:27:0x003b=Splitter:B:27:0x003b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read() throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            byte[] r0 = r5.a     // Catch:{ all -> 0x0040 }
            java.io.InputStream r1 = r5.in     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x003b
            if (r1 != 0) goto L_0x000a
            goto L_0x003b
        L_0x000a:
            int r2 = r5.e     // Catch:{ all -> 0x0040 }
            int r3 = r5.b     // Catch:{ all -> 0x0040 }
            r4 = -1
            if (r2 < r3) goto L_0x0019
            int r1 = r5.a(r1, r0)     // Catch:{ all -> 0x0040 }
            if (r1 != r4) goto L_0x0019
            monitor-exit(r5)
            return r4
        L_0x0019:
            byte[] r1 = r5.a     // Catch:{ all -> 0x0040 }
            if (r0 == r1) goto L_0x0026
            byte[] r0 = r5.a     // Catch:{ all -> 0x0040 }
            if (r0 != 0) goto L_0x0026
            java.io.IOException r0 = b()     // Catch:{ all -> 0x0040 }
            throw r0     // Catch:{ all -> 0x0040 }
        L_0x0026:
            int r1 = r5.b     // Catch:{ all -> 0x0040 }
            int r2 = r5.e     // Catch:{ all -> 0x0040 }
            int r1 = r1 - r2
            if (r1 <= 0) goto L_0x0039
            int r1 = r5.e     // Catch:{ all -> 0x0040 }
            int r2 = r1 + 1
            r5.e = r2     // Catch:{ all -> 0x0040 }
            byte r0 = r0[r1]     // Catch:{ all -> 0x0040 }
            r0 = r0 & 255(0xff, float:3.57E-43)
            monitor-exit(r5)
            return r0
        L_0x0039:
            monitor-exit(r5)
            return r4
        L_0x003b:
            java.io.IOException r0 = b()     // Catch:{ all -> 0x0040 }
            throw r0     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream.read():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0044, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005a, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0067, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(byte[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            byte[] r0 = r5.a     // Catch:{ all -> 0x009d }
            if (r0 != 0) goto L_0x000a
            java.io.IOException r6 = b()     // Catch:{ all -> 0x009d }
            throw r6     // Catch:{ all -> 0x009d }
        L_0x000a:
            if (r8 != 0) goto L_0x000f
            r6 = 0
            monitor-exit(r5)
            return r6
        L_0x000f:
            java.io.InputStream r1 = r5.in     // Catch:{ all -> 0x009d }
            if (r1 != 0) goto L_0x0018
            java.io.IOException r6 = b()     // Catch:{ all -> 0x009d }
            throw r6     // Catch:{ all -> 0x009d }
        L_0x0018:
            int r2 = r5.e     // Catch:{ all -> 0x009d }
            int r3 = r5.b     // Catch:{ all -> 0x009d }
            if (r2 >= r3) goto L_0x0045
            int r2 = r5.b     // Catch:{ all -> 0x009d }
            int r3 = r5.e     // Catch:{ all -> 0x009d }
            int r2 = r2 - r3
            if (r2 < r8) goto L_0x0027
            r2 = r8
            goto L_0x002c
        L_0x0027:
            int r2 = r5.b     // Catch:{ all -> 0x009d }
            int r3 = r5.e     // Catch:{ all -> 0x009d }
            int r2 = r2 - r3
        L_0x002c:
            int r3 = r5.e     // Catch:{ all -> 0x009d }
            java.lang.System.arraycopy(r0, r3, r6, r7, r2)     // Catch:{ all -> 0x009d }
            int r3 = r5.e     // Catch:{ all -> 0x009d }
            int r3 = r3 + r2
            r5.e = r3     // Catch:{ all -> 0x009d }
            if (r2 == r8) goto L_0x0043
            int r3 = r1.available()     // Catch:{ all -> 0x009d }
            if (r3 != 0) goto L_0x003f
            goto L_0x0043
        L_0x003f:
            int r7 = r7 + r2
            int r2 = r8 - r2
            goto L_0x0046
        L_0x0043:
            monitor-exit(r5)
            return r2
        L_0x0045:
            r2 = r8
        L_0x0046:
            int r3 = r5.d     // Catch:{ all -> 0x009d }
            r4 = -1
            if (r3 != r4) goto L_0x005b
            int r3 = r0.length     // Catch:{ all -> 0x009d }
            if (r2 < r3) goto L_0x005b
            int r3 = r1.read(r6, r7, r2)     // Catch:{ all -> 0x009d }
            if (r3 != r4) goto L_0x008d
            if (r2 != r8) goto L_0x0057
            goto L_0x0059
        L_0x0057:
            int r4 = r8 - r2
        L_0x0059:
            monitor-exit(r5)
            return r4
        L_0x005b:
            int r3 = r5.a(r1, r0)     // Catch:{ all -> 0x009d }
            if (r3 != r4) goto L_0x0068
            if (r2 != r8) goto L_0x0064
            goto L_0x0066
        L_0x0064:
            int r4 = r8 - r2
        L_0x0066:
            monitor-exit(r5)
            return r4
        L_0x0068:
            byte[] r3 = r5.a     // Catch:{ all -> 0x009d }
            if (r0 == r3) goto L_0x0075
            byte[] r0 = r5.a     // Catch:{ all -> 0x009d }
            if (r0 != 0) goto L_0x0075
            java.io.IOException r6 = b()     // Catch:{ all -> 0x009d }
            throw r6     // Catch:{ all -> 0x009d }
        L_0x0075:
            int r3 = r5.b     // Catch:{ all -> 0x009d }
            int r4 = r5.e     // Catch:{ all -> 0x009d }
            int r3 = r3 - r4
            if (r3 < r2) goto L_0x007e
            r3 = r2
            goto L_0x0083
        L_0x007e:
            int r3 = r5.b     // Catch:{ all -> 0x009d }
            int r4 = r5.e     // Catch:{ all -> 0x009d }
            int r3 = r3 - r4
        L_0x0083:
            int r4 = r5.e     // Catch:{ all -> 0x009d }
            java.lang.System.arraycopy(r0, r4, r6, r7, r3)     // Catch:{ all -> 0x009d }
            int r4 = r5.e     // Catch:{ all -> 0x009d }
            int r4 = r4 + r3
            r5.e = r4     // Catch:{ all -> 0x009d }
        L_0x008d:
            int r2 = r2 - r3
            if (r2 != 0) goto L_0x0092
            monitor-exit(r5)
            return r8
        L_0x0092:
            int r4 = r1.available()     // Catch:{ all -> 0x009d }
            if (r4 != 0) goto L_0x009b
            int r8 = r8 - r2
            monitor-exit(r5)
            return r8
        L_0x009b:
            int r7 = r7 + r3
            goto L_0x0046
        L_0x009d:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream.read(byte[], int, int):int");
    }

    public synchronized void reset() throws IOException {
        if (this.a == null) {
            throw new IOException("Stream is closed");
        } else if (-1 == this.d) {
            throw new InvalidMarkException("Mark has been invalidated");
        } else {
            this.e = this.d;
        }
    }

    public synchronized long skip(long j) throws IOException {
        byte[] bArr = this.a;
        InputStream inputStream = this.in;
        if (bArr == null) {
            throw b();
        } else if (j < 1) {
            return 0;
        } else {
            if (inputStream == null) {
                throw b();
            } else if (((long) (this.b - this.e)) >= j) {
                this.e = (int) (((long) this.e) + j);
                return j;
            } else {
                long j2 = (long) (this.b - this.e);
                this.e = this.b;
                if (this.d == -1 || j > ((long) this.c)) {
                    return j2 + inputStream.skip(j - j2);
                } else if (a(inputStream, bArr) == -1) {
                    return j2;
                } else {
                    long j3 = j - j2;
                    if (((long) (this.b - this.e)) >= j3) {
                        this.e = (int) (((long) this.e) + j3);
                        return j;
                    }
                    long j4 = (j2 + ((long) this.b)) - ((long) this.e);
                    this.e = this.b;
                    return j4;
                }
            }
        }
    }
}
