package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_AdapterViewItemSelectionEvent extends d {
    private final long id;
    private final int position;
    private final View selectedView;
    private final AdapterView<?> view;

    AutoValue_AdapterViewItemSelectionEvent(AdapterView<?> adapterView, View view2, int i, long j) {
        if (adapterView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = adapterView;
        if (view2 == null) {
            throw new NullPointerException("Null selectedView");
        }
        this.selectedView = view2;
        this.position = i;
        this.id = j;
    }

    @NonNull
    public AdapterView<?> view() {
        return this.view;
    }

    @NonNull
    public View selectedView() {
        return this.selectedView;
    }

    public int position() {
        return this.position;
    }

    public long id() {
        return this.id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdapterViewItemSelectionEvent{view=");
        sb.append(this.view);
        sb.append(", selectedView=");
        sb.append(this.selectedView);
        sb.append(", position=");
        sb.append(this.position);
        sb.append(", id=");
        sb.append(this.id);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (!this.view.equals(dVar.view()) || !this.selectedView.equals(dVar.selectedView()) || this.position != dVar.position() || this.id != dVar.id()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (int) (((long) ((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.selectedView.hashCode()) * 1000003) ^ this.position) * 1000003)) ^ ((this.id >>> 32) ^ this.id));
    }
}
