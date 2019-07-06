package com.google.android.exoplayer2.decoder;

import java.nio.ByteBuffer;

public class DecoderInputBuffer extends Buffer {
    private final int bufferReplacementMode;
    public final CryptoInfo cryptoInfo = new CryptoInfo();
    public ByteBuffer data;
    public long timeUs;

    public static DecoderInputBuffer newFlagsOnlyInstance() {
        return new DecoderInputBuffer(0);
    }

    public DecoderInputBuffer(int i) {
        this.bufferReplacementMode = i;
    }

    public void ensureSpaceForWrite(int i) throws IllegalStateException {
        if (this.data == null) {
            this.data = createReplacementByteBuffer(i);
            return;
        }
        int capacity = this.data.capacity();
        int position = this.data.position();
        int i2 = i + position;
        if (capacity < i2) {
            ByteBuffer createReplacementByteBuffer = createReplacementByteBuffer(i2);
            if (position > 0) {
                this.data.position(0);
                this.data.limit(position);
                createReplacementByteBuffer.put(this.data);
            }
            this.data = createReplacementByteBuffer;
        }
    }

    public final boolean isFlagsOnly() {
        return this.data == null && this.bufferReplacementMode == 0;
    }

    public final boolean isEncrypted() {
        return getFlag(1073741824);
    }

    public final void flip() {
        this.data.flip();
    }

    public void clear() {
        super.clear();
        if (this.data != null) {
            this.data.clear();
        }
    }

    private ByteBuffer createReplacementByteBuffer(int i) {
        if (this.bufferReplacementMode == 1) {
            return ByteBuffer.allocate(i);
        }
        if (this.bufferReplacementMode == 2) {
            return ByteBuffer.allocateDirect(i);
        }
        int capacity = this.data == null ? 0 : this.data.capacity();
        StringBuilder sb = new StringBuilder();
        sb.append("Buffer too small (");
        sb.append(capacity);
        sb.append(" < ");
        sb.append(i);
        sb.append(")");
        throw new IllegalStateException(sb.toString());
    }
}
