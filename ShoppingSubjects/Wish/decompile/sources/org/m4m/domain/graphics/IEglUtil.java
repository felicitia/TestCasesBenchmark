package org.m4m.domain.graphics;

import java.nio.FloatBuffer;
import org.m4m.domain.Resolution;
import org.m4m.domain.graphics.TextureRenderer.FillMode;

public interface IEglUtil {
    void checkEglError(String str);

    Program createProgram(String str, String str2);

    int createTexture(int i);

    void drawFrame(Program program, FloatBuffer floatBuffer, float[] fArr, float[] fArr2, float f, TextureType textureType, int i, Resolution resolution, FillMode fillMode);

    void setIdentityMatrix(float[] fArr, int i);
}
