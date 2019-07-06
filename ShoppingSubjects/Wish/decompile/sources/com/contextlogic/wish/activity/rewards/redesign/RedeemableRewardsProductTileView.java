package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.rewards.redesign.RedeemableRewardProductsAdapter.OnClickListener;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.FeedTileLogger.Action;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class RedeemableRewardsProductTileView extends ConstraintLayout implements ImageRestorable {
    private NetworkImageView mImage;
    private ThemedTextView mPointsRequiredBadge;
    /* access modifiers changed from: private */
    public int mPosition;
    /* access modifiers changed from: private */
    public WishProduct mProduct;
    private View mRedeemButton;
    private ThemedTextView mValueText;

    public RedeemableRewardsProductTileView(Context context) {
        this(context, null);
    }

    public RedeemableRewardsProductTileView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RedeemableRewardsProductTileView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPosition = -1;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.redeemable_rewards_product_tile, this, true);
        this.mPointsRequiredBadge = (ThemedTextView) findViewById(R.id.redeemable_rewards_product_tile_pts_badge);
        this.mValueText = (ThemedTextView) findViewById(R.id.redeemable_rewards_product_tile_value_text);
        this.mImage = (NetworkImageView) findViewById(R.id.redeemable_rewards_product_tile_image);
        this.mRedeemButton = findViewById(R.id.redeemable_rewards_product_tile_button);
    }

    public void setup(WishProduct wishProduct, final OnClickListener onClickListener, int i) {
        this.mProduct = wishProduct;
        this.mPosition = i;
        this.mPointsRequiredBadge.setText(getContext().getString(R.string.pts_amount, new Object[]{Integer.valueOf(this.mProduct.getValueInPointsForVariation(this.mProduct.getDefaultCommerceVariationId()))}));
        if (this.mProduct.getValue().getValue() > 0.0d) {
            this.mValueText.setText(getContext().getString(R.string.value_amount, new Object[]{this.mProduct.getValue().toFormattedString()}));
        } else {
            this.mValueText.setText(null);
        }
        this.mImage.setImage(this.mProduct.getImage());
        this.mImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickListener.onImageClicked(RedeemableRewardsProductTileView.this.mProduct);
                FeedTileLogger.getInstance().addToQueue(RedeemableRewardsProductTileView.this.mProduct.getLoggingFields(), Action.CLICKED, RedeemableRewardsProductTileView.this.mPosition);
            }
        });
        this.mRedeemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickListener.onRedeemItemClicked(RedeemableRewardsProductTileView.this.mProduct);
            }
        });
    }

    public void releaseImages() {
        if (this.mImage != null) {
            this.mImage.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mImage != null) {
            this.mImage.restoreImages();
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.mRedeemButton != null) {
            this.mRedeemButton.setAlpha(z ? 1.0f : 0.25f);
            this.mRedeemButton.setClickable(z);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mProduct != null && this.mPosition >= 0) {
            FeedTileLogger.getInstance().addToQueue(this.mProduct.getLoggingFields(), Action.IMPRESSION, this.mPosition);
        }
    }
}
