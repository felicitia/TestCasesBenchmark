package com.contextlogic.wish.dialog.popupanimation.itemadded;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.crashlytics.android.Crashlytics;

public class ItemAddedToWishlistDialogFragment extends BaseDialogFragment {
    private NetworkImageView mIcon;
    private ThemedTextView mWishListName;

    /* access modifiers changed from: protected */
    public boolean shouldAnimateDown() {
        return true;
    }

    public static ItemAddedToWishlistDialogFragment createItemAddedToWishListDialogFragment(String str, WishImage wishImage) {
        ItemAddedToWishlistDialogFragment itemAddedToWishlistDialogFragment = new ItemAddedToWishlistDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ArgumentWishlistName", str);
        bundle.putParcelable("ArgumentWishImage", wishImage);
        itemAddedToWishlistDialogFragment.setArguments(bundle);
        return itemAddedToWishlistDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.wishlist_add_confirmation_popup, viewGroup, false);
        inflate.setBackground(new ColorDrawable(getContext().getResources().getColor(R.color.white)));
        this.mIcon = (NetworkImageView) inflate.findViewById(R.id.wishlist_confirmation_popup_icon);
        if (ExperimentDataCenter.getInstance().shouldSeeHeartAfterWishListAdd()) {
            this.mIcon.setImageResource(R.drawable.wishlist_confirmation_heart);
        } else if (ExperimentDataCenter.getInstance().shouldSeeItemAfterWishListAdd()) {
            WishImage wishImage = (WishImage) getArguments().getParcelable("ArgumentWishImage");
            if (wishImage != null) {
                this.mIcon.setImage(wishImage);
            } else {
                this.mIcon.setImageResource(R.drawable.wishlist_confirmation_heart);
            }
        }
        this.mWishListName = (ThemedTextView) inflate.findViewById(R.id.wishlist_confirmation_wishlist_name);
        String string = getArguments().getString("ArgumentWishlistName");
        if (string != null) {
            String string2 = getString(R.string.item_added_to, string);
            int lastIndexOf = string2.lastIndexOf(string);
            if (lastIndexOf != -1) {
                SpannableString spannableString = new SpannableString(string2);
                spannableString.setSpan(new StyleSpan(1), lastIndexOf, string.length() + lastIndexOf, 33);
                this.mWishListName.setText(spannableString);
            } else {
                this.mWishListName.setText(string2);
                StringBuilder sb = new StringBuilder();
                sb.append("wishlistName not found in dialog text, wishlist name: ");
                sb.append(string);
                sb.append(", dialog text: ");
                sb.append(string2);
                Crashlytics.logException(new Exception(sb.toString()));
            }
        }
        return inflate;
    }
}
