package com.braintree.org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

public class Base64Encoder implements Encoder {
    protected final byte[] decodingTable = new byte[128];
    protected final byte[] encodingTable = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    protected byte padding = 61;

    private boolean ignore(char c) {
        return c == 10 || c == 13 || c == 9 || c == ' ';
    }

    /* access modifiers changed from: protected */
    public void initialiseDecodingTable() {
        for (int i = 0; i < this.encodingTable.length; i++) {
            this.decodingTable[this.encodingTable[i]] = (byte) i;
        }
    }

    public Base64Encoder() {
        initialiseDecodingTable();
    }

    public int encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3;
        int i4;
        int i5 = i2 % 3;
        int i6 = i2 - i5;
        int i7 = i;
        while (true) {
            i3 = i + i6;
            i4 = 4;
            if (i7 >= i3) {
                break;
            }
            byte b = bArr[i7] & 255;
            byte b2 = bArr[i7 + 1] & 255;
            byte b3 = bArr[i7 + 2] & 255;
            outputStream.write(this.encodingTable[(b >>> 2) & 63]);
            outputStream.write(this.encodingTable[((b << 4) | (b2 >>> 4)) & 63]);
            outputStream.write(this.encodingTable[((b2 << 2) | (b3 >>> 6)) & 63]);
            outputStream.write(this.encodingTable[b3 & 63]);
            i7 += 3;
        }
        switch (i5) {
            case 1:
                byte b4 = bArr[i3] & 255;
                int i8 = (b4 >>> 2) & 63;
                int i9 = (b4 << 4) & 63;
                outputStream.write(this.encodingTable[i8]);
                outputStream.write(this.encodingTable[i9]);
                outputStream.write(this.padding);
                outputStream.write(this.padding);
                break;
            case 2:
                byte b5 = bArr[i3] & 255;
                byte b6 = bArr[i3 + 1] & 255;
                int i10 = (b5 >>> 2) & 63;
                int i11 = ((b5 << 4) | (b6 >>> 4)) & 63;
                int i12 = (b6 << 2) & 63;
                outputStream.write(this.encodingTable[i10]);
                outputStream.write(this.encodingTable[i11]);
                outputStream.write(this.encodingTable[i12]);
                outputStream.write(this.padding);
                break;
        }
        int i13 = (i6 / 3) * 4;
        if (i5 == 0) {
            i4 = 0;
        }
        return i13 + i4;
    }

    public int decode(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        while (length > 0 && ignore(str.charAt(length - 1))) {
            length--;
        }
        int i = length - 4;
        int i2 = 0;
        int nextI = nextI(str, 0, i);
        while (nextI < i) {
            int i3 = nextI + 1;
            byte b = this.decodingTable[str.charAt(nextI)];
            int nextI2 = nextI(str, i3, i);
            int i4 = nextI2 + 1;
            byte b2 = this.decodingTable[str.charAt(nextI2)];
            int nextI3 = nextI(str, i4, i);
            int i5 = nextI3 + 1;
            byte b3 = this.decodingTable[str.charAt(nextI3)];
            int nextI4 = nextI(str, i5, i);
            int i6 = nextI4 + 1;
            byte b4 = this.decodingTable[str.charAt(nextI4)];
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i2 += 3;
            nextI = nextI(str, i6, i);
        }
        return i2 + decodeLastBlock(outputStream, str.charAt(i), str.charAt(length - 3), str.charAt(length - 2), str.charAt(length - 1));
    }

    private int decodeLastBlock(OutputStream outputStream, char c, char c2, char c3, char c4) throws IOException {
        if (c3 == this.padding) {
            outputStream.write((this.decodingTable[c] << 2) | (this.decodingTable[c2] >> 4));
            return 1;
        } else if (c4 == this.padding) {
            byte b = this.decodingTable[c];
            byte b2 = this.decodingTable[c2];
            byte b3 = this.decodingTable[c3];
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            return 2;
        } else {
            byte b4 = this.decodingTable[c];
            byte b5 = this.decodingTable[c2];
            byte b6 = this.decodingTable[c3];
            byte b7 = this.decodingTable[c4];
            outputStream.write((b4 << 2) | (b5 >> 4));
            outputStream.write((b5 << 4) | (b6 >> 2));
            outputStream.write((b6 << 6) | b7);
            return 3;
        }
    }

    private int nextI(String str, int i, int i2) {
        while (i < i2 && ignore(str.charAt(i))) {
            i++;
        }
        return i;
    }
}
