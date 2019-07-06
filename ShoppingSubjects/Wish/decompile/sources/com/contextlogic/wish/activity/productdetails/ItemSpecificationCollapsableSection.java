package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.StringUtil;

public class ItemSpecificationCollapsableSection extends CollapsableSection {
    private View mColorHeader;
    private TextView mColorHeaderText;
    private TextView mColorText;
    private WishProduct mProduct;
    private TextView mSizeChartText;
    private TextView mSizeHeaderText;
    private TextView mSizeText;

    public ItemSpecificationCollapsableSection(Context context) {
        this(context, null);
    }

    public ItemSpecificationCollapsableSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void init(WishProduct wishProduct) {
        CharSequence charSequence;
        CharSequence charSequence2;
        CharSequence charSequence3;
        if (wishProduct != null) {
            this.mProduct = wishProduct;
            String str = null;
            final View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_details_item_specification, null);
            setTitle(WishApplication.getInstance().getString(R.string.collapsable_section_item_specification));
            addContent(inflate);
            this.mSizeHeaderText = (TextView) inflate.findViewById(R.id.product_details_fragment_overview_size_header_text);
            this.mSizeText = (TextView) inflate.findViewById(R.id.product_details_fragment_overview_size_text);
            this.mSizeChartText = (TextView) inflate.findViewById(R.id.product_details_fragment_overview_size_chart_view);
            this.mColorHeader = inflate.findViewById(R.id.product_details_fragment_overview_color_header);
            this.mColorHeaderText = (TextView) inflate.findViewById(R.id.product_details_fragment_overview_color_header_text);
            this.mColorText = (TextView) inflate.findViewById(R.id.product_details_fragment_overview_color_text);
            this.mSizeChartText.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ItemSpecificationCollapsableSection.this.clickSizingChart();
                }
            });
            Resources resources = getResources();
            if (wishProduct.getVariationSizes() != null && wishProduct.getVariationSizes().size() > 0) {
                charSequence2 = StringUtil.joinList(wishProduct.getVariationSizes(), ", ", false, true);
                charSequence = resources.getQuantityString(R.plurals.detail_table_size, wishProduct.getVariationSizes().size());
            } else if (wishProduct.getHiddenVariationSizes() == null || wishProduct.getHiddenVariationSizes().size() <= 0) {
                charSequence2 = null;
                charSequence = null;
            } else {
                charSequence2 = StringUtil.joinList(wishProduct.getHiddenVariationSizes(), ", ", false, true);
                charSequence = resources.getQuantityString(R.plurals.detail_table_size, wishProduct.getHiddenVariationSizes().size());
            }
            if (charSequence != null) {
                this.mSizeHeaderText.setText(charSequence);
                this.mSizeText.setText(charSequence2);
                if (wishProduct.getSizingChartUrl() != null) {
                    this.mSizeChartText.setText(WishApplication.getInstance().getString(R.string.sizing_chart));
                } else {
                    this.mSizeChartText.setVisibility(8);
                }
            } else {
                this.mSizeHeaderText.setVisibility(8);
                this.mSizeText.setVisibility(8);
                this.mSizeChartText.setVisibility(8);
                inflate.findViewById(R.id.product_details_fragment_overview_size_header).setVisibility(8);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mColorHeader.getLayoutParams();
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, 0, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            }
            if (this.mProduct.getVariationColors() != null && this.mProduct.getVariationColors().size() > 0) {
                str = StringUtil.joinList(this.mProduct.getVariationColors(), ", ", false, true);
                charSequence3 = resources.getQuantityString(R.plurals.detail_table_color, this.mProduct.getVariationColors().size());
            } else if (this.mProduct.getHiddenVariationColors() == null || this.mProduct.getHiddenVariationColors().size() <= 0) {
                charSequence3 = null;
            } else {
                str = StringUtil.joinList(this.mProduct.getHiddenVariationColors(), ", ", false, true);
                charSequence3 = resources.getQuantityString(R.plurals.detail_table_color, this.mProduct.getHiddenVariationColors().size());
            }
            if (charSequence3 != null) {
                this.mColorHeaderText.setText(charSequence3);
                this.mColorText.setText(str);
            } else {
                this.mColorHeader.setVisibility(8);
                this.mColorText.setVisibility(8);
            }
            openSection();
            inflate.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    inflate.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ItemSpecificationCollapsableSection.this.setTargetHeight(ItemSpecificationCollapsableSection.this.getHeight());
                    ItemSpecificationCollapsableSection.this.closeSection();
                }
            });
            this.mOpenClickSectionEvent = WishAnalyticsEvent.CLICK_MOBILE_PRODUCT_DETAILS_ITEM_SPECIFICATION_OPEN;
            this.mCloseClickSectionEvent = WishAnalyticsEvent.CLICK_MOBILE_PRODUCT_DETAILS_ITEM_SPECIFICATION_CLOSE;
            return;
        }
        setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void clickSizingChart() {
        if (this.mProduct != null && getContext() != null) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SIZING_CHART);
            String sizingChartUrl = this.mProduct.getSizingChartUrl();
            Intent intent = new Intent();
            intent.setClass(getContext(), WebViewActivity.class);
            intent.putExtra("ExtraUrl", sizingChartUrl);
            getContext().startActivity(intent);
        }
    }
}
