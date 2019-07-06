package org.m4m.domain;

public interface IPreview {
    PreviewContext getSharedContext();

    void renderSurfaceFromFrameBuffer(int i);

    void requestRendering();

    void setListener(IOnFrameAvailableListener iOnFrameAvailableListener);
}
