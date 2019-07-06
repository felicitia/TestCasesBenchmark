package com.jakewharton.rxbinding2.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MenuItem;

/* compiled from: MenuItemActionViewExpandEvent */
public abstract class c extends b {
    @CheckResult
    @NonNull
    public static c create(@NonNull MenuItem menuItem) {
        return new AutoValue_MenuItemActionViewExpandEvent(menuItem);
    }

    c() {
    }
}
