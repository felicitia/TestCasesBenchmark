package com.onfido.android.sdk.capture.errors;

import kotlin.jvm.internal.Intrinsics;

public final class OnfidoException extends Exception {
    public OnfidoException(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        super(str);
    }
}
