package com.contextlogic.wish.activity.cart.commerceloan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.items.CartItemsView;
import com.contextlogic.wish.api.model.WishCommerceLoanCart;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;

public class CommerceLoanCartItemView extends LinearLayout implements ImageRestorable {
    private CartItemsView mCartItemsView;
    private TextView mCommerceLoanText;
    private NetworkImageView mImageView;
    private TextView mRowTitle;
    private View mSectionContainer;
    private TextView mSectionTitle;

    public CommerceLoanCartItemView(Context context, CartContext cartContext, CartItemsView cartItemsView) {
        super(context);
        init(cartContext);
        this.mCartItemsView = cartItemsView;
    }

    private void init(CartContext cartContext) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.commerce_loan_cart_item_view, this, true);
        boolean z = !cartContext.isFreeOrder() && (cartContext.hasValidBillingInfo() || cartContext.getCheckoutActionManager().alwaysShowPaymentCredentials()) && cartContext.getCheckoutActionManager().canShowPaymentCredentials();
        int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        if (!z) {
            inflate.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize);
        } else {
            inflate.setPadding(0, 0, 0, dimensionPixelSize);
        }
        setOrientation(1);
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_image);
        this.mRowTitle = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_title);
        this.mCommerceLoanText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_commerce_loan_text);
        this.mSectionContainer = inflate.findViewById(R.id.cart_fragment_cart_items_header_container);
        this.mSectionTitle = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_header_title);
    }

    public void setItem(WishCommerceLoanCart wishCommerceLoanCart) {
        boolean isPayHalfLoan = wishCommerceLoanCart.isPayHalfLoan();
        this.mRowTitle.setText(isPayHalfLoan ? R.string.pay_half_later : R.string.buy_now_commerce_loan);
        this.mImageView.setVisibility(isPayHalfLoan ? 8 : 0);
        if (isPayHalfLoan) {
            this.mCartItemsView.disableEditingPaymentMethod();
        } else {
            this.mImageView.setImage(wishCommerceLoanCart.getImage());
        }
        if (!wishCommerceLoanCart.isDefaultLoan()) {
            this.mSectionTitle.setText(R.string.payment_due);
            this.mCommerceLoanText.setText(R.string.payment_due);
            return;
        }
        this.mCommerceLoanText.setText(R.string.commerce_loan_cart_subtitle);
    }

    public void releaseImages() {
        if (this.mImageView != null) {
            this.mImageView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mImageView != null) {
            this.mImageView.restoreImages();
        }
    }

    public void showHeader() {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.twelve_padding);
        this.mSectionTitle.setPadding(dimensionPixelSize, dimensionPixelSize2, 0, dimensionPixelSize2);
        this.mSectionContainer.setVisibility(0);
    }
}
