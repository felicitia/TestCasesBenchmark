package com.contextlogic.wish.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;

public class PoweredByWishView extends LinearLayout {
    public PoweredByWishView(Context context) {
        this(context, null);
    }

    public PoweredByWishView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PoweredByWishView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.powered_by_wish, this);
        setOrientation(0);
        if (WishApplication.getInstance().isWishApp()) {
            setVisibility(8);
        }
    }
}
