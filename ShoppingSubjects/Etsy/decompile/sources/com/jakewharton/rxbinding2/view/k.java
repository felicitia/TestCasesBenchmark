package com.jakewharton.rxbinding2.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

/* compiled from: ViewScrollChangeEvent */
public abstract class k {
    public abstract int oldScrollX();

    public abstract int oldScrollY();

    public abstract int scrollX();

    public abstract int scrollY();

    @NonNull
    public abstract View view();

    @CheckResult
    @NonNull
    public static k create(@NonNull View view, int i, int i2, int i3, int i4) {
        AutoValue_ViewScrollChangeEvent autoValue_ViewScrollChangeEvent = new AutoValue_ViewScrollChangeEvent(view, i, i2, i3, i4);
        return autoValue_ViewScrollChangeEvent;
    }

    k() {
    }
}
