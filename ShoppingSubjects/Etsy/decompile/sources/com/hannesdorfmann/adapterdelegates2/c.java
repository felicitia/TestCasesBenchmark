package com.hannesdorfmann.adapterdelegates2;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

/* compiled from: AdapterDelegatesManager */
public class c<T> {
    SparseArrayCompat<b<T>> a = new SparseArrayCompat<>();
    private b<T> b;

    public c<T> a(int i, @NonNull b<T> bVar) {
        return a(i, false, bVar);
    }

    public c<T> a(int i, boolean z, @NonNull b<T> bVar) {
        if (bVar == null) {
            throw new NullPointerException("AdapterDelegate is null!");
        } else if (i == 2147483646) {
            throw new IllegalArgumentException("The view type = 2147483646 is reserved for fallback adapter delegate (see setFallbackDelegate() ). Please use another view type.");
        } else if (z || this.a.get(i) == null) {
            this.a.put(i, bVar);
            return this;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("An AdapterDelegate is already registered for the viewType = ");
            sb.append(i);
            sb.append(". Already registered AdapterDelegate is ");
            sb.append(this.a.get(i));
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public int a(@NonNull T t, int i) {
        if (t == null) {
            throw new NullPointerException("Items datasource is null!");
        }
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((b) this.a.valueAt(i2)).a(t, i)) {
                return this.a.keyAt(i2);
            }
        }
        if (this.b != null) {
            return 2147483646;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("No AdapterDelegate added that matches position=");
        sb.append(i);
        sb.append(" in data source");
        throw new IllegalArgumentException(sb.toString());
    }

    @NonNull
    public ViewHolder a(ViewGroup viewGroup, int i) {
        b<T> bVar = (b) this.a.get(i);
        if (bVar == null) {
            if (this.b == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("No AdapterDelegate added for ViewType ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
            bVar = this.b;
        }
        ViewHolder a2 = bVar.a(viewGroup);
        if (a2 != null) {
            return a2;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("ViewHolder returned from AdapterDelegate ");
        sb2.append(bVar);
        sb2.append(" for ViewType =");
        sb2.append(i);
        sb2.append(" is null!");
        throw new NullPointerException(sb2.toString());
    }

    public void a(@NonNull T t, int i, @NonNull ViewHolder viewHolder) {
        b<T> bVar = (b) this.a.get(viewHolder.getItemViewType());
        if (bVar == null) {
            if (this.b == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("No AdapterDelegate added for ViewType ");
                sb.append(viewHolder.getItemViewType());
                throw new NullPointerException(sb.toString());
            }
            bVar = this.b;
        }
        bVar.a(t, i, viewHolder);
    }
}
