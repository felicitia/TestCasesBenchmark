package com.etsy.android.uikit.adapter.sectioned;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.etsy.android.lib.logger.f;
import com.etsy.android.uikit.adapter.sectioned.b;
import com.etsy.android.uikit.adapter.sectioned.b.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: SectionedRecyclerViewListAdapter */
abstract class d<ItemType, SectionType extends b<ItemType>, ViewHolderType extends ViewHolder> extends a<ViewHolderType> {
    private static final String TAG = f.a(d.class);
    protected final List<SectionType> mSections = new ArrayList();

    d() {
    }

    public int getSectionCount() {
        return this.mSections.size();
    }

    public int getItemCountForSection(int i) {
        return ((b) this.mSections.get(i)).b();
    }

    @NonNull
    public final ItemType getItem(@NonNull a aVar) {
        return ((b) this.mSections.get(aVar.a)).a(aVar.b);
    }

    @NonNull
    public final ItemType getItem(int i) {
        return getItem(getRelativePosition(i));
    }

    public final void addItem(@NonNull ItemType itemtype, @NonNull a aVar) {
        int absolutePosition = getAbsolutePosition(aVar);
        ((b) this.mSections.get(aVar.a)).a(aVar.b, itemtype);
        notifyItemInserted(absolutePosition);
    }

    public final void removeItem(@NonNull a aVar) {
        int absolutePosition = getAbsolutePosition(aVar);
        ((b) this.mSections.get(aVar.a)).b(aVar.b);
        notifyItemRemoved(absolutePosition);
    }

    public final void addItems(@NonNull Collection<ItemType> collection, @NonNull a aVar) {
        int absolutePosition = getAbsolutePosition(aVar);
        ((b) this.mSections.get(aVar.a)).a(aVar.b, collection);
        notifyItemRangeInserted(absolutePosition, collection.size());
    }

    public final void removeItems(@NonNull a aVar, int i) {
        int absolutePosition = getAbsolutePosition(aVar);
        ((b) this.mSections.get(aVar.a)).a(aVar.b, i);
        notifyItemRangeRemoved(absolutePosition, i);
    }

    public final void changeItem(@NonNull ItemType itemtype, @NonNull a aVar) {
        int absolutePosition = getAbsolutePosition(aVar);
        ((b) this.mSections.get(aVar.a)).b(aVar.b, itemtype);
        notifyItemChanged(absolutePosition);
    }

    public final void changeItems(@NonNull List<ItemType> list, @NonNull a aVar) {
        int absolutePosition = getAbsolutePosition(aVar);
        b bVar = (b) this.mSections.get(aVar.a);
        bVar.b(aVar.b, (Collection<ItemType>) list);
        notifyItemRangeChanged(absolutePosition, bVar.b());
    }

    public final void notifyChange(@NonNull a aVar, int i) {
        notifyItemRangeChanged(getAbsolutePosition(aVar), i);
    }

    public final void notifyChange(@NonNull a aVar) {
        notifyChange(aVar, 1);
    }

    public final void addSection(int i, @NonNull SectionType sectiontype) {
        String a = sectiontype.a();
        if (hasSection(a)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Section with id ");
            sb.append(a);
            sb.append(" already exists");
            throw new IllegalArgumentException(sb.toString());
        }
        int absolutePosition = getAbsolutePosition(new a(i, 0));
        this.mSections.add(i, sectiontype);
        notifyItemRangeInserted(absolutePosition, sectiontype.b());
    }

    public final void addSections(@NonNull Collection<SectionType> collection) {
        addSections(this.mSections.size(), collection);
    }

    public final void addSections(int i, @NonNull Collection<SectionType> collection) {
        int i2 = 0;
        int absolutePosition = getAbsolutePosition(new a(i, 0));
        for (SectionType b : collection) {
            i2 += b.b();
        }
        this.mSections.addAll(i, collection);
        notifyItemRangeInserted(absolutePosition, i2);
    }

    @Nullable
    public final SectionType removeSection(int i) {
        if (i < 0 || i >= this.mSections.size()) {
            return null;
        }
        int absolutePosition = getAbsolutePosition(new a(i, 0));
        SectionType sectiontype = (b) this.mSections.remove(i);
        notifyItemRangeRemoved(absolutePosition, sectiontype.b());
        return sectiontype;
    }

    @Nullable
    public final SectionType removeSectionWithId(@NonNull String str) {
        return removeSection(getSectionPositionForId(str));
    }

    public final void notifySectionChanged(int i) {
        notifyItemRangeChanged(getAbsolutePosition(new a(i, 0)), ((b) this.mSections.get(i)).b());
    }

    public final void moveItem(@NonNull a aVar, @NonNull a aVar2) {
        int absolutePosition = getAbsolutePosition(aVar);
        Object b = ((b) this.mSections.get(aVar.a)).b(aVar.b);
        if (b != null) {
            ((b) this.mSections.get(aVar2.a)).a(aVar2.b, b);
            notifyItemMoved(absolutePosition, getAbsolutePosition(aVar2));
        }
    }

    public final boolean hasSection(@NonNull String str) {
        int size = this.mSections.size();
        for (int i = 0; i < size; i++) {
            if (((b) this.mSections.get(i)).a().equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public final SectionType getSectionForId(@NonNull String str) {
        int size = this.mSections.size();
        for (int i = 0; i < size; i++) {
            SectionType sectiontype = (b) this.mSections.get(i);
            if (sectiontype.a().equals(str)) {
                return sectiontype;
            }
        }
        return null;
    }

    @NonNull
    public final SectionType getSection(int i) {
        return (b) this.mSections.get(i);
    }

    @NonNull
    public final a getPosition(@NonNull String str, int i) {
        int sectionPositionForId = getSectionPositionForId(str);
        if (sectionPositionForId >= 0) {
            return new a(sectionPositionForId, i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append(": invalid section id ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    public final int getSectionPositionForId(@NonNull String str) {
        int size = this.mSections.size();
        for (int i = 0; i < size; i++) {
            if (((b) this.mSections.get(i)).a().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public final void changeSections(@NonNull List<SectionType> list) {
        this.mSections.clear();
        this.mSections.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    public final List<ItemType> getFlattenedItems() {
        ArrayList arrayList = new ArrayList(getItemCount());
        int size = this.mSections.size();
        for (int i = 0; i < size; i++) {
            arrayList.addAll(((b) this.mSections.get(i)).c());
        }
        return arrayList;
    }
}
