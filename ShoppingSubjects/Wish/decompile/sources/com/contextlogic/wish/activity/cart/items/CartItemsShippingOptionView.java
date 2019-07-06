package com.contextlogic.wish.activity.cart.items;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishShippingOption;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;

public class CartItemsShippingOptionView extends LinearLayout {
    private TextView mDateText;
    private TextView mNameAndPriceText;
    private AutoReleasableImageView mRadioButton;

    public CartItemsShippingOptionView(Context context) {
        this(context, null);
    }

    public CartItemsShippingOptionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cart_items_shipping_option_view, this);
        setOrientation(0);
        setGravity(17);
        setLayoutParams(new LayoutParams(-1, -2));
        this.mRadioButton = (AutoReleasableImageView) inflate.findViewById(R.id.cart_items_shipping_option_radio_button);
        this.mNameAndPriceText = (TextView) inflate.findViewById(R.id.cart_items_shipping_option_name_and_price_text);
        this.mDateText = (TextView) inflate.findViewById(R.id.cart_items_shipping_option_date_text);
    }

    public void setShippingOption(WishShippingOption wishShippingOption) {
        String name = wishShippingOption.getName();
        String formattedString = wishShippingOption.getPrice().getValue() > 0.0d ? wishShippingOption.getPrice().toFormattedString() : getContext().getString(R.string.free);
        boolean isExpressType = wishShippingOption.isExpressType();
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(": ");
        sb.append(formattedString);
        sb.append(isExpressType ? "   " : " ");
        SpannableString spannableString = new SpannableString(sb.toString());
        spannableString.setSpan(new StyleSpan(1), 0, name.length(), 34);
        if (isExpressType) {
            Drawable drawable = getResources().getDrawable(R.drawable.fast_shipping_icon);
            int lineHeight = this.mNameAndPriceText.getLineHeight();
            drawable.setBounds(0, 0, lineHeight, lineHeight);
            spannableString.setSpan(new ImageSpan(drawable), spannableString.length() - 2, spannableString.length() - 1, 33);
        }
        this.mNameAndPriceText.setText(spannableString);
        this.mDateText.setText(wishShippingOption.getShippingTimeString());
        this.mRadioButton.setImageResource(wishShippingOption.isSelected() ? R.drawable.radio_button_selected : R.drawable.radio_button);
    }
}
