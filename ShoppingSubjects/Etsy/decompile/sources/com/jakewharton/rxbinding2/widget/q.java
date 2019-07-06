package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;

/* compiled from: TextViewEditorActionEvent */
public abstract class q {
    public abstract int actionId();

    @Nullable
    public abstract KeyEvent keyEvent();

    @NonNull
    public abstract TextView view();

    @CheckResult
    @NonNull
    public static q create(@NonNull TextView textView, int i, @Nullable KeyEvent keyEvent) {
        return new AutoValue_TextViewEditorActionEvent(textView, i, keyEvent);
    }

    q() {
    }
}
