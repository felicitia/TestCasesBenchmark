package com.jakewharton.rxbinding2.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

/* compiled from: ViewAttachAttachedEvent */
public abstract class d extends f {
    @CheckResult
    @NonNull
    public static d create(@NonNull View view) {
        return new AutoValue_ViewAttachAttachedEvent(view);
    }

    d() {
    }
}
