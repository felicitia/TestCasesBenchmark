package com.onfido.android.sdk.capture.upload;

import kotlin.jvm.internal.Intrinsics;

public enum UploadErrorType {
    NETWORK("network"),
    DOCUMENT("document"),
    NO_FACE("no_face"),
    MULTIPLE_FACES("multiple_faces"),
    GLARE("glare"),
    GENERIC("generic");
    
    private final String b;

    protected UploadErrorType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        this.b = str;
    }

    public final String getKey() {
        return this.b;
    }
}
