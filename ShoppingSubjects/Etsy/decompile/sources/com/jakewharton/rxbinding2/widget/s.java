package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;

/* compiled from: TextViewTextChangeEvent */
public abstract class s {
    public abstract int before();

    public abstract int count();

    public abstract int start();

    @NonNull
    public abstract CharSequence text();

    @NonNull
    public abstract TextView view();

    @CheckResult
    @NonNull
    public static s create(@NonNull TextView textView, @NonNull CharSequence charSequence, int i, int i2, int i3) {
        AutoValue_TextViewTextChangeEvent autoValue_TextViewTextChangeEvent = new AutoValue_TextViewTextChangeEvent(textView, charSequence, i, i2, i3);
        return autoValue_TextViewTextChangeEvent;
    }

    s() {
    }
}
