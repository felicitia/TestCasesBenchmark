package com.contextlogic.wish.ui.recyclerview.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;
import com.contextlogic.wish.ui.recyclerview.BindingHolder;

public class BindingHolderCreator<B extends ViewDataBinding> implements ViewHolderCreator<BindingHolder<B>> {
    public BindingHolder<B> createViewHolder(View view) {
        return new BindingHolder<>(view);
    }
}
