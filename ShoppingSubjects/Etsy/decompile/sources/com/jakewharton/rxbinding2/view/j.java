package com.jakewharton.rxbinding2.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

/* compiled from: ViewLayoutChangeEvent */
public abstract class j {
    public abstract int bottom();

    public abstract int left();

    public abstract int oldBottom();

    public abstract int oldLeft();

    public abstract int oldRight();

    public abstract int oldTop();

    public abstract int right();

    public abstract int top();

    @NonNull
    public abstract View view();

    @CheckResult
    @NonNull
    public static j create(@NonNull View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        AutoValue_ViewLayoutChangeEvent autoValue_ViewLayoutChangeEvent = new AutoValue_ViewLayoutChangeEvent(view, i, i2, i3, i4, i5, i6, i7, i8);
        return autoValue_ViewLayoutChangeEvent;
    }

    j() {
    }
}