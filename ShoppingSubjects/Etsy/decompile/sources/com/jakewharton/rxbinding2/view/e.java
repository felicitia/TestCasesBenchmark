package com.jakewharton.rxbinding2.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

/* compiled from: ViewAttachDetachedEvent */
public abstract class e extends f {
    @CheckResult
    @NonNull
    public static e create(@NonNull View view) {
        return new AutoValue_ViewAttachDetachedEvent(view);
    }

    e() {
    }
}
