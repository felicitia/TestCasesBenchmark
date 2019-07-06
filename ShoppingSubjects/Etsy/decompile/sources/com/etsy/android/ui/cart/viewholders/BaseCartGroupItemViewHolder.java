package com.etsy.android.ui.cart.viewholders;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public abstract class BaseCartGroupItemViewHolder extends BaseViewHolder<CartGroupItem> {
    protected final float ALPHA_FULL = 1.0f;
    protected final float ALPHA_HALF = 0.5f;
    private boolean mViewsEnabled = true;

    private class a implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }

        private a() {
        }
    }

    public abstract void bindCartGroupItem(CartGroupItem cartGroupItem);

    public BaseCartGroupItemViewHolder(View view) {
        super(view);
    }

    public final void bind(CartGroupItem cartGroupItem) {
        if (cartGroupItem.isEnabled()) {
            setItemEnabled(true);
        }
        bindCartGroupItem(cartGroupItem);
        if (!cartGroupItem.isEnabled()) {
            setItemEnabled(false);
        }
    }

    private void setItemEnabled(boolean z) {
        if (this.mViewsEnabled != z) {
            setViewsEnabled(this.itemView, z, 0);
            this.mViewsEnabled = z;
        }
    }

    private void setViewsEnabled(View view, boolean z, int i) {
        OnTouchListener onTouchListener = null;
        if (!z) {
            onTouchListener = new a();
        }
        view.setOnTouchListener(onTouchListener);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (i == 0) {
                    childAt.setAlpha(z ? 1.0f : 0.5f);
                }
                setViewsEnabled(childAt, z, i + 1);
            }
        }
    }
}
