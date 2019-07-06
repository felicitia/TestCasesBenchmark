package com.onfido.android.sdk.capture.ui.camera;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

public final class CaptureFrameData {
    private final byte[] a;
    private final int b;
    private final int c;
    private final RectData d;
    private final int e;

    public CaptureFrameData(byte[] bArr, int i, int i2, RectData rectData, int i3) {
        Intrinsics.checkParameterIsNotNull(bArr, "data");
        Intrinsics.checkParameterIsNotNull(rectData, "outerRect");
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.d = rectData;
        this.e = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CaptureFrameData) {
            CaptureFrameData captureFrameData = (CaptureFrameData) obj;
            if (Intrinsics.areEqual(this.a, captureFrameData.a)) {
                if (this.b == captureFrameData.b) {
                    if ((this.c == captureFrameData.c) && Intrinsics.areEqual(this.d, captureFrameData.d)) {
                        if (this.e == captureFrameData.e) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final byte[] getData() {
        return this.a;
    }

    public final int getFrameHeight() {
        return this.c;
    }

    public final int getFrameWidth() {
        return this.b;
    }

    public final RectData getOuterRect() {
        return this.d;
    }

    public final int getRotation() {
        return this.e;
    }

    public int hashCode() {
        byte[] bArr = this.a;
        int i = 0;
        int hashCode = (((((bArr != null ? Arrays.hashCode(bArr) : 0) * 31) + this.b) * 31) + this.c) * 31;
        RectData rectData = this.d;
        if (rectData != null) {
            i = rectData.hashCode();
        }
        return ((hashCode + i) * 31) + this.e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CaptureFrameData(data=");
        sb.append(Arrays.toString(this.a));
        sb.append(", frameWidth=");
        sb.append(this.b);
        sb.append(", frameHeight=");
        sb.append(this.c);
        sb.append(", outerRect=");
        sb.append(this.d);
        sb.append(", rotation=");
        sb.append(this.e);
        sb.append(")");
        return sb.toString();
    }
}
