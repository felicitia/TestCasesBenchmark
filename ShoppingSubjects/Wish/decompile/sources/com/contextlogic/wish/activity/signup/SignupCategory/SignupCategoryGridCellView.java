package com.contextlogic.wish.activity.signup.SignupCategory;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishImage.ImageSize;
import com.contextlogic.wish.api.model.WishSignupCategory;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.view.Recyclable;
import java.util.ArrayList;
import java.util.Iterator;

public class SignupCategoryGridCellView extends LinearLayout implements ImageRestorable, Recyclable {
    private WishSignupCategory mCategory;
    private SignupCategoryCellFragment mFragment;
    private NetworkImageView mImageView1;
    private NetworkImageView mImageView2;
    private NetworkImageView mImageView3;
    private NetworkImageView mImageView4;
    private View mWishBackView;
    private AutoReleasableImageView mWishCheckImage;
    private ProgressBar mWishLoadingSpinner;

    public interface SignupCategoryCellFragment {
        boolean isWishPending(String str, SignupCategoryGridCellView signupCategoryGridCellView);
    }

    public SignupCategoryGridCellView(SignupCategoryActivity signupCategoryActivity, SignupCategoryFragment signupCategoryFragment) {
        super(signupCategoryActivity);
        init();
        this.mFragment = signupCategoryFragment;
    }

    public void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.signup_category_grid_cell_view, this);
        this.mWishLoadingSpinner = (ProgressBar) inflate.findViewById(R.id.signup_category_grid_cell_wish_button_loading_spinner);
        this.mWishCheckImage = (AutoReleasableImageView) inflate.findViewById(R.id.signup_category_grid_cell_check_image);
        this.mWishCheckImage.setImageResource(R.drawable.ic_check_dark);
        this.mImageView1 = (NetworkImageView) inflate.findViewById(R.id.signup_category_grid_cell_image1);
        this.mImageView2 = (NetworkImageView) inflate.findViewById(R.id.signup_category_grid_cell_image2);
        this.mImageView3 = (NetworkImageView) inflate.findViewById(R.id.signup_category_grid_cell_image3);
        this.mImageView4 = (NetworkImageView) inflate.findViewById(R.id.signup_category_grid_cell_image4);
        this.mWishBackView = inflate.findViewById(R.id.signup_category_grid_cell_wish_view);
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImageView1.setImagePrefetcher(imageHttpPrefetcher);
        this.mImageView2.setImagePrefetcher(imageHttpPrefetcher);
        this.mImageView3.setImagePrefetcher(imageHttpPrefetcher);
        this.mImageView4.setImagePrefetcher(imageHttpPrefetcher);
    }

    public void refreshWishState(boolean z) {
        if (this.mFragment != null && this.mCategory != null) {
            if (z) {
                this.mWishCheckImage.setVisibility(8);
                this.mWishLoadingSpinner.setVisibility(0);
                this.mWishBackView.setVisibility(0);
            } else if (this.mCategory.isAlreadyWishing()) {
                this.mWishLoadingSpinner.setVisibility(8);
                this.mWishCheckImage.setVisibility(0);
                this.mWishBackView.setVisibility(0);
            } else {
                this.mWishBackView.setVisibility(8);
            }
        }
    }

    public void refreshWishState() {
        if (this.mFragment != null && this.mCategory != null) {
            refreshWishState(this.mFragment.isWishPending(this.mCategory.getId(), this));
        }
    }

    public NetworkImageView getImageView() {
        return this.mImageView1;
    }

    public void setCategory(WishSignupCategory wishSignupCategory) {
        this.mCategory = wishSignupCategory;
        refreshWishState();
        ArrayList arrayList = new ArrayList();
        Iterator it = this.mCategory.getPreviews().iterator();
        while (it.hasNext()) {
            arrayList.add(((WishImage) it.next()).getUrlString(ImageSize.MEDIUM));
        }
        this.mImageView1.setImage(new WishImage((String) arrayList.get(0)));
        this.mImageView2.setImage(new WishImage((String) arrayList.get(1)));
        this.mImageView3.setImage(new WishImage((String) arrayList.get(2)));
        this.mImageView4.setImage(new WishImage((String) arrayList.get(3)));
    }

    public void releaseImages() {
        this.mImageView1.releaseImages();
        this.mImageView2.releaseImages();
        this.mImageView3.releaseImages();
        this.mImageView4.releaseImages();
    }

    public void restoreImages() {
        this.mImageView1.restoreImages();
        this.mImageView2.restoreImages();
        this.mImageView3.restoreImages();
        this.mImageView4.restoreImages();
    }

    public void recycle() {
        this.mImageView1.releaseImages();
        this.mImageView1.setImage(null);
        this.mImageView2.releaseImages();
        this.mImageView2.setImage(null);
        this.mImageView3.releaseImages();
        this.mImageView3.setImage(null);
        this.mImageView4.releaseImages();
        this.mImageView4.setImage(null);
    }
}
