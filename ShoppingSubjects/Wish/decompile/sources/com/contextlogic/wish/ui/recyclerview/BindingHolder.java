package com.contextlogic.wish.ui.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public class BindingHolder<B extends ViewDataBinding> extends ViewHolder {
    private B mBinding;

    public BindingHolder(View view) {
        super(view);
        this.mBinding = DataBindingUtil.bind(view);
    }

    public B getBinding() {
        return this.mBinding;
    }
}
