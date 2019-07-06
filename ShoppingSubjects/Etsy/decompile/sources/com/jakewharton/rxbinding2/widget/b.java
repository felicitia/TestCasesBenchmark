package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

/* compiled from: AdapterViewItemClickEvent */
public abstract class b {
    @NonNull
    public abstract View clickedView();

    public abstract long id();

    public abstract int position();

    @NonNull
    public abstract AdapterView<?> view();

    @CheckResult
    @NonNull
    public static b create(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        AutoValue_AdapterViewItemClickEvent autoValue_AdapterViewItemClickEvent = new AutoValue_AdapterViewItemClickEvent(adapterView, view, i, j);
        return autoValue_AdapterViewItemClickEvent;
    }

    b() {
    }
}
