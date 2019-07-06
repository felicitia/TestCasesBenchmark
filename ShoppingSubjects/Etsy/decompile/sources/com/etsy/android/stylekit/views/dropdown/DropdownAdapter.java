package com.etsy.android.stylekit.views.dropdown;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DropdownAdapter<T> extends ArrayAdapter<T> {
    private static final int TYPE_DEFAULT = 0;
    private static final int TYPE_DROPDOWN_ITEM = 1;
    private static final int TYPE_DROPDOWN_ITEM_SELECTED = 2;
    @NonNull
    private b<T> factory;
    protected LayoutInflater inflater;
    private T[] items;
    private int selectedPositionHint = -1;
    @NonNull
    protected Spinner spinner;

    public DropdownAdapter(@NonNull Spinner spinner2, T[] tArr, @NonNull b<T> bVar) {
        super(spinner2.getContext(), 17367043);
        this.inflater = LayoutInflater.from(spinner2.getContext());
        this.spinner = spinner2;
        this.items = tArr;
        this.factory = bVar;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        int itemViewType = getItemViewType(i);
        if (view == null) {
            aVar = getOptionsHolder(itemViewType, viewGroup);
        } else {
            aVar = (a) view.getTag();
            if (aVar.b != itemViewType) {
                aVar = getOptionsHolder(itemViewType, viewGroup);
            }
        }
        aVar.a(getItem(i));
        return aVar.a;
    }

    private a<T> getOptionsHolder(int i, ViewGroup viewGroup) {
        switch (i) {
            case 1:
                a<T> b = this.factory.b(this.inflater, viewGroup);
                b.a(i);
                return b;
            case 2:
                a<T> c = this.factory.c(this.inflater, viewGroup);
                c.a(i);
                return c;
            default:
                return this.factory.a(this.inflater, viewGroup);
        }
    }

    @NonNull
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = this.factory.a(this.inflater, viewGroup);
            aVar.a(0);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(getItem(i));
        return aVar.a;
    }

    public int getItemViewType(int i) {
        return this.spinner.getSelectedItemPosition() == i ? 2 : 1;
    }

    @Nullable
    public T getItem(int i) {
        return this.items[i];
    }

    public int getCount() {
        return this.items.length;
    }

    /* access modifiers changed from: 0000 */
    public void setSelectedPositionHint(int i) {
        this.selectedPositionHint = i;
    }

    /* access modifiers changed from: 0000 */
    public int getSelectedPositionHint() {
        return this.selectedPositionHint;
    }
}
