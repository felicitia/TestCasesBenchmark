package com.contextlogic.wish.activity.signup.SignupCategory;

import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.model.WishSignupCategory;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView.Adapter;

public class SignupCategoryAdapter<T extends BaseActivity, S extends UiFragment<T>> extends Adapter {
    private final SignupCategoryActivity mBaseActivity;
    private SignupCategoryFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;

    public int getColumnCount() {
        return 2;
    }

    public int getItemHeight(int i, int i2) {
        return i2;
    }

    public SignupCategoryAdapter(SignupCategoryActivity signupCategoryActivity, SignupCategoryFragment signupCategoryFragment) {
        this.mBaseActivity = signupCategoryActivity;
        this.mFragment = signupCategoryFragment;
    }

    public int getCount() {
        return this.mFragment.getSignupCategories().size();
    }

    public Object getItem(int i) {
        return this.mFragment.getSignupCategories().get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        SignupCategoryGridCellView signupCategoryGridCellView;
        if (view != null) {
            signupCategoryGridCellView = (SignupCategoryGridCellView) view;
        } else {
            signupCategoryGridCellView = new SignupCategoryGridCellView(this.mBaseActivity, this.mFragment);
            if (this.mImagePrefetcher != null) {
                signupCategoryGridCellView.setImagePrefetcher(this.mImagePrefetcher);
            }
        }
        signupCategoryGridCellView.setCategory((WishSignupCategory) this.mFragment.getSignupCategories().get(i));
        return signupCategoryGridCellView;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }
}
