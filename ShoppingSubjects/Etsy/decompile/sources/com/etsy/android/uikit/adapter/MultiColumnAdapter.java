package com.etsy.android.uikit.adapter;

import android.support.annotation.CallSuper;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.BaseModel;
import java.util.ArrayList;
import java.util.Collection;

@Deprecated
public abstract class MultiColumnAdapter<T extends BaseModel> extends BaseModelArrayAdapter<T> {
    private static final String TAG = f.a(MultiColumnAdapter.class);
    protected a mCardRowGenerator;
    protected ArrayList<T> mResults = new ArrayList<>(0);

    /* access modifiers changed from: protected */
    public abstract void bindRow(int i, int i2, Object obj);

    public MultiColumnAdapter(FragmentActivity fragmentActivity, c cVar, int i, a aVar) {
        super(fragmentActivity, i, cVar);
        this.mCardRowGenerator = aVar;
    }

    public void addAll(Collection<? extends T> collection) {
        this.mResults.addAll(collection);
        super.addAll(collection);
    }

    public void notifyDataSetChanged() {
        this.mCardRowGenerator.b();
        super.notifyDataSetChanged();
    }

    @CallSuper
    public void refreshActivity(FragmentActivity fragmentActivity) {
        super.refreshActivity(fragmentActivity);
        this.mCardRowGenerator.a(fragmentActivity);
    }

    public int getRealCount() {
        return this.mResults.size();
    }

    public int getCount() {
        return (int) Math.ceil((double) (((float) this.mResults.size()) / ((float) this.mCardRowGenerator.a())));
    }

    public void remove(T t) {
        super.remove(t);
        this.mResults.remove(t);
    }

    public void add(T t) {
        super.add(t);
        this.mResults.add(t);
    }

    public void insert(T t, int i) {
        super.insert(t, i);
        this.mResults.add(i, t);
    }

    public void clear() {
        this.mResults.clear();
        notifyDataSetChanged();
    }

    public int getItemViewType(int i) {
        return super.getItemViewType(i) + (super.getViewTypeCount() * this.mCardRowGenerator.k());
    }

    public int getViewTypeCount() {
        return super.getViewTypeCount() * 2;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return getDefaultView(i, view);
    }

    /* access modifiers changed from: protected */
    public View getDefaultView(int i, View view) {
        int a = this.mCardRowGenerator.a();
        View a2 = this.mCardRowGenerator.a(view);
        bindRow(i, a, a2.getTag());
        return a2;
    }
}
