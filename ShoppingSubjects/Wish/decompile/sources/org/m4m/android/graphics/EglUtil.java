package org.m4m.android.graphics;

import android.opengl.GLES20;
import android.opengl.Matrix;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.m4m.domain.Resolution;
import org.m4m.domain.graphics.IEglUtil;
import org.m4m.domain.graphics.Program;
import org.m4m.domain.graphics.TextureRenderer.FillMode;
import org.m4m.domain.graphics.TextureType;
import org.m4m.domain.pipeline.TriangleVerticesCalculator;

public class EglUtil implements IEglUtil {
    private static final TriangleVerticesCalculator scaleCalculator = new TriangleVerticesCalculator();

    private static class EglUtilSingletonHolder {
        /* access modifiers changed from: private */
        public static final EglUtil INSTANCE = new EglUtil();
    }

    private EglUtil() {
    }

    public static EglUtil getInstance() {
        return EglUtilSingletonHolder.INSTANCE;
    }

    public Resolution getCurrentSurfaceResolution() {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        EGLSurface eglGetCurrentSurface = egl10.eglGetCurrentSurface(12378);
        int[] iArr = {0};
        egl10.eglQuerySurface(eglGetDisplay, eglGetCurrentSurface, 12375, iArr);
        int[] iArr2 = {0};
        egl10.eglQuerySurface(eglGetDisplay, eglGetCurrentSurface, 12374, iArr2);
        return new Resolution(iArr[0], iArr2[0]);
    }

    public Program createProgram(String str, String str2) {
        ShaderProgram createShaderProgram = createShaderProgram(str, str2);
        if (createShaderProgram.getProgramHandle() == 0) {
            throw new RuntimeException("Failed to create shader program.");
        }
        Program program = new Program();
        program.programHandle = createShaderProgram.getProgramHandle();
        program.positionHandle = createShaderProgram.getAttributeLocation("aPosition");
        program.textureHandle = createShaderProgram.getAttributeLocation("aTextureCoord");
        program.mvpMatrixHandle = createShaderProgram.getAttributeLocation("uMVPMatrix");
        program.stMatrixHandle = createShaderProgram.getAttributeLocation("uSTMatrix");
        return program;
    }

    public int createTexture(int i) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i2 = iArr[0];
        GLES20.glBindTexture(i, i2);
        checkEglError("glBindTexture mTextureID");
        GLES20.glTexParameterf(i, 10241, 9728.0f);
        GLES20.glTexParameterf(i, 10240, 9729.0f);
        GLES20.glTexParameteri(i, 10242, 33071);
        GLES20.glTexParameteri(i, 10243, 33071);
        checkEglError("glTexParameter");
        return i2;
    }

    public void drawFrameStart(Program program, FloatBuffer floatBuffer, float[] fArr, float[] fArr2, float f, TextureType textureType, int i, Resolution resolution, FillMode fillMode) {
        Resolution resolution2;
        int i2;
        int i3;
        Resolution currentSurfaceResolution;
        Program program2 = program;
        FloatBuffer floatBuffer2 = floatBuffer;
        float[] fArr3 = fArr;
        float f2 = f;
        checkEglError("onDrawFrame start");
        switch (fillMode) {
            case PreserveSize:
                resolution2 = resolution;
                break;
            case PreserveAspectFit:
            case PreserveAspectCrop:
                currentSurfaceResolution = getCurrentSurfaceResolution();
                break;
            default:
                currentSurfaceResolution = new Resolution(0, 0);
                break;
        }
        resolution2 = currentSurfaceResolution;
        GLES20.glViewport(0, 0, resolution2.width(), resolution2.height());
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16640);
        GLES20.glUseProgram(program2.programHandle);
        checkEglError("glUseProgram");
        GLES20.glActiveTexture(33984);
        checkEglError("glActiveTexture");
        if (textureType == TextureType.GL_TEXTURE_2D) {
            i3 = i;
            i2 = 3553;
        } else {
            i3 = i;
            i2 = 36197;
        }
        GLES20.glBindTexture(i2, i3);
        checkEglError("glBindTexture");
        floatBuffer2.position(0);
        FloatBuffer floatBuffer3 = floatBuffer2;
        GLES20.glVertexAttribPointer(program2.positionHandle, 3, 5126, false, 20, floatBuffer3);
        checkEglError("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(program2.positionHandle);
        checkEglError("glEnableVertexAttribArray maPositionHandle");
        floatBuffer2.position(3);
        GLES20.glVertexAttribPointer(program2.textureHandle, 3, 5126, false, 20, floatBuffer3);
        checkEglError("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(program2.textureHandle);
        checkEglError("glEnableVertexAttribArray maTextureHandle");
        Matrix.setIdentityM(fArr3, 0);
        switch (fillMode) {
            case PreserveSize:
                if (f2 == 90.0f || f2 == 270.0f) {
                    Matrix.rotateM(fArr3, 0, 180.0f, 0.0f, 0.0f, 1.0f);
                    break;
                }
            case PreserveAspectFit:
                float[] scale_PreserveAspectFit = scaleCalculator.getScale_PreserveAspectFit((int) f2, resolution.width(), resolution.height(), resolution2.width(), resolution2.height());
                Matrix.scaleM(fArr3, 0, scale_PreserveAspectFit[0], scale_PreserveAspectFit[1], 1.0f);
                Matrix.rotateM(fArr3, 0, -f2, 0.0f, 0.0f, 1.0f);
                break;
            case PreserveAspectCrop:
                float[] scale_PreserveAspectCrop = scaleCalculator.getScale_PreserveAspectCrop((int) f2, resolution.width(), resolution.height(), resolution2.width(), resolution2.height());
                Matrix.scaleM(fArr3, 0, scale_PreserveAspectCrop[0], scale_PreserveAspectCrop[1], 1.0f);
                Matrix.rotateM(fArr3, 0, -f2, 0.0f, 0.0f, 1.0f);
                break;
        }
        GLES20.glUniformMatrix4fv(program2.mvpMatrixHandle, 1, false, fArr3, 0);
        GLES20.glUniformMatrix4fv(program2.stMatrixHandle, 1, false, fArr2, 0);
    }

    public void drawFrameFinish() {
        GLES20.glDrawArrays(5, 0, 4);
        checkEglError("glDrawArrays");
        GLES20.glFinish();
    }

    public void drawFrame(Program program, FloatBuffer floatBuffer, float[] fArr, float[] fArr2, float f, TextureType textureType, int i, Resolution resolution, FillMode fillMode) {
        drawFrameStart(program, floatBuffer, fArr, fArr2, f, textureType, i, resolution, fillMode);
        drawFrameFinish();
    }

    private ShaderProgram createShaderProgram(String str, String str2) {
        ShaderProgram shaderProgram = new ShaderProgram(this);
        shaderProgram.create(str, str2);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(shaderProgram.getProgramHandle(), 35714, iArr, 0);
        if (iArr[0] != 1) {
            GLES20.glDeleteProgram(shaderProgram.getProgramHandle());
        }
        return shaderProgram;
    }

    public void checkEglError(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(": glError ");
            sb.append(glGetError);
            throw new RuntimeException(sb.toString());
        }
    }

    public void setIdentityMatrix(float[] fArr, int i) {
        Matrix.setIdentityM(fArr, 0);
    }
}
