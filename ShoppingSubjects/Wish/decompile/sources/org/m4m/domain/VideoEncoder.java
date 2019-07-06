package org.m4m.domain;

import org.m4m.VideoFormat;

public class VideoEncoder extends Encoder implements ITransform {
    public void setOutputSurface(ISurface iSurface) {
    }

    public void waitForSurface(long j) {
    }

    public VideoEncoder(IMediaCodec iMediaCodec) {
        super(iMediaCodec);
    }

    public void setMediaFormat(MediaFormat mediaFormat) {
        this.mediaFormat = mediaFormat;
        getVideoFormat().setColorFormat(2130708361);
    }

    private VideoFormat getVideoFormat() {
        return (VideoFormat) this.mediaFormat;
    }

    public void drain(int i) {
        if (this.state == PluginState.Normal) {
            getInputCommandQueue().clear();
            this.mediaCodec.signalEndOfInputStream();
        }
    }

    /* access modifiers changed from: protected */
    public void feedMeIfNotDraining() {
        if (this.frameCount < 2 && this.state != PluginState.Draining && this.state != PluginState.Drained) {
            Pair first = getInputCommandQueue().first();
            if (first == null || first.left != Command.NeedData) {
                getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(getTrackId()));
            }
        }
    }

    public void push(Frame frame) {
        super.push(frame);
    }

    public void notifySurfaceReady(ISurface iSurface) {
        if (this.frameCount < 2) {
            iSurface.swapBuffers();
            this.frameCount++;
        }
    }

    public void releaseOutputBuffer(int i) {
        super.releaseOutputBuffer(i);
        this.frameCount--;
    }
}
