package com.threatmetrix.TrustDefender.internal;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public final class E extends FilterInputStream {

    /* renamed from: byte reason: not valid java name */
    private int f146byte = this.f151int;

    /* renamed from: do reason: not valid java name */
    private int[] f147do = new int[this.f151int];

    /* renamed from: else reason: not valid java name */
    private int f148else;

    /* renamed from: for reason: not valid java name */
    private long[] f149for = new long[4];

    /* renamed from: if reason: not valid java name */
    private short f150if;

    /* renamed from: int reason: not valid java name */
    private final int f151int;

    /* renamed from: new reason: not valid java name */
    private long[] f152new = new long[4];

    public final boolean markSupported() {
        return false;
    }

    public E(InputStream inputStream, int i, int i2, short s, int i3, int i4) throws IOException {
        super(inputStream);
        this.f151int = Math.min(Math.max(s, 4), 8);
        this.f149for = O.m230int(i ^ i4, this.f151int ^ i4);
        this.f152new = O.m230int(i2 ^ i4, i3 ^ i4);
        this.f148else = this.in.read();
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int read = read();
        if (read == -1) {
            return -1;
        }
        bArr[i] = (byte) read;
        int i3 = 1;
        while (i3 < i2) {
            int read2 = read();
            if (read2 == -1) {
                return i3;
            }
            bArr[i + i3] = (byte) read2;
            i3++;
        }
        return i3;
    }

    public final long skip(long j) throws IOException {
        long j2 = 0;
        while (j2 < j && read() != -1) {
            j2++;
        }
        return j2;
    }

    public final int available() throws IOException {
        return this.f151int - this.f146byte;
    }

    public final int read() throws IOException {
        if (this.f146byte == this.f151int) {
            if (this.f148else == -1) {
                Arrays.fill(this.f147do, -1);
            } else {
                this.f147do[0] = this.f148else;
                for (int i = 1; i < this.f151int; i++) {
                    this.f147do[i] = this.in.read();
                }
                long[] jArr = this.f149for;
                long[] jArr2 = this.f152new;
                short s = this.f150if;
                int i2 = (s + 2) % 4;
                long j = ((jArr[s % 4] * 2147483085) + jArr2[i2]) % 2147483647L;
                int i3 = (s + 3) % 4;
                jArr2[i3] = ((jArr[i3] * 2147483085) + jArr2[i2]) / 2147483647L;
                jArr[i3] = j;
                for (int i4 = 0; i4 < this.f151int; i4++) {
                    int[] iArr = this.f147do;
                    iArr[i4] = (int) (((long) iArr[i4]) ^ ((this.f149for[this.f150if] >> (i4 << 3)) & 255));
                }
                this.f150if = (short) ((this.f150if + 1) % 4);
                this.f148else = this.in.read();
                if (this.f148else == -1) {
                    Arrays.fill(this.f147do, this.f151int - this.f147do[this.f151int - 1], this.f151int, -1);
                }
            }
            this.f146byte = 0;
        }
        int[] iArr2 = this.f147do;
        int i5 = this.f146byte;
        this.f146byte = i5 + 1;
        return iArr2[i5];
    }
}
