package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* compiled from: Sink */
public interface s extends Closeable, Flushable {
    u a();

    void a_(c cVar, long j) throws IOException;

    void close() throws IOException;

    void flush() throws IOException;
}
