package com.contextlogic.wish.dialog.cartabandon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCartAbandonOffer;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DisplayUtil;

public class CartAbandonOfferDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    /* access modifiers changed from: private */
    public WishCartAbandonOffer mCartAbandonOffer;

    public boolean isCancelable() {
        return false;
    }

    public static CartAbandonOfferDialogFragment<BaseActivity> createAbandonOfferDialog(CartContext cartContext) {
        WishCartAbandonOffer cartAbandonOffer = cartContext.getCart().getCartAbandonOffer();
        CartAbandonOfferDialogFragment<BaseActivity> cartAbandonOfferDialogFragment = new CartAbandonOfferDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentCartAbandonOffer", cartAbandonOffer);
        bundle.putString("ArgumentActionText", cartContext.getCheckoutActionManager().getCheckoutButtonContext().getCartAbandonModalActionText());
        cartAbandonOfferDialogFragment.setArguments(bundle);
        return cartAbandonOfferDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        this.mCartAbandonOffer = (WishCartAbandonOffer) arguments.getParcelable("ArgumentCartAbandonOffer");
        String string = arguments.getString("ArgumentActionText");
        boolean shouldShowUpdatedCartOfferModal = ExperimentDataCenter.getInstance().shouldShowUpdatedCartOfferModal();
        View inflate = layoutInflater.inflate(shouldShowUpdatedCartOfferModal ? R.layout.cart_abandon_offer_dialog_fragment_redesign : R.layout.cart_abandon_offer_dialog_fragment, viewGroup, false);
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.cart_abandon_offer_dialog_title);
        ThemedTextView themedTextView2 = (ThemedTextView) inflate.findViewById(R.id.cart_abandon_offer_dialog_message);
        ThemedTextView themedTextView3 = (ThemedTextView) inflate.findViewById(R.id.cart_abandon_offer_dialog_accept_offer);
        ThemedTextView themedTextView4 = (ThemedTextView) inflate.findViewById(R.id.cart_abandon_offer_dialog_dismiss_offer);
        if (!shouldShowUpdatedCartOfferModal) {
            ((NetworkImageView) inflate.findViewById(R.id.cart_abandon_offer_dialog_image)).setImage(new WishImage(this.mCartAbandonOffer.getImageUrl()));
        }
        themedTextView.setText(this.mCartAbandonOffer.getTitle());
        themedTextView2.setText(this.mCartAbandonOffer.getMessage());
        themedTextView3.setText(string);
        themedTextView3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartAbandonOfferDialogFragment.this.handleClaim();
            }
        });
        themedTextView4.setText(this.mCartAbandonOffer.getDismissText());
        themedTextView4.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartAbandonOfferDialogFragment.this.handleDismiss();
            }
        });
        return inflate;
    }

    public int getDialogWidth() {
        return (int) (getResources().getFraction(ExperimentDataCenter.getInstance().shouldShowUpdatedCartOfferModal() ? R.fraction.dialog_min_width_minor : R.fraction.dialog_min_width_major, 1, 1) * ((float) DisplayUtil.getDisplayWidth()));
    }

    /* access modifiers changed from: private */
    public void handleClaim() {
        showProgressSpinner();
        withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.claimCartAbandonOffer(CartAbandonOfferDialogFragment.this.mCartAbandonOffer.getOfferId());
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleDismiss() {
        showProgressSpinner();
        withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.dismissCartAbandonOffer(CartAbandonOfferDialogFragment.this.mCartAbandonOffer.getOfferId());
            }
        });
    }
}
