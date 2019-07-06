package org.m4m.domain;

public interface ISurface {
    void awaitAndCopyNewImage();

    void drawImage();

    ISurfaceWrapper getCleanObject();

    void makeCurrent();

    void release();

    void setInputSize(int i, int i2);

    void setPresentationTime(long j);

    void swapBuffers();
}
