package org.m4m.domain;

public interface IPluginOutput extends IOutput, IReadyFrameProvider, ISurfaceProvider {
    void releaseOutputBuffer(int i);

    void setOutputSurface(ISurface iSurface);

    void setOutputTrackId(int i);
}
