package com.jakewharton.rxbinding2.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;

/* compiled from: MenuItemActionViewCollapseEvent */
public abstract class a extends b {
    @CheckResult
    @NonNull
    public static a create(@NonNull MenuItem menuItem) {
        return new AutoValue_MenuItemActionViewCollapseEvent(menuItem);
    }

    a() {
    }
}
