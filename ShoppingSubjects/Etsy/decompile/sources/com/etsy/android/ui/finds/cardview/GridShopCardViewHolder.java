package com.etsy.android.ui.finds.cardview;

import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.ui.cardview.clickhandlers.m;
import com.etsy.android.ui.cardview.viewholders.ShopCardViewHolder.a;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class GridShopCardViewHolder extends BaseViewHolder<ShopCard> {
    private ImageViewWithAspectRatio[] imageViews = {(ImageViewWithAspectRatio) findViewById(R.id.image_1), (ImageViewWithAspectRatio) findViewById(R.id.image_2), (ImageViewWithAspectRatio) findViewById(R.id.image_3), (ImageViewWithAspectRatio) findViewById(R.id.image_4)};
    /* access modifiers changed from: private */
    public m mClickListener;
    /* access modifiers changed from: private */
    public ImageView mFavButton;
    private c mImageBatch;
    private GridLayout mImageGrid;
    private TextView mItemCount;
    private a mReviewViewHolder;
    private ImageView mShopAvatar;
    private TextView mShopName;

    public GridShopCardViewHolder(ViewGroup viewGroup, m mVar, c cVar, boolean z) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_shop_grid, viewGroup, false));
        this.mImageBatch = cVar;
        this.mShopAvatar = (ImageView) findViewById(R.id.object_avatar);
        this.mShopName = (TextView) findViewById(R.id.shop_name);
        this.mReviewViewHolder = new a(viewGroup.getContext(), (RatingIconView) findViewById(R.id.rating), (TextView) findViewById(R.id.review_count));
        this.mFavButton = (ImageView) findViewById(R.id.btn_fav);
        this.mItemCount = (TextView) findViewById(R.id.item_count);
        this.mClickListener = mVar;
        this.mImageGrid = (GridLayout) findViewById(R.id.image_grid);
        if (z) {
            this.mImageGrid.setRowCount(2);
            this.mImageGrid.setColumnCount(2);
            getRootView().getLayoutParams().width = this.itemView.getResources().getDimensionPixelSize(R.dimen.horizontal_shop_card_width);
        }
    }

    public void bind(final ShopCard shopCard) {
        int i = 0;
        while (i < shopCard.getCardListings().size() && i < this.imageViews.length) {
            this.imageViews[i].setImageInfo(((ListingLike) shopCard.getCardListings().get(i)).getListingImage(), this.mImageBatch);
            this.imageViews[i].setUseStandardRatio(true);
            i++;
        }
        this.mShopName.setText(shopCard.getShopName());
        this.mReviewViewHolder.a(shopCard);
        this.mFavButton.setImageResource(shopCard.isFavorite() ? R.drawable.ic_favorited_selector : R.drawable.ic_favorite_selector);
        this.mFavButton.setOnClickListener(new TrackingOnClickListener(new i[]{shopCard}) {
            public void onViewClick(View view) {
                if (GridShopCardViewHolder.this.mClickListener != null) {
                    GridShopCardViewHolder.this.mClickListener.a(shopCard, GridShopCardViewHolder.this.mFavButton, shopCard.isFavorite());
                }
            }
        });
        this.mImageBatch.a(shopCard.getAvatarUrl(), this.mShopAvatar);
        int activeListingCount = shopCard.getActiveListingCount();
        this.mItemCount.setText(getRootView().getContext().getResources().getQuantityString(R.plurals.item_lowercase_quantity, activeListingCount, new Object[]{Integer.valueOf(activeListingCount)}));
        getRootView().setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (GridShopCardViewHolder.this.mClickListener != null) {
                    GridShopCardViewHolder.this.mClickListener.a(shopCard, shopCard.getContentSource());
                }
            }
        });
    }

    public void recycle() {
        for (ImageViewWithAspectRatio imageDrawable : this.imageViews) {
            imageDrawable.setImageDrawable(null);
        }
        this.mShopAvatar.setImageDrawable(null);
    }
}
