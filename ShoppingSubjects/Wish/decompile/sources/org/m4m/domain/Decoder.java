package org.m4m.domain;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.m4m.domain.IMediaCodec.BufferInfo;

abstract class Decoder extends MediaCodecPlugin implements IFrameAllocator, ITransform {
    private ISurfaceWrapper clearOutputSurface;
    private ArrayList<Long> framesPTSToSkip = new ArrayList<>();
    private final MediaFormatType mediaFormatType;
    private ISurface outputSurface;

    public Decoder(IMediaCodec iMediaCodec, MediaFormatType mediaFormatType2) {
        super(iMediaCodec);
        this.mediaFormatType = mediaFormatType2;
    }

    public void setMediaFormat(MediaFormat mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    public void setOutputSurface(ISurface iSurface) {
        this.outputSurface = iSurface;
        this.clearOutputSurface = iSurface.getCleanObject();
    }

    public void push(Frame frame) {
        super.push(frame);
        this.mediaCodec.queueInputBuffer(frame.getBufferIndex(), 0, frame.getLength(), frame.getSampleTime(), frame.getFlags());
        if (frame.isSkipFrame()) {
            this.framesPTSToSkip.add(Long.valueOf(frame.getSampleTime()));
        }
        getOutputBufferIndex();
        feedMeIfNotDraining();
    }

    public void pull(Frame frame) {
        BufferInfo bufferInfo = new BufferInfo();
        int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, (long) this.timeout);
        if (dequeueOutputBuffer >= 0) {
            ByteBuffer[] outputBuffers = this.mediaCodec.getOutputBuffers();
            frame.setSampleTime(bufferInfo.presentationTimeUs);
            frame.setFlags(bufferInfo.flags);
            frame.setLength(bufferInfo.size);
            ByteBuffer duplicate = outputBuffers[dequeueOutputBuffer].duplicate();
            duplicate.position(0);
            if (frame.getLength() >= 0) {
                duplicate.limit(frame.getLength());
            }
            frame.getByteBuffer().position(0);
            frame.getByteBuffer().put(outputBuffers[dequeueOutputBuffer]);
            this.mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
        }
    }

    public void waitForSurface(long j) {
        this.outputSurface.awaitAndCopyNewImage();
        this.outputSurface.drawImage();
        this.outputSurface.setPresentationTime(j * 1000);
    }

    public void releaseOutputBuffer(int i) {
        this.mediaCodec.releaseOutputBuffer(i, this.clearOutputSurface != null);
    }

    public void stop() {
        super.stop();
        this.outputBufferInfos.clear();
        this.outputBufferIndexes.clear();
        this.inputBufferIndexes.clear();
        getOutputCommandQueue().clear();
    }

    public void configure() {
        this.mediaCodec.configure(this.mediaFormat, this.clearOutputSurface, 0);
    }

    public ISurface getSurface() {
        return this.outputSurface;
    }

    public void drain(int i) {
        getInputCommandQueue().clear();
        this.mediaCodec.queueInputBuffer(i, 0, 0, 0, 4);
    }

    public MediaFormatType getMediaFormatType() {
        return this.mediaFormatType;
    }

    public void recreate() {
        this.mediaCodec.recreate();
    }

    /* access modifiers changed from: protected */
    public void hasData() {
        super.hasData();
        getOutputCommandQueue().queue(Command.NextPair, Integer.valueOf(0));
    }

    private void outputFormatChanged() {
        getOutputCommandQueue().queue(Command.OutputFormatChanged, Integer.valueOf(0));
    }

    private int addOutputBuffer(int i, BufferInfo bufferInfo) {
        if (!this.framesPTSToSkip.contains(Long.valueOf(bufferInfo.presentationTimeUs)) || bufferInfo.isEof()) {
            this.outputBufferIndexes.add(Integer.valueOf(i));
            this.outputBufferInfos.add(bufferInfo);
            return i;
        }
        this.framesPTSToSkip.remove(Long.valueOf(bufferInfo.presentationTimeUs));
        if (i >= 0) {
            this.mediaCodec.releaseOutputBuffer(i, false);
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int getOutputBufferIndex() {
        BufferInfo bufferInfo = new BufferInfo();
        int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, (long) this.timeout);
        if (this.state == PluginState.Draining && dequeueOutputBuffer == -1) {
            this.state = PluginState.Drained;
        }
        if (!(dequeueOutputBuffer == -1 || dequeueOutputBuffer == -2)) {
            dequeueOutputBuffer = addOutputBuffer(dequeueOutputBuffer, bufferInfo);
        }
        if (dequeueOutputBuffer >= 0) {
            hasData();
        }
        if (bufferInfo.isEof() && this.state != PluginState.Drained) {
            setState(PluginState.Draining);
            getOutputCommandQueue().queue(Command.EndOfFile, Integer.valueOf(this.outputTrackId));
        }
        if (dequeueOutputBuffer == -2) {
            this.outputMediaFormat = this.mediaCodec.getOutputFormat();
            outputFormatChanged();
        }
        return dequeueOutputBuffer;
    }
}
