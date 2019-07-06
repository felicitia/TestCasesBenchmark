package com.contextlogic.wish.dialog.popupanimation.itemadded;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class ItemAddedPopupView extends LinearLayout {
    public ItemAddedPopupView(Context context, WishCartItem wishCartItem, int i, WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
        super(context);
        setOrientation(1);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        setLayoutParams(new LayoutParams(-1, -1));
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.item_added_popup_view, this);
        initItemAddedMessage(context, i);
        initSavingsMessage(context, wishCartItem, i, wishLocalizedCurrencyValue);
        initImage(wishCartItem);
        if (wishCartItem.getPromotionAddToCartSpec() != null && wishCartItem.getPromotionAddToCartSpec().getAddToCartText() != null) {
            initPromotionMessage(wishCartItem.getPromotionAddToCartSpec().getAddToCartText());
        }
    }

    private void initItemAddedMessage(Context context, int i) {
        ((TextView) findViewById(R.id.item_added_message)).setText(context.getResources().getQuantityString(R.plurals.item_added_to_cart, i));
    }

    private void initSavingsMessage(Context context, WishCartItem wishCartItem, int i, WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
        WishLocalizedCurrencyValue multiply = wishLocalizedCurrencyValue.subtract(wishCartItem.getPrice()).multiply((double) i);
        if (multiply.getValue() > 0.0d) {
            ThemedTextView themedTextView = (ThemedTextView) findViewById(R.id.you_saved_message);
            themedTextView.setVisibility(0);
            themedTextView.setText(String.format(context.getString(R.string.you_saved), new Object[]{multiply.toFormattedString()}));
        }
    }

    private void initImage(WishCartItem wishCartItem) {
        ((NetworkImageView) findViewById(R.id.product_image)).setImage(wishCartItem.getImage());
    }

    private void initPromotionMessage(String str) {
        ((LinearLayout) findViewById(R.id.promotion_message_container)).setVisibility(0);
        AutoReleasableImageView autoReleasableImageView = (AutoReleasableImageView) findViewById(R.id.offer_tag);
        ((ThemedTextView) findViewById(R.id.promotion_message)).setText(str);
    }
}
