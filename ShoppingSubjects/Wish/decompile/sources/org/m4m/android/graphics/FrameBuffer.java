package org.m4m.android.graphics;

import android.opengl.GLES20;
import org.m4m.domain.IFrameBuffer;
import org.m4m.domain.Resolution;
import org.m4m.domain.graphics.IEglUtil;

public class FrameBuffer implements IFrameBuffer {
    private int depthBuffer = -1;
    private int framebuffer = -1;
    private int offScreenTexture = -1;
    private Resolution resolution;
    private IEglUtil utils;

    public FrameBuffer(IEglUtil iEglUtil) {
        this.utils = iEglUtil;
    }

    public void setResolution(Resolution resolution2) {
        this.resolution = resolution2;
        if (this.framebuffer != -1) {
            release();
        }
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        this.utils.checkEglError("glGenTextures");
        this.offScreenTexture = iArr[0];
        GLES20.glBindTexture(3553, this.offScreenTexture);
        this.utils.checkEglError("glBindTexture");
        GLES20.glTexImage2D(3553, 0, 6408, resolution2.width(), resolution2.height(), 0, 6408, 5121, null);
        this.utils.checkEglError("glTexImage2D");
        GLES20.glTexParameterf(3553, 10241, 9728.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glGenFramebuffers(1, iArr, 0);
        this.utils.checkEglError("glGenFramebuffers");
        this.framebuffer = iArr[0];
        GLES20.glBindFramebuffer(36160, this.framebuffer);
        this.utils.checkEglError("glBindFramebuffer");
        GLES20.glGenRenderbuffers(1, iArr, 0);
        this.utils.checkEglError("glGenRenderbuffers");
        this.depthBuffer = iArr[0];
        GLES20.glBindRenderbuffer(36161, this.depthBuffer);
        this.utils.checkEglError("glBindRenderbuffer");
        GLES20.glRenderbufferStorage(36161, 33189, resolution2.width(), resolution2.height());
        this.utils.checkEglError("glRenderbufferStorage");
        GLES20.glFramebufferRenderbuffer(36160, 36096, 36161, this.depthBuffer);
        this.utils.checkEglError("glFramebufferRenderbuffer");
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.offScreenTexture, 0);
        this.utils.checkEglError("glFramebufferTexture2D");
        int glCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
        this.utils.checkEglError("glCheckFramebufferStatus");
        if (glCheckFramebufferStatus != 36053) {
            StringBuilder sb = new StringBuilder();
            sb.append("Incomplete framebuffer. Status: ");
            sb.append(glCheckFramebufferStatus);
            throw new RuntimeException(sb.toString());
        }
        GLES20.glBindFramebuffer(36160, 0);
        this.utils.checkEglError("glBindFramebuffer(0)");
    }

    public int getTextureId() {
        return this.offScreenTexture;
    }

    public void release() {
        int[] iArr = new int[1];
        if (this.offScreenTexture > 0) {
            iArr[0] = this.offScreenTexture;
            GLES20.glDeleteTextures(1, iArr, 0);
            this.offScreenTexture = -1;
        }
        if (this.framebuffer > 0) {
            iArr[0] = this.framebuffer;
            GLES20.glDeleteFramebuffers(1, iArr, 0);
            this.framebuffer = -1;
        }
        if (this.depthBuffer > 0) {
            iArr[0] = this.depthBuffer;
            GLES20.glDeleteRenderbuffers(1, iArr, 0);
            this.depthBuffer = -1;
        }
    }

    public void bind() {
        GLES20.glViewport(0, 0, this.resolution.width(), this.resolution.height());
        GLES20.glBindFramebuffer(36160, this.framebuffer);
    }

    public void unbind() {
        GLES20.glBindFramebuffer(36160, 0);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }
}
