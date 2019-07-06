package com.contextlogic.wish.activity.pricewatch;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishPriceWatchSpec.PriceWatchItem;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class PriceWatchItemView extends LinearLayout implements ImageRestorable {
    private ThemedButton mBuyButton;
    private NetworkImageView mImageView;
    private ThemedTextView mNoPriceChangeText;
    private ThemedTextView mPriceChageText;
    private View mPriceInfoContainer;
    private ThemedTextView mPriceNowText;
    private ThemedTextView mPriceWasText;
    /* access modifiers changed from: private */
    public PriceWatchItemClickListener mPriceWatchItemClickListener;
    private ThemedTextView mRemoveButton;
    private ThemedTextView mTimestampText;
    private ThemedTextView mTitle;

    public interface PriceWatchItemClickListener {
        void onBuyClick(String str, double d, double d2);

        void onOpenClick(String str);

        void onRemoveClick(String str);
    }

    public PriceWatchItemView(Context context) {
        this(context, null, 0);
    }

    public PriceWatchItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.price_watch_item_view, this, true);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.price_watch_item_title);
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.price_watch_item_image);
        this.mPriceChageText = (ThemedTextView) inflate.findViewById(R.id.price_watch_price_change_text);
        this.mNoPriceChangeText = (ThemedTextView) inflate.findViewById(R.id.price_watch_no_price_change_text);
        this.mTimestampText = (ThemedTextView) inflate.findViewById(R.id.price_watch_timestamp_text);
        this.mPriceNowText = (ThemedTextView) inflate.findViewById(R.id.price_watch_now_text);
        this.mPriceWasText = (ThemedTextView) inflate.findViewById(R.id.price_watch_was_text);
        this.mRemoveButton = (ThemedTextView) inflate.findViewById(R.id.price_watch_remove_button);
        this.mBuyButton = (ThemedButton) inflate.findViewById(R.id.price_watch_buy_button);
        this.mPriceInfoContainer = inflate.findViewById(R.id.price_watch_price_info);
    }

    public void setup(final PriceWatchItem priceWatchItem, boolean z) {
        Drawable drawable;
        this.mTitle.setText(priceWatchItem.getTitle());
        this.mImageView.setImage(priceWatchItem.getImage());
        this.mImageView.setRoundedCorners(true);
        this.mTimestampText.setText(priceWatchItem.getFormattedTimestamp());
        double priceDifference = priceWatchItem.getPriceDifference();
        if (priceDifference == 0.0d) {
            this.mNoPriceChangeText.setVisibility(0);
            this.mPriceChageText.setVisibility(8);
        } else {
            this.mNoPriceChangeText.setVisibility(8);
            int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.price_watch_price_change_arrow_height);
            if (priceDifference < 0.0d) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.price_watch_price_decrease_arrow);
                this.mPriceChageText.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.price_watch_price_decrease_color));
            } else {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.price_watch_price_increase_arrow);
                this.mPriceChageText.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.price_watch_price_increase_color));
            }
            drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            this.mPriceChageText.setCompoundDrawables(drawable, null, null, null);
            this.mPriceChageText.setText(priceWatchItem.getFormattedPriceDiff());
            this.mPriceChageText.setVisibility(0);
        }
        String string = priceWatchItem.getCurrentPrice().getValue() == 0.0d ? getResources().getString(R.string.free) : priceWatchItem.getCurrentPrice().toFormattedString();
        String string2 = priceWatchItem.getPreviousPrice().getValue() == 0.0d ? getResources().getString(R.string.free) : priceWatchItem.getPreviousPrice().toFormattedString();
        this.mPriceNowText.setText(WishApplication.getInstance().getResources().getString(R.string.now_price, new Object[]{string}));
        this.mPriceWasText.setText(WishApplication.getInstance().getResources().getString(R.string.was_price, new Object[]{string2}));
        this.mBuyButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PriceWatchItemView.this.mPriceWatchItemClickListener != null) {
                    PriceWatchItemView.this.mPriceWatchItemClickListener.onBuyClick(priceWatchItem.getProductId(), priceWatchItem.getUsdPriceDifference(), priceWatchItem.getPreviousPrice().getUsdValue());
                }
            }
        });
        AnonymousClass2 r0 = new OnClickListener() {
            public void onClick(View view) {
                if (PriceWatchItemView.this.mPriceWatchItemClickListener != null) {
                    PriceWatchItemView.this.mPriceWatchItemClickListener.onOpenClick(priceWatchItem.getProductId());
                }
            }
        };
        this.mImageView.setOnClickListener(r0);
        this.mPriceInfoContainer.setOnClickListener(r0);
        this.mTitle.setOnClickListener(r0);
        if (z) {
            this.mRemoveButton.setVisibility(8);
            LayoutParams layoutParams = new LayoutParams(this.mBuyButton.getLayoutParams());
            layoutParams.addRule(9);
            layoutParams.addRule(11, 0);
            layoutParams.setMargins(0, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding), 0, 0);
            this.mBuyButton.setLayoutParams(layoutParams);
            setPadding(0, 0, 0, 0);
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.mImageView.getLayoutParams());
            int dimensionPixelSize2 = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.twelve_padding);
            layoutParams2.setMargins(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
            this.mImageView.setLayoutParams(layoutParams2);
            this.mTitle.setPadding(0, dimensionPixelSize2, 0, 0);
            return;
        }
        this.mRemoveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PriceWatchItemView.this.mPriceWatchItemClickListener != null) {
                    PriceWatchItemView.this.mPriceWatchItemClickListener.onRemoveClick(priceWatchItem.getProductId());
                }
            }
        });
    }

    public void setListener(PriceWatchItemClickListener priceWatchItemClickListener) {
        this.mPriceWatchItemClickListener = priceWatchItemClickListener;
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

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        if (this.mImageView != null) {
            this.mImageView.setImagePrefetcher(imageHttpPrefetcher);
        }
    }
}
