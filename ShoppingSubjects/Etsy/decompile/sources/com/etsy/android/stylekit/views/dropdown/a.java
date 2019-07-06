package com.etsy.android.stylekit.views.dropdown;

import android.view.View;

/* compiled from: DropdownViewHolder */
public abstract class a<T> {
    public View a;
    int b;

    public abstract void a(T t);

    public a(View view) {
        this.a = view;
        this.a.setTag(this);
    }

    public void a(int i) {
        this.b = i;
    }
}
