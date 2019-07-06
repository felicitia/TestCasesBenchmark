package com.jakewharton.rxbinding2.widget;

import android.support.annotation.NonNull;
import android.widget.AbsListView;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_AbsListViewScrollEvent extends a {
    private final int firstVisibleItem;
    private final int scrollState;
    private final int totalItemCount;
    private final AbsListView view;
    private final int visibleItemCount;

    AutoValue_AbsListViewScrollEvent(AbsListView absListView, int i, int i2, int i3, int i4) {
        if (absListView == null) {
            throw new NullPointerException("Null view");
        }
        this.view = absListView;
        this.scrollState = i;
        this.firstVisibleItem = i2;
        this.visibleItemCount = i3;
        this.totalItemCount = i4;
    }

    @NonNull
    public AbsListView view() {
        return this.view;
    }

    public int scrollState() {
        return this.scrollState;
    }

    public int firstVisibleItem() {
        return this.firstVisibleItem;
    }

    public int visibleItemCount() {
        return this.visibleItemCount;
    }

    public int totalItemCount() {
        return this.totalItemCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AbsListViewScrollEvent{view=");
        sb.append(this.view);
        sb.append(", scrollState=");
        sb.append(this.scrollState);
        sb.append(", firstVisibleItem=");
        sb.append(this.firstVisibleItem);
        sb.append(", visibleItemCount=");
        sb.append(this.visibleItemCount);
        sb.append(", totalItemCount=");
        sb.append(this.totalItemCount);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (!(this.view.equals(aVar.view()) && this.scrollState == aVar.scrollState() && this.firstVisibleItem == aVar.firstVisibleItem() && this.visibleItemCount == aVar.visibleItemCount() && this.totalItemCount == aVar.totalItemCount())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.scrollState) * 1000003) ^ this.firstVisibleItem) * 1000003) ^ this.visibleItemCount) * 1000003) ^ this.totalItemCount;
    }
}
