package com.etsy.android.ui.cart.viewholders;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.util.l;

public class CartGroupBottomDecoration extends ItemDecoration {
    int mCartSpacingPx = -1;

    public CartGroupBottomDecoration(Activity activity) {
        this.mCartSpacingPx = activity.getResources().getDimensionPixelOffset(R.dimen.msco_cart_group_spacing);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition != -1 && l.c(view) && childAdapterPosition == 0) {
            rect.top = this.mCartSpacingPx;
        }
    }
}
