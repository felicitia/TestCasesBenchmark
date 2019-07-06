package org.m4m.android.graphics;

import android.opengl.GLES20;
import java.util.HashMap;
import org.m4m.domain.graphics.IEglUtil;

public class ShaderProgram {
    private static int INVALID_VALUE = -1;
    private HashMap<String, Integer> attributes = new HashMap<>();
    private IEglUtil eglUtil;
    private int programHandle = INVALID_VALUE;

    public ShaderProgram(IEglUtil iEglUtil) {
        this.eglUtil = iEglUtil;
    }

    public void create(String str, String str2) {
        int i = INVALID_VALUE;
        if (str != null) {
            i = loadShader(35633, str);
        }
        if (i == 0) {
            this.programHandle = 0;
            return;
        }
        int i2 = INVALID_VALUE;
        if (str2 != null) {
            i2 = loadShader(35632, str2);
        }
        if (i2 == 0) {
            this.programHandle = 0;
            return;
        }
        this.programHandle = GLES20.glCreateProgram();
        this.eglUtil.checkEglError("glCreateProgram");
        GLES20.glAttachShader(this.programHandle, i);
        this.eglUtil.checkEglError("glAttachShader");
        GLES20.glAttachShader(this.programHandle, i2);
        this.eglUtil.checkEglError("glAttachShader");
        GLES20.glLinkProgram(this.programHandle);
    }

    private int loadShader(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        this.eglUtil.checkEglError("glCreateShader");
        GLES20.glShaderSource(glCreateShader, str);
        this.eglUtil.checkEglError("glShaderSource");
        GLES20.glCompileShader(glCreateShader);
        this.eglUtil.checkEglError("glCompileShader");
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        String glGetShaderInfoLog = GLES20.glGetShaderInfoLog(glCreateShader);
        GLES20.glDeleteShader(glCreateShader);
        StringBuilder sb = new StringBuilder();
        sb.append("Shader compilation failed with: ");
        sb.append(glGetShaderInfoLog);
        throw new IllegalArgumentException(sb.toString());
    }

    public int getAttributeLocation(String str) {
        if (this.attributes.containsKey(str)) {
            return ((Integer) this.attributes.get(str)).intValue();
        }
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.programHandle, str);
        IEglUtil iEglUtil = this.eglUtil;
        StringBuilder sb = new StringBuilder();
        sb.append("glGetAttribLocation ");
        sb.append(str);
        iEglUtil.checkEglError(sb.toString());
        if (glGetAttribLocation == -1) {
            glGetAttribLocation = GLES20.glGetUniformLocation(this.programHandle, str);
            IEglUtil iEglUtil2 = this.eglUtil;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("glGetUniformLocation ");
            sb2.append(str);
            iEglUtil2.checkEglError(sb2.toString());
        }
        if (glGetAttribLocation == -1) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Can't find a location for attribute ");
            sb3.append(str);
            throw new IllegalStateException(sb3.toString());
        }
        this.attributes.put(str, Integer.valueOf(glGetAttribLocation));
        return glGetAttribLocation;
    }

    public int getProgramHandle() {
        return this.programHandle;
    }
}
