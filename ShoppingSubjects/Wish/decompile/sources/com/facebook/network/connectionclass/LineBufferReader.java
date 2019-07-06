package com.facebook.network.connectionclass;

import java.io.FileInputStream;
import java.io.IOException;

class LineBufferReader {
    private int mBytesInBuffer;
    private int mFileBufIndex;
    private byte[] mFileBuffer = new byte[512];
    private FileInputStream mInputStream;

    public void setFileStream(FileInputStream fileInputStream) {
        this.mInputStream = fileInputStream;
        this.mBytesInBuffer = 0;
        this.mFileBufIndex = 0;
    }

    public int readLine(byte[] bArr) throws IOException {
        if (this.mFileBufIndex >= this.mBytesInBuffer) {
            this.mBytesInBuffer = this.mInputStream.read(this.mFileBuffer);
            this.mFileBufIndex = 0;
        }
        int i = 0;
        while (this.mBytesInBuffer != -1 && i < bArr.length && this.mFileBuffer[this.mFileBufIndex] != 10) {
            bArr[i] = this.mFileBuffer[this.mFileBufIndex];
            this.mFileBufIndex++;
            if (this.mFileBufIndex >= this.mBytesInBuffer) {
                this.mBytesInBuffer = this.mInputStream.read(this.mFileBuffer);
                this.mFileBufIndex = 0;
            }
            i++;
        }
        this.mFileBufIndex++;
        if (this.mBytesInBuffer == -1) {
            return -1;
        }
        return i;
    }

    public void skipLine() throws IOException {
        if (this.mFileBufIndex >= this.mBytesInBuffer) {
            this.mBytesInBuffer = this.mInputStream.read(this.mFileBuffer);
            this.mFileBufIndex = 0;
        }
        while (this.mBytesInBuffer != -1 && this.mFileBuffer[this.mFileBufIndex] != 10) {
            this.mFileBufIndex++;
            if (this.mFileBufIndex >= this.mBytesInBuffer) {
                this.mBytesInBuffer = this.mInputStream.read(this.mFileBuffer);
                this.mFileBufIndex = 0;
            }
        }
        this.mFileBufIndex++;
    }
}
