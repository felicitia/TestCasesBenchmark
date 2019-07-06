package org.m4m.domain;

import org.m4m.VideoFormat;

public class VideoDecoder extends Decoder implements IVideoOutput {
    public /* bridge */ /* synthetic */ void configure() {
        super.configure();
    }

    public /* bridge */ /* synthetic */ MediaFormatType getMediaFormatType() {
        return super.getMediaFormatType();
    }

    public /* bridge */ /* synthetic */ ISurface getSurface() {
        return super.getSurface();
    }

    public /* bridge */ /* synthetic */ void pull(Frame frame) {
        super.pull(frame);
    }

    public /* bridge */ /* synthetic */ void recreate() {
        super.recreate();
    }

    public /* bridge */ /* synthetic */ void releaseOutputBuffer(int i) {
        super.releaseOutputBuffer(i);
    }

    public /* bridge */ /* synthetic */ void setMediaFormat(MediaFormat mediaFormat) {
        super.setMediaFormat(mediaFormat);
    }

    public /* bridge */ /* synthetic */ void setOutputSurface(ISurface iSurface) {
        super.setOutputSurface(iSurface);
    }

    public /* bridge */ /* synthetic */ void waitForSurface(long j) {
        super.waitForSurface(j);
    }

    public VideoDecoder(IMediaCodec iMediaCodec) {
        super(iMediaCodec, MediaFormatType.VIDEO);
    }

    public void drain(int i) {
        if (this.state == PluginState.Normal) {
            super.drain(i);
            this.mediaCodec.signalEndOfInputStream();
        }
    }

    public void push(Frame frame) {
        if (this.state == PluginState.Draining || this.state == PluginState.Drained) {
            throw new RuntimeException("Out of order operation.");
        }
        super.push(frame);
    }

    public void stop() {
        super.stop();
        recreate();
    }

    /* access modifiers changed from: protected */
    public void initInputCommandQueue() {
        getInputCommandQueue().queue(Command.NeedInputFormat, Integer.valueOf(getTrackId()));
    }

    public Resolution getOutputResolution() {
        return ((VideoFormat) getOutputMediaFormat()).getVideoFrameSize();
    }
}
