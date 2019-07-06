package org.m4m.domain;

import java.io.IOException;

public class PassThroughPlugin extends Plugin implements IFrameAllocator {
    Frame frame;
    private boolean frameDelivered;
    private MediaFormatType mediaFormatType;
    int outputTrackId;

    public void checkIfOutputQueueHasData() {
    }

    public void close() throws IOException {
    }

    public void configure() {
    }

    public void fillCommandQueues() {
    }

    public ISurface getSurface() {
        return null;
    }

    public void releaseOutputBuffer(int i) {
    }

    public void setOutputSurface(ISurface iSurface) {
    }

    public void start() {
    }

    public void stop() {
    }

    public void waitForSurface(long j) {
    }

    /* access modifiers changed from: protected */
    public void initInputCommandQueue() {
        feedMeIfNotDraining();
    }

    public Frame findFreeFrame() {
        return this.frame;
    }

    public MediaFormatType getMediaFormatType() {
        return this.mediaFormatType;
    }

    public void drain(int i) {
        super.drain(i);
        getOutputCommandQueue().queue(Command.EndOfFile, Integer.valueOf(0));
    }

    public void push(Frame frame2) {
        super.push(frame2);
        if (!frame2.equals((Object) Frame.EOF())) {
            this.frameDelivered = false;
            this.frame = frame2;
            getOutputCommandQueue().queue(Command.HasData, Integer.valueOf(0));
        }
    }

    public void pull(Frame frame2) {
        frame2.copyInfoFrom(getFrame());
    }

    public Frame getFrame() {
        if (!this.frameDelivered) {
            this.frameDelivered = true;
            feedMeIfNotDraining();
            this.frame.setTrackId(this.outputTrackId);
            return this.frame;
        } else if (this.state == PluginState.Draining) {
            return Frame.EOF();
        } else {
            throw new UnsupportedOperationException("Attempt to pull a frame twice.");
        }
    }

    public void skipProcessing() {
        getInputCommandQueue().queue(Command.NextPair, Integer.valueOf(getTrackId()));
    }

    public void setMediaFormat(MediaFormat mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    public void setOutputTrackId(int i) {
        this.outputTrackId = i;
    }
}
