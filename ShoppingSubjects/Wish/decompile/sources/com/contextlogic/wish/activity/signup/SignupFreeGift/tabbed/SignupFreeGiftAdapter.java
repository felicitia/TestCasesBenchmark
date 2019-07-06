package com.contextlogic.wish.activity.signup.SignupFreeGift.tabbed;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftGridCellView;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView.Adapter;
import java.util.List;

public class SignupFreeGiftAdapter extends Adapter {
    private final Fragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private List<WishProduct> mProducts;

    public int getColumnCount() {
        return 2;
    }

    public SignupFreeGiftAdapter(Fragment fragment) {
        this.mFragment = fragment;
    }

    public int getItemHeight(int i, int i2) {
        if (!ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            return i2 + WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.free_gift_fragment_gift_cell_view_height_default);
        }
        if (ExperimentDataCenter.getInstance().shouldSeeNewBigClaimButton()) {
            return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.free_gift_grid_cell_height_redesign_big_button);
        }
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.free_gift_grid_cell_height_redesign);
    }

    public int getCount() {
        if (this.mProducts == null) {
            return 0;
        }
        return this.mProducts.size();
    }

    public WishProduct getItem(int i) {
        if (i < getCount()) {
            return (WishProduct) this.mProducts.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        SignupFreeGiftGridCellView signupFreeGiftGridCellView;
        if (view != null) {
            signupFreeGiftGridCellView = (SignupFreeGiftGridCellView) view;
        } else {
            signupFreeGiftGridCellView = new SignupFreeGiftGridCellView(this.mFragment.getContext());
            if (this.mImagePrefetcher != null) {
                signupFreeGiftGridCellView.setImagePrefetcher(this.mImagePrefetcher);
            }
        }
        signupFreeGiftGridCellView.setProduct(getItem(i));
        return signupFreeGiftGridCellView;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public void setItems(List<WishProduct> list) {
        this.mProducts = list;
        notifyDataSetChanged();
    }
}
