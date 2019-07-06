package org.m4m.domain;

import java.nio.ByteBuffer;

public interface IMediaCodec {

    public static class BufferInfo {
        public int flags;
        public int offset;
        public long presentationTimeUs;
        public int size;

        public boolean isEof() {
            return (this.flags & 4) != 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BufferInfo bufferInfo = (BufferInfo) obj;
            return this.flags == bufferInfo.flags && this.offset == bufferInfo.offset && this.presentationTimeUs == bufferInfo.presentationTimeUs && this.size == bufferInfo.size;
        }

        public int hashCode() {
            return (((((this.flags * 31) + this.offset) * 31) + ((int) (this.presentationTimeUs ^ (this.presentationTimeUs >>> 32)))) * 31) + this.size;
        }
    }

    void configure(MediaFormat mediaFormat, ISurfaceWrapper iSurfaceWrapper, int i);

    ISurface createInputSurface();

    ISurface createSimpleInputSurface(IEglContext iEglContext);

    int dequeueInputBuffer(long j);

    int dequeueOutputBuffer(BufferInfo bufferInfo, long j);

    ByteBuffer[] getInputBuffers();

    ByteBuffer[] getOutputBuffers();

    MediaFormat getOutputFormat();

    void queueInputBuffer(int i, int i2, int i3, long j, int i4);

    void recreate();

    void release();

    void releaseOutputBuffer(int i, boolean z);

    void signalEndOfInputStream();

    void start();

    void stop();
}
