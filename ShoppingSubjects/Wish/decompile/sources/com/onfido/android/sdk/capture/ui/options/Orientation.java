package com.onfido.android.sdk.capture.ui.options;

import kotlin.jvm.internal.Intrinsics;

public enum Orientation {
    PORTRAIT,
    LANDSCAPE;

    public final boolean isPortrait() {
        return Intrinsics.areEqual(this, PORTRAIT);
    }
}
