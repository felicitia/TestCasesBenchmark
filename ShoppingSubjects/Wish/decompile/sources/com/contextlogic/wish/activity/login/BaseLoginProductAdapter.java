package com.contextlogic.wish.activity.login;

import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.grid.StaggeredGridView.Adapter;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.TabletUtil;

public abstract class BaseLoginProductAdapter extends Adapter {
    public int getColumnCount() {
        if (!ExperimentDataCenter.getInstance().shouldShowLoginRedesignProductFeed()) {
            return super.getColumnCount();
        }
        int i = 3;
        if (TabletUtil.isTablet()) {
            if (DisplayUtil.isLandscape()) {
                return 4;
            }
            return 3;
        } else if (DisplayUtil.isLandscape()) {
            return 3;
        } else {
            if (!ExperimentDataCenter.getInstance().shouldShowLoginRedesignProductFeed3Col()) {
                i = 2;
            }
            return i;
        }
    }

    /* access modifiers changed from: protected */
    public void scaleDownView(View view) {
        if (ExperimentDataCenter.getInstance().shouldShowLoginRedesignProductFeed() && !DisplayUtil.isLandscape() && !TabletUtil.isTablet() && ExperimentDataCenter.getInstance().shouldShowLoginRedesignProductFeed2Col()) {
            int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.product_grid_2col_padding);
            view.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        }
    }
}
