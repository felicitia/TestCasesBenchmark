package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.BaseModel;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;

public abstract class BaseModelArrayAdapter<T extends BaseModel> extends ArrayAdapter<T> {
    private Reference<FragmentActivity> mActivity;
    private c mImageBatch;
    private int mLayoutId;

    public BaseModelArrayAdapter(FragmentActivity fragmentActivity, int i, c cVar) {
        super(fragmentActivity.getApplicationContext(), i);
        this.mActivity = new WeakReference(fragmentActivity);
        this.mLayoutId = i;
        this.mImageBatch = cVar;
    }

    public T getItem(int i) {
        return (BaseModel) super.getItem(i);
    }

    public void addAll(Collection<? extends T> collection) {
        if (VERSION.SDK_INT >= 11) {
            super.addAll(collection);
        } else if (collection != null) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                add((BaseModel) it.next());
            }
        }
    }

    public void addAll(T... tArr) {
        if (VERSION.SDK_INT >= 11) {
            super.addAll(tArr);
        } else if (tArr != null) {
            for (T add : tArr) {
                add(add);
            }
        }
    }

    public FragmentActivity getActivityContext() {
        return (FragmentActivity) this.mActivity.get();
    }

    public c getImageBatch() {
        return this.mImageBatch;
    }

    public int getLayoutId() {
        return this.mLayoutId;
    }

    public LayoutInflater getLayoutInflater() {
        return LayoutInflater.from((Context) this.mActivity.get());
    }

    public boolean isValidPosition(int i) {
        return i >= 0 && i < getCount() && getItem(i) != null;
    }

    @CallSuper
    public void refreshActivity(FragmentActivity fragmentActivity) {
        this.mActivity = new WeakReference(fragmentActivity);
    }

    public int getRealCount() {
        return getCount();
    }
}
