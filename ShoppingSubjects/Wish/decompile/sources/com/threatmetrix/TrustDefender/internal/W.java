package com.threatmetrix.TrustDefender.internal;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class W extends FilterInputStream {

    /* renamed from: do reason: not valid java name */
    private static final int[] f569do = L.f335new;

    /* renamed from: for reason: not valid java name */
    private static final int[] f570for = L.f333if;

    /* renamed from: if reason: not valid java name */
    private static final byte[] f571if = L.f332for;

    /* renamed from: int reason: not valid java name */
    private static final int[] f572int = L.f334int;

    /* renamed from: new reason: not valid java name */
    private static final int[] f573new = L.f330byte;

    /* renamed from: break reason: not valid java name */
    private int f574break = Integer.MAX_VALUE;

    /* renamed from: byte reason: not valid java name */
    private final int[] f575byte = new int[4];

    /* renamed from: case reason: not valid java name */
    private final byte[][] f576case;

    /* renamed from: char reason: not valid java name */
    private final int f577char;

    /* renamed from: else reason: not valid java name */
    private final byte[] f578else = new byte[16];

    /* renamed from: goto reason: not valid java name */
    private final byte[] f579goto = new byte[16];

    /* renamed from: long reason: not valid java name */
    private int f580long = 16;

    /* renamed from: try reason: not valid java name */
    private final int[] f581try;

    /* renamed from: void reason: not valid java name */
    private int f582void = 16;

    public final boolean markSupported() {
        return false;
    }

    public W(InputStream inputStream, int i, byte[] bArr, byte[][] bArr2) {
        super(inputStream);
        this.f577char = i;
        this.f581try = L.m139for(bArr, i);
        this.f576case = m365if(bArr2);
    }

    public final int read() throws IOException {
        m364for();
        if (this.f582void >= this.f580long) {
            return -1;
        }
        byte[] bArr = this.f579goto;
        int i = this.f582void;
        this.f582void = i + 1;
        return bArr[i] & 255;
    }

    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i + i2;
        int i4 = i;
        while (i4 < i3) {
            m364for();
            if (this.f582void < this.f580long) {
                int i5 = i4 + 1;
                byte[] bArr2 = this.f579goto;
                int i6 = this.f582void;
                this.f582void = i6 + 1;
                bArr[i4] = bArr2[i6];
                i4 = i5;
            } else if (i4 == i) {
                return -1;
            } else {
                return i2 - (i3 - i4);
            }
        }
        return i2;
    }

    public final long skip(long j) throws IOException {
        long j2 = 0;
        while (j2 < j && read() != -1) {
            j2++;
        }
        return j2;
    }

    public final int available() throws IOException {
        return this.f580long - this.f582void;
    }

    public final void close() throws IOException {
        super.close();
    }

    public final synchronized void mark(int i) {
    }

    public final synchronized void reset() throws IOException {
    }

    /* renamed from: if reason: not valid java name */
    private static byte[][] m365if(byte[][] bArr) {
        byte[][] bArr2 = new byte[bArr.length][];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = new byte[bArr[i].length];
            for (int i2 = 0; i2 < bArr[i].length; i2++) {
                bArr2[i][bArr[i][i2]] = (byte) i2;
            }
        }
        return bArr2;
    }

    /* renamed from: for reason: not valid java name */
    private int m364for() throws IOException {
        if (this.f574break == Integer.MAX_VALUE) {
            this.f574break = this.in.read();
        }
        int i = 16;
        if (this.f582void == 16) {
            this.f578else[0] = (byte) this.f574break;
            int i2 = 1;
            do {
                i2 += this.in.read(this.f578else, i2, 16 - i2);
            } while (i2 < 16);
            m366int(this.f578else, this.f579goto);
            this.f574break = this.in.read();
            this.f582void = 0;
            if (this.f574break < 0) {
                i = 16 - (this.f579goto[15] & 255);
            }
            this.f580long = i;
        }
        return this.f580long;
    }

    /* renamed from: int reason: not valid java name */
    private void m366int(byte[] bArr, byte[] bArr2) {
        this.f575byte[0] = ((((bArr[0] << 24) | ((bArr[1] & 255) << 16)) | ((bArr[2] & 255) << 8)) | (bArr[3] & 255)) ^ this.f581try[0];
        this.f575byte[1] = ((((bArr[4] << 24) | ((bArr[5] & 255) << 16)) | ((bArr[6] & 255) << 8)) | (bArr[7] & 255)) ^ this.f581try[1];
        this.f575byte[2] = ((((bArr[8] << 24) | ((bArr[9] & 255) << 16)) | ((bArr[10] & 255) << 8)) | (bArr[11] & 255)) ^ this.f581try[2];
        this.f575byte[3] = ((bArr[15] & 255) | (((bArr[12] << 24) | ((bArr[13] & 255) << 16)) | ((bArr[14] & 255) << 8))) ^ this.f581try[3];
        int i = 1;
        int i2 = 4;
        while (i < this.f577char) {
            int i3 = (((f570for[this.f575byte[this.f576case[0][0]] >>> 24] ^ f569do[(this.f575byte[this.f576case[1][0]] >>> 16) & 255]) ^ f572int[(this.f575byte[this.f576case[2][0]] >>> 8) & 255]) ^ f573new[this.f575byte[this.f576case[3][0]] & 255]) ^ this.f581try[i2];
            int i4 = (((f569do[(this.f575byte[this.f576case[1][1]] >>> 16) & 255] ^ f570for[this.f575byte[this.f576case[0][1]] >>> 24]) ^ f572int[(this.f575byte[this.f576case[2][1]] >>> 8) & 255]) ^ f573new[this.f575byte[this.f576case[3][1]] & 255]) ^ this.f581try[i2 + 1];
            int i5 = (((f569do[(this.f575byte[this.f576case[1][2]] >>> 16) & 255] ^ f570for[this.f575byte[this.f576case[0][2]] >>> 24]) ^ f572int[(this.f575byte[this.f576case[2][2]] >>> 8) & 255]) ^ f573new[this.f575byte[this.f576case[3][2]] & 255]) ^ this.f581try[i2 + 2];
            int i6 = (((f569do[(this.f575byte[this.f576case[1][3]] >>> 16) & 255] ^ f570for[this.f575byte[this.f576case[0][3]] >>> 24]) ^ f572int[(this.f575byte[this.f576case[2][3]] >>> 8) & 255]) ^ f573new[this.f575byte[this.f576case[3][3]] & 255]) ^ this.f581try[i2 + 3];
            this.f575byte[0] = i3;
            this.f575byte[1] = i4;
            this.f575byte[2] = i5;
            this.f575byte[3] = i6;
            i++;
            i2 += 4;
        }
        int i7 = this.f581try[i2];
        bArr2[0] = (byte) (f571if[this.f575byte[this.f576case[0][0]] >>> 24] ^ (i7 >>> 24));
        bArr2[1] = (byte) (f571if[(this.f575byte[this.f576case[1][0]] >>> 16) & 255] ^ (i7 >>> 16));
        bArr2[2] = (byte) (f571if[(this.f575byte[this.f576case[2][0]] >>> 8) & 255] ^ (i7 >>> 8));
        bArr2[3] = (byte) (i7 ^ f571if[this.f575byte[this.f576case[3][0]] & 255]);
        int i8 = this.f581try[i2 + 1];
        bArr2[4] = (byte) (f571if[this.f575byte[this.f576case[0][1]] >>> 24] ^ (i8 >>> 24));
        bArr2[5] = (byte) (f571if[(this.f575byte[this.f576case[1][1]] >>> 16) & 255] ^ (i8 >>> 16));
        bArr2[6] = (byte) (f571if[(this.f575byte[this.f576case[2][1]] >>> 8) & 255] ^ (i8 >>> 8));
        bArr2[7] = (byte) (i8 ^ f571if[this.f575byte[this.f576case[3][1]] & 255]);
        int i9 = this.f581try[i2 + 2];
        bArr2[8] = (byte) (f571if[this.f575byte[this.f576case[0][2]] >>> 24] ^ (i9 >>> 24));
        bArr2[9] = (byte) (f571if[(this.f575byte[this.f576case[1][2]] >>> 16) & 255] ^ (i9 >>> 16));
        bArr2[10] = (byte) (f571if[(this.f575byte[this.f576case[2][2]] >>> 8) & 255] ^ (i9 >>> 8));
        bArr2[11] = (byte) (i9 ^ f571if[this.f575byte[this.f576case[3][2]] & 255]);
        int i10 = this.f581try[i2 + 3];
        bArr2[12] = (byte) (f571if[this.f575byte[this.f576case[0][3]] >>> 24] ^ (i10 >>> 24));
        bArr2[13] = (byte) (f571if[(this.f575byte[this.f576case[1][3]] >>> 16) & 255] ^ (i10 >>> 16));
        bArr2[14] = (byte) (f571if[(this.f575byte[this.f576case[2][3]] >>> 8) & 255] ^ (i10 >>> 8));
        bArr2[15] = (byte) (i10 ^ f571if[this.f575byte[this.f576case[3][3]] & 255]);
    }
}
