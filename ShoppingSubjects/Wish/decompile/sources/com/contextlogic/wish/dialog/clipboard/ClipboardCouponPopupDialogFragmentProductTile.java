package com.contextlogic.wish.dialog.clipboard;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class ClipboardCouponPopupDialogFragmentProductTile extends LinearLayout {
    private WishProduct mProduct;
    private LinearLayout mTextContainer;
    private int mTileImageSize;

    public ClipboardCouponPopupDialogFragmentProductTile(Context context, WishProduct wishProduct, int i) {
        super(context);
        this.mProduct = wishProduct;
        this.mTileImageSize = i;
        init(context);
    }

    private void init(Context context) {
        setOrientation(1);
        setGravity(1);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new LayoutParams(this.mTileImageSize, this.mTileImageSize));
        frameLayout.setBackgroundResource(R.color.white);
        frameLayout.setPadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.feed_tile_border), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.feed_tile_border), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.feed_tile_border), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.feed_tile_border));
        NetworkImageView networkImageView = new NetworkImageView(context);
        networkImageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        networkImageView.setImage(this.mProduct.getImage());
        frameLayout.addView(networkImageView);
        addView(frameLayout);
        this.mTextContainer = new LinearLayout(context);
        this.mTextContainer.setOrientation(0);
        this.mTextContainer.setGravity(1);
        if (!(this.mProduct == null || this.mProduct.getCommerceValue() == null)) {
            ThemedTextView themedTextView = new ThemedTextView(context);
            themedTextView.setText(this.mProduct.getCommerceValue().toFormattedString());
            themedTextView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
            themedTextView.setTypeface(1);
            themedTextView.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_title));
            if (this.mProduct.getValue() != null) {
                themedTextView.setPadding(0, 0, WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.two_padding), 0);
            }
            this.mTextContainer.addView(themedTextView);
        }
        if (!(this.mProduct == null || this.mProduct.getValue() == null || this.mProduct.getCommerceValue() == null || this.mProduct.getValue().getValue() <= this.mProduct.getCommerceValue().getValue())) {
            ThemedTextView themedTextView2 = new ThemedTextView(context);
            themedTextView2.setText(this.mProduct.getValue().toFormattedString());
            themedTextView2.setPaintFlags(themedTextView2.getPaintFlags() | 16);
            themedTextView2.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.cool_gray3));
            themedTextView2.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_subtitle));
            this.mTextContainer.addView(themedTextView2);
        }
        addView(this.mTextContainer);
    }

    public int getBackgroundPadding() {
        this.mTextContainer.measure(0, 0);
        return this.mTextContainer.getMeasuredHeight() + WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.twenty_four_padding);
    }
}
