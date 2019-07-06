package com.etsy.android.uikit.adapter;

import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.core.img.c;

public abstract class BaseRecyclerViewAdapter<T> extends AbstractContextRecyclerViewAdapter<T, FragmentActivity> {
    protected BaseRecyclerViewAdapter(FragmentActivity fragmentActivity, c cVar) {
        super(fragmentActivity, cVar);
    }
}
