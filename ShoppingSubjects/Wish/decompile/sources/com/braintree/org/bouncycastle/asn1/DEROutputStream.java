package com.braintree.org.bouncycastle.asn1;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DEROutputStream extends FilterOutputStream {
    public DEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    private void writeLength(int i) throws IOException {
        if (i > 127) {
            int i2 = i;
            int i3 = 1;
            while (true) {
                i2 >>>= 8;
                if (i2 == 0) {
                    break;
                }
                i3++;
            }
            write((byte) (i3 | 128));
            for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
                write((byte) (i >> i4));
            }
            return;
        }
        write((byte) i);
    }

    /* access modifiers changed from: 0000 */
    public void writeEncoded(int i, byte[] bArr) throws IOException {
        write(i);
        writeLength(bArr.length);
        write(bArr);
    }

    /* access modifiers changed from: 0000 */
    public void writeTag(int i, int i2) throws IOException {
        if (i2 < 31) {
            write(i | i2);
            return;
        }
        write(i | 31);
        if (i2 < 128) {
            write(i2);
            return;
        }
        byte[] bArr = new byte[5];
        int length = bArr.length - 1;
        bArr[length] = (byte) (i2 & 127);
        do {
            i2 >>= 7;
            length--;
            bArr[length] = (byte) ((i2 & 127) | 128);
        } while (i2 > 127);
        write(bArr, length, bArr.length - length);
    }

    /* access modifiers changed from: 0000 */
    public void writeEncoded(int i, int i2, byte[] bArr) throws IOException {
        writeTag(i, i2);
        writeLength(bArr.length);
        write(bArr);
    }

    /* access modifiers changed from: protected */
    public void writeNull() throws IOException {
        write(5);
        write(0);
    }

    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    public void writeObject(Object obj) throws IOException {
        if (obj == null) {
            writeNull();
        } else if (obj instanceof DERObject) {
            ((DERObject) obj).encode(this);
        } else if (obj instanceof DEREncodable) {
            ((DEREncodable) obj).getDERObject().encode(this);
        } else {
            throw new IOException("object not DEREncodable");
        }
    }
}
