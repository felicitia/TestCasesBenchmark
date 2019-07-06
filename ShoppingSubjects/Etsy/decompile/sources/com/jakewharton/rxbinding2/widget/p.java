package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;

/* compiled from: TextViewBeforeTextChangeEvent */
public abstract class p {
    public abstract int after();

    public abstract int count();

    public abstract int start();

    @NonNull
    public abstract CharSequence text();

    @NonNull
    public abstract TextView view();

    @CheckResult
    @NonNull
    public static p create(@NonNull TextView textView, @NonNull CharSequence charSequence, int i, int i2, int i3) {
        AutoValue_TextViewBeforeTextChangeEvent autoValue_TextViewBeforeTextChangeEvent = new AutoValue_TextViewBeforeTextChangeEvent(textView, charSequence, i, i2, i3);
        return autoValue_TextViewBeforeTextChangeEvent;
    }

    p() {
    }
}
