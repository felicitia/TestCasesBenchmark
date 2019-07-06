package org.m4m.domain;

import org.m4m.domain.graphics.TextureRenderer.FillMode;

public interface IEffectorSurface extends ISurface {
    void drawImage(int i, float[] fArr, FillMode fillMode);

    void drawImage2D(int i, float[] fArr, int i2, FillMode fillMode);

    int getSurfaceId();

    void getTransformMatrix(float[] fArr);

    void release();
}
