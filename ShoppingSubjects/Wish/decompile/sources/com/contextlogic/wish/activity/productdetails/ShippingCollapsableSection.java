package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.returnpolicy.ReturnPolicyActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishShippingOption;
import com.contextlogic.wish.application.WishApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShippingCollapsableSection extends CollapsableSection {
    private View mDividerView;
    private TextView mReturnPolicyDetails;
    private TextView mReturnPolicyText;
    private TextView mShippingMerchant;
    private LinearLayout mShippingRowContainer;
    private List<ProductDetailsShippingCollapsibleSectionRow> mShippingRowList;

    public ShippingCollapsableSection(Context context) {
        this(context, null);
    }

    public ShippingCollapsableSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void init(WishProduct wishProduct, View view) {
        String str;
        this.mDividerView = view;
        if (wishProduct != null) {
            final View inflate = LayoutInflater.from(getContext()).inflate(R.layout.product_details_shipping, null);
            setTitle(WishApplication.getInstance().getString(R.string.collapsable_section_shipping_information));
            addContent(inflate);
            boolean shouldShowShippingPriceRange = ExperimentDataCenter.getInstance().shouldShowShippingPriceRange();
            this.mShippingRowContainer = (LinearLayout) inflate.findViewById(R.id.product_details_shipping_row_container);
            this.mShippingRowList = new ArrayList();
            ArrayList<WishShippingOption> allShippingOptions = wishProduct.getAllShippingOptions();
            Map shippingOptionToPriceRanges = wishProduct.getShippingOptionToPriceRanges();
            for (WishShippingOption wishShippingOption : allShippingOptions) {
                ProductDetailsShippingCollapsibleSectionRow productDetailsShippingCollapsibleSectionRow = new ProductDetailsShippingCollapsibleSectionRow(getContext());
                String optionId = wishShippingOption.getOptionId();
                if (!shouldShowShippingPriceRange || shippingOptionToPriceRanges == null || shippingOptionToPriceRanges.get(optionId) == null || ((List) shippingOptionToPriceRanges.get(optionId)).isEmpty()) {
                    str = formatPrice(wishShippingOption.getPrice());
                } else {
                    str = formatPriceRange((List) shippingOptionToPriceRanges.get(optionId));
                }
                productDetailsShippingCollapsibleSectionRow.setup(wishShippingOption.getName(), str, wishShippingOption.getShippingTimeString(), wishShippingOption.isExpressType());
                this.mShippingRowContainer.addView(productDetailsShippingCollapsibleSectionRow);
                this.mShippingRowList.add(productDetailsShippingCollapsibleSectionRow);
            }
            this.mShippingMerchant = (TextView) inflate.findViewById(R.id.product_details_fragment_overview_shipping_merchant);
            this.mShippingMerchant.setText(getContext().getString(R.string.merchant_shipping, new Object[]{wishProduct.getMerchantInfoTitle()}));
            this.mReturnPolicyText = (TextView) inflate.findViewById(R.id.product_details_fragment_overview_return_policy_text);
            this.mReturnPolicyText.setText(wishProduct.getReturnPolicyShortString());
            this.mReturnPolicyDetails = (TextView) inflate.findViewById(R.id.product_details_fragment_overview_shipping_return_policy_details);
            if (wishProduct.getReturnPolicyShortString() != null) {
                this.mReturnPolicyDetails.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(ShippingCollapsableSection.this.getContext(), ReturnPolicyActivity.class);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_RETURN_POLICY_FROM_PRODUCT_DETAILS);
                        ShippingCollapsableSection.this.getContext().startActivity(intent);
                    }
                });
            } else {
                this.mReturnPolicyDetails.setVisibility(8);
            }
            openSection();
            inflate.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    inflate.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ShippingCollapsableSection.this.setTargetHeight(ShippingCollapsableSection.this.getHeight());
                    ShippingCollapsableSection.this.closeSection();
                }
            });
            this.mOpenClickSectionEvent = WishAnalyticsEvent.CLICK_MOBILE_PRODUCT_DETAILS_ITEM_SHIPPING_OPEN;
            this.mCloseClickSectionEvent = WishAnalyticsEvent.CLICK_MOBILE_PRODUCT_DETAILS_ITEM_SHIPPING_CLOSE;
            return;
        }
        setVisibility(8);
    }

    public void hideShippingPrices() {
        for (ProductDetailsShippingCollapsibleSectionRow hideShippingPrice : this.mShippingRowList) {
            hideShippingPrice.hideShippingPrice();
        }
    }

    private String formatPrice(WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
        if (wishLocalizedCurrencyValue.getValue() <= 0.0d) {
            return WishApplication.getInstance().getString(R.string.free);
        }
        return wishLocalizedCurrencyValue.toFormattedString(false, true);
    }

    private String formatPriceRange(List<WishShippingOption> list) {
        WishLocalizedCurrencyValue price = ((WishShippingOption) list.get(WishProduct.MIN_PRICE_RANGE_INDEX)).getPrice();
        WishLocalizedCurrencyValue price2 = ((WishShippingOption) list.get(WishProduct.MAX_PRICE_RANGE_INDEX)).getPrice();
        if (price.getValue() == price2.getValue()) {
            return formatPrice(price);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(formatPrice(price));
        sb.append(" - ");
        sb.append(formatPrice(price2));
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void openSection() {
        super.openSection();
        if (this.mDividerView != null) {
            this.mDividerView.setVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    public void closeSection() {
        super.closeSection();
        if (this.mDividerView != null) {
            this.mDividerView.setVisibility(0);
        }
    }
}
