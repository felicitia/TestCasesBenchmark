package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

/* compiled from: AdapterViewItemSelectionEvent */
public abstract class d extends f {
    public abstract long id();

    public abstract int position();

    @NonNull
    public abstract View selectedView();

    @CheckResult
    @NonNull
    public static f create(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        AutoValue_AdapterViewItemSelectionEvent autoValue_AdapterViewItemSelectionEvent = new AutoValue_AdapterViewItemSelectionEvent(adapterView, view, i, j);
        return autoValue_AdapterViewItemSelectionEvent;
    }

    d() {
    }
}
