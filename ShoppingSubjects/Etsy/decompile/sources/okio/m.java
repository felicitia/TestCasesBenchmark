package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: Okio */
public final class m {
    static final Logger a = Logger.getLogger(m.class.getName());

    private m() {
    }

    public static e a(t tVar) {
        return new p(tVar);
    }

    public static d a(s sVar) {
        return new o(sVar);
    }

    private static s a(final OutputStream outputStream, final u uVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (uVar != null) {
            return new s() {
                public void a_(c cVar, long j) throws IOException {
                    v.a(cVar.b, 0, j);
                    while (j > 0) {
                        u.this.g();
                        q qVar = cVar.a;
                        int min = (int) Math.min(j, (long) (qVar.c - qVar.b));
                        outputStream.write(qVar.a, qVar.b, min);
                        qVar.b += min;
                        long j2 = (long) min;
                        long j3 = j - j2;
                        cVar.b -= j2;
                        if (qVar.b == qVar.c) {
                            cVar.a = qVar.b();
                            r.a(qVar);
                        }
                        j = j3;
                    }
                }

                public void flush() throws IOException {
                    outputStream.flush();
                }

                public void close() throws IOException {
                    outputStream.close();
                }

                public u a() {
                    return u.this;
                }

                public String toString() {
                    StringBuilder sb = new StringBuilder();
                    sb.append("sink(");
                    sb.append(outputStream);
                    sb.append(")");
                    return sb.toString();
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static s a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getOutputStream() == null) {
            throw new IOException("socket's output stream == null");
        } else {
            a c = c(socket);
            return c.a(a(socket.getOutputStream(), (u) c));
        }
    }

    public static t a(InputStream inputStream) {
        return a(inputStream, new u());
    }

    private static t a(final InputStream inputStream, final u uVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (uVar != null) {
            return new t() {
                public long a(c cVar, long j) throws IOException {
                    if (j < 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("byteCount < 0: ");
                        sb.append(j);
                        throw new IllegalArgumentException(sb.toString());
                    } else if (j == 0) {
                        return 0;
                    } else {
                        try {
                            u.this.g();
                            q f = cVar.f(1);
                            int read = inputStream.read(f.a, f.c, (int) Math.min(j, (long) (8192 - f.c)));
                            if (read == -1) {
                                return -1;
                            }
                            f.c += read;
                            long j2 = (long) read;
                            cVar.b += j2;
                            return j2;
                        } catch (AssertionError e) {
                            if (m.a(e)) {
                                throw new IOException(e);
                            }
                            throw e;
                        }
                    }
                }

                public void close() throws IOException {
                    inputStream.close();
                }

                public u a() {
                    return u.this;
                }

                public String toString() {
                    StringBuilder sb = new StringBuilder();
                    sb.append("source(");
                    sb.append(inputStream);
                    sb.append(")");
                    return sb.toString();
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static t a(File file) throws FileNotFoundException {
        if (file != null) {
            return a((InputStream) new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static t b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getInputStream() == null) {
            throw new IOException("socket's input stream == null");
        } else {
            a c = c(socket);
            return c.a(a(socket.getInputStream(), (u) c));
        }
    }

    private static a c(final Socket socket) {
        return new a() {
            /* access modifiers changed from: protected */
            public IOException a(IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            /* access modifiers changed from: protected */
            public void a() {
                try {
                    socket.close();
                } catch (Exception e) {
                    Logger logger = m.a;
                    Level level = Level.WARNING;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to close timed out socket ");
                    sb.append(socket);
                    logger.log(level, sb.toString(), e);
                } catch (AssertionError e2) {
                    if (m.a(e2)) {
                        Logger logger2 = m.a;
                        Level level2 = Level.WARNING;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Failed to close timed out socket ");
                        sb2.append(socket);
                        logger2.log(level2, sb2.toString(), e2);
                        return;
                    }
                    throw e2;
                }
            }
        };
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
