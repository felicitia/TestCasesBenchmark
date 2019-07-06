package org.m4m.domain;

public abstract class Plugin extends Input implements IPluginOutput, ITransform {
    protected MediaFormat mediaFormat = null;
    private CommandQueue outputQueue = new CommandQueue();

    public boolean canConnectFirst(IInputRaw iInputRaw) {
        return true;
    }

    public boolean canConnectFirst(IOutputRaw iOutputRaw) {
        return true;
    }

    public void checkIfOutputQueueHasData() {
    }

    public void incrementConnectedPluginsCount() {
    }

    public void notifySurfaceReady(ISurface iSurface) {
    }

    public void recreate() {
    }

    public abstract void start();

    public abstract void stop();

    public /* bridge */ /* synthetic */ void drain(int i) {
        super.drain(i);
    }

    public /* bridge */ /* synthetic */ CommandQueue getInputCommandQueue() {
        return super.getInputCommandQueue();
    }

    public /* bridge */ /* synthetic */ int getTrackId() {
        return super.getTrackId();
    }

    public /* bridge */ /* synthetic */ void setTrackId(int i) {
        super.setTrackId(i);
    }

    public /* bridge */ /* synthetic */ void skipProcessing() {
        super.skipProcessing();
    }

    protected Plugin() {
    }

    public CommandQueue getOutputCommandQueue() {
        return this.outputQueue;
    }

    public void push(Frame frame) {
        if (frame.equals((Object) Frame.EOF())) {
            drain(frame.getBufferIndex());
        }
    }

    public MediaFormatType getMediaFormatType() {
        if (this.mediaFormat.getMimeType().startsWith("audio")) {
            return MediaFormatType.AUDIO;
        }
        return MediaFormatType.VIDEO;
    }

    public MediaFormat getMediaFormatByType(MediaFormatType mediaFormatType) {
        if (this.mediaFormat.getMimeType().startsWith(mediaFormatType.toString())) {
            return this.mediaFormat;
        }
        return null;
    }

    public MediaFormat getOutputMediaFormat() {
        return this.mediaFormat;
    }

    public void setInputResolution(Resolution resolution) {
        getSurface().setInputSize(resolution.width(), resolution.height());
    }
}
