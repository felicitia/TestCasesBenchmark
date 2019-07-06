package com.onfido.android.sdk.capture.ui.camera.face;

import kotlin.jvm.internal.Intrinsics;

public final class UnknownCameraException extends Exception {
    public UnknownCameraException(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        super(str);
    }
}
