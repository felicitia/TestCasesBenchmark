package org.m4m.android;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.view.Surface;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.m4m.domain.ISurfaceTexture;
import org.m4m.domain.graphics.IEglUtil;
import org.m4m.domain.graphics.TextureRenderer;
import org.m4m.domain.graphics.TextureRenderer.FillMode;

public class OutputSurface implements OnFrameAvailableListener {
    private EGL10 egl;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay;
    private EGLSurface eglSurface;
    private IEglUtil eglUtil;
    private boolean isFrameAvailable;
    private final Object isFrameAvailableSyncGuard = new Object();
    private Surface surface;
    private SurfaceTexture surfaceTexture;
    private int textureId;
    private TextureRenderer textureRender;

    public OutputSurface(IEglUtil iEglUtil) {
        this.eglUtil = iEglUtil;
        this.textureRender = new TextureRenderer(this.eglUtil);
        this.textureRender.surfaceCreated();
        this.textureId = this.eglUtil.createTexture(36197);
        this.surfaceTexture = new SurfaceTexture(this.textureId);
        this.surfaceTexture.setOnFrameAvailableListener(this);
        this.surface = new Surface(this.surfaceTexture);
    }

    public void setInputSize(int i, int i2) {
        this.textureRender.setInputSize(i, i2);
    }

    public void release() {
        if (this.egl != null) {
            if (this.egl.eglGetCurrentContext().equals(this.eglContext)) {
                this.egl.eglMakeCurrent(this.eglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            }
            this.egl.eglDestroySurface(this.eglDisplay, this.eglSurface);
            this.egl.eglDestroyContext(this.eglDisplay, this.eglContext);
        }
        this.surface.release();
        this.surfaceTexture.release();
        this.eglUtil = null;
        this.eglDisplay = null;
        this.eglContext = null;
        this.eglSurface = null;
        this.egl = null;
        this.textureRender = null;
        this.surface = null;
        this.surfaceTexture = null;
    }

    public void makeCurrent() {
        String str = "Failed to set up EGL context and surface.";
        if (this.egl == null) {
            throw new RuntimeException(str);
        }
        this.eglUtil.checkEglError("before makeCurrent");
        if (!this.egl.eglMakeCurrent(this.eglDisplay, this.eglSurface, this.eglSurface, this.eglContext)) {
            throw new RuntimeException(str);
        }
    }

    public Surface getSurface() {
        return this.surface;
    }

    public int getTextureId() {
        return this.textureId;
    }

    public void getTransformMatrix(float[] fArr) {
        this.surfaceTexture.getTransformMatrix(fArr);
    }

    public void awaitNewImage() {
        synchronized (this.isFrameAvailableSyncGuard) {
            int i = 0;
            while (!this.isFrameAvailable) {
                try {
                    this.isFrameAvailableSyncGuard.wait(500);
                    if (!this.isFrameAvailable) {
                        i++;
                        if (i > 20) {
                            throw new RuntimeException("Frame wait timed out.");
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.isFrameAvailable = false;
        }
    }

    public void updateTexImage() {
        this.surfaceTexture.updateTexImage();
    }

    public void drawImage() {
        this.textureRender.drawFrameOES((ISurfaceTexture) new SurfaceTextureWrapper(this.surfaceTexture), this.textureId, 0.0f, FillMode.PreserveAspectFit);
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture2) {
        synchronized (this.isFrameAvailableSyncGuard) {
            boolean z = this.isFrameAvailable;
            this.isFrameAvailable = true;
            this.isFrameAvailableSyncGuard.notifyAll();
        }
    }

    public TextureRenderer getTextureRender() {
        return this.textureRender;
    }
}
