package com.contextlogic.wish.activity.productdetails.bundles;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;
import java.util.Iterator;

public class BundlesProductInfoRowView extends LinearLayout {
    private CheckBox mCheckBox;
    private View mLayout;
    private ThemedTextView mListPriceView;
    private ThemedTextView mListPriceViewSwapped;
    private ThemedTextView mPriceView;
    private ThemedTextView mPriceViewSwapped;

    public BundlesProductInfoRowView(Context context) {
        this(context, null);
    }

    public BundlesProductInfoRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void init() {
        this.mLayout = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.bundles_product_info_row_view, this);
        this.mLayout.setLayoutParams(new LayoutParams(-1, -2));
        this.mPriceView = (ThemedTextView) this.mLayout.findViewById(R.id.bundles_product_info_row_view_product_your_price);
        this.mListPriceView = (ThemedTextView) this.mLayout.findViewById(R.id.bundles_product_info_row_view_product_retail_price);
        this.mPriceViewSwapped = (ThemedTextView) this.mLayout.findViewById(R.id.bundles_product_info_row_view_product_your_price_swapped);
        this.mListPriceViewSwapped = (ThemedTextView) this.mLayout.findViewById(R.id.bundles_product_info_row_view_product_retail_price_swapped);
        setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.screen_padding));
    }

    private void setPriceText(WishProduct wishProduct) {
        WishLocalizedCurrencyValue variationPrice = wishProduct.getVariationPrice(wishProduct.getDefaultCommerceVariationId());
        WishLocalizedCurrencyValue variationRetailPrice = wishProduct.getVariationRetailPrice(wishProduct.getDefaultCommerceVariationId());
        if (wishProduct.getPriceReplacementText() == null) {
            String formattedString = variationPrice.toFormattedString();
            this.mPriceView.setText(formattedString);
            this.mPriceViewSwapped.setText(formattedString);
        } else {
            String priceReplacementText = wishProduct.getPriceReplacementText();
            this.mPriceView.setText(priceReplacementText);
            this.mPriceViewSwapped.setText(priceReplacementText);
        }
        boolean z = true;
        if (wishProduct.getPriceReplacementSubText() != null) {
            String priceReplacementSubText = wishProduct.getPriceReplacementSubText();
            this.mListPriceView.setText(priceReplacementSubText);
            this.mListPriceViewSwapped.setText(priceReplacementSubText);
        } else if (variationPrice.getValue() < variationRetailPrice.getValue()) {
            String formattedString2 = variationRetailPrice.toFormattedString();
            this.mListPriceView.setText(formattedString2);
            this.mListPriceViewSwapped.setText(formattedString2);
            this.mListPriceView.setPaintFlags(this.mListPriceView.getPaintFlags() | 16);
            this.mListPriceViewSwapped.setPaintFlags(this.mListPriceViewSwapped.getPaintFlags() | 16);
        } else {
            z = false;
        }
        if (!ExperimentDataCenter.getInstance().shouldShowCrossedOutPriceToLeftInProductDetail() || !z) {
            this.mPriceViewSwapped.setVisibility(8);
            this.mListPriceViewSwapped.setVisibility(8);
            this.mPriceView.setVisibility(0);
            this.mListPriceView.setVisibility(0);
            return;
        }
        this.mPriceView.setVisibility(8);
        this.mListPriceView.setVisibility(8);
        this.mPriceViewSwapped.setVisibility(0);
        this.mListPriceViewSwapped.setVisibility(0);
    }

    public void setup(final WishProduct wishProduct, int i, ArrayList<BundlesViewConnector> arrayList) {
        this.mCheckBox = (CheckBox) this.mLayout.findViewById(R.id.bundles_product_info_row_view_checkbox);
        final ThemedTextView themedTextView = (ThemedTextView) this.mLayout.findViewById(R.id.bundles_product_info_row_view_product_name);
        themedTextView.setText(wishProduct.getName());
        AutoReleasableImageView autoReleasableImageView = (AutoReleasableImageView) this.mLayout.findViewById(R.id.bundles_product_info_row_view_right_arrow);
        autoReleasableImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUNDLES_PRODUCT_DETAILS, wishProduct.getProductId());
                Intent intent = new Intent();
                intent.setClass(BundlesProductInfoRowView.this.getContext(), ProductDetailsActivity.class);
                ProductDetailsActivity.addInitialProduct(intent, new WishProduct(wishProduct.getProductId()));
                BundlesProductInfoRowView.this.getContext().startActivity(intent);
            }
        });
        if (i == 0) {
            autoReleasableImageView.setVisibility(8);
            themedTextView.setTypeface(1);
        }
        setPriceText(wishProduct);
        CheckBox checkBox = this.mCheckBox;
        final WishProduct wishProduct2 = wishProduct;
        final ArrayList<BundlesViewConnector> arrayList2 = arrayList;
        final int i2 = i;
        AnonymousClass2 r1 = new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUNDLES_CHECK, wishProduct2.getProductId());
                } else {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUNDLES_UNCHECK, wishProduct2.getProductId());
                }
                if (arrayList2 != null) {
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        ((BundlesViewConnector) it.next()).handleCheckboxTapped(i2, z);
                        themedTextView.setTextColor(WishApplication.getInstance().getResources().getColor(z ? R.color.text_primary : R.color.text_hint));
                    }
                }
            }
        };
        checkBox.setOnCheckedChangeListener(r1);
    }

    public boolean isChecked() {
        return this.mCheckBox.isChecked();
    }
}
