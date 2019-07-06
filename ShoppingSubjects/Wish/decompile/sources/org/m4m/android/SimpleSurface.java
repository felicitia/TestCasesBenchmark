package org.m4m.android;

import android.media.MediaCodec;
import android.opengl.EGLContext;
import android.view.Surface;
import org.m4m.android.AndroidMediaObjectFactory.Converter;
import org.m4m.domain.ISurface;
import org.m4m.domain.ISurfaceWrapper;

public class SimpleSurface implements ISurface {
    private Surface androidSurface;
    private int height;
    private InputSurface inputSurface;
    private int width;

    public void awaitAndCopyNewImage() {
    }

    public void drawImage() {
    }

    public SimpleSurface(MediaCodec mediaCodec, EGLContext eGLContext) {
        this.androidSurface = mediaCodec.createInputSurface();
        this.inputSurface = new InputSurface(this.androidSurface, eGLContext);
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
        return Converter.convert(this.inputSurface.getSurface());
    }

    public void setInputSize(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public void release() {
        this.inputSurface.release();
        this.androidSurface.release();
        this.inputSurface = null;
        this.androidSurface = null;
    }
}
