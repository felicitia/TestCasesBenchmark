package org.m4m.domain;

import java.nio.ByteBuffer;
import org.m4m.domain.IMediaCodec.BufferInfo;

public class Frame {
    private static final Frame emptyFrame;
    private static final Frame eofFrame = new EofFrame();
    private int bufferIndex;
    private final BufferInfo bufferInfo = new BufferInfo();
    protected ByteBuffer byteBuffer;
    private boolean skipFrame = false;
    protected int trackId;

    static {
        Frame frame = new Frame(null, 0, 0, 0, 0, 0);
        emptyFrame = frame;
    }

    public Frame(ByteBuffer byteBuffer2, int i, long j, int i2, int i3, int i4) {
        this.byteBuffer = byteBuffer2;
        this.trackId = i4;
        this.bufferInfo.flags = i3;
        this.bufferInfo.presentationTimeUs = j;
        this.bufferIndex = i2;
        this.bufferInfo.size = i;
    }

    public void set(ByteBuffer byteBuffer2, int i, long j, int i2, int i3, int i4) {
        this.byteBuffer = byteBuffer2;
        this.trackId = i4;
        this.bufferInfo.flags = i3;
        this.bufferInfo.presentationTimeUs = j;
        this.bufferIndex = i2;
        this.bufferInfo.size = i;
    }

    public ByteBuffer getByteBuffer() {
        return this.byteBuffer;
    }

    public int getLength() {
        return this.bufferInfo.size;
    }

    public void setLength(int i) {
        this.bufferInfo.size = i;
    }

    public long getSampleTime() {
        return this.bufferInfo.presentationTimeUs;
    }

    public void setSampleTime(long j) {
        this.bufferInfo.presentationTimeUs = j;
    }

    public int getTrackId() {
        return this.trackId;
    }

    public void setTrackId(int i) {
        this.trackId = i;
    }

    public static Frame EOF() {
        return eofFrame;
    }

    public static Frame empty() {
        return emptyFrame;
    }

    public void copyInfoFrom(Frame frame) {
        copyBufferInfoFrom(frame);
    }

    public void copyDataFrom(Frame frame) {
        copyBufferInfoFrom(frame);
        ByteBuffer duplicate = frame.getByteBuffer().duplicate();
        duplicate.rewind();
        if (frame.getLength() >= 0) {
            duplicate.limit(frame.getLength());
        }
        this.byteBuffer.rewind();
        this.byteBuffer.put(duplicate);
    }

    private void copyBufferInfoFrom(Frame frame) {
        this.bufferInfo.size = frame.getLength();
        this.bufferInfo.presentationTimeUs = frame.getSampleTime();
        this.bufferInfo.flags = frame.getFlags();
        this.trackId = frame.getTrackId();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Frame)) {
            return false;
        }
        return equals((Frame) obj);
    }

    private boolean equals(Frame frame) {
        if (frame instanceof EofFrame) {
            return ((EofFrame) frame).equals(this);
        }
        if (this.bufferInfo.size == 0 && frame.bufferInfo.size == 0) {
            return true;
        }
        if (this.bufferInfo.size == frame.bufferInfo.size && this.bufferInfo.presentationTimeUs == frame.bufferInfo.presentationTimeUs && this.byteBuffer.equals(frame.byteBuffer) && this.trackId == frame.trackId) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.bufferInfo.hashCode();
        if (this.byteBuffer != null) {
            hashCode = (hashCode * 31) + this.byteBuffer.hashCode();
        }
        return (((hashCode * 31) + this.trackId) * 31) + this.bufferIndex;
    }

    public int getBufferIndex() {
        return this.bufferIndex;
    }

    public int getFlags() {
        return this.bufferInfo.flags;
    }

    public void setFlags(int i) {
        this.bufferInfo.flags = i;
    }

    public void toSkipFrame(boolean z) {
        this.skipFrame = z;
    }

    public boolean isSkipFrame() {
        return this.skipFrame;
    }
}
