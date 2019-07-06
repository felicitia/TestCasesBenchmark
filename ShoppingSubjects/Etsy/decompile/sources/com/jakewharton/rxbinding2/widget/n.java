package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.widget.TextView;

/* compiled from: TextViewAfterTextChangeEvent */
public abstract class n {
    @Nullable
    public abstract Editable editable();

    @NonNull
    public abstract TextView view();

    @CheckResult
    @NonNull
    public static n create(@NonNull TextView textView, @Nullable Editable editable) {
        return new AutoValue_TextViewAfterTextChangeEvent(textView, editable);
    }

    n() {
    }
}
