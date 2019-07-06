package com.jakewharton.rxbinding2.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_ViewGroupHierarchyChildViewRemoveEvent extends i {
    private final View child;
    private final ViewGroup view;

    AutoValue_ViewGroupHierarchyChildViewRemoveEvent(ViewGroup viewGroup, View view2) {
        if (viewGroup == null) {
            throw new NullPointerException("Null view");
        }
        this.view = viewGroup;
        if (view2 == null) {
            throw new NullPointerException("Null child");
        }
        this.child = view2;
    }

    @NonNull
    public ViewGroup view() {
        return this.view;
    }

    @NonNull
    public View child() {
        return this.child;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ViewGroupHierarchyChildViewRemoveEvent{view=");
        sb.append(this.view);
        sb.append(", child=");
        sb.append(this.child);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        if (!this.view.equals(iVar.view()) || !this.child.equals(iVar.child())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((this.view.hashCode() ^ 1000003) * 1000003) ^ this.child.hashCode();
    }
}
