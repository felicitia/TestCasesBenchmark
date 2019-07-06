package com.etsy.android.stylekit.views.dropdown;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.Space;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

public class PromptDropdownAdapter<T> extends DropdownAdapter<T> {
    private static final int TYPE_PROMPT = 3;
    private c<T> factory;

    public PromptDropdownAdapter(@NonNull Spinner spinner, T[] tArr, @NonNull b<T> bVar, @NonNull c<T> cVar) {
        super(spinner, tArr, bVar);
        this.factory = cVar;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (i != 0) {
            return super.getDropDownView(i, view, viewGroup);
        }
        AnonymousClass1 r1 = new a<T>(new Space(getContext())) {
            public void a(T t) {
            }
        };
        r1.a(3);
        return r1.a;
    }

    @NonNull
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i != 0) {
            return super.getView(i, view, viewGroup);
        }
        a a = this.factory.a(this.inflater, viewGroup);
        a.a(3);
        return a.a;
    }

    @Nullable
    public T getItem(int i) {
        if (i == 0) {
            return super.getItem(i);
        }
        return super.getItem(i - 1);
    }

    public int getCount() {
        return super.getCount() + 1;
    }
}
