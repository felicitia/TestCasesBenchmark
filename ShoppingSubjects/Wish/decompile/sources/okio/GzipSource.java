package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class GzipSource implements Source {
    private final CRC32 crc = new CRC32();
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private int section = 0;
    private final BufferedSource source;

    public GzipSource(Source source2) {
        if (source2 == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.inflater = new Inflater(true);
        this.source = Okio.buffer(source2);
        this.inflaterSource = new InflaterSource(this.source, this.inflater);
    }

    public long read(Buffer buffer, long j) throws IOException {
        if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (j == 0) {
            return 0;
        } else {
            if (this.section == 0) {
                consumeHeader();
                this.section = 1;
            }
            if (this.section == 1) {
                long j2 = buffer.size;
                long read = this.inflaterSource.read(buffer, j);
                if (read != -1) {
                    updateCrc(buffer, j2, read);
                    return read;
                }
                this.section = 2;
            }
            if (this.section == 2) {
                consumeTrailer();
                this.section = 3;
                if (!this.source.exhausted()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    private void consumeHeader() throws IOException {
        this.source.require(10);
        byte b = this.source.buffer().getByte(3);
        boolean z = ((b >> 1) & 1) == 1;
        if (z) {
            updateCrc(this.source.buffer(), 0, 10);
        }
        checkEqual("ID1ID2", 8075, this.source.readShort());
        this.source.skip(8);
        if (((b >> 2) & 1) == 1) {
            this.source.require(2);
            if (z) {
                updateCrc(this.source.buffer(), 0, 2);
            }
            long readShortLe = (long) this.source.buffer().readShortLe();
            this.source.require(readShortLe);
            if (z) {
                updateCrc(this.source.buffer(), 0, readShortLe);
            }
            this.source.skip(readShortLe);
        }
        if (((b >> 3) & 1) == 1) {
            long indexOf = this.source.indexOf(0);
            if (indexOf == -1) {
                throw new EOFException();
            }
            if (z) {
                updateCrc(this.source.buffer(), 0, indexOf + 1);
            }
            this.source.skip(indexOf + 1);
        }
        if (((b >> 4) & 1) == 1) {
            long indexOf2 = this.source.indexOf(0);
            if (indexOf2 == -1) {
                throw new EOFException();
            }
            if (z) {
                updateCrc(this.source.buffer(), 0, indexOf2 + 1);
            }
            this.source.skip(indexOf2 + 1);
        }
        if (z) {
            checkEqual("FHCRC", this.source.readShortLe(), (short) ((int) this.crc.getValue()));
            this.crc.reset();
        }
    }

    private void consumeTrailer() throws IOException {
        checkEqual("CRC", this.source.readIntLe(), (int) this.crc.getValue());
        checkEqual("ISIZE", this.source.readIntLe(), (int) this.inflater.getBytesWritten());
    }

    public Timeout timeout() {
        return this.source.timeout();
    }

    public void close() throws IOException {
        this.inflaterSource.close();
    }

    private void updateCrc(Buffer buffer, long j, long j2) {
        Segment segment = buffer.head;
        while (j >= ((long) (segment.limit - segment.pos))) {
            long j3 = j - ((long) (segment.limit - segment.pos));
            segment = segment.next;
            j = j3;
        }
        while (j2 > 0) {
            int i = (int) (((long) segment.pos) + j);
            int min = (int) Math.min((long) (segment.limit - i), j2);
            this.crc.update(segment.data, i, min);
            long j4 = j2 - ((long) min);
            segment = segment.next;
            j = 0;
            j2 = j4;
        }
    }

    private void checkEqual(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }
}