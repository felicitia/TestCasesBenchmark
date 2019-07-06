package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.edge_detector.EdgeDetectionResults;
import kotlin.jvm.internal.Intrinsics;

public final class ImageProcessingResults {
    private final boolean a;
    private final EdgeDetectionResults b;
    private final boolean c;

    public ImageProcessingResults(boolean z, EdgeDetectionResults edgeDetectionResults, boolean z2) {
        Intrinsics.checkParameterIsNotNull(edgeDetectionResults, "edgeDetectionResults");
        this.a = z;
        this.b = edgeDetectionResults;
        this.c = z2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageProcessingResults)) {
            return false;
        }
        ImageProcessingResults imageProcessingResults = (ImageProcessingResults) obj;
        return this.a == imageProcessingResults.a && !(Intrinsics.areEqual(this.b, imageProcessingResults.b) ^ true) && this.c == imageProcessingResults.c;
    }

    public final EdgeDetectionResults getEdgeDetectionResults() {
        return this.b;
    }

    public final boolean getHasBlur() {
        return this.c;
    }

    public final boolean getHasGlare() {
        return this.a;
    }

    public int hashCode() {
        return (((Boolean.valueOf(this.a).hashCode() * 31) + this.b.hashCode()) * 31) + Boolean.valueOf(this.c).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ImageProcessingResults(hasGlare=");
        sb.append(this.a);
        sb.append(", edgeDetectionResults=");
        sb.append(this.b);
        sb.append(", hasBlur=");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }
}
