package org.m4m.android;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.m4m.domain.IEglContext;
import org.m4m.domain.IMediaCodec;
import org.m4m.domain.ISurface;
import org.m4m.domain.MediaFormat;

public abstract class MediaCodecDecoderPlugin implements IMediaCodec {
    private BufferInfo inputBufferInfo;
    private ByteBuffer[] inputBuffers;
    protected MediaCodec mediaCodec;
    private BufferInfo outputBufferInfo;
    private ByteBuffer[] outputBuffers;

    public ISurface createInputSurface() {
        return null;
    }

    public ISurface createSimpleInputSurface(IEglContext iEglContext) {
        return null;
    }

    public void signalEndOfInputStream() {
    }

    public MediaCodecDecoderPlugin(String str) {
        try {
            this.mediaCodec = MediaCodec.createDecoderByType(str);
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        this.outputBufferInfo = new BufferInfo();
        this.inputBufferInfo = new BufferInfo();
    }

    public void start() {
        this.mediaCodec.start();
        this.inputBuffers = null;
        this.outputBuffers = null;
    }

    public void releaseOutputBuffer(int i, boolean z) {
        this.mediaCodec.releaseOutputBuffer(i, z);
    }

    public ByteBuffer[] getInputBuffers() {
        if (this.inputBuffers == null) {
            this.inputBuffers = this.mediaCodec.getInputBuffers();
        }
        return this.inputBuffers;
    }

    public ByteBuffer[] getOutputBuffers() {
        if (this.outputBuffers == null) {
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
        }
        return this.outputBuffers;
    }

    public void queueInputBuffer(int i, int i2, int i3, long j, int i4) {
        this.mediaCodec.queueInputBuffer(i, i2, i3, j, i4);
    }

    public int dequeueInputBuffer(long j) {
        return this.mediaCodec.dequeueInputBuffer(j);
    }

    public int dequeueOutputBuffer(IMediaCodec.BufferInfo bufferInfo, long j) {
        int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(this.outputBufferInfo, j);
        if (dequeueOutputBuffer == -3) {
            this.outputBuffers = null;
            getOutputBuffers();
        }
        BufferInfoTranslator.convertFromAndroid(this.outputBufferInfo, bufferInfo);
        return dequeueOutputBuffer;
    }

    public MediaFormat getOutputFormat() {
        return MediaFormatTranslator.toDomain(this.mediaCodec.getOutputFormat());
    }

    public void stop() {
        this.mediaCodec.stop();
    }
}
