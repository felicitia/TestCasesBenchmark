package org.m4m.domain;

import org.m4m.domain.pipeline.IOnStopListener;

public abstract class Render extends Input {
    protected IOnStopListener onStopListener;

    public abstract int getTrackIdByMediaFormat(MediaFormat mediaFormat);

    public void pushWithReleaser(Frame frame, IPluginOutput iPluginOutput) {
    }

    public abstract void start();

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

    public void addOnStopListener(IOnStopListener iOnStopListener) {
        this.onStopListener = iOnStopListener;
    }
}
