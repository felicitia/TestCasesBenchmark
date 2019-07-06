package org.m4m.domain;

public class AudioDecoder extends Decoder {
    public /* bridge */ /* synthetic */ void configure() {
        super.configure();
    }

    public /* bridge */ /* synthetic */ void drain(int i) {
        super.drain(i);
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

    public /* bridge */ /* synthetic */ void push(Frame frame) {
        super.push(frame);
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

    public AudioDecoder(IMediaCodec iMediaCodec) {
        super(iMediaCodec, MediaFormatType.AUDIO);
    }

    public void stop() {
        super.stop();
        recreate();
    }

    public MediaFormat getOutputMediaFormat() {
        return this.outputMediaFormat;
    }
}
