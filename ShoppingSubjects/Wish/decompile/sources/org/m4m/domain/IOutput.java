package org.m4m.domain;

import java.io.Closeable;

public interface IOutput extends Closeable, IOutputRaw, IRunnable {
    MediaFormat getMediaFormatByType(MediaFormatType mediaFormatType);

    void incrementConnectedPluginsCount();

    void pull(Frame frame);
}
