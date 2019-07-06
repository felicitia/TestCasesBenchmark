package com.etsy.android.uikit.adapter;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.util.CrashUtil;

public abstract class BaseCursorAdapter extends CursorAdapter {
    protected FragmentActivity mActivity;
    protected final c mImageBatch;
    protected LayoutInflater mLayoutInflater;
    protected int mLayoutResourceId;

    public BaseCursorAdapter(FragmentActivity fragmentActivity, c cVar) {
        super(fragmentActivity.getApplicationContext(), (Cursor) null, 0);
        this.mLayoutResourceId = 0;
        this.mActivity = fragmentActivity;
        this.mImageBatch = cVar;
        this.mLayoutInflater = LayoutInflater.from(fragmentActivity);
    }

    public BaseCursorAdapter(FragmentActivity fragmentActivity, int i, c cVar) {
        this(fragmentActivity, cVar);
        this.mLayoutResourceId = i;
    }

    public long getItemId(int i) {
        try {
            return super.getItemId(i);
        } catch (IllegalStateException e) {
            CrashUtil.a().a((Throwable) e);
            return 0;
        }
    }

    public FragmentActivity getActivityContext() {
        return this.mActivity;
    }

    public c getImageBatch() {
        return this.mImageBatch;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mLayoutInflater;
    }

    public int getLayoutId() {
        return this.mLayoutResourceId;
    }

    public void refreshContext(FragmentActivity fragmentActivity) {
        this.mActivity = fragmentActivity;
        this.mLayoutInflater = fragmentActivity.getLayoutInflater();
    }
}
