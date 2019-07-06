package com.onfido.android.sdk.capture.ui.options;

import com.onfido.android.sdk.capture.ui.CaptureType;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class WelcomeScreenOptions extends BaseOptions {
    private final List<CaptureType> a;

    public WelcomeScreenOptions(List<? extends CaptureType> list) {
        Intrinsics.checkParameterIsNotNull(list, "flowSteps");
        this.a = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WelcomeScreenOptions) && !(Intrinsics.areEqual(this.a, ((WelcomeScreenOptions) obj).a) ^ true);
    }

    public final List<CaptureType> getFlowSteps() {
        return this.a;
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
