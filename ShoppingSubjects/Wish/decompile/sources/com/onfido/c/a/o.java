package com.onfido.c.a;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class o implements Closeable {
    private static final Logger c = Logger.getLogger(o.class.getName());
    private static final byte[] d = new byte[4096];
    final RandomAccessFile a;
    int b;
    private int e;
    private a f;
    private a g;
    private final byte[] h = new byte[16];

    static class a {
        static final a a = new a(0, 0);
        final int b;
        final int c;

        a(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append("[position = ");
            sb.append(this.b);
            sb.append(", length = ");
            sb.append(this.c);
            sb.append("]");
            return sb.toString();
        }
    }

    final class b extends InputStream {
        private int b;
        private int c;

        b(a aVar) {
            this.b = o.this.a(aVar.b + 4);
            this.c = aVar.c;
        }

        public int read() {
            if (this.c == 0) {
                return -1;
            }
            o.this.a.seek((long) this.b);
            int read = o.this.a.read();
            this.b = o.this.a(this.b + 1);
            this.c--;
            return read;
        }

        public int read(byte[] bArr, int i, int i2) {
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (this.c == 0) {
                return -1;
            } else {
                if (i2 > this.c) {
                    i2 = this.c;
                }
                o.this.a(this.b, bArr, i, i2);
                this.b = o.this.a(this.b + i2);
                this.c -= i2;
                return i2;
            }
        }
    }

    public o(File file) {
        if (!file.exists()) {
            a(file);
        }
        this.a = b(file);
        d();
    }

    private static int a(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16) + ((bArr[i + 2] & 255) << 8) + (bArr[i + 3] & 255);
    }

    private void a(int i, int i2) {
        while (i2 > 0) {
            int min = Math.min(i2, d.length);
            b(i, d, 0, min);
            i2 -= min;
            i += min;
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        b(this.h, 0, i);
        b(this.h, 4, i2);
        b(this.h, 8, i3);
        b(this.h, 12, i4);
        this.a.seek(0);
        this.a.write(this.h);
    }

    /* JADX INFO: finally extract failed */
    private static void a(File file) {
        StringBuilder sb = new StringBuilder();
        sb.append(file.getPath());
        sb.append(".tmp");
        File file2 = new File(sb.toString());
        RandomAccessFile b2 = b(file2);
        try {
            b2.setLength(4096);
            b2.seek(0);
            byte[] bArr = new byte[16];
            b(bArr, 0, 4096);
            b2.write(bArr);
            b2.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            b2.close();
            throw th;
        }
    }

    private static RandomAccessFile b(File file) {
        return new RandomAccessFile(file, "rwd");
    }

    private void b(int i, byte[] bArr, int i2, int i3) {
        RandomAccessFile randomAccessFile;
        int a2 = a(i);
        if (a2 + i3 <= this.b) {
            this.a.seek((long) a2);
            randomAccessFile = this.a;
        } else {
            int i4 = this.b - a2;
            this.a.seek((long) a2);
            this.a.write(bArr, i2, i4);
            this.a.seek(16);
            randomAccessFile = this.a;
            i2 += i4;
            i3 -= i4;
        }
        randomAccessFile.write(bArr, i2, i3);
    }

    private static void b(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private a c(int i) {
        if (i == 0) {
            return a.a;
        }
        a(i, this.h, 0, 4);
        return new a(i, a(this.h, 0));
    }

    private void d() {
        this.a.seek(0);
        this.a.readFully(this.h);
        this.b = a(this.h, 0);
        if (((long) this.b) > this.a.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append("File is truncated. Expected length: ");
            sb.append(this.b);
            sb.append(", Actual length: ");
            sb.append(this.a.length());
            throw new IOException(sb.toString());
        } else if (this.b <= 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("File is corrupt; length stored in header (");
            sb2.append(this.b);
            sb2.append(") is invalid.");
            throw new IOException(sb2.toString());
        } else {
            this.e = a(this.h, 4);
            int a2 = a(this.h, 8);
            int a3 = a(this.h, 12);
            this.f = c(a2);
            this.g = c(a3);
        }
    }

    private void d(int i) {
        int i2 = i + 4;
        int f2 = f();
        if (f2 < i2) {
            int i3 = this.b;
            while (true) {
                f2 += i3;
                int i4 = i3 << 1;
                if (i4 < i3) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Cannot grow file beyond ");
                    sb.append(i3);
                    sb.append(" bytes");
                    throw new EOFException(sb.toString());
                } else if (f2 >= i2) {
                    e(i4);
                    int a2 = a(this.g.b + 4 + this.g.c);
                    if (a2 <= this.f.b) {
                        FileChannel channel = this.a.getChannel();
                        channel.position((long) this.b);
                        int i5 = a2 - 16;
                        long j = (long) i5;
                        if (channel.transferTo(16, j, channel) != j) {
                            throw new AssertionError("Copied insufficient number of bytes!");
                        }
                        a(16, i5);
                    }
                    if (this.g.b < this.f.b) {
                        int i6 = (this.b + this.g.b) - 16;
                        a(i4, this.e, this.f.b, i6);
                        this.g = new a(i6, this.g.c);
                    } else {
                        a(i4, this.e, this.f.b, this.g.b);
                    }
                    this.b = i4;
                    return;
                } else {
                    i3 = i4;
                }
            }
        }
    }

    private int e() {
        if (this.e == 0) {
            return 16;
        }
        return this.g.b >= this.f.b ? (this.g.b - this.f.b) + 4 + this.g.c + 16 : (((this.g.b + 4) + this.g.c) + this.b) - this.f.b;
    }

    private void e(int i) {
        this.a.setLength((long) i);
        this.a.getChannel().force(true);
    }

    private int f() {
        return this.b - e();
    }

    /* access modifiers changed from: 0000 */
    public int a(int i) {
        return i < this.b ? i : (i + 16) - this.b;
    }

    public synchronized int a(a aVar) {
        int i = this.f.b;
        for (int i2 = 0; i2 < this.e; i2++) {
            a c2 = c(i);
            if (!aVar.a(new b(c2), c2.c)) {
                return i2 + 1;
            }
            i = a(c2.b + 4 + c2.c);
        }
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i, byte[] bArr, int i2, int i3) {
        RandomAccessFile randomAccessFile;
        int a2 = a(i);
        if (a2 + i3 <= this.b) {
            this.a.seek((long) a2);
            randomAccessFile = this.a;
        } else {
            int i4 = this.b - a2;
            this.a.seek((long) a2);
            this.a.readFully(bArr, i2, i4);
            this.a.seek(16);
            randomAccessFile = this.a;
            i2 += i4;
            i3 -= i4;
        }
        randomAccessFile.readFully(bArr, i2, i3);
    }

    public void a(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    public synchronized void a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            try {
                throw new NullPointerException("data == null");
            } catch (Throwable th) {
                throw th;
            }
        } else {
            if ((i | i2) >= 0) {
                if (i2 <= bArr.length - i) {
                    d(i2);
                    boolean a2 = a();
                    a aVar = new a(a2 ? 16 : a(this.g.b + 4 + this.g.c), i2);
                    b(this.h, 0, i2);
                    b(aVar.b, this.h, 0, 4);
                    b(aVar.b + 4, bArr, i, i2);
                    a(this.b, this.e + 1, a2 ? aVar.b : this.f.b, aVar.b);
                    this.g = aVar;
                    this.e++;
                    if (a2) {
                        this.f = this.g;
                    }
                }
            }
            throw new IndexOutOfBoundsException();
        }
    }

    public synchronized boolean a() {
        return this.e == 0;
    }

    public synchronized int b() {
        return this.e;
    }

    public synchronized void b(int i) {
        if (a()) {
            throw new NoSuchElementException();
        } else if (i < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot remove negative (");
            sb.append(i);
            sb.append(") number of elements.");
            throw new IllegalArgumentException(sb.toString());
        } else if (i != 0) {
            if (i == this.e) {
                c();
            } else if (i > this.e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Cannot remove more elements (");
                sb2.append(i);
                sb2.append(") than present in queue (");
                sb2.append(this.e);
                sb2.append(").");
                throw new IllegalArgumentException(sb2.toString());
            } else {
                int i2 = this.f.b;
                int i3 = this.f.b;
                int i4 = this.f.c;
                int i5 = 0;
                for (int i6 = 0; i6 < i; i6++) {
                    i5 += i4 + 4;
                    i3 = a(i3 + 4 + i4);
                    a(i3, this.h, 0, 4);
                    i4 = a(this.h, 0);
                }
                a(this.b, this.e - i, i3, this.g.b);
                this.e -= i;
                this.f = new a(i3, i4);
                a(i2, i5);
            }
        }
    }

    public synchronized void c() {
        a(4096, 0, 0, 0);
        this.a.seek(16);
        this.a.write(d, 0, 4080);
        this.e = 0;
        this.f = a.a;
        this.g = a.a;
        if (this.b > 4096) {
            e(4096);
        }
        this.b = 4096;
    }

    public synchronized void close() {
        this.a.close();
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('[');
        sb.append("fileLength=");
        sb.append(this.b);
        sb.append(", size=");
        sb.append(this.e);
        sb.append(", first=");
        sb.append(this.f);
        sb.append(", last=");
        sb.append(this.g);
        sb.append(", element lengths=[");
        try {
            a((a) new a() {
                boolean a = true;

                public boolean a(InputStream inputStream, int i) {
                    if (this.a) {
                        this.a = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(i);
                    return true;
                }
            });
        } catch (IOException e2) {
            c.log(Level.WARNING, "read error", e2);
        }
        sb.append("]]");
        return sb.toString();
    }
}
