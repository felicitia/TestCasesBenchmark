package com.contextlogic.wish.activity.productdetails.bundles;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class BundlesView extends LinearLayout {
    private BaseActivity mBaseActivity;
    private ArrayList<WishProduct> mBundledProducts;
    private BundlesExtendPriceView mBundlesExtendPriceView;
    private BuyCallback mBuyCallback;
    private BundlesProductImageView mProductImageView;
    private BundlesProductInfoView mProductInfoView;
    private ArrayList<BundlesViewConnector> mProductSelectors;

    public interface BuyCallback {
        void onBuy(ArrayList<WishProduct> arrayList);
    }

    public BundlesView(Context context) {
        this(context, null);
    }

    public BundlesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
    }

    public void setup(ArrayList<WishProduct> arrayList, BaseActivity baseActivity, BuyCallback buyCallback) {
        this.mBuyCallback = buyCallback;
        this.mBaseActivity = baseActivity;
        this.mBundledProducts = arrayList;
        this.mProductSelectors = new ArrayList<>();
        int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.screen_padding);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setText(R.string.customers_also_bought_dot_dot_dot);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        themedTextView.setTextSize(1, ((float) ((int) WishApplication.getInstance().getResources().getDimension(R.dimen.text_size_subtitle))) / WishApplication.getInstance().getResources().getDisplayMetrics().density);
        themedTextView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
        themedTextView.setTypeface(1);
        themedTextView.setPadding(0, 0, 0, dimensionPixelSize);
        if (arrayList.size() > 0 && ((WishProduct) arrayList.get(0)).getBundledProductsHeaderText() != null) {
            themedTextView.setText(((WishProduct) arrayList.get(0)).getBundledProductsHeaderText());
        }
        addView(themedTextView);
        setLayoutParams(layoutParams);
        this.mProductImageView = new BundlesProductImageView(getContext());
        this.mProductImageView.setup(this.mBundledProducts);
        this.mProductSelectors.add(this.mProductImageView);
        addView(this.mProductImageView);
        this.mBundlesExtendPriceView = new BundlesExtendPriceView(getContext());
        this.mBundlesExtendPriceView.setup(arrayList, this);
        addView(this.mBundlesExtendPriceView);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
    }

    public void extendView() {
        removeView(this.mBundlesExtendPriceView);
        BundlesExtendedPriceView bundlesExtendedPriceView = new BundlesExtendedPriceView(getContext());
        bundlesExtendedPriceView.setup(this, this.mBundledProducts);
        this.mProductSelectors.add(bundlesExtendedPriceView);
        this.mProductInfoView = new BundlesProductInfoView(getContext());
        this.mProductInfoView.setup(this.mBundledProducts, getContext(), this.mProductSelectors);
        addView(this.mProductInfoView);
        addView(bundlesExtendedPriceView);
    }

    public void handleBuy() {
        this.mBuyCallback.onBuy(this.mProductInfoView.getSelectedProducts());
    }

    public void releaseImages() {
        this.mProductImageView.releaseImages();
    }

    public void restoreImages() {
        this.mProductImageView.restoreImages();
    }
}
