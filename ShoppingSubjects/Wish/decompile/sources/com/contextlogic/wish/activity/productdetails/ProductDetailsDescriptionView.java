package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView.ScrollViewListener;

public class ProductDetailsDescriptionView extends ProductDetailsPagerView implements ScrollViewListener {
    private TextView mDescriptionText;
    private ObservableScrollView mScroller;

    public int getFirstItemPosition() {
        return 0;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.product_details_fragment_description;
    }

    public void refreshWishStates(boolean z) {
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public ProductDetailsDescriptionView(Context context) {
        super(context);
    }

    public void setup(WishProduct wishProduct, int i, ProductDetailsFragment productDetailsFragment) {
        super.setup(wishProduct, i, productDetailsFragment);
        this.mScroller = (ObservableScrollView) this.mRootLayout.findViewById(R.id.product_details_fragment_description_scroller);
        this.mScroller.setScrollViewListener(this);
        setupScroller(this.mScroller);
        this.mSpacerView = this.mRootLayout.findViewById(R.id.product_details_fragment_description_spacer_view);
        this.mSpacerView.setLayoutParams(new LayoutParams(-1, productDetailsFragment.getTabAreaSize()));
        this.mDescriptionText = (TextView) this.mRootLayout.findViewById(R.id.product_details_fragment_description_text);
        this.mDescriptionText.setText(wishProduct.getDescription());
    }

    public void cleanup() {
        releaseImages();
    }

    public int getCurrentScrollY() {
        if (this.mScroller != null) {
            return this.mScroller.getScrollY();
        }
        return 0;
    }

    public void onScrollChanged(int i, int i2) {
        handleScrollChanged(i, i2);
    }
}
