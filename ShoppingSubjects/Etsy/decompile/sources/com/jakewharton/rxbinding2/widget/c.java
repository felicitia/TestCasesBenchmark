package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

/* compiled from: AdapterViewItemLongClickEvent */
public abstract class c {
    @NonNull
    public abstract View clickedView();

    public abstract long id();

    public abstract int position();

    @NonNull
    public abstract AdapterView<?> view();

    @CheckResult
    @NonNull
    public static c create(@NonNull AdapterView<?> adapterView, @NonNull View view, int i, long j) {
        AutoValue_AdapterViewItemLongClickEvent autoValue_AdapterViewItemLongClickEvent = new AutoValue_AdapterViewItemLongClickEvent(adapterView, view, i, j);
        return autoValue_AdapterViewItemLongClickEvent;
    }

    c() {
    }
}
