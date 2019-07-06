package org.m4m.domain;

import java.io.Closeable;

public interface ICameraSource extends Closeable, IOutputRaw, IRunnable, IVideoOutput {
    void configure();

    Frame getFrame();

    ISurface getSurface();

    void setOutputSurface(ISurface iSurface);

    void setPreview(IPreview iPreview);
}
