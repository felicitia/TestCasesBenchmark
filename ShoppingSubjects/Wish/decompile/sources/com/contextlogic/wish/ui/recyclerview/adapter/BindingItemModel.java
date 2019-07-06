package com.contextlogic.wish.ui.recyclerview.adapter;

import android.databinding.ViewDataBinding;
import com.contextlogic.wish.ui.recyclerview.BindingHolder;

public abstract class BindingItemModel<B extends ViewDataBinding> implements ItemModel<BindingHolder<B>> {
    public ViewHolderCreator<BindingHolder<B>> getViewHolderCreator() {
        return new BindingHolderCreator();
    }
}
