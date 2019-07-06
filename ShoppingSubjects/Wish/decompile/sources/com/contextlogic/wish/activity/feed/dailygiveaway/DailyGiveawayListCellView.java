package com.contextlogic.wish.activity.feed.dailygiveaway;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedView.DailyGiveawayState;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;

public class DailyGiveawayListCellView extends LinearLayout implements ImageRestorable, Recyclable {
    /* access modifiers changed from: private */
    public DailyGiveawayListAdapter mAdapter;
    /* access modifiers changed from: private */
    public BaseActivity mBaseActivity;
    private ThemedButton mClaimButton;
    /* access modifiers changed from: private */
    public ProductFeedFragment mFragment;
    /* access modifiers changed from: private */
    public WishProduct mItem;
    private NetworkImageView mProductImage;
    private ThemedTextView mProductName;
    private ThemedTextView mProductPrice;
    private ThemedTextView mProductStock;
    private RoundCornerProgressBar mProgressBar;
    private ThemedTextView mProgressBarText;
    /* access modifiers changed from: private */
    public DailyGiveawayState mState = this.mAdapter.getState();

    public DailyGiveawayListCellView(Context context, BaseActivity baseActivity, ProductFeedFragment productFeedFragment, DailyGiveawayListAdapter dailyGiveawayListAdapter) {
        super(context);
        this.mBaseActivity = baseActivity;
        this.mFragment = productFeedFragment;
        this.mAdapter = dailyGiveawayListAdapter;
        init();
    }

    public void init() {
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (DailyGiveawayListAdapter.shouldExpandTile(this.mState)) {
            view = layoutInflater.inflate(R.layout.daily_giveaway_list_cell_expanded_view, this);
            this.mClaimButton = (ThemedButton) view.findViewById(R.id.daily_giveaway_tile_product_claim_button);
        } else {
            view = layoutInflater.inflate(R.layout.daily_giveaway_list_cell_view, this);
            this.mProductStock = (ThemedTextView) view.findViewById(R.id.daily_giveaway_tile_product_available_count);
        }
        this.mProductImage = (NetworkImageView) view.findViewById(R.id.daily_giveaway_tile_product_image);
        this.mProductName = (ThemedTextView) view.findViewById(R.id.daily_giveaway_tile_product_name);
        this.mProductPrice = (ThemedTextView) view.findViewById(R.id.daily_giveaway_tile_product_original_price);
        this.mProgressBar = (RoundCornerProgressBar) view.findViewById(R.id.daily_giveaway_tile_progress_bar);
        this.mProgressBarText = (ThemedTextView) view.findViewById(R.id.daily_giveaway_tile_progress_bar_text);
        this.mProductPrice.setPaintFlags(this.mProductPrice.getPaintFlags() | 16);
    }

    public void refreshState(DailyGiveawayListAdapter dailyGiveawayListAdapter) {
        this.mAdapter = dailyGiveawayListAdapter;
        DailyGiveawayState dailyGiveawayState = this.mState;
        this.mState = this.mAdapter.getState();
        if (dailyGiveawayState != this.mState) {
            removeAllViews();
            init();
        }
    }

    public void setProduct(WishProduct wishProduct) {
        this.mItem = wishProduct;
        this.mProductImage.setImage(wishProduct.getImage());
        this.mProductName.setText(wishProduct.getName());
        if (wishProduct.getValue().getValue() > 0.0d) {
            this.mProductPrice.setText(wishProduct.getValue().toFormattedString());
            this.mProductPrice.setVisibility(0);
        } else {
            this.mProductPrice.setVisibility(8);
        }
        if (DailyGiveawayListAdapter.shouldExpandTile(this.mState)) {
            setProgress(wishProduct.getDailyGiveawayInventory(), wishProduct.getDailyGiveawayQuantity());
        } else {
            this.mProductStock.setText(WishApplication.getInstance().getResources().getQuantityString(R.plurals.number_available, wishProduct.getDailyGiveawayQuantity(), new Object[]{Integer.valueOf(wishProduct.getDailyGiveawayQuantity())}));
        }
        this.mProductImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_CURRENT_PRODUCT_DETAILS, DailyGiveawayListCellView.this.mAdapter.getExtraInfo());
                Intent intent = new Intent();
                intent.setClass(DailyGiveawayListCellView.this.mBaseActivity, ProductDetailsActivity.class);
                ProductDetailsActivity.addInitialProduct(intent, DailyGiveawayListCellView.this.mItem);
                if (DailyGiveawayListCellView.this.mState == DailyGiveawayState.ONGOING && DailyGiveawayListCellView.this.mItem.getDailyGiveawayInventory() > 0) {
                    intent.putExtra("ArgExtraSource", Source.DAILY_GIVEAWAY);
                }
                DailyGiveawayListCellView.this.mBaseActivity.startActivity(intent);
            }
        });
    }

    private void setProgress(int i, int i2) {
        float f = (float) i2;
        this.mProgressBar.setMax(f);
        float f2 = (float) i;
        this.mProgressBar.setProgress(f2);
        this.mProgressBarText.setText(WishApplication.getInstance().getResources().getString(R.string.fraction_available, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        if ((f2 / f > 0.2f || i <= 0) && (i != 1 || i2 <= 1)) {
            this.mProgressBar.setProgressColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            this.mProgressBarText.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.cool_gray1));
        } else {
            this.mProgressBar.setProgressColor(WishApplication.getInstance().getResources().getColor(R.color.daily_giveaway_progress_bar_red));
            this.mProgressBarText.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.daily_giveaway_progress_bar_red));
        }
        if (i == 0) {
            loadButton(true);
        } else {
            loadButton();
        }
    }

    public void releaseImages() {
        this.mProductImage.releaseImages();
    }

    public void restoreImages() {
        this.mProductImage.restoreImages();
    }

    public void recycle() {
        this.mProductImage.recycle();
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mProductImage.setImagePrefetcher(imageHttpPrefetcher);
    }

    private void loadButton() {
        loadButton(false);
    }

    private void loadButton(boolean z) {
        if (z || this.mState == DailyGiveawayState.ENDED) {
            this.mClaimButton.setBackgroundResource(R.drawable.daily_giveaway_claim_button_out_of_stock);
            this.mClaimButton.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.cool_gray3));
            this.mClaimButton.setText(WishApplication.getInstance().getResources().getString(R.string.none_left));
            this.mClaimButton.setClickable(false);
            return;
        }
        this.mClaimButton.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.white));
        this.mClaimButton.setText(WishApplication.getInstance().getResources().getString(R.string.claim));
        if (this.mState == DailyGiveawayState.ONGOING) {
            this.mClaimButton.setBackgroundResource(R.drawable.daily_giveaway_claim_button_active);
            this.mClaimButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    DailyGiveawayListCellView.this.mFragment.claimGiveaway(DailyGiveawayListCellView.this.mItem);
                }
            });
            this.mClaimButton.setClickable(true);
        } else if (this.mState == DailyGiveawayState.ALREADY_CLAIMED) {
            this.mClaimButton.setBackgroundResource(R.drawable.daily_giveaway_claim_blue_button_claimed);
            this.mClaimButton.setClickable(false);
        }
    }
}
