package com.onfido.android.sdk.capture.ui.camera;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

public final class PreviewFrameData {
    private final byte[] a;
    private final int b;
    private final int c;
    private final RectData d;
    private final RectData e;
    private final int f;

    public PreviewFrameData(byte[] bArr, int i, int i2, RectData rectData, RectData rectData2, int i3) {
        Intrinsics.checkParameterIsNotNull(bArr, "data");
        Intrinsics.checkParameterIsNotNull(rectData, "outerRect");
        Intrinsics.checkParameterIsNotNull(rectData2, "innerRect");
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.d = rectData;
        this.e = rectData2;
        this.f = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PreviewFrameData) {
            PreviewFrameData previewFrameData = (PreviewFrameData) obj;
            if (Intrinsics.areEqual(this.a, previewFrameData.a)) {
                if (this.b == previewFrameData.b) {
                    if ((this.c == previewFrameData.c) && Intrinsics.areEqual(this.d, previewFrameData.d) && Intrinsics.areEqual(this.e, previewFrameData.e)) {
                        if (this.f == previewFrameData.f) {
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

    public final RectData getInnerRect() {
        return this.e;
    }

    public final RectData getOuterRect() {
        return this.d;
    }

    public int hashCode() {
        byte[] bArr = this.a;
        int i = 0;
        int hashCode = (((((bArr != null ? Arrays.hashCode(bArr) : 0) * 31) + this.b) * 31) + this.c) * 31;
        RectData rectData = this.d;
        int hashCode2 = (hashCode + (rectData != null ? rectData.hashCode() : 0)) * 31;
        RectData rectData2 = this.e;
        if (rectData2 != null) {
            i = rectData2.hashCode();
        }
        return ((hashCode2 + i) * 31) + this.f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PreviewFrameData(data=");
        sb.append(Arrays.toString(this.a));
        sb.append(", frameWidth=");
        sb.append(this.b);
        sb.append(", frameHeight=");
        sb.append(this.c);
        sb.append(", outerRect=");
        sb.append(this.d);
        sb.append(", innerRect=");
        sb.append(this.e);
        sb.append(", rotation=");
        sb.append(this.f);
        sb.append(")");
        return sb.toString();
    }
}
