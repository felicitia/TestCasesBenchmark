package org.m4m.android;

import android.graphics.SurfaceTexture;
import org.m4m.domain.ISurfaceTexture;
import org.m4m.domain.Wrapper;

public class SurfaceTextureWrapper extends Wrapper<SurfaceTexture> implements ISurfaceTexture {
    public SurfaceTextureWrapper(SurfaceTexture surfaceTexture) {
        super(surfaceTexture);
    }

    public float[] getTransformMatrix() {
        float[] fArr = new float[16];
        ((SurfaceTexture) getNativeObject()).getTransformMatrix(fArr);
        return fArr;
    }
}
