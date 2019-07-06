package com.braintree.org.bouncycastle.asn1;

import com.braintree.org.bouncycastle.util.io.Streams;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

class DefiniteLengthInputStream extends LimitedInputStream {
    private static final byte[] EMPTY_BYTES = new byte[0];
    private final int _originalLength;
    private int _remaining;

    DefiniteLengthInputStream(InputStream inputStream, int i) {
        super(inputStream, i);
        if (i < 0) {
            throw new IllegalArgumentException("negative lengths not allowed");
        }
        this._originalLength = i;
        this._remaining = i;
        if (i == 0) {
            setParentEofDetect(true);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getRemaining() {
        return this._remaining;
    }

    public int read() throws IOException {
        if (this._remaining == 0) {
            return -1;
        }
        int read = this._in.read();
        if (read < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("DEF length ");
            sb.append(this._originalLength);
            sb.append(" object truncated by ");
            sb.append(this._remaining);
            throw new EOFException(sb.toString());
        }
        int i = this._remaining - 1;
        this._remaining = i;
        if (i == 0) {
            setParentEofDetect(true);
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this._remaining == 0) {
            return -1;
        }
        int read = this._in.read(bArr, i, Math.min(i2, this._remaining));
        if (read < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("DEF length ");
            sb.append(this._originalLength);
            sb.append(" object truncated by ");
            sb.append(this._remaining);
            throw new EOFException(sb.toString());
        }
        int i3 = this._remaining - read;
        this._remaining = i3;
        if (i3 == 0) {
            setParentEofDetect(true);
        }
        return read;
    }

    /* access modifiers changed from: 0000 */
    public byte[] toByteArray() throws IOException {
        if (this._remaining == 0) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[this._remaining];
        int readFully = this._remaining - Streams.readFully(this._in, bArr);
        this._remaining = readFully;
        if (readFully != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("DEF length ");
            sb.append(this._originalLength);
            sb.append(" object truncated by ");
            sb.append(this._remaining);
            throw new EOFException(sb.toString());
        }
        setParentEofDetect(true);
        return bArr;
    }
}
