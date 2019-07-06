package org.m4m.domain.pipeline;

public class TriangleVerticesCalculator {
    static int stride = 5;
    private final float[] defaultTriangleVerticesData = getDefaultTriangleVerticesData();
    private final float[] scale = {1.0f, 1.0f};
    private final float[] triangleVerticesData = getDefaultTriangleVerticesData();

    public float[] getScale_PreserveAspectFit(int i, int i2, int i3, int i4, int i5) {
        float[] fArr = this.scale;
        this.scale[1] = 1.0f;
        fArr[0] = 1.0f;
        if (i == 90 || i == 270) {
            int i6 = i3;
            i3 = i2;
            i2 = i6;
        }
        float f = ((float) i2) / ((float) i3);
        float f2 = (float) i4;
        float f3 = f2 / f;
        float f4 = (float) i5;
        if (f3 < f4) {
            this.scale[1] = f3 / f4;
        } else {
            this.scale[0] = (f4 * f) / f2;
        }
        return this.scale;
    }

    public float[] getScale_PreserveAspectCrop(int i, int i2, int i3, int i4, int i5) {
        float[] fArr = this.scale;
        this.scale[1] = 1.0f;
        fArr[0] = 1.0f;
        if (i == 90 || i == 270) {
            int i6 = i3;
            i3 = i2;
            i2 = i6;
        }
        float f = ((float) i2) / ((float) i3);
        float f2 = (float) i4;
        float f3 = (float) i5;
        if (f > f2 / f3) {
            this.scale[0] = (f3 * f) / f2;
        } else {
            this.scale[1] = (f2 / f) / f3;
        }
        return this.scale;
    }

    public static float[] getDefaultTriangleVerticesData() {
        return new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
    }
}
