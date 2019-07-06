package okio;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;

/* compiled from: BufferedSink */
public interface d extends WritableByteChannel, s {
    long a(t tVar) throws IOException;

    d b(String str) throws IOException;

    d b(String str, int i, int i2) throws IOException;

    c c();

    d c(ByteString byteString) throws IOException;

    d c(byte[] bArr) throws IOException;

    d c(byte[] bArr, int i, int i2) throws IOException;

    void flush() throws IOException;

    d h(int i) throws IOException;

    d i(int i) throws IOException;

    d j(int i) throws IOException;

    d k(int i) throws IOException;

    d l(long j) throws IOException;

    d m(long j) throws IOException;

    d w() throws IOException;
}
