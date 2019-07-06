package org.m4m.android;

import android.media.MediaCodec;
import android.opengl.EGL14;
import org.m4m.android.AndroidMediaObjectFactory.Converter;
import org.m4m.domain.ISurface;
import org.m4m.domain.ISurfaceWrapper;
import org.m4m.domain.graphics.IEglUtil;

public class Surface implements ISurface {
    private int height;
    private final InputSurface inputSurface;
    private final OutputSurface outputSurface;
    private int width;

    public void release() {
    }

    public Surface(MediaCodec mediaCodec, IEglUtil iEglUtil) {
        this.inputSurface = new InputSurface(mediaCodec.createInputSurface(), EGL14.eglGetCurrentContext());
        this.inputSurface.makeCurrent();
        this.outputSurface = new OutputSurface(iEglUtil);
    }

    public void awaitAndCopyNewImage() {
        this.outputSurface.awaitNewImage();
        this.outputSurface.updateTexImage();
    }

    public void drawImage() {
        this.outputSurface.drawImage();
    }

    public void setPresentationTime(long j) {
        this.inputSurface.setPresentationTime(j);
    }

    public void swapBuffers() {
        this.inputSurface.swapBuffers();
    }

    public void makeCurrent() {
        this.inputSurface.makeCurrent();
    }

    public ISurfaceWrapper getCleanObject() {
        return Converter.convert(this.outputSurface.getSurface());
    }

    public void setInputSize(int i, int i2) {
        this.width = i;
        this.height = i2;
        this.outputSurface.setInputSize(i, i2);
    }
}
