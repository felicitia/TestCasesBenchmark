package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.AbsListView;

/* compiled from: AbsListViewScrollEvent */
public abstract class a {
    public abstract int firstVisibleItem();

    public abstract int scrollState();

    public abstract int totalItemCount();

    @NonNull
    public abstract AbsListView view();

    public abstract int visibleItemCount();

    @CheckResult
    @NonNull
    public static a create(AbsListView absListView, int i, int i2, int i3, int i4) {
        AutoValue_AbsListViewScrollEvent autoValue_AbsListViewScrollEvent = new AutoValue_AbsListViewScrollEvent(absListView, i, i2, i3, i4);
        return autoValue_AbsListViewScrollEvent;
    }

    a() {
    }
}
