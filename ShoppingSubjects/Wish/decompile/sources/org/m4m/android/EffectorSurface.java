package org.m4m.android;

import org.m4m.android.AndroidMediaObjectFactory.Converter;
import org.m4m.domain.IEffectorSurface;
import org.m4m.domain.ISurfaceWrapper;
import org.m4m.domain.graphics.IEglUtil;
import org.m4m.domain.graphics.TextureRenderer.FillMode;

public class EffectorSurface implements IEffectorSurface {
    private int height;
    OutputSurface outputSurface;
    private int width;

    public void setPresentationTime(long j) {
    }

    public void swapBuffers() {
    }

    public EffectorSurface(IEglUtil iEglUtil) {
        this.outputSurface = new OutputSurface(iEglUtil);
    }

    public void release() {
        this.outputSurface.release();
    }

    public void updateTexImage() {
        this.outputSurface.updateTexImage();
    }

    public void awaitAndCopyNewImage() {
        awaitNewImage();
        updateTexImage();
    }

    public void awaitNewImage() {
        this.outputSurface.awaitNewImage();
    }

    public void drawImage() {
        this.outputSurface.drawImage();
    }

    public void makeCurrent() {
        this.outputSurface.makeCurrent();
    }

    public void getTransformMatrix(float[] fArr) {
        this.outputSurface.getTransformMatrix(fArr);
    }

    public ISurfaceWrapper getCleanObject() {
        return Converter.convert(this.outputSurface.getSurface());
    }

    public int getSurfaceId() {
        return this.outputSurface.getTextureId();
    }

    public void drawImage(int i, float[] fArr, FillMode fillMode) {
        this.outputSurface.getTextureRender().drawFrameOES(fArr, i, 0.0f, fillMode);
    }

    public void drawImage2D(int i, float[] fArr, int i2, FillMode fillMode) {
        this.outputSurface.getTextureRender().drawFrame2D(fArr, i, (float) i2, fillMode);
    }

    public void setInputSize(int i, int i2) {
        this.width = i;
        this.height = i2;
        this.outputSurface.getTextureRender().setInputSize(i, i2);
    }
}
