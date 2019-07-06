package com.contextlogic.wish.activity.signup.SignupFreeGift;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.view.Recyclable;

public class SignupFreeGiftGridCellView extends LinearLayout implements ImageRestorable, Recyclable {
    private RelativeLayout mButtonLayoutV1;
    private RelativeLayout mButtonLayoutV2;
    private NetworkImageView mImageView;
    private TextView mPriceLayoutMainText;
    private TextView mPriceLayoutSubText;

    public SignupFreeGiftGridCellView(Context context) {
        super(context);
        init();
    }

    public void init() {
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            view = layoutInflater.inflate(R.layout.signup_free_gift_grid_cell_view_redesign, this);
        } else {
            view = layoutInflater.inflate(R.layout.signup_free_gift_grid_cell_view, this);
        }
        this.mImageView = (NetworkImageView) view.findViewById(R.id.fragment_signup_free_gift_grid_cell_view_image);
        this.mButtonLayoutV1 = (RelativeLayout) view.findViewById(R.id.fragment_signup_free_gift_grid_cell_view_price_layout_v1);
        this.mButtonLayoutV2 = (RelativeLayout) view.findViewById(R.id.fragment_signup_free_gift_grid_cell_view_price_layout_v2);
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            if (ExperimentDataCenter.getInstance().shouldSeeNewBigClaimButton()) {
                this.mButtonLayoutV1.setVisibility(8);
                this.mButtonLayoutV2.setVisibility(0);
            } else {
                this.mButtonLayoutV1.setVisibility(0);
                this.mButtonLayoutV2.setVisibility(8);
            }
        }
        this.mPriceLayoutMainText = (TextView) view.findViewById(getMainPriceTextId());
        this.mPriceLayoutSubText = (TextView) view.findViewById(getSubPriceTextId());
        this.mPriceLayoutSubText.setPaintFlags(this.mPriceLayoutSubText.getPaintFlags() | 16);
    }

    private int getMainPriceTextId() {
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            return ExperimentDataCenter.getInstance().shouldSeeNewBigClaimButton() ? R.id.fragment_signup_free_gift_grid_cell_view_price_main_text_v2 : R.id.fragment_signup_free_gift_grid_cell_view_price_main_text_v1;
        }
        return R.id.fragment_signup_free_gift_grid_cell_view_price_main_text;
    }

    private int getSubPriceTextId() {
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            return ExperimentDataCenter.getInstance().shouldSeeNewBigClaimButton() ? R.id.fragment_signup_free_gift_grid_cell_view_price_sub_text_v2 : R.id.fragment_signup_free_gift_grid_cell_view_price_sub_text_v1;
        }
        return R.id.fragment_signup_free_gift_grid_cell_view_price_sub_text;
    }

    private void setPrices(WishProduct wishProduct) {
        WishLocalizedCurrencyValue signupGiftPrice = wishProduct.getSignupGiftPrice();
        WishLocalizedCurrencyValue variationRetailPrice = wishProduct.getVariationRetailPrice(wishProduct.getCommerceDefaultVariationId());
        if (signupGiftPrice.getValue() > 0.0d) {
            this.mPriceLayoutMainText.setText(signupGiftPrice.toFormattedString());
        } else {
            this.mPriceLayoutMainText.setText(WishApplication.getInstance().getResources().getString(R.string.free));
        }
        if (variationRetailPrice.getValue() > signupGiftPrice.getValue()) {
            this.mPriceLayoutSubText.setVisibility(0);
            if (variationRetailPrice.getValue() > 0.0d) {
                this.mPriceLayoutSubText.setText(variationRetailPrice.toFormattedString());
            } else {
                this.mPriceLayoutSubText.setText(WishApplication.getInstance().getResources().getString(R.string.free));
            }
        } else {
            this.mPriceLayoutSubText.setVisibility(8);
        }
    }

    public void setProduct(WishProduct wishProduct) {
        setPrices(wishProduct);
        this.mImageView.setImage(wishProduct.getImage());
    }

    public void releaseImages() {
        this.mImageView.releaseImages();
    }

    public void restoreImages() {
        this.mImageView.restoreImages();
    }

    public void recycle() {
        this.mImageView.releaseImages();
        this.mImageView.setImage(null);
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImageView.setImagePrefetcher(imageHttpPrefetcher);
    }
}
