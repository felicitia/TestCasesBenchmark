package com.facebook.network.connectionclass;

import java.util.NoSuchElementException;

class ByteArrayScanner {
    private int mCurrentOffset;
    private byte[] mData;
    private char mDelimiter;
    private boolean mDelimiterSet;
    private int mTotalLength;

    ByteArrayScanner() {
    }

    public ByteArrayScanner reset(byte[] bArr, int i) {
        this.mData = bArr;
        this.mCurrentOffset = 0;
        this.mTotalLength = i;
        this.mDelimiterSet = false;
        return this;
    }

    public ByteArrayScanner useDelimiter(char c) {
        throwIfNotReset();
        this.mDelimiter = c;
        this.mDelimiterSet = true;
        return this;
    }

    private void throwIfNotReset() {
        if (this.mData == null) {
            throw new IllegalStateException("Must call reset first");
        }
    }

    private void throwIfDelimiterNotSet() {
        if (!this.mDelimiterSet) {
            throw new IllegalStateException("Must call useDelimiter first");
        }
    }

    public boolean nextStringEquals(String str) throws NoSuchElementException {
        int i = this.mCurrentOffset;
        if (str.length() != advance()) {
            return false;
        }
        int i2 = i;
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (str.charAt(i3) != this.mData[i2]) {
                return false;
            }
            i2++;
        }
        return true;
    }

    public int nextInt() throws NoSuchElementException {
        throwIfNotReset();
        throwIfDelimiterNotSet();
        int i = this.mCurrentOffset;
        return parseInt(this.mData, i, advance() + i);
    }

    public void skip() throws NoSuchElementException {
        throwIfNotReset();
        throwIfDelimiterNotSet();
        advance();
    }

    private int advance() throws NoSuchElementException {
        throwIfNotReset();
        throwIfDelimiterNotSet();
        if (this.mTotalLength <= this.mCurrentOffset) {
            StringBuilder sb = new StringBuilder();
            sb.append("Reading past end of input stream at ");
            sb.append(this.mCurrentOffset);
            sb.append(".");
            throw new NoSuchElementException(sb.toString());
        }
        int indexOf = indexOf(this.mData, this.mCurrentOffset, this.mTotalLength, this.mDelimiter);
        if (indexOf == -1) {
            int i = this.mTotalLength - this.mCurrentOffset;
            this.mCurrentOffset = this.mTotalLength;
            return i;
        }
        int i2 = indexOf - this.mCurrentOffset;
        this.mCurrentOffset = indexOf + 1;
        return i2;
    }

    private static int parseInt(byte[] bArr, int i, int i2) throws NumberFormatException {
        int i3 = 0;
        while (i < i2) {
            int i4 = i + 1;
            int i5 = bArr[i] - 48;
            if (i5 < 0 || i5 > 9) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid int in buffer at ");
                sb.append(i4 - 1);
                sb.append(".");
                throw new NumberFormatException(sb.toString());
            }
            i3 = (i3 * 10) + i5;
            i = i4;
        }
        return i3;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=byte, for r4v0, types: [byte, char] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int indexOf(byte[] r1, int r2, int r3, byte r4) {
        /*
        L_0x0000:
            if (r2 >= r3) goto L_0x000a
            byte r0 = r1[r2]
            if (r0 != r4) goto L_0x0007
            return r2
        L_0x0007:
            int r2 = r2 + 1
            goto L_0x0000
        L_0x000a:
            r1 = -1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.network.connectionclass.ByteArrayScanner.indexOf(byte[], int, int, char):int");
    }
}
