package com.otaliastudios.cameraview;

public class Frame {
    private byte[] mData = null;
    private int mFormat = -1;
    FrameManager mManager;
    private int mRotation = 0;
    private Size mSize = null;
    private long mTime = -1;

    Frame(FrameManager frameManager) {
        this.mManager = frameManager;
    }

    /* access modifiers changed from: 0000 */
    public void set(byte[] bArr, long j, int i, Size size, int i2) {
        this.mData = bArr;
        this.mTime = j;
        this.mRotation = i;
        this.mSize = size;
        this.mFormat = i2;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Frame) && ((Frame) obj).mTime == this.mTime;
    }

    public void release() {
        if (this.mManager != null) {
            this.mManager.onFrameReleased(this);
        }
        this.mData = null;
        this.mRotation = 0;
        this.mTime = -1;
        this.mSize = null;
        this.mFormat = -1;
    }

    /* access modifiers changed from: 0000 */
    public void releaseManager() {
        this.mManager = null;
    }

    public byte[] getData() {
        return this.mData;
    }

    public long getTime() {
        return this.mTime;
    }
}
