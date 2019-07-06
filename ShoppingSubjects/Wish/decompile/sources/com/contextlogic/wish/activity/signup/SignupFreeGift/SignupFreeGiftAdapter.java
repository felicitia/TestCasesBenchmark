package com.contextlogic.wish.activity.signup.SignupFreeGift;

import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView.Adapter;

public class SignupFreeGiftAdapter extends Adapter {
    private final SignupFreeGiftActivity mBaseActivity;
    private final SignupFreeGiftFragment mFragment;
    private SignupFreeGiftView mFreeGiftView;
    private ImageHttpPrefetcher mImagePrefetcher;

    public int getColumnCount() {
        return 2;
    }

    public SignupFreeGiftAdapter(SignupFreeGiftActivity signupFreeGiftActivity, SignupFreeGiftView signupFreeGiftView, SignupFreeGiftFragment signupFreeGiftFragment) {
        this.mBaseActivity = signupFreeGiftActivity;
        this.mFreeGiftView = signupFreeGiftView;
        this.mFragment = signupFreeGiftFragment;
    }

    public int getItemHeight(int i, int i2) {
        if (!ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            return i2 + this.mFreeGiftView.getFreeGiftFragment().getResources().getDimensionPixelSize(R.dimen.free_gift_fragment_gift_cell_view_height_default);
        }
        if (ExperimentDataCenter.getInstance().shouldSeeNewBigClaimButton()) {
            return this.mFreeGiftView.getFreeGiftFragment().getResources().getDimensionPixelSize(R.dimen.free_gift_grid_cell_height_redesign_big_button);
        }
        return this.mFreeGiftView.getFreeGiftFragment().getResources().getDimensionPixelSize(R.dimen.free_gift_grid_cell_height_redesign);
    }

    public int getCount() {
        return this.mFreeGiftView.getProducts().size();
    }

    public Object getItem(int i) {
        return this.mFreeGiftView.getProducts().get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        SignupFreeGiftGridCellView signupFreeGiftGridCellView;
        if (view != null) {
            signupFreeGiftGridCellView = (SignupFreeGiftGridCellView) view;
        } else {
            signupFreeGiftGridCellView = new SignupFreeGiftGridCellView(this.mBaseActivity);
            if (this.mImagePrefetcher != null) {
                signupFreeGiftGridCellView.setImagePrefetcher(this.mImagePrefetcher);
            }
        }
        signupFreeGiftGridCellView.setProduct((WishProduct) this.mFreeGiftView.getProducts().get(i));
        return signupFreeGiftGridCellView;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }
}
