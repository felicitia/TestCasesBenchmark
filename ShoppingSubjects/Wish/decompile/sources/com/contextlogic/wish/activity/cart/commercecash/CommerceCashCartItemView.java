package com.contextlogic.wish.activity.cart.commercecash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCommerceCashCart;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.util.StringUtil;

public class CommerceCashCartItemView extends RelativeLayout implements ImageRestorable {
    private TextView mCommerceCashText;
    private NetworkImageView mImageView;
    private TextView mPriceText;
    private TextView mRowTitle;

    public CommerceCashCartItemView(Context context, CartContext cartContext) {
        super(context);
        init(cartContext);
    }

    private void init(CartContext cartContext) {
        boolean z = true;
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.commerce_cash_cart_item_view, this, true);
        if (cartContext.isFreeOrder() || ((!cartContext.hasValidBillingInfo() && !cartContext.getCheckoutActionManager().alwaysShowPaymentCredentials()) || !cartContext.getCheckoutActionManager().canShowPaymentCredentials() || cartContext.isCommerceCashMissingValidShippingInfo())) {
            z = false;
        }
        if (!z) {
            inflate.setPadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding), 0);
        } else {
            inflate.setPadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding), 0, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding), 0);
        }
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_image);
        this.mRowTitle = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_title);
        this.mCommerceCashText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_commerce_cash_text);
        this.mPriceText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_your_price);
    }

    public void setItem(WishCommerceCashCart wishCommerceCashCart) {
        WishApplication.getInstance();
        String capitalize = StringUtil.capitalize(WishApplication.getAppType());
        this.mRowTitle.setText(WishApplication.getInstance().getString(R.string.commerce_cash, new Object[]{capitalize}));
        this.mCommerceCashText.setVisibility(0);
        this.mCommerceCashText.setText(wishCommerceCashCart.getBonusMessage());
        this.mPriceText.setText(wishCommerceCashCart.getAmount().toFormattedString(ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency(), false));
        this.mImageView.setImage(wishCommerceCashCart.getImage());
    }

    public void releaseImages() {
        this.mImageView.releaseImages();
    }

    public void restoreImages() {
        this.mImageView.restoreImages();
    }
}
