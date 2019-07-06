package org.m4m.domain;

public interface IFrameBuffer {
    void bind();

    int getTextureId();

    void release();

    void setResolution(Resolution resolution);

    void unbind();
}
