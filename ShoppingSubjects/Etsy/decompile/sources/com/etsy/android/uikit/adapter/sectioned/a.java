package com.etsy.android.uikit.adapter.sectioned;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.etsy.android.lib.logger.f;

/* compiled from: AbsSectionedRecyclerViewAdapter */
abstract class a<VH extends ViewHolder> extends Adapter<VH> {
    private static final String TAG = f.a(a.class);

    public abstract int getItemCountForSection(int i);

    public abstract int getItemViewType(@NonNull com.etsy.android.uikit.adapter.sectioned.b.a aVar, int i);

    public abstract int getSectionCount();

    public abstract void onBindViewHolder(@NonNull VH vh, @NonNull com.etsy.android.uikit.adapter.sectioned.b.a aVar, int i);

    a() {
    }

    public final int getItemCount() {
        int i = 0;
        for (int i2 = 0; i2 < getSectionCount(); i2++) {
            i += getItemCountForSection(i2);
        }
        return i;
    }

    @VisibleForTesting
    public final void onBindViewHolder(VH vh, int i) {
        onBindViewHolder(vh, getRelativePosition(i), i);
    }

    @VisibleForTesting
    public final int getItemViewType(int i) {
        return getItemViewType(getRelativePosition(i), i);
    }

    /* access modifiers changed from: protected */
    public final com.etsy.android.uikit.adapter.sectioned.b.a getRelativePosition(int i) {
        int sectionCount = getSectionCount();
        int i2 = 0;
        int i3 = 0;
        while (i2 < sectionCount) {
            int itemCountForSection = getItemCountForSection(i2);
            int i4 = i3 + itemCountForSection;
            int i5 = i - i3;
            if (i5 >= 0 && i5 < itemCountForSection) {
                return new com.etsy.android.uikit.adapter.sectioned.b.a(i2, i5);
            }
            i2++;
            i3 = i4;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append(": invalid position ");
        sb.append(i);
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: 0000 */
    public final int getAbsolutePosition(@NonNull com.etsy.android.uikit.adapter.sectioned.b.a aVar) {
        int sectionCount = getSectionCount();
        int i = aVar.a;
        if (i < 0 || i > sectionCount) {
            StringBuilder sb = new StringBuilder();
            sb.append(TAG);
            sb.append(": invalid section ");
            sb.append(i);
            throw new IllegalStateException(sb.toString());
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += getItemCountForSection(i3);
        }
        return i2 + aVar.b;
    }
}
