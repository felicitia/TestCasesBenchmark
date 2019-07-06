package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;

public class ProductDetailsShippingCollapsibleSectionRow extends LinearLayout {
    private TextView mBodyText;
    private ImageView mExpressIcon;
    private TextView mPriceText;
    private TextView mTitleText;

    public ProductDetailsShippingCollapsibleSectionRow(Context context) {
        this(context, null);
    }

    public ProductDetailsShippingCollapsibleSectionRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.product_details_shipping_row, this, true);
        setLayoutParams(new LayoutParams(-1, -2));
        setOrientation(1);
        this.mTitleText = (TextView) inflate.findViewById(R.id.product_details_shipping_row_title);
        this.mPriceText = (TextView) inflate.findViewById(R.id.product_details_shipping_row_price_text);
        this.mBodyText = (TextView) inflate.findViewById(R.id.product_details_shipping_row_body_text);
        this.mExpressIcon = (ImageView) inflate.findViewById(R.id.product_details_shipping_row_express_icon);
    }

    public void setup(String str, String str2, String str3, boolean z) {
        this.mTitleText.setText(str);
        this.mPriceText.setText(str2);
        this.mBodyText.setText(str3);
        this.mExpressIcon.setVisibility(z ? 0 : 8);
    }

    public void hideShippingPrice() {
        this.mPriceText.setVisibility(8);
    }
}
