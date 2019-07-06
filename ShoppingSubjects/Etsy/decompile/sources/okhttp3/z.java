package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.internal.c;
import okio.ByteString;
import okio.d;
import okio.m;
import okio.t;

/* compiled from: RequestBody */
public abstract class z {
    public abstract u a();

    public abstract void a(d dVar) throws IOException;

    public long b() throws IOException {
        return -1;
    }

    public static z a(u uVar, String str) {
        Charset charset = c.e;
        if (uVar != null) {
            charset = uVar.b();
            if (charset == null) {
                charset = c.e;
                StringBuilder sb = new StringBuilder();
                sb.append(uVar);
                sb.append("; charset=utf-8");
                uVar = u.a(sb.toString());
            }
        }
        return a(uVar, str.getBytes(charset));
    }

    public static z a(final u uVar, final ByteString byteString) {
        return new z() {
            public u a() {
                return u.this;
            }

            public long b() throws IOException {
                return (long) byteString.size();
            }

            public void a(d dVar) throws IOException {
                dVar.c(byteString);
            }
        };
    }

    public static z a(u uVar, byte[] bArr) {
        return a(uVar, bArr, 0, bArr.length);
    }

    public static z a(final u uVar, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        c.a((long) bArr.length, (long) i, (long) i2);
        return new z() {
            public u a() {
                return u.this;
            }

            public long b() {
                return (long) i2;
            }

            public void a(d dVar) throws IOException {
                dVar.c(bArr, i, i2);
            }
        };
    }

    public static z a(final u uVar, final File file) {
        if (file != null) {
            return new z() {
                public u a() {
                    return u.this;
                }

                public long b() {
                    return file.length();
                }

                public void a(d dVar) throws IOException {
                    t tVar = null;
                    try {
                        t a2 = m.a(file);
                        try {
                            dVar.a(a2);
                            c.a((Closeable) a2);
                        } catch (Throwable th) {
                            th = th;
                            tVar = a2;
                            c.a((Closeable) tVar);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        c.a((Closeable) tVar);
                        throw th;
                    }
                }
            };
        }
        throw new NullPointerException("content == null");
    }
}
