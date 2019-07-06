package com.fasterxml.jackson.core.io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.math3.dfp.Dfp;

public class UTF32Reader extends a {
    protected final boolean _bigEndian;
    protected int _byteCount = 0;
    protected int _charCount = 0;
    protected final boolean _managedBuffers;
    protected char _surrogate = 0;

    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    public /* bridge */ /* synthetic */ int read() throws IOException {
        return super.read();
    }

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        super(iOContext, inputStream, bArr, i, i2);
        boolean z2 = false;
        this._bigEndian = z;
        if (inputStream != null) {
            z2 = true;
        }
        this._managedBuffers = z2;
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        byte b;
        if (this._buffer == null) {
            return -1;
        }
        if (i2 < 1) {
            return i2;
        }
        if (i < 0 || i + i2 > cArr.length) {
            reportBounds(cArr, i, i2);
        }
        int i5 = i2 + i;
        if (this._surrogate != 0) {
            i3 = i + 1;
            cArr[i] = this._surrogate;
            this._surrogate = 0;
        } else {
            int i6 = this._length - this._ptr;
            if (i6 < 4 && !loadMore(i6)) {
                return -1;
            }
            i3 = i;
        }
        while (true) {
            if (i3 >= i5) {
                i4 = i3;
                break;
            }
            int i7 = this._ptr;
            if (this._bigEndian) {
                b = (this._buffer[i7 + 3] & 255) | (this._buffer[i7] << 24) | ((this._buffer[i7 + 1] & 255) << 16) | ((this._buffer[i7 + 2] & 255) << 8);
            } else {
                b = (this._buffer[i7 + 3] << 24) | (this._buffer[i7] & 255) | ((this._buffer[i7 + 1] & 255) << 8) | ((this._buffer[i7 + 2] & 255) << 16);
            }
            this._ptr += 4;
            if (b > 65535) {
                if (b > 1114111) {
                    int i8 = i3 - i;
                    StringBuilder sb = new StringBuilder();
                    sb.append("(above ");
                    sb.append(Integer.toHexString(1114111));
                    sb.append(") ");
                    reportInvalid(b, i8, sb.toString());
                }
                int i9 = b - Dfp.FINITE;
                i4 = i3 + 1;
                cArr[i3] = (char) (55296 + (i9 >> 10));
                b = (i9 & 1023) | Dfp.FINITE;
                if (i4 >= i5) {
                    this._surrogate = (char) b;
                    break;
                }
                i3 = i4;
            }
            i4 = i3 + 1;
            cArr[i3] = (char) b;
            if (this._ptr >= this._length) {
                break;
            }
            i3 = i4;
        }
        int i10 = i4 - i;
        this._charCount += i10;
        return i10;
    }

    private void reportUnexpectedEOF(int i, int i2) throws IOException {
        int i3 = this._byteCount + i;
        int i4 = this._charCount;
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected EOF in the middle of a 4-byte UTF-32 char: got ");
        sb.append(i);
        sb.append(", needed ");
        sb.append(i2);
        sb.append(", at char #");
        sb.append(i4);
        sb.append(", byte #");
        sb.append(i3);
        sb.append(")");
        throw new CharConversionException(sb.toString());
    }

    private void reportInvalid(int i, int i2, String str) throws IOException {
        int i3 = (this._byteCount + this._ptr) - 1;
        int i4 = this._charCount + i2;
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid UTF-32 character 0x");
        sb.append(Integer.toHexString(i));
        sb.append(str);
        sb.append(" at char #");
        sb.append(i4);
        sb.append(", byte #");
        sb.append(i3);
        sb.append(")");
        throw new CharConversionException(sb.toString());
    }

    private boolean loadMore(int i) throws IOException {
        this._byteCount += this._length - i;
        if (i > 0) {
            if (this._ptr > 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    this._buffer[i2] = this._buffer[this._ptr + i2];
                }
                this._ptr = 0;
            }
            this._length = i;
        } else {
            this._ptr = 0;
            int read = this._in == null ? -1 : this._in.read(this._buffer);
            if (read < 1) {
                this._length = 0;
                if (read < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    return false;
                }
                reportStrangeStream();
            }
            this._length = read;
        }
        while (this._length < 4) {
            int read2 = this._in == null ? -1 : this._in.read(this._buffer, this._length, this._buffer.length - this._length);
            if (read2 < 1) {
                if (read2 < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length += read2;
        }
        return true;
    }
}
