package com.otaliastudios.cameraview;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

class FrameManager {
    private int mBufferSize = -1;
    private BufferCallback mCallback;
    private int mPoolSize;
    private LinkedBlockingQueue<Frame> mQueue = new LinkedBlockingQueue<>(this.mPoolSize);

    interface BufferCallback {
        void onBufferAvailable(byte[] bArr);
    }

    FrameManager(int i, BufferCallback bufferCallback) {
        this.mPoolSize = i;
        this.mCallback = bufferCallback;
    }

    /* access modifiers changed from: 0000 */
    public void release() {
        Iterator it = this.mQueue.iterator();
        while (it.hasNext()) {
            Frame frame = (Frame) it.next();
            frame.releaseManager();
            frame.release();
        }
        this.mQueue.clear();
        this.mBufferSize = -1;
    }

    /* access modifiers changed from: 0000 */
    public void onFrameReleased(Frame frame) {
        byte[] data = frame.getData();
        if (!this.mQueue.offer(frame)) {
            frame.releaseManager();
        }
        if (data != null && this.mCallback != null && data.length == this.mBufferSize) {
            this.mCallback.onBufferAvailable(data);
        }
    }

    /* access modifiers changed from: 0000 */
    public Frame getFrame(byte[] bArr, long j, int i, Size size, int i2) {
        Frame frame = (Frame) this.mQueue.poll();
        if (frame == null) {
            frame = new Frame(this);
        }
        frame.set(bArr, j, i, size, i2);
        return frame;
    }

    /* access modifiers changed from: 0000 */
    public int allocate(int i, Size size) {
        this.mBufferSize = getBufferSize(i, size);
        for (int i2 = 0; i2 < this.mPoolSize; i2++) {
            this.mCallback.onBufferAvailable(new byte[this.mBufferSize]);
        }
        return this.mBufferSize;
    }

    private int getBufferSize(int i, Size size) {
        return ((int) Math.ceil(((double) ((long) ((size.getHeight() * size.getWidth()) * i))) / 8.0d)) + 1;
    }
}
