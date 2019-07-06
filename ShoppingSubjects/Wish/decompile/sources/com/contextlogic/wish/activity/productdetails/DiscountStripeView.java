package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishPromotionProductDetailsStripeSpec;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class DiscountStripeView extends FrameLayout {
    private ThemedTextView mText;

    public DiscountStripeView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mText = (ThemedTextView) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_details_discount_stripe_view, this).findViewById(R.id.product_details_discount_stripe_text);
    }

    public void setup(WishPromotionProductDetailsStripeSpec wishPromotionProductDetailsStripeSpec) {
        if (wishPromotionProductDetailsStripeSpec == null || wishPromotionProductDetailsStripeSpec.getProductDetailsText() == null || wishPromotionProductDetailsStripeSpec.getProductDetailsText().isEmpty()) {
            setVisibility(8);
        } else {
            this.mText.setText(wishPromotionProductDetailsStripeSpec.getProductDetailsText());
        }
    }
}
