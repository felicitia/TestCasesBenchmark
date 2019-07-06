package com.onfido.android.sdk.capture.validation;

import kotlin.jvm.internal.Intrinsics;

public final class PostCaptureValidationResults {
    private final boolean a;
    private final Boolean b;

    public PostCaptureValidationResults(boolean z, Boolean bool) {
        this.a = z;
        this.b = bool;
    }

    public final boolean didBarcodeDetectionRun() {
        return this.b != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PostCaptureValidationResults) {
            PostCaptureValidationResults postCaptureValidationResults = (PostCaptureValidationResults) obj;
            return (this.a == postCaptureValidationResults.a) && Intrinsics.areEqual(this.b, postCaptureValidationResults.b);
        }
    }

    public final Boolean getHasBarcode() {
        return this.b;
    }

    public final boolean getHasBlur() {
        return this.a;
    }

    public final boolean hasIssue() {
        return this.a || Intrinsics.areEqual(this.b, Boolean.valueOf(false));
    }

    public int hashCode() {
        boolean z = this.a;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        Boolean bool = this.b;
        return i + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PostCaptureValidationResults(hasBlur=");
        sb.append(this.a);
        sb.append(", hasBarcode=");
        sb.append(this.b);
        sb.append(")");
        return sb.toString();
    }
}
