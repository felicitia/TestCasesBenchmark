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
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishShippingOption;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;

public class CartItemsShippingViewRedesigned extends LinearLayout {
    private AutoReleasableImageView mRadioButton;
    private TextView mText;

    public CartItemsShippingViewRedesigned(Context context) {
        this(context, null);
    }

    public CartItemsShippingViewRedesigned(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cart_items_shipping_option_view_redesigned, this);
        this.mRadioButton = (AutoReleasableImageView) inflate.findViewById(R.id.cart_items_shipping_option_radio_button);
        this.mText = (TextView) inflate.findViewById(R.id.cart_items_shipping_option_text);
    }

    public void setShippingOption(WishShippingOption wishShippingOption) {
        StringBuilder sb = new StringBuilder();
        sb.append(wishShippingOption.getName());
        sb.append(": ");
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(wishShippingOption.getShippingTimeString());
        sb3.append(" ");
        String sb4 = sb3.toString();
        String formattedString = wishShippingOption.getPrice().getValue() > 0.0d ? wishShippingOption.getPrice().toFormattedString() : getContext().getString(R.string.free);
        boolean isExpressType = wishShippingOption.isExpressType();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(sb2);
        sb5.append(sb4);
        sb5.append(formattedString);
        sb5.append(isExpressType ? "   " : " ");
        SpannableString spannableString = new SpannableString(sb5.toString());
        spannableString.setSpan(new StyleSpan(1), 0, sb2.length(), 34);
        if (isExpressType) {
            Drawable drawable = getResources().getDrawable(R.drawable.fast_shipping_icon);
            int lineHeight = this.mText.getLineHeight();
            drawable.setBounds(0, 0, lineHeight, lineHeight);
            spannableString.setSpan(new ImageSpan(drawable), spannableString.length() - 2, spannableString.length() - 1, 33);
        }
        this.mText.setText(spannableString);
        this.mRadioButton.setImageResource(wishShippingOption.isSelected() ? R.drawable.cart_notices_redesign_radio_button_selected : R.drawable.cart_notices_redesigned_radio_button_not_selected);
    }
}
