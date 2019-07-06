package org.m4m.android;

import android.opengl.EGLContext;
import org.m4m.domain.IEglContext;
import org.m4m.domain.IWrapper;

public class EGLContextWrapper implements IEglContext, IWrapper<EGLContext> {
    private EGLContext eglContext;

    public EGLContextWrapper(EGLContext eGLContext) {
        this.eglContext = eGLContext;
    }

    public EGLContext getNativeObject() {
        return this.eglContext;
    }
}
