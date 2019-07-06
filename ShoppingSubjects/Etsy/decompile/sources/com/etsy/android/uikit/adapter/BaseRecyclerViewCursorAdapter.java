package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class BaseRecyclerViewCursorAdapter<T extends ViewHolder> extends Adapter<T> {
    private Cursor mCursor;
    private final LayoutInflater mLayoutInflater;

    /* access modifiers changed from: protected */
    public abstract void bindViewHolder(T t, Cursor cursor);

    /* access modifiers changed from: protected */
    public abstract T createViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i);

    public BaseRecyclerViewCursorAdapter(Context context, Cursor cursor) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mCursor = cursor;
    }

    public T onCreateViewHolder(ViewGroup viewGroup, int i) {
        return createViewHolder(this.mLayoutInflater, viewGroup, i);
    }

    public void onBindViewHolder(T t, int i) {
        if (this.mCursor != null) {
            this.mCursor.moveToPosition(i);
            bindViewHolder(t, this.mCursor);
        }
    }

    public int getItemCount() {
        if (this.mCursor != null) {
            return this.mCursor.getCount();
        }
        return 0;
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    public void closeCursor() {
        if (this.mCursor != null) {
            this.mCursor.close();
        }
    }

    public void changeCursor(Cursor cursor) {
        if (cursor != this.mCursor) {
            closeCursor();
            this.mCursor = cursor;
            notifyDataSetChanged();
        }
    }
}
