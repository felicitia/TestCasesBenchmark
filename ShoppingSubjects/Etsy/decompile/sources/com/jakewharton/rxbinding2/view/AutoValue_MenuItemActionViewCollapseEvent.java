package com.jakewharton.rxbinding2.view;

import android.support.annotation.NonNull;
import android.view.MenuItem;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_MenuItemActionViewCollapseEvent extends a {
    private final MenuItem menuItem;

    AutoValue_MenuItemActionViewCollapseEvent(MenuItem menuItem2) {
        if (menuItem2 == null) {
            throw new NullPointerException("Null menuItem");
        }
        this.menuItem = menuItem2;
    }

    @NonNull
    public MenuItem menuItem() {
        return this.menuItem;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MenuItemActionViewCollapseEvent{menuItem=");
        sb.append(this.menuItem);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        return this.menuItem.equals(((a) obj).menuItem());
    }

    public int hashCode() {
        return this.menuItem.hashCode() ^ 1000003;
    }
}
