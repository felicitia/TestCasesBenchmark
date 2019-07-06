package org.m4m.domain.graphics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.m4m.domain.ISurfaceTexture;
import org.m4m.domain.Resolution;
import org.m4m.domain.pipeline.TriangleVerticesCalculator;

public class TextureRenderer {
    private IEglUtil eglUtil;
    private float[] mvpMatrix = new float[16];
    private Program programWithOES;
    private Program programWithSampler;
    private Resolution resolution;
    private float[] stMatrix = new float[16];
    private FloatBuffer triangleVertices;

    public enum FillMode {
        PreserveSize,
        PreserveAspectFit,
        PreserveAspectCrop
    }

    public TextureRenderer(IEglUtil iEglUtil) {
        this.eglUtil = iEglUtil;
        float[] defaultTriangleVerticesData = TriangleVerticesCalculator.getDefaultTriangleVerticesData();
        this.triangleVertices = ByteBuffer.allocateDirect(defaultTriangleVerticesData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        putTriangleVertices(defaultTriangleVerticesData);
        this.eglUtil.setIdentityMatrix(this.stMatrix, 0);
    }

    public void setInputSize(int i, int i2) {
        this.resolution = new Resolution(i, i2);
    }

    private void putTriangleVertices(float[] fArr) {
        this.triangleVertices.position(0);
        this.triangleVertices.put(fArr);
        this.triangleVertices.position(0);
    }

    public void drawFrame2D(float[] fArr, int i, float f, FillMode fillMode) {
        this.eglUtil.drawFrame(this.programWithSampler, this.triangleVertices, this.mvpMatrix, fArr, f, TextureType.GL_TEXTURE_2D, i, this.resolution, fillMode);
    }

    public void drawFrameOES(ISurfaceTexture iSurfaceTexture, int i, float f, FillMode fillMode) {
        this.stMatrix = iSurfaceTexture.getTransformMatrix();
        drawFrameOES(this.stMatrix, i, f, fillMode);
    }

    public void drawFrameOES(float[] fArr, int i, float f, FillMode fillMode) {
        this.eglUtil.drawFrame(this.programWithOES, this.triangleVertices, this.mvpMatrix, fArr, f, TextureType.GL_TEXTURE_EXTERNAL_OES, i, this.resolution, fillMode);
    }

    public void surfaceCreated() {
        this.programWithOES = this.eglUtil.createProgram("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        this.programWithSampler = this.eglUtil.createProgram("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition* vec4(1.0, -1.0, 1.0, 1.0);\n  vTextureCoord = ((uSTMatrix * aTextureCoord) * vec4(1.0, 1.0, 1.0, 1.0) ).xy;\n}\n", "precision mediump float;\nuniform sampler2D sTexture;\nvarying vec2 vTextureCoord;\nvoid main() {\n   gl_FragColor = texture2D(sTexture, vTextureCoord);\n}");
    }
}
