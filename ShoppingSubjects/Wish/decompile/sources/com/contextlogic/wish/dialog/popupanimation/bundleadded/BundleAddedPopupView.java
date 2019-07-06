package com.contextlogic.wish.dialog.popupanimation.bundleadded;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class BundleAddedPopupView extends LinearLayout {
    private final ArrayList<WishCartItem> mCartItems;

    public BundleAddedPopupView(Context context, ArrayList<WishCartItem> arrayList) {
        super(context);
        this.mCartItems = arrayList;
        setOrientation(1);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        setLayoutParams(new LayoutParams(-1, -1));
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.bundle_added_popup_view, this);
        ((BundleAddedImageView) findViewById(R.id.bundled_product_images)).setup(this.mCartItems);
        ((ThemedTextView) findViewById(R.id.bundle_added_message)).setText(context.getResources().getQuantityString(R.plurals.item_added_to_cart, arrayList.size()));
        initSavingsMessage(context);
    }

    private void initSavingsMessage(Context context) {
        WishLocalizedCurrencyValue wishLocalizedCurrencyValue = new WishLocalizedCurrencyValue(0.0d);
        for (int i = 0; i < this.mCartItems.size(); i++) {
            wishLocalizedCurrencyValue = wishLocalizedCurrencyValue.add(((WishCartItem) this.mCartItems.get(i)).getRetailPrice().subtract(((WishCartItem) this.mCartItems.get(i)).getPrice()));
        }
        if (wishLocalizedCurrencyValue.getValue() > 0.0d) {
            ((ThemedTextView) findViewById(R.id.your_savings_message)).setText(String.format(context.getString(R.string.your_savings), new Object[]{wishLocalizedCurrencyValue.toFormattedString()}));
        }
    }
}
