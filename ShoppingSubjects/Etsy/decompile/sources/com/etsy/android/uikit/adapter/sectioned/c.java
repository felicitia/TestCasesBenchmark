package com.etsy.android.uikit.adapter.sectioned;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Collection;
import java.util.List;

/* compiled from: RecyclerViewListSection */
public final class c<ItemType> implements b<ItemType> {
    private final List<ItemType> a;
    @NonNull
    private final String b;

    @NonNull
    public String a() {
        return this.b;
    }

    @NonNull
    public List<ItemType> c() {
        return this.a;
    }

    public void a(int i, @NonNull ItemType itemtype) {
        this.a.add(i, itemtype);
    }

    public int b() {
        return this.a.size();
    }

    public ItemType a(int i) {
        return this.a.get(i);
    }

    public void a(int i, @NonNull Collection<ItemType> collection) {
        this.a.addAll(i, collection);
    }

    @Nullable
    public ItemType b(int i) {
        return this.a.remove(i);
    }

    public void a(int i, int i2) {
        this.a.subList(i, i2 + i).clear();
    }

    public void b(int i, @NonNull ItemType itemtype) {
        this.a.set(i, itemtype);
    }

    public void b(int i, @NonNull Collection<ItemType> collection) {
        this.a.subList(i, collection.size() + i).clear();
        this.a.addAll(i, collection);
    }
}
